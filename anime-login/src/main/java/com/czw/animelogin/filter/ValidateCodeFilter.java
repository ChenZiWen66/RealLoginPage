package com.czw.animelogin.filter;

import com.czw.animelogin.controller.ValidateCodeController;
import com.czw.animelogin.entity.ValidateCodeEntity;
import com.czw.animelogin.exception.ValidateCodeException;
import com.czw.animelogin.handler.ValidateCodeFailureHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeFailureHandler validateCodeFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果连接来源是登陆界面并且是post请求就进行验证操作
        if (StringUtils.equalsIgnoreCase(request.getRequestURI(), "/login") && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                validateCodeFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);//FilterChain是让请求跳转到下一个过滤器链
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ValidateCodeEntity validateCodeEntity = (ValidateCodeEntity) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "userInputValidateCode");//从请求中获取用户输入的验证码

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (validateCodeEntity == null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if (validateCodeEntity.isExpire()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已过期！");
        }
        if (!StringUtils.equalsIgnoreCase(validateCodeEntity.getValidateCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        //删除session中的验证码
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
    }
}
