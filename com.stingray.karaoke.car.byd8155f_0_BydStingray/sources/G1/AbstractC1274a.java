package g1;

import B2.N;
import C0.C0114v;
import C0.c0;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.L;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.F;
import F8.K;
import F8.O;
import F8.P;
import F8.Y;
import F8.a0;
import I5.y;
import N0.G;
import N0.H;
import Q.c1;
import Q5.C0545a;
import Q5.C0565f;
import Q5.C0595m1;
import Q5.C0607p1;
import Q5.M1;
import Q5.N1;
import Q5.O1;
import Q5.U2;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0655h;
import Q7.Q;
import T.C0730a0;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0757o;
import T.C0761q;
import T.C0772x;
import T.InterfaceC0744h0;
import T.Q0;
import T.R0;
import T.S;
import T.X;
import U5.AbstractC0805b;
import U5.C0807d;
import W7.AbstractC0839c;
import W7.C0840d;
import a.AbstractC0904a;
import a6.C0928a;
import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.view.View;
import android.widget.TextView;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import b1.AbstractC0989b;
import b1.InterfaceC0988a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import e1.C1189b;
import e1.C1191d;
import e1.s;
import e1.t;
import e2.W;
import g1.AbstractC1274a;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import k6.AbstractC1692D;
import m7.q;
import n6.C1966G;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.u;
import n7.v;
import n7.x;
import n7.z;
import o8.C2058c;
import r9.InterfaceC2320j;
import w.AbstractC2565A;
import x.AbstractC2614C;
import x.AbstractC2637h;
import x.C0;
import x.C2640i0;
import x.E0;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: g1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1274a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Thread f14702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f14703b = 0;
    public static final /* synthetic */ int c = 0;
    public static final /* synthetic */ int d = 0;

    public static final boolean A(AbstractC0287w abstractC0287w, K k10, Set set) {
        boolean zA;
        if (A7.m.a(abstractC0287w.B0(), k10)) {
            return true;
        }
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        InterfaceC0655h interfaceC0655h = interfaceC0654gN instanceof InterfaceC0655h ? (InterfaceC0655h) interfaceC0654gN : null;
        List listS = interfaceC0655h != null ? interfaceC0655h.s() : null;
        Iterable iterableE0 = AbstractC1998m.E0(abstractC0287w.o0());
        if (!(iterableE0 instanceof Collection) || !((Collection) iterableE0).isEmpty()) {
            Iterator it = iterableE0.iterator();
            do {
                P8.b bVar = (P8.b) it;
                if (bVar.f5459m.hasNext()) {
                    x xVar = (x) bVar.next();
                    int i7 = xVar.f20159a;
                    O o = (O) xVar.f20160b;
                    Q q4 = listS != null ? (Q) AbstractC1998m.b0(i7, listS) : null;
                    if ((q4 == null || set == null || !set.contains(q4)) && !o.c()) {
                        AbstractC0287w abstractC0287wB = o.b();
                        A7.m.e(abstractC0287wB, "argument.type");
                        zA = A(abstractC0287wB, k10, set);
                    } else {
                        zA = false;
                    }
                }
            } while (!zA);
            return true;
        }
        return false;
    }

    public static final long D(long j3, R8.c cVar, R8.c cVar2) {
        A7.m.f(cVar, "sourceUnit");
        A7.m.f(cVar2, "targetUnit");
        return cVar2.l.convert(j3, cVar.l);
    }

    public static final P E(AbstractC0287w abstractC0287w, int i7, Q q4) {
        A7.m.f(abstractC0287w, "type");
        A7.k.p(i7, "projectionKind");
        if ((q4 != null ? q4.e0() : 0) == i7) {
            i7 = 1;
        }
        return new P(i7, abstractC0287w);
    }

    public static final boolean F(char c10, char c11, boolean z6) {
        if (c10 == c11) {
            return true;
        }
        if (!z6) {
            return false;
        }
        char upperCase = Character.toUpperCase(c10);
        char upperCase2 = Character.toUpperCase(c11);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final void G(AbstractC0287w abstractC0287w, A a10, LinkedHashSet linkedHashSet, Set set) {
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN instanceof Q) {
            if (!A7.m.a(abstractC0287w.B0(), a10.B0())) {
                linkedHashSet.add(interfaceC0654gN);
                return;
            }
            for (AbstractC0287w abstractC0287w2 : ((Q) interfaceC0654gN).getUpperBounds()) {
                A7.m.e(abstractC0287w2, "upperBound");
                G(abstractC0287w2, a10, linkedHashSet, set);
            }
            return;
        }
        InterfaceC0654g interfaceC0654gN2 = abstractC0287w.B0().n();
        InterfaceC0655h interfaceC0655h = interfaceC0654gN2 instanceof InterfaceC0655h ? (InterfaceC0655h) interfaceC0654gN2 : null;
        List listS = interfaceC0655h != null ? interfaceC0655h.s() : null;
        int i7 = 0;
        for (O o : abstractC0287w.o0()) {
            int i10 = i7 + 1;
            Q q4 = listS != null ? (Q) AbstractC1998m.b0(i7, listS) : null;
            if ((q4 == null || set == null || !set.contains(q4)) && !o.c() && !AbstractC1998m.T(linkedHashSet, o.b().B0().n()) && !A7.m.a(o.b().B0(), a10.B0())) {
                AbstractC0287w abstractC0287wB = o.b();
                A7.m.e(abstractC0287wB, "argument.type");
                G(abstractC0287wB, a10, linkedHashSet, set);
            }
            i7 = i10;
        }
    }

    public static final C0840d H(Annotation[] annotationArr, C2058c c2058c) {
        Annotation annotation;
        A7.m.f(annotationArr, "<this>");
        A7.m.f(c2058c, "fqName");
        int length = annotationArr.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                annotation = null;
                break;
            }
            annotation = annotationArr[i7];
            if (AbstractC0839c.a(z.i(z.g(annotation))).b().equals(c2058c)) {
                break;
            }
            i7++;
        }
        if (annotation != null) {
            return new C0840d(annotation);
        }
        return null;
    }

    public static Object I(Class cls, Object obj) {
        if (obj instanceof Q6.a) {
            return cls.cast(obj);
        }
        if (obj instanceof Q6.b) {
            return I(cls, ((Q6.b) obj).c());
        }
        throw new IllegalStateException("Given component holder " + obj.getClass() + " does not implement " + Q6.a.class + " or " + Q6.b.class);
    }

    public static final ArrayList K(Annotation[] annotationArr) {
        A7.m.f(annotationArr, "<this>");
        ArrayList arrayList = new ArrayList(annotationArr.length);
        for (Annotation annotation : annotationArr) {
            arrayList.add(new C0840d(annotation));
        }
        return arrayList;
    }

    public static final N7.i L(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        N7.i iVarL = abstractC0287w.B0().l();
        A7.m.e(iVarL, "constructor.builtIns");
        return iVarL;
    }

    public static final AbstractC0287w M(Q q4) {
        Object obj;
        List upperBounds = q4.getUpperBounds();
        A7.m.e(upperBounds, "upperBounds");
        upperBounds.isEmpty();
        List upperBounds2 = q4.getUpperBounds();
        A7.m.e(upperBounds2, "upperBounds");
        Iterator it = upperBounds2.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            InterfaceC0654g interfaceC0654gN = ((AbstractC0287w) next).B0().n();
            InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
            if (interfaceC0652e != null && interfaceC0652e.E() != 2 && interfaceC0652e.E() != 5) {
                obj = next;
                break;
            }
        }
        AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
        if (abstractC0287w != null) {
            return abstractC0287w;
        }
        List upperBounds3 = q4.getUpperBounds();
        A7.m.e(upperBounds3, "upperBounds");
        Object objY = AbstractC1998m.Y(upperBounds3);
        A7.m.e(objY, "upperBounds.first()");
        return (AbstractC0287w) objY;
    }

    public static long N(double d10) {
        if (!R(d10)) {
            throw new IllegalArgumentException("not a normal value");
        }
        int exponent = Math.getExponent(d10);
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d10) & 4503599627370495L;
        return exponent == -1023 ? jDoubleToRawLongBits << 1 : jDoubleToRawLongBits | 4503599627370496L;
    }

    public static final Y0.h O(H h10, int i7) {
        G g7 = h10.f4348a;
        if (g7.f4340a.l.length() != 0) {
            int iF = h10.f(i7);
            if ((i7 != 0 && iF == h10.f(i7 - 1)) || (i7 != g7.f4340a.l.length() && iF == h10.f(i7 + 1))) {
                return h10.a(i7);
            }
        }
        return h10.j(i7);
    }

    public static final boolean P(Q q4, K k10, Set set) {
        A7.m.f(q4, "typeParameter");
        List<AbstractC0287w> upperBounds = q4.getUpperBounds();
        A7.m.e(upperBounds, "typeParameter.upperBounds");
        if (upperBounds.isEmpty()) {
            return false;
        }
        for (AbstractC0287w abstractC0287w : upperBounds) {
            A7.m.e(abstractC0287w, "upperBound");
            if (A(abstractC0287w, q4.j().B0(), set) && (k10 == null || A7.m.a(abstractC0287w.B0(), k10))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean Q(c9.H h10) {
        int i7 = h10.o;
        return 400 <= i7 && i7 < 500;
    }

    public static boolean R(double d10) {
        return Math.getExponent(d10) <= 1023;
    }

    public static final boolean S(c9.H h10) {
        int i7 = h10.o;
        return 100 <= i7 && i7 < 200;
    }

    public static boolean T(char c10) {
        return Character.isWhitespace(c10) || Character.isSpaceChar(c10);
    }

    public static final a0 U(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarG = Y.g(abstractC0287w, true);
        A7.m.e(a0VarG, "makeNullable(this)");
        return a0VarG;
    }

    public static int X(N n10, int i7, int i10, int i11) {
        AbstractC1346c.c(Math.max(Math.max(i7, i10), i11) <= 31);
        int i12 = (1 << i7) - 1;
        int i13 = (1 << i10) - 1;
        io.sentry.config.a.N(io.sentry.config.a.N(i12, i13), 1 << i11);
        if (n10.b() < i7) {
            return -1;
        }
        int i14 = n10.i(i7);
        if (i14 != i12) {
            return i14;
        }
        if (n10.b() < i10) {
            return -1;
        }
        int i15 = n10.i(i10);
        int i16 = i14 + i15;
        if (i15 != i13) {
            return i16;
        }
        if (n10.b() < i11) {
            return -1;
        }
        return i16 + n10.i(i11);
    }

    public static final AbstractC0287w Y(AbstractC0287w abstractC0287w, R7.h hVar) {
        return (abstractC0287w.k().isEmpty() && hVar.isEmpty()) ? abstractC0287w : abstractC0287w.K0().N0(AbstractC0268c.q(abstractC0287w.t0(), hVar));
    }

    public static final a0 Z(AbstractC0287w abstractC0287w) {
        a0 a0VarP;
        A7.m.f(abstractC0287w, "<this>");
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            AbstractC0282q abstractC0282q = (AbstractC0282q) a0VarK0;
            A aP = abstractC0282q.f2477m;
            if (!aP.B0().p().isEmpty() && aP.B0().n() != null) {
                List listP = aP.B0().p();
                A7.m.e(listP, "constructor.parameters");
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(listP, 10));
                Iterator it = listP.iterator();
                while (it.hasNext()) {
                    arrayList.add(new F((Q) it.next()));
                }
                aP = AbstractC0268c.p(aP, arrayList, null, 2);
            }
            A aP2 = abstractC0282q.f2478n;
            if (!aP2.B0().p().isEmpty() && aP2.B0().n() != null) {
                List listP2 = aP2.B0().p();
                A7.m.e(listP2, "constructor.parameters");
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listP2, 10));
                Iterator it2 = listP2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new F((Q) it2.next()));
                }
                aP2 = AbstractC0268c.p(aP2, arrayList2, null, 2);
            }
            a0VarP = C0269d.j(aP, aP2);
        } else {
            if (!(a0VarK0 instanceof A)) {
                throw new A9.k();
            }
            A a10 = (A) a0VarK0;
            boolean zIsEmpty = a10.B0().p().isEmpty();
            a0VarP = a10;
            if (!zIsEmpty) {
                InterfaceC0654g interfaceC0654gN = a10.B0().n();
                a0VarP = a10;
                if (interfaceC0654gN != null) {
                    List listP3 = a10.B0().p();
                    A7.m.e(listP3, "constructor.parameters");
                    ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(listP3, 10));
                    Iterator it3 = listP3.iterator();
                    while (it3.hasNext()) {
                        arrayList3.add(new F((Q) it3.next()));
                    }
                    a0VarP = AbstractC0268c.p(a10, arrayList3, null, 2);
                }
            }
        }
        return AbstractC0268c.g(a0VarP, a0VarK0);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(final g0.o r39, final java.lang.String r40, final int r41, final com.stingray.karaoke.car.utils.FontSize r42, int r43, z7.InterfaceC2782k r44, final boolean r45, final z7.InterfaceC2782k r46, final z7.InterfaceC2782k r47, final com.stingray.karaoke.scoring.utils.ScoringSoundFxType r48, T.C0761q r49, final int r50, final int r51, final int r52) {
        /*
            Method dump skipped, instruction units count: 619
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.AbstractC1274a.a(g0.o, java.lang.String, int, com.stingray.karaoke.car.utils.FontSize, int, z7.k, boolean, z7.k, z7.k, com.stingray.karaoke.scoring.utils.ScoringSoundFxType, T.q, int, int, int):void");
    }

    public static void a0(TextView textView, int i7) {
        l9.l.D(i7);
        if (i7 != textView.getPaint().getFontMetricsInt(null)) {
            textView.setLineSpacing(i7 - r0, 1.0f);
        }
    }

    public static final void b(g0.o oVar, String str, FontSize fontSize, boolean z6, InterfaceC2772a interfaceC2772a, float f10, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-675860068);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(fontSize) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.d(TsNormalNum.THOUSAND_FOUR_HALF) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((i7 & 196608) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.c(f10) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 8388608 : 4194304;
        }
        if ((4793491 & i10) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            X xR = C0735d.R(Float.valueOf(f10), c0761q);
            g0.o oVarD = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
            c0761q.Q(-380843022);
            boolean zF = ((57344 & i10) == 16384) | c0761q.f(xR) | ((29360128 & i10) == 8388608);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C0595m1(z6, interfaceC2782k, xR);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            int i11 = i10 >> 6;
            k(oVarD, TsNormalNum.THOUSAND_FOUR_HALF, z6, interfaceC2772a, (InterfaceC2772a) objG, b0.g.b(1881891497, new G6.e(fontSize, 4, str), c0761q), c0761q, (i11 & 112) | 196608 | (i11 & 896) | (i11 & 7168), 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new A3.b(oVar, str, fontSize, z6, interfaceC2772a, f10, interfaceC2782k, i7);
        }
    }

    public static void b0(N n10) {
        n10.u(3);
        n10.u(8);
        boolean zH = n10.h();
        boolean zH2 = n10.h();
        if (zH) {
            n10.u(5);
        }
        if (zH2) {
            n10.u(6);
        }
    }

    public static final void c(g0.o oVar, ArrayList arrayList, boolean z6, InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n, b6.g gVar, Integer num, L l, C0761q c0761q, int i7) {
        g0.o oVar2;
        c0761q.S(-816899389);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(arrayList) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(gVar) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i10 |= c0761q.f(num) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(l) ? 8388608 : 4194304;
        }
        if ((4793491 & i10) == 4793490 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            int i11 = i10 << 3;
            int i12 = 1572864 | (i10 & 14) | (i11 & 896) | (i11 & 7168) | (i11 & 57344) | (458752 & (i10 >> 6));
            int i13 = i10 << 9;
            com.bumptech.glide.c.B(lVar, null, arrayList, z6, interfaceC2772a, l, true, interfaceC2785n, gVar, num, c0761q, i12 | (29360128 & i13) | (234881024 & i13) | (i13 & 1879048192), 2);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0565f(oVar2, arrayList, z6, interfaceC2772a, interfaceC2785n, gVar, num, l, i7);
        }
    }

    public static void c0(N n10) {
        int i7;
        int i10 = n10.i(2);
        if (i10 == 0) {
            n10.u(6);
            return;
        }
        int iX = X(n10, 5, 8, 16) + 1;
        if (i10 == 1) {
            n10.u(iX * 7);
            return;
        }
        if (i10 == 2) {
            boolean zH = n10.h();
            int i11 = zH ? 1 : 5;
            int i12 = zH ? 7 : 5;
            int i13 = zH ? 8 : 6;
            int i14 = 0;
            while (i14 < iX) {
                if (n10.h()) {
                    n10.u(7);
                    i7 = 0;
                } else {
                    if (n10.i(2) == 3 && n10.i(i12) * i11 != 0) {
                        n10.t();
                    }
                    i7 = n10.i(i13) * i11;
                    if (i7 != 0 && i7 != 180) {
                        n10.t();
                    }
                    n10.t();
                }
                if (i7 != 0 && i7 != 180 && n10.h()) {
                    i14++;
                }
                i14++;
            }
        }
    }

    public static final void d(C0928a c0928a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(141875612);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c0928a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(c0928a, !((Boolean) com.bumptech.glide.c.R(c0928a.f12269F, c0761q, 0).getValue()).booleanValue(), null, false, true, false, null), b0.g.b(-2014504230, new y(c0928a, 2), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 1, c0928a);
        }
    }

    public static final List d0(ArrayList arrayList) {
        int size = arrayList.size();
        return size != 0 ? size != 1 ? Collections.unmodifiableList(new ArrayList(arrayList)) : Collections.singletonList(AbstractC1998m.Y(arrayList)) : u.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void e(g0.o r22, z7.InterfaceC2772a r23, z7.InterfaceC2772a r24, T.C0761q r25, int r26) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.AbstractC1274a.e(g0.o, z7.a, z7.a, T.q, int):void");
    }

    public static final Map e0(Map map) {
        int size = map.size();
        if (size == 0) {
            return v.l;
        }
        if (size != 1) {
            return Collections.unmodifiableMap(new LinkedHashMap(map));
        }
        Map.Entry entry = (Map.Entry) AbstractC1998m.X(map.entrySet());
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    public static final a1.d f(Context context) {
        float f10 = context.getResources().getConfiguration().fontScale;
        float f11 = context.getResources().getDisplayMetrics().density;
        InterfaceC0988a interfaceC0988aA = AbstractC0989b.a(f10);
        if (interfaceC0988aA == null) {
            interfaceC0988aA = new a1.l(f10);
        }
        return new a1.d(f11, f10, interfaceC0988aA);
    }

    public static final void g(InterfaceC2772a interfaceC2772a, t tVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        int i11;
        int i12;
        Object obj;
        c0761q.S(-2032877254);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2772a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(tVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        int i13 = i10;
        if ((i13 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            View view = (View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f);
            a1.b bVar2 = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            a1.k kVar = (a1.k) c0761q.k(AbstractC0227s0.l);
            C0757o c0757oQ = C0735d.Q(c0761q);
            X xR = C0735d.R(bVar, c0761q);
            UUID uuid = (UUID) w0.c.Z(new Object[0], null, e1.e.f13774n, c0761q, 3072, 6);
            boolean zF = c0761q.f(view) | c0761q.f(bVar2);
            Object objG = c0761q.G();
            Object obj2 = C0753m.f8270a;
            if (zF || objG == obj2) {
                i11 = i13;
                i12 = 32;
                e1.v vVar = new e1.v(interfaceC2772a, tVar, view, kVar, bVar2, uuid);
                b0.b bVar3 = new b0.b(488261145, new C1191d(xR, 1), true);
                s sVar = vVar.f13815r;
                sVar.setParentCompositionContext(c0757oQ);
                sVar.f13806u.setValue(bVar3);
                sVar.f13808w = true;
                if (sVar.o == null && !sVar.isAttachedToWindow()) {
                    throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.");
                }
                sVar.d();
                c0761q.a0(vVar);
                obj = vVar;
            } else {
                i11 = i13;
                i12 = 32;
                obj = objG;
            }
            e1.v vVar2 = (e1.v) obj;
            boolean zH = c0761q.h(vVar2);
            Object objG2 = c0761q.G();
            if (zH || objG2 == obj2) {
                objG2 = new C1189b(vVar2, 0);
                c0761q.a0(objG2);
            }
            C0735d.d(vVar2, (InterfaceC2782k) objG2, c0761q);
            boolean zH2 = c0761q.h(vVar2) | ((i11 & 14) == 4) | ((i11 & 112) == i12) | c0761q.f(kVar);
            Object objG3 = c0761q.G();
            if (zH2 || objG3 == obj2) {
                objG3 = new K.G(vVar2, interfaceC2772a, tVar, kVar, 2);
                c0761q.a0(objG3);
            }
            C0735d.i((InterfaceC2772a) objG3, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(interfaceC2772a, tVar, bVar, i7, 9);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void h(final java.lang.String r18, boolean r19, boolean r20, boolean r21, boolean r22, final b0.b r23, T.C0761q r24, final int r25, final int r26) {
        /*
            Method dump skipped, instruction units count: 839
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.AbstractC1274a.h(java.lang.String, boolean, boolean, boolean, boolean, b0.b, T.q, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void i(g0.o r28, T.C0761q r29, int r30) {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.AbstractC1274a.i(g0.o, T.q, int):void");
    }

    public static final void j(final g0.o oVar, final int i7, final int i10, final int i11, final boolean z6, final InterfaceC2782k interfaceC2782k, final InterfaceC2782k interfaceC2782k2, final ScoringSoundFxType scoringSoundFxType, final b0.b bVar, C0761q c0761q, final int i12) {
        int i13;
        Integer num;
        S s6;
        Q0 q02;
        InterfaceC2787p interfaceC2787p;
        int i14;
        C0761q c0761q2;
        Object objR;
        int i15 = 0;
        c0761q.S(120043103);
        if ((i12 & 6) == 0) {
            i13 = (c0761q.f(oVar) ? 4 : 2) | i12;
        } else {
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i12 & 384) == 0) {
            i13 |= c0761q.d(i10) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= c0761q.d(i11) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i12) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 131072 : 65536;
        }
        if ((i12 & 1572864) == 0) {
            i13 |= c0761q.h(interfaceC2782k2) ? 1048576 : 524288;
        }
        if ((i12 & 12582912) == 0) {
            i13 |= c0761q.f(scoringSoundFxType) ? 8388608 : 4194304;
        }
        if ((i12 & 100663296) == 0) {
            i13 |= c0761q.h(bVar) ? 67108864 : 33554432;
        }
        if ((i13 & 38347923) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(55018609);
            Object objG = c0761q.G();
            S s10 = C0753m.f8270a;
            S s11 = S.f8224q;
            if (objG == s10) {
                objG = C0735d.O(Boolean.FALSE, s11);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            Object objE = M5.b.e(55020561, c0761q, false);
            if (objE == s10) {
                objE = C0735d.O(Boolean.FALSE, s11);
                c0761q.a0(objE);
            }
            X x9 = (X) objE;
            Object objE2 = M5.b.e(55022480, c0761q, false);
            if (objE2 == s10) {
                objE2 = C0735d.M(0);
                c0761q.a0(objE2);
            }
            C0732b0 c0732b0 = (C0732b0) objE2;
            c0761q.p(false);
            int i16 = ((Boolean) x9.getValue()).booleanValue() ? i7 : 0;
            C0 c02 = new C0(i10, i11, AbstractC2614C.c);
            C2640i0 c2640i0 = AbstractC2637h.f23537a;
            int i17 = i13;
            Q0 q0C = AbstractC2637h.c(Integer.valueOf(i16), E0.f23397b, c02, null, "NumberRollingPopUpAnimation roll", null, c0761q, 24576, 8);
            c0761q.Q(55034707);
            Object objG2 = c0761q.G();
            if (objG2 == s10) {
                objG2 = C0735d.L(1.0f);
                c0761q.a0(objG2);
            }
            C0730a0 c0730a0 = (C0730a0) objG2;
            c0761q.p(false);
            float fI = c0730a0.i();
            c0761q.Q(55039548);
            Object objG3 = c0761q.G();
            if (objG3 == s10) {
                objG3 = new U5.i(i15);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            x.N n10 = new x.N();
            ((InterfaceC2782k) objG3).b(n10);
            Q0 q0B = AbstractC2637h.b(fI, new x.O(n10), "NumberRollingPopUpAnimation popup", null, c0761q, 3120, 20);
            Integer numValueOf = Integer.valueOf(((Number) q0C.getValue()).intValue());
            c0761q.Q(55049500);
            boolean zF = c0761q.f(q0C) | ((57344 & i17) == 16384) | ((458752 & i17) == 131072) | ((29360128 & i17) == 8388608) | ((i17 & 112) == 32) | ((i17 & 896) == 256) | ((i17 & 7168) == 2048) | ((3670016 & i17) == 1048576);
            Object objG4 = c0761q.G();
            if (zF || objG4 == s10) {
                num = numValueOf;
                s6 = s10;
                q02 = q0C;
                interfaceC2787p = bVar;
                i14 = i17;
                U5.j jVar = new U5.j(z6, interfaceC2782k, scoringSoundFxType, i7, i10, i11, interfaceC2782k2, q0C, x8, c0732b0, c0730a0, null);
                c0761q2 = c0761q;
                c0761q2.a0(jVar);
                objG4 = jVar;
            } else {
                num = numValueOf;
                s6 = s10;
                q02 = q0C;
                c0761q2 = c0761q;
                interfaceC2787p = bVar;
                i14 = i17;
            }
            c0761q2.p(false);
            C0735d.f(c0761q2, num, (InterfaceC2785n) objG4);
            m7.q qVar = m7.q.f19267a;
            c0761q2.Q(55066676);
            Object objG5 = c0761q.G();
            if (objG5 == s6) {
                objG5 = new U5.k(x9, null);
                c0761q2.a0(objG5);
            }
            c0761q2.p(false);
            C0735d.f(c0761q2, qVar, (InterfaceC2785n) objG5);
            if (z6) {
                float fFloatValue = ((Number) q0B.getValue()).floatValue();
                objR = h6.r.r(oVar, fFloatValue, fFloatValue);
            } else {
                objR = g0.l.f14684a;
            }
            interfaceC2787p.o(objR, Integer.valueOf(z6 ? ((Number) q02.getValue()).intValue() : i7), c0761q2, Integer.valueOf((i14 >> 18) & 896));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.e
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i12 | 1);
                    b0.b bVar2 = bVar;
                    AbstractC1274a.j(oVar, i7, i10, i11, z6, interfaceC2782k, interfaceC2782k2, scoringSoundFxType, bVar2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void k(g0.o r24, final int r25, final boolean r26, final z7.InterfaceC2772a r27, z7.InterfaceC2772a r28, final b0.b r29, T.C0761q r30, final int r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.AbstractC1274a.k(g0.o, int, boolean, z7.a, z7.a, b0.b, T.q, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [boolean, int] */
    public static final void l(g0.o oVar, String str, C1966G c1966g, boolean z6, FontSize fontSize, boolean z10, int i7, int i10, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C0761q c0761q, int i11) {
        int i12;
        ?? r12;
        c0761q.S(-2071429387);
        if ((i11 & 6) == 0) {
            i12 = (c0761q.f(oVar) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i12 |= (i11 & 512) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= c0761q.f(fontSize) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i12 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i12 |= c0761q.d(i7) ? 1048576 : 524288;
        }
        if ((12582912 & i11) == 0) {
            i12 |= c0761q.d(i10) ? 8388608 : 4194304;
        }
        if ((100663296 & i11) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 67108864 : 33554432;
        }
        if ((805306368 & i11) == 0) {
            i12 |= c0761q.h(interfaceC2782k2) ? 536870912 : 268435456;
        }
        int i13 = i12;
        if ((i13 & 306783379) == 306783378 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.e eVar = g0.b.f14673y;
            C0119a c0119a = AbstractC0127i.f1076a;
            m6.b bVar = m6.c.f19252a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.l0(c0761q)), eVar, c0761q, 48);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, c0141xA, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.l lVar = g0.l.f14684a;
            int i15 = i13 >> 3;
            c1.b(str, lVar, l6.d.c(c0761q).f18689V, h6.f.g(FontSize.Subtitle1, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.a) c0761q.k(O5.b.f5155b)).f5146f, c0761q, (i15 & 14) | 48, 0, 65008);
            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            D.S sB = D.P.b(AbstractC0127i.d, g0.b.f14670v, c0761q, 54);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarD2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            c0761q.Q(1647148431);
            if (z6) {
                c0761q.Q(1647154346);
                Object objG = c0761q.G();
                if (objG == C0753m.f8270a) {
                    objG = new A3.v(3);
                    c0761q.a0(objG);
                }
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
                r12 = 0;
                c0761q.p(false);
                k(null, i10, z10, interfaceC2772a, null, AbstractC0805b.f9098a, c0761q, ((i13 >> 18) & 112) | 199680 | ((i13 >> 9) & 896), 17);
            } else {
                r12 = 0;
            }
            c0761q.p(r12);
            int i17 = i13 >> 9;
            j(lVar, c1966g.f20000a, 1600, i7, z10, interfaceC2782k, interfaceC2782k2, ScoringSoundFxType.RS_SCORE, b0.g.b(716896, new U5.m(fontSize, r12), c0761q), c0761q, (i17 & 7168) | 113246598 | (i15 & 57344) | (458752 & i17) | (i17 & 3670016));
            c0761q.Q(1647204816);
            if (z6) {
                AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.o(lVar, bVar.k0(c0761q)));
            }
            M5.b.n(c0761q, r12, true, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.s(oVar, str, c1966g, z6, fontSize, z10, i7, i10, interfaceC2782k, interfaceC2782k2, i11);
        }
    }

    public static final void m(g0.o oVar, String str, String str2, String str3, C1966G c1966g, float f10, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C0761q c0761q, int i7, int i10) {
        int i11;
        int i12;
        c0761q.S(797100717);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= (32768 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.c(f10) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i11 |= c0761q.g(z6) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.f(str4) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.g(z10) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.h(interfaceC2772a3) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.h(interfaceC2782k2) ? 2048 : 1024;
        }
        if ((i11 & 306783379) == 306783378 && (i12 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarC = androidx.compose.foundation.layout.c.c(oVar, 1.0f);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarL = androidx.compose.foundation.layout.a.l(oVarC, bVar.E0(c0761q), bVar.F0(c0761q));
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar2 = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                objG = new p(bVar2);
                c0761q.a0(objG);
            }
            p pVar = (p) objG;
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new C1283j();
                c0761q.a0(objG2);
            }
            C1283j c1283j = (C1283j) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG3);
            }
            X x8 = (X) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = new C1285l(c1283j);
                c0761q.a0(objG4);
            }
            C1285l c1285l = (C1285l) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = C0735d.O(m7.q.f19267a, S.f8222n);
                c0761q.a0(objG5);
            }
            X x9 = (X) objG5;
            boolean zH = c0761q.h(pVar) | c0761q.d(257);
            Object objG6 = c0761q.G();
            if (zH || objG6 == obj) {
                objG6 = new M1(x9, pVar, c1285l, x8, 5);
                c0761q.a0(objG6);
            }
            C0.K k10 = (C0.K) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = new N1(x8, c1285l, 5);
                c0761q.a0(objG7);
            }
            InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) objG7;
            boolean zH2 = c0761q.h(pVar);
            Object objG8 = c0761q.G();
            if (zH2 || objG8 == obj) {
                objG8 = new O1(pVar, 5);
                c0761q.a0(objG8);
            }
            c0.a(L0.k.a(oVarL, false, (InterfaceC2782k) objG8), b0.g.b(1200550679, new U5.n(x9, c1283j, interfaceC2772a4, str, str2, str3, c1966g, z10, interfaceC2782k, interfaceC2782k2, z6, f10, str4, interfaceC2772a3, interfaceC2772a, interfaceC2772a2, 0), c0761q), k10, c0761q, 48);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0807d(oVar, str, str2, str3, c1966g, f10, z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, i7, i10, 1);
        }
    }

    public static final void n(g0.o oVar, String str, String str2, String str3, C1966G c1966g, float f10, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C0761q c0761q, int i7, int i10) {
        int i11;
        int i12;
        c0761q.S(-551389951);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= (32768 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.c(f10) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i11 |= c0761q.g(z6) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.f(str4) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.g(z10) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.h(interfaceC2772a3) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.h(interfaceC2782k2) ? 2048 : 1024;
        }
        if ((i11 & 306783379) == 306783378 && (i12 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarT = h6.r.t(androidx.compose.foundation.layout.c.c(oVar, 1.0f), h6.r.q(c0761q));
            m6.b bVar = m6.c.f19252a;
            g0.o oVarL = androidx.compose.foundation.layout.a.l(oVarT, bVar.E0(c0761q), bVar.F0(c0761q));
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar2 = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                objG = new p(bVar2);
                c0761q.a0(objG);
            }
            p pVar = (p) objG;
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new C1283j();
                c0761q.a0(objG2);
            }
            C1283j c1283j = (C1283j) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG3);
            }
            X x8 = (X) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = new C1285l(c1283j);
                c0761q.a0(objG4);
            }
            C1285l c1285l = (C1285l) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = C0735d.O(m7.q.f19267a, S.f8222n);
                c0761q.a0(objG5);
            }
            X x9 = (X) objG5;
            boolean zH = c0761q.h(pVar) | c0761q.d(257);
            Object objG6 = c0761q.G();
            if (zH || objG6 == obj) {
                objG6 = new M1(x9, pVar, c1285l, x8, 6);
                c0761q.a0(objG6);
            }
            C0.K k10 = (C0.K) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = new N1(x8, c1285l, 6);
                c0761q.a0(objG7);
            }
            InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) objG7;
            boolean zH2 = c0761q.h(pVar);
            Object objG8 = c0761q.G();
            if (zH2 || objG8 == obj) {
                objG8 = new O1(pVar, 6);
                c0761q.a0(objG8);
            }
            c0.a(L0.k.a(oVarL, false, (InterfaceC2782k) objG8), b0.g.b(1200550679, new U5.n(x9, c1283j, interfaceC2772a4, str, str2, str3, c1966g, z10, interfaceC2782k, interfaceC2782k2, z6, f10, str4, interfaceC2772a3, interfaceC2772a, interfaceC2772a2, 1), c0761q), k10, c0761q, 48);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0807d(oVar, str, str2, str3, c1966g, f10, z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, i7, i10, 0);
        }
    }

    public static final void o(g0.o oVar, String str, String str2, String str3, C1966G c1966g, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C0761q c0761q, int i7, int i10) {
        int i11;
        int i12;
        A7.m.f(str4, "encouragementMessage");
        A7.m.f(interfaceC2772a, "onRetry");
        A7.m.f(interfaceC2772a2, "onContinue");
        A7.m.f(interfaceC2772a3, "onAnimationEnd");
        A7.m.f(interfaceC2782k, "playSoundFx");
        A7.m.f(interfaceC2782k2, "stopSoundFx");
        c0761q.S(2090359350);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= (i7 & 32768) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.g(z6) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i11 |= c0761q.f(str4) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z10) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.h(interfaceC2772a3) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.h(interfaceC2782k2) ? 256 : 128;
        }
        int i13 = i12;
        if ((i11 & 306783379) == 306783378 && (i13 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(727197365);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.L(0.0f);
                c0761q.a0(objG);
            }
            C0730a0 c0730a0 = (C0730a0) objG;
            c0761q.p(false);
            c0761q.Q(727200290);
            int i14 = 57344 & i11;
            boolean z11 = i14 == 16384 || ((i11 & 32768) != 0 && c0761q.h(c1966g));
            Object objG2 = c0761q.G();
            if (z11 || objG2 == s6) {
                objG2 = new U5.p(c1966g, c0730a0, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, c1966g, (InterfaceC2785n) objG2);
            int i15 = U5.q.f9176a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()];
            if (i15 == 1 || i15 == 2 || i15 == 3) {
                c0761q.Q(727207550);
                int i16 = i11 << 3;
                int i17 = i13 << 3;
                m(oVar, str, str2, str3, c1966g, c0730a0.i(), z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, c0761q, (i11 & 8190) | 32768 | i14 | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024) | (i16 & 1879048192), ((i11 >> 27) & 14) | (i17 & 112) | (i17 & 896) | (i17 & 7168));
                c0761q.p(false);
            } else {
                c0761q.Q(727228222);
                int i18 = i11 << 3;
                int i19 = i13 << 3;
                n(oVar, str, str2, str3, c1966g, c0730a0.i(), z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, c0761q, (i11 & 8190) | 32768 | i14 | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192), ((i11 >> 27) & 14) | (i19 & 112) | (i19 & 896) | (i19 & 7168));
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.p(oVar, str, str2, str3, c1966g, z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, i7, i10, 1);
        }
    }

    public static final void p(g0.o oVar, final InterfaceC2785n interfaceC2785n, final b6.g gVar, final boolean z6, final InterfaceC2782k interfaceC2782k, final InterfaceC2772a interfaceC2772a, final InterfaceC2772a interfaceC2772a2, final InterfaceC2772a interfaceC2772a3, final L l, C0761q c0761q, final int i7) {
        final g0.o oVar2;
        c0761q.S(-1631203075);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(gVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a3) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.f(l) ? 67108864 : 33554432;
        }
        int i11 = i10;
        if ((i11 & 38347923) == 38347922 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            int i12 = i11 << 12;
            com.bumptech.glide.c.A(androidx.compose.foundation.layout.c.c(lVar, 1.0f), null, com.bumptech.glide.c.W(Integer.MAX_VALUE, z6, interfaceC2782k, interfaceC2772a, interfaceC2772a2, interfaceC2772a3, c0761q), null, l, interfaceC2785n, gVar, false, null, c0761q, ((i11 >> 12) & 57344) | (458752 & i12) | (i12 & 3670016), 394);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.B1
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    InterfaceC2772a interfaceC2772a4 = interfaceC2772a3;
                    D.L l10 = l;
                    AbstractC1274a.p(oVar2, interfaceC2785n, gVar, z6, interfaceC2782k, interfaceC2772a, interfaceC2772a2, interfaceC2772a4, l10, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void q(f6.p pVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-784590813);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(pVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(pVar, AbstractC2673a.X(R.string.MyKaraoke_AppInfo, c0761q), false, null, 250), b0.g.b(-1670617563, new y(pVar, 8), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 7, pVar);
        }
    }

    public static final void r(g0.o oVar, String str, String str2, String str3, boolean z6, C0761q c0761q, int i7) {
        int i10;
        C0158h c0158h;
        C0158h c0158h2;
        C0159i c0159i;
        m6.b bVar;
        int i11;
        c0761q.S(644274217);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z6) ? 16384 : 8192;
        }
        int i12 = i10;
        if ((i12 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarG = androidx.compose.foundation.layout.a.g(oVar, 2);
            g0.f fVar = g0.b.f14670v;
            C0119a c0119a = AbstractC0127i.f1076a;
            m6.b bVar2 = m6.c.f19252a;
            D.S sB = D.P.b(new C0124f(bVar2.F(c0761q)), fVar, c0761q, 48);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarG);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i2 = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i2);
            } else {
                c0761q.d0();
            }
            C0158h c0158h3 = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h3);
            C0158h c0158h4 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h4);
            C0158h c0158h5 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h5);
            }
            C0158h c0158h6 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h6);
            c0761q.Q(-373630358);
            g0.l lVar = g0.l.f14684a;
            if (z6) {
                c0158h = c0158h4;
                c0158h2 = c0158h5;
                c0159i = c0159i2;
                bVar = bVar2;
                i11 = i12;
            } else {
                c0158h = c0158h4;
                c0158h2 = c0158h5;
                c0159i = c0159i2;
                bVar = bVar2;
                i11 = i12;
                A3.p.b(str3, h6.q.m(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), J.e.a(bVar2.f0(c0761q))), l9.l.W(R.drawable.default_tile, c0761q, 0), l9.l.W(R.drawable.default_tile, c0761q, 0), c0761q, ((i12 >> 9) & 14) | 48, 64480);
            }
            c0761q.p(false);
            g0.o oVarB = T.b(lVar, 1.0f);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, z6 ? g0.b.f14673y : g0.b.f14672x, c0761q, 0);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarB);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h3);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h2);
            }
            C0735d.V(c0761q, oVarD2, c0158h6);
            c0761q.Q(-1517980232);
            if (z6) {
                c0761q.Q(194808704);
                float f10 = 100;
                float f11 = 110;
                float f12 = 120;
                switch (m6.a.f19251a[((LayoutMode) c0761q.k(AbstractC1692D.f18238a)).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        c0761q.Q(-2036471007);
                        c0761q.p(false);
                        break;
                    case 4:
                        c0761q.Q(-2036469598);
                        c0761q.p(false);
                        f10 = f11;
                        break;
                    case 5:
                    case 6:
                        c0761q.Q(-2036466911);
                        c0761q.p(false);
                        f10 = f12;
                        break;
                    case 7:
                        c0761q.Q(-2036465437);
                        f10 = m6.b.i(bVar, c0761q) ? f11 : f12;
                        c0761q.p(false);
                        break;
                    case 8:
                        c0761q.Q(-2036462878);
                        f10 = m6.b.k(bVar, c0761q) ? f11 : f12;
                        c0761q.p(false);
                        break;
                    case 9:
                        c0761q.Q(-2036460330);
                        c0761q.Q(-2036460225);
                        boolean z10 = m6.b.k(bVar, c0761q) || m6.b.i(bVar, c0761q);
                        c0761q.p(false);
                        f10 = z10 ? f11 : f12;
                        c0761q.p(false);
                        break;
                    default:
                        throw M5.b.d(-2036475262, c0761q, false);
                }
                c0761q.p(false);
                A3.p.b(str3, h6.q.m(androidx.compose.foundation.layout.c.l(lVar, f10), J.e.a(bVar.f0(c0761q))), l9.l.W(R.drawable.default_tile, c0761q, 0), l9.l.W(R.drawable.default_tile, c0761q, 0), c0761q, ((i11 >> 9) & 14) | 48, 64480);
            }
            c0761q.p(false);
            long j3 = l6.d.c(c0761q).f18696b;
            long jG = h6.f.g(FontSize.H9, c0761q);
            R0 r02 = O5.b.f5155b;
            m6.b bVar3 = bVar;
            c1.b(str, null, j3, jG, null, null, null, 0L, null, new Y0.i(5), 0L, 2, false, 1, 0, null, ((O5.a) c0761q.k(r02)).l, c0761q, (i11 >> 3) & 14, 3120, 54770);
            c0761q.Q(-1517948920);
            if (!z6) {
                AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar3.H(6, c0761q)));
            }
            c0761q.p(false);
            c1.b(AbstractC2673a.X(R.string.Global_InTheStyleOf, c0761q), null, l6.d.c(c0761q).f18689V, h6.f.g(FontSize.Caption, c0761q), null, null, null, 0L, null, new Y0.i(5), 0L, 2, false, 1, 0, null, ((O5.a) c0761q.k(r02)).f5146f, c0761q, 0, 3120, 54770);
            c1.b(str2, null, l6.d.c(c0761q).f18696b, h6.f.g(FontSize.f13042H8, c0761q), null, null, null, 0L, null, new Y0.i(5), 0L, 2, false, 1, 0, null, ((O5.a) c0761q.k(r02)).d, c0761q, (i11 >> 6) & 14, 3120, 54770);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new U2(oVar, str, str2, str3, z6, i7);
        }
    }

    public static final void s(final V5.o oVar, final InterfaceC2772a interfaceC2772a, final g0.o oVar2, boolean z6, float f10, final g0.c cVar, final L l, final InterfaceC2787p interfaceC2787p, boolean z10, final b0.b bVar, C0761q c0761q, final int i7) {
        int i10;
        final boolean z11;
        final float f11;
        final boolean z12;
        A7.m.f(interfaceC2772a, "onRefresh");
        c0761q.S(641518836);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar2) ? 256 : 128;
        }
        int i11 = i10 | 27648;
        if ((196608 & i7) == 0) {
            i11 |= c0761q.f(cVar) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.f(l) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i11 |= c0761q.h(interfaceC2787p) ? 8388608 : 4194304;
        }
        int i12 = i11 | 100663296;
        if ((805306368 & i7) == 0) {
            i12 |= c0761q.h(bVar) ? 536870912 : 268435456;
        }
        if ((306783379 & i12) == 306783378 && c0761q.x()) {
            c0761q.K();
            z11 = z6;
            f11 = f10;
            z12 = z10;
        } else {
            float f12 = 80;
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                Object c0772x = new C0772x(C0735d.B(c0761q));
                c0761q.a0(c0772x);
                objG = c0772x;
            }
            X8.c cVar2 = ((C0772x) objG).l;
            X xR = C0735d.R(interfaceC2772a, c0761q);
            Boolean bool = (Boolean) oVar.d.getValue();
            bool.getClass();
            c0761q.Q(297036403);
            int i13 = i12 & 14;
            boolean z13 = i13 == 4;
            Object objG2 = c0761q.G();
            if (z13 || objG2 == obj) {
                objG2 = new V5.j(oVar, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, bool, (InterfaceC2785n) objG2);
            float fY = ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).y(f12);
            c0761q.Q(297047944);
            boolean zF = (i13 == 4) | c0761q.f(cVar2);
            Object objG3 = c0761q.G();
            if (zF || objG3 == obj) {
                A7.m.d(cVar, "null cannot be cast to non-null type androidx.compose.ui.BiasAlignment");
                objG3 = new V5.l(oVar, cVar2, !(((g0.g) cVar).f14679b == 1.0f), new G6.i(xR, 6));
                c0761q.a0(objG3);
            }
            V5.l lVar = (V5.l) objG3;
            c0761q.p(false);
            lVar.f9525p = true;
            lVar.f9526q = fY;
            g0.o oVarA = androidx.compose.ui.input.nestedscroll.a.a(oVar2, lVar, null);
            g0.g gVar = g0.b.l;
            C0.K kE = AbstractC0133o.e(gVar, false);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarA);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a2 = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a2);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            androidx.compose.foundation.layout.b bVar2 = androidx.compose.foundation.layout.b.f11474a;
            bVar.invoke(c0761q, Integer.valueOf((i12 >> 27) & 14));
            g0.l lVar2 = g0.l.f14684a;
            g0.o oVarN = h6.q.n(bVar2.b(androidx.compose.foundation.layout.a.j(lVar2, l)));
            C0.K kE2 = AbstractC0133o.e(gVar, false);
            int i15 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarN);
            c0761q.U();
            int i16 = i12;
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a2);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i15))) {
                AbstractC1347d.l(i15, c0761q, i15, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            g0.o oVarA2 = bVar2.a(lVar2, cVar);
            C0.K kE3 = AbstractC0133o.e(gVar, false);
            int i17 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarA2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a2);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE3, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i17))) {
                AbstractC1347d.l(i17, c0761q, i17, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            interfaceC2787p.o(oVar, new a1.e(f12), c0761q, Integer.valueOf(i13 | ((i16 >> 9) & 112) | ((i16 >> 15) & 896)));
            M5.b.n(c0761q, true, true, true);
            z11 = true;
            f11 = f12;
            z12 = true;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: V5.i
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj2, Object obj3) {
                    ((Integer) obj3).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    o oVar3 = oVar;
                    b0.b bVar3 = bVar;
                    AbstractC1274a.s(oVar3, interfaceC2772a, oVar2, z11, f11, cVar, l, interfaceC2787p, z12, bVar3, (C0761q) obj2, iZ);
                    return q.f19267a;
                }
            };
        }
    }

    public static final void t(g0.o oVar, W w10, boolean z6, C0761q c0761q, int i7) {
        g0.o oVar2;
        A7.m.f(w10, "videoPlayer");
        c0761q.S(676995762);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(w10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            oVar2 = g0.l.f14684a;
            AbstractC0120b.a(h6.q.n(androidx.compose.foundation.layout.c.c(oVar2, 1.0f)), null, false, b0.g.b(-1798028920, new R5.c(1, w10, z6), c0761q), c0761q, 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Y5.f(oVar2, w10, z6, i7, 0);
        }
    }

    public static final void u(g0.o oVar, W w10, boolean z6, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(871747703);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(w10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        int i11 = i10;
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            c0761q.Q(-335864825);
            boolean zH = c0761q.h(context) | c0761q.h(w10);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new Q5.O(context, 10, w10);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            androidx.compose.ui.viewinterop.a.a((InterfaceC2782k) objG, oVar, null, c0761q, (i11 << 3) & 112, 4);
            androidx.compose.animation.a.c(!z6, null, AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, Y5.b.f10783a, c0761q, 200064, 18);
            androidx.compose.animation.a.c(z6, androidx.compose.foundation.layout.c.c(g0.l.f14684a, 1.0f), AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, Y5.b.f10784b, c0761q, ((i11 >> 6) & 14) | 200112, 16);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Y5.f(oVar, w10, z6, i7, 1);
        }
    }

    public static final void v(g0.o oVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1177876616);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            e1.f fVar = e1.f.f13777b;
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            int i12 = (((((i10 << 3) & 112) | (((i10 >> 3) & 14) | 384)) << 6) & 896) | 6;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, fVar, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            A7.k.o((i12 >> 6) & 14, bVar, c0761q, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new O.G(oVar, bVar, i7, 1);
        }
    }

    public static final P w(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        return new P(1, abstractC0287w);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.ArrayList] */
    public static final boolean x(ArrayList arrayList) {
        ?? arrayList2;
        long j3;
        if (arrayList.size() < 2) {
            return true;
        }
        if (arrayList.size() == 0 || arrayList.size() == 1) {
            arrayList2 = u.l;
        } else {
            arrayList2 = new ArrayList();
            Object obj = arrayList.get(0);
            int iC = AbstractC1999n.C(arrayList);
            int i7 = 0;
            while (i7 < iC) {
                i7++;
                Object obj2 = arrayList.get(i7);
                L0.o oVar = (L0.o) obj2;
                L0.o oVar2 = (L0.o) obj;
                arrayList2.add(new m0.c(h6.e.a(Math.abs(m0.c.d(oVar2.e().a()) - m0.c.d(oVar.e().a())), Math.abs(m0.c.e(oVar2.e().a()) - m0.c.e(oVar.e().a())))));
                obj = obj2;
            }
        }
        if (arrayList2.size() == 1) {
            j3 = ((m0.c) AbstractC1998m.Y(arrayList2)).f18880a;
        } else {
            if (arrayList2.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object objY = AbstractC1998m.Y(arrayList2);
            int iC2 = AbstractC1999n.C(arrayList2);
            if (1 <= iC2) {
                int i10 = 1;
                while (true) {
                    objY = new m0.c(m0.c.h(((m0.c) objY).f18880a, ((m0.c) arrayList2.get(i10)).f18880a));
                    if (i10 == iC2) {
                        break;
                    }
                    i10++;
                }
            }
            j3 = ((m0.c) objY).f18880a;
        }
        return m0.c.e(j3) < m0.c.d(j3);
    }

    public static void y(int i7) {
        if (2 > i7 || i7 >= 37) {
            StringBuilder sbJ = M5.b.j("radix ", i7, " was not in valid range ");
            sbJ.append(new G7.d(2, 36, 1));
            throw new IllegalArgumentException(sbJ.toString());
        }
    }

    public abstract long B();

    public abstract c9.y C();

    public abstract Object J(D0.e eVar);

    public abstract void V(Throwable th);

    public abstract void W(B8.t tVar);

    public abstract void f0(InterfaceC2320j interfaceC2320j);

    public abstract boolean z(D0.e eVar);
}
