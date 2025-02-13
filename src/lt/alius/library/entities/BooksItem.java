package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Knygos entity.
 */
@Entity(tableName = "books_items")
public class BooksItem extends BaseEntity {
    public String title;
    public String isbn;
    public int pages;
    private ArrayList<BooksItemCopy> booksItemCopies;
    private ArrayList<BooksAuthor> booksAuthors;

    public BooksItem() {
        //
    }
    public BooksItem(String title) {
        this.title = title;
    }

    public ArrayList<BooksItemCopy> getBooksItemCopies() {
        return booksItemCopies;
    }

    public int getLendedNowCount() {
        int count = 0;

        if (booksItemCopies != null) {
            for (BooksItemCopy booksItemCopy : booksItemCopies) {
                count += Arrays.stream(booksItemCopy.booksLends).filter(booksLend -> booksLend.isNowLended()).count();
            }
        }

        return count;
    }

    public boolean isLendable() {
        if (booksItemCopies == null) {
            return false;
        }
        for (BooksItemCopy booksItemCopy : booksItemCopies) {
            return Arrays.stream(booksItemCopy.booksLends).filter(booksLend -> booksLend.isNowLended()).count() > 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return "#%s „%s“, %s".formatted(id, title, isbn);
    }
}
