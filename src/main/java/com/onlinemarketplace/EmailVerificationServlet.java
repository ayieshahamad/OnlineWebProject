package com.onlinemarketplace;

import com.onlinemarketplace.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/EmailVerification")
public class EmailVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        System.out.println(request.getParameter("userID"));
        String scope = request.getParameter("scope");
        String hash = request.getParameter("hash");
        String message = null;
        // verify with database
        if(UserDAO.verifyEmailHash(userID.toString(), hash) && scope.equals("activation")) {
            //update status as active

            UserDAO.updateVerificationStaus(userID.toString(), "true");
            UserDAO.updateVerification_HashCode(userID.toString(), null);
            message = "<br>Email verified! Your account is now activated. Click <a href=\"login.jsp\">here</a> to login";
            request.setAttribute("message",message);
            request.getRequestDispatcher("/verification.jsp").forward(request,response);
        }
        else if(UserDAO.verifyEmailHash_forgotPassword(userID.toString(), hash) && scope.equals("forgotpassword")) {
            //update status as active
            //UserDAO.updateVerification_HashCode(userID.toString(), null);
            //put in session to save userID
            System.out.println("in forgot password braces");
            request.getSession().setAttribute("userID", userID.toString());
            request.getSession().setAttribute("ResetPassword", "ResetPassword");
            System.out.println("in forgot password braces 2");
            request.getRequestDispatcher("/ResetPassword.jsp").forward(request,response);
        }



    }

}
