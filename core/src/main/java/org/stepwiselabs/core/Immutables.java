package org.stepwiselabs.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Immutables {

    private Immutables() {
        throw new InstantiationError();
    }

    /**
     * Returns an immutable map from the passed {@link Map}.
     * @param map
     * @return An immutable {@link Map}
     */
    public static <K, V> Map<K,V> toImmutable(Map<K,V> map){
        Preconditions.checkNotNull(map, "map" );
        Map<K,V> clone = new HashMap<>();
        clone.putAll(map);
        return Collections.unmodifiableMap(map);
    }

    /**
     * Returns an immutable list from the passed {@link List}.
     * @param list
     * @return An immutable {@link List}
     */
    public static <T> List<T> toImmutable(List<T> list){
        Preconditions.checkNotNull(list, "list" );
        List<T> clone = new ArrayList<>();
        clone.addAll(list);
        return Collections.unmodifiableList(list);
    }
}
