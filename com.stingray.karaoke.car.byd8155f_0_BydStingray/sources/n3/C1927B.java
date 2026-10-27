package n3;

import A7.C0054b;
import java.util.ArrayList;
import java.util.Iterator;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import u.C2459G;

/* JADX INFO: renamed from: n3.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1927B extends y implements Iterable, B7.a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final /* synthetic */ int f19789y = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C2459G f19790u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f19791v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f19792w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f19793x;

    public C1927B(C1929D c1929d) {
        super(c1929d);
        this.f19790u = new C2459G(0);
    }

    @Override // n3.y
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C1927B)) {
            return false;
        }
        if (super.equals(obj)) {
            C2459G c2459g = this.f19790u;
            int iF = c2459g.f();
            C1927B c1927b = (C1927B) obj;
            C2459G c2459g2 = c1927b.f19790u;
            if (iF == c2459g2.f() && this.f19791v == c1927b.f19791v) {
                for (y yVar : (P8.a) P8.m.X(new C0054b(c2459g, 3))) {
                    if (!yVar.equals(c2459g2.c(yVar.f19917q))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // n3.y
    public final int hashCode() {
        int iD = this.f19791v;
        C2459G c2459g = this.f19790u;
        int iF = c2459g.f();
        for (int i7 = 0; i7 < iF; i7++) {
            iD = (((iD * 31) + c2459g.d(i7)) * 31) + ((y) c2459g.g(i7)).hashCode();
        }
        return iD;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C1926A(this);
    }

    @Override // n3.y
    public final w l(h3.y yVar) {
        return t(yVar, false, this);
    }

    public final y r(String str, boolean z6) {
        Object next;
        C1927B c1927b;
        A7.m.f(str, "route");
        C2459G c2459g = this.f19790u;
        A7.m.f(c2459g, "<this>");
        Iterator it = ((P8.a) P8.m.X(new C0054b(c2459g, 3))).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            y yVar = (y) next;
            if (Q8.o.t0(yVar.f19918r, str, false) || yVar.q(str) != null) {
                break;
            }
        }
        y yVar2 = (y) next;
        if (yVar2 != null) {
            return yVar2;
        }
        if (!z6 || (c1927b = this.f19914m) == null || Q8.h.M0(str)) {
            return null;
        }
        return c1927b.r(str, true);
    }

    public final y s(int i7, C1927B c1927b, boolean z6, y yVar) {
        C2459G c2459g = this.f19790u;
        y yVarS = (y) c2459g.c(i7);
        if (yVar != null) {
            if (A7.m.a(yVarS, yVar) && A7.m.a(yVarS.f19914m, yVar.f19914m)) {
                return yVarS;
            }
            yVarS = null;
        } else if (yVarS != null) {
            return yVarS;
        }
        if (z6) {
            Iterator it = ((P8.a) P8.m.X(new C0054b(c2459g, 3))).iterator();
            while (true) {
                if (!it.hasNext()) {
                    yVarS = null;
                    break;
                }
                y yVar2 = (y) it.next();
                yVarS = (!(yVar2 instanceof C1927B) || A7.m.a(yVar2, c1927b)) ? null : ((C1927B) yVar2).s(i7, this, true, yVar);
                if (yVarS != null) {
                    break;
                }
            }
        }
        if (yVarS != null) {
            return yVarS;
        }
        C1927B c1927b2 = this.f19914m;
        if (c1927b2 == null || c1927b2.equals(c1927b)) {
            return null;
        }
        C1927B c1927b3 = this.f19914m;
        A7.m.c(c1927b3);
        return c1927b3.s(i7, this, z6, yVar);
    }

    public final w t(h3.y yVar, boolean z6, C1927B c1927b) {
        w wVarT;
        w wVarL = super.l(yVar);
        ArrayList arrayList = new ArrayList();
        C1926A c1926a = new C1926A(this);
        while (true) {
            if (!c1926a.hasNext()) {
                break;
            }
            y yVar2 = (y) c1926a.next();
            wVarT = A7.m.a(yVar2, c1927b) ? null : yVar2.l(yVar);
            if (wVarT != null) {
                arrayList.add(wVarT);
            }
        }
        w wVar = (w) AbstractC1998m.j0(arrayList);
        C1927B c1927b2 = this.f19914m;
        if (c1927b2 != null && z6 && !c1927b2.equals(c1927b)) {
            wVarT = c1927b2.t(yVar, true, this);
        }
        return (w) AbstractC1998m.j0(AbstractC1996k.M(new w[]{wVarL, wVar, wVarT}));
    }

    @Override // n3.y
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        String str = this.f19793x;
        y yVarR = (str == null || Q8.h.M0(str)) ? null : r(str, true);
        if (yVarR == null) {
            yVarR = s(this.f19791v, this, false, null);
        }
        sb.append(" startDestination=");
        if (yVarR == null) {
            String str2 = this.f19793x;
            if (str2 != null) {
                sb.append(str2);
            } else {
                String str3 = this.f19792w;
                if (str3 != null) {
                    sb.append(str3);
                } else {
                    sb.append("0x" + Integer.toHexString(this.f19791v));
                }
            }
        } else {
            sb.append("{");
            sb.append(yVarR.toString());
            sb.append("}");
        }
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }

    public final w u(String str, boolean z6, C1927B c1927b) {
        w wVarU;
        A7.m.f(str, "route");
        w wVarQ = q(str);
        ArrayList arrayList = new ArrayList();
        C1926A c1926a = new C1926A(this);
        while (true) {
            if (!c1926a.hasNext()) {
                break;
            }
            y yVar = (y) c1926a.next();
            wVarU = A7.m.a(yVar, c1927b) ? null : yVar instanceof C1927B ? ((C1927B) yVar).u(str, false, this) : yVar.q(str);
            if (wVarU != null) {
                arrayList.add(wVarU);
            }
        }
        w wVar = (w) AbstractC1998m.j0(arrayList);
        C1927B c1927b2 = this.f19914m;
        if (c1927b2 != null && z6 && !c1927b2.equals(c1927b)) {
            wVarU = c1927b2.u(str, true, this);
        }
        return (w) AbstractC1998m.j0(AbstractC1996k.M(new w[]{wVarQ, wVar, wVarU}));
    }
}
