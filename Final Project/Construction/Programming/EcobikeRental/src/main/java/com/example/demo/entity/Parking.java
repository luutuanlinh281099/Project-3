package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * class định nghĩa một lớp bãi đỗ xe
 * @author nguyễn duy hoài lâm
 */
@Entity (name = "parking")
@EntityListeners (AuditingEntityListener.class)

public class Parking {
    /**
     * id bãi xe
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * tên bãi
     */
    @Column (name = "ten_bai")
    private String nameParking;
    /**
     * địa chỉ bãi
     */
    @Column (name = "dia_chi")
    private String address;
    /**
     * tổng số ô chưa trong bãi
     */
    @Column (name = "tong_o_chua")
    private int totalCellContains;
    /**
     * số ô trống trong bãi
     */
    @Column (name = "so_o_trong")
    private int cellEmpty;
    /**
     * danh sách xe trong bãi
     */
    @OneToMany (mappedBy = "parking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch (FetchMode.SUBSELECT)
    @JsonIgnore
    private Set<Bike> bike;

    public Set<Bike> getBike () {
        return bike;
    }

    public void setBike (Set<Bike> bike) {
        this.bike = bike;
    }

    public Long getId () {
        return id;
    }

    public String getNameParking () {
        return nameParking;
    }

    public String getAddress () {
        return address;
    }

    public int getTotalCellContains () {
        return totalCellContains;
    }

    public int getCellEmpty () {
        return cellEmpty;
    }

    public void setCellEmpty (int cellEmpty) {
        this.cellEmpty = cellEmpty;
    }

}
