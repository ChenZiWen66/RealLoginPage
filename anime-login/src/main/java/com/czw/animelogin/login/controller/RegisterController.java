package com.czw.animelogin.login.controller;

import com.czw.animelogin.login.services.UserInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户注册",tags = "注册接口")
@RestController
@RequestMapping("/login")
public class RegisterController {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "传入用户名密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",paramType = "String",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "String",dataType = "String")
    })
    public String userInfoRegister(@ApiParam(value = "用户名") @RequestParam(value = "username") String username,
                                   @ApiParam(value = "密码") @RequestParam(value = "password") String password) {
        LOG.info("开始注册");
        userInfoService.InsertUserInfo(username,password,"USER");
        return "注册成功";
    }
}
