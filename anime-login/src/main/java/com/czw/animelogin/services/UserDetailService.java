package com.czw.animelogin.services;

import com.czw.animelogin.entity.UserInfoEntity;
import com.czw.animelogin.mapper.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取查询数据表获取用户信息
        List<UserInfoEntity> userInfoByNameList = userInfoMapper.getUserInfoByName(username);
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        if (userInfoByNameList.size() != 0) {
            UserInfoEntity userInfoEntity = userInfoByNameList.get(0);
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfoEntity.getUser_role());
            simpleGrantedAuthorities.add(simpleGrantedAuthority);
            return new User(username, userInfoEntity.getLogin_password(), simpleGrantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username);
        }


    }
}
