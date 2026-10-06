package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username is required.");
        }
        else if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password is required.");
        }
        else if (username.equals("teena") && password.equals("12345")) {
            request.setAttribute("username", username);
        }
        else {
            request.setAttribute("error", "Invalid username or password.");
        }

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }
}
