import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.oreilly.servlet.*;

public class RegisterUserServlet extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		PrintWriter out = res.getWriter();
		try{
			String path = getServletContext().getRealPath("/images");
			MultipartRequest mpr = new MultipartRequest(req,path,5*1024*1024);
			String username = mpr.getParameter("user");
			String password = mpr.getParameter("pass");
			String filename = mpr.getFilesystemName("photo");
			String fullpath = path + "/" + filename;
			
			FileInputStream fis = new FileInputStream(fullpath);
			
			Connection con = DBConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES (?,?,?)");
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setBinaryStream(3,fis,fis.available());
			ps.executeUpdate();
			
			
			out.println("Registration Successful");
			res.setHeader("Refresh","4;login.html");
			
			
		} catch(Exception e){
			e.printStackTrace();
			out.println("Error: "+e.getMessage());
		}
	}
}