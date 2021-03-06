package se.lexicon.simon.data;

import se.lexicon.simon.db.MySQLConnection;
import se.lexicon.simon.model.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class BookDAOIMPL implements BookDAO {

    public Book persist(Book book) {

        String createSQL = "INSERT INTO book (isbn, title, max_loan_days) VALUES (?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowsAffected = 0;
        Book createdBook = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            LocalDate time = LocalDate.now();

            preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getMaxLoanDays());


            rowsAffected = preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                book.setId(resultSet.getInt(1));
            }

        }
        catch (SQLIntegrityConstraintViolationException exception){
            System.out.println("Can Not Create: Violation of Constraint");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        return book;
    }

    @Override
    public Collection<Book> findAll() {

        //Preparing
        Collection<Book> booksFound = new ArrayList<>();

        String findAll = "SELECT * FROM book";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            //Establish Connection with Database.
            connection = MySQLConnection.getInstance().getConnection();

            // Prepare request statement to Database.
            preparedStatement = connection.prepareStatement(findAll);

            //Execute request statement to the Database. (Commit)
            // Gets the Result as a set.
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

               booksFound.add(
                       new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("max_loan_days")
                        )
               );

            }

        } catch (SQLException exception){
            exception.printStackTrace();
        }

        return booksFound;
    }


    @Override
    public Book findById(int bookId) {
        Book bookFound = null;
        String findById = "SELECT * FROM book WHERE book_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, bookId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                bookFound = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("max_loan_days")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return bookFound;
    }

    @Override
    public Collection<Book> findByTitle(String bookTitle) {
        Collection<Book> booksFound = new ArrayList<>();

        String findByTitle = "SELECT * FROM book WHERE title LIKE ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByTitle);
            // ex. Narnia%
            preparedStatement.setString(1, bookTitle + "%");

            resultSet = preparedStatement.executeQuery();

           while(resultSet.next()){
               booksFound.add(
                       new Book(
                               resultSet.getInt("book_id"),
                               resultSet.getString("isbn"),
                               resultSet.getString("title"),
                               resultSet.getInt("max_loan_days")
                       )
               );
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksFound;
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book bookFound = null;
        String findByIsbn = "SELECT * FROM book WHERE isbn = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByIsbn);
            preparedStatement.setString(1, isbn);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                bookFound = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getInt("max_loan_days")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return bookFound;
    }

    @Override
    public Book update(Book book) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        Book updatedBook = null;

        try{
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE book SET isbn = ?, title = ?, max_loan_days = ? WHERE book_id = ?");
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getMaxLoanDays());
            preparedStatement.setInt(4, book.getId());

             rowsAffected = preparedStatement.executeUpdate();

             preparedStatement.close();

//             preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE book_id = ?");
//             preparedStatement.setInt(1, book.getId());
//             ResultSet resultSet = preparedStatement.executeQuery();
//
//             if (resultSet.next()){
//                 updatedBook = new Book(
//                         resultSet.getInt("book_id"),
//                         resultSet.getString("isbn"),
//                         resultSet.getString("title"),
//                         resultSet.getInt("max_loan_days")
//                 );
//             }

        } catch (SQLException ex){
            ex.printStackTrace();
        }


        if (rowsAffected >= 1){
            return book;
        }else {
            return null;
        }
    }


    @Override
    public boolean delete(int bookId) {
        String deleteById = "DELETE FROM book WHERE book_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean wasDeleted = false;

        try {
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, bookId);

//           if (preparedStatement.executeUpdate() >= 1){
//               wasDeleted = true;
//           }else {
//               wasDeleted = false;
//           }

            //Ternary Operator
//           wasDeleted = preparedStatement.executeUpdate() >= 1 ? true : false;

            wasDeleted = preparedStatement.executeUpdate() >= 1;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wasDeleted;
    }

    @Override
    public boolean clear() {

        String clearBooksSQL = "DELETE FROM book";
        String findAll = "SELECT * FROM book";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean empty = false;


        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(clearBooksSQL);
                empty = preparedStatement.executeUpdate() >= 1;
            }else{
                empty = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empty;
    }
}
