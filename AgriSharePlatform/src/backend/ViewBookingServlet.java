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
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ViewBookingServlet")
public class ViewBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>View Bookings</title>");
        out.println("<link rel='stylesheet' href='/AgriSharePlatform/css/style.css'>");
        out.println("</head><body>");

        out.println("<h2 align='center'>Booking Details</h2>");
        out.println("<table border='1' align='center' cellpadding='10'>");
        out.println("<tr><th>ID</th><th>User</th><th>Equipment</th><th>Date</th><th>Days</th></tr>");

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
        }

        out.println("</table></body></html>");
    }
}