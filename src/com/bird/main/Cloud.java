package com.bird.main;

import com.bird.util.Constant;

import java.awt.*;

/*
* 随风飘动的云彩，在屏幕的上半部分飘动
* 一片云彩对应的类
* */
public class Cloud {
    //100ms的速度
    public static final int MAX_SPEED = 5;
    public static final int MIN_SPEED = 0;
    private Image img;
    private int speed;
    private int dir;
    public static final int DIR_LEFT = 1;
    public static final int DIR_NONE = 0;
    public static final int DIR_RIGHT = 2;
    private int x,y;

    public Cloud(Image img, int dir, int x, int y) {
        super();
        this.img = img;
        this.speed = Constant.CLOUD_SPEED;
        this.dir = dir;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g){
        x = dir == DIR_LEFT ? x - speed: x + speed;
        g.drawImage(img,x,y,null);
    }
    public boolean isOutFrame(){
        boolean result = false;
        if(dir == DIR_LEFT){
            if(x < -img.getWidth(null)){
                result = true;
            }
        }else if(dir == DIR_RIGHT){
            if(x > Constant.FRAME_WIDTH){
                result = true;
            }
        }
        return result;
    }
}
