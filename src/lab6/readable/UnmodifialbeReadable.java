package lab6.readable;

import lab6.iterable.AuthorsIterator;
import lab6.iterable.ReadableIterable;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class UnmodifialbeReadable implements ReadableIterable {

    private final ReadableIterable readable;

    public UnmodifialbeReadable(ReadableIterable readableIterable){
        this.readable = readableIterable;
    }

    @Override
    public Iterator<String> iterator() {
        return new AuthorsIterator(readable.authors());
    }

    @Override
    public String getTitle() {
        return readable.getTitle();
    }

    @Override
    public int getNumOfPages() {
        return readable.getNumOfPages();
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setNumOfPages(int numOfPages) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double calculateAveragePages() {
        return readable.calculateAveragePages();
    }

    @Override
    public String[] authors() {
        return readable.authors();
    }

    @Override
    public void setAuthors(String[] authors) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int numOfAuthors() {
        return readable.numOfAuthors();
    }

    @Override
    public void output(OutputStream out) {
        readable.output(out);
    }

    @Override
    public void write(Writer out) {
        readable.write(out);
    }
}
