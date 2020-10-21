package org.example.reminderProject;

import org.example.reminderProject.handlers.CarHandler;
import org.example.reminderProject.handlers.CarReminderHandler;
import org.example.reminderProject.handlers.EmployeeHandler;
import org.example.reminderProject.handlers.EmployeeReminderHandler;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeHandler employeeHandler = new EmployeeHandler();
        EmployeeReminderHandler employeeReminderHandler = new EmployeeReminderHandler();
        CarHandler carHandler = new CarHandler();
        CarReminderHandler carReminderHandler = new CarReminderHandler();

        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Write command ");
            printFirstOption();
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("employee")) {
                employeeHandler.handleEmployee();
            } else if (command.equalsIgnoreCase("employeereminder")) {
                employeeReminderHandler.handleRemidnder();
            } else if (command.equalsIgnoreCase("car")) {
                carHandler.handle();
            } else if (command.equalsIgnoreCase("carreminder")) {
                carReminderHandler.handlerReminder();
            }

        }while (!command.equalsIgnoreCase("quit"));

        }

    private  static void printFirstOption(){
        System.out.println("Employee");
        System.out.println("EmployeeReminder");
        System.out.println("Car");
        System.out.println("CarRemonder");
        System.out.println("Quit");
    }

}

