package F8;

import java.util.List;

/* JADX INFO: renamed from: F8.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0287w implements R7.a, I8.c {
    public int l;

    public abstract K B0();

    public abstract boolean F0();

    public abstract AbstractC0287w I0(G8.f fVar);

    public abstract a0 K0();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractC0287w)) {
            return false;
        }
        AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
        if (F0() == abstractC0287w.F0()) {
            if (AbstractC0268c.t(G8.e.f2723m, K0(), abstractC0287w.K0())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode;
        int i7 = this.l;
        if (i7 != 0) {
            return i7;
        }
        if (AbstractC0268c.i(this)) {
            iHashCode = super.hashCode();
        } else {
            iHashCode = (F0() ? 1 : 0) + ((o0().hashCode() + (B0().hashCode() * 31)) * 31);
        }
        this.l = iHashCode;
        return iHashCode;
    }

    @Override // R7.a
    public final R7.h k() {
        return AbstractC0274i.a(t0());
    }

    public abstract List o0();

    public abstract H t0();

    public abstract y8.n z0();
}
