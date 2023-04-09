package lab5.runnable;

import lab4.readable.ReadableIO;

import java.util.Random;

public class RunnableWriter implements Runnable{

    private final Synchronizer<ReadableIO> synchronizer;
    private final ReadableIO readableIO;

    public RunnableWriter(Synchronizer<ReadableIO> synchronizer, ReadableIO readableIO){
        this.synchronizer = synchronizer;
        this.readableIO = readableIO;
    }

    @Override
    public void run() {
        for (int i=0;i<readableIO.authors().length;i++){
            //synchronizer.write();
            synchronizer.waitForWriteTurn();
            readableIO.authors()[i] = "";
            for(int j=0;j<10;j++){
                readableIO.authors()[i] += (char)new Random().nextInt(65, 122);
            }
            System.out.format("Write: %s to position %d%n", readableIO.authors()[i], i);
            //synchronizer.read();
            synchronizer.notifyWriteTurn();
        }
    }
}
