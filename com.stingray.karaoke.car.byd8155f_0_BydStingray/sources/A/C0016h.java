package A;

import x.AbstractC2631e;
import x.C2656w;
import x.InterfaceC2647m;

/* JADX INFO: renamed from: A.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0016h implements InterfaceC0012f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x.C0 f180b = AbstractC2631e.q(125, 0, new C2656w(0.25f, 0.1f, 0.25f, 1.0f), 2);

    @Override // A.InterfaceC0012f
    public final float a(float f10, float f11, float f12) {
        float fAbs = Math.abs((f11 + f10) - f10);
        float f13 = (0.3f * f12) - (0.0f * fAbs);
        float f14 = f12 - f13;
        if ((fAbs <= f12) && f14 < fAbs) {
            f13 = f12 - fAbs;
        }
        return f10 - f13;
    }

    @Override // A.InterfaceC0012f
    public final InterfaceC2647m b() {
        return this.f180b;
    }
}
