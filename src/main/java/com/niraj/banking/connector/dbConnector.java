/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.banking.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dell
 */
public class dbConnector {
    private  String className;
    private  String url;
    private  String username;
    private  String password;
    Connection conn;
    
    public dbConnector(){
        //Step 1: Register the Driver Class.
        try{
            
            Class.forName(className);
            
            
        }catch(ClassNotFoundException ce){
            System.out.println(ce);
            System.out.println("The Driver class is not found.");
        }
    }

    public dbConnector(String className, String url, String username, String password) {
        this.className = className;
        this.url = url;
        this.username = username;
        this.password = password;
        try{
            
            Class.forName(this.className);
            
            
        }catch(ClassNotFoundException ce){
            System.out.println(ce);
            System.out.println("The Driver class is not found.");
        }
        
    }
    
    
    
    //1. Create // no of rows affected is returned.
    //2. Retrieve // actual rows is returned.
    //3. Update // no of rows
    //4. Delete // no of rows
    
    
    //Step 2 Creating a connection
    public void createConnection(){
        try{
            conn = DriverManager.getConnection(url, username, password);
            if(conn!=null){
                System.out.println("");
                //System.out.println("Database Connection successful.");
            }
        }catch(SQLException se){
            System.out.println(se);
            System.out.println("The Connection is not acquired. May be dbURL, username or password is wrong.");
        }
    }
    
    //Step 5: Close the connection.
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Cannot close connection.");
        }
    }
    
    
    public boolean cud(String sqlStatement){
        this.createConnection();
        try{
            //3. Create an Statement.
            Statement stmt = conn.createStatement();
            //4. Execute Statement.
            if(stmt.executeUpdate(sqlStatement) > 0){
                
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException se){
            System.out.println(se);
        }
        return false;
    }
    
    public ResultSet select(String sqlStatement){
        this.createConnection();
        try{
            //3. Create an Statement.
            Statement stmt = conn.createStatement();
            //4. Execute Statement.
            ResultSet res = stmt.executeQuery(sqlStatement);
            return res;
        }catch(SQLException se){
            System.out.println("Cannot Execute SELECT query.");
        }
        
        return null;
    }
}
