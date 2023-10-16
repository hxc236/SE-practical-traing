package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.CartBean;
import com.example.shop.bean.CollectBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CollectMapper extends BaseMapper<CollectBean> {
    @Select("select * from v_collect where uid=#{uid}")
    List<CollectBean> selectList(@Param("uid")Integer uid);

    @Select("select * from v_collect where uid=#{uid} and pid=#{pid}")
    CartBean selectCollect(@Param("uid")Integer uid, @Param("pid")Integer pid);
}
