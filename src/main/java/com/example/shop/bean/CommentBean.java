package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.shop.util.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tbl_comment")
public class CommentBean {
    @TableId(type = IdType.AUTO)
    int id;
    @NotNull
    int pid;

    @NotNull
    int uid;
    @NotNull
    String comment;

    Date ctime;
    String commentcategory;


}
