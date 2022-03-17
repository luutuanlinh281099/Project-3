package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;

/**
 * Lớp truy vấn dữ liệu từ cơ sở dữ liệu của lịch sử thuê xe
 *
 * @author nguyễn duy hoài lâm
 */
public interface RentalBikeRepository extends JpaRepository<RentalBike, Long> {
    RentalBike findByBikeIdAndStatus (Long bikeId, boolean status);
}
