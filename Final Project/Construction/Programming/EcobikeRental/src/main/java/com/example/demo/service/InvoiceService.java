package com.example.demo.service;

import com.example.demo.entity.*;

/**
 * interface định nghĩa các hàm liên quan đến hóa đơn
 *
 * @author lưu tuấn linh
 */
public interface InvoiceService {
    Invoice findByRentalBike (RentalBike rentalBike);

    Invoice save (Invoice invoice);
}
