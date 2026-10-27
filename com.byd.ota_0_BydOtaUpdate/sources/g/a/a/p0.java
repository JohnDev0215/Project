package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class p0 extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f3952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f3953d;
    public q s;
    public int x;
    public q y;

    public p0(f fVar) {
        int i2 = 0;
        q qVarS = s(fVar, 0);
        if (qVarS instanceof m) {
            this.f3952c = (m) qVarS;
            qVarS = s(fVar, 1);
            i2 = 1;
        }
        if (qVarS instanceof j) {
            this.f3953d = (j) qVarS;
            i2++;
            qVarS = s(fVar, i2);
        }
        if (!(qVarS instanceof x)) {
            this.s = qVarS;
            i2++;
            qVarS = s(fVar, i2);
        }
        if (fVar.c() != i2 + 1) {
            throw new IllegalArgumentException("input vector too large");
        }
        if (!(qVarS instanceof x)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        x xVar = (x) qVarS;
        t(xVar.q());
        this.y = xVar.p();
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        q qVar2;
        j jVar;
        m mVar;
        if (!(qVar instanceof p0)) {
            return false;
        }
        if (this == qVar) {
            return true;
        }
        p0 p0Var = (p0) qVar;
        m mVar2 = this.f3952c;
        if (mVar2 != null && ((mVar = p0Var.f3952c) == null || !mVar.equals(mVar2))) {
            return false;
        }
        j jVar2 = this.f3953d;
        if (jVar2 != null && ((jVar = p0Var.f3953d) == null || !jVar.equals(jVar2))) {
            return false;
        }
        q qVar3 = this.s;
        if (qVar3 == null || ((qVar2 = p0Var.s) != null && qVar2.equals(qVar3))) {
            return this.y.equals(p0Var.y);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m mVar = this.f3952c;
        if (mVar != null) {
            byteArrayOutputStream.write(mVar.f("DER"));
        }
        j jVar = this.f3953d;
        if (jVar != null) {
            byteArrayOutputStream.write(jVar.f("DER"));
        }
        q qVar = this.s;
        if (qVar != null) {
            byteArrayOutputStream.write(qVar.f("DER"));
        }
        byteArrayOutputStream.write(new h1(true, this.x, this.y).f("DER"));
        pVar.f(32, 8, byteArrayOutputStream.toByteArray());
    }

    @Override // g.a.a.l
    public int hashCode() {
        m mVar = this.f3952c;
        int iHashCode = mVar != null ? mVar.hashCode() : 0;
        j jVar = this.f3953d;
        if (jVar != null) {
            iHashCode ^= jVar.hashCode();
        }
        q qVar = this.s;
        if (qVar != null) {
            iHashCode ^= qVar.hashCode();
        }
        return iHashCode ^ this.y.hashCode();
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        return e().length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return true;
    }

    public q n() {
        return this.s;
    }

    public m o() {
        return this.f3952c;
    }

    public int p() {
        return this.x;
    }

    public q q() {
        return this.y;
    }

    public j r() {
        return this.f3953d;
    }

    public final q s(f fVar, int i2) {
        if (fVar.c() > i2) {
            return fVar.b(i2).c();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public final void t(int i2) {
        if (i2 >= 0 && i2 <= 2) {
            this.x = i2;
            return;
        }
        throw new IllegalArgumentException("invalid encoding value: " + i2);
    }
}
