package e.d0.f;

import e.c0;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<c0> f3374a = new LinkedHashSet();

    public synchronized void a(c0 c0Var) {
        this.f3374a.remove(c0Var);
    }

    public synchronized void b(c0 c0Var) {
        this.f3374a.add(c0Var);
    }

    public synchronized boolean c(c0 c0Var) {
        return this.f3374a.contains(c0Var);
    }
}
