package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;

/**
 * Lớp truy vấn dữ liệu từ cơ sở dữ liệu của bãi xe
 *
 * @author nguyễn duy hoài lâm
 */
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Parking findParkingByBikeId (Long bikeId);
}
