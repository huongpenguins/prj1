package com.example.character;

import javafx.scene.input.KeyEvent;
import com.example.Animation;
import com.example.GameLoop;
import com.example.Maze;
import com.example.pellet.AllPellet;
import com.example.pellet.Pellet;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Pacman extends Characters {

    //GraphicsContext g;
    Maze maze;

    Animation die;
    Animation eatGhost;

    long startPause;
    long eatGhostTime;
    

    Image up = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_up.png").toExternalForm());
    Image down = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_down.png").toExternalForm());
    Image left = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_left.png").toExternalForm());
    Image right = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_right.png").toExternalForm());
    Image dieImage = new Image(getClass().getResource("/com/example/pictures/pacman/pacman_death.png").toExternalForm());
    //Image eatGhostImage = new Image(getClass().getResource("/com/example/pictures/pacman/100.png").toExternalForm());
    
    int state;
    final int MOVING=0;
    final int EATGHOST=1;
    final int DIE=2;
    final int PAUSE=3;


    public Pacman(double x, double y,GameLoop gameLoop) {
        super(x, y, 32.0, 32.0, 3.2, gameLoop);
        //this.g = g;
        state = MOVING;
        moveUp = new Animation(up, gameLoop.getG(), this,1000000000/12);
        moveDown = new Animation(down, gameLoop.getG(), this,1000000000/12);  
        moveLeft = new Animation(left, gameLoop.getG(), this,1000000000/12);  
        moveRight = new Animation(right, gameLoop.getG(), this,1000000000/12);
        die = new Animation(dieImage, gameLoop.getG(), this,1000000000/12);
        //eatGhost = new Animation(eatGhostImage, gameLoop.getG(), this,1000000000/2);
        direction = RIGHT;
        nextDirection = RIGHT;
        curAnimation = moveRight;
        curAnimation.start();

    }
    @Override
    public void update() {
        if(gameLoop.state == gameLoop.INGAME||gameLoop.state==gameLoop.PACMANDEATH){
            switch (state) {
                case MOVING:
                    Pellet pellet=checkCollionWithPellet();
                    if(pellet!=null){
                        
                        AllPellet.pellets.remove(pellet);
                        GameLoop.point.set(gameLoop.point.get()+pellet.getPoint());
                        if(pellet.getPoint()==50) 
                        {
                            gameLoop.ghosts.setFighten(true);
                        }
                        
                    }
                    Ghost ghost = checkCollisionWithGhost();
                    if(ghost!=null){

                        if(ghost.isFighten==true){
                            if(ghost.isEye ==false&&gameLoop.state == gameLoop.INGAME){
                               state = EATGHOST;
                                eatGhostTime= System.currentTimeMillis();
                                curAnimation.stop();
                                return; 
                            }
                            
                        }
                        else{
                            state = DIE; 
                            curAnimation.stop();
                            curAnimation = die;
                            curAnimation.start();
                            gameLoop.startPacmanDeath = System.currentTimeMillis();
                            GameLoop.lives.set(GameLoop.lives.get()-1);
                            startPause =System.currentTimeMillis();
                            return;
                        }
                    }
                    
                    move(thisMaze);
                    break;


                case EATGHOST:
                    if(System.currentTimeMillis() - eatGhostTime >=1500){
                        state = MOVING;
                        curAnimation = moveRight;
                        curAnimation.start();
                        return;
                    }
                    break;
                case DIE:
                    if(curAnimation.isLastFrame()){
                        if(gameLoop.lives.get()==0) {    
                            gameLoop.state = gameLoop.GAMEOVER;
                            gameLoop.startGameOver = System.currentTimeMillis();
                                break; 
                
                            }
                            else{
                                    gameLoop.state =gameLoop.PACMANDEATH;
                            }
                    }
 
                break;
            }
        }
        

    }

    @Override
    public void render() {
        curAnimation.draw();
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
    public Ghost checkCollisionWithGhost() {
        for (Ghost g : AllGhost.ghosts) {
            if (g.getBound().getBoundsInParent().intersects(this.bound.getBoundsInParent())) {
                return g;
            }
        }
        return null;
    }

}
