package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.DeliveryBean;
import com.example.shop.bean.ShoppingBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeliveryMapper extends BaseMapper<DeliveryBean> {
    @Select("select * from v_delivery where uid=#{uid} order by ctime desc")
    List<DeliveryBean> selectUid(@Param("uid") Integer uid);

    @Select("select * from v_delivery where id=#{id}")
    DeliveryBean selectId(@Param("id") Integer id);

    @Select("select * from v_delivery")
    List<DeliveryBean> selectAll();
}
