package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class k1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3887c;

    public k1(byte[] bArr) {
        this.f3887c = g.a.f.a.f(bArr);
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f3887c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof k1) {
            return g.a.f.a.a(this.f3887c, ((k1) qVar).f3887c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(21, this.f3887c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3887c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3887c.length) + 1 + this.f3887c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }
}
