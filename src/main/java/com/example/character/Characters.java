package com.example.character;
import com.example.GameLoop;

import javafx.scene.shape.Rectangle;

public class Characters {
    int level =1;
    double x,y,width,height;// x y la toa do
    Rectangle bound ;
    double v; // vantoc
    public  GameLoop gameLoop;

    
    public Characters(double x, double y,double width,double height,double v,GameLoop  gameLoop){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bound = new Rectangle();
        this.v = v; 
        this.gameLoop = gameLoop;

    }   
    
     public void update(){

     };

     public void render(){

     }

    /**
     * 
     * @return Rectangle bound sau khi cap nhat
     */
    public Rectangle getBound(){
        bound.setX(this.x+width/2-8);
        bound.setY(this.y+height/2-8);
        bound.setWidth(16);
        bound.setHeight(16);
        return bound;
    }


    /**
     * x y la toa do cua nhan vat neu +v la toa do cua o tiep theo
     * kiem tra cac o xung quanh
     * @param maze
     * @param x,y 
     * @return true neu va cham voi maze
     * @return false neu khong va cham voi maze
     */
    public boolean checkCollisionWitMaze(char [][] maze,double x,double y){
        int i = (int) ((y+8)/16);
        int j = (int) ((x+8)/16);
        int i1 = (int) ((y+8+16)/16);
        int j1 = (int) ((x+8+16)/16);
        
        if(i<0||j<0||i1>=31||j1>=28){
            return false;
        }
        if(maze[i][j]=='X'){
            return true;
            
        }
        if(maze[i1][j]=='X'&&y+8+16>16*i1){
            return true;
            
        }   
        if(maze[i][j1]=='X'&&x+8+16>16*j1){
           return true;
            
        }
        if(maze[i1][j1]=='X'&&x+8+16>16*j1&&y+8+16>16*i1){
            return true;
            
        }
        return false;       
        
    }
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
    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public double getV() {
        return this.v;
    }

    public void setV(double v) {
        this.v = v;
    }
    
}
