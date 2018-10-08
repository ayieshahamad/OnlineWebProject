<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>${user}</title>
</head>
<body>
     <%@ include file="header.html" %>
     <div style= "height:500px; margin-left : 35px; margin-right : 35px; margin-top:10px;">

         <!--Remaining section-->
          <%@ include file="loggedInMenuBar.html"%>
         <h3> Welcome ${user} </h3>

         <div style="margin-left:30;">

             ${myAds}
             ${myprofile}
             ${message}
         </div>
     </div>
    <!-- <%@ include file="footer.html" %>-->
</body>
</html>