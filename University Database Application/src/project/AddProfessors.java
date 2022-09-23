package project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/project/AddProfessors")
public class AddProfessors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddProfessors() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/AddProfessors.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String prof_ssn = request.getParameter("prof_ssn");
		String prof_name = request.getParameter("prof_name");
		String prof_age = request.getParameter("prof_age");
		String prof_gender = request.getParameter("prof_gender");
		String prof_rank = request.getParameter("prof_rank");
		String speciality = request.getParameter("speciality");
		final int countId;
		
		Random rand = new Random();
		countId = rand.nextInt(50) + 1;
		
		@SuppressWarnings("unchecked")
		ArrayList<Professors> professors = (ArrayList<Professors>) request.getSession().getAttribute("professors");
		
		Professors p = new Professors();
		
		p.setProf_ssn(prof_ssn);
		p.setProf_name(prof_name);
		p.setProf_age(prof_age);
		p.setProf_gender(prof_gender);
		p.setProf_rank(prof_rank);
		p.setSpeciality(speciality);	
		
		professors.add(p);
		
		final String JDBC_DRIVER = "org.postgresql.Driver";
		final String DB_URL = "jdbc:postgresql://localhost:5432/CS422-SUMMER2017";
		
		final String USER = "postgres";
		final String PASS = "root";


		java.sql.Connection con = null;
		java.sql.Statement stmt1=null;
		java.sql.Statement stmt2 = null;
		java.sql.Statement stmt3 = null;
		
		ResultSet rs;
		ResultSet r;
		
		try {

			Class.forName(JDBC_DRIVER);

			con = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			String query = " ";
			query = "Select * from professors";
			
			rs = stmt1.executeQuery(query);
			int temp=0;
			while(rs.next()){
				temp++;
			}
			stmt3 = con.createStatement();
			stmt2 = con.createStatement();
			stmt3.executeUpdate("insert into professors (prof_ssn,prof_name,prof_age,prof_gender,prof_rank,speciality) values ('"+prof_ssn+"','"+prof_name+"','"+prof_age+"','"+prof_gender+"','"+prof_rank+"','"+speciality+"')");
			//System.out.println(count);
			if(temp>0){
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

		}
		
		
		response.sendRedirect("ViewProfessors");
		
	//	doGet(request, response);
	}

}
