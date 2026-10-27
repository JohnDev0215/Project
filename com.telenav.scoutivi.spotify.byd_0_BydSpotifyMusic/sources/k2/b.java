package k2;

/* JADX INFO: compiled from: CachedHashCodeArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<K, V> extends n.a<K, V> {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f7240n;

    @Override // n.g, java.util.Map
    public void clear() {
        this.f7240n = 0;
        super.clear();
    }

    @Override // n.g, java.util.Map
    public int hashCode() {
        if (this.f7240n == 0) {
            this.f7240n = super.hashCode();
        }
        return this.f7240n;
    }

    @Override // n.g
    public void l(n.g<? extends K, ? extends V> gVar) {
        this.f7240n = 0;
        super.l(gVar);
    }

    @Override // n.g
    public V m(int i7) {
        this.f7240n = 0;
        return (V) super.m(i7);
    }

    @Override // n.g
    public V n(int i7, V v6) {
        this.f7240n = 0;
        return (V) super.n(i7, v6);
    }

    @Override // n.g, java.util.Map
    public V put(K k7, V v6) {
        this.f7240n = 0;
        return (V) super.put(k7, v6);
    }
}
