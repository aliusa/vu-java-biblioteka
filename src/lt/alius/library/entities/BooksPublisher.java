package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;

/**
 * Knygos leidėjo entity.
 */
@Entity(tableName = "books_publishers")
public class BooksPublisher extends BaseEntity {
    public BooksItem booksItem;
    public String title;

    @Override
    public String toString() {
        return "%s".formatted(title);
    }
}
