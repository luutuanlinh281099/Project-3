package com.example.demo.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Bike;

/**
 * Lớp truy vấn dữ liệu từ cơ sở dữ liệu của bike
 * @author nguyễn duy hoài lâm
 */
public interface BikeRepository extends JpaRepository<Bike, Long>{
    List<Bike> findBikeByParkingId(Long parkingId);
    Bike findBikeByStatus(boolean status);
}
