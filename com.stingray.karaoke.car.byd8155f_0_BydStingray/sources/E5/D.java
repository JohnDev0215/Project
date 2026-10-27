package E5;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Map.Entry {
    public D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public D f1754m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public D f1755n;
    public D o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public D f1756p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f1757q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f1758r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object f1759s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1760t;

    public D() {
        this.f1757q = null;
        this.f1758r = -1;
        this.f1756p = this;
        this.o = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.f1757q;
        if (obj2 == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!obj2.equals(entry.getKey())) {
            return false;
        }
        Object obj3 = this.f1759s;
        if (obj3 == null) {
            if (entry.getValue() != null) {
                return false;
            }
        } else if (!obj3.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f1757q;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f1759s;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f1757q;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f1759s;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.f1759s;
        this.f1759s = obj;
        return obj2;
    }

    public final String toString() {
        return this.f1757q + "=" + this.f1759s;
    }

    public D(D d, Object obj, int i7, D d10, D d11) {
        this.l = d;
        this.f1757q = obj;
        this.f1758r = i7;
        this.f1760t = 1;
        this.o = d10;
        this.f1756p = d11;
        d11.o = this;
        d10.f1756p = this;
    }
}
