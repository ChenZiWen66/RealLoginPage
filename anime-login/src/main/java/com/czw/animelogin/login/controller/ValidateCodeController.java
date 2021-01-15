package com.czw.animelogin.login.controller;

import com.czw.animelogin.login.entity.ValidateCodeEntity;
import com.czw.animelogin.login.services.ValidateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码的接口
 */
@RestController
public class ValidateCodeController {
    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    ValidateServices validateServices;

    /**
     * 生成一个验证码，并返回
     *
     * @param request
     * @param response
     */
    @GetMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCodeEntity validateCode = validateServices.createValidateCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_IMAGE_CODE, validateCode);
        ImageIO.write(validateCode.getValidateCodeImage(), "jpeg", response.getOutputStream());
    }
}
