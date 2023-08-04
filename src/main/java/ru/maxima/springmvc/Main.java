package ru.maxima.springmvc;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maxima.springmvc.models.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
//            Person person = session.get(Person.class, 3);
            System.out.println("We have the person here");
            String name = "Ara";
            Query query = session.createQuery("FROM Person where name =:par");
            query.setParameter("par",  name +"%");
            List<Person> people = query.getResultList();
            for (Person l : people) {
                System.out.println(l.getName());
            }

//            System.out.println(person.getName());
            session.getTransaction().commit();

            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sessionFactory.close();
        }
    }

    }

