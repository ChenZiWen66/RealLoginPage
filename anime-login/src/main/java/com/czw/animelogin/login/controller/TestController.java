package com.czw.animelogin.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "权限测试",tags = "权限测试接口")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "管理员登陆测试", notes = "管理员登陆测试")
    @GetMapping("/adminLogin")
//    @PreAuthorize("hasAuthority('admin')")
    public String adminLogin(){
        return "这是管理员登陆界面";
    }

    @ApiOperation(value = "普通用户登陆测试", notes = "普通用户登陆测试")
    @GetMapping("/userLogin")
    public String userLogin(){
        return "这是用户登陆界面";
    }

}
