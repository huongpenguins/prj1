package com.example;

import com.example.character.AllGhost;
import com.example.character.Blinky;
import com.example.character.Clyde;
import com.example.character.Ghost;
import com.example.character.Inky;
import com.example.character.Pacman;
import com.example.character.Pinky;
import com.example.pellet.AllPellet;

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
    int FPS =60;
    private long last_update = 0;
    public long startTime;
    public long startFighten;
    public long startPacmanDeath;
    public long startWin;
    public long startGameOver;
    public long startGhostDeath;

    Maze maze;
    AllPellet pellets;
    public AllGhost ghosts = new AllGhost();
    public Pacman pacman;
    public Blinky blinky;
    public Inky inky;
    public Pinky pinky;
    public Clyde clyde;

    public int state;
    public  final int START=0;
    final int WIN=1;
    public final int GAMEOVER=2;
    public final int INGAME=3;
    public final int PACMANDEATH=5;
    public final int GHOSTDEATH = 6;

    
    Image mazeImage = new Image(getClass().getResource("/com/example/pictures/maze/maze_blue.png").toExternalForm());
    Image readyImage = new Image(getClass().getResource("/com/example/pictures/ready.png").toExternalForm());
    Image gameOverImage = new Image(getClass().getResource("/com/example/pictures/game_over.png").toExternalForm());
    private GameLoop(GraphicsContext g){
        this.g=g;
        Maze.initMaze(level.getValue(),this);

        pellets = new AllPellet();
        pacman = new Pacman(16*13-8,16*23-8,this);
        blinky = new Blinky(16*13-8,16*11-8,this);
        inky = new Inky(16*12-8, 16*14-8,this);
        pinky = new Pinky (16*13-8,16*14-8,this);
        clyde = new Clyde(16*14-8,16*14-8,this);
        state = START;
        startTime = System.currentTimeMillis();

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
        if(AllPellet.pellets.isEmpty()) state = WIN;
        switch (state) {
            
            case START:
                AllGhost.ghosts.remove(blinky);
                AllGhost.ghosts.remove(inky);
                AllGhost.ghosts.remove(pinky);
                AllGhost.ghosts.remove(clyde);

                blinky = new Blinky(16*13-8,16*11-8,this);
                inky = new Inky(16*12-8, 16*14-8,this);
                pinky = new Pinky (16*13-8,16*14-8,this);
                clyde = new Clyde(16*14-8,16*14-8,this);
                pacman = new Pacman(16*13-8,16*23-8,this);

                if(System.currentTimeMillis()-startTime>3000){
                    state = INGAME;
                    startTime = System.currentTimeMillis();
                    
                    
                }
                break;
            case INGAME:
                AllPellet.update();
                pacman.update();
                blinky.update();
                //AllGhost.update();
                break;
 
            case WIN:
                if(System.currentTimeMillis() - startWin > 3000){
                    state = START;
                    lives.set(3);
                    point.set(0);
                    lives.set(3);
                    AllPellet.pellets.removeAll(AllPellet.pellets);
                    Maze.initMaze(1, this);


                }
                break;
            case GHOSTDEATH:
                if(System.currentTimeMillis()- startGhostDeath >2000){
                    state = INGAME;
                    return;
                }
                AllPellet.update();
                for(Ghost ghost : AllGhost.ghosts){
                    if(ghost.isDeath == true) {
                        ghost.update();
                    }
                }
            
                break;
            case GAMEOVER:
                if(System.currentTimeMillis() - startGameOver > 3000){
                    state = START;
                    lives.set(3);
                    point.set(0);
                    lives.set(3);
                    AllPellet.pellets.removeAll(AllPellet.pellets);
                    Maze.initMaze(1, this);


                }
                else{

                }
                break;
            case PACMANDEATH:
                if(System.currentTimeMillis()-startPacmanDeath > 3000){
                    if(lives.get()>=0) state = START;
                    else state = GAMEOVER;
                    return;
                }
                else{
                    AllPellet.update();
                    pacman.update();
                }
                break;
        }

        
    }
    public void render(GraphicsContext g){
        pellets.render();

        switch (state) {
            case START:
                pacman.render();
                ghosts.render();
                g.drawImage(readyImage,13*16,19*16);
            case INGAME:
            pacman.render();
            ghosts.render();
                break;

            case WIN:
                
                break;
            case GHOSTDEATH:
                AllGhost.render();
                
                break;
            case GAMEOVER:
                 g.drawImage(gameOverImage,13*16,19*16);
                break;
            case PACMANDEATH:
                pacman.render();
                break;
        }
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
