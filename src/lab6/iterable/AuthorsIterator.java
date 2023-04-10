package lab6.iterable;

import java.util.Iterator;

public class AuthorsIterator implements Iterator<String> {

    private final String[] authors;
    private int nextIndex;

    public AuthorsIterator(String[] authors){
        this.authors = authors;
        nextIndex = 0;
    }


    @Override
    public boolean hasNext() {
        return authors.length > nextIndex;
    }

    @Override
    public String next() {
        return authors[nextIndex++];
    }
}
