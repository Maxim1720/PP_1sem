package lab5.readable;

import lab4.readable.ReadableIO;

public class ReadableReader {

    private final ReadableIO readableIO;

    public ReadableReader(ReadableIO readableIO){
        this.readableIO = readableIO;
    }

    public void read(){
        for(int i = 0; i < readableIO.authors().length; i++){
            System.out.println("Read: "+ readableIO.authors()[i] + " from position " + i);
        }
    }

}
