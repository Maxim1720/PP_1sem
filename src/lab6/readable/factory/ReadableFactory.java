package lab6.readable.factory;

import lab6.iterable.ReadableIterable;

public interface ReadableFactory {
    <R extends ReadableIterable> R createInstance();
    <R extends ReadableIterable> R createInstance(String title, int numOfPages, String[] authors);
}
