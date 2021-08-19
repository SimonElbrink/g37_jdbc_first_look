package se.lexicon.simon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static final MySQLConnection instance = new MySQLConnection();

    //Constructor
    private MySQLConnection(){}

    public static MySQLConnection getInstance() {
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return connection;
    }









}
