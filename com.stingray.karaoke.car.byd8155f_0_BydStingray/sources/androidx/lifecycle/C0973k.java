package androidx.lifecycle;

import io.sentry.C1489n1;
import io.sentry.android.navigation.SentryNavigationListener;
import java.util.HashMap;
import java.util.List;
import n3.C1930E;
import r.C2257p;

/* JADX INFO: renamed from: androidx.lifecycle.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0973k implements InterfaceC0986y {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f11995m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f11996n;

    public C0973k(DefaultLifecycleObserver defaultLifecycleObserver, InterfaceC0986y interfaceC0986y) {
        A7.m.f(defaultLifecycleObserver, "defaultLifecycleObserver");
        this.f11995m = defaultLifecycleObserver;
        this.f11996n = interfaceC0986y;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        switch (this.l) {
            case 0:
                int i7 = AbstractC0972j.f11994a[enumC0980s.ordinal()];
                DefaultLifecycleObserver defaultLifecycleObserver = (DefaultLifecycleObserver) this.f11995m;
                switch (i7) {
                    case 1:
                        defaultLifecycleObserver.onCreate(a10);
                        break;
                    case 2:
                        defaultLifecycleObserver.onStart(a10);
                        break;
                    case 3:
                        defaultLifecycleObserver.onResume(a10);
                        break;
                    case 4:
                        defaultLifecycleObserver.onPause(a10);
                        break;
                    case 5:
                        defaultLifecycleObserver.onStop(a10);
                        break;
                    case 6:
                        defaultLifecycleObserver.onDestroy(a10);
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                }
                InterfaceC0986y interfaceC0986y = (InterfaceC0986y) this.f11996n;
                if (interfaceC0986y != null) {
                    interfaceC0986y.b(a10, enumC0980s);
                    return;
                }
                return;
            case 1:
                if (enumC0980s == EnumC0980s.ON_START) {
                    ((A9.b) this.f11995m).V0(this);
                    ((C2257p) this.f11996n).e();
                    return;
                }
                return;
            case 2:
                HashMap map = ((C0967e) this.f11996n).f11980a;
                List list = (List) map.get(enumC0980s);
                InterfaceC0987z interfaceC0987z = (InterfaceC0987z) this.f11995m;
                C0967e.a(list, a10, enumC0980s, interfaceC0987z);
                C0967e.a((List) map.get(EnumC0980s.ON_ANY), a10, enumC0980s, interfaceC0987z);
                return;
            default:
                EnumC0980s enumC0980s2 = EnumC0980s.ON_RESUME;
                SentryNavigationListener sentryNavigationListener = (SentryNavigationListener) this.f11996n;
                C1930E c1930e = (C1930E) this.f11995m;
                if (enumC0980s == enumC0980s2) {
                    c1930e.b(sentryNavigationListener);
                    return;
                } else {
                    if (enumC0980s == EnumC0980s.ON_PAUSE) {
                        c1930e.f19812q.remove(sentryNavigationListener);
                        return;
                    }
                    return;
                }
        }
    }

    public C0973k(C1930E c1930e, SentryNavigationListener sentryNavigationListener) {
        this.f11995m = c1930e;
        this.f11996n = sentryNavigationListener;
        n7.z.c("ComposeNavigation");
        C1489n1.s().g("maven:io.sentry:sentry-compose");
    }

    public C0973k(InterfaceC0987z interfaceC0987z) {
        this.f11995m = interfaceC0987z;
        C0969g c0969g = C0969g.c;
        Class<?> cls = interfaceC0987z.getClass();
        C0967e c0967e = (C0967e) c0969g.f11987a.get(cls);
        this.f11996n = c0967e == null ? c0969g.a(cls, null) : c0967e;
    }

    public C0973k(A9.b bVar, C2257p c2257p) {
        this.f11995m = bVar;
        this.f11996n = c2257p;
    }
}
