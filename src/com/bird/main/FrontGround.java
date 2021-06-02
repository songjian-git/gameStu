package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;
import sun.misc.ClassLoaderUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
* 游戏前景类，游戏中的遮挡层
* 存在多朵云彩
* */
public class FrontGround {
    //重新运算的时间间隔
    public static final int LOGIC_INTERVAL = 100;
    public static final int CLOUD_COUNT = 2;
    //屏幕中的最多的云彩的数量
    public static final int MAX_CLOUD_COUNT = 5;
    //用来管理前景中的云彩的容器
    private List<Cloud> cloudList;

    //使用到的图片资源
    private Image[] cloudImgs;

    private int cloudDir;

    //用于控制云的逻辑运算周期的事件变量
    private long time;

    public FrontGround(){
        cloudList = new ArrayList<>();
        cloudImgs = new Image[CLOUD_COUNT];
        for(int i=0; i < CLOUD_COUNT; i++){
            cloudImgs[i] = GameUtil.loadBufferedImage(Constant.CLOOUD_IMG_PATH[i]);
        }
        time = System.currentTimeMillis();
        cloudDir = Cloud.DIR_RIGHT;
    }
    public void draw(Graphics g){
        logic();
        for(int i = 0; i < cloudList.size(); i++){
            cloudList.get(i).draw(g);
        }
    }
    /*
    * 关于容器中的云彩的个数的控制，速度，方向等的控制
    * */
    private void logic(){
        //云的逻辑是每个100ms运算一次
        if(System.currentTimeMillis() - time > LOGIC_INTERVAL){
            time = System.currentTimeMillis();
            //运算一次
            //如果屏幕中的云彩的数量小于最大数量，可以考虑加一个
            if(cloudList.size() < MAX_CLOUD_COUNT){
                //1%的概率添加
                if(GameUtil.isInPercent(Constant.CLOUD_BORN_PERCENT)){
                    int index = GameUtil.getRandomNumber(0,CLOUD_COUNT);
                    int x = -cloudImgs[index].getWidth(null);
                    int y = GameUtil.getRandomNumber(Constant.TOP_BAR_HEIGHT, Constant.FRAME_HEIGHT/4);
                    Cloud cloud = new Cloud(cloudImgs[index],cloudDir,x,y);
                    cloudList.add(cloud);
                }
            }
            //云彩飞出屏幕的处理
            for(int i =0; i<cloudList.size();i++){
                Cloud cloud = cloudList.get(i);
                if(cloud.isOutFrame()){
                    cloudList.remove(i);
                    i--;
                }
            }
        }
    }
}
