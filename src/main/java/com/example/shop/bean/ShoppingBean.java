package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@TableName("tbl_shopping")
public class ShoppingBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private Integer count;
    @NotNull
    private Integer pid;
    @NotNull
    private Integer oid;
}
