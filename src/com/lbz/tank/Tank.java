package com.lbz.tank;

import java.awt.*;

/**
 * @author lbz
 * @create 2020-09-19 14:01
 */
public class Tank {

    private int x,y;
    private Dir dir;
    private static final int SPEED = 10;
    private boolean mvoe = false;
    private TankFrame tankFrame = null;

    public boolean isMvoe() {
        return mvoe;
    }

    public void setMvoe(boolean mvoe) {
        this.mvoe = mvoe;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){

        Color color = g.getColor();
        g.setColor(Color.green);
        g.fillRect(x,y,30,30);
        //没有按键，直接返回
        if(!mvoe) return;
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

    public void fire() {
        tankFrame.bulletList.add(new Bullet(x,y,dir,tankFrame));
    }
}
