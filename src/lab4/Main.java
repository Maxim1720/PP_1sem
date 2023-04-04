package lab4;

import lab3.exception.InvalidNumOfPagesException;
import lab4.readable.BookIO;
import lab4.readable.MagazineIO;
import lab4.readable.ReadableIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InvalidNumOfPagesException {
        ArrayList<ReadableIO> database = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ReadableIO readable = createReadableFromUserInput();
            database.add(readable);
        }
        System.out.println(database);
        // ...
    }

    public static ReadableIO createReadableFromUserInput() throws InvalidNumOfPagesException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the type of readable (book/magazine): ");
        String type = scanner.nextLine();
        System.out.print("Enter the title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the number of pages: ");
        int numOfPages = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of authors/editors: ");
        int numOfAuthors = Integer.parseInt(scanner.nextLine());
        String[] authors = new String[numOfAuthors];
        for (int i = 0; i < numOfAuthors; i++) {
            System.out.print("Enter the name of author/editor " + (i+1) + ": ");
            authors[i] = scanner.nextLine();
        }
        ReadableIO readable;
        if (type.equalsIgnoreCase("book")) {
            readable = new BookIO(title, numOfPages, authors);
        } else if (type.equalsIgnoreCase("magazine")) {
            readable = new MagazineIO(title, numOfPages, authors);
        } else {
            throw new IllegalArgumentException("Invalid readable type: " + type);
        }
        return readable;
    }

}
