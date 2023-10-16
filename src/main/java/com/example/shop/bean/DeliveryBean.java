package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_delivery")
public class DeliveryBean {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer oid; // 订单id
    private Integer sid; // 商品id
    private String company;
    private Date dtime;

    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String address;
    @TableField(exist = false)
    private Date ctime;
    @TableField(exist = false)
    private String product;
    @TableField(exist = false)
    public Integer uid;
}
