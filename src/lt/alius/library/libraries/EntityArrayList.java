package lt.alius.library.libraries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @Override
    public boolean add(E entity) {
        if (entity.getId() == null) {
            entity.setId(this.getLatestId() + 1);
            var formattedDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            entity.created_at = entity.updated_at = formattedDatetime;
        }
        return super.add(entity);
    }
}
