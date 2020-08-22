package org.example.reminder_my_project;

import org.example.reminder_my_project.database.CarReminderDao;
import org.example.reminder_my_project.database.EntityDao;
import org.example.reminder_my_project.project.Car;
import org.example.reminder_my_project.project.CarReminder;
import org.example.reminder_my_project.project.ReminderPeriod;
import org.example.reminder_my_project.project.ReminderType;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class CarReminderHandler {
    private Scanner scanner = new Scanner(System.in);

    public void handlerReminder(String [] words){
        CarReminderDao carReminderDao = new CarReminderDao();
        if (words[1].equalsIgnoreCase("add")) {
            addCarReminder();
        } else if  (words[1].equalsIgnoreCase("show")) {
            showCarReminder();
        } else if (words[1].equalsIgnoreCase("findby")) {
            findByCarReinder(carReminderDao);
        } else if (words[1].equalsIgnoreCase("delete")) {
            deleteCarReminder();
        }
    }

    private void deleteCarReminder() {
        EntityDao<CarReminder> entityDao = new EntityDao<>();
        System.out.println("Which reminder to delete?");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<CarReminder> carReminderDelete = entityDao
                .findById(CarReminder.class, id);

        if (carReminderDelete.isPresent()) {
            CarReminder carReminder = carReminderDelete.get();
            entityDao.delete(carReminder);
            System.out.println("Car removed. ");
        } else {
            System.out.println("Not found car");
        }

    }

    private void findByCarReinder(CarReminderDao carReminderDao) {
        System.out.println("Write car reminder phrase:");
        String reminderPhrase = scanner.nextLine();
        carReminderDao.findByReminder(reminderPhrase)
                .forEach(System.out::println);
    }

    private void showCarReminder() {
        EntityDao<CarReminder> reminderEntityDao = new EntityDao<>();
        reminderEntityDao
                .findAll(CarReminder.class)
                .forEach(System.out::println);

    }

    private void addCarReminder() {

        EntityDao<CarReminder> carReminderEntityDao = new EntityDao<>();

        System.out.println("Select the type of reminder: \n  +" +
                "1 -  LEASING,\n" +
                "2 -  INSURANCE,\n" +
                "3 -  REVIEW,\n" +
                "4 -  OIL_CHANGE,\n" +
                "5 -  FIRE_EXTINGUISHER_VALIDITY,\n" +
                "6 -  TACHO_LEGALIZATION,\n" +
                "7 -  CAR_WASH,\n" +
                "8 -  THERMOMETER_CALIBRATION");

        int reminder = Integer.parseInt(scanner.nextLine());
        ReminderType reminderType;
        switch (reminder) {
            case 1:
                reminderType = ReminderType.LEASING;
                break;
            case 2:
                reminderType = ReminderType.INSURANCE;
                break;
            case 3:
                reminderType = ReminderType.REVIEW;
                break;
            case 4:
                reminderType = ReminderType.OIL_CHANGE;
                break;
            case 5:
                reminderType = ReminderType.FIRE_EXTINGUISHER_VALIDITY;
                break;
            case 6:
                reminderType = ReminderType.TACHO_LEGALIZATION;
                break;
            case 7:
                reminderType = ReminderType.CAR_WASH;
                break;
            case 8:
                reminderType = ReminderType.THERMOMETER_CALIBRATION;
            default:
                throw new IllegalStateException("Unexpected value" + reminder);

        }

        System.out.println("Write amount reminder:");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Write date:");
        System.out.println("YEAR");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("MONTH");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("DAY");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.println("Select the period of reminder: \n +" +
                " 1-- YEAR,\n" +
                " 2 - MOUNTH,\n" +
                " 3 - WEEK,\n" +
                " 4 - DAY,\n" +
                " 5 - NONE;");

        int period = Integer.parseInt(scanner.nextLine());
        ReminderPeriod reminderPeriod;
        switch (period) {
            case 1:
                reminderPeriod = ReminderPeriod.YEAR;
                break;
            case 2:
                reminderPeriod = ReminderPeriod.MOUNTH;
                break;
            case 3:
                reminderPeriod = ReminderPeriod.WEEK;
                break;
            case 4:
                reminderPeriod = ReminderPeriod.DAY;
                break;
            case 5:
                reminderPeriod = ReminderPeriod.NONE;
                break;
            default:
                throw new IllegalStateException("Unexpected value" + period);
        }

         CarReminder carReminder = new CarReminder(reminderType, amount, LocalDate.of(year,month, day), reminderPeriod );
         carReminderEntityDao.saveOrUpdate(carReminder);
    }


}
