
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.login(username, password);

        if (user != null) {

            HttpSession session = request.getSession(true);

            session.setAttribute("loggedInUser", user);

            response.sendRedirect(
                request.getContextPath() + "/dashboard.html"
            );

        } else {

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                "<html><body>" +
                "<h2>Login Failed!</h2>" +
                "<p>Invalid username or password.</p>" +
                "<a href='login.html'>Try Again</a>" +
                "</body></html>"
            );
        }
    }
}


