package com.example.shop.bean;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class BuyerOrder {
    // 商品名称
    private String product;
//   商品图片 、
    private String logo;
//    下单时间
    private Date ctime;
    //    购买数量
    private Integer count;
//    购买该物品花费的总金额
    private BigInteger totalPrice;
}
