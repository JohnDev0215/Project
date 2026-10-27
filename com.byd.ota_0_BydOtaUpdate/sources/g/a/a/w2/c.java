package g.a.a.w2;

import g.a.a.c1;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.c f4113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.j f4114d;

    public c(g.a.a.r rVar) {
        this.f4113c = g.a.a.c.q(false);
        this.f4114d = null;
        if (rVar.size() == 0) {
            this.f4113c = null;
            this.f4114d = null;
            return;
        }
        if (rVar.q(0) instanceof g.a.a.c) {
            this.f4113c = g.a.a.c.o(rVar.q(0));
        } else {
            this.f4113c = null;
            this.f4114d = g.a.a.j.n(rVar.q(0));
        }
        if (rVar.size() > 1) {
            if (this.f4113c == null) {
                throw new IllegalArgumentException("wrong sequence in constructor");
            }
            this.f4114d = g.a.a.j.n(rVar.q(1));
        }
    }

    public static c g(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj instanceof y) {
            return g(y.a((y) obj));
        }
        if (obj != null) {
            return new c(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        g.a.a.c cVar = this.f4113c;
        if (cVar != null) {
            fVar.a(cVar);
        }
        g.a.a.j jVar = this.f4114d;
        if (jVar != null) {
            fVar.a(jVar);
        }
        return new c1(fVar);
    }

    public BigInteger h() {
        g.a.a.j jVar = this.f4114d;
        if (jVar != null) {
            return jVar.q();
        }
        return null;
    }

    public boolean i() {
        g.a.a.c cVar = this.f4113c;
        return cVar != null && cVar.r();
    }

    public String toString() {
        StringBuilder sb;
        if (this.f4114d != null) {
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(i());
            sb.append("), pathLenConstraint = ");
            sb.append(this.f4114d.q());
        } else {
            if (this.f4113c == null) {
                return "BasicConstraints: isCa(false)";
            }
            sb = new StringBuilder();
            sb.append("BasicConstraints: isCa(");
            sb.append(i());
            sb.append(")");
        }
        return sb.toString();
    }
}
