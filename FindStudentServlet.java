import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FindStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
		response.setContentType("text/html");

        try {
            int roll = Integer.parseInt(request.getParameter("roll"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM students WHERE rollno=?"
            );

            ps.setInt(1, roll);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Percentage: " + rs.getDouble("percentage") + "<br>");
                out.println("Division: " + rs.getString("division"));
            } else {
                out.println("Student Not Found");
            }

            String path = request.getContextPath();
			out.println("<a href='" + path + "/home.html'>Go Home</a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}