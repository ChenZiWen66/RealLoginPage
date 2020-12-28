package com.czw.animelogin.mapper;

import com.czw.animelogin.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    List<UserInfoEntity> getUserInfoByName(@Param("userName") String userName);

    void insertUserInfo(@Param("login_username") String login_username,
                        @Param("login_password") String login_password,
                        @Param("user_role") String user_role);
}
