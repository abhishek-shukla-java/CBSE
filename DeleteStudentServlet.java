import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
		response.setContentType("text/html");

        try {
            int roll = Integer.parseInt(request.getParameter("roll"));

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM students WHERE rollno=?"
            );

            ps.setInt(1, roll);

            ps.executeUpdate();

            out.println("Deleted Successfully<br>");
			out.println("You will be redirected in 4 seconds");
            response.setHeader("Refresh","4;home.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}