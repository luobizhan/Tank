package com.lbz.tank;

import java.awt.*;

/**
 * @author lbz
 * @create 2020-09-19 17:09
 */
public class Bullet {
    private int x,y;
    private Dir dir;
    private static final int SPEED = 10;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    TankFrame tankFrame = null;
    public Bullet( int x,int y,Dir dir,TankFrame tankFrame) {
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,WIDTH,HEIGHT);
        move();
        remove();
    }

    public void move(){
        switch (dir){
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
        }
    }
    public void remove(){
        //判断子弹是否存活并删除无效子弹
        if(x<0||x>tankFrame.getWidth()||y<0||y>tankFrame.getHeight()){
            tankFrame.bulletList.remove(this);
        }
    }
}
