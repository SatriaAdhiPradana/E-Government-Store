<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<tr>
<th>STUDENT SSN</th><th>NAME</th><th>AGE</th><th>GENDER</th><th>DEGREE PROGRAM</th><th>PROJECT ID</th><th>OPERATIONS</th>
</tr>
<c:forEach items="${students}" var="s">
<tr>
<td>${s.stu_ssn}</td>
<td>${s.stu_name}</td>
<td>${s.stu_age}</td>
<td>${s.stu_gender}</td>
<td>${s.degree_program}</td>
<td> ${project_number} </td>
<td><a href="RemoveStudents?id=${s.stu_ssn}&projectId=${param['id']}">Remove Student</a></td>
</tr>
</c:forEach>
</table>

<br>
<input type="hidden" name="project_number" value="${param['project_number']}">
<br><br>
<a href="AddStudents?project_number=${param['project_number']}">Add New Student</a>
</body>
</html>
