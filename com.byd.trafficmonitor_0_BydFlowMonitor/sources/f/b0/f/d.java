package f.b0.f;

import f.a0;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<a0> f5053a = new LinkedHashSet();

    public synchronized void a(a0 a0Var) {
        this.f5053a.remove(a0Var);
    }

    public synchronized void b(a0 a0Var) {
        this.f5053a.add(a0Var);
    }

    public synchronized boolean c(a0 a0Var) {
        return this.f5053a.contains(a0Var);
    }
}
