package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CopyOnWriteArrayList<a> f2343a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final FragmentManager f2344b;

    /* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final FragmentManager.l f2345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2346b;

        a(FragmentManager.l lVar, boolean z6) {
            this.f2345a = lVar;
            this.f2346b = z6;
        }
    }

    k(FragmentManager fragmentManager) {
        this.f2344b = fragmentManager;
    }

    void a(Fragment fragment, Bundle bundle, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().a(fragment, bundle, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.a(this.f2344b, fragment, bundle);
            }
        }
    }

    void b(Fragment fragment, boolean z6) {
        Context contextJ = this.f2344b.t0().j();
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().b(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.b(this.f2344b, fragment, contextJ);
            }
        }
    }

    void c(Fragment fragment, Bundle bundle, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().c(fragment, bundle, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.c(this.f2344b, fragment, bundle);
            }
        }
    }

    void d(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().d(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.d(this.f2344b, fragment);
            }
        }
    }

    void e(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().e(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.e(this.f2344b, fragment);
            }
        }
    }

    void f(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().f(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.f(this.f2344b, fragment);
            }
        }
    }

    void g(Fragment fragment, boolean z6) {
        Context contextJ = this.f2344b.t0().j();
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().g(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.g(this.f2344b, fragment, contextJ);
            }
        }
    }

    void h(Fragment fragment, Bundle bundle, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().h(fragment, bundle, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.h(this.f2344b, fragment, bundle);
            }
        }
    }

    void i(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().i(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.i(this.f2344b, fragment);
            }
        }
    }

    void j(Fragment fragment, Bundle bundle, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().j(fragment, bundle, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.j(this.f2344b, fragment, bundle);
            }
        }
    }

    void k(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().k(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.k(this.f2344b, fragment);
            }
        }
    }

    void l(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().l(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.l(this.f2344b, fragment);
            }
        }
    }

    void m(Fragment fragment, View view, Bundle bundle, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().m(fragment, view, bundle, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.m(this.f2344b, fragment, view, bundle);
            }
        }
    }

    void n(Fragment fragment, boolean z6) {
        Fragment fragmentW0 = this.f2344b.w0();
        if (fragmentW0 != null) {
            fragmentW0.H().v0().n(fragment, true);
        }
        for (a aVar : this.f2343a) {
            if (!z6 || aVar.f2346b) {
                aVar.f2345a.n(this.f2344b, fragment);
            }
        }
    }

    public void o(FragmentManager.l lVar, boolean z6) {
        this.f2343a.add(new a(lVar, z6));
    }

    public void p(FragmentManager.l lVar) {
        synchronized (this.f2343a) {
            int i7 = 0;
            int size = this.f2343a.size();
            while (true) {
                if (i7 >= size) {
                    break;
                }
                if (this.f2343a.get(i7).f2345a == lVar) {
                    this.f2343a.remove(i7);
                    break;
                }
                i7++;
            }
        }
    }
}
