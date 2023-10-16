package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.lang.reflect.Type;

@Data
public class SalesBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private Integer total_sales;
    @NotNull
    private String product;
    @NotNull
    private Integer sales;
    @NotNull
    private Integer number;

}
