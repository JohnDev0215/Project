package A;

import x.InterfaceC2647m;

/* JADX INFO: renamed from: A.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0012f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0010e f168a = C0010e.f162a;

    default float a(float f10, float f11, float f12) {
        f168a.getClass();
        float f13 = f11 + f10;
        if ((f10 >= 0.0f && f13 <= f12) || (f10 < 0.0f && f13 > f12)) {
            return 0.0f;
        }
        float f14 = f13 - f12;
        return Math.abs(f10) < Math.abs(f14) ? f10 : f14;
    }

    default InterfaceC2647m b() {
        f168a.getClass();
        return C0010e.f163b;
    }
}
