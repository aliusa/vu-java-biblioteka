package lt.alius.library.libraries.database;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.EntityArrayList;

import java.io.FileNotFoundException;

public abstract class DatabaseWrapper {
    public abstract <T extends BaseEntity> EntityArrayList<T> getList(Class<T> entityClass);
    public abstract <T extends BaseEntity> void add(T entity) throws FileNotFoundException;
    public abstract <T extends BaseEntity> void remove(Class<T> entityClass, int id) throws FileNotFoundException;
    public abstract <T extends BaseEntity> Integer getLastId(Class<T> entityClass) throws FileNotFoundException;
}
