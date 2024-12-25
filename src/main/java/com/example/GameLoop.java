package com.example;

import com.example.character.Ghost;
import com.example.character.Pacman;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameLoop {
    
    static ConDatabase con = new ConDatabase();
    public static IntegerProperty lives = new SimpleIntegerProperty(con.select("LIVES")) ;
    public static IntegerProperty point = new SimpleIntegerProperty(con.select("POINT")) ;
    public static IntegerProperty level = new SimpleIntegerProperty(con.select("LEVEL")) ;
    
    static private GameLoop instance;
    GraphicsContext g;
    int FPS =90;
    Maze maze;
    Pacman pacman;
    private long last_update = 0;
    long startTime;
    Ghost ghost;
    Image mazeImage = new Image(getClass().getResource("/com/example/pictures/maze/maze_blue.png").toExternalForm());
    private GameLoop(GraphicsContext g){
        this.g=g;
        Maze.initMaze(level.getValue());

        
        pacman = new Pacman(16*13-8,16*23-8,this);
        pacman.setX(280);
        pacman.setY(168);
        ghost = new Ghost(16 * 13 - 8, 16 * 11 - 8, this);
        startTime = System.nanoTime();

    }
    static public GameLoop getInstance (GraphicsContext g){
        if(instance==null) instance = new GameLoop(g);
        //else instance.g = g;
        return instance;
        
        
    }

    public void start(){
        new AnimationTimer() {
            @Override
            public void handle(long now){
                if(now - last_update>=(double)1000000000/FPS){
                    
                    g.drawImage(mazeImage, 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
                        update();
                        render(g);
                        last_update = now;
                    
                    
                }
            
            }
       }.start();
    }

    public void update(){
        pacman.update();
        ghost.update();
    }

    public void render(GraphicsContext g){
        //g.drawImage(mazeImage, 0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
        pacman.render();
        ghost.render();
    }
    
    public GraphicsContext getG() {
        return this.g;
    }

    public void setG(GraphicsContext g) {
        this.g = g;
    }

    public int getFPS() {
        return this.FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public long getLast_update() {
        return this.last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

}
