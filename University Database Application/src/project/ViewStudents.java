package project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewStudents
 */

@WebServlet("/project/ViewStudents")
public class ViewStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Projects p;
   
    public ViewStudents() {
        super();
    }

    ArrayList<Students> students;
    ArrayList<Projects> projects;
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		students= new ArrayList<Students>();
		String project_number = request.getParameter("project_number");
		System.out.println("id is :"+project_number);
		
		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5432/CS422-SUMMER2017";
		final String USER = "postgres";
		final String PASS = "root";
		projects=(ArrayList<Projects>) request.getSession().getAttribute("projects");
		
		for ( Projects p: projects ) {
			System.out.println(p + " : ");
		}
		java.sql.Connection con = null;
		java.sql.Statement stmt = null;
		ResultSet rs;
		
		try {

			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			String query = "";
			query = "Select * from students";	
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Students s=new Students();
				String pid =(String) rs.getObject("projectId");
				if(project_number.equals(pid)){
					s.setStu_ssn(rs.getString("stu_ssn"));
					s.setStu_name(rs.getString("stu_name"));
					s.setStu_age(rs.getString("stu_age"));
					s.setStu_gender(rs.getString("stu_gender"));
					s.setDegree_program(rs.getString("degree_program"));
					request.getSession().setAttribute("project_number", pid);
					students.add(s);
				}
			}
			stmt.close();
			con.close();
			request.getSession().setAttribute("students", students);

			request.getRequestDispatcher("/WEB-INF/ViewStudents.jsp")
			.forward(request, response);
			
			
		}catch (Exception e) {

			System.out.println(e);

		} finally {

			if (stmt != null ) {
				try {
					stmt.close();
					
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

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request, response);
	}

}
