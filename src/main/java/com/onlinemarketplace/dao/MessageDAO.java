package com.onlinemarketplace.dao;

import com.onlinemarketplace.database.Database;
import com.onlinemarketplace.model.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class MessageDAO {
    //make it in new file later
    public static boolean insertMsgInTable(int ad, int sender,int rec, String msg) throws ParseException {
        System.out.println(sender+ " msg saving");

        Database.getInstance().startDB_Connection();
        try {
            String sql = "INSERT INTO "+Database.getInstance().databaseName+".message (sender, receiver, ad, message) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = Database.getInstance().getConn().prepareStatement(sql);
            preparedStatement.setString(1,Integer.toString(sender));
            preparedStatement.setString(2,Integer.toString(rec));
            preparedStatement.setString(3,Integer.toString(ad));
            preparedStatement.setString(4,msg);
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e)        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        Database.getInstance().closeDB_Connection();
        return false;
    }
    public  static int getMessage(int userID, Message []message){
        boolean retValue = false;
        int i =0;
        Database.getInstance().startDB_Connection();
        String sqlQuery =  "SELECT * FROM "+Database.getInstance().databaseName+".message WHERE receiver = "+userID+"";
        try {
            Statement stmt = Database.getInstance().getConn().createStatement();
            ResultSet resultSet =  stmt.executeQuery(sqlQuery);

            while (resultSet.next()) {
                retValue = true;
                message[i] = new Message();
                message[i].setSender(Integer.parseInt(resultSet.getString("sender")));
                message[i].setRecv(Integer.parseInt(resultSet.getString("receiver")));
                message[i].setAd(Integer.parseInt(resultSet.getString("ad")));
                message[i].setMessage(resultSet.getString("message"));
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

}
