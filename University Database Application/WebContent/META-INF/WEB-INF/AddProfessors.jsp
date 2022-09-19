<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AddProfessors" method="post">
professor SSN :      <input type="text" value="ssn" name="prof_ssn"><br><br>
 Name : 	   <input type="text" value="name" name="prof_name"><br><br>
 age  :  <input type="text" value="age" name="prof_age"><br><br>
 gender : <input type="text" value="gender" name="prof_gender"><br><br>
rank :  <input type="text" value="rank" name="prof_rank"><br><br>
Speciality : <input type="text" value="speciality" name="speciality"><br><br>

<input type="submit" value="AddProfessors" name="AddProfessors">
</form>
</body>
</html>
