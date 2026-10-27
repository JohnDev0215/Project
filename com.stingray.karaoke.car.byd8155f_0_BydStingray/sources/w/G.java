package w;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final G f23068b;
    public static final G c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P f23069a;

    static {
        LinkedHashMap linkedHashMap = null;
        H h10 = null;
        N n10 = null;
        t tVar = null;
        h6.n nVar = null;
        f23068b = new G(new P(h10, n10, tVar, nVar, false, linkedHashMap, 63));
        c = new G(new P(h10, n10, tVar, nVar, true, linkedHashMap, 47));
    }

    public G(P p10) {
        this.f23069a = p10;
    }

    public final G a(G g7) {
        P p10 = g7.f23069a;
        P p11 = this.f23069a;
        H h10 = p10.f23083a;
        if (h10 == null) {
            h10 = p11.f23083a;
        }
        H h11 = h10;
        N n10 = p10.f23084b;
        if (n10 == null) {
            n10 = p11.f23084b;
        }
        N n11 = n10;
        t tVar = p10.c;
        if (tVar == null) {
            tVar = p11.c;
        }
        return new G(new P(h11, n11, tVar, null, p10.d || p11.d, n7.z.u(p11.f23085e, p10.f23085e)));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof G) && A7.m.a(((G) obj).f23069a, this.f23069a);
    }

    public final int hashCode() {
        return this.f23069a.hashCode();
    }

    public final String toString() {
        if (equals(f23068b)) {
            return "ExitTransition.None";
        }
        if (equals(c)) {
            return "ExitTransition.KeepUntilTransitionsFinished";
        }
        StringBuilder sb = new StringBuilder("ExitTransition: \nFade - ");
        P p10 = this.f23069a;
        H h10 = p10.f23083a;
        sb.append(h10 != null ? h10.toString() : null);
        sb.append(",\nSlide - ");
        N n10 = p10.f23084b;
        sb.append(n10 != null ? n10.toString() : null);
        sb.append(",\nShrink - ");
        t tVar = p10.c;
        sb.append(tVar != null ? tVar.toString() : null);
        sb.append(",\nScale - ");
        sb.append((String) null);
        sb.append(",\nKeepUntilTransitionsFinished - ");
        sb.append(p10.d);
        return sb.toString();
    }
}
