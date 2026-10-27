package com.google.protobuf;

import com.google.protobuf.c0;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: MapFieldLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class m0<K, V> extends LinkedHashMap<K, V> {
    public static final m0 f;
    public boolean e;

    static {
        m0 m0Var = new m0();
        f = m0Var;
        m0Var.e = false;
    }

    public m0() {
        this.e = true;
    }

    public static <K, V> int b(Map<K, V> map) {
        int iE = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            iE += e(entry.getValue()) ^ e(entry.getKey());
        }
        return iE;
    }

    public static int e(Object obj) {
        if (!(obj instanceof byte[])) {
            if (obj instanceof c0.a) {
                throw new UnsupportedOperationException();
            }
            return obj.hashCode();
        }
        byte[] bArr = (byte[]) obj;
        Charset charset = c0.a;
        int length = bArr.length;
        int i = length;
        for (int i2 = 0; i2 < 0 + length; i2++) {
            i = (i * 31) + bArr[i2];
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static LinkedHashMap h(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (value instanceof byte[]) {
                byte[] bArr = (byte[]) value;
                value = (V) Arrays.copyOf(bArr, bArr.length);
            }
            linkedHashMap.put(key, value);
        }
        return linkedHashMap;
    }

    public static <K, V> boolean l(Map<K, V> map, Map<K, V> map2) {
        if (map == map2) {
            return true;
        }
        if (map.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                return false;
            }
            V value = entry.getValue();
            V v = map2.get(entry.getKey());
            if (!(((value instanceof byte[]) && (v instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) v) : value.equals(v))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        k();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return (obj instanceof Map) && l(this, (Map) obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return b(this);
    }

    public final void k() {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        k();
        Charset charset = c0.a;
        k.getClass();
        v.getClass();
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        k();
        for (K k : map.keySet()) {
            Charset charset = c0.a;
            k.getClass();
            map.get(k).getClass();
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        k();
        return (V) super.remove(obj);
    }

    public m0(Map<K, V> map) {
        super(map);
        this.e = true;
    }
}
