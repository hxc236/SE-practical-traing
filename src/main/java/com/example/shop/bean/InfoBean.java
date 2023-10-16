package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.example.shop.util.NotNull;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("tbl_info")
public class InfoBean {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotNull
    private String info;
    @NotNull
    private String description;


    private Date ctime;


    private Integer uid;

    // getters and setters
}
