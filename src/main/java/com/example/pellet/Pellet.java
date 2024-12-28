package com.example.pellet;

import com.example.GameLoop;
import com.example.character.Pacman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

abstract public class Pellet {
     double x,y,width,height; 
     Image image;
     Rectangle bound; 
     int point;
     boolean isEaten;
    public GraphicsContext g;
    GameLoop gameLoop;
    
    public Pellet(double x, double y, int point,double width,double height,GameLoop gameLoop){
        this.x = x;
        this.y = y;
        this.point = point;
        this.width = width;
        this.height = height;
        this.gameLoop = gameLoop;
        this.g = gameLoop.getG();
        bound = new Rectangle();
        this.isEaten = false;
    }

    /**
     * 
     * @return Rectangle bound sau khi cap nhat
     */
    public Rectangle getBound(){
        bound.setX(this.x);
        bound.setY(this.y);
        bound.setWidth(this.width);
        bound.setHeight(this.height);
        return bound;
    }

    public boolean checkCollisionWithPacman(Pacman pacman){
        return this.getBound().getBoundsInParent().intersects(pacman.getBound().getBoundsInParent());
    }

    abstract public void update();

    abstract public void render();

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
       
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean isIsEaten() {
        return this.isEaten;
    }

    public boolean getIsEaten() {
        return this.isEaten;
    }

    public void setIsEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }


    

}
