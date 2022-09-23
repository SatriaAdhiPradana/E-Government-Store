package project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddProjects
 */
@WebServlet("/project/AddProjects")
public class AddProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prof_ssn = request.getParameter("prof_ssn");
		System.out.println("hello p_ssn is : "+prof_ssn);
		request.getSession().setAttribute("prof_ssn", prof_ssn);
		request.getRequestDispatcher("/WEB-INF/AddProjects.jsp")
		.forward(request, response);	
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prof_ssn = request.getParameter("prof_ssn");
		String project_number = request.getParameter("project_number");
		String sponsor_name = request.getParameter("sponsor_name");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String budget = request.getParameter("budget");
		System.out.println("ssssssss"+prof_ssn); 
		ArrayList<Projects> projects = (ArrayList<Projects>) request.getSession().getAttribute("projects");
		ArrayList<Professors> professor = (ArrayList<Professors>) request.getSession().getAttribute("professors");
		
		
		
		Projects a =new Projects();
		a.setProject_number(project_number);
		a.setSponsor_name(sponsor_name);
		a.setStart_date(start_date);
		a.setEnd_date(end_date);
		a.setBudget(budget);
		for (Professors professors : professor) {
		if(prof_ssn.equals(professors.getProf_ssn())){
			a.setProfs_ssn(professors);
			projects.add(a);
		}
		
}
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
		query = "Select * from projects";
		rs = stmt1.executeQuery(query);
		int temp1=0;
		while(rs.next()){
			temp1++;
		}
		stmt2 = con.createStatement();
		for (Professors professors : professor) {
			if(prof_ssn.equals(professors.getProf_ssn())){
				a.setProfs_ssn(professors);
				stmt2.executeUpdate("insert into projects(project_number,sponsor_name,start_date,end_date,budget, profs_ssn) values ('"+project_number+"','"+sponsor_name+"','"+start_date+"','"+end_date+"','"+budget+"','"+prof_ssn+"')");
				projects.add(a);
			}
			
		}
		//System.out.println(count);
		if(temp1>0){
			System.out.println("inserted");
		}
		rs.close();
		stmt1.close();
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
	}

response.sendRedirect("ViewProjects?prof_ssn="+prof_ssn);

	}
	
}
