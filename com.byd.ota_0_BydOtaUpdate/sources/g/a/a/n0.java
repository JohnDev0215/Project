package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class n0 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char[] f3930c;

    public n0(String str) {
        this.f3930c = str.toCharArray();
    }

    public n0(char[] cArr) {
        this.f3930c = cArr;
    }

    @Override // g.a.a.w
    public String d() {
        return new String(this.f3930c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof n0) {
            return g.a.f.a.b(this.f3930c, ((n0) qVar).f3930c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.c(30);
        pVar.i(this.f3930c.length * 2);
        int i2 = 0;
        while (true) {
            char[] cArr = this.f3930c;
            if (i2 == cArr.length) {
                return;
            }
            char c2 = cArr[i2];
            pVar.c((byte) (c2 >> '\b'));
            pVar.c((byte) c2);
            i2++;
        }
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.A(this.f3930c);
    }

    @Override // g.a.a.q
    public int i() {
        return y1.a(this.f3930c.length * 2) + 1 + (this.f3930c.length * 2);
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
