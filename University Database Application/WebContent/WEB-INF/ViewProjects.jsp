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
<th>PROJECT NUMBER</th><th>SPONSOR NAME</th><th>STARTING DATE</th><th>END DATE</th><th>BUDGET</th><th>VIEW STUDENTS</th><th>OPERATIONS</th>
</tr>
<c:forEach items="${projects}" var="a">
<tr>
<td>${a.project_number}</td>
<td>${a.sponsor_name}</td>
<td>${a.start_date}</td>
<td>${a.end_date}</td>
<td>${a.budget}</td>
<td><a href="ViewStudents?project_number=${a.project_number}">View Students</a></td>
<td><a href="RemoveProject?pId=${a.project_number}&prof_ssn=${prof_ssn}">Remove Project</a></td>
</tr>
</c:forEach>
</table>
<br>
<input type="hidden" name="prof_ssn" value="${param['prof_ssn']}">
<br><br>
<a href="AddProjects?prof_ssn=${param['prof_ssn']}">Add New Project</a>
</body>
</html>
