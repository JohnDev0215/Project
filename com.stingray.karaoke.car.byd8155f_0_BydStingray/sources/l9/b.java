package l9;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class b implements p9.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X509TrustManager f18863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f18864b;

    public b(X509TrustManager x509TrustManager, Method method) {
        this.f18863a = x509TrustManager;
        this.f18864b = method;
    }

    @Override // p9.d
    public final X509Certificate a(X509Certificate x509Certificate) {
        try {
            Object objInvoke = this.f18864b.invoke(this.f18863a, x509Certificate);
            A7.m.d(objInvoke, "null cannot be cast to non-null type java.security.cert.TrustAnchor");
            return ((TrustAnchor) objInvoke).getTrustedCert();
        } catch (IllegalAccessException e10) {
            throw new AssertionError("unable to get issues and signature", e10);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return A7.m.a(this.f18863a, bVar.f18863a) && A7.m.a(this.f18864b, bVar.f18864b);
    }

    public final int hashCode() {
        return this.f18864b.hashCode() + (this.f18863a.hashCode() * 31);
    }

    public final String toString() {
        return "CustomTrustRootIndex(trustManager=" + this.f18863a + ", findByIssuerAndSignatureMethod=" + this.f18864b + ')';
    }
}
