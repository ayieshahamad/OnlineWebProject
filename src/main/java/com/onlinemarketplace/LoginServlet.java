package com.onlinemarketplace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.register.Login;
import com.onlinemarketplace.model.User;
import com.onlinemarketplace.util.BCrypt;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //PrintWriter out = response.getWriter();
        //out.println("<hello>");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email_ID = request.getParameter("email_ID");
        String password = request.getParameter("password");
        //validate
        User user = new User();

        if(UserDAO.login(email_ID, BCrypt.hashpw(password,BCrypt.SALT), user) == false) {
            System.out.println("Invalid email ID or password");
            request.setAttribute("errorMessage","Invalid email ID or password. Please enter correct email ID and password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        else {
            if(user.isVerify()) {
                HttpSession session=request.getSession();
                session.setAttribute("name",user.getFirstName());
                session.setAttribute("userID",user.getUserID());
                System.out.println("cookie usedID = "+user.getUserID());
                session.setMaxInactiveInterval(60*30);//30 minutes//half hour


                Cookie userName = new Cookie("userID", Integer.toString(user.getUserID()));
                userName.setMaxAge(30*60);
                response.addCookie(userName);

                //


                request.setAttribute("user", user.getFirstName());
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "<br>Your account is not verified yet. " +
                        "<br>email has been sent to your account already. " +
                        "<br>Please verfy your account and Click <a href=\"login.jsp\">here</a> to login" +
                        "<br>");

                request.getRequestDispatcher("/verification.jsp").forward(request, response);
            }
        }
    }
}
