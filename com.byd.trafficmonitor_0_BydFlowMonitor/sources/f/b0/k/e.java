package f.b0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: compiled from: TrustRootIndex.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: compiled from: TrustRootIndex.java */
    public static final class a extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X509TrustManager f5295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f5296b;

        public a(X509TrustManager x509TrustManager, Method method) {
            this.f5296b = method;
            this.f5295a = x509TrustManager;
        }

        @Override // f.b0.k.e
        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f5296b.invoke(this.f5295a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException unused2) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f5295a.equals(aVar.f5295a) && this.f5296b.equals(aVar.f5296b);
        }

        public int hashCode() {
            return this.f5295a.hashCode() + (this.f5296b.hashCode() * 31);
        }
    }

    /* JADX INFO: compiled from: TrustRootIndex.java */
    public static final class b extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<X500Principal, Set<X509Certificate>> f5297a = new LinkedHashMap();

        public b(X509Certificate... x509CertificateArr) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                Set<X509Certificate> linkedHashSet = this.f5297a.get(subjectX500Principal);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet<>(1);
                    this.f5297a.put(subjectX500Principal, linkedHashSet);
                }
                linkedHashSet.add(x509Certificate);
            }
        }

        @Override // f.b0.k.e
        public X509Certificate a(X509Certificate x509Certificate) {
            Set<X509Certificate> set = this.f5297a.get(x509Certificate.getIssuerX500Principal());
            if (set == null) {
                return null;
            }
            for (X509Certificate x509Certificate2 : set) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return x509Certificate2;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof b) && ((b) obj).f5297a.equals(this.f5297a);
        }

        public int hashCode() {
            return this.f5297a.hashCode();
        }
    }

    public static e b(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new a(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return c(x509TrustManager.getAcceptedIssuers());
        }
    }

    public static e c(X509Certificate... x509CertificateArr) {
        return new b(x509CertificateArr);
    }

    public abstract X509Certificate a(X509Certificate x509Certificate);
}
