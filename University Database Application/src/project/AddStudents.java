package project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/AddStudents")
public class AddStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String project_number ="";   
    
    public AddStudents() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		project_number = request.getParameter("project_number");
		System.out.println("ID is : "+project_number);
		request.getSession().setAttribute("aId", project_number);
		request.getRequestDispatcher("/WEB-INF/AddStudents.jsp")
		.forward(request, response);	
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("ssssssss :"+project_number);
		String stu_ssn = request.getParameter("stu_ssn");
		String stu_name = request.getParameter("stu_name");
		String stu_gender = request.getParameter("stu_gender");
		String stu_age = request.getParameter("stu_age");
		String degree_program = request.getParameter("degree_program");
		System.out.println("name : "+stu_name+" degree program :"+degree_program);
		ArrayList<Students> students = (ArrayList<Students>) request.getSession().getAttribute("students");
		ArrayList<Projects> projects = (ArrayList<Projects>) request.getSession().getAttribute("projects");
		
		
		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5432/CS422-SUMMER2017";
		final String USER = "postgres";
		final String PASS = "root";
		
		java.sql.Connection con = null;
		java.sql.Statement stmt1=null;
		java.sql.Statement stmt2=null;
		ResultSet rs;
		try {

			Class.forName(JDBC_DRIVER);

			con = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt1 = con.createStatement();
			String query = " ";
			query = "Select * from students";
			rs = stmt1.executeQuery(query);
			int temp1=0;
			while(rs.next()){
				temp1++;
				System.out.println("temp1  is : "+temp1);
			}
			stmt2 = con.createStatement();
			for (Projects project : projects) {
				String id2 = project.getProject_number();
				if(id2.equals(project_number)){
					System.out.println("entered");
					Students s =new Students();
					s.setProjectId(project);
					s.setStu_ssn(stu_ssn);
					s.setStu_name(stu_name);
					s.setStu_gender(stu_gender);
					s.setStu_age(stu_age);
					s.setDegree_program(degree_program);
					s.setProjectId(project);
					stmt2.executeUpdate("insert into students (stu_ssn,stu_name,stu_age,stu_gender,degree_program,projectId) values ('"+stu_ssn+"','"+stu_name+"','"+stu_age+"','"+stu_gender+"','"+degree_program+"','"+project_number+"')");
					students.add(s);
				}
				
			}
			//System.out.println(count);
			if(temp1>0){
				System.out.println("inserted");
			}
			rs.close();
			stmt1.close();
			stmt2.close();
			con.close();
			
			
		}catch (Exception e) {

			System.out.println(e);

		} finally {

			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		response.sendRedirect("ViewStudents?project_number="+project_number);
		}

		
	}

	}


