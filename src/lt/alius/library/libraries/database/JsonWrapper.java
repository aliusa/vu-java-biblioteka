package lt.alius.library.libraries.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;
import lt.alius.library.libraries.EntityArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonWrapper extends DatabaseWrapper {
    private static final String FILE_NAME = "database.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected <T extends BaseEntity> Map<String, Object> getJsonData(Class<T> entityClass) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null; // Return empty list if file doesn't exist

        try {

            Map<String, Object> jsonData = Map.of();
            if (file.length() > 0) {
                // Read the entire JSON file as a map
                jsonData = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
                });
            }

            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //
        }
        return null;
    }

    protected <T extends BaseEntity> Object getUnmappedList(Class<T> entityClass) {
        Map<String, Object> jsonData = getJsonData(entityClass);

        // Get the table name from the @Entity annotation
        String tableName = getTableName(entityClass);

        // Extract the correct list from the JSON map
        return jsonData.get(tableName);
    }

    protected <T extends BaseEntity> EntityArrayList<T> getMappedList(Class<T> entityClass) {
        Object rawList = getUnmappedList(entityClass);

        if (rawList instanceof List<?>) {
            List<?> list = (List<?>) rawList;
            EntityArrayList<T> entityList = new EntityArrayList<>();

            // Convert each entry to the correct entity entityClass
            for (Object item : list) {
                T entity = (T) objectMapper.convertValue(item, entityClass);
                entityList.add(entity);
            }
            return entityList;
        }
        return new EntityArrayList<>();
    }

    /**
     * Gauti sąrašą pagal entity.
     *
     * @param entityClass
     * @return
     * @param <T>
     */
    @Override
    public <T extends BaseEntity> EntityArrayList<T> getList(Class<T> entityClass) {
        return getMappedList(entityClass);
    }

    // Metodas automatiškai paimti tableName iš @Entity anotacijos
    private static <T extends BaseEntity> String getTableName(Class<T> entityClass) {
        Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        if (entityAnnotation == null) {
            throw new IllegalArgumentException("Class " + entityClass.getSimpleName() + " must be annotated with @Entity");
        }
        return entityAnnotation.tableName();
    }

    /**
     * Gauti paskutinį ID arba `null`.
     *
     * @param entityClass
     * @return
     * @param <T>
     * @throws FileNotFoundException
     */
    @Override
    public <T extends BaseEntity> Integer getLastId(Class<T> entityClass) throws FileNotFoundException {
        var items = getList(entityClass);
        if (items.isEmpty()) {
            return 1;
        }
        T item = items.get(items.size() - 1);
        return item.getId();
    }

    @Override
    public <T extends BaseEntity> void add(T entity) throws FileNotFoundException {
        Map<String, Object> jsonData = getJsonData(entity.getClass());
        if (jsonData.size() > 0) {
            File file = new File(FILE_NAME);

            String tableName = getTableName(entity.getClass());
            EntityArrayList<T> list = new EntityArrayList<T>();
            if (jsonData.containsKey(tableName) && jsonData.get(tableName) instanceof List<?>) {
                ArrayList<T> rawList = (ArrayList<T>) jsonData.get(tableName);
                if (rawList instanceof List<?>) {
                    // Convert each entry to the correct entity entityClass
                    for (Object item : rawList) {
                        T entity2 = (T) objectMapper.convertValue(item, entity.getClass());
                        list.add(entity2);
                    }
                }
            } else {
                list = new EntityArrayList<T>();
            }
            entity.setId(list.getLatestId() + 1);
            var formattedDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            entity.created_at = formattedDatetime;
            entity.updated_at = formattedDatetime;
            list.add(entity);
            jsonData.put(tableName, list);


            //Save to file in thread
            Thread  thread = new Thread(() -> {
                // Write updated data back to the file
                try {
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT).writerWithDefaultPrettyPrinter().writeValue(file, jsonData);
                } catch (IOException e) {
                    System.out.println("Error writing to file " + FILE_NAME);
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }

    @Override
    public <T extends BaseEntity> void remove(Class<T> entityClass, int id) throws FileNotFoundException {
        EntityArrayList<T> master = new EntityArrayList<T>();

        String tableName = getTableName(entityClass);
        System.out.println(tableName);
        //List<T> list = getList(entityClass.getClass()); // Sukuriame naują sąrašą, kad būtų galima keisti
        //list.add(entityClass);
        //save(tableName, list);

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            //throw new FileNotFoundException();
        }

        //todo
    }

    // Universalus metodas rasti objektą pagal ID
    public <T extends BaseEntity> Optional<T> getById(Class<T> entityClass, int id) {
        return getList(entityClass).stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();
    }
}
