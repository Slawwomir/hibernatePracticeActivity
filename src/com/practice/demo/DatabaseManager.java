package com.practice.demo;

import com.practice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DatabaseManager {

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

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();

            // begin transaction
            session.beginTransaction();

            // retrieve object to remove
            Employee employee = session.get(Employee.class, id);

            // commit transaction
            session.getTransaction().commit();

            // get new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // remove
            session.remove(employee);

            // commit transaction
            session.getTransaction().commit();

            // Done!
            System.out.println("Employee has been removed!");
        }
    }

    public void removeEmployee(Employee employee){
        removeEmployee(employee.getId());
    }

    public Employee[] getAll() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        List<Employee> employees;

        try(Session session = factory.getCurrentSession()) {

            // begin transaction
            session.beginTransaction();

            // retrieve objects
        employees = session.createQuery("from Employee").getResultList();

            // commit transaction
            session.getTransaction().commit();

        }
        finally {
            factory.close();
        }

        return employees.toArray(new Employee[0]);
    }
}
