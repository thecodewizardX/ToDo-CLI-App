package com.todocli.main;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TaskManager{
    private final PriorityQueue<Task> tasks;              // For maintain sorted queue based by priority
    private final ArrayList<Task> completedList;          // For maintain completed Tasks, it maintains insertion order because  we want first finished, first show

    public TaskManager(){
        tasks = new PriorityQueue<>();
        completedList = new ArrayList<>();
    }


    // To add tasks and stored in pending tasks
    public boolean add(Task task){
        return tasks.offer(task);
    }
    // To remove tasks from the pending tasks
    public void remove(Task task){
        tasks.remove(task);
    }
    // to return pending tasks
    public PriorityQueue<Task> getTasks(){
        return tasks;
    }
    // To remove all the tasks  from pending tasks
    public void removeAllPendingTasks(){
        tasks.clear();
    }


    // To remove all the tasks from completed tasks
    public void removeAllCompletedTasks(){
        completedList.clear();
    }
    // To add completed tasks act like a draft
    public void addCompletedTask(Task task){
        completedList.add(task);
    }
    // To remove from completed tasks
    public void removeCompletedTask(Task task) {
        completedList.remove(task);
    }
    // To return completed tasks
    public ArrayList<Task> getCompletedTasks(){
        return completedList;
    }


    public void saveAllTasks(){
        FileManager.saveTasks(this.tasks,this.completedList);
    }
}
