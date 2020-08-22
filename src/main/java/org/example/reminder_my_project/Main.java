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

            if(words[0].equalsIgnoreCase("car")){
                carHandler.handle(words);
            } else if
                (words[0].equalsIgnoreCase("reminder"));
                    carReminderHandler.handlerReminder(words);

            }

         while (!commandCar.equalsIgnoreCase("quit"));

        }



    private  static void showComond(){
        System.out.println("car show");
        System.out.println("car add");
        System.out.println("car findby {mark} {model} {regNum}");
        System.out.println("car delete");
        System.out.println("reminder show");
        System.out.println("reminder add");
        System.out.println("remnder findby {type} {date}");
        System.out.println("delete");
    }

}

