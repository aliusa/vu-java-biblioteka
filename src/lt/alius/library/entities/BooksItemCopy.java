package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.BookQuality;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.interfaces.Timestampable;

@Entity(tableName = "books_items_copy")
public class BooksItemCopy extends BaseEntity implements Timestampable {
    public BooksItem booksItem;
    //public UsersItem usersItem;
    public BooksLend[] booksLends;
    public BookQuality bookQuality;

    @Override
    public String toString() {
        return "%s, %s, #%s %s".formatted(id, "test", 15, "tastt@astst.lt");
        //return "%s, %s, #%s %s".formatted(id, booksItem.title, usersItem.getId(), usersItem.email);
    }
}
