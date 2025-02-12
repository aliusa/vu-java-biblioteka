package lt.alius.library.libraries.database;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JsonWrapper extends DatabaseWrapper {
    private static final String FILE_NAME = "database.json";
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // Universalus metodas gauti sąrašą pagal klasę
    @Override
    public <T extends BaseEntity> ArrayList<T> getList(Class<T> type) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>(); // Return empty list if file doesn't exist

        try {
            // Read the entire JSON file as a map
            Map<String, Object> jsonData = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
            });

            // Get the table name from the @Entity annotation
            String tableName = getTableName(type);

            // Extract the correct list from the JSON map
            Object rawList = jsonData.get(tableName);

            if (rawList instanceof List<?>) {
                List<?> list = (List<?>) rawList;
                ArrayList<T> entityList = new ArrayList<>();

                // Convert each entry to the correct entity type
                for (Object item : list) {
                    T entity = objectMapper.convertValue(item, type);
                    entityList.add(entity);
                }
                return entityList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //
        }
        return new ArrayList<>();
    }

    // Metodas automatiškai paimti tableName iš @Entity anotacijos
    private static <T extends BaseEntity> String getTableName(Class<T> type) {
        Entity entityAnnotation = type.getAnnotation(Entity.class);
        if (entityAnnotation == null) {
            throw new IllegalArgumentException("Class " + type.getSimpleName() + " must be annotated with @Entity");
        }
        return entityAnnotation.tableName();
    }

    // Metodas pridėti objektą automatiškai nustatant tableName
    @Override
    public <T extends BaseEntity> void add(T entity) throws FileNotFoundException {

        //todo generate ID

        List<T> master = new ArrayList<T>();

        String tableName = getTableName(entity.getClass());
        System.out.println(tableName);
        //List<T> list = getList(entity.getClass()); // Sukuriame naują sąrašą, kad būtų galima keisti
        //list.add(entity);
        //save(tableName, list);

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try {
            List<T> list = objectMapper.readValue(file, new TypeReference<List<T>>() {});
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter());

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        var classe = entity.getClass();
        //add to end of older list
        //UsersItem tempList = new UsersItem();//saugo
        //tempList.setStore(store);
        //tempList.setItems(items);


        master.add((T) entity);

        //try {
        try {
            objectMapper.writeValue(file, master);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //} catch (JsonGenerationException e) {
        //    e.printStackTrace();
        //} catch (JsonMappingException e) {
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    // Universalus metodas išsaugoti duomenis į failą
    /*private static void save(String tableName, List<? extends BaseEntity> list) {
        File file = new File(FILE_NAME);
        LibraryDatabase databaseData;

        if (file.exists()) {
            try {
                databaseData = objectMapper.readValue(file, LibraryDatabase.class);
            } catch (IOException e) {
                e.printStackTrace();
                databaseData = new LibraryDatabase();
            }
        } else {
            databaseData = new LibraryDatabase();
        }

        Map<String, List<? extends BaseEntity>> data = databaseData.getData();
        data.put(tableName, list);
        //databaseData.setData(data);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, databaseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }/**/

    @Override
    public <T extends BaseEntity> void remove(T entity, int id) throws FileNotFoundException {
        List<T> master = new ArrayList<T>();

        String tableName = getTableName(entity.getClass());
        System.out.println(tableName);
        //List<T> list = getList(entity.getClass()); // Sukuriame naują sąrašą, kad būtų galima keisti
        //list.add(entity);
        //save(tableName, list);

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            //throw new FileNotFoundException();
        }

        //todo
    }

    // Universalus metodas rasti objektą pagal ID
    public <T extends BaseEntity> Optional<T> getById(int id, Class<T> type) {
        return getList(type).stream()
                .filter(entity -> entity.getId() == id)
                .findFirst();
    }
}
