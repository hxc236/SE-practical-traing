package com.example.shop.controller;

import com.example.shop.bean.UserBean;
import com.example.shop.bean.VxResp;
import com.example.shop.mapper.UserMapper;
import com.example.shop.security.MD5Utils;
import com.example.shop.service.AccountService;
import com.example.shop.service.VerifyService;
import com.example.shop.util.FileUtil;
import com.example.shop.util.NotNullUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
@Api(tags = "账户验证接口", description = "包括用户登录、注册、验证码请求等操作。")
@Controller // 使类成为控制器
public class UserController {

    @Autowired      // 注入，就像new
    UserMapper userMapper;
    @ResponseBody
    @RequestMapping("/userinfo/vx")
    public VxResp userinfo(int uid){
        UserBean userBean;
        userBean = userMapper.selectById(uid);
        VxResp vxResp = new VxResp();
        vxResp.userBean = userBean;
        return vxResp;
    }

    @ResponseBody       // 把java对象转成字符串
    @RequestMapping("/login/vx")
    public VxResp loginvx(UserBean userBean) {
        VxResp vx = new VxResp();

        if(userMapper.getUserbylogin(userBean.getUsername(),userBean.getPassword()) != null) // 有账号，验证密码
        {

            UserBean user = userMapper.getUserbylogin(userBean.getUsername(),userBean.getPassword() );
            if(user == null) {
                vx.fail("您的密码错误！");
            } else {
                // 登录成功的这个人的id返回给小程序
                vx.uid = String.valueOf(user.getId());
            }
        }
        else {
           vx.fail("请先注册！");
        }
        // 有这个人直接登录
        // 没有这个人就注册后登录
        return vx;  // 把对象转成字符串返回给Java
    }
    @Autowired
     VerifyService verifyService;

    @Autowired
     AccountService accountService;
    @ResponseBody
    @RequestMapping("/verify-code")
    public VxResp verifyCode(@RequestParam("email") String email){
        VxResp vx = new VxResp();
        try {
            verifyService.sendVerifyCode(email);
            System.out.println("发送成功");
            vx.fail("邮箱发送成功！");
            vx.code=1;
            return vx;
        } catch (Exception e) {
            vx.fail("邮箱发送失败！");
        }
        return vx;
    }
    @ResponseBody
    @RequestMapping("/register/vx")
    public VxResp register(String username, String password, String email, String verify){
        System.out.println("进入注册");
        VxResp vx = new VxResp();
        if (verifyService.doVerify(email, verify)) {
            accountService.createAccount(username, MD5Utils.inputPassToFormPass(password));
            vx.fail("注册成功！");
            vx.code=1;
            return vx;
        } else {
            vx.fail("验证码错误！");
        }
        return vx;
    }

    // localhost:/login?username=李四&password=111111
    @RequestMapping("/login")
    public String login(UserBean userBean ) throws Exception {
        //利用MD5Utils工具进行加密
        String password_ = MD5Utils.inputPassToFormPass(userBean.getPassword());
        userBean.setStatus("卖家");
        UserBean user = userMapper.haveUser(userBean.getUsername(),userBean.getPassword());

        if(user == null ) {
            System.out.println("用户名或密码错误");
            return "redirect:/index.html?msg=" + URLEncoder.encode("用户名或密码错误", "utf-8");
        } else {
            System.out.println("登录成功");
            return "redirect:/main?uid=" + user.getId();
        }
    }

    @RequestMapping("/main")        // 见到Request, 转发带参
    public String main(int uid, HttpServletRequest req) {
        // 按照uid查询
        req.setAttribute("user", userMapper.selectById(uid));
        return "/main";
    }


    // 转发： return "/地址"; 地址是不带后缀的
    // 1. 转发打开的是templates里面的网页
    //      templates里的网页只能通过Java处理，转发打开
    // 2. 带参： req.setAttribute("参数名", 值);
    //      网页中使用  ${}渲染转发带过来的数据
    // 3. 转发是java的行为，地址栏中的地址不会发生改变

    // 重定向 return "redirect:/地址.html"
    // 1. 重定向打开的是static里面的网页
    // 2. 带参：return "redirect:/地址.html?参数名=值";
    //      网页使用js获取地址中的参数值
    // 3. 重定向是浏览器的行为，地址栏中的地址会发生改变
    //      <a href="地址">重定向</a>本身就是一个重定向
    // 4. 对于进入java地址，一般使用重定向进入

    // 127.0.0.1:8080/upload
    @ResponseBody   // 该注解中return会把东西原模原样输出，底层用的json，依赖包是jackson
    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        // 保存file
        FileUtil.createFile("N:/create/shop/upload");
        file.transferTo(new File("N:/create/shop/upload/" + fileName));
        // 1.转发 2.重定向
        return "/shop/upload/" + fileName;  // 3.把字符串返回给网页
    }

}
