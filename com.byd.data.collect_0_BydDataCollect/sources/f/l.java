package f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: CipherSuite.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {
    final String r1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Comparator<String> f5451a = new Comparator() { // from class: f.b
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return l.d((String) obj, (String) obj2);
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<String, l> f5452b = new LinkedHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f5453c = c("SSL_RSA_WITH_NULL_MD5", 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final l f5454d = c("SSL_RSA_WITH_NULL_SHA", 2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final l f5455e = c("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final l f5456f = c("SSL_RSA_WITH_RC4_128_MD5", 4);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final l f5457g = c("SSL_RSA_WITH_RC4_128_SHA", 5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final l f5458h = c("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l f5459i = c("SSL_RSA_WITH_DES_CBC_SHA", 9);
    public static final l j = c("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    public static final l k = c("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    public static final l l = c("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    public static final l m = c("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    public static final l n = c("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    public static final l o = c("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    public static final l p = c("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    public static final l q = c("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    public static final l r = c("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    public static final l s = c("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    public static final l t = c("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    public static final l u = c("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    public static final l v = c("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    public static final l w = c("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    public static final l x = c("TLS_KRB5_WITH_RC4_128_SHA", 32);
    public static final l y = c("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final l z = c("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final l A = c("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final l B = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final l C = c("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final l D = c("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final l E = c("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final l F = c("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final l G = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final l H = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final l I = c("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final l J = c("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final l K = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final l L = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final l M = c("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final l N = c("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final l O = c("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final l P = c("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final l Q = c("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final l R = c("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final l S = c("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final l T = c("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final l U = c("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final l V = c("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final l W = c("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final l X = c("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final l Y = c("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    public static final l Z = c("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
    public static final l a0 = c("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    public static final l b0 = c("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    public static final l c0 = c("TLS_PSK_WITH_RC4_128_SHA", 138);
    public static final l d0 = c("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
    public static final l e0 = c("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
    public static final l f0 = c("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
    public static final l g0 = c("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    public static final l h0 = c("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    public static final l i0 = c("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    public static final l j0 = c("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final l k0 = c("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    public static final l l0 = c("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
    public static final l m0 = c("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
    public static final l n0 = c("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
    public static final l o0 = c("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
    public static final l p0 = c("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final l q0 = c("TLS_FALLBACK_SCSV", 22016);
    public static final l r0 = c("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    public static final l s0 = c("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    public static final l t0 = c("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    public static final l u0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    public static final l v0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    public static final l w0 = c("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    public static final l x0 = c("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    public static final l y0 = c("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    public static final l z0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    public static final l A0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    public static final l B0 = c("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    public static final l C0 = c("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    public static final l D0 = c("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    public static final l E0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    public static final l F0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    public static final l G0 = c("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    public static final l H0 = c("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    public static final l I0 = c("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    public static final l J0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    public static final l K0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    public static final l L0 = c("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    public static final l M0 = c("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    public static final l N0 = c("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    public static final l O0 = c("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    public static final l P0 = c("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    public static final l Q0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    public static final l R0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    public static final l S0 = c("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    public static final l T0 = c("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    public static final l U0 = c("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    public static final l V0 = c("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    public static final l W0 = c("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    public static final l X0 = c("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    public static final l Y0 = c("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    public static final l Z0 = c("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    public static final l a1 = c("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    public static final l b1 = c("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    public static final l c1 = c("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    public static final l d1 = c("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    public static final l e1 = c("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    public static final l f1 = c("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    public static final l g1 = c("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    public static final l h1 = c("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    public static final l i1 = c("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final l j1 = c("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    public static final l k1 = c("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52394);
    public static final l l1 = c("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 52396);
    public static final l m1 = c("TLS_AES_128_GCM_SHA256", 4865);
    public static final l n1 = c("TLS_AES_256_GCM_SHA384", 4866);
    public static final l o1 = c("TLS_CHACHA20_POLY1305_SHA256", 4867);
    public static final l p1 = c("TLS_AES_128_CCM_SHA256", 4868);
    public static final l q1 = c("TLS_AES_128_CCM_8_SHA256", 4869);

    private l(String str) {
        Objects.requireNonNull(str);
        this.r1 = str;
    }

    public static synchronized l a(String str) {
        l lVar;
        Map<String, l> map = f5452b;
        lVar = map.get(str);
        if (lVar == null) {
            lVar = map.get(e(str));
            if (lVar == null) {
                lVar = new l(str);
            }
            map.put(str, lVar);
        }
        return lVar;
    }

    static List<l> b(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static l c(String str, int i2) {
        l lVar = new l(str);
        f5452b.put(str, lVar);
        return lVar;
    }

    static /* synthetic */ int d(String str, String str2) {
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

    private static String e(String str) {
        if (str.startsWith("TLS_")) {
            return "SSL_" + str.substring(4);
        }
        if (!str.startsWith("SSL_")) {
            return str;
        }
        return "TLS_" + str.substring(4);
    }

    public String toString() {
        return this.r1;
    }
}
