package com.todocli.main;

import java.util.ArrayList;
import java.util.List;

public class Task implements Comparable<Task>{
    private String name;       // Task name
    private String description;
    private int priority;
    private boolean status;

    public Task(String name,String description,int priority) {
        if(name.isBlank()){
            this.name="none";
        }else{
            this.name = name;
        }
        if(name.length()>30){
            this.name=name.substring(0,30);
        }
        this.priority = priority;
        this.description = description;
        status = false;
    }
    // This constructor created for load tasks from csv file
    public Task(String name,String description,int priority,boolean status) {
        if(name.isBlank()){
            this.name="none";
        }else{
            this.name = name;
        }
        if(name.length()>30){
            this.name=name.substring(0,30);
        }
        this.priority = priority;
        this.description = description;
        this.status = status;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getPriority(){
        return priority;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return status;
    }

    // wrap the description into multiple lines for structured print
    private static List<String> wrapDescription(int length,String description){
        List<String> lines = new ArrayList<>();
        while (description.length() > length) {
            int split =description.lastIndexOf(" ", length);
            if (split == -1){
                split= length;
            }
            lines.add(description.substring(0, split));
            description = description.substring(split).trim();
        }
        lines.add(description);
        return lines;
    }

    @Override
    public String toString(){
        List<String> lines = wrapDescription(20, description);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("| %-15s | %-20s | %-8d | %-10s |", name, lines.getFirst(),
                priority, status?"completed":"pending"));
        for (int i= 1;i < lines.size();i++) {
            if(i==1){
                builder.append("\n");
            }
            if(i == lines.size()-1){
                builder.append(String.format("| %-15s | %-20s | %-8s | %-10s |", "", lines.get(i), "", ""));
            }else{
                builder.append(String.format("| %-15s | %-20s | %-8s | %-10s |\n", "", lines.get(i), "", ""));
            }

        }
        return builder.toString();
    }

    @Override
    public int compareTo(Task t) {
        return Integer.compare(this.getPriority(), t.getPriority());
    }
}
