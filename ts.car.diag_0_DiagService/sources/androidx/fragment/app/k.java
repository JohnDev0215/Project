package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final CopyOnWriteArrayList<a> f697a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NonNull
    private final FragmentManager f698b;

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        final FragmentManager.l f699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f700b;
    }

    k(@NonNull FragmentManager fragmentManager) {
        this.f698b = fragmentManager;
    }

    void a(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().a(fragment, bundle, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.a(this.f698b, fragment, bundle);
            }
        }
    }

    void b(@NonNull Fragment fragment, boolean z) {
        Context contextK = this.f698b.s0().k();
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().b(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.b(this.f698b, fragment, contextK);
            }
        }
    }

    void c(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().c(fragment, bundle, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.c(this.f698b, fragment, bundle);
            }
        }
    }

    void d(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().d(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.d(this.f698b, fragment);
            }
        }
    }

    void e(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().e(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.e(this.f698b, fragment);
            }
        }
    }

    void f(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().f(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.f(this.f698b, fragment);
            }
        }
    }

    void g(@NonNull Fragment fragment, boolean z) {
        Context contextK = this.f698b.s0().k();
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().g(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.g(this.f698b, fragment, contextK);
            }
        }
    }

    void h(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().h(fragment, bundle, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.h(this.f698b, fragment, bundle);
            }
        }
    }

    void i(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().i(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.i(this.f698b, fragment);
            }
        }
    }

    void j(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().j(fragment, bundle, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.j(this.f698b, fragment, bundle);
            }
        }
    }

    void k(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().k(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.k(this.f698b, fragment);
            }
        }
    }

    void l(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().l(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.l(this.f698b, fragment);
            }
        }
    }

    void m(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().m(fragment, view, bundle, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.m(this.f698b, fragment, view, bundle);
            }
        }
    }

    void n(@NonNull Fragment fragment, boolean z) {
        Fragment fragmentV0 = this.f698b.v0();
        if (fragmentV0 != null) {
            fragmentV0.B().u0().n(fragment, true);
        }
        for (a aVar : this.f697a) {
            if (!z || aVar.f700b) {
                aVar.f699a.n(this.f698b, fragment);
            }
        }
    }
}
