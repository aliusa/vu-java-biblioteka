package lt.alius.library.entities;

import lt.alius.library.libraries.BaseEntity;
import lt.alius.library.libraries.interfaces.Identifiable;

/**
 * Bibliotekos/sistemos administratorių entity.
 */
public class AdministratorsUser extends BaseEntity implements Identifiable {

    public String email;

    @Override
    public String getIdentifierField() {
        return "email";
    }

    @Override
    public String getIdentifierValue() {
        return email;
    }

    @Override
    public String toString() {
        return "%s".formatted(email);
    }
}
