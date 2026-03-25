import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
		response.setContentType("text/html");

        try {
            int roll = Integer.parseInt(request.getParameter("roll"));
            String name = request.getParameter("name");
            int eng = Integer.parseInt(request.getParameter("eng"));
            int hin = Integer.parseInt(request.getParameter("hin"));
            int soc = Integer.parseInt(request.getParameter("soc"));
            int math = Integer.parseInt(request.getParameter("math"));
            int sci = Integer.parseInt(request.getParameter("sci"));
            int comp = Integer.parseInt(request.getParameter("comp"));

            int total = eng + hin + soc + math + sci + comp;
            double per = total / 6.0;

            String div;
            if (per >= 60) div = "First";
            else if (per >= 50) div = "Second";
            else if (per >= 40) div = "Third";
            else div = "Fail";

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setInt(3, eng);
            ps.setInt(4, hin);
            ps.setInt(5, soc);
            ps.setInt(6, math);
            ps.setInt(7, sci);
            ps.setInt(8, comp);
            ps.setDouble(9, per);
            ps.setString(10, div);

            ps.executeUpdate();

            out.println("Student Added Successfully<br>");
            String path = request.getContextPath();
			out.println("<a href='" + path + "/home.html'>Go Home</a>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}