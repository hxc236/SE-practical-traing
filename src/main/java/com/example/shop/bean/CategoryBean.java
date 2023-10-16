package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_category")
public class CategoryBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private String category;
}
