package com.example.character;

import java.util.Random;
import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.image.Image;

public class Pinky extends Ghost {

    private Image up = new Image(getClass().getResource("/com/example/pictures/pinky/pinky_up.png").toExternalForm());
    private Image down = new Image(getClass().getResource("/com/example/pictures/pinky/pinky_down.png").toExternalForm());
    private Image left = new Image(getClass().getResource("/com/example/pictures/pinky/pinky_left.png").toExternalForm());
    private Image right = new Image(getClass().getResource("/com/example/pictures/pinky/pinky_right.png").toExternalForm());
    
    public Pinky(double x, double y, GameLoop gameLoop) {
        super(x, y, gameLoop);  
        scatterPoint = new int[]{29,26};
        scatterPoints = new int[][]{{20,23},{5,24},{29,26},{7,6},{9,15},{23,26},{24,24},{27,1},
                                    {2,15},{1,1},{14,2},{16,6},{23,12},{13,6},{28,26}};
        moveRight = new Animation(right, gameLoop.getG(), this, 1000000000/12);
        moveDown = new Animation(down, gameLoop.getG(), this, 1000000000/12);
        moveLeft = new Animation(left, gameLoop.getG(), this, 1000000000/12);
        moveUp = new Animation(up, gameLoop.getG(), this, 1000000000/12);
        inHouse=true;
        timeInHouse = 3000;
        curAnimation = moveUp;
        curAnimation.start();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public int[] getDestination(char [][] maze) {
        int des[];
        if (gameLoop.point.getValue() >= 800&& state!=FRIGHTENED) {

            int desI=(int)((gameLoop.getPacman().getY()+8)/16+1e-4);
            int desJ= (int)((gameLoop.getPacman().getX()+8)/16+1e-4);
            switch (gameLoop.getPacman().direction) {
                case UP:
                    desI=desI-1;
                    if(inMaze(desI,desJ)) {
                        if(maze[desI][desJ]!='X') break;
                            desJ=desJ+1;
                        if(inMaze(desI,desJ)){
                            if(maze[desI][desJ]!='X') break;
                        }
                            desJ = desJ-2;
                        if(inMaze(desI,desJ)){
                            if(maze[desI][desJ]!='X') break;
                        }
                        
                    }
                    
            
                    break;
                case DOWN:
                    desI=desI+1;

                    if(inMaze(desI,desJ)) {
                        if(maze[desI][desJ]!='X') break;
                            desJ=desJ+1;
                        if(inMaze(desI,desJ)){
                            if(maze[desI][desJ]!='X') break;
                        }
                            desJ = desJ-2;
                        if(inMaze(desI,desJ)){
                            if(maze[desI][desJ]!='X') break;
                        }
                        
                    }
                    break;
                case LEFT:
                desJ= desJ-1;
                if(inMaze(desI,desJ)) {
                    if(maze[desI][desJ]!='X') break;
                        desI=desI+1;
                    if(inMaze(desI,desJ)){
                        if(maze[desI][desJ]!='X') break;
                    }
                        desI = desI-2;
                    if(inMaze(desI,desJ)){
                        if(maze[desI][desJ]!='X') break;
                    }
                }
                    break;
                case RIGHT:
                desJ= desJ+1;
                if(inMaze(desI,desJ)) {
                    if(maze[desI][desJ]!='X') break;
                        desI=desI+1;
                    if(inMaze(desI,desJ)){
                        if(maze[desI][desJ]!='X') break;
                    }
                        desI = desI-2;
                    if(inMaze(desI,desJ)){
                        if(maze[desI][desJ]!='X') break;
                    }
                    
                }
                    break;
            }

            if(desI<0) desI=0;
            if(desJ<0) desJ=0;
            if(desI>=31) desI=30;
            if(desJ>=28) desJ=27;

            if(thisMaze[desI][desJ] == 'X'){
                desI=(int)((gameLoop.getPacman().getY()+8)/16+1e-4);
                desJ= (int)((gameLoop.getPacman().getX()+8)/16+1e-4);
            }

            
            des = findDestination(desI, desJ, maze);
            if(des == null){
                findRandomDes();
            }

        } else {
            return findRandomDes();
        }
        return des;
    }
}

