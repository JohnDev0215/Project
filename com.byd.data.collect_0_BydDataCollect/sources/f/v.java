package f;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a */
    private final j0 f5508a;

    /* JADX INFO: renamed from: b */
    private final l f5509b;

    /* JADX INFO: renamed from: c */
    private final List<Certificate> f5510c;

    /* JADX INFO: renamed from: d */
    private final List<Certificate> f5511d;

    private v(j0 j0Var, l lVar, List<Certificate> list, List<Certificate> list2) {
        this.f5508a = j0Var;
        this.f5509b = lVar;
        this.f5510c = list;
        this.f5511d = list2;
    }

    public static v b(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        l lVarA = l.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        j0 j0VarA = j0.a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listT = peerCertificates != null ? f.k0.e.t(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new v(j0VarA, lVarA, listT, localCertificates != null ? f.k0.e.t(localCertificates) : Collections.emptyList());
    }

    private List<String> c(List<Certificate> list) {
        ArrayList arrayList = new ArrayList();
        for (Certificate certificate : list) {
            if (certificate instanceof X509Certificate) {
                arrayList.add(String.valueOf(((X509Certificate) certificate).getSubjectDN()));
            } else {
                arrayList.add(certificate.getType());
            }
        }
        return arrayList;
    }

    public l a() {
        return this.f5509b;
    }

    public List<Certificate> d() {
        return this.f5510c;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f5508a.equals(vVar.f5508a) && this.f5509b.equals(vVar.f5509b) && this.f5510c.equals(vVar.f5510c) && this.f5511d.equals(vVar.f5511d);
    }

    public int hashCode() {
        return ((((((527 + this.f5508a.hashCode()) * 31) + this.f5509b.hashCode()) * 31) + this.f5510c.hashCode()) * 31) + this.f5511d.hashCode();
    }

    public String toString() {
        return "Handshake{tlsVersion=" + this.f5508a + " cipherSuite=" + this.f5509b + " peerCertificates=" + c(this.f5510c) + " localCertificates=" + c(this.f5511d) + '}';
    }
}
