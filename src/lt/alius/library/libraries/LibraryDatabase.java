package lt.alius.library.libraries;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryDatabase {
    public ArrayList books_authors;
    public ArrayList books_items;
    public ArrayList books_items_copy;
    public ArrayList books_lends;
    public ArrayList books_publishers;
    public ArrayList users_items;
    public ArrayList<String> tableNames = new ArrayList<String>();

    public LibraryDatabase() {
        var packageName = "lt.alius.library.entities";

        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class> classes = reader.lines()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String line) {
                        return line.endsWith(".class");
                    }
                })
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());

        classes.forEach(new Consumer<Class>() {
            @Override
            public void accept(Class aClass) {
                //users_items.
                Entity entityAnnotation = (Entity) aClass.getAnnotation(Entity.class);
                if (entityAnnotation != null) {
                    tableNames.add(entityAnnotation.tableName());
                }
            }
        });
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
