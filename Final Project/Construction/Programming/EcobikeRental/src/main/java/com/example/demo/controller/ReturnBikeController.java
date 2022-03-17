package com.example.demo.controller;

import com.example.demo.api.*;
import static com.example.demo.constant.Constant.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import static java.lang.Long.parseLong;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * Lớp xử lý quá trình trả xe
 *
 * @author nguyễn duy hoài lâm
 */
@Controller
public class ReturnBikeController {
    @Autowired
    BikeService bikeService;
    @Autowired
    ParkingService parkingService;
    @Autowired
    RentalBikeService rentalBikeService;
    @Autowired
    InvoiceService invoiceService;

    /**
     * lấy thông tin để xuất hóa đơn ra view
     *
     * @param parkingId: id bãi xe để trả xe vào
     * @param model:     lưu thông tin gử cho view
     * @return gọi trang view "invoice"
     */
    @GetMapping ("/returnBike")
    public String viewInvoice (@RequestParam (value = "id", required = false) Long parkingId, Model model) {
        /**
         * tìm xe đã được thuê
         */
        Bike bike = bikeService.findBikesByStatus(true);
        /**
         * tìm bãi xe sẽ trả xe vào
         */
        Parking parking = parkingService.findParking(parkingId);
        /**
         * thông tin thuê xe theo id xe cần trả với trang thái thông tin thuê xe là chưa trả
         */
        RentalBike rentalBike = rentalBikeService.findByBikeIdAndStatus(bike.getId(), false);
        /**
         * dừng đồng hồ bấm giờ mỗi lần bấm nút trả xe cập nhật lần dừng mới
         */
        rentalBike.setTimeEnd(new Date());
        /**
         * lưu thông tin thuê xe
         */
        rentalBikeService.save(rentalBike);
        /**
         * lưu số tiền thuê
         */
        long tongTienThue = Amount.rentMoneyBike(rentalBike.getTimeStart(), rentalBike.getTimeEnd(), bike.getCategoryBike().getId());
        /**
         * tìm kiến hóa đơn theo thông tin thuê xe
         */
        Invoice invoice = invoiceService.findByRentalBike(rentalBike);
        /**
         * nếu chưa có hóa đơn tương ứng sẽ khởi tạo hóa đơn mới
         */
        if (invoice == null) {
            invoice = new Invoice();
            invoice.setRentalBike(rentalBike);
        }
        /**
         * lưu tổng tiền thuê xe và lưu hóa đơn
         */
        invoice.setRentMoney(tongTienThue);
        invoiceService.save(invoice);
        /**
         * gửi toàn bộ thông tin hóa đơn đến view qua model
         */
        model.addAttribute("rental", rentalBike);
        model.addAttribute("parking", parking);
        model.addAttribute("bike", bike);
        model.addAttribute("invoice", invoice);
        model.addAttribute("tienTraXe", tongTienThue);
        model.addAttribute("rentTime", Amount.rentTime);
        return "invoice";
    }

    /**
     * phương thức xử lý quá trình giao dịch trả xe
     *
     * @param cardCode
     * @param owner
     * @param cvvCode
     * @param dateExpired
     * @param transactionContent
     * @param amount
     * @param bike
     * @param parking
     * @return kết quả giao dịch
     */
    @PostMapping ("/processTransaction/returnBike")
    @ResponseBody
    public ResponseEntity returnBike (@RequestParam (name = "cardCode", required = false) String cardCode,
                                      @RequestParam (name = "owner", required = false) String owner,
                                      @RequestParam (name = "cvvCode", required = false) String cvvCode,
                                      @RequestParam (name = "dateExpired", required = false) String dateExpired,
                                      @RequestParam (name = "transactionContent", required = false) String transactionContent,
                                      @RequestParam (name = "amount", required = false) String amount,
                                      @RequestParam (name = "bike", required = false) Bike bike,
                                      @RequestParam (name = "parking", required = false) Parking parking) {
        /**
         * thuộc tính dùng để tạo đối tượng lưu trữ thông tin giao dịch
         */
        Transaction transaction = new Transaction();
        transaction.setCommand("pay");
        transaction.setAmount(amount);
        transaction.setCardCode(cardCode);
        transaction.setCvvCode(cvvCode);
        transaction.setDateExpired(dateExpired);
        transaction.setOwner(owner);
        transaction.setTransactionContent(transactionContent);
        transaction.setCreatedAt(new Date());
        /**
         * thông tin thuê xe theo id xe cần trả với trang thái thông tin thuê xe là chưa trả
         */
        RentalBike rentalBike = rentalBikeService.findByBikeIdAndStatus(bike.getId(), false);
        /**
         * Hóa đơn với thông tin thuê xe tương ứng
         */
        Invoice invoice = invoiceService.findByRentalBike(rentalBike);
        /**
         * nếu tất cả các thông tin giao dịch đều đầy đủ thì kiểm tra số tiền trả xe
         */
        if (! cardCode.equals("") && ! owner.equals("") && ! cvvCode.equals("") && ! dateExpired.equals("") && ! transactionContent.equals("") && ! amount.equals("")) {
            /**
             * nếu không đủ thông báo lỗi
             */
            if (parseLong(amount) < invoice.getRentMoney()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(invoice.getRentMoney());
            }
            /**
             * nếu đủ
             */
            else {
                /**
                 * chuyển trang thái xe thành chưa thuê
                 */
                bike.setStatus(false);
                /**
                 * trạng thái thông tin thuê xe là đã trả
                 */
                rentalBike.setStatus(true);
                /**
                 * số ô trống trong bãi được chọn trừ đi một
                 */
                parking.setCellEmpty(parking.getCellEmpty() - 1);
            }
        }
        try {
            /**
             * gọi api xử lý giao dịch thanh toán trả xe
             */
            String apiOutput = TransactionApi.processTransaction(transaction, PAY, VERSION, APP_CODE);
            /**
             * lấy mã lỗi trả về
             */
            String errorCode = apiOutput.substring(apiOutput.indexOf("\":\"") + 3, apiOutput.indexOf("\":\"") + 5);
            /**
             * nếu mã lỗi là "00" tức giao dịch thành công
             */
            if (errorCode.equals("00")) {
                /**
                 * đưa số tiền đã cọc vào thông tin giao dịch
                 */
                transaction.setAmount(String.valueOf(rentalBike.getDeposits()));
                /**
                 * gọi api xử lý giao dịch trả cọc
                 */
                TransactionApi.processTransaction(transaction, REFUND, VERSION, APP_CODE);
                /**
                 * lưu lại thông tin xe, thuê xe, bãi xe vào cơ sở dữ liệu
                 */
                bikeService.saveBike(bike);
                rentalBikeService.save(rentalBike);
                parkingService.saveParking(parking);
            }
            return ResponseEntity.ok(apiOutput);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
