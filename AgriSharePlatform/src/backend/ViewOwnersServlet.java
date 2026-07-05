package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewOwnersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<title>Equipment Owners</title>");
        out.println("<link rel='stylesheet' href='/AgriSharePlatform/css/style.css'>");
        out.println("</head><body>");

        out.println("<h2 align='center'>Equipment Owners</h2>");

        out.println("<table border='1' align='center' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("</tr>");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM owners");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("</tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}