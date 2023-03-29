package lab3.readable;

import lab3.exception.InvalidNumOfPagesException;

public interface Readable {
    String getTitle();
    int getNumOfPages();
    void setTitle(String title);
    void setNumOfPages(int numOfPages) throws InvalidNumOfPagesException;
    double calculateAveragePages();
    String[] authors();
    void setAuthors(String[] authors);

    int numOfAuthors();
}
