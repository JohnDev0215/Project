package d5;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class L extends AbstractC1074C {
    @Override // d5.AbstractC1074C
    public final AbstractC1074C b(Object obj) {
        obj.getClass();
        a(obj);
        return this;
    }

    public final M h() {
        int i7 = this.f13378b;
        if (i7 == 0) {
            int i10 = M.f13389n;
            return j0.f13433u;
        }
        if (i7 != 1) {
            M mU = M.u(i7, this.f13377a);
            this.f13378b = mU.size();
            this.c = true;
            return mU;
        }
        Object obj = this.f13377a[0];
        Objects.requireNonNull(obj);
        int i11 = M.f13389n;
        return new q0(obj);
    }
}
