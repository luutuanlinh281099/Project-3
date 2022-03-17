package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

/**
 * interface định nghĩa các hàm liên quan đến Bike
 *
 * @author nguyễn thị lan
 */
public interface BikeService {
    List<Bike> findBikes (Long parkingId);

    Bike findBike (Long bikeId);

    Bike saveBike (Bike bike);

    Bike findBikesByStatus (boolean status);
}
