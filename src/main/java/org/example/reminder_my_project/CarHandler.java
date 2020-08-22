package org.example.reminder_my_project;

import org.example.reminder_my_project.database.CarDao;
import org.example.reminder_my_project.database.EntityDao;
import org.example.reminder_my_project.project.Car;

import java.util.Optional;
import java.util.Scanner;

public class CarHandler {
    private Scanner scanner = new Scanner(System.in);

    public void handle(String[] words) {
        CarDao carDao = new CarDao();
        if (words[1].equalsIgnoreCase("add")) {
            addCar();
        } else if (words[1].equalsIgnoreCase("show")) {
            showCars();
        } else if (words[1].equalsIgnoreCase("findby")) {
            findBy(carDao);
        } else if (words[1].equalsIgnoreCase("delete")) {
            deleteCar();
        }
    }

    private void findBy(CarDao carDao) {
        System.out.println("Enter searched phrase:");
        String phrase = scanner.nextLine();
        carDao.findByAny(phrase)
                .forEach(System.out::println);
    }

//    private void findBy(String[] words) {
//        Scanner scanner = new Scanner(System.in);
//        CarDao carDao = new CarDao();
//        System.out.println("id car \n" +
//                "mark \n" +
//                "model \n" +
//                "registration number \n");
//        String commandCar = scanner.nextLine();
//        if (commandCar.equalsIgnoreCase("id")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("mark")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("model")) {
//            carDao.findByAny(commandCar);
//        } else if (commandCar.equalsIgnoreCase("registration")) {
//            carDao.findByAny(commandCar);
//        }
//    }


    private void deleteCar() {
        EntityDao<Car> entityDao = new EntityDao<>();
        System.out.println("Which car to delete - enter the car id:");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carDelete = entityDao
                .findById(Car.class, id);

        if (carDelete.isPresent()) {
            Car car = carDelete.get();
            entityDao.delete(car);
            System.out.println("Car removed. ");
        } else {
            System.out.println("Not found car");
        }
    }

    private static void showCars() {
        EntityDao<Car> carEntityDao = new EntityDao<>();
        carEntityDao
                .findAll(Car.class)
                .forEach(System.out::println);
    }

    private  void addCar() {
        EntityDao<Car> carEntityDao = new EntityDao<>();
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