<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
    <body>
        <%@ include file="header.html" %>
        <div style= "height:400px; margin-left : 35px; margin-right : 35px; margin-top:10px;">
            <%@ include file="loggedOutMenuBar.html" %>

            <p style="color:red">${errorMessage}</p>
            <br>
            <form action="login" method="post" class="w3-container">
                <table style="color:black;" >
                    <tr><td><h3>Login</h3></td></tr>
                    <tr><td>Email   </td>
                        <td><input style="width:300px; height:30px" class="w3-round w3-border " type="email" required="required" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"   name="email_ID" /></td>
                    </tr>
                    <tr><td>Password</td>
                        <td><input style="width:300px; height:30px" class="w3-round w3-border " type="password" name="password" required="required"/></td>
                    </tr>
                    <tr><td></td>
                        <td><input style="width:300px;" class="w3-btn w3-blue-grey" type="submit" value="Login" /></td>
                    </tr>
                    <tr><td></td>
                        <td><a href="forgotPassword.jsp">Forgot password?</a></td>
                    </tr>
                </table>
            </form>
        </div>

        <%@ include file="footer.html" %>
    </body>
</html>