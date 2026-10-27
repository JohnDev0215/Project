package g.a.a.p2;

import g.a.a.c1;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class q extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f3987c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.n f3988d;

    public q(int i2, byte[] bArr) {
        this.f3987c = new g.a.a.j(i2);
        this.f3988d = new y0(bArr);
    }

    public q(g.a.a.r rVar) {
        g.a.a.e eVarQ;
        if (rVar.size() == 1) {
            this.f3987c = null;
            eVarQ = rVar.q(0);
        } else {
            this.f3987c = (g.a.a.j) rVar.q(0);
            eVarQ = rVar.q(1);
        }
        this.f3988d = (g.a.a.n) eVarQ;
    }

    public q(byte[] bArr) {
        this.f3987c = null;
        this.f3988d = new y0(bArr);
    }

    public static q h(Object obj) {
        if (obj instanceof q) {
            return (q) obj;
        }
        if (obj != null) {
            return new q(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        g.a.a.j jVar = this.f3987c;
        if (jVar != null) {
            fVar.a(jVar);
        }
        fVar.a(this.f3988d);
        return new c1(fVar);
    }

    public byte[] g() {
        return this.f3988d.p();
    }

    public BigInteger i() {
        g.a.a.j jVar = this.f3987c;
        if (jVar == null) {
            return null;
        }
        return jVar.q();
    }
}
