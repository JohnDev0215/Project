package t8;

import F8.C0269d;
import F8.H;
import F8.K;
import Q7.InterfaceC0654g;
import e0.C1183w;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class m implements K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f22504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m7.n f22505b;

    public m(Set set) {
        H.f2437m.getClass();
        H h10 = H.f2438n;
        A7.m.f(h10, "attributes");
        C0269d.s(h10, this, n7.u.l, H8.i.a(2, true, "unknown integer literal type"), false);
        this.f22505b = h6.r.o(new C1183w(this, 11));
        this.f22504a = set;
    }

    @Override // F8.K
    public final N7.i l() {
        throw null;
    }

    @Override // F8.K
    public final boolean m() {
        return false;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        return null;
    }

    @Override // F8.K
    public final Collection o() {
        return (List) this.f22505b.getValue();
    }

    @Override // F8.K
    public final List p() {
        return n7.u.l;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IntegerLiteralType");
        sb.append("[" + AbstractC1998m.f0(this.f22504a, ",", null, null, l.f22503m, 30) + ']');
        return sb.toString();
    }
}
