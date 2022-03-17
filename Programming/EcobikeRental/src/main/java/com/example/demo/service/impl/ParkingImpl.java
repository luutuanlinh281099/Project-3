package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * lớp triển khai các hàm được định nghĩa trong interface ParkingService
 *
 * @author nguyễn thị lan
 */
@Service
public class ParkingImpl implements ParkingService {

    @Autowired
    ParkingRepository parkingRebository;

    /**
     * tìm kiếm tất cả bãi xe
     *
     * @return danh sách bãi xe
     */
    @Override
    public List<Parking> findAllParking () {
        return parkingRebository.findAll();
    }

    /**
     * tìm kiếm bãi xe theo id
     *
     * @param parkingId: id bãi xe
     * @return bãi xe nếu tìm thấy và gọi ResourceNotFoundException nếu không tìm thấy
     */
    @Override
    public Parking findParking (Long parkingId) {
        return parkingRebository.findById(parkingId).orElseThrow(()->new ResourceNotFoundException("parking", "id", parkingId));
    }

    /**
     * tìm kiếm bãi xe theo id xe liên quan đến bãi
     *
     * @param bikeId: id xe
     * @return bãi xe
     */
    @Override
    public Parking findParkingByBikeId (Long bikeId) {
        return parkingRebository.findParkingByBikeId(bikeId);
    }

    /**
     * lưu thông tin bãi xe vào cơ sở dữ liệu
     *
     * @param parking: bãi xe cần được lưu
     * @return bãi xe đã lưu
     */
    @Override
    public Parking saveParking (Parking parking) {
        return parkingRebository.save(parking);
    }


}
