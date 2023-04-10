package lab6.utils;

import lab5.readable.IOSyncUtils;
import lab6.iterable.ReadableIterable;
import lab6.readable.UnmodifialbeReadable;
import lab6.readable.factory.ReadableFactory;

public class IOUtils extends IOSyncUtils {

    private static ReadableFactory factory;

    public static ReadableIterable unmodifiableReadable(ReadableIterable readable){
        return new UnmodifialbeReadable(readable);
    }

    public static void setReadableFactory(ReadableFactory readableFactory){
        factory = readableFactory;
    }

    public static ReadableIterable createInstance(){
        return factory.createInstance();
    }
}
