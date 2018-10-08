<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Post Ad</title>
</head>
<body>
     <%@ include file="header.html" %>
     <div style= "height:500px; margin-left : 35px; margin-right : 35px; margin-top:10px;">
     <!-- menu-->

         <!--Remaining section-->
         <%@ include file="loggedInMenuBar.html"%>
     <!-- /menu-->

         <div style="margin-left:30;">
             <h4>${message}</h4>
             <h3>${title}</h3>
                <table style="color:black;" >

                    <tr><td colspan =2><img src="${image}" style="height:200px; width:250px"/></td>
                    </tr>
                    <tr><td>Price</td>
                        <td>$${price}</td></tr>
                    <tr><td>Type</td>

                        <td>${type}</td>
                    </tr>

                    <tr><td>Description</td>
                        <td>${description}</td>
                    </tr>

                    <tr><td>Location</td>
                        <td>${location}</td>
                    </tr>
                    <tr><td>Date Posted</td>
                        <td>${datePosted}</td>
                    </tr>
                    <tr>


                    </tr>
                </table>





         </div>
     </div>
     <%@ include file="footer.html" %>
</body>
</html>