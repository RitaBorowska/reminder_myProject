package org.example.reminderProject.database;

import org.example.reminderProject.model.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    public List<Car> findByAny(String phrase) {
        List<Car> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Car> criteriaQuery = cb.createQuery(Car.class);

            Root<Car> rootTable = criteriaQuery.from(Car.class);

            criteriaQuery
                    .select(rootTable)
                    .where(
                            cb.or(
                                    cb.like(rootTable.get("mark"), "%"+phrase+"%"),
                                    cb.like(rootTable.get("model"), "%"+phrase+"%"),
                                    cb.like(rootTable.get("regNum"), "%"+phrase+"%")
                            )
                    );

            list.addAll(session.createQuery(criteriaQuery).list());

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
