package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.ShoppingBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface ShoppingMapper extends BaseMapper<ShoppingBean> {
    @Select("select * from tbl_shopping where oid=#{oid}")
    ShoppingBean selectShopping(@Param("oid")Integer oid);
}
