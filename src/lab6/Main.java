package lab6;

import lab6.iterable.ReadableIterable;
import lab6.readable.factory.BookFactory;
import lab6.readable.factory.MagazineFactory;
import lab6.utils.IOUtils;

public class Main {
    public static void main(String[] args) {
        IOUtils.setReadableFactory(new BookFactory());
        printAuthors(IOUtils.createInstance());
        IOUtils.setReadableFactory(new MagazineFactory());
        printAuthors(IOUtils.createInstance());



        ReadableIterable readableIterable = IOUtils.unmodifiableReadable(IOUtils.createInstance());
        try {
            readableIterable.setAuthors(new String[]{});
        }catch (UnsupportedOperationException e){
            System.out.println("You can't modify this object");
        }

    }


    private static void printAuthors(ReadableIterable readableIterable){
        readableIterable.setAuthors(new String[]{"a1", "b2"});
        for (String s: readableIterable){
            System.out.println(s);
        }
    }
}
