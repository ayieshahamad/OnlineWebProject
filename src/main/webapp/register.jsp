<html>
    <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Register</title>
    </head>
    <body>
        <%@ include file="header.html" %>
        <div style= "height:400px; margin-left : 35px; margin-right : 35px; margin-top:10px;">

            <%@ include file="loggedOutMenuBar.html" %>

            <!--Remaining section-->
            <p id = "message" style="color:red">${errorMessage}</p>
            <form action="register" method="post" onsubmit="return myFunction()">
                <table style="color:black;" >
                    <tr><td><h3>Register</h3></td></tr>
                    <tr><td>First Name</td>
                        <td><input value="${firstName}" type="text" name="firstName" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                    </tr>
                    <tr><td>Last Name</td>
                        <td><input value="${lastName}" type="text" name="lastName" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                    </tr>
                    <tr><td>Email</td>
                        <td><input value="${emailID}" type="email" name="email_ID" style="width:300px; height:30px" class="w3-round w3-border " required="required" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/></td>
                    </tr>
                    <tr><td>Password</td>
                        <td><input type="password" name="password" id="password" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                    </tr>
                    <tr><td>Confirm Password</td>
                        <td><input type="password" name="confirmpassword" id="confirmpassword" data-match="#password" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                    </tr>
                    <tr><td></td>
                        <td><input id="registerButton" style="width:300px;" class="w3-btn w3-blue-grey" type="submit" value="Register" /></td>
                    </tr>
                </table>
            </form>

        </div>
        <%@ include file="footer.html" %>
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>-->
        <script type="text/javascript">

            function myFunction() {
                var password1 = document.getElementById("password").value;
                var password2 = document.getElementById("confirmpassword").value;
                var ok = true;
                if (password1 != password2) {
                    //alert("Passwords Do not match");
                    //document.getElementById("password").style.backgroundColor = "red";
                    //document.getElementById("confirmpassword").style.borderColor = "red";
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'Password do not match';
                    ok = false;
                }
                return ok;
            }
        </script>;
    </body>
</html>