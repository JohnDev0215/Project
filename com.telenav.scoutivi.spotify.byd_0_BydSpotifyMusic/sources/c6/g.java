package c6;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import p6.h;

/* JADX INFO: compiled from: CertificatePinner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<c> f3710a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final o6.c f3711b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f3709d = new b(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f3708c = new a().a();

    /* JADX INFO: compiled from: CertificatePinner.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<c> f3712a = new ArrayList();

        public final g a() {
            return new g(h5.x.M(this.f3712a), null, 2, 0 == true ? 1 : 0);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final String a(Certificate certificate) {
            kotlin.jvm.internal.l.e(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
            }
            return "sha256/" + c((X509Certificate) certificate).a();
        }

        public final p6.h b(X509Certificate sha1Hash) {
            kotlin.jvm.internal.l.e(sha1Hash, "$this$sha1Hash");
            h.a aVar = p6.h.f8870j;
            PublicKey publicKey = sha1Hash.getPublicKey();
            kotlin.jvm.internal.l.d(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            kotlin.jvm.internal.l.d(encoded, "publicKey.encoded");
            return h.a.e(aVar, encoded, 0, 0, 3, null).p();
        }

        public final p6.h c(X509Certificate sha256Hash) {
            kotlin.jvm.internal.l.e(sha256Hash, "$this$sha256Hash");
            h.a aVar = p6.h.f8870j;
            PublicKey publicKey = sha256Hash.getPublicKey();
            kotlin.jvm.internal.l.d(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            kotlin.jvm.internal.l.d(encoded, "publicKey.encoded");
            return h.a.e(aVar, encoded, 0, 0, 3, null).q();
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.kt */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f3713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f3714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final p6.h f3715c;

        public final p6.h a() {
            return this.f3715c;
        }

        public final String b() {
            return this.f3714b;
        }

        public final boolean c(String hostname) {
            kotlin.jvm.internal.l.e(hostname, "hostname");
            if (y5.p.A(this.f3713a, "**.", false, 2, null)) {
                int length = this.f3713a.length() - 3;
                int length2 = hostname.length() - length;
                if (!y5.p.r(hostname, hostname.length() - length, this.f3713a, 3, length, false, 16, null)) {
                    return false;
                }
                if (length2 != 0 && hostname.charAt(length2 - 1) != '.') {
                    return false;
                }
            } else {
                if (!y5.p.A(this.f3713a, "*.", false, 2, null)) {
                    return kotlin.jvm.internal.l.a(hostname, this.f3713a);
                }
                int length3 = this.f3713a.length() - 1;
                int length4 = hostname.length() - length3;
                if (!y5.p.r(hostname, hostname.length() - length3, this.f3713a, 1, length3, false, 16, null) || y5.q.U(hostname, '.', length4 - 1, false, 4, null) != -1) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return ((kotlin.jvm.internal.l.a(this.f3713a, cVar.f3713a) ^ true) || (kotlin.jvm.internal.l.a(this.f3714b, cVar.f3714b) ^ true) || (kotlin.jvm.internal.l.a(this.f3715c, cVar.f3715c) ^ true)) ? false : true;
        }

        public int hashCode() {
            return (((this.f3713a.hashCode() * 31) + this.f3714b.hashCode()) * 31) + this.f3715c.hashCode();
        }

        public String toString() {
            return this.f3714b + '/' + this.f3715c.a();
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.kt */
    static final class d extends kotlin.jvm.internal.m implements r5.a<List<? extends X509Certificate>> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ List f3717g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ String f3718h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(List list, String str) {
            super(0);
            this.f3717g = list;
            this.f3718h = str;
        }

        @Override // r5.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<X509Certificate> invoke() {
            List<Certificate> listA;
            o6.c cVarD = g.this.d();
            if (cVarD == null || (listA = cVarD.a(this.f3717g, this.f3718h)) == null) {
                listA = this.f3717g;
            }
            ArrayList arrayList = new ArrayList(h5.q.p(listA, 10));
            for (Certificate certificate : listA) {
                Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                arrayList.add((X509Certificate) certificate);
            }
            return arrayList;
        }
    }

    public g(Set<c> pins, o6.c cVar) {
        kotlin.jvm.internal.l.e(pins, "pins");
        this.f3710a = pins;
        this.f3711b = cVar;
    }

    public final void a(String hostname, List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        kotlin.jvm.internal.l.e(hostname, "hostname");
        kotlin.jvm.internal.l.e(peerCertificates, "peerCertificates");
        b(hostname, new d(peerCertificates, hostname));
    }

    public final void b(String hostname, r5.a<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) throws SSLPeerUnverifiedException {
        kotlin.jvm.internal.l.e(hostname, "hostname");
        kotlin.jvm.internal.l.e(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        List<c> listC = c(hostname);
        if (listC.isEmpty()) {
            return;
        }
        List<? extends X509Certificate> listInvoke = cleanedPeerCertificatesFn.invoke();
        for (X509Certificate x509Certificate : listInvoke) {
            p6.h hVarC = null;
            p6.h hVarB = null;
            for (c cVar : listC) {
                String strB = cVar.b();
                int iHashCode = strB.hashCode();
                if (iHashCode != -903629273) {
                    if (iHashCode != 3528965 || !strB.equals("sha1")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + cVar.b());
                    }
                    if (hVarB == null) {
                        hVarB = f3709d.b(x509Certificate);
                    }
                    if (kotlin.jvm.internal.l.a(cVar.a(), hVarB)) {
                        return;
                    }
                } else {
                    if (!strB.equals("sha256")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + cVar.b());
                    }
                    if (hVarC == null) {
                        hVarC = f3709d.c(x509Certificate);
                    }
                    if (kotlin.jvm.internal.l.a(cVar.a(), hVarC)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        for (X509Certificate x509Certificate2 : listInvoke) {
            sb.append("\n    ");
            sb.append(f3709d.a(x509Certificate2));
            sb.append(": ");
            Principal subjectDN = x509Certificate2.getSubjectDN();
            kotlin.jvm.internal.l.d(subjectDN, "element.subjectDN");
            sb.append(subjectDN.getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(hostname);
        sb.append(":");
        for (c cVar2 : listC) {
            sb.append("\n    ");
            sb.append(cVar2);
        }
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(string);
    }

    public final List<c> c(String hostname) {
        kotlin.jvm.internal.l.e(hostname, "hostname");
        Set<c> set = this.f3710a;
        List<c> listH = h5.p.h();
        for (Object obj : set) {
            if (((c) obj).c(hostname)) {
                if (listH.isEmpty()) {
                    listH = new ArrayList<>();
                }
                kotlin.jvm.internal.v.a(listH).add(obj);
            }
        }
        return listH;
    }

    public final o6.c d() {
        return this.f3711b;
    }

    public final g e(o6.c certificateChainCleaner) {
        kotlin.jvm.internal.l.e(certificateChainCleaner, "certificateChainCleaner");
        return kotlin.jvm.internal.l.a(this.f3711b, certificateChainCleaner) ? this : new g(this.f3710a, certificateChainCleaner);
    }

    public boolean equals(Object obj) {
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (kotlin.jvm.internal.l.a(gVar.f3710a, this.f3710a) && kotlin.jvm.internal.l.a(gVar.f3711b, this.f3711b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (1517 + this.f3710a.hashCode()) * 41;
        o6.c cVar = this.f3711b;
        return iHashCode + (cVar != null ? cVar.hashCode() : 0);
    }

    public /* synthetic */ g(Set set, o6.c cVar, int i7, kotlin.jvm.internal.g gVar) {
        this(set, (i7 & 2) != 0 ? null : cVar);
    }
}
