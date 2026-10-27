package h6;

import A.EnumC0017h0;
import B8.t;
import D.AbstractC0120b;
import E0.AbstractC0156f;
import E0.C0170u;
import P.C;
import Q5.Y2;
import T.C0730a0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.S;
import T.X;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c9.D;
import h2.AbstractC1343A;
import h2.AbstractC1347d;
import h3.y;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import io.sentry.U;
import io.sentry.android.core.B;
import j0.C1536d;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import k1.AbstractC1598c;
import k1.C1596a;
import l1.C1731b;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.AbstractC1874c;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1878g;
import n0.C1880i;
import n0.C1891t;
import n0.InterfaceC1859I;
import n0.InterfaceC1889r;
import n3.C1928C;
import n3.C1940e;
import n3.P;
import n6.C1972e;
import n6.C1973f;
import n6.C1974g;
import n6.C1975h;
import n6.C1976i;
import n6.C1981n;
import n6.J;
import n7.u;
import o3.C2036i;
import o3.C2037j;
import o6.C2048a;
import q0.C2164c;
import q0.C2165d;
import s3.AbstractC2367F;
import s3.C2377P;
import u.AbstractC2457E;
import u.C2453A;
import x.B0;
import x.InterfaceC2615D;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15584a = 0;

    public static Double A(Bundle bundle, K k10, String str) {
        Double dValueOf = Double.valueOf(Float.valueOf(bundle.getFloat(str, bundle.getInt(str, -1))).doubleValue());
        k10.k(EnumC1495p1.DEBUG, str + " read: " + dValueOf, new Object[0]);
        return dValueOf;
    }

    public static List B(Bundle bundle, K k10, String str) {
        String string = bundle.getString(str);
        k10.k(EnumC1495p1.DEBUG, str + " read: " + string, new Object[0]);
        if (string != null) {
            return Arrays.asList(string.split(",", -1));
        }
        return null;
    }

    public static long C(Bundle bundle, K k10, String str, long j3) {
        long j10 = bundle.getInt(str, (int) j3);
        k10.k(EnumC1495p1.DEBUG, str + " read: " + j10, new Object[0]);
        return j10;
    }

    public static String D(Bundle bundle, K k10, String str, String str2) {
        String string = bundle.getString(str, str2);
        k10.k(EnumC1495p1.DEBUG, str + " read: " + string, new Object[0]);
        return string;
    }

    public static String E(Bundle bundle, K k10, String str, String str2) {
        String string = bundle.getString(str, str2);
        k10.k(EnumC1495p1.DEBUG, str + " read: " + string, new Object[0]);
        return string;
    }

    public static String F(int i7) {
        Object[] objArr = {Integer.valueOf(Color.red(i7)), Integer.valueOf(Color.green(i7)), Integer.valueOf(Color.blue(i7)), Double.valueOf(((double) Color.alpha(i7)) / 255.0d)};
        int i10 = AbstractC1343A.f15195a;
        return String.format(Locale.US, "rgba(%d,%d,%d,%.3f)", objArr);
    }

    public static final void a(Boolean bool, g0.o oVar, InterfaceC2615D interfaceC2615D, String str, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar2;
        String str2;
        c0761q.S(-310686752);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(bool) : c0761q.h(bool) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        int i11 = i10 | 48;
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2615D) ? 256 : 128;
        }
        int i12 = i11 | 3072;
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.h(bVar) ? 16384 : 8192;
        }
        if ((i12 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
            str2 = str;
        } else {
            g0.l lVar = g0.l.f14684a;
            b(B0.c(bool, "Crossfade", c0761q, (i12 & 14) | ((i12 >> 6) & 112)), lVar, interfaceC2615D, null, bVar, c0761q, i12 & 58352);
            oVar2 = lVar;
            str2 = "Crossfade";
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0.a(bool, oVar2, interfaceC2615D, str2, bVar, i7);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0187, code lost:
    
        if (((r1 & ((~r1) << 6)) & (-9187201950435737472L)) == 0) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0189, code lost:
    
        r9 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(x.x0 r21, g0.o r22, x.InterfaceC2615D r23, z7.InterfaceC2782k r24, b0.b r25, T.C0761q r26, int r27) {
        /*
            Method dump skipped, instruction units count: 712
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.m.b(x.x0, g0.o, x.D, z7.k, b0.b, T.q, int):void");
    }

    public static final void c(g0.o oVar, J j3, C1972e c1972e, C2048a c2048a, boolean z6, boolean z10, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(oVar, "modifier");
        A7.m.f(j3, "scoringValues");
        A7.m.f(c1972e, "gameLoopManager");
        c0761q.S(1847208445);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(j3) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(c1972e) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(c2048a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.g(false) ? 1048576 : 524288;
        }
        if ((599187 & i10) == 599186 && c0761q.x()) {
            c0761q.K();
        } else {
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            c0761q.Q(781369111);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.L(0.0f);
                c0761q.a0(objG);
            }
            C0730a0 c0730a0 = (C0730a0) objG;
            Object objE = M5.b.e(781371095, c0761q, false);
            if (objE == s6) {
                objE = C0735d.L(0.0f);
                c0761q.a0(objE);
            }
            C0730a0 c0730a02 = (C0730a0) objE;
            Object objE2 = M5.b.e(781373083, c0761q, false);
            S s10 = S.f8224q;
            if (objE2 == s6) {
                objE2 = C0735d.O(new LinkedHashMap(), s10);
                c0761q.a0(objE2);
            }
            X x8 = (X) objE2;
            Object objE3 = M5.b.e(781376333, c0761q, false);
            if (objE3 == s6) {
                objE3 = C0735d.O(new LinkedHashMap(), s10);
                c0761q.a0(objE3);
            }
            X x9 = (X) objE3;
            c0761q.p(false);
            m7.q qVar = m7.q.f19267a;
            c0761q.Q(781379247);
            boolean zH = ((i10 & 112) == 32) | c0761q.h(c1972e);
            Object objG2 = c0761q.G();
            if (zH || objG2 == s6) {
                objG2 = new C1973f(c1972e, j3, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar, (InterfaceC2785n) objG2);
            Object value = c1972e.f20075v.getValue();
            c0761q.Q(781389204);
            boolean zH2 = c0761q.h(c1972e);
            Object objG3 = c0761q.G();
            if (zH2 || objG3 == s6) {
                objG3 = new C1974g(c1972e, c0730a0, c0730a02, null);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C0735d.f(c0761q, value, (InterfaceC2785n) objG3);
            Object value2 = c1972e.f20076w.getValue();
            c0761q.Q(781394452);
            boolean zH3 = c0761q.h(c1972e);
            Object objG4 = c0761q.G();
            if (zH3 || objG4 == s6) {
                objG4 = new C1975h(c1972e, c0730a0, c0730a02, null);
                c0761q.a0(objG4);
            }
            c0761q.p(false);
            C0735d.f(c0761q, value2, (InterfaceC2785n) objG4);
            Integer numValueOf = Integer.valueOf(c1972e.f20078y.i());
            c0761q.Q(781402293);
            Object objG5 = c0761q.G();
            if (objG5 == s6) {
                objG5 = new C1976i(2, null);
                c0761q.a0(objG5);
            }
            c0761q.p(false);
            C0735d.f(c0761q, numValueOf, (InterfaceC2785n) objG5);
            AbstractC0120b.a(oVar, null, false, b0.g.b(1634838995, new C1981n(c2048a, c1972e, z10, z6, j3, context, c0730a0, c0730a02, x8, x9), c0761q), c0761q, (i10 & 14) | 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Y2(oVar, j3, c1972e, c2048a, z6, z10, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:18:0x0041). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d(y0.z r8, s7.AbstractC2408a r9) {
        /*
            boolean r0 = r9 instanceof z.c
            if (r0 == 0) goto L13
            r0 = r9
            z.c r0 = (z.c) r0
            int r1 = r0.f24230n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f24230n = r1
            goto L18
        L13:
            z.c r0 = new z.c
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f24229m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f24230n
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            y0.z r8 = r0.l
            h8.AbstractC1386c.u(r9)
            goto L41
        L29:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L31:
            h8.AbstractC1386c.u(r9)
        L34:
            r0.l = r8
            r0.f24230n = r3
            y0.i r9 = y0.EnumC2731i.f24067m
            java.lang.Object r9 = r8.a(r9, r0)
            if (r9 != r1) goto L41
            goto L6e
        L41:
            y0.h r9 = (y0.C2730h) r9
            int r2 = r9.c
            r2 = r2 & 66
            if (r2 == 0) goto L34
            java.lang.Object r9 = r9.f24065a
            int r2 = r9.size()
            r4 = 0
            r5 = r4
        L51:
            if (r5 >= r2) goto L6a
            java.lang.Object r6 = r9.get(r5)
            y0.q r6 = (y0.q) r6
            boolean r7 = r6.b()
            if (r7 != 0) goto L34
            boolean r7 = r6.f24082h
            if (r7 != 0) goto L34
            boolean r6 = r6.d
            if (r6 == 0) goto L34
            int r5 = r5 + 1
            goto L51
        L6a:
            java.lang.Object r1 = r9.get(r4)
        L6e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.m.d(y0.z, s7.a):java.lang.Object");
    }

    public static final boolean e(C1536d c1536d, long j3) {
        if (!c1536d.l.f14695x) {
            return false;
        }
        C0170u c0170u = (C0170u) AbstractC0156f.t(c1536d).f1469H.c;
        if (!c0170u.f1703V.f14695x) {
            return false;
        }
        long j10 = c0170u.f872n;
        long jN = c0170u.N(0L);
        float fD = m0.c.d(jN);
        float fE = m0.c.e(jN);
        float f10 = ((int) (j10 >> 32)) + fD;
        float f11 = ((int) (j10 & 4294967295L)) + fE;
        float fD2 = m0.c.d(j3);
        if (fD > fD2 || fD2 > f10) {
            return false;
        }
        float fE2 = m0.c.e(j3);
        return fE <= fE2 && fE2 <= f11;
    }

    public static final LinearGradient f(float f10, float f11, long j3) {
        long j10 = C1891t.f19451j;
        return new LinearGradient(f10, 0.0f, f10, f11, new int[]{AbstractC1861K.E(j10), AbstractC1861K.E(j3), AbstractC1861K.E(j10)}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
    }

    public static void g(g1.q qVar, y yVar, String str, k1.g gVar, C1731b c1731b) throws k1.h {
        long j3;
        str.getClass();
        switch (str) {
            case "centerVertically":
                String strC = gVar.C(str);
                C1731b c1731bB = strC.equals("parent") ? qVar.b(0) : qVar.b(strC);
                c1731b.q(c1731bB);
                c1731b.f(c1731bB);
                return;
            case "center":
                String strC2 = gVar.C(str);
                C1731b c1731bB2 = strC2.equals("parent") ? qVar.b(0) : qVar.b(strC2);
                c1731b.p(c1731bB2);
                c1731b.j(c1731bB2);
                c1731b.q(c1731bB2);
                c1731b.f(c1731bB2);
                return;
            case "custom":
                AbstractC1598c abstractC1598cA = gVar.A(str);
                k1.g gVar2 = abstractC1598cA instanceof k1.g ? (k1.g) abstractC1598cA : null;
                if (gVar2 == null) {
                    return;
                }
                for (String str2 : gVar2.F()) {
                    AbstractC1598c abstractC1598cV = gVar2.v(str2);
                    if (abstractC1598cV instanceof k1.e) {
                        float fL = abstractC1598cV.l();
                        if (c1731b.f18399i0 == null) {
                            c1731b.f18399i0 = new HashMap();
                        }
                        c1731b.f18399i0.put(str2, Float.valueOf(fL));
                    } else if (abstractC1598cV instanceof k1.i) {
                        String strD = abstractC1598cV.d();
                        if (strD.startsWith("#")) {
                            String strSubstring = strD.substring(1);
                            if (strSubstring.length() == 6) {
                                strSubstring = "FF".concat(strSubstring);
                            }
                            j3 = Long.parseLong(strSubstring, 16);
                        } else {
                            j3 = -1;
                        }
                        if (j3 != -1) {
                            c1731b.f18397h0.put(str2, Integer.valueOf((int) j3));
                        }
                    }
                }
                return;
            case "rotationX":
                c1731b.f18415z = yVar.l(gVar.v(str));
                return;
            case "rotationY":
                c1731b.f18359A = yVar.l(gVar.v(str));
                return;
            case "rotationZ":
                c1731b.B = yVar.l(gVar.v(str));
                return;
            case "translationX":
                c1731b.f18360C = qVar.f14745a.m(yVar.l(gVar.v(str)));
                return;
            case "translationY":
                c1731b.f18361D = qVar.f14745a.m(yVar.l(gVar.v(str)));
                return;
            case "translationZ":
                c1731b.f18362E = qVar.f14745a.m(yVar.l(gVar.v(str)));
                return;
            case "height":
                c1731b.f18391e0 = u(gVar, str, qVar, qVar.f14745a);
                return;
            case "motion":
                AbstractC1598c abstractC1598cV2 = gVar.v(str);
                if (abstractC1598cV2 instanceof k1.g) {
                    k1.g gVar3 = (k1.g) abstractC1598cV2;
                    j1.b bVar = new j1.b();
                    bVar.f17000a = new int[10];
                    bVar.f17001b = new int[10];
                    bVar.c = 0;
                    bVar.d = new int[10];
                    bVar.f17002e = new float[10];
                    bVar.f17003f = 0;
                    bVar.f17004g = new int[5];
                    bVar.f17005h = new String[5];
                    bVar.f17006i = 0;
                    for (String str3 : gVar3.F()) {
                        str3.getClass();
                        switch (str3) {
                            case "stagger":
                                bVar.a(gVar3.x(str3), 600);
                                continue;
                                break;
                            case "easing":
                                bVar.c(603, gVar3.C(str3));
                                continue;
                                break;
                            case "quantize":
                                AbstractC1598c abstractC1598cV3 = gVar3.v(str3);
                                if (abstractC1598cV3 instanceof C1596a) {
                                    C1596a c1596a = (C1596a) abstractC1598cV3;
                                    int size = c1596a.f17619p.size();
                                    if (size <= 0) {
                                        break;
                                    } else {
                                        bVar.b(610, c1596a.y(0));
                                        if (size <= 1) {
                                            break;
                                        } else {
                                            bVar.c(611, c1596a.B(1));
                                            if (size > 2) {
                                                bVar.a(c1596a.w(2), 602);
                                            }
                                        }
                                    }
                                } else {
                                    AbstractC1598c abstractC1598cV4 = gVar3.v(str3);
                                    if (abstractC1598cV4 == null) {
                                        StringBuilder sbJ = AbstractC1347d.j("no int found for key <", str3, ">, found [");
                                        sbJ.append(abstractC1598cV4.q());
                                        sbJ.append("] : ");
                                        sbJ.append(abstractC1598cV4);
                                        throw new k1.h(sbJ.toString(), gVar3);
                                    }
                                    bVar.b(610, abstractC1598cV4.m());
                                }
                                break;
                            case "pathArc":
                                String strC3 = gVar3.C(str3);
                                String[] strArr = {"none", "startVertical", "startHorizontal", "flip", "below", "above"};
                                int i7 = 0;
                                while (true) {
                                    if (i7 >= 6) {
                                        i7 = -1;
                                    } else if (!strArr[i7].equals(strC3)) {
                                        i7++;
                                    }
                                }
                                if (i7 == -1) {
                                    System.err.println("0 pathArc = '" + strC3 + "'");
                                    break;
                                } else {
                                    bVar.b(607, i7);
                                    break;
                                }
                                break;
                            case "relativeTo":
                                bVar.c(605, gVar3.C(str3));
                                break;
                        }
                    }
                    c1731b.getClass();
                    return;
                }
                return;
            case "pivotX":
                c1731b.f18413x = yVar.l(gVar.v(str));
                return;
            case "pivotY":
                c1731b.f18414y = yVar.l(gVar.v(str));
                return;
            case "scaleX":
                c1731b.f18364G = yVar.l(gVar.v(str));
                return;
            case "scaleY":
                c1731b.f18365H = yVar.l(gVar.v(str));
                return;
            case "hRtlBias":
                float fL2 = yVar.l(gVar.v(str));
                if (!qVar.f14746b) {
                    fL2 = 1.0f - fL2;
                }
                c1731b.f18396h = fL2;
                return;
            case "vWeight":
                c1731b.f18394g = yVar.l(gVar.v(str));
                return;
            case "alpha":
                c1731b.f18363F = yVar.l(gVar.v(str));
                return;
            case "hBias":
                c1731b.f18396h = yVar.l(gVar.v(str));
                return;
            case "vBias":
                c1731b.f18398i = yVar.l(gVar.v(str));
                return;
            case "width":
                c1731b.f18389d0 = u(gVar, str, qVar, qVar.f14745a);
                return;
            case "hWeight":
                c1731b.f18392f = yVar.l(gVar.v(str));
                return;
            case "centerHorizontally":
                String strC4 = gVar.C(str);
                C1731b c1731bB3 = strC4.equals("parent") ? qVar.b(0) : qVar.b(strC4);
                c1731b.p(c1731bB3);
                c1731b.j(c1731bB3);
                return;
            case "visibility":
                String strC5 = gVar.C(str);
                strC5.getClass();
                switch (strC5) {
                    case "invisible":
                        c1731b.f18366I = 4;
                        c1731b.f18363F = 0.0f;
                        return;
                    case "gone":
                        c1731b.f18366I = 8;
                        return;
                    case "visible":
                        c1731b.f18366I = 0;
                        return;
                    default:
                        return;
                }
            default:
                t(qVar, yVar, str, gVar, c1731b);
                return;
        }
    }

    public static final void h(long j3, EnumC0017h0 enumC0017h0) {
        if (enumC0017h0 == EnumC0017h0.l) {
            if (a1.a.g(j3) == Integer.MAX_VALUE) {
                throw new IllegalStateException("Vertically scrollable component was measured with an infinity maximum height constraints, which is disallowed. One of the common reasons is nesting layouts like LazyColumn and Column(Modifier.verticalScroll()). If you want to add a header before the list of items please add a header as a separate item() before the main items() inside the LazyColumn scope. There are could be other reasons for this to happen: your ComposeView was added into a LinearLayout with some weight, you applied Modifier.wrapContentSize(unbounded = true) or wrote a custom layout. Please try to remove the source of infinite constraints in the hierarchy above the scrolling container.");
            }
        } else if (a1.a.h(j3) == Integer.MAX_VALUE) {
            throw new IllegalStateException("Horizontally scrollable component was measured with an infinity maximum width constraints, which is disallowed. One of the common reasons is nesting layouts like LazyRow and Row(Modifier.horizontalScroll()). If you want to add a header before the list of items please add a header as a separate item() before the main items() inside the LazyRow scope. There are could be other reasons for this to happen: your ComposeView was added into a LinearLayout with some weight, you applied Modifier.wrapContentSize(unbounded = true) or wrote a custom layout. Please try to remove the source of infinite constraints in the hierarchy above the scrolling container.");
        }
    }

    public static void i(C1928C c1928c, String str, List list, b0.b bVar, int i7) {
        int i10 = i7 & 2;
        u uVar = u.l;
        if (i10 != 0) {
            list = uVar;
        }
        P p10 = c1928c.f19794g;
        p10.getClass();
        C2037j c2037j = new C2037j((C2036i) p10.b(j.l(C2036i.class)), str, bVar);
        for (C1940e c1940e : list) {
            ((LinkedHashMap) c2037j.d).put(c1940e.f19856a, c1940e.f19857b);
        }
        c2037j.f20468i = null;
        c2037j.f20469j = null;
        c2037j.f20470k = null;
        c2037j.l = null;
        c2037j.f20471m = null;
        c1928c.f19796i.add(c2037j.c());
    }

    public static int j(C2377P c2377p, T1.f fVar, View view, View view2, AbstractC2367F abstractC2367F, boolean z6) {
        if (abstractC2367F.v() == 0 || c2377p.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return Math.abs(AbstractC2367F.D(view) - AbstractC2367F.D(view2)) + 1;
        }
        return Math.min(fVar.l(), fVar.b(view2) - fVar.e(view));
    }

    public static int k(C2377P c2377p, T1.f fVar, View view, View view2, AbstractC2367F abstractC2367F, boolean z6, boolean z10) {
        if (abstractC2367F.v() == 0 || c2377p.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z10 ? Math.max(0, (c2377p.b() - Math.max(AbstractC2367F.D(view), AbstractC2367F.D(view2))) - 1) : Math.max(0, Math.min(AbstractC2367F.D(view), AbstractC2367F.D(view2)));
        if (z6) {
            return Math.round((iMax * (Math.abs(fVar.b(view2) - fVar.e(view)) / (Math.abs(AbstractC2367F.D(view) - AbstractC2367F.D(view2)) + 1))) + (fVar.k() - fVar.e(view)));
        }
        return iMax;
    }

    public static int l(C2377P c2377p, T1.f fVar, View view, View view2, AbstractC2367F abstractC2367F, boolean z6) {
        if (abstractC2367F.v() == 0 || c2377p.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return c2377p.b();
        }
        return (int) (((fVar.b(view2) - fVar.e(view)) / (Math.abs(AbstractC2367F.D(view) - AbstractC2367F.D(view2)) + 1)) * c2377p.b());
    }

    public static io.sentry.instrumentation.file.g m(FileOutputStream fileOutputStream, File file) {
        E e10 = E.f15834a;
        U uI = io.sentry.util.f.f16921a ? e10.i() : e10.b();
        return new io.sentry.instrumentation.file.g(new t(file, uI != null ? uI.j("file.write") : null, fileOutputStream, e10.q(), 22));
    }

    public static io.sentry.instrumentation.file.g n(FileOutputStream fileOutputStream, File file, boolean z6) {
        E e10 = E.f15834a;
        U uI = io.sentry.util.f.f16921a ? e10.i() : e10.b();
        return new io.sentry.instrumentation.file.g(new t(file, uI != null ? uI.j("file.write") : null, fileOutputStream, e10.q(), 22));
    }

    public static final void o(p0.d dVar, C2164c c2164c) {
        Canvas canvas;
        boolean z6;
        float f10;
        float f11;
        InterfaceC1889r interfaceC1889rM = dVar.F().m();
        C2164c c2164c2 = (C2164c) dVar.F().f15500n;
        if (c2164c.f21133r) {
            return;
        }
        c2164c.a();
        C2165d c2165d = c2164c.f21120a;
        RenderNode renderNode = c2165d.c;
        if (!renderNode.hasDisplayList()) {
            try {
                c2164c.e();
            } catch (Throwable unused) {
            }
        }
        boolean z10 = c2165d.f21148m > 0.0f;
        if (z10) {
            interfaceC1889rM.s();
        }
        Canvas canvasA = AbstractC1874c.a(interfaceC1889rM);
        boolean zIsHardwareAccelerated = canvasA.isHardwareAccelerated();
        if (zIsHardwareAccelerated) {
            canvas = canvasA;
        } else {
            canvasA.save();
            long j3 = c2164c.f21134s;
            float f12 = (int) (j3 >> 32);
            float f13 = (int) (j3 & 4294967295L);
            long j10 = c2164c.f21135t;
            float f14 = f12 + ((int) (j10 >> 32));
            float f15 = f13 + ((int) (j10 & 4294967295L));
            float f16 = c2165d.f21143g;
            int i7 = c2165d.f21144h;
            if (f16 < 1.0f || !AbstractC1861K.p(i7, 3) || j.j(c2165d.f21157w, 1)) {
                C1878g c1878gH = c2164c.o;
                if (c1878gH == null) {
                    c1878gH = AbstractC1861K.h();
                    c2164c.o = c1878gH;
                }
                c1878gH.c(f16);
                c1878gH.d(i7);
                c1878gH.f(null);
                canvas = canvasA;
                f10 = f13;
                f11 = f12;
                canvas.saveLayer(f12, f13, f14, f15, c1878gH.f19432a);
            } else {
                canvasA.save();
                canvas = canvasA;
                f10 = f13;
                f11 = f12;
            }
            canvas.translate(f11, f10);
            Matrix matrix = c2165d.f21141e;
            if (matrix == null) {
                matrix = new Matrix();
                c2165d.f21141e = matrix;
            }
            renderNode.getMatrix(matrix);
            canvas.concat(matrix);
        }
        boolean z11 = !zIsHardwareAccelerated && c2164c.f21137v;
        if (z11) {
            interfaceC1889rM.m();
            AbstractC1858H abstractC1858HC = c2164c.c();
            if (abstractC1858HC instanceof C1856F) {
                InterfaceC1889r.q(interfaceC1889rM, abstractC1858HC.a());
            } else if (abstractC1858HC instanceof C1857G) {
                C1880i c1880iI = c2164c.f21129m;
                if (c1880iI != null) {
                    c1880iI.f19436a.rewind();
                } else {
                    c1880iI = AbstractC1861K.i();
                    c2164c.f21129m = c1880iI;
                }
                InterfaceC1859I.a(c1880iI, ((C1857G) abstractC1858HC).f19377a);
                interfaceC1889rM.p(c1880iI, 1);
            } else if (abstractC1858HC instanceof C1855E) {
                interfaceC1889rM.p(((C1855E) abstractC1858HC).f19375a, 1);
            }
        }
        if (c2164c2 != null) {
            C c = c2164c2.f21132q;
            if (!c.f5202a) {
                throw new IllegalArgumentException("Only add dependencies during a tracking");
            }
            C2453A c2453a = (C2453A) c.d;
            if (c2453a != null) {
                c2453a.a(c2164c);
            } else if (((C2164c) c.f5203b) != null) {
                int i10 = AbstractC2457E.f22585a;
                C2453A c2453a2 = new C2453A();
                C2164c c2164c3 = (C2164c) c.f5203b;
                A7.m.c(c2164c3);
                c2453a2.a(c2164c3);
                c2453a2.a(c2164c);
                c.d = c2453a2;
                c.f5203b = null;
            } else {
                c.f5203b = c2164c;
            }
            C2453A c2453a3 = (C2453A) c.f5204e;
            if (c2453a3 != null) {
                z6 = !c2453a3.j(c2164c);
            } else if (((C2164c) c.c) != c2164c) {
                z6 = true;
            } else {
                c.c = null;
                z6 = false;
            }
            if (z6) {
                c2164c.f21131p++;
            }
        }
        AbstractC1874c.a(interfaceC1889rM).drawRenderNode(renderNode);
        if (z11) {
            interfaceC1889rM.k();
        }
        if (z10) {
            interfaceC1889rM.o();
        }
        if (zIsHardwareAccelerated) {
            return;
        }
        canvas.restore();
    }

    public static Bundle p(Context context, K k10, B b10) {
        if (b10 == null) {
            e.q(k10, "The ILogger object is required.");
        }
        return (Build.VERSION.SDK_INT >= 33 ? context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L)) : context.getPackageManager().getApplicationInfo(context.getPackageName(), 128)).metaData;
    }

    public static final float q(float f10, float f11, float f12) {
        return AbstractC1347d.a(1, f10, f11, f12);
    }

    public static B5.u r(String str) throws ProtocolException {
        int i7;
        String strSubstring;
        A7.m.f(str, "statusLine");
        boolean zZ0 = Q8.o.z0(str, "HTTP/1.", false);
        D d = D.HTTP_1_0;
        if (zZ0) {
            i7 = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt != 0) {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                d = D.HTTP_1_1;
            }
        } else {
            if (!Q8.o.z0(str, "ICY ", false)) {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            i7 = 4;
        }
        int i10 = i7 + 3;
        if (str.length() < i10) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        try {
            String strSubstring2 = str.substring(i7, i10);
            A7.m.e(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            int i11 = Integer.parseInt(strSubstring2);
            if (str.length() <= i10) {
                strSubstring = "";
            } else {
                if (str.charAt(i10) != ' ') {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                strSubstring = str.substring(i7 + 4);
                A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
            }
            return new B5.u(d, i11, strSubstring, 11);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void s(int r6, g1.q r7, h3.y r8, k1.C1596a r9) throws k1.h {
        /*
            r0 = 2
            r1 = 1
            if (r6 != 0) goto Lb
            l1.g r6 = r7.e(r1)
            m1.h r6 = (m1.h) r6
            goto L11
        Lb:
            l1.g r6 = r7.e(r0)
            m1.i r6 = (m1.i) r6
        L11:
            k1.c r2 = r9.u(r1)
            boolean r3 = r2 instanceof k1.C1596a
            if (r3 == 0) goto Lb6
            k1.a r2 = (k1.C1596a) r2
            java.util.ArrayList r3 = r2.f17619p
            int r3 = r3.size()
            if (r3 >= r1) goto L25
            goto Lb6
        L25:
            r3 = 0
            r4 = r3
        L27:
            java.util.ArrayList r5 = r2.f17619p
            int r5 = r5.size()
            if (r4 >= r5) goto L3c
            java.lang.String r5 = r2.B(r4)
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            r6.r(r5)
            int r4 = r4 + r1
            goto L27
        L3c:
            java.util.ArrayList r2 = r9.f17619p
            int r2 = r2.size()
            if (r2 <= r0) goto Lb6
            k1.c r9 = r9.u(r0)
            boolean r0 = r9 instanceof k1.g
            if (r0 != 0) goto L4d
            return
        L4d:
            k1.g r9 = (k1.g) r9
            java.util.ArrayList r0 = r9.F()
            java.util.Iterator r0 = r0.iterator()
        L57:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Lb6
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            r2.getClass()
            java.lang.String r4 = "style"
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L72
            t(r7, r8, r2, r9, r6)
            goto L57
        L72:
            k1.c r2 = r9.v(r2)
            boolean r4 = r2 instanceof k1.C1596a
            if (r4 == 0) goto L90
            r4 = r2
            k1.a r4 = (k1.C1596a) r4
            java.util.ArrayList r5 = r4.f17619p
            int r5 = r5.size()
            if (r5 <= r1) goto L90
            java.lang.String r2 = r4.B(r3)
            float r4 = r4.w(r1)
            r6.f18896n0 = r4
            goto L94
        L90:
            java.lang.String r2 = r2.d()
        L94:
            r2.getClass()
            java.lang.String r4 = "packed"
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto Lb1
            java.lang.String r4 = "spread_inside"
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto Lac
            l1.i r2 = l1.i.l
            r6.f18902t0 = r2
            goto L57
        Lac:
            l1.i r2 = l1.i.f18434m
            r6.f18902t0 = r2
            goto L57
        Lb1:
            l1.i r2 = l1.i.f18435n
            r6.f18902t0 = r2
            goto L57
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.m.s(int, g1.q, h3.y, k1.a):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x014e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void t(g1.q r18, h3.y r19, java.lang.String r20, k1.g r21, l1.C1731b r22) throws k1.h {
        /*
            Method dump skipped, instruction units count: 956
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.m.t(g1.q, h3.y, java.lang.String, k1.g, l1.b):void");
    }

    public static l1.f u(k1.g gVar, String str, g1.q qVar, G2.a aVar) throws k1.h {
        AbstractC1598c abstractC1598cV = gVar.v(str);
        l1.f fVarB = l1.f.b(0);
        if (abstractC1598cV instanceof k1.i) {
            return v(abstractC1598cV.d());
        }
        if (abstractC1598cV instanceof k1.e) {
            return l1.f.b(qVar.c(Float.valueOf(aVar.m(gVar.x(str)))));
        }
        if (!(abstractC1598cV instanceof k1.g)) {
            return fVarB;
        }
        k1.g gVar2 = (k1.g) abstractC1598cV;
        String strD = gVar2.D("value");
        if (strD != null) {
            fVarB = v(strD);
        }
        AbstractC1598c abstractC1598cA = gVar2.A("min");
        if (abstractC1598cA != null) {
            if (abstractC1598cA instanceof k1.e) {
                int iC = qVar.c(Float.valueOf(aVar.m(((k1.e) abstractC1598cA).l())));
                if (iC >= 0) {
                    fVarB.f18426a = iC;
                }
            } else if (abstractC1598cA instanceof k1.i) {
                fVarB.f18426a = -2;
            }
        }
        AbstractC1598c abstractC1598cA2 = gVar2.A("max");
        if (abstractC1598cA2 == null) {
            return fVarB;
        }
        if (abstractC1598cA2 instanceof k1.e) {
            int iC2 = qVar.c(Float.valueOf(aVar.m(((k1.e) abstractC1598cA2).l())));
            if (fVarB.f18427b < 0) {
                return fVarB;
            }
            fVarB.f18427b = iC2;
            return fVarB;
        }
        if (!(abstractC1598cA2 instanceof k1.i)) {
            return fVarB;
        }
        String str2 = l1.f.f18422i;
        if (!fVarB.f18430g) {
            return fVarB;
        }
        fVarB.f18429f = str2;
        fVarB.f18427b = Integer.MAX_VALUE;
        return fVarB;
    }

    public static l1.f v(String str) {
        l1.f fVarB;
        String str2;
        String str3;
        fVarB = l1.f.b(0);
        str.getClass();
        str2 = l1.f.f18423j;
        str3 = l1.f.f18422i;
        switch (str) {
            case "preferWrap":
                return l1.f.c(str3);
            case "parent":
                return new l1.f(l1.f.f18424k);
            case "spread":
                return l1.f.c(str2);
            case "wrap":
                return new l1.f(str3);
            default:
                if (str.endsWith("%")) {
                    float f10 = Float.parseFloat(str.substring(0, str.indexOf(37))) / 100.0f;
                    l1.f fVar = new l1.f(l1.f.l);
                    fVar.c = f10;
                    fVar.f18430g = true;
                    fVar.f18427b = 0;
                    return fVar;
                }
                if (!str.contains(":")) {
                    return fVarB;
                }
                l1.f fVar2 = new l1.f(l1.f.f18425m);
                fVar2.f18428e = str;
                fVar2.f18429f = str2;
                fVar2.f18430g = true;
                return fVar2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:72:0x0115. Please report as an issue. */
    public static void w(int i7, g1.q qVar, String str, k1.g gVar) throws k1.h {
        float fW;
        ArrayList<String> arrayListF = gVar.F();
        C1731b c1731bB = qVar.b(str);
        if (i7 == 0) {
            qVar.d(0, str);
        } else {
            qVar.d(1, str);
        }
        boolean z6 = qVar.f14746b || i7 == 0;
        m1.g gVar2 = (m1.g) c1731bB.c;
        boolean z10 = false;
        boolean z11 = true;
        float fX = 0.0f;
        for (String str2 : arrayListF) {
            str2.getClass();
            switch (str2) {
                case "percent":
                    AbstractC1598c abstractC1598cA = gVar.A(str2);
                    C1596a c1596a = abstractC1598cA instanceof C1596a ? (C1596a) abstractC1598cA : null;
                    if (c1596a != null) {
                        if (c1596a.f17619p.size() > 1) {
                            String strB = c1596a.B(0);
                            fW = c1596a.w(1);
                            strB.getClass();
                            switch (strB) {
                                case "end":
                                    z11 = !z6;
                                    fX = fW;
                                    break;
                                case "left":
                                    fX = fW;
                                    break;
                                case "right":
                                    fX = fW;
                                    z11 = false;
                                    break;
                                case "start":
                                    z11 = z6;
                                    fX = fW;
                                    break;
                                default:
                                    fX = fW;
                                    break;
                            }
                        }
                        z10 = true;
                        break;
                    } else {
                        fX = gVar.x(str2);
                    }
                    z10 = true;
                    z11 = true;
                    break;
                case "end":
                    fX = qVar.f14745a.m(gVar.x(str2));
                    z11 = !z6;
                    break;
                case "left":
                    fX = qVar.f14745a.m(gVar.x(str2));
                    z11 = true;
                    break;
                case "right":
                    fX = qVar.f14745a.m(gVar.x(str2));
                    z11 = false;
                    break;
                case "start":
                    fX = qVar.f14745a.m(gVar.x(str2));
                    z11 = z6;
                    break;
            }
        }
        if (z10) {
            if (z11) {
                gVar2.d = -1;
                gVar2.f18942e = -1;
                gVar2.f18943f = fX;
                return;
            } else {
                gVar2.d = -1;
                gVar2.f18942e = -1;
                gVar2.f18943f = 1.0f - fX;
                return;
            }
        }
        if (z11) {
            gVar2.d = gVar2.f18940a.c(Float.valueOf(fX));
            gVar2.f18942e = -1;
            gVar2.f18943f = 0.0f;
        } else {
            Float fValueOf = Float.valueOf(fX);
            gVar2.d = -1;
            gVar2.f18942e = gVar2.f18940a.c(fValueOf);
            gVar2.f18943f = 0.0f;
        }
    }

    public static void x(g1.q qVar, y yVar, String str, k1.g gVar) throws k1.h {
        C1731b c1731bB = qVar.b(str);
        l1.f fVar = c1731bB.f18389d0;
        String str2 = l1.f.f18422i;
        if (fVar == null) {
            c1731bB.f18389d0 = new l1.f(str2);
        }
        if (c1731bB.f18391e0 == null) {
            c1731bB.f18391e0 = new l1.f(str2);
        }
        Iterator it = gVar.F().iterator();
        while (it.hasNext()) {
            g(qVar, yVar, (String) it.next(), gVar, c1731bB);
        }
    }

    public static boolean y(Bundle bundle, K k10, String str, boolean z6) {
        boolean z10 = bundle.getBoolean(str, z6);
        k10.k(EnumC1495p1.DEBUG, str + " read: " + z10, new Object[0]);
        return z10;
    }

    public static Boolean z(Bundle bundle, K k10) {
        if (bundle.getSerializable("io.sentry.traces.enable") == null) {
            k10.k(EnumC1495p1.DEBUG, "io.sentry.traces.enable used default null", new Object[0]);
            return null;
        }
        boolean z6 = bundle.getBoolean("io.sentry.traces.enable", false);
        k10.k(EnumC1495p1.DEBUG, "io.sentry.traces.enable read: " + z6, new Object[0]);
        return Boolean.valueOf(z6);
    }
}
