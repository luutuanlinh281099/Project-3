package com.example.demo.controller;

import com.example.demo.api.*;
import static com.example.demo.constant.Constant.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import static java.lang.Integer.parseInt;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * lớp xử lý quá trình thuê xe
 * @author nguyễn thị liên
 */
@Controller
public class RentalBikeController {
    @Autowired
    CategoryBikeService categoryBikeService;
    @Autowired
    RentalBikeService rentalBikeService;
    @Autowired
    BikeService bikeService;
    @Autowired
    ParkingService parkingService;

    /**
     * Kiểm tra mã xe có tồn tại hay không
     * @param bikeId: mã xe nhập vào
     * @param model
     * @return kết quả tìm kiếm
     */
    @PostMapping (value = "/bike")
    @ResponseBody
    public ResponseEntity checkId (@RequestParam (value = "id", required = false) Long bikeId, Model model) {
        Bike bike = bikeService.findBike(bikeId);
        model.addAttribute(bike);
        try {
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    /**
     * lấy thông tin xe được chọn để thuê gửi đến trang thanh toán cọc qua model để tiến hành giao dịch thuê xe
     * @param bikeId
     * @param model
     * @return gọi trang view "transaction"
     */
    @RequestMapping (value = "/transaction", method = RequestMethod.POST)
    public String viewTransaction (@RequestParam (value = "id", required = false) Long bikeId, Model model) {
        System.out.println(bikeId);
        Bike bike = bikeService.findBike(bikeId);
        model.addAttribute("bike", bike);
        return "transaction";
    }

    /**
     * phương thức xử lý quá trình giao dịch thuê xe
     *
     * @param cardCode
     * @param owner
     * @param cvvCode
     * @param dateExpired
     * @param transactionContent
     * @param amount
     * @param bike
     * @return kết quả giao dịch
     */
    @PostMapping ("/processTransaction/rentalBike")
    @ResponseBody
    public ResponseEntity rentalBike (@RequestParam (name = "cardCode", required = false) String cardCode,
                                        @RequestParam (name = "owner", required = false) String owner,
                                        @RequestParam (name = "cvvCode", required = false) String cvvCode,
                                        @RequestParam (name = "dateExpired", required = false) String dateExpired,
                                        @RequestParam (name = "transactionContent", required = false) String transactionContent,
                                        @RequestParam (name = "amount", required = false) String amount,
                                        @RequestParam (name = "bike", required = false) Bike bike) {
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
         * khởi tạo thông tin thuê xe
         */
        RentalBike rentalBike = new RentalBike();
        /**
         * tìm kiếm loại xe của xe muốn thuê
         */
        CategoryBike categoryBike = categoryBikeService.findByBike(bike);
        /**
         * bãi xe hiện tại đang đỗ
         */
        Parking parking = parkingService.findParkingByBikeId(bike.getId());
        /**
         * nếu tất cả các thông tin giao dịch đều đầy đủ thì kiểm tra số tiền cọc xe
         */
        if (! cardCode.equals("") && ! owner.equals("") && ! cvvCode.equals("") && ! dateExpired.equals("") && ! transactionContent.equals("") && ! amount.equals("")) {
            /**
             * nếu không đủ thông báo lỗi
             */
            if (parseInt(amount) < 0.4 * categoryBike.getPriceBike()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0.4 * categoryBike.getPriceBike());
            }
            /**
             * nếu đủ
             */
            else {
                /**
                 * lưu thông tin thuê xe gồm tiền cọc, xe được thuê, trạng thái chưa trả
                 */
                rentalBike.setDeposits(parseInt(transaction.getAmount()));
                rentalBike.setBike(bike);
                rentalBike.setStatus(false);
                /**
                 * lưu trạng thái xe đã thuê
                 */
                bike.setStatus(true);
                /**
                 * số ô trống của bãi xe mà xe được thuê đã từng đỗ tăng lên 1
                 */
                parking.setCellEmpty(parking.getCellEmpty() + 1);
            }
        }
        try {
            /**
             * gọi api xử lý giao dịch thanh toán cọc xe
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
                 * lưu thông tin xe, thông tin thuê xe, bãi xe vào cơ sở dữ liệu
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
