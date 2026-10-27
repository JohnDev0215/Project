package g.a.a.x2;

import g.a.a.c1;
import g.a.a.o0;
import g.a.a.q;
import g.a.a.r;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public o0 f4172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.j f4173d;

    public c(r rVar) {
        if (rVar.size() == 2) {
            this.f4172c = o0.t(rVar.q(0));
            this.f4173d = g.a.a.j.n(rVar.q(1));
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
        }
    }

    public c(byte[] bArr, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        }
        this.f4172c = new o0(bArr);
        this.f4173d = new g.a.a.j(i2);
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

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4172c);
        fVar.a(this.f4173d);
        return new c1(fVar);
    }

    public BigInteger h() {
        return this.f4173d.p();
    }

    public byte[] i() {
        return this.f4172c.p();
    }
}
