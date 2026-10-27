package e;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f3568a = new a().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set<b> f3569b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public final e.d0.l.b f3570c;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<b> f3571a = new ArrayList();

        public g a() {
            return new g(new LinkedHashSet(this.f3571a), null);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f3572a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f3573b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f3574c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ByteString f3575d;

        public boolean a(String str) {
            if (!this.f3572a.startsWith("*.")) {
                return str.equals(this.f3573b);
            }
            int iIndexOf = str.indexOf(46) + 1;
            String str2 = this.f3573b;
            return str.regionMatches(false, iIndexOf, str2, 0, str2.length());
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f3572a.equals(bVar.f3572a) && this.f3574c.equals(bVar.f3574c) && this.f3575d.equals(bVar.f3575d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f3572a.hashCode()) * 31) + this.f3574c.hashCode()) * 31) + this.f3575d.hashCode();
        }

        public String toString() {
            return this.f3574c + this.f3575d.base64();
        }
    }

    public g(Set<b> set, @Nullable e.d0.l.b bVar) {
        this.f3569b = set;
        this.f3570c = bVar;
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
        e.d0.l.b bVar = this.f3570c;
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
                if (bVar2.f3574c.equals("sha256/")) {
                    if (byteStringE == null) {
                        byteStringE = e(x509Certificate);
                    }
                    if (bVar2.f3575d.equals(byteStringE)) {
                        return;
                    }
                } else {
                    if (!bVar2.f3574c.equals("sha1/")) {
                        throw new AssertionError();
                    }
                    if (byteStringD == null) {
                        byteStringD = d(x509Certificate);
                    }
                    if (bVar2.f3575d.equals(byteStringD)) {
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
        for (b bVar : this.f3569b) {
            if (bVar.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(bVar);
            }
        }
        return listEmptyList;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (e.d0.c.k(this.f3570c, gVar.f3570c) && this.f3569b.equals(gVar.f3569b)) {
                return true;
            }
        }
        return false;
    }

    public g f(e.d0.l.b bVar) {
        return e.d0.c.k(this.f3570c, bVar) ? this : new g(this.f3569b, bVar);
    }

    public int hashCode() {
        e.d0.l.b bVar = this.f3570c;
        return ((bVar != null ? bVar.hashCode() : 0) * 31) + this.f3569b.hashCode();
    }
}
