package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_user")
public class UserBean {
    @TableId(type = IdType.AUTO)
    private Integer id;     // 使用Integer，包装类，有equals，可接受null
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String user;
    @NotNull
    private String status;
    private String store;
}
