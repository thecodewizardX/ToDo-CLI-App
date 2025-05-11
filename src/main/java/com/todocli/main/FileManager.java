/*
* it acts like a local database using csv files
* I use the external library opnCsv to manipulate csv files.
* it is a utility class for manage data (tasks)
* */

package com.todocli.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class FileManager {

    public FileManager(String username) {
    }
    private final static Path  PENDING_TASKS_PATH = Paths.get(System.getProperty("user.home"), "todo_cli","pending_tasks.csv");
    private final static Path  COMPLETED_FILE_PATH = Paths.get(System.getProperty("user.home"), "todo_cli","completed_tasks.csv");
    private final static Path  DATABASE_PATH = Paths.get(System.getProperty("user.home"), "database","users.csv");
    static{
        try {
            Files.createDirectories(PENDING_TASKS_PATH.getParent());
            Files.createDirectories(COMPLETED_FILE_PATH.getParent());
            Files.createDirectories(DATABASE_PATH.getParent());
            if(!Files.exists(PENDING_TASKS_PATH)){
                Files.createFile(PENDING_TASKS_PATH);
            }
            if(!Files.exists(COMPLETED_FILE_PATH)){
                Files.createFile(COMPLETED_FILE_PATH);
            }
            if(!Files.exists(DATABASE_PATH)){
                Files.createFile(DATABASE_PATH);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveTasks(PriorityQueue<Task> pendingTasks, ArrayList<Task> completedTasks){
            try (CSVWriter writer = new CSVWriter(new FileWriter(PENDING_TASKS_PATH.toFile()))) {
                for (Task task : pendingTasks) {
                    String[] taskData = {task.getName(),task.getDescription(),
                            String.valueOf(task.getPriority()),String.valueOf(task.getStatus())};
                    writer.writeNext(taskData);
                }
            } catch (IOException e) {
                System.out.println("Error occurs : "+e.getMessage());
            }
        try (CSVWriter writer = new CSVWriter(new FileWriter(COMPLETED_FILE_PATH.toFile()))) {
            for (Task task : completedTasks) {
                String[] taskData = {task.getName(),task.getDescription(),
                        String.valueOf(task.getPriority()),String.valueOf(task.getStatus())};
                writer.writeNext(taskData);
            }
        } catch (IOException e) {
            System.out.println("Error occurs : "+e.getMessage());
        }
    }

    //  Load pending tasks from pending_tasks.csv
    public static PriorityQueue<Task> loadPendingTasks(){
        PriorityQueue<Task> pendingTasks = new PriorityQueue<>();
        try (CSVReader reader = new CSVReader(new FileReader(PENDING_TASKS_PATH.toFile()))) {
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                pendingTasks.add(new Task(nextLine[0],nextLine[1]
                        ,Integer.parseInt(nextLine[2]),Boolean.parseBoolean(nextLine[3])));
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error occurs : "+e.getMessage());
        }
        return pendingTasks;
    }

    //  Load COMPLETED tasks from  completed_tasks.csv
    public static ArrayList<Task> loadCompletedTasks(){
        ArrayList<Task> completedTasks = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(COMPLETED_FILE_PATH.toFile()))) {
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
               completedTasks.add(new Task(nextLine[0],nextLine[1]
                        ,Integer.parseInt(nextLine[2]),Boolean.parseBoolean(nextLine[3])));
            }
        } catch (IOException e) {
            System.out.println("Error occurs : "+e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return completedTasks;
    }
    public static boolean signIn(String username, String password){
        try (CSVReader reader = new CSVReader(new FileReader(DATABASE_PATH.toFile()))) {
            String[] user;
            while((user = reader.readNext()) != null){
                if(user[0].equals(username) && user[1].equals(password)){
                    return true;
                }
            }
            return false;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean signUp(String username, String password,String phoneNumber){
        if(signIn(username,password)){
            return false;
        }else{
            try (CSVWriter writer = new CSVWriter(new FileWriter(DATABASE_PATH.toFile(),true))) {
                writer.writeNext(new String[]{username,password,phoneNumber});
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
