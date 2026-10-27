package u;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: u.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2463c implements Iterator, Map.Entry {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22594m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22595n;
    public final /* synthetic */ C2465e o;

    public C2463c(C2465e c2465e) {
        this.o = c2465e;
        this.l = c2465e.f22587n - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f22595n) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i7 = this.f22594m;
        C2465e c2465e = this.o;
        return A7.m.a(key, c2465e.g(i7)) && A7.m.a(entry.getValue(), c2465e.k(this.f22594m));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (this.f22595n) {
            return this.o.g(this.f22594m);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.f22595n) {
            return this.o.k(this.f22594m);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22594m < this.l;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f22595n) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i7 = this.f22594m;
        C2465e c2465e = this.o;
        Object objG = c2465e.g(i7);
        Object objK = c2465e.k(this.f22594m);
        return (objG == null ? 0 : objG.hashCode()) ^ (objK != null ? objK.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f22594m++;
        this.f22595n = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f22595n) {
            throw new IllegalStateException();
        }
        this.o.i(this.f22594m);
        this.f22594m--;
        this.l--;
        this.f22595n = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f22595n) {
            return this.o.j(this.f22594m, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
