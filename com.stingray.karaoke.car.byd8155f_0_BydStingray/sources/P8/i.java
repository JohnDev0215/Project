package P8;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import u.C2453A;
import u.C2485y;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Iterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f5471m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5472n;
    public final Object o;

    public i(Object obj, Map map) {
        this.l = 1;
        this.f5471m = obj;
        this.o = map;
    }

    public void b() {
        Object objB;
        int i7 = this.f5472n;
        j jVar = (j) this.o;
        if (i7 == -2) {
            objB = ((InterfaceC2772a) jVar.f5474b).a();
        } else {
            InterfaceC2782k interfaceC2782k = (InterfaceC2782k) jVar.c;
            Object obj = this.f5471m;
            A7.m.c(obj);
            objB = interfaceC2782k.b(obj);
        }
        this.f5471m = objB;
        this.f5472n = objB == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (this.f5472n < 0) {
                    b();
                }
                return this.f5472n == 1;
            case 1:
                return this.f5472n < ((Map) this.o).size();
            default:
                return ((l) this.f5471m).hasNext();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                if (this.f5472n < 0) {
                    b();
                }
                if (this.f5472n == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.f5471m;
                A7.m.d(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
                this.f5472n = -1;
                return obj;
            case 1:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object obj2 = this.f5471m;
                this.f5472n++;
                Object obj3 = ((Map) this.o).get(obj2);
                if (obj3 != null) {
                    this.f5471m = ((Z.a) obj3).f10806b;
                    return obj2;
                }
                throw new ConcurrentModificationException("Hash code of an element (" + obj2 + ") has changed after it was added to the persistent set.");
            default:
                return ((l) this.f5471m).next();
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
                int i7 = this.f5472n;
                if (i7 != -1) {
                    ((C2453A) this.o).k(i7);
                    this.f5472n = -1;
                    return;
                }
                return;
        }
    }

    public i(j jVar) {
        this.l = 0;
        this.o = jVar;
        this.f5472n = -2;
    }

    public i(C2453A c2453a) {
        this.l = 2;
        this.o = c2453a;
        this.f5472n = -1;
        this.f5471m = n.G(new C2485y(c2453a, this, null));
    }
}
