package lt.alius.library.libraries;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Entity {
    /**
     * Raktas JSON faile, pagal kurį randama entity reikšmės.
     */
    String tableName();
}
