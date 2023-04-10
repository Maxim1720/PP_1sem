package lab6.readable.factory;

import lab3.exception.InvalidNumOfPagesException;
import lab6.iterable.ReadableIterable;
import lab6.readable.BookIterable;

public class BookFactory implements ReadableFactory{

    @Override
    public <R extends ReadableIterable> R createInstance() {
        return (R) new BookIterable();
    }

    @Override
    public <R extends ReadableIterable> R createInstance(String title, int numOfPages, String[] authors) {
        try {
            return (R) new BookIterable(title,numOfPages,authors);
        } catch (InvalidNumOfPagesException e) {
            throw new RuntimeException(e);
        }
    }


}
