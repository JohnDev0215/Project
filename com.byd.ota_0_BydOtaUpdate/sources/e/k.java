package e;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h[] f3593a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f3594b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f3595c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f3596d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f3597e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f3598f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    public final String[] f3599g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Nullable
    public final String[] f3600h;

    static {
        h[] hVarArr = {h.Y0, h.c1, h.Z0, h.d1, h.j1, h.i1, h.z0, h.J0, h.A0, h.K0, h.h0, h.i0, h.F, h.J, h.j};
        f3593a = hVarArr;
        a aVarC = new a(true).c(hVarArr);
        TlsVersion tlsVersion = TlsVersion.TLS_1_0;
        k kVarA = aVarC.f(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, tlsVersion).d(true).a();
        f3594b = kVarA;
        f3595c = new a(kVarA).f(tlsVersion).d(true).a();
        f3596d = new a(false).a();
    }

    public k(a aVar) {
        this.f3597e = aVar.f3601a;
        this.f3599g = aVar.f3602b;
        this.f3600h = aVar.f3603c;
        this.f3598f = aVar.f3604d;
    }

    public void a(SSLSocket sSLSocket, boolean z) {
        k kVarE = e(sSLSocket, z);
        String[] strArr = kVarE.f3600h;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = kVarE.f3599g;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    @Nullable
    public List<h> b() {
        String[] strArr = this.f3599g;
        if (strArr != null) {
            return h.b(strArr);
        }
        return null;
    }

    public boolean c(SSLSocket sSLSocket) {
        if (!this.f3597e) {
            return false;
        }
        String[] strArr = this.f3600h;
        if (strArr != null && !e.d0.c.t(e.d0.c.p, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f3599g;
        return strArr2 == null || e.d0.c.t(h.f3576a, strArr2, sSLSocket.getEnabledCipherSuites());
    }

    public boolean d() {
        return this.f3597e;
    }

    public final k e(SSLSocket sSLSocket, boolean z) {
        String[] strArrR = this.f3599g != null ? e.d0.c.r(h.f3576a, sSLSocket.getEnabledCipherSuites(), this.f3599g) : sSLSocket.getEnabledCipherSuites();
        String[] strArrR2 = this.f3600h != null ? e.d0.c.r(e.d0.c.p, sSLSocket.getEnabledProtocols(), this.f3600h) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iP = e.d0.c.p(h.f3576a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iP != -1) {
            strArrR = e.d0.c.e(strArrR, supportedCipherSuites[iP]);
        }
        return new a(this).b(strArrR).e(strArrR2).a();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        boolean z = this.f3597e;
        if (z != kVar.f3597e) {
            return false;
        }
        return !z || (Arrays.equals(this.f3599g, kVar.f3599g) && Arrays.equals(this.f3600h, kVar.f3600h) && this.f3598f == kVar.f3598f);
    }

    public boolean f() {
        return this.f3598f;
    }

    @Nullable
    public List<TlsVersion> g() {
        String[] strArr = this.f3600h;
        if (strArr != null) {
            return TlsVersion.forJavaNames(strArr);
        }
        return null;
    }

    public int hashCode() {
        if (this.f3597e) {
            return ((((527 + Arrays.hashCode(this.f3599g)) * 31) + Arrays.hashCode(this.f3600h)) * 31) + (!this.f3598f ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f3597e) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f3599g != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.f3600h != null ? g().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f3598f + ")";
    }

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3601a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        public String[] f3602b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        public String[] f3603c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3604d;

        public a(boolean z) {
            this.f3601a = z;
        }

        public k a() {
            return new k(this);
        }

        public a b(String... strArr) {
            if (!this.f3601a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.f3602b = (String[]) strArr.clone();
            return this;
        }

        public a c(h... hVarArr) {
            if (!this.f3601a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i2 = 0; i2 < hVarArr.length; i2++) {
                strArr[i2] = hVarArr[i2].k1;
            }
            return b(strArr);
        }

        public a d(boolean z) {
            if (!this.f3601a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.f3604d = z;
            return this;
        }

        public a e(String... strArr) {
            if (!this.f3601a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f3603c = (String[]) strArr.clone();
            return this;
        }

        public a f(TlsVersion... tlsVersionArr) {
            if (!this.f3601a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[tlsVersionArr.length];
            for (int i2 = 0; i2 < tlsVersionArr.length; i2++) {
                strArr[i2] = tlsVersionArr[i2].javaName;
            }
            return e(strArr);
        }

        public a(k kVar) {
            this.f3601a = kVar.f3597e;
            this.f3602b = kVar.f3599g;
            this.f3603c = kVar.f3600h;
            this.f3604d = kVar.f3598f;
        }
    }
}
