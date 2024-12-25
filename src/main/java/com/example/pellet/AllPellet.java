package com.example.pellet;

import java.util.ArrayList;

import com.example.character.Pacman;

public class AllPellet {
    public static ArrayList<Pellet> pellets = new ArrayList<Pellet>();
    
    public static void update(){
        for (Pellet pellet : pellets) {
            if(pellet.isEaten){
                pellets.remove(pellet);
            }
            else{
                pellet.update();
            }
        }
    }
    public static void render(){
        for (Pellet pellet : pellets) {
            pellet.render();
        }
    }
    public static Pellet checkCollision(Pacman Player){
        for (Pellet pellet : pellets) {
            if(pellet.checkCollisionWithPacman(Player)){
                return pellet;
            }
        }
         return null;
    }
    


}
