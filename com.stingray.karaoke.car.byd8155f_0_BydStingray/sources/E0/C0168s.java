package E0;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import n7.AbstractC1999n;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: E0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0168s implements List, B7.a {
    public int o;
    public Object[] l = new Object[16];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long[] f1699m = new long[16];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1700n = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1701p = true;

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ void add(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i7, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.f1700n = -1;
        q();
        this.f1701p = true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return (obj instanceof g0.n) && indexOf((g0.n) obj) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains((g0.n) it.next())) {
                return false;
            }
        }
        return true;
    }

    public final long d() {
        long jA = AbstractC0156f.a(Float.POSITIVE_INFINITY, false);
        int i7 = this.f1700n + 1;
        int iC = AbstractC1999n.C(this);
        if (i7 <= iC) {
            while (true) {
                long j3 = this.f1699m[i7];
                if (AbstractC0156f.h(j3, jA) < 0) {
                    jA = j3;
                }
                if (Float.intBitsToFloat((int) (jA >> 32)) < 0.0f && ((int) (4294967295L & jA)) != 0) {
                    return jA;
                }
                if (i7 == iC) {
                    break;
                }
                i7++;
            }
        }
        return jA;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        Object obj = this.l[i7];
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
        return (g0.n) obj;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof g0.n)) {
            return -1;
        }
        g0.n nVar = (g0.n) obj;
        int iC = AbstractC1999n.C(this);
        if (iC < 0) {
            return -1;
        }
        int i7 = 0;
        while (!A7.m.a(this.l[i7], nVar)) {
            if (i7 == iC) {
                return -1;
            }
            i7++;
        }
        return i7;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.o == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C0167q(this, 0, 7);
    }

    public final void l(g0.n nVar, float f10, boolean z6, InterfaceC2772a interfaceC2772a) {
        int i7 = this.f1700n;
        int i10 = i7 + 1;
        this.f1700n = i10;
        Object[] objArr = this.l;
        if (i10 >= objArr.length) {
            int length = objArr.length + 16;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, length);
            A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.l = objArrCopyOf;
            long[] jArrCopyOf = Arrays.copyOf(this.f1699m, length);
            A7.m.e(jArrCopyOf, "copyOf(this, newSize)");
            this.f1699m = jArrCopyOf;
        }
        Object[] objArr2 = this.l;
        int i11 = this.f1700n;
        objArr2[i11] = nVar;
        this.f1699m[i11] = AbstractC0156f.a(f10, z6);
        q();
        interfaceC2772a.a();
        this.f1700n = i7;
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof g0.n)) {
            return -1;
        }
        g0.n nVar = (g0.n) obj;
        for (int iC = AbstractC1999n.C(this); -1 < iC; iC--) {
            if (A7.m.a(this.l[iC], nVar)) {
                return iC;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new C0167q(this, 0, 7);
    }

    public final void q() {
        int i7 = this.f1700n + 1;
        int iC = AbstractC1999n.C(this);
        if (i7 <= iC) {
            while (true) {
                this.l[i7] = null;
                if (i7 == iC) {
                    break;
                } else {
                    i7++;
                }
            }
        }
        this.o = this.f1700n + 1;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i7) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i7, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.o;
    }

    @Override // java.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final List subList(int i7, int i10) {
        return new r(this, i7, i10);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return A7.l.a(this);
    }

    @Override // java.util.List, java.util.Collection
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i7) {
        return new C0167q(this, i7, 6);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return A7.l.b(this, objArr);
    }
}
