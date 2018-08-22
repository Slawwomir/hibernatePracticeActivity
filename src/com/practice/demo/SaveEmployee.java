package com.practice.demo;

import com.practice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SaveEmployee {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try(Session session = factory.getCurrentSession()) {

            // create employee object
            Employee employee = new Employee("Jakub", "Kozlowski", "dziuraDupa");
            System.out.println(employee);

            // begin transaction
            session.beginTransaction();

            // save object
            session.save(employee);

            // commit transaction
            session.getTransaction().commit();
            
            // Done!
            System.out.println("Employee has been saved!");
        }
        finally {
            factory.close();
        }
    }
}
