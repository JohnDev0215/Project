package c6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final i[] f3793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final i[] f3794f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final l f3795g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final l f3796h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l f3797i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final l f3798j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final b f3799k = new b(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f3800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f3801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String[] f3802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f3803d;

    /* JADX INFO: compiled from: ConnectionSpec.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3804a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String[] f3805b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String[] f3806c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f3807d;

        public a(boolean z6) {
            this.f3804a = z6;
        }

        public final l a() {
            return new l(this.f3804a, this.f3807d, this.f3805b, this.f3806c);
        }

        public final a b(i... cipherSuites) {
            kotlin.jvm.internal.l.e(cipherSuites, "cipherSuites");
            if (!this.f3804a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(cipherSuites.length);
            for (i iVar : cipherSuites) {
                arrayList.add(iVar.c());
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            String[] strArr = (String[]) array;
            return c((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        public final a c(String... cipherSuites) throws CloneNotSupportedException {
            kotlin.jvm.internal.l.e(cipherSuites, "cipherSuites");
            if (!this.f3804a) {
                throw new IllegalArgumentException("no cipher suites for cleartext connections".toString());
            }
            if (!(!(cipherSuites.length == 0))) {
                throw new IllegalArgumentException("At least one cipher suite is required".toString());
            }
            Object objClone = cipherSuites.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.f3805b = (String[]) objClone;
            return this;
        }

        public final a d(boolean z6) {
            if (!this.f3804a) {
                throw new IllegalArgumentException("no TLS extensions for cleartext connections".toString());
            }
            this.f3807d = z6;
            return this;
        }

        public final a e(e0... tlsVersions) {
            kotlin.jvm.internal.l.e(tlsVersions, "tlsVersions");
            if (!this.f3804a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            ArrayList arrayList = new ArrayList(tlsVersions.length);
            for (e0 e0Var : tlsVersions) {
                arrayList.add(e0Var.javaName());
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            String[] strArr = (String[]) array;
            return f((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        public final a f(String... tlsVersions) throws CloneNotSupportedException {
            kotlin.jvm.internal.l.e(tlsVersions, "tlsVersions");
            if (!this.f3804a) {
                throw new IllegalArgumentException("no TLS versions for cleartext connections".toString());
            }
            if (!(!(tlsVersions.length == 0))) {
                throw new IllegalArgumentException("At least one TLS version is required".toString());
            }
            Object objClone = tlsVersions.clone();
            Objects.requireNonNull(objClone, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            this.f3806c = (String[]) objClone;
            return this;
        }

        public a(l connectionSpec) {
            kotlin.jvm.internal.l.e(connectionSpec, "connectionSpec");
            this.f3804a = connectionSpec.f();
            this.f3805b = connectionSpec.f3802c;
            this.f3806c = connectionSpec.f3803d;
            this.f3807d = connectionSpec.h();
        }
    }

    /* JADX INFO: compiled from: ConnectionSpec.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    static {
        i iVar = i.f3761n1;
        i iVar2 = i.f3764o1;
        i iVar3 = i.f3767p1;
        i iVar4 = i.Z0;
        i iVar5 = i.f3731d1;
        i iVar6 = i.f3722a1;
        i iVar7 = i.f3734e1;
        i iVar8 = i.f3752k1;
        i iVar9 = i.f3749j1;
        i[] iVarArr = {iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9};
        f3793e = iVarArr;
        i[] iVarArr2 = {iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, i.K0, i.L0, i.f3745i0, i.f3748j0, i.G, i.K, i.f3750k};
        f3794f = iVarArr2;
        a aVarB = new a(true).b((i[]) Arrays.copyOf(iVarArr, iVarArr.length));
        e0 e0Var = e0.TLS_1_3;
        e0 e0Var2 = e0.TLS_1_2;
        f3795g = aVarB.e(e0Var, e0Var2).d(true).a();
        f3796h = new a(true).b((i[]) Arrays.copyOf(iVarArr2, iVarArr2.length)).e(e0Var, e0Var2).d(true).a();
        f3797i = new a(true).b((i[]) Arrays.copyOf(iVarArr2, iVarArr2.length)).e(e0Var, e0Var2, e0.TLS_1_1, e0.TLS_1_0).d(true).a();
        f3798j = new a(false).a();
    }

    public l(boolean z6, boolean z7, String[] strArr, String[] strArr2) {
        this.f3800a = z6;
        this.f3801b = z7;
        this.f3802c = strArr;
        this.f3803d = strArr2;
    }

    private final l g(SSLSocket sSLSocket, boolean z6) throws CloneNotSupportedException {
        String[] cipherSuitesIntersection;
        String[] tlsVersionsIntersection;
        if (this.f3802c != null) {
            String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            kotlin.jvm.internal.l.d(enabledCipherSuites, "sslSocket.enabledCipherSuites");
            cipherSuitesIntersection = d6.b.A(enabledCipherSuites, this.f3802c, i.f3776s1.c());
        } else {
            cipherSuitesIntersection = sSLSocket.getEnabledCipherSuites();
        }
        if (this.f3803d != null) {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            kotlin.jvm.internal.l.d(enabledProtocols, "sslSocket.enabledProtocols");
            tlsVersionsIntersection = d6.b.A(enabledProtocols, this.f3803d, j5.b.b());
        } else {
            tlsVersionsIntersection = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        kotlin.jvm.internal.l.d(supportedCipherSuites, "supportedCipherSuites");
        int iT = d6.b.t(supportedCipherSuites, "TLS_FALLBACK_SCSV", i.f3776s1.c());
        if (z6 && iT != -1) {
            kotlin.jvm.internal.l.d(cipherSuitesIntersection, "cipherSuitesIntersection");
            String str = supportedCipherSuites[iT];
            kotlin.jvm.internal.l.d(str, "supportedCipherSuites[indexOfFallbackScsv]");
            cipherSuitesIntersection = d6.b.k(cipherSuitesIntersection, str);
        }
        a aVar = new a(this);
        kotlin.jvm.internal.l.d(cipherSuitesIntersection, "cipherSuitesIntersection");
        a aVarC = aVar.c((String[]) Arrays.copyOf(cipherSuitesIntersection, cipherSuitesIntersection.length));
        kotlin.jvm.internal.l.d(tlsVersionsIntersection, "tlsVersionsIntersection");
        return aVarC.f((String[]) Arrays.copyOf(tlsVersionsIntersection, tlsVersionsIntersection.length)).a();
    }

    public final void c(SSLSocket sslSocket, boolean z6) throws CloneNotSupportedException {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        l lVarG = g(sslSocket, z6);
        if (lVarG.i() != null) {
            sslSocket.setEnabledProtocols(lVarG.f3803d);
        }
        if (lVarG.d() != null) {
            sslSocket.setEnabledCipherSuites(lVarG.f3802c);
        }
    }

    public final List<i> d() {
        String[] strArr = this.f3802c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(i.f3776s1.b(str));
        }
        return h5.x.J(arrayList);
    }

    public final boolean e(SSLSocket socket) {
        kotlin.jvm.internal.l.e(socket, "socket");
        if (!this.f3800a) {
            return false;
        }
        String[] strArr = this.f3803d;
        if (strArr != null && !d6.b.q(strArr, socket.getEnabledProtocols(), j5.b.b())) {
            return false;
        }
        String[] strArr2 = this.f3802c;
        return strArr2 == null || d6.b.q(strArr2, socket.getEnabledCipherSuites(), i.f3776s1.c());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        boolean z6 = this.f3800a;
        l lVar = (l) obj;
        if (z6 != lVar.f3800a) {
            return false;
        }
        return !z6 || (Arrays.equals(this.f3802c, lVar.f3802c) && Arrays.equals(this.f3803d, lVar.f3803d) && this.f3801b == lVar.f3801b);
    }

    public final boolean f() {
        return this.f3800a;
    }

    public final boolean h() {
        return this.f3801b;
    }

    public int hashCode() {
        if (!this.f3800a) {
            return 17;
        }
        String[] strArr = this.f3802c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.f3803d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f3801b ? 1 : 0);
    }

    public final List<e0> i() {
        String[] strArr = this.f3803d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(e0.Companion.a(str));
        }
        return h5.x.J(arrayList);
    }

    public String toString() {
        if (!this.f3800a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(d(), "[all enabled]") + ", tlsVersions=" + Objects.toString(i(), "[all enabled]") + ", supportsTlsExtensions=" + this.f3801b + ')';
    }
}
