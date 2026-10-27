package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class l1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3904c;

    public l1(byte[] bArr) {
        this.f3904c = bArr;
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.b(this.f3904c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof l1) {
            return g.a.f.a.a(this.f3904c, ((l1) qVar).f3904c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(26, this.f3904c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3904c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3904c.length) + 1 + this.f3904c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
