package g.a.c.e;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class l implements AlgorithmParameterSpec, g.a.c.c.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n f5253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5254b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5255c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5256d;

    public l(n nVar) {
        this.f5253a = nVar;
        this.f5255c = g.a.a.d2.a.p.r();
        this.f5256d = null;
    }

    public l(String str) {
        this(str, g.a.a.d2.a.p.r(), null);
    }

    public l(String str, String str2) {
        this(str, str2, null);
    }

    public l(String str, String str2, String str3) {
        g.a.a.d2.e eVarA;
        try {
            eVarA = g.a.a.d2.d.a(new g.a.a.m(str));
        } catch (IllegalArgumentException unused) {
            g.a.a.m mVarB = g.a.a.d2.d.b(str);
            if (mVarB != null) {
                str = mVarB.r();
                eVarA = g.a.a.d2.d.a(mVarB);
            } else {
                eVarA = null;
            }
        }
        if (eVarA == null) {
            throw new IllegalArgumentException("no key parameter set for passed in name/OID.");
        }
        this.f5253a = new n(eVarA.h(), eVarA.i(), eVarA.g());
        this.f5254b = str;
        this.f5255c = str2;
        this.f5256d = str3;
    }

    public static l e(g.a.a.d2.f fVar) {
        return fVar.h() != null ? new l(fVar.j().r(), fVar.g().r(), fVar.h().r()) : new l(fVar.j().r(), fVar.g().r());
    }

    @Override // g.a.c.c.d
    public n a() {
        return this.f5253a;
    }

    @Override // g.a.c.c.d
    public String b() {
        return this.f5254b;
    }

    @Override // g.a.c.c.d
    public String c() {
        return this.f5255c;
    }

    @Override // g.a.c.c.d
    public String d() {
        return this.f5256d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        if (!this.f5253a.equals(lVar.f5253a) || !this.f5255c.equals(lVar.f5255c)) {
            return false;
        }
        String str = this.f5256d;
        String str2 = lVar.f5256d;
        return str == str2 || (str != null && str.equals(str2));
    }

    public int hashCode() {
        int iHashCode = this.f5253a.hashCode() ^ this.f5255c.hashCode();
        String str = this.f5256d;
        return iHashCode ^ (str != null ? str.hashCode() : 0);
    }
}
