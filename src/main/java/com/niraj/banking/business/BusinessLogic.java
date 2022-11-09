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
public interface BusinessLogic {
    public void updateCurrentCount();
    public int findCurrentAccountCount();
    public ArrayList<Account> listAll();
    public Account findAccount(int accNo);
    public boolean deposit(int accNo, float amount);
    public int withdraw(int accNo, float amount);
    public boolean createAccount(int accNo, String name, String address, float amount, char accType);
    public int fundTransfer(int srcAccNo, int trgtAccNo, float amount);
}
