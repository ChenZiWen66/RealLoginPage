package com.czw.animelogin.login.services;

import com.czw.animelogin.login.mapper.UserOperateLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger LOG = LoggerFactory.getLogger(LogService.class);
    @Autowired
    UserOperateLogMapper userOperateLogMapper;

    /**
     * 插入日志
     *
     * @param username 用户名
     * @param log_info 日志描述
     */
    public void insertLog(String username, String log_info) {
        userOperateLogMapper.insertLog(username, log_info);
    }
}
