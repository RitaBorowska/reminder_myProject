package org.example.reminder_my_project;

import org.example.reminder_my_project.database.EntityDao;
import org.example.reminder_my_project.database.HibernateUtil;
import org.example.reminder_my_project.project.Car;

public class Main {

    public static void main(String[] args) {
        System.out.println("Initial version");
        HibernateUtil.getOurSessionFactory();
        System.out.println("Tested hibernate");

        EntityDao<Car> carEntityDao = new EntityDao<>();
        carEntityDao.saveOrUpdate(new Car("GDA 4566", "DAF", "40P"));
        carEntityDao.saveOrUpdate(new Car("GDA 7654", "MAN", "657UF"));
    }
}
