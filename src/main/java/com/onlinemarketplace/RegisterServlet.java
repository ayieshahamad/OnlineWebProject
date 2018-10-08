package com.onlinemarketplace;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.User;
import com.onlinemarketplace.register.Register;
import com.onlinemarketplace.util.BCrypt;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email_ID = request.getParameter("email_ID");
        String password = request.getParameter("password");

        if(UserDAO.checkIfEmailIdExist(email_ID) == false) {//email not found in DB.  so register this ID
            User user = new User();
            Register register = new Register();
            user.setUser(firstName, lastName, email_ID, password);
            user.setHashCode(BCrypt.hashpw(BCrypt.gensalt(30), BCrypt.gensalt()));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            user.setDateJoined(formatter.format(new Date()));
            Database.getInstance().saveUserProfile_register(user);
            user.setUserID(Database.getInstance().getUseID(email_ID));
            register.sendEmail(firstName + " " + lastName, email_ID, user.getHashCode(), user.getUserID());

            String message = "<br>Email has been sent to this account: <b>" + email_ID + "</b><br>"+
                             "Please verify your account and <a href=\"login.jsp\">login</a>";
            request.setAttribute("message", message);

            request.getRequestDispatcher("/verification.jsp").forward(request, response);
        }else {
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("emailID", email_ID);
            request.setAttribute("errorMessage", "This email address is already registered!");
            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
        }
       /* } else {
            request.setAttribute("errorMessage", "Invalid Credentials! Please try login again.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }*/

    }
}
