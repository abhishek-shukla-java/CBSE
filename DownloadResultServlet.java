import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
public class DownloadResultServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{		
		try
		{
			int roll = Integer.parseInt(req.getParameter("roll"));
			Connection con = DBConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE rollno=?");
			
			ps.setInt(1, roll);

            ResultSet rs = ps.executeQuery();
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Conetent-Disposition","attachment; filename=result.xls");
			PrintWriter out = res.getWriter();
			
			ResultSetMetaData rd = rs.getMetaData();
			
			int count = rd.getColumnCount();
			for(int i=1;i<=count;i++)
			{
				out.print(rd.getColumnName(i)+"\t");
			}
			out.println();
			
			if(rs.next())
			{
				for(int i=1;i<=count;i++)
				{
					out.print(rs.getString(i)+"\t");
				}
				out.println();
			}
			else
			{
				out.print("No record found for this roll number:"+roll);
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
