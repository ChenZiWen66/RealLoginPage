package com.czw.animelogin.services;

import com.czw.animelogin.entity.UserInfoEntity;
import com.czw.animelogin.mapper.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserInfoEntity> getUserInfo(String username) {
        LOG.info("开始获取用户信息:" + username);
        return userInfoMapper.getUserInfoByName(username);
    }

    public void InsertUserInfo(String username, String password, String userRole) {
        userInfoMapper.insertUserInfo(username, passwordEncoder.encode(password), userRole);
    }
}
