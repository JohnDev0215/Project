package U;

import E0.Y;
import T.B0;
import T.C0735d;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class u extends C {
    public static final u c;

    static {
        int i7 = 0;
        c = new u(i7, i7, 3);
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        if (b02.f8167n != 0) {
            C0735d.y("Cannot reset when inserting");
            throw null;
        }
        b02.z();
        b02.f8172t = 0;
        b02.f8173u = b02.m() - b02.f8162h;
        b02.f8163i = 0;
        b02.f8164j = 0;
        b02.o = 0;
    }
}
