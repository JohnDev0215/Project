package okhttp3;

import com.spotify.connectivity.productstate.RxProductState;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.a;
import kotlin.collections.EmptyList;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import p.lu;
import p.py0;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class Handshake {
    public static final Companion e = new Companion(0);
    public final TlsVersion a;
    public final CipherSuite b;
    public final List c;
    public final v81 d;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static Handshake a(SSLSession sSLSession) throws IOException {
            final List listL;
            String cipherSuite = sSLSession.getCipherSuite();
            if (cipherSuite == null) {
                throw new IllegalStateException("cipherSuite == null".toString());
            }
            if (v03.b(cipherSuite, "TLS_NULL_WITH_NULL_NULL") || v03.b(cipherSuite, "SSL_NULL_WITH_NULL_NULL")) {
                throw new IOException("cipherSuite == ".concat(cipherSuite));
            }
            CipherSuite cipherSuiteB = CipherSuite.b.b(cipherSuite);
            String protocol = sSLSession.getProtocol();
            if (protocol == null) {
                throw new IllegalStateException("tlsVersion == null".toString());
            }
            if (v03.b("NONE", protocol)) {
                throw new IOException("tlsVersion == NONE");
            }
            TlsVersion.c.getClass();
            TlsVersion tlsVersionA = TlsVersion.Companion.a(protocol);
            try {
                Certificate[] peerCertificates = sSLSession.getPeerCertificates();
                listL = peerCertificates != null ? Util.l(Arrays.copyOf(peerCertificates, peerCertificates.length)) : EmptyList.c;
            } catch (SSLPeerUnverifiedException unused) {
                listL = EmptyList.c;
            }
            Certificate[] localCertificates = sSLSession.getLocalCertificates();
            return new Handshake(tlsVersionA, cipherSuiteB, localCertificates != null ? Util.l(Arrays.copyOf(localCertificates, localCertificates.length)) : EmptyList.c, new py0() { // from class: okhttp3.Handshake$Companion$handshake$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    return listL;
                }
            });
        }

        public static Handshake b(TlsVersion tlsVersion, CipherSuite cipherSuite, List list, List list2) {
            v03.j(list, "peerCertificates");
            v03.j(list2, "localCertificates");
            final List listY = Util.y(list);
            return new Handshake(tlsVersion, cipherSuite, Util.y(list2), new py0() { // from class: okhttp3.Handshake$Companion$get$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    return listY;
                }
            });
        }
    }

    public Handshake(TlsVersion tlsVersion, CipherSuite cipherSuite, List list, final py0 py0Var) {
        v03.j(tlsVersion, "tlsVersion");
        v03.j(cipherSuite, "cipherSuite");
        v03.j(list, "localCertificates");
        this.a = tlsVersion;
        this.b = cipherSuite;
        this.c = list;
        this.d = a.c(new py0() { // from class: okhttp3.Handshake$peerCertificates$2
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                try {
                    return (List) py0Var.invoke();
                } catch (SSLPeerUnverifiedException unused) {
                    return EmptyList.c;
                }
            }
        });
    }

    public final List a() {
        return (List) this.d.getValue();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Handshake) {
            Handshake handshake = (Handshake) obj;
            if (handshake.a == this.a && v03.b(handshake.b, this.b) && v03.b(handshake.a(), a()) && v03.b(handshake.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((a().hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> listA = a();
        ArrayList arrayList = new ArrayList(lu.W(listA));
        for (Certificate certificate : listA) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                v03.i(type2, RxProductState.Keys.KEY_TYPE);
            }
            arrayList.add(type2);
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.a);
        sb.append(" cipherSuite=");
        sb.append(this.b);
        sb.append(" peerCertificates=");
        sb.append(string);
        sb.append(" localCertificates=");
        List<Certificate> list = this.c;
        ArrayList arrayList2 = new ArrayList(lu.W(list));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                v03.i(type, RxProductState.Keys.KEY_TYPE);
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
