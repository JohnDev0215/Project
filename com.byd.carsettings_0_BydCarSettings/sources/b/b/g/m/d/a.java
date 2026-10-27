package b.b.g.m.d;

import java.security.cert.X509Certificate;
import java.util.Comparator;

/* JADX INFO: compiled from: BydCert.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Comparator<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public X509Certificate f890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f892c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f893d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f894e;

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(a aVar, a aVar2) {
        if (aVar == null || aVar2 == null) {
            return 0;
        }
        return aVar.f891b.compareToIgnoreCase(aVar2.f891b);
    }

    public String b() {
        return this.f894e;
    }

    public X509Certificate c() {
        return this.f890a;
    }

    public String d() {
        return this.f892c;
    }

    public String e() {
        return this.f891b;
    }

    public boolean f() {
        return this.f893d;
    }

    public void g(String str) {
        this.f894e = str;
    }

    public void h(X509Certificate x509Certificate) {
        this.f890a = x509Certificate;
    }

    public void i(boolean z) {
        this.f893d = z;
    }

    public void j(String str) {
        this.f892c = str;
    }

    public void k(String str) {
        this.f891b = str;
    }

    public String toString() {
        return "BydCert{cert=" + this.f890a + ", subject='" + this.f891b + "', issuer='" + this.f892c + "', enable=" + this.f893d + '}';
    }
}
