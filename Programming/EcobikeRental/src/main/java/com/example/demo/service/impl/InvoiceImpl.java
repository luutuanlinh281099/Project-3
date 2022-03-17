package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * lớp triển khai các hàm được định nghĩa trong interface InvoiceService
 *
 * @author lưu tuấn linh
 */
@Service
public class InvoiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    /**
     * tìm kiếm hóa đơn theo thông tin thuê xe
     *
     * @param rentalBike: thông tin thuê xe
     * @return hóa đơn
     */
    @Override
    public Invoice findByRentalBike (RentalBike rentalBike) {
        return invoiceRepository.findByRentalBike(rentalBike);
    }

    /**
     * lưu thông tin hóa đơn vào cơ sở dữ liệu
     *
     * @param invoice: hóa đơn cần được lưu
     * @return hóa đơn đã lưu
     */
    @Override
    public Invoice save (Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
