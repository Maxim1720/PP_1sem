package lab4.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.readable.Book;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class BookIO extends Book implements ReadableIO {
    public BookIO(String title, int numOfPages, String[] authors) throws InvalidNumOfPagesException {
        super(title,numOfPages,authors);
    }

    @Override
    public void output(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("Book %s %d %s%n", getTitle(), getNumOfPages(), Arrays.toString(authors()));
        writer.flush();
    }

    @Override
    public void write(Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("Book %s %d %s%n", getTitle(), getNumOfPages(), Arrays.toString(authors()));
        writer.flush();
    }
}
