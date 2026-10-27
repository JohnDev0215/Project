package F0;

import java.util.List;
import u.AbstractC2470j;
import u.C2476p;
import u.C2477q;

/* JADX INFO: loaded from: classes.dex */
public final class P0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.j f2104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2477q f2105b;

    public P0(L0.o oVar, C2476p c2476p) {
        this.f2104a = oVar.d;
        int[] iArr = AbstractC2470j.f22603a;
        this.f2105b = new C2477q();
        List listH = L0.o.h(oVar, true, 4);
        int size = listH.size();
        for (int i7 = 0; i7 < size; i7++) {
            L0.o oVar2 = (L0.o) listH.get(i7);
            if (c2476p.b(oVar2.f3920g)) {
                this.f2105b.a(oVar2.f3920g);
            }
        }
    }
}
