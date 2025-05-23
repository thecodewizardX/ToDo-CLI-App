/*
 * This is the main class, for the application.
 *
 * */


package com.todocli.main;

import java.util.Scanner;

public class ToDo {

    public static void startApp(String userName) {
        TaskManager taskManager = new TaskManager();
        TaskController taskController = new TaskController(taskManager,userName);

        UIManager.printBanner();   // Print the banner
        displayMainMenu(taskController);
    }

    private static void displayMainMenu(TaskController taskController) {
        Scanner scan = new Scanner(System.in);
        while(true){
            UIManager.displayMainMenu();
            System.out.print("Enter your choice: ");
            if(scan.hasNextInt()){
                int choice = scan.nextInt();
                scan.nextLine();   // to prevent from buffer issues
                switch(choice){
                    case 1 -> displayTaskManagementMenu(taskController);
                    case 2 -> displayTaskStatusMenu(taskController);
                    case 3 -> {
                        System.out.print("Do you want to exit? (Y/N) : ");
                        char answer = Character.toLowerCase(scan.nextLine().charAt(0));
                        if(answer == 'y'){
                            System.out.println("ToDo Auto Save all tasks...");
                            System.out.println("Exiting...");
                            taskController.saveAllTasks();
                            System.exit(0);
                        }else{
                            System.out.println("Cancelled...");
                        }
                    }
                    default -> System.out.println("Invalid choice! TRY AGAIN.");
                }
            }else {
                System.out.println("Enter valid number!.");
                scan.nextLine();  //To prevent buffer issue
            }

        }
    }
    private static void displayTaskManagementMenu(TaskController taskController) {
        while(true){
            Scanner scan = new Scanner(System.in);
            UIManager.displayTaskManagementMenu();
            System.out.print("Enter your choice: ");
            if(scan.hasNextInt()){
                int choice = scan.nextInt();
                scan.nextLine();  // To prevent from buffer issues
                switch(choice){
                    case 1 -> taskController.add();
                    case 2 -> taskController.remove();
                    case 3 -> taskController.editTask();
                    case 4 -> taskController.viewAllTasks();
                    case 5 -> displayMainMenu(taskController);
                    case 6 -> {
                        System.out.print("Do you want to exit? (Y/N) : ");
                        char answer = Character.toLowerCase(scan.nextLine().charAt(0));
                        if(answer == 'y'){
                            System.out.println("ToDo Auto Save all tasks...");
                            System.out.println("Exiting...");
                            taskController.saveAllTasks();
                            System.exit(0);
                        }else{
                            System.out.println("Cancelled...");
                        }
                    }
                    default -> System.out.println("Invalid choice! TRY AGAIN.");
                }
            }else {
                System.out.println("Enter valid number!.");
                scan.nextLine();  //To prevent buffer issue
            }
        }
    }
    private static void displayTaskStatusMenu(TaskController taskController) {
        while(true){
            Scanner scan = new Scanner(System.in);
            UIManager.displayTaskStatusMenu();
            System.out.print("Enter your choice: ");
            if(scan.hasNextInt()){
                int choice = scan.nextInt();
                scan.nextLine();   // to prevent from buffer issues
                switch(choice){
                    case 1 -> taskController.markAsCompleted();
                    case 2 -> taskController.markAsUnComplete();
                    case 3 -> taskController.viewCompletedTasks();
                    case 4 -> taskController.viewTasks();
                    case 5 -> displayMainMenu(taskController);
                    case 6 -> {
                        System.out.print("Do you want to exit? (Y/N) : ");
                        char answer = Character.toLowerCase(scan.nextLine().charAt(0));
                        if(answer == 'y'){
                            System.out.println("ToDo Auto Save all tasks...");
                            System.out.println("Exiting...");
                            taskController.saveAllTasks();
                            System.exit(0);
                        }else{
                            System.out.println("Cancelled...");
                        }
                        break;
                        }
                    default -> System.out.println("Invalid choice! TRY AGAIN.");
                }
            }else {
                System.out.println("Enter valid number!.");
                scan.nextLine();  //To prevent buffer issue
            }
        }
    }
}
