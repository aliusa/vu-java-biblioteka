package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;

import java.util.Date;

/**
 * Knygos kopijos paskolinimo naudotojui entity.
 */
@Entity(tableName = "books_lends")
public class BooksLend extends BaseEntity {
    public UsersItem usersItem;
    public BooksItemCopy booksItemCopy;
    public Date lendStart;
    public Date lendEnd;

    public boolean isNowLended() {
        return lendStart != null && lendEnd != null;
    }

    @Override
    public String toString() {
        return "[#%s]".formatted(id);
    }
}
