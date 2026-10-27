package h8;

import A.EnumC0017h0;
import A.K0;
import A3.v;
import A7.m;
import B2.O;
import C1.j;
import F0.AbstractC0227s0;
import S8.E;
import T.C0746i0;
import T.C0753m;
import T.C0761q;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.compose.ui.draw.ShadowGraphicsLayerElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import h3.y;
import io.sentry.C1489n1;
import j8.G;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import l8.InterfaceC1788f;
import n0.AbstractC1851A;
import n0.AbstractC1861K;
import n0.C1886o;
import n0.InterfaceC1866P;
import n7.AbstractC1985B;
import n7.AbstractC1991f;
import n7.u;
import o8.C2061f;
import p8.AbstractC2136k;
import p8.C2132g;
import p8.C2133h;
import p8.C2137l;
import p8.C2138m;
import q8.AbstractC2208l;
import r9.o;
import r9.z;
import y.AbstractC2680A;
import y.C2709n;
import y.o0;
import y.p0;
import y.q0;
import y.r0;
import z3.C2764b;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: h8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1386c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15604a = 0;

    public static final boolean a(Object[] objArr, int i7, int i10, List list) {
        if (i10 != list.size()) {
            return false;
        }
        for (int i11 = 0; i11 < i10; i11++) {
            if (!A7.m.a(objArr[i7 + i11], list.get(i11))) {
                return false;
            }
        }
        return true;
    }

    public static final String b(Object[] objArr, int i7, int i10, AbstractC1991f abstractC1991f) {
        StringBuilder sb = new StringBuilder((i10 * 3) + 2);
        sb.append("[");
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i7 + i11];
            if (obj == abstractC1991f) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        A7.m.e(string, "toString(...)");
        return string;
    }

    public static int c(Context context, String str) {
        if (str != null) {
            return (Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : new v1.j(context).f22977b.areNotificationsEnabled() ? 0 : -1;
        }
        throw new NullPointerException("permission must be non-null");
    }

    public static final void d(int i7, int i10) {
        if (i7 <= i10) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i7 + ") is greater than size (" + i10 + ").");
    }

    public static final z3.i e(Context context) {
        final y yVar = new y(context);
        final int i7 = 0;
        m7.n nVarO = h6.r.o(new InterfaceC2772a() { // from class: z3.d
            @Override // z7.InterfaceC2772a
            public final Object a() {
                int largeMemoryClass;
                C3.h hVarA;
                int i10 = 0;
                switch (i7) {
                    case 0:
                        Context context2 = (Context) yVar.f15499m;
                        Bitmap.Config config = N3.d.f4575a;
                        double d = 0.2d;
                        try {
                            Object systemService = context2.getSystemService((Class<Object>) ActivityManager.class);
                            m.c(systemService);
                            if (((ActivityManager) systemService).isLowRamDevice()) {
                                d = 0.15d;
                            }
                        } catch (Exception unused) {
                        }
                        j jVar = new j(3, false);
                        if (d > 0.0d) {
                            Bitmap.Config config2 = N3.d.f4575a;
                            try {
                                Object systemService2 = context2.getSystemService((Class<Object>) ActivityManager.class);
                                m.c(systemService2);
                                ActivityManager activityManager = (ActivityManager) systemService2;
                                largeMemoryClass = (context2.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
                            } catch (Exception unused2) {
                                largeMemoryClass = 256;
                            }
                            double d10 = 1024;
                            i10 = (int) (d * ((double) largeMemoryClass) * d10 * d10);
                            break;
                        }
                        return new I3.c(i10 > 0 ? new C1489n1(i10, jVar) : new O(jVar, 20), jVar);
                    default:
                        y yVar2 = yVar;
                        N3.g gVar = N3.g.f4582a;
                        Context context3 = (Context) yVar2.f15499m;
                        synchronized (gVar) {
                            try {
                                hVarA = N3.g.f4583b;
                                if (hVarA == null) {
                                    C3.a aVar = new C3.a();
                                    aVar.f968b = o.f21939a;
                                    aVar.c = 0.02d;
                                    aVar.d = 10485760L;
                                    aVar.f969e = 262144000L;
                                    Z8.e eVar = E.f8084a;
                                    aVar.f970f = Z8.d.f11010n;
                                    Bitmap.Config config3 = N3.d.f4575a;
                                    File cacheDir = context3.getCacheDir();
                                    if (cacheDir == null) {
                                        throw new IllegalStateException("cacheDir == null");
                                    }
                                    cacheDir.mkdirs();
                                    File fileU = x7.a.u(cacheDir);
                                    String str = z.f21951m;
                                    aVar.f967a = C1886o.j(fileU);
                                    hVarA = aVar.a();
                                    N3.g.f4583b = hVarA;
                                }
                            } finally {
                            }
                        }
                        return hVarA;
                }
            }
        });
        final int i10 = 1;
        m7.n nVarO2 = h6.r.o(new InterfaceC2772a() { // from class: z3.d
            @Override // z7.InterfaceC2772a
            public final Object a() {
                int largeMemoryClass;
                C3.h hVarA;
                int i102 = 0;
                switch (i10) {
                    case 0:
                        Context context2 = (Context) yVar.f15499m;
                        Bitmap.Config config = N3.d.f4575a;
                        double d = 0.2d;
                        try {
                            Object systemService = context2.getSystemService((Class<Object>) ActivityManager.class);
                            m.c(systemService);
                            if (((ActivityManager) systemService).isLowRamDevice()) {
                                d = 0.15d;
                            }
                        } catch (Exception unused) {
                        }
                        j jVar = new j(3, false);
                        if (d > 0.0d) {
                            Bitmap.Config config2 = N3.d.f4575a;
                            try {
                                Object systemService2 = context2.getSystemService((Class<Object>) ActivityManager.class);
                                m.c(systemService2);
                                ActivityManager activityManager = (ActivityManager) systemService2;
                                largeMemoryClass = (context2.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
                            } catch (Exception unused2) {
                                largeMemoryClass = 256;
                            }
                            double d10 = 1024;
                            i102 = (int) (d * ((double) largeMemoryClass) * d10 * d10);
                            break;
                        }
                        return new I3.c(i102 > 0 ? new C1489n1(i102, jVar) : new O(jVar, 20), jVar);
                    default:
                        y yVar2 = yVar;
                        N3.g gVar = N3.g.f4582a;
                        Context context3 = (Context) yVar2.f15499m;
                        synchronized (gVar) {
                            try {
                                hVarA = N3.g.f4583b;
                                if (hVarA == null) {
                                    C3.a aVar = new C3.a();
                                    aVar.f968b = o.f21939a;
                                    aVar.c = 0.02d;
                                    aVar.d = 10485760L;
                                    aVar.f969e = 262144000L;
                                    Z8.e eVar = E.f8084a;
                                    aVar.f970f = Z8.d.f11010n;
                                    Bitmap.Config config3 = N3.d.f4575a;
                                    File cacheDir = context3.getCacheDir();
                                    if (cacheDir == null) {
                                        throw new IllegalStateException("cacheDir == null");
                                    }
                                    cacheDir.mkdirs();
                                    File fileU = x7.a.u(cacheDir);
                                    String str = z.f21951m;
                                    aVar.f967a = C1886o.j(fileU);
                                    hVarA = aVar.a();
                                    N3.g.f4583b = hVarA;
                                }
                            } finally {
                            }
                        }
                        return hVarA;
                }
            }
        });
        m7.n nVarO3 = h6.r.o(new v(20));
        u uVar = u.l;
        return new z3.i((Context) yVar.f15499m, (K3.c) yVar.f15500n, nVarO, nVarO2, nVarO3, new C2764b(uVar, uVar, uVar, uVar, uVar), (N3.f) yVar.o);
    }

    public static final m7.k f(Throwable th) {
        A7.m.f(th, "exception");
        return new m7.k(th);
    }

    public static boolean g(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return true;
        }
        for (File file2 : fileArrListFiles) {
            if (!g(file2)) {
                return false;
            }
        }
        return file.delete();
    }

    public static final Object h(AbstractC2136k abstractC2136k, C2138m c2138m) {
        A7.m.f(abstractC2136k, "<this>");
        A7.m.f(c2138m, "extension");
        if (abstractC2136k.l(c2138m)) {
            return abstractC2136k.k(c2138m);
        }
        return null;
    }

    public static final Object i(AbstractC2136k abstractC2136k, C2138m c2138m, int i7) {
        A7.m.f(abstractC2136k, "<this>");
        A7.m.f(c2138m, "extension");
        abstractC2136k.o(c2138m);
        C2133h c2133h = abstractC2136k.l;
        c2133h.getClass();
        C2137l c2137l = c2138m.d;
        if (!c2137l.f20985n) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        p8.z zVar = c2133h.f20980a;
        Object obj = zVar.get(c2137l);
        if (i7 >= (obj == null ? 0 : ((List) obj).size())) {
            return null;
        }
        abstractC2136k.o(c2138m);
        if (!c2137l.f20985n) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object obj2 = zVar.get(c2137l);
        if (obj2 != null) {
            return c2138m.a(((List) obj2).get(i7));
        }
        throw new IndexOutOfBoundsException();
    }

    public static final p j(G g7, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, boolean z6, boolean z10, boolean z11) {
        A7.m.f(g7, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        C2138m c2138m = m8.k.d;
        A7.m.e(c2138m, "propertySignature");
        m8.e eVar = (m8.e) h(g7, c2138m);
        if (eVar == null) {
            return null;
        }
        if (z6) {
            C2132g c2132g = n8.h.f20171a;
            n8.d dVarB = n8.h.b(g7, interfaceC1788f, c0746i0, z11);
            if (dVarB == null) {
                return null;
            }
            return AbstractC1985B.e(dVarB);
        }
        if (!z10 || (eVar.f19291m & 2) != 2) {
            return null;
        }
        m8.c cVar = eVar.o;
        A7.m.e(cVar, "signature.syntheticMethod");
        return new p(interfaceC1788f.n(cVar.f19281n).concat(interfaceC1788f.n(cVar.o)));
    }

    public static boolean l(Context context, String str) {
        h6.e.q(context, "The application context is required.");
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    public static byte[] m(String str, long j3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        File file = new File(str);
        if (!file.exists()) {
            throw new IOException(android.hardware.bydauto.ac.tsimpl.a.i("File '", file.getName(), "' doesn't exists"));
        }
        if (!file.isFile()) {
            throw new IOException(android.hardware.bydauto.ac.tsimpl.a.i("Reading path ", str, " failed, because it's not a file."));
        }
        if (!file.canRead()) {
            throw new IOException(android.hardware.bydauto.ac.tsimpl.a.i("Reading the item ", str, " failed, because can't read the file."));
        }
        if (file.length() > j3) {
            throw new IOException(String.format("Reading file failed, because size located at '%s' with %d bytes is bigger than the maximum allowed size of %d bytes.", str, Long.valueOf(file.length()), Long.valueOf(j3)));
        }
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bArr = new byte[1024];
                } finally {
                }
            } finally {
            }
            while (true) {
                int i7 = bufferedInputStream.read(bArr);
                if (i7 == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.write(bArr, 0, i7);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String n(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            }
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append("\n");
                sb.append(line2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static final String o(C2061f c2061f) {
        A7.m.f(c2061f, "<this>");
        String strB = c2061f.b();
        A7.m.e(strB, "asString()");
        if (!AbstractC2208l.f21343a.contains(strB)) {
            for (int i7 = 0; i7 < strB.length(); i7++) {
                char cCharAt = strB.charAt(i7);
                if (Character.isLetterOrDigit(cCharAt) || cCharAt == '_') {
                }
            }
            String strB2 = c2061f.b();
            A7.m.e(strB2, "asString()");
            return strB2;
        }
        StringBuilder sb = new StringBuilder();
        String strB3 = c2061f.b();
        A7.m.e(strB3, "asString()");
        sb.append("`".concat(strB3));
        sb.append('`');
        return sb.toString();
    }

    public static final String p(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C2061f c2061f = (C2061f) it.next();
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(o(c2061f));
        }
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String q(String str, String str2, String str3, String str4, String str5) {
        A7.m.f(str, "lowerRendered");
        A7.m.f(str2, "lowerPrefix");
        A7.m.f(str3, "upperRendered");
        A7.m.f(str4, "upperPrefix");
        A7.m.f(str5, "foldedPrefix");
        if (!Q8.o.z0(str, str2, false) || !Q8.o.z0(str3, str4, false)) {
            return null;
        }
        String strSubstring = str.substring(str2.length());
        A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        String strSubstring2 = str3.substring(str4.length());
        A7.m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
        String strConcat = str5.concat(strSubstring);
        if (strSubstring.equals(strSubstring2)) {
            return strConcat;
        }
        if (!v(strSubstring, strSubstring2)) {
            return null;
        }
        return strConcat + '!';
    }

    public static final void r(Object[] objArr, int i7, int i10) {
        A7.m.f(objArr, "<this>");
        while (i7 < i10) {
            objArr[i7] = null;
            i7++;
        }
    }

    public static final g0.o s(g0.o oVar, K0 k02, EnumC0017h0 enumC0017h0, boolean z6, boolean z10, A.r rVar, C.k kVar, C0761q c0761q) {
        r0 r0Var;
        Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
        p0 p0Var = (p0) c0761q.k(q0.f23989a);
        if (p0Var != null) {
            c0761q.Q(1586021609);
            boolean zF = c0761q.f(context) | c0761q.f(p0Var);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new C2709n(context, p0Var);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            r0Var = (C2709n) objG;
        } else {
            c0761q.Q(1586120933);
            c0761q.p(false);
            r0Var = o0.f23977n;
        }
        EnumC0017h0 enumC0017h02 = EnumC0017h0.l;
        g0.o oVarK = oVar.k(enumC0017h0 == enumC0017h02 ? AbstractC2680A.c : AbstractC2680A.f23825b).k(r0Var.d());
        boolean z11 = !z10;
        if (((a1.k) c0761q.k(AbstractC0227s0.l)) == a1.k.f11038m && enumC0017h0 != enumC0017h02) {
            z11 = z10;
        }
        return androidx.compose.foundation.gestures.a.b(oVarK, k02, enumC0017h0, r0Var, z6, z11, rVar, kVar, null);
    }

    public static g0.o t(g0.o oVar, float f10, InterfaceC1866P interfaceC1866P, int i7) {
        boolean z6;
        if ((i7 & 2) != 0) {
            interfaceC1866P = AbstractC1861K.f19381a;
        }
        InterfaceC1866P interfaceC1866P2 = interfaceC1866P;
        if ((i7 & 4) != 0) {
            z6 = Float.compare(f10, (float) 0) > 0;
        } else {
            z6 = false;
        }
        long j3 = AbstractC1851A.f19369a;
        return (Float.compare(f10, (float) 0) > 0 || z6) ? oVar.k(new ShadowGraphicsLayerElement(f10, interfaceC1866P2, z6, j3, j3)) : oVar;
    }

    public static final void u(Object obj) throws Throwable {
        if (obj instanceof m7.k) {
            throw ((m7.k) obj).l;
        }
    }

    public static final boolean v(String str, String str2) {
        A7.m.f(str, "lower");
        A7.m.f(str2, "upper");
        if (!str.equals(Q8.o.x0(str2, "?", "")) && (!Q8.o.s0(str2, "?", false) || !A7.m.a(str.concat("?"), str2))) {
            if (!A7.m.a("(" + str + ")?", str2)) {
                return false;
            }
        }
        return true;
    }
}
