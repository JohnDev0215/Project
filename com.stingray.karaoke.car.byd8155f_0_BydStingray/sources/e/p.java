package E;

import G.M;
import G.N;
import G.O;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class p implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f1380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M f1381b;
    public final long c;
    public final /* synthetic */ boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ M f1382e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1383f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f1384g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ g0.e f1385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ g0.f f1386i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f1387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1388k;
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f1389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ D f1390n;

    public p(long j3, boolean z6, l lVar, M m10, int i7, int i10, g0.e eVar, g0.f fVar, boolean z10, int i11, int i12, long j10, D d) {
        this.d = z6;
        this.f1382e = m10;
        this.f1383f = i7;
        this.f1384g = i10;
        this.f1385h = eVar;
        this.f1386i = fVar;
        this.f1387j = z10;
        this.f1388k = i11;
        this.l = i12;
        this.f1389m = j10;
        this.f1390n = d;
        this.f1380a = lVar;
        this.f1381b = m10;
        this.c = io.sentry.config.a.f(z6 ? a1.a.h(j3) : Integer.MAX_VALUE, z6 ? Integer.MAX_VALUE : a1.a.g(j3), 5);
    }

    @Override // G.O
    public final N a(int i7, int i10, int i11, long j3) {
        return b(j3, i7);
    }

    public final u b(long j3, int i7) {
        l lVar = this.f1380a;
        Object objC = lVar.c(i7);
        Object objI = lVar.f1362b.i(i7);
        List listA = this.f1381b.a(j3, i7);
        int i10 = i7 == this.f1383f + (-1) ? 0 : this.f1384g;
        return new u(i7, listA, this.d, this.f1385h, this.f1386i, this.f1382e.f2515m.getLayoutDirection(), this.f1387j, this.f1388k, this.l, i10, this.f1389m, objC, objI, this.f1390n.f1312n, j3);
    }
}
