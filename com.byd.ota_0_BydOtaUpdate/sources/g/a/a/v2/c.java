package g.a.a.v2;

import g.a.a.c1;
import g.a.a.e;
import g.a.a.l;
import g.a.a.q;
import g.a.a.r;
import g.a.a.x;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class c extends l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static d f4085c = g.a.a.v2.e.b.M;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4086d;
    public int s;
    public d x;
    public b[] y;

    public c(r rVar) {
        this(f4085c, rVar);
    }

    public c(d dVar, r rVar) {
        this.x = dVar;
        this.y = new b[rVar.size()];
        Enumeration enumerationR = rVar.r();
        int i2 = 0;
        while (enumerationR.hasMoreElements()) {
            this.y[i2] = b.h(enumerationR.nextElement());
            i2++;
        }
    }

    public c(d dVar, c cVar) {
        this.y = cVar.y;
        this.x = dVar;
    }

    public static c g(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj != null) {
            return new c(r.n(obj));
        }
        return null;
    }

    public static c h(x xVar, boolean z) {
        return g(r.o(xVar, true));
    }

    public static c i(d dVar, Object obj) {
        if (obj instanceof c) {
            return new c(dVar, (c) obj);
        }
        if (obj != null) {
            return new c(dVar, r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return new c1(this.y);
    }

    @Override // g.a.a.l
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c) && !(obj instanceof r)) {
            return false;
        }
        if (c().equals(((e) obj).c())) {
            return true;
        }
        try {
            return this.x.a(this, new c(r.n(((e) obj).c())));
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // g.a.a.l
    public int hashCode() {
        if (this.f4086d) {
            return this.s;
        }
        this.f4086d = true;
        int iB = this.x.b(this);
        this.s = iB;
        return iB;
    }

    public b[] j() {
        b[] bVarArr = this.y;
        int length = bVarArr.length;
        b[] bVarArr2 = new b[length];
        System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
        return bVarArr2;
    }

    public String toString() {
        return this.x.c(this);
    }
}
