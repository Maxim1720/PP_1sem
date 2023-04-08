package lab4;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab4.readable.MagazineIO;
import lab4.readable.ReadableIO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class InputOutputUtils {

    // запись в байтовый поток
    public static <R extends ReadableIO> void output(R readableIO, OutputStream out) throws IOException {
        readableIO.output(out);
        out.write("\n".getBytes(StandardCharsets.UTF_8));
    }

    // чтение из байтового потока
    public static <R extends ReadableIO> R input(InputStream in) throws InvalidNumOfPagesException, IOException {
        String content = "";
        int c;
        do {
            c=in.read();
            content += (char) c;
        }
        while (c!='\n');
        StringReader stringReader = new StringReader(content);
        return read(stringReader);
    }

    // запись в символьный поток
    public static <R extends ReadableIO> void write(R readable, Writer out) throws IOException {
        readable.write(out);
        out.append('\n');
    }

    // чтение из символьного потока
    public static <R extends ReadableIO> R read(Reader in) throws InvalidNumOfPagesException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.wordChars(33,255);
        tokenizer.ordinaryChar(',');
        tokenizer.ordinaryChar('[');
        tokenizer.ordinaryChar(']');
        tokenizer.eolIsSignificant(true);
        try {
            tokenizer.nextToken();
            String type = tokenizer.sval;
            R readable = getReadable(type);

            tokenizer.nextToken();
            String val = "";
            if(tokenizer.ttype == StreamTokenizer.TT_WORD){
                val = tokenizer.sval;
            }
            else if(tokenizer.ttype == StreamTokenizer.TT_NUMBER){
                val = String.valueOf((int) tokenizer.nval);
            }
            readable.setTitle(val);

            tokenizer.nextToken();
            readable.setNumOfPages((int) tokenizer.nval);

            ArrayList<String> authors = new ArrayList<>();
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOL) {
                val= "";
                if(tokenizer.sval != null){
                    val = tokenizer.sval;
                }
                else if(tokenizer.ttype == StreamTokenizer.TT_NUMBER){
                    val = String.valueOf((int) tokenizer.nval);
                }
                if(!val.isEmpty()){
                    authors.add(val);
                }
            }
            readable.setAuthors(authors.toArray(new String[]{}));
            return readable;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("can't read token");
        }

    }

    private static <R extends ReadableIO> R getReadable(String typeName){
        switch (typeName){
            case "BookIO" -> {
                return (R)new BookIO();
            }
            case "MagazineIO" ->{
                return (R)new MagazineIO();
            }
            default -> throw new IllegalStateException("Unexpected value: " + typeName);
        }
    }

    // вывод сериализованных объектов
    public static <T extends ReadableIO> void serialize(T object, OutputStream out) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        objectOut.writeObject(object);
    }

    // ввод десериализованного объекта
    public static <R extends ReadableIO> R deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(in);
        R obj = (R) objectIn.readObject();
        return obj;
    }
}
