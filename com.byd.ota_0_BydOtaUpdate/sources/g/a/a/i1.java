package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class i1 extends q implements w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f3868c;

    public i1(String str) {
        this.f3868c = g.a.f.i.i(str);
    }

    public i1(byte[] bArr) {
        this.f3868c = bArr;
    }

    public static i1 n(Object obj) {
        if (obj == null || (obj instanceof i1)) {
            return (i1) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (i1) q.j((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    @Override // g.a.a.w
    public String d() {
        return g.a.f.i.c(this.f3868c);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar instanceof i1) {
            return g.a.f.a.a(this.f3868c, ((i1) qVar).f3868c);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.g(12, this.f3868c);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return g.a.f.a.z(this.f3868c);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        return y1.a(this.f3868c.length) + 1 + this.f3868c.length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public String toString() {
        return d();
    }
}
