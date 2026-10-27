package X;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class i extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g f9962n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k f9963p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9964q;

    public i(g gVar, int i7) {
        super(i7, gVar.d());
        this.f9962n = gVar;
        this.o = gVar.s();
        this.f9964q = -1;
        c();
    }

    @Override // X.a, java.util.ListIterator
    public final void add(Object obj) {
        b();
        int i7 = this.l;
        g gVar = this.f9962n;
        gVar.add(i7, obj);
        this.l++;
        this.f9947m = gVar.d();
        this.o = gVar.s();
        this.f9964q = -1;
        c();
    }

    public final void b() {
        if (this.o != this.f9962n.s()) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public final void c() {
        g gVar = this.f9962n;
        Object[] objArr = gVar.f9958q;
        if (objArr == null) {
            this.f9963p = null;
            return;
        }
        int i7 = (gVar.f9960s - 1) & (-32);
        int i10 = this.l;
        if (i10 > i7) {
            i10 = i7;
        }
        int i11 = (gVar.o / 5) + 1;
        k kVar = this.f9963p;
        if (kVar == null) {
            this.f9963p = new k(objArr, i10, i7, i11);
            return;
        }
        kVar.l = i10;
        kVar.f9947m = i7;
        kVar.f9966n = i11;
        if (kVar.o.length < i11) {
            kVar.o = new Object[i11];
        }
        kVar.o[0] = objArr;
        ?? r02 = i10 == i7 ? 1 : 0;
        kVar.f9967p = r02;
        kVar.c(i10 - r02, 1);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        b();
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i7 = this.l;
        this.f9964q = i7;
        k kVar = this.f9963p;
        g gVar = this.f9962n;
        if (kVar == null) {
            Object[] objArr = gVar.f9959r;
            this.l = i7 + 1;
            return objArr[i7];
        }
        if (kVar.hasNext()) {
            this.l++;
            return kVar.next();
        }
        Object[] objArr2 = gVar.f9959r;
        int i10 = this.l;
        this.l = i10 + 1;
        return objArr2[i10 - kVar.f9947m];
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        b();
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i7 = this.l;
        this.f9964q = i7 - 1;
        k kVar = this.f9963p;
        g gVar = this.f9962n;
        if (kVar == null) {
            Object[] objArr = gVar.f9959r;
            int i10 = i7 - 1;
            this.l = i10;
            return objArr[i10];
        }
        int i11 = kVar.f9947m;
        if (i7 <= i11) {
            this.l = i7 - 1;
            return kVar.previous();
        }
        Object[] objArr2 = gVar.f9959r;
        int i12 = i7 - 1;
        this.l = i12;
        return objArr2[i12 - i11];
    }

    @Override // X.a, java.util.ListIterator, java.util.Iterator
    public final void remove() {
        b();
        int i7 = this.f9964q;
        if (i7 == -1) {
            throw new IllegalStateException();
        }
        g gVar = this.f9962n;
        gVar.l(i7);
        int i10 = this.f9964q;
        if (i10 < this.l) {
            this.l = i10;
        }
        this.f9947m = gVar.d();
        this.o = gVar.s();
        this.f9964q = -1;
        c();
    }

    @Override // X.a, java.util.ListIterator
    public final void set(Object obj) {
        b();
        int i7 = this.f9964q;
        if (i7 == -1) {
            throw new IllegalStateException();
        }
        g gVar = this.f9962n;
        gVar.set(i7, obj);
        this.o = gVar.s();
        c();
    }
}
