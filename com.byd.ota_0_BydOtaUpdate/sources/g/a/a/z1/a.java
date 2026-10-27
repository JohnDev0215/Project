package g.a.a.z1;

import g.a.a.m;
import g.a.a.x2.g;
import g.a.a.x2.h;
import g.a.a.x2.i;
import g.a.d.b.d;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static h f4198a = new C0090a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Hashtable f4199b = new Hashtable();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Hashtable f4200c = new Hashtable();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Hashtable f4201d = new Hashtable();

    /* JADX INFO: renamed from: g.a.a.z1.a$a, reason: collision with other inner class name */
    public static class C0090a extends h {
        @Override // g.a.a.x2.h
        public g a() {
            BigInteger bigIntegerE = a.e("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C03");
            BigInteger bigIntegerE2 = a.e("F1FD178C0B3AD58F10126DE8CE42435B3961ADBCABC8CA6DE8FCF353D86E9C00");
            BigInteger bigIntegerE3 = a.e("EE353FCA5428A9300D4ABA754A44C00FDFEC0C9AE4B1A1803075ED967B7BB73F");
            BigInteger bigIntegerE4 = a.e("F1FD178C0B3AD58F10126DE8CE42435B53DC67E140D2BF941FFDD459C6D655E1");
            BigInteger bigIntegerValueOf = BigInteger.valueOf(1L);
            d dVarC = a.c(new d.f(bigIntegerE, bigIntegerE2, bigIntegerE3, bigIntegerE4, bigIntegerValueOf));
            return new g(dVarC, new i(dVarC, g.a.f.j.d.a("04B6B3D4C356C139EB31183D4749D423958C27D2DCAF98B70164C97A2DD98F5CFF6142E0F7C8B204911F9271F0F3ECEF8C2701C307E8E4C9E183115A1554062CFB")), bigIntegerE4, bigIntegerValueOf, (byte[]) null);
        }
    }

    static {
        d("FRP256v1", b.f4202a, f4198a);
    }

    public static d c(d dVar) {
        return dVar;
    }

    public static void d(String str, m mVar, h hVar) {
        f4199b.put(g.a.f.i.g(str), mVar);
        f4201d.put(mVar, str);
        f4200c.put(mVar, hVar);
    }

    public static BigInteger e(String str) {
        return new BigInteger(1, g.a.f.j.d.a(str));
    }

    public static g f(String str) {
        m mVarJ = j(str);
        if (mVarJ == null) {
            return null;
        }
        return g(mVarJ);
    }

    public static g g(m mVar) {
        h hVar = (h) f4200c.get(mVar);
        if (hVar == null) {
            return null;
        }
        return hVar.b();
    }

    public static String h(m mVar) {
        return (String) f4201d.get(mVar);
    }

    public static Enumeration i() {
        return f4201d.elements();
    }

    public static m j(String str) {
        return (m) f4199b.get(g.a.f.i.g(str));
    }
}
