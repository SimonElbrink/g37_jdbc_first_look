package se.lexicon.simon.data;

import se.lexicon.simon.db.MySQLConnection;

import java.sql.*;

public class AppUserDAO {

    public static void printOutMatchingAppUsers(String username){
        String findByName = "SELECT * FROM app_user WHERE username = ?";

        try{
            Connection connection = MySQLConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findByName);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("USERNAME: " + resultSet.getString("username"));
                System.out.println("REGISTRATION DATE: " + resultSet.getString("reg_date"));
                System.out.println();
            }


        } catch (SQLException ex){
            ex.printStackTrace();
        }


    }

    public static void findAndPrintAll(){
        try{
            Connection connection = MySQLConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            String findAll = "SELECT * FROM app_user";

            ResultSet resultSet = statement.executeQuery(findAll);

            while (resultSet.next()){
                System.out.println("ID: " + resultSet.getString(1));
                System.out.println("USERNAME: " + resultSet.getString("username"));
                System.out.println("REGISTRATION DATE: " + resultSet.getString("reg_date"));
                System.out.println();
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}
