package com.todocli.main;

import java.util.Scanner;

public class Authentication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("--------------      Welcome to ToDo app        ------------");
        UIManager.displayLoginMenu();
        while(true){
            System.out.print("Enter your option : ");
            int option = Integer.parseInt(scan.nextLine());
            switch (option) {
                case 1 -> {
                    System.out.println("---------------------- Welcome Back User ----------------------------");
                    System.out.print("Enter username : ");
                    String username = scan.nextLine();
                    System.out.print("Enter password : ");
                    String password = scan.nextLine();
                    if(username != null && password != null) {
                        if(FileManager.signIn(username, password)){
                            ToDo.startApp(username);
                        }else{
                            System.out.println("Invalid username or password");
                        }
                    }
                }
                case 2 -> {
                    System.out.println("---------------------- Welcome to ToDo App ----------------");
                    System.out.print("Enter username : ");
                    String userName = scan.nextLine();
                    System.out.print("Enter password : ");
                    String passWord = scan.nextLine();
                    System.out.print("Enter phone number : ");
                    String phoneNumber = scan.nextLine();
                    if(userName != null && passWord != null) {
                        if(FileManager.signUp(userName,passWord,phoneNumber)){
                            ToDo.startApp(userName);
                        }else{
                            System.out.println("Already logged in");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Exiting .....");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}

