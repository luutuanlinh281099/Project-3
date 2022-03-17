package com.example.demo.service;

import com.example.demo.entity.*;

/**
 * interface định nghĩa các hàm liên quan đến category bike
 *
 * @author nguyễn duy hoài lâm
 */
public interface CategoryBikeService {
    CategoryBike findByBike (Bike bike);
}
