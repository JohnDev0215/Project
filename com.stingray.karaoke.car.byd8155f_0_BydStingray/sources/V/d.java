package V;

import A7.m;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import n7.AbstractC1996k;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public final class d implements RandomAccess {
    public Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public a f9278m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9279n = 0;

    public d(Object[] objArr) {
        this.l = objArr;
    }

    public final void a(int i7, Object obj) {
        j(this.f9279n + 1);
        Object[] objArr = this.l;
        int i10 = this.f9279n;
        if (i7 != i10) {
            AbstractC1996k.D(i7 + 1, i7, i10, objArr, objArr);
        }
        objArr[i7] = obj;
        this.f9279n++;
    }

    public final void b(Object obj) {
        j(this.f9279n + 1);
        Object[] objArr = this.l;
        int i7 = this.f9279n;
        objArr[i7] = obj;
        this.f9279n = i7 + 1;
    }

    public final void c(int i7, d dVar) {
        if (dVar.l()) {
            return;
        }
        j(this.f9279n + dVar.f9279n);
        Object[] objArr = this.l;
        int i10 = this.f9279n;
        if (i7 != i10) {
            AbstractC1996k.D(dVar.f9279n + i7, i7, i10, objArr, objArr);
        }
        AbstractC1996k.D(i7, 0, dVar.f9279n, dVar.l, objArr);
        this.f9279n += dVar.f9279n;
    }

    public final void d(int i7, List list) {
        if (list.isEmpty()) {
            return;
        }
        j(list.size() + this.f9279n);
        Object[] objArr = this.l;
        if (i7 != this.f9279n) {
            AbstractC1996k.D(list.size() + i7, i7, this.f9279n, objArr, objArr);
        }
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            objArr[i7 + i10] = list.get(i10);
        }
        this.f9279n = list.size() + this.f9279n;
    }

    public final boolean e(int i7, Collection collection) {
        int i10 = 0;
        if (collection.isEmpty()) {
            return false;
        }
        j(collection.size() + this.f9279n);
        Object[] objArr = this.l;
        if (i7 != this.f9279n) {
            AbstractC1996k.D(collection.size() + i7, i7, this.f9279n, objArr, objArr);
        }
        for (Object obj : collection) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            objArr[i10 + i7] = obj;
            i10 = i11;
        }
        this.f9279n = collection.size() + this.f9279n;
        return true;
    }

    public final List f() {
        a aVar = this.f9278m;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this);
        this.f9278m = aVar2;
        return aVar2;
    }

    public final void g() {
        Object[] objArr = this.l;
        int i7 = this.f9279n;
        while (true) {
            i7--;
            if (-1 >= i7) {
                this.f9279n = 0;
                return;
            }
            objArr[i7] = null;
        }
    }

    public final boolean i(Object obj) {
        int i7 = this.f9279n - 1;
        if (i7 >= 0) {
            for (int i10 = 0; !m.a(this.l[i10], obj); i10++) {
                if (i10 != i7) {
                }
            }
            return true;
        }
        return false;
    }

    public final void j(int i7) {
        Object[] objArr = this.l;
        if (objArr.length < i7) {
            Object[] objArrCopyOf = Arrays.copyOf(objArr, Math.max(i7, objArr.length * 2));
            m.e(objArrCopyOf, "copyOf(this, newSize)");
            this.l = objArrCopyOf;
        }
    }

    public final int k(Object obj) {
        int i7 = this.f9279n;
        if (i7 <= 0) {
            return -1;
        }
        Object[] objArr = this.l;
        int i10 = 0;
        while (!m.a(obj, objArr[i10])) {
            i10++;
            if (i10 >= i7) {
                return -1;
            }
        }
        return i10;
    }

    public final boolean l() {
        return this.f9279n == 0;
    }

    public final boolean m() {
        return this.f9279n != 0;
    }

    public final boolean n(Object obj) {
        int iK = k(obj);
        if (iK < 0) {
            return false;
        }
        o(iK);
        return true;
    }

    public final Object o(int i7) {
        Object[] objArr = this.l;
        Object obj = objArr[i7];
        int i10 = this.f9279n;
        if (i7 != i10 - 1) {
            AbstractC1996k.D(i7, i7 + 1, i10, objArr, objArr);
        }
        int i11 = this.f9279n - 1;
        this.f9279n = i11;
        objArr[i11] = null;
        return obj;
    }

    public final void p(int i7, int i10) {
        if (i10 > i7) {
            int i11 = this.f9279n;
            if (i10 < i11) {
                Object[] objArr = this.l;
                AbstractC1996k.D(i7, i10, i11, objArr, objArr);
            }
            int i12 = this.f9279n;
            int i13 = i12 - (i10 - i7);
            int i14 = i12 - 1;
            if (i13 <= i14) {
                int i15 = i13;
                while (true) {
                    this.l[i15] = null;
                    if (i15 == i14) {
                        break;
                    } else {
                        i15++;
                    }
                }
            }
            this.f9279n = i13;
        }
    }

    public final void q(Comparator comparator) {
        Arrays.sort(this.l, 0, this.f9279n, comparator);
    }
}
