package lt.alius.library.libraries;

import lt.alius.library.libraries.database.JsonWrapper;
import lt.alius.library.libraries.interfaces.DataStorage;

import java.io.FileNotFoundException;

public class Database implements DataStorage {
    private static Database instance;
    private JsonWrapper jsonWrapper;

    private Database() {
        //private fro Singleton
        this.jsonWrapper = new JsonWrapper();
    }

    /**
     * Singleton Database instance
     * @return
     */
    public static synchronized Database getInstance() {
        //Singleton
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public <T extends BaseEntity> EntityArrayList<T> getList(Class<T> entityClass) {
        return getInstance().jsonWrapper.getList(entityClass);
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
     * @param entityClass
     * @param id
     * @param <T>
     * @throws FileNotFoundException
     */
    @Override
    public <T extends BaseEntity> void remove(Class<T> entityClass, int id) throws FileNotFoundException {
        getInstance().jsonWrapper.remove(entityClass, id);
    }
}
