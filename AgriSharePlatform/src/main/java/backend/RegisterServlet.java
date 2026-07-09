package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {

            Connection con = DBConnection.getConnection();

            String query =
                "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                response.sendRedirect("frontend/login.html");
            } else {
                response.getWriter().println("Registration Failed!");
            }

        } catch(Exception e) {
            e.printStackTrace();
            response.getWriter().println(e.getMessage());
        }
    }
}