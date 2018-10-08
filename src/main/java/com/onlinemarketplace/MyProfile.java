package com.onlinemarketplace;

import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(urlPatterns = "/MyProfile")
public class MyProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("My Profile- doGet");
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("name")!=null) {
            //
            System.out.println("session exists");
            if(session.getAttribute("name")!=null) {
                System.out.println("from session");

                //display here
                User user = new User();

                request.setAttribute("myprofile",displayProfile((int)session.getAttribute("userID"),user));
                request.setAttribute("userObject",user);
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

                request.setAttribute("myprofile",displayProfile(Integer.parseInt(userID),user));
                request.setAttribute("userObject",user);
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
    private String displayProfile(int userID, User user){
        //User user = new User();
        boolean retValue = UserDAO.getUserProfile(userID,user);
        String profile = null;
        if(retValue == true)
        {
            profile = "<table>" +
                    "  <tr>" +
                    "    <td><b>Username</b></td>" +
                    "    <td>"+user.getUserName()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>First Name</b></td>" +
                    "    <td>"+user.getFirstName()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Last Name</b></td>" +
                    "    <td>"+user.getLastName()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Email</b></td>" +
                    "    <td>"+user.getEmail_Id()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Address</b></td>" +
                    "    <td>"+user.getAddress()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>City</b></td>" +
                    "    <td>"+user.getCity()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>State</b></td>" +
                    "    <td>"+user.getState()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Country</b></td>" +
                    "    <td>"+user.getCountry()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Postal Code</b></td>" +
                    "    <td>"+user.getPostal_code()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>Date of Birth</b></td>" +
                    "    <td>"+user.getDOB()+"</td> " +
                    "  </tr>" +
                    "  <tr>" +
                    "    <td><b>User Since</b></td>" +
                    "    <td>"+user.getDateJoined()+"</td> " +
                    "  </tr>" +
                    "</table>";
        }
        return profile;
    }

}