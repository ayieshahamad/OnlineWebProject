package com.onlinemarketplace;

import com.onlinemarketplace.dao.AdDAO;
import com.onlinemarketplace.dao.MessageDAO;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns="/AdFunctionality")
public class AdFunctions extends HttpServlet {
    //http://localhost:8080/AdFunctionality?scope=delete&adID=1&userID=1
    //http://localhost:8080/AdFunctionality?scope=edit&adID=1&userID=1
    //http://localhost:8080/AdFunctionality?scope=view&adID=1&userID=1
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer adID = Integer.parseInt(request.getParameter("adID"));
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        String scope = request.getParameter("scope");
        //checking for valid userID since logged in
        HttpSession session=request.getSession(false);
        //int userID_from_session = (int)session.getAttribute("userID");

        //if(userID_from_session == userID) {//valid
            if (scope.equals("view")) {
                session.setAttribute("adID",adID);
                Ad[] ad = new Ad[1];
                int countOfAds =  AdDAO.getUserAds(userID,ad,adID);
                request.setAttribute("user", UserDAO.getFirstName(userID));

                request.setAttribute("adID", adID);
                request.setAttribute("title", ad[0].getName());
                request.setAttribute("price", ad[0].getPrice());
                request.setAttribute("type", ad[0].getType());
                request.setAttribute("description", ad[0].getDescription());
                request.setAttribute("image", ad[0].getImageUrl());
                request.setAttribute("datePosted", ad[0].getDatePosted());
                request.setAttribute("location", ad[0].getLocation());

                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
                System.out.println("view");
            }
            else if (scope.equals("delete")) {
                AdDAO.deleteAd(userID,adID);
                request.getRequestDispatcher("MyAds").forward(request, response);
                System.out.println("deleted");
            }
            else if (scope.equals("edit")) {
                //session.setAttribute("editAd","editAd");
                session.setAttribute("adID",adID);
                Ad[] ad = new Ad[1];
                int countOfAds =  AdDAO.getUserAds(userID,ad,adID);
                request.setAttribute("adID", adID);
                request.setAttribute("title", ad[0].getName());
                request.setAttribute("price", ad[0].getPrice());
                request.setAttribute("type", ad[0].getType());
                request.setAttribute("description", ad[0].getDescription());
                request.setAttribute("image", ad[0].getImageUrl());
                request.setAttribute("location", ad[0].getLocation());
               // request.setAttribute("buttonName", "Save");

                request.getRequestDispatcher("/updateAd.jsp").forward(request, response);
                System.out.println("edit");
            }
            else if (scope.equals("saveAd")) {

                //insert into table
                userID = (int)request.getSession(false).getAttribute("userID");
                if(AdDAO.checkIfAdIsSavedAlready(userID,adID) == false)//means not saved
                {
                    AdDAO.saveAd(userID, adID);
                    System.out.println("save ad");
                }
                request.getRequestDispatcher("myhome").forward(request, response);

            }
            else if (scope.equals("msg")) {

                System.out.println("msg");
                int userID_sender = (int)session.getAttribute("userID");

                request.setAttribute("user",UserDAO.getFirstName(userID_sender));
                int rec = userID;
                int ad = adID;
                Ad ad2 = new Ad();
                AdDAO.getAdName(ad,ad2,rec);
                String msg = "Hello, let me know details about  '"+ ad2.getName();
                try {
                    MessageDAO.insertMsgInTable(ad,userID_sender,rec,msg);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                request.setAttribute("message","default message sent");
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);


            }
       // }
    }
}
