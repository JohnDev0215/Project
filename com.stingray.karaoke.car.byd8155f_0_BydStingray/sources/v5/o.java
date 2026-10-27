package V5;

import T.C0735d;
import T.C0738e0;
import T.S;
import x.AbstractC2631e;
import x.C2629d;
import y.n0;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2629d f9530a = AbstractC2631e.a(0.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n0 f9531b = new n0();
    public final C0738e0 c;
    public final C0738e0 d;

    public o(boolean z6) {
        Boolean boolValueOf = Boolean.valueOf(z6);
        S s6 = S.f8224q;
        this.c = C0735d.O(boolValueOf, s6);
        this.d = C0735d.O(Boolean.FALSE, s6);
    }

    public final float a() {
        return ((Number) this.f9530a.d()).floatValue();
    }

    public final boolean b() {
        return ((Boolean) this.c.getValue()).booleanValue();
    }
}
