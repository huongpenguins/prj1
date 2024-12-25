package com.example.character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import com.example.Animation;
import com.example.GameLoop;
import com.example.Maze;

import javafx.scene.image.Image;


public class Ghost extends Characters {

     

    Animation curAnimation;
    Animation moveUp;
    Animation moveDown;
    Animation moveLeft;
    Animation moveRight;
    Animation die;
    Animation eatGhost;
    Image up = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_up.png").toExternalForm());
    Image down = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_down.png").toExternalForm());
    Image left = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_left.png").toExternalForm());
    Image right = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_right.png").toExternalForm());
    

    int state;
    final int CHASE=0; // duoi theo pacman
    final int SCATTER=1; // di ngau nhien
    final int FRIGHTENED=2; // so hai
    final int EATEN=3; // bi an
    final int EATPACMAN=4; // an pacman

    int scatterPoint;
    
    int nextDirection;
    public int direction;
    final int UP = 0;  
    final int DOWN = 1;
    final int LEFT = 2;
    public final int RIGHT = 3;
   

    public Ghost(double x, double y,GameLoop gameLoop) {
        super(x,y,32,32,3.2,gameLoop);
        AllGhost.ghosts.add(this);
        moveRight = new Animation(up, gameLoop.getG(), this, 1000000000/12);
        state = SCATTER;
        curAnimation= moveRight;
        curAnimation.start();
    }


    public void update(){
        int curI= (int)(y+8)/16;
        int curJ = (int)(x+8)/16;

        
        
        switch (state) {
            case SCATTER:
                if(checkCollisionWithPacman()){
                state =EATPACMAN;
            }
                int[] point = findDestination((int)(gameLoop.getPacman().getY()+8)/16, (int)(gameLoop.getPacman().getX()+8)/16);
                
                if(point == null) {
                    System.out.println("null");
                    System.out.println(gameLoop.getPacman().getX() );
                    System.out.println(gameLoop.getPacman().getY());
                    return;
                }
                
                break;
        
            default:
                break;
        }
    }
    
    public void render(){
        curAnimation.draw();

    }  
     public boolean checkCollisionWithPacman() {
        if (this.getBound().getBoundsInParent().intersects(gameLoop.getPacman().getBound().getBoundsInParent())) {
            return true;
        }
       return false;
    }
    /**
     * Su dung thuat toan A* tim duong di ngan nhat
     * @param x hang muc tieu
     * @param y cot muc tieu
     * @return tra ve o tiep theo se di
     */
    public int[] findDestination(int desI,int desJ){
        int firstI = (int)(y+8)/16;
        int firstJ = (int) (x+8)/16;
       PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.f));
       HashMap<String, Node> closeList = new HashMap<>();
        Node start = new Node(firstI,firstJ,0,getHeuristic(firstI,firstJ,desI,desJ),null);
        openList.add(start);
        while(!openList.isEmpty()){
            
            Node current = openList.poll(); // lay va loai bo phan tu o dau hang doi
            if(closeList.containsKey(current.toString())) {
                if(closeList.get(current.toString()).f>current.f){
                    closeList.remove(current.toString());
                    closeList.put(current.toString(),current);
                }
                else{
                    continue;
                }
                
            }
            closeList.put(current.toString(),current);

            if(current.i == desI && current.j == desJ){
                while(current.parent.parent!=null){
                    current = current.parent;
                }
                int[] next = new int[]{current.i,current.j};
                return next;
            }
           
           for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if(i==0&&j==0) continue;
                    if(i!=0&&j!=0) continue;
                    int curI = current.i;
                    int curJ = current.j;
                    
                    if(curI+i<0||curJ+j<0||curI+i>=31||curJ+j>=28) continue;
                    if(gameLoop.getMaze().maze[level][curI+i][curJ+j]=='X') continue;
                    int nextI = curI+i;
                    int nextJ = curJ+j;
                    double nextG = current.g+1;
                    double nextH = getHeuristic(nextI,nextJ,desI,desJ);
                    Node child = new Node(nextI,nextJ,nextG,nextH,current);
                    if(!closeList.containsKey(child.toString()))
                    openList.add(child);


                }

           }
        }
        return null;
    }
/**
 * 
 * @param x hang hien tai
 * @param y cot hien tai
 * @param targetX hang muc tieu
 * @param targetY cot muc tieu
 * @return khoang khach uoc luong giua 2 o
 */
    public double getHeuristic(int x,int y,int targetX,int targetY){
        return Math.sqrt((targetX-x)*(targetX-x)+(targetY-y)*(targetY-y));
    }
    public static class Node{
        int i,j; // cot, hang cua Node
        double f,g,h; // f = g + h; // f la tong chi phi uoc tinh
                   // g la chi phi tu diem bat dau den diem hien tai
                     // h la chi phi uoc tinh tu diem hien tai den diem dich
        Node parent;
        public Node(int i,int j){
            this.i = i;
            this.j = j;
            this.g = Integer.MAX_VALUE;
            this.parent = null;

        }
        public Node(int i,int j,double g,double h,Node parent){
            this.i = i;
            this.j = j;
            this.g = g;
            this.h = h;
            f= g+h;
            this.parent = parent;
        }
        @Override
        public String toString() {
            return i + "+" + j;
        }


    }

}
