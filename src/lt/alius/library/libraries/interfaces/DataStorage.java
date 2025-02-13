package lt.alius.library.libraries.interfaces;

import lt.alius.library.libraries.BaseEntity;

import java.io.FileNotFoundException;

public interface DataStorage {
    public <T extends BaseEntity> void add(T entity) throws FileNotFoundException;
    public <T extends BaseEntity> void save(T entity) throws FileNotFoundException;
    public <T extends BaseEntity> void remove(Class<T> entityClass, int id) throws FileNotFoundException;
}
