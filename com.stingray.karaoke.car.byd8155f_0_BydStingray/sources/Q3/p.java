package Q3;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class p implements R3.a, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O3.j f6210a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final R3.e f6211b;
    public W3.k c;

    public p(O3.j jVar, X3.b bVar, W3.j jVar2) {
        this.f6210a = jVar;
        jVar2.getClass();
        R3.f fVarT0 = jVar2.f9791a.t0();
        this.f6211b = fVarT0;
        bVar.d(fVarT0);
        fVarT0.a(this);
    }

    public static int d(int i7, int i10) {
        int i11 = i7 / i10;
        if ((i7 ^ i10) < 0 && i11 * i10 != i7) {
            i11--;
        }
        return i7 - (i11 * i10);
    }

    @Override // R3.a
    public final void b() {
        this.f6210a.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
    }
}
