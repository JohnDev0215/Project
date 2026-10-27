package G;

import E0.InterfaceC0165o;
import java.util.ArrayList;
import l3.C1764u;
import p0.C2071b;
import q0.C2164c;

/* JADX INFO: loaded from: classes.dex */
public final class A extends g0.n implements InterfaceC0165o {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public androidx.compose.foundation.lazy.layout.a f2490y;

    @Override // g0.n
    public final void A0() {
        this.f2490y.f();
    }

    @Override // E0.InterfaceC0165o
    public final void a(E0.H h10) {
        ArrayList arrayList = this.f2490y.f11494i;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0314y c0314y = (C0314y) arrayList.get(i7);
            C2164c c2164c = c0314y.l;
            if (c2164c != null) {
                long j3 = c0314y.f2627k;
                long j10 = c2164c.f21134s;
                float f10 = ((int) (j3 >> 32)) - ((int) (j10 >> 32));
                float f11 = ((int) (j3 & 4294967295L)) - ((int) (4294967295L & j10));
                C2071b c2071b = h10.l;
                ((C1764u) c2071b.f20638m.f15499m).X(f10, f11);
                try {
                    h6.m.o(h10, c2164c);
                } finally {
                    ((C1764u) c2071b.f20638m.f15499m).X(-f10, -f11);
                }
            }
        }
        h10.a();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof A) && A7.m.a(this.f2490y, ((A) obj).f2490y);
    }

    public final int hashCode() {
        return this.f2490y.hashCode();
    }

    public final String toString() {
        return "DisplayingDisappearingItemsNode(animator=" + this.f2490y + ')';
    }

    @Override // g0.n
    public final void z0() {
        this.f2490y.f11495j = this;
    }
}
