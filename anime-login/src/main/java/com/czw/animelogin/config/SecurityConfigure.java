package com.czw.animelogin.config;

import com.czw.animelogin.handler.MyAccessDeniedHandler;
import com.czw.animelogin.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
//                    .loginPage("/Login.html")
//                    .loginPage("/demo/login.html")
                    .loginProcessingUrl("/login")
                    .loginPage("/demo/TestLogin.html")
                    .and()
                .authorizeRequests()
                    .antMatchers("/demo/TestLogin.html","/demo/RegisterPage.html", "/login/register").permitAll()
                    .antMatchers("/test/adminLogin").hasAuthority("Admin")
                    .anyRequest().authenticated().and()
                .exceptionHandling()
                    .accessDeniedHandler(myAccessDeniedHandler)
//                    .accessDeniedPage("/demo/AccessDeniedPage.html")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
