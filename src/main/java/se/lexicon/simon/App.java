package se.lexicon.simon;

public class App
{
    public static void main( String[] args )
    {
        AppUserDAO.findAndPrintAll();

        AppUserDAO.printOutMatchingAppUsers("simonelbrink");


    }
}
