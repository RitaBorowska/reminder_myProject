package org.example.reminderProject.database;

import org.example.reminderProject.model.CarReminder;
import org.example.reminderProject.model.CarReminderType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarReminderDao {

        public List<CarReminder> findByTypeOfReminder(CarReminderType reminderType) {
        List<CarReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);
            Root<CarReminder> reminderCarRoot = criteriaQuery.from(CarReminder.class);
            criteriaQuery.select(reminderCarRoot)
                    .where(cb.equal(reminderCarRoot.get("type"), reminderType));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<CarReminder> findByDateOfReminder (LocalDate date) {
        List<CarReminder> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);
            Root<CarReminder> reminderCarRoot = criteriaQuery.from(CarReminder.class);
            criteriaQuery.select(reminderCarRoot)
                    .where(cb.equal(reminderCarRoot.get("date"), date));
            list.addAll(session.createQuery(criteriaQuery).list());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
//    public List<CarReminder> findByReminder(CarReminderType reminderPhrase) {
//        List<CarReminder> list = new ArrayList<>();
//
//        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
//        try (Session session = sessionFactory.openSession()) {
//
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//
//            CriteriaQuery<CarReminder> criteriaQuery = cb.createQuery(CarReminder.class);
//
//            Root<CarReminder> rootTable = criteriaQuery.from(CarReminder.class);
//
//            criteriaQuery
//                    .select(rootTable)
//                    .where(
//                            cb.or(
//                                    cb.equal(rootTable.get("type"),reminderPhrase),
//                                    cb.between(rootTable.get("date"), LocalDate.now(), LocalDate.now().plusMonths(1))
//                            )
//                    );
//
//            list.addAll(session.createQuery(criteriaQuery).list());
//
//        } catch (HibernateException he) {
//            he.printStackTrace();
//        }
//        return list;
//    }