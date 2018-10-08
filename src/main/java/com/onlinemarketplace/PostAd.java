package com.onlinemarketplace;

import com.onlinemarketplace.dao.AdDAO;
import com.onlinemarketplace.dao.UserDAO;
import com.onlinemarketplace.model.Ad;
import com.onlinemarketplace.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/PostAd")
@MultipartConfig

public class PostAd extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(PostAd.class.getCanonicalName());
    @Override//not working
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Ad ad = new Ad();

        ad.setName(request.getParameter("title"));
        ad.setPrice(Double.parseDouble(request.getParameter("price")));
        ad.setType(request.getParameter("type"));
        ad.setDescription(request.getParameter("description"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ad.setDatePosted(formatter.format(new Date()));
        ad.setAvailable(true);//hardcode
        ad.setImageUrl("D://ComIT.ORG/imageURL/1.jpg");

        //save file
        //processRequest(request,response);

        System.out.println("post ad - doGet " + request.getParameter("user"));
        //
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("name")!=null) {
            //
            System.out.println("session exists");
            if(session.getAttribute("name")!=null) {
                System.out.println("from session ad");
                //display here
                if(session.getAttribute("editAd")!=null) { //it means we have to execute update query else we have to insert ad
                    AdDAO.updateAd(ad, (int) session.getAttribute("userID"), (int) session.getAttribute("adID"));
                    session.setAttribute("editAd",null);
                    session.setAttribute("adID",null);
                }
                else {
                    try {
                        AdDAO.postAd(ad,(int)session.getAttribute("userID"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                request.setAttribute("message", "ad posted");
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
                System.out.println("from cookie ad");
                //display here

                try {
                    AdDAO.postAd(ad,Integer.parseInt(userID));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                request.setAttribute("message", "ad posted");
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                request.getRequestDispatcher("/welcome.jsp").forward(request, response);
            }
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //working
        Ad ad = new Ad();

        ad.setName(request.getParameter("title"));
        ad.setPrice(Double.parseDouble(request.getParameter("price")));
        ad.setType(request.getParameter("type"));
        ad.setDescription(request.getParameter("description"));
        ad.setLocation(request.getParameter("location"));



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //String str_date = formatter.format(new Date());

       //     Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(str_date);//new
         //   java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//new

        ad.setDatePosted(formatter.format(new Date()));
        ad.setAvailable(true);//hardcode
        ad.setImageUrl("D:\\ComIT.ORG\\imageURL\\1.jpg");

        //save file
        //String [] fileName=new String[1];
        //fileName[0]="";
        //processRequest(request,response,fileName);

        System.out.println("post ad - doGet " + request.getParameter("user"));
        //
        HttpSession session=request.getSession(false);
        if(session!=null && session.getAttribute("name")!=null) {
            //
            System.out.println("session exists");
            if(session.getAttribute("name")!=null) {
                System.out.println("from session ad");
                //display here
                /*if(session.getAttribute("editAd")!=null) { //it means we have to execute update query else we have to insert ad
                    AdDAO.updateAd(ad, (int) session.getAttribute("userID"), (int) session.getAttribute("adID"));
                    session.setAttribute("editAd",null);
                    session.setAttribute("adID",null);
                }
                else*/

                processRequest(request,response,ad,(int)session.getAttribute("userID"));

                try {
                    AdDAO.postAd(ad,(int)session.getAttribute("userID"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                request.setAttribute("message", "Your Ad is now posted");
                request.setAttribute("user", session.getAttribute("name"));
                //request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                //request.setAttribute("adID", adID);
                request.setAttribute("title", ad.getName());
                request.setAttribute("price", ad.getPrice());
                request.setAttribute("type", ad.getType());
                request.setAttribute("description", ad.getDescription());
                request.setAttribute("image", ad.getImageUrl());
                request.setAttribute("datePosted", ad.getDatePosted());
                request.setAttribute("location", ad.getLocation());

                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
                System.out.println("view");
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
                processRequest(request,response,ad,Integer.parseInt(userID));

                try {
                    AdDAO.postAd(ad,Integer.parseInt(userID));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //request.setAttribute("message", "ad posted");
                request.setAttribute("user", UserDAO.getFirstName(Integer.parseInt(userID)));
                //request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                request.setAttribute("message", " Your Ad is now posted");
                request.setAttribute("title", ad.getName());
                request.setAttribute("price", ad.getPrice());
                request.setAttribute("type", ad.getType());
                request.setAttribute("description", ad.getDescription());
                request.setAttribute("image", ad.getImageUrl());
                request.setAttribute("datePosted", ad.getDatePosted());
                request.setAttribute("location", ad.getLocation());

                request.getRequestDispatcher("/viewAd.jsp").forward(request, response);
                System.out.println("view");
            }
            else
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
    }
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response, Ad ad, int userID)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String pathToProject = "D://ComIT.ORG/Online Web Project/src/main/webapp/";
        final String pathToInternalProject = "imageURL/"+userID+"/";
        final String path = pathToProject + pathToInternalProject;//request.getParameter("destination");
        final Part filePart = request.getPart("file");
        String fileName = "1.jpg";//getFileName(filePart);
        System.out.println(getFileName(filePart));
        OutputStream out = null;
        InputStream filecontent = null;
        //final PrintWriter writer = response.getWriter();
        //checking if path exist
        Path p = Paths.get(path);
        if(Files.exists(p)){
            //path exist
            System.out.println("Directory exist : "+p);
        }else {
            //create  path
            Files.createDirectory(p);
            System.out.println("Directoy Created");
        }
        //now selecting a name
        int i = 2;
        while(true) {
            p = Paths.get(path + fileName);
            if (Files.exists(p)) {
                //file is already there so rename to next
                fileName = i+".jpg";
                i++;
            }
            else
                break;
        }
        //done with naming a file
        ad.setImageUrl(pathToInternalProject+fileName);

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
           // writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            /*writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
*/
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            /*if (writer != null) {
                writer.close();
            }*/
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}