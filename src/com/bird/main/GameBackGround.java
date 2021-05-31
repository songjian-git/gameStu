package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;

/*
* 游戏背景类
* 所有的游戏背景绘制都在此完成
* */
public class GameBackGround {
    //背景需要的资源
    private Image bkImg;

    public GameBackGround(){
        bkImg = GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
    }
    //自定义一个绘制的方法,用系统提供的画笔将图片会知道指定的位置
    public void draw(Graphics graphics){
        //先绘制背景色
        graphics.setColor(Constant.BK_COLOR);
        graphics.fillRect(0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        //绘制的循环的次数
        int width = bkImg.getWidth(null);
        int height = bkImg.getHeight(null);
        int count = Constant.FRAME_WIDTH/width + 1;
        for(int i =0; i<count; i++){
            graphics.drawImage(bkImg,width*i,Constant.FRAME_HEIGHT-height,null);
        }
    }
}
