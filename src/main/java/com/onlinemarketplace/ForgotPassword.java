package com.onlinemarketplace;

import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.User;
import com.onlinemarketplace.register.Register;
import com.onlinemarketplace.util.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ForgotYourPassword")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email_ID = request.getParameter("email_ID");
        if(UserDAO.checkIfEmailIdExist(email_ID) == true) {//email not found in DB.  so register this ID

            Register register = new Register();

            String hash=BCrypt.hashpw(BCrypt.gensalt(30), BCrypt.gensalt());
            int userID =Database.getInstance().getUseID(email_ID);
            UserDAO.updateVerification_Password_HashCode(Integer.toString(userID),hash);
            register.sendEmail_ForgotPassword("User", email_ID, hash, userID);

            String message = "<br>Email has been sent to this account: <b>" + email_ID + "</b><br>"+
                    "Please click the link provided in your email to reset the password.";
            request.setAttribute("message", message);

            request.getRequestDispatcher("/verification.jsp").forward(request, response);
        }else {
            request.setAttribute("emailID", email_ID);
            request.setAttribute("errorMessage", "This email ID is not registered");
            request.getRequestDispatcher("/forgotPassword.jsp")
                    .forward(request, response);
        }
    }
}
