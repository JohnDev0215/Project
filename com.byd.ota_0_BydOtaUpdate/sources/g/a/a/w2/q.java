package g.a.a.w2;

import g.a.a.o0;

/* JADX INFO: loaded from: classes.dex */
public class q extends g.a.a.l {
    public boolean E3;
    public g.a.a.r F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l f4138c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4139d;
    public boolean s;
    public s x;
    public boolean y;

    public q(g.a.a.r rVar) {
        this.F3 = rVar;
        for (int i2 = 0; i2 != rVar.size(); i2++) {
            g.a.a.x xVarN = g.a.a.x.n(rVar.q(i2));
            int iQ = xVarN.q();
            if (iQ == 0) {
                this.f4138c = l.i(xVarN, true);
            } else if (iQ == 1) {
                this.f4139d = g.a.a.c.p(xVarN, false).r();
            } else if (iQ == 2) {
                this.s = g.a.a.c.p(xVarN, false).r();
            } else if (iQ == 3) {
                this.x = new s(o0.u(xVarN, false));
            } else if (iQ == 4) {
                this.y = g.a.a.c.p(xVarN, false).r();
            } else {
                if (iQ != 5) {
                    throw new IllegalArgumentException("unknown tag in IssuingDistributionPoint");
                }
                this.E3 = g.a.a.c.p(xVarN, false).r();
            }
        }
    }

    public static q i(Object obj) {
        if (obj instanceof q) {
            return (q) obj;
        }
        if (obj != null) {
            return new q(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.F3;
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

    public final String h(boolean z) {
        return z ? "true" : "false";
    }

    public boolean j() {
        return this.y;
    }

    public String toString() {
        String strD = g.a.f.i.d();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IssuingDistributionPoint: [");
        stringBuffer.append(strD);
        l lVar = this.f4138c;
        if (lVar != null) {
            g(stringBuffer, strD, "distributionPoint", lVar.toString());
        }
        boolean z = this.f4139d;
        if (z) {
            g(stringBuffer, strD, "onlyContainsUserCerts", h(z));
        }
        boolean z2 = this.s;
        if (z2) {
            g(stringBuffer, strD, "onlyContainsCACerts", h(z2));
        }
        s sVar = this.x;
        if (sVar != null) {
            g(stringBuffer, strD, "onlySomeReasons", sVar.toString());
        }
        boolean z3 = this.E3;
        if (z3) {
            g(stringBuffer, strD, "onlyContainsAttributeCerts", h(z3));
        }
        boolean z4 = this.y;
        if (z4) {
            g(stringBuffer, strD, "indirectCRL", h(z4));
        }
        stringBuffer.append("]");
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }
}
