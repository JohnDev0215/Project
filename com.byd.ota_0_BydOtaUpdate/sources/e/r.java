package e;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TlsVersion f3630a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h f3631b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<Certificate> f3632c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<Certificate> f3633d;

    public r(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.f3630a = tlsVersion;
        this.f3631b = hVar;
        this.f3632c = list;
        this.f3633d = list2;
    }

    public static r b(SSLSession sSLSession) {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        h hVarA = h.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        TlsVersion tlsVersionForJavaName = TlsVersion.forJavaName(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listO = peerCertificates != null ? e.d0.c.o(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new r(tlsVersionForJavaName, hVarA, listO, localCertificates != null ? e.d0.c.o(localCertificates) : Collections.emptyList());
    }

    public h a() {
        return this.f3631b;
    }

    public List<Certificate> c() {
        return this.f3632c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f3630a.equals(rVar.f3630a) && this.f3631b.equals(rVar.f3631b) && this.f3632c.equals(rVar.f3632c) && this.f3633d.equals(rVar.f3633d);
    }

    public int hashCode() {
        return ((((((527 + this.f3630a.hashCode()) * 31) + this.f3631b.hashCode()) * 31) + this.f3632c.hashCode()) * 31) + this.f3633d.hashCode();
    }
}
