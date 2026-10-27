package g.a.a.w2;

import g.a.a.h1;

/* JADX INFO: loaded from: classes.dex */
public class l extends g.a.a.l implements g.a.a.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.e f4129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4130d;

    public l(g.a.a.x xVar) {
        int iQ = xVar.q();
        this.f4130d = iQ;
        this.f4129c = iQ == 0 ? p.h(xVar, false) : g.a.a.t.p(xVar, false);
    }

    public static l h(Object obj) {
        if (obj == null || (obj instanceof l)) {
            return (l) obj;
        }
        if (obj instanceof g.a.a.x) {
            return new l((g.a.a.x) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public static l i(g.a.a.x xVar, boolean z) {
        return h(g.a.a.x.o(xVar, true));
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return new h1(false, this.f4130d, this.f4129c);
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
        String string;
        String str;
        String strD = g.a.f.i.d();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPointName: [");
        stringBuffer.append(strD);
        if (this.f4130d == 0) {
            string = this.f4129c.toString();
            str = "fullName";
        } else {
            string = this.f4129c.toString();
            str = "nameRelativeToCRLIssuer";
        }
        g(stringBuffer, strD, str, string);
        stringBuffer.append("]");
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }
}
