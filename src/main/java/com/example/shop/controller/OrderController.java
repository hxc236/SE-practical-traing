package com.example.shop.controller;

import com.example.shop.bean.*;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.mapper.ShoppingMapper;
import com.example.shop.mapper.UserMapper;
import com.example.shop.util.NotNullUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    @Autowired
    private UserMapper userMapper;

//    展示对应商家的订单列表
    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        System.out.println("进入订单列表");
        req.setAttribute("retList", orderMapper.selectList());
        return "/order/list";
    }
//  订单删除
    @RequestMapping("/del")
    public String del(int id) {
        orderMapper.deleteById(id);
        return "redirect:/order/list";
    }
// 订单更新
    @GetMapping("/add")
    public String add(Integer id, HttpServletRequest req) {
        req.setAttribute("bean", orderMapper.selectById(id));
        return "/order/add"; // 转发 templates/order/add.html
    }


    @PostMapping("add")
    public String add(OrderBean bean, HttpServletResponse resp) {
        System.out.println(bean);
        System.out.println("post表单进来的");
        if(bean.getMobile() == null || bean.getAddress() == null) {
            // price product logo num 只要有一个没写
            return jsAlert("请完善信息！", resp);
        }
        orderMapper.updateById(bean);
        System.out.println(bean.getUid());
        return "redirect:/order/list";
    }
//    接收小程序发来的数据更新数据
    @ResponseBody
    @RequestMapping("/add/vx")
    public VxResp add(@RequestBody List<Order> orders) {
        VxResp vx = new VxResp();
        OrderBean orderBean = new OrderBean();
        orderBean.setAddress(orders.get(0).getAddress());
        orderBean.setMobile(orders.get(0).getMobile());
        orderBean.setTotal(orders.get(0).getTotal());
        orderBean.setUid(orders.get(0).getUid());
        orderBean.setCtime(new Date());
        orderBean.setName(userMapper.selectById(orders.get(0).getUid()).getUsername());
        orderMapper.insert(orderBean);
        int oid_ = orderBean.getId();
        System.out.println(oid_);
        for(Order order : orders){
           ShoppingBean shoppingBean = new ShoppingBean();
           shoppingBean.setCount(order.getCount());
           shoppingBean.setPid(order.getPid());
           shoppingBean.setOid(oid_);
           shoppingMapper.insert(shoppingBean);
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/index/vx")
    public VxResp index(Integer uid){
        VxResp vx = new VxResp();
        System.out.println("****");
        vx.buyer_orders = orderMapper.selectBuyerList(uid);
        System.out.println(orderMapper.selectBuyerList(uid));
        return vx;
    }

//    @ResponseBody
//    @RequestMapping("/add/vx")
//    public VxResp add(CartBean cart) {
//        VxResp vx = new VxResp();
//        if(cart.getCount() > 0) {
//            orderMapper.insert();
//        } else {
//            vx.code = 0;
//            vx.msg = "数量必须大于0";
//        }
//        return vx;
//    }
//
//    @ResponseBody
//    @RequestMapping("/delete/vx")
//    public VxResp delete(Integer id) {
//        VxResp vx = new VxResp();
//        if(orderMapper.selectById(id) != null) {
//            orderMapper.deleteById(id);
//        } else {
//            vx.code = 0;
//            vx.msg = "记录不存在";
//        }
//        return vx;
//    }
//
//    @ResponseBody
//    @RequestMapping("/update/vx")
//    public VxResp update(Integer id, Integer addition) {
//        VxResp vx = new VxResp();
//        CartBean cartBean = orderMapper.selectById(id);
//        int count = cartBean.getCount() + addition;
//        if (count > 0) {
//            cartMapper.updateCount(count, id);
//        } else {
//            vx.code = 0;
//            vx.msg = "至少购买一个";
//        }
//        return vx;
//    }

}

