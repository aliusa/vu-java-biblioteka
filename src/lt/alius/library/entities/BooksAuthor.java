package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.interfaces.Timestampable;

import java.util.ArrayList;

@Entity(tableName = "books_authors")
public class BooksAuthor extends BaseEntity implements Timestampable {
    public String author;
    public ArrayList<BooksItem> booksItems;
}
