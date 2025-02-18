/*
 * Bilbiotekos projektas.
 * Alius Sultanovas, PKIM 2024
 */

package lt.alius.library;

import lt.alius.library.entities.BooksItem;
import lt.alius.library.entities.UsersItem;
import lt.alius.library.libraries.Database;
import lt.alius.library.libraries.EntityArrayList;
import lt.alius.library.libraries.Runnable;
import lt.alius.library.libraries.Utilities;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static Thread thread = new Thread(new Runnable());

    public static void main(String[] args) {

        //Start thread only on first run
        if (thread.getState().equals("NEW")) {
            thread.start();
        }
        //thread

        HashMap<Integer, String> inputOptions = new HashMap<>();
        inputOptions.put(1, "Knygų sąrašas");
        inputOptions.put(2, "Naudotojų sąrašas");
        inputOptions.put(3, "Dabar išduotų knygų sąrašas");
        inputOptions.put(4, "Pridėti naują knygą");
        System.out.println("Ką norėtumėt daryti?");
        inputOptions.forEach((integer, s) -> System.out.printf("%s - %s%n", integer, s));
        System.out.print("Pasirinkimas: ");

        Scanner scanner = new Scanner(System.in);

        String response = scanner.next();
        switch (response) {
            case "1":
                EntityArrayList<BooksItem> booksItems = Database.getInstance().getList(BooksItem.class);
                //System.out.println(Arrays.toString(booksItems.toArray()));//combine all books to single string
                //for (BooksItem booksItem : booksItems) {
                //    System.out.println(booksItem.toString());
                //}
                System.out.printf("%n%n");

                System.out.printf("-".repeat(70) + "%n");
                System.out.printf("| %-4s | %-26s | %-17s | %-10s |%n", "ID", "Pavadinimas", "ISBN", "Puslapiai");
                System.out.printf("-".repeat(70) + "%n");
                for (BooksItem booksItem : booksItems) {
                    System.out.printf("| %-4s | %-26s | %-17s | %-10s |%n", booksItem.getId(), booksItem.title, booksItem.isbn, booksItem.pages);
                }
                System.out.printf("-".repeat(70));

                break;
            case "2":
                EntityArrayList<UsersItem> usersItems = Database.getInstance().getList(UsersItem.class);
                //System.out.println(Arrays.toString(usersItems.toArray()));
                System.out.printf("-".repeat(50) + "%n");
                System.out.printf("| %-4s | %-24s | %-12s |%n", "ID", "el.paštas", "tel.");
                System.out.printf("-".repeat(50) + "%n");
                for (UsersItem usersItem : usersItems) {
                    //System.out.println(usersItem.toString());
                    System.out.printf("| %-4s | %-24s | %-12s |%n", usersItem.getId(), usersItem.email, usersItem.phone);
                }
                System.out.printf("-".repeat(50));
                break;
            case "3":
                booksItems = Database.getInstance().getList(BooksItem.class);
                for (BooksItem booksItem1 : booksItems) {
                    System.out.printf("#%s - %s%n", booksItem1.getId(), booksItem1.getLendedNowCount());
                }
                System.out.println("Dabar išduotų knygų kiekis: ");//todo
                break;
            case "4":
                BooksItem booksItem = new BooksItem();
                booksItem.title = "Book Title " + new Random().nextInt(1000, Integer.MAX_VALUE);
                booksItem.isbn = Utilities.generateIsbn();
                booksItem.pages = new Random().nextInt(1, 500);
                try {
                    Database.getInstance().add(booksItem);
                } catch (FileNotFoundException e) {
                    //throw new RuntimeException(e);
                    System.out.println("Failed to add BooksItem to JSON.");
                } finally {
                    System.out.println(booksItem);
                    System.out.println("Database operation completed.");
                    booksItem = null;
                }
                break;
            default:
                System.out.println("Invalid entry. Please retry.");
                break;
        }
        System.out.println();
        System.out.println();
        main(args);
        scanner.close();
    }
}
