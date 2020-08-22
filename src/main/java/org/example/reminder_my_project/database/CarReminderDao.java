package org.example.reminder_my_project.database;

import org.example.reminder_my_project.project.Car;
import org.example.reminder_my_project.project.CarReminder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarReminderDao {

    public List<CarReminder> findByReminder(String reminderPhrase) {

        List<CarReminder> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);

            Root<CarReminder> rootTable = criteriaQuery.from(CarReminder.class);

            criteriaQuery
                    .select(rootTable)
                    .where(
                            cb.or(
                                    cb.like(rootTable.get("type"), reminderPhrase),
                                    cb.like(rootTable.get("date"), reminderPhrase)
                            )
                    );

            list.addAll(session.createQuery(criteriaQuery).list());

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
