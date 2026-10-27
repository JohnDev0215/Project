package J2;

import B2.C0073m;
import B2.H;
import B2.L;
import B2.r;
import B2.s;
import B2.t;
import B2.v;
import B2.w;
import e2.C1207o;
import e2.C1208p;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3049a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3050b;

    public a(int i7) {
        if ((i7 & 1) != 0) {
            this.f3050b = new H("image/jpeg", 65496, 2);
        } else {
            this.f3050b = new b();
        }
    }

    private final void d() {
    }

    private final void h(long j3, long j10) {
    }

    @Override // B2.r
    public final void a() {
        switch (this.f3049a) {
            case 0:
                ((r) this.f3050b).a();
                break;
        }
    }

    @Override // B2.r
    public final void b(t tVar) {
        switch (this.f3049a) {
            case 0:
                ((r) this.f3050b).b(tVar);
                break;
            default:
                T t10 = (T) tVar;
                L lU = t10.u(0, 3);
                t10.w(new w(-9223372036854775807L));
                t10.e();
                C1208p c1208p = (C1208p) this.f3050b;
                C1207o c1207oA = c1208p.a();
                c1207oA.f14246m = e2.L.l("text/x-unknown");
                c1207oA.f14244j = c1208p.f14316n;
                lU.d(new C1208p(c1207oA));
                break;
        }
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) {
        switch (this.f3049a) {
            case 0:
                return ((r) this.f3050b).c(sVar, vVar);
            default:
                return ((C0073m) sVar).s(Integer.MAX_VALUE) == -1 ? -1 : 0;
        }
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        switch (this.f3049a) {
            case 0:
                ((r) this.f3050b).e(j3, j10);
                break;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        switch (this.f3049a) {
            case 0:
                return ((r) this.f3050b).f(sVar);
            default:
                return true;
        }
    }

    public a(C1208p c1208p) {
        this.f3050b = c1208p;
    }
}
