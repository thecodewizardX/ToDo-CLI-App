// this class is a utility class, and it provide assci ui.

package com.todocli.main;

public class UIManager {
    public static void printBanner() {
        System.out.println(" /$$$$$$$$\\             /$$");
        System.out.println("\\__  $$__/             |$$");
        System.out.println("   | $$  /$$$$$$   /$$$$$$$  /$$$$$$");
        System.out.println("   | $$ /$$__  $$ /$$__  $$ /$$__  $$");
        System.out.println("   | $$| $$  \\ $$| $$  | $$| $$  \\ $$");
        System.out.println("   | $$| $$  | $$| $$  | $$| $$  | $$");
        System.out.println("   | $$|  $$$$$$/|  $$$$$$$|  $$$$$$/");
        System.out.println("   |__/ \\______/  \\_______/ \\______/");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::            T O D O   A P P          ::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("\uD83D\uDCCC Plan it. \uD83D\uDCC5 Do it. \uD83C\uDFAF Achieve it.");
    }
    // To display Main Menu
    public static void displayMainMenu(){
        System.out.println("""
          =========================================
          |             Home Menu                 |
          =========================================
          |      1) Task Management               |
          |      2) Task Status Management        |
          |      3) Exit Application              |
          =========================================""");
    }
    public static void displayTaskManagementMenu(){
        System.out.println("""
          =========================================
          |          Task Management              |
          =========================================
          |     1) Add Task                       |
          |     2) Remove Task                    |
          |     3) Edit Task                      |
          |     4) Display All Tasks              |
          |     5) Back to MAIN MENU              |
          |     6) Exit Application               |
          =========================================""");
    }

    public static void displayTaskStatusMenu(){
        System.out.println("""
          =========================================
          |          Task Status Management       |
          =========================================
          |      1) Mark As Completed             |
          |      2) Mark As Uncompleted           |
          |      3) View Completed Tasks          |
          |      4) View Pending Tasks            |
          |      5) Back to MAIN MENU             |
          |      6) Exit Application              |
          =========================================""");
    }

    public static void printRemoveMenu() {
        System.out.println("""
          =========================================
          |              Remove Task              |
          =========================================
          |    1) Remove Task from Pending tasks  |
          |    2) Remove Task from completed tasks|
          |    3) Remove All Pending Tasks        |
          |    4) Remove All completed Tasks      |
          =========================================""");
    }

    public static void printEditTaskMenu() {
        System.out.println("""
          =========================================
          |                Edit                    |
          =========================================
          |      1) Edit Name                      |
          |      2) Edit Priority                  |
          |      3) Edit Description               |
          ==========================================""");
    }

    public static void printTaskTableHeader() {
        System.out.println("+-----------------+----------------------+----------+------------+");
        System.out.println("| Task Name       | Description          | Priority | Status     |");
        System.out.println("+-----------------+----------------------+----------+------------+");
    }
    public static void printTaskTableFooter() {
        System.out.println("------------------------------------------------------------------");
    }
}
