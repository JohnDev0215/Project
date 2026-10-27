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
import androidx.navigation.m;
import androidx.navigation.q;
import androidx.navigation.r;
import androidx.navigation.u;

/* JADX INFO: loaded from: classes.dex */
public class NavHostFragment extends Fragment {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private m f2667g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private Boolean f2668h0 = null;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private View f2669i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private int f2670j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private boolean f2671k0;

    public static NavController O1(Fragment fragment) {
        for (Fragment fragmentG = fragment; fragmentG != null; fragmentG = fragmentG.G()) {
            if (fragmentG instanceof NavHostFragment) {
                return ((NavHostFragment) fragmentG).Q1();
            }
            Fragment fragmentX0 = fragmentG.H().x0();
            if (fragmentX0 instanceof NavHostFragment) {
                return ((NavHostFragment) fragmentX0).Q1();
            }
        }
        View viewW = fragment.W();
        if (viewW != null) {
            return q.a(viewW);
        }
        Dialog dialogS1 = fragment instanceof androidx.fragment.app.c ? ((androidx.fragment.app.c) fragment).S1() : null;
        if (dialogS1 != null && dialogS1.getWindow() != null) {
            return q.a(dialogS1.getWindow().getDecorView());
        }
        throw new IllegalStateException("Fragment " + fragment + " does not have a NavController set");
    }

    private int P1() {
        int iC = C();
        return (iC == 0 || iC == -1) ? b.nav_host_fragment_container : iC;
    }

    @Override // androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        View view = this.f2669i0;
        if (view != null && q.a(view) == this.f2667g0) {
            q.d(this.f2669i0, null);
        }
        this.f2669i0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.G0(context, attributeSet, bundle);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, u.NavHost);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(u.NavHost_navGraph, 0);
        if (resourceId != 0) {
            this.f2670j0 = resourceId;
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, c.NavHostFragment);
        if (typedArrayObtainStyledAttributes2.getBoolean(c.NavHostFragment_defaultNavHost, false)) {
            this.f2671k0 = true;
        }
        typedArrayObtainStyledAttributes2.recycle();
    }

    @Override // androidx.fragment.app.Fragment
    public void N0(boolean z6) {
        m mVar = this.f2667g0;
        if (mVar != null) {
            mVar.b(z6);
        } else {
            this.f2668h0 = Boolean.valueOf(z6);
        }
    }

    @Deprecated
    protected r<? extends a.C0025a> N1() {
        return new a(u1(), r(), P1());
    }

    @Override // androidx.fragment.app.Fragment
    public void Q0(Bundle bundle) {
        super.Q0(bundle);
        Bundle bundleY = this.f2667g0.y();
        if (bundleY != null) {
            bundle.putBundle("android-support-nav:fragment:navControllerState", bundleY);
        }
        if (this.f2671k0) {
            bundle.putBoolean("android-support-nav:fragment:defaultHost", true);
        }
        int i7 = this.f2670j0;
        if (i7 != 0) {
            bundle.putInt("android-support-nav:fragment:graphId", i7);
        }
    }

    public final NavController Q1() {
        m mVar = this.f2667g0;
        if (mVar != null) {
            return mVar;
        }
        throw new IllegalStateException("NavController is not available before onCreate()");
    }

    protected void R1(NavController navController) {
        navController.k().a(new DialogFragmentNavigator(u1(), r()));
        navController.k().a(N1());
    }

    @Override // androidx.fragment.app.Fragment
    public void T0(View view, Bundle bundle) {
        super.T0(view, bundle);
        if (!(view instanceof ViewGroup)) {
            throw new IllegalStateException("created host view " + view + " is not a ViewGroup");
        }
        q.d(view, this.f2667g0);
        if (view.getParent() != null) {
            View view2 = (View) view.getParent();
            this.f2669i0 = view2;
            if (view2.getId() == C()) {
                q.d(this.f2669i0, this.f2667g0);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        if (this.f2671k0) {
            H().l().u(this).i();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void s0(Fragment fragment) {
        super.s0(fragment);
        ((DialogFragmentNavigator) this.f2667g0.k().d(DialogFragmentNavigator.class)).h(fragment);
    }

    @Override // androidx.fragment.app.Fragment
    public void u0(Bundle bundle) {
        Bundle bundle2;
        m mVar = new m(u1());
        this.f2667g0 = mVar;
        mVar.C(this);
        this.f2667g0.D(t1().c());
        m mVar2 = this.f2667g0;
        Boolean bool = this.f2668h0;
        mVar2.b(bool != null && bool.booleanValue());
        this.f2668h0 = null;
        this.f2667g0.E(k());
        R1(this.f2667g0);
        if (bundle != null) {
            bundle2 = bundle.getBundle("android-support-nav:fragment:navControllerState");
            if (bundle.getBoolean("android-support-nav:fragment:defaultHost", false)) {
                this.f2671k0 = true;
                H().l().u(this).i();
            }
            this.f2670j0 = bundle.getInt("android-support-nav:fragment:graphId");
        } else {
            bundle2 = null;
        }
        if (bundle2 != null) {
            this.f2667g0.x(bundle2);
        }
        int i7 = this.f2670j0;
        if (i7 != 0) {
            this.f2667g0.z(i7);
        } else {
            Bundle bundleQ = q();
            int i8 = bundleQ != null ? bundleQ.getInt("android-support-nav:fragment:graphId") : 0;
            Bundle bundle3 = bundleQ != null ? bundleQ.getBundle("android-support-nav:fragment:startDestinationArgs") : null;
            if (i8 != 0) {
                this.f2667g0.A(i8, bundle3);
            }
        }
        super.u0(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentContainerView fragmentContainerView = new FragmentContainerView(layoutInflater.getContext());
        fragmentContainerView.setId(P1());
        return fragmentContainerView;
    }
}
