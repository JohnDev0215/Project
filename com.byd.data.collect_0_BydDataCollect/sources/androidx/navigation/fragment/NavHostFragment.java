package androidx.navigation.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.a;
import androidx.navigation.l;
import androidx.navigation.p;
import androidx.navigation.q;
import androidx.navigation.t;

/* JADX INFO: loaded from: classes.dex */
public class NavHostFragment extends Fragment {
    private l Y;
    private Boolean Z = null;
    private View a0;
    private int b0;
    private boolean c0;

    public static NavController q1(Fragment fragment) {
        for (Fragment fragmentZ = fragment; fragmentZ != null; fragmentZ = fragmentZ.z()) {
            if (fragmentZ instanceof NavHostFragment) {
                return ((NavHostFragment) fragmentZ).s1();
            }
            Fragment fragmentK0 = fragmentZ.A().k0();
            if (fragmentK0 instanceof NavHostFragment) {
                return ((NavHostFragment) fragmentK0).s1();
            }
        }
        View viewL = fragment.L();
        if (viewL != null) {
            return p.b(viewL);
        }
        Dialog dialogT1 = fragment instanceof androidx.fragment.app.b ? ((androidx.fragment.app.b) fragment).t1() : null;
        if (dialogT1 != null && dialogT1.getWindow() != null) {
            return p.b(dialogT1.getWindow().getDecorView());
        }
        throw new IllegalStateException("Fragment " + fragment + " does not have a NavController set");
    }

    private int r1() {
        int iV = v();
        return (iV == 0 || iV == -1) ? b.nav_host_fragment_container : iV;
    }

    @Override // androidx.fragment.app.Fragment
    public void C0(View view, Bundle bundle) {
        super.C0(view, bundle);
        if (!(view instanceof ViewGroup)) {
            throw new IllegalStateException("created host view " + view + " is not a ViewGroup");
        }
        p.e(view, this.Y);
        if (view.getParent() != null) {
            View view2 = (View) view.getParent();
            this.a0 = view2;
            if (view2.getId() == v()) {
                p.e(this.a0, this.Y);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Context context) {
        super.a0(context);
        if (this.c0) {
            A().i().t(this).i();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void b0(Fragment fragment) {
        super.b0(fragment);
        ((DialogFragmentNavigator) this.Y.i().d(DialogFragmentNavigator.class)).h(fragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void d0(Bundle bundle) {
        Bundle bundle2;
        l lVar = new l(c1());
        this.Y = lVar;
        lVar.y(this);
        this.Y.z(b1().d());
        l lVar2 = this.Y;
        Boolean bool = this.Z;
        lVar2.b(bool != null && bool.booleanValue());
        this.Z = null;
        this.Y.A(j());
        t1(this.Y);
        if (bundle != null) {
            bundle2 = bundle.getBundle("android-support-nav:fragment:navControllerState");
            if (bundle.getBoolean("android-support-nav:fragment:defaultHost", false)) {
                this.c0 = true;
                A().i().t(this).i();
            }
            this.b0 = bundle.getInt("android-support-nav:fragment:graphId");
        } else {
            bundle2 = null;
        }
        if (bundle2 != null) {
            this.Y.t(bundle2);
        }
        int i2 = this.b0;
        if (i2 != 0) {
            this.Y.v(i2);
        } else {
            Bundle bundleN = n();
            int i3 = bundleN != null ? bundleN.getInt("android-support-nav:fragment:graphId") : 0;
            Bundle bundle3 = bundleN != null ? bundleN.getBundle("android-support-nav:fragment:startDestinationArgs") : null;
            if (i3 != 0) {
                this.Y.w(i3, bundle3);
            }
        }
        super.d0(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentContainerView fragmentContainerView = new FragmentContainerView(layoutInflater.getContext());
        fragmentContainerView.setId(r1());
        return fragmentContainerView;
    }

    @Override // androidx.fragment.app.Fragment
    public void k0() {
        super.k0();
        View view = this.a0;
        if (view != null && p.b(view) == this.Y) {
            p.e(this.a0, null);
        }
        this.a0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void p0(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.p0(context, attributeSet, bundle);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.NavHost);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(t.NavHost_navGraph, 0);
        if (resourceId != 0) {
            this.b0 = resourceId;
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, c.NavHostFragment);
        if (typedArrayObtainStyledAttributes2.getBoolean(c.NavHostFragment_defaultNavHost, false)) {
            this.c0 = true;
        }
        typedArrayObtainStyledAttributes2.recycle();
    }

    @Deprecated
    protected q<? extends a.C0024a> p1() {
        return new a(c1(), o(), r1());
    }

    public final NavController s1() {
        l lVar = this.Y;
        if (lVar != null) {
            return lVar;
        }
        throw new IllegalStateException("NavController is not available before onCreate()");
    }

    protected void t1(NavController navController) {
        navController.i().a(new DialogFragmentNavigator(c1(), o()));
        navController.i().a(p1());
    }

    @Override // androidx.fragment.app.Fragment
    public void w0(boolean z) {
        l lVar = this.Y;
        if (lVar != null) {
            lVar.b(z);
        } else {
            this.Z = Boolean.valueOf(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void z0(Bundle bundle) {
        super.z0(bundle);
        Bundle bundleU = this.Y.u();
        if (bundleU != null) {
            bundle.putBundle("android-support-nav:fragment:navControllerState", bundleU);
        }
        if (this.c0) {
            bundle.putBoolean("android-support-nav:fragment:defaultHost", true);
        }
        int i2 = this.b0;
        if (i2 != 0) {
            bundle.putInt("android-support-nav:fragment:graphId", i2);
        }
    }
}
