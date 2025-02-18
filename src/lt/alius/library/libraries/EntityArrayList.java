package lt.alius.library.libraries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * Generic.
 *
 * @param <E>
 */
public class EntityArrayList<E extends BaseEntity> extends ArrayList<E> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Surasti objektą pagal ID
    public E findById(int id) {
        for (E entity : this) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public boolean removeById(int id) {
        return this.removeIf(entity -> entity.getId() == id);
    }

    public boolean existsById(int id) {
        return this.stream().anyMatch(entity -> entity.getId() == id);
    }

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public Integer getLatestId() {
        return this.isEmpty() ? null : this.get(this.size() - 1).getId();
    }
}
