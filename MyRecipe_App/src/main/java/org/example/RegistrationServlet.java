package org.example;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

    // Update the database connection details accordingly
    private static final String DB_URL = "jdbc:mysql://localhost:3306/recipe_app";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username and password from the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Insert the user data into the database
        try {
            // Create a database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the statement
            statement.executeUpdate();

            // Close the resources
            statement.close();
            connection.close();

            // Redirect the user to a success page or login page
            response.sendRedirect("registration_success.html");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors
            // Redirect the user to an error page
            response.sendRedirect("registration_error.html");
        }
    }
}
