import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AllStudentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
		response.setContentType("text/html");

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            out.println("<table border=1>");
            out.println("<tr><th>Roll</th><th>Name</th><th>english</th><th>hindi</th><th>social</th><th>science</th><th>maths</th><th>computer</th><th>Percentage</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("rollno") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getString("english") + "</td>");
				out.println("<td>" + rs.getString("hindi") + "</td>");
				out.println("<td>" + rs.getString("social") + "</td>");
				out.println("<td>" + rs.getString("science") + "</td>");
				out.println("<td>" + rs.getString("maths") + "</td>");
				out.println("<td>" + rs.getString("computer") + "</td>");
                out.println("<td>" + rs.getDouble("percentage") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            String path = request.getContextPath();
			out.println("<a href='" + path + "/home.html'>Go Home</a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}