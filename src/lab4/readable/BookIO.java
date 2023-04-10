package lab4.readable;

import lab3.exception.InvalidNumOfPagesException;
import lab3.readable.Book;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**Код написан на языке Java и представляет собой класс BookIO, который расширяет класс Book и реализует интерфейс ReadableIO. Класс BookIO содержит конструкторы и методы для ввода и вывода данных о книге из различных потоков данных.

 В методе output класса BookIO данные о книге выводятся в заданный выходной поток OutputStream. В методе write данные о книге записываются в заданный Writer.

 Если в процессе выполнения метода write происходит ошибка, то в консоль выводится сообщение "I can't write this book: " и информация о книге, которую не удалось записать.*/

public class BookIO extends Book implements ReadableIO {

    public BookIO(){

    }

    public BookIO(String title, int numOfPages, String[] authors) throws InvalidNumOfPagesException {
        super(title,numOfPages,authors);
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
