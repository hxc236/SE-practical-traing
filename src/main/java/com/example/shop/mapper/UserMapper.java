package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper // @Repository UserMapper应该是Spring容器中的组件
public interface UserMapper extends BaseMapper<UserBean> {
    @Select("select * from tbl_user where username=#{username} and password=#{password} and status=#{status}")
    UserBean getUser(UserBean user);

    @Select("select * from tbl_user where username=#{username} and password=#{password}")
    UserBean haveUser(@Param("username") String username,@Param("password") String password);
    @Select("select * from tbl_user where username=#{username} and password = #{password}")
    UserBean getUserbylogin(@Param("username") String username,@Param("password") String password);
}
