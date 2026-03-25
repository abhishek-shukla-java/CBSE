import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
		response.setContentType("text/html");

        try {
            int roll = Integer.parseInt(request.getParameter("roll"));
            String name = request.getParameter("name");
            int eng = Integer.parseInt(request.getParameter("eng"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE students SET name=?, english=? WHERE rollno=?"
            );

            ps.setString(1, name);
            ps.setInt(2, eng);
            ps.setInt(3, roll);

            ps.executeUpdate();

            out.println("Updated Successfully<br>");
            String path = request.getContextPath();
			out.println("<a href='" + path + "/home.html'>Go Home</a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}