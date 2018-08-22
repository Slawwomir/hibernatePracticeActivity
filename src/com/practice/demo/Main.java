package com.practice.demo;

import com.practice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Employee.class)
            .buildSessionFactory();

    public static void main(String[] args) {

    }

    public Employee readEmployee(int id){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Employee employee = null;

        try(Session session = factory.getCurrentSession()){
            // start transaction
            session.beginTransaction();

            // retrieve employee object using id primary key
            employee = session.get(Employee.class, id);

            // Commit operation
            session.getTransaction().commit();

            // Check retrieved object
            System.out.println("Retrieved object: " + employee);

        }
        finally {
            factory.close();
        }

        return employee;
    }

    public void saveEmployee(Employee employee){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try(Session session = factory.getCurrentSession()) {
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

    public void removeEmployee(int id){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try(Session session = factory.getCurrentSession()) {

            // begin transaction
            session.beginTransaction();

            // retrieve object to remove
            Employee employee = session.get(Employee.class, id);

            // commit transaction
            session.getTransaction().commit();

            // remove
            session.remove(employee);

            // commit transaction
            session.getTransaction().commit();

            // Done!
            System.out.println("Employee has been removed!");
        }
        finally {
            factory.close();
        }
    }

    public void removeEmployee(Employee employee){
        removeEmployee(employee.getId());
    }
}
