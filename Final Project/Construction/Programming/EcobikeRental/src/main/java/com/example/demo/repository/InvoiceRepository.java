package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.*;

/**
 * Lớp truy vấn dữ liệu từ cơ sở dữ liệu của hóa đơn
 * @author nguyễn duy hoài lâm
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByRentalBike(RentalBike rentalBike);
}
