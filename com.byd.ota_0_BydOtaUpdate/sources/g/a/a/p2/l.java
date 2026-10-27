package g.a.a.p2;

import g.a.a.c1;
import g.a.a.w0;
import g.a.a.y0;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class l extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.w2.a f3973c = new g.a.a.w2.a(n.Q, w0.f4105c);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.n f3974d;
    public final g.a.a.j s;
    public final g.a.a.j x;
    public final g.a.a.w2.a y;

    public l(g.a.a.r rVar) {
        Enumeration enumerationR = rVar.r();
        this.f3974d = (g.a.a.n) enumerationR.nextElement();
        this.s = (g.a.a.j) enumerationR.nextElement();
        if (enumerationR.hasMoreElements()) {
            Object objNextElement = enumerationR.nextElement();
            if (objNextElement instanceof g.a.a.j) {
                this.x = g.a.a.j.n(objNextElement);
                objNextElement = enumerationR.hasMoreElements() ? enumerationR.nextElement() : null;
            } else {
                this.x = null;
            }
            if (objNextElement != null) {
                this.y = g.a.a.w2.a.h(objNextElement);
                return;
            }
        } else {
            this.x = null;
        }
        this.y = null;
    }

    public l(byte[] bArr, int i2) {
        this(bArr, i2, 0);
    }

    public l(byte[] bArr, int i2, int i3) {
        this(bArr, i2, i3, null);
    }

    public l(byte[] bArr, int i2, int i3, g.a.a.w2.a aVar) {
        this.f3974d = new y0(g.a.f.a.f(bArr));
        this.s = new g.a.a.j(i2);
        this.x = i3 > 0 ? new g.a.a.j(i3) : null;
        this.y = aVar;
    }

    public static l g(Object obj) {
        if (obj instanceof l) {
            return (l) obj;
        }
        if (obj != null) {
            return new l(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3974d);
        fVar.a(this.s);
        g.a.a.j jVar = this.x;
        if (jVar != null) {
            fVar.a(jVar);
        }
        g.a.a.w2.a aVar = this.y;
        if (aVar != null && !aVar.equals(f3973c)) {
            fVar.a(this.y);
        }
        return new c1(fVar);
    }

    public BigInteger h() {
        return this.s.q();
    }

    public BigInteger i() {
        g.a.a.j jVar = this.x;
        if (jVar != null) {
            return jVar.q();
        }
        return null;
    }

    public g.a.a.w2.a j() {
        g.a.a.w2.a aVar = this.y;
        return aVar != null ? aVar : f3973c;
    }

    public byte[] k() {
        return this.f3974d.p();
    }

    public boolean l() {
        g.a.a.w2.a aVar = this.y;
        return aVar == null || aVar.equals(f3973c);
    }
}
