package com.czw.animelogin.login.mapper;

import com.czw.animelogin.login.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    List<UserInfoEntity> getUserInfoByName(@Param("userName") String userName);

    void insertUserInfo(@Param("login_username") String login_username,
                        @Param("login_password") String login_password,
                        @Param("user_role") String user_role);
}
