package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_order")
public class OrderBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String mobile;
    @NotNull
    private String address;
    @NotNull
    private Date ctime;
    @NotNull
    private Integer uid;
    @NotNull
    private Integer total;



}
