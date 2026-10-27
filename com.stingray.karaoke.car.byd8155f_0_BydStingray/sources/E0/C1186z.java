package e0;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: e0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1186z implements Iterator, B7.a {
    public final C1181u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f13755m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f13756n;
    public Map.Entry o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Map.Entry f13757p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f13758q;

    public C1186z(C1181u c1181u, Iterator it, int i7) {
        this.f13758q = i7;
        this.l = c1181u;
        this.f13755m = it;
        this.f13756n = c1181u.b().d;
        b();
    }

    public final void b() {
        this.o = this.f13757p;
        Iterator it = this.f13755m;
        this.f13757p = it.hasNext() ? (Map.Entry) it.next() : null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13757p != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f13758q) {
            case 0:
                b();
                if (this.o != null) {
                    return new C1185y(this);
                }
                throw new IllegalStateException();
            case 1:
                Map.Entry entry = this.f13757p;
                if (entry == null) {
                    throw new IllegalStateException();
                }
                b();
                return entry.getKey();
            default:
                Map.Entry entry2 = this.f13757p;
                if (entry2 == null) {
                    throw new IllegalStateException();
                }
                b();
                return entry2.getValue();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        C1181u c1181u = this.l;
        if (c1181u.b().d != this.f13756n) {
            throw new ConcurrentModificationException();
        }
        Map.Entry entry = this.o;
        if (entry == null) {
            throw new IllegalStateException();
        }
        c1181u.remove(entry.getKey());
        this.o = null;
        this.f13756n = c1181u.b().d;
    }
}
