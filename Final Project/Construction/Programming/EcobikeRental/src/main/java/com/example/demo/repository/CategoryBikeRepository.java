package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;

/**
 * Lớp truy vấn dữ liệu từ cơ sở dữ liệu của category bike
 * @author nguyễn duy hoài lâm
 */
public interface CategoryBikeRepository extends JpaRepository<CategoryBike, Long> {
    CategoryBike findByBike(Bike bike);
}
