package com.example;

import com.example.pellet.AllPellet;
import com.example.pellet.PacDot;
import com.example.pellet.PowerPellet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Maze {

    final static private int LEVEL = 20;
    final static private int COT = 28;
    final static private int HANG  = 31;
    final static private double BLOOK_SIZE = 16;


    //GameLoop gameLoop;
    GraphicsContext g2;
    //int level = 
    static public char[][][] maze = new char[LEVEL][HANG][COT];
    public static void initMaze(int level){
        //this.gameLoop = gameLoop;
        switch (level) {
            case 1:
            maze[1] = new char[][]{                   
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                {'X','o','o','o','o','o','o','o','o','o','o','o','o','X','X','o','o','o','o','o','o','o','o','o','o','o','o','X'},
                {'X','o','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','o','X'},
                {'X','O','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','O','X'},
                {'X','o','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','o','X'},
                {'X','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','X'},
                {'X','o','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','o','X'},
                {'X','o','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','o','X'},
                {'X','o','o','o','o','o','o','X','X','o','o','o','o','X','X','o','o','o','o','X','X','o','o','o','o','o','o','X'},
                {'X','X','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','o','o','o','o','o','o','o','o','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','X',' ',' ',' ',' ',' ',' ','X','o','X','X','o','X','X','X','X','X','X'},
                {'o','o','o','o','o','o','o','o','o','o','X',' ',' ',' ',' ',' ',' ','X','o','o','o','o','o','o','o','o','o','o'},
                {'X','X','X','X','X','X','o','X','X','o','X',' ',' ',' ',' ',' ',' ','X','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','o','o','o','o','o','o','o','o','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X'},
                {'X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X'},
                {'X','o','o','o','o','o','o','o','o','o','o','o','o','X','X','o','o','o','o','o','o','o','o','o','o','o','o','X'},
                {'X','o','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','o','X'},
                {'X','o','X','X','X','X','o','X','X','X','X','X','o','X','X','o','X','X','X','X','X','o','X','X','X','X','o','X'},
                {'X','O','o','o','X','X','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','X','X','o','o','O','X'},
                {'X','X','X','o','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','o','X','X','X'},
                {'X','X','X','o','X','X','o','X','X','o','X','X','X','X','X','X','X','X','o','X','X','o','X','X','o','X','X','X'},
                {'X','o','o','o','o','o','o','X','X','o','o','o','o','X','X','o','o','o','o','X','X','o','o','o','o','o','o','X'},
                {'X','o','X','X','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','X','X','o','X'},
                {'X','o','X','X','X','X','X','X','X','X','X','X','o','X','X','o','X','X','X','X','X','X','X','X','X','X','o','X'},
                {'X','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','o','X'},
                {'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
                            };
                for(int i = 0; i < HANG; i++)
                for(int j = 0 ; j < COT ; j++){
                    switch (maze[level][i][j]) {
                        case 'o':    
                            //Image dot = new Image(getClass().getResource("/com/example/pictures/pellet/pacdot.png").toExternalForm());  
                            PacDot pacDot = new PacDot(j*BLOOK_SIZE+BLOOK_SIZE/2 - 2,i*BLOOK_SIZE+BLOOK_SIZE/2 - 2);  
                            AllPellet.pellets.add(pacDot); 
                            // pacDot.g = gameLoop.g;
                            // pacDot.render();
                            //g2.drawImage(pacDot,j*BLOOK_SIZE+BLOOK_SIZE/2 - 2,i*BLOOK_SIZE+BLOOK_SIZE/2 - 2,4,4);
                            
                            break;
                        case 'O':    
                            PowerPellet powerPellet = new PowerPellet(j*BLOOK_SIZE+BLOOK_SIZE/2 - 8,i*BLOOK_SIZE+BLOOK_SIZE/2 - 8);
                            AllPellet.pellets.add(powerPellet);
                            // powerPellet.g = gameLoop.g;
                            // powerPellet.render();
                            //g2.drawImage(powerPellet,j*BLOOK_SIZE+BLOOK_SIZE/2 - 8,i*BLOOK_SIZE+BLOOK_SIZE/2 - 8,16,16);
                            break;
                        default:
                            break;
                    }
                }

                break;
            case 2:

                break;
            default:
                break;
        }
}
/**
 * 
 * @param maze // mảng 2 chiều biểu diễn bản đồ
 * @param g2 // GraphicContext cua canvas
 */
    public  void drawMaze(int level,GraphicsContext g2){
        Image dot = new Image(getClass().getResource("/com/example/pictures/pellet/pacdot.png").toExternalForm());
        Image powerPellet = new Image(getClass().getResource("/com/example/pictures/pellet/powerPellet.png").toExternalForm());
    
       for(int i = 0; i < HANG; i++)
        for(int j = 0 ; j < COT ; j++){
            switch (maze[level][i][j]) {
                case 'o':    

                    g2.drawImage(dot,j*BLOOK_SIZE+BLOOK_SIZE/2 - 2,i*BLOOK_SIZE+BLOOK_SIZE/2 - 2,4,4);
                    
                    break;
                case 'O':    
                    g2.drawImage(powerPellet,j*BLOOK_SIZE+BLOOK_SIZE/2 - 8,i*BLOOK_SIZE+BLOOK_SIZE/2 - 8,16,16);
                    break;
                default:
                    break;
            }
        }

        
    }

    public  void updateMaze(){
        
    }
    

}
