package com.example.shop.service.impl;

import com.example.shop.bean.CommentBean;
import com.example.shop.mapper.CommentMapper;
import com.example.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean addcomment(String comment, Integer uid, Integer pid) {
        CommentBean commentBean = new CommentBean();
        boolean positive = classifySentiment(comment);
        if(positive){
            commentBean.setCommentcategory("好评");
        }else {
            commentBean.setCommentcategory("差评");
        }
        commentBean.setComment(comment);
        commentBean.setPid(pid);
        commentBean.setUid(uid);
        commentBean.setCtime(new Date());
        commentMapper.insert(commentBean);


        return true;
    }

    @Override
    public boolean classifySentiment(String comment) {
        String[] positiveKeywords = {"喜欢", "满意", "好评", "优质", "速度快", "服务好", "价格合理", "质量好"};
        String[] negativeKeywords = {"讨厌", "不满意", "差评", "糟糕", "速度慢", "服务差", "价格贵", "质量差"};

        int positiveCount = 0;
        int negativeCount = 0;

        for (String keyword : positiveKeywords) {
            if (comment.contains(keyword)) {
                positiveCount++;
            }
        }

        for (String keyword : negativeKeywords) {
            if (comment.contains(keyword)) {
                negativeCount++;
            }
        }

        if (positiveCount >= negativeCount) {
            return true;
        } else {
            return false;
        }
    }

}
