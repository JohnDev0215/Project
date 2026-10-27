package io.reactivex.rxjava3.internal.util;

import java.util.HashMap;
import java.util.Map;
import spi.qs;

/* JADX INFO: loaded from: classes2.dex */
public enum HashMapSupplier implements qs<Map<Object, Object>> {
    INSTANCE;

    public static <K, V> qs<Map<K, V>> asSupplier() {
        return INSTANCE;
    }

    public Map<Object, Object> get() {
        return new HashMap();
    }
}
