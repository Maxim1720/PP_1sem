package lab4;

import lab4.readable.ReadableIO;

import java.io.*;

public class InputOutputUtils {

    // запись в байтовый поток
    public static <R extends ReadableIO> void output(R readableIO, OutputStream out) throws IOException {
        readableIO.output(out);
    }

    // чтение из байтового потока
    public static <R extends ReadableIO> R input(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(in.readAllBytes()));
        return (R) objectIn.readObject();
    }

    // запись в символьный поток
    public static <R extends ReadableIO> void write(R readable, Writer out) throws IOException {
        readable.write(out);
    }

    // чтение из символьного потока
    public static <R extends ReadableIO> R read(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.eolIsSignificant(true);
        tokenizer.wordChars('_', '_');
        tokenizer.wordChars('$', '$');
        tokenizer.wordChars('.', '.');
        tokenizer.quoteChar('"');
        tokenizer.quoteChar('\'');
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        tokenizer.ordinaryChar('\\');
        tokenizer.ordinaryChar('.');
        tokenizer.parseNumbers();
        tokenizer.nextToken();

        // Создаем новый объект, используя конструктор без параметров
        R object = null;
        try {
            object = (R) Class.forName(tokenizer.sval).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Заполняем поля объекта из потока
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                try {
                    object.getClass().getField(tokenizer.sval).set(object, readValue(tokenizer));
                } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }

    private static Object readValue(StreamTokenizer tokenizer) throws IOException {
        switch (tokenizer.nextToken()) {
            case StreamTokenizer.TT_NUMBER -> {
                if (tokenizer.nval == (int) tokenizer.nval) {
                    return (int) tokenizer.nval;
                } else {
                    return tokenizer.nval;
                }
            }
            case StreamTokenizer.TT_WORD, '"', '\'' -> {
                return tokenizer.sval;
            }
            default -> {
                return null;
            }
        }
    }

    // вывод сериализованных объектов
    public static <T extends Serializable> void serialize(T object, OutputStream out) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        objectOut.writeObject(object);
    }

    // ввод десериализованного объекта
    public static <T extends Serializable> T deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(in);
        return (T) objectIn.readObject();
    }
}
