package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f5592c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f5593d;

    public m(r rVar) {
        if (!g.a.a.j.n(rVar.q(0)).q().equals(BigInteger.valueOf(0L))) {
            throw new IllegalArgumentException("unknown version of sequence");
        }
        this.f5592c = g.a.f.a.f(n.n(rVar.q(1)).p());
        this.f5593d = g.a.f.a.f(n.n(rVar.q(2)).p());
    }

    public m(byte[] bArr, byte[] bArr2) {
        this.f5592c = g.a.f.a.f(bArr);
        this.f5593d = g.a.f.a.f(bArr2);
    }

    public static m g(Object obj) {
        if (obj instanceof m) {
            return (m) obj;
        }
        if (obj != null) {
            return new m(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(0L));
        fVar.a(new y0(this.f5592c));
        fVar.a(new y0(this.f5593d));
        return new c1(fVar);
    }

    public byte[] h() {
        return g.a.f.a.f(this.f5592c);
    }

    public byte[] i() {
        return g.a.f.a.f(this.f5593d);
    }
}
