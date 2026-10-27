package Q5;

import C0.C0102i;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import g1.C1278e;
import g1.C1283j;
import h2.AbstractC1347d;
import java.io.IOException;
import n0.AbstractC1861K;
import n0.C1883l;
import n0.C1884m;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e3 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ T.X f6773m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1283j f6774n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f6775p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f6776q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f6777r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Integer f6778s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e3(T.X x8, C1283j c1283j, InterfaceC2772a interfaceC2772a, long j3, long j10, String str, Integer num) {
        super(2);
        this.f6773m = x8;
        this.f6774n = c1283j;
        this.o = interfaceC2772a;
        this.f6775p = j3;
        this.f6776q = j10;
        this.f6777r = str;
        this.f6778s = num;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
        g0.l lVar;
        T.S s6;
        float f10;
        m7.q qVar;
        C1278e c1278e;
        boolean z6;
        C0761q c0761q = (C0761q) obj;
        int iIntValue = ((Number) obj2).intValue() & 3;
        m7.q qVar2 = m7.q.f19267a;
        if (iIntValue == 2 && c0761q.x()) {
            c0761q.K();
            return qVar2;
        }
        this.f6773m.setValue(qVar2);
        C1283j c1283j = this.f6774n;
        int i7 = c1283j.f14726b;
        c1283j.e();
        c0761q.Q(442051442);
        C1283j c1283j2 = (C1283j) c1283j.d().f14705a;
        C1278e c1278eC = c1283j2.c();
        C1278e c1278eC2 = c1283j2.c();
        m6.b bVar = m6.c.f19252a;
        float fB0 = bVar.B0(c0761q);
        g0.l lVar2 = g0.l.f14684a;
        g0.o oVarD = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(lVar2, 1.0f), 1.0f);
        c0761q.Q(1261192594);
        Object objG = c0761q.G();
        T.S s10 = C0753m.f8270a;
        if (objG == s10) {
            objG = I1.f6392p;
            c0761q.a0(objG);
        }
        c0761q.p(false);
        g0.o oVarB = androidx.compose.foundation.a.b(h6.i.c(h6.q.m(C1283j.b(oVarD, c1278eC, (InterfaceC2782k) objG), J.e.a(bVar.f0(c0761q))), 2, this.f6775p, J.e.a(bVar.f0(c0761q))), this.f6776q, AbstractC1861K.f19381a);
        C0.K kE = AbstractC0133o.e(g0.b.f14664p, false);
        int i10 = c0761q.f8304P;
        InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
        g0.o oVarD2 = g0.a.d(c0761q, oVarB);
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
        if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i10))) {
            AbstractC1347d.l(i10, c0761q, i10, c0158h);
        }
        C0735d.V(c0761q, oVarD2, C0160j.c);
        c0761q.Q(2135935199);
        Integer num = this.f6778s;
        if (num != null) {
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar2, 0.5f);
            AbstractC2268a abstractC2268aW = l9.l.W(num.intValue(), c0761q, 0);
            C0.O o = C0102i.f902a;
            long j3 = l6.d.c(c0761q).f18700e;
            s6 = s10;
            lVar = lVar2;
            f10 = fB0;
            qVar = qVar2;
            c1278e = c1278eC2;
            h6.q.a(abstractC2268aW, null, oVarC, null, o, 0.0f, new C1883l(j3, 5, C1884m.f19441a.a(j3, 5)), c0761q, 25008, 40);
            z6 = false;
        } else {
            lVar = lVar2;
            s6 = s10;
            f10 = fB0;
            qVar = qVar2;
            c1278e = c1278eC2;
            z6 = false;
        }
        c0761q.p(z6);
        c0761q.p(true);
        g0.o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
        c0761q.Q(1261224422);
        float f11 = f10;
        boolean zF = c0761q.f(c1278eC) | c0761q.c(f11);
        Object objG2 = c0761q.G();
        if (zF || objG2 == s6) {
            objG2 = new R1(c1278eC, f11, 1);
            c0761q.a0(objG2);
        }
        c0761q.p(z6);
        com.bumptech.glide.c.x(C1283j.b(oVarD3, c1278e, (InterfaceC2782k) objG2), this.f6777r, 0L, 2, c0761q, 24576, 4);
        c0761q.p(z6);
        if (c1283j.f14726b == i7) {
            return qVar;
        }
        C0735d.i(this.o, c0761q);
        return qVar;
    }
}
