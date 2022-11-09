/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.banking.business;

import com.niraj.banking.model.Account;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class BusinessLogicHardCoded implements BusinessLogic{
    public int accCount = 0;
    ArrayList<Account> ledger = new ArrayList();

    public BusinessLogicHardCoded() {
        
    }
    
    
    @Override
    public ArrayList<Account> listAll(){
        return ledger;
    }
    
    @Override
    public Account findAccount(int accNo){
        for(Account a: ledger){
            if(a.getAccNo() == accNo){
                return a;
            }
        }
        return null;
    }
    
    @Override
    public boolean deposit(int accNo, float amount){
        Account a = findAccount(accNo);
        if(a!=null){
            a.setAmount(a.getAmount()+amount);
            return true; // succesfully deposited
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
            return 1; // successfully withdrawn
        }
        else{
            return 0; // account bhetiyena
        }
    }
    
    @Override
    public boolean createAccount(int accNo, String name, String address, float amount, char accType){
        Account a = new Account(accNo, name, address, amount, accType);
        return ledger.add(a);
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
        trgt.setAmount(trgt.getAmount()+amount);
        return 1; //successfully transferred.
        
    }

    @Override
    public int findCurrentAccountCount() {
        return accCount+1;
    }

    @Override
    public void updateCurrentCount() {
        accCount++;
    }
    
}
