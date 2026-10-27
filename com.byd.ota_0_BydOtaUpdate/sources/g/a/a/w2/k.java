package g.a.a.w2;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.o0;

/* JADX INFO: loaded from: classes.dex */
public class k extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l f4127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public s f4128d;
    public p s;

    public k(g.a.a.r rVar) {
        for (int i2 = 0; i2 != rVar.size(); i2++) {
            g.a.a.x xVarN = g.a.a.x.n(rVar.q(i2));
            int iQ = xVarN.q();
            if (iQ == 0) {
                this.f4127c = l.i(xVarN, true);
            } else if (iQ == 1) {
                this.f4128d = new s(o0.u(xVarN, false));
            } else {
                if (iQ != 2) {
                    throw new IllegalArgumentException("Unknown tag encountered in structure: " + xVarN.q());
                }
                this.s = p.h(xVarN, false);
            }
        }
    }

    public static k h(Object obj) {
        if (obj == null || (obj instanceof k)) {
            return (k) obj;
        }
        if (obj instanceof g.a.a.r) {
            return new k((g.a.a.r) obj);
        }
        throw new IllegalArgumentException("Invalid DistributionPoint: " + obj.getClass().getName());
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        if (this.f4127c != null) {
            fVar.a(new h1(0, this.f4127c));
        }
        if (this.f4128d != null) {
            fVar.a(new h1(false, 1, this.f4128d));
        }
        if (this.s != null) {
            fVar.a(new h1(false, 2, this.s));
        }
        return new c1(fVar);
    }

    public final void g(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }

    public String toString() {
        String strD = g.a.f.i.d();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPoint: [");
        stringBuffer.append(strD);
        l lVar = this.f4127c;
        if (lVar != null) {
            g(stringBuffer, strD, "distributionPoint", lVar.toString());
        }
        s sVar = this.f4128d;
        if (sVar != null) {
            g(stringBuffer, strD, "reasons", sVar.toString());
        }
        p pVar = this.s;
        if (pVar != null) {
            g(stringBuffer, strD, "cRLIssuer", pVar.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }
}
