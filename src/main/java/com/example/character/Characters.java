package com.example.character;
import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.shape.Rectangle;

public class Characters {
    int level =1;
    double x,y,width,height;// x y la toa do
    Rectangle bound ;
    double v; // vantoc
    
    public  GameLoop gameLoop;

    Animation curAnimation;
    Animation moveUp;
    Animation moveDown;
    Animation moveLeft;
    Animation moveRight;

    int nextDirection;
    public int direction;
    final int UP = 0;  
    final int DOWN = 1;
    final int LEFT = 2;
    public final int RIGHT = 3;

    public Characters(double x, double y,double width,double height,double v,GameLoop  gameLoop){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bound = new Rectangle();
        this.v = v; 
        this.gameLoop = gameLoop;
        thisMaze = gameLoop.getMaze().maze[level];
        curAnimation = moveRight;
        
        if(thisMaze == null) {
            System.out.println("null");
        }
    }   
    
     public void update(){
        

     }

     public void render(){

     }


     char[][] thisMaze;

     public void move(char[][] thisMaze){
        double n=(y+8)/16+1e-3;
        int curI= (int)((y+8)/16+1e-3);
        double m=(x+8)/16+1e-3;
        int curJ = (int)((x+8)/16+1e-3);

          if(nextDirection!=direction){
             if (nextDirection == UP && !checkCollisionWitMaze(thisMaze, x, y - v)) {
                 direction = UP;
             } else if (nextDirection == DOWN && !checkCollisionWitMaze(thisMaze, x, y + v)) {
                 direction = DOWN;
             } else if (nextDirection == LEFT && !checkCollisionWitMaze(thisMaze, x - v, y)) {
                 direction = LEFT;
             } else if (nextDirection == RIGHT && !checkCollisionWitMaze(thisMaze, x + v, y)) {
                 direction = RIGHT;
             }
         }

         if(direction == UP){
             if(!checkCollisionWitMaze(thisMaze, x, y-v)){
                 if(y-v+8<0){
                     y=16*30-8;
                 }
                 else{
                    
                    y-=v;
                 }
                 curAnimation.stop();
                 curAnimation = moveUp;
                 curAnimation.start();
             }
             if(checkCollisionWitMaze(thisMaze, x, y-v)){
                 if(!checkCollisionWitMaze(thisMaze, x, y)){
                     y=((int)((y-v+8)/16+1+1e-4))*16-8; 
                 }
             }
         }
         if(direction == DOWN){
             if(!checkCollisionWitMaze(thisMaze, x, y+v)){
                 if(y+v+8>16*30){
                     y=0-8;
                 }
                 else{
                     y+=v;
                 }
                 curAnimation.stop();
                 curAnimation = moveDown;
                 curAnimation.start();
             }
             if(checkCollisionWitMaze(thisMaze, x, y+v)){
                 if(!checkCollisionWitMaze(thisMaze, x, y)){
                     y=((int)((y+v+8)/16+1e-4))*16-8; 
                 }
             }
         }
         if(direction == LEFT){
             if(!checkCollisionWitMaze(thisMaze, x-v, y)){
                 if(x-v+8<0){
                     x=27*16-8;
                 }
                 else{
                    
                       x-=v; 
                 }
                 curAnimation.stop();
                 curAnimation = moveLeft;
                 curAnimation.start();
             }
             if(checkCollisionWitMaze(thisMaze, x-v, y)){
                 if(!checkCollisionWitMaze(thisMaze, x, y)){
                     x=((int)((x-v+8)/16+1+1e-4))*16-8; 
                 }
             }
         }
         if(direction == RIGHT){
             if(!checkCollisionWitMaze(thisMaze, x+v, y)){
                 if(x+v+8>27*16){
                     x=0-8;
                 }
                 else{
                        x+=v;
                
                 }
                 curAnimation.stop();
                 curAnimation = moveRight;
                 curAnimation.start();
             }
             if(checkCollisionWitMaze(thisMaze, x+v, y)){
                 if(!checkCollisionWitMaze(thisMaze, x, y)){
                     x=((int)((x+v+8)/16+1e-4))*16-8; 
 
                 }
             }
             
         }
     }

    //  public void moveTo(char[][] maze,int[] next){
        
    //  }
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
        int i = (int) ((y+8)/16+1e-4);
        int j = (int) ((x+8)/16+1e-4);
        int i1 = (int) ((y+8+16)/16+1e-4);
        int j1 = (int) ((x+8+16)/16+1e-4);
        //if(i<0||i1>=31||j<0||j1>=28) return false;
        if(i<0&&direction!=RIGHT&&direction!=LEFT) return false;
        if(j<0&&direction!=UP&&direction!=DOWN) return false;
        if(i1>=31&&direction !=LEFT &&nextDirection!=RIGHT) return false;
        if(j1>=28&&direction != UP&& nextDirection!= DOWN) return false;
        if(i<0&&(direction==RIGHT || direction==LEFT)) return true;
        if(j<0&&(direction==UP|| direction==DOWN)) return true;
        if(i1>=31&&(direction ==LEFT || nextDirection==RIGHT)) return true;
        if(j1>=28&&(direction == UP || nextDirection== DOWN)) return true;
        if(maze[i][j]=='X'){
            return true;
            
        }
        if(maze[i1][j]=='X'&&Math.abs(y+8+16-16*i1)>1e-4){
            return true;
            
        }   
        if(maze[i][j1]=='X'&&Math.abs(x+8+16-16*j1)>1e-4){
           return true;
            
        }
        if(maze[i1][j1]=='X'&&Math.abs(x+8+16-16*j1)>1e-4&&Math.abs(y+8+16-16*i1)>1e-4){
            return true;
            
        }
        return false;       
        
    }
    public boolean inMaze(int i,int j){
        if(i<0||i>=31||j<0||j>=28) return false;
        return true;
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
