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
<th>SSN</th><th>NAME</th><th>AGE</th><th>GENDER</th><th>RANK</th><th>SPECIALITY</th><th>OPERATIONS</th><th>VIEW PROJECTS</th>
</tr>
<c:forEach items="${professors}" var="m">
<tr>
<td>${m.prof_ssn}</td>
<td>${m.prof_name}</td>
<td>${m.prof_age}</td>
<td>${m.prof_gender}</td>
<td>${m.prof_rank}</td>
<td>${m.speciality}</td>
<td><a href="RemoveProfessors?prof_ssn=${m.prof_ssn}">Remove</a></td>
<td><a href="ViewProjects?prof_ssn=${m.prof_ssn}">View Projects</a></td>
</tr>
</c:forEach>

</table>
<br><br>
<a href="AddProfessors">Add New Professor</a>
</body>
</html>
