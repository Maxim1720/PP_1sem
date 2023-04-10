package lab6;

import lab6.iterable.ReadableIterable;
import lab6.readable.factory.BookFactory;
import lab6.utils.IOUtils;

public class Main {
    public static void main(String[] args) {
        // Set the readable factory to create instances of books
        IOUtils.setReadableFactory(new BookFactory());

        // Create a book with some authors
        String[] authors = {"Author 1", "Author 2"};
        ReadableIterable book = IOUtils.createInstance("Book Title", 200, authors);

        // Print the book title and its authors
        System.out.println("Book title: " + book.getTitle());
        System.out.println("Authors: ");
        for (String author : book) {
            System.out.println("- " + author);
        }

        // Try to modify the book's title (should throw an exception)
        try {
            book.setTitle("New Title");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify the title of this book.");
        }

        // Try to add a new author to the book (should throw an exception)
        try {
            book.setAuthors(new String[] {"Author 3"});
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot add authors to this book.");
        }

        // Create a magazine with no authors
        ReadableIterable magazine = IOUtils.createInstance("Magazine Title", 50, new String[0]);

        // Print the magazine title and its authors
        System.out.println("Magazine title: " + magazine.getTitle());
        System.out.println("Authors: ");
        for (String author : magazine) {
            System.out.println("- " + author);
        }

        // Try to calculate the average number of pages per author (should return 0)
        System.out.println("Average pages per author: " + magazine.calculateAveragePages());

        // Create an unmodifiable version of the magazine
        ReadableIterable unmodifiableMagazine = IOUtils.unmodifiableReadable(magazine);

        // Try to modify the unmodifiable magazine's title (should throw an exception)
        try {
            unmodifiableMagazine.setTitle("New Title");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify the title of this magazine.");
        }

        // Try to add a new author to the unmodifiable magazine (should throw an exception)
        try {
            unmodifiableMagazine.setAuthors(new String[] {"Author 1"});
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot add authors to this magazine.");
        }
    }
}

