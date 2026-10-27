package com.bumptech.glide;

import A7.A;
import A7.m;
import C0.C0102i;
import C0.O;
import C0.c0;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0124f;
import D.C0141x;
import D.P;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import E5.z;
import F0.AbstractC0227s0;
import H7.B;
import H7.InterfaceC0318c;
import H7.InterfaceC0319d;
import H7.x;
import K7.C0393q;
import K7.o0;
import K7.p0;
import K7.r0;
import N0.C0411f;
import N0.H;
import N0.K;
import O.C0481l;
import O.C0482m;
import O.C0484o;
import O.InterfaceC0478i;
import O0.w;
import P8.n;
import Q.AbstractC0535v;
import Q.c1;
import Q5.C0549b;
import Q5.C0553c;
import Q5.C0594m0;
import Q5.C0622t1;
import Q5.C0626u1;
import Q5.C0634w1;
import Q5.C0641y0;
import Q5.K0;
import Q5.L;
import Q5.M1;
import Q5.N1;
import Q5.O1;
import Q5.R2;
import Q5.T2;
import Q5.U2;
import Q5.V2;
import Q5.W1;
import Q5.W2;
import Q5.Y0;
import Q5.c3;
import Q5.d3;
import Q5.f3;
import Q5.i3;
import Q5.j3;
import Q5.l3;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.f0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.Q0;
import T.R0;
import T.S;
import T.X;
import U.D;
import Y4.t;
import Z7.o;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.ImageType;
import com.stingray.kca.model.Images;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.kca.model.ScoringDetail;
import f1.AbstractC1252g;
import f1.C1248c;
import f1.C1251f;
import g1.C1283j;
import g1.C1285l;
import g1.p;
import h2.AbstractC1347d;
import h3.y;
import h6.q;
import h6.r;
import io.sentry.C1489n1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import k6.AbstractC1692D;
import k6.s;
import l0.InterfaceC1716g;
import n0.AbstractC1861K;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n6.C1966G;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static C1489n1 f12671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f12672b = 0;
    public static final /* synthetic */ int c = 0;

    /* JADX WARN: Removed duplicated region for block: B:100:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void A(final g0.o r21, java.lang.Integer r22, final java.util.ArrayList r23, z7.InterfaceC2772a r24, final D.L r25, final z7.InterfaceC2785n r26, final b6.g r27, boolean r28, java.lang.Integer r29, T.C0761q r30, final int r31, final int r32) {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.A(g0.o, java.lang.Integer, java.util.ArrayList, z7.a, D.L, z7.n, b6.g, boolean, java.lang.Integer, T.q, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void B(final g0.o r23, java.lang.Integer r24, final java.util.ArrayList r25, final boolean r26, final z7.InterfaceC2772a r27, final D.L r28, final boolean r29, final z7.InterfaceC2785n r30, final b6.g r31, final java.lang.Integer r32, T.C0761q r33, final int r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.B(g0.o, java.lang.Integer, java.util.ArrayList, boolean, z7.a, D.L, boolean, z7.n, b6.g, java.lang.Integer, T.q, int, int):void");
    }

    public static final void B0(D d, int i7, int i10) {
        int i11 = 1 << i7;
        int i12 = d.f8941j;
        if ((i12 & i11) == 0) {
            d.f8941j = i11 | i12;
            d.f8937f[(d.f8938g - d.U().f8934a) + i7] = i10;
        } else {
            C0735d.Y("Already pushed argument " + d.U().b(i7));
            throw null;
        }
    }

    public static final C0482m C(t4.e eVar, InterfaceC0478i interfaceC0478i) {
        boolean z6 = eVar.f() == 1;
        z zVar = (z) eVar.o;
        return new C0482m(F(zVar, z6, true, interfaceC0478i), F(zVar, z6, false, interfaceC0478i), z6);
    }

    public static final void C0(D d, int i7, Object obj) {
        int i10 = 1 << i7;
        int i11 = d.f8942k;
        if ((i11 & i10) == 0) {
            d.f8942k = i10 | i11;
            d.f8939h[(d.f8940i - d.U().f8935b) + i7] = obj;
        } else {
            C0735d.Y("Already pushed argument " + d.U().c(i7));
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0082 -> B:25:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0085 -> B:25:0x0065). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object D(java.util.List r6, N1.C0431l r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof N1.C0425f
            if (r0 == 0) goto L13
            r0 = r8
            N1.f r0 = (N1.C0425f) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            N1.f r0 = new N1.f
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f4523n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            java.util.Iterator r6 = r0.f4522m
            java.io.Serializable r7 = r0.l
            A7.z r7 = (A7.z) r7
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Throwable -> L30
            goto L65
        L30:
            r8 = move-exception
            goto L7e
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.io.Serializable r6 = r0.l
            java.util.List r6 = (java.util.List) r6
            h8.AbstractC1386c.u(r8)
            goto L5c
        L42:
            h8.AbstractC1386c.u(r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            N1.h r2 = new N1.h
            r5 = 0
            r2.<init>(r6, r8, r5)
            r0.l = r8
            r0.o = r4
            java.lang.Object r6 = r7.a(r2, r0)
            if (r6 != r1) goto L5b
            goto L93
        L5b:
            r6 = r8
        L5c:
            A7.z r7 = new A7.z
            r7.<init>()
            java.util.Iterator r6 = r6.iterator()
        L65:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L8b
            java.lang.Object r8 = r6.next()
            z7.k r8 = (z7.InterfaceC2782k) r8
            r0.l = r7     // Catch: java.lang.Throwable -> L30
            r0.f4522m = r6     // Catch: java.lang.Throwable -> L30
            r0.o = r3     // Catch: java.lang.Throwable -> L30
            java.lang.Object r8 = r8.b(r0)     // Catch: java.lang.Throwable -> L30
            if (r8 != r1) goto L65
            goto L93
        L7e:
            java.lang.Object r2 = r7.l
            if (r2 != 0) goto L85
            r7.l = r8
            goto L65
        L85:
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            h6.q.l(r2, r8)
            goto L65
        L8b:
            java.lang.Object r6 = r7.l
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            if (r6 != 0) goto L94
            m7.q r1 = m7.q.f19267a
        L93:
            return r1
        L94:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.D(java.util.List, N1.l, s7.c):java.lang.Object");
    }

    public static final C0661n D0(f0 f0Var) {
        m.f(f0Var, "<this>");
        C0661n c0661n = (C0661n) o.d.get(f0Var);
        return c0661n == null ? AbstractC0662o.f(f0Var) : c0661n;
    }

    public static final C0481l E(t4.e eVar, z zVar, C0481l c0481l) {
        boolean z6 = eVar.f22462m;
        int i7 = zVar.c;
        int i10 = zVar.f1841b;
        int i11 = z6 ? i10 : i7;
        m7.g gVar = m7.g.f19257m;
        m7.f fVarN = r.n(gVar, new C0393q(i11, i, zVar));
        m7.f fVarN2 = r.n(gVar, new C0484o(zVar, i11, z6 ? i7 : i10, eVar, fVarN));
        if (1 != c0481l.c) {
            return (C0481l) fVarN2.getValue();
        }
        int i12 = zVar.d;
        if (i11 == i12) {
            return c0481l;
        }
        H h10 = (H) zVar.f1842e;
        if (((Number) fVarN.getValue()).intValue() != h10.f(i12)) {
            return (C0481l) fVarN2.getValue();
        }
        int i13 = c0481l.f4971b;
        long jL = h10.l(i13);
        if (i12 != -1) {
            if (i11 != i12) {
                if (((z6 ? 1 : 0) ^ ((i10 < i7 || i10 <= i7) ? 0 : 1)) == 0) {
                }
            }
            return zVar.b(i11);
        }
        int i14 = K.c;
        return (i13 == ((int) (jL >> 32)) || i13 == ((int) (4294967295L & jL))) ? (C0481l) fVarN2.getValue() : zVar.b(i11);
    }

    public static final C0481l F(z zVar, boolean z6, boolean z10, InterfaceC0478i interfaceC0478i) {
        long j3;
        long jA = interfaceC0478i.a(zVar, z10 ? zVar.f1841b : zVar.c);
        if (z6 ^ z10) {
            int i7 = K.c;
            j3 = jA >> 32;
        } else {
            int i10 = K.c;
            j3 = 4294967295L & jA;
        }
        return zVar.b((int) j3);
    }

    public static boolean G(byte[] bArr, byte[] bArr2, int i7) {
        for (int i10 = 0; i10 < i7; i10++) {
            if (i10 >= bArr.length || i10 >= bArr2.length || bArr[i10] != bArr2[i10]) {
                return false;
            }
        }
        return true;
    }

    public static int H(int i7, int i10, byte[] bArr) {
        int i11 = 0;
        int i12 = 0;
        for (int i13 = (i10 + i7) - 1; i13 >= i7; i13--) {
            i11 += (bArr[i13] & 255) << i12;
            i12 += 8;
        }
        return i11;
    }

    public static final C0481l L(C0481l c0481l, z zVar, int i7) {
        return new C0481l(((H) zVar.f1842e).a(i7), i7, c0481l.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static g0.o O(g0.o oVar, InterfaceC2772a interfaceC2772a) {
        m.f(oVar, "$this$clickableNoRipple");
        m.f(interfaceC2772a, "onClick");
        return g0.a.b(oVar, new M5.f(true, null, interfaceC2772a, 0 == true ? 1 : 0, 1));
    }

    public static g0.o P(g0.o oVar, boolean z6, C.k kVar, L0.g gVar, InterfaceC2772a interfaceC2772a, int i7) {
        if ((i7 & 1) != 0) {
            z6 = true;
        }
        boolean z10 = z6;
        C.k kVar2 = (i7 & 4) != 0 ? null : kVar;
        L0.g gVar2 = (i7 & 8) != 0 ? null : gVar;
        m.f(oVar, "$this$clickableRipple");
        m.f(interfaceC2772a, "onClick");
        return g0.a.b(oVar, new M5.g(kVar2, z10, (String) null, interfaceC2772a, gVar2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static g0.o Q(int i7, g0.o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        boolean z10 = (i7 & 1) != 0 ? true : z6;
        m.f(oVar, "$this$clickableUnboundedRipple");
        m.f(interfaceC2772a, "onClick");
        return g0.a.b(oVar, new M5.f(z10, null, interfaceC2772a, 0 == true ? 1 : 0, 2));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v10 ??, still in use, count: 1, list:
          (r11v10 ?? I:java.lang.Object) from 0x005a: INVOKE (r10v0 ?? I:T.q), (r11v10 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final T.X R(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v10 ??, still in use, count: 1, list:
          (r11v10 ?? I:java.lang.Object) from 0x005a: INVOKE (r10v0 ?? I:T.q), (r11v10 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
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

    public static final X S(C.k kVar, C0761q c0761q, int i7) {
        Object objG = c0761q.G();
        S s6 = C0753m.f8270a;
        if (objG == s6) {
            objG = C0735d.O(Boolean.FALSE, S.f8224q);
            c0761q.a0(objG);
        }
        X x8 = (X) objG;
        boolean z6 = (((i7 & 14) ^ 6) > 4 && c0761q.f(kVar)) || (i7 & 6) == 4;
        Object objG2 = c0761q.G();
        if (z6 || objG2 == s6) {
            objG2 = new C.g(kVar, x8, null);
            c0761q.a0(objG2);
        }
        C0735d.f(c0761q, kVar, (InterfaceC2785n) objG2);
        return x8;
    }

    public static final R7.h T(R7.h hVar, R7.h hVar2) {
        m.f(hVar, "first");
        m.f(hVar2, "second");
        return hVar.isEmpty() ? hVar2 : hVar2.isEmpty() ? hVar : new R7.i(new R7.h[]{hVar, hVar2});
    }

    public static final T2 U(String str, C0761q c0761q) {
        m.f(str, "id");
        c0761q.Q(1091745061);
        T2 t22 = new T2("Empty_".concat(str), 1, L.d);
        c0761q.p(false);
        return t22;
    }

    public static final ArrayList V(List list, KaraokeItemChildType karaokeItemChildType, boolean z6, boolean z10, InterfaceC2782k interfaceC2782k, int i7, C0761q c0761q, int i10) {
        m.f(list, "karaokeItems");
        m.f(interfaceC2782k, "onTileClicked");
        c0761q.Q(1214525678);
        boolean z11 = (i10 & 8) != 0 ? false : z10;
        int i11 = (i10 & 32) != 0 ? 0 : i7;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            KaraokeItem karaokeItem = (KaraokeItem) it.next();
            String str = karaokeItem.f13107a;
            m.c(str);
            arrayList.add(new T2(str, 1, b0.g.b(-550714838, new i3(karaokeItem, z6, interfaceC2782k, karaokeItemChildType, z11, i11), c0761q)));
        }
        c0761q.p(false);
        return arrayList;
    }

    public static final ArrayList W(int i7, boolean z6, InterfaceC2782k interfaceC2782k, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, C0761q c0761q) {
        m.f(interfaceC2782k, "toggleParentalControls");
        m.f(interfaceC2772a, "openFaq");
        m.f(interfaceC2772a2, "openContactUs");
        m.f(interfaceC2772a3, "openAbout");
        c0761q.Q(2017419431);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new T2("Parental Controls", i7, b0.g.b(2083545131, new j3(interfaceC2782k, z6), c0761q)));
        arrayList.add(new T2("FAQ", 1, b0.g.b(539082580, new l3(interfaceC2772a, 0), c0761q)));
        arrayList.add(new T2("Contact Us", 1, b0.g.b(-1403095885, new l3(interfaceC2772a2, 1), c0761q)));
        arrayList.add(new T2("About", 1, b0.g.b(949692946, new l3(interfaceC2772a3, 2), c0761q)));
        c0761q.p(false);
        return arrayList;
    }

    public static c9.D X(String str) throws IOException {
        if (str.equals("http/1.0")) {
            return c9.D.HTTP_1_0;
        }
        if (str.equals("http/1.1")) {
            return c9.D.HTTP_1_1;
        }
        if (str.equals("h2_prior_knowledge")) {
            return c9.D.H2_PRIOR_KNOWLEDGE;
        }
        if (str.equals("h2")) {
            return c9.D.HTTP_2;
        }
        if (str.equals("spdy/3.1")) {
            return c9.D.SPDY_3;
        }
        if (str.equals("quic")) {
            return c9.D.QUIC;
        }
        throw new IOException("Unexpected protocol: ".concat(str));
    }

    public static int Y(View view, int i7) {
        return n.U(i7, view.getContext(), view.getClass().getCanonicalName());
    }

    public static ColorStateList Z(Context context, TypedArray typedArray, int i7) {
        int resourceId;
        ColorStateList colorStateListH;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (colorStateListH = h6.f.h(context, resourceId)) == null) ? typedArray.getColorStateList(i7) : colorStateListH;
    }

    public static final void a(int i7, int i10, C0761q c0761q, KaraokeItem karaokeItem, g0.o oVar, InterfaceC2772a interfaceC2772a) {
        g0.o oVar2;
        int i11;
        g0.o oVar3;
        m.f(karaokeItem, "item");
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(-858228052);
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
            i11 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar2;
        } else {
            oVar3 = i12 != 0 ? g0.l.f14684a : oVar2;
            String str = karaokeItem.f13108b;
            m.c(str);
            Boolean bool = Boolean.TRUE;
            b(oVar3, str, karaokeItem.d, m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), interfaceC2772a, c0761q, (i11 & 14) | ((i11 << 9) & 458752));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new c3(oVar3, karaokeItem, interfaceC2772a, i7, i10);
        }
    }

    public static ColorStateList a0(Context context, y yVar, int i7) {
        int resourceId;
        ColorStateList colorStateListH;
        TypedArray typedArray = (TypedArray) yVar.f15500n;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (colorStateListH = h6.f.h(context, resourceId)) == null) ? yVar.p(i7) : colorStateListH;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0261  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(g0.o r41, java.lang.String r42, java.lang.String r43, boolean r44, boolean r45, z7.InterfaceC2772a r46, T.C0761q r47, int r48) {
        /*
            Method dump skipped, instruction units count: 839
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.b(g0.o, java.lang.String, java.lang.String, boolean, boolean, z7.a, T.q, int):void");
    }

    public static final void c(int i7, int i10, C0761q c0761q, KaraokeItem karaokeItem, g0.o oVar, InterfaceC2772a interfaceC2772a) {
        int i11;
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(-1075350869);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean bool = Boolean.TRUE;
            d(oVar, karaokeItem.f13108b, karaokeItem.d, i7, m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), interfaceC2772a, c0761q, (i11 & 14) | ((i11 << 3) & 7168) | ((i11 << 9) & 3670016));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new c3(oVar, karaokeItem, i7, interfaceC2772a, i10, 0);
        }
    }

    public static Drawable c0(Context context, TypedArray typedArray, int i7) {
        int resourceId;
        Drawable drawableK;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (drawableK = h6.f.k(context, resourceId)) == null) ? typedArray.getDrawable(i7) : drawableK;
    }

    public static final void d(final g0.o oVar, final String str, final String str2, final int i7, final boolean z6, final boolean z10, final InterfaceC2772a interfaceC2772a, C0761q c0761q, final int i10) {
        int i11;
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(-1621833730);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.d(i7) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 1048576 : 524288;
        }
        if ((599187 & i11) == 599186 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-1316174445);
            if (!z6) {
                y(((i11 >> 12) & 112) | (i11 & 14), c0761q, oVar, z10);
                c0761q.p(false);
                C0754m0 c0754m0R = c0761q.r();
                if (c0754m0R != null) {
                    final int i12 = 0;
                    c0754m0R.d = new InterfaceC2785n() { // from class: Q5.a3
                        @Override // z7.InterfaceC2785n
                        public final Object invoke(Object obj, Object obj2) {
                            switch (i12) {
                                case 0:
                                    ((Integer) obj2).getClass();
                                    int iZ = C0735d.Z(i10 | 1);
                                    boolean z11 = z10;
                                    InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
                                    com.bumptech.glide.c.d(oVar, str, str2, i7, z6, z11, interfaceC2772a2, (C0761q) obj, iZ);
                                    break;
                                default:
                                    ((Integer) obj2).getClass();
                                    int iZ2 = C0735d.Z(i10 | 1);
                                    boolean z12 = z10;
                                    InterfaceC2772a interfaceC2772a3 = interfaceC2772a;
                                    com.bumptech.glide.c.d(oVar, str, str2, i7, z6, z12, interfaceC2772a3, (C0761q) obj, iZ2);
                                    break;
                            }
                            return m7.q.f19267a;
                        }
                    };
                    return;
                }
                return;
            }
            c0761q.p(false);
            l6.d.b(true, false, b0.g.b(-711461078, new d3(oVar, interfaceC2772a, i7, str, str2), c0761q), c0761q, 390, 2);
        }
        C0754m0 c0754m0R2 = c0761q.r();
        if (c0754m0R2 != null) {
            final int i13 = 1;
            c0754m0R2.d = new InterfaceC2785n() { // from class: Q5.a3
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    switch (i13) {
                        case 0:
                            ((Integer) obj2).getClass();
                            int iZ = C0735d.Z(i10 | 1);
                            boolean z11 = z10;
                            InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
                            com.bumptech.glide.c.d(oVar, str, str2, i7, z6, z11, interfaceC2772a2, (C0761q) obj, iZ);
                            break;
                        default:
                            ((Integer) obj2).getClass();
                            int iZ2 = C0735d.Z(i10 | 1);
                            boolean z12 = z10;
                            InterfaceC2772a interfaceC2772a3 = interfaceC2772a;
                            com.bumptech.glide.c.d(oVar, str, str2, i7, z6, z12, interfaceC2772a3, (C0761q) obj, iZ2);
                            break;
                    }
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final float d0(Layout layout, int i7, Paint paint) {
        float fAbs;
        float width;
        float lineLeft = layout.getLineLeft(i7);
        w wVar = O0.y.f5060a;
        if (layout.getEllipsisCount(i7) <= 0 || layout.getParagraphDirection(i7) != 1 || lineLeft >= 0.0f) {
            return 0.0f;
        }
        float fMeasureText = paint.measureText("…") + (layout.getPrimaryHorizontal(layout.getEllipsisStart(i7) + layout.getLineStart(i7)) - lineLeft);
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i7);
        if ((paragraphAlignment == null ? -1 : Q0.d.f6091a[paragraphAlignment.ordinal()]) == 1) {
            fAbs = Math.abs(lineLeft);
            width = (layout.getWidth() - fMeasureText) / 2.0f;
        } else {
            fAbs = Math.abs(lineLeft);
            width = layout.getWidth() - fMeasureText;
        }
        return width + fAbs;
    }

    public static final void e(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        String str;
        Map map;
        Map map2;
        m.f(karaokeItem, "item");
        c0761q.S(5098674);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zB = AbstractC1692D.b((LayoutMode) c0761q.k(AbstractC1692D.f18238a));
            Images images = karaokeItem.f13121s;
            if (images == null || (map2 = images.f13100b) == null || (str = (String) map2.get(ImageType.PROMOLARGE.getValue())) == null) {
                str = karaokeItem.c;
            }
            AbstractC0120b.a(oVar, null, false, b0.g.b(-811271672, new M5.e(oVar, zB, (images == null || (map = images.f13100b) == null) ? null : (String) map.get(ImageType.PROMOSMALL.getValue()), str, karaokeItem, interfaceC2772a), c0761q), c0761q, (i10 & 14) | 3072, 6);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new V2(oVar, karaokeItem, interfaceC2772a, i7, 2);
        }
    }

    public static final float e0(Layout layout, int i7, Paint paint) {
        float width;
        float width2;
        w wVar = O0.y.f5060a;
        if (layout.getEllipsisCount(i7) <= 0) {
            return 0.0f;
        }
        if (layout.getParagraphDirection(i7) != -1 || layout.getWidth() >= layout.getLineRight(i7)) {
            return 0.0f;
        }
        float fMeasureText = paint.measureText("…") + (layout.getLineRight(i7) - layout.getPrimaryHorizontal(layout.getEllipsisStart(i7) + layout.getLineStart(i7)));
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i7);
        if ((paragraphAlignment != null ? Q0.d.f6091a[paragraphAlignment.ordinal()] : -1) == 1) {
            width = layout.getWidth() - layout.getLineRight(i7);
            width2 = (layout.getWidth() - fMeasureText) / 2.0f;
        } else {
            width = layout.getWidth() - layout.getLineRight(i7);
            width2 = layout.getWidth() - fMeasureText;
        }
        return width - width2;
    }

    public static final void f(final V5.o oVar, final float f10, C0761q c0761q, final int i7) {
        int i10;
        c0761q.S(355144048);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            V5.g.a(oVar, f10, null, false, false, false, l6.d.c(c0761q).f18704g, l6.d.c(c0761q).f18702f, null, 0.0f, true, false, c0761q, i10 & 126, 54, 828);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.l0
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).intValue();
                    int iZ = C0735d.Z(i7 | 1);
                    com.bumptech.glide.c.f(oVar, f10, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final InterfaceC0318c f0(InterfaceC0319d interfaceC0319d) {
        Object obj;
        if (interfaceC0319d instanceof InterfaceC0318c) {
            return (InterfaceC0318c) interfaceC0319d;
        }
        if (!(interfaceC0319d instanceof H7.y)) {
            throw new r0("Cannot calculate JVM erasure for type: " + interfaceC0319d);
        }
        H7.w wVar = p0.o[0];
        Object objA = ((p0) ((H7.y) interfaceC0319d)).f3828m.a();
        m.e(objA, "<get-upperBounds>(...)");
        List list = (List) objA;
        Iterator it = list.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            x xVar = (x) next;
            m.d(xVar, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
            InterfaceC0654g interfaceC0654gN = ((o0) xVar).f3824a.B0().n();
            InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
            if (interfaceC0652e != null && interfaceC0652e.E() != 2 && interfaceC0652e.E() != 5) {
                obj = next;
                break;
            }
        }
        x xVar2 = (x) obj;
        if (xVar2 == null) {
            xVar2 = (x) AbstractC1998m.a0(list);
        }
        return xVar2 != null ? g0(xVar2) : A.f443a.b(Object.class);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v11 ??, still in use, count: 1, list:
          (r2v11 ?? I:java.lang.Object) from 0x00ef: INVOKE (r24v0 ?? I:T.q), (r2v11 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final void g(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v11 ??, still in use, count: 1, list:
          (r2v11 ?? I:java.lang.Object) from 0x00ef: INVOKE (r24v0 ?? I:T.q), (r2v11 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
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

    public static final InterfaceC0318c g0(x xVar) {
        InterfaceC0318c interfaceC0318cF0;
        InterfaceC0319d interfaceC0319dC = xVar.c();
        if (interfaceC0319dC != null && (interfaceC0318cF0 = f0(interfaceC0319dC)) != null) {
            return interfaceC0318cF0;
        }
        throw new r0("Cannot calculate JVM erasure for type: " + xVar);
    }

    public static final void h(int i7, int i10, C0761q c0761q, KaraokeItem karaokeItem, g0.o oVar, InterfaceC2772a interfaceC2772a) throws XmlPullParserException, IOException {
        int i11;
        String str;
        Map map;
        c0761q.S(1136051211);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarA = androidx.compose.foundation.a.a(q.m(oVar, J.e.a(m6.c.f19252a.f0(c0761q))), r.l(i7, c0761q), null, 6);
            Images images = karaokeItem.f13121s;
            if (images == null || (map = images.f13100b) == null || (str = (String) map.get(ImageType.THUMBNAILSQUAREFOREGROUND.getValue())) == null) {
                str = karaokeItem.c;
            }
            Boolean bool = Boolean.TRUE;
            k(oVarA, str, false, m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), interfaceC2772a, c0761q, (i11 << 6) & 458752, 4);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new c3(oVar, karaokeItem, i7, interfaceC2772a, i10, 2);
        }
    }

    public static final C0411f h0(T0.y yVar) {
        C0411f c0411f = yVar.f8481a;
        c0411f.getClass();
        long j3 = yVar.f8482b;
        return c0411f.subSequence(K.e(j3), K.d(j3));
    }

    public static final void i(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        c0761q.S(197673726);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean bool = Boolean.TRUE;
            k(oVar, karaokeItem.c, m.a(karaokeItem.o, bool), m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), interfaceC2772a, c0761q, (i10 & 14) | ((i10 << 9) & 458752), 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new V2(oVar, karaokeItem, interfaceC2772a, i7, 0);
        }
    }

    public static final C0411f i0(T0.y yVar, int i7) {
        C0411f c0411f = yVar.f8481a;
        long j3 = yVar.f8482b;
        return c0411f.subSequence(K.d(j3), Math.min(K.d(j3) + i7, yVar.f8481a.l.length()));
    }

    public static final void j(g0.o oVar, KaraokeSongResponseV2 karaokeSongResponseV2, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        InterfaceC2772a interfaceC2772a2;
        c0761q.S(-116870561);
        if ((i7 & 6) == 0) {
            i10 = i7 | (c0761q.f(oVar) ? 4 : 2);
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeSongResponseV2) ? 32 : 16;
        }
        int i11 = i10 | 384;
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            interfaceC2772a2 = interfaceC2772a;
        } else {
            k(oVar, karaokeSongResponseV2 != null ? karaokeSongResponseV2.l : null, karaokeSongResponseV2 != null ? m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE) : false, karaokeSongResponseV2 != null ? m.a(karaokeSongResponseV2.f13145m, Boolean.TRUE) : false, karaokeSongResponseV2 != null ? m.a(karaokeSongResponseV2.f13143j, Boolean.TRUE) : false, null, c0761q, (i11 & 14) | ((i11 << 9) & 458752), 0);
            interfaceC2772a2 = null;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0641y0(i7, 4, oVar, karaokeSongResponseV2, interfaceC2772a2);
        }
    }

    public static final C0411f j0(T0.y yVar, int i7) {
        C0411f c0411f = yVar.f8481a;
        long j3 = yVar.f8482b;
        return c0411f.subSequence(Math.max(0, K.e(j3) - i7), K.e(j3));
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void k(final g0.o r16, final java.lang.String r17, boolean r18, final boolean r19, final boolean r20, z7.InterfaceC2772a r21, T.C0761q r22, final int r23, final int r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.k(g0.o, java.lang.String, boolean, boolean, boolean, z7.a, T.q, int, int):void");
    }

    public static final void l(final F.y yVar, final int i7, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(-1674377821);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(yVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1762102758);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.G(new InterfaceC2772a() { // from class: Q5.k0
                    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
                    @Override // z7.InterfaceC2772a
                    public final Object a() {
                        F.y yVar2 = yVar;
                        F.p pVar = (F.p) AbstractC1998m.i0(yVar2.g().f1920g);
                        boolean z6 = false;
                        if (pVar != null) {
                            if (pVar.f1927a >= (yVar2.g().f1923j - 1) - i7) {
                                z6 = true;
                            }
                        }
                        return Boolean.valueOf(z6);
                    }
                });
                c0761q.a0(objG);
            }
            Q0 q02 = (Q0) objG;
            c0761q.p(false);
            c0761q.Q(1762111321);
            boolean z6 = (i11 & 896) == 256;
            Object objG2 = c0761q.G();
            if (z6 || objG2 == s6) {
                objG2 = new C0594m0(q02, interfaceC2772a, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, q02, (InterfaceC2785n) objG2);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.j(yVar, i7, interfaceC2772a, i10);
        }
    }

    public static final void m(g0.o oVar, String str, String str2, boolean z6, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1918275099);
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
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            long j3 = l6.d.c(c0761q).f18696b;
            long j10 = l6.d.c(c0761q).f18671D;
            g0.o oVarD = androidx.compose.foundation.layout.c.d(oVar, 1.0f);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarM = q.m(oVarD, J.e.a(bVar.f0(c0761q)));
            c0761q.Q(1013810092);
            boolean z10 = ((i10 & 57344) == 16384) | ((i10 & 7168) == 2048);
            Object objG = c0761q.G();
            if (z10 || objG == C0753m.f8270a) {
                objG = new W1(1, interfaceC2782k, z6);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarC = h6.i.c(O(oVarM, (InterfaceC2772a) objG), 3, j10, J.e.a(bVar.f0(c0761q)));
            C0.K kE = AbstractC0133o.e(g0.b.l, false);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarC);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD2, c0158h4);
            g0.o oVarL = androidx.compose.foundation.layout.a.l(oVar, bVar.F(c0761q), bVar.H(6, c0761q));
            D.S sB = P.b(AbstractC0127i.f1076a, g0.b.f14670v, c0761q, 48);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarL);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarB = T.b(lVar, 5.0f);
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q)), g0.b.f14672x, c0761q, 0);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD4 = g0.a.d(c0761q, oVarB);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD4, c0158h4);
            R0 r02 = O5.c.f5157b;
            int i14 = i10;
            c1.b(str, null, j3, h6.f.g(FontSize.H9, c0761q), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, (i10 >> 3) & 14, 0, 65522);
            c0761q.Q(-579178493);
            if (str2 != null && !Q8.h.M0(str2)) {
                c1.b(str2, null, l6.d.c(c0761q).d, h6.f.g(FontSize.Subtitle1, c0761q), null, null, null, 0L, null, null, 0L, 2, false, 0, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, (i14 >> 6) & 14, 48, 63474);
            }
            c0761q.p(false);
            c0761q.p(true);
            AbstractC0120b.b(c0761q, T.b(lVar, 0.5f));
            H7.H.j(lVar, null, null, false, z6, null, 1.25f, null, null, 0, c0761q, ((i14 << 3) & 57344) | 1572870, 942);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new U2(oVar, str, str2, z6, interfaceC2782k, i7, 0);
        }
    }

    public static H7.A m0(x xVar) {
        m.f(xVar, "type");
        return new H7.A(B.l, xVar);
    }

    public static final void n(String str, List list, LoadingInterface$LoadingState loadingInterface$LoadingState, final InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n3, b6.g gVar, D.L l, boolean z6, C0761q c0761q, int i7) {
        int i10;
        boolean z10;
        float fJ;
        D.L l10;
        int i11;
        boolean z11;
        boolean z12;
        final String str2;
        m.f(str, "searchText");
        m.f(list, "searchData");
        m.f(loadingInterface$LoadingState, "loadingState");
        m.f(interfaceC2785n, "searchAction");
        m.f(interfaceC2785n2, "openItemDetails");
        m.f(interfaceC2782k, "onMoreSelected");
        m.f(interfaceC2785n3, "onFirstVisibleItem");
        m.f(gVar, "firstVisibleItem");
        m.f(l, "contentPadding");
        c0761q.S(-959927328);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(loadingInterface$LoadingState) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2785n2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2785n3) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.h(gVar) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.f(l) ? 67108864 : 33554432;
        }
        if ((805306368 & i7) == 0) {
            i10 |= c0761q.g(z6) ? 536870912 : 268435456;
        }
        int i12 = i10;
        if ((306783379 & i12) == 306783378 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zBooleanValue = ((Boolean) c0761q.k(AbstractC1692D.f18239b)).booleanValue();
            InterfaceC1716g interfaceC1716g = (InterfaceC1716g) c0761q.k(AbstractC0227s0.f2260g);
            int i13 = z6 ? R.string.Search_HintDisabled : R.string.Search_Hint;
            g0.l lVar = g0.l.f14684a;
            g0.o oVarO = androidx.compose.foundation.layout.a.o(lVar, AbstractC2673a.J(l, c0761q), AbstractC2673a.K(l, c0761q), AbstractC2673a.I(l, c0761q), 0.0f, 8);
            c0761q.Q(1104367923);
            boolean z13 = (1879048192 & i12) == 536870912;
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (z13 || objG == s6) {
                objG = new C0553c(3, z6);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarO2 = O(oVarO, (InterfaceC2772a) objG);
            String strX = AbstractC2673a.X(i13, c0761q);
            c0761q.Q(1104376801);
            int i14 = i12 & 7168;
            boolean z14 = i14 == 2048;
            Object objG2 = c0761q.G();
            if (z14 || objG2 == s6) {
                objG2 = new C0622t1(interfaceC2785n, 0);
                c0761q.a0(objG2);
            }
            InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) objG2;
            c0761q.p(false);
            c0761q.Q(1104378988);
            int i15 = i12 & 14;
            boolean zH = c0761q.h(interfaceC1716g) | (i14 == 2048) | (i15 == 4);
            Object objG3 = c0761q.G();
            if (zH || objG3 == s6) {
                objG3 = new C0626u1(interfaceC1716g, interfaceC2785n, str, 0);
                c0761q.a0(objG3);
            }
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG3;
            c0761q.p(false);
            c0761q.Q(1104382468);
            boolean zH2 = c0761q.h(interfaceC1716g) | (i14 == 2048);
            Object objG4 = c0761q.G();
            if (zH2 || objG4 == s6) {
                objG4 = new M5.d(interfaceC1716g, 3, interfaceC2785n);
                c0761q.a0(objG4);
            }
            c0761q.p(false);
            n.g(oVarO2, str, strX, null, interfaceC2782k2, interfaceC2772a, (InterfaceC2772a) objG4, !z6, c0761q, (i12 << 3) & 112);
            if (loadingInterface$LoadingState == LoadingInterface$LoadingState.Loading) {
                c0761q.Q(-123692510);
                io.sentry.config.a.t(androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.c(lVar, 1.0f), l), 0L, c0761q, 0, 2);
                c0761q.p(false);
            } else if (loadingInterface$LoadingState == LoadingInterface$LoadingState.Error) {
                c0761q.Q(-123486143);
                c0761q.Q(1104395395);
                if (zBooleanValue) {
                    l10 = l;
                    i11 = i15;
                    z11 = false;
                    z12 = true;
                    str2 = str;
                } else {
                    c0761q.Q(1104396718);
                    i11 = i15;
                    boolean z15 = (i14 == 2048) | (i11 == 4);
                    Object objG5 = c0761q.G();
                    if (z15 || objG5 == s6) {
                        final int i16 = 0;
                        l10 = l;
                        str2 = str;
                        objG5 = new InterfaceC2772a() { // from class: Q5.v1
                            @Override // z7.InterfaceC2772a
                            public final Object a() {
                                switch (i16) {
                                    case 0:
                                        interfaceC2785n.invoke(str2, 0L);
                                        break;
                                    default:
                                        interfaceC2785n.invoke(str2, 0L);
                                        break;
                                }
                                return m7.q.f19267a;
                            }
                        };
                        c0761q.a0(objG5);
                    } else {
                        l10 = l;
                        str2 = str;
                    }
                    z11 = false;
                    c0761q.p(false);
                    z12 = true;
                    h6.n.a(false, (InterfaceC2772a) objG5, c0761q, 0, 1);
                }
                c0761q.p(z11);
                g0.o oVarJ = androidx.compose.foundation.layout.a.j(lVar, l10);
                c0761q.Q(1104402130);
                boolean z16 = (i14 == 2048 ? z12 : false) | (i11 == 4 ? z12 : false);
                Object objG6 = c0761q.G();
                if (z16 || objG6 == s6) {
                    final int i17 = 1;
                    objG6 = new InterfaceC2772a() { // from class: Q5.v1
                        @Override // z7.InterfaceC2772a
                        public final Object a() {
                            switch (i17) {
                                case 0:
                                    interfaceC2785n.invoke(str2, 0L);
                                    break;
                                default:
                                    interfaceC2785n.invoke(str2, 0L);
                                    break;
                            }
                            return m7.q.f19267a;
                        }
                    };
                    c0761q.a0(objG6);
                }
                c0761q.p(false);
                H7.H.b(oVarJ, null, false, (InterfaceC2772a) objG6, c0761q, 0, 6);
                c0761q.p(false);
            } else if (!list.isEmpty()) {
                c0761q.Q(-123180948);
                int i18 = (i12 & 112) | ((i12 << 6) & 896);
                int i19 = i12 >> 3;
                d.j(null, list, str, interfaceC2785n2, interfaceC2782k, interfaceC2785n3, gVar, l, c0761q, i18 | (i19 & 7168) | (57344 & i19) | (458752 & i19) | (3670016 & i19) | (i19 & 29360128));
                c0761q.p(false);
            } else if (Q8.h.M0(str)) {
                c0761q.Q(-122365617);
                c0761q.p(false);
            } else {
                c0761q.Q(-122791340);
                float fH = AbstractC2673a.H(l, c0761q);
                float fI = AbstractC2673a.I(l, c0761q);
                c0761q.Q(1104423047);
                if (c0761q.k(AbstractC1692D.f18238a) == LayoutMode.WIDE_ULTRA) {
                    z10 = false;
                    fJ = 0;
                } else {
                    z10 = false;
                    fJ = AbstractC2673a.J(l, c0761q);
                }
                float f10 = fJ;
                c0761q.p(z10);
                C7.a.b(androidx.compose.foundation.layout.a.o(lVar, f10, 0.0f, fI, fH, 2), AbstractC2673a.X(R.string.Explore_EmptyTitle, c0761q), AbstractC2673a.X(R.string.Explore_EmptyMessage, c0761q), null, null, c0761q, 0, 24);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0634w1(str, list, loadingInterface$LoadingState, interfaceC2785n, interfaceC2785n2, interfaceC2782k, interfaceC2785n3, gVar, l, z6, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void o(int r30, T.C0761q r31, g0.o r32, java.lang.String r33, java.lang.String r34, java.lang.String r35, z7.InterfaceC2772a r36, boolean r37, boolean r38, boolean r39) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 788
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.o(int, T.q, g0.o, java.lang.String, java.lang.String, java.lang.String, z7.a, boolean, boolean, boolean):void");
    }

    public static final void p(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        m.f(karaokeItem, "item");
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(1432952466);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean bool = Boolean.TRUE;
            o(((i10 << 15) & 29360128) | (i10 & 14), c0761q, oVar, karaokeItem.c, karaokeItem.f13108b, karaokeItem.d, interfaceC2772a, m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), m.a(karaokeItem.o, bool));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new V2(oVar, karaokeItem, interfaceC2772a, i7, 1);
        }
    }

    public static boolean p0(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static final void q(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        Integer num;
        m.f(interfaceC2772a, "onRemoveClicked");
        m.f(interfaceC2772a2, "onTileClicked");
        c0761q.S(-543160302);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            ScoringDetail scoringDetail = karaokeItem.f13122t;
            C1966G c1966g = null;
            if (scoringDetail != null && (num = scoringDetail.f13198a) != null) {
                int iIntValue = num.intValue();
                Float f10 = scoringDetail.f13199b;
                if (f10 != null) {
                    float fFloatValue = f10.floatValue();
                    Integer num2 = scoringDetail.c;
                    if (num2 != null) {
                        int iIntValue2 = num2.intValue();
                        Float f11 = scoringDetail.d;
                        if (f11 != null) {
                            c1966g = new C1966G(iIntValue, fFloatValue, iIntValue2, f11.floatValue());
                        }
                    }
                }
            }
            Boolean bool = Boolean.TRUE;
            boolean zA = m.a(karaokeItem.f13117n, bool);
            boolean zA2 = m.a(karaokeItem.f13116m, bool);
            boolean zA3 = m.a(karaokeItem.o, bool);
            int i11 = i10 & 14;
            int i12 = i10 << 12;
            r(oVar, karaokeItem.c, karaokeItem.f13108b, karaokeItem.d, zA, zA2, interfaceC2772a, interfaceC2772a2, c1966g, zA3, c0761q, (i12 & 29360128) | i11 | (3670016 & i12) | 134217728);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(oVar, karaokeItem, interfaceC2772a, interfaceC2772a2, i7, 2);
        }
    }

    public static int q0(int i7, int i10, float f10) {
        return y1.a.f(y1.a.h(i10, Math.round(Color.alpha(i10) * f10)), i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x032b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void r(final g0.o r39, final java.lang.String r40, final java.lang.String r41, final java.lang.String r42, final boolean r43, final boolean r44, final z7.InterfaceC2772a r45, final z7.InterfaceC2772a r46, final n6.C1966G r47, final boolean r48, T.C0761q r49, final int r50) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1195
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.r(g0.o, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, z7.a, z7.a, n6.G, boolean, T.q, int):void");
    }

    public static String r0(String str, Object... objArr) {
        int iIndexOf;
        String string;
        int i7 = 0;
        for (int i10 = 0; i10 < objArr.length; i10++) {
            Object obj = objArr[i10];
            if (obj == null) {
                string = "null";
            } else {
                try {
                    string = obj.toString();
                } catch (Exception e10) {
                    String str2 = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str2, (Throwable) e10);
                    StringBuilder sbJ = AbstractC1347d.j("<", str2, " threw ");
                    sbJ.append(e10.getClass().getName());
                    sbJ.append(">");
                    string = sbJ.toString();
                }
            }
            objArr[i10] = string;
        }
        StringBuilder sb = new StringBuilder((objArr.length * 16) + str.length());
        int i11 = 0;
        while (i7 < objArr.length && (iIndexOf = str.indexOf("%s", i11)) != -1) {
            sb.append((CharSequence) str, i11, iIndexOf);
            sb.append(objArr[i7]);
            i11 = iIndexOf + 2;
            i7++;
        }
        sb.append((CharSequence) str, i11, str.length());
        if (i7 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i7]);
            for (int i12 = i7 + 1; i12 < objArr.length; i12++) {
                sb.append(", ");
                sb.append(objArr[i12]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static final void s(int i7, C0761q c0761q, g0.o oVar, String str, String str2, String str3, InterfaceC2772a interfaceC2772a, boolean z6, boolean z10, boolean z11) {
        int i10;
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(1580147946);
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
        if ((196608 & i7) == 0) {
            i10 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.g(z11) ? 8388608 : 4194304;
        }
        if ((4793491 & i10) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(815150812);
            boolean z12 = (i10 & 3670016) == 1048576;
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (z12 || objG == obj) {
                objG = new C0549b(interfaceC2772a, 15);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarP = P(oVar, false, null, null, (InterfaceC2772a) objG, 15);
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new p(bVar);
                c0761q.a0(objG2);
            }
            p pVar = (p) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = new C1283j();
                c0761q.a0(objG3);
            }
            C1283j c1283j = (C1283j) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG4);
            }
            X x8 = (X) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = new C1285l(c1283j);
                c0761q.a0(objG5);
            }
            C1285l c1285l = (C1285l) objG5;
            Object objG6 = c0761q.G();
            if (objG6 == obj) {
                objG6 = C0735d.O(m7.q.f19267a, S.f8222n);
                c0761q.a0(objG6);
            }
            X x9 = (X) objG6;
            boolean zD = c0761q.d(257) | c0761q.h(pVar);
            Object objG7 = c0761q.G();
            if (zD || objG7 == obj) {
                objG7 = new M1(x9, pVar, c1285l, x8, 4);
                c0761q.a0(objG7);
            }
            C0.K k10 = (C0.K) objG7;
            Object objG8 = c0761q.G();
            if (objG8 == obj) {
                objG8 = new N1(x8, c1285l, 4);
                c0761q.a0(objG8);
            }
            InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG8;
            boolean zH = c0761q.h(pVar);
            Object objG9 = c0761q.G();
            if (zH || objG9 == obj) {
                objG9 = new O1(pVar, 4);
                c0761q.a0(objG9);
            }
            c0.a(L0.k.a(oVarP, false, (InterfaceC2782k) objG9), b0.g.b(1200550679, new f3(x9, c1283j, interfaceC2772a2, z6, z10, str, z11, str2, str3), c0761q), k10, c0761q, 48);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W2(oVar, str, str2, str3, z6, z10, interfaceC2772a, z11, i7);
        }
    }

    public static final void t(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        m.f(interfaceC2772a, "onTileClicked");
        c0761q.S(477385428);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean bool = Boolean.TRUE;
            s(((i10 << 12) & 3670016) | (i10 & 14), c0761q, oVar, karaokeItem.c, karaokeItem.f13108b, karaokeItem.d, interfaceC2772a, m.a(karaokeItem.f13117n, bool), m.a(karaokeItem.f13116m, bool), m.a(karaokeItem.o, bool));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new V2(oVar, karaokeItem, interfaceC2772a, i7, 3);
        }
    }

    public static final void u(g0.o oVar, String str, R2 r22, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        R2 r23;
        long j3;
        c0761q.S(1446714611);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        int i11 = i10 | 384;
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            r23 = r22;
        } else {
            r23 = R2.l;
            int iOrdinal = r23.ordinal();
            if (iOrdinal == 0) {
                c0761q.Q(415110600);
                j3 = l6.d.c(c0761q).f18696b;
                c0761q.p(false);
            } else {
                if (iOrdinal != 1) {
                    throw M5.b.d(415108550, c0761q, false);
                }
                c0761q.Q(415112295);
                j3 = l6.d.c(c0761q).f18702f;
                c0761q.p(false);
            }
            long j10 = j3;
            c0761q.Q(415113570);
            long j11 = C1891t.f19451j;
            c0761q.p(false);
            c0761q.Q(415118610);
            long j12 = l6.d.c(c0761q).f18672E;
            c0761q.p(false);
            g0.o oVarD = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(oVar, 1.0f), 1.0f);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarB = androidx.compose.foundation.a.b(h6.i.c(O(q.m(oVarD, J.e.a(bVar.f0(c0761q))), interfaceC2772a), 2, j11, J.e.a(bVar.f0(c0761q))), j12, AbstractC1861K.f19381a);
            C0.K kE = AbstractC0133o.e(g0.b.l, false);
            int i12 = c0761q.f8304P;
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            c1.b(str, androidx.compose.foundation.layout.b.f11474a.a(androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.d(g0.l.f14684a, 1.0f), bVar.F(c0761q), 0.0f, 2), g0.b.f14664p), j10, h6.f.g(FontSize.H9, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, (i11 >> 3) & 14, 0, 65008);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(oVar, str, r23, interfaceC2772a, i7, 3);
        }
    }

    public static final void v(g0.o oVar, String str, boolean z6, AbstractC2268a abstractC2268a, C0761q c0761q, int i7) throws XmlPullParserException, IOException {
        int i10;
        AbstractC2268a abstractC2268aW;
        AbstractC2268a abstractC2268a2;
        c0761q.S(757374561);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            abstractC2268a2 = abstractC2268a;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                abstractC2268aW = l9.l.W(R.drawable.default_tile, c0761q, 0);
            } else {
                c0761q.K();
                abstractC2268aW = abstractC2268a;
            }
            c0761q.q();
            C0.K kE = AbstractC0133o.e(g0.b.l, false);
            int i11 = c0761q.f8304P;
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
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.o oVarM = q.m(androidx.compose.foundation.layout.c.c(g0.l.f14684a, 1.0f), J.e.a(m6.c.f19252a.f0(c0761q)));
            O o = C0102i.f902a;
            if (str != null) {
                c0761q.Q(-849638665);
                K3.h hVar = new K3.h((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b));
                hVar.c = str;
                hVar.f3533g = new M3.a(100);
                A3.p.b(hVar.a(), oVarM, null, abstractC2268aW, c0761q, 48, 64488);
                c0761q.p(false);
            } else {
                c0761q.Q(-849251258);
                q.a(l9.l.W(R.drawable.default_tile, c0761q, 0), null, oVarM, null, o, 0.0f, null, c0761q, 24624, 104);
                c0761q.p(false);
            }
            c0761q.Q(2050823187);
            c0761q.p(false);
            c0761q.p(true);
            abstractC2268a2 = abstractC2268aW;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.r(oVar, str, z6, abstractC2268a2, i7, 1);
        }
    }

    public static final void w(g0.o oVar, String str, long j3, N0.L l, C0761q c0761q, int i7) {
        int i10;
        int i11;
        N0.L l10;
        long j10;
        long j11;
        N0.L l11;
        c0761q.S(1192872844);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.d(5) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            j11 = j3;
            l11 = l;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                long j12 = l6.d.c(c0761q).c;
                i11 = i10 & (-64513);
                l10 = ((O5.d) c0761q.k(O5.c.f5157b)).f5159b;
                j10 = j12;
            } else {
                c0761q.K();
                i11 = i10 & (-64513);
                j10 = j3;
                l10 = l;
            }
            c0761q.q();
            if (str != null) {
                c1.b(str, oVar, j10, h6.f.g(FontSize.Subtitle1, c0761q), null, null, null, 0L, null, new Y0.i(5), 0L, 2, true, 1, 0, null, l10, c0761q, ((i11 >> 3) & 14) | ((i11 << 3) & 112) | ((i11 << 21) & 1879048192), 3504, 50672);
            }
            j11 = j10;
            l11 = l10;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new K0(oVar, str, j11, l11, i7, 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void x(final g0.o r29, final java.lang.String r30, long r31, int r33, T.C0761q r34, final int r35, final int r36) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.c.x(g0.o, java.lang.String, long, int, T.q, int, int):void");
    }

    public static final void y(int i7, C0761q c0761q, g0.o oVar, boolean z6) {
        int i10;
        c0761q.S(954069006);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-445105624);
            boolean z10 = z6 && !((Boolean) c0761q.k(s.f18295a)).booleanValue();
            c0761q.p(false);
            if (z10) {
                c0761q.Q(-913318793);
                z(oVar, AbstractC2673a.X(R.string.ParentalControls_IsEnabled, c0761q), R.drawable.ic_lock, c0761q, i10 & 14);
                c0761q.p(false);
            } else {
                c0761q.Q(-913135490);
                z(oVar, AbstractC2673a.X(R.string.Global_Unavailable, c0761q), R.drawable.ic_block, c0761q, i10 & 14);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Y0(oVar, z6, i7);
        }
    }

    public static final Object y0(Set set, Enum r22, Enum r32, Enum r42, boolean z6) {
        if (!z6) {
            if (r42 != null) {
                set = AbstractC1998m.D0(AbstractC1985B.r(set, r42));
            }
            return AbstractC1998m.s0(set);
        }
        Enum r12 = set.contains(r22) ? r22 : set.contains(r32) ? r32 : null;
        if (m.a(r12, r22) && m.a(r42, r32)) {
            return null;
        }
        return r42 == null ? r12 : r42;
    }

    public static final void z(g0.o oVar, String str, int i7, C0761q c0761q, int i10) {
        c0761q.Q(-1645276145);
        g0.o oVarD = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(oVar, 1.0f), 1.0f);
        m6.b bVar = m6.c.f19252a;
        g0.o oVarM = q.m(oVarD, J.e.a(bVar.f0(c0761q)));
        c0761q.Q(-380055767);
        c0761q.Q(1385129319);
        List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.d.c(c0761q).f18718t)), new m7.i(Float.valueOf(1.0f), new C1891t(l6.d.c(c0761q).f18719u)));
        c0761q.p(false);
        m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
        C1853C c1853cN = C1886o.n((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
        c0761q.p(false);
        g0.o oVarM2 = androidx.compose.foundation.layout.a.m(androidx.compose.foundation.a.a(oVarM, c1853cN, null, 6), bVar.F(c0761q), 0.0f, 2);
        C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.f1078e, g0.b.f14673y, c0761q, 54);
        int i11 = c0761q.f8304P;
        InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
        g0.o oVarD2 = g0.a.d(c0761q, oVarM2);
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
        if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
            AbstractC1347d.l(i11, c0761q, i11, c0158h);
        }
        C0735d.V(c0761q, oVarD2, C0160j.c);
        g0.l lVar = g0.l.f14684a;
        AbstractC0120b.b(c0761q, lVar);
        AbstractC0535v.a(l9.l.W(i7, c0761q, (i10 >> 6) & 14), null, androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.d(lVar, 0.3f), 1.0f), l6.d.c(c0761q).c, c0761q, 432, 0);
        c1.b(str, androidx.compose.foundation.layout.c.d(lVar, 1.0f), l6.d.c(c0761q).f18696b, h6.f.g(FontSize.H9, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).d, c0761q, ((i10 >> 3) & 14) | 48, 0, 65008);
        AbstractC0120b.b(c0761q, lVar);
        c0761q.p(true);
        c0761q.p(false);
    }

    public abstract void A0(boolean z6);

    public abstract boolean E0(View view, int i7);

    public abstract boolean I(AbstractC1252g abstractC1252g, C1248c c1248c, C1248c c1248c2);

    public abstract boolean J(AbstractC1252g abstractC1252g, Object obj, Object obj2);

    public abstract boolean K(AbstractC1252g abstractC1252g, C1251f c1251f, C1251f c1251f2);

    public abstract int M(View view, int i7);

    public abstract int N(View view, int i7);

    public abstract void b0(t tVar, float f10, float f11);

    public int k0(View view) {
        return 0;
    }

    public int l0() {
        return 0;
    }

    public abstract boolean n0();

    public abstract boolean o0();

    public void s0(View view, int i7) {
    }

    public abstract void t0(int i7);

    public abstract void u0(View view, int i7, int i10);

    public abstract void v0(View view, float f10, float f11);

    public abstract void w0(C1251f c1251f, C1251f c1251f2);

    public abstract void x0(C1251f c1251f, Thread thread);

    public abstract void z0(boolean z6);
}
