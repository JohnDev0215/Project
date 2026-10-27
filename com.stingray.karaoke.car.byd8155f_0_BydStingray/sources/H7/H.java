package H7;

import A.C0044v0;
import B5.L;
import D.AbstractC0133o;
import D.InterfaceC0123e;
import D.InterfaceC0125g;
import E.C0147d;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F.C0190a;
import F0.AbstractC0227s0;
import F0.W0;
import K.C0331b0;
import K.G0;
import N0.J;
import N1.InterfaceC0428i;
import Q5.C0551b1;
import Q5.C0598n0;
import Q5.F0;
import Q5.U2;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0666t;
import S0.InterfaceC0702n;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import T.X;
import T7.Q;
import a.AbstractC0904a;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemElement;
import androidx.compose.ui.ZIndexElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.h0;
import androidx.lifecycle.l0;
import b8.C1011f;
import c6.C1034a;
import c9.C1051l;
import c9.K;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import h2.AbstractC1347d;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import n0.C1863M;
import n3.C1930E;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import q7.InterfaceC2189c;
import w.AbstractC2565A;
import x.AbstractC2631e;
import x.L0;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f2848a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f2849b = 0;
    public static final /* synthetic */ int c = 0;
    public static final /* synthetic */ int d = 0;

    public static c9.t A(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        List listL = n7.u.l;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if (cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") ? true : cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        C1051l c1051lC = C1051l.f12571b.c(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        K kA0 = io.sentry.config.a.a0(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
        }
        List listL2 = peerCertificates != null ? d9.b.l(Arrays.copyOf(peerCertificates, peerCertificates.length)) : listL;
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            listL = d9.b.l(Arrays.copyOf(localCertificates, localCertificates.length));
        }
        return new c9.t(kA0, c1051lC, listL, new c9.s(1, listL2));
    }

    public static final Type B(A a10) {
        B b10 = a10.f2839a;
        if (b10 == null) {
            return I.f2850n;
        }
        x xVar = a10.f2840b;
        A7.m.c(xVar);
        int iOrdinal = b10.ordinal();
        if (iOrdinal == 0) {
            return r(xVar, true);
        }
        if (iOrdinal == 1) {
            return new I(null, r(xVar, true));
        }
        if (iOrdinal == 2) {
            return new I(r(xVar, true), null);
        }
        throw new A9.k();
    }

    public static final int C(N0.o oVar, long j3, W0 w02) {
        float f10 = w02 != null ? w02.f() : 0.0f;
        int iC = oVar.c(m0.c.e(j3));
        if (m0.c.e(j3) < oVar.d(iC) - f10 || m0.c.e(j3) > oVar.b(iC) + f10 || m0.c.d(j3) < (-f10) || m0.c.d(j3) > oVar.d + f10) {
            return -1;
        }
        return iC;
    }

    public static final Object D(L0.j jVar, L0.u uVar) {
        Object obj = jVar.l.get(uVar);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public static final long E(C0331b0 c0331b0, m0.d dVar, int i7) {
        G0 g0D = c0331b0.d();
        N0.o oVar = g0D != null ? g0D.f3161a.f4349b : null;
        C0.r rVarC = c0331b0.c();
        return (oVar == null || rVarC == null) ? N0.K.f4356b : oVar.f(dVar.h(rVarC.B(0L)), i7, N0.F.f4339b);
    }

    public static boolean F(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static boolean G(L l, String str) {
        if (D5.g.h(l) || "Segment.io".equals(str)) {
            return true;
        }
        if (l.l.containsKey(str)) {
            return l.b(str);
        }
        if (l.l.containsKey("All")) {
            return l.b("All");
        }
        return true;
    }

    public static final boolean H(int i7) {
        int type = Character.getType(i7);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    public static final boolean I(int i7) {
        return Character.isWhitespace(i7) || i7 == 160;
    }

    public static final boolean J(int i7) {
        int type;
        return (!I(i7) || (type = Character.getType(i7)) == 14 || type == 13 || i7 == 10) ? false : true;
    }

    public static final O8.g K(ArrayList arrayList) {
        O8.g gVar = new O8.g();
        for (Object obj : arrayList) {
            y8.n nVar = (y8.n) obj;
            if (nVar != null && nVar != y8.m.f24213b) {
                gVar.add(obj);
            }
        }
        return gVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x011d, code lost:
    
        if (v8.AbstractC2564d.g(r0).equals(v8.AbstractC2564d.g(r3)) == false) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static h8.l L(Q7.InterfaceC0666t r8, T7.Q r9) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.L(Q7.t, T7.Q):h8.l");
    }

    public static final J M(C0761q c0761q) {
        InterfaceC0702n interfaceC0702n = (InterfaceC0702n) c0761q.k(AbstractC0227s0.f2262i);
        a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
        a1.k kVar = (a1.k) c0761q.k(AbstractC0227s0.l);
        boolean zF = c0761q.f(interfaceC0702n) | c0761q.f(bVar) | c0761q.f(kVar) | c0761q.d(8);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new J(interfaceC0702n, bVar, kVar, 8);
            c0761q.a0(objG);
        }
        return (J) objG;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object O(A.K0 r5, float r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof A.C0029n0
            if (r0 == 0) goto L13
            r0 = r7
            A.n0 r0 = (A.C0029n0) r0
            int r1 = r0.f215n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f215n = r1
            goto L18
        L13:
            A.n0 r0 = new A.n0
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f214m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f215n
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            A7.w r5 = r0.l
            h8.AbstractC1386c.u(r7)
            goto L4d
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            h8.AbstractC1386c.u(r7)
            A7.w r7 = new A7.w
            r7.<init>()
            A.o0 r2 = new A.o0
            r4 = 0
            r2.<init>(r7, r6, r4)
            r0.l = r7
            r0.f215n = r3
            y.j0 r6 = y.j0.l
            java.lang.Object r5 = r5.e(r6, r2, r0)
            if (r5 != r1) goto L4c
            return r1
        L4c:
            r5 = r7
        L4d:
            float r5 = r5.l
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.O(A.K0, float, s7.c):java.lang.Object");
    }

    public static void P(EditorInfo editorInfo, CharSequence charSequence) {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30) {
            B1.a.f(editorInfo, charSequence);
            return;
        }
        charSequence.getClass();
        if (i7 >= 30) {
            B1.a.f(editorInfo, charSequence);
            return;
        }
        int i10 = editorInfo.initialSelStart;
        int i11 = editorInfo.initialSelEnd;
        int i12 = i10 > i11 ? i11 : i10;
        if (i10 <= i11) {
            i10 = i11;
        }
        int length = charSequence.length();
        if (i12 < 0 || i10 > length) {
            Q(editorInfo, null, 0, 0);
            return;
        }
        int i13 = editorInfo.inputType & 4095;
        if (i13 == 129 || i13 == 225 || i13 == 18) {
            Q(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            Q(editorInfo, charSequence, i12, i10);
            return;
        }
        int i14 = i10 - i12;
        int i15 = i14 > 1024 ? 0 : i14;
        int i16 = 2048 - i15;
        int iMin = Math.min(charSequence.length() - i10, i16 - Math.min(i12, (int) (((double) i16) * 0.8d)));
        int iMin2 = Math.min(i12, i16 - iMin);
        int i17 = i12 - iMin2;
        if (Character.isLowSurrogate(charSequence.charAt(i17))) {
            i17++;
            iMin2--;
        }
        if (Character.isHighSurrogate(charSequence.charAt((i10 + iMin) - 1))) {
            iMin--;
        }
        int i18 = iMin2 + i15;
        Q(editorInfo, i15 != i14 ? TextUtils.concat(charSequence.subSequence(i17, i17 + iMin2), charSequence.subSequence(i10, iMin + i10)) : charSequence.subSequence(i17, i18 + iMin + i17), iMin2, i18);
    }

    public static void Q(EditorInfo editorInfo, CharSequence charSequence, int i7, int i10) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i7);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i10);
    }

    public static String R(String str) {
        int length = str.length();
        int i7 = 0;
        while (i7 < length) {
            char cCharAt = str.charAt(i7);
            if (cCharAt >= 'A' && cCharAt <= 'Z') {
                char[] charArray = str.toCharArray();
                while (i7 < length) {
                    char c10 = charArray[i7];
                    if (c10 >= 'A' && c10 <= 'Z') {
                        charArray[i7] = (char) (c10 ^ ' ');
                    }
                    i7++;
                }
                return String.valueOf(charArray);
            }
            i7++;
        }
        return str;
    }

    public static final long S(long j3) {
        return h6.j.f((int) (j3 >> 32), (int) (j3 & 4294967295L));
    }

    public static String T(String str) {
        int length = str.length();
        int i7 = 0;
        while (i7 < length) {
            char cCharAt = str.charAt(i7);
            if (cCharAt >= 'a' && cCharAt <= 'z') {
                char[] charArray = str.toCharArray();
                while (i7 < length) {
                    char c10 = charArray[i7];
                    if (c10 >= 'a' && c10 <= 'z') {
                        charArray[i7] = (char) (c10 ^ ' ');
                    }
                    i7++;
                }
                return String.valueOf(charArray);
            }
            i7++;
        }
        return str;
    }

    public static final void a(g0.o oVar, final float f10, b0.b bVar, C0761q c0761q, final int i7) {
        final g0.o oVar2;
        final b0.b bVar2;
        g0.f fVar = g0.b.f14670v;
        c0761q.S(2124200644);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(fVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(bVar) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
            bVar2 = bVar;
        } else {
            oVar2 = g0.l.f14684a;
            C0.K kE = AbstractC0133o.e(g0.b.f14664p, false);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar2);
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
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            c0761q.Q(1501729235);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.M(0);
                c0761q.a0(objG);
            }
            C0732b0 c0732b0 = (C0732b0) objG;
            Object objE = M5.b.e(1501731219, c0761q, false);
            if (objE == s6) {
                objE = C0735d.M(0);
                c0761q.a0(objE);
            }
            C0732b0 c0732b02 = (C0732b0) objE;
            c0761q.p(false);
            float density = ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).getDensity();
            c0761q.Q(1501739052);
            boolean zC = ((i10 & 896) == 256) | ((i10 & 112) == 32) | c0761q.c(density);
            Object objG2 = c0761q.G();
            if (zC || objG2 == s6) {
                objG2 = new C0551b1(f10, density, c0732b02, c0732b0);
                c0761q.a0(objG2);
            }
            C0.K k10 = (C0.K) objG2;
            c0761q.p(false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVar2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, k10, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            bVar2 = bVar;
            bVar2.invoke(c0761q, Integer.valueOf((i10 >> 9) & 14));
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.Z0
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    b0.b bVar3 = bVar2;
                    H7.H.a(oVar2, f10, bVar3, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(g0.o r44, java.lang.String r45, boolean r46, z7.InterfaceC2772a r47, T.C0761q r48, int r49, int r50) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.b(g0.o, java.lang.String, boolean, z7.a, T.q, int, int):void");
    }

    public static final long c(int i7, int i10) {
        return (((long) i10) & 4294967295L) | (((long) i7) << 32);
    }

    public static final void d(C0190a c0190a, g0.o oVar, F.y yVar, D.L l, boolean z6, InterfaceC0125g interfaceC0125g, InterfaceC0123e interfaceC0123e, A.r rVar, boolean z10, C0598n0 c0598n0, C0761q c0761q, int i7) {
        int i10;
        int i11;
        A.r rVarC;
        boolean z11;
        boolean z12;
        g0.o oVar2;
        g0.o oVar3;
        boolean z13;
        A.r rVar2;
        boolean z14;
        c0761q.S(1485410512);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c0190a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        int i12 = i10 | 48;
        if ((i7 & 384) == 0) {
            i12 |= c0761q.f(yVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i12 |= c0761q.f(l) ? 2048 : 1024;
        }
        int i13 = i12 | 24576;
        if ((i7 & 196608) == 0) {
            i13 |= c0761q.f(interfaceC0125g) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i13 |= c0761q.f(interfaceC0123e) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i13 |= 4194304;
        }
        int i14 = i13 | 100663296;
        if ((805306368 & i7) == 0) {
            i14 |= c0761q.h(c0598n0) ? 536870912 : 268435456;
        }
        if ((306783379 & i14) == 306783378 && c0761q.x()) {
            c0761q.K();
            oVar3 = oVar;
            z13 = z6;
            rVar2 = rVar;
            z14 = z10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                g0.l lVar = g0.l.f14684a;
                i11 = i14 & (-29360129);
                rVarC = AbstractC0904a.C(c0761q);
                z11 = false;
                z12 = true;
                oVar2 = lVar;
            } else {
                c0761q.K();
                i11 = i14 & (-29360129);
                oVar2 = oVar;
                z11 = z6;
                rVarC = rVar;
                z12 = z10;
            }
            c0761q.q();
            int i15 = i11 >> 3;
            int i16 = (i11 & 14) | ((i11 >> 15) & 112) | (i15 & 896);
            boolean z15 = ((((i16 & 14) ^ 6) > 4 && c0761q.f(c0190a)) || (i16 & 6) == 4) | ((((i16 & 112) ^ 48) > 32 && c0761q.f(interfaceC0123e)) || (i16 & 48) == 32) | ((((i16 & 896) ^ 384) > 256 && c0761q.f(l)) || (i16 & 384) == 256);
            Object objG = c0761q.G();
            if (z15 || objG == C0753m.f8270a) {
                objG = new F.c(new C0044v0(l, c0190a, interfaceC0123e, 1));
                c0761q.a0(objG);
            }
            int i17 = i11 << 9;
            AbstractC0904a.f(oVar2, yVar, (F.c) objG, l, z11, rVarC, z12, interfaceC0125g, interfaceC0123e, c0598n0, c0761q, (i15 & 14) | 196608 | (i15 & 112) | (i11 & 7168) | (57344 & i11) | (29360128 & i15) | (234881024 & i17) | (i17 & 1879048192), (i11 >> 27) & 14);
            oVar3 = oVar2;
            z13 = z11;
            rVar2 = rVarC;
            z14 = z12;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new F.e(c0190a, oVar3, yVar, l, z13, interfaceC0125g, interfaceC0123e, rVar2, z14, c0598n0, i7);
        }
    }

    public static final void e(f6.h hVar, f6.g gVar, e6.h hVar2, C1034a c1034a, f6.o oVar, C0761q c0761q, int i7) {
        int i10;
        boolean z6;
        A7.m.f(hVar, "mainViewModel");
        A7.m.f(gVar, "homeViewModel");
        A7.m.f(hVar2, "exploreViewModel");
        A7.m.f(c1034a, "companionViewModel");
        A7.m.f(oVar, "myKaraokeViewModel");
        c0761q.S(17853122);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(hVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(gVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(hVar2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(c1034a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(oVar) ? 16384 : 8192;
        }
        int i11 = i10;
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            NavigationType navigationType = hVar.d;
            W5.e eVar = hVar.f12234b;
            X xW = C0735d.w(eVar.b(navigationType).f19799D, null, null, c0761q, 48, 2);
            Object value = xW.getValue();
            c0761q.Q(-1688877327);
            boolean zF = c0761q.f(xW) | c0761q.h(hVar);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (zF || objG == obj) {
                objG = new W5.k(xW, hVar, null);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            C0735d.f(c0761q, value, (InterfaceC2785n) objG);
            C1930E c1930eB = eVar.b(hVar.d);
            String value2 = NavigationRoutes.TABS.getValue();
            c0761q.Q(-1688860167);
            boolean zH = c0761q.h(hVar) | c0761q.h(gVar) | ((i11 & 896) == 256) | c0761q.h(c1034a) | c0761q.h(oVar);
            Object objG2 = c0761q.G();
            if (zH || objG2 == obj) {
                z6 = false;
                Object f02 = new F0(hVar, gVar, hVar2, c1034a, oVar, 2);
                c0761q.a0(f02);
                objG2 = f02;
            } else {
                z6 = false;
            }
            c0761q.p(z6);
            h6.q.b(c1930eB, value2, null, null, null, null, null, null, null, null, (InterfaceC2782k) objG2, c0761q, 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W5.j(hVar, gVar, hVar2, c1034a, oVar, i7, 0);
        }
    }

    public static final void f(PlayerStatus playerStatus, InterfaceC2782k interfaceC2782k, SideMenuType sideMenuType, C1930E c1930e, boolean z6, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(playerStatus, "playerStatus");
        A7.m.f(interfaceC2782k, "onCloseSideMenu");
        c0761q.S(379809260);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(playerStatus) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(sideMenuType) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(c1930e) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean z10 = sideMenuType != null;
            g0.e eVar = g0.b.f14674z;
            androidx.compose.animation.a.c(z10, null, z6 ? AbstractC2565A.a(eVar, 13) : AbstractC2565A.b(null, 3), z6 ? AbstractC2565A.d(eVar, 13) : AbstractC2565A.c(null, 3), null, b0.g.b(459410628, new M5.g(sideMenuType, c1930e, z6, playerStatus, interfaceC2782k), c0761q), c0761q, 196608, 18);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new U2(playerStatus, interfaceC2782k, sideMenuType, c1930e, z6, i7);
        }
    }

    public static final void g(C0147d c0147d, P5.p pVar, String str, g0.o oVar, Integer num, boolean z6, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar2;
        boolean z10;
        Integer num2;
        A7.m.f(c0147d, "<this>");
        c0761q.S(-865288376);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c0147d) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(pVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(str) ? 256 : 128;
        }
        int i11 = i10 | 224256;
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(bVar) ? 1048576 : 524288;
        }
        if ((599187 & i11) == 599186 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
            num2 = num;
            z10 = z6;
        } else {
            g0.l lVar = g0.l.f14684a;
            Object obj = L0.f23437a;
            h(pVar, str, lVar, new LazyLayoutAnimateItemElement(AbstractC2631e.p(400.0f, 1, new a1.h(AbstractC2673a.d(1, 1)))), true, null, bVar, c0761q, ((i11 >> 3) & 58366) | (458752 & (i11 << 3)) | (i11 & 3670016));
            oVar2 = lVar;
            z10 = true;
            num2 = null;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new P5.h(c0147d, pVar, str, oVar2, num2, z10, bVar, i7);
        }
    }

    public static final void h(final P5.p pVar, String str, g0.o oVar, g0.o oVar2, final boolean z6, Integer num, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar3;
        boolean zA;
        boolean zA2;
        boolean z10;
        g0.o oVarA;
        c0761q.S(1634784918);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(pVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            oVar3 = oVar2;
            i10 |= c0761q.f(oVar3) ? 2048 : 1024;
        } else {
            oVar3 = oVar2;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(num) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(bVar) ? 1048576 : 524288;
        }
        if ((599187 & i10) == 599186 && c0761q.x()) {
            c0761q.K();
        } else {
            if (num != null) {
                zA = num.equals(pVar.f());
            } else {
                Object value = pVar.f5388k.getValue();
                zA = A7.m.a(str, value != null ? pVar.l(value) : null);
            }
            S s6 = C0753m.f8270a;
            if (zA) {
                c0761q.Q(2021750495);
                ZIndexElement zIndexElement = new ZIndexElement();
                c0761q.Q(1312146279);
                boolean zH = ((i10 & 57344) == 16384) | c0761q.h(pVar);
                Object objG = c0761q.G();
                if (zH || objG == s6) {
                    final int i11 = 0;
                    objG = new InterfaceC2782k() { // from class: P5.i
                        @Override // z7.InterfaceC2782k
                        public final Object b(Object obj) {
                            C1863M c1863m = (C1863M) obj;
                            switch (i11) {
                                case 0:
                                    A7.m.f(c1863m, "$this$graphicsLayer");
                                    boolean z11 = z6;
                                    p pVar2 = pVar;
                                    c1863m.r((z11 && pVar2.q()) ? 0.0f : pVar2.g());
                                    c1863m.u((!z11 || pVar2.q()) ? pVar2.h() : 0.0f);
                                    break;
                                default:
                                    A7.m.f(c1863m, "$this$graphicsLayer");
                                    p pVar3 = pVar;
                                    boolean z12 = z6;
                                    y yVar = pVar3.f5383f;
                                    c1863m.r((z12 && pVar3.q()) ? 0.0f : m0.c.d(((m0.c) yVar.f5409b.d()).f18880a));
                                    c1863m.u((!z12 || pVar3.q()) ? m0.c.e(((m0.c) yVar.f5409b.d()).f18880a) : 0.0f);
                                    break;
                            }
                            return m7.q.f19267a;
                        }
                    };
                    c0761q.a0(objG);
                }
                c0761q.p(false);
                g0.o oVarA2 = androidx.compose.ui.graphics.a.a(zIndexElement, (InterfaceC2782k) objG);
                c0761q.p(false);
                oVarA = oVarA2;
                z10 = false;
            } else {
                c0761q.Q(2022108266);
                P5.y yVar = pVar.f5383f;
                if (num != null) {
                    P5.g gVar = (P5.g) yVar.c.getValue();
                    zA2 = num.equals(gVar != null ? Integer.valueOf(gVar.f5356a) : null);
                } else {
                    P5.g gVar2 = (P5.g) yVar.c.getValue();
                    zA2 = A7.m.a(str, gVar2 != null ? gVar2.f5357b : null);
                }
                if (zA2) {
                    ZIndexElement zIndexElement2 = new ZIndexElement();
                    c0761q.Q(1312165650);
                    boolean zH2 = ((i10 & 57344) == 16384) | c0761q.h(pVar);
                    Object objG2 = c0761q.G();
                    if (zH2 || objG2 == s6) {
                        final int i12 = 1;
                        objG2 = new InterfaceC2782k() { // from class: P5.i
                            @Override // z7.InterfaceC2782k
                            public final Object b(Object obj) {
                                C1863M c1863m = (C1863M) obj;
                                switch (i12) {
                                    case 0:
                                        A7.m.f(c1863m, "$this$graphicsLayer");
                                        boolean z11 = z6;
                                        p pVar2 = pVar;
                                        c1863m.r((z11 && pVar2.q()) ? 0.0f : pVar2.g());
                                        c1863m.u((!z11 || pVar2.q()) ? pVar2.h() : 0.0f);
                                        break;
                                    default:
                                        A7.m.f(c1863m, "$this$graphicsLayer");
                                        p pVar3 = pVar;
                                        boolean z12 = z6;
                                        y yVar2 = pVar3.f5383f;
                                        c1863m.r((z12 && pVar3.q()) ? 0.0f : m0.c.d(((m0.c) yVar2.f5409b.d()).f18880a));
                                        c1863m.u((!z12 || pVar3.q()) ? m0.c.e(((m0.c) yVar2.f5409b.d()).f18880a) : 0.0f);
                                        break;
                                }
                                return m7.q.f19267a;
                            }
                        };
                        c0761q.a0(objG2);
                    }
                    z10 = false;
                    c0761q.p(false);
                    oVarA = androidx.compose.ui.graphics.a.a(zIndexElement2, (InterfaceC2782k) objG2);
                } else {
                    z10 = false;
                    oVarA = oVar3;
                }
                c0761q.p(z10);
            }
            g0.o oVarK = oVar.k(oVarA);
            C0.K kE = AbstractC0133o.e(g0.b.l, z10);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarK);
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
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            bVar.o(androidx.compose.foundation.layout.b.f11474a, Boolean.valueOf(zA), c0761q, Integer.valueOf(((i10 >> 12) & 896) | 6));
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new P5.h(pVar, str, oVar, oVar2, z6, num, bVar, i7);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:60:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void i(java.lang.String r82, int r83, z7.InterfaceC2772a r84, z7.InterfaceC2782k r85, T.C0761q r86, int r87) {
        /*
            Method dump skipped, instruction units count: 1085
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.i(java.lang.String, int, z7.a, z7.k, T.q, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:205:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void j(g0.o r45, java.lang.Integer r46, N0.C0411f r47, boolean r48, final boolean r49, z7.InterfaceC2782k r50, float r51, com.stingray.karaoke.car.utils.FontSize r52, N0.L r53, int r54, T.C0761q r55, final int r56, final int r57) {
        /*
            Method dump skipped, instruction units count: 1040
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.j(g0.o, java.lang.Integer, N0.f, boolean, boolean, z7.k, float, com.stingray.karaoke.car.utils.FontSize, N0.L, int, T.q, int, int):void");
    }

    public static final void k(int i7, List list) {
        int size = list.size();
        if (i7 < 0 || i7 >= size) {
            throw new IndexOutOfBoundsException("Index " + i7 + " is out of bounds. The list has " + size + " elements.");
        }
    }

    public static final void l(int i7, int i10, List list) {
        int size = list.size();
        if (i7 > i10) {
            throw new IllegalArgumentException("Indices are out of order. fromIndex (" + i7 + ") is greater than toIndex (" + i10 + ").");
        }
        if (i7 < 0) {
            throw new IndexOutOfBoundsException(A7.k.g("fromIndex (", i7, ") is less than 0."));
        }
        if (i10 <= size) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i10 + ") is more than than the list size (" + size + ')');
    }

    public static final long m(C0331b0 c0331b0, m0.d dVar, m0.d dVar2, int i7) {
        long jE = E(c0331b0, dVar, i7);
        if (N0.K.b(jE)) {
            return N0.K.f4356b;
        }
        long jE2 = E(c0331b0, dVar2, i7);
        if (N0.K.b(jE2)) {
            return N0.K.f4356b;
        }
        int i10 = (int) (jE >> 32);
        int i11 = (int) (jE2 & 4294967295L);
        return AbstractC0904a.q(Math.min(i10, i10), Math.max(i11, i11));
    }

    public static final boolean n(N0.H h10, int i7) {
        int iF = h10.f(i7);
        if (i7 == h10.i(iF) || i7 == h10.e(iF, false)) {
            if (h10.j(i7) == h10.a(i7)) {
                return false;
            }
        } else if (h10.a(i7) == h10.a(i7 - 1)) {
            return false;
        }
        return true;
    }

    public static final String o(Type type) {
        if (!(type instanceof Class)) {
            return type.toString();
        }
        Class cls = (Class) type;
        if (!cls.isArray()) {
            return cls.getName();
        }
        P8.k kVarD0 = P8.m.d0(type, G.f2847t);
        return ((Class) P8.m.f0(kVarD0)).getName() + Q8.o.v0(P8.m.Z(kVarD0), "[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object p(A.K0 r5, float r6, x.C0 r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof A.C0025l0
            if (r0 == 0) goto L13
            r0 = r8
            A.l0 r0 = (A.C0025l0) r0
            int r1 = r0.f203n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f203n = r1
            goto L18
        L13:
            A.l0 r0 = new A.l0
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f202m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f203n
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            A7.w r5 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L4d
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            h8.AbstractC1386c.u(r8)
            A7.w r8 = new A7.w
            r8.<init>()
            A.m0 r2 = new A.m0
            r4 = 0
            r2.<init>(r6, r7, r8, r4)
            r0.l = r8
            r0.f203n = r3
            y.j0 r6 = y.j0.l
            java.lang.Object r5 = r5.e(r6, r2, r0)
            if (r5 != r1) goto L4c
            return r1
        L4c:
            r5 = r8
        L4d:
            float r5 = r5.l
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: H7.H.p(A.K0, float, x.C0, s7.c):java.lang.Object");
    }

    public static c9.u q(c9.u uVar, c9.u uVar2) {
        A7.D d10 = new A7.D(5, false);
        int size = uVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            String strL = uVar.l(i7);
            String strS = uVar.s(i7);
            if ((!"Warning".equalsIgnoreCase(strL) || !Q8.o.z0(strS, "1", false)) && ("Content-Length".equalsIgnoreCase(strL) || "Content-Encoding".equalsIgnoreCase(strL) || "Content-Type".equalsIgnoreCase(strL) || !F(strL) || uVar2.d(strL) == null)) {
                d10.k(strL, strS);
            }
        }
        int size2 = uVar2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            String strL2 = uVar2.l(i10);
            if (!"Content-Length".equalsIgnoreCase(strL2) && !"Content-Encoding".equalsIgnoreCase(strL2) && !"Content-Type".equalsIgnoreCase(strL2) && F(strL2)) {
                d10.k(strL2, uVar2.s(i10));
            }
        }
        return d10.m();
    }

    public static final Type r(x xVar, boolean z6) {
        InterfaceC0319d interfaceC0319dC = xVar.c();
        if (interfaceC0319dC instanceof y) {
            return new E((y) interfaceC0319dC);
        }
        if (!(interfaceC0319dC instanceof InterfaceC0318c)) {
            throw new UnsupportedOperationException("Unsupported type classifier: " + xVar);
        }
        InterfaceC0318c interfaceC0318c = (InterfaceC0318c) interfaceC0319dC;
        Class clsJ = z6 ? n7.z.j(interfaceC0318c) : n7.z.i(interfaceC0318c);
        List listA = xVar.a();
        if (listA.isEmpty()) {
            return clsJ;
        }
        if (!clsJ.isArray()) {
            return v(clsJ, listA);
        }
        if (clsJ.getComponentType().isPrimitive()) {
            return clsJ;
        }
        A a10 = (A) AbstractC1998m.t0(listA);
        if (a10 == null) {
            throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + xVar);
        }
        B b10 = a10.f2839a;
        int i7 = b10 == null ? -1 : F.f2846a[b10.ordinal()];
        if (i7 == -1 || i7 == 1) {
            return clsJ;
        }
        if (i7 != 2 && i7 != 3) {
            throw new A9.k();
        }
        x xVar2 = a10.f2840b;
        A7.m.c(xVar2);
        Type typeR = r(xVar2, false);
        return typeR instanceof Class ? clsJ : new C0316a(typeR);
    }

    public static final Collection s(Collection collection, Collection collection2) {
        A7.m.f(collection2, "collection");
        if (collection2.isEmpty()) {
            return collection;
        }
        if (collection == null) {
            return collection2;
        }
        if (collection instanceof LinkedHashSet) {
            ((LinkedHashSet) collection).addAll(collection2);
            return collection;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        linkedHashSet.addAll(collection2);
        return linkedHashSet;
    }

    public static final boolean t(m0.d dVar, float f10, float f11) {
        return f10 <= dVar.c && dVar.f18882a <= f10 && f11 <= dVar.d && dVar.f18883b <= f11;
    }

    public static final N6.f u(l0 l0Var, C0761q c0761q) {
        N6.f fVarD;
        c0761q.R(1770922558);
        if (l0Var instanceof InterfaceC0977o) {
            Context baseContext = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            h0 h0VarD = ((InterfaceC0977o) l0Var).d();
            A7.m.f(baseContext, "context");
            A7.m.f(h0VarD, "delegateFactory");
            while (baseContext instanceof ContextWrapper) {
                if (baseContext instanceof h.n) {
                    fVarD = N6.f.d((h.n) baseContext, h0VarD);
                } else {
                    baseContext = ((ContextWrapper) baseContext).getBaseContext();
                    A7.m.e(baseContext, "ctx.baseContext");
                }
            }
            throw new IllegalStateException("Expected an activity context for creating a HiltViewModelFactory but instead found: " + baseContext);
        }
        fVarD = null;
        c0761q.p(false);
        return fVarD;
    }

    public static final D v(Class cls, List list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(B((A) it.next()));
            }
            return new D(cls, null, arrayList);
        }
        if (Modifier.isStatic(cls.getModifiers())) {
            ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list, 10));
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList2.add(B((A) it2.next()));
            }
            return new D(cls, declaringClass, arrayList2);
        }
        int length = cls.getTypeParameters().length;
        D dV = v(declaringClass, list.subList(length, list.size()));
        List listSubList = list.subList(0, length);
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(listSubList, 10));
        Iterator it3 = listSubList.iterator();
        while (it3.hasNext()) {
            arrayList3.add(B((A) it3.next()));
        }
        return new D(cls, dV, arrayList3);
    }

    public static boolean w(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2) {
        A7.m.f(interfaceC0649b, "superDescriptor");
        A7.m.f(interfaceC0649b2, "subDescriptor");
        if ((interfaceC0649b2 instanceof C1011f) && (interfaceC0649b instanceof InterfaceC0666t)) {
            C1011f c1011f = (C1011f) interfaceC0649b2;
            c1011f.D0().size();
            InterfaceC0666t interfaceC0666t = (InterfaceC0666t) interfaceC0649b;
            interfaceC0666t.D0().size();
            List listD0 = c1011f.b1().D0();
            A7.m.e(listD0, "subDescriptor.original.valueParameters");
            List listD02 = interfaceC0666t.b1().D0();
            A7.m.e(listD02, "superDescriptor.original.valueParameters");
            for (m7.i iVar : AbstractC1998m.F0(listD0, listD02)) {
                Q q4 = (Q) iVar.l;
                Q q10 = (Q) iVar.f19259m;
                A7.m.e(q4, "subParameter");
                boolean z6 = L((InterfaceC0666t) interfaceC0649b2, q4) instanceof h8.k;
                A7.m.e(q10, "superParameter");
                if (z6 != (L(interfaceC0666t, q10) instanceof h8.k)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final Object x(InterfaceC0428i interfaceC0428i, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        return interfaceC0428i.c(new S1.f(interfaceC2785n, null), interfaceC2189c);
    }

    public static final boolean y(int i7, int i10) {
        return i7 == i10;
    }

    public static boolean z(String str, String str2) {
        char c10;
        int length = str.length();
        if (str == str2) {
            return true;
        }
        if (length != str2.length()) {
            return false;
        }
        for (int i7 = 0; i7 < length; i7++) {
            if (str.charAt(i7) != str2.charAt(i7) && ((c10 = (char) ((r4 | ' ') - 97)) >= 26 || c10 != ((char) ((r5 | ' ') - 97)))) {
                return false;
            }
        }
        return true;
    }

    public abstract void N(String str, C5.g gVar, B5.y yVar);
}
