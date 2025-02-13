package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.EntityArrayList;
import lt.alius.library.libraries.interfaces.Identifiable;

/**
 * Turinio naudotojo entity.
 */
@Entity(tableName = "users_items")
public class UsersItem  extends BaseEntity implements Identifiable {
    public String email;
    public String phone;
    private EntityArrayList<BooksLend> booksLends;
    public int booksAllowedToLend = -1;

    @Override
    public String toString() {
        return "#%s %s %s".formatted(id, email, phone);
    }

    @Override
    public String getIdentifierField() {
        return "email";
    }

    @Override
    public String getIdentifierValue() {
        return email;
    }
}
