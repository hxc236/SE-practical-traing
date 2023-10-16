package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_collect")
public class CollectBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private Integer pid; // 商品id
    @NotNull
    private Integer uid; // 用户id

    @TableField(exist = false)
    private String product;
    @TableField(exist = false)
    private Integer price;
    @TableField(exist = false)
    private String logo;
}