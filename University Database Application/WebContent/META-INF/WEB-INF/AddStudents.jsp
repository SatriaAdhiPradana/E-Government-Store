<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br><br>
<form action="AddStudents" method="post">
Student SSN  :      <input type="text" value="title" name="stu_ssn"><br><br>
Student Name : 	    <input type="text" value="author" name="stu_name"><br><br>
Student Age  : 	    <input type="text" value="author" name="stu_age"><br><br>
Student Gender : 	<input type="text" value="author" name="stu_gender"><br><br>
Degree Program : 	<input type="text" value="author" name="degree_program"><br><br>
<input type="hidden" value="${project_number}" name="project_number">
<input type="submit" value="AddStudents" name="AddStudents">
</form>

</body>
</html>
