package com.czw.animelogin.login.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperateLogMapper {
    public void insertLog(@Param("username") String username, @Param("log_info") String log_info);
}
