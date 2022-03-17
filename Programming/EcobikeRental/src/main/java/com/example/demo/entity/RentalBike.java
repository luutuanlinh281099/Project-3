package com.example.demo.entity;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * class định nghĩa một lớp dùng lưu thông tin xe được thuê
 * @author nguyễn thị liên
 */
@Entity(name = "rental_bike")
@EntityListeners (AuditingEntityListener.class)

public class RentalBike {
	/**
	 * id thông tin thuê xe
	 */
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * thời gian xe bắt đầu thuê
	 */
	@Column (name = "thoi_gian_bat_dau_thue")
	@Temporal (TemporalType.TIMESTAMP)
	@CreatedDate
	private Date timeStart;
	/**
	 * thời gian xe kết thúc thuê
	 */
	@Column (name = "thoi_gian_ket_thuc_thue")
	@Temporal (TemporalType.TIMESTAMP)
	private Date timeEnd;
	/**
	 * tiền đặt cọc khi thuê xe
	 */
	@Column (name = "tien_coc")
	private int deposits;
	/**
	 * trạng thái xe thuê đã được trả hay chưa (true: là đã trả, false: là chưa trả)
	 */
	@Column (name = "trang_thai")
	private boolean status;
	/**
	 * xe được thuê
	 */
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "bike_id")
    private Bike bike;

	public Bike getBike () {
		return bike;
	}

	public void setBike (Bike bike) {
        this.bike = bike;
    }

    public Long getId() {
		return id;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public int getDeposits () {
		return deposits;
	}

	public void setDeposits (int deposits) {
		this.deposits = deposits;
	}

	public boolean isStatus () {
		return status;
	}

	public void setStatus (boolean status) {
		this.status = status;
	}
}
