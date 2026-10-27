package g.a.a.w2;

import g.a.a.h1;
import g.a.a.v0;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class o extends g.a.a.l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.e f4135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4136d;

    public o(int i2, g.a.a.e eVar) {
        this.f4135c = eVar;
        this.f4136d = i2;
    }

    public static o g(Object obj) {
        if (obj == null || (obj instanceof o)) {
            return (o) obj;
        }
        if (obj instanceof g.a.a.x) {
            g.a.a.x xVar = (g.a.a.x) obj;
            int iQ = xVar.q();
            switch (iQ) {
                case 0:
                    return new o(iQ, g.a.a.r.o(xVar, false));
                case 1:
                    return new o(iQ, v0.o(xVar, false));
                case 2:
                    return new o(iQ, v0.o(xVar, false));
                case 3:
                    throw new IllegalArgumentException("unknown tag: " + iQ);
                case 4:
                    return new o(iQ, g.a.a.v2.c.h(xVar, true));
                case 5:
                    return new o(iQ, g.a.a.r.o(xVar, false));
                case 6:
                    return new o(iQ, v0.o(xVar, false));
                case 7:
                    return new o(iQ, g.a.a.n.o(xVar, false));
                case 8:
                    return new o(iQ, g.a.a.m.t(xVar, false));
            }
        }
        if (obj instanceof byte[]) {
            try {
                return g(g.a.a.q.j((byte[]) obj));
            } catch (IOException unused) {
                throw new IllegalArgumentException("unable to parse encoded general name");
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4136d == 4 ? new h1(true, this.f4136d, this.f4135c) : new h1(false, this.f4136d, this.f4135c);
    }

    public g.a.a.e h() {
        return this.f4135c;
    }

    public int i() {
        return this.f4136d;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            int r1 = r3.f4136d
            r0.append(r1)
            java.lang.String r1 = ": "
            r0.append(r1)
            int r1 = r3.f4136d
            r2 = 1
            if (r1 == r2) goto L2f
            r2 = 2
            if (r1 == r2) goto L2f
            r2 = 4
            if (r1 == r2) goto L24
            r2 = 6
            if (r1 == r2) goto L2f
            g.a.a.e r1 = r3.f4135c
            java.lang.String r1 = r1.toString()
            goto L39
        L24:
            g.a.a.e r1 = r3.f4135c
            g.a.a.v2.c r1 = g.a.a.v2.c.g(r1)
            java.lang.String r1 = r1.toString()
            goto L39
        L2f:
            g.a.a.e r1 = r3.f4135c
            g.a.a.v0 r1 = g.a.a.v0.n(r1)
            java.lang.String r1 = r1.d()
        L39:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.a.w2.o.toString():java.lang.String");
    }
}
