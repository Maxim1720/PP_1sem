package lab5.thread;

import lab3.readable.Readable;
import lab4.readable.ReadableIO;
import lab5.readable.ReadableReader;

public class ReadableReaderThread extends Thread{
    private final ReadableReader readableReader;

    public ReadableReaderThread(ReadableIO readableIO){
        readableReader = new ReadableReader(readableIO);
    }
    @Override
    public void run() {
        readableReader.read();
    }
}
