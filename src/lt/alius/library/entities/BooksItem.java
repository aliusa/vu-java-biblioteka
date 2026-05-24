package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.EntityArrayList;

import java.util.Arrays;

/**
 * Knygos entity.
 */
@Entity(tableName = "books_items")
public class BooksItem extends BaseEntity {
    //@ nullable
    public String title;
     //@ nullable
    public String isbn;
    public int pages = 1;
    //@ nullable
    public EntityArrayList<BooksItemCopy> booksItemCopies;
    //@ nullable
    public EntityArrayList<BooksAuthor> booksAuthors;

    //@ public invariant title == null || title instanceof String;
    //@ public invariant isbn == null || isbn instanceof String;
    //@ public invariant pages >= 1;
    //@ public invariant booksItemCopies == null || booksItemCopies != null;
    //@ public invariant booksAuthors == null || booksAuthors != null;

    public BooksItem() {}

    /*@ public normal_behavior
      @   requires newTitle != null;
      @   requires newTitle.length() > 0;
      @   requires newTitle.length() <= 512;
      @   assignable title;
      @   ensures title == newTitle;
      @   ensures title != \old(title) || \old(title) == newTitle;
      @ also
      @ public exceptional_behavior
      @   requires newTitle == null || newTitle.length() == 0 || newTitle.length() > 512;
      @   assignable \nothing;
      @   signals (IllegalArgumentException e) true;
      @*/
    public void setTitle(String newTitle) {
        if (newTitle == null || newTitle.length() == 0 || newTitle.length() > 512) {
            throw new IllegalArgumentException("Invalid title");
        }

        //@ assert newTitle.length() <= 512;

        title = newTitle;
    }

    /*@ public normal_behavior
      @   requires lends != null;
      @   requires lends.length <= Integer.MAX_VALUE;
      @   assignable \nothing;
      @   ensures \result >= 0;
      @ also
      @ public exceptional_behavior
      @   requires lends == null;
      @   assignable \nothing;
      @   signals (IllegalArgumentException e) true;
      @*/
    public static /*@ pure @*/ int countNowLended(BooksLend[] lends) {
        if (lends == null) {
            throw new IllegalArgumentException("Lends cannot be null");
        }

        int count = 0;
        //@ loop_invariant 0 <= count;
        for (int i = 0; i < lends.length; i++) {
            //@ assume i >= 0;
            //@ assume i <= lends.length;

            BooksLend bookslend = lends[i];
            if (bookslend != null && bookslend.isNowLended()) {
                //@ assume count < Integer.MAX_VALUE;
                count = count + 1;
            }
        }
        return count;
    }

    /*@ public normal_behavior
      @   requires booksItemCopies == null || booksItemCopies.size() >= 0;
      @   assignable \nothing;
      @   ensures \result >= 0;
      @*/
    public /*@ pure @*/ int getLendedNowCount() {
        int count = 0;

        if (booksItemCopies != null) {
            //@ loop_invariant 0 <= count;
            for (int i = 0; i < booksItemCopies.size(); i++) {
                //@ assume i >= 0;
                //@ assume i < booksItemCopies.size();

                BooksItemCopy booksItemCopy = booksItemCopies.get(i);

                if (booksItemCopy != null && booksItemCopy.booksLends != null) {
                    int add = countNowLended(booksItemCopy.booksLends);
                    int all = countAllLendsSpec(booksItemCopy.booksLends);

                    //@ assume all >= add;
                    //@ assume add >= 0;
                    //@ assume count <= Integer.MAX_VALUE - add;

                    count = count + add;
                }
            }
        }

        return count;
    }

    /*@ public normal_behavior
      @   requires lends != null;
      @   assignable \nothing;
      @   ensures \result >= 0;
      @*/
    public static /*@ pure @*/ int countAllLendsSpec(BooksLend[] lends) {
        //@ assume lends.length >= 0;

        //@ assume (\sum int i; 0 <= i && i < lends.length; 1) >= 0;

        return lends.length;
    }

    /*@ public normal_behavior
      @   assignable \nothing;
      @   ensures booksItemCopies == null ==> \result == false;
      @*/
    public /*@ pure @*/ boolean isLendable() {
        if (booksItemCopies == null) {
            return false;
        }
        for (int i = 0; i < booksItemCopies.size(); i++) {
            //@ assume 0 <= i;
            //@ assume i < booksItemCopies.size();

            BooksItemCopy booksItemCopy = booksItemCopies.get(i);

            if (booksItemCopy != null && booksItemCopy.booksLends != null) {
                if (hasAnyLend(booksItemCopy.booksLends)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*@ public normal_behavior
      @   requires lends != null;
      @   assignable \nothing;
      @   ensures \result == true ==>
      @       (\exists int i; 0 <= i && i < lends.length; lends[i] != null);
      @*/
    public static /*@ pure @*/ boolean hasAnyLend(BooksLend[] lends) {
        for (int i = 0; i < lends.length; i++) {
            //@ assume 0 <= i && i < lends.length;
            if (lends[i] != null) {
                return true;
            }
        }
        return false;
    }

    //@ skipesc
    @Override
    public String toString() {
        return "#%s „%s“, %s".formatted(id, title, isbn);
    }
}
