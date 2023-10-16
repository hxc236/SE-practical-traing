package com.example.shop.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.shop.bean.CartBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
    @Autowired
    CartMapper cartMapper;

    @ResponseBody
    @RequestMapping("/index/vx")
    public VxResp index(Integer uid) {
        VxResp vx = new VxResp();
        vx.cart = cartMapper.selectList(uid);
        return vx;
    }

    @ResponseBody
    @RequestMapping("/add/vx")
    public VxResp add(CartBean cart) {
        VxResp vx = new VxResp();
        CartBean oldCart = cartMapper.selectCart(cart.getUid(), cart.getPid());
        if(oldCart != null) {
            oldCart.setCount(cart.getCount() + oldCart.getCount());
            cartMapper.updateById(oldCart);
        } else {
            cartMapper.insert(cart);
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/delete/vx")
    public VxResp delete(Integer id) {
        VxResp vx = new VxResp();
        if(cartMapper.selectById(id) != null) {
            cartMapper.deleteById(id);
        } else {
            vx.code = 0;
            vx.msg = "记录不存在";
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/clear/vx")
    public VxResp clear(Integer uid) {
        VxResp vx = new VxResp();
        cartMapper.clearList(uid);
        return vx;
    }

    @ResponseBody
    @RequestMapping("/update/vx")
    public VxResp update(Integer id, Integer addition) {
        VxResp vx = new VxResp();
        CartBean cartBean = cartMapper.selectById(id);
        int count = cartBean.getCount() + addition;
        if (count > 0) {
            cartMapper.updateCount(count, id);
        } else {
            vx.code = 0;
            vx.msg = "至少购买一个";
        }
        return vx;
    }
}