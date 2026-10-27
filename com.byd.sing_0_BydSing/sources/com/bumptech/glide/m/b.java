package com.bumptech.glide.m;

/* JADX INFO: compiled from: CachedHashCodeArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<K, V> extends c.d.a<K, V> {
    private int j;

    @Override // c.d.g, java.util.Map
    public void clear() {
        this.j = 0;
        super.clear();
    }

    @Override // c.d.g, java.util.Map
    public int hashCode() {
        if (this.j == 0) {
            this.j = super.hashCode();
        }
        return this.j;
    }

    @Override // c.d.g
    public void j(c.d.g<? extends K, ? extends V> gVar) {
        this.j = 0;
        super.j(gVar);
    }

    @Override // c.d.g
    public V k(int i) {
        this.j = 0;
        return (V) super.k(i);
    }

    @Override // c.d.g
    public V l(int i, V v) {
        this.j = 0;
        return (V) super.l(i, v);
    }

    @Override // c.d.g, java.util.Map
    public V put(K k, V v) {
        this.j = 0;
        return (V) super.put(k, v);
    }
}
