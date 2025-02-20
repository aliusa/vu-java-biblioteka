package lt.alius.library.libraries;

public abstract class BaseEntity {
    protected Integer id = null;
    public String created_at = null;
    public String updated_at = null;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public abstract String toString();
}
