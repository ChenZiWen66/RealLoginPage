package com.czw.animelogin.login.entity;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码类
 */
public class ValidateCodeEntity {
    private String validateCode;//验证码内容
    private BufferedImage validateCodeImage;//前台显示的验证码图片
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
