package K6;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l extends S6.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f3715a;

    public l(boolean z6) {
        this.f3715a = z6;
    }

    @Override // S6.a
    public final void c(S6.c cVar) {
        if (this.f3715a) {
            k kVar = new k();
            HashMap map = cVar.f8070a;
            S6.h hVar = (S6.h) map.get(w9.m.class);
            if (hVar == null) {
                map.put(w9.m.class, kVar);
            } else if (hVar instanceof S6.d) {
                ((S6.d) hVar).f8071a.add(0, kVar);
            } else {
                map.put(w9.m.class, new S6.d(kVar, hVar));
            }
        }
    }
}
