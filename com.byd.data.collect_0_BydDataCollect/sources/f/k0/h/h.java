package f.k0.h;

import f.i0;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<i0> f5205a = new LinkedHashSet();

    h() {
    }

    public synchronized void a(i0 i0Var) {
        this.f5205a.remove(i0Var);
    }

    public synchronized void b(i0 i0Var) {
        this.f5205a.add(i0Var);
    }

    public synchronized boolean c(i0 i0Var) {
        return this.f5205a.contains(i0Var);
    }
}
