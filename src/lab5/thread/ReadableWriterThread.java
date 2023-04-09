package lab5.thread;

import lab4.readable.ReadableIO;
import lab5.readable.ReadableWriter;

public class ReadableWriterThread extends Thread{
    private final ReadableWriter readableWriter;
    public ReadableWriterThread(ReadableIO readableIO){
        readableWriter = new ReadableWriter(readableIO);
    }
    @Override
    public void run() {
        readableWriter.write();
    }
}
