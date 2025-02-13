package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.EntityArrayList;

/**
 * Knygos autoriaus entity.
 */
@Entity(tableName = "books_authors")
public class BooksAuthor extends BaseEntity {
    public String author;
    public EntityArrayList<BooksItem> booksItems;
}
