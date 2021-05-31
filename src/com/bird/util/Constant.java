package com.bird.util;

import java.awt.*;

/*
* 游戏中的常量类
* */
public class Constant {
    //游戏窗口的宽高
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 500;

    //游戏标题
    public static final String GAME_TITLE = "单身狗之怒";

    //窗口初始化位置
    public static final int FRAME_X = 350;
    public static final int FRAME_Y = 170;

    //图片资源路径
    public static final String BK_IMG_PATH = "img/land.png";
    public static final String[] BIRDS_IMG_PATH = {"img/bird_1.png","img/bird_up.png","img/bird_down.png"};

    public static final int GAME_INTERVAL = 33;

    //游戏背景色
    public static final Color BK_COLOR = new Color(0x4bc4df);

    //定义标题栏的高度
    public static final int TOP_BAR_HEIGHT = 2;
}
