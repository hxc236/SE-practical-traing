package com.example.shop.bean;

import lombok.Data;

import java.util.Date;
@Data
public class CommentWithUser {
    private int id;
    private int pid;
    private int uid;
    private String comment;
    private Date ctime;
    private String commentcategory;
    private String username;
}
