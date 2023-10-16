package com.example.shop.controller;


import com.example.shop.bean.CommentBean;
import com.example.shop.bean.UserBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.CommentMapper;
import com.example.shop.mapper.UserMapper;

import com.example.shop.security.MD5Utils;
import com.example.shop.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(tags = "评论接口", description = "包括查询用户评论，商品评论，添加评论，删除评论。")
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping("/vx")
    public VxResp showComments(Integer pid) {
        VxResp vx = new VxResp();
        vx.commentWithUsers  =commentMapper.getCommentWithUserById(pid);
//        vx.comments = commentMapper.selectCommentById(pid);
        return vx;

    }

    @ResponseBody
    @RequestMapping("/vx/usercomment")
    public VxResp showUserComments(Integer uid) {
        VxResp vx = new VxResp();
        vx.comments = commentMapper.selectCommentByUid(uid);
        return vx;

    }

    @ResponseBody
    @RequestMapping("/add")
    public VxResp addComment(
            @RequestParam String comment,
            @RequestParam Integer uid,
            @RequestParam Integer pid) {


        VxResp vx = new VxResp();
        if (commentService.addcomment(comment, uid,pid)) {

            vx.fail("评论成功！");
            vx.code=1;
            return vx;
        } else {
            vx.fail("服务器发生错误！");
        }
        return vx;

    }
    @RequestMapping("/list")
    public String list(int uid,HttpServletRequest req) {
        UserBean userBean ;
        userBean = userMapper.selectById(uid);
        if(userBean.getUser().equals("admin")){
            req.setAttribute("retList",commentMapper.selectList(null));
            return "/comment/all";
        }
        req.setAttribute("retList", commentMapper.getProductCommentsBySellerId(uid));

        return "/comment/list";
        //
    }
    @RequestMapping("/del")
    public String del(int id){
        CommentBean commentBean = commentMapper.selectById(id);
//        System.out.println(userBean.getId());
        commentMapper.deleteById(id);

        return "redirect:/comment/list?uid="+ 1;
    }



}
