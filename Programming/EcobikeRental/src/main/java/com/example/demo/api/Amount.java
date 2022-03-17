package com.example.demo.api;

import java.util.*;

/**
 * class tính số tiền thuê xe
 *
 * @author nguyễn duy hoài lâm
 */
public class Amount {
    /**
     * thời gian thuê xe
     */
    public static long rentTime;

    /**
     * Phương thức giúp tính toán số tiền thuê xe
     *
     * @param start:          Thời gian bắt đầu thuê
     * @param end:            thời gian kết thúc thuê
     * @param categoryBikeId: id loại xe được để tính tiền tương ứng
     * @return rentMoney: trả về số tiền thuê xe sau khi tính toán
     */
    public static long rentMoneyBike (Date start, Date end, long categoryBikeId) {
        long rentMoney = 0;
        rentTime = (end.getTime() - start.getTime()) / 60000;
        System.out.println(rentTime);
        if (rentTime <= 10) rentMoney = 0;
        else if (rentTime <= 30) rentMoney = 10000;
        else {
            if ((rentTime - 30) % 15 != 0) rentTime = (rentTime - 30) / 15 + 1;
            else rentTime = (rentTime - 30) / 15;
            rentMoney = 10000 + rentTime * 3000;
        }
        System.out.println(rentMoney);
        if (categoryBikeId != 1) rentMoney = (long) (rentMoney * 1.5);
        return rentMoney;
    }
}
