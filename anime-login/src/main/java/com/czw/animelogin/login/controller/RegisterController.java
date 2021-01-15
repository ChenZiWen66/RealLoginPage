package com.czw.animelogin.login.controller;

import com.czw.animelogin.login.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class RegisterController {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    public String userInfoRegister(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password) {
        LOG.info("开始注册");
        userInfoService.InsertUserInfo(username,password,"USER");
        return "注册成功";
    }
}
