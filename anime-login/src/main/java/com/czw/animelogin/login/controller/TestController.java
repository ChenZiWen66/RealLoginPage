package com.czw.animelogin.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是一个测试接口，用于模拟用户或管理员登陆
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/adminLogin")
//    @PreAuthorize("hasAuthority('admin')")
    public String adminLogin(){
        return "这是管理员登陆界面";
    }
    @GetMapping("/userLogin")
    public String userLogin(){
        return "这是用户登陆界面";
    }

}
