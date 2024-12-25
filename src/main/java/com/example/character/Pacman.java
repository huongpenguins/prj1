package com.example.character;

import javafx.scene.input.KeyEvent;
import com.example.Animation;
import com.example.GameLoop;
import com.example.Maze;
import com.example.pellet.AllPellet;
import com.example.pellet.Pellet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Pacman extends Characters {

    //GraphicsContext g;
    Maze maze;

    Animation curAnimation;
    Animation moveUp;
    Animation moveDown;
    Animation moveLeft;
    Animation moveRight;
    Animation die;
    Animation eatGhost;
    

    Image up = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_up.png").toExternalForm());
    Image down = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_down.png").toExternalForm());
    Image left = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_left.png").toExternalForm());
    Image right = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_right.png").toExternalForm());
    Image dieImage = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_death.png").toExternalForm());
    Image eatGhostImage = new Image(getClass().getResource("/com/example/pictures/pacman/100.png").toExternalForm());
    
    int state;
    final int MOVING=0;
    final int EATGHOST=1;
    final int DIE=2;
    final int PAUSE=3;

    int nextDirection;
    public int direction;
    final int UP = 0;  
    final int DOWN = 1;
    final int LEFT = 2;
    public final int RIGHT = 3;

    public Pacman(double x, double y,GameLoop gameLoop) {
        super(x, y, 32, 32, 4, gameLoop);
        //this.g = g;
        state = MOVING;
        moveUp = new Animation(up, gameLoop.getG(), this,1000000000/12);
        moveDown = new Animation(down, gameLoop.getG(), this,1000000000/12);  
        moveLeft = new Animation(left, gameLoop.getG(), this,1000000000/12);  
        moveRight = new Animation(right, gameLoop.getG(), this,1000000000/12);
        die = new Animation(dieImage, gameLoop.getG(), this,1000000000/12);
        eatGhost = new Animation(eatGhostImage, gameLoop.getG(), this,1000000000/2);
        direction = RIGHT;
        nextDirection = RIGHT;
        curAnimation = moveRight;
        curAnimation.start();

    }
    @Override
    public void update() {
        //curAnimation.handle(System.nanoTime());
        switch (state) {
            case MOVING:
                if(checkCollionWithPellet()!=null){
                    AllPellet.pellets.remove(checkCollionWithPellet());
                    gameLoop.point.add(10);
                }
                
                move();
                break;
            case PAUSE:
                
            break;
            case EATGHOST:
                
                break;
            case DIE:
                
            break;
        }

    }

    @Override
    public void render() {
        if(state!=EATGHOST){
            curAnimation.draw();
        }
        else{

        }

    }   
   
    char[][] thisMaze ;
    public void move(){
         Maze.initMaze(1);
         thisMaze = Maze.maze[1];
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
                    y=16*31;
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
                    y=((int)(y-v+8)/16+1)*16-8; 
                }
            }
        }
        if(direction == DOWN){
            if(!checkCollisionWitMaze(thisMaze, x, y+v)){
                if(y+v+8>16*31){
                    y=0;
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
                    y=((int)(y+v+8)/16)*16-8; 
                }
            }
        }
        if(direction == LEFT){
            if(!checkCollisionWitMaze(thisMaze, x-v, y)){
                if(x-v+8<0){
                    x=28*16;
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
                    x=((int)(x-v+8)/16+1)*16-8; 
                }
            }
        }
        if(direction == RIGHT){
            if(!checkCollisionWitMaze(thisMaze, x+v, y)){
                if(x+v+8>28*16){
                    x=0;
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
                    x=((int)(x+v+8)/16)*16-8; 

                }
            }
            
        }
    }


    public void addKeyListener(KeyCode k){
        
        if(k == KeyCode.UP){
            if(!checkCollisionWitMaze(thisMaze, x, y-v)){
                nextDirection = UP;
               direction = UP; 
            }
            else {
                nextDirection = UP;
            }
            
        }
        if(k == KeyCode.DOWN){
            if(!checkCollisionWitMaze(thisMaze, x, y+v)){
                nextDirection = DOWN;
               direction = DOWN;
        }
            else {
                nextDirection = DOWN;
            }
            
        }
        if(k == KeyCode.LEFT){
            if(!checkCollisionWitMaze(thisMaze, x-v, y)){
                nextDirection = LEFT;
               direction = LEFT;
            }
            else {
                nextDirection = LEFT;
            }
        }
        if(k == KeyCode.RIGHT){
            if(!checkCollisionWitMaze(thisMaze, x+v, y)){
                nextDirection = RIGHT;
               direction = RIGHT;
            }
            else {
                nextDirection = RIGHT;
            }
        }
 

    }

    public Pellet checkCollionWithPellet(){
        for(Pellet p : AllPellet.pellets){
            if(p.getBound().getBoundsInParent().intersects(this.bound.getBoundsInParent())){
                return p;
            }
        }
        return null;
    }

}
