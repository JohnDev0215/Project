package h6;

import A.C0050y0;
import D.C0130l;
import E0.C0167q;
import F0.D0;
import F0.W;
import F8.AbstractC0287w;
import H7.H;
import Q5.C0619s2;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.X;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaCodecInfo;
import android.os.Build;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.lifecycle.C;
import androidx.lifecycle.EnumC0981t;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.karaoke.car.utils.WindowSizeClass;
import d5.d0;
import d8.C1134u;
import e0.C1178r;
import e2.C1207o;
import e2.C1208p;
import e2.L;
import g1.AbstractC1274a;
import h8.C1390g;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import io.sentry.android.core.B;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import k6.AbstractC1692D;
import k6.C1701i;
import k6.u;
import m0.AbstractC1792a;
import n0.AbstractC1887p;
import n0.C1868S;
import n0.InterfaceC1866P;
import n3.C1932G;
import n3.C1933H;
import n3.C1934I;
import n3.C1945j;
import n3.y;
import n7.AbstractC2000o;
import n7.z;
import o3.C2038k;
import o3.C2040m;
import o3.C2041n;
import o3.C2042o;
import s2.s;
import s2.v;
import w8.C2608c;
import w8.EnumC2609d;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15579a = 0;

    public static int A(char c, int i7, int i10, CharSequence charSequence) {
        while (i7 < i10) {
            if (charSequence.charAt(i7) != c) {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static int B(CharSequence charSequence, int i7, int i10) {
        while (i7 < i10) {
            char cCharAt = charSequence.charAt(i7);
            if (cCharAt != '\t' && cCharAt != ' ') {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static final void a(C2042o c2042o, C0761q c0761q, int i7) {
        c0761q.S(294589392);
        int i10 = 4;
        int i11 = (i7 & 6) == 0 ? (c0761q.f(c2042o) ? 4 : 2) | i7 : i7;
        if ((i11 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            d0.h hVarS = AbstractC2673a.S(c0761q);
            boolean z6 = false;
            X x8 = C0735d.x(c2042o.b().f19880e, c0761q, 0);
            List list = (List) x8.getValue();
            boolean zBooleanValue = ((Boolean) c0761q.k(D0.f2013a)).booleanValue();
            boolean zF = c0761q.f(list);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            Object obj2 = objG;
            if (zF || objG == obj) {
                C1178r c1178r = new C1178r();
                ArrayList arrayList = new ArrayList();
                for (Object obj3 : list) {
                    C1945j c1945j = (C1945j) obj3;
                    if (zBooleanValue || c1945j.f19870s.f11908p.compareTo(EnumC0981t.o) >= 0) {
                        arrayList.add(obj3);
                    }
                }
                c1178r.addAll(arrayList);
                c0761q.a0(c1178r);
                obj2 = c1178r;
            }
            C1178r c1178r2 = (C1178r) obj2;
            b(c1178r2, (List) x8.getValue(), c0761q, 0);
            X x9 = C0735d.x(c2042o.b().f19881f, c0761q, 0);
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new C1178r();
                c0761q.a0(objG2);
            }
            C1178r c1178r3 = (C1178r) objG2;
            c0761q.Q(1361037007);
            ListIterator listIterator = c1178r2.listIterator();
            while (true) {
                C0167q c0167q = (C0167q) listIterator;
                if (!c0167q.hasNext()) {
                    break;
                }
                C1945j c1945j2 = (C1945j) c0167q.next();
                y yVar = c1945j2.f19865m;
                A7.m.d(yVar, "null cannot be cast to non-null type androidx.navigation.compose.DialogNavigator.Destination");
                C2041n c2041n = (C2041n) yVar;
                boolean zH = c0761q.h(c1945j2) | ((i11 & 14) != i10 ? z6 : true);
                Object objG3 = c0761q.G();
                if (zH || objG3 == obj) {
                    objG3 = new C1134u(c2042o, 3, c1945j2);
                    c0761q.a0(objG3);
                }
                AbstractC1274a.g((InterfaceC2772a) objG3, c2041n.f20478u, b0.g.b(1129586364, new C0619s2(c1945j2, c2042o, hVarS, c1178r3, c2041n), c0761q), c0761q, 384);
                i10 = 4;
                z6 = false;
            }
            boolean z10 = z6;
            c0761q.p(z10);
            Set set = (Set) x9.getValue();
            boolean zF2 = c0761q.f(x9) | ((i11 & 14) == 4 ? true : z10);
            Object objG4 = c0761q.G();
            if (zF2 || objG4 == obj) {
                objG4 = new C2038k(x9, c2042o, c1178r3, null);
                c0761q.a0(objG4);
            }
            C0735d.g(set, c1178r3, (InterfaceC2785n) objG4, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 9, c2042o);
        }
    }

    public static final void b(C1178r c1178r, List list, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1537894851);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1178r) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zBooleanValue = ((Boolean) c0761q.k(D0.f2013a)).booleanValue();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C1945j c1945j = (C1945j) it.next();
                C c = c1945j.f19870s;
                boolean zG = c0761q.g(zBooleanValue) | c0761q.h(c1178r) | c0761q.h(c1945j);
                Object objG = c0761q.G();
                if (zG || objG == C0753m.f8270a) {
                    objG = new C2040m(c1178r, c1945j, zBooleanValue);
                    c0761q.a0(objG);
                }
                C0735d.d(c, (InterfaceC2782k) objG, c0761q);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(c1178r, list, i7, 8);
        }
    }

    public static final g0.o c(g0.o oVar, float f10, long j3, InterfaceC1866P interfaceC1866P) {
        return d(oVar, f10, new C1868S(j3), interfaceC1866P);
    }

    public static final g0.o d(g0.o oVar, float f10, AbstractC1887p abstractC1887p, InterfaceC1866P interfaceC1866P) {
        return oVar.k(new BorderModifierNodeElement(f10, abstractC1887p, interfaceC1866P));
    }

    public static final Object e(Object obj, boolean z6) {
        EnumC2609d enumC2609d;
        A7.m.f(obj, "possiblyPrimitiveType");
        if (!z6) {
            return obj;
        }
        h8.l lVar = (h8.l) obj;
        if (!(lVar instanceof h8.k) || (enumC2609d = ((h8.k) lVar).f15615i) == null) {
            return lVar;
        }
        String strE = C2608c.c(enumC2609d.e()).e();
        A7.m.e(strE, "byFqNameWithoutInnerClas…apperFqName).internalName");
        return C1390g.d(strE);
    }

    public static y8.n g(String str, Collection collection) {
        A7.m.f(str, "message");
        A7.m.f(collection, "types");
        Collection collection2 = collection;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collection2, 10));
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(((AbstractC0287w) it.next()).z0());
        }
        O8.g gVarK = H.K(arrayList);
        int i7 = gVarK.l;
        y8.n aVar = i7 != 0 ? i7 != 1 ? new y8.a(str, (y8.n[]) gVarK.toArray(new y8.n[0])) : (y8.n) gVarK.get(0) : y8.m.f24213b;
        return gVarK.l <= 1 ? aVar : new y8.j(aVar);
    }

    public static int h(boolean z6) {
        List<MediaCodecInfo.VideoCapabilities.PerformancePoint> supportedPerformancePoints;
        try {
            C1207o c1207o = new C1207o();
            c1207o.f14246m = L.l("video/avc");
            C1208p c1208p = new C1208p(c1207o);
            if (c1208p.f14316n != null) {
                d0 d0VarG = v.g(s2.i.f22094m, c1208p, z6, false);
                for (int i7 = 0; i7 < d0VarG.o; i7++) {
                    if (((s2.n) d0VarG.get(i7)).d != null && ((s2.n) d0VarG.get(i7)).d.getVideoCapabilities() != null && (supportedPerformancePoints = ((s2.n) d0VarG.get(i7)).d.getVideoCapabilities().getSupportedPerformancePoints()) != null && !supportedPerformancePoints.isEmpty()) {
                        MediaCodecInfo.VideoCapabilities.PerformancePoint performancePoint = new MediaCodecInfo.VideoCapabilities.PerformancePoint(1280, 720, 60);
                        for (int i10 = 0; i10 < supportedPerformancePoints.size(); i10++) {
                            if (supportedPerformancePoints.get(i10).covers(performancePoint)) {
                                return 2;
                            }
                        }
                        return 1;
                    }
                }
            }
        } catch (s unused) {
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013f A[EDGE_INSN: B:125:0x013f->B:61:0x013f BREAK  A[LOOP:0: B:50:0x0109->B:54:0x0116]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.sentry.android.replay.viewhierarchy.d i(android.view.View r17, io.sentry.android.replay.viewhierarchy.d r18, int r19, io.sentry.F1 r20) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.i.i(android.view.View, io.sentry.android.replay.viewhierarchy.d, int, io.sentry.F1):io.sentry.android.replay.viewhierarchy.d");
    }

    public static String j(Context context, K k10) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i7 = applicationInfo.labelRes;
            if (i7 != 0) {
                return context.getString(i7);
            }
            CharSequence charSequence = applicationInfo.nonLocalizedLabel;
            return charSequence != null ? charSequence.toString() : context.getPackageManager().getApplicationLabel(applicationInfo).toString();
        } catch (Throwable th) {
            k10.s(EnumC1495p1.ERROR, "Error getting application name.", th);
            return null;
        }
    }

    public static String k(K k10) {
        try {
            return Build.MODEL.split(" ", -1)[0];
        } catch (Throwable th) {
            k10.s(EnumC1495p1.ERROR, "Error getting device family.", th);
            return null;
        }
    }

    public static String l(K k10) {
        String property = System.getProperty("os.version");
        File file = new File("/proc/version");
        if (!file.canRead()) {
            return property;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                return line;
            } finally {
            }
        } catch (IOException e10) {
            k10.s(EnumC1495p1.ERROR, "Exception while attempting to read kernel information", e10);
            return property;
        }
    }

    public static LayoutMode m(C0761q c0761q) {
        LayoutMode layoutMode;
        c0761q.Q(-984304025);
        C0773y c0773y = AbstractC1692D.d;
        float f10 = ((u) c0761q.k(c0773y)).f18298a / ((u) c0761q.k(c0773y)).f18299b;
        C0773y c0773y2 = AbstractC1692D.f18240e;
        boolean z6 = f10 > ((C1701i) c0761q.k(c0773y2)).c && v(6, c0761q);
        c0761q.p(false);
        if (z6) {
            return LayoutMode.WIDE_ULTRA;
        }
        c0761q.Q(1907914569);
        boolean z10 = ((float) ((u) c0761q.k(c0773y)).f18298a) / ((float) ((u) c0761q.k(c0773y)).f18299b) > ((C1701i) c0761q.k(c0773y2)).f18269b;
        c0761q.p(false);
        if (z10) {
            c0761q.Q(-984166757);
            layoutMode = r(6, c0761q) ? LayoutMode.WIDE_COMPACT : LayoutMode.WIDE_NORMAL;
            c0761q.p(false);
        } else {
            c0761q.Q(1907922251);
            boolean z11 = ((float) ((u) c0761q.k(c0773y)).f18298a) / ((float) ((u) c0761q.k(c0773y)).f18299b) < ((C1701i) c0761q.k(c0773y2)).f18268a;
            c0761q.p(false);
            if (z11) {
                c0761q.Q(-983923717);
                c0761q.Q(1907923716);
                boolean zV = v(6, c0761q);
                c0761q.p(false);
                if (zV) {
                    layoutMode = LayoutMode.NARROW_ULTRA;
                } else {
                    c0761q.Q(1907926662);
                    boolean zR = r(6, c0761q);
                    c0761q.p(false);
                    layoutMode = zR ? LayoutMode.NARROW_COMPACT : LayoutMode.NARROW_NORMAL;
                }
                c0761q.p(false);
            } else {
                c0761q.Q(-983600325);
                c0761q.Q(1907934148);
                boolean zV2 = v(6, c0761q);
                c0761q.p(false);
                if (zV2) {
                    layoutMode = LayoutMode.SQUARE_ULTRA;
                } else {
                    c0761q.Q(1907937094);
                    boolean zR2 = r(6, c0761q);
                    c0761q.p(false);
                    layoutMode = zR2 ? LayoutMode.SQUARE_COMPACT : LayoutMode.SQUARE_NORMAL;
                }
                c0761q.p(false);
            }
        }
        return layoutMode;
    }

    public static ActivityManager.MemoryInfo n(Context context, K k10) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo;
            }
            k10.k(EnumC1495p1.INFO, "Error getting MemoryInfo.", new Object[0]);
            return null;
        } catch (Throwable th) {
            k10.s(EnumC1495p1.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    public static PackageInfo o(Context context, int i7, K k10, B b10) {
        try {
            b10.getClass();
            return Build.VERSION.SDK_INT >= 33 ? context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(i7)) : context.getPackageManager().getPackageInfo(context.getPackageName(), i7);
        } catch (Throwable th) {
            k10.s(EnumC1495p1.ERROR, "Error getting package info.", th);
            return null;
        }
    }

    public static WindowSizeClass p(C0761q c0761q) {
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18299b;
        return f10 <= 480.0f ? WindowSizeClass.COMPACT : f10 <= 900.0f ? WindowSizeClass.MEDIUM : f10 <= 1600.0f ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA;
    }

    public static WindowSizeClass q(C0761q c0761q) {
        float f10 = ((u) c0761q.k(AbstractC1692D.d)).f18298a;
        return f10 <= 600.0f ? WindowSizeClass.COMPACT : f10 <= 840.0f ? WindowSizeClass.MEDIUM : f10 <= 1500.0f ? WindowSizeClass.EXPANDED : WindowSizeClass.ULTRA;
    }

    public static boolean r(int i7, C0761q c0761q) {
        WindowSizeClass windowSizeClassQ = q(c0761q);
        WindowSizeClass windowSizeClass = WindowSizeClass.COMPACT;
        return windowSizeClassQ == windowSizeClass || p(c0761q) == windowSizeClass;
    }

    public static boolean s(int i7, CharSequence charSequence) {
        if (i7 >= charSequence.length()) {
            return false;
        }
        char cCharAt = charSequence.charAt(i7);
        switch (cCharAt) {
            case '!':
            case '\"':
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
                return true;
            default:
                switch (cCharAt) {
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                        return true;
                    default:
                        switch (cCharAt) {
                            case '[':
                            case '\\':
                            case ']':
                            case '^':
                            case '_':
                            case '`':
                                return true;
                            default:
                                switch (cCharAt) {
                                    case '{':
                                    case '|':
                                    case '}':
                                    case '~':
                                        return true;
                                    default:
                                        return false;
                                }
                        }
                }
        }
    }

    public static boolean t() {
        try {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            return runningAppProcessInfo.importance == 100;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static final boolean u(m0.e eVar) {
        float fB = AbstractC1792a.b(eVar.f18886e);
        long j3 = eVar.f18886e;
        if (fB == AbstractC1792a.c(j3)) {
            float fB2 = AbstractC1792a.b(j3);
            long j10 = eVar.f18887f;
            if (fB2 == AbstractC1792a.b(j10) && AbstractC1792a.b(j3) == AbstractC1792a.c(j10)) {
                float fB3 = AbstractC1792a.b(j3);
                long j11 = eVar.f18888g;
                if (fB3 == AbstractC1792a.b(j11) && AbstractC1792a.b(j3) == AbstractC1792a.c(j11)) {
                    float fB4 = AbstractC1792a.b(j3);
                    long j12 = eVar.f18889h;
                    if (fB4 == AbstractC1792a.b(j12) && AbstractC1792a.b(j3) == AbstractC1792a.c(j12)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean v(int i7, C0761q c0761q) {
        WindowSizeClass windowSizeClassQ = q(c0761q);
        WindowSizeClass windowSizeClass = WindowSizeClass.ULTRA;
        return windowSizeClassQ == windowSizeClass || p(c0761q) == windowSizeClass;
    }

    public static boolean w(Double d, boolean z6) {
        return d == null ? z6 : !d.isNaN() && d.doubleValue() >= 0.0d && d.doubleValue() <= 1.0d;
    }

    public static final C1933H x(InterfaceC2782k interfaceC2782k) {
        C1934I c1934i = new C1934I();
        interfaceC2782k.b(c1934i);
        boolean z6 = c1934i.f19833b;
        C1932G c1932g = c1934i.f19832a;
        c1932g.f19822a = z6;
        c1932g.f19823b = c1934i.c;
        int i7 = c1934i.d;
        boolean z10 = c1934i.f19834e;
        boolean z11 = c1934i.f19835f;
        c1932g.c = i7;
        c1932g.d = z10;
        c1932g.f19824e = z11;
        return new C1933H(c1932g.f19822a, c1932g.f19823b, c1932g.c, c1932g.d, c1932g.f19824e, c1932g.f19825f, c1932g.f19826g);
    }

    public static C0050y0 y(Context context, K k10, B b10) {
        String str;
        try {
            PackageInfo packageInfoO = o(context, 0, k10, b10);
            PackageManager packageManager = context.getPackageManager();
            if (packageInfoO != null && packageManager != null) {
                str = packageInfoO.packageName;
                try {
                    String installerPackageName = packageManager.getInstallerPackageName(str);
                    return new C0050y0(installerPackageName, installerPackageName == null);
                } catch (IllegalArgumentException unused) {
                    k10.k(EnumC1495p1.DEBUG, "%s package isn't installed.", str);
                    return null;
                }
            }
        } catch (IllegalArgumentException unused2) {
            str = null;
        }
        return null;
    }

    public static final long z(float f10, long j3) {
        return z.a(Math.max(0.0f, AbstractC1792a.b(j3) - f10), Math.max(0.0f, AbstractC1792a.c(j3) - f10));
    }

    public abstract List f(String str, List list);
}
