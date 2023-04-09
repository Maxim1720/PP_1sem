package lab5.thread;

import lab3.readable.Readable;
import lab4.readable.ReadableIO;
import lab5.readable.ReadableWriter;
import lab5.runnable.Synchronizer;

import java.util.Random;

public class ReadableWriterThread extends Thread{

    private final ReadableIO readableIO;
    public ReadableWriterThread(ReadableIO readableIO){
        this.readableIO = readableIO;
    }


    @Override
    public void run() {
        for (int i=0;i<readableIO.authors().length;i++){
            readableIO.authors()[i] = "";
            for(int j=0;j<8;j++){
                readableIO.authors()[i] += (char)new Random().nextInt(65, 122);
            }
            System.out.format("Write: %s to position %d%n", readableIO.authors()[i], i);
        }
    }
}
