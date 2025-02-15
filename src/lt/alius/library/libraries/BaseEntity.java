package lt.alius.library.libraries;

public abstract class BaseEntity {
    public Integer id = null;
    public String created_at = null;
    public String updated_at = null;

    public Integer getId() {
        return id;
    }


    //todo: prideti abstract metodus, kuri implementuotu childai ir kazkas naudotu ta metoda


    @Override
    public String toString() {
        return "[%s]".formatted(id);
    }
}
