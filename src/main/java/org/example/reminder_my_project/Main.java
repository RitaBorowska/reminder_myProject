package org.example.reminder_my_project;

import org.example.reminder_my_project.database.EntityDao;
import org.example.reminder_my_project.database.HibernateUtil;
import org.example.reminder_my_project.project.Car;
import org.hibernate.Session;
import org.hibernate.transform.CacheableResultTransformer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Initial version");

        Scanner scanner = new Scanner(System.in);
        String commandCar;

        do {
            System.out.println("Write command ");
            commandCar = scanner.nextLine();
            System.out.println("Car add \n " +
                    "Car show \n " +
                    "Car find by \n " +
                    "Car delete \n" +
                    "Quit");

            commandCar = scanner.nextLine();
            String[] words = commandCar.split(" ");

            if(words[0].equalsIgnoreCase("car")
                    && words[1].equalsIgnoreCase("add")) {
                addCar(words);
            } else if (words[0].equalsIgnoreCase("car")
                    && words[1].equalsIgnoreCase("show")) {
                showCar(words);
            } else if (words[0].equalsIgnoreCase("car")
                    && words[1].equalsIgnoreCase("find")
                    && words[2].equalsIgnoreCase("by")){

                System.out.println("id car \n" +
                        "mark \n" +
                        "model \n" +
                        "registration number \n");
                commandCar = scanner.nextLine();
                String[] words2 = commandCar.split(" ");
                if (words2[0].equalsIgnoreCase("id")) {
                    findById(words2);
                } else if (words2[0].equalsIgnoreCase("mark")) {
                    findByMark(words2);
                } else if (words2[0].equalsIgnoreCase("model")) {
                    findByModel(words2);
                } else if (words2[0].equalsIgnoreCase("registration")
                        && words2[1].equalsIgnoreCase("number")) {
                    findByRegNum(words2);
                }


            } else if (words[0].equalsIgnoreCase("car")
                    && words[1].equalsIgnoreCase("delete")) {
                deleteCar(words);
            }
        } while (!commandCar.equalsIgnoreCase("quit"));

        }

    private static void findByRegNum(String[] words2) {

        EntityDao<Car> entityDao = new EntityDao<>();

        System.out.println("Write registeration number: ");

        Scanner scanner = new Scanner(System.in);
        String regNum = scanner.nextLine();

        List<Car> resultCarList = entityDao
                .findByRegNum(Car.class, regNum);

        if (resultCarList
                .stream()
                .findFirst()
                .isPresent()) {
            System.out.println("Found registration number");
            resultCarList.forEach(System.out::println);
        } else
            System.out.println("Registration number not found");
    }

    private static void findByModel(String[] words2) {

        EntityDao<Car> entityDao = new EntityDao<>();

        System.out.println("Write car model: ");

        Scanner scanner = new Scanner(System.in);
        String model = scanner.nextLine();

        List<Car> resultCarList = entityDao
                .findByModel(Car.class, model);

        if (resultCarList
                .stream()
                .findFirst()
                .isPresent()) {
            System.out.println("Found car model");
            resultCarList.forEach(System.out::println);
        } else
            System.out.println("Car model not found");
    }

    private static void findByMark(String[] words2) {

        EntityDao<Car> entityDao = new EntityDao<>();

        System.out.println("Write mark: ");

        Scanner scanner = new Scanner(System.in);
        String mark = scanner.nextLine();

        List<Car> resultCarList = entityDao
                .findByMark(Car.class, mark);

        if (resultCarList
                .stream()
                .findFirst()
                .isPresent()) {
            System.out.println("Found car mark");
            resultCarList.forEach(System.out::println);
        } else
            System.out.println("Car mark not found");
    }

    private static void findById(String[] words2) {

        EntityDao<Car> entityDao = new EntityDao<>();

        System.out.println("Write car ID number: ");

        Scanner scanner = new Scanner(System.in);
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> resultCarOptional = entityDao
                .findById(Car.class, id);

        if (resultCarOptional.isPresent()) {
            System.out.println("Found car ID number: " + resultCarOptional.get());
        } else
            System.out.println("Car ID number not found");

    }

    private static void deleteCar(String[] words) {

        EntityDao<Car> entityDao = new EntityDao<>();

        System.out.println("Which car to delete - enter the car id:");

        Scanner scanner = new Scanner(System.in);
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carDelete = entityDao
                .findById(Car.class, id);

        if (carDelete.isPresent()) {
            Car car = carDelete.get();
            System.out.println("Delete car: ");
            entityDao.delete(car);
        } else
            System.out.println("Not found car");

    }

    private static void showCar(String[] words) {

        EntityDao<Car> carEntityDao = new EntityDao<>();
        carEntityDao
                .findAll(Car.class)
                .forEach(System.out::println);
    }

    private static void addCar(String[] words) {

        EntityDao<Car> carEntityDao = new EntityDao<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter car mark");
        String mark = scanner.nextLine();

        System.out.println("Enter car model");
        String model = scanner.nextLine();

        System.out.println("Provide the car registration number");
        String regNum = scanner.nextLine();

        Car car = new Car(mark, model, regNum);
        carEntityDao.saveOrUpdate(car);
    }


}

