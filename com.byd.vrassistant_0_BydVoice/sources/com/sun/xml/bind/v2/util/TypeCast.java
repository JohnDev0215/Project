package com.sun.xml.bind.v2.util;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TypeCast {
    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> checkedCast(Map<?, ?> map, Class<K> cls, Class<V> cls2) {
        if (map == 0) {
            return null;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (!cls.isInstance(entry.getKey())) {
                throw new ClassCastException(entry.getKey().getClass().toString());
            }
            if (!cls2.isInstance(entry.getValue())) {
                throw new ClassCastException(entry.getValue().getClass().toString());
            }
        }
        return map;
    }
}
