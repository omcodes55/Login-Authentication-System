

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//create table user(userid varchar(30), password varchar(20));
//insert into user values("prince123", "Prince@2004");,



/**
 * Servlet implementation class demo1
 */
@WebServlet("/demo1")
public class demo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public demo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		// JDBC CONNECTION 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb11", "root","Vadher@2004");
			Statement st = conn.createStatement();
			String query = "select * from user where userid = '"+userid+"' and password = '"+password+"' ";
			ResultSet rs = st.executeQuery(query);
			 if(rs.next()) {
				 out.print("<h1>"+userid+" : Welcome to our Project </h1> <br>");
				 out.print("<h1>Login Successfully </h1> <br>");
			 }
			 else {
				// The userId and password is not available in Database(omdb11)
				 out.print("<h1>"+userid+"(userid) or password invalid, please enter correct Id and Password  </h1> <br>");
				 out.print("<h1>Login Failed, Try Again</h1> <br>");
			 }
			 rs.close();
			 st.close();
			 conn.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.print("<h2>Login Falied! Because of server</h2> <br>");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("<h2>Login Failed! Because of server expection</h2> <br>");
			e.printStackTrace();
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
