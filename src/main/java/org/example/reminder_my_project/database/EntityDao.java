package org.example.reminder_my_project.database;

import org.example.reminder_my_project.project.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityDao<T>{

    public void saveOrUpdate(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Optional<T> findById(Class<T> classType, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // istnieje prawdopodobieństwo, że rekord nie zostanie odnaleziony
            return Optional.ofNullable(session.get(classType, id));
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return Optional.empty();
    }

    public void delete(T entity) {
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // instrukcja która służy do usuwania z bazy danych
            session.delete(entity);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<T> findAll(Class<T> classType) {
        List<T> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<T> rootTable = criteriaQuery.from(classType);

            // wykonanie zapytania
            criteriaQuery.select(rootTable);

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Car> findByMark(Class<Car> classType, String mark) {
        List<Car> listByMark = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = cb.createQuery(classType);
            Root<Car> carRoot = criteriaQuery.from(classType);
            criteriaQuery.select(carRoot)
                    .where(cb.equal(carRoot.get("mark"), mark));
            listByMark.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException he) {
            he.printStackTrace();
        }
        return listByMark;
    }

    public List<Car> findByModel(Class<Car> classType, String model) {
        List<Car> listByModel = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = cb.createQuery(classType);
            Root<Car> carRoot = criteriaQuery.from(classType);
            criteriaQuery.select(carRoot)
                    .where(cb.equal(carRoot.get("model"), model));
            listByModel.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException he) {
            he.printStackTrace();
        }
        return listByModel;
    }

    public List<Car> findByRegNum(Class<Car> classType, String regNum) {
        List<Car> listByRegNum = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = cb.createQuery(classType);
            Root<Car> carRoot = criteriaQuery.from(classType);
            criteriaQuery.select(carRoot)
                    .where(cb.equal(carRoot.get("regNum"), regNum));
            listByRegNum.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException he) {
            he.printStackTrace();
        }

        return listByRegNum;
    }
}
