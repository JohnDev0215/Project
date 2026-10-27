package h9;

import A7.m;
import c9.H;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    static {
        C2322l c2322l = C2322l.o;
        m6.b.p("\"\\");
        m6.b.p("\t ,=");
    }

    public static final boolean a(H h10) {
        if (m.a((String) h10.l.f2766n, "HEAD")) {
            return false;
        }
        int i7 = h10.o;
        return (((i7 >= 100 && i7 < 200) || i7 == 204 || i7 == 304) && d9.b.k(h10) == -1 && !"chunked".equalsIgnoreCase(H.g(h10, "Transfer-Encoding"))) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(c9.C1041b r35, c9.w r36, c9.u r37) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h9.d.b(c9.b, c9.w, c9.u):void");
    }
}
