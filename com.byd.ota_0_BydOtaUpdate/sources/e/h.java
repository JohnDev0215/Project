package e;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: compiled from: CipherSuite.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static final h A;
    public static final h A0;
    public static final h B;
    public static final h B0;
    public static final h C;
    public static final h C0;
    public static final h D;
    public static final h D0;
    public static final h E;
    public static final h E0;
    public static final h F;
    public static final h F0;
    public static final h G;
    public static final h G0;
    public static final h H;
    public static final h H0;
    public static final h I;
    public static final h I0;
    public static final h J;
    public static final h J0;
    public static final h K;
    public static final h K0;
    public static final h L;
    public static final h L0;
    public static final h M;
    public static final h M0;
    public static final h N;
    public static final h N0;
    public static final h O;
    public static final h O0;
    public static final h P;
    public static final h P0;
    public static final h Q;
    public static final h Q0;
    public static final h R;
    public static final h R0;
    public static final h S;
    public static final h S0;
    public static final h T;
    public static final h T0;
    public static final h U;
    public static final h U0;
    public static final h V;
    public static final h V0;
    public static final h W;
    public static final h W0;
    public static final h X;
    public static final h X0;
    public static final h Y;
    public static final h Y0;
    public static final h Z;
    public static final h Z0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator<String> f3576a;
    public static final h a0;
    public static final h a1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<String, h> f3577b;
    public static final h b0;
    public static final h b1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f3578c;
    public static final h c0;
    public static final h c1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f3579d;
    public static final h d0;
    public static final h d1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f3580e;
    public static final h e0;
    public static final h e1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final h f3581f;
    public static final h f0;
    public static final h f1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f3582g;
    public static final h g0;
    public static final h g1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f3583h;
    public static final h h0;
    public static final h h1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f3584i;
    public static final h i0;
    public static final h i1;
    public static final h j;
    public static final h j0;
    public static final h j1;
    public static final h k;
    public static final h k0;
    public static final h l;
    public static final h l0;
    public static final h m;
    public static final h m0;
    public static final h n;
    public static final h n0;
    public static final h o;
    public static final h o0;
    public static final h p;
    public static final h p0;
    public static final h q;
    public static final h q0;
    public static final h r;
    public static final h r0;
    public static final h s;
    public static final h s0;
    public static final h t;
    public static final h t0;
    public static final h u;
    public static final h u0;
    public static final h v;
    public static final h v0;
    public static final h w;
    public static final h w0;
    public static final h x;
    public static final h x0;
    public static final h y;
    public static final h y0;
    public static final h z;
    public static final h z0;
    public final String k1;

    /* JADX INFO: compiled from: CipherSuite.java */
    public class a implements Comparator<String> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            int iMin = Math.min(str.length(), str2.length());
            for (int i2 = 4; i2 < iMin; i2++) {
                char cCharAt = str.charAt(i2);
                char cCharAt2 = str2.charAt(i2);
                if (cCharAt != cCharAt2) {
                    return cCharAt < cCharAt2 ? -1 : 1;
                }
            }
            int length = str.length();
            int length2 = str2.length();
            if (length != length2) {
                return length < length2 ? -1 : 1;
            }
            return 0;
        }
    }

    static {
        a aVar = new a();
        f3576a = aVar;
        f3577b = new TreeMap(aVar);
        f3578c = c("SSL_RSA_WITH_NULL_MD5", 1);
        f3579d = c("SSL_RSA_WITH_NULL_SHA", 2);
        f3580e = c("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
        f3581f = c("SSL_RSA_WITH_RC4_128_MD5", 4);
        f3582g = c("SSL_RSA_WITH_RC4_128_SHA", 5);
        f3583h = c("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
        f3584i = c("SSL_RSA_WITH_DES_CBC_SHA", 9);
        j = c("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        k = c("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
        l = c("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
        m = c("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        n = c("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
        o = c("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
        p = c("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        q = c("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
        r = c("SSL_DH_anon_WITH_RC4_128_MD5", 24);
        s = c("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
        t = c("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
        u = c("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
        v = c("TLS_KRB5_WITH_DES_CBC_SHA", 30);
        w = c("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
        x = c("TLS_KRB5_WITH_RC4_128_SHA", 32);
        y = c("TLS_KRB5_WITH_DES_CBC_MD5", 34);
        z = c("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
        A = c("TLS_KRB5_WITH_RC4_128_MD5", 36);
        B = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
        C = c("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
        D = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
        E = c("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
        F = c("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
        G = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        H = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        I = c("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
        J = c("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        K = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        L = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        M = c("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
        N = c("TLS_RSA_WITH_NULL_SHA256", 59);
        O = c("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        P = c("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        Q = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
        R = c("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        S = c("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        T = c("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        U = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        V = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        W = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        X = c("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
        Y = c("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
        Z = c("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
        a0 = c("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
        b0 = c("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
        c0 = c("TLS_PSK_WITH_RC4_128_SHA", 138);
        d0 = c("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
        e0 = c("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
        f0 = c("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
        g0 = c("TLS_RSA_WITH_SEED_CBC_SHA", 150);
        h0 = c("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
        i0 = c("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
        j0 = c("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
        k0 = c("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
        l0 = c("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
        m0 = c("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
        n0 = c("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
        o0 = c("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
        p0 = c("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
        q0 = c("TLS_FALLBACK_SCSV", 22016);
        r0 = c("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
        s0 = c("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
        t0 = c("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
        u0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
        v0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
        w0 = c("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
        x0 = c("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
        y0 = c("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
        z0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
        A0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
        B0 = c("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
        C0 = c("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
        D0 = c("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
        E0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
        F0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
        G0 = c("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
        H0 = c("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
        I0 = c("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
        J0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
        K0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
        L0 = c("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
        M0 = c("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
        N0 = c("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
        O0 = c("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
        P0 = c("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
        Q0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
        R0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
        S0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
        T0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
        U0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
        V0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
        W0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
        X0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
        Y0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
        Z0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
        a1 = c("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
        b1 = c("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
        c1 = c("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
        d1 = c("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
        e1 = c("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
        f1 = c("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
        g1 = c("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
        h1 = c("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
        i1 = c("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
        j1 = c("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    }

    public h(String str) {
        Objects.requireNonNull(str);
        this.k1 = str;
    }

    public static synchronized h a(String str) {
        h hVar;
        Map<String, h> map = f3577b;
        hVar = map.get(str);
        if (hVar == null) {
            hVar = new h(str);
            map.put(str, hVar);
        }
        return hVar;
    }

    public static List<h> b(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static h c(String str, int i2) {
        return a(str);
    }

    public String toString() {
        return this.k1;
    }
}
