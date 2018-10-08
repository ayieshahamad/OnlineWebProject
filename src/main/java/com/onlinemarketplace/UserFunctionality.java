package com.onlinemarketplace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/UserFunctionality")
public class UserFunctionality  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


       // request.getRequestDispatcher("/register.jsp").forward(request,response);
        System.out.println("here in logout");
        {
            //logout
            HttpSession session=request.getSession(false);
            if(session != null ) {
                System.out.println("not null");
                session.invalidate();
            }
            //cookie logout
            Cookie loginCookie = null;
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("userID")){
                        loginCookie = cookie;
                        break;
                    }
                }
            }
            if(loginCookie != null){
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
            }

            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
