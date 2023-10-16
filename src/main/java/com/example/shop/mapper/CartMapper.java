package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CartBean;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<CartBean> {
    @Select("select * from v_cart where uid=#{uid}")
    List<CartBean> selectList(@Param("uid")Integer uid);

    @Select("select * from v_cart where uid=#{uid} and pid=#{pid}")
    CartBean selectCart(@Param("uid")Integer uid, @Param("pid")Integer pid);

    @Select("update tbl_cart set count = #{count} where id = #{id}")
    void updateCount(@Param("count")Integer count, @Param("id")Integer id);

    @Delete("delete from tbl_cart where uid = ${uid}")
    void clearList(@Param("uid")Integer uid);
}