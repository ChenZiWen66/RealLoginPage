package com.czw.animelogin.common.handler;

import com.czw.animelogin.login.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    LogService logService;

    private static final Logger LOG = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LOG.info("用户登陆没有权限被拒绝");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logService.insertLog(authentication.getName(),"没有用户权限被拒绝");
        request.setAttribute("SPRING_SECURITY_403_EXCEPTION", accessDeniedException);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        request.getRequestDispatcher("/demo/AccessDeniedPage.html").forward(request, response);
    }
}
