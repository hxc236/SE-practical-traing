package com.example.shop;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.io.*;

@SpringBootTest
class ShopApplicationTests {
    @Autowired
    JavaMailSender sender;

    @Test
    void contextLoads() {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("【山东科技大学教务处】关于近期学校对您的处分决定");
        //设置邮件内容
        message.setText("XXX同学您好，经监控和教务巡查发现，您近期存在旷课、迟到、早退、上课刷抖音行为，" +
                "现已通知相关辅导员，请手写5000字书面检讨，并在2022年4月1日17点前交到辅导员办公室。");
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo("1325319890@qq.com");
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("jackdaw7777@163.com");
        //OK，万事俱备只欠发送
        sender.send(message);
    }
    @Test
    public  void embark() {
        String javaInput = "买的银色版真的很好看，一天就到了，晚上就开始拿起来完系统很丝滑流畅，做工扎实，手感细腻，很精致哦苹果一如既往的好品质";

        try {
            // 构建Python进程命令
            ProcessBuilder pb = new ProcessBuilder("python", "embark.py");

            // 启动Python进程
            Process process = pb.start();

            // 获取Python进程的输入流和输出流
            OutputStream outputStream = process.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // 向Python发送数据
            writer.println(javaInput);
            writer.flush();

            // 读取Python的输出
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 关闭流和进程
            writer.close();
            reader.close();
            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
