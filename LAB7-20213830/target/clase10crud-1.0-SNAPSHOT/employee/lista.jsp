<%@ page import="com.example.clase10crud.beans.Employee" %><%--
  Created by IntelliJ IDEA.
  User: josti
  Date: 6/06/2024
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="java.util.ArrayList<com.example.clase10crud.beans.Employee>" />
<html>
<head>
    <title>Empleados</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <h1 class="float-start link-dark">Lista de Empleados</h1>
        <div class="float-end">
            <a class="btn btn-secondary mt-1" href="<%=request.getContextPath()%>/JobServlet">Lista de Trabajos</a>
            <a class="btn btn-primary mt-1" href="<%=request.getContextPath() %>/home?action=new">Crear Empleado</a>
        </div>
    </div>
    <hr/>

    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Hire Date</th>
            <th>Salary</th>
            <th>Job Id</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Employee employee : lista) { %>
        <tr>
            <td><%=employee.getEmployeeId()%>
            </td>
            <td><%=employee.getFullNameEmployee()%>
            </td>
            <td><%=employee.getEmail()%>
            </td>
            <td><%=employee.getPhoneNumber()%>
            </td>
            <td><%=employee.getHireDate()%>
            </td>
            <td><%=employee.getSalary()%>
            </td>
            <td><%=employee.getJobId()%>
            </td>

            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/home?action=edit&id=<%= employee.getEmployeeId() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/home?action=del&id=<%= employee.getEmployeeId() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>