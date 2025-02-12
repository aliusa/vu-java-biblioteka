package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.interfaces.Timestampable;

@Entity(tableName = "books_publishers")
public class BooksPublisher extends BaseEntity implements Timestampable {
    public BooksItem booksItem;
    public String title;
}
