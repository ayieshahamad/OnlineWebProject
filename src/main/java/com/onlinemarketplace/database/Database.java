package com.onlinemarketplace.database;

import com.onlinemarketplace.model.User;
import com.onlinemarketplace.util.BCrypt;

import java.sql.*;
import java.util.Date;

public class Database {
    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Ayesha1234?";
    public static final String databaseName = "ONLINE_MARKET_PLACE";

    private Connection conn ;
    //private Statement stmt ;
    private boolean databaseExist;

    public Database(){
        conn = null;
        //stmt = null;
        databaseExist = false;
        //for now
        createDatabase();
    }

    public Connection getConn() {
        return conn;
    }

    public boolean startDB_Connection(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.cj.jdbc.Driver//com.mysql.jdbc.Driver (previous)
            //STEP 3: Open a connection
            System.out.println("Connecting to Database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);/////////////////////////connection  nahi bana
            //stmt = conn.createStatement();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return  false;
    }
    public boolean closeDB_Connection(){
        /*try{
            if(stmt!=null) {
                stmt.close();
                stmt = null;
            }
        }catch(SQLException se2){
        }// nothing we can do
*/
        try{
            if(conn!=null) {
                conn.close();
                conn = null;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
        return false;
    }
    //im not calling this fuction
    public boolean createDatabase(){
        startDB_Connection();
        try{
            //STEP 4: Execute a query
            System.out.println("Creating Database if it does not exist...");
            //stmt = conn.createStatement();
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            //iterate each catalog in the ResultSet
            while (resultSet.next()) {
                // Get the Database name, which is at position 1
                //System.out.println(databaseName);
                if(resultSet.getString(1).equalsIgnoreCase(databaseName) ){
                    databaseExist = true;
                    System.out.println("exists");
                }
            }
            resultSet.close();

            //if Database doesnot exist, create a new one
            if(databaseExist == false){

                System.out.println("Creating Database...");
                //stmt = conn.createStatement();
                String sql = "CREATE DATABASE "+databaseName;
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Database created successfully...");
                //creating user table
                sql = "CREATE TABLE "+databaseName+".UserProfile\n" +
                        "(\n" +
                        "UserID int PRIMARY KEY AUTO_INCREMENT,\n"+
                        "EmailID varchar(50) NOT NULL UNIQUE,\n" +
                        "Password varchar(100) NOT NULL,\n" +
                        "Username varchar(50),\n" +
                        "FirstName varchar(50) NOT NULL,\n" +
                        "LastName varchar(50) NOT NULL,\n" +
                        "Address varchar(100),\n" +
                        "City varchar(50),\n" +
                        "State varchar(50),\n" +
                        "Country varchar(50),\n" +
                        "PostalCode varchar(50),\n" +
                        "DOB varchar(50),\n" +
                        "Phone varchar(50),\n" +
                        "VerificationStatus varchar(50) DEFAULT 'false',\n" +
                        "Verification_HashCode varchar(100)," +
                        "Verification_Password_HashCode varchar(100)," +
                        "DateJoined varchar(15)" +
                        ")";
                stmt.executeUpdate(sql);
                //create product table
                sql = "CREATE TABLE "+databaseName+".Product\n" +
                        "(\n"+
                        "UserID int,\n" +
                        "ProductID int PRIMARY KEY  AUTO_INCREMENT,\n" +
                        "Name varchar(50) NOT NULL,\n" +
                        "Type varchar(50),\n" +
                        "Price double(100,2) NOT NULL,\n" +
                        "Description varchar(500) NOT NULL,\n" +
                        "DiliveryOption varchar(50) DEFAULT 'Pickup',\n" +
                        "Available varchar(50),\n" +
                        "Payment varchar(50) DEFAULT 'cash',\n" +
                        "DatePosted DATE," +
                        "Location varchar(50),"+
                        "Image1 varchar(50),\n" +
                        "Image2 varchar(50),\n" +
                        "Image3 varchar(50),\n" +
                        "Image4 varchar(50),\n" +
                        "Image5 varchar(50),\n" +
                        "FOREIGN KEY (UserID) REFERENCES UserProfile(UserID)" +
                        ")";
                stmt.executeUpdate(sql);
                //create saved ads
                sql = "CREATE TABLE "+databaseName+".SavedAds\n" +
                        "(\n"+
                        "UserID INT ,\n" +
                        "ProductID INT ," +
                        "FOREIGN KEY (UserID) REFERENCES UserProfile(UserID)" +
                        ")";
                stmt.executeUpdate(sql);
                if(stmt!=null) {
                    stmt.close();
                    stmt = null;
                }
                closeDB_Connection();
            }
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateUserProfile(User user){
        startDB_Connection();
        try {
            String sql = "UPDATE "+databaseName+".UserProfile " +
                    "SET FirstName = ?, LastName = ? "+
                    "WHERE EmailID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail_Id());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB_Connection();
        return true;
    }

    public boolean saveUserProfile_register(User user){
        startDB_Connection();
        try {
            //Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            String sql = "INSERT INTO "+databaseName+".UserProfile (FirstName, LastName, EmailID, Password, Verification_HashCode, DateJoined,Verification_Password_HashCode) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail_Id());
            preparedStatement.setString(4,BCrypt.hashpw(user.getPassword(),BCrypt.SALT));
            preparedStatement.setString(5,user.getHashCode());
            preparedStatement.setString(6,user.getDateJoined());
            preparedStatement.setString(7,"");
            preparedStatement.execute();
            preparedStatement.close();
            //BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
            //System.out.println(sql);
        }catch (Exception e)        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        closeDB_Connection();
        return false;
    }

    public int getUseID(String emailID)
    {
        startDB_Connection();
        int id = -1;
        String sqlQuery =  "SELECT UserID FROM "+databaseName+".UserProfile WHERE EmailID = '"+emailID+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                //System.out.println("in" + resultSet.getString("FirstName"));
                //user = new User();
                id = resultSet.getInt("UserID");
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB_Connection();
        return id;
    }
    public boolean login(String emailID, String password, User user){
        startDB_Connection();
        boolean check = false;

        String sqlQuery =  "SELECT * FROM "+databaseName+".UserProfile WHERE EmailID = '"+emailID+"' AND Password = '"+ password+"'";
        //System.out.println(sqlQuery);
        System.out.println(password);
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                System.out.println("in" + resultSet.getString("FirstName"));
                //user = new User();
                user.setUser(resultSet.getString("FirstName"),resultSet.getString("LastName"),resultSet.getString("EmailID"),null );
                check = true;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB_Connection();
        return check;
    }

    public boolean deleteAccount(String email){
        startDB_Connection();
        try {
            String sql = "DELETE FROM "+databaseName+".UserProfile " +
                    "WHERE EmailID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeDB_Connection();
        return true;
    }
}
