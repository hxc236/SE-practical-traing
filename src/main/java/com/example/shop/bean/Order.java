package com.example.shop.bean;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;
//[
//        {
//        "uid": "3",
//        "mobile": "1111",
//        "address": "李四的地址",
//        "total": "10",
//        "pid": "1",
//        "count": "2"
//        }
//]
@Data
public class Order{
//    买家id
    private Integer uid;
//    private String name = "";
//    买家电话
    private String mobile;
//    买家地址
    private String address;
//    总额
    private Integer total;
//  商品id
    private Integer pid;
//    商品数量
    private Integer count;
}
