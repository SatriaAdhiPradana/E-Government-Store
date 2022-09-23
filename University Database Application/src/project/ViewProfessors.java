package project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/project/ViewProfessors")
public class ViewProfessors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<Professors> professors;
  
    public ViewProfessors() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	@SuppressWarnings("unused")
		ArrayList<Professors> professors = new ArrayList<Professors>();
	}
    
   
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

			 professors = new ArrayList<Professors>();
			 
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String query = "";
			query = "Select * from professors";	
			rs = stmt.executeQuery(query);
			//
			String query1="";
			System.out.println("fffffffffffasdad");
			//
			while (rs.next()) {
				query1 = "Select * from professors";	
				r = stmt1.executeQuery(query1);
				Professors p = new Professors();
				p.setProf_ssn(rs.getString("prof_ssn"));
				p.setProf_name(rs.getString("prof_name"));
				p.setProf_age(rs.getString("prof_age"));
				p.setProf_gender(rs.getString("prof_gender"));
				p.setProf_rank(rs.getString("prof_rank"));
				p.setSpeciality(rs.getString("speciality"));
							
				professors.add(p);
	}

	
			
			System.out.println("size is"+professors.size());
			
			stmt.close();
			stmt1.close();
			con.close();
			
			request.getSession().setAttribute("professors", professors);
			RequestDispatcher patch=request.getRequestDispatcher("/WEB-INF/ViewProfessors.jsp");
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
	}

}
