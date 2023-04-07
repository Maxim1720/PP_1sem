package lab4.readable;

import lab3.readable.Readable;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public interface ReadableIO extends Readable {
    void output(OutputStream out);
    void write(Writer out);
}
