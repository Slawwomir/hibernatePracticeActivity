package com.practice.demo;

import com.practice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadEmployee {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try(Session session = factory.getCurrentSession()){

            // set proper id
            int id = 1;

            // start transaction
            session.beginTransaction();

            // retrieve employee object using id primary key
            Employee employee = session.get(Employee.class, id);

            // Commit operation
            session.getTransaction().commit();

            // Check retrieved object
            System.out.println(employee);

        }
        finally {
            factory.close();
        }
    }
}
