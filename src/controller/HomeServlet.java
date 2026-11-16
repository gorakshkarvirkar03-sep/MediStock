
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println(
            "<html><body>" +
            "<h1>Welcome to MediStock!</h1>" +
            "<p>Java Servlet is working successfully.</p>" +
            "</body></html>"
        );
    }
}

