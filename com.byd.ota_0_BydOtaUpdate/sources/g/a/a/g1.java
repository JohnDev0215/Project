package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class g1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f3845c;

    public g1(byte[] bArr) {
        this.f3845c = g.a.f.a.f(bArr);
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f3845c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof g1) {
            return g.a.f.a.a(this.f3845c, ((g1) qVar).f3845c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(20, this.f3845c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3845c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3845c.length) + 1 + this.f3845c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
