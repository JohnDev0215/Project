package c9;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import n7.AbstractC1998m;
import p7.C2112b;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n f12589e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f12590f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f12591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f12592b;
    public final String[] c;
    public final String[] d;

    static {
        C1051l c1051l = C1051l.f12583r;
        C1051l c1051l2 = C1051l.f12584s;
        C1051l c1051l3 = C1051l.f12585t;
        C1051l c1051l4 = C1051l.l;
        C1051l c1051l5 = C1051l.f12580n;
        C1051l c1051l6 = C1051l.f12579m;
        C1051l c1051l7 = C1051l.o;
        C1051l c1051l8 = C1051l.f12582q;
        C1051l c1051l9 = C1051l.f12581p;
        C1051l[] c1051lArr = {c1051l, c1051l2, c1051l3, c1051l4, c1051l5, c1051l6, c1051l7, c1051l8, c1051l9, C1051l.f12577j, C1051l.f12578k, C1051l.f12575h, C1051l.f12576i, C1051l.f12573f, C1051l.f12574g, C1051l.f12572e};
        m mVar = new m();
        mVar.b((C1051l[]) Arrays.copyOf(new C1051l[]{c1051l, c1051l2, c1051l3, c1051l4, c1051l5, c1051l6, c1051l7, c1051l8, c1051l9}, 9));
        K k10 = K.TLS_1_3;
        K k11 = K.TLS_1_2;
        mVar.d(k10, k11);
        if (!mVar.f12587a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        mVar.f12588b = true;
        mVar.a();
        m mVar2 = new m();
        mVar2.b((C1051l[]) Arrays.copyOf(c1051lArr, 16));
        mVar2.d(k10, k11);
        if (!mVar2.f12587a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        mVar2.f12588b = true;
        f12589e = mVar2.a();
        m mVar3 = new m();
        mVar3.b((C1051l[]) Arrays.copyOf(c1051lArr, 16));
        mVar3.d(k10, k11, K.TLS_1_1, K.TLS_1_0);
        if (!mVar3.f12587a) {
            throw new IllegalArgumentException("no TLS extensions for cleartext connections");
        }
        mVar3.f12588b = true;
        mVar3.a();
        f12590f = new n(false, false, null, null);
    }

    public n(boolean z6, boolean z10, String[] strArr, String[] strArr2) {
        this.f12591a = z6;
        this.f12592b = z10;
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
            arrayList.add(C1051l.f12571b.c(str));
        }
        return AbstractC1998m.z0(arrayList);
    }

    public final boolean b(SSLSocket sSLSocket) {
        if (!this.f12591a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !d9.b.j(strArr, sSLSocket.getEnabledProtocols(), C2112b.f20944m)) {
            return false;
        }
        String[] strArr2 = this.c;
        return strArr2 == null || d9.b.j(strArr2, sSLSocket.getEnabledCipherSuites(), C1051l.c);
    }

    public final List c() {
        String[] strArr = this.d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(io.sentry.config.a.a0(str));
        }
        return AbstractC1998m.z0(arrayList);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        n nVar = (n) obj;
        boolean z6 = nVar.f12591a;
        boolean z10 = this.f12591a;
        if (z10 != z6) {
            return false;
        }
        return !z10 || (Arrays.equals(this.c, nVar.c) && Arrays.equals(this.d, nVar.d) && this.f12592b == nVar.f12592b);
    }

    public final int hashCode() {
        if (!this.f12591a) {
            return 17;
        }
        String[] strArr = this.c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f12592b ? 1 : 0);
    }

    public final String toString() {
        if (!this.f12591a) {
            return "ConnectionSpec()";
        }
        StringBuilder sb = new StringBuilder("ConnectionSpec(cipherSuites=");
        sb.append(Objects.toString(a(), "[all enabled]"));
        sb.append(", tlsVersions=");
        sb.append(Objects.toString(c(), "[all enabled]"));
        sb.append(", supportsTlsExtensions=");
        return AbstractC1347d.i(sb, this.f12592b, ')');
    }
}
