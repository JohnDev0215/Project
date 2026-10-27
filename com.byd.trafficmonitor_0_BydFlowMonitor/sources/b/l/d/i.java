package b.l.d;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CopyOnWriteArrayList<a> f2820a = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final FragmentManager f2821b;

    /* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final FragmentManager.l f2822a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2823b;
    }

    public i(FragmentManager fragmentManager) {
        this.f2821b = fragmentManager;
    }

    public void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().a(fragment, bundle, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void b(Fragment fragment, boolean z) {
        this.f2821b.u0().f();
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().b(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().c(fragment, bundle, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void d(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().d(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void e(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().e(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void f(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().f(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void g(Fragment fragment, boolean z) {
        this.f2821b.u0().f();
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().g(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void h(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().h(fragment, bundle, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void i(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().i(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void j(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().j(fragment, bundle, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void k(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().k(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void l(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().l(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void m(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().m(fragment, view, bundle, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }

    public void n(Fragment fragment, boolean z) {
        Fragment fragmentX0 = this.f2821b.x0();
        if (fragmentX0 != null) {
            fragmentX0.getParentFragmentManager().w0().n(fragment, true);
        }
        for (a aVar : this.f2820a) {
            if (!z || aVar.f2823b) {
                FragmentManager.l lVar = aVar.f2822a;
                throw null;
            }
        }
    }
}
