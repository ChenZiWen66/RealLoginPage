package com.czw.animelogin.common.handler;

import com.czw.animelogin.login.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
    @Autowired
    LogService logService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        Authentication authentication = authenticationFailureBadCredentialsEvent.getAuthentication();
        logService.insertLog(authentication.getName(), "用户密码错误");
    }
}
