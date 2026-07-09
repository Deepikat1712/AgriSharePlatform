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

public class ViewBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String cp = request.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Booking Details</title>");
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
        out.println("<h1>Booking Details</h1>");

        out.println("<table border='1' align='center' cellpadding='10'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>User</th>");
        out.println("<th>Equipment</th>");
        out.println("<th>Date</th>");
        out.println("<th>Days</th>");
        out.println("</tr>");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM booking");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("user_name") + "</td>");
                out.println("<td>" + rs.getString("equipment_name") + "</td>");
                out.println("<td>" + rs.getString("date") + "</td>");
                out.println("<td>" + rs.getInt("days") + "</td>");
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