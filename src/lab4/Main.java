package lab4;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab4.readable.MagazineIO;
import lab4.readable.ReadableIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static String FILES_PATH = "files";
    public static String FILE_NAME = "readables";
    public static String SER_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".ser";
    public static String BIN_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".bin";

    public static String TXT_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidNumOfPagesException {

        getFillInterface();

        String filesPath = FILES_PATH;

        System.out.println("--------Readable---------");
        ReadableIO readableIO = new BookIO();
        readableIO.setAuthors(new String[]{"lo", "h"});
        readableIO.setNumOfPages(12);
        readableIO.setTitle("dwad");
        System.out.println(readableIO);

        System.out.println("-----------Output-Input-----------");
        FileOutputStream fileOutputStream = new FileOutputStream(filesPath+"/readables.bin", true);
        FileInputStream fileInputStream = new FileInputStream(filesPath+"/readables.bin");

        InputOutputUtils.output(readableIO, fileOutputStream);
        fileOutputStream.close();

        while (fileInputStream.available() > 0){
            ReadableIO readableIO1 = InputOutputUtils.input(fileInputStream);
            System.out.println(readableIO1);
        }
        fileInputStream.close();

        System.out.println("------------Write-Read-------------");
        FileWriter fileWriter = new FileWriter(filesPath+"/readables.txt", true);
        InputOutputUtils.write(readableIO, fileWriter);
        fileWriter.close();

        FileReader fileReader = new FileReader(filesPath+"/readables.txt");
        while (fileReader.ready()){
            ReadableIO r = InputOutputUtils.read(fileReader);
            System.out.println(r);
        }
        fileReader.close();

        System.out.println("------Serializable-Deserializable----------");
        FileOutputStream fileOutputStream1 = new FileOutputStream(filesPath+"/readables.ser", true);
        FileInputStream fileInputStream1 = new FileInputStream(filesPath+"/readables.ser");


        InputOutputUtils.serialize(readableIO, fileOutputStream1);
        fileOutputStream1.close();

        while (fileInputStream1.available()>0){
            System.out.println(InputOutputUtils.deserialize(fileInputStream1).toString());
        }
    }


    private static void getFillInterface() throws InvalidNumOfPagesException {

        int c = 0;
        Scanner scanner = new Scanner(System.in);
        while (c != '\n') {

            try {
                FileInputStream fileInputStream = new FileInputStream(BIN_FILE_PATH);
                System.out.println("-------Readables in bin file:-------");
                while (fileInputStream.available() > 0){
                    System.out.println(InputOutputUtils.input(fileInputStream).toString());
                }
                fileInputStream.close();
                System.out.println("-----------------------------------");
            } catch (FileNotFoundException e) {
                System.out.println("!!!!!file did not created!!!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Choose readable object: 1)Book 2)Magazine");
            System.out.print("readable number: ");
            c = scanner.nextInt();
            ReadableIO readableIO;
            switch (c) {
                case 1 -> readableIO = new BookIO();
                case 2 -> readableIO = new MagazineIO();
                default -> throw new RuntimeException("Incorrect readable number");
            }
            System.out.println("Please, write title of this readable: ");
            readableIO.setTitle(scanner.nextLine());
            System.out.print("Please, write number of pages of this readable: ");
            readableIO.setNumOfPages(scanner.nextInt());


            System.out.print("authors count: ");
            String[] authors = new String[scanner.nextInt()];

            System.out.println("Please, write authors of this readable: ");
            for (int i=0;i<authors.length;i++){
                authors[i] = scanner.next();
            }
            readableIO.setAuthors(authors);

            System.out.println("Readable: " + readableIO);
            System.out.println("Can I save this? : 1) yes 2) no");

            c = scanner.nextInt();
            switch (c){
                case 1:
                    try {
                        savingReadable(readableIO);
                        System.out.println("Readable saved successfully");
                    } catch (IOException e) {
                        System.out.println("I can't save this readable :(");
                    }
                case 2:
                    continue;
            }


            System.out.println("Want to exit? 1)yes 2)no");
            c = scanner.nextInt();
            switch (c){
                case 1 -> {
                    return;
                }
                case 2 ->{
                }
            }
        }

    }

    private static void savingReadable(ReadableIO readableIO) throws IOException {
        System.out.println("how will save readable? : 1)serialize 2)binary 3) as text");
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            case 1: InputOutputUtils.serialize(readableIO, new FileOutputStream(SER_FILE_PATH, true));
            case 2: InputOutputUtils.output(readableIO, new FileOutputStream(BIN_FILE_PATH, true));
            case 3: InputOutputUtils.write(readableIO, new FileWriter(TXT_FILE_PATH, true));
        }
    }

}
