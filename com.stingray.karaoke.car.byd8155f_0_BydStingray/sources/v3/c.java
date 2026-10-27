package V3;

import R3.m;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c implements e {
    public final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f9452m;

    public c(b bVar, b bVar2) {
        this.l = bVar;
        this.f9452m = bVar2;
    }

    @Override // V3.e
    public final List F0() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    @Override // V3.e
    public final boolean I0() {
        return this.l.I0() && this.f9452m.I0();
    }

    @Override // V3.e
    public final R3.e t0() {
        return new m(this.l.t0(), this.f9452m.t0());
    }
}
