package c9;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f12587a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f12588b;
    public Object c;
    public Serializable d;

    public n a() {
        return new n(this.f12587a, this.f12588b, (String[]) this.c, (String[]) this.d);
    }

    public void b(C1051l... c1051lArr) {
        A7.m.f(c1051lArr, "cipherSuites");
        if (!this.f12587a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(c1051lArr.length);
        for (C1051l c1051l : c1051lArr) {
            arrayList.add(c1051l.f12586a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        c((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void c(String... strArr) {
        A7.m.f(strArr, "cipherSuites");
        if (!this.f12587a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.c = (String[]) strArr.clone();
    }

    public void d(K... kArr) {
        if (!this.f12587a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(kArr.length);
        for (K k10 : kArr) {
            arrayList.add(k10.l);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        e((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Serializable, java.lang.String[]] */
    public void e(String... strArr) {
        A7.m.f(strArr, "tlsVersions");
        if (!this.f12587a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.d = (String[]) strArr.clone();
    }
}
