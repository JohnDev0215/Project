package com.github.benmanes.caffeine.cache;

import defpackage.k91;
import java.util.AbstractMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: WriteThroughEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<K, V> extends AbstractMap.SimpleEntry<K, V> {
    static final long serialVersionUID = 1;
    public final ConcurrentMap<K, V> e;

    public a(k91 k91Var, Object obj, Object obj2) {
        super(obj, obj2);
        Objects.requireNonNull(k91Var);
        this.e = k91Var;
    }

    @Override // java.util.AbstractMap.SimpleEntry, java.util.Map.Entry
    public final V setValue(V v) {
        this.e.put(getKey(), v);
        return (V) super.setValue(v);
    }

    public Object writeReplace() {
        return new AbstractMap.SimpleEntry(this);
    }
}
