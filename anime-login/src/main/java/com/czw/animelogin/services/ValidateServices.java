package com.czw.animelogin.services;

import com.czw.animelogin.entity.ValidateCodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码服务
 */
@Service
public class ValidateServices {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateServices.class);
    private Random random = new Random();
    @Value("${validateCode.width}")
    private int width;//验证码图片宽度
    @Value("${validateCode.height}")
    private int height;//验证码图片高度
    @Value("${validateCode.length}")
    private int length;//验证码位数
    @Value("${validateCode.expireIn}")
    private int expireIn;//验证码有效时间

    public ValidateCodeEntity createValidateCode() {
        LOG.info("开始创建一个验证码");
        //创建一个画板
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

//        填充颜色
        graphics.setColor(getRandomColor(160, 200));
        graphics.fillRect(0, 0, this.width, this.height);

        //画100根随机线
        for (int i = 0; i < 100; i++) {
            graphics.setColor(getRandomColor(200, 255));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            graphics.drawLine(x, y, x + x1, y + y1);
        }

        //生成一个四位数验证码
        StringBuilder validateCode = new StringBuilder();
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        for (int i = 0; i < this.length; i++) {
            int randomNumber = random.nextInt(10);
            validateCode.append(randomNumber);
            graphics.setColor(getRandomColor(20, 130));
            graphics.drawString(String.valueOf(randomNumber), width / length * i, 16);
        }
        String code = validateCode.toString();
        graphics.dispose();
        LOG.info("创建的验证码为" + code);
        return new ValidateCodeEntity(code, image, expireIn);
    }

    /**
     * 获取一个随机颜色值
     *
     * @param colorValue_min:每个RGB的最小值
     * @param colorValue_max:每个RGB的最大值
     * @return
     */
    private Color getRandomColor(int colorValue_min, int colorValue_max) {
        colorValue_max = Math.min(colorValue_max, 255);
        colorValue_min = Math.min(colorValue_min, 255);
        colorValue_max = Math.max(colorValue_max, 0);
        colorValue_min = Math.max(colorValue_min, 0);
        return new Color(colorValue_min + random.nextInt(colorValue_max - colorValue_min),
                colorValue_min + random.nextInt(colorValue_max - colorValue_min),
                colorValue_min + random.nextInt(colorValue_max - colorValue_min));
    }
}
