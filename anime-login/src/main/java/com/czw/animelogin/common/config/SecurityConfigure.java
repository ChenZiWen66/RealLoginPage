package com.czw.animelogin.common.config;

import com.czw.animelogin.common.filter.ValidateCodeFilter;
import com.czw.animelogin.common.handler.MyAccessDeniedHandler;
import com.czw.animelogin.common.handler.MyAuthenticationFailureHandler;
import com.czw.animelogin.common.handler.MyAuthenticationSuccessHandler;
import com.czw.animelogin.login.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    ValidateCodeFilter validateCodeFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginProcessingUrl("/login")
                    .loginPage("/demo/TestLogin.html")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
                    .and()
                .authorizeRequests()
                    .antMatchers("/demo/TestLogin.html",
                            "/demo/RegisterPage.html",
                            "/login/register",
                            "/getValidateCode",
                            "/live2d/**",
                            "/doc.html",//swagger入口
                            "/v2/api-docs",//swagger api json
                            "/swagger-resources/configuration/ui",//用来获取支持的动作
                            "/swagger-resources",//用来获取api-docs的URI
                            "/swagger-resources/configuration/security",//安全选项
                            "/webjars/**").permitAll()
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
