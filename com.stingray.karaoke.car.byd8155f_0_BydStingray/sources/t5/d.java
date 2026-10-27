package T5;

import T.C0761q;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import g0.o;
import h6.n;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC2785n {
    public final /* synthetic */ float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8676n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8677p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f8678q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f8679r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8680s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f8681t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8682u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ boolean f8683v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f8684w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ int f8685x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8686y;

    public d(float f10, boolean z6, boolean z10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, float f11, PlayerStatus playerStatus, InterfaceC2772a interfaceC2772a3, boolean z11, InterfaceC2772a interfaceC2772a4, boolean z12, int i7, int i10, InterfaceC2772a interfaceC2772a5) {
        this.l = f10;
        this.f8675m = z6;
        this.f8676n = z10;
        this.o = interfaceC2772a;
        this.f8677p = interfaceC2772a2;
        this.f8678q = f11;
        this.f8679r = playerStatus;
        this.f8680s = interfaceC2772a3;
        this.f8681t = z11;
        this.f8682u = interfaceC2772a4;
        this.f8683v = z12;
        this.f8684w = i7;
        this.f8685x = i10;
        this.f8686y = interfaceC2772a5;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        long j3;
        long j10;
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            float f10 = this.l;
            o oVarL = androidx.compose.foundation.layout.c.l(lVar, f10);
            boolean z6 = this.f8675m;
            int i7 = z6 ? R.drawable.ic_liked : R.drawable.ic_like;
            if (z6) {
                c0761q.Q(-1639791151);
                j3 = l6.d.c(c0761q).f18670C;
            } else {
                c0761q.Q(-1639790349);
                j3 = l6.d.c(c0761q).f18696b;
            }
            c0761q.p(false);
            l9.l.q(oVarL, i7, this.f8676n, j3, this.o, c0761q, 0, 0);
            l9.l.q(androidx.compose.foundation.layout.c.l(lVar, f10), R.drawable.ic_skip_previous, this.f8676n, 0L, this.f8677p, c0761q, 0, 8);
            l9.l.s(androidx.compose.foundation.layout.c.l(lVar, this.f8678q), this.f8679r, null, this.f8676n, this.f8680s, c0761q, 384);
            o oVarL2 = androidx.compose.foundation.layout.c.l(lVar, f10);
            boolean z10 = this.f8681t;
            boolean z11 = this.f8676n;
            l9.l.q(oVarL2, R.drawable.ic_skip_next, z10 && z11, 0L, this.f8682u, c0761q, 0, 8);
            o oVarL3 = androidx.compose.foundation.layout.c.l(lVar, f10);
            boolean z12 = this.f8683v;
            o oVarE = n.e(oVarL3, z12 ? 1.0f : 0.0f);
            boolean z13 = z11 && z12;
            if (this.f8684w == 0) {
                c0761q.Q(-1639717069);
                j10 = l6.d.c(c0761q).f18696b;
            } else {
                c0761q.Q(-1639716206);
                j10 = l6.d.c(c0761q).f18702f;
            }
            c0761q.p(false);
            l9.l.q(oVarE, this.f8685x, z13, j10, this.f8686y, c0761q, 0, 0);
        }
        return q.f19267a;
    }
}
