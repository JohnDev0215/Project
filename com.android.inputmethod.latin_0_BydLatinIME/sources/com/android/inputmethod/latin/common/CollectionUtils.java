package com.android.inputmethod.latin.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @Nonnull
    public static <E> ArrayList<E> arrayAsList(@Nonnull final E[] array, final int start, final int end) {
        if (start < 0 || start > end || end > array.length) {
            throw new IllegalArgumentException("Invalid start: " + start + " end: " + end + " with array.length: " + array.length);
        }
        ArrayList<E> arrayList = new ArrayList<>(end - start);
        while (start < end) {
            arrayList.add(array[start]);
            start++;
        }
        return arrayList;
    }

    public static boolean isNullOrEmpty(@Nullable final Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty(@Nullable final Map map) {
        return map == null || map.isEmpty();
    }
}
