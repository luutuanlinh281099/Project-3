package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 * lớp xử lý xem bãi xe
 *
 * @author nguyen thi lan
 */
@Controller
public class ViewParkingController {
    @Autowired
    ParkingService parkingService;
    @Autowired
    BikeService bikeService;

    /**
     * phương thức lấy danh sách bãi xe và kiểm tra trạng thái có xe được thuê hay chưa gửi đến view
     *
     * @param model: dùng lưu trữ thông tin gửi đến view
     * @return gọi trang view "parking"
     */
    @GetMapping ("/")
    public String allParking (Model model) {
        /**
         * tìm kiếm xe đã được thuê (không có trả về null) lưu kết quả vào bike thêm vào model
         */
        model.addAttribute("bike", bikeService.findBikesByStatus(true));
        /**
         * lấy danh sách bãi xe thêm vào model để view gọi thông qua parkings
         */
        model.addAttribute("parkings", parkingService.findAllParking());
        return "parking";
    }

    /**
     * lấy thông in bãi xe và gửi các thông tin liên quan đến bãi xe được tìm thấy theo id để hiển thi lên view
     *
     * @param model:     dùng lưu trữ thông tin gửi đến view
     * @param parkingId: id bãi xe cần tìm
     * @return gọi trang view "infor_parking"
     */
    @GetMapping ("/parking/{id}")
    public String inforParking (Model model, @PathVariable (value = "id") Long parkingId) {
        /**
         * lấy ra xe được tìm thấy theo parkingId thêm vào model để view gọi thông qua parking
         */
        Parking parking = parkingService.findParking(parkingId);
        model.addAttribute("parking", parking);
        /**
         * tìm kiếm xe đã được thuê (không có trả về null) lưu kết quả vào bike thêm vào model
         */
        model.addAttribute("bike", bikeService.findBikesByStatus(true));
        /**
         * lấy ra danh sách xe trong bãi thêm vào model để view gọi thông qua bikes
         */
        model.addAttribute("bikes", bikeService.findBikes(parkingId));
        return "infor_parking";
    }

    /**
     * tìm kiếm bãi xe theo id gửi cho view xử lý
     *
     * @param parkingId: id bãi xe cần tìm
     * @return bãi xe được tìm thấy
     */
    @GetMapping ("/findParking/{id}")
    @ResponseBody
    public Parking getParkingReturn (@PathVariable (value = "id") Long parkingId) {
        Parking parking = parkingService.findParking(parkingId);
        return parking;
    }
}
