package g.a.a.p2;

import g.a.a.c1;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class j extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f3969c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.n f3970d;

    public j(g.a.a.r rVar) {
        this.f3970d = (g.a.a.n) rVar.q(0);
        this.f3969c = (g.a.a.j) rVar.q(1);
    }

    public j(byte[] bArr, int i2) {
        if (bArr.length != 8) {
            throw new IllegalArgumentException("salt length must be 8");
        }
        this.f3970d = new y0(bArr);
        this.f3969c = new g.a.a.j(i2);
    }

    public static j g(Object obj) {
        if (obj instanceof j) {
            return (j) obj;
        }
        if (obj != null) {
            return new j(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3970d);
        fVar.a(this.f3969c);
        return new c1(fVar);
    }

    public BigInteger h() {
        return this.f3969c.q();
    }

    public byte[] i() {
        return this.f3970d.p();
    }
}
