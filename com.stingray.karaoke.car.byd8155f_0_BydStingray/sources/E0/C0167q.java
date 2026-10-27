package E0;

import e0.AbstractC1179s;
import e0.C1178r;
import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import o7.C2049a;
import o7.C2050b;

/* JADX INFO: renamed from: E0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0167q implements ListIterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1691m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1692n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f1693p;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0167q(C0168s c0168s, int i7, int i10) {
        this(c0168s, (i10 & 1) != 0 ? 0 : i7, 0, c0168s.o);
        this.l = 0;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                d();
                int i7 = this.f1691m + 1;
                C1178r c1178r = (C1178r) this.f1693p;
                c1178r.add(i7, obj);
                this.f1692n = -1;
                this.f1691m++;
                this.o = c1178r.s();
                return;
            case 2:
                b();
                int i10 = this.f1691m;
                this.f1691m = i10 + 1;
                C2049a c2049a = (C2049a) this.f1693p;
                c2049a.add(i10, obj);
                this.f1692n = -1;
                this.o = ((AbstractList) c2049a).modCount;
                return;
            default:
                c();
                int i11 = this.f1691m;
                this.f1691m = i11 + 1;
                C2050b c2050b = (C2050b) this.f1693p;
                c2050b.add(i11, obj);
                this.f1692n = -1;
                this.o = ((AbstractList) c2050b).modCount;
                return;
        }
    }

    public void b() {
        if (((AbstractList) ((C2049a) this.f1693p).f20542p).modCount != this.o) {
            throw new ConcurrentModificationException();
        }
    }

    public void c() {
        if (((AbstractList) ((C2050b) this.f1693p)).modCount != this.o) {
            throw new ConcurrentModificationException();
        }
    }

    public void d() {
        if (((C1178r) this.f1693p).s() != this.o) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                return this.f1691m < this.o;
            case 1:
                return this.f1691m < ((C1178r) this.f1693p).size() - 1;
            case 2:
                return this.f1691m < ((C2049a) this.f1693p).f20541n;
            default:
                return this.f1691m < ((C2050b) this.f1693p).f20543m;
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.l) {
            case 0:
                if (this.f1691m > this.f1692n) {
                }
                break;
            case 1:
                if (this.f1691m >= 0) {
                }
                break;
            case 2:
                if (this.f1691m > 0) {
                }
                break;
            default:
                if (this.f1691m > 0) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                Object[] objArr = ((C0168s) this.f1693p).l;
                int i7 = this.f1691m;
                this.f1691m = i7 + 1;
                Object obj = objArr[i7];
                A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
                return (g0.n) obj;
            case 1:
                d();
                int i10 = this.f1691m + 1;
                this.f1692n = i10;
                C1178r c1178r = (C1178r) this.f1693p;
                AbstractC1179s.a(i10, c1178r.size());
                Object obj2 = c1178r.get(i10);
                this.f1691m = i10;
                return obj2;
            case 2:
                b();
                int i11 = this.f1691m;
                C2049a c2049a = (C2049a) this.f1693p;
                if (i11 >= c2049a.f20541n) {
                    throw new NoSuchElementException();
                }
                this.f1691m = i11 + 1;
                this.f1692n = i11;
                return c2049a.l[c2049a.f20540m + i11];
            default:
                c();
                int i12 = this.f1691m;
                C2050b c2050b = (C2050b) this.f1693p;
                if (i12 >= c2050b.f20543m) {
                    throw new NoSuchElementException();
                }
                this.f1691m = i12 + 1;
                this.f1692n = i12;
                return c2050b.l[i12];
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.l) {
        }
        return this.f1691m;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.l) {
            case 0:
                Object[] objArr = ((C0168s) this.f1693p).l;
                int i7 = this.f1691m - 1;
                this.f1691m = i7;
                Object obj = objArr[i7];
                A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
                return (g0.n) obj;
            case 1:
                d();
                int i10 = this.f1691m;
                C1178r c1178r = (C1178r) this.f1693p;
                AbstractC1179s.a(i10, c1178r.size());
                int i11 = this.f1691m;
                this.f1692n = i11;
                this.f1691m--;
                return c1178r.get(i11);
            case 2:
                b();
                int i12 = this.f1691m;
                if (i12 <= 0) {
                    throw new NoSuchElementException();
                }
                int i13 = i12 - 1;
                this.f1691m = i13;
                this.f1692n = i13;
                C2049a c2049a = (C2049a) this.f1693p;
                return c2049a.l[c2049a.f20540m + i13];
            default:
                c();
                int i14 = this.f1691m;
                if (i14 <= 0) {
                    throw new NoSuchElementException();
                }
                int i15 = i14 - 1;
                this.f1691m = i15;
                this.f1692n = i15;
                return ((C2050b) this.f1693p).l[i15];
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        switch (this.l) {
        }
        return this.f1691m - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                d();
                int i7 = this.f1691m;
                C1178r c1178r = (C1178r) this.f1693p;
                c1178r.remove(i7);
                this.f1691m--;
                this.f1692n = -1;
                this.o = c1178r.s();
                return;
            case 2:
                b();
                int i10 = this.f1692n;
                if (i10 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                C2049a c2049a = (C2049a) this.f1693p;
                c2049a.l(i10);
                this.f1691m = this.f1692n;
                this.f1692n = -1;
                this.o = ((AbstractList) c2049a).modCount;
                return;
            default:
                c();
                int i11 = this.f1692n;
                if (i11 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                C2050b c2050b = (C2050b) this.f1693p;
                c2050b.l(i11);
                this.f1691m = this.f1692n;
                this.f1692n = -1;
                this.o = ((AbstractList) c2050b).modCount;
                return;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                d();
                int i7 = this.f1692n;
                if (i7 < 0) {
                    throw new IllegalStateException("Cannot call set before the first call to next() or previous() or immediately after a call to add() or remove()");
                }
                C1178r c1178r = (C1178r) this.f1693p;
                c1178r.set(i7, obj);
                this.o = c1178r.s();
                return;
            case 2:
                b();
                int i10 = this.f1692n;
                if (i10 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((C2049a) this.f1693p).set(i10, obj);
                return;
            default:
                c();
                int i11 = this.f1692n;
                if (i11 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((C2050b) this.f1693p).set(i11, obj);
                return;
        }
    }

    public C0167q(C0168s c0168s, int i7, int i10, int i11) {
        this.l = 0;
        this.f1693p = c0168s;
        this.f1691m = i7;
        this.f1692n = i10;
        this.o = i11;
    }

    public C0167q(C2050b c2050b, int i7) {
        this.l = 3;
        A7.m.f(c2050b, "list");
        this.f1693p = c2050b;
        this.f1691m = i7;
        this.f1692n = -1;
        this.o = ((AbstractList) c2050b).modCount;
    }

    public C0167q(C1178r c1178r, int i7) {
        this.l = 1;
        this.f1693p = c1178r;
        this.f1691m = i7 - 1;
        this.f1692n = -1;
        this.o = c1178r.s();
    }

    public C0167q(C2049a c2049a, int i7) {
        this.l = 2;
        A7.m.f(c2049a, "list");
        this.f1693p = c2049a;
        this.f1691m = i7;
        this.f1692n = -1;
        this.o = ((AbstractList) c2049a).modCount;
    }
}
