package o7;

import A7.m;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: renamed from: o7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2052d implements Map.Entry, B7.d {
    public final C2053e l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20546m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f20547n;

    public C2052d(C2053e c2053e, int i7) {
        m.f(c2053e, "map");
        this.l = c2053e;
        this.f20546m = i7;
        this.f20547n = c2053e.f20554s;
    }

    public final void b() {
        if (this.l.f20554s != this.f20547n) {
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            if (m.a(entry.getKey(), getKey()) && m.a(entry.getValue(), getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        b();
        return this.l.l[this.f20546m];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        b();
        Object[] objArr = this.l.f20549m;
        m.c(objArr);
        return objArr[this.f20546m];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        b();
        C2053e c2053e = this.l;
        c2053e.c();
        Object[] objArr = c2053e.f20549m;
        if (objArr == null) {
            int length = c2053e.l.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            c2053e.f20549m = objArr;
        }
        int i7 = this.f20546m;
        Object obj2 = objArr[i7];
        objArr[i7] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
