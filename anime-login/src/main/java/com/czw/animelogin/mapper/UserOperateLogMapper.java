package com.czw.animelogin.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserOperateLogMapper {
    public void insertLog(@Param("username") String username, @Param("log_info") String log_info);
}
