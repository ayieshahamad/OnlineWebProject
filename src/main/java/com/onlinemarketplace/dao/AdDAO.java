package com.onlinemarketplace.dao;
import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.Ad;
import com.onlinemarketplace.util.BCrypt;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AdDAO {
    public static boolean postAd(Ad ad, int userID) throws ParseException {
        System.out.println(userID+ " adDAO");


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String str_date = formatter.format(new Date());
        //java.sql.Date sqlDate = ;

            Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(str_date);//new
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//new


        /*********************************************************************************/
        Database.getInstance().startDB_Connection();
        try {
            //Statement st = conn.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            String sql = "INSERT INTO "+Database.getInstance().databaseName+".Product (UserID, Name, Type, Price, Description, Available, DatePosted, Location, Image1, Image2) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,Integer.toString(userID));
            preparedStatement.setString(2,ad.getName());
            preparedStatement.setString(3,ad.getType());
            preparedStatement.setString(4,Double.toString(ad.getPrice()));
            preparedStatement.setString(5,ad.getDescription());
            if(ad.isAvailable())
                preparedStatement.setString(6,"true");
            else
                preparedStatement.setString(6,"false");

            preparedStatement.setString(7,sqlDate.toString());//ad.getDatePosted());
            preparedStatement.setString(8,ad.getLocation());//ad.getDatePosted());
            preparedStatement.setString(9,ad.getImageUrl());
            preparedStatement.setString(10,ad.getImageUrl2());
            preparedStatement.execute();
            preparedStatement.close();
            //BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
            //System.out.println(sql);
        }catch (Exception e)        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        Database.getInstance().closeDB_Connection();
        return false;
    }

    public static int getUserAds(int userID, Ad [] ad, int adID){
        boolean retValue = false;
        //String returnString = "";
        int i = 0;
        Database.getInstance().startDB_Connection();
        String sqlQuery = null;
        if(adID == 0)
            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product WHERE UserID = "+userID+"";
        else
            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product WHERE UserID = "+userID+" AND ProductID = "+ adID;

        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
                //System.out.println("in" + resultSet.getString("FirstName"));
                ad[i] = new Ad();
                ad[i].setProductID(Integer.parseInt(resultSet.getString("ProductID")));
                ad[i].setName(resultSet.getString("Name"));
                ad[i].setType(resultSet.getString("Type"));
                ad[i].setPrice(Double.parseDouble(resultSet.getString("Price")));
                //String newLine = StringEscapeUtils.unescapeJava(rs.getString("column_value"));
                ad[i].setDescription(resultSet.getString("Description"));

                //ad[i].set(resultSet.getString("DiliveryOption"));
                //ad[i].set(resultSet.getString("Payment"));
                ad[i].setDatePosted(resultSet.getString("DatePosted"));
                ad[i].setLocation(resultSet.getString("Location"));
                ad[i].setImageUrl(resultSet.getString("image1"));
                ad[i].setImageUrl2(resultSet.getString("image2"));

                if(resultSet.getString("Available").equals("true"))
                    ad[i].setAvailable(true);
                else
                    ad[i].setAvailable(false);

                i++;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return i;
    }

    public static boolean deleteAd(int userID, int adID){
        Database.getInstance().startDB_Connection();
        try {
            String sql = "DELETE FROM "+Database.getInstance().databaseName+".Product " +
                    "WHERE UserID = ? AND ProductID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,Integer.toString(userID));
            preparedStatement.setString(2,Integer.toString(adID));
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
        return true;
    }

    public static void updateAd(Ad ad,int userID,int adID) {
        Database.getInstance().startDB_Connection();
        try {
            String sql = "update "+Database.getInstance().databaseName+".Product set Name = ?, Type = ?, Location = ?, Price =?, Description=? where USERID = ? AND ProductID = ?";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);

            preparedStatement.setString(1,ad.getName());
            preparedStatement.setString(2,ad.getType());
            preparedStatement.setString(3,ad.getLocation());
            preparedStatement.setString(4,Double.toString(ad.getPrice()));
            preparedStatement.setString(5,ad.getDescription());
            //preparedStatement.setString(5,ad.getImageUrl());
            //preparedStatement.setString(6,ad.getImageUrl2());
            preparedStatement.setString(6,Integer.toString(userID));
            preparedStatement.setString(7,Integer.toString(adID));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();
    }

    public static int getAllUsersAds( Ad [] ad, int sort){
        boolean retValue = false;
        //String returnString = "";
        int i = 0;
        Database.getInstance().startDB_Connection();
        String sqlQuery = null;
        if(sort==0)
            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product ";
        else if(sort == 1)
            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product order by DatePosted DESC ";
        else if(sort == 2)
            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product order by price ASC";

        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
                //System.out.println("in" + resultSet.getString("FirstName"));
                ad[i] = new Ad();
                ad[i].setUserID(Integer.parseInt(resultSet.getString("UserID")));

                ad[i].setProductID(Integer.parseInt(resultSet.getString("ProductID")));
                ad[i].setName(resultSet.getString("Name"));
                ad[i].setType(resultSet.getString("Type"));
                ad[i].setPrice(Double.parseDouble(resultSet.getString("Price")));
                ad[i].setDescription(resultSet.getString("Description"));

                //ad[i].set(resultSet.getString("DiliveryOption"));
                //ad[i].set(resultSet.getString("Payment"));
                ad[i].setDatePosted(resultSet.getString("DatePosted"));
                ad[i].setImageUrl(resultSet.getString("image1"));
                ad[i].setImageUrl2(resultSet.getString("image2"));

                if(resultSet.getString("Available").equals("true"))
                    ad[i].setAvailable(true);
                else
                    ad[i].setAvailable(false);

                i++;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return i;
    }

    public static boolean saveAd(int userID,int adID){
        System.out.println(userID+ " saving ad");
        Database.getInstance().startDB_Connection();
        try {
            //Statement st = conn.createStatement();
            String sql = "INSERT INTO "+Database.getInstance().databaseName+".savedads (UserID, ProductID) "
                    + "VALUES (?,?)";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,Integer.toString(userID));
            preparedStatement.setString(2,Integer.toString(adID));
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e)        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        Database.getInstance().closeDB_Connection();
        return false;
    }

    public static boolean checkIfAdIsSavedAlready(int userID, int adID){
        boolean retValue = false;
        Database.getInstance().startDB_Connection();
        String sqlQuery = null;

            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".savedads WHERE UserID = "+userID+" AND ProductID = "+ adID;

        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return retValue;
    }

    public static int searchForAKeyWord(Ad[] ad,String keyword){
        boolean retValue = false;
        Database.getInstance().startDB_Connection();
        String sqlQuery = null;
        int i = 0;
        //SELECT * FROM suppliersWHERE supplier_name like '%bob%';
        sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".product WHERE Name like '%"+ keyword +"%' OR Description like '%"+keyword+"%'";

        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
                //System.out.println("in" + resultSet.getString("FirstName"));
                ad[i] = new Ad();
                ad[i].setUserID(Integer.parseInt(resultSet.getString("UserID")));

                ad[i].setProductID(Integer.parseInt(resultSet.getString("ProductID")));
                ad[i].setName(resultSet.getString("Name"));
                ad[i].setType(resultSet.getString("Type"));
                ad[i].setPrice(Double.parseDouble(resultSet.getString("Price")));
                ad[i].setDescription(resultSet.getString("Description"));

                //ad[i].set(resultSet.getString("DiliveryOption"));
                //ad[i].set(resultSet.getString("Payment"));
                ad[i].setDatePosted(resultSet.getString("DatePosted"));
                ad[i].setImageUrl(resultSet.getString("image1"));
                ad[i].setImageUrl2(resultSet.getString("image2"));

                if(resultSet.getString("Available").equals("true"))
                    ad[i].setAvailable(true);
                else
                    ad[i].setAvailable(false);

                i++;
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return i;
    }


    public static int getAdName(int adID, Ad ad,int userID){
        boolean retValue = false;
        //String returnString = "";
        int i = 0;
        Database.getInstance().startDB_Connection();
        String sqlQuery = null;

            sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".Product WHERE UserID = "+userID+" AND ProductID = "+ adID;

        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
                //System.out.println("in" + resultSet.getString("FirstName"));
                //ad[i] = new Ad();
                ad.setName(resultSet.getString("Name"));

            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.getInstance().closeDB_Connection();

        return i;
    }

}
