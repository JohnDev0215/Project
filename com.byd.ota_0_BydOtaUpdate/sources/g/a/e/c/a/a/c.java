package g.a.e.c.a.a;

import g.a.a.w0;
import g.a.b.n;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static g.a.a.w2.a a(String str) {
        if (str.equals("SHA-1")) {
            return new g.a.a.w2.a(g.a.a.o2.b.f3948i, w0.f4105c);
        }
        if (str.equals("SHA-224")) {
            return new g.a.a.w2.a(g.a.a.l2.b.f3912f, w0.f4105c);
        }
        if (str.equals("SHA-256")) {
            return new g.a.a.w2.a(g.a.a.l2.b.f3909c, w0.f4105c);
        }
        if (str.equals("SHA-384")) {
            return new g.a.a.w2.a(g.a.a.l2.b.f3910d, w0.f4105c);
        }
        if (str.equals("SHA-512")) {
            return new g.a.a.w2.a(g.a.a.l2.b.f3911e, w0.f4105c);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }

    public static n b(g.a.a.w2.a aVar) {
        if (aVar.g().equals(g.a.a.o2.b.f3948i)) {
            return g.a.b.n0.a.b();
        }
        if (aVar.g().equals(g.a.a.l2.b.f3912f)) {
            return g.a.b.n0.a.c();
        }
        if (aVar.g().equals(g.a.a.l2.b.f3909c)) {
            return g.a.b.n0.a.d();
        }
        if (aVar.g().equals(g.a.a.l2.b.f3910d)) {
            return g.a.b.n0.a.e();
        }
        if (aVar.g().equals(g.a.a.l2.b.f3911e)) {
            return g.a.b.n0.a.j();
        }
        throw new IllegalArgumentException("unrecognised OID in digest algorithm identifier: " + aVar.g());
    }
}
