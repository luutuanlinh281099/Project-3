package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.*;

/**
 * class định nghía một lớp các loại xe
 * @author nguyễn duy hoài lâm
 */
@Entity (name = "category_bike")
@EntityListeners (AuditingEntityListener.class)
public class CategoryBike {
    /**
     * id loại xe
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * tên loại xe
     */
    @Column (name = "loai_xe")
    private String categoryBike;
    /**
     * giá tương tứng với từng loại xe
     */
    @Column (name = "gia_xe")
    private int priceBike;
    /**
     * ảnh loại xe
     */
    @Column(name="image")
    private String image;
    /**
     * danh sách các xe có cung loại
     */
    @OneToMany(mappedBy = "categoryBike", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Bike> bike;

    public Long getId () {
        return id;
    }

    public String getCategoryBike () {
        return categoryBike;
    }

    public Set<Bike> getBike () {
        return bike;
    }

    public void setBike (Set<Bike> bike) {
        this.bike = bike;
    }

    public int getPriceBike () {
        return priceBike;
    }

    public String getImage () {
        return image;
    }

}
