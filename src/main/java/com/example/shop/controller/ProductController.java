package com.example.shop.controller;

import com.example.shop.bean.ProductBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;


    @ResponseBody
    @RequestMapping("/selectById/vx")
    public VxResp selectById(Integer id) {
        VxResp vx = new VxResp();
        vx.product = productMapper.selectById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        vx.product.setFtime(sdf.format(vx.product.getCtime()));
        return vx;
    }

    @ResponseBody
    @RequestMapping("/index/vx")
    public VxResp index(Integer cid) {
        VxResp vx = new VxResp();
        vx.categorys = categoryMapper.selectList(null);
        vx.hots =  productMapper.selectHot();
        if(cid == null && !vx.categorys.isEmpty()) {
            // cid 没有, 类别数组有
            cid = vx.categorys.get(0).getId();
        }
        if(cid == null) {
            // 数据库是空的
            vx.products = new ArrayList<>();    // 空的商品数组
        } else {    // 有类别，按照类别id查询这个商品的类别列表
            vx.products = productMapper.selectProduct(cid);
        }
        // sdf 时间格式化工具          年月日时分
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(ProductBean bean : vx.products) {
            bean.setFtime(sdf.format(bean.getCtime()));
        }
        return vx;
    }

    // localhost:8080/product/list?uid=
    @RequestMapping("/list")
    public String list(int uid, HttpServletRequest req) {

        req.setAttribute("retList", productMapper.selectList(uid));
        return "/product/list";
    }

    @RequestMapping("/all")
    public String all(HttpServletRequest req) {
        req.setAttribute("retList", productMapper.selectView());
        return "/product/all";
    }

    @RequestMapping("/del")
    public String del(int id) {
        int uid = productMapper.selectById(id).getUid();
        productMapper.deleteById(id);
        return "redirect:/product/list?uid=" + uid;
    }

    // @RequestMapping是什么请求都可以进
    @GetMapping("/add")
    public String add(Integer id, HttpServletRequest req) {
        System.out.println("123");
        req.setAttribute("cateList", categoryMapper.selectList(null));
        req.setAttribute("bean", id == null ? null : productMapper.selectById(id));
        return "/product/add"; // 转发 templates/product/add.html
    }

    @PostMapping("/add")
    public String add(ProductBean bean, HttpServletResponse resp) {

        if(NotNullUtil.isBlank(bean)) {
            // price product logo num 只要有一个没写
            return jsAlert("请完善信息！", resp);
        }
        if(bean.getId() == null) {  // 如果bean的
            bean.setCtime(new Date());
            productMapper.insert(bean);
        } else {
            productMapper.updateById(bean);
        }
        return "redirect:/product/list?uid=" + bean.getUid();
    }
}
