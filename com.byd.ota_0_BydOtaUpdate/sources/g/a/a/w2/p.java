package g.a.a.w2;

import g.a.a.c1;

/* JADX INFO: loaded from: classes.dex */
public class p extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o[] f4137c;

    public p(g.a.a.r rVar) {
        this.f4137c = new o[rVar.size()];
        for (int i2 = 0; i2 != rVar.size(); i2++) {
            this.f4137c[i2] = o.g(rVar.q(i2));
        }
    }

    public static p g(Object obj) {
        if (obj instanceof p) {
            return (p) obj;
        }
        if (obj != null) {
            return new p(g.a.a.r.n(obj));
        }
        return null;
    }

    public static p h(g.a.a.x xVar, boolean z) {
        return g(g.a.a.r.o(xVar, z));
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return new c1(this.f4137c);
    }

    public o[] i() {
        o[] oVarArr = this.f4137c;
        o[] oVarArr2 = new o[oVarArr.length];
        System.arraycopy(oVarArr, 0, oVarArr2, 0, oVarArr.length);
        return oVarArr2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String strD = g.a.f.i.d();
        stringBuffer.append("GeneralNames:");
        stringBuffer.append(strD);
        for (int i2 = 0; i2 != this.f4137c.length; i2++) {
            stringBuffer.append("    ");
            stringBuffer.append(this.f4137c[i2]);
            stringBuffer.append(strD);
        }
        return stringBuffer.toString();
    }
}
