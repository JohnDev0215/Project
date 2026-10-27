package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class s0 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f4033c;

    public s0(byte[] bArr) {
        this.f4033c = bArr;
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f4033c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof s0) {
            return g.a.f.a.a(this.f4033c, ((s0) qVar).f4033c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(27, this.f4033c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f4033c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f4033c.length) + 1 + this.f4033c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
