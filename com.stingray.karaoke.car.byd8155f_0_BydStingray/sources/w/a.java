package W;

import java.util.List;
import n7.AbstractC1989d;

/* JADX INFO: loaded from: classes.dex */
public final class a extends AbstractC1989d {
    public final X.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9668m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f9669n;

    public a(X.c cVar, int i7, int i10) {
        this.l = cVar;
        this.f9668m = i7;
        com.bumptech.glide.d.t(i7, i10, cVar.d());
        this.f9669n = i10 - i7;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.f9669n;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        com.bumptech.glide.d.r(i7, this.f9669n);
        return this.l.get(this.f9668m + i7);
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final List subList(int i7, int i10) {
        com.bumptech.glide.d.t(i7, i10, this.f9669n);
        int i11 = this.f9668m;
        return new a(this.l, i7 + i11, i11 + i10);
    }
}
