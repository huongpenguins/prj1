package com.example.character;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.image.Image;



public abstract class Ghost extends Characters {

    char[][] mazeInHouse;
    
    long startEatPacman;
    long startFighten;
    long starteaten; 
    long startChange;
    public boolean isDeath=false; 
    public boolean isEye=false;

    Animation die;
    Animation eatPacman;
    Animation frighten;
    Animation eaten;
    Animation eyeUp;
    Animation eyeDown;
    Animation eyeLeft;
    Animation eyeRight;

    Image frImage = new Image(getClass().getResource("/com/example/pictures/eye/scared_blue.png").toExternalForm());
    Image white_ghost = new Image(getClass().getResource("/com/example/pictures/eye/scared_white.png").toExternalForm());
    Image eatImage = new Image(getClass().getResource("/com/example/pictures/pacman/100.png").toExternalForm());
    Image eyeUpImage = new Image(getClass().getResource("/com/example/pictures/eye/eyes_up.png").toExternalForm());
    Image eyeDownImage = new Image(getClass().getResource("/com/example/pictures/eye/eyes_down.png").toExternalForm());
    Image eyeLeftImage = new Image(getClass().getResource("/com/example/pictures/eye/eyes_left.png").toExternalForm());
    Image eyeRightImage = new Image(getClass().getResource("/com/example/pictures/eye/eyes_right.png").toExternalForm());

    boolean isFighten=false;
    boolean inHouse=true;
    long timeInHouse;

    int state;
    final int MOVING=0; // di chuyen theo dich
    final int FRIGHTENED=1; // so hai
    final int EATEN=2; // bi an
    final int EATPACMAN=3; // an pacman

    int[][] scatterPoints;
    int [] scatterPoint;
    

    public Ghost(double x, double y,GameLoop gameLoop) {
        super(x,y,32,32,1.6,gameLoop);
        AllGhost.ghosts.add(this);
        frighten = new Animation(frImage,gameLoop.getG(),this,1000000000/12);
        eyeRight = new Animation(eyeRightImage, gameLoop.getG(), this, 1000000000/12);
        eyeLeft = new Animation(eyeLeftImage, gameLoop.getG(), this, 1000000000/12);
        eyeUp = new Animation(eyeUpImage, gameLoop.getG(), this, 1000000000/12);
        eyeDown = new Animation(eyeDownImage, gameLoop.getG(), this, 1000000000/12);
        eaten = new Animation(eatImage, gameLoop.getG(), this, 1000000000/12);
        mazeInHouse=(gameLoop.getMaze().maze[level]).clone();
        this.mazeInHouse[12][13]='o';
        this.mazeInHouse[12][14]='o';
        isFighten=false;
        state = MOVING;
        direction = UP;
        nextDirection = UP;
 
        
    }

    abstract public int[] getDestination(char maze[][]);
    @Override
    public void update(){
        if(gameLoop.state == gameLoop.INGAME||gameLoop.state ==gameLoop.GHOSTDEATH){

        int curI= (int)((y+8)/16+1e-3);
        double m=(x+8)/16+1e-3;
        int curJ = (int)((x+8)/16+1e-3);

            if(inHouse == true){
                if(System.currentTimeMillis() - gameLoop.startTime >timeInHouse){
                    if(Math.abs(x-200)<1e-5&&Math.abs(y-168)<1e-5){
                        state=MOVING;
                        inHouse=false;
                        return;
                    }
                    int[] p=findDestination(11, 13,mazeInHouse);
                    if(p!=null){
                      updateDirection(findDestination(11, 13,mazeInHouse),mazeInHouse);  
                    }
                   
                    move(mazeInHouse);
                }
                else{
                    return;
                }
            }

            else{

            
                switch (state) {
                    
                    case MOVING:
                        if(isFighten ==true){
                                    state =FRIGHTENED;
                                    curAnimation.stop();
                                    curAnimation = frighten;
                                    curAnimation.start();
                                    return;
                        
                                }
                        else if(checkCollisionWithPacman()){
                            state =EATPACMAN;
                            startEatPacman= System.currentTimeMillis();

                            return;
                        } 
                        
                        int point[]=getDestination(thisMaze);
                        if(point!=null) {
                            int p[] = findDestination(point[0], point[1], thisMaze);
                            if(p!=null){
                            updateDirection(p,thisMaze);
                        }
                        }
                        move(thisMaze);
                        
                        break;

                    case EATPACMAN:
                        break;
                    case EATEN:
                        if(System.currentTimeMillis()- starteaten<1500){
                            isDeath =true;
                            curAnimation.stop();
                            curAnimation= eaten;
                            curAnimation.start();
                        }
                        else{
                            
                            if(Math.abs(x-200)<1e-5&&Math.abs(y- 168)<1e-5){
                                isDeath=false;
                                setV(1.6);
                                state = MOVING;
                                isEye = false;
                                gameLoop.state = gameLoop.INGAME;
                                isFighten =false;
                                curAnimation.stop();
                                curAnimation = moveRight;
                                curAnimation.start();
                                return;

                            }
                            else{
                                setV(4);
                                curAnimation.stop();
                                curAnimation = eyeRight;
                                curAnimation.start();
                        
                                updateDirection(findDestination(11, 13, thisMaze),thisMaze);
                                move(thisMaze);
                            }

                        }
                    break;
                    case FRIGHTENED:
                        
                        if( checkCollisionWithPacman()&&isEye==false){
                            state = EATEN;
                            isEye=true;
                            gameLoop.state = gameLoop.GHOSTDEATH;
                            gameLoop.startGhostDeath = System.currentTimeMillis();
                            curAnimation.stop();
                            curAnimation = eaten;
                            curAnimation.start();
                            starteaten = System.currentTimeMillis();
                            return;
                        }

                        point=getDestination(thisMaze);
                        if(point!=null)
                        updateDirection(findDestination(point[0], point[1], thisMaze),thisMaze);
                        move(thisMaze);
                        
                        if(System.currentTimeMillis()-startFighten>8000){

                            if (System.currentTimeMillis()-startChange==500){
                                Image image;
                                image = (curAnimation.getImage()==frImage) ? white_ghost : frImage;
                                curAnimation.setImage(image);
                                startChange=System.currentTimeMillis();
                            }
                            
                        }
                    
                        if(System.currentTimeMillis()-startFighten>=10000){
                            
                            isFighten =false;
                            state =MOVING;
                            curAnimation.stop();
                            curAnimation = moveRight;
                            curAnimation.start();
                            
                        }
                        
                        break;
                    
                }
            }
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

    
    
    public void updateDirection(int[] next,char[][] thisMaze){
        int curI= (int)((y+8)/16+1e-3);
        double m=(x+8)/16+1e-3;
        int curJ = (int)((x+8)/16+1e-3);

        
        if(next[0]>curI){
            nextDirection=DOWN;
            if(!checkCollisionWitMaze(thisMaze, x, y+v)){
                direction = DOWN;
            }
            else{
                if(x-next[1]*16+8>1e-4){
                    direction = LEFT;
                }
                if(next[1]*16 - x - 8>1e-4){
                    direction = RIGHT;
                }
            }
            
        }
        if(next[0]<curI){
            nextDirection= UP;
            if(!checkCollisionWitMaze(thisMaze, x, y-v)){
                direction = UP; 
            }
            else{
                if(x-next[1]*16+8>1e-4){
                    direction = LEFT;
                }
                if(next[1]*16 - x - 8>1e-4){
                    direction = RIGHT;
                }
            }

        }
        if(next[1]>curJ){
            nextDirection=RIGHT;
            if(!checkCollisionWitMaze(thisMaze, x+v, y)){
                direction = RIGHT;
            }
            else{
                if(y-next[0]*16+8>1e-4){
                    direction = UP;
                }
                if(next[0]*16 - y - 8>1e-4){
                    direction = DOWN;
                }
            }
            
        }
        if(next[1]<curJ){
            nextDirection=LEFT;
            if(!checkCollisionWitMaze(thisMaze, x-v, y)){
                direction = LEFT;
            }
            else{
                if(y-next[0]*16+8>1e-4){
                    direction = UP;
                }
                if(next[0]*16 - y - 8>1e-4){
                    direction = DOWN;
                }
            }
            

        }
        if(next[0] == curI&&next[1]==curJ){
            switch (direction) {
                case UP:
                    if(Math.abs(y-next[0]*16+8)<1e-4) {
                        if(x-next[1]*16+8>1e-4){
                            nextDirection = LEFT;
                        }
                        if(next[1]*16-8-x>1e-4){
                            nextDirection = RIGHT;
                        }
                    }
                    else{
                        return;
                    }
                    break;
                case DOWN:
                if(Math.abs(y-next[0]*16+8)<1e-4) {
                    if(x-next[1]*16+8>1e-4){
                        nextDirection = LEFT;
                    }
                    if(next[1]*16-8-x>1e-4){
                        nextDirection = RIGHT;
                    }
                }
                else{
                    return;
                }
                    break;
                case LEFT:
                if(Math.abs(x-next[1]*16+8)<1e-4) {
                    if(y-next[0]*16+8>1e-4){
                        nextDirection = UP;
                    }
                    if(next[0]*16-8-y>1e-4){
                        nextDirection = DOWN;
                    }
                }
                else{
                    return;
                }
                    break;
                case RIGHT:
                if(Math.abs(x-next[1]*16+8)<1e-4) {
                    if(y-next[0]*16+8>1e-4){
                        nextDirection = UP;
                    }
                    if(next[0]*16-8-y>1e-4){
                        nextDirection = DOWN;
                    }
                }
                else{
                    return;
                }
                    break;
                    
            }
        }
    }
    public int[] findRandomDes(){
        int[] des;
        int curI = (int)((y+8)/16+1e-5);
            int curJ = (int)((x+8)/16+1e-5);
            des = scatterPoint;
            if (curI == scatterPoint[0] && curJ == scatterPoint[1]) {
                Random random = new Random();
                int randomIndex = random.nextInt(scatterPoints.length);
                scatterPoint = scatterPoints[randomIndex];
                des = scatterPoint;
            }
        return des;
    }
    /**
     * Su dung thuat toan A* tim duong di ngan nhat
     * @param x hang muc tieu
     * @param y cot muc tieu
     * @return tra ve o tiep theo se di
     */
    public int[] findDestination(int desI,int desJ,char[][] maze){
        int firstI = (int) ((y+8)/16+1e-4);
        int firstJ = (int) ((x+8)/16+1e-4);
       PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.f));
       HashMap<String, Node> closeList = new HashMap<>();
        Node start = new Node(firstI,firstJ,0,getHeuristic(firstI,firstJ,desI,desJ),null);
        openList.add(start);

        while(!openList.isEmpty()){
            
            Node current = openList.poll(); // lay va loai bo phan tu o dau hang doi
            if(closeList.containsKey(current.toString())) {
                    continue;
    
            }
            closeList.put(current.toString(),current);

            if(current.i == desI && current.j == desJ){
                if(current.parent==null) {
                    return new int[]{current.i,current.j};
                }
                
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
                    if(maze[curI+i][curJ+j]=='X') continue;
                    int nextI = curI+i;
                    int nextJ = curJ+j;
                    double nextG = current.g+1;
                    double nextH = getHeuristic(nextI,nextJ,desI,desJ);
                    Node child = new Node(nextI,nextJ,nextG,nextH,current);
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
