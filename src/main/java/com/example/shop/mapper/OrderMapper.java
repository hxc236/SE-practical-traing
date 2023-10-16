package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.BuyerOrder;
import com.example.shop.bean.OrderBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mockito.internal.matchers.Or;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Mapper
public interface OrderMapper extends BaseMapper<OrderBean> {


    @Select("select * from tbl_order order by ctime desc")
    List<OrderBean> selectList();

    @Select("SELECT *\n" +
            "FROM v_order\n" +
            "WHERE uid = #{uid}\n" +
            "  AND product IS NOT NULL AND product <> ''\n" +
            "  AND logo IS NOT NULL AND logo <> ''\n" +
            "  AND count IS NOT NULL AND count <> ''\n" +
            "  AND total_price IS NOT NULL AND total_price <> ''\n")
    List<BuyerOrder> selectBuyerList(@Param("uid") int uid);

//    @Select("select seller from v_order where id=#{id}")
//    Integer selectSeller(@Param("id") Integer id);
//    @Select("select * from v_order where uid=#{uid} order by ctime desc")
//    List<OrderBean> selectBuyList(@Param("uid") int uid);



}
