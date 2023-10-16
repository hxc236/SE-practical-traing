package com.example.shop.service;

import com.example.shop.mapper.CommentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface CommentService {
    public boolean addcomment(String comment,
                              Integer uid,
                              Integer pid);
    public boolean classifySentiment(String comment);




}
