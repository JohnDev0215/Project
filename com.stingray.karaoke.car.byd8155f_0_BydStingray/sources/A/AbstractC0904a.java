package a;

import A.C0050y0;
import A.r;
import A7.k;
import A7.m;
import C0.C0102i;
import C0.C0114v;
import C0.InterfaceC0103j;
import C0.c0;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.C0142y;
import E.D;
import E.G;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F0.C0223q;
import F0.W0;
import F8.A;
import G7.c;
import H4.f;
import H4.g;
import H4.h;
import H4.i;
import H7.H;
import I5.y;
import N0.J;
import N0.K;
import N0.L;
import O.C0472c;
import O.C0473d;
import O.C0474e;
import O.C0475f;
import O.C0477h;
import O.C0479j;
import O.InterfaceC0480k;
import O.x;
import O3.j;
import Q.AbstractC0535v;
import Q.K0;
import Q5.C0549b;
import Q5.C0559d1;
import Q5.C0561e;
import Q5.C0563e1;
import Q5.C0574h0;
import Q5.C0575h1;
import Q5.C0591l1;
import Q5.C0595m1;
import Q5.C0599n1;
import Q5.C0607p1;
import Q5.C0620t;
import Q5.C0626u1;
import Q5.L2;
import Q5.M1;
import Q5.M2;
import Q5.N1;
import Q5.O1;
import Q5.P2;
import Q5.Q2;
import Q5.Y;
import Q7.InterfaceC0652e;
import Q7.Q;
import Q8.o;
import S8.k0;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import T.Q0;
import T.S;
import T.X;
import Y2.d;
import a.AbstractC0904a;
import a1.b;
import a1.e;
import a1.n;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.text.InputFilter;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c9.u;
import com.airbnb.lottie.compose.LottieAnimationSizeElement;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import d8.C1113E;
import e1.C1187A;
import e8.C1236a;
import g0.l;
import g1.AbstractC1274a;
import g1.C1283j;
import g1.C1285l;
import g1.p;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import k6.AbstractC1692D;
import k6.C1701i;
import k6.t;
import l0.InterfaceC1716g;
import l3.C1764u;
import l8.InterfaceC1788f;
import m7.q;
import n0.AbstractC1861K;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n3.C1928C;
import n3.C1930E;
import n3.C1940e;
import n3.C1941f;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.z;
import o8.C2057b;
import o8.C2061f;
import w.AbstractC2565A;
import w.O;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.AbstractC2637h;
import x.C2658y;
import x3.AbstractC2673a;
import y0.v;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: a.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0904a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static k0 f11023a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f11024b = 0;
    public static final /* synthetic */ int c = 0;
    public static final /* synthetic */ int d = 0;

    public static boolean A(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static final boolean B(int i7, int i10) {
        return i7 == i10;
    }

    public static r C(C0761q c0761q) {
        float f10 = O.f23082a;
        b bVar = (b) c0761q.k(AbstractC0227s0.f2259f);
        boolean zC = c0761q.c(bVar.getDensity());
        Object objG = c0761q.G();
        Object obj = C0753m.f8270a;
        if (zC || objG == obj) {
            objG = new C2658y(new C1764u(bVar));
            c0761q.a0(objG);
        }
        C2658y c2658y = (C2658y) objG;
        boolean zF = c0761q.f(c2658y);
        Object objG2 = c0761q.G();
        if (zF || objG2 == obj) {
            objG2 = new r(c2658y);
            c0761q.a0(objG2);
        }
        return (r) objG2;
    }

    public static final C2057b D(InterfaceC1788f interfaceC1788f, int i7) {
        m.f(interfaceC1788f, "<this>");
        return C2057b.e(interfaceC1788f.u(i7), interfaceC1788f.p(i7));
    }

    public static final C2061f F(InterfaceC1788f interfaceC1788f, int i7) {
        m.f(interfaceC1788f, "<this>");
        return C2061f.d(interfaceC1788f.n(i7));
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x006c A[LOOP:0: B:4:0x000d->B:37:0x006c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006f A[EDGE_INSN: B:42:0x006f->B:38:0x006f BREAK  A[LOOP:0: B:4:0x000d->B:37:0x006c], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final E0.p0 G(E0.F r7) {
        /*
            E0.Y r7 = r7.f1469H
            java.lang.Object r7 = r7.f1603f
            g0.n r7 = (g0.n) r7
            int r0 = r7.o
            r0 = r0 & 8
            r1 = 0
            if (r0 == 0) goto L6f
        Ld:
            if (r7 == 0) goto L6f
            int r0 = r7.f14686n
            r0 = r0 & 8
            if (r0 == 0) goto L66
            r0 = r7
            r2 = r1
        L17:
            if (r0 == 0) goto L66
            boolean r3 = r0 instanceof E0.p0
            if (r3 == 0) goto L28
            r3 = r0
            E0.p0 r3 = (E0.p0) r3
            boolean r3 = r3.i0()
            if (r3 == 0) goto L61
            r1 = r0
            goto L6f
        L28:
            int r3 = r0.f14686n
            r3 = r3 & 8
            if (r3 == 0) goto L61
            boolean r3 = r0 instanceof E0.AbstractC0164n
            if (r3 == 0) goto L61
            r3 = r0
            E0.n r3 = (E0.AbstractC0164n) r3
            g0.n r3 = r3.f1690z
            r4 = 0
        L38:
            r5 = 1
            if (r3 == 0) goto L5e
            int r6 = r3.f14686n
            r6 = r6 & 8
            if (r6 == 0) goto L5b
            int r4 = r4 + 1
            if (r4 != r5) goto L47
            r0 = r3
            goto L5b
        L47:
            if (r2 != 0) goto L52
            V.d r2 = new V.d
            r5 = 16
            g0.n[] r5 = new g0.n[r5]
            r2.<init>(r5)
        L52:
            if (r0 == 0) goto L58
            r2.b(r0)
            r0 = r1
        L58:
            r2.b(r3)
        L5b:
            g0.n r3 = r3.f14688q
            goto L38
        L5e:
            if (r4 != r5) goto L61
            goto L17
        L61:
            g0.n r0 = E0.AbstractC0156f.f(r2)
            goto L17
        L66:
            int r0 = r7.o
            r0 = r0 & 8
            if (r0 == 0) goto L6f
            g0.n r7 = r7.f14688q
            goto Ld
        L6f:
            E0.p0 r1 = (E0.p0) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.AbstractC0904a.G(E0.F):E0.p0");
    }

    public static final long H(double d10) {
        return O((float) d10, 4294967296L);
    }

    public static final long I(int i7) {
        return O(i7, 4294967296L);
    }

    public static final float J(String str, L l, C0761q c0761q) {
        m.f(str, "text");
        c0761q.Q(535498638);
        float fP0 = ((b) c0761q.k(AbstractC0227s0.f2259f)).p0((int) (J.a(H.M(c0761q), str, l).c >> 32));
        c0761q.p(false);
        return fP0;
    }

    public static final void K(i iVar) {
        m.f(iVar, "<this>");
        if ((iVar instanceof g) || (iVar instanceof h) || !(iVar instanceof f)) {
            return;
        }
    }

    public static final boolean L(long j3) {
        n[] nVarArr = a1.m.f11041b;
        return (j3 & 1095216660480L) == 0;
    }

    public static u M(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        int length = strArr2.length;
        int i7 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            String str = strArr2[i10];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i10] = Q8.h.g1(str).toString();
        }
        int iN = z.n(0, strArr2.length - 1, 2);
        if (iN >= 0) {
            while (true) {
                String str2 = strArr2[i7];
                String str3 = strArr2[i7 + 1];
                t(str2);
                v(str3, str2);
                if (i7 == iN) {
                    break;
                }
                i7 += 2;
            }
        }
        return new u(strArr2);
    }

    public static void N(d dVar, int i7, h2.g gVar) {
        long jD = dVar.d(i7);
        List listG = dVar.g(jD);
        if (listG.isEmpty()) {
            return;
        }
        if (i7 == dVar.h() - 1) {
            throw new IllegalStateException();
        }
        long jD2 = dVar.d(i7 + 1) - dVar.d(i7);
        if (jD2 > 0) {
            gVar.accept(new Y2.a(listG, jD, jD2));
        }
    }

    public static final long O(float f10, long j3) {
        long jFloatToIntBits = j3 | (((long) Float.floatToIntBits(f10)) & 4294967295L);
        n[] nVarArr = a1.m.f11041b;
        return jFloatToIntBits;
    }

    public static C2061f P(C2061f c2061f, String str, String str2, int i7) {
        char cCharAt;
        char cCharAt2;
        Object next;
        boolean z6 = (i7 & 4) != 0;
        if ((i7 & 8) != 0) {
            str2 = null;
        }
        if (!c2061f.f20573m) {
            String strC = c2061f.c();
            if (o.z0(strC, str, false) && strC.length() != str.length() && ('a' > (cCharAt = strC.charAt(str.length())) || cCharAt >= '{')) {
                if (str2 != null) {
                    return C2061f.e(str2.concat(Q8.h.S0(strC, str)));
                }
                if (!z6) {
                    return c2061f;
                }
                String strS0 = Q8.h.S0(strC, str);
                if (strS0.length() != 0 && io.sentry.config.a.f0(0, strS0)) {
                    if (strS0.length() != 1 && io.sentry.config.a.f0(1, strS0)) {
                        c cVarD = new G7.d(0, strS0.length() - 1, 1).iterator();
                        while (true) {
                            if (!cVarD.f2715n) {
                                next = null;
                                break;
                            }
                            next = cVarD.next();
                            if (!io.sentry.config.a.f0(((Number) next).intValue(), strS0)) {
                                break;
                            }
                        }
                        Integer num = (Integer) next;
                        if (num != null) {
                            int iIntValue = num.intValue() - 1;
                            String strSubstring = strS0.substring(0, iIntValue);
                            m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                            String strN0 = io.sentry.config.a.n0(strSubstring);
                            String strSubstring2 = strS0.substring(iIntValue);
                            m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
                            strS0 = strN0.concat(strSubstring2);
                        } else {
                            strS0 = io.sentry.config.a.n0(strS0);
                        }
                    } else if (strS0.length() != 0 && 'A' <= (cCharAt2 = strS0.charAt(0)) && cCharAt2 < '[') {
                        char lowerCase = Character.toLowerCase(cCharAt2);
                        String strSubstring3 = strS0.substring(1);
                        m.e(strSubstring3, "this as java.lang.String).substring(startIndex)");
                        strS0 = lowerCase + strSubstring3;
                    }
                }
                if (C2061f.f(strS0)) {
                    return C2061f.e(strS0);
                }
            }
        }
        return null;
    }

    public static C1236a S(int i7, boolean z6, boolean z10, C1113E c1113e, int i10) {
        boolean z11 = (i10 & 1) != 0 ? false : z6;
        boolean z12 = (i10 & 2) != 0 ? false : z10;
        if ((i10 & 4) != 0) {
            c1113e = null;
        }
        k.p(i7, "<this>");
        return new C1236a(i7, z12, z11, c1113e != null ? AbstractC1985B.t(c1113e) : null, 34);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void T(Y2.d r13, Y2.k r14, h2.g r15) {
        /*
            long r0 = r14.f10648a
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r5 = 0
            if (r4 != 0) goto Le
            r4 = r5
            goto L27
        Le:
            int r4 = r13.a(r0)
            r6 = -1
            if (r4 != r6) goto L19
            int r4 = r13.h()
        L19:
            if (r4 <= 0) goto L27
            int r6 = r4 + (-1)
            long r6 = r13.d(r6)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 != 0) goto L27
            int r4 = r4 + (-1)
        L27:
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L52
            int r2 = r13.h()
            if (r4 >= r2) goto L52
            java.util.List r7 = r13.g(r0)
            long r2 = r13.d(r4)
            boolean r6 = r7.isEmpty()
            if (r6 != 0) goto L52
            long r8 = r14.f10648a
            int r6 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r6 >= 0) goto L52
            Y2.a r12 = new Y2.a
            long r10 = r2 - r8
            r6 = r12
            r6.<init>(r7, r8, r10)
            r15.accept(r12)
            r2 = 1
            goto L53
        L52:
            r2 = r5
        L53:
            r3 = r4
        L54:
            int r6 = r13.h()
            if (r3 >= r6) goto L60
            N(r13, r3, r15)
            int r3 = r3 + 1
            goto L54
        L60:
            boolean r14 = r14.f10649b
            if (r14 == 0) goto L89
            if (r2 == 0) goto L68
            int r4 = r4 + (-1)
        L68:
            if (r5 >= r4) goto L70
            N(r13, r5, r15)
            int r5 = r5 + 1
            goto L68
        L70:
            if (r2 == 0) goto L89
            Y2.a r14 = new Y2.a
            java.util.List r7 = r13.g(r0)
            long r8 = r13.d(r4)
            long r2 = r13.d(r4)
            long r10 = r0 - r2
            r6 = r14
            r6.<init>(r7, r8, r10)
            r15.accept(r14)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.AbstractC0904a.T(Y2.d, Y2.k, h2.g):void");
    }

    public static final long U(long j3, long j10) {
        int iC;
        int iE = K.e(j3);
        int iD = K.d(j3);
        if (K.e(j10) >= K.d(j3) || K.e(j3) >= K.d(j10)) {
            if (iD > K.e(j10)) {
                iE -= K.c(j10);
                iC = K.c(j10);
                iD -= iC;
            }
        } else if (K.e(j10) > K.e(j3) || K.d(j3) > K.d(j10)) {
            if (K.e(j3) > K.e(j10) || K.d(j10) > K.d(j3)) {
                int iE2 = K.e(j10);
                if (iE >= K.d(j10) || iE2 > iE) {
                    iD = K.e(j10);
                } else {
                    iE = K.e(j10);
                    iC = K.c(j10);
                }
            } else {
                iC = K.c(j10);
            }
            iD -= iC;
        } else {
            iE = K.e(j10);
            iD = iE;
        }
        return q(iE, iD);
    }

    public static final void a(final int i7, C0761q c0761q, final boolean z6) {
        int i10;
        c0761q.S(649504546);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else if (z6) {
            io.sentry.config.a.t(androidx.compose.foundation.a.b(l.f14684a, l6.d.c(c0761q).f18720v, AbstractC1861K.f19381a), 0L, c0761q, 0, 2);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.c1
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).intValue();
                    AbstractC0904a.a(C0735d.Z(i7 | 1), (C0761q) obj, z6);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void b(g0.o oVar, final List list, final List list2, final String str, final int i7, final LoadingInterface$LoadingState loadingInterface$LoadingState, final InterfaceC2785n interfaceC2785n, final InterfaceC2785n interfaceC2785n2, final InterfaceC2782k interfaceC2782k, final InterfaceC2782k interfaceC2782k2, final InterfaceC2785n interfaceC2785n3, final b6.g gVar, final D.L l, final Integer num, final boolean z6, C0761q c0761q, final int i10, final int i11) {
        int i12;
        final g0.o oVar2;
        c0761q.S(1176322036);
        int i13 = i10 | 6;
        if ((i10 & 48) == 0) {
            i13 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i13 |= c0761q.h(list2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i13 |= c0761q.f(str) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= c0761q.d(i7) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i13 |= c0761q.f(loadingInterface$LoadingState) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i13 |= c0761q.h(interfaceC2785n) ? 1048576 : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i13 |= c0761q.h(interfaceC2785n2) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 67108864 : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i13 |= c0761q.h(interfaceC2782k2) ? 536870912 : 268435456;
        }
        int i14 = i13;
        if ((i11 & 6) == 0) {
            i12 = i11 | (c0761q.h(interfaceC2785n3) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= c0761q.h(gVar) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i12 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= c0761q.f(num) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= c0761q.g(z6) ? 16384 : 8192;
        }
        int i15 = i12;
        if ((i14 & 306783379) == 306783378 && (i15 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            l lVar = l.f14684a;
            InterfaceC1716g interfaceC1716g = (InterfaceC1716g) c0761q.k(AbstractC0227s0.f2260g);
            g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            q qVar = q.f19267a;
            c0761q.Q(-931155640);
            boolean zH = c0761q.h(interfaceC1716g);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new Y(interfaceC1716g, null);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarA = v.a(oVarD, qVar, (InterfaceC2785n) objG);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(m6.c.f19252a.F0(c0761q)), g0.b.f14673y, c0761q, 48);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarA);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            int i17 = i14 >> 9;
            int i18 = i14 >> 3;
            int i19 = i15 << 18;
            com.bumptech.glide.c.n(str, list2, loadingInterface$LoadingState, interfaceC2785n, interfaceC2785n2, interfaceC2782k, interfaceC2785n3, gVar, l, z6, c0761q, (i17 & 458752) | (i17 & 14) | (i18 & 112) | (i17 & 896) | (i17 & 7168) | (i17 & 57344) | (i19 & 3670016) | (i19 & 29360128) | (i19 & 234881024) | ((i15 << 15) & 1879048192));
            c0761q.Q(-562459630);
            if (Q8.h.M0(str)) {
                int i20 = i14 >> 18;
                int i21 = (i18 & 14) | (i20 & 112) | ((i14 >> 6) & 896) | (i20 & 7168);
                int i22 = i15 << 12;
                d(list, interfaceC2785n2, i7, interfaceC2782k2, interfaceC2785n3, gVar, l, num, c0761q, i21 | (i22 & 57344) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128));
            }
            c0761q.p(false);
            c0761q.p(true);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.X
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    int iZ2 = C0735d.Z(i11);
                    Integer num2 = num;
                    boolean z10 = z6;
                    AbstractC0904a.b(oVar2, list, list2, str, i7, loadingInterface$LoadingState, interfaceC2785n, interfaceC2785n2, interfaceC2782k, interfaceC2782k2, interfaceC2785n3, gVar, l, num2, z10, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void c(e6.g gVar, boolean z6, C0761q c0761q, int i7, int i10) {
        int i11;
        boolean z10;
        m.f(gVar, "viewModel");
        c0761q.S(-1067068970);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.h(gVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        int i12 = i10 & 2;
        if (i12 != 0) {
            i11 |= 48;
        } else if ((i7 & 48) == 0) {
            i11 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            z10 = z6;
        } else {
            boolean z11 = i12 != 0 ? false : z6;
            c0761q.Q(1511184859);
            String strX = z11 ? AbstractC2673a.X(R.string.Tab_Explore, c0761q) : null;
            c0761q.p(false);
            l(null, new C0607p1(gVar, strX, false, new C1701i(1.4f, 5), 122), b0.g.b(-733993452, new y(gVar, 4), c0761q), c0761q, 384, 1);
            z10 = z11;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0620t(gVar, z10, i7, i10, 1);
        }
    }

    public static final void d(final List list, final InterfaceC2785n interfaceC2785n, final int i7, final InterfaceC2782k interfaceC2782k, final InterfaceC2785n interfaceC2785n2, final b6.g gVar, final D.L l, final Integer num, C0761q c0761q, final int i10) {
        int i11;
        c0761q.S(-1330132856);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.h(list) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.h(interfaceC2785n2) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(gVar) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= c0761q.f(l) ? 1048576 : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= c0761q.f(num) ? 8388608 : 4194304;
        }
        if ((4793491 & i11) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            D dA = G.a(i7, 0, c0761q, (i11 >> 6) & 14, 2);
            if (list != null && !list.isEmpty()) {
                AbstractC0120b.a(null, null, false, b0.g.b(2125226999, new C0574h0(list, interfaceC2785n2, dA, interfaceC2782k, i7, l, interfaceC2785n, gVar, num), c0761q), c0761q, 3072, 7);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.W
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).intValue();
                    AbstractC0904a.d(list, interfaceC2785n, i7, interfaceC2782k, interfaceC2785n2, gVar, l, num, (C0761q) obj, C0735d.Z(i10 | 1));
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void e(InterfaceC0480k interfaceC0480k, g0.c cVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(476043083);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(interfaceC0480k) : c0761q.h(interfaceC0480k) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(cVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean z6 = false;
            boolean z10 = (i10 & 112) == 32;
            if ((i10 & 14) == 4 || ((i10 & 8) != 0 && c0761q.f(interfaceC0480k))) {
                z6 = true;
            }
            boolean z11 = z10 | z6;
            Object objG = c0761q.G();
            if (z11 || objG == C0753m.f8270a) {
                objG = new C0479j(cVar, interfaceC0480k);
                c0761q.a0(objG);
            }
            e1.m.a((C0479j) objG, null, new C1187A(false, true, true, 1, true, false), bVar, c0761q, ((i10 << 3) & 7168) | 384, 2);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(interfaceC0480k, cVar, bVar, i7, 6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:208:0x0314  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void f(g0.o r34, F.y r35, F.c r36, D.L r37, boolean r38, A.r r39, boolean r40, D.InterfaceC0125g r41, D.InterfaceC0123e r42, Q5.C0598n0 r43, T.C0761q r44, int r45, int r46) {
        /*
            Method dump skipped, instruction units count: 907
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.AbstractC0904a.f(g0.o, F.y, F.c, D.L, boolean, A.r, boolean, D.g, D.e, Q5.n0, T.q, int, int):void");
    }

    public static final void g(O3.a aVar, InterfaceC2772a interfaceC2772a, g0.o oVar, boolean z6, boolean z10, boolean z11, boolean z12, int i7, boolean z13, g0.c cVar, InterfaceC0103j interfaceC0103j, boolean z14, boolean z15, Map map, int i10, boolean z16, C0761q c0761q, int i11, int i12) {
        m.f(interfaceC2772a, "progress");
        c0761q.S(382909894);
        g0.g gVar = g0.b.f14664p;
        C0.O o = C0102i.f903b;
        c0761q.R(185152185);
        Object objG = c0761q.G();
        S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = new j();
            c0761q.a0(objG);
        }
        j jVar = (j) objG;
        c0761q.p(false);
        c0761q.R(185152232);
        Object objG2 = c0761q.G();
        if (objG2 == s6) {
            objG2 = new Matrix();
            c0761q.a0(objG2);
        }
        Matrix matrix = (Matrix) objG2;
        c0761q.p(false);
        c0761q.R(185152312);
        boolean zF = c0761q.f(aVar);
        Object objG3 = c0761q.G();
        Map map2 = null;
        if (zF || objG3 == s6) {
            objG3 = C0735d.O(null, S.f8224q);
            c0761q.a0(objG3);
        }
        X x8 = (X) objG3;
        c0761q.p(false);
        c0761q.R(185152364);
        boolean z17 = false;
        boolean z18 = false;
        boolean z19 = true;
        boolean z20 = false;
        int i13 = 1;
        boolean z21 = false;
        boolean z22 = true;
        boolean z23 = false;
        int i14 = 1;
        boolean z24 = false;
        if (aVar == null || aVar.b() == 0.0f) {
            AbstractC0133o.a(oVar, c0761q, (i11 >> 6) & 14);
            c0761q.p(false);
            C0754m0 c0754m0R = c0761q.r();
            if (c0754m0R != null) {
                c0754m0R.d = new S3.i(aVar, interfaceC2772a, oVar, z17, z18, z19, z20, i13, z21, gVar, o, z22, z23, map2, i14, z24, i11, i12, 0);
                return;
            }
            return;
        }
        c0761q.p(false);
        Rect rect = aVar.f5077k;
        Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
        int iWidth = rect.width();
        int iHeight = rect.height();
        m.f(oVar, "<this>");
        h6.j.b(0, c0761q, oVar.k(new LottieAnimationSizeElement(iWidth, iHeight)), new S3.j(rect, o, gVar, matrix, jVar, false, false, 1, 1, aVar, null, false, false, true, false, true, false, context, interfaceC2772a, x8));
        C0754m0 c0754m0R2 = c0761q.r();
        if (c0754m0R2 != null) {
            c0754m0R2.d = new S3.i(aVar, interfaceC2772a, oVar, z17, z18, z19, z20, i13, z21, gVar, o, z22, z23, map2, i14, z24, i11, i12, 1);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r14v4 ??, still in use, count: 1, list:
          (r14v4 ?? I:java.lang.Object) from 0x01d2: INVOKE (r26v0 ?? I:T.q), (r14v4 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final void h(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r14v4 ??, still in use, count: 1, list:
          (r14v4 ?? I:java.lang.Object) from 0x01d2: INVOKE (r26v0 ?? I:T.q), (r14v4 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r21v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    public static final void i(g0.o oVar, final List list, final InterfaceC2782k interfaceC2782k, final StingrayKaraokeTabScreen stingrayKaraokeTabScreen, boolean z6, final InterfaceC2772a interfaceC2772a, C0761q c0761q, final int i7) {
        final g0.o oVar2;
        final boolean z10;
        X x8;
        m.f(list, "allScreens");
        m.f(interfaceC2782k, "onTabSelected");
        m.f(stingrayKaraokeTabScreen, "currentScreen");
        m.f(interfaceC2772a, "navigateToDevSettings");
        c0761q.S(490940770);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(stingrayKaraokeTabScreen) ? 2048 : 1024;
        }
        int i11 = i10 | 24576;
        if ((196608 & i7) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
            z10 = z6;
        } else {
            l lVar = l.f14684a;
            c0761q.Q(578274505);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(false, S.f8224q);
                c0761q.a0(objG);
            }
            X x9 = (X) objG;
            c0761q.p(false);
            Q0 q0B = AbstractC2637h.b(((Boolean) x9.getValue()).booleanValue() ? 0.4f : 0.0f, AbstractC2631e.q(200, 0, AbstractC2614C.c, 2), "NavTabExpand", null, c0761q, 3072, 20);
            c0761q.Q(2117181601);
            c0761q.Q(1985396463);
            List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.d.c(c0761q).o)), new m7.i(Float.valueOf(0.6f), new C1891t(l6.d.c(c0761q).f18714p)), new m7.i(Float.valueOf(1.0f), new C1891t(C1891t.f19451j)));
            c0761q.p(false);
            m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
            C1853C c1853cK = C1886o.k((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
            c0761q.p(false);
            g0.o oVarD = androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.c.b(androidx.compose.foundation.a.a(lVar, c1853cK, null, 6), 1.0f), ((Number) q0B.getValue()).floatValue());
            c0761q.Q(578290914);
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new G6.i(x9, 7);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            AbstractC0133o.a(com.bumptech.glide.c.O(oVarD, (InterfaceC2772a) objG2), c0761q, 0);
            g0.o oVarP = androidx.compose.foundation.layout.a.p(androidx.compose.foundation.layout.c.b(lVar, 1.0f));
            m6.b bVar = m6.c.f19252a;
            g0.o oVarM = androidx.compose.foundation.layout.a.m(oVarP, bVar.C0(6, c0761q), 0.0f, 2);
            c0761q.Q(578301666);
            Object objG3 = c0761q.G();
            if (objG3 == s6) {
                objG3 = new G6.i(x9, 8);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            g0.o oVarO = com.bumptech.glide.c.O(oVarM, (InterfaceC2772a) objG3);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarO);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            C0142y c0142y = C0142y.f1119a;
            g0.o oVarL = androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.a.o(lVar, 0.0f, bVar.F0(c0761q), 0.0f, bVar.H(6, c0761q) * 1.5f, 5), bVar.D0(6, c0761q));
            c0761q.Q(-1524137455);
            boolean z11 = (458752 & i11) == 131072;
            Object objG4 = c0761q.G();
            if (z11 || objG4 == s6) {
                objG4 = new G6.i(interfaceC2772a, x9);
                c0761q.a0(objG4);
            }
            c0761q.p(false);
            float f10 = 1.0f;
            X x10 = x9;
            AbstractC0535v.a(l9.l.W(R.drawable.ic_burger, c0761q, 0), "menu_toggle", com.bumptech.glide.c.Q(7, oVarL, (InterfaceC2772a) objG4, false), l6.d.c(c0761q).f18696b, c0761q, 48, 0);
            AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
            c0761q.Q(-1524122042);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                StingrayKaraokeTabScreen stingrayKaraokeTabScreen2 = (StingrayKaraokeTabScreen) it.next();
                g0.o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, f10);
                String strX = AbstractC2673a.X(stingrayKaraokeTabScreen2.getLabelId(), c0761q);
                int iconId = stingrayKaraokeTabScreen2.getIconId();
                boolean z12 = stingrayKaraokeTabScreen == stingrayKaraokeTabScreen2;
                boolean zBooleanValue = ((Boolean) x10.getValue()).booleanValue();
                c0761q.Q(-7735208);
                boolean zF = ((i11 & 896) == 256) | c0761q.f(stingrayKaraokeTabScreen2);
                Object objG5 = c0761q.G();
                if (zF || objG5 == s6) {
                    x8 = x10;
                    objG5 = new C0626u1(interfaceC2782k, stingrayKaraokeTabScreen2, x8, 1);
                    c0761q.a0(objG5);
                } else {
                    x8 = x10;
                }
                c0761q.p(false);
                r(oVarD3, iconId, strX, zBooleanValue, z12, (InterfaceC2772a) objG5, c0761q, 6);
                AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
                x10 = x8;
                f10 = 1.0f;
            }
            c0761q.p(false);
            AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 5.0f));
            c0761q.p(true);
            oVar2 = lVar;
            z10 = false;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: W5.m
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    boolean z13 = z10;
                    InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
                    AbstractC0904a.i(oVar2, list, interfaceC2782k, stingrayKaraokeTabScreen, z13, interfaceC2772a2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void j(final g0.o oVar, final ArrayList arrayList, List list, final int i7, final InterfaceC2782k interfaceC2782k, long j3, C0761q c0761q, final int i10) {
        int i11;
        List list2;
        long j10;
        final long j11;
        m.f(interfaceC2782k, "onSelection");
        c0761q.S(1632040200);
        int i12 = (i10 & 6) == 0 ? (c0761q.f(oVar) ? 4 : 2) | i10 : i10;
        if ((i10 & 48) == 0) {
            i12 |= c0761q.h(arrayList) ? 32 : 16;
        }
        int i13 = i12 | 384;
        if ((i10 & 3072) == 0) {
            i13 |= c0761q.d(i7) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i13 |= 65536;
        }
        if ((74899 & i13) == 74898 && c0761q.x()) {
            c0761q.K();
            list2 = list;
            j11 = j3;
        } else {
            c0761q.M();
            if ((i10 & 1) == 0 || c0761q.w()) {
                i11 = i13 & (-458753);
                list2 = n7.u.l;
                j10 = l6.d.c(c0761q).f18710j;
            } else {
                c0761q.K();
                i11 = i13 & (-458753);
                list2 = list;
                j10 = j3;
            }
            c0761q.q();
            c0761q.Q(-456403496);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.M(i7);
                c0761q.a0(objG);
            }
            C0732b0 c0732b0 = (C0732b0) objG;
            Object objE = M5.b.e(-456401251, c0761q, false);
            S s10 = S.f8224q;
            if (objE == s6) {
                int size = arrayList.size();
                ArrayList arrayList2 = new ArrayList(size);
                for (int i14 = 0; i14 < size; i14++) {
                    arrayList2.add(new e(0));
                }
                objE = C0735d.O(arrayList2, s10);
                c0761q.a0(objE);
            }
            X x8 = (X) objE;
            Object objE2 = M5.b.e(-456398871, c0761q, false);
            if (objE2 == s6) {
                objE2 = C0735d.O(new e(0), s10);
                c0761q.a0(objE2);
            }
            X x9 = (X) objE2;
            Object objE3 = M5.b.e(-456396918, c0761q, false);
            if (objE3 == s6) {
                objE3 = C0735d.O(Boolean.FALSE, s10);
                c0761q.a0(objE3);
            }
            X x10 = (X) objE3;
            c0761q.p(false);
            b bVar = (b) c0761q.k(AbstractC0227s0.f2259f);
            FontSize fontSize = FontSize.Subtitle1;
            q qVar = q.f19267a;
            c0761q.Q(-456392210);
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new L2(x10, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar, (InterfaceC2785n) objG2);
            Integer numValueOf = Integer.valueOf(i7);
            c0761q.Q(-456389736);
            boolean z6 = (i11 & 7168) == 2048;
            Object objG3 = c0761q.G();
            if (z6 || objG3 == s6) {
                objG3 = new M2(i7, c0732b0, null);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C0735d.f(c0761q, numValueOf, (InterfaceC2785n) objG3);
            androidx.compose.animation.a.c(((Boolean) x10.getValue()).booleanValue(), null, AbstractC2565A.b(AbstractC2631e.q(0, 100, null, 5), 2), AbstractC2565A.c(null, 3), null, b0.g.b(767826400, new P2(oVar, j10, bVar, x8, c0732b0, x9, arrayList, interfaceC2782k, list2, fontSize), c0761q), c0761q, 200064, 18);
            j11 = j10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            final List list3 = list2;
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.J2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    ArrayList arrayList3 = arrayList;
                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                    long j12 = j11;
                    AbstractC0904a.j(oVar, arrayList3, list3, i7, interfaceC2782k2, j12, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void k(final PlayerStatus playerStatus, final InterfaceC2772a interfaceC2772a, final C1930E c1930e, final InterfaceC2772a interfaceC2772a2, C0761q c0761q, int i7) {
        int i10;
        m.f(playerStatus, "playerStatus");
        m.f(interfaceC2772a, "onClose");
        m.f(interfaceC2772a2, "navigateBack");
        c0761q.S(1964911695);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(playerStatus) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(c1930e) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            String value = PlayerSideMenuRoute.SongQueue.getValue();
            c0761q.Q(-1329297734);
            boolean zH = ((i10 & 112) == 32) | ((i10 & 7168) == 2048) | ((i10 & 14) == 4) | c0761q.h(c1930e);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new InterfaceC2782k() { // from class: X5.L
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        n3.K k10;
                        C1928C c1928c = (C1928C) obj;
                        A7.m.f(c1928c, "$this$NavHost");
                        String value2 = PlayerSideMenuRoute.SongQueue.getValue();
                        C1930E c1930e2 = c1930e;
                        PlayerStatus playerStatus2 = playerStatus;
                        InterfaceC2772a interfaceC2772a3 = interfaceC2772a2;
                        InterfaceC2772a interfaceC2772a4 = interfaceC2772a;
                        h6.m.i(c1928c, value2, null, new b0.b(1822477196, new S5.j(playerStatus2, c1930e2, interfaceC2772a3, interfaceC2772a4), true), 254);
                        h6.m.i(c1928c, PlayerSideMenuRoute.Explore.getValue(), null, new b0.b(-1274728715, new M(interfaceC2772a3, interfaceC2772a4, 3), true), 254);
                        String strH = android.hardware.bydauto.ac.tsimpl.a.h(PlayerSideMenuRoute.Songs.getValue(), "/{title}/{id}?source={source}&source_type={source_type}");
                        C0050y0 c0050y0 = (C0050y0) M5.b.m().f18608m;
                        n3.K k11 = (n3.K) c0050y0.f301n;
                        n3.K k12 = n3.M.f19839e;
                        if (k11 == null) {
                            k11 = k12;
                        }
                        C1940e c1940e = new C1940e("title", new C1941f(k11, c0050y0.f300m));
                        C0050y0 c0050y02 = (C0050y0) M5.b.m().f18608m;
                        n3.K k13 = (n3.K) c0050y02.f301n;
                        if (k13 == null) {
                            k13 = k12;
                        }
                        C1940e c1940e2 = new C1940e("id", new C1941f(k13, c0050y02.f300m));
                        C0050y0 c0050y03 = (C0050y0) M5.b.m().f18608m;
                        c0050y03.f300m = true;
                        n3.K k14 = (n3.K) c0050y03.f301n;
                        if (k14 == null) {
                            k14 = k12;
                        }
                        C1940e c1940e3 = new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k14, c0050y03.f300m));
                        C0050y0 c0050y04 = (C0050y0) M5.b.m().f18608m;
                        c0050y04.f300m = true;
                        n3.K k15 = (n3.K) c0050y04.f301n;
                        if (k15 == null) {
                            k15 = k12;
                        }
                        h6.m.i(c1928c, strH, AbstractC1999n.D(c1940e, c1940e2, c1940e3, new C1940e("source_type", new C1941f(k15, c0050y04.f300m))), new b0.b(1983228180, new M(interfaceC2772a3, interfaceC2772a4, 4), true), 252);
                        String strH2 = android.hardware.bydauto.ac.tsimpl.a.h(PlayerSideMenuRoute.Artists.getValue(), "/{title}/{id}?source={source}&source_type={source_type}");
                        C0050y0 c0050y05 = (C0050y0) M5.b.m().f18608m;
                        n3.K k16 = (n3.K) c0050y05.f301n;
                        if (k16 == null) {
                            k16 = k12;
                        }
                        C1940e c1940e4 = new C1940e("title", new C1941f(k16, c0050y05.f300m));
                        C0050y0 c0050y06 = (C0050y0) M5.b.m().f18608m;
                        n3.K k17 = (n3.K) c0050y06.f301n;
                        if (k17 == null) {
                            k17 = k12;
                        }
                        C1940e c1940e5 = new C1940e("id", new C1941f(k17, c0050y06.f300m));
                        C0050y0 c0050y07 = (C0050y0) M5.b.m().f18608m;
                        c0050y07.f300m = true;
                        n3.K k18 = (n3.K) c0050y07.f301n;
                        if (k18 == null) {
                            k18 = k12;
                        }
                        C1940e c1940e6 = new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k18, c0050y07.f300m));
                        C0050y0 c0050y08 = (C0050y0) M5.b.m().f18608m;
                        c0050y08.f300m = true;
                        n3.K k19 = (n3.K) c0050y08.f301n;
                        if (k19 == null) {
                            k19 = k12;
                            k10 = k19;
                        } else {
                            k10 = k12;
                        }
                        h6.m.i(c1928c, strH2, AbstractC1999n.D(c1940e4, c1940e5, c1940e6, new C1940e("source_type", new C1941f(k19, c0050y08.f300m))), new b0.b(946217779, new M(interfaceC2772a3, interfaceC2772a4, 0), true), 252);
                        String strH3 = android.hardware.bydauto.ac.tsimpl.a.h(PlayerSideMenuRoute.SongDetails.getValue(), "/{title}/{id}?source={source}&source_type={source_type}");
                        C0050y0 c0050y09 = (C0050y0) M5.b.m().f18608m;
                        n3.K k20 = (n3.K) c0050y09.f301n;
                        if (k20 == null) {
                            k20 = k10;
                        }
                        C1940e c1940e7 = new C1940e("title", new C1941f(k20, c0050y09.f300m));
                        C0050y0 c0050y010 = (C0050y0) M5.b.m().f18608m;
                        n3.K k21 = (n3.K) c0050y010.f301n;
                        if (k21 == null) {
                            k21 = k10;
                        }
                        C1940e c1940e8 = new C1940e("id", new C1941f(k21, c0050y010.f300m));
                        C0050y0 c0050y011 = (C0050y0) M5.b.m().f18608m;
                        c0050y011.f300m = true;
                        n3.K k22 = (n3.K) c0050y011.f301n;
                        if (k22 == null) {
                            k22 = k10;
                        }
                        C1940e c1940e9 = new C1940e(BYDAutoMultimediaDevice.PARAM_SOURCE, new C1941f(k22, c0050y011.f300m));
                        C0050y0 c0050y012 = (C0050y0) M5.b.m().f18608m;
                        c0050y012.f300m = true;
                        n3.K k23 = (n3.K) c0050y012.f301n;
                        h6.m.i(c1928c, strH3, AbstractC1999n.D(c1940e7, c1940e8, c1940e9, new C1940e("source_type", new C1941f(k23 == null ? k10 : k23, c0050y012.f300m))), new b0.b(-90792622, new M(interfaceC2772a3, interfaceC2772a4, 1), true), 252);
                        h6.m.i(c1928c, PlayerSideMenuRoute.Scoring.getValue(), null, new b0.b(-1127803023, new M(interfaceC2772a3, interfaceC2772a4, 2), true), 254);
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(objG);
            }
            c0761q.p(false);
            h6.q.b(c1930e, value, null, null, null, null, null, null, null, null, (InterfaceC2782k) objG, c0761q, ((i10 >> 6) & 14) | 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(playerStatus, interfaceC2772a, c1930e, interfaceC2772a2, i7, 8);
        }
    }

    public static final void l(g0.o oVar, C0607p1 c0607p1, b0.b bVar, C0761q c0761q, int i7, int i10) {
        g0.o oVar2;
        int i11;
        g0.o oVar3;
        q qVar;
        c0761q.S(115230762);
        int i12 = i10 & 1;
        if (i12 != 0) {
            i11 = i7 | 6;
            oVar2 = oVar;
        } else if ((i7 & 6) == 0) {
            oVar2 = oVar;
            i11 = (c0761q.f(oVar2) ? 4 : 2) | i7;
        } else {
            oVar2 = oVar;
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(c0607p1) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar2;
        } else {
            oVar3 = i12 != 0 ? l.f14684a : oVar2;
            C0591l1 c0591l1 = c0607p1.f7012b;
            C0591l1 c0591l12 = c0607p1.c;
            C0223q c0223q = c0607p1.f7021n;
            C0595m1 c0595m1 = c0607p1.o;
            C0599n1 c0599n1 = c0607p1.f7019k;
            c0761q.Q(-1239870674);
            Object obj = C0753m.f8270a;
            InterfaceC2772a interfaceC2772a = c0607p1.f7017i;
            if (interfaceC2772a != null) {
                c0761q.Q(-1090585511);
                boolean zF = c0761q.f(interfaceC2772a);
                Object objG = c0761q.G();
                if (zF || objG == obj) {
                    objG = new C0549b(interfaceC2772a, 5);
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                h6.n.a(false, (InterfaceC2772a) objG, c0761q, 0, 1);
            }
            c0761q.p(false);
            q qVar2 = q.f19267a;
            c0761q.Q(-1239868960);
            boolean zF2 = c0761q.f(c0591l1);
            Object objG2 = c0761q.G();
            if (zF2 || objG2 == obj) {
                objG2 = new C0559d1(c0591l1, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar2, (InterfaceC2785n) objG2);
            b0.b bVarB = b0.g.b(-846928956, new C0575h1(oVar3, c0607p1, c0607p1.f7011a, c0591l12, bVar, c0607p1.f7015g, c0607p1.f7016h, interfaceC2772a, c0607p1.f7018j, c0599n1, c0595m1, c0607p1.f7020m, c0223q, c0607p1.l, c0607p1.d), c0761q);
            c0761q.Q(-1239703659);
            C1701i c1701i = c0607p1.f7022p;
            if (c1701i == null) {
                qVar = null;
            } else {
                C0735d.a(AbstractC1692D.f18240e.a(c1701i), b0.g.b(-485405803, new C0563e1(bVarB, 1), c0761q), c0761q, 56);
                qVar = qVar2;
            }
            c0761q.p(false);
            if (qVar == null) {
                bVarB.invoke(c0761q, 6);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0561e(oVar3, c0607p1, bVar, i7, i10, 1);
        }
    }

    public static final void m(g0.o oVar, final ArrayList arrayList, final int i7, final float f10, final InterfaceC2782k interfaceC2782k, C0761q c0761q, final int i10) {
        final g0.o oVar2;
        m.f(interfaceC2782k, "onSelection");
        c0761q.S(-788487832);
        int i11 = i10 | 6;
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(arrayList) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.c(f10) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            l lVar = l.f14684a;
            c0761q.Q(222238329);
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = C0735d.M(i7);
                c0761q.a0(objG);
            }
            C0732b0 c0732b0 = (C0732b0) objG;
            c0761q.p(false);
            K0.a(c0732b0.i(), androidx.compose.foundation.layout.c.s(lVar, null, 3), C1891t.f19451j, 0L, f10, Q5.K.f6418a, Q5.K.f6419b, b0.g.b(1388615304, new Q2(arrayList, interfaceC2782k, c0732b0), c0761q), c0761q, ((i11 << 3) & 57344) | 14352768);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.K2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    ArrayList arrayList2 = arrayList;
                    float f11 = f10;
                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                    AbstractC0904a.m(oVar2, arrayList2, i7, f11, interfaceC2782k2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void n(InterfaceC0480k interfaceC0480k, boolean z6, Y0.h hVar, boolean z10, long j3, g0.o oVar, C0761q c0761q, int i7) {
        int i10;
        int i11;
        long j10;
        boolean z11;
        long j11;
        c0761q.S(-843755800);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(interfaceC0480k) : c0761q.h(interfaceC0480k) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(hVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(oVar) ? 131072 : 65536;
        }
        if ((74899 & i10) == 74898 && c0761q.x()) {
            c0761q.K();
            j11 = j3;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-57345);
                j10 = 9205357640488583168L;
            } else {
                c0761q.K();
                i11 = i10 & (-57345);
                j10 = j3;
            }
            c0761q.q();
            Y0.h hVar2 = Y0.h.f10616m;
            Y0.h hVar3 = Y0.h.l;
            if (z6) {
                float f10 = x.f5002a;
                z11 = (hVar == hVar3 && !z10) || (hVar == hVar2 && z10);
            } else {
                float f11 = x.f5002a;
                z11 = !((hVar == hVar3 && !z10) || (hVar == hVar2 && z10));
            }
            g0.d dVar = z11 ? g0.a.f14660b : g0.a.f14659a;
            int i12 = i11 & 14;
            boolean zG = ((i11 & 112) == 32) | (i12 == 4 || ((i11 & 8) != 0 && c0761q.h(interfaceC0480k))) | c0761q.g(z11);
            Object objG = c0761q.G();
            if (zG || objG == C0753m.f8270a) {
                objG = new C0474e(interfaceC0480k, z6, z11);
                c0761q.a0(objG);
            }
            e(interfaceC0480k, dVar, b0.g.b(280174801, new C0472c((W0) c0761q.k(AbstractC0227s0.f2268q), j10, z11, L0.k.a(oVar, false, (InterfaceC2782k) objG), interfaceC0480k), c0761q), c0761q, i12 | 384);
            j11 = j10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0473d(interfaceC0480k, z6, hVar, z10, j11, oVar, i7);
        }
    }

    public static final void o(int i7, C0761q c0761q, g0.o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        int i10;
        c0761q.S(2111672474);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0120b.b(c0761q, g0.a.b(androidx.compose.foundation.layout.c.m(oVar, x.f5002a, x.f5003b), new C0477h(interfaceC2772a, z6)));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0475f(i7, oVar, interfaceC2772a, z6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0063 A[LOOP:0: B:4:0x000d->B:35:0x0063, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0066 A[EDGE_INSN: B:40:0x0066->B:36:0x0066 BREAK  A[LOOP:0: B:4:0x000d->B:35:0x0063], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final L0.o p(E0.F r8, boolean r9) {
        /*
            E0.Y r0 = r8.f1469H
            java.lang.Object r0 = r0.f1603f
            g0.n r0 = (g0.n) r0
            int r1 = r0.o
            r1 = r1 & 8
            r2 = 0
            if (r1 == 0) goto L66
        Ld:
            if (r0 == 0) goto L66
            int r1 = r0.f14686n
            r1 = r1 & 8
            if (r1 == 0) goto L5d
            r1 = r0
            r3 = r2
        L17:
            if (r1 == 0) goto L5d
            boolean r4 = r1 instanceof E0.p0
            if (r4 == 0) goto L1f
            r2 = r1
            goto L66
        L1f:
            int r4 = r1.f14686n
            r4 = r4 & 8
            if (r4 == 0) goto L58
            boolean r4 = r1 instanceof E0.AbstractC0164n
            if (r4 == 0) goto L58
            r4 = r1
            E0.n r4 = (E0.AbstractC0164n) r4
            g0.n r4 = r4.f1690z
            r5 = 0
        L2f:
            r6 = 1
            if (r4 == 0) goto L55
            int r7 = r4.f14686n
            r7 = r7 & 8
            if (r7 == 0) goto L52
            int r5 = r5 + 1
            if (r5 != r6) goto L3e
            r1 = r4
            goto L52
        L3e:
            if (r3 != 0) goto L49
            V.d r3 = new V.d
            r6 = 16
            g0.n[] r6 = new g0.n[r6]
            r3.<init>(r6)
        L49:
            if (r1 == 0) goto L4f
            r3.b(r1)
            r1 = r2
        L4f:
            r3.b(r4)
        L52:
            g0.n r4 = r4.f14688q
            goto L2f
        L55:
            if (r5 != r6) goto L58
            goto L17
        L58:
            g0.n r1 = E0.AbstractC0156f.f(r3)
            goto L17
        L5d:
            int r1 = r0.o
            r1 = r1 & 8
            if (r1 == 0) goto L66
            g0.n r0 = r0.f14688q
            goto Ld
        L66:
            A7.m.c(r2)
            E0.p0 r2 = (E0.p0) r2
            g0.n r2 = (g0.n) r2
            g0.n r0 = r2.l
            L0.j r1 = r8.q()
            A7.m.c(r1)
            L0.o r2 = new L0.o
            r2.<init>(r0, r9, r8, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: a.AbstractC0904a.p(E0.F, boolean):L0.o");
    }

    public static final long q(int i7, int i10) {
        if (i7 < 0) {
            throw new IllegalArgumentException(("start cannot be negative. [start: " + i7 + ", end: " + i10 + ']').toString());
        }
        if (i10 >= 0) {
            long j3 = (((long) i10) & 4294967295L) | (((long) i7) << 32);
            int i11 = K.c;
            return j3;
        }
        throw new IllegalArgumentException(("end cannot be negative. [start: " + i7 + ", end: " + i10 + ']').toString());
    }

    public static final void r(final g0.o oVar, final int i7, final String str, final boolean z6, final boolean z10, final InterfaceC2772a interfaceC2772a, C0761q c0761q, final int i10) {
        int i11;
        g0.o oVarQ;
        C0.K k10;
        c0761q.S(554412126);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(str) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.g(z10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            Object obj = C0753m.f8270a;
            l lVar = l.f14684a;
            if (z6) {
                c0761q.Q(-449121695);
                c0761q.Q(1786629491);
                boolean z11 = (i11 & 458752) == 131072;
                Object objG = c0761q.G();
                if (z11 || objG == obj) {
                    objG = new C0549b(interfaceC2772a, 24);
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                oVarQ = com.bumptech.glide.c.P(lVar, false, null, null, (InterfaceC2772a) objG, 15);
                c0761q.p(false);
            } else {
                c0761q.Q(-449022216);
                c0761q.Q(1786632979);
                boolean z12 = (i11 & 458752) == 131072;
                Object objG2 = c0761q.G();
                if (z12 || objG2 == obj) {
                    objG2 = new C0549b(interfaceC2772a, 25);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                oVarQ = com.bumptech.glide.c.Q(7, lVar, (InterfaceC2772a) objG2, false);
                c0761q.p(false);
            }
            g0.o oVarM = androidx.compose.foundation.layout.a.m(oVar.k(oVarQ), 0.0f, m6.c.f19252a.H(6, c0761q), 1);
            g0.g gVar = g0.b.l;
            C0.K kE = AbstractC0133o.e(gVar, false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarM);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a2 = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a2);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.o oVarA = androidx.compose.foundation.layout.b.f11474a.a(lVar, gVar);
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            b bVar = (b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = new p(bVar);
                c0761q.a0(objG3);
            }
            p pVar = (p) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = new C1283j();
                c0761q.a0(objG4);
            }
            C1283j c1283j = (C1283j) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG5);
            }
            X x8 = (X) objG5;
            Object objG6 = c0761q.G();
            if (objG6 == obj) {
                objG6 = new C1285l(c1283j);
                c0761q.a0(objG6);
            }
            C1285l c1285l = (C1285l) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = C0735d.O(q.f19267a, S.f8222n);
                c0761q.a0(objG7);
            }
            X x9 = (X) objG7;
            boolean zH = c0761q.h(pVar) | c0761q.d(257);
            Object objG8 = c0761q.G();
            if (zH || objG8 == obj) {
                objG8 = new M1(x9, pVar, c1285l, x8, 8);
                c0761q.a0(objG8);
            }
            C0.K k11 = (C0.K) objG8;
            Object objG9 = c0761q.G();
            if (objG9 == obj) {
                k10 = k11;
                objG9 = new N1(x8, c1285l, 8);
                c0761q.a0(objG9);
            } else {
                k10 = k11;
            }
            InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) objG9;
            boolean zH2 = c0761q.h(pVar);
            Object objG10 = c0761q.G();
            if (zH2 || objG10 == obj) {
                objG10 = new O1(pVar, 8);
                c0761q.a0(objG10);
            }
            c0.a(L0.k.a(oVarA, false, (InterfaceC2782k) objG10), b0.g.b(1200550679, new W5.q(x9, c1283j, interfaceC2772a3, z10, i7, str, z6), c0761q), k10, c0761q, 48);
            c0761q.p(false);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: W5.n
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj2, Object obj3) {
                    ((Integer) obj3).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    boolean z13 = z10;
                    InterfaceC2772a interfaceC2772a4 = interfaceC2772a;
                    AbstractC0904a.r(oVar, i7, str, z6, z13, interfaceC2772a4, (C0761q) obj2, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final D.L s(boolean z6, boolean z10, float f10, C0761q c0761q) {
        float fF0;
        float f11;
        c0761q.Q(-1404116143);
        C0773y c0773y = AbstractC1692D.f18238a;
        boolean z11 = c0761q.k(c0773y) == LayoutMode.WIDE_ULTRA;
        C0773y c0773y2 = AbstractC1692D.c;
        boolean zBooleanValue = ((Boolean) c0761q.k(c0773y2)).booleanValue();
        C0773y c0773y3 = AbstractC1692D.f18239b;
        boolean zBooleanValue2 = ((Boolean) c0761q.k(c0773y3)).booleanValue();
        m6.b bVar = m6.c.f19252a;
        float fE0 = bVar.E0(c0761q) * (z11 ? 5.0f : 1.0f);
        c0761q.Q(471855959);
        float fC0 = z11 ? fE0 - bVar.C0(6, c0761q) : fE0;
        c0761q.p(false);
        c0761q.Q(471860566);
        if (!z11 && !zBooleanValue && !zBooleanValue2) {
            fE0 = bVar.C0(6, c0761q) + fC0;
        }
        c0761q.p(false);
        boolean z12 = z6 && !z10;
        float f12 = z12 ? f10 : 0;
        if (z12) {
            fF0 = 0;
        } else {
            c0761q.Q(471873128);
            boolean zB = AbstractC1692D.b((LayoutMode) c0761q.k(c0773y));
            c0761q.p(false);
            if (zB) {
                c0761q.Q(471874260);
                fF0 = bVar.H(6, c0761q);
                c0761q.p(false);
            } else {
                c0761q.Q(471876273);
                fF0 = bVar.F0(c0761q);
                c0761q.p(false);
            }
        }
        float f13 = f12 + fF0;
        float fF02 = bVar.F0(c0761q);
        if (((Boolean) c0761q.k(c0773y3)).booleanValue()) {
            f11 = 0;
        } else {
            C8.d dVar = t.f18296a;
            c0761q.Q(968522668);
            f11 = ((e) t.f18297b.getValue()).l;
            c0761q.p(false);
        }
        float f14 = fF02 + f11;
        c0761q.Q(471882423);
        boolean z13 = ((Boolean) c0761q.k(c0773y2)).booleanValue() && !((Boolean) c0761q.k(c0773y3)).booleanValue();
        c0761q.p(false);
        D.L l = new D.L(fC0, f13, fE0, f14 + (z13 ? bVar.Z(c0761q) : 0));
        c0761q.p(false);
        return l;
    }

    public static void t(String str) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if ('!' > cCharAt || cCharAt >= 127) {
                throw new IllegalArgumentException(d9.b.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i7), str).toString());
            }
        }
    }

    public static void u(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    public static void v(String str, String str2) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                StringBuilder sb = new StringBuilder();
                sb.append(d9.b.i("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i7), str2));
                sb.append(d9.b.q(str2) ? "" : ": ".concat(str));
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
    }

    public static final long w(long j3, int i7) {
        int i10 = K.c;
        int i11 = (int) (j3 >> 32);
        int iV = AbstractC2673a.v(i11, 0, i7);
        int i12 = (int) (4294967295L & j3);
        int iV2 = AbstractC2673a.v(i12, 0, i7);
        return (iV == i11 && iV2 == i12) ? j3 : q(iV, iV2);
    }

    public static final boolean x(m0.d dVar, float f10, float f11) {
        return f10 <= dVar.c && dVar.f18882a <= f10 && f11 <= dVar.d && dVar.f18883b <= f11;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final n0.C1877f y(k0.C1591c r23, float r24) {
        /*
            r0 = r23
            r3 = r24
            double r1 = (double) r3
            double r1 = java.lang.Math.ceil(r1)
            float r1 = (float) r1
            int r1 = (int) r1
            int r1 = r1 * 2
            n0.f r2 = P8.n.f5477a
            n0.b r4 = P8.n.f5478b
            p0.b r5 = P8.n.c
            if (r2 == 0) goto L29
            if (r4 == 0) goto L29
            android.graphics.Bitmap r6 = r2.f19431a
            int r7 = r6.getWidth()
            if (r1 > r7) goto L29
            int r6 = r6.getHeight()
            if (r1 <= r6) goto L26
            goto L29
        L26:
            r8 = r2
            r9 = r4
            goto L37
        L29:
            r2 = 1
            n0.f r2 = n0.AbstractC1861K.f(r1, r1, r2)
            P8.n.f5477a = r2
            n0.b r4 = n0.AbstractC1861K.a(r2)
            P8.n.f5478b = r4
            goto L26
        L37:
            if (r5 != 0) goto L41
            p0.b r1 = new p0.b
            r1.<init>()
            P8.n.c = r1
            goto L42
        L41:
            r1 = r5
        L42:
            k0.a r2 = r0.l
            a1.k r2 = r2.getLayoutDirection()
            android.graphics.Bitmap r4 = r8.f19431a
            int r5 = r4.getWidth()
            float r5 = (float) r5
            int r4 = r4.getHeight()
            float r4 = (float) r4
            long r4 = h6.j.f(r5, r4)
            p0.a r7 = r1.l
            a1.b r6 = r7.f20636a
            a1.k r15 = r7.f20637b
            n0.r r13 = r7.c
            long r11 = r7.d
            r7.f20636a = r0
            r7.f20637b = r2
            r7.c = r9
            r7.d = r4
            r9.m()
            long r4 = n0.C1891t.f19445b
            long r16 = r1.e()
            r0 = 0
            r2 = 0
            r18 = 58
            r10 = r1
            r19 = r11
            r11 = r4
            r4 = r13
            r13 = r16
            r5 = r15
            r15 = r0
            r16 = r2
            r17 = r18
            p0.d.M(r10, r11, r13, r15, r16, r17)
            r21 = 4278190080(0xff000000, double:2.113706745E-314)
            long r11 = n0.AbstractC1861K.d(r21)
            long r13 = h6.j.f(r3, r3)
            r15 = 0
            r16 = 0
            r17 = 120(0x78, float:1.68E-43)
            p0.d.M(r10, r11, r13, r15, r16, r17)
            long r10 = n0.AbstractC1861K.d(r21)
            long r12 = h6.e.a(r3, r3)
            r14 = 120(0x78, float:1.68E-43)
            r15 = 0
            r0 = r1
            r1 = r10
            r3 = r24
            r11 = r4
            r10 = r5
            r4 = r12
            r12 = r6
            r6 = r15
            r13 = r7
            r7 = r14
            p0.d.G(r0, r1, r3, r4, r6, r7)
            r9.k()
            r13.f20636a = r12
            r13.f20637b = r10
            r13.c = r11
            r0 = r19
            r13.d = r0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: a.AbstractC0904a.y(k0.c, float):n0.f");
    }

    public static final F8.L z(InterfaceC0652e interfaceC0652e, InterfaceC0652e interfaceC0652e2) {
        m.f(interfaceC0652e, "from");
        m.f(interfaceC0652e2, "to");
        interfaceC0652e.s().size();
        interfaceC0652e2.s().size();
        List listS = interfaceC0652e.s();
        m.e(listS, "from.declaredTypeParameters");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listS, 10));
        Iterator it = listS.iterator();
        while (it.hasNext()) {
            arrayList.add(((Q) it.next()).C());
        }
        List listS2 = interfaceC0652e2.s();
        m.e(listS2, "to.declaredTypeParameters");
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listS2, 10));
        Iterator it2 = listS2.iterator();
        while (it2.hasNext()) {
            A aJ = ((Q) it2.next()).j();
            m.e(aJ, "it.defaultType");
            arrayList2.add(AbstractC1274a.w(aJ));
        }
        return new F8.L(z.x(AbstractC1998m.F0(arrayList, arrayList2)), false);
    }

    public abstract InputFilter[] E(InputFilter[] inputFilterArr);

    public abstract void Q(boolean z6);

    public abstract void R(boolean z6);
}
