package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CommentBean;
import com.example.shop.bean.CommentWithUser;
import com.example.shop.bean.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<CommentBean> {


    @Select("SELECT c.id, c.pid, c.uid, c.comment, c.ctime, p.id " +
            "FROM tbl_comment c " +
            "JOIN tbl_product p ON c.pid = p.id " +
            "WHERE p.id = #{id}")
    List<CommentBean> selectCommentById(@Param("id") int id);

    @Select("SELECT p.id, p.product, c.comment, c.ctime, c.commentcategory " +
            "FROM v_product p " +
            "LEFT JOIN tbl_comment c ON p.id = c.pid " +
            "WHERE p.uid = #{sellerId} AND c.comment IS NOT NULL " +
            "ORDER BY p.ctime DESC, c.ctime DESC")
    List<UserComment> getProductCommentsBySellerId(@Param("sellerId") int sellerId);


    @Select("SELECT uid FROM tbl_comment WHERE id = #{id}")
    int findUidById(int id);
    @Select("SELECT * FROM tbl_comment WHERE uid = #{uid}")
    List<CommentBean> selectCommentByUid(Integer uid);

    @Select("SELECT c.*, u.username " +
            "FROM tbl_comment c " +
            "JOIN tbl_user u ON c.uid = u.id " +
            "WHERE c.pid = #{pid}")
    List<CommentWithUser> getCommentWithUserById(@Param("pid") int pid);
}
