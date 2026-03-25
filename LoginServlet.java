import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		res.sendRedirect("login.html");
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username =? AND pass =?");
			
			ps.setString(1,user);
			ps.setString(2,pass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("home.html");
				rd.forward(req,res);
			}
			
			else{
				RequestDispatcher rd = req.getRequestDispatcher("error.html");
				rd.forward(req,res);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}