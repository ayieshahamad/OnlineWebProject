<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>${user}</title>
</head>
<body>
     <%@ include file="header.html" %>
     <div style= "height:600px; margin-left : 35px; margin-right : 35px; margin-top:10px;">

         <!--Remaining section-->
          <%@ include file="loggedInMenuBar.html"%>
          <h3> Welcome ${user} </h3>

          <div class="w3-dropdown-hover">
          <button type="submit" value="sort" class="w3-button w3-border " style="color:#008B8B"><b>Sort by:</b></button>

              <div class="w3-dropdown-content w3-bar-block w3-card-4">
                  <a href="http://localhost:8080/myhome?sort=date" type="" class="w3-bar-item w3-button"  value="Date (latest)" id="date">Date (latest)</a>
                  <a href="http://localhost:8080/myhome?sort=price" type="" class="w3-bar-item w3-button"  value="Price (min-max)" id="price">Price (min-max)</a>

              </div>
            </div>

          <div style="margin-left:30;">
             ${AllAds}

         </div>
     </div>
    <!-- <%@ include file="footer.html" %>-->
</body>
</html>