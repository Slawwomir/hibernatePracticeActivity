package com.practice.demo;

import com.practice.entity.Employee;

import java.util.Arrays;

public class DBMTest {

    public static void main(String[] args) throws Exception {

        DatabaseManager databaseManager = new DatabaseManager();

        Thread.sleep(500);

        Employee[] employees = {new Employee("Darek", "Pietruszewski", "HurryUP"),
            new Employee("Kasia", "Morska", "Szyszki"),
            new Employee("Adam", "Krzym", "Medico")};

        for (Employee employee: employees) {
            databaseManager.saveEmployee(employee);
            System.out.println("Employee has been saved: " + employee);
        }

        System.out.println("\n\nAfter saving\n\n");

        System.out.println("Reading from database:");

        for(int i = 1; i < employees.length + 1; i++){
            Employee employee = databaseManager.readEmployee(i);
            System.out.println("Employee with id " + i + ": " + employee);
        }

        System.out.println("Before deleting");

        Employee[] empls = databaseManager.getAll();

        System.out.println(Arrays.toString(empls));

        for(Employee employee : empls){
            databaseManager.removeEmployee(employee);
        }

        System.out.println("After deleting");

        empls = databaseManager.getAll();

        System.out.println(Arrays.toString(empls));
    }
}
