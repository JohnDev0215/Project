package f;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.TlsVersion;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TlsVersion f5369a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h f5370b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<Certificate> f5371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<Certificate> f5372d;

    public p(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.f5369a = tlsVersion;
        this.f5370b = hVar;
        this.f5371c = list;
        this.f5372d = list2;
    }

    public static p b(SSLSession sSLSession) {
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
        List listO = peerCertificates != null ? f.b0.c.o(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new p(tlsVersionForJavaName, hVarA, listO, localCertificates != null ? f.b0.c.o(localCertificates) : Collections.emptyList());
    }

    public h a() {
        return this.f5370b;
    }

    public List<Certificate> c() {
        return this.f5371c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return f.b0.c.k(this.f5370b, pVar.f5370b) && this.f5370b.equals(pVar.f5370b) && this.f5371c.equals(pVar.f5371c) && this.f5372d.equals(pVar.f5372d);
    }

    public int hashCode() {
        TlsVersion tlsVersion = this.f5369a;
        return ((((((527 + (tlsVersion != null ? tlsVersion.hashCode() : 0)) * 31) + this.f5370b.hashCode()) * 31) + this.f5371c.hashCode()) * 31) + this.f5372d.hashCode();
    }
}
