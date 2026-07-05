package backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddEquipmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("price"));
        String owner = request.getParameter("owner");

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO equipment(name,category,price,owner) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, price);
            ps.setString(4, owner);

            int i = ps.executeUpdate();

            if(i > 0){
                response.getWriter().println("Equipment Added Successfully!");
            }else{
                response.getWriter().println("Failed to Add Equipment!");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}