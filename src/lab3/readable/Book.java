package lab3.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.exception.NullAuthorsException;

import java.util.Arrays;
import java.util.Objects;

public class Book implements Readable {
    private String title;
    private int numOfPages;
    private String[] authors;

    public Book() {}

    public Book(String title, int numOfPages, String[] authors) throws InvalidNumOfPagesException {
        setTitle(title);
        setNumOfPages(numOfPages);
        setAuthors(authors);
    }

    public String getTitle() {
        return title;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public String[] authors() {
        return authors;
    }

    public String getAuthor(int index){
        return authors[index];
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfPages(int numOfPages) throws InvalidNumOfPagesException {
        if (numOfPages <= 0) {
            throw new InvalidNumOfPagesException("Number of pages must be positive");
        }
        this.numOfPages = numOfPages;
    }

    public void setAuthors(String[] authors) {
        if(authors == null){
            throw new NullAuthorsException("Authors can't be null");
        }
        this.authors = authors;
    }

    public double calculateAveragePages() {
        return numOfPages / (double)authors.length;
    }

    @Override
    public String toString() {
        return "Book: " + title + " (" + numOfPages + " pages) by " + Arrays.toString(authors);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Readable)) {
            return false;
        }
        Readable other = (Readable) obj;
        return title.equals(other.getTitle()) && numOfPages == other.getNumOfPages() && Arrays.equals(authors, other.authors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numOfPages, Arrays.hashCode(authors));
    }


}

