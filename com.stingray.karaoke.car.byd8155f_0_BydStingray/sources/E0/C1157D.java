package e0;

import java.util.List;
import java.util.ListIterator;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.C1984A;

/* JADX INFO: renamed from: e0.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1157D implements ListIterator, B7.a {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f13677m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f13678n;

    public C1157D(Q8.e eVar, int i7) {
        this.f13678n = eVar;
        this.f13677m = ((List) eVar.f7222m).listIterator(AbstractC1998m.R(i7, eVar));
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.l) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            case 1:
                ListIterator listIterator = (ListIterator) this.f13677m;
                listIterator.add(obj);
                listIterator.previous();
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (((A7.x) this.f13677m).l < ((C1158E) this.f13678n).o - 1) {
                }
                break;
        }
        return ((ListIterator) this.f13677m).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.l) {
            case 0:
                if (((A7.x) this.f13677m).l >= 0) {
                }
                break;
        }
        return ((ListIterator) this.f13677m).hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                A7.x xVar = (A7.x) this.f13677m;
                int i7 = xVar.l + 1;
                C1158E c1158e = (C1158E) this.f13678n;
                AbstractC1179s.a(i7, c1158e.o);
                xVar.l = i7;
                break;
        }
        return ((ListIterator) this.f13677m).previous();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.l) {
            case 0:
                return ((A7.x) this.f13677m).l + 1;
            case 1:
                return AbstractC1999n.C((C1984A) this.f13678n) - ((ListIterator) this.f13677m).previousIndex();
            default:
                return AbstractC1999n.C((Q8.e) this.f13678n) - ((ListIterator) this.f13677m).previousIndex();
        }
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.l) {
            case 0:
                A7.x xVar = (A7.x) this.f13677m;
                int i7 = xVar.l;
                C1158E c1158e = (C1158E) this.f13678n;
                AbstractC1179s.a(i7, c1158e.o);
                xVar.l = i7 - 1;
                break;
        }
        return ((ListIterator) this.f13677m).next();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        switch (this.l) {
            case 0:
                return ((A7.x) this.f13677m).l;
            case 1:
                return AbstractC1999n.C((C1984A) this.f13678n) - ((ListIterator) this.f13677m).nextIndex();
            default:
                return AbstractC1999n.C((Q8.e) this.f13678n) - ((ListIterator) this.f13677m).nextIndex();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            case 1:
                ((ListIterator) this.f13677m).remove();
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.l) {
            case 0:
                throw new IllegalStateException("Cannot modify a state list through an iterator");
            case 1:
                ((ListIterator) this.f13677m).set(obj);
                return;
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C1157D(C1984A c1984a, int i7) {
        this.f13678n = c1984a;
        this.f13677m = c1984a.l.listIterator(AbstractC1998m.R(i7, c1984a));
    }

    public C1157D(A7.x xVar, C1158E c1158e) {
        this.f13677m = xVar;
        this.f13678n = c1158e;
    }
}
