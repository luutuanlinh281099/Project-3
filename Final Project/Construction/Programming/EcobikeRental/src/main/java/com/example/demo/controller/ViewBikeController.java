package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * lớp xử lý xem xe
 * @author nguyen thi lan
 */
@Controller
public class ViewBikeController {
    @Autowired
    BikeService bikeService;

    /**
     * Tìm kiếm xe để lấy thông tin theo id xe
     * @param bikeId: id xe
     * @return xe được tìm thấy
     */
    @GetMapping ("/bike/{id}")
    @ResponseBody
    public Bike inforBike (@PathVariable (value = "id") Long bikeId) {
        Bike bike = bikeService.findBike(bikeId);
        return bike;
    }
}
