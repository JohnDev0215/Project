package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j extends androidx.viewpager.widget.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f1410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f1411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private k f1412e;
    private Fragment f;

    @Deprecated
    public j(g gVar) {
        this(gVar, 0);
    }

    private static String x(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    @Override // androidx.viewpager.widget.a
    public void b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f1412e == null) {
            this.f1412e = this.f1410c.a();
        }
        this.f1412e.h(fragment);
        if (fragment == this.f) {
            this.f = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void d(ViewGroup viewGroup) {
        k kVar = this.f1412e;
        if (kVar != null) {
            kVar.g();
            this.f1412e = null;
        }
    }

    @Override // androidx.viewpager.widget.a
    public Object j(ViewGroup viewGroup, int i) {
        if (this.f1412e == null) {
            this.f1412e = this.f1410c.a();
        }
        long jW = w(i);
        Fragment fragmentD = this.f1410c.d(x(viewGroup.getId(), jW));
        if (fragmentD != null) {
            this.f1412e.d(fragmentD);
        } else {
            fragmentD = v(i);
            this.f1412e.b(viewGroup.getId(), fragmentD, x(viewGroup.getId(), jW));
        }
        if (fragmentD != this.f) {
            fragmentD.setMenuVisibility(false);
            if (this.f1411d == 1) {
                this.f1412e.l(fragmentD, Lifecycle.State.STARTED);
            } else {
                fragmentD.setUserVisibleHint(false);
            }
        }
        return fragmentD;
    }

    @Override // androidx.viewpager.widget.a
    public boolean k(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // androidx.viewpager.widget.a
    public void n(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.a
    public Parcelable o() {
        return null;
    }

    @Override // androidx.viewpager.widget.a
    public void q(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.f1411d == 1) {
                    if (this.f1412e == null) {
                        this.f1412e = this.f1410c.a();
                    }
                    this.f1412e.l(this.f, Lifecycle.State.STARTED);
                } else {
                    this.f.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.f1411d == 1) {
                if (this.f1412e == null) {
                    this.f1412e = this.f1410c.a();
                }
                this.f1412e.l(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.f = fragment;
        }
    }

    @Override // androidx.viewpager.widget.a
    public void t(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public abstract Fragment v(int i);

    public long w(int i) {
        return i;
    }

    public j(g gVar, int i) {
        this.f1412e = null;
        this.f = null;
        this.f1410c = gVar;
        this.f1411d = i;
    }
}
