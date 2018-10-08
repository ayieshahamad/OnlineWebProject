<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Edit Ad</title>
</head>
<body>
     <%@ include file="header.html" %>
     <div style= "height:500px; margin-left : 35px; margin-right : 35px; margin-top:10px;">

         <div>
             ${message}
               <!-- ad post form-->
                <form action="EditAd" method="post">
                <!-- i had to to this-->
                <input value="${adID}" type="text" name="adID" hidden/>

                    <table style="color:black;" >
                        <tr><td><h3>Edit Ad</h3></td></tr>
                        <tr><td>Title</td>
                            <td><input value="${title}" type="text" name="title" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                        </tr>
                        <tr><td>Price</td>
                            <td><input value="${price}" type="text" name="price" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                        </tr>
                        <tr><td>Type</td>

                            <td><select  value="${type}" class="w3-select w3-round w3-border" name="type" style="width:300px; " required="required">
                                  <option value="" disabled selected>Choose your option</option>
                                  <option value="type1">type 1</option>
                                  <option value="type2">type 2</option>
                                  <option value="type3">type 3</option>
                                </select></td>
                        </tr>

                        <tr><td>Description</td>
                            <td><textarea value="${description}" type="text" name="description" style="width:300px; height:70px" class="w3-round w3-border " required="required">${description}</textarea></td>
                        </tr>
                        <!--<tr><td>Image</td>
                            <td><input type="file" name="file" id="file" value="${image}" accept="image/*" class="w3-round w3-border" style="width:300px; height:50px; background-imag: url('imageURL/1.jpg')"></td
                        -->
                        </tr>
                        <tr><td>Location</td>
                            <td><input value="${location}" type="text" name="location" style="width:300px; height:30px" class="w3-round w3-border " required="required"/></td>
                        </tr>
                        <tr>
                            <td><a href="home" id="cancelButton" style="width:150px;" class="w3-btn w3-grey" type="submit" value="Cancel" >Cancel</a></td>
                            <td><input id="postButton" style="width:150px;" class="w3-btn w3-blue-grey" type="submit" value="Update" /></td>


                        </tr>
                    </table>
                </form>


         </div>
     </div>
     <%@ include file="footer.html" %>
</body>
</html>