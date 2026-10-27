package g.a.a.p2;

import g.a.a.c1;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f3975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.n f3976d;

    public m(g.a.a.r rVar) {
        this.f3976d = (g.a.a.n) rVar.q(0);
        this.f3975c = g.a.a.j.n(rVar.q(1));
    }

    public m(byte[] bArr, int i2) {
        this.f3976d = new y0(bArr);
        this.f3975c = new g.a.a.j(i2);
    }

    public static m h(Object obj) {
        if (obj instanceof m) {
            return (m) obj;
        }
        if (obj != null) {
            return new m(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3976d);
        fVar.a(this.f3975c);
        return new c1(fVar);
    }

    public byte[] g() {
        return this.f3976d.p();
    }

    public BigInteger i() {
        return this.f3975c.q();
    }
}
