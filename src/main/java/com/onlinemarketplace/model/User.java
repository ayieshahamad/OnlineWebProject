package com.onlinemarketplace.model;
import com.onlinemarketplace.database.Database;

public class User {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String postal_code;
    private String DOB;
    private String phone;

    private String email_Id;//unique
    private String password;//or class password
    private String hashCode;
    private boolean verify;
    private String dateJoined;

    public User(){
        userID = -1;
        firstName = null;
        lastName = null;
        password = null;
        email_Id = null;
        hashCode = null;
        verify = false;
        dateJoined = "";

        address = "";
        city="";
        state="";
        country="";
        postal_code="";
        DOB= "";
        phone="";
    }

    public void setUser(String firstName, String lastName, String email_Id, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email_Id = email_Id;
        // count++;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String  getFirstName(){
        return firstName;
    }

    public String getEmail_Id(){
        return email_Id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword(){
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setUserProfile(int userID, String userName, String firstName, String lastName, String address, String country, String state, String city, String postal_code, String DOB, String phone, String email_Id, String dateJoined) {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.postal_code = postal_code;
        this.DOB = DOB;
        this.phone = phone;
        this.email_Id = email_Id;
        this.dateJoined = dateJoined;
    }

    public void setUserName(String userName) {
        this.userName = (userName==null)? "":userName;
    }

    public void setAddress(String address) {
        this.address = (address==null)? "":address;
    }

    public void setCountry(String country) {
        this.country = (country==null)? "":country;
    }

    public void setState(String state) {
        this.state = (state==null)? "":state;
    }

    public void setCity(String city) {
        this.city = (city==null)? "":city;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = (postal_code==null)? "":postal_code;
    }

    public void setDOB(String DOB) {
        this.DOB = (DOB==null)? "":DOB;
    }

    public void setPhone(String phone) {
        this.phone = (phone==null)? "":phone;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPhone() {
        return phone;
    }

    //User can do following functions
    //register
    public void register(){

    }
    // login
    public void login(){

    }
    //logout
    public void logout(){
        this.firstName = null;
        this.lastName = null;
        this.password = null;
        this.email_Id = null;
    }
    //view profile
    public void viewProfile(){
        System.out.println(
                "Name :      "+firstName+" "+lastName+"\n"+
                        "User Name : "+""+"\n"+
                        "Email :     "+email_Id+"\n"+
                        "Address :   "+""+"\n"+
                        "Phone :     "+""+"\n"
        );
    }
    //edit profile
    public void editProfile(){
        System.out.println(
                "Edit Profile\n"+
                        "1- First Name  :"+firstName+"\n"+
                        "2- Last Name   :"+lastName+"\n"+
                        "3- Back/Done "
        );

    }
    //deactivate prpofile
    public void deactivateProfile(){
        //delete from database and logout
        Database.getInstance().deleteAccount(email_Id);
    }
}
