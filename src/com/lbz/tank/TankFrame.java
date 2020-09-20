package com.lbz.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lbz
 * @create 2020-09-19 11:12
 */
public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEGIHT = 600;
    List bulletList = new ArrayList<Bullet>();
    Tank tank = new Tank(30,30,Dir.DOWN,this);

    public TankFrame(){
        //修改窗口属性
        setSize(GAME_WIDTH,GAME_HEGIHT);
        setTitle("tank war");
        setResizable(false);
        setVisible(true);
        //添加键盘事件监听
        addKeyListener(new MyListener());
        //监听关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    //解决屏幕闪烁
    Image offScreenImage = null ;
    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEGIHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEGIHT);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("共发射了"+bulletList.size()+"颗子弹",10,60);

        tank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet b = (Bullet) bulletList.get(i);
            b.paint(g);
        }
    }

    class MyListener extends KeyAdapter {

        Dir dir;

        boolean bU = false;
        boolean bL = false;
        boolean bD = false;
        boolean bR = false;


        //键盘监听
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(!bD && !bL && !bU && !bR){
                tank.setMvoe(false);
            }else {
                tank.setMvoe(true);
                if (bL) tank.setDir(Dir.LEFT);
                if (bU) tank.setDir(Dir.UP);
                if (bD) tank.setDir(Dir.DOWN);
                if (bR) tank.setDir(Dir.RIGHT);
            }
        }
    }
}
