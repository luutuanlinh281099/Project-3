package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * lớp triển khai các hàm được định nghĩa trong interface BikeService
 *
 * @author nguyễn thi lan
 */
@Service
public class BikeImpl implements BikeService {
    @Autowired
    BikeRepository bikeRebository;

    /**
     * tìm kiếm tất cả các xe trong bãi xe theo id bãi xe
     *
     * @param parkingId: id bãi xe
     * @return danh sách xe trong bãi
     */
    @Override
    public List<Bike> findBikes (Long parkingId) {
        return bikeRebository.findBikeByParkingId(parkingId);
    }

    /**
     * tìm kiếm xe theo id
     *
     * @param bikeId
     * @return trả về bike nều tìn thấy và gọi ResourceNotFoundException nếu không tìm thấy
     */
    @Override
    public Bike findBike (Long bikeId) {
        return bikeRebository.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException("bike", "id", bikeId));
    }

    /**
     * lưu thông tin xe vào cơ sở dữ liệu
     *
     * @param bike
     * @return xe được lưu
     */
    @Override
    public Bike saveBike (Bike bike) {
        return bikeRebository.save(bike);
    }

    /**
     * tìm kiếm xe theo trạng thái đang được thuê hay chưa
     *
     * @param status
     * @return
     */
    @Override
    public Bike findBikesByStatus (boolean status) {
        return bikeRebository.findBikeByStatus(status);
    }
}
