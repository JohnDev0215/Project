package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class u0 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f4074c;

    public u0(byte[] bArr) {
        this.f4074c = g.a.f.a.f(bArr);
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f4074c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof u0) {
            return g.a.f.a.a(this.f4074c, ((u0) qVar).f4074c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(25, this.f4074c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f4074c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f4074c.length) + 1 + this.f4074c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }
}
