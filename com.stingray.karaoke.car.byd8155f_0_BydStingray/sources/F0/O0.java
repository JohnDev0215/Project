package F0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class O0 implements E0.j0 {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f2099m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Float f2100n = null;
    public Float o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public L0.h f2101p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public L0.h f2102q = null;

    public O0(int i7, ArrayList arrayList) {
        this.l = i7;
        this.f2099m = arrayList;
    }

    @Override // E0.j0
    public final boolean A() {
        return this.f2099m.contains(this);
    }
}
