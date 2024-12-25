package com.example;

import com.example.character.Characters;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation extends AnimationTimer{
    int curIndex=0;
    long lastUpdate;
    double delayTime;
    Image image;
    Characters character;
    GraphicsContext g;

    public Animation(Image image,GraphicsContext g,Characters character,double delayTime){
        curIndex=0;
        lastUpdate=0;
        this.character = character;
        this.delayTime = delayTime;
        this.g= g;
        this.image = image;
        
    }
    @Override
    public void start(){
        super.start();
    }
    @Override
    public void stop(){
        super.stop();
    } 
    @Override
    public void handle(long now) {
        if(now - lastUpdate>=delayTime){
            lastUpdate=now;
            //System.out.println("curIndex: " + curIndex + ", characterX: " + character.getX() + ", characterY: " + character.getY());
            curIndex = (curIndex+1)%((int)image.getWidth()/16); 
        }
;
    }
    public void draw(){
        //animation.handle(System.nanoTime());
        //System.out.println(curIndex);
        g.drawImage(image,curIndex*16,0,16,16,
        character.getX(),character.getY(),32,32);
    }

    public void update(){
        //curIndex=curIndex%((int)image.getWidth()/16);
    }
    public void resetAnimation(){
        curIndex=0;
        lastUpdate=0;
    }

    public int getCurIndex() {
        return this.curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public long getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public Image getImage() {
        
        return this.image;

    }

    public void setImage(Image image) {
        curIndex=0;
        this.image = image;
    }



}
