package g.a.a.w2;

/* JADX INFO: loaded from: classes.dex */
public class d extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.r f4115c;

    public d(g.a.a.r rVar) {
        this.f4115c = null;
        this.f4115c = rVar;
    }

    public static d h(Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        if (obj != null) {
            return new d(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4115c;
    }

    public k[] g() {
        k[] kVarArr = new k[this.f4115c.size()];
        for (int i2 = 0; i2 != this.f4115c.size(); i2++) {
            kVarArr[i2] = k.h(this.f4115c.q(i2));
        }
        return kVarArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String strD = g.a.f.i.d();
        stringBuffer.append("CRLDistPoint:");
        stringBuffer.append(strD);
        k[] kVarArrG = g();
        for (int i2 = 0; i2 != kVarArrG.length; i2++) {
            stringBuffer.append("    ");
            stringBuffer.append(kVarArrG[i2]);
            stringBuffer.append(strD);
        }
        return stringBuffer.toString();
    }
}
