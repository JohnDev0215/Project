package K;

import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0331b0 f3398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final O.K f3399b;
    public final T0.y c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3400e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final O.Q f3401f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final T0.s f3402g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final N0 f3403h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final N f3404i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final T f3405j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final InterfaceC2782k f3406k;
    public final int l;

    public u0(C0331b0 c0331b0, O.K k10, T0.y yVar, boolean z6, boolean z10, O.Q q4, T0.s sVar, N0 n02, N n10, C0365x c0365x, int i7) {
        U u4 = X.f3202a;
        this.f3398a = c0331b0;
        this.f3399b = k10;
        this.c = yVar;
        this.d = z6;
        this.f3400e = z10;
        this.f3401f = q4;
        this.f3402g = sVar;
        this.f3403h = n02;
        this.f3404i = n10;
        this.f3405j = u4;
        this.f3406k = c0365x;
        this.l = i7;
    }

    public final void a(List list) {
        M.t tVar = this.f3398a.d;
        ArrayList arrayListA0 = AbstractC1998m.A0(list);
        arrayListA0.add(0, new T0.k());
        this.f3406k.b(tVar.n(arrayListA0));
    }
}
