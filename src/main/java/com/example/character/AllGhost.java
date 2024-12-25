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


}
