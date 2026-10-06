package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("username");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String course = request.getParameter("course");
        String branch = request.getParameter("branch");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Registration Details</title>

                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background: #eef7f2;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        min-height: 100vh;
                        margin: 0;
                    }

                    .result-card {
                        background: #ffffff;
                        width: 380px;
                        padding: 30px;
                        border-radius: 12px;
                        box-shadow: 0 6px 20px rgba(0,0,0,0.12);
                    }

                    h2 {
                        color: #198754;
                        text-align: center;
                        margin-bottom: 20px;
                    }

                    .detail {
                        font-size: 16px;
                        padding: 10px 0;
                        border-bottom: 1px solid #ddd;
                    }

                    .label {
                        font-weight: bold;
                        color: #333;
                    }

                    .success {
                        color: #198754;
                        text-align: center;
                        font-weight: bold;
                        margin-top: 20px;
                    }
                </style>
            </head>

            <body>
                <div class="result-card">

                    <h2>Registration Details</h2>
            """);

        out.println("<div class='detail'><span class='label'>Name:</span> " + name + "</div>");
        out.println("<div class='detail'><span class='label'>Age:</span> " + age + "</div>");
        out.println("<div class='detail'><span class='label'>Email:</span> " + email + "</div>");
        out.println("<div class='detail'><span class='label'>Gender:</span> " + gender + "</div>");
        out.println("<div class='detail'><span class='label'>Course:</span> " + course + "</div>");
        out.println("<div class='detail'><span class='label'>Branch:</span> " + branch + "</div>");

        out.println("<div class='success'>Registration Successful!</div>");

        out.println("""
                </div>
            </body>
            </html>
            """);
    }
}