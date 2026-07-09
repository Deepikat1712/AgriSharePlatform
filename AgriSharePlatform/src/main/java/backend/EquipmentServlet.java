package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EquipmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Available Equipment</title>");
        out.println("<link rel='stylesheet' href='/AgriSharePlatform/css/style.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<nav>");
        out.println("<div class='logo'>");
        out.println("<img src='/AgriSharePlatform/images/logo.png' width='50'>");
        out.println("<h2>🌱 AgriShare</h2>");
        out.println("</div>");

        out.println("<ul>");
        out.println("<li><a href='frontend/dashboard.html'>Dashboard</a></li>");
        out.println("<li><a href='frontend/equipment.html'>Equipment</a></li>");
        out.println("<li><a href='frontend/booking.html'>Bookings</a></li>");
        out.println("<li><a href='frontend/login.html'>Logout</a></li>");
        out.println("</ul>");
        out.println("</nav>");

        out.println("<div class='equipment-page'>");
        out.println("<h1>Available Equipment</h1>");
        out.println("<div class='card-container'>");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM equipment");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<div class='card'>");

                out.println("<h3>" + rs.getString("name") + "</h3>");
                out.println("<p><b>Category :</b> "
                        + rs.getString("category") + "</p>");
                out.println("<p><b>Price :</b> ₹"
                        + rs.getInt("price") + " / Day</p>");
                out.println("<p><b>Owner :</b> "
                        + rs.getString("owner") + "</p>");

                out.println("<a href='frontend/booking.html'>");
                out.println("<button>Book Now</button>");
                out.println("</a>");

                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}