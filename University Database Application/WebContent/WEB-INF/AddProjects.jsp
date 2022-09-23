<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Project</title>
</head>
<body>
<form action="AddProjects" method="post">
Project Number  :     <input type="text" value="project number" name="project_number"><br><br>
Sponsor Name : 	 <input type="text" value="sponsor name" name="sponsor_name"><br><br>
Start Date   :     <input type="text" value="start date" name="start_date"><br><br>
End Date   :     <input type="text" value="end date" name="end_date"><br><br>
Budget   :     <input type="text" value="budget" name="budget"><br><br>
<input type="hidden" value="${param['prof_ssn']}" name="prof_ssn">
<input type="submit" value="Add Project" name="AddProjects">
</form>

</body>
</html>
