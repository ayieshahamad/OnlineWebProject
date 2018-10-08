package com.onlinemarketplace.dao;
import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.User;
import com.onlinemarketplace.util.BCrypt;

import javax.xml.crypto.Data;
import java.sql.*;

public class UserDAO {
    public static boolean checkIfEmailIdExist(String emailID){
        Database.getInstance().startDB_Connection();
        boolean check = false;
        String sqlQuery = "SELECT EmailID FROM "+Database.getInstance().databaseName+".UserProfile WHERE EmailID = '"+emailID+"'";
        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String email = resultSet.getString("EmailID");
                System.out.println(email);
                check = true;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
        return check;
    }
    public  static boolean getUserProfile(int userID, User user){
        boolean retValue = false;
        Database.getInstance().startDB_Connection();
        String sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".UserProfile WHERE UserID = "+userID+"";
        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                retValue = true;
                System.out.println("in" + resultSet.getString("FirstName"));

                user.setUserID(Integer.parseInt(resultSet.getString("UserID")));
                user.setUserName(resultSet.getString("UserName"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmail_Id(resultSet.getString("EmailID"));

                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPostal_code(resultSet.getString("postalcode"));
                user.setDOB(resultSet.getString("DOB"));
                user.setPhone(resultSet.getString("phone"));
                user.setDateJoined(resultSet.getString("dateJoined"));

                if(resultSet.getString("VerificationStatus").equals("true"))
                    user.setVerify(true);
                else
                    user.setVerify(false);
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return retValue;
    }
    public static String getFirstName(int userID){
        String name = null;
        Database.getInstance().startDB_Connection();
        String sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".UserProfile WHERE UserID = "+userID+"";
        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                System.out.println("in" + resultSet.getString("FirstName"));
                name = resultSet.getString("FirstName");
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return name;
    }
    public static boolean login(String emailID, String password, User user){
        Database.getInstance().startDB_Connection();
        boolean check = false;

        String sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".UserProfile WHERE EmailID = '"+emailID+"' AND Password = '"+ password+"'";
        //System.out.println(sqlQuery);
        //System.out.println(password);
        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                System.out.println("in" + resultSet.getString("FirstName"));

                user.setUser(resultSet.getString("FirstName"),resultSet.getString("LastName"),resultSet.getString("EmailID"),null );
                user.setUserID(Integer.parseInt(resultSet.getString("UserID")));
                if(resultSet.getString("VerificationStatus").equals("true"))
                    user.setVerify(true);
                else
                    user.setVerify(false);
                check = true;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
        return check;
    }
    public static boolean verifyEmailHash(String user_id, String hash)  {
        Database.getInstance().startDB_Connection();
        boolean verified = false;
        try {
            String sql = "select * from "+Database.getInstance().databaseName+".UserProfile where UserID = ? and Verification_HashCode = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, hash);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("here"+user_id+" "+hash);
           // if (resultSet != null) {
                while (resultSet.next()) {
                    verified = true;
                    System.out.println("true");
                }
           // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
        return verified;
    }
    public static boolean verifyEmailHash_forgotPassword(String user_id, String hash)  {
        Database.getInstance().startDB_Connection();
        boolean verified = false;
        try {
            String sql = "select * from "+Database.getInstance().databaseName+".UserProfile where UserID = ? and Verification_Password_HashCode = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, hash);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("here "+user_id+" "+hash);
            // if (resultSet != null) {
            while (resultSet.next()) {
                verified = true;
                System.out.println("true");
            }
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
        return verified;
    }
    public static void updateVerificationStaus(String userID, String verificationStatus) {
        Database.getInstance().startDB_Connection();
        try {
            String sql = "update "+Database.getInstance().databaseName+".UserProfile set VerificationStatus = ? where USERID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,verificationStatus);
            preparedStatement.setString(2,userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
    }
    public static void updateVerification_HashCode(String userID, String verficationHashCode) {
        Database.getInstance().startDB_Connection();
        try {
            String sql = "update "+Database.getInstance().databaseName+".UserProfile set Verification_HashCode = ? where USERID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,verficationHashCode);
            preparedStatement.setString(2,userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
    }
    public static void updateVerification_Password_HashCode(String userID, String verficationHashCode) {
        Database.getInstance().startDB_Connection();
        try {
            String sql = "update "+Database.getInstance().databaseName+".UserProfile set Verification_Password_HashCode = ? where USERID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,verficationHashCode);
            preparedStatement.setString(2,userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
    }
    public static void updatePassword(String userID, String password) {
        Database.getInstance().startDB_Connection();
        try {
            String sql = "update "+Database.getInstance().databaseName+".UserProfile set password = ? where USERID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,BCrypt.hashpw(password,BCrypt.SALT));
            preparedStatement.setString(2,userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
    }

}
