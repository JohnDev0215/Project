package W5;

import A.E;
import A.G0;
import A7.A;
import K.q0;
import Q.AbstractC0535v;
import Q.c1;
import Q5.C0606p0;
import Q5.C0623t2;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0761q;
import T.S;
import T.X;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.karaoke.car.utils.FontSize;
import e0.C1178r;
import g1.AbstractC1274a;
import g1.C1275b;
import g1.C1278e;
import g1.C1283j;
import g1.C1287n;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import k1.C1596a;
import n0.C1891t;
import n7.AbstractC1999n;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import w.C2577h;
import w.C2579j;
import w.F;
import w.G;
import w.u;
import x.x0;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class o extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9852m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9853n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9854p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9855q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ List f9856r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f9857s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(X x8, C1283j c1283j, InterfaceC2772a interfaceC2772a, List list, StingrayKaraokeTabScreen stingrayKaraokeTabScreen, InterfaceC2782k interfaceC2782k) {
        super(2);
        this.o = x8;
        this.f9854p = c1283j;
        this.f9855q = interfaceC2772a;
        this.f9856r = list;
        this.f9857s = stingrayKaraokeTabScreen;
        this.f9853n = interfaceC2782k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, k1.h, IOException {
        g0.l lVar;
        S s6;
        long j3;
        switch (this.f9852m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                int iIntValue = ((Number) obj2).intValue() & 3;
                m7.q qVar = m7.q.f19267a;
                if (iIntValue == 2 && c0761q.x()) {
                    c0761q.K();
                    return qVar;
                }
                ((X) this.o).setValue(qVar);
                C1283j c1283j = (C1283j) this.f9854p;
                int i7 = c1283j.f14726b;
                c1283j.e();
                c0761q.Q(-1224403216);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                List<StingrayKaraokeTabScreen> list = this.f9856r;
                for (StingrayKaraokeTabScreen stingrayKaraokeTabScreen : list) {
                    arrayList.add(c1283j.c());
                    arrayList2.add(c1283j.c());
                }
                c0761q.Q(1068889936);
                boolean z6 = false;
                int i10 = 0;
                for (Object obj3 : list) {
                    int i11 = i10 + 1;
                    if (i10 < 0) {
                        AbstractC1999n.I();
                        throw null;
                    }
                    StingrayKaraokeTabScreen stingrayKaraokeTabScreen2 = (StingrayKaraokeTabScreen) obj3;
                    C1278e c1278e = (C1278e) arrayList.get(i10);
                    C1278e c1278e2 = (C1278e) arrayList2.get(i10);
                    boolean z10 = ((StingrayKaraokeTabScreen) this.f9857s) == stingrayKaraokeTabScreen2 ? true : z6;
                    String strX = AbstractC2673a.X(stingrayKaraokeTabScreen2.getLabelId(), c0761q);
                    g0.l lVar2 = g0.l.f14684a;
                    c0761q.Q(784243520);
                    Object objG = c0761q.G();
                    S s10 = C0753m.f8270a;
                    if (objG == s10) {
                        objG = p.f9858m;
                        c0761q.a0(objG);
                    }
                    c0761q.p(z6);
                    g0.o oVarL = androidx.compose.foundation.layout.c.l(C1283j.b(lVar2, c1278e, (InterfaceC2782k) objG), m6.c.f19252a.V(c0761q));
                    c0761q.Q(784249877);
                    InterfaceC2782k interfaceC2782k = this.f9853n;
                    boolean zF = c0761q.f(interfaceC2782k) | c0761q.f(stingrayKaraokeTabScreen2);
                    Object objG2 = c0761q.G();
                    if (zF || objG2 == s10) {
                        objG2 = new C0606p0(interfaceC2782k, stingrayKaraokeTabScreen2, 4);
                        c0761q.a0(objG2);
                    }
                    c0761q.p(false);
                    g0.o oVarQ = com.bumptech.glide.c.Q(7, oVarL, (InterfaceC2772a) objG2, false);
                    AbstractC2268a abstractC2268aW = l9.l.W(stingrayKaraokeTabScreen2.getIconId(), c0761q, 0);
                    if (z10) {
                        c0761q.Q(784257110);
                        lVar = lVar2;
                        s6 = s10;
                        j3 = l6.d.c(c0761q).f18702f;
                    } else {
                        lVar = lVar2;
                        s6 = s10;
                        c0761q.Q(784257956);
                        j3 = l6.d.c(c0761q).f18708i;
                    }
                    c0761q.p(false);
                    S s11 = s6;
                    g0.l lVar3 = lVar;
                    ArrayList arrayList3 = arrayList2;
                    m7.q qVar2 = qVar;
                    AbstractC0535v.a(abstractC2268aW, strX, oVarQ, j3, c0761q, 0, 0);
                    c0761q.Q(784262288);
                    boolean zF2 = c0761q.f(c1278e);
                    Object objG3 = c0761q.G();
                    if (zF2 || objG3 == s11) {
                        objG3 = new C0623t2(c1278e, 12);
                        c0761q.a0(objG3);
                    }
                    c0761q.p(false);
                    g0.o oVarB = C1283j.b(lVar3, c1278e2, (InterfaceC2782k) objG3);
                    c0761q.Q(1068923563);
                    long j10 = z10 ? l6.d.c(c0761q).f18696b : C1891t.f19451j;
                    c0761q.p(false);
                    C0761q c0761q2 = c0761q;
                    c1.b(strX, oVarB, j10, h6.f.g(FontSize.Subtitle2, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 1, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q2, 0, 3072, 56816);
                    c0761q = c0761q2;
                    i10 = i11;
                    arrayList2 = arrayList3;
                    i7 = i7;
                    arrayList = arrayList;
                    c1283j = c1283j;
                    qVar = qVar2;
                    z6 = false;
                }
                int i12 = z6;
                int i13 = i7;
                C1283j c1283j2 = c1283j;
                C0761q c0761q3 = c0761q;
                m7.q qVar3 = qVar;
                c0761q3.p(i12);
                C1278e[] c1278eArr = (C1278e[]) arrayList.toArray(new C1278e[i12]);
                g1.o[] oVarArr = (g1.o[]) Arrays.copyOf(c1278eArr, c1278eArr.length);
                C1275b c1275b = C1275b.f14704b;
                int i14 = c1283j2.d;
                c1283j2.d = i14 + 1;
                C1287n c1287n = new C1287n(Integer.valueOf(i14));
                C1596a c1596a = new C1596a(new char[i12]);
                int length = oVarArr.length;
                for (int i15 = i12; i15 < length; i15++) {
                    g1.o oVar = oVarArr[i15];
                    oVar.f14739b.get(A.f443a.b(AbstractC1274a.class).b());
                    c1596a.s(k1.i.s(oVar.a().toString()));
                }
                C1596a c1596a2 = new C1596a(new char[i12]);
                c1596a2.s(k1.i.s("spread"));
                Float f10 = (Float) c1275b.f14705a;
                c1596a2.s(new k1.e(f10 != null ? f10.floatValue() : 0.5f));
                k1.g gVarA = c1283j2.a(c1287n);
                gVarA.getClass();
                k1.i iVar = new k1.i("hChain".toCharArray());
                iVar.f17620m = 0L;
                iVar.r(5);
                gVarA.G("type", iVar);
                gVarA.G("contains", c1596a);
                gVarA.G("style", c1596a2);
                c1283j2.f(16);
                int length2 = oVarArr.length;
                for (int i16 = i12; i16 < length2; i16++) {
                    c1283j2.f(oVarArr[i16].hashCode());
                }
                c1283j2.f(c1275b.hashCode());
                c0761q3.p(i12);
                if (c1283j2.f14726b == i13) {
                    return qVar3;
                }
                C0735d.i((InterfaceC2772a) this.f9855q, c0761q3);
                return qVar3;
            default:
                C0761q c0761q4 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    Object objG4 = c0761q4.G();
                    S s12 = C0753m.f8270a;
                    InterfaceC2782k interfaceC2782k2 = this.f9853n;
                    C2579j c2579j = (C2579j) this.f9855q;
                    if (objG4 == s12) {
                        objG4 = (u) interfaceC2782k2.b(c2579j);
                        c0761q4.a0(objG4);
                    }
                    u uVar = (u) objG4;
                    x0 x0Var = (x0) this.o;
                    Object objC = x0Var.f().c();
                    Object obj4 = this.f9854p;
                    boolean zG = c0761q4.g(A7.m.a(objC, obj4));
                    Object objG5 = c0761q4.G();
                    if (zG || objG5 == s12) {
                        objG5 = A7.m.a(x0Var.f().c(), obj4) ? G.f23068b : ((u) interfaceC2782k2.b(c2579j)).f23142b;
                        c0761q4.a0(objG5);
                    }
                    G g7 = (G) objG5;
                    Object objG6 = c0761q4.G();
                    C0738e0 c0738e0 = x0Var.d;
                    if (objG6 == s12) {
                        objG6 = new C2577h(A7.m.a(obj4, c0738e0.getValue()));
                        c0761q4.a0(objG6);
                    }
                    C2577h c2577h = (C2577h) objG6;
                    F f11 = uVar.f23141a;
                    g0.l lVar4 = g0.l.f14684a;
                    boolean zH = c0761q4.h(uVar);
                    Object objG7 = c0761q4.G();
                    if (zH || objG7 == s12) {
                        objG7 = new E(uVar, 4);
                        c0761q4.a0(objG7);
                    }
                    g0.o oVarB2 = androidx.compose.ui.layout.a.b(lVar4, (InterfaceC2786o) objG7);
                    c2577h.f23102a.setValue(Boolean.valueOf(A7.m.a(obj4, c0738e0.getValue())));
                    g0.o oVarK = oVarB2.k(c2577h);
                    boolean zH2 = c0761q4.h(obj4);
                    Object objG8 = c0761q4.G();
                    if (zH2 || objG8 == s12) {
                        objG8 = new E.i(obj4, 2);
                        c0761q4.a0(objG8);
                    }
                    InterfaceC2782k interfaceC2782k3 = (InterfaceC2782k) objG8;
                    boolean zF3 = c0761q4.f(g7);
                    Object objG9 = c0761q4.G();
                    if (zF3 || objG9 == s12) {
                        objG9 = new G0(g7, 13);
                        c0761q4.a0(objG9);
                    }
                    androidx.compose.animation.a.a((x0) this.o, interfaceC2782k3, oVarK, f11, g7, (InterfaceC2785n) objG9, b0.g.b(-616195562, new q0((C1178r) this.f9856r, obj4, c2579j, (b0.b) this.f9857s, 1), c0761q4), c0761q4, 12582912);
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(x0 x0Var, Object obj, InterfaceC2782k interfaceC2782k, C2579j c2579j, C1178r c1178r, b0.b bVar) {
        super(2);
        this.o = x0Var;
        this.f9854p = obj;
        this.f9853n = interfaceC2782k;
        this.f9855q = c2579j;
        this.f9856r = c1178r;
        this.f9857s = bVar;
    }
}
