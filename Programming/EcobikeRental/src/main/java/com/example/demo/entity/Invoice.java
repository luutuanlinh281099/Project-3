package com.example.demo.entity;

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * class định nghĩa một lớp hóa đơn
 * @author nguyễn thị lan
 */
@Entity (name = "invoice")
@EntityListeners (AuditingEntityListener.class)
public class Invoice {
	/**
	 * id hóa đơn
	 */
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * thông tin thuê xe để tạo hóa đơn tương ứng
	 */
	@OneToOne
	@JoinColumn(name = "rental_bike_id")
	private RentalBike rentalBike;
	/**
	 * tổng số tiền thuê xe sau khi tính toán
	 */
	@Column (name = "tong_tien_thue")
	private long rentMoney;

	public Long getId() {
		return id;
	}

	public long getRentMoney () {
		return rentMoney;
	}

	public void setRentMoney (long rentMoney) {
		this.rentMoney = rentMoney;
	}

	public RentalBike getRentalBike () {
		return rentalBike;
	}

	public void setRentalBike (RentalBike rentalBike) {
		this.rentalBike = rentalBike;
	}
}
