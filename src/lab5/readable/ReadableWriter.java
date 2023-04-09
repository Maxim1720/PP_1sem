package lab5.readable;

import lab3.readable.Readable;
import lab4.readable.ReadableIO;

import java.util.Random;

public class ReadableWriter {

    private final ReadableIO readableIO;

    public ReadableWriter(ReadableIO readableIO){
        this.readableIO = readableIO;
    }
    public void write(){
        int size = readableIO.authors().length;
        String[] authors = new String[size];
        for (int i=0;i<size;i++){
            authors[i] = "";
            for(int j=0;j<8;j++){
                authors[i] += (char)new Random().nextInt(65, 122);
            }
            readableIO.authors()[i] = authors[i];
            System.out.format("Write: %s to position %d%n", authors[i], i);
        }
        readableIO.setAuthors(authors);
    }


}
