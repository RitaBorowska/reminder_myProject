package org.example.reminder_my_project;

import org.example.reminder_my_project.database.EntityDao;
import org.example.reminder_my_project.project.Car;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Initial version");

        Scanner scanner = new Scanner(System.in);
        String commandCar;

        CarHandler carHandler = new CarHandler();
        CarReminderHandler carReminderHandler = new CarReminderHandler();
        do {
            System.out.println("Write command ");
            showComond();
            commandCar = scanner.nextLine();
            String[] words = commandCar.split(" ");

            if (words[0].equalsIgnoreCase("car")) {
                carHandler.handle();
            } else if (words[0].equalsIgnoreCase("reminder")) {
                carReminderHandler.handlerReminder();
            }

        }while (!commandCar.equalsIgnoreCase("quit"));

        }

    private  static void showComond(){
        System.out.println(" - [Car]");
        System.out.println(" - [Reminder]");
        System.out.println(" - [quit]");
    }

}

