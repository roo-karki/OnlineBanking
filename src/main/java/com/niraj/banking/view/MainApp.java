/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.banking.view;

import com.niraj.banking.business.BusinessLogic;
import com.niraj.banking.business.BusinessLogicHardCoded;
import com.niraj.banking.business.BusinessLogicMogodbImpl;
import com.niraj.banking.business.BusinessLogicMySQLImpl;
import com.niraj.banking.model.Account;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class MainApp {
    static BusinessLogic bl = new BusinessLogicMySQLImpl();
    
    
    public static void displayMenu(){
        System.out.println("XYZ BANK");
        System.out.println("========");
        System.out.println("");
        System.out.println("1. Open New Account");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Withdraw Amount");
        System.out.println("4. List all Accounts");
        System.out.println("5. Account to Account Fund Transfer");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.println("Enter your choice: ");
    }
    
    public static void newAccount(){
        String name;
        String address;
        float amount;
        char accType;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome to New Account Creation.");
        System.out.println("================================");
        System.out.println("");
        System.out.println("Enter your name: ");
        name = sc.nextLine();
        System.out.println("Enter your address: ");
        address = sc.nextLine();
        System.out.println("Enter opening balance: ");
        amount = sc.nextFloat();
        sc.nextLine();
        System.out.println("Enter Account Type (C for current, S for saving, F for fixed: ");
        accType = sc.nextLine().charAt(0);
        
        if(bl.createAccount(bl.findCurrentAccountCount()+1, name, address, amount, accType)){
            System.out.println("New Account creation successfull.");
            System.out.println("");
            bl.updateCurrentCount();
            
        }
        else{
            System.out.println("Cannot create account.");
            System.out.println("");
        }
        
    }
    
    public static void listAll(){
        System.out.println("List of all accounts in bank.");
        System.out.println("=============================");
        System.out.println("");
        for(Account a: bl.listAll()){
            System.out.println(a);
        }
        System.out.println("");
    }
    public static void deposit(){
        int accNum;
        float amount;
        Account a;
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome to Deposit.");
        System.out.println("===================");
        System.out.println("");
        System.out.println("Enter your Account Number: ");
        accNum = sc.nextInt();
        System.out.println("Enter the Amount: ");
        amount = sc.nextFloat();
        
        if(bl.deposit(accNum, amount)){
            System.out.println("Deposit Sucessfull.");
        }
        else{
            System.out.println("Cannot find the account to deposit.");
        }
        System.out.println("");
    }
    
    public static void withdraw(){
        int accNum;
        float amount;
        Account a;
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome to Withdraw.");
        System.out.println("===================");
        System.out.println("");
        System.out.println("Enter your Account Number: ");
        accNum = sc.nextInt();
        System.out.println("");
        System.out.println("Enter the amount to withdraw: ");
        amount = sc.nextFloat();
        int status = bl.withdraw(accNum, amount);
        switch (status) {
            case -1:
                System.out.println("Insufficient balance. Cannot Withdraw.");
                break;
            case 0:
                System.out.println("Account Not found in our Bank.");
                break;
            default:
                System.out.println("Withdraw sucessful.");
                break;
        }
        System.out.println("");
    }
    
    public static void fundtransfer(){
        Account src, trgt;
        float amount;
        int srcAccNo, trgtAccNo;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("Welcome to fund transfer!");
        System.out.println("=========================");
        System.out.println("");
        
        System.out.println("Enter the source account number: ");
        srcAccNo = sc.nextInt();
        System.out.println("Enter the target account number: ");
        trgtAccNo = sc.nextInt();
        System.out.println("Enter the amount to transfer from source to target account: ");
        amount = sc.nextFloat();
        
        int status = bl.fundTransfer(srcAccNo, trgtAccNo, amount);
        switch(status){
            case -1:
                System.out.println("Source Account doesn't Exist.");
                break;
            case -2:
                System.out.println("Target Account doesn't Exist.");
                break;
            case 0:
                System.out.println("Insufficient fund in source account.");
                break;
            default:
                System.out.println("Fund transfer sucessfull.");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        int choice;
        
        do{
            displayMenu();
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch(choice){
                case 1: 
                    newAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    listAll();
                    break;
                case 0:
                    System.out.println("Exiting.");
                    break;
                case 5:
                    fundtransfer();
                    break;
                default:
                    System.out.println("Enter valid choices.");
            }
        }while(choice != 0);   
    }
}
