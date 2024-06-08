<%--
  Created by IntelliJ IDEA.
  User: josti
  Date: 6/06/2024
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="employee" type="com.example.clase10crud.beans.Employee" scope="request" />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar un empleado</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Editar un empleado</h1>
    <form method="post" action="<%=request.getContextPath()%>/home?action=e">
        <div class="mb-3">
            <input type="hidden" class="form-control" name="employeeId" value="<%=employee.getEmployeeId()%>">
        </div>
        <div class="mb-3">
            <label>First Name</label>
            <input type="text" class="form-control" name="firstName" value="<%= employee.getFullNameEmployee().split(" ")[0] == null ? "" : employee.getFullNameEmployee().split(" ")[0]%>">
        </div>
        <div class="mb-3">
            <label>Last Name</label>
            <input type="text" class="form-control" name="lastName" value="<%=employee.getFullNameEmployee().split(" ")[1] == null ? "" : employee.getFullNameEmployee().split(" ")[1]%>">
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="text" class="form-control" name="email" value="<%=employee.getEmail()%>">
        </div>
        <div class="mb-3">
            <label>Phone Number</label>
            <input type="text" class="form-control" name="phoneNumber" value="<%=employee.getPhoneNumber()%>">
        </div>
        <div class="mb-3">
            <label>Hire Date</label>
            <input type="datetime-local" class="form-control" name="hireDate" value="<%=employee.getHireDate()%>">

        </div>
        <div class="mb-3">
            <label>Job Id</label>
            <input type="text" class="form-control" name="jobId" value="<%=employee.getJobId()%>">
        </div>
        <div class="mb-3">
            <label>Salary</label>
            <input type="text" class="form-control" name="salary" value="<%=employee.getSalary()%>">
        </div>
        <div class="mb-3">
            <label>Comision PCT</label>
            <input type="text" class="form-control" name="comisionPct" value="<%=employee.getCommissionPct()%>">
        </div>

        <a href="<%=request.getContextPath()%>/home" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
