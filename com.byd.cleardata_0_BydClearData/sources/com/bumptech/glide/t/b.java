package com.bumptech.glide.t;

/* JADX INFO: loaded from: classes.dex */
public final class b<K, V> extends a.d.a<K, V> {
    private int j;

    @Override // a.d.g, java.util.Map
    public void clear() {
        this.j = 0;
        super.clear();
    }

    @Override // a.d.g, java.util.Map
    public int hashCode() {
        if (this.j == 0) {
            this.j = super.hashCode();
        }
        return this.j;
    }

    @Override // a.d.g
    public void j(a.d.g<? extends K, ? extends V> gVar) {
        this.j = 0;
        super.j(gVar);
    }

    @Override // a.d.g
    public V k(int i) {
        this.j = 0;
        return (V) super.k(i);
    }

    @Override // a.d.g
    public V l(int i, V v) {
        this.j = 0;
        return (V) super.l(i, v);
    }

    @Override // a.d.g, java.util.Map
    public V put(K k, V v) {
        this.j = 0;
        return (V) super.put(k, v);
    }
}
