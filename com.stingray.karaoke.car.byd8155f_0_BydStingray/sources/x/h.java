package X;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object[] f9961n;
    public final k o;

    public h(int i7, int i10, int i11, Object[] objArr, Object[] objArr2) {
        super(i7, i10);
        this.f9961n = objArr2;
        int i12 = (i10 - 1) & (-32);
        this.o = new k(objArr, i7 > i12 ? i12 : i7, i12, i11);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        k kVar = this.o;
        if (kVar.hasNext()) {
            this.l++;
            return kVar.next();
        }
        int i7 = this.l;
        this.l = i7 + 1;
        return this.f9961n[i7 - kVar.f9947m];
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i7 = this.l;
        k kVar = this.o;
        int i10 = kVar.f9947m;
        if (i7 <= i10) {
            this.l = i7 - 1;
            return kVar.previous();
        }
        int i11 = i7 - 1;
        this.l = i11;
        return this.f9961n[i11 - i10];
    }
}
