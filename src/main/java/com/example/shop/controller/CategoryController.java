package com.example.shop.controller;

import com.example.shop.bean.CategoryBean;
import com.example.shop.bean.ProductBean;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping("/list")
    public String list(HttpServletRequest req) {

        req.setAttribute("retList", categoryMapper.selectList(null));
        return "/category/list";
    }

    @RequestMapping("/del")
    public String del(int id) {

        categoryMapper.deleteById(id);
        return "redirect:/category/list";
    }

    // @RequestMapping是什么请求都可以进
    @GetMapping("/add")
    public String add(Integer id, HttpServletRequest req) {
        req.setAttribute("cateList", categoryMapper.selectList(null));
        req.setAttribute("bean", id == null ? null : categoryMapper.selectById(id));
        return "/category/add";
    }

    @PostMapping("add")
    public String add(CategoryBean bean, HttpServletResponse resp) {

        if(NotNullUtil.isBlank(bean)) {

            return jsAlert("请完善信息！", resp);
        }
        try {
            if(bean.getId() == null) {
                categoryMapper.insert(bean);
            } else {
                categoryMapper.updateById(bean);
            }
        } catch (Exception e) {
            return jsAlert("类别名称不能重复！", resp);
        }
        return "redirect:/category/list";
    }
}
