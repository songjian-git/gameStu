package com.bird.main;

//静态导入
import static com.bird.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
* 游戏的主窗口类。所有的关于游戏中绘制的内容哥都在此类中完成
* 游戏开发：基本都是用第几页面的绘制，而不是用jdk提供的各种组件
* */
//继承java.awt.Frame
public class GameFrame extends Frame implements Runnable{

    //创建游戏背景对象
    private GameBackGround backGround;
    //游戏前景对象
    private FrontGround frontGround;

    //定义小鸟对象
    private Bird bird;

    //所有在屏幕上绘制的内容，都是通过系统线程绘制的
    //系统线程完成的工作：屏幕内容的绘制，窗口的事件的监听和处理
    //目前项目中存在两个线程完成任务。一个是系统线程，一个是自己创建启动的线程，负责调用repaint()
    //两个线程会抢夺系统资源，可能会出现，一次刷新绘制的内容，并没有在一次刷新周期中完成。

    //在构造方法中对我们的游戏窗口做初始化工作
    public GameFrame(){
        initFrame();
        //窗口默认不可见，设置窗口可见
        setVisible(true);
        initGame();
    }

    //对窗口进行必要的初始化
    private void initFrame(){
        //设置窗口的大小
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //设置标题
        setTitle(GAME_TITLE);
        //设置窗口显示的初始化位置
        setLocation(FRAME_X,FRAME_Y);
        //设置窗口大小不可变
        setResizable(false);
        //给窗口添加关闭的事件
        //当窗口发生各种事件的时候，事件会派发给你参数对象
        //蚕食对象会调用对应的方法
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //添加按键监听
        addKeyListener(new MyKeyListener());
    }
    //定义用于接受按键事件对象的内部类
    class MyKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        //处理按键被按下的事件
        @Override
        public void keyPressed(KeyEvent e) {
            //捕获的系统传入的按键的虚拟键值
            int keyCode = e.getKeyCode();
            //当按下空格键，用力向上飞
            if(keyCode == KeyEvent.VK_SPACE){
                bird.fly();
            }
        }

        //处理按键被松开的事件
        @Override
        public void keyReleased(KeyEvent e) {
            //捕获的系统传入的按键的虚拟键值
            int keyCode = e.getKeyCode();
            //当按下空格键，用力向上飞
            if(keyCode == KeyEvent.VK_SPACE){
                bird.down();
            }
        }
    }
    private void initGame(){
        backGround = new GameBackGround();
        frontGround = new FrontGround();
        bird = new Bird();

        //启动用于刷新窗口的线程
        new Thread(this).start();
    }
    /*
    * 所有的我们需要绘制的内容都要在在此方法中调用绘制
    * update方式是jvm调用的。不要去主动调用该方法
    * 该方法中的绘制的所有的内容，在调用的时候，都会被会知道Frame上来
    * update何时被jvm调用呢.当repaint()方法被调用的时候
    * 参数g：是画笔。是系统提供的画笔。系统进行实例化
    *
    * 关于repaint调用：单独启动一个线程，在单独的线程中去不断的去调用repaint()
    * 需要在单独的线程中快速的调用repaint(),让系统对整个窗口进行重绘
    * */
    public void update(Graphics g){
        backGround.draw(g);
        bird.draw(g);
        frontGround.draw(g);
    }

    @Override
    public void run() {
        while(true){
            //通过调用repaint。让jvm去执行update方法。进行重新的绘制
            //一秒钟30次FPS  Frame pre second
            repaint();
            try {
                Thread.sleep(GAME_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
