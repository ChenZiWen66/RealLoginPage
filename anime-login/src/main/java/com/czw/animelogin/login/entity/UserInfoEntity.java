package com.czw.animelogin.login.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@ApiModel(value = "UserInfoEntity ", description = "用户对象")
@Repository
public class UserInfoEntity implements Serializable {
    private static final long serialVersionUID = -5380775358768726556L;
    @ApiModelProperty(value = "主键", hidden = true)
    private int id;
    @ApiModelProperty(value = "登陆用户名", hidden = false)
    private String login_username;
    @ApiModelProperty(value = "登陆密码", hidden = false)
    private String login_password;
    @ApiModelProperty(value = "用户权限(角色)", hidden = false)
    private String user_role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
