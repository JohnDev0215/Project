package f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h[] f5339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f5340b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f5341c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f5342d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5343e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f5344f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String[] f5345g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String[] f5346h;

    static {
        h[] hVarArr = {h.X0, h.b1, h.Y0, h.c1, h.i1, h.h1, h.y0, h.I0, h.z0, h.J0, h.g0, h.h0, h.E, h.I, h.f5330i};
        f5339a = hVarArr;
        a aVarC = new a(true).c(hVarArr);
        TlsVersion tlsVersion = TlsVersion.TLS_1_0;
        k kVarA = aVarC.f(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, tlsVersion).d(true).a();
        f5340b = kVarA;
        f5341c = new a(kVarA).f(tlsVersion).d(true).a();
        f5342d = new a(false).a();
    }

    public k(a aVar) {
        this.f5343e = aVar.f5347a;
        this.f5345g = aVar.f5348b;
        this.f5346h = aVar.f5349c;
        this.f5344f = aVar.f5350d;
    }

    public static boolean e(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length != 0 && strArr2.length != 0) {
            for (String str : strArr) {
                if (f.b0.c.p(strArr2, str) != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void a(SSLSocket sSLSocket, boolean z) {
        k kVarF = f(sSLSocket, z);
        String[] strArr = kVarF.f5346h;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = kVarF.f5345g;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public List<h> b() {
        if (this.f5345g == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.f5345g.length);
        for (String str : this.f5345g) {
            arrayList.add(h.a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean c(SSLSocket sSLSocket) {
        if (!this.f5343e) {
            return false;
        }
        String[] strArr = this.f5346h;
        if (strArr != null && !e(strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f5345g;
        return strArr2 == null || e(strArr2, sSLSocket.getEnabledCipherSuites());
    }

    public boolean d() {
        return this.f5343e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        boolean z = this.f5343e;
        if (z != kVar.f5343e) {
            return false;
        }
        return !z || (Arrays.equals(this.f5345g, kVar.f5345g) && Arrays.equals(this.f5346h, kVar.f5346h) && this.f5344f == kVar.f5344f);
    }

    public final k f(SSLSocket sSLSocket, boolean z) {
        String[] strArr = this.f5345g;
        String[] enabledCipherSuites = strArr != null ? (String[]) f.b0.c.s(String.class, strArr, sSLSocket.getEnabledCipherSuites()) : sSLSocket.getEnabledCipherSuites();
        String[] strArr2 = this.f5346h;
        String[] enabledProtocols = strArr2 != null ? (String[]) f.b0.c.s(String.class, strArr2, sSLSocket.getEnabledProtocols()) : sSLSocket.getEnabledProtocols();
        if (z && f.b0.c.p(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV") != -1) {
            enabledCipherSuites = f.b0.c.e(enabledCipherSuites, "TLS_FALLBACK_SCSV");
        }
        return new a(this).b(enabledCipherSuites).e(enabledProtocols).a();
    }

    public boolean g() {
        return this.f5344f;
    }

    public List<TlsVersion> h() {
        if (this.f5346h == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.f5346h.length);
        for (String str : this.f5346h) {
            arrayList.add(TlsVersion.forJavaName(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int hashCode() {
        if (this.f5343e) {
            return ((((527 + Arrays.hashCode(this.f5345g)) * 31) + Arrays.hashCode(this.f5346h)) * 31) + (!this.f5344f ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f5343e) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f5345g != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.f5346h != null ? h().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f5344f + ")";
    }

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5347a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String[] f5348b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String[] f5349c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5350d;

        public a(boolean z) {
            this.f5347a = z;
        }

        public k a() {
            return new k(this);
        }

        public a b(String... strArr) {
            if (!this.f5347a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.f5348b = (String[]) strArr.clone();
            return this;
        }

        public a c(h... hVarArr) {
            if (!this.f5347a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i2 = 0; i2 < hVarArr.length; i2++) {
                strArr[i2] = hVarArr[i2].j1;
            }
            return b(strArr);
        }

        public a d(boolean z) {
            if (!this.f5347a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.f5350d = z;
            return this;
        }

        public a e(String... strArr) {
            if (!this.f5347a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f5349c = (String[]) strArr.clone();
            return this;
        }

        public a f(TlsVersion... tlsVersionArr) {
            if (!this.f5347a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[tlsVersionArr.length];
            for (int i2 = 0; i2 < tlsVersionArr.length; i2++) {
                strArr[i2] = tlsVersionArr[i2].javaName;
            }
            return e(strArr);
        }

        public a(k kVar) {
            this.f5347a = kVar.f5343e;
            this.f5348b = kVar.f5345g;
            this.f5349c = kVar.f5346h;
            this.f5350d = kVar.f5344f;
        }
    }
}
