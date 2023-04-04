package lab3;

import lab3.exception.InvalidNumOfPagesException;
import lab3.exception.NullAuthorsException;
import lab3.readable.Book;
import lab3.readable.Magazine;
import lab3.readable.Readable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InvalidNumOfPagesException {
        ArrayList<Readable> base = new ArrayList<>();
        base.add(new Book("Java Programming", 500, new String[]{"John Smith"}));
        base.add(new Magazine("National Geographic", 100, new String[]{"Jane Doe"}));
        base.add(new Magazine("Time", 50, new String[]{"Joe Bloggs"}));
        base.add(new Book("Data Structures", 400, new String[]{"Mary Johnson", "David Lee"}));


        int authorsCount = 0;
        for(Readable r : base){
            for (String s: r.authors()){
                if(s.length() == 5){
                    authorsCount++;
                }
            }
        }
        System.out.println("Authors size equals 5 count: "+authorsCount);

        try {
            Book wrongBook = new Book("1984", 194, null);
            base.add(wrongBook);
        }
        catch (NullAuthorsException e){
            System.out.println(e.getMessage());
            try {
                Magazine magazine = new Magazine("wrong magazine", -1, new String[]{"test1", "test2"});
                base.add(magazine);
            }
            catch (InvalidNumOfPagesException ex){
                System.out.println(ex.getMessage());
            }
        }

        // Display complete information about all array objects
        for (Readable obj : base) {
            System.out.println(obj.toString());
        }

        // Find objects in the array whose functional method returns the same result
        ArrayList<Readable> sameResult = new ArrayList<>();
        for (int i = 0; i < base.size() - 1; i++) {
            for (int j = i + 1; j < base.size(); j++) {
                if (base.get(i).calculateAveragePages() == base.get(j).calculateAveragePages()) {
                    sameResult.add(base.get(i));
                    sameResult.add(base.get(j));
                }
            }
        }
        System.out.println("Objects with the same result: " + Arrays.toString(sameResult.toArray()));

        // Split the original array into two arrays, in which elements of the same type will be stored
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Magazine> magazines = new ArrayList<>();
        for (Readable obj : base) {
            if (obj instanceof Book) {
                books.add((Book)obj);
            } else if (obj instanceof Magazine) {
                magazines.add((Magazine)obj);
            }
        }
        System.out.println("Books: " + Arrays.toString(books.toArray()));
        System.out.println("Magazines: " + Arrays.toString(magazines.toArray()));
    }
}
