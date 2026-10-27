package f;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f5314a = new a().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set<b> f5315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f.b0.k.b f5316c;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<b> f5317a = new ArrayList();

        public g a() {
            return new g(new LinkedHashSet(this.f5317a), null);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f5318a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f5319b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f5320c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ByteString f5321d;

        public boolean a(String str) {
            if (!this.f5318a.startsWith("*.")) {
                return str.equals(this.f5319b);
            }
            int iIndexOf = str.indexOf(46) + 1;
            String str2 = this.f5319b;
            return str.regionMatches(false, iIndexOf, str2, 0, str2.length());
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f5318a.equals(bVar.f5318a) && this.f5320c.equals(bVar.f5320c) && this.f5321d.equals(bVar.f5321d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f5318a.hashCode()) * 31) + this.f5320c.hashCode()) * 31) + this.f5321d.hashCode();
        }

        public String toString() {
            return this.f5320c + this.f5321d.base64();
        }
    }

    public g(Set<b> set, f.b0.k.b bVar) {
        this.f5315b = set;
        this.f5316c = bVar;
    }

    public static String c(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + e((X509Certificate) certificate).base64();
    }

    public static ByteString d(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    public static ByteString e(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<b> listB = b(str);
        if (listB.isEmpty()) {
            return;
        }
        f.b0.k.b bVar = this.f5316c;
        if (bVar != null) {
            list = bVar.a(list, str);
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i2);
            int size2 = listB.size();
            ByteString byteStringE = null;
            ByteString byteStringD = null;
            for (int i3 = 0; i3 < size2; i3++) {
                b bVar2 = listB.get(i3);
                if (bVar2.f5320c.equals("sha256/")) {
                    if (byteStringE == null) {
                        byteStringE = e(x509Certificate);
                    }
                    if (bVar2.f5321d.equals(byteStringE)) {
                        return;
                    }
                } else {
                    if (!bVar2.f5320c.equals("sha1/")) {
                        throw new AssertionError();
                    }
                    if (byteStringD == null) {
                        byteStringD = d(x509Certificate);
                    }
                    if (bVar2.f5321d.equals(byteStringD)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i4 = 0; i4 < size3; i4++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i4);
            sb.append("\n    ");
            sb.append(c(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size4 = listB.size();
        for (int i5 = 0; i5 < size4; i5++) {
            b bVar3 = listB.get(i5);
            sb.append("\n    ");
            sb.append(bVar3);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public List<b> b(String str) {
        List<b> listEmptyList = Collections.emptyList();
        for (b bVar : this.f5315b) {
            if (bVar.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(bVar);
            }
        }
        return listEmptyList;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (f.b0.c.k(this.f5316c, gVar.f5316c) && this.f5315b.equals(gVar.f5315b)) {
                return true;
            }
        }
        return false;
    }

    public g f(f.b0.k.b bVar) {
        return f.b0.c.k(this.f5316c, bVar) ? this : new g(this.f5315b, bVar);
    }

    public int hashCode() {
        f.b0.k.b bVar = this.f5316c;
        return ((bVar != null ? bVar.hashCode() : 0) * 31) + this.f5315b.hashCode();
    }
}
