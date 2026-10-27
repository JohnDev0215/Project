package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class v1 extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4081d;

    public v1(byte[] bArr) throws IOException {
        this.f4081d = bArr;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        byte[] bArr = this.f4081d;
        if (bArr != null) {
            pVar.g(48, bArr);
        } else {
            super.m().h(pVar);
        }
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        byte[] bArr = this.f4081d;
        return bArr != null ? y1.a(bArr.length) + 1 + this.f4081d.length : super.m().i();
    }

    @Override // g.a.a.r, g.a.a.q
    public q l() {
        if (this.f4081d != null) {
            t();
        }
        return super.l();
    }

    @Override // g.a.a.r, g.a.a.q
    public q m() {
        if (this.f4081d != null) {
            t();
        }
        return super.m();
    }

    @Override // g.a.a.r
    public synchronized e q(int i2) {
        if (this.f4081d != null) {
            t();
        }
        return super.q(i2);
    }

    @Override // g.a.a.r
    public synchronized Enumeration r() {
        byte[] bArr = this.f4081d;
        if (bArr == null) {
            return super.r();
        }
        return new u1(bArr);
    }

    @Override // g.a.a.r
    public synchronized int size() {
        if (this.f4081d != null) {
            t();
        }
        return super.size();
    }

    public final void t() {
        u1 u1Var = new u1(this.f4081d);
        while (u1Var.hasMoreElements()) {
            this.f4011c.addElement(u1Var.nextElement());
        }
        this.f4081d = null;
    }
}
