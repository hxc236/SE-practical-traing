package com.example.shop.controller;

import com.example.shop.bean.CollectBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectMapper collectMapper;

    @ResponseBody
    @RequestMapping("/collected/vx")
    public VxResp collected(Integer uid, Integer pid) {
        VxResp vx = new VxResp();
        vx.collected = 0;
        if(collectMapper.selectCollect(uid, pid) != null) {
            vx.collected = 1;
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/change/vx")
    public VxResp change(Integer uid, Integer pid) {
        VxResp vx = new VxResp();
        if(collectMapper.selectCollect(uid, pid) != null) {
            vx.collected = 0;
            Integer id = collectMapper.selectCollect(uid, pid).getId();
            collectMapper.deleteById(id);
        } else {
            vx.collected = 1;
            CollectBean collect = new CollectBean();
            collect.setUid(uid);
            collect.setPid(pid);
            collectMapper.insert(collect);
        }
        return vx;
    }

    @ResponseBody
    @RequestMapping("/index/vx")
    public VxResp index(Integer uid) {
        VxResp vx = new VxResp();
        vx.collects = collectMapper.selectList(uid);
        return vx;
    }
}