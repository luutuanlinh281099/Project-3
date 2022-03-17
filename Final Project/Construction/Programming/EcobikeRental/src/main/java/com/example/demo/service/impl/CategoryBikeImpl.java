package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * lớp triển khai các hàm được định nghĩa trong interface InvoiceService
 *
 * @author nguyen duy hoai lam
 */
@Service
public class CategoryBikeImpl implements CategoryBikeService {
    @Autowired
    CategoryBikeRepository categoryBikeRepository;

    /**
     * tìm kiếm loại xe theo xe
     *
     * @param bike
     * @return loại xe tương ứng với xe
     */
    @Override
    public CategoryBike findByBike (Bike bike) {
        return categoryBikeRepository.findByBike(bike);
    }
}
