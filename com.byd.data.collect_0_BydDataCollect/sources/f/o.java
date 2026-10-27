package f;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final l[] f5470a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final l[] f5471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f5472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final o f5473d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final o f5474e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final o f5475f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final boolean f5476g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f5477h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    final String[] f5478i;

    @Nullable
    final String[] j;

    static {
        l lVar = l.m1;
        l lVar2 = l.n1;
        l lVar3 = l.o1;
        l lVar4 = l.Y0;
        l lVar5 = l.c1;
        l lVar6 = l.Z0;
        l lVar7 = l.d1;
        l lVar8 = l.j1;
        l lVar9 = l.i1;
        l[] lVarArr = {lVar, lVar2, lVar3, lVar4, lVar5, lVar6, lVar7, lVar8, lVar9};
        f5470a = lVarArr;
        l[] lVarArr2 = {lVar, lVar2, lVar3, lVar4, lVar5, lVar6, lVar7, lVar8, lVar9, l.J0, l.K0, l.h0, l.i0, l.F, l.J, l.j};
        f5471b = lVarArr2;
        a aVarC = new a(true).c(lVarArr);
        j0 j0Var = j0.TLS_1_3;
        j0 j0Var2 = j0.TLS_1_2;
        f5472c = aVarC.f(j0Var, j0Var2).d(true).a();
        f5473d = new a(true).c(lVarArr2).f(j0Var, j0Var2).d(true).a();
        f5474e = new a(true).c(lVarArr2).f(j0Var, j0Var2, j0.TLS_1_1, j0.TLS_1_0).d(true).a();
        f5475f = new a(false).a();
    }

    o(a aVar) {
        this.f5476g = aVar.f5479a;
        this.f5478i = aVar.f5480b;
        this.j = aVar.f5481c;
        this.f5477h = aVar.f5482d;
    }

    private o e(SSLSocket sSLSocket, boolean z) {
        String[] strArrY = this.f5478i != null ? f.k0.e.y(l.f5451a, sSLSocket.getEnabledCipherSuites(), this.f5478i) : sSLSocket.getEnabledCipherSuites();
        String[] strArrY2 = this.j != null ? f.k0.e.y(f.k0.e.j, sSLSocket.getEnabledProtocols(), this.j) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iV = f.k0.e.v(l.f5451a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iV != -1) {
            strArrY = f.k0.e.h(strArrY, supportedCipherSuites[iV]);
        }
        return new a(this).b(strArrY).e(strArrY2).a();
    }

    void a(SSLSocket sSLSocket, boolean z) {
        o oVarE = e(sSLSocket, z);
        String[] strArr = oVarE.j;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = oVarE.f5478i;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    @Nullable
    public List<l> b() {
        String[] strArr = this.f5478i;
        if (strArr != null) {
            return l.b(strArr);
        }
        return null;
    }

    public boolean c(SSLSocket sSLSocket) {
        if (!this.f5476g) {
            return false;
        }
        String[] strArr = this.j;
        if (strArr != null && !f.k0.e.B(f.k0.e.j, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f5478i;
        return strArr2 == null || f.k0.e.B(l.f5451a, strArr2, sSLSocket.getEnabledCipherSuites());
    }

    public boolean d() {
        return this.f5476g;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        o oVar = (o) obj;
        boolean z = this.f5476g;
        if (z != oVar.f5476g) {
            return false;
        }
        return !z || (Arrays.equals(this.f5478i, oVar.f5478i) && Arrays.equals(this.j, oVar.j) && this.f5477h == oVar.f5477h);
    }

    public boolean f() {
        return this.f5477h;
    }

    @Nullable
    public List<j0> g() {
        String[] strArr = this.j;
        if (strArr != null) {
            return j0.b(strArr);
        }
        return null;
    }

    public int hashCode() {
        if (this.f5476g) {
            return ((((527 + Arrays.hashCode(this.f5478i)) * 31) + Arrays.hashCode(this.j)) * 31) + (!this.f5477h ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f5476g) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(b(), "[all enabled]") + ", tlsVersions=" + Objects.toString(g(), "[all enabled]") + ", supportsTlsExtensions=" + this.f5477h + ")";
    }

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f5479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        String[] f5480b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        String[] f5481c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f5482d;

        a(boolean z) {
            this.f5479a = z;
        }

        public o a() {
            return new o(this);
        }

        public a b(String... strArr) {
            if (!this.f5479a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            this.f5480b = (String[]) strArr.clone();
            return this;
        }

        public a c(l... lVarArr) {
            if (!this.f5479a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[lVarArr.length];
            for (int i2 = 0; i2 < lVarArr.length; i2++) {
                strArr[i2] = lVarArr[i2].r1;
            }
            return b(strArr);
        }

        public a d(boolean z) {
            if (!this.f5479a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.f5482d = z;
            return this;
        }

        public a e(String... strArr) {
            if (!this.f5479a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f5481c = (String[]) strArr.clone();
            return this;
        }

        public a f(j0... j0VarArr) {
            if (!this.f5479a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[j0VarArr.length];
            for (int i2 = 0; i2 < j0VarArr.length; i2++) {
                strArr[i2] = j0VarArr[i2].javaName;
            }
            return e(strArr);
        }

        public a(o oVar) {
            this.f5479a = oVar.f5476g;
            this.f5480b = oVar.f5478i;
            this.f5481c = oVar.j;
            this.f5482d = oVar.f5477h;
        }
    }
}
