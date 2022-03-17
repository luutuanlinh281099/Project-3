package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

/**
 * interface định nghĩa các hàm liên quan đến bãi đỗ xe
 *
 * @author nguyễn thị lan
 */
public interface ParkingService {
    List<Parking> findAllParking ();

    Parking findParking (Long parkingId);

    Parking findParkingByBikeId (Long bikeId);

    Parking saveParking (Parking parking);
}
