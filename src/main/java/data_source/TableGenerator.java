package data_source;

import models.Employee;

import java.sql.*;
import java.util.List;

public class TableGenerator {
    final String dbUrl;

    public TableGenerator(String dbName) {
        dbUrl = "jdbc:sqlite:" + dbName;
    }

    public void createTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "CREATE table if not exists " + tableName +
                    " (employee_id integer primary key," +
                    " first_name varchar(20) not null," +
                    " last_name varchar(20) not null)";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAll(List<Employee> employeeList, String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            for (Employee employee : employeeList) {
                String query = "INSERT INTO " + tableName +
                        " VALUES (" + employee.getEmployeeId() + ", '" + employee.getFirstName() + "', '" + employee.getLastName() + "')";
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select all from table (first_name, last_name) AS "Имя" и "Фамилия"
    public void selectAS(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "SELECT first_name AS Имя, last_name AS Фамилия" +
                    " FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            printSelectResult(query, resultSet, metaData, 1, 2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select all from table (first_name) in UpperCase
    public void selectUPPER(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "SELECT UPPER(first_name)" +
                    " FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            printSelectResult(query, resultSet, metaData, 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select all from table (id)
    public void selectId(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "SELECT employee_id" +
                    " FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            printSelectResult(query, resultSet, metaData, 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select all from table (first_name) first three symbs
    public void selectSUBSTRING(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "SELECT SUBSTRING(first_name, 1, 3)" +
                    " FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            printSelectResult(query, resultSet, metaData, 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select first five rows from table (first_name) first three symbs
    public void selectLIMIT(String tableName) {
        try (Connection connection = DriverManager.getConnection(dbUrl);
             Statement statement = connection.createStatement()) {
            String query = "SELECT *" +
                    " FROM " + tableName +
                    " LIMIT 5";
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            printSelectResult(query, resultSet, metaData, 1, 2, 3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printSelectResult(String query, ResultSet resultSet, ResultSetMetaData resultSetMetaData, int ... columns) throws SQLException {
        int columnSize = 20;
        StringBuilder builder = new StringBuilder();
        builder.append(query).append("\n\n");
        for (int i = 0; i < columns.length; i++) {
            builder.append(resultSetMetaData.getColumnLabel(columns[i]));
            int diff = columnSize - resultSetMetaData.getColumnLabel(columns[i]).length();
            for (int j = 0; j < diff; j++) builder.append(" ");
        }
        builder.append("\n\n");
        while (resultSet.next()) {
            for (int i = 0; i < columns.length; i++) {
                builder.append(resultSet.getString(columns[i]));
                int diff = columnSize - resultSet.getString(columns[i]).length();
                for (int j = 0; j < diff; j++) builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("\n\n");
        System.out.println(builder);
    }
}
