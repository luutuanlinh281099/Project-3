package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * lớp triển khai các hàm được định nghĩa trong interface RentalBikeService
 *
 * @author nguyễn thị liên
 */
@Service
public class RentalBikeImpl implements RentalBikeService {
    @Autowired
    RentalBikeRepository rentalBikeRepository;

    /**
     * phương thức tìm kiếm thông tin thuê xe tương ứng id xe và trạng thái đã trả hay chưa
     *
     * @param bikeId: id xe
     * @param status: trạng thái đang thuê hay đã trả
     * @return thông tin thuê xe
     */
    @Override
    public RentalBike findByBikeIdAndStatus (Long bikeId, boolean status) {
        return rentalBikeRepository.findByBikeIdAndStatus(bikeId, status);
    }

    /**
     * Lưu thông tin thuê xe vào cơ sở dữ liệu
     *
     * @param rentalBike: thông tin thuê xe cần lưu
     * @return thông tin thuê xe đã lưu
     */
    @Override
    public RentalBike save (RentalBike rentalBike) {
        return rentalBikeRepository.save(rentalBike);
    }
}
