package f;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: CipherSuite.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {
    public final String j1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentMap<String, h> f5322a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f5323b = b("SSL_RSA_WITH_NULL_MD5", 1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f5324c = b("SSL_RSA_WITH_NULL_SHA", 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h f5325d = b("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f5326e = b("SSL_RSA_WITH_RC4_128_MD5", 4);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final h f5327f = b("SSL_RSA_WITH_RC4_128_SHA", 5);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f5328g = b("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f5329h = b("SSL_RSA_WITH_DES_CBC_SHA", 9);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f5330i = b("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    public static final h j = b("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    public static final h k = b("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    public static final h l = b("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    public static final h m = b("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    public static final h n = b("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    public static final h o = b("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    public static final h p = b("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    public static final h q = b("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    public static final h r = b("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    public static final h s = b("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    public static final h t = b("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    public static final h u = b("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    public static final h v = b("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    public static final h w = b("TLS_KRB5_WITH_RC4_128_SHA", 32);
    public static final h x = b("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final h y = b("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final h z = b("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final h A = b("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final h B = b("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final h C = b("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final h D = b("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final h E = b("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final h F = b("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final h G = b("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final h H = b("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final h I = b("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final h J = b("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final h K = b("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final h L = b("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final h M = b("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final h N = b("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final h O = b("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final h P = b("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final h Q = b("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final h R = b("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final h S = b("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final h T = b("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final h U = b("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final h V = b("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final h W = b("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final h X = b("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    public static final h Y = b("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
    public static final h Z = b("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    public static final h a0 = b("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    public static final h b0 = b("TLS_PSK_WITH_RC4_128_SHA", 138);
    public static final h c0 = b("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
    public static final h d0 = b("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
    public static final h e0 = b("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
    public static final h f0 = b("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    public static final h g0 = b("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    public static final h h0 = b("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    public static final h i0 = b("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final h j0 = b("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    public static final h k0 = b("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
    public static final h l0 = b("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
    public static final h m0 = b("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
    public static final h n0 = b("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
    public static final h o0 = b("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final h p0 = b("TLS_FALLBACK_SCSV", 22016);
    public static final h q0 = b("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    public static final h r0 = b("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    public static final h s0 = b("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    public static final h t0 = b("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    public static final h u0 = b("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    public static final h v0 = b("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    public static final h w0 = b("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    public static final h x0 = b("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    public static final h y0 = b("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    public static final h z0 = b("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    public static final h A0 = b("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    public static final h B0 = b("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    public static final h C0 = b("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    public static final h D0 = b("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    public static final h E0 = b("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    public static final h F0 = b("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    public static final h G0 = b("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    public static final h H0 = b("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    public static final h I0 = b("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    public static final h J0 = b("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    public static final h K0 = b("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    public static final h L0 = b("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    public static final h M0 = b("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    public static final h N0 = b("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    public static final h O0 = b("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    public static final h P0 = b("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    public static final h Q0 = b("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    public static final h R0 = b("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    public static final h S0 = b("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    public static final h T0 = b("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    public static final h U0 = b("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    public static final h V0 = b("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    public static final h W0 = b("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    public static final h X0 = b("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    public static final h Y0 = b("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    public static final h Z0 = b("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    public static final h a1 = b("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    public static final h b1 = b("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    public static final h c1 = b("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    public static final h d1 = b("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    public static final h e1 = b("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    public static final h f1 = b("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    public static final h g1 = b("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    public static final h h1 = b("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final h i1 = b("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);

    public h(String str) {
        Objects.requireNonNull(str);
        this.j1 = str;
    }

    public static h a(String str) {
        ConcurrentMap<String, h> concurrentMap = f5322a;
        h hVar = concurrentMap.get(str);
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h(str);
        h hVarPutIfAbsent = concurrentMap.putIfAbsent(str, hVar2);
        return hVarPutIfAbsent == null ? hVar2 : hVarPutIfAbsent;
    }

    public static h b(String str, int i2) {
        return a(str);
    }

    public String toString() {
        return this.j1;
    }
}
