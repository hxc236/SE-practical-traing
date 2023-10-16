package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_product")
public class ProductBean {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotNull
    private String product;
    @NotNull
    private Integer price;
    @NotNull
    private String logo;
    private String hot; // 热卖、非热卖
    private Date ctime;
    @NotNull
    private Integer num; // 库存
    private Integer uid; // user 外键 谁在卖
    private Integer cid; // 类别 外键 所属类别id

    // 这两个是从视图中查询出来的，原表中并没有，排除掉
    @TableField(exist = false)
    private String user;
    @TableField(exist = false)
    private String category;
    @TableField(exist = false)
    private String ftime;    // 默认是null
}
