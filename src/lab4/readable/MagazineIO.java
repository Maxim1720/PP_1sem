package lab4.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.readable.Magazine;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;

public class MagazineIO extends Magazine implements ReadableIO {

    public MagazineIO(String title, int numOfPages, String[] editors) throws InvalidNumOfPagesException {
        super(title,numOfPages,editors);
    }

    @Override
    public void output(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("Magazine %s %d %s%n", getTitle(), getNumOfPages(), Arrays.toString(authors()));
        writer.flush();
    }

    @Override
    public void write(Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.printf("Magazine %s %d %s%n", getTitle(), getNumOfPages(), Arrays.toString(authors()));
        writer.flush();
    }
}
