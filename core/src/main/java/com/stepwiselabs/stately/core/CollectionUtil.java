package com.stepwiselabs.stately.core;

import java.util.*;

import static com.stepwiselabs.stately.core.Preconditions.checkNotEmpty;
import static com.stepwiselabs.stately.core.Preconditions.checkNotNull;


/**
 * Common {@link java.util.Collection} utility methods.
 *
 * @author sterrasi
 */
public class CollectionUtil {

    @SafeVarargs
    public static <T> List<T> arrayList(T ... values){
        List<T> list = new ArrayList<>();
        for ( T value : values ){
            list.add(value);
        }
        return list;
    }

    @SafeVarargs
    public static <T> List<T> linkedList(T ... values){
        List<T> list = new LinkedList<>();
        for ( T value : values ){
            list.add(value);
        }
        return list;
    }


    public static <T> List<T> arrayList(Collection<T> values){
        List<T> list = new ArrayList<>();
        for ( T value : values ){
            list.add(value);
        }
        return list;
    }


    @SafeVarargs
    public static <T> Set<T> hashSet(T ... values){
        Set<T> set = new HashSet<>();
        for ( T value : values ){
            set.add(value);
        }
        return set;
    }

    public static <T> Set<T> hashSet( Collection<T> values ){
        Set<T> set = new HashSet<>();
        for ( T value : values ){
            set.add(value);
        }
        return set;
    }

    public static String join( final Collection<?> values, String separator ){
        checkNotEmpty(values,"values");
        checkNotNull(separator, "separator");

        StringBuilder sb = new StringBuilder();
        for ( Object obj : values ){
            if ( sb.length() > 0){
                sb.append(separator);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    /**
     * Returns an immutable map from the passed {@link Map}.
     * @param map
     * @return An immutable {@link Map}
     */
    public static <K, V> Map<K,V> toImmutable(Map<K,V> map){
        checkNotNull(map, "map" );
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
        checkNotNull(list, "list" );
        List<T> clone = new ArrayList<>();
        clone.addAll(list);
        return Collections.unmodifiableList(list);
    }
}
