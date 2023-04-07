package lab3.readable;

import lab3.exception.InvalidNumOfPagesException;

import java.io.Serializable;

public interface Readable extends Serializable {
    String getTitle();
    int getNumOfPages();
    void setTitle(String title);
    void setNumOfPages(int numOfPages) throws InvalidNumOfPagesException;
    double calculateAveragePages();
    String[] authors();
    void setAuthors(String[] authors);

    int numOfAuthors();
}
