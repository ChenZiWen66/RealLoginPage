package com.czw.animelogin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.czw.animelogin.login.mapper")
public class AnimeLoginApplication {
    private static final Logger LOG = LoggerFactory.getLogger(AnimeLoginApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AnimeLoginApplication.class, args);
        LOG.info("登录项目启动成功");
    }

}
