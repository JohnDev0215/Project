package c9;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC2000o;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K f12609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1051l f12610b;
    public final List c;
    public final m7.n d;

    public t(K k10, C1051l c1051l, List list, InterfaceC2772a interfaceC2772a) {
        this.f12609a = k10;
        this.f12610b = c1051l;
        this.c = list;
        this.d = h6.r.o(new D8.q(interfaceC2772a, 1));
    }

    public final List a() {
        return (List) this.d.getValue();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof t) {
            t tVar = (t) obj;
            if (tVar.f12609a == this.f12609a && A7.m.a(tVar.f12610b, this.f12610b) && A7.m.a(tVar.a(), a()) && A7.m.a(tVar.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((a().hashCode() + ((this.f12610b.hashCode() + ((this.f12609a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> listA = a();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listA, 10));
        for (Certificate certificate : listA) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                A7.m.e(type2, "type");
            }
            arrayList.add(type2);
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.f12609a);
        sb.append(" cipherSuite=");
        sb.append(this.f12610b);
        sb.append(" peerCertificates=");
        sb.append(string);
        sb.append(" localCertificates=");
        List<Certificate> list = this.c;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list, 10));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                A7.m.e(type, "type");
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
