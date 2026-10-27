package okhttp3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.EmptyList;
import okhttp3.internal.tls.CertificateChainCleaner;
import p.ng2;
import p.ou;
import p.py0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CertificatePinner {
    public static final Companion c = new Companion(0);
    public static final CertificatePinner d = new CertificatePinner(ou.q0(new Builder().a), null);
    public final Set a;
    public final CertificateChainCleaner b;

    public static final class Builder {
        public final ArrayList a = new ArrayList();
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class Pin {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pin)) {
                return false;
            }
            Pin pin = (Pin) obj;
            pin.getClass();
            if (!v03.b(null, null)) {
                return false;
            }
            pin.getClass();
            if (!v03.b(null, null)) {
                return false;
            }
            pin.getClass();
            return v03.b(null, null);
        }

        public final int hashCode() {
            throw null;
        }

        public final String toString() {
            throw null;
        }
    }

    public CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner) {
        v03.j(set, "pins");
        this.a = set;
        this.b = certificateChainCleaner;
    }

    public final void a(final String str, final List list) {
        v03.j(str, "hostname");
        v03.j(list, "peerCertificates");
        b(str, new py0() { // from class: okhttp3.CertificatePinner$check$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
            @Override // p.py0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invoke() {
                /*
                    r4 = this;
                    okhttp3.CertificatePinner r0 = r4.this$0
                    okhttp3.internal.tls.CertificateChainCleaner r0 = r0.b
                    if (r0 == 0) goto L10
                    java.util.List<java.security.cert.Certificate> r1 = r2
                    java.lang.String r2 = r3
                    java.util.List r0 = r0.a(r2, r1)
                    if (r0 != 0) goto L12
                L10:
                    java.util.List<java.security.cert.Certificate> r0 = r2
                L12:
                    java.util.ArrayList r1 = new java.util.ArrayList
                    int r2 = p.lu.W(r0)
                    r1.<init>(r2)
                    java.util.Iterator r0 = r0.iterator()
                L1f:
                    boolean r2 = r0.hasNext()
                    if (r2 == 0) goto L36
                    java.lang.Object r2 = r0.next()
                    java.security.cert.Certificate r2 = (java.security.cert.Certificate) r2
                    java.lang.String r3 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
                    p.v03.g(r2, r3)
                    java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
                    r1.add(r2)
                    goto L1f
                L36:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner$check$1.invoke():java.lang.Object");
            }
        });
    }

    public final void b(String str, py0 py0Var) {
        v03.j(str, "hostname");
        EmptyList emptyList = EmptyList.c;
        Iterator it = this.a.iterator();
        if (!it.hasNext()) {
            emptyList.getClass();
        } else {
            ((Pin) it.next()).getClass();
            ng2.g0(null, "**.", false);
            throw null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (v03.b(certificatePinner.a, this.a) && v03.b(certificatePinner.b, this.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() + 1517) * 41;
        CertificateChainCleaner certificateChainCleaner = this.b;
        return iHashCode + (certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0);
    }
}
