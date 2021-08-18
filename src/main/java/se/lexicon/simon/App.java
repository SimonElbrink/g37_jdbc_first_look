package se.lexicon.simon;

import java.sql.*;

public class App
{
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";



    public static void main( String[] args )
    {

//        try{
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            Statement statement = connection.createStatement();
//
//            String findAll = "SELECT * FROM app_user";
//
//            ResultSet resultSet = statement.executeQuery(findAll);
//
//            while (resultSet.next()){
//                System.out.println("ID: " + resultSet.getString(1));
//                System.out.println("USERNAME: " + resultSet.getString("username"));
//                System.out.println("REGISTRATION DATE: " + resultSet.getString("reg_date"));
//                System.out.println();
//            }
//
//
//        } catch (SQLException ex){
//            ex.printStackTrace();
//        }

        printOutMatchingAppUsers("simonelbrink");


    }

    public static void printOutMatchingAppUsers(String username){
        // IntelliJ does not like the app_user, it can't find it. let's fix that in next commit.
        // This is not an SQL problem it is a IntelliJ problem.
        String findByName = "SELECT * FROM app_user WHERE username = ?";

        try{
            //The lecture told us Connections are "expensive" to create. let's have a look at that next.
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
}
