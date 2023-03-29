package lab3.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.exception.NullAuthorsException;

import java.util.Arrays;
import java.util.Objects;

public class Magazine implements Readable {
    private String title;
    private int numOfPages;
    private String[] editors;

    public Magazine() {}

    public Magazine(String title, int numOfPages, String[] editors) throws InvalidNumOfPagesException {
        setTitle(title);
        setNumOfPages(numOfPages);
        setAuthors(editors);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getNumOfPages() {
        return numOfPages;
    }

    @Override
    public String[] authors() {
        return editors;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setNumOfPages(int numOfPages) throws InvalidNumOfPagesException {
        if (numOfPages <= 0) {
            throw new InvalidNumOfPagesException("Number of pages must be positive");
        }
        this.numOfPages = numOfPages;
    }

    @Override
    public void setAuthors(String[] editors) {
        if(editors == null){
            throw new NullAuthorsException("Authors can't be null");
        }
        this.editors = editors;
    }

    @Override
    public int numOfAuthors() {
        return authors().length;
    }

    @Override
    public double calculateAveragePages() {
        return numOfPages / (double)editors.length;
    }

    @Override
    public String toString() {
        return "Magazine: " + title + " (" + numOfPages + " pages) edited by " + Arrays.toString(editors);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Readable)) {
            return false;
        }
        Readable r = (Readable) obj;
        return title.equals(r.getTitle()) && numOfPages == r.getNumOfPages() && Arrays.equals(editors, r.authors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numOfPages, Arrays.hashCode(authors()));
    }
}