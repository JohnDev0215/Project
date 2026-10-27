package q8;

import D8.v;
import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0266a;
import F8.C0278m;
import F8.C0286v;
import F8.C0289y;
import F8.K;
import F8.O;
import F8.Y;
import F8.a0;
import H7.w;
import Q7.AbstractC0662o;
import Q7.B;
import Q7.C0661n;
import Q7.D;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0670x;
import Q7.InterfaceC0671y;
import Q7.L;
import Q7.U;
import T7.C0790i;
import T7.I;
import T7.J;
import T7.Q;
import T7.u;
import e0.C1183w;
import g1.AbstractC1274a;
import g1.C1276c;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l3.C1764u;
import l3.P;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import t8.C2448a;
import t8.C2449b;
import v8.AbstractC2564d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: q8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2203g implements InterfaceC2205i {
    public static final C2203g c;
    public static final C2203g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2203g f21280e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2206j f21281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m7.n f21282b = h6.r.o(new C1183w(this, 7));

    static {
        h6.r.u(C2200d.f21265p);
        h6.r.u(C2200d.f21264n);
        h6.r.u(C2200d.o);
        h6.r.u(C2200d.f21266q);
        h6.r.u(C2200d.f21271v);
        c = h6.r.u(C2200d.f21268s);
        h6.r.u(C2200d.f21269t);
        d = h6.r.u(C2200d.f21272w);
        f21280e = h6.r.u(C2200d.f21267r);
        h6.r.u(C2200d.f21270u);
    }

    public C2203g(C2206j c2206j) {
        this.f21281a = c2206j;
    }

    public static void X(StringBuilder sb) {
        int length = sb.length();
        if (length == 0 || sb.charAt(length - 1) != ' ') {
            sb.append(' ');
        }
    }

    public static boolean j0(AbstractC0287w abstractC0287w) {
        if (C7.a.R(abstractC0287w)) {
            List listO0 = abstractC0287w.o0();
            if (listO0 == null || !listO0.isEmpty()) {
                Iterator it = listO0.iterator();
                while (it.hasNext()) {
                    if (((O) it.next()).c()) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static final void n(C2203g c2203g, L l, StringBuilder sb) {
        if (!c2203g.r()) {
            C2206j c2206j = c2203g.f21281a;
            w[] wVarArr = C2206j.f21298W;
            if (!((Boolean) c2206j.f21324g.a(wVarArr[5], c2206j)).booleanValue()) {
                if (c2203g.q().contains(EnumC2204h.ANNOTATIONS)) {
                    c2203g.y(sb, l, null);
                    T7.r rVarU = l.U();
                    if (rVarU != null) {
                        c2203g.y(sb, rVarU, R7.d.FIELD);
                    }
                    T7.r rVarM = l.M();
                    if (rVarM != null) {
                        c2203g.y(sb, rVarM, R7.d.PROPERTY_DELEGATE_FIELD);
                    }
                    if (((EnumC2211o) c2206j.f21304G.a(wVarArr[31], c2206j)) == EnumC2211o.f21348m) {
                        I iC = l.c();
                        if (iC != null) {
                            c2203g.y(sb, iC, R7.d.PROPERTY_GETTER);
                        }
                        J jD = l.d();
                        if (jD != null) {
                            c2203g.y(sb, jD, R7.d.PROPERTY_SETTER);
                            List listD0 = jD.D0();
                            A7.m.e(listD0, "setter.valueParameters");
                            Q q4 = (Q) AbstractC1998m.r0(listD0);
                            A7.m.e(q4, "it");
                            c2203g.y(sb, q4, R7.d.SETTER_PARAMETER);
                        }
                    }
                }
                List listV = l.V();
                A7.m.e(listV, "property.contextReceiverParameters");
                c2203g.C(sb, listV);
                C0661n c0661nE = l.e();
                A7.m.e(c0661nE, "property.visibility");
                c2203g.h0(c0661nE, sb);
                c2203g.N(sb, c2203g.q().contains(EnumC2204h.CONST) && l.G(), "const");
                c2203g.K(l, sb);
                c2203g.M(l, sb);
                c2203g.S(l, sb);
                c2203g.N(sb, c2203g.q().contains(EnumC2204h.LATEINIT) && l.Y(), "lateinit");
                c2203g.J(l, sb);
            }
            c2203g.e0(l, sb, false);
            List listT = l.t();
            A7.m.e(listT, "property.typeParameters");
            c2203g.d0(sb, listT, true);
            c2203g.V(l, sb);
        }
        c2203g.P(l, sb, true);
        sb.append(": ");
        AbstractC0287w abstractC0287wB = l.b();
        A7.m.e(abstractC0287wB, "property.type");
        sb.append(c2203g.Y(abstractC0287wB));
        c2203g.W(l, sb);
        c2203g.H(l, sb);
        List listT2 = l.t();
        A7.m.e(listT2, "property.typeParameters");
        c2203g.i0(sb, listT2);
    }

    public static int v(InterfaceC0670x interfaceC0670x) {
        if (interfaceC0670x instanceof InterfaceC0652e) {
            return ((InterfaceC0652e) interfaceC0670x).E() == 2 ? 4 : 1;
        }
        InterfaceC0657j interfaceC0657jO = interfaceC0670x.o();
        InterfaceC0652e interfaceC0652e = interfaceC0657jO instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657jO : null;
        if (interfaceC0652e == null || !(interfaceC0670x instanceof InterfaceC0650c)) {
            return 1;
        }
        InterfaceC0650c interfaceC0650c = (InterfaceC0650c) interfaceC0670x;
        Collection collectionQ = interfaceC0650c.q();
        A7.m.e(collectionQ, "this.overriddenDescriptors");
        if (!collectionQ.isEmpty() && interfaceC0652e.h() != 1) {
            return 3;
        }
        if (interfaceC0652e.E() != 2 || A7.m.a(interfaceC0650c.e(), AbstractC0662o.f7189a)) {
            return 1;
        }
        return interfaceC0650c.h() == 4 ? 4 : 3;
    }

    public final void A(InterfaceC0655h interfaceC0655h, StringBuilder sb) {
        List listS = interfaceC0655h.s();
        A7.m.e(listS, "classifier.declaredTypeParameters");
        List listP = interfaceC0655h.C().p();
        A7.m.e(listP, "classifier.typeConstructor.parameters");
        if (u() && interfaceC0655h.d0() && listP.size() > listS.size()) {
            sb.append(" /*captured type parameters: ");
            c0(sb, listP.subList(listS.size(), listP.size()));
            sb.append("*/");
        }
    }

    public final String B(t8.g gVar) {
        if (gVar instanceof C2449b) {
            return AbstractC1998m.f0((Iterable) ((C2449b) gVar).f22499a, ", ", "{", "}", new C2202f(this, 1), 24);
        }
        if (gVar instanceof C2448a) {
            return Q8.h.S0(x((R7.b) ((C2448a) gVar).f22499a, null), "@");
        }
        if (!(gVar instanceof t8.r)) {
            return gVar.toString();
        }
        t8.q qVar = (t8.q) ((t8.r) gVar).f22499a;
        if (qVar instanceof t8.o) {
            return ((t8.o) qVar).f22506a + "::class";
        }
        if (!(qVar instanceof t8.p)) {
            throw new A9.k();
        }
        t8.p pVar = (t8.p) qVar;
        String strB = pVar.f22507a.f22497a.b().b();
        for (int i7 = 0; i7 < pVar.f22507a.f22498b; i7++) {
            strB = "kotlin.Array<" + strB + '>';
        }
        return android.hardware.bydauto.ac.tsimpl.a.h(strB, "::class");
    }

    public final void C(StringBuilder sb, List list) {
        if (list.isEmpty()) {
            return;
        }
        sb.append("context(");
        Iterator it = list.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            int i10 = i7 + 1;
            u uVar = (u) it.next();
            y(sb, uVar, R7.d.RECEIVER);
            AbstractC0287w abstractC0287wB = uVar.b();
            A7.m.e(abstractC0287wB, "contextReceiver.type");
            sb.append(G(abstractC0287wB));
            if (i7 == AbstractC1999n.C(list)) {
                sb.append(") ");
            } else {
                sb.append(", ");
            }
            i7 = i10;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D(java.lang.StringBuilder r6, F8.A r7) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q8.C2203g.D(java.lang.StringBuilder, F8.A):void");
    }

    public final String E(String str) {
        int iOrdinal = s().ordinal();
        if (iOrdinal == 0) {
            return str;
        }
        if (iOrdinal == 1) {
            return android.hardware.bydauto.ac.tsimpl.a.i("<font color=red><b>", str, "</b></font>");
        }
        throw new A9.k();
    }

    public final String F(String str, String str2, N7.i iVar) {
        A7.m.f(str, "lowerRendered");
        A7.m.f(str2, "upperRendered");
        if (AbstractC1386c.v(str, str2)) {
            return Q8.o.z0(str2, "(", false) ? android.hardware.bydauto.ac.tsimpl.a.i("(", str, ")!") : str.concat("!");
        }
        String strA = p().a(iVar.i(N7.n.B), this);
        String strB1 = Q8.h.b1(strA, "Collection", strA);
        String strQ = AbstractC1386c.q(str, strB1.concat("Mutable"), str2, strB1, strB1.concat("(Mutable)"));
        if (strQ != null) {
            return strQ;
        }
        String strQ2 = AbstractC1386c.q(str, strB1.concat("MutableMap.MutableEntry"), str2, strB1.concat("Map.Entry"), strB1.concat("(Mutable)Map.(Mutable)Entry"));
        if (strQ2 != null) {
            return strQ2;
        }
        String strA2 = p().a(iVar.j("Array"), this);
        String strB12 = Q8.h.b1(strA2, "Array", strA2);
        String strQ3 = AbstractC1386c.q(str, strB12.concat(o("Array<")), str2, strB12.concat(o("Array<out ")), strB12.concat(o("Array<(out) ")));
        if (strQ3 != null) {
            return strQ3;
        }
        return "(" + str + ".." + str2 + ')';
    }

    public final String G(AbstractC0287w abstractC0287w) {
        String strY = Y(abstractC0287w);
        if ((!j0(abstractC0287w) || Y.e(abstractC0287w)) && !(abstractC0287w instanceof C0278m)) {
            return strY;
        }
        return "(" + strY + ')';
    }

    public final void H(U u4, StringBuilder sb) {
        t8.g gVarX0;
        C2206j c2206j = this.f21281a;
        if (!((Boolean) c2206j.f21336u.a(C2206j.f21298W[19], c2206j)).booleanValue() || (gVarX0 = u4.x0()) == null) {
            return;
        }
        sb.append(" = ");
        sb.append(o(B(gVarX0)));
    }

    public final String I(String str) {
        int iOrdinal = s().ordinal();
        if (iOrdinal == 0) {
            return str;
        }
        if (iOrdinal != 1) {
            throw new A9.k();
        }
        C2206j c2206j = this.f21281a;
        return ((Boolean) c2206j.f21318U.a(C2206j.f21298W[46], c2206j)).booleanValue() ? str : android.hardware.bydauto.ac.tsimpl.a.i("<b>", str, "</b>");
    }

    public final void J(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        String str;
        if (q().contains(EnumC2204h.MEMBER_KIND) && u() && interfaceC0650c.N() != 1) {
            sb.append("/*");
            int iN = interfaceC0650c.N();
            if (iN == 1) {
                str = "DECLARATION";
            } else if (iN == 2) {
                str = "FAKE_OVERRIDE";
            } else if (iN == 3) {
                str = "DELEGATION";
            } else {
                if (iN != 4) {
                    throw null;
                }
                str = "SYNTHESIZED";
            }
            sb.append(io.sentry.config.a.n0(str));
            sb.append("*/ ");
        }
    }

    public final void K(InterfaceC0670x interfaceC0670x, StringBuilder sb) {
        N(sb, interfaceC0670x.z(), "external");
        boolean z6 = false;
        N(sb, q().contains(EnumC2204h.EXPECT) && interfaceC0670x.c0(), "expect");
        if (q().contains(EnumC2204h.ACTUAL) && interfaceC0670x.y0()) {
            z6 = true;
        }
        N(sb, z6, "actual");
    }

    public final void L(int i7, int i10, StringBuilder sb) {
        String str;
        C2206j c2206j = this.f21281a;
        if (((Boolean) c2206j.f21331p.a(C2206j.f21298W[14], c2206j)).booleanValue() || i7 != i10) {
            boolean zContains = q().contains(EnumC2204h.MODALITY);
            if (i7 == 1) {
                str = "FINAL";
            } else if (i7 == 2) {
                str = "SEALED";
            } else if (i7 == 3) {
                str = "OPEN";
            } else {
                if (i7 != 4) {
                    throw null;
                }
                str = "ABSTRACT";
            }
            N(sb, zContains, io.sentry.config.a.n0(str));
        }
    }

    public final void M(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        if (r8.d.s(interfaceC0650c) && interfaceC0650c.h() == 1) {
            return;
        }
        C2206j c2206j = this.f21281a;
        if (((EnumC2209m) c2206j.f21299A.a(C2206j.f21298W[25], c2206j)) == EnumC2209m.l && interfaceC0650c.h() == 3 && !interfaceC0650c.q().isEmpty()) {
            return;
        }
        int iH = interfaceC0650c.h();
        A7.k.s(iH, "callable.modality");
        L(iH, v(interfaceC0650c), sb);
    }

    public final void N(StringBuilder sb, boolean z6, String str) {
        if (z6) {
            sb.append(I(str));
            sb.append(" ");
        }
    }

    public final String O(C2061f c2061f, boolean z6) {
        String strO = o(AbstractC1386c.o(c2061f));
        C2206j c2206j = this.f21281a;
        return (((Boolean) c2206j.f21318U.a(C2206j.f21298W[46], c2206j)).booleanValue() && s() == r.f21350m && z6) ? android.hardware.bydauto.ac.tsimpl.a.i("<b>", strO, "</b>") : strO;
    }

    public final void P(InterfaceC0657j interfaceC0657j, StringBuilder sb, boolean z6) {
        C2061f name = interfaceC0657j.getName();
        A7.m.e(name, "descriptor.name");
        sb.append(O(name, z6));
    }

    public final void Q(StringBuilder sb, AbstractC0287w abstractC0287w) {
        a0 a0VarK0 = abstractC0287w.K0();
        C0266a c0266a = a0VarK0 instanceof C0266a ? (C0266a) a0VarK0 : null;
        if (c0266a == null) {
            R(sb, abstractC0287w);
            return;
        }
        C2206j c2206j = this.f21281a;
        w[] wVarArr = C2206j.f21298W;
        boolean zBooleanValue = ((Boolean) c2206j.f21314Q.a(wVarArr[41], c2206j)).booleanValue();
        A a10 = c0266a.f2459m;
        if (zBooleanValue) {
            R(sb, a10);
            return;
        }
        R(sb, c0266a.f2460n);
        if (((Boolean) c2206j.f21313P.a(wVarArr[40], c2206j)).booleanValue()) {
            r rVarS = s();
            C2212p c2212p = r.f21350m;
            if (rVarS == c2212p) {
                sb.append("<font color=\"808080\"><i>");
            }
            sb.append(" /* = ");
            R(sb, a10);
            sb.append(" */");
            if (s() == c2212p) {
                sb.append("</i></font>");
            }
        }
    }

    public final void R(StringBuilder sb, AbstractC0287w abstractC0287w) {
        C2061f c2061fG;
        String strO;
        boolean z6 = abstractC0287w instanceof C0289y;
        C2206j c2206j = this.f21281a;
        if (z6 && c2206j.n()) {
            E8.i iVar = ((C0289y) abstractC0287w).o;
            if (iVar.f1854n == E8.k.l || iVar.f1854n == E8.k.f1857m) {
                sb.append("<Not computed yet>");
                return;
            }
        }
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            sb.append(((AbstractC0282q) a0VarK0).P0(this, this));
            return;
        }
        if (a0VarK0 instanceof A) {
            A a10 = (A) a0VarK0;
            if (a10.equals(Y.f2458b) || a10.B0() == Y.f2457a.f2858m) {
                sb.append("???");
                return;
            }
            K kB0 = a10.B0();
            boolean z10 = false;
            if ((kB0 instanceof H8.g) && ((H8.g) kB0).f2864a == H8.h.f2888u) {
                if (!((Boolean) c2206j.f21335t.a(C2206j.f21298W[18], c2206j)).booleanValue()) {
                    sb.append("???");
                    return;
                }
                K kB02 = a10.B0();
                A7.m.d(kB02, "null cannot be cast to non-null type org.jetbrains.kotlin.types.error.ErrorTypeConstructor");
                sb.append(E(((H8.g) kB02).f2865b[0]));
                return;
            }
            if (AbstractC0268c.i(a10)) {
                D(sb, a10);
                return;
            }
            if (!j0(a10)) {
                D(sb, a10);
                return;
            }
            int length = sb.length();
            ((C2203g) this.f21282b.getValue()).y(sb, a10, null);
            boolean z11 = sb.length() != length;
            AbstractC0287w abstractC0287wM = C7.a.M(a10);
            List listJ = C7.a.J(a10);
            if (!listJ.isEmpty()) {
                sb.append("context(");
                Iterator it = listJ.subList(0, AbstractC1999n.C(listJ)).iterator();
                while (it.hasNext()) {
                    Q(sb, (AbstractC0287w) it.next());
                    sb.append(", ");
                }
                Q(sb, (AbstractC0287w) AbstractC1998m.h0(listJ));
                sb.append(") ");
            }
            boolean zS = C7.a.S(a10);
            boolean zF0 = a10.F0();
            boolean z12 = zF0 || (z11 && abstractC0287wM != null);
            if (z12) {
                if (zS) {
                    sb.insert(length, '(');
                } else {
                    if (z11) {
                        AbstractC1274a.T(Q8.h.N0(sb));
                        if (sb.charAt(Q8.h.G0(sb) - 1) != ')') {
                            sb.insert(Q8.h.G0(sb), "()");
                        }
                    }
                    sb.append("(");
                }
            }
            N(sb, zS, "suspend");
            if (abstractC0287wM != null) {
                boolean z13 = (j0(abstractC0287wM) && !abstractC0287wM.F0()) || C7.a.S(abstractC0287wM) || !abstractC0287wM.k().isEmpty() || (abstractC0287wM instanceof C0278m);
                if (z13) {
                    sb.append("(");
                }
                Q(sb, abstractC0287wM);
                if (z13) {
                    sb.append(")");
                }
                sb.append(".");
            }
            sb.append("(");
            if (!C7.a.R(a10) || a10.k().e(N7.n.f4857p) == null || a10.o0().size() > 1) {
                int i7 = 0;
                for (O o : C7.a.O(a10)) {
                    int i10 = i7 + 1;
                    if (i7 > 0) {
                        sb.append(", ");
                    }
                    if (((Boolean) c2206j.f21316S.a(C2206j.f21298W[43], c2206j)).booleanValue()) {
                        AbstractC0287w abstractC0287wB = o.b();
                        A7.m.e(abstractC0287wB, "typeProjection.type");
                        c2061fG = C7.a.G(abstractC0287wB);
                    } else {
                        c2061fG = null;
                    }
                    if (c2061fG != null) {
                        sb.append(O(c2061fG, z10));
                        sb.append(": ");
                    }
                    A7.m.f(o, "typeProjection");
                    StringBuilder sb2 = new StringBuilder();
                    AbstractC1998m.d0(AbstractC1387d.j(o), sb2, ", ", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : new C2202f(this, 0));
                    String string = sb2.toString();
                    A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
                    sb.append(string);
                    i7 = i10;
                    z10 = false;
                }
            } else {
                sb.append("???");
            }
            sb.append(") ");
            int iOrdinal = s().ordinal();
            if (iOrdinal == 0) {
                strO = o("->");
            } else {
                if (iOrdinal != 1) {
                    throw new A9.k();
                }
                strO = "&rarr;";
            }
            sb.append(strO);
            sb.append(" ");
            C7.a.R(a10);
            AbstractC0287w abstractC0287wB2 = ((O) AbstractC1998m.h0(a10.o0())).b();
            A7.m.e(abstractC0287wB2, "arguments.last().type");
            Q(sb, abstractC0287wB2);
            if (z12) {
                sb.append(")");
            }
            if (zF0) {
                sb.append("?");
            }
        }
    }

    public final void S(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        if (q().contains(EnumC2204h.OVERRIDE) && !interfaceC0650c.q().isEmpty()) {
            C2206j c2206j = this.f21281a;
            if (((EnumC2209m) c2206j.f21299A.a(C2206j.f21298W[25], c2206j)) != EnumC2209m.f21344m) {
                N(sb, true, "override");
                if (u()) {
                    sb.append("/*");
                    sb.append(interfaceC0650c.q().size());
                    sb.append("*/ ");
                }
            }
        }
    }

    public final void T(C2058c c2058c, String str, StringBuilder sb) {
        sb.append(I(str));
        C2060e c2060eI = c2058c.i();
        A7.m.e(c2060eI, "fqName.toUnsafe()");
        String strO = o(AbstractC1386c.p(c2060eI.e()));
        if (strO.length() > 0) {
            sb.append(" ");
            sb.append(strO);
        }
    }

    public final void U(StringBuilder sb, P p10) {
        P p11 = (P) p10.o;
        InterfaceC0655h interfaceC0655h = (InterfaceC0655h) p10.f18537m;
        if (p11 != null) {
            U(sb, p11);
            sb.append('.');
            C2061f name = interfaceC0655h.getName();
            A7.m.e(name, "possiblyInnerType.classifierDescriptor.name");
            sb.append(O(name, false));
        } else {
            K kC = interfaceC0655h.C();
            A7.m.e(kC, "possiblyInnerType.classi…escriptor.typeConstructor");
            sb.append(a0(kC));
        }
        sb.append(Z((List) p10.f18538n));
    }

    public final void V(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        u uVarK = interfaceC0650c.K();
        if (uVarK != null) {
            y(sb, uVarK, R7.d.RECEIVER);
            AbstractC0287w abstractC0287wB = uVarK.b();
            A7.m.e(abstractC0287wB, "receiver.type");
            sb.append(G(abstractC0287wB));
            sb.append(".");
        }
    }

    public final void W(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        u uVarK;
        C2206j c2206j = this.f21281a;
        if (((Boolean) c2206j.f21302E.a(C2206j.f21298W[29], c2206j)).booleanValue() && (uVarK = interfaceC0650c.K()) != null) {
            sb.append(" on ");
            AbstractC0287w abstractC0287wB = uVarK.b();
            A7.m.e(abstractC0287wB, "receiver.type");
            sb.append(Y(abstractC0287wB));
        }
    }

    public final String Y(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "type");
        StringBuilder sb = new StringBuilder();
        C2206j c2206j = this.f21281a;
        Q(sb, (AbstractC0287w) ((InterfaceC2782k) c2206j.f21339x.a(C2206j.f21298W[22], c2206j)).b(abstractC0287w));
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String Z(List list) {
        A7.m.f(list, "typeArguments");
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(o("<"));
        AbstractC1998m.d0(list, sb, ", ", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : new C2202f(this, 0));
        sb.append(o(">"));
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @Override // q8.InterfaceC2205i
    public final void a() {
        this.f21281a.a();
    }

    public final String a0(K k10) {
        A7.m.f(k10, "typeConstructor");
        InterfaceC0654g interfaceC0654gN = k10.n();
        if (interfaceC0654gN instanceof Q7.Q ? true : interfaceC0654gN instanceof InterfaceC0652e ? true : interfaceC0654gN instanceof v) {
            A7.m.f(interfaceC0654gN, "klass");
            return H8.i.f(interfaceC0654gN) ? interfaceC0654gN.C().toString() : p().a(interfaceC0654gN, this);
        }
        if (interfaceC0654gN == null) {
            return k10 instanceof C0286v ? ((C0286v) k10).c(C2200d.f21275z) : k10.toString();
        }
        throw new IllegalStateException(("Unexpected classifier: " + interfaceC0654gN.getClass()).toString());
    }

    @Override // q8.InterfaceC2205i
    public final void b() {
        this.f21281a.b();
    }

    public final void b0(Q7.Q q4, StringBuilder sb, boolean z6) {
        String str;
        if (z6) {
            sb.append(o("<"));
        }
        if (u()) {
            sb.append("/*");
            sb.append(q4.getIndex());
            sb.append("*/ ");
        }
        N(sb, q4.R(), "reified");
        int iE0 = q4.e0();
        if (iE0 == 1) {
            str = "";
        } else if (iE0 == 2) {
            str = "in";
        } else {
            if (iE0 != 3) {
                throw null;
            }
            str = "out";
        }
        boolean z10 = true;
        N(sb, str.length() > 0, str);
        y(sb, q4, null);
        P(q4, sb, z6);
        int size = q4.getUpperBounds().size();
        if ((size > 1 && !z6) || size == 1) {
            AbstractC0287w abstractC0287w = (AbstractC0287w) q4.getUpperBounds().iterator().next();
            if (abstractC0287w == null) {
                N7.i.a(141);
                throw null;
            }
            if (!N7.i.w(abstractC0287w) || !abstractC0287w.F0()) {
                sb.append(" : ");
                sb.append(Y(abstractC0287w));
            }
        } else if (z6) {
            for (AbstractC0287w abstractC0287w2 : q4.getUpperBounds()) {
                if (abstractC0287w2 == null) {
                    N7.i.a(141);
                    throw null;
                }
                if (!N7.i.w(abstractC0287w2) || !abstractC0287w2.F0()) {
                    if (z10) {
                        sb.append(" : ");
                    } else {
                        sb.append(" & ");
                    }
                    sb.append(Y(abstractC0287w2));
                    z10 = false;
                }
            }
        }
        if (z6) {
            sb.append(o(">"));
        }
    }

    @Override // q8.InterfaceC2205i
    public final void c() {
        this.f21281a.c();
    }

    public final void c0(StringBuilder sb, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b0((Q7.Q) it.next(), sb, false);
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
    }

    @Override // q8.InterfaceC2205i
    public final void d(InterfaceC2199c interfaceC2199c) {
        this.f21281a.d(interfaceC2199c);
    }

    public final void d0(StringBuilder sb, List list, boolean z6) {
        C2206j c2206j = this.f21281a;
        if (((Boolean) c2206j.f21337v.a(C2206j.f21298W[20], c2206j)).booleanValue() || list.isEmpty()) {
            return;
        }
        sb.append(o("<"));
        c0(sb, list);
        sb.append(o(">"));
        if (z6) {
            sb.append(" ");
        }
    }

    @Override // q8.InterfaceC2205i
    public final void e(EnumC2210n enumC2210n) {
        this.f21281a.e(enumC2210n);
    }

    public final void e0(U u4, StringBuilder sb, boolean z6) {
        if (z6 || !(u4 instanceof Q)) {
            sb.append(I(u4.I() ? "var" : "val"));
            sb.append(" ");
        }
    }

    @Override // q8.InterfaceC2205i
    public final void f(Set set) {
        A7.m.f(set, "<set-?>");
        this.f21281a.f(set);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f0(T7.Q r11, boolean r12, java.lang.StringBuilder r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q8.C2203g.f0(T7.Q, boolean, java.lang.StringBuilder, boolean):void");
    }

    @Override // q8.InterfaceC2205i
    public final void g() {
        this.f21281a.g();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0(java.lang.StringBuilder r8, java.util.List r9, boolean r10) {
        /*
            r7 = this;
            q8.j r0 = r7.f21281a
            H7.w[] r1 = q8.C2206j.f21298W
            r2 = 28
            r1 = r1[r2]
            g1.c r2 = r0.f21301D
            java.lang.Object r0 = r2.a(r1, r0)
            q8.n r0 = (q8.EnumC2210n) r0
            int r0 = r0.ordinal()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L27
            if (r0 == r1) goto L25
            r10 = 2
            if (r0 != r10) goto L1f
        L1d:
            r10 = r2
            goto L28
        L1f:
            A9.k r7 = new A9.k
            r7.<init>()
            throw r7
        L25:
            if (r10 != 0) goto L1d
        L27:
            r10 = r1
        L28:
            int r0 = r9.size()
            q8.e r3 = r7.t()
            r3.getClass()
            java.lang.String r3 = "builder"
            A7.m.f(r8, r3)
            java.lang.String r3 = "("
            r8.append(r3)
            java.util.Iterator r9 = r9.iterator()
            r3 = r2
        L42:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L71
            int r4 = r3 + 1
            java.lang.Object r5 = r9.next()
            T7.Q r5 = (T7.Q) r5
            q8.e r6 = r7.t()
            r6.getClass()
            java.lang.String r6 = "parameter"
            A7.m.f(r5, r6)
            r7.f0(r5, r10, r8, r2)
            q8.e r5 = r7.t()
            r5.getClass()
            int r5 = r0 + (-1)
            if (r3 == r5) goto L6f
            java.lang.String r3 = ", "
            r8.append(r3)
        L6f:
            r3 = r4
            goto L42
        L71:
            q8.e r7 = r7.t()
            r7.getClass()
            java.lang.String r7 = ")"
            r8.append(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q8.C2203g.g0(java.lang.StringBuilder, java.util.List, boolean):void");
    }

    @Override // q8.InterfaceC2205i
    public final void h() {
        this.f21281a.h();
    }

    public final boolean h0(C0661n c0661n, StringBuilder sb) {
        if (!q().contains(EnumC2204h.VISIBILITY)) {
            return false;
        }
        C2206j c2206j = this.f21281a;
        w[] wVarArr = C2206j.f21298W;
        if (((Boolean) c2206j.f21330n.a(wVarArr[12], c2206j)).booleanValue()) {
            c0661n = AbstractC0662o.f(c0661n.f7187a.c());
        }
        if (!((Boolean) c2206j.o.a(wVarArr[13], c2206j)).booleanValue() && A7.m.a(c0661n, AbstractC0662o.f7196j)) {
            return false;
        }
        sb.append(I(c0661n.f7187a.b()));
        sb.append(" ");
        return true;
    }

    @Override // q8.InterfaceC2205i
    public final Set i() {
        return this.f21281a.i();
    }

    public final void i0(StringBuilder sb, List list) {
        C2206j c2206j = this.f21281a;
        if (((Boolean) c2206j.f21337v.a(C2206j.f21298W[20], c2206j)).booleanValue()) {
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Q7.Q q4 = (Q7.Q) it.next();
            List upperBounds = q4.getUpperBounds();
            A7.m.e(upperBounds, "typeParameter.upperBounds");
            for (AbstractC0287w abstractC0287w : AbstractC1998m.V(upperBounds)) {
                StringBuilder sb2 = new StringBuilder();
                C2061f name = q4.getName();
                A7.m.e(name, "typeParameter.name");
                sb2.append(O(name, false));
                sb2.append(" : ");
                A7.m.e(abstractC0287w, "it");
                sb2.append(Y(abstractC0287w));
                arrayList.add(sb2.toString());
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        sb.append(" ");
        sb.append(I("where"));
        sb.append(" ");
        AbstractC1998m.d0(arrayList, sb, ", ", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : null);
    }

    @Override // q8.InterfaceC2205i
    public final void j(LinkedHashSet linkedHashSet) {
        this.f21281a.j(linkedHashSet);
    }

    @Override // q8.InterfaceC2205i
    public final void k() {
        this.f21281a.k();
    }

    @Override // q8.InterfaceC2205i
    public final void l() {
        this.f21281a.l();
    }

    @Override // q8.InterfaceC2205i
    public final void m() {
        this.f21281a.m();
    }

    public final String o(String str) {
        return s().a(str);
    }

    public final InterfaceC2199c p() {
        C2206j c2206j = this.f21281a;
        return (InterfaceC2199c) c2206j.f21321b.a(C2206j.f21298W[0], c2206j);
    }

    public final Set q() {
        C2206j c2206j = this.f21281a;
        return (Set) c2206j.f21322e.a(C2206j.f21298W[3], c2206j);
    }

    public final boolean r() {
        C2206j c2206j = this.f21281a;
        return ((Boolean) c2206j.f21323f.a(C2206j.f21298W[4], c2206j)).booleanValue();
    }

    public final r s() {
        C2206j c2206j = this.f21281a;
        return (r) c2206j.f21300C.a(C2206j.f21298W[27], c2206j);
    }

    public final C2201e t() {
        C2206j c2206j = this.f21281a;
        return (C2201e) c2206j.B.a(C2206j.f21298W[26], c2206j);
    }

    public final boolean u() {
        C2206j c2206j = this.f21281a;
        return ((Boolean) c2206j.f21327j.a(C2206j.f21298W[8], c2206j)).booleanValue();
    }

    public final String w(InterfaceC0657j interfaceC0657j) {
        InterfaceC0657j interfaceC0657jO;
        String str;
        A7.m.f(interfaceC0657j, "declarationDescriptor");
        StringBuilder sb = new StringBuilder();
        interfaceC0657j.S(new C1764u(this, 17), sb);
        C2206j c2206j = this.f21281a;
        C1276c c1276c = c2206j.c;
        w[] wVarArr = C2206j.f21298W;
        if (((Boolean) c1276c.a(wVarArr[1], c2206j)).booleanValue() && !(interfaceC0657j instanceof D) && !(interfaceC0657j instanceof Q7.I) && (interfaceC0657jO = interfaceC0657j.o()) != null && !(interfaceC0657jO instanceof InterfaceC0671y)) {
            sb.append(" ");
            int iOrdinal = s().ordinal();
            if (iOrdinal == 0) {
                str = "defined in";
            } else {
                if (iOrdinal != 1) {
                    throw new A9.k();
                }
                str = "<i>defined in</i>";
            }
            sb.append(str);
            sb.append(" ");
            C2060e c2060eG = r8.d.g(interfaceC0657jO);
            A7.m.e(c2060eG, "getFqName(containingDeclaration)");
            sb.append(c2060eG.f20571a.isEmpty() ? "root package" : o(AbstractC1386c.p(c2060eG.e())));
            if (((Boolean) c2206j.d.a(wVarArr[2], c2206j)).booleanValue() && (interfaceC0657jO instanceof D) && (interfaceC0657j instanceof InterfaceC0658k)) {
                ((InterfaceC0658k) interfaceC0657j).g().getClass();
            }
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String x(R7.b bVar, R7.d dVar) {
        List listX;
        C0790i c0790iP0;
        List listD0;
        A7.m.f(bVar, "annotation");
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        if (dVar != null) {
            sb.append(dVar.l + ':');
        }
        AbstractC0287w abstractC0287wB = bVar.b();
        sb.append(Y(abstractC0287wB));
        C2206j c2206j = this.f21281a;
        w[] wVarArr = C2206j.f21298W;
        w wVar = wVarArr[37];
        C1276c c1276c = c2206j.f21310M;
        if (((EnumC2197a) c1276c.a(wVar, c2206j)).l) {
            Map mapC = bVar.c();
            List list = null;
            InterfaceC0652e interfaceC0652eD = ((Boolean) c2206j.f21305H.a(wVarArr[32], c2206j)).booleanValue() ? AbstractC2564d.d(bVar) : null;
            if (interfaceC0652eD != null && (c0790iP0 = interfaceC0652eD.p0()) != null && (listD0 = c0790iP0.D0()) != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : listD0) {
                    if (((Q) obj).d1()) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((Q) it.next()).getName());
                }
                list = arrayList2;
            }
            if (list == null) {
                list = n7.u.l;
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : list) {
                C2061f c2061f = (C2061f) obj2;
                A7.m.e(c2061f, "it");
                if (!mapC.containsKey(c2061f)) {
                    arrayList3.add(obj2);
                }
            }
            ArrayList arrayList4 = new ArrayList(AbstractC2000o.J(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((C2061f) it2.next()).b() + " = ...");
            }
            Set<Map.Entry> setEntrySet = mapC.entrySet();
            ArrayList arrayList5 = new ArrayList(AbstractC2000o.J(setEntrySet, 10));
            for (Map.Entry entry : setEntrySet) {
                C2061f c2061f2 = (C2061f) entry.getKey();
                t8.g gVar = (t8.g) entry.getValue();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(c2061f2.b());
                sb2.append(" = ");
                sb2.append(!list.contains(c2061f2) ? B(gVar) : "...");
                arrayList5.add(sb2.toString());
            }
            ArrayList arrayListN0 = AbstractC1998m.n0(arrayList4, arrayList5);
            if (arrayListN0.size() <= 1) {
                listX = AbstractC1998m.z0(arrayListN0);
            } else {
                Object[] array = arrayListN0.toArray(new Comparable[0]);
                Comparable[] comparableArr = (Comparable[]) array;
                A7.m.f(comparableArr, "<this>");
                if (comparableArr.length > 1) {
                    Arrays.sort(comparableArr);
                }
                listX = AbstractC1996k.x(array);
            }
            if (((EnumC2197a) c1276c.a(C2206j.f21298W[37], c2206j)).f21260m || !listX.isEmpty()) {
                AbstractC1998m.d0(listX, sb, ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) != 0 ? "" : ")", -1, "...", (124 & 64) != 0 ? null : null);
            }
        }
        if (u() && (AbstractC0268c.i(abstractC0287wB) || (abstractC0287wB.B0().n() instanceof B))) {
            sb.append(" /* annotation class not found */");
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final void y(StringBuilder sb, R7.a aVar, R7.d dVar) {
        if (q().contains(EnumC2204h.ANNOTATIONS)) {
            boolean z6 = aVar instanceof AbstractC0287w;
            C2206j c2206j = this.f21281a;
            Set setI = z6 ? c2206j.i() : (Set) c2206j.f21307J.a(C2206j.f21298W[34], c2206j);
            InterfaceC2782k interfaceC2782k = (InterfaceC2782k) c2206j.f21309L.a(C2206j.f21298W[36], c2206j);
            for (R7.b bVar : aVar.k()) {
                if (!AbstractC1998m.T(setI, bVar.a()) && !A7.m.a(bVar.a(), N7.n.f4859r) && (interfaceC2782k == null || ((Boolean) interfaceC2782k.b(bVar)).booleanValue())) {
                    sb.append(x(bVar, dVar));
                    if (((Boolean) c2206j.f21306I.a(C2206j.f21298W[33], c2206j)).booleanValue()) {
                        sb.append('\n');
                    } else {
                        sb.append(" ");
                    }
                }
            }
        }
    }
}
