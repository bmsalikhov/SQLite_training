package main;

import data_source.TableGenerator;
import models.Employee;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "Steven", "King"));
        employees.add(new Employee(101, "Neena", "Kochhar"));
        employees.add(new Employee(102, "Lex", "De Haan"));
        employees.add(new Employee(103, "Alexander", "Hunold"));
        employees.add(new Employee(104, "Bruce", "Ernst"));
        employees.add(new Employee(105, "David", "Austin"));
        employees.add(new Employee(106, "Valli", "Pataballa"));
        employees.add(new Employee(107, "Diana", "Lorentz"));

        // build a unique baseName for tests
        final String baseName = System.currentTimeMillis() + ".db";
        TableGenerator tableGenerator = new TableGenerator(baseName);
        final String tableName = "employees";
        tableGenerator.createTable(tableName);
        tableGenerator.insertAll(employees, tableName);
        tableGenerator.selectAS(tableName);
        tableGenerator.selectUPPER(tableName);
        tableGenerator.selectId(tableName);
        tableGenerator.selectSUBSTRING(tableName);
        tableGenerator.selectLIMIT(tableName);
    }
}
