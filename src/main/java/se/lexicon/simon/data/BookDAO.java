package se.lexicon.simon.data;

import se.lexicon.simon.model.Book;

import java.util.Collection;
//Contract
//DAO - DATA ACCESS OBJECT
public interface BookDAO {

    //CRUD

    //Create
    Book persist(Book book);

    //Read's
    Collection<Book> findAll();

    Book findById(int bookId);

    Collection<Book> findByTitle(String bookTitle);

    Book findByIsbn(String isbn);

    //Update
    Book update(Book book);

    //Delete
    boolean delete(int bookId);

    //Bonus
    boolean clear();






}
