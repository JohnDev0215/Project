package com.amplifyframework.util;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class Immutable {
    private Immutable() {
    }

    public static <K, V> Map<K, V> of(@Nullable Map<K, V> map) {
        if (map == null) {
            return null;
        }
        return Collections.unmodifiableMap(new HashMap(map));
    }

    public static <E> Set<E> of(@Nullable Set<E> set) {
        if (set == null) {
            return null;
        }
        return Collections.unmodifiableSet(new HashSet(set));
    }

    public static <T> List<T> of(@Nullable List<T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(new ArrayList(list));
    }
}
