package com.onlinemarketplace;


import com.onlinemarketplace.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/resetPassword")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        //get user ID from String
        HttpSession session=request.getSession(false);
        if(session!=null ) {
            System.out.println("session exists for reset password");
            if(session.getAttribute("userID")!=null) {//it is never going to be null
                System.out.println("from session userID");
                UserDAO.updatePassword((String)session.getAttribute("userID"),password);
                request.setAttribute("message", "Your password is now reset. <br>Please login with your new password.");

                //empty session
                //HttpSession session=request.getSession(false);
                if(session != null ) {
                    System.out.println("deleting session");
                    session.invalidate();
                }
                //
                request.getRequestDispatcher("/verification.jsp").forward(request, response);
            }
            else {//this is never going to execute
                request.setAttribute("message", "Oooops! Something went wrong while updating password");
                request.getRequestDispatcher("/verfication.jsp").forward(request, response);
            }
        }
    }
}
