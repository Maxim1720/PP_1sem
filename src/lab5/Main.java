package lab5;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab4.readable.MagazineIO;
import lab4.readable.ReadableIO;
import lab5.readable.IOSyncUtils;
import lab5.runnable.RunnableReader;
import lab5.runnable.RunnableWriter;
import lab5.runnable.Synchronizer;
import lab5.thread.ReadableReaderThread;
import lab5.thread.ReadableWriterThread;

public class Main {

    public static void main(String[] args) throws InvalidNumOfPagesException, InterruptedException {
        System.out.println("\t\tTask 1");
        task1();
        System.out.println("\t\tTask 2");
        task2();
        System.out.println("\t\tTask 3");
        task3();

    }
    public static void task1() throws InvalidNumOfPagesException, InterruptedException {

        String[] authors = new String[10];

        ReadableIO readableIO = new BookIO("Zoloto", 12, authors);

        ReadableWriterThread readableWriterThread = new ReadableWriterThread(readableIO);
        ReadableReaderThread readableReaderThread = new ReadableReaderThread(readableIO);
        readableWriterThread.start();
        readableReaderThread.start();

        readableWriterThread.join();
        readableReaderThread.join();
    }

    public static void task2() throws InvalidNumOfPagesException, InterruptedException {
        String[] authors = new String[10];
        ReadableIO readableIO = new MagazineIO("Magazine", 122, authors);
        syncWriteRead(readableIO);
    }

    public static void task3() throws InvalidNumOfPagesException, InterruptedException {
        String[] authors = new String[10];
        ReadableIO readableIO = IOSyncUtils.synchronizedReadable(
                new MagazineIO("Magazine",445, authors)
        );
        syncWriteRead(readableIO);
    }

    private static void syncWriteRead(ReadableIO readableIO) throws InterruptedException {
        Synchronizer<ReadableIO> synchronizer = new Synchronizer<>();

        Thread writeThread = new Thread(new RunnableWriter(synchronizer,readableIO));
        Thread readThread = new Thread(new RunnableReader(synchronizer, readableIO));

        writeThread.start();
        readThread.start();

        writeThread.join();
        readThread.join();
    }
}
