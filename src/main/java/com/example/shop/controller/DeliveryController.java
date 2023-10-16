package com.example.shop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shop.bean.DeliveryBean;
import com.example.shop.bean.OrderBean;
import com.example.shop.bean.ProductBean;
import com.example.shop.bean.ShoppingBean;
import com.example.shop.mapper.DeliveryMapper;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.mapper.ShoppingMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DeliveryController extends BaseController{
    @Autowired
    DeliveryMapper deliveryMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ShoppingMapper shoppingMapper;
    @Autowired
    ProductMapper productMapper;

    @RequestMapping("/list")
    public String list(Integer uid, HttpServletRequest req) {
        req.setAttribute("retList", deliveryMapper.selectUid(uid));
        return "/delivery/list";
    }

    @RequestMapping("/table")
    public String table(){
        return "/delivery/table";
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<DeliveryBean> all(Integer page, Integer limit) {
        IPage<DeliveryBean> deliveryBeanIPage = new Page<>(page, limit);
        IPage<DeliveryBean> result = deliveryMapper.selectPage(deliveryBeanIPage, null);
        List<DeliveryBean> deliveryBeanList = result.getRecords();
        for(DeliveryBean delivery : deliveryBeanList) {
            OrderBean order = orderMapper.selectById(delivery.getOid());
            delivery.setAddress(order.getAddress());
            delivery.setName(order.getName());
            delivery.setCtime(order.getCtime());
            ShoppingBean shopping = shoppingMapper.selectById(delivery.getSid());
            ProductBean product = productMapper.selectById(shopping.getPid());
            delivery.setProduct(product.getProduct());
        }
        return deliveryBeanList;
    }

    @RequestMapping("/del")
    public String del(int id) {
        int uid = deliveryMapper.selectId(id).getUid();
        deliveryMapper.deleteById(id);
        return "redirect:/delivery/list?uid=" + uid;
    }

    @GetMapping("/add")
    public String add(Integer id, HttpServletRequest req) {
        System.out.println(deliveryMapper.selectId(id));
        req.setAttribute("bean", id == null ? null : deliveryMapper.selectId(id));
        return "/delivery/add"; // 转发 templates/order/add.html
    }

    @PostMapping("/add")
    public String add(DeliveryBean bean, HttpServletResponse resp) {
        if(NotNullUtil.isBlank(bean)) {
            return jsAlert("请完善信息！", resp);
        }
        System.out.println(bean);
        deliveryMapper.updateById(bean);
        return "redirect:/delivery/list?uid=" + bean.getUid();
    }
}
