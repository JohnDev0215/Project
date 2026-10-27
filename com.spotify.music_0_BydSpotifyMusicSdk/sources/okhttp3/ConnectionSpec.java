package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import p.jm1;
import p.ou;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionSpec {
    public static final ConnectionSpec e;
    public static final ConnectionSpec f;
    public final boolean a;
    public final boolean b;
    public final String[] c;
    public final String[] d;

    public static final class Builder {
        public boolean a;
        public String[] b;
        public String[] c;
        public boolean d;

        public Builder(boolean z) {
            this.a = z;
        }

        public final ConnectionSpec a() {
            return new ConnectionSpec(this.a, this.d, this.b, this.c);
        }

        public final void b(String... strArr) {
            v03.j(strArr, "cipherSuites");
            if (!this.a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            if (!(!(strArr.length == 0))) {
                throw new IllegalArgumentException("At least one cipher suite is required".toString());
            }
            this.b = (String[]) strArr.clone();
        }

        public final void c(CipherSuite... cipherSuiteArr) {
            v03.j(cipherSuiteArr, "cipherSuites");
            if (!this.a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(cipherSuiteArr.length);
            for (CipherSuite cipherSuite : cipherSuiteArr) {
                arrayList.add(cipherSuite.a);
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            b((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        public final void d() {
            if (!this.a) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections".toString());
            }
            this.d = true;
        }

        public final void e(String... strArr) {
            v03.j(strArr, "tlsVersions");
            if (!this.a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            if (!(!(strArr.length == 0))) {
                throw new IllegalArgumentException("At least one TLS version is required".toString());
            }
            this.c = (String[]) strArr.clone();
        }

        public final void f(TlsVersion... tlsVersionArr) {
            if (!this.a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(tlsVersionArr.length);
            for (TlsVersion tlsVersion : tlsVersionArr) {
                arrayList.add(tlsVersion.a());
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            e((String[]) Arrays.copyOf(strArr, strArr.length));
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        CipherSuite cipherSuite = CipherSuite.r;
        CipherSuite cipherSuite2 = CipherSuite.s;
        CipherSuite cipherSuite3 = CipherSuite.t;
        CipherSuite cipherSuite4 = CipherSuite.l;
        CipherSuite cipherSuite5 = CipherSuite.n;
        CipherSuite cipherSuite6 = CipherSuite.m;
        CipherSuite cipherSuite7 = CipherSuite.o;
        CipherSuite cipherSuite8 = CipherSuite.q;
        CipherSuite cipherSuite9 = CipherSuite.f92p;
        CipherSuite[] cipherSuiteArr = {cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9, CipherSuite.j, CipherSuite.k, CipherSuite.h, CipherSuite.i, CipherSuite.f, CipherSuite.g, CipherSuite.e};
        Builder builder = new Builder(true);
        builder.c((CipherSuite[]) Arrays.copyOf(new CipherSuite[]{cipherSuite, cipherSuite2, cipherSuite3, cipherSuite4, cipherSuite5, cipherSuite6, cipherSuite7, cipherSuite8, cipherSuite9}, 9));
        TlsVersion tlsVersion = TlsVersion.TLS_1_3;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_2;
        builder.f(tlsVersion, tlsVersion2);
        builder.d();
        builder.a();
        Builder builder2 = new Builder(true);
        builder2.c((CipherSuite[]) Arrays.copyOf(cipherSuiteArr, 16));
        builder2.f(tlsVersion, tlsVersion2);
        builder2.d();
        e = builder2.a();
        Builder builder3 = new Builder(true);
        builder3.c((CipherSuite[]) Arrays.copyOf(cipherSuiteArr, 16));
        builder3.f(tlsVersion, tlsVersion2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        builder3.d();
        builder3.a();
        f = new Builder(false).a();
    }

    public ConnectionSpec(boolean z, boolean z2, String[] strArr, String[] strArr2) {
        this.a = z;
        this.b = z2;
        this.c = strArr;
        this.d = strArr2;
    }

    public final List a() {
        String[] strArr = this.c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(CipherSuite.b.b(str));
        }
        return ou.n0(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !Util.j(strArr, sSLSocket.getEnabledProtocols(), jm1.c)) {
            return false;
        }
        String[] strArr2 = this.c;
        if (strArr2 == null) {
            return true;
        }
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        CipherSuite.b.getClass();
        return Util.j(strArr2, enabledCipherSuites, CipherSuite.c);
    }

    public final List c() {
        String[] strArr = this.d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            TlsVersion.c.getClass();
            arrayList.add(TlsVersion.Companion.a(str));
        }
        return ou.n0(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec connectionSpec = (ConnectionSpec) obj;
        boolean z = connectionSpec.a;
        boolean z2 = this.a;
        if (z2 != z) {
            return false;
        }
        return !z2 || (Arrays.equals(this.c, connectionSpec.c) && Arrays.equals(this.d, connectionSpec.d) && this.b == connectionSpec.b);
    }

    public final int hashCode() {
        if (!this.a) {
            return 17;
        }
        String[] strArr = this.c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.b ? 1 : 0);
    }

    public final String toString() {
        if (!this.a) {
            return "ConnectionSpec()";
        }
        StringBuilder sb = new StringBuilder("ConnectionSpec(cipherSuites=");
        sb.append(Objects.toString(a(), "[all enabled]"));
        sb.append(", tlsVersions=");
        sb.append(Objects.toString(c(), "[all enabled]"));
        sb.append(", supportsTlsExtensions=");
        return u4.n(sb, this.b, ')');
    }
}
