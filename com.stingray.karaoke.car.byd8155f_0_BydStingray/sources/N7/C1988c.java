package n7;

import java.util.RandomAccess;

/* JADX INFO: renamed from: n7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1988c extends AbstractC1989d implements RandomAccess {
    public final AbstractC1989d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20154m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f20155n;

    public C1988c(AbstractC1989d abstractC1989d, int i7, int i10) {
        A7.m.f(abstractC1989d, "list");
        this.l = abstractC1989d;
        this.f20154m = i7;
        h6.r.c(i7, i10, abstractC1989d.d());
        this.f20155n = i10 - i7;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.f20155n;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        int i10 = this.f20155n;
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
        return this.l.get(this.f20154m + i7);
    }
}
