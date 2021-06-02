package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;

import static com.bird.util.Constant.TOP_BAR_HEIGHT;

/*
* 小鸟的核心类，小鸟的飞行的逻辑，和绘制都在此类中进行控制
* */
public class Bird {
    //图片的张数
    public static final int IMG_COUNT = 3;
    //鸟的图片资源数组对象
    private Image[] images;
    //小鸟的坐标属性
    private int x,y;

    //小鸟在y轴伤的速度的属性
    private int dealtY;
    //小鸟向上飞行的最大速度
    public static final int MAX_UPDEALTY = 8;
    //小鸟向下飞行的最大速度
    public static final int MAX_DOWNDEALTY = 15;

    //定义鸟的状态
    private int state;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_UP = 1;
    public static final int STATE_DOWN = 2;
    public static final int STATE_DIE = 3;



    //在构造方法中对资源进行初始化
    public Bird(){
        images = new Image[IMG_COUNT];
        for (int i =0; i< IMG_COUNT; i++){
            images[i] = GameUtil.loadBufferedImage(Constant.BIRDS_IMG_PATH[i]);
        }
        //初始化小鸟的初始坐标,在窗口的左侧
        x = Constant.FRAME_WIDTH >> 1;
        y = Constant.FRAME_HEIGHT >> 1;
    }
    //绘制小鸟
    public void draw(Graphics g){
        flyLogic();

        int halfImgW = images[state].getWidth(null) >> 1;
        int halfImgH = images[state].getHeight(null) >> 1;
        g.drawImage(images[state],x-halfImgW,y-halfImgH,null);
    }

    //小鸟在飞行中的y坐标的变化的逻辑部分
    private void flyLogic(){
        switch(state){
            case STATE_NORMAL:
                break;
            case STATE_UP:
                dealtY++;
                if(dealtY > MAX_UPDEALTY){
                    dealtY = MAX_UPDEALTY;
                }
                y -= dealtY;
                //撞到上沿
                if(y < (images[state].getHeight(null) >>1 ) + TOP_BAR_HEIGHT){
                    state = STATE_DIE;
                    reset();
                }
                break;
            case STATE_DOWN:
                dealtY++;
                if(dealtY > MAX_DOWNDEALTY){
                    dealtY = MAX_DOWNDEALTY;
                }
                y += dealtY;
                //撞到下沿
                if(y > Constant.FRAME_WIDTH - (images[state].getHeight(null)>>1)){
                    state = STATE_DIE;
                    reset();
                }
                break;
        }
    }

    //改变鸟状态为向上飞行
    public void fly(){
        if(state != STATE_UP){
            state = STATE_UP;
            dealtY = 0;
        }

    }
    //改变表状态为自由落体
    public void down() {
        if(state != STATE_DOWN){
            state = STATE_DOWN;
            dealtY = 0;
        }
    }
    //小鸟的状态重置
    public void reset(){
        state = STATE_NORMAL;
        x = Constant.FRAME_WIDTH >> 1;
        y = Constant.FRAME_HEIGHT >> 1;
    }
}
