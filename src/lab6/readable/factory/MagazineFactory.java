package lab6.readable.factory;

import lab3.exception.InvalidNumOfPagesException;
import lab6.iterable.ReadableIterable;
import lab6.readable.MagazineIterable;

public class MagazineFactory implements ReadableFactory{
    @Override
    public <R extends ReadableIterable> R createInstance() {
        return (R) new MagazineIterable();
    }

    @Override
    public <R extends ReadableIterable> R createInstance(String title, int numOfPages, String[] authors) {
        try {
            return (R) new MagazineIterable(title,numOfPages,authors);
        } catch (InvalidNumOfPagesException e) {
            throw new RuntimeException(e);
        }
    }
}
