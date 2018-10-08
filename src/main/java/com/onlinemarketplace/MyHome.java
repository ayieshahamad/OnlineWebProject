package com.onlinemarketplace;

import com.onlinemarketplace.dao.AdDAO;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/myhome")
public class MyHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Get all the ads from db
        System.out.println("in All Ads - do GET");
        int _sort = 0;
        String sort = request.getParameter("sort");
        if(sort.equals("date"))
            _sort = 1;
        else
            _sort = 2;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {

            if (session.getAttribute("name") != null) {
                System.out.println("from session");
                request.setAttribute("message","");
                request.setAttribute("myprofile","");
                request.setAttribute("AllAds", displayAllAds(_sort));
                request.setAttribute("user", session.getAttribute("name"));
                request.getRequestDispatcher("/myhome.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String userID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userID")) {
                        userID = cookie.getValue();
                        System.out.println(cookie.getValue());

                    }
                }
            }
            if (userID != null) {
                System.out.println("from cookie");
                request.setAttribute("message","");
                request.setAttribute("myprofile","");
                request.setAttribute("AllAds", displayAllAds(_sort));
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                request.getRequestDispatcher("/myhome.jsp").forward(request, response);
            } else
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //called from welcome page
        //Get all the ads from db
        System.out.println("in All Ads - do post");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {

            if (session.getAttribute("name") != null) {
                System.out.println("from session");
                request.setAttribute("message","");
                request.setAttribute("myprofile","");
                request.setAttribute("AllAds", displayAllAds(0));
                request.setAttribute("user", session.getAttribute("name"));
                request.getRequestDispatcher("/myhome.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String userID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userID")) {
                        userID = cookie.getValue();
                        System.out.println(cookie.getValue());

                    }
                }
            }
            if (userID != null) {
                System.out.println("from cookie");
                request.setAttribute("message","");
                request.setAttribute("myprofile","");
                request.setAttribute("AllAds", displayAllAds(0));
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                request.getRequestDispatcher("/myhome.jsp").forward(request, response);
            } else
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private String displayAllAds(int _sort) {
        Ad[] ad = new Ad[50];
        int countOfAds =  AdDAO.getAllUsersAds(ad,_sort);
        String returnAdDisplayString = "";
        int i =0;
        while(i < countOfAds) {
            returnAdDisplayString +=
                    "<tr style=\"border-bottom: 1px solid #ddd;\">" +
                            "<td width=\"100\" height=\"100\"><a href=\"http://localhost:8080/AdFunctionality?scope=view&adID="+ad[i].getProductID()+"&userID="+ad[i].getUserID()+"\"><img src=\" "+ad[i].getImageUrl() +" \" onerror=\"this.src='imageURL/6.png'\"  alt=\"imageURL/6.png\" style=\"width:100px;height:100px;\"></a></td>"+
                            "<td width=\"400\" height=\"100\" style=\"overflow: hidden;  text-overflow: ellipsis; white-space: nowrap;;\"><b>"+ad[i].getName()+"- $"+ ad[i].getPrice() +"</b><br>"+ad[i].getDatePosted()+"<br>" + ad[i].getDescription() + "</td>\n"+
                            "<td width=\"50\" height=\"100\"><a href=\"http://localhost:8080/AdFunctionality?scope=saveAd&adID="+ad[i].getProductID()+"&userID="+ad[i].getUserID()+"\" class=\"material-icons w3-button\" style=\"visibility:hidden;color:grey\" >bookmark</a><br><a  href=\"http://localhost:8080/AdFunctionality?scope=msg&adID="+ad[i].getProductID()+"&userID="+ad[i].getUserID()+"\" class=\"material-icons w3-button\" style=\"color:grey\">message</a></td>\n"+
                            "</tr>" ;

            i++;
        }
        return "<table border=\"0\" width=\"650\" style=\"table-layout:fixed; border-collapse: collapse; white-space:nowrap;\">" + returnAdDisplayString + "</table>";
    }
}
