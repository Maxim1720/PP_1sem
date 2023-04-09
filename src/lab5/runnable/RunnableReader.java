package lab5.runnable;

import lab4.readable.ReadableIO;

public class RunnableReader implements Runnable{

    private final Synchronizer<ReadableIO> synchronizer;

    private final ReadableIO readableIO;

    public RunnableReader(Synchronizer<ReadableIO> synchronizer, ReadableIO readableIO){
        this.synchronizer = synchronizer;
        this.readableIO = readableIO;
    }

    @Override
    public void run() {
        for(int i=0;i<readableIO.authors().length;i++){
            synchronizer.waitForReadTurn();
            System.out.println("Read: " + readableIO.authors()[i] + " from position " + i);
            synchronizer.notifyReadTurn();
        }
    }
}
