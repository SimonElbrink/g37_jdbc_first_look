package se.lexicon.simon;

import se.lexicon.simon.data.AppUserDAO;
import se.lexicon.simon.data.BookDAO;
import se.lexicon.simon.data.BookDAOIMPL;
import se.lexicon.simon.model.Book;

import java.util.Collection;


public class App
{
    public static void main( String[] args )
    {
//        AppUserDAO.findAndPrintAll();

//        AppUserDAO.printOutMatchingAppUsers("simonelbrink");

        BookDAO bookDao = new BookDAOIMPL();

//        Collection<Book> all = bookDao.findAll();
//
//        all.forEach(System.out::println);

//        System.out.println(bookDao.findById(90));
//        System.out.println(bookDao.findByIsbn("4213423nda"));

//        bookDao.findByTitle("Narnia").forEach(System.out::println);

//        Book book = new Book(46578, "3737dh", "Harry Potter 3", 15);
//        boolean wasCreated = bookDao.create(book);
//        System.out.println(wasCreated);

//        boolean wasDeleted = bookDao.delete(5);
//
//        System.out.println("wasDeleted = " + wasDeleted);

        System.out.println(bookDao.clear());


    }
}
