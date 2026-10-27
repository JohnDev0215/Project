package androidx.fragment.app;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import defpackage.rp0;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public final CopyOnWriteArrayList<a> a = new CopyOnWriteArrayList<>();
    public final FragmentManager b;

    /* JADX INFO: compiled from: FragmentLifecycleCallbacksDispatcher.java */
    public static final class a {
        public final FragmentManager.k a;
        public final boolean b = false;

        public a(rp0 rp0Var) {
            this.a = rp0Var;
        }
    }

    public g(FragmentManager fragmentManager) {
        this.b = fragmentManager;
    }

    public final void a(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.a(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void b(boolean z) {
        FragmentManager fragmentManager = this.b;
        Context context = fragmentManager.u.l;
        Fragment fragment = fragmentManager.w;
        if (fragment != null) {
            fragment.M().m.b(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void c(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.c(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void d(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.d(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void e(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.e(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void f(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.f(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void g(boolean z) {
        FragmentManager fragmentManager = this.b;
        Context context = fragmentManager.u.l;
        Fragment fragment = fragmentManager.w;
        if (fragment != null) {
            fragment.M().m.g(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void h(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.h(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void i(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.i(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void j(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.j(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void k(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.k(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void l(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.l(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }

    public final void m(Fragment fragment, View view, boolean z) {
        Fragment fragment2 = this.b.w;
        if (fragment2 != null) {
            fragment2.M().m.m(fragment, view, true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                FragmentManager.k kVar = aVar.a;
                FragmentManager fragmentManager = this.b;
                rp0 rp0Var = (rp0) kVar;
                if (fragment == rp0Var.a) {
                    g gVar = fragmentManager.m;
                    synchronized (gVar.a) {
                        int size = gVar.a.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            }
                            if (gVar.a.get(i).a == rp0Var) {
                                gVar.a.remove(i);
                                break;
                            }
                            i++;
                        }
                    }
                    FragmentStateAdapter fragmentStateAdapter = rp0Var.c;
                    FrameLayout frameLayout = rp0Var.b;
                    fragmentStateAdapter.getClass();
                    FragmentStateAdapter.D(view, frameLayout);
                } else {
                    continue;
                }
            }
        }
    }

    public final void n(boolean z) {
        Fragment fragment = this.b.w;
        if (fragment != null) {
            fragment.M().m.n(true);
        }
        for (a aVar : this.a) {
            if (!z || aVar.b) {
                aVar.a.getClass();
            }
        }
    }
}
