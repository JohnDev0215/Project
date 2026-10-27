package w;

/* JADX INFO: loaded from: classes.dex */
public final class F {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final F f23066b = new F(new P(null, null, null, null, false, null, 63));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P f23067a;

    public F(P p10) {
        this.f23067a = p10;
    }

    public final F a(F f10) {
        h6.n nVar = null;
        P p10 = f10.f23067a;
        P p11 = this.f23067a;
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
        return new F(new P(h11, n11, tVar, nVar, false, n7.z.u(p11.f23085e, p10.f23085e), 16));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof F) && A7.m.a(((F) obj).f23067a, this.f23067a);
    }

    public final int hashCode() {
        return this.f23067a.hashCode();
    }

    public final String toString() {
        if (equals(f23066b)) {
            return "EnterTransition.None";
        }
        StringBuilder sb = new StringBuilder("EnterTransition: \nFade - ");
        P p10 = this.f23067a;
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
        return sb.toString();
    }
}
