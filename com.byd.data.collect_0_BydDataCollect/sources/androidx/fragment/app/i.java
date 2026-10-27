package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.j;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArrayList<a> f1441a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f1442b;

    /* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final j.f f1443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1444b;
    }

    i(j jVar) {
        this.f1442b = jVar;
    }

    void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().a(fragment, bundle, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void b(Fragment fragment, Context context, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().b(fragment, context, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().c(fragment, bundle, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void d(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().d(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void e(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().e(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void f(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().f(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void g(Fragment fragment, Context context, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().g(fragment, context, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void h(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().h(fragment, bundle, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void i(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().i(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void j(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().j(fragment, bundle, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void k(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().k(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void l(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().l(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void m(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().m(fragment, view, bundle, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }

    void n(Fragment fragment, boolean z) {
        Fragment fragmentJ0 = this.f1442b.j0();
        if (fragmentJ0 != null) {
            fragmentJ0.A().i0().n(fragment, true);
        }
        for (a aVar : this.f1441a) {
            if (!z || aVar.f1444b) {
                j.f fVar = aVar.f1443a;
                throw null;
            }
        }
    }
}
