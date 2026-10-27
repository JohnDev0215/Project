package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class b1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3771c;

    public b1(byte[] bArr) {
        this.f3771c = bArr;
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f3771c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof b1) {
            return g.a.f.a.a(this.f3771c, ((b1) qVar).f3771c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(19, this.f3771c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3771c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3771c.length) + 1 + this.f3771c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
