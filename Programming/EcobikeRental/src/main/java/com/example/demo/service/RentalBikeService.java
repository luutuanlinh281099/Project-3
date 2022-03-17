package com.example.demo.service;

import com.example.demo.entity.*;

/**
 * interface định nghĩa các hàm liên quan đến thông tin thuê xe
 *
 * @author nguyễn thị liên
 */
public interface RentalBikeService {
    RentalBike findByBikeIdAndStatus (Long bikeId, boolean status);

    RentalBike save (RentalBike rentalBike);
}
