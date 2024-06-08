package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {
    public ArrayList<Employee>listar(){

        ArrayList<Employee> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt(1));
                employee.setFullNameEmployee(rs.getString(2)+" "+rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setPassword(rs.getString(5));
                employee.setPhoneNumber(rs.getString(6));
                employee.setHireDate(rs.getString(7));
                employee.setJobId(rs.getString(8));
                employee.setSalary(rs.getDouble(9));
                employee.setCommissionPct(rs.getDouble(10));
                employee.setManagerId(rs.getInt(11));
                employee.setDepartmentId(rs.getInt(12));
                employee.setEnabled(rs.getInt(13));

                lista.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void crear( String fullNameEmployee, String email, String phoneNumber,
                      String hireDate, String jobId, double salary, double comissionPct){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "insert into employees (first_name, last_name, email, phone_number, hire_date, salary, commission_pct, job_id) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String[] nameParts = fullNameEmployee.split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            //pstmt.setInt(1,employeeId);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3,email);
            pstmt.setString(4,phoneNumber);
            pstmt.setString(5,hireDate);
            pstmt.setDouble(6,salary);
            pstmt.setDouble(7,comissionPct);
            pstmt.setString(8,jobId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee buscarPorId(int id){

        Employee employee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees where employee_id = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {

                    employee = new Employee();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setFullNameEmployee(rs.getString(2)+" "+rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setPassword(rs.getString(5));
                    employee.setPhoneNumber(rs.getString(6));
                    employee.setHireDate(rs.getString(7));
                    employee.setJobId(rs.getString(8));
                    employee.setSalary(rs.getDouble(9));
                    employee.setCommissionPct(rs.getDouble(10));
                    employee.setManagerId(rs.getInt(11));
                    employee.setDepartmentId(rs.getInt(12));
                    employee.setEnabled(rs.getInt(13));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public void actualizar(Employee employee){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";


        String sql = "update employees set first_name = ?" +
                ", last_name = ?" +
                ", email = ?" +
                ", phone_number = ?" +
                ", job_id = ?"+
                ", hire_date = ?" +
                ", salary = ?" +
                ", commission_pct = ?" +
                " WHERE employee_id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String[] nameParts = employee.getFullNameEmployee().split(" ", 2);
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3,employee.getEmail());
            pstmt.setString(4,employee.getPhoneNumber());
            pstmt.setString(5,employee.getJobId());
            pstmt.setString(6,employee.getHireDate());
            pstmt.setDouble(7,employee.getSalary());
            pstmt.setDouble(8,employee.getCommissionPct());

            pstmt.setInt(9,employee.getEmployeeId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrar(int employeeId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "delete from employees where employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,employeeId);
            pstmt.executeUpdate();

        }
    }
}