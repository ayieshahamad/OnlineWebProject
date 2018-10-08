<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Verification</title>
</head>
<body>
    <%@ include file="header.html" %>
    <div style= "height:400px; margin-left : 35px; margin-right : 35px; margin-top:10px;">
        <%@ include file="loggedOutMenuBar.html" %>

        <!--Remaining section-->
        <div style="border-style:dotted; border-color: green; border-width:1; background-color:MintCream; width:800px; height:200; margin:30;">
            <p style="margin:30;"> ${message} </p>
        </div>

    </div>
    <%@ include file="footer.html" %>
</body>
</html>