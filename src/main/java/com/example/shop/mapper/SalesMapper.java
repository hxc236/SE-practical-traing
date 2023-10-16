package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.SalesBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.List;
@Mapper
public interface SalesMapper extends BaseMapper<SalesBean> {

    @Select("select sum(sales) from v_sales")
    Integer Cal_Total_Sales();

    @Select("select * from v_sales limit 3")
    List<SalesBean> getSales();
}
