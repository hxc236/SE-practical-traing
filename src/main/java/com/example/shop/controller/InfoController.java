package com.example.shop.controller;


import com.example.shop.bean.InfoBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.InfoMapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Api(tags = "求购信息接口", description = "包括发布，查看操作。")
@Controller
@RequestMapping("/info")
public class InfoController extends BaseController{
    @Autowired
    InfoMapper  infoMapper;
    @RequestMapping("/addinfo")
    public VxResp addInfo(

            @RequestParam String info,
            @RequestParam String description,

            @RequestParam Integer uid
    ) {
        InfoBean infoBean = new InfoBean();
        infoBean.setInfo(info);
        infoBean.setDescription(description);
        infoBean.setCtime(new Date());
        infoBean.setUid(uid);
        VxResp vx = new VxResp();

        int result = infoMapper.insert(infoBean);
        if (result > 0) {
            vx.fail("添加成功");

        } else {

            vx.fail("添加失败");
        }

        return vx;
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest req) {

        req.setAttribute("retList", infoMapper.selectList(null));
        System.out.println(infoMapper.selectList(null));
        return "/info/list";

    }
    @RequestMapping("/del")
    public String del(int id){
        infoMapper.deleteById(id);
        return "redirect:/info/list";
    }


}
