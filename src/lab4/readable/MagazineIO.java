package lab4.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.readable.Magazine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class MagazineIO extends Magazine implements ReadableIO {

    public MagazineIO(){

    }

    public MagazineIO(String title, int numOfPages, String[] editors) throws InvalidNumOfPagesException {
        super(title,numOfPages,editors);
    }

    @Override
    public void output(OutputStream out) {
        try {
            out.write(this.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void write(Writer out) {
        try {
            out.write(toString());
        } catch (IOException e) {
            System.out.println("I can't write this book: " + this);
        }
    }
}
