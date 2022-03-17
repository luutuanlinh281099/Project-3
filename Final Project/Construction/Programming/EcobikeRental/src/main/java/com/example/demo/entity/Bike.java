package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * Class định nghĩa một lớp bike
 *
 * @author nguyễn duy hoài lâm
 */
@Entity (name = "bike")
@EntityListeners (AuditingEntityListener.class)
@JsonIgnoreProperties (value = {"rentalBikes"})
public class Bike {
    /**
     * id xe
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * thuộc tính dung lượng pin xe với xe dùng điện
     */
    @Column (name = "dung_luong_pin")
    private float energy;
    /**
     * bãi xe nơi xe được đỗ
     */
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "parking_id")
    private Parking parking;
    /**
     * loại xe
     */
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "category_bike_id")
    private CategoryBike categoryBike;
    /**
     * trạng thái xe được thuê hay chưa ( true là đã thuê, false là chưa thuê)
     */
    @Column (name = "trang_thai_thue")
    private boolean status;
    /**
     * thông tin thuê xe
     */
    @OneToMany (mappedBy = "bike", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch (FetchMode.SUBSELECT)
    @JsonIgnore
    private Set<RentalBike> rentalBikes;

    public Parking getParking () {
        return parking;
    }

    public void setParking (Parking parking) {
        this.parking = parking;
    }

    public Long getId () {
        return id;
    }

    public float getEnergy () {
        return energy;
    }

    public void setEnergy (float energy) {
        this.energy = energy;
    }

    public boolean isStatus () {
        return status;
    }

    public void setStatus (boolean status) {
        this.status = status;
    }

    public CategoryBike getCategoryBike () {
        return categoryBike;
    }

    public Set<RentalBike> getRentalBikes () {
        return rentalBikes;
    }

    public void setRentalBikes (Set<RentalBike> rentalBikes) {
        this.rentalBikes = rentalBikes;
    }
}
