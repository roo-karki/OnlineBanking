/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.banking.business;

import com.niraj.banking.connector.dbConnector;
import com.niraj.banking.model.Account;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class BusinessLogicOracleImpl implements BusinessLogic{
    dbConnector dbConn;
    public BusinessLogicOracleImpl() {
        dbConn = new dbConnector("com.oracle.cj.jdbc.Driver", "jdbc:oracle://localhost:3306/bankdatabase", "root", "N3Ur0t0x1c");
    }

    
    @Override
    public void updateCurrentCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findCurrentAccountCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Account> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account findAccount(int accNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deposit(int accNo, float amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int withdraw(int accNo, float amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createAccount(int accNo, String name, String address, float amount, char accType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int fundTransfer(int srcAccNo, int trgtAccNo, float amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
