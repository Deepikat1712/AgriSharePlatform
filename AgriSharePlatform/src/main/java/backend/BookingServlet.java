package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class BookingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("user_name");
        String equipmentName = request.getParameter("equipment_name");
        String date = request.getParameter("date");
        int days = Integer.parseInt(
                request.getParameter("days"));

        try {

            Connection con = DBConnection.getConnection();

            String query =
                "INSERT INTO booking(user_name, equipment_name, date, days) VALUES(?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setString(1, userName);
            ps.setString(2, equipmentName);
            ps.setString(3, date);
            ps.setInt(4, days);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                response.getWriter().println(
                    "Booking Successful!");
            } else {
                response.getWriter().println(
                    "Booking Failed!");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}