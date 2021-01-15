package com.czw.animelogin.login.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码类
 */
@ApiModel(value = "ValidateCodeEntity", description = "验证码")
public class ValidateCodeEntity {
    @ApiModelProperty(value = "验证码内容", hidden = false)
    private String validateCode;//验证码内容
    @ApiModelProperty(value = "验证码图片", hidden = false)
    private BufferedImage validateCodeImage;//前台显示的验证码图片
    @ApiModelProperty(value = "验证码过期时间", hidden = false)
    private LocalDateTime expireTime;//过期时间

    public ValidateCodeEntity(String validateCode, BufferedImage validateCodeImage, LocalDateTime expireTime) {
        this.validateCode = validateCode;
        this.validateCodeImage = validateCodeImage;
        this.expireTime = expireTime;
    }

    /**
     *
     * @param validateCode
     * @param validateCodeImage
     * @param effectiveTime 有效时间（秒）
     */
    public ValidateCodeEntity(String validateCode, BufferedImage validateCodeImage, int effectiveTime) {
        this.validateCode = validateCode;
        this.validateCodeImage = validateCodeImage;
        this.expireTime = LocalDateTime.now().plusSeconds(effectiveTime);
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public BufferedImage getValidateCodeImage() {
        return validateCodeImage;
    }

    public void setValidateCodeImage(BufferedImage validateCodeImage) {
        this.validateCodeImage = validateCodeImage;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
