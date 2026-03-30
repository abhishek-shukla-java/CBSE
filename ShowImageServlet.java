import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowImageServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("image/jpeg");
		try
		{
			Connection con = DBConnection.getConnection();
			String user = req.getParameter("user");
			PreparedStatement ps = con.prepareStatement("Select photo from users where username=?");
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
		
			if(rs.next())
			{
				InputStream is = rs.getBinaryStream("photo");
				OutputStream os = res.getOutputStream();
				byte[] buffer = new byte[1024];
				int bytesRead;
				while((bytesRead=is.read(buffer))!=-1)
				{
					os.write(buffer,0,bytesRead);
				}
				os.flush();
				os.close();
			
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}