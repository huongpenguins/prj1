package com.example.character;

import java.util.ArrayList;

public class AllGhost {
    public static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

    public static void update(){
        for (Ghost ghost : ghosts) {
            
            ghost.update();
        }
    }

    public static void render(){
        for (Ghost ghost : ghosts) {
            ghost.render();
        }
    }

    public static void setFighten(boolean a){
        for (Ghost ghost : ghosts) {
            ghost.isFighten=a;
            ghost.startFighten = System.currentTimeMillis();
            ghost.startChange = ghost.startFighten +6000;
            ghost.curAnimation.stop();
            ghost.curAnimation = ghost.frighten;
            ghost.curAnimation.start();

        }
    }

}
