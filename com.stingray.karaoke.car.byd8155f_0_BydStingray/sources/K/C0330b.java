package K;

import a.AbstractC0904a;
import k0.C1591c;
import n0.C1877f;
import n0.C1884m;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0330b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3218m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f3219n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0330b(long j3, int i7) {
        super(1);
        this.f3218m = i7;
        this.f3219n = j3;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3218m) {
            case 0:
                C1591c c1591c = (C1591c) obj;
                float fD = m0.f.d(c1591c.l.e()) / 2.0f;
                C1877f c1877fY = AbstractC0904a.y(c1591c, fD);
                C1884m c1884m = C1884m.f19441a;
                long j3 = this.f3219n;
                break;
            case 1:
                ((L0.j) obj).l(O.x.c, new O.w(O.l, this.f3219n, 2, true));
                break;
            case 2:
                io.sentry.U u4 = (io.sentry.U) obj;
                A7.m.f(u4, "it");
                long j10 = this.f3219n;
                if (j10 > 0) {
                    u4.A(Long.valueOf(j10), "http.request_content_length");
                }
                break;
            default:
                io.sentry.U u10 = (io.sentry.U) obj;
                A7.m.f(u10, "it");
                long j11 = this.f3219n;
                if (j11 > 0) {
                    u10.A(Long.valueOf(j11), "http.response_content_length");
                }
                break;
        }
        return m7.q.f19267a;
    }
}
