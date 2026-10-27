package P8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Iterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f5464m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5465n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ k f5466p;

    public f(g gVar) {
        this.l = 0;
        this.f5466p = gVar;
        this.f5464m = gVar.f5467a.iterator();
        this.f5465n = -1;
    }

    public void b() {
        Object next;
        g gVar;
        do {
            Iterator it = this.f5464m;
            if (!it.hasNext()) {
                this.f5465n = 0;
                return;
            } else {
                next = it.next();
                gVar = (g) this.f5466p;
            }
        } while (((Boolean) gVar.c.b(next)).booleanValue() != gVar.f5468b);
        this.o = next;
        this.f5465n = 1;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [A7.o, z7.k] */
    public void c() {
        Iterator it = this.f5464m;
        if (it.hasNext()) {
            Object next = it.next();
            if (((Boolean) ((A7.o) ((j) this.f5466p).c).b(next)).booleanValue()) {
                this.f5465n = 1;
                this.o = next;
                return;
            }
        }
        this.f5465n = 0;
    }

    public boolean d() {
        Iterator it;
        Iterator it2 = (Iterator) this.o;
        if (it2 != null && it2.hasNext()) {
            this.f5465n = 1;
            return true;
        }
        do {
            Iterator it3 = this.f5464m;
            if (!it3.hasNext()) {
                this.f5465n = 2;
                this.o = null;
                return false;
            }
            Object next = it3.next();
            h hVar = (h) this.f5466p;
            it = (Iterator) hVar.c.b(hVar.f5470b.b(next));
        } while (!it.hasNext());
        this.o = it;
        this.f5465n = 1;
        return true;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (this.f5465n == -1) {
                    b();
                }
                return this.f5465n == 1;
            case 1:
                int i7 = this.f5465n;
                if (i7 == 1) {
                    return true;
                }
                if (i7 == 2) {
                    return false;
                }
                return d();
            default:
                if (this.f5465n == -1) {
                    c();
                }
                return this.f5465n == 1;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                if (this.f5465n == -1) {
                    b();
                }
                if (this.f5465n == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.o;
                this.o = null;
                this.f5465n = -1;
                return obj;
            case 1:
                int i7 = this.f5465n;
                if (i7 == 2) {
                    throw new NoSuchElementException();
                }
                if (i7 == 0 && !d()) {
                    throw new NoSuchElementException();
                }
                this.f5465n = 0;
                Iterator it = (Iterator) this.o;
                A7.m.c(it);
                return it.next();
            default:
                if (this.f5465n == -1) {
                    c();
                }
                if (this.f5465n == 0) {
                    throw new NoSuchElementException();
                }
                Object obj2 = this.o;
                this.o = null;
                this.f5465n = -1;
                return obj2;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public f(h hVar) {
        this.l = 1;
        this.f5466p = hVar;
        this.f5464m = hVar.f5469a.iterator();
    }

    public f(j jVar) {
        this.l = 2;
        this.f5466p = jVar;
        this.f5464m = ((k) jVar.f5474b).iterator();
        this.f5465n = -1;
    }
}
