package com.example.shop.bean;

import java.util.List;

// 返回给小程序的东西都在这里定义
public class VxResp {
    // 1 成功 0 失败
    public int code = 1;    // 默认成功
    // 错误提示语
    public String msg;      // 默认没有内容

    public String uid = ""; // 给小程序的uid

    public ProductBean product; // 给小程序的商品详情

    public List<ProductBean> hots;

    public List<CategoryBean> categorys;

    public List<ProductBean> products;
    public List<CommentBean> comments;

    public List<OrderBean> order;

    public List<CartBean> cart;

    public List<CollectBean> collects;
    public List<CommentWithUser> commentWithUsers;
    public UserBean userBean;

    public Integer collected;
    public List<BuyerOrder> buyer_orders;

    // 失败函数
    public void fail(String msg) {
        this.code = 0;
        this.msg = msg;
    }

}
