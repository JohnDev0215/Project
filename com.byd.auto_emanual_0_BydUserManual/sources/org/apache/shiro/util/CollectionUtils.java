package org.apache.shiro.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.subject.PrincipalCollection;

/* JADX INFO: loaded from: classes.dex */
public class CollectionUtils {
    public static <E> Set<E> asSet(E... eArr) {
        if (eArr == null || eArr.length == 0) {
            return Collections.emptySet();
        }
        if (eArr.length == 1) {
            return Collections.singleton(eArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(((eArr.length * 4) / 3) + 1);
        Collections.addAll(linkedHashSet, eArr);
        return linkedHashSet;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static int size(Collection collection) {
        if (collection != null) {
            return collection.size();
        }
        return 0;
    }

    public static int size(Map map) {
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    @Deprecated
    public static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    public static <E> List<E> asList(E... eArr) {
        if (eArr == null || eArr.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(eArr);
    }

    static int computeListCapacity(int i) {
        return (int) Math.min(((long) i) + 5 + ((long) (i / 10)), 2147483647L);
    }
}
