package Q5;

import D.AbstractC0127i;
import D.AbstractC0139v;
import D.C0141x;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.AbstractC2637h;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class V0 implements InterfaceC2786o {
    public final /* synthetic */ boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f6598m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ N0.L f6599n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f6600p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f6601q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ N0.L f6602r;

    public V0(boolean z6, String str, N0.L l, String str2, String str3, long j3, N0.L l10) {
        this.l = z6;
        this.f6598m = str;
        this.f6599n = l;
        this.o = str2;
        this.f6600p = str3;
        this.f6601q = j3;
        this.f6602r = l10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        A7.m.f((w.q) obj, "$this$AnimatedVisibility");
        T.Q0 q0B = AbstractC2637h.b(this.l ? 1.0f : 0.0f, AbstractC2631e.q(200, 0, AbstractC2614C.c, 2), "headerTextAlpha", null, c0761q, 3072, 20);
        g0.l lVar = g0.l.f14684a;
        g0.o oVarE = h6.n.e(lVar, ((Number) q0B.getValue()).floatValue());
        C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
        int i7 = c0761q.f8304P;
        InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
        g0.o oVarD = g0.a.d(c0761q, oVarE);
        InterfaceC0161k.f1681b.getClass();
        C0159i c0159i = C0160j.f1678b;
        c0761q.U();
        if (c0761q.f8303O) {
            c0761q.l(c0159i);
        } else {
            c0761q.d0();
        }
        C0735d.V(c0761q, c0141xA, C0160j.f1679e);
        C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
        C0158h c0158h = C0160j.f1680f;
        if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
            AbstractC1347d.l(i7, c0761q, i7, c0158h);
        }
        C0735d.V(c0761q, oVarD, C0160j.c);
        c0761q.Q(980786556);
        String str = this.f6598m;
        if (str != null && !Q8.h.M0(str)) {
            w0.c.t(androidx.compose.foundation.layout.c.d(lVar, 1.0f), str, this.f6599n, c0761q, 6);
            c0761q.Q(980794200);
            String str2 = this.o;
            if (str2 != null && !Q8.h.M0(str2)) {
                w0.c.r(androidx.compose.foundation.layout.c.d(lVar, 1.0f), str2, c0761q, 6);
            }
            c0761q.p(false);
            String str3 = this.f6600p;
            if (str3 != null && !Q8.h.M0(str3)) {
                w0.c.s(androidx.compose.foundation.layout.c.d(lVar, 1.0f), str3, this.f6601q, this.f6602r, c0761q, 6);
            }
        }
        c0761q.p(false);
        c0761q.p(true);
        return m7.q.f19267a;
    }
}
