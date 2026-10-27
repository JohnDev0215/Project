package c.a.g.e;

import android.text.TextUtils;
import c.a.g.l.l;
import e.a0;
import e.d;
import e.k;
import e.t;
import e.w;
import e.y;
import h.m;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;

/* JADX INFO: compiled from: GcyApiManager.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2667a = "h";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2668b = "https://fota-vehicle-global.iov.byd.auto:6113/ota/otaService/";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2669c = "https://fota-vehicle-global.iov.byd.auto:16113/ota/otaService/";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static SSLSocketFactory f2670d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static SSLSocketFactory f2671e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static TrustManager[] f2672f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static TrustManager[] f2673g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile g f2674h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile f f2675i;

    /* JADX INFO: compiled from: GcyApiManager.java */
    public class b implements t {
        public b() {
        }

        @Override // e.t
        public a0 a(t.a aVar) throws IOException {
            y yVarB = aVar.b();
            c.a.b.d.e.c(h.f2667a, "request.port = " + yVarB.h().y() + ", request.encodedPath = " + yVarB.h().h());
            return aVar.a(yVarB);
        }
    }

    /* JADX INFO: compiled from: GcyApiManager.java */
    public class c implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        public c() {
        }
    }

    public h() {
        d dVar = new t() { // from class: c.a.g.e.d
            @Override // e.t
            public final a0 a(t.a aVar) {
                return h.k(aVar);
            }
        };
        e eVar = new t() { // from class: c.a.g.e.e
            @Override // e.t
            public final a0 a(t.a aVar) {
                return aVar.a(aVar.b().g().c(new d.a().c().d().a()).b());
            }
        };
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.a() { // from class: c.a.g.e.b
            @Override // okhttp3.logging.HttpLoggingInterceptor.a
            public final void a(String str) {
                h.m(str);
            }
        });
        httpLoggingInterceptor.d(HttpLoggingInterceptor.Level.BODY);
        c.b.a.e eVarB = new c.b.a.f().c().e("YYYY-MM-dd HH:mm:ss").d().b();
        k kVarA = new k.a(k.f3594b).f(TlsVersion.TLS_1_2).b("BYD_TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA384").a();
        SSLSocketFactory sSLSocketFactoryF = f();
        f2670d = sSLSocketFactoryF;
        if (sSLSocketFactoryF == null) {
            c.a.b.d.e.b(f2667a, "socketFactory is null!");
            return;
        }
        SSLSocketFactory sSLSocketFactoryG = g();
        f2671e = sSLSocketFactoryG;
        if (sSLSocketFactoryG == null) {
            c.a.b.d.e.b(f2667a, "socketFactorySingle is null!");
            return;
        }
        TrustManager[] trustManagerArr = f2672f;
        if (trustManagerArr.length != 1 || !(trustManagerArr[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(f2672f));
        }
        TrustManager[] trustManagerArr2 = f2673g;
        if (trustManagerArr2.length != 1 || !(trustManagerArr2[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(f2673g));
        }
        w.b bVarT = new w().t();
        bVarT.i(b());
        w.b bVarB = bVarT.g(new HostnameVerifier() { // from class: c.a.g.e.c
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return h.n(str, sSLSession);
            }
        }).f(Collections.singletonList(kVarA)).a(dVar).a(httpLoggingInterceptor).a(new b()).b(eVar);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        f2674h = (g) new m.b().g(bVarB.e(60L, timeUnit).h(60L, timeUnit).j(60L, timeUnit).c()).a(h.p.a.g.d()).b(h.q.a.a.d(eVarB)).c(l.h() ? f2669c : f2668b).e().d(g.class);
        f2675i = (f) new m.b().g(new w().t().g(new HostnameVerifier() { // from class: c.a.g.e.a
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return h.o(str, sSLSession);
            }
        }).a(dVar).b(eVar).e(60L, timeUnit).h(60L, timeUnit).j(60L, timeUnit).c()).a(h.p.a.g.d()).b(h.q.a.a.d(eVarB)).c(l.h() ? f2669c : f2668b).e().d(f.class);
    }

    public static f c() {
        if (f2675i == null) {
            synchronized (h.class) {
                if (f2675i == null) {
                    new h();
                }
            }
        }
        return f2675i;
    }

    public static g d() {
        if (f2674h == null) {
            synchronized (h.class) {
                if (f2674h == null) {
                    new h();
                }
            }
        }
        return f2674h;
    }

    public static /* synthetic */ a0 k(t.a aVar) throws IOException {
        y.a aVarA = aVar.b().g().a("User-Agent", "BYD").a("Content-Type", "application/json;charset=utf-8");
        String strD = c.a.b.c.a.k().D();
        if (!TextUtils.isEmpty(strD)) {
            aVarA.a("Authorization", String.format("basic%1$s", strD));
        }
        return aVar.a(aVarA.b());
    }

    public static /* synthetic */ void m(String str) {
        if (TextUtils.isEmpty(str) || str.contains("iccid") || str.contains("vin") || str.contains("uuid")) {
            return;
        }
        c.a.b.d.e.c(f2667a, "message: " + str);
    }

    public static /* synthetic */ boolean n(String str, SSLSession sSLSession) {
        return true;
    }

    public static /* synthetic */ boolean o(String str, SSLSession sSLSession) {
        return true;
    }

    public static void p(boolean z) {
        c.a.b.d.e.c(f2667a, "isOtaNetTestEnv = " + z);
        f2674h = null;
        f2675i = null;
    }

    public final SSLSocketFactory b() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new c()}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final KeyManager[] e() throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        X509Certificate x509CertificateA = c.a.f.a.b.a("MIIDcjCCAlqgAwIBAgIUU2L49mPQPzxyar1CVPrXhsHLwlwwDQYJKoZIhvcNAQELBQAwUzEfMB0GA1UEAwwWQllEIElkZW50aXR5IFN1YkNBIFJTQTEVMBMGA1UECgwMQllEIElORFVTVFJZMQwwCgYDVQQLDANHSFkxCzAJBgNVBAYTAkNOMB4XDTIyMTAxMjA3MTMyNVoXDTQyMTAwNzA3MTMyNVowNDELMAkGA1UEBhMCQ04xFTATBgNVBAoMDEJZRCBJTkRVU1RSWTEOMAwGA1UEAwwFRFgwMDEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQD9kaHZr3c5SGIBkcRR9bdkLbqZiH+SeaUwjt9kgnWPX59rkEDvR4ieCv8ur+/uSKs3C/NnMSIUoEnE4JDT5CgVZui/CVrMfDasLkI/eSPLqlIuu0fCsmT/qlmxNy1hj5IhWpYBd7fYWaROfqmt59J4gD/X+Hkp4cAQuMRLSmOszjkOsnhxhIamZH21YO0q/e9Exb8erQ6vsOK55AKxUutGIX0DI+hRAWubJKS4DIxY5hVcMBCjA1rT5teVN/KHSqiBQqB79FfSgmLBEajORXXatM/DDK3wIqitb/v/90ZwhXyAwXzpf+Gpi89sMTslNDRDXMQmKheFb8mCSJvXt/LtAgMBAAGjXTBbMAkGA1UdEwQCMAAwDgYDVR0PAQH/BAQDAgbAMB8GA1UdIwQYMBaAFNEaAkVwftT6cBZQNqvKJrgRJGwbMB0GA1UdDgQWBBT/JUuWphOf0AY66QZR2RJAUwWFXjANBgkqhkiG9w0BAQsFAAOCAQEAkRb4Scen+fEmrDibHSTgccJFmZMeIBHpXMGmlh2SUXmxD++TnYwhHdvSZe5e8yu7e3hzY5BUC6WuS4WNPNIlcQRaP3MGk6OAVJflq8YtwNqS3M9lVmPeqMRPGOAH322NZKcl5nmIooz6tbx1tJ6yiJFFW+S6iGccpDMzZIQTEb9m9JYTjSsdNZgBf9X6YptDE1rE3276u4wUHbORlRRWl1wJXcmh/4ag4qrNVM+8UTKt9aDC6aRnUKhjqCSUfyM5avIs54QnrTs/RGZ6E6t0CGPqR+AxipsD608IoLC7TOAs+Q+iynKd4mZMoxg97up73XydmCD6Zj6jVgd7EClcXQ==");
        keyStore.load(null, null);
        keyStore.setCertificateEntry("client", x509CertificateA);
        keyStore.setKeyEntry("prialias", c.a.f.a.b.b("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQD9kaHZr3c5SGIBkcRR9bdkLbqZiH+SeaUwjt9kgnWPX59rkEDvR4ieCv8ur+/uSKs3C/NnMSIUoEnE4JDT5CgVZui/CVrMfDasLkI/eSPLqlIuu0fCsmT/qlmxNy1hj5IhWpYBd7fYWaROfqmt59J4gD/X+Hkp4cAQuMRLSmOszjkOsnhxhIamZH21YO0q/e9Exb8erQ6vsOK55AKxUutGIX0DI+hRAWubJKS4DIxY5hVcMBCjA1rT5teVN/KHSqiBQqB79FfSgmLBEajORXXatM/DDK3wIqitb/v/90ZwhXyAwXzpf+Gpi89sMTslNDRDXMQmKheFb8mCSJvXt/LtAgMBAAECggEACZ+ssY2z9rFFQaNTUiZYmYLAIZzz9Tk3XcpuYWraJLY6X2NliNoBg2NjdVV+w7UaWnJ9AHOgRCwj4JxX+CghT/6h4sOWIKCuP9wEQ6hzWq182lfdTLRnROolkVDzdfp0T15tcbd7e3NGMHx6MCbWRCCJ9nEbGCMrSPcvlrPyEy2FLH1C9n48U0A8+QvuQbjE1bAoQfgRZV+RWsCTHdxbmnZTbccvPVS3ELVpEckcvqywO51cmVK7Z1ll15R6RZd6m0ucfjCqQbNnDWomVn2xKwkq1wOByUKS6GRwQ8ucQdzBBPWvVK6uTm5q50Eb+diccE+VE3EmVJPmgPdT/EAhwQKBgQD/4jG2yd5hBTiW+dVFOtN1tkYFRukMSJBmB7XZ4UEYi4EniqGFMO11yosq3O3UYxcr7mj7tpAnBXRMZj6G4S6LiS4Z86GSTYG6UprYlnMLp4V+waxdO0TPfd6cfvZ5QfbcZbG57L3KLx0zMEs+CWGluYG11194PG0VOyqSYNq19QKBgQD9rysdEwtWZgldgkv8Keb4PYAkqY4BtKFoN0yjPpe+rYc7dIkBtWMrQY7uptEyzT81bBqhyXT66rPhDnygVmfXy3Vcsr65eXEbnpOFBPnEFBFp0ztRhQFfkgcvUAZ9tk80IuE2FZho4KlYEKuKN/ZpqpUh5Qiat1GRZrJ9MQC2GQKBgDUGncDAUooOd+ypdy2yhBGXpSkAydiWzP2nyWKOUFwSxL/59YDRY9QOFgra35ossqeS70SCT5sWgUQ3x+tRjMCzu8768teLoj+HULljbBHVNE/ROLPVTWwmLFSH5vG33r1LgaoCyk20t7tc3arMbOTX7W5OZGVZOE23BhpxYcFlAoGARHi4QVO3lq1Zz8Rwb/J7Zftrj/m325v+iNuMMu+dE5lQy+prZGuqBa3fP2kFRBoD+o5gLdTrXyJkRNWdK8XGp4dB+TBBrFwr/FFz7c5m/9aO0Kpz80/cDAilKc8GwX2oqSDU+r0fcwdYje2J73elaeXh9NEV3nSmboFyobnNQFkCgYEA8Ad9FFL+wOfss8hnksS0y7xCPUm38XTsk3ktJnGgsaP0uqsKtLLGsewSETdnhHCHjzd1xmFpMv3u83+PCzoPvzx+DIJTd4XwpCJAkGz5LBxTB1ERqEcA53U3omNAaBywnToucc0Av5EnqqgMaB3JeKWjbDvdY27V26M/8DETUXc="), null, new Certificate[]{x509CertificateA});
        keyManagerFactory.init(keyStore, null);
        return keyManagerFactory.getKeyManagers();
    }

    public final SSLSocketFactory f() {
        try {
            return h(e(), i()).getSocketFactory();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public final SSLSocketFactory g() {
        try {
            return h(e(), j()).getSocketFactory();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public final SSLContext h(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr) throws Exception {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(keyManagerArr, trustManagerArr, new SecureRandom());
        return sSLContext;
    }

    public final TrustManager[] i() throws Exception {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        X509Certificate x509CertificateA = c.a.f.a.b.a("MIIDjzCCAnegAwIBAgIUKbHwKk9KaDzEG4LwqD+8R9FhhLgwDQYJKoZIhvcNAQELBQAwTDEYMBYGA1UEAwwPQllEIFJvb3QgQ0EgUlNBMRUwEwYDVQQKDAxCWUQgSU5EVVNUUlkxDDAKBgNVBAsMA0dIWTELMAkGA1UEBhMCQ04wIBcNMjIwNDIwMTMzNjQ1WhgPMjA1MjA0MTkxMzM2NDVaMFMxHzAdBgNVBAMMFkJZRCBJZGVudGl0eSBTdWJDQSBSU0ExFTATBgNVBAoMDEJZRCBJTkRVU1RSWTEMMAoGA1UECwwDR0hZMQswCQYDVQQGEwJDTjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMDm9qQD7CPjQSOYj4s0LmhcXwLN7fMcbs47NW8yrwM48l+ywvwBSc/+aZmaHV1ngJDWW8+Vyp2WJ9bpYvuBlbmQgrZEjgFng3WvH/LenOyQ2WM+Frvm4mlCn9H71Oy4S9tBilcHdspsckv/EGiTZtz1rnI/R4rF6wsA6c7sVjkwMhimXlLOMmipQHeIE/5xRd7+uMudfMVkfz+2scEthQjoyNJxwL1krp+E1Hm0QIwpprvaB/1bLOaMqXltHb7pHOioB8SBIL3nTQj08St0F2NVDVcKvU+pGkyKwa3pABZJ5HBYdmHbqqcL7e5pe/FOfEWItW8z0MKncL0qFUqCnqUCAwEAAaNgMF4wHQYDVR0OBBYEFNEaAkVwftT6cBZQNqvKJrgRJGwbMB8GA1UdIwQYMBaAFOmuqIWPWT5sFYuPuR+tJSi76rb0MA8GA1UdEwQIMAYBAf8CAQAwCwYDVR0PBAQDAgIEMA0GCSqGSIb3DQEBCwUAA4IBAQCgt016U2P/4cfIjPBKotMO6UG7jE2+IrQ4ubBUAGK5TxwcgZ2PPUjyJvC67NTSz2aZ28tmN5+JluFojUAdIgsL4018/Fdba4VwlMcvS+rtJDufe+DD0PWNMTn/uWEUGW91IgjPLbkt9H4Ii+dg0tDnd++P1xhs0yImUUe1SlCrjhwZpyW7B1m1fUS7b6Ia4o+E3RFihznXO5DYiSnQ5Gcc2kJDJvjTXZX2UyxIoylvlmKxtPFQHy9yHeA6Z/B6bsxIJFPm+0JMU+bA1uPnhoqkIEdVw1z9fAPiIgdxV3kJPuJiUA4uqiTyIuw1nXEaffofYwvBJW35frFaIo6Mv+VR");
        keyStore.load(null, null);
        keyStore.setCertificateEntry("server", x509CertificateA);
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        f2672f = trustManagers;
        return trustManagers;
    }

    public final TrustManager[] j() throws Exception {
        TrustManager[] trustManagerArr = {new c()};
        f2673g = trustManagerArr;
        return trustManagerArr;
    }
}
