package q1;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Jobs.java */
/* JADX INFO: loaded from: classes.dex */
final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<n1.f, l<?>> f9105a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<n1.f, l<?>> f9106b = new HashMap();

    s() {
    }

    private Map<n1.f, l<?>> b(boolean z6) {
        return z6 ? this.f9106b : this.f9105a;
    }

    l<?> a(n1.f fVar, boolean z6) {
        return b(z6).get(fVar);
    }

    void c(n1.f fVar, l<?> lVar) {
        b(lVar.p()).put(fVar, lVar);
    }

    void d(n1.f fVar, l<?> lVar) {
        Map<n1.f, l<?>> mapB = b(lVar.p());
        if (lVar.equals(mapB.get(fVar))) {
            mapB.remove(fVar);
        }
    }
}
