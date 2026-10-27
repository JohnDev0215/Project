package p;

import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class p50 implements wy1 {
    public final /* synthetic */ int a = 0;
    public final xy1 b;

    public p50(xy1 xy1Var) {
        this.b = xy1Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 0:
                return Optional.e(((wy1) xy1Var).get());
            default:
                return xy1Var.get();
        }
    }

    public p50(ct1 ct1Var) {
        this.b = ct1Var;
    }
}
