package c6;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: Handshake.kt */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f3836e = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g5.f f3837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e0 f3838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final i f3839c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<Certificate> f3840d;

    /* JADX INFO: compiled from: Handshake.kt */
    public static final class a {

        /* JADX INFO: renamed from: c6.s$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Handshake.kt */
        static final class C0043a extends kotlin.jvm.internal.m implements r5.a<List<? extends Certificate>> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ List f3841e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0043a(List list) {
                super(0);
                this.f3841e = list;
            }

            @Override // r5.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<Certificate> invoke() {
                return this.f3841e;
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private final List<Certificate> b(Certificate[] certificateArr) {
            return certificateArr != null ? d6.b.s((Certificate[]) Arrays.copyOf(certificateArr, certificateArr.length)) : h5.p.h();
        }

        public final s a(SSLSession handshake) throws IOException {
            List<Certificate> listH;
            kotlin.jvm.internal.l.e(handshake, "$this$handshake");
            String cipherSuite = handshake.getCipherSuite();
            if (cipherSuite == null) {
                throw new IllegalStateException("cipherSuite == null".toString());
            }
            int iHashCode = cipherSuite.hashCode();
            if (iHashCode == 1019404634 ? cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") : iHashCode == 1208658923 && cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
                throw new IOException("cipherSuite == " + cipherSuite);
            }
            i iVarB = i.f3776s1.b(cipherSuite);
            String protocol = handshake.getProtocol();
            if (protocol == null) {
                throw new IllegalStateException("tlsVersion == null".toString());
            }
            if (kotlin.jvm.internal.l.a("NONE", protocol)) {
                throw new IOException("tlsVersion == NONE");
            }
            e0 e0VarA = e0.Companion.a(protocol);
            try {
                listH = b(handshake.getPeerCertificates());
            } catch (SSLPeerUnverifiedException unused) {
                listH = h5.p.h();
            }
            return new s(e0VarA, iVarB, b(handshake.getLocalCertificates()), new C0043a(listH));
        }
    }

    /* JADX INFO: compiled from: Handshake.kt */
    static final class b extends kotlin.jvm.internal.m implements r5.a<List<? extends Certificate>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ r5.a f3842e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(r5.a aVar) {
            super(0);
            this.f3842e = aVar;
        }

        @Override // r5.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Certificate> invoke() {
            try {
                return (List) this.f3842e.invoke();
            } catch (SSLPeerUnverifiedException unused) {
                return h5.p.h();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public s(e0 tlsVersion, i cipherSuite, List<? extends Certificate> localCertificates, r5.a<? extends List<? extends Certificate>> peerCertificatesFn) {
        kotlin.jvm.internal.l.e(tlsVersion, "tlsVersion");
        kotlin.jvm.internal.l.e(cipherSuite, "cipherSuite");
        kotlin.jvm.internal.l.e(localCertificates, "localCertificates");
        kotlin.jvm.internal.l.e(peerCertificatesFn, "peerCertificatesFn");
        this.f3838b = tlsVersion;
        this.f3839c = cipherSuite;
        this.f3840d = localCertificates;
        this.f3837a = g5.h.a(new b(peerCertificatesFn));
    }

    private final String b(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectDN().toString();
        }
        String type = certificate.getType();
        kotlin.jvm.internal.l.d(type, "type");
        return type;
    }

    public final i a() {
        return this.f3839c;
    }

    public final List<Certificate> c() {
        return this.f3840d;
    }

    public final List<Certificate> d() {
        return (List) this.f3837a.getValue();
    }

    public final e0 e() {
        return this.f3838b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof s) {
            s sVar = (s) obj;
            if (sVar.f3838b == this.f3838b && kotlin.jvm.internal.l.a(sVar.f3839c, this.f3839c) && kotlin.jvm.internal.l.a(sVar.d(), d()) && kotlin.jvm.internal.l.a(sVar.f3840d, this.f3840d)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((527 + this.f3838b.hashCode()) * 31) + this.f3839c.hashCode()) * 31) + d().hashCode()) * 31) + this.f3840d.hashCode();
    }

    public String toString() {
        List<Certificate> listD = d();
        ArrayList arrayList = new ArrayList(h5.q.p(listD, 10));
        Iterator<T> it = listD.iterator();
        while (it.hasNext()) {
            arrayList.add(b((Certificate) it.next()));
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Handshake{");
        sb.append("tlsVersion=");
        sb.append(this.f3838b);
        sb.append(' ');
        sb.append("cipherSuite=");
        sb.append(this.f3839c);
        sb.append(' ');
        sb.append("peerCertificates=");
        sb.append(string);
        sb.append(' ');
        sb.append("localCertificates=");
        List<Certificate> list = this.f3840d;
        ArrayList arrayList2 = new ArrayList(h5.q.p(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(b((Certificate) it2.next()));
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
