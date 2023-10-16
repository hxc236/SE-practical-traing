package com.example.shop.controller;

import com.example.shop.mapper.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/data")
public class SalesController {

    @Autowired
    private SalesMapper salesMapper;

    @RequestMapping("/month_data")
    String MonthData(HttpServletRequest req){
        req.setAttribute("total_sales",salesMapper.Cal_Total_Sales());
        req.setAttribute("retList", salesMapper.getSales());
        return "/data/month_data";
    }
}
