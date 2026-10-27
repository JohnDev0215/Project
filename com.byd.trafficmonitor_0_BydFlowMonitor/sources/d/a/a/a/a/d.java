package d.a.a.a.a;

import external.org.apache.commons.lang3.JavaVersion;

/* JADX INFO: compiled from: SystemUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static final String A;
    public static final String B;
    public static final String C;
    public static final String D;
    public static final String E;
    public static final String F;
    public static final String G;
    public static final String H;
    public static final String I;
    public static final String J;
    public static final String K;
    public static final String L;
    public static final String M;
    public static final String N;
    public static final String O;
    public static final String P;
    public static final String Q;
    public static final boolean R;
    public static final boolean S;
    public static final boolean T;
    public static final boolean U;
    public static final boolean V;
    public static final boolean W;
    public static final boolean X;
    public static final boolean Y;
    public static final boolean Z;
    public static final boolean a0;
    public static final boolean b0;
    public static final boolean c0;
    public static final boolean d0;
    public static final boolean e0;
    public static final boolean f0;
    public static final boolean g0;
    public static final boolean h0;
    public static final boolean i0;
    public static final boolean j0;
    public static final boolean k0;
    public static final boolean l0;
    public static final boolean m0;
    public static final boolean n0;
    public static final boolean o0;
    public static final boolean p0;
    public static final boolean q0;
    public static final boolean r0;
    public static final boolean s0;
    public static final String t;
    public static final boolean t0;
    public static final JavaVersion u;
    public static final boolean u0;
    public static final String v;
    public static final boolean v0;
    public static final String w;
    public static final String x;
    public static final String y;
    public static final String z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4969a = d("awt.toolkit");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f4970b = d("file.encoding");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f4971c = d("file.separator");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f4972d = d("java.awt.fonts");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f4973e = d("java.awt.graphicsenv");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f4974f = d("java.awt.headless");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f4975g = d("java.awt.printerjob");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f4976h = d("java.class.path");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f4977i = d("java.class.version");
    public static final String j = d("java.compiler");
    public static final String k = d("java.endorsed.dirs");
    public static final String l = d("java.ext.dirs");
    public static final String m = d("java.home");
    public static final String n = d("java.io.tmpdir");
    public static final String o = d("java.library.path");
    public static final String p = d("java.runtime.name");
    public static final String q = d("java.runtime.version");
    public static final String r = d("java.specification.name");
    public static final String s = d("java.specification.vendor");

    static {
        String strD = d("java.specification.version");
        t = strD;
        u = JavaVersion.get(strD);
        v = d("java.util.prefs.PreferencesFactory");
        w = d("java.vendor");
        x = d("java.vendor.url");
        y = d("java.version");
        z = d("java.vm.info");
        A = d("java.vm.name");
        B = d("java.vm.specification.name");
        C = d("java.vm.specification.vendor");
        D = d("java.vm.specification.version");
        E = d("java.vm.vendor");
        F = d("java.vm.version");
        G = d("line.separator");
        H = d("os.arch");
        I = d("os.name");
        J = d("os.version");
        K = d("path.separator");
        L = d(d("user.country") == null ? "user.region" : "user.country");
        M = d("user.dir");
        N = d("user.home");
        O = d("user.language");
        P = d("user.name");
        Q = d("user.timezone");
        R = a("1.1");
        S = a("1.2");
        T = a("1.3");
        U = a("1.4");
        V = a("1.5");
        W = a("1.6");
        X = a("1.7");
        boolean zC = c("AIX");
        Y = zC;
        boolean zC2 = c("HP-UX");
        Z = zC2;
        boolean zC3 = c("Irix");
        a0 = zC3;
        boolean z2 = c("Linux") || c("LINUX");
        b0 = z2;
        c0 = c("Mac");
        boolean zC4 = c("Mac OS X");
        d0 = zC4;
        boolean zC5 = c("FreeBSD");
        e0 = zC5;
        boolean zC6 = c("OpenBSD");
        f0 = zC6;
        boolean zC7 = c("NetBSD");
        g0 = zC7;
        h0 = c("OS/2");
        boolean zC8 = c("Solaris");
        i0 = zC8;
        boolean zC9 = c("SunOS");
        j0 = zC9;
        k0 = zC || zC2 || zC3 || z2 || zC4 || zC8 || zC9 || zC5 || zC6 || zC7;
        l0 = c("Windows");
        m0 = b("Windows", "5.0");
        n0 = b("Windows", "5.2");
        o0 = b("Windows Server 2008", "6.1");
        p0 = b("Windows 9", "4.0");
        q0 = b("Windows 9", "4.1");
        r0 = b("Windows", "4.9");
        s0 = c("Windows NT");
        t0 = b("Windows", "5.1");
        u0 = b("Windows", "6.0");
        v0 = b("Windows", "6.1");
    }

    public static boolean a(String str) {
        return f(t, str);
    }

    public static boolean b(String str, String str2) {
        return g(I, J, str, str2);
    }

    public static boolean c(String str) {
        return h(I, str);
    }

    public static String d(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            System.err.println("Caught a SecurityException reading the system property '" + str + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    public static boolean e(JavaVersion javaVersion) {
        return u.atLeast(javaVersion);
    }

    public static boolean f(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    public static boolean g(String str, String str2, String str3, String str4) {
        return str != null && str2 != null && str.startsWith(str3) && str2.startsWith(str4);
    }

    public static boolean h(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }
}
