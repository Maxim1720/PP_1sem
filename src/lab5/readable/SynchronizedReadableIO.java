package lab5.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.ReadableIO;

import java.io.*;

public class SynchronizedReadableIO implements ReadableIO, Serializable {
    private final ReadableIO original;

    public SynchronizedReadableIO(ReadableIO original) {
        this.original = original;
    }

    @Override
    public synchronized String getTitle() {
        return original.getTitle();
    }

    @Override
    public synchronized int getNumOfPages() {
        return original.getNumOfPages();
    }

    @Override
    public synchronized void setTitle(String title) {
        original.setTitle(title);
    }

    @Override
    public synchronized void setNumOfPages(int numOfPages) throws InvalidNumOfPagesException {
        original.setNumOfPages(numOfPages);
    }

    @Override
    public synchronized double calculateAveragePages() {
        return original.calculateAveragePages();
    }

    @Override
    public synchronized String[] authors() {
        return original.authors();
    }

    @Override
    public synchronized void setAuthors(String[] authors) {
        original.setAuthors(authors);
    }

    @Override
    public synchronized int numOfAuthors() {
        return original.numOfAuthors();
    }

    @Override
    public synchronized void output(OutputStream out) {
        original.output(out);
    }

    @Override
    public synchronized void write(Writer out) {
        original.write(out);
    }
}
