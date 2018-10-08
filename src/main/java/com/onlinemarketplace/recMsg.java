package com.onlinemarketplace;

import com.onlinemarketplace.dao.AdDAO;
import com.onlinemarketplace.dao.MessageDAO;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.Ad;
import com.onlinemarketplace.model.Message;
import com.onlinemarketplace.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/recMsg")
public class recMsg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        System.out.println("Message receiving");
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("name")!=null) {
            //
            System.out.println("session exists");
            if(session.getAttribute("name")!=null) {
                System.out.println("from session");

                //display here
                User user = new User();

                request.setAttribute("message",messageRec((int)session.getAttribute("userID")));
                //request.setAttribute("userObject",user);
                request.setAttribute("user", session.getAttribute("name"));
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);

            }
            else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
        else {
            String userID = null;
            Cookie[] cookies = request.getCookies();
            if(cookies !=null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("userID")) {
                        userID = cookie.getValue();
                        System.out.println(cookie.getValue());

                    }
                }
            }
            if(userID!=null){
                System.out.println("from cookie");
                //display here
                User user = new User();

                request.setAttribute("message",messageRec(Integer.parseInt(userID)));
                //request.setAttribute("userObject",user);
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            }
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            //request.getRequestDispatcher("/index.jsp").forward(request, response);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String messageRec(int uID) {
        Message[] message = new Message[100];
        int msgCount = MessageDAO.getMessage(uID, message);
        Ad ad = new Ad();
        int i =0;
        String str = "";
        while (i < msgCount) {
            AdDAO.getAdName(message[i].getAd(), ad, message[i].getSender());
            str=str+ "Post: " + ad.getName() + "\nSender: " + UserDAO.getFirstName(message[i].getSender()) + "\nMessage: " + message[i].getMessage()+"<br>";
            i++;
        }
        return str;
    }
}
