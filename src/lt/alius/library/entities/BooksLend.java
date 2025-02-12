package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.interfaces.Timestampable;

import java.util.Date;

@Entity(tableName = "books_lends")
public class BooksLend extends BaseEntity implements Timestampable {
    public UsersItem usersItem;
    public BooksItemCopy booksItemCopy;
    public Date lendStart;
    public Date lendEnd;

    public boolean isNowLended() {
        return lendStart != null && lendEnd != null;
    }
}
