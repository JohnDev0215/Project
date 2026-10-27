package com.android.permissioncontroller.permission.utils;

import android.util.ArraySet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T firstOrNull(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @SafeVarargs
    public static <T> boolean retainAll(ArraySet<T> arraySet, T... valuesToRetain) {
        boolean removed = false;
        for (int i = arraySet.size() - 1; i >= 0; i--) {
            if (!ArrayUtils.contains(valuesToRetain, arraySet.valueAt(i))) {
                arraySet.removeAt(i);
                removed = true;
            }
        }
        return removed;
    }

    public static <T> List<T> singletonOrEmpty(T element) {
        return element != null ? Collections.singletonList(element) : Collections.emptyList();
    }
}
