package V5;

import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import Q.J;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import com.stingray.karaoke.car.ui.composables.swipeRefresh.CircularProgressPainter;
import h2.AbstractC1347d;
import h6.q;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC2786o {
    public final /* synthetic */ h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f9499m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ CircularProgressPainter f9500n;

    public e(h hVar, long j3, CircularProgressPainter circularProgressPainter) {
        this.l = hVar;
        this.f9499m = j3;
        this.f9500n = circularProgressPainter;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.g(zBooleanValue) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
            K kE = AbstractC0133o.e(g0.b.f14664p, false);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarC);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            if (zBooleanValue) {
                c0761q.Q(-828288494);
                h hVar = this.l;
                float f10 = hVar.f9509b;
                float f11 = hVar.c;
                J.a(androidx.compose.foundation.layout.c.l(lVar, (f10 + f11) * 2), this.f9499m, f11, 0L, 0, c0761q, 0, 24);
                c0761q.p(false);
            } else {
                c0761q.Q(-827958251);
                q.a(this.f9500n, "Refreshing", null, null, null, 0.0f, null, c0761q, 48, 124);
                c0761q.p(false);
            }
            c0761q.p(true);
        }
        return m7.q.f19267a;
    }
}
