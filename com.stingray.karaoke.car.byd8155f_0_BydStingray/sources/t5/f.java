package T5;

import D.AbstractC0127i;
import D.C0119a;
import D.C0124f;
import D.P;
import D.S;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import V8.N;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import g0.o;
import h2.AbstractC1347d;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2785n {
    public final /* synthetic */ o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8700m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8701n;
    public final /* synthetic */ N o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f8702p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8703q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f8704r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8705s;

    public f(o oVar, boolean z6, InterfaceC2772a interfaceC2772a, N n10, PlayerStatus playerStatus, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3) {
        this.l = oVar;
        this.f8700m = z6;
        this.f8701n = interfaceC2772a;
        this.o = n10;
        this.f8702p = playerStatus;
        this.f8703q = interfaceC2772a2;
        this.f8704r = z10;
        this.f8705s = interfaceC2772a3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.f fVar = g0.b.f14670v;
            C0119a c0119a = AbstractC0127i.f1076a;
            S sB = P.b(new C0124f(m6.c.f19252a.F(c0761q)), fVar, c0761q, 48);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, this.l);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            o oVarD2 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 0.75f), 1.0f);
            InterfaceC2772a interfaceC2772a = this.f8701n;
            boolean z6 = this.f8700m;
            l9.l.q(oVarD2, R.drawable.ic_skip_previous, z6, 0L, interfaceC2772a, c0761q, 6, 8);
            l9.l.s(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), this.f8702p, Float.valueOf(((Number) com.bumptech.glide.c.R(this.o, c0761q, 0).getValue()).floatValue()), z6, this.f8703q, c0761q, 6);
            l9.l.q(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 0.75f), 1.0f), R.drawable.ic_skip_next, this.f8704r && z6, 0L, this.f8705s, c0761q, 6, 8);
            c0761q.p(true);
        }
        return q.f19267a;
    }
}
