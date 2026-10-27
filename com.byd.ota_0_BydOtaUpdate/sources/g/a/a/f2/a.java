package g.a.a.f2;

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
    public static h f3828a = new C0086a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static h f3829b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Hashtable f3830c = new Hashtable();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Hashtable f3831d = new Hashtable();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Hashtable f3832e = new Hashtable();

    /* JADX INFO: renamed from: g.a.a.f2.a$a, reason: collision with other inner class name */
    public static class C0086a extends h {
        @Override // g.a.a.x2.h
        public g a() {
            BigInteger bigIntegerE = a.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF");
            BigInteger bigIntegerE2 = a.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC");
            BigInteger bigIntegerE3 = a.e("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93");
            BigInteger bigIntegerE4 = a.e("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123");
            BigInteger bigIntegerValueOf = BigInteger.valueOf(1L);
            d dVarC = a.c(new d.f(bigIntegerE, bigIntegerE2, bigIntegerE3, bigIntegerE4, bigIntegerValueOf));
            return new g(dVarC, new i(dVarC, g.a.f.j.d.a("0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0")), bigIntegerE4, bigIntegerValueOf, (byte[]) null);
        }
    }

    public static class b extends h {
        @Override // g.a.a.x2.h
        public g a() {
            BigInteger bigIntegerE = a.e("BDB6F4FE3E8B1D9E0DA8C0D46F4C318CEFE4AFE3B6B8551F");
            BigInteger bigIntegerE2 = a.e("BB8E5E8FBC115E139FE6A814FE48AAA6F0ADA1AA5DF91985");
            BigInteger bigIntegerE3 = a.e("1854BEBDC31B21B7AEFC80AB0ECD10D5B1B3308E6DBF11C1");
            BigInteger bigIntegerE4 = a.e("BDB6F4FE3E8B1D9E0DA8C0D40FC962195DFAE76F56564677");
            BigInteger bigIntegerValueOf = BigInteger.valueOf(1L);
            d dVarC = a.c(new d.f(bigIntegerE, bigIntegerE2, bigIntegerE3, bigIntegerE4, bigIntegerValueOf));
            return new g(dVarC, new i(dVarC, g.a.f.j.d.a("044AD5F7048DE709AD51236DE65E4D4B482C836DC6E410664002BB3A02D4AAADACAE24817A4CA3A1B014B5270432DB27D2")), bigIntegerE4, bigIntegerValueOf, (byte[]) null);
        }
    }

    static {
        d("wapip192v1", g.a.a.f2.b.J, f3829b);
        d("sm2p256v1", g.a.a.f2.b.F, f3828a);
    }

    public static d c(d dVar) {
        return dVar;
    }

    public static void d(String str, m mVar, h hVar) {
        f3830c.put(g.a.f.i.g(str), mVar);
        f3832e.put(mVar, str);
        f3831d.put(mVar, hVar);
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
        h hVar = (h) f3831d.get(mVar);
        if (hVar == null) {
            return null;
        }
        return hVar.b();
    }

    public static String h(m mVar) {
        return (String) f3832e.get(mVar);
    }

    public static Enumeration i() {
        return f3832e.elements();
    }

    public static m j(String str) {
        return (m) f3830c.get(g.a.f.i.g(str));
    }
}
