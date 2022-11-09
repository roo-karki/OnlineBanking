/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.banking.business;

import com.niraj.banking.connector.dbConnector;
import com.niraj.banking.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class BusinessLogicMySQLImpl implements BusinessLogic{
 
    dbConnector dbConn;
    public int accCount;
    //ArrayList<Account> ledger = new ArrayList();

    public BusinessLogicMySQLImpl() {
        dbConn = new dbConnector("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/bankdatabase", "root", "N3Ur0t0x1c");
        accCount = findCurrentAccountCount();
        System.out.println("Current accno = "+accCount);
    }
    
    @Override
    public int findCurrentAccountCount(){
        String sqlStmt = "SELECT account.accNo FROM bankdatabase.account;";
        ResultSet rs = dbConn.select(sqlStmt);
        int currentCount = 1;
        try{
            while(rs.next()){
                currentCount = rs.getInt("accNo")+1;
            }
        }catch(SQLException se){
            System.out.println(se);
        }
        dbConn.closeConnection();
        return currentCount;
    }
    
    @Override
    public ArrayList<Account> listAll(){
        String sqlStmt = "SELECT * FROM bankdatabase.account;";
        ResultSet rs = dbConn.select(sqlStmt);
        ArrayList<Account> aList = new ArrayList<>();
        try{
            while(rs.next()){
                Account acc = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getString("address"), rs.getFloat("amount"), rs.getString("accType").charAt(0));
                aList.add(acc);
            }
        }catch(SQLException se){
            System.out.println(se);
        }
        dbConn.closeConnection();
        return aList;
        
    }
    
    @Override
    public Account findAccount(int accNo){
        String sqlStmt = "SELECT * FROM bankdatabase.account WHERE account.accNo='"+accNo+"';";
        ResultSet rs = dbConn.select(sqlStmt);
        Account acc;
        try{
            if(rs.next()){
                acc = new Account(rs.getInt("accNo"), rs.getString("name"), rs.getString("address"), rs.getFloat("amount"), rs.getString("accType").charAt(0));
                dbConn.closeConnection();
                return acc;
            }
        }catch(SQLException se){
            System.out.println("No result Set.");
        }
        dbConn.closeConnection();
        return null;
    }
    
    @Override
    public boolean deposit(int accNo, float amount){
        Account a = findAccount(accNo);
        if(a!=null){
            a.setAmount(a.getAmount()+amount);
            String sqlStmt = "UPDATE `bankdatabase`.`account` SET `amount` = '"+a.getAmount()+"' WHERE (`accNo` = '"+a.getAccNo()+"');";
            boolean status = dbConn.cud(sqlStmt);
            dbConn.closeConnection();
            return status;
        }
        else{
            return false; // account bhetiyena
        }
    }
    
    @Override
    public int withdraw(int accNo, float amount){
        Account a = findAccount(accNo);
        if(a!=null){
            float currentBalance = a.getAmount();
            if(currentBalance < amount){
                return -1;// here -1 refers to insuffieicent balance
            }
            a.setAmount(currentBalance-amount);
            String sqlStmt = "UPDATE `bankdatabase`.`account` SET `amount` = '"+a.getAmount()+"' WHERE (`accNo` = '"+a.getAccNo()+"');";
            dbConn.cud(sqlStmt); 
            dbConn.closeConnection();
            return 1; // successfully withdrawn
        }
        else{
            return 0; // account bhetiyena
        }
    }
    
    @Override
    public boolean createAccount(int accNo, String name, String address, float amount, char accType){
        Account a = new Account(accNo, name, address, amount, accType);
        String sqlstmt = "INSERT INTO `bankdatabase`.`account` (`accNo`, `name`, `address`, `amount`, `accType`) VALUES ('"+accNo+"', '"+name+"', '"+address+"', '"+amount+"', '"+accType+"');";
        boolean status = dbConn.cud(sqlstmt);
        dbConn.closeConnection();
        return status;
    }
    
    @Override
    public int fundTransfer(int srcAccNo, int trgtAccNo, float amount){
        Account src = findAccount(srcAccNo);
        if(src == null){
            return -1; // source account doesnt exist.
        }
        Account trgt = findAccount(trgtAccNo);
        if(trgt == null){
            return -2; // target account doesnt exist.
        }
        
        if(src.getAmount()<amount){
            return 0; // not sufficient fund.
        }
        src.setAmount(src.getAmount()-amount);
        String sqlStmt = "UPDATE `bankdatabase`.`account` SET `amount` = '"+src.getAmount()+"' WHERE (`accNo` = '"+src.getAccNo()+"');";
        dbConn.cud(sqlStmt); 
        dbConn.closeConnection();
        trgt.setAmount(trgt.getAmount()+amount);
        String sqlStmt2 = "UPDATE `bankdatabase`.`account` SET `amount` = '"+trgt.getAmount()+"' WHERE (`accNo` = '"+trgt.getAccNo()+"');";
        dbConn.cud(sqlStmt2); 
        dbConn.closeConnection();
        return 1; //successfully transferred.   
    }

    @Override
    public void updateCurrentCount() {
        accCount = findCurrentAccountCount();
    }
    
}
