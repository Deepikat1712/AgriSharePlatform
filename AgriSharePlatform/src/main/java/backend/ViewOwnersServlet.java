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

        String cp = request.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Equipment Owners</title>");
        out.println("<link rel='stylesheet' href='" + cp + "/css/style.css'>");
        out.println("</head>");

        out.println("<body>");

        out.println("<nav>");
        out.println("<div class='logo'>");
        out.println("<img src='" + cp + "/images/logo.png' width='50'>");
        out.println("<h2>🌱 AgriShare</h2>");
        out.println("</div>");

        out.println("<ul>");
        out.println("<li><a href='" + cp + "/frontend/admin.html'>Admin Dashboard</a></li>");
        out.println("</ul>");
        out.println("</nav>");

        out.println("<div class='equipment-page'>");
        out.println("<h1>Equipment Owners</h1>");

        out.println("<table border='1' align='center' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("</tr>");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM owners");

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
            out.println("<h3>Error : " + e.getMessage() + "</h3>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}