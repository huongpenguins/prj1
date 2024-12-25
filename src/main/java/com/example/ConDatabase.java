package com.example;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConDatabase{
    static String url = "jdbc:sqlite:prj1\\src\\main\\resources\\com\\example\\data\\prj1_db.db";
    static Statement st;
    public  Connection connect(){
     
        try {
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return null;
    }
    public void createTable() {
        Connection c = connect();
        try {
             st = c.createStatement();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        String rq = "CREATE TABLE GAME (ID VARCHAR NOT NULL,VALUE INT NOT NULL,PRIMARY KEY(ID),CONSTRAINT VALUES_CHK CHECK (VALUE>=0));" ;
        try {
            st.execute(rq);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
    }
    public void insert() {
        Connection c = connect();
        try {
             st = c.createStatement();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        String rq = "INSERT INTO GAME VALUES (\'POINT\',0),(\'LIVES\',3),(\'LEVEL\',1)" ;
        try {
            st.execute(rq);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    public void update(int point,int live,int level){
        Connection c = connect();
        try {
             st = c.createStatement();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        String rq1 = "UPDATE GAME SET VALUE ="+point+"WHERE ID = 'POINT'" ;
        String rq2 = "UPDATE GAME SET VALUE ="+live+"WHERE ID = 'LIVES'" ;
        String rq3 = "UPDATE GAME SET VALUE ="+level+"WHERE ID = 'LEVEL'" ;
        try {
            st.execute(rq1);
            st.execute(rq2);
            st.execute(rq3);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    public int select(String s) {
        Connection c = connect();
        int value=-1;
        try {
             st = c.createStatement();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        String rq = "SELECT VALUE FROM GAME WHERE ID = "+"\'"+s+"\'" ;
        try {
            value =  st.executeQuery(rq).getInt("VALUE");
             
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return value;
    }
}
