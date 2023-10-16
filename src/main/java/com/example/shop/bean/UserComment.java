package com.example.shop.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserComment {
    private int id;//c.id
    private String product;
    private String comment;
    private Date ctime;
    String commentcategory;

}
