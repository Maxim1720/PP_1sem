package lab4;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab4.readable.MagazineIO;
import lab4.readable.ReadableIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;

/**Этот код представляет программу для сохранения и загрузки объектов с информацией о книгах и журналах.
 Она использует сериализацию и бинарное кодирование для сохранения этих объектов в файлы.
 Программа позволяет пользователю выбрать, хочет ли он сохранить объект или нет, а также позволяет пользователю выбрать формат сохранения - сериализация, бинарное кодирование или текстовый формат.
 Пользователь также может выбрать тип объекта (книга или журнал) и ввести информацию о нем, такую ​​как заголовок, количество страниц и авторов.*/
public class Main {

    public static String FILES_PATH = "files";
    public static String FILE_NAME = "readables";
    public static String SER_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".ser";
    public static String BIN_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".bin";

    public static String TXT_FILE_PATH = FILES_PATH+"/"+FILE_NAME+".txt";

    public static void main(String[] args) throws IOException, InvalidNumOfPagesException {
        getFillInterface();
    }


    private static void getFillInterface() throws InvalidNumOfPagesException, IOException {
        while (!exit()) {
            outputReadablesFromFiles();
            System.out.println();
            ReadableIO readableIO = getFilledReadable();
            System.out.println("Readable: " + readableIO);
            save(readableIO);
        }
    }

    private static ReadableIO getFilledReadable() throws IOException, InvalidNumOfPagesException {
        String answer;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose readable object: 1)Book 2)Magazine");
        System.out.print("readable number: ");
        answer = bufferedReader.readLine();
        ReadableIO readableIO;
        switch (answer) {
            case "1" -> readableIO = new BookIO();
            case "2" -> readableIO = new MagazineIO();
            default -> throw new RuntimeException("Incorrect readable number");
        }
        System.out.print("Please, write title of this readable: ");
        readableIO.setTitle(bufferedReader.readLine());
        System.out.print("Please, write number of pages of this readable: ");
        readableIO.setNumOfPages(Integer.parseInt(bufferedReader.readLine()));

        System.out.print("authors count: ");
        String[] authors = new String[Integer.parseInt(bufferedReader.readLine())];

        System.out.println("Please, write authors of this readable: ");
        for (int i=0;i<authors.length;i++){
            authors[i] = bufferedReader.readLine();
        }
        readableIO.setAuthors(authors);
        return readableIO;
    }

    private static void save(ReadableIO readableIO) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Can I save this? : 1) yes 2) no");
        String answer = bufferedReader.readLine();

        if (answer.equals("1")){
            try {
                saveReadable(readableIO);
                System.out.println("Readable saved successfully");
            } catch (IOException e) {
                System.out.println("I can't save this readable :(");
            }
        }
    }
    private static void outputReadablesFromFiles() {
        try (FileInputStream fileInputStream = new FileInputStream(SER_FILE_PATH)) {
            System.out.println("---SER---");
            outputReadables(getReadables(() -> {
                try {
                    return fileInputStream.available() > 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    return InputOutputUtils.deserialize(fileInputStream);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (FileInputStream fileInputStream = new FileInputStream(BIN_FILE_PATH)) {
            System.out.println("---BIN---");
            outputReadables(getReadables(() -> {
                try {
                    return fileInputStream.available() > 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    return InputOutputUtils.input(fileInputStream);
                } catch (InvalidNumOfPagesException | IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (FileReader fileReader = new FileReader(TXT_FILE_PATH)) {
            System.out.println("---TXT---");
            outputReadables(getReadables(() -> {
                try {
                    return fileReader.ready();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                try {
                    return InputOutputUtils.read(fileReader);
                } catch (InvalidNumOfPagesException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ReadableIO[] getReadables(Supplier<Boolean> availableRead, Supplier<ReadableIO> readableIOSupplier) throws IOException {
        ArrayList<ReadableIO> readableIOS = new ArrayList<>();
        while (availableRead.get()){
            readableIOS.add(readableIOSupplier.get());
        }
        return readableIOS.toArray(new ReadableIO[]{});
    }
    private static void outputReadables(ReadableIO[] readableIOS){
        for (ReadableIO r : readableIOS){
            System.out.println(r);
        }
    }

    private static void saveReadable(ReadableIO readableIO) throws IOException {
        System.out.println("how will save readable? : 1)serialize 2)binary 3) as text");
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();

        switch (c) {
            case 1 -> {
                FileOutputStream fileOutputStream = new FileOutputStream(SER_FILE_PATH, true);
                InputOutputUtils.serialize(readableIO, fileOutputStream);
                fileOutputStream.close();
            }
            case 2 -> {
                FileOutputStream fileOutputStream = new FileOutputStream(BIN_FILE_PATH, true);
                InputOutputUtils.output(readableIO, fileOutputStream);
                fileOutputStream.close();
            }
            case 3 -> {
                FileWriter fileWriter = new FileWriter(TXT_FILE_PATH, true);
                InputOutputUtils.write(readableIO, fileWriter);
                fileWriter.close();
            }
        }
    }

    private static boolean exit() {
        System.out.println("exit? :1)yes 2)no");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt() == 1;
    }

}
