package lt.alius.library.libraries.database;

import lt.alius.library.libraries.BaseEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class DatabaseWrapper {
    public abstract <T extends BaseEntity> ArrayList<T> getList(Class<T> type);
    public abstract <T extends BaseEntity> void add(T entity) throws FileNotFoundException;
    public abstract <T extends BaseEntity> void remove(T entity, int id) throws FileNotFoundException;
}
