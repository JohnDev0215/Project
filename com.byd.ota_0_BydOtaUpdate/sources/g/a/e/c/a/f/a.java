package g.a.e.c.a.f;

import g.a.a.m;
import g.a.b.a0.u;
import g.a.b.a0.x;
import g.a.b.a0.z;
import g.a.b.n;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static n a(m mVar) {
        if (mVar.equals(g.a.a.l2.b.f3909c)) {
            return new u();
        }
        if (mVar.equals(g.a.a.l2.b.f3911e)) {
            return new x();
        }
        if (mVar.equals(g.a.a.l2.b.m)) {
            return new z(128);
        }
        if (mVar.equals(g.a.a.l2.b.n)) {
            return new z(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + mVar);
    }

    public static String b(m mVar) {
        if (mVar.equals(g.a.a.l2.b.f3909c)) {
            return "SHA256";
        }
        if (mVar.equals(g.a.a.l2.b.f3911e)) {
            return "SHA512";
        }
        if (mVar.equals(g.a.a.l2.b.m)) {
            return "SHAKE128";
        }
        if (mVar.equals(g.a.a.l2.b.n)) {
            return "SHAKE256";
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + mVar);
    }
}
