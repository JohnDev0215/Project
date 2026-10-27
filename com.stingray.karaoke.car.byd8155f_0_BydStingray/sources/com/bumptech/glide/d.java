package com.bumptech.glide;

import A3.v;
import A7.m;
import B5.z;
import B8.t;
import C0.K;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.C0119a;
import D.C0124f;
import D.P;
import D.S;
import E.D;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F1.A;
import F1.H;
import H7.InterfaceC0321f;
import H7.w;
import I5.y;
import K7.A0;
import K7.m0;
import K7.r;
import N0.L;
import Q.AbstractC0535v;
import Q.c1;
import Q5.A1;
import Q5.AbstractC0644z;
import Q5.C0545a;
import Q5.C0607p1;
import Q5.C0625u0;
import Q5.C0638x1;
import Q5.C0642y1;
import Q5.C0646z1;
import Q5.G;
import Q8.o;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.X;
import T1.s;
import a.AbstractC0904a;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.EdgeEffect;
import c5.C1032i;
import c5.InterfaceC1031h;
import c9.F;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import g1.AbstractC1274a;
import h.x;
import h2.AbstractC1347d;
import h6.C1382a;
import h6.n;
import io.sentry.EnumC1495p1;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import k6.AbstractC1692D;
import k6.C1691C;
import n0.AbstractC1861K;
import n0.C1865O;
import r9.C2319i;
import x3.AbstractC2673a;
import y0.AbstractC2737o;
import y0.C2730h;
import y0.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static t f12673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f12674b = 0;
    public static final /* synthetic */ int c = 0;

    public static int A(int i7, int i10, float f10) {
        if (i7 == i10 || f10 <= 0.0f) {
            return i7;
        }
        if (f10 >= 1.0f) {
            return i10;
        }
        float f11 = ((i7 >> 24) & 255) / 255.0f;
        float f12 = ((i10 >> 24) & 255) / 255.0f;
        float f13 = f(((i7 >> 16) & 255) / 255.0f);
        float f14 = f(((i7 >> 8) & 255) / 255.0f);
        float f15 = f((i7 & 255) / 255.0f);
        float f16 = f(((i10 >> 16) & 255) / 255.0f);
        float f17 = f(((i10 >> 8) & 255) / 255.0f);
        float f18 = f((i10 & 255) / 255.0f);
        float fA = AbstractC1347d.a(f12, f11, f10, f11);
        float fA2 = AbstractC1347d.a(f16, f13, f10, f13);
        float fA3 = AbstractC1347d.a(f17, f14, f10, f14);
        float fA4 = AbstractC1347d.a(f18, f15, f10, f15);
        float fI = i(fA2) * 255.0f;
        float fI2 = i(fA3) * 255.0f;
        return Math.round(i(fA4) * 255.0f) | (Math.round(fI) << 16) | (Math.round(fA * 255.0f) << 24) | (Math.round(fI2) << 8);
    }

    public static final int B(Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (Exception unused) {
                int height = bitmap.getHeight() * bitmap.getWidth();
                Bitmap.Config config = bitmap.getConfig();
                return (config == Bitmap.Config.ALPHA_8 ? 1 : (config == Bitmap.Config.RGB_565 || config == Bitmap.Config.ARGB_4444) ? 2 : config == Bitmap.Config.RGBA_F16 ? 8 : 4) * height;
            }
        }
        throw new IllegalStateException(("Cannot obtain size for recycled bitmap: " + bitmap + " [" + bitmap.getWidth() + " x " + bitmap.getHeight() + "] + " + bitmap.getConfig()).toString());
    }

    public static float C(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return I1.c.b(edgeEffect);
        }
        return 0.0f;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, m7.f] */
    public static final Field D(w wVar) {
        m.f(wVar, "<this>");
        m0 m0VarC = A0.c(wVar);
        if (m0VarC != null) {
            return (Field) m0VarC.f3815q.getValue();
        }
        return null;
    }

    public static final Method E(InterfaceC0321f interfaceC0321f) {
        L7.e eVarI;
        m.f(interfaceC0321f, "<this>");
        r rVarA = A0.a(interfaceC0321f);
        Member memberS = (rVarA == null || (eVarI = rVarA.i()) == null) ? null : eVarI.s();
        if (memberS instanceof Method) {
            return (Method) memberS;
        }
        return null;
    }

    public static boolean F(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, java.util.List] */
    public static final boolean G(C2730h c2730h) {
        ?? r52 = c2730h.f24065a;
        int size = r52.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!AbstractC2737o.e(((q) r52.get(i7)).f24083i, 2)) {
                return false;
            }
        }
        return true;
    }

    public static InterfaceC1031h H(InterfaceC1031h interfaceC1031h) {
        return ((interfaceC1031h instanceof c5.k) || (interfaceC1031h instanceof C1032i)) ? interfaceC1031h : interfaceC1031h instanceof Serializable ? new C1032i(interfaceC1031h) : new c5.k(interfaceC1031h);
    }

    public static float K(EdgeEffect edgeEffect, float f10, float f11) {
        if (Build.VERSION.SDK_INT >= 31) {
            return I1.c.c(edgeEffect, f10, f11);
        }
        I1.b.a(edgeEffect, f10, f11);
        return f10;
    }

    public static void L(View view, Y4.g gVar) {
        T4.a aVar = gVar.l.f10709b;
        if (aVar == null || !aVar.f8645a) {
            return;
        }
        float fE = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            int[] iArr = H.f2303a;
            fE += A.e((View) parent);
        }
        Y4.f fVar = gVar.l;
        if (fVar.l != fE) {
            fVar.l = fE;
            gVar.k();
        }
    }

    public static final String M(String str) {
        m.f(str, "<this>");
        int i7 = 0;
        int i10 = -1;
        if (!Q8.h.C0(str, ":", false)) {
            try {
                String ascii = IDN.toASCII(str);
                m.e(ascii, "toASCII(host)");
                Locale locale = Locale.US;
                m.e(locale, "US");
                String lowerCase = ascii.toLowerCase(locale);
                m.e(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i11 = 0; i11 < length; i11++) {
                    char cCharAt = lowerCase.charAt(i11);
                    if (m.g(cCharAt, 31) <= 0 || m.g(cCharAt, 127) >= 0 || Q8.h.J0(" #%/:?@[\\]", cCharAt, 0, false, 6) != -1) {
                        return null;
                    }
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressY = (o.z0(str, "[", false) && o.s0(str, "]", false)) ? y(str, 1, str.length() - 1) : y(str, 0, str.length());
        if (inetAddressY == null) {
            return null;
        }
        byte[] address = inetAddressY.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return inetAddressY.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        int i12 = 0;
        int i13 = 0;
        while (i12 < address.length) {
            int i14 = i12;
            while (i14 < 16 && address[i14] == 0 && address[i14 + 1] == 0) {
                i14 += 2;
            }
            int i15 = i14 - i12;
            if (i15 > i13 && i15 >= 4) {
                i10 = i12;
                i13 = i15;
            }
            i12 = i14 + 2;
        }
        C2319i c2319i = new C2319i();
        while (i7 < address.length) {
            if (i7 == i10) {
                c2319i.X(58);
                i7 += i13;
                if (i7 == 16) {
                    c2319i.X(58);
                }
            } else {
                if (i7 > 0) {
                    c2319i.X(58);
                }
                byte b10 = address[i7];
                byte[] bArr = d9.b.f13645a;
                c2319i.Z(((b10 & 255) << 8) | (address[i7 + 1] & 255));
                i7 += 2;
            }
        }
        return c2319i.Q();
    }

    public static final long N(long j3, R8.c cVar) {
        m.f(cVar, "unit");
        R8.c cVar2 = R8.c.f7567m;
        long jD = AbstractC1274a.D(4611686018426999999L, cVar2, cVar);
        if ((-jD) > j3 || j3 > jD) {
            R8.c cVar3 = R8.c.f7568n;
            m.f(cVar3, "targetUnit");
            return z(AbstractC2673a.w(cVar3.l.convert(j3, cVar.l), -4611686018427387903L, 4611686018427387903L));
        }
        long jD2 = AbstractC1274a.D(j3, cVar, cVar2) << 1;
        int i7 = R8.a.f7565n;
        int i10 = R8.b.f7566a;
        return jD2;
    }

    public static final L O(L l, long j3, float f10) {
        m.f(l, "$this$withGlow");
        return L.b(l, 0L, 0L, null, null, 0L, new C1865O(j3, f10, 2), 0L, null, null, null, 16769023);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(g0.o r30, boolean r31, D.L r32, z7.InterfaceC2772a r33, T.C0761q r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 994
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.a(g0.o, boolean, D.L, z7.a, T.q, int, int):void");
    }

    public static final void b(C1382a c1382a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(428263741);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1382a) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            C0607p1 c0607p1 = new C0607p1(c1382a, AbstractC2673a.X(R.string.Global_About, c0761q), false, null, 250);
            C1691C c1691c = c1382a.l;
            X xR = c.R(c1691c.f18229a, c0761q, 0);
            X xR2 = c.R(c1691c.d, c0761q, 0);
            if (((Boolean) xR.getValue()).booleanValue()) {
                c0761q.Q(-974960624);
                AbstractC0904a.l(null, c0607p1, AbstractC0644z.f7157a, c0761q, 384, 1);
                c0761q.p(false);
            } else {
                c0761q.Q(-974669317);
                AbstractC0904a.l(null, c0607p1, b0.g.b(71193503, new G6.e(c1382a, 1, xR2), c0761q), c0761q, 384, 1);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 0, c1382a);
        }
    }

    public static final int c(long j3) {
        return AbstractC2673a.v((int) (j3 & 255), 0, 2555) | (AbstractC2673a.v((int) ((j3 >> 24) & 255), 0, 255) << 24) | (AbstractC2673a.v((int) ((j3 >> 16) & 255), 0, 255) << 16) | (AbstractC2673a.v((int) ((j3 >> 8) & 255), 0, 255) << 8);
    }

    public static final void d(final String str, final String str2, final String str3, final String str4, final String str5, final InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, C0761q c0761q, final int i7) {
        int i10;
        final InterfaceC2772a interfaceC2772a3;
        m.f(str, "id");
        m.f(str3, "text");
        m.f(str4, "confirmBtnText");
        m.f(interfaceC2772a, "onConfirm");
        c0761q.S(1067509369);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str2) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str4) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(str5) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        int i11 = i10 | 1572864;
        if ((i11 & 599187) == 599186 && c0761q.x()) {
            c0761q.K();
            interfaceC2772a3 = interfaceC2772a2;
        } else {
            c0761q.Q(-764054934);
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = new v(3);
                c0761q.a0(objG);
            }
            InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) objG;
            c0761q.p(false);
            AbstractC1274a.h(str, true, false, false, false, b0.g.b(1119694930, new S5.d(str, str2, str3, str4, str5, interfaceC2772a, interfaceC2772a4), c0761q), c0761q, (i11 & 14) | 196656, 28);
            interfaceC2772a3 = interfaceC2772a4;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: S5.c
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    InterfaceC2772a interfaceC2772a5 = interfaceC2772a;
                    InterfaceC2772a interfaceC2772a6 = interfaceC2772a3;
                    com.bumptech.glide.d.d(str, str2, str3, str4, str5, interfaceC2772a5, interfaceC2772a6, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void e(final g0.o r59, final java.lang.String r60, java.lang.String r61, final java.lang.String r62, final java.lang.String r63, java.lang.String r64, final z7.InterfaceC2772a r65, z7.InterfaceC2772a r66, final D.L r67, T.C0761q r68, final int r69, final int r70) {
        /*
            Method dump skipped, instruction units count: 1085
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.e(g0.o, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, z7.a, z7.a, D.L, T.q, int, int):void");
    }

    public static float f(float f10) {
        return f10 <= 0.04045f ? f10 / 12.92f : (float) Math.pow((f10 + 0.055f) / 1.055f, 2.4000000953674316d);
    }

    public static final void g(g0.o oVar, List list, D.L l, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        g0.o oVar2;
        c0761q.S(1741581982);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            C7.a.e(lVar, list, l, interfaceC2785n, interfaceC2782k, null, null, G.f6357a, c0761q, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & 896) | (i10 & 7168) | (i10 & 57344), 96);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0625u0(oVar2, list, l, interfaceC2785n, interfaceC2782k, i7);
        }
    }

    public static final void h(f6.g gVar, C0761q c0761q, int i7) {
        int i10;
        m.f(gVar, "viewModel");
        c0761q.S(601827419);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(gVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(gVar, null, false, null, 254), b0.g.b(-186998819, new y(gVar, 6), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 5, gVar);
        }
    }

    public static float i(float f10) {
        return f10 <= 0.0031308f ? f10 * 12.92f : (float) ((Math.pow(f10, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    public static final void j(g0.o oVar, List list, String str, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n2, b6.g gVar, D.L l, C0761q c0761q, int i7) {
        g0.o oVar2;
        m.f(list, "searchContent");
        m.f(str, "searchText");
        m.f(interfaceC2785n, "openItemDetails");
        m.f(interfaceC2782k, "onMoreSelected");
        m.f(interfaceC2785n2, "onFirstVisibleItem");
        m.f(gVar, "firstVisibleItem");
        m.f(l, "contentPadding");
        c0761q.S(-2051618813);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2785n2) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(gVar) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(l) ? 8388608 : 4194304;
        }
        if ((4793491 & i10) == 4793490 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            D dA = E.G.a(gVar.f12242a, gVar.f12243b, c0761q, 0, 0);
            c0761q.Q(-802228737);
            boolean zF = c0761q.f(dA) | c0761q.h(gVar);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (zF || objG == obj) {
                objG = new C0642y1(dA, gVar, null);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            C0735d.f(c0761q, gVar, (InterfaceC2785n) objG);
            D.L lC = androidx.compose.foundation.layout.a.c(AbstractC2673a.J(l, c0761q), 0.0f, AbstractC2673a.I(l, c0761q), AbstractC2673a.H(l, c0761q), 2);
            if (c0761q.k(AbstractC1692D.f18238a) == LayoutMode.NARROW_COMPACT) {
                c0761q.Q(901400170);
                c0761q.Q(-802201674);
                int i11 = i10 & 458752;
                boolean zF2 = ((i10 & 7168) == 2048) | (i11 == 131072) | c0761q.f(dA);
                Object objG2 = c0761q.G();
                if (zF2 || objG2 == obj) {
                    objG2 = new C0646z1(interfaceC2785n, interfaceC2785n2, dA, 0);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                InterfaceC2785n interfaceC2785n3 = (InterfaceC2785n) ((InterfaceC0321f) objG2);
                c0761q.Q(-802200272);
                boolean zF3 = ((i10 & 57344) == 16384) | (i11 == 131072) | c0761q.f(dA);
                Object objG3 = c0761q.G();
                if (zF3 || objG3 == obj) {
                    objG3 = new A1(interfaceC2782k, interfaceC2785n2, dA, 0);
                    c0761q.a0(objG3);
                }
                c0761q.p(false);
                int i12 = i10 & 14;
                int i13 = i10 << 3;
                C7.a.t(lVar, null, list, str, lC, interfaceC2785n3, (InterfaceC2782k) ((InterfaceC0321f) objG3), dA, c0761q, i12 | (i13 & 896) | (i13 & 7168));
                c0761q.p(false);
            } else {
                c0761q.Q(901707752);
                c0761q.Q(-802191658);
                int i14 = i10 & 458752;
                boolean zF4 = ((i10 & 7168) == 2048) | (i14 == 131072) | c0761q.f(dA);
                Object objG4 = c0761q.G();
                if (zF4 || objG4 == obj) {
                    objG4 = new C0646z1(interfaceC2785n, interfaceC2785n2, dA, 1);
                    c0761q.a0(objG4);
                }
                c0761q.p(false);
                InterfaceC2785n interfaceC2785n4 = (InterfaceC2785n) ((InterfaceC0321f) objG4);
                c0761q.Q(-802190256);
                boolean zF5 = ((i10 & 57344) == 16384) | (i14 == 131072) | c0761q.f(dA);
                Object objG5 = c0761q.G();
                if (zF5 || objG5 == obj) {
                    objG5 = new A1(interfaceC2782k, interfaceC2785n2, dA, 1);
                    c0761q.a0(objG5);
                }
                c0761q.p(false);
                C7.a.e(lVar, list, lC, interfaceC2785n4, (InterfaceC2782k) ((InterfaceC0321f) objG5), dA, str, null, c0761q, (i10 & 126) | ((i10 << 12) & 3670016), 128);
                c0761q.p(false);
            }
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0638x1(oVar2, list, str, interfaceC2785n, interfaceC2782k, interfaceC2785n2, gVar, l, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void k(g0.o r30, com.stingray.karaoke.car.utils.FontSize r31, java.lang.String r32, T.C0761q r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.k(g0.o, com.stingray.karaoke.car.utils.FontSize, java.lang.String, T.q, int, int):void");
    }

    public static final void l(final String str, final long j3, final long j10, final Integer num, C0761q c0761q, final int i7) {
        int i10;
        g0.l lVar;
        long j11;
        m.f(str, "text");
        c0761q.S(-740838874);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.e(j3) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.e(j10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(num) ? 2048 : 1024;
        }
        int i11 = i10;
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar2 = g0.l.f14684a;
            g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar2, 1.0f);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarM = androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.a.o(oVarC, 0.0f, 0.0f, 0.0f, bVar.H(6, c0761q), 7), bVar.F(c0761q), 0.0f, 2);
            K kE = AbstractC0133o.e(g0.b.f14667s, false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarM);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            float f10 = 2;
            g0.o oVarL = androidx.compose.foundation.layout.a.l(androidx.compose.foundation.a.b(h6.q.m(androidx.compose.foundation.layout.a.g(lVar2, 2), J.e.a(bVar.f0(c0761q))), j3, AbstractC1861K.f19381a), bVar.F(c0761q) * f10, bVar.H(6, c0761q) * f10);
            g0.f fVar = g0.b.f14670v;
            C0119a c0119a = AbstractC0127i.f1076a;
            S sB = P.b(new C0124f(bVar.F(c0761q)), fVar, c0761q, 48);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarL);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            long jG = h6.f.g(FontSize.H9, c0761q);
            c0761q.Q(1512580069);
            if (num == null) {
                lVar = lVar2;
                j11 = jG;
            } else {
                lVar = lVar2;
                j11 = jG;
                AbstractC0535v.a(l9.l.W(num.intValue(), c0761q, 0), null, androidx.compose.foundation.layout.c.l(lVar2, ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).K(jG)), j10, c0761q, ((i11 << 3) & 7168) | 48, 0);
            }
            c0761q.p(false);
            c1.b(str, lVar, j10, j11, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).d, c0761q, (i11 & 14) | 48 | (i11 & 896), 0, 65520);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.n3
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    long j12 = j10;
                    Integer num2 = num;
                    com.bumptech.glide.d.l(str, j3, j12, num2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:18:0x0041). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object m(y0.z r7, s7.AbstractC2408a r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof O.C0485p
            if (r0 == 0) goto L13
            r0 = r8
            O.p r0 = (O.C0485p) r0
            int r1 = r0.f4984n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4984n = r1
            goto L18
        L13:
            O.p r0 = new O.p
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f4983m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f4984n
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            y0.z r7 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L41
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            h8.AbstractC1386c.u(r8)
        L34:
            y0.i r8 = y0.EnumC2731i.f24067m
            r0.l = r7
            r0.f4984n = r3
            java.lang.Object r8 = r7.a(r8, r0)
            if (r8 != r1) goto L41
            goto L5d
        L41:
            y0.h r8 = (y0.C2730h) r8
            java.lang.Object r2 = r8.f24065a
            int r4 = r2.size()
            r5 = 0
        L4a:
            if (r5 >= r4) goto L5c
            java.lang.Object r6 = r2.get(r5)
            y0.q r6 = (y0.q) r6
            boolean r6 = y0.AbstractC2737o.a(r6)
            if (r6 != 0) goto L59
            goto L34
        L59:
            int r5 = r5 + 1
            goto L4a
        L5c:
            r1 = r8
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.m(y0.z, s7.a):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object n(y0.z r16, N1.C0420a r17, B5.u r18, y0.C2730h r19, s7.AbstractC2408a r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.n(y0.z, N1.a, B5.u, y0.h, s7.a):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ba A[Catch: CancellationException -> 0x0030, TryCatch #0 {CancellationException -> 0x0030, blocks: (B:13:0x002b, B:40:0x00b2, B:42:0x00ba, B:44:0x00c6, B:46:0x00d2, B:47:0x00d5, B:48:0x00d7, B:49:0x00db, B:20:0x0041, B:26:0x0065, B:28:0x0069, B:30:0x0079, B:32:0x0085, B:37:0x0098, B:31:0x0081, B:23:0x004b), top: B:54:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00db A[Catch: CancellationException -> 0x0030, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x0030, blocks: (B:13:0x002b, B:40:0x00b2, B:42:0x00ba, B:44:0x00c6, B:46:0x00d2, B:47:0x00d5, B:48:0x00d7, B:49:0x00db, B:20:0x0041, B:26:0x0065, B:28:0x0069, B:30:0x0079, B:32:0x0085, B:37:0x0098, B:31:0x0081, B:23:0x004b), top: B:54:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object o(y0.z r11, K.InterfaceC0355n0 r12, y0.C2730h r13, s7.AbstractC2408a r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.o(y0.z, K.n0, y0.h, s7.a):java.lang.Object");
    }

    public static void p(z zVar) {
        LinkedHashMap linkedHashMap = A6.d.f439a;
        if (linkedHashMap.isEmpty()) {
            return;
        }
        linkedHashMap.put("navigation_uid", String.valueOf(C6.b.f1011e));
        zVar.putAll(linkedHashMap);
    }

    public static void q(x xVar, androidx.lifecycle.A a10, InterfaceC2782k interfaceC2782k) {
        m.f(xVar, "<this>");
        xVar.a(a10, new X1.h(interfaceC2782k, true));
    }

    public static final void r(int i7, int i10) {
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
    }

    public static final void s(int i7, int i10) {
        if (i7 < 0 || i7 > i10) {
            throw new IndexOutOfBoundsException(A7.k.i("index: ", ", size: ", i7, i10));
        }
    }

    public static final void t(int i7, int i10, int i11) {
        if (i7 < 0 || i10 > i11) {
            StringBuilder sbL = M5.b.l("fromIndex: ", ", toIndex: ", i7, i10, ", size: ");
            sbL.append(i11);
            throw new IndexOutOfBoundsException(sbL.toString());
        }
        if (i7 > i10) {
            throw new IllegalArgumentException(A7.k.i("fromIndex: ", " > toIndex: ", i7, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static C8.c u(o8.C2058c r8, E8.o r9, Q7.InterfaceC0671y r10, java.io.InputStream r11) throws java.lang.IllegalAccessException, java.io.IOException, java.lang.reflect.InvocationTargetException {
        /*
            java.lang.String r0 = "fqName"
            A7.m.f(r8, r0)
            java.lang.String r0 = "storageManager"
            A7.m.f(r9, r0)
            java.lang.String r0 = "module"
            A7.m.f(r10, r0)
            k8.a r0 = k8.C1708a.f18312f     // Catch: java.lang.Throwable -> L70
            k8.a r6 = h6.j.p(r11)     // Catch: java.lang.Throwable -> L70
            java.lang.String r0 = "ourVersion"
            k8.a r1 = k8.C1708a.f18312f     // Catch: java.lang.Throwable -> L70
            A7.m.f(r1, r0)     // Catch: java.lang.Throwable -> L70
            int r0 = r6.c     // Catch: java.lang.Throwable -> L70
            int r2 = r1.c     // Catch: java.lang.Throwable -> L70
            int r3 = r1.f18823b     // Catch: java.lang.Throwable -> L70
            int r4 = r6.f18823b     // Catch: java.lang.Throwable -> L70
            r5 = 0
            r7 = 1
            if (r4 != 0) goto L2f
            if (r3 != 0) goto L2d
            if (r0 != r2) goto L2d
            goto L33
        L2d:
            r7 = r5
            goto L33
        L2f:
            if (r4 != r3) goto L2d
            if (r0 > r2) goto L2d
        L33:
            r0 = 0
            if (r7 == 0) goto L72
            p8.g r2 = new p8.g     // Catch: java.lang.Throwable -> L70
            r2.<init>()     // Catch: java.lang.Throwable -> L70
            k8.AbstractC1709b.a(r2)     // Catch: java.lang.Throwable -> L70
            j8.a r3 = j8.E.f17090v     // Catch: java.lang.Throwable -> L70
            r3.getClass()     // Catch: java.lang.Throwable -> L70
            p8.e r4 = new p8.e     // Catch: java.lang.Throwable -> L70
            r4.<init>(r11)     // Catch: java.lang.Throwable -> L70
            java.lang.Object r2 = r3.a(r4, r2)     // Catch: java.lang.Throwable -> L70
            p8.a r2 = (p8.AbstractC2126a) r2     // Catch: java.lang.Throwable -> L70
            r4.a(r5)     // Catch: p8.C2142q -> L6c java.lang.Throwable -> L70
            boolean r3 = r2.b()     // Catch: java.lang.Throwable -> L70
            if (r3 == 0) goto L5b
            j8.E r2 = (j8.E) r2     // Catch: java.lang.Throwable -> L70
            r5 = r2
            goto L73
        L5b:
            A9.k r8 = new A9.k     // Catch: java.lang.Throwable -> L70
            r8.<init>()     // Catch: java.lang.Throwable -> L70
            p8.q r9 = new p8.q     // Catch: java.lang.Throwable -> L70
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L70
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L70
            r9.l = r2     // Catch: java.lang.Throwable -> L70
            throw r9     // Catch: java.lang.Throwable -> L70
        L6c:
            r8 = move-exception
            r8.l = r2     // Catch: java.lang.Throwable -> L70
            throw r8     // Catch: java.lang.Throwable -> L70
        L70:
            r8 = move-exception
            goto La3
        L72:
            r5 = r0
        L73:
            n7.AbstractC1985B.c(r11, r0)
            if (r5 == 0) goto L82
            C8.c r11 = new C8.c
            r1 = r11
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>(r2, r3, r4, r5, r6)
            return r11
        L82:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Kotlin built-in definition format version is not supported: expected "
            r9.<init>(r10)
            r9.append(r1)
            java.lang.String r10 = ", actual "
            r9.append(r10)
            r9.append(r6)
            java.lang.String r10 = ". Please update Kotlin"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        La3:
            throw r8     // Catch: java.lang.Throwable -> La4
        La4:
            r9 = move-exception
            n7.AbstractC1985B.c(r11, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.u(o8.c, E8.o, Q7.y, java.io.InputStream):C8.c");
    }

    public static s v(Context context) {
        ProviderInfo providerInfo;
        C1.e eVar;
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        l9.l.F(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            eVar = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArr = packageManager.getPackageInfo(str2, 64).signatures;
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArr) {
                    arrayList.add(signature.toByteArray());
                }
                eVar = new C1.e(str, Collections.singletonList(arrayList), str2, "emojicompat-emoji-font");
            } catch (PackageManager.NameNotFoundException e10) {
                n.c("emoji2.text.DefaultEmojiConfig", EnumC1495p1.ERROR, null, e10);
                Log.wtf("emoji2.text.DefaultEmojiConfig", e10);
                eVar = null;
            }
        }
        if (eVar == null) {
            return null;
        }
        return new s(new T1.r(context, eVar));
    }

    public static F w(String str, c9.y yVar) {
        m.f(str, "<this>");
        Charset charset = Q8.a.f7211a;
        if (yVar != null) {
            Pattern pattern = c9.y.d;
            Charset charsetA = yVar.a(null);
            if (charsetA == null) {
                yVar = P8.n.L(yVar + "; charset=utf-8");
            } else {
                charset = charsetA;
            }
        }
        byte[] bytes = str.getBytes(charset);
        m.e(bytes, "this as java.lang.String).getBytes(charset)");
        int length = bytes.length;
        d9.b.b(bytes.length, 0, length);
        return new F(yVar, length, bytes, 0);
    }

    public static c x(int i7) {
        if (i7 != 0 && i7 == 1) {
            return new Y4.d();
        }
        return new Y4.i();
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d5, code lost:
    
        if (r7 == r0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00d7, code lost:
    
        if (r8 != (-1)) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d9, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00da, code lost:
    
        r1 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r1, r1);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ec, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008e A[LOOP:2: B:25:0x004c->B:50:0x008e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00a2 A[EDGE_INSN: B:85:0x00a2->B:54:0x00a2 BREAK  A[LOOP:2: B:25:0x004c->B:50:0x008e], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.net.InetAddress y(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.d.y(java.lang.String, int, int):java.net.InetAddress");
    }

    public static final long z(long j3) {
        long j10 = (j3 << 1) + 1;
        int i7 = R8.a.f7565n;
        int i10 = R8.b.f7566a;
        return j10;
    }

    public abstract void I(int i7);

    public abstract void J(Typeface typeface, boolean z6);
}
