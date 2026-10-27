package E5;

import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class C implements Iterator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1751m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Map.Entry f1752n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ AbstractMap f1753p;
    public final /* synthetic */ int l = 0;
    public Map.Entry o = null;

    public C(l5.n nVar) {
        this.f1753p = nVar;
        this.f1752n = nVar.f18660q.o;
        this.f1751m = nVar.f18659p;
    }

    public D a() {
        D d = (D) this.f1752n;
        E e10 = (E) this.f1753p;
        if (d == e10.f1763n) {
            throw new NoSuchElementException();
        }
        if (e10.f1764p != this.f1751m) {
            throw new ConcurrentModificationException();
        }
        this.f1752n = d.o;
        this.o = d;
        return d;
    }

    public l5.m b() {
        l5.m mVar = (l5.m) this.f1752n;
        l5.n nVar = (l5.n) this.f1753p;
        if (mVar == nVar.f18660q) {
            throw new NoSuchElementException();
        }
        if (nVar.f18659p != this.f1751m) {
            throw new ConcurrentModificationException();
        }
        this.f1752n = mVar.o;
        this.o = mVar;
        return mVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (((D) this.f1752n) != ((E) this.f1753p).f1763n) {
                }
                break;
            default:
                if (((l5.m) this.f1752n) != ((l5.n) this.f1753p).f18660q) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.l) {
            case 0:
                return a();
            default:
                return b();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                D d = (D) this.o;
                if (d == null) {
                    throw new IllegalStateException();
                }
                E e10 = (E) this.f1753p;
                e10.d(d, true);
                this.o = null;
                this.f1751m = e10.f1764p;
                return;
            default:
                l5.m mVar = (l5.m) this.o;
                if (mVar == null) {
                    throw new IllegalStateException();
                }
                l5.n nVar = (l5.n) this.f1753p;
                nVar.d(mVar, true);
                this.o = null;
                this.f1751m = nVar.f18659p;
                return;
        }
    }

    public C(E e10) {
        this.f1753p = e10;
        this.f1752n = e10.f1763n.o;
        this.f1751m = e10.f1764p;
    }
}
