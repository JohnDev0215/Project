package f;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okio.ByteString;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f5120a = new a().a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<b> f5121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final f.k0.n.c f5122c;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<b> f5123a = new ArrayList();

        public k a() {
            return new k(new LinkedHashSet(this.f5123a), null);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f5124a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final String f5125b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final String f5126c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final ByteString f5127d;

        boolean a(String str) {
            if (!this.f5124a.startsWith("*.")) {
                return str.equals(this.f5125b);
            }
            int iIndexOf = str.indexOf(46);
            if ((str.length() - iIndexOf) - 1 == this.f5125b.length()) {
                String str2 = this.f5125b;
                if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f5124a.equals(bVar.f5124a) && this.f5126c.equals(bVar.f5126c) && this.f5127d.equals(bVar.f5127d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f5124a.hashCode()) * 31) + this.f5126c.hashCode()) * 31) + this.f5127d.hashCode();
        }

        public String toString() {
            return this.f5126c + this.f5127d.a();
        }
    }

    k(Set<b> set, @Nullable f.k0.n.c cVar) {
        this.f5121b = set;
        this.f5122c = cVar;
    }

    public static String c(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + e((X509Certificate) certificate).a();
    }

    static ByteString d(X509Certificate x509Certificate) {
        return ByteString.m(x509Certificate.getPublicKey().getEncoded()).q();
    }

    static ByteString e(X509Certificate x509Certificate) {
        return ByteString.m(x509Certificate.getPublicKey().getEncoded()).r();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List<b> listB = b(str);
        if (listB.isEmpty()) {
            return;
        }
        f.k0.n.c cVar = this.f5122c;
        if (cVar != null) {
            list = cVar.a(list, str);
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i2);
            int size2 = listB.size();
            ByteString byteStringE = null;
            ByteString byteStringD = null;
            for (int i3 = 0; i3 < size2; i3++) {
                b bVar = listB.get(i3);
                if (bVar.f5126c.equals("sha256/")) {
                    if (byteStringE == null) {
                        byteStringE = e(x509Certificate);
                    }
                    if (bVar.f5127d.equals(byteStringE)) {
                        return;
                    }
                } else {
                    if (!bVar.f5126c.equals("sha1/")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + bVar.f5126c);
                    }
                    if (byteStringD == null) {
                        byteStringD = d(x509Certificate);
                    }
                    if (bVar.f5127d.equals(byteStringD)) {
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
            b bVar2 = listB.get(i5);
            sb.append("\n    ");
            sb.append(bVar2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    List<b> b(String str) {
        List<b> listEmptyList = Collections.emptyList();
        for (b bVar : this.f5121b) {
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
        if (obj instanceof k) {
            k kVar = (k) obj;
            if (Objects.equals(this.f5122c, kVar.f5122c) && this.f5121b.equals(kVar.f5121b)) {
                return true;
            }
        }
        return false;
    }

    k f(@Nullable f.k0.n.c cVar) {
        return Objects.equals(this.f5122c, cVar) ? this : new k(this.f5121b, cVar);
    }

    public int hashCode() {
        return (Objects.hashCode(this.f5122c) * 31) + this.f5121b.hashCode();
    }
}
