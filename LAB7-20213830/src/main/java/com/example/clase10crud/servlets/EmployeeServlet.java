package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Job;
import com.example.clase10crud.daos.EmployeeDao;
import com.example.clase10crud.daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//  http://localhost:8080/EmployeeServlet
@WebServlet(name = "EmployeeServlet", value = "/home")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        EmployeeDao employeeDao = new EmployeeDao();
        switch (action){
            case "lista":
                //saca del modelo

                ArrayList<Employee> list = employeeDao.listar();

                //mandar la lista a la vista -> employee/lista.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("employee/lista.jsp");
                rd.forward(request,response);

                break;
            case "new":

                request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                //Job job = jobDao.buscarPorId(id);
                Employee employee = employeeDao.buscarPorId(id);

                if(employee != null){
                    request.setAttribute("employee",employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/home");
                }
                break;
            case "del":
                //String idd = request.getParameter("id");
                int idd = Integer.parseInt(request.getParameter("id"));
                //Job jobb = jobDao.buscarPorId(idd);
                Employee emp = employeeDao.buscarPorId(idd);

                if(emp != null){
                    try {
                        //jobDao.borrar(idd);
                        employeeDao.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        EmployeeDao employeeDao = new EmployeeDao();
        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear"://voy a crear un nuevo empleado

                //int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phoneNumber");
                String hireDate = request.getParameter("hireDate");
                String jobId = request.getParameter("jobId");
                String salary = request.getParameter("salary");

                String comissionPct = request.getParameter("comissionPct");

                String fullNameEmployee = firstName + " " + lastName;
                //Employee employee = employeeDao.buscarPorId(employeeId);
                //if(employee == null){
                employeeDao.crear( fullNameEmployee, email, phoneNumber,
                        hireDate, jobId, Double.parseDouble(salary), Double.parseDouble(comissionPct));
                        response.sendRedirect(request.getContextPath() + "/home");
                //}else{
                    //request.getRequestDispatcher("employee/form_new.jsp").forward(request,response);
                //}

                break;
            case "e": // voy a actualizar
                // Obtengo los parámetros de la solicitud
                int employeeId2 = Integer.parseInt(request.getParameter("employeeId"));
                String firstName2 = request.getParameter("firstName");
                String lastName2 = request.getParameter("lastName");
                String email2 = request.getParameter("email");
                String phoneNumber2 = request.getParameter("phoneNumber");
                String hireDate2 = request.getParameter("hireDate");
                String jobId2 = request.getParameter("jobId");
                String salary2 = request.getParameter("salary");
                String comissionPct2 = request.getParameter("comissionPct");
                Double salaryD = null;
                if (salary2 != null && !salary2.isEmpty()) {
                    salaryD = Double.parseDouble(salary2.trim());
                }

                Double commissionPctD = null;
                if (comissionPct2 != null && !comissionPct2.isEmpty()) {
                    commissionPctD = Double.parseDouble(comissionPct2.trim());
                }

                boolean isAllValid2 = true;
                // Realiza las validaciones necesarias
                if(jobId2.length() > 10){
                    isAllValid2 = false;
                }
                if (email2.length() > 25) {
                    isAllValid2 = false;
                }
                if (firstName2.length() > 20) {
                    isAllValid2 = false;
                }
                if (lastName2.length() > 25) {
                    isAllValid2 = false;
                }

                if (isAllValid2) {
                    // Concateno firstName y lastName para obtener fullNameEmployee
                    String fullNameEmployee2 = firstName2 + " " + lastName2;
                    // Creo un objeto Employee
                    Employee employee = new Employee();
                    employee.setEmployeeId(employeeId2);
                    employee.setFullNameEmployee(fullNameEmployee2);
                    employee.setEmail(email2);
                    employee.setPhoneNumber(phoneNumber2);
                    employee.setHireDate(hireDate2);
                    employee.setJobId(jobId2);

                    if (salaryD != null) {
                        employee.setSalary(salaryD);
                    }
                    if (commissionPctD != null) {
                        employee.setCommissionPct(commissionPctD);
                    }
                    // Llamo al método actualizar del DAO
                    employeeDao.actualizar(employee);
                    // Redirijo a la lista de empleados
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    // Si los datos no son válidos, reenvío al formulario de edición
                    request.setAttribute("employee", employeeDao.buscarPorId(Integer.parseInt(request.getParameter("id"))));
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request, response);
                }
                break;
        }
    }

}