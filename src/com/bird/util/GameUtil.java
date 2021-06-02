package com.bird.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/*
* 游戏工具类，所有的游戏中用到的工具方法都定义在此
* */
public class GameUtil {
    private GameUtil(){

    }
    public static BufferedImage loadBufferedImage(String imgPath){
        try{
            return ImageIO.read(new FileInputStream(imgPath));
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 求本次调用该方法时对于概率用的事件是否发生的方法
    * @Param num 取之范围是1-100，代表事件发生的概率的百分比，1代表1%
    * */
    public static boolean isInPercent(int num){
        return getRandomNumber(1,101) <= num;
    }
    public static int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max-min)+min);
    }
}
