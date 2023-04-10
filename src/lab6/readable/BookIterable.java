package lab6.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab6.iterable.AuthorsIterator;
import lab6.iterable.ReadableIterable;

import java.util.Iterator;

public class BookIterable extends BookIO implements ReadableIterable {

    public BookIterable(){

    }
    public BookIterable(String title, int numOfPages, String[] authors) throws InvalidNumOfPagesException {
        super(title,numOfPages,authors);
    }

    @Override
    public Iterator<String> iterator() {
        return new AuthorsIterator(authors());
    }
}
