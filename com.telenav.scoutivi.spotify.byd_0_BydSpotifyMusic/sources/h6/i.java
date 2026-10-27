package h6;

import c6.d0;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RouteDatabase.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<d0> f6743a = new LinkedHashSet();

    public final synchronized void a(d0 route) {
        l.e(route, "route");
        this.f6743a.remove(route);
    }

    public final synchronized void b(d0 failedRoute) {
        l.e(failedRoute, "failedRoute");
        this.f6743a.add(failedRoute);
    }

    public final synchronized boolean c(d0 route) {
        l.e(route, "route");
        return this.f6743a.contains(route);
    }
}
