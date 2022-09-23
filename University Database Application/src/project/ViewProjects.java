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
 * Servlet implementation class ViewProjects
 */
@WebServlet("/project/ViewProjects")
public class ViewProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	ArrayList<Projects> projects;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		projects= new ArrayList<Projects>();
		String prof_ssn = request.getParameter("prof_ssn");
		System.out.println("ssn is :"+prof_ssn);
		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5432/CS422-SUMMER2017";
		
		final String USER = "postgres";
		final String PASS = "root";

		java.sql.Connection con = null;
		java.sql.Statement stmt = null;
		java.sql.Statement stmt1=null;
		ResultSet rs;
		ResultSet r;
		
		try {

			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String query = "";
			query = "Select * from projects";	
			rs = stmt.executeQuery(query);
			//
			String query1="";
			System.out.println("fffffffffffasdad");
			//
			
			while (rs.next()) {
				Projects a=new Projects();
				String pSsn =(String) rs.getObject("profs_ssn");
				if(prof_ssn.equals(pSsn)){
					a.setProject_number(rs.getString("project_number"));
					a.setSponsor_name(rs.getString("sponsor_name"));
					a.setStart_date(rs.getString("start_date"));
					a.setEnd_date(rs.getString("end_date"));
					a.setBudget(rs.getString("budget"));
					request.getSession().setAttribute("prof_ssn", pSsn);
					projects.add(a);
				}
			}
			
			//System.out.println("size is"+musician.size());
			
			stmt.close();
			stmt1.close();
			con.close();
			request.getSession().setAttribute("projects", projects);

			RequestDispatcher patch=request.getRequestDispatcher("/WEB-INF/ViewProjects.jsp");
			patch.forward(request, response);
			
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
