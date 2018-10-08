package com.onlinemarketplace;

import com.onlinemarketplace.dao.AdDAO;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/EditAd")
public class EditAd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ad ad = new Ad();
        ad.setName(request.getParameter("title"));
        ad.setPrice(Double.parseDouble(request.getParameter("price")));
        ad.setType(request.getParameter("type"));
        ad.setDescription(request.getParameter("description"));
        ad.setLocation(request.getParameter("location"));

        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //ad.setDatePosted(formatter.format(new Date()));
        ad.setAvailable(true);//hardcode
        //ad.setImageUrl("D:\\ComIT.ORG\\imageURL\\1.jpg");

        int adID = Integer.parseInt( request.getParameter("adID"));
        System.out.println("edit ad - doGet " + request.getParameter("user"));
        //
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("name")!=null) {
            //
            System.out.println("session exists");
            if(session.getAttribute("name")!=null) {
                System.out.println("from session ad");
                //display here
                //if(session.getAttribute("editAd")!=null) { //it means we have to execute update query else we have to insert ad
                    AdDAO.updateAd(ad, (int) session.getAttribute("userID"), (int) session.getAttribute("adID"));
                    Ad[] ad_updated = new Ad[1];
                    AdDAO.getUserAds((int) session.getAttribute("userID"), ad_updated, (int) session.getAttribute("adID"));
                    //session.setAttribute("editAd",null);
                    //session.setAttribute("adID",null);

                request.setAttribute("title", ad_updated[0].getName());
                request.setAttribute("price", ad_updated[0].getPrice());
                request.setAttribute("type", ad_updated[0].getType());
                request.setAttribute("description", ad_updated[0].getDescription());
                request.setAttribute("image", ad_updated[0].getImageUrl());
                request.setAttribute("datePosted", ad_updated[0].getDatePosted());
                request.setAttribute("location", ad_updated[0].getLocation());

                request.setAttribute("message", "Your post is now updated ");
                request.setAttribute("user", session.getAttribute("name"));
                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
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
                System.out.println("from cookie ad");
                //display here
                AdDAO.updateAd(ad,Integer.parseInt(userID),adID);
                Ad[] ad_updated = new Ad[1];
                AdDAO.getUserAds(Integer.parseInt(userID), ad_updated, adID);

                request.setAttribute("title", ad_updated[0].getName());
                request.setAttribute("price", ad_updated[0].getPrice());
                request.setAttribute("type", ad_updated[0].getType());
                request.setAttribute("description", ad_updated[0].getDescription());
                request.setAttribute("image", ad_updated[0].getImageUrl());
                request.setAttribute("datePosted", ad_updated[0].getDatePosted());
                request.setAttribute("location", ad_updated[0].getLocation());

                request.setAttribute("message", "Your post is now updated");
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
            }
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
    }
}
