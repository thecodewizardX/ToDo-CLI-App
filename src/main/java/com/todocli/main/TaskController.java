/*
 * This class provide a CLI for basic operations like,
Features :
                1) Task Management
                Features :
                   1) Add Task
                   2) Remove Task
                   3) Edit Task
                   4) Display All Tasks
                   5) Back to MAIN MENU
                   6) Exit Application
                2) Task Status Management
                Features :
                   1) Mark As Completed
                   2) Mark As Uncompleted
                   3) View Completed Tasks
                   4) View Pending Tasks
                   5) Back to MAIN MENU
                   6) Exit Application
                3) EXIT Application
 */

package com.todocli.main;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TaskController {
    private final TaskManager taskManager;
    private final Scanner scan;

    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
        scan = new Scanner(System.in);
        addAll();    //Add both pending & completed tasks from dataSets
    }

    // To add task
    public void add() {
        System.out.print("Enter the task name : ");
        // to remove extra spaces at the end  $ means end of the string
        String taskName = scan.nextLine().replaceAll("\\s+$","");
        System.out.print("Enter the task priority : ");
        if(scan.hasNextInt()){
            int priority = scan.nextInt();
            scan.nextLine();         // To prevent from buffer issues
            System.out.print("Enter the task description : ");
            String description = scan.nextLine();
            Task task = new Task(taskName,description,priority);
            if(taskManager.add(task)){
                System.out.println("Task added...");
                return;
            }
            System.out.println("Error occurs...");
        }else{
            System.out.println("Invalid input. Try again.");
            scan.nextLine();  // To prevent buffer issues
        }
    }

    // Add all the both pending & completed tasks, used for load pending tasks
    public void addAll(){
        for(Task task : FileManager.loadPendingTasks()){
            taskManager.add(task);
        }
        for(Task task : FileManager.loadCompletedTasks()){
            taskManager.addCompletedTask(task);
        }
    }

    // To remove Task
    public void remove() {
        UIManager.printRemoveMenu();
        System.out.print("Enter the your option : ");
        if(scan.hasNextInt()){
            int option = scan.nextInt();
            scan.nextLine();
            switch(option){
                case 1:
                    System.out.print("Enter the task name to remove : ");
                    String taskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
                    for(Task task : taskManager.getTasks()){
                        if(task != null && task.getName() != null &&
                                task.getName().replaceAll("\\s","").equalsIgnoreCase(taskName)){
                            System.out.print("Are you sure to remove the task (Y/N) : ");
                            char choice = scan.nextLine().charAt(0);
                            if(choice == 'Y'||choice == 'y'){
                                taskManager.remove(task);
                                System.out.println("Task removed...");
                                return;
                            }
                            System.out.println("Task not removed...");
                        }
                    }
                    System.out.println("Can't find task...");
                    break;
                case 2 :
                    System.out.print("Enter the task name to remove : ");
                    String completedTaskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
                    for(Task task : taskManager.getCompletedTasks()){
                        if(task != null && task.getName() != null &&
                                task.getName().replaceAll("\\s","").equalsIgnoreCase(completedTaskName)){
                            taskManager.removeCompletedTask(task);
                            System.out.println("Task removed...");
                            return;
                        }
                        System.out.println("Task not removed....");
                    }
                    System.out.println("Can't find task...");
                    break;
                case 3 :
                    System.out.println("Are you sure to remove the all pending tasks (Y/N) : ");
                    char choice = scan.nextLine().charAt(0);
                    if(choice == 'Y'||choice == 'y'){
                        taskManager.removeAllPendingTasks();
                        System.out.println("All tasks are removed...");
                        return;
                    }
                    System.out.println("tasks not removed...");
                    break;
                case 4 :
                    System.out.println("Are you sure to remove the all completed tasks (Y/N) : ");
                    char choice2 = scan.nextLine().charAt(0);
                    if(choice2 == 'Y'||choice2 == 'y'){
                        taskManager.removeAllCompletedTasks();
                        System.out.println("All tasks are removed...");
                        return;
                    }
                    System.out.println("tasks not removed...");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }else{
            System.out.println("Invalid input. Try again.");
            scan.nextLine();
        }
    }
    //To edit Task
    public void editTask() {
        UIManager.printEditTaskMenu();
        System.out.print("Enter your option : ");
        if(scan.hasNextInt()){
            int option = scan.nextInt();
            scan.nextLine();    // To prevent from buffer issues
            if(option == 1){
                System.out.print("Enter the task name to edit : ");
                String prevTaskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
                for(Task task : taskManager.getTasks()){
                    if(task != null && task.getName() != null && task.getName().replaceAll("\\s","").equalsIgnoreCase(prevTaskName)){
                        System.out.print("Enter the name to update  : ");
                        // to remove extra spaces at the end  $ means end of the string
                        String newName = scan.nextLine().replaceAll("\\s+$","");
                        System.out.print("Are you sure to edit this task? (Y/N) : ");
                        char choice = scan.nextLine().charAt(0);
                        if(choice == 'Y' || choice == 'y'){
                            task.setName(newName);
                            System.out.println("Task name updated...");
                            return;
                        }
                        System.out.println("Task name not updated...");
                    }
                }
                System.out.println("can't find task...");
            } else if (option == 2) {
                System.out.print("Enter the task name to edit : ");
                String taskName = scan.nextLine().trim().toLowerCase();
                Task taskToUpdate = null;
                for(Task task : taskManager.getTasks()){
                    if(task != null && task.getName() != null && task.getName().trim().equalsIgnoreCase(taskName)){
                        taskToUpdate = task;
                    }
                    if(taskToUpdate != null){
                        System.out.print("Enter the priority to update  : ");
                        if(scan.hasNextInt()){
                            int priority = scan.nextInt();
                            scan.nextLine();   // to prevent Buffer issues
                            System.out.print("Are you sure to edit this task? (Y/N) : ");
                            char choice = scan.nextLine().charAt(0);
                            if(choice == 'Y' || choice == 'y'){
                                // Remove old task and add new because for maintain priority sorting.
                                taskManager.remove(taskToUpdate);
                                taskManager.add(new Task(taskToUpdate.getName(),taskToUpdate.getDescription(),priority));
                                System.out.println("Task priority updated...");
                                return;
                            }
                        }
                        System.out.println("Task priority not updated...");
                    }
                }
                System.out.println("can't find task");
            }else if(option == 3){
                System.out.print("Enter the task name to edit : ");
                String taskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
                for(Task task : taskManager.getTasks()){
                    if(task != null && task.getName() != null && task.getName().replaceAll("\\s","").equalsIgnoreCase(taskName)){
                        System.out.println("Enter the  description to update  : ");
                        String description = scan.nextLine();
                        System.out.print("Are you sure to edit this task? (Y/N) : ");
                        char choice = scan.nextLine().charAt(0);
                        if(choice == 'Y' || choice == 'y'){
                            task.setDescription(description);
                            System.out.println("Task description updated...");
                            return;
                        }
                        System.out.println("Task description not updated...");
                    }
                }
                System.out.println("can't find task");
            }
            else{
                System.out.println("Wrong option!");
            }
        }else{
            System.out.println("Invalid input. Try again.");
            scan.nextLine();    // To prevent from buffer issues
        }
    }
    // To View  pending tasks
    public void viewTasks(){
        if(taskManager.getTasks().isEmpty()){
            System.out.println("........          No pending tasks found             ........");
            return;
        }
        UIManager.printTaskTableHeader();
        PriorityQueue<Task> tasks = new PriorityQueue<>(taskManager.getTasks());
        while(!tasks.isEmpty()){
            Task task = tasks.poll();
            System.out.println(task);
        }
        UIManager.printTaskTableFooter();
    }
    // To mark a Task as completed
    public void markAsCompleted() {
        System.out.print("Enter the task name to complete : ");
        String taskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
        for(Task task : taskManager.getTasks()){
            if(task != null && task.getName() != null &&
                    task.getName().replaceAll("\\s","").equalsIgnoreCase(taskName)){
                task.setStatus(true);
                taskManager.addCompletedTask(task);
                taskManager.remove(task);
                System.out.println("Task completed...");
                return;
            }
        }
        System.out.println("Can't find task...");
    }
    // To Mark as unComplete
    public void markAsUnComplete() {
        System.out.print("Enter the task name to change pending task : ");
        String taskName = scan.nextLine().replaceAll("\\s","").toLowerCase();
        for(Task task : taskManager.getCompletedTasks()){
            if(task != null && task.getName() != null &&
                    task.getName().replaceAll("\\s","").equalsIgnoreCase(taskName)){
                task.setStatus(false);
                taskManager.removeCompletedTask(task);
                taskManager.add(task);
                System.out.println("Task changed to pending...");
                return;
            }
        }
        System.out.println("Can't find task...");
    }
    // To display Completed tasks
    public void viewCompletedTasks() {
        if(taskManager.getCompletedTasks().isEmpty()){
            System.out.println("........         No completed tasks found            ........");
            return;
        }
        UIManager.printTaskTableHeader();
        for(Task task : taskManager.getCompletedTasks()){
            System.out.println(task);
        }
       UIManager.printTaskTableFooter();
    }
    public void saveAllTasks(){
        taskManager.saveAllTasks();
    }
    public void viewAllTasks(){
        // we use poll for print the tasks, the for each loop or iteration does not maintain priority order
        UIManager.printTaskTableHeader();
        PriorityQueue<Task> tasks = new PriorityQueue<>(taskManager.getTasks());
        while(!tasks.isEmpty()){
            Task task = tasks.poll();
            System.out.println(task);
        }
       for(Task task : taskManager.getCompletedTasks()){
           System.out.println(task);
       }
        UIManager.printTaskTableFooter();
    }
}
