package lt.alius.library.libraries;

import lt.alius.library.libraries.database.JsonWrapper;
import lt.alius.library.libraries.interfaces.DataStorage;

import java.io.FileNotFoundException;
import java.util.*;

public class Database implements DataStorage {
    private static Database instance;
    private JsonWrapper jsonWrapper;

    private Database() {
        //private fro Singleton
        this.jsonWrapper = new JsonWrapper();
    }

    public static synchronized Database getInstance() {
        //Singleton
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public <T extends BaseEntity> ArrayList<T> getList(Class<T> type) {
        return getInstance().jsonWrapper.getList(type);
    }

    /**
     * Add file to JSON file.
     * @param entity
     * @param <T>
     * @throws FileNotFoundException
     */
    @Override
    public <T extends BaseEntity> void add(T entity) throws FileNotFoundException {
        getInstance().jsonWrapper.add(entity);
    }

    /**
     * Alias for `add()`
     * @param entity
     * @param <T>
     */
    @Override
    public <T extends BaseEntity> void save(T entity) {
        try {
            getInstance().jsonWrapper.add(entity);
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
        }
    }

    /**
     * Remove entity from JSON file
     * @param entity
     * @param id
     * @param <T>
     * @throws FileNotFoundException
     */
    @Override
    public <T extends BaseEntity> void remove(T entity, int id) throws FileNotFoundException {
        getInstance().jsonWrapper.remove(entity, id);
    }
}
