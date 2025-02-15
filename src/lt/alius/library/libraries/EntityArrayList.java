package lt.alius.library.libraries;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Generic.
 *
 * @param <E>
 */
public class EntityArrayList<E extends BaseEntity> extends ArrayList<E> {
    private ArrayList<E> entities = new ArrayList<E>();

    public ArrayList<E> getAll() {
        //todo: todofix kad imtu jau sukurta
        for (int i = 0; i < size(); i++) {
            E element = super.get(i);
            entities.add(element);
        }
        return entities;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(getAll().toArray(), size());
        //return super.toArray();
    }

    //todo: kazkoki kita metoda sugalvoti. Kad butu realus, prasmingas
}
