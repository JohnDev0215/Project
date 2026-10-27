package b.l.d;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentState;
import androidx.fragment.app.SpecialEffectsController;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentStateManager.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f2831a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f2832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Fragment f2833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2834d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2835e = -1;

    /* JADX INFO: compiled from: FragmentStateManager.java */
    public class a implements View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2836b;

        public a(View view) {
            this.f2836b = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.f2836b.removeOnAttachStateChangeListener(this);
            b.h.m.t.h0(this.f2836b);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: FragmentStateManager.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2838a;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f2838a = iArr;
            try {
                iArr[Lifecycle.State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2838a[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2838a[Lifecycle.State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2838a[Lifecycle.State.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public n(i iVar, o oVar, Fragment fragment) {
        this.f2831a = iVar;
        this.f2832b = oVar;
        this.f2833c = fragment;
    }

    public void a() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        i iVar = this.f2831a;
        Fragment fragment2 = this.f2833c;
        iVar.a(fragment2, fragment2.mSavedFragmentState, false);
    }

    public void b() {
        int iJ = this.f2832b.j(this.f2833c);
        Fragment fragment = this.f2833c;
        fragment.mContainer.addView(fragment.mView, iJ);
    }

    public void c() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        Fragment fragment2 = fragment.mTarget;
        n nVarM = null;
        if (fragment2 != null) {
            n nVarM2 = this.f2832b.m(fragment2.mWho);
            if (nVarM2 == null) {
                throw new IllegalStateException("Fragment " + this.f2833c + " declared target fragment " + this.f2833c.mTarget + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.f2833c;
            fragment3.mTargetWho = fragment3.mTarget.mWho;
            fragment3.mTarget = null;
            nVarM = nVarM2;
        } else {
            String str = fragment.mTargetWho;
            if (str != null && (nVarM = this.f2832b.m(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f2833c + " declared target fragment " + this.f2833c.mTargetWho + " that does not belong to this FragmentManager!");
            }
        }
        if (nVarM != null && (FragmentManager.f922b || nVarM.k().mState < 1)) {
            nVarM.m();
        }
        Fragment fragment4 = this.f2833c;
        fragment4.mHost = fragment4.mFragmentManager.u0();
        Fragment fragment5 = this.f2833c;
        fragment5.mParentFragment = fragment5.mFragmentManager.x0();
        this.f2831a.g(this.f2833c, false);
        this.f2833c.performAttach();
        this.f2831a.b(this.f2833c, false);
    }

    public int d() {
        Fragment fragment;
        ViewGroup viewGroup;
        Fragment fragment2 = this.f2833c;
        if (fragment2.mFragmentManager == null) {
            return fragment2.mState;
        }
        int iMin = this.f2835e;
        int i2 = b.f2838a[fragment2.mMaxState.ordinal()];
        if (i2 != 1) {
            iMin = i2 != 2 ? i2 != 3 ? i2 != 4 ? Math.min(iMin, -1) : Math.min(iMin, 0) : Math.min(iMin, 1) : Math.min(iMin, 5);
        }
        Fragment fragment3 = this.f2833c;
        if (fragment3.mFromLayout) {
            if (fragment3.mInLayout) {
                iMin = Math.max(this.f2835e, 2);
                View view = this.f2833c.mView;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.f2835e < 4 ? Math.min(iMin, fragment3.mState) : Math.min(iMin, 1);
            }
        }
        if (!this.f2833c.mAdded) {
            iMin = Math.min(iMin, 1);
        }
        SpecialEffectsController.Operation.LifecycleImpact lifecycleImpactL = null;
        if (FragmentManager.f922b && (viewGroup = (fragment = this.f2833c).mContainer) != null) {
            lifecycleImpactL = SpecialEffectsController.n(viewGroup, fragment.getParentFragmentManager()).l(this);
        }
        if (lifecycleImpactL == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            iMin = Math.min(iMin, 6);
        } else if (lifecycleImpactL == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            iMin = Math.max(iMin, 3);
        } else {
            Fragment fragment4 = this.f2833c;
            if (fragment4.mRemoving) {
                iMin = fragment4.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, -1);
            }
        }
        Fragment fragment5 = this.f2833c;
        if (fragment5.mDeferStart && fragment5.mState < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + iMin + " for " + this.f2833c);
        }
        return iMin;
    }

    public void e() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        if (fragment.mIsCreated) {
            fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
            this.f2833c.mState = 1;
            return;
        }
        this.f2831a.h(fragment, fragment.mSavedFragmentState, false);
        Fragment fragment2 = this.f2833c;
        fragment2.performCreate(fragment2.mSavedFragmentState);
        i iVar = this.f2831a;
        Fragment fragment3 = this.f2833c;
        iVar.c(fragment3, fragment3.mSavedFragmentState, false);
    }

    public void f() {
        String resourceName;
        if (this.f2833c.mFromLayout) {
            return;
        }
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        LayoutInflater layoutInflaterPerformGetLayoutInflater = fragment.performGetLayoutInflater(fragment.mSavedFragmentState);
        ViewGroup viewGroup = null;
        Fragment fragment2 = this.f2833c;
        ViewGroup viewGroup2 = fragment2.mContainer;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i2 = fragment2.mContainerId;
            if (i2 != 0) {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f2833c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.mFragmentManager.p0().c(this.f2833c.mContainerId);
                if (viewGroup == null) {
                    Fragment fragment3 = this.f2833c;
                    if (!fragment3.mRestored) {
                        try {
                            resourceName = fragment3.getResources().getResourceName(this.f2833c.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f2833c.mContainerId) + " (" + resourceName + ") for fragment " + this.f2833c);
                    }
                }
            }
        }
        Fragment fragment4 = this.f2833c;
        fragment4.mContainer = viewGroup;
        fragment4.performCreateView(layoutInflaterPerformGetLayoutInflater, viewGroup, fragment4.mSavedFragmentState);
        View view = this.f2833c.mView;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.f2833c;
            fragment5.mView.setTag(b.l.b.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                b();
            }
            Fragment fragment6 = this.f2833c;
            if (fragment6.mHidden) {
                fragment6.mView.setVisibility(8);
            }
            if (b.h.m.t.O(this.f2833c.mView)) {
                b.h.m.t.h0(this.f2833c.mView);
            } else {
                View view2 = this.f2833c.mView;
                view2.addOnAttachStateChangeListener(new a(view2));
            }
            this.f2833c.performViewCreated();
            i iVar = this.f2831a;
            Fragment fragment7 = this.f2833c;
            iVar.m(fragment7, fragment7.mView, fragment7.mSavedFragmentState, false);
            int visibility = this.f2833c.mView.getVisibility();
            float alpha = this.f2833c.mView.getAlpha();
            if (FragmentManager.f922b) {
                this.f2833c.setPostOnViewCreatedAlpha(alpha);
                Fragment fragment8 = this.f2833c;
                if (fragment8.mContainer != null && visibility == 0) {
                    View viewFindFocus = fragment8.mView.findFocus();
                    if (viewFindFocus != null) {
                        this.f2833c.setFocusedView(viewFindFocus);
                        if (FragmentManager.G0(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + this.f2833c);
                        }
                    }
                    this.f2833c.mView.setAlpha(0.0f);
                }
            } else {
                Fragment fragment9 = this.f2833c;
                if (visibility == 0 && fragment9.mContainer != null) {
                    z = true;
                }
                fragment9.mIsNewlyAdded = z;
            }
        }
        this.f2833c.mState = 2;
    }

    public void g() {
        Fragment fragmentF;
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        boolean zIsChangingConfigurations = true;
        boolean z = fragment.mRemoving && !fragment.isInBackStack();
        if (!(z || this.f2832b.o().p(this.f2833c))) {
            String str = this.f2833c.mTargetWho;
            if (str != null && (fragmentF = this.f2832b.f(str)) != null && fragmentF.mRetainInstance) {
                this.f2833c.mTarget = fragmentF;
            }
            this.f2833c.mState = 0;
            return;
        }
        g<?> gVar = this.f2833c.mHost;
        if (gVar instanceof b.n.t) {
            zIsChangingConfigurations = this.f2832b.o().m();
        } else if (gVar.f() instanceof Activity) {
            zIsChangingConfigurations = true ^ ((Activity) gVar.f()).isChangingConfigurations();
        }
        if (z || zIsChangingConfigurations) {
            this.f2832b.o().g(this.f2833c);
        }
        this.f2833c.performDestroy();
        this.f2831a.d(this.f2833c, false);
        for (n nVar : this.f2832b.k()) {
            if (nVar != null) {
                Fragment fragmentK = nVar.k();
                if (this.f2833c.mWho.equals(fragmentK.mTargetWho)) {
                    fragmentK.mTarget = this.f2833c;
                    fragmentK.mTargetWho = null;
                }
            }
        }
        Fragment fragment2 = this.f2833c;
        String str2 = fragment2.mTargetWho;
        if (str2 != null) {
            fragment2.mTarget = this.f2832b.f(str2);
        }
        this.f2832b.q(this);
    }

    public void h() {
        View view;
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.f2833c);
        }
        Fragment fragment = this.f2833c;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && (view = fragment.mView) != null) {
            viewGroup.removeView(view);
        }
        this.f2833c.performDestroyView();
        this.f2831a.n(this.f2833c, false);
        Fragment fragment2 = this.f2833c;
        fragment2.mContainer = null;
        fragment2.mView = null;
        fragment2.mViewLifecycleOwner = null;
        fragment2.mViewLifecycleOwnerLiveData.j(null);
        this.f2833c.mInLayout = false;
    }

    public void i() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f2833c);
        }
        this.f2833c.performDetach();
        boolean z = false;
        this.f2831a.e(this.f2833c, false);
        Fragment fragment = this.f2833c;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !fragment.isInBackStack()) {
            z = true;
        }
        if (z || this.f2832b.o().p(this.f2833c)) {
            if (FragmentManager.G0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f2833c);
            }
            this.f2833c.initState();
        }
    }

    public void j() {
        Fragment fragment = this.f2833c;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (FragmentManager.G0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f2833c);
            }
            Fragment fragment2 = this.f2833c;
            fragment2.performCreateView(fragment2.performGetLayoutInflater(fragment2.mSavedFragmentState), null, this.f2833c.mSavedFragmentState);
            View view = this.f2833c.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f2833c;
                fragment3.mView.setTag(b.l.b.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f2833c;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                this.f2833c.performViewCreated();
                i iVar = this.f2831a;
                Fragment fragment5 = this.f2833c;
                iVar.m(fragment5, fragment5.mView, fragment5.mSavedFragmentState, false);
                this.f2833c.mState = 2;
            }
        }
    }

    public Fragment k() {
        return this.f2833c;
    }

    public final boolean l(View view) {
        if (view == this.f2833c.mView) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.f2833c.mView) {
                return true;
            }
        }
        return false;
    }

    public void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.f2834d) {
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + k());
                return;
            }
            return;
        }
        try {
            this.f2834d = true;
            while (true) {
                int iD = d();
                Fragment fragment = this.f2833c;
                int i2 = fragment.mState;
                if (iD == i2) {
                    if (FragmentManager.f922b && fragment.mHiddenChanged) {
                        if (fragment.mView != null && (viewGroup = fragment.mContainer) != null) {
                            SpecialEffectsController specialEffectsControllerN = SpecialEffectsController.n(viewGroup, fragment.getParentFragmentManager());
                            if (this.f2833c.mHidden) {
                                specialEffectsControllerN.c(this);
                            } else {
                                specialEffectsControllerN.e(this);
                            }
                        }
                        Fragment fragment2 = this.f2833c;
                        FragmentManager fragmentManager = fragment2.mFragmentManager;
                        if (fragmentManager != null) {
                            fragmentManager.E0(fragment2);
                        }
                        Fragment fragment3 = this.f2833c;
                        fragment3.mHiddenChanged = false;
                        fragment3.onHiddenChanged(fragment3.mHidden);
                    }
                    return;
                }
                if (iD <= i2) {
                    switch (i2 - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            this.f2833c.mState = 1;
                            break;
                        case 2:
                            fragment.mInLayout = false;
                            fragment.mState = 2;
                            break;
                        case 3:
                            if (FragmentManager.G0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f2833c);
                            }
                            Fragment fragment4 = this.f2833c;
                            if (fragment4.mView != null && fragment4.mSavedViewState == null) {
                                s();
                            }
                            Fragment fragment5 = this.f2833c;
                            if (fragment5.mView != null && (viewGroup3 = fragment5.mContainer) != null) {
                                SpecialEffectsController.n(viewGroup3, fragment5.getParentFragmentManager()).d(this);
                            }
                            this.f2833c.mState = 3;
                            break;
                        case 4:
                            v();
                            break;
                        case 5:
                            fragment.mState = 5;
                            break;
                        case 6:
                            n();
                            break;
                    }
                } else {
                    switch (i2 + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (fragment.mView != null && (viewGroup2 = fragment.mContainer) != null) {
                                SpecialEffectsController.n(viewGroup2, fragment.getParentFragmentManager()).b(SpecialEffectsController.Operation.State.from(this.f2833c.mView.getVisibility()), this);
                            }
                            this.f2833c.mState = 4;
                            break;
                        case 5:
                            u();
                            break;
                        case 6:
                            fragment.mState = 6;
                            break;
                        case 7:
                            p();
                            break;
                    }
                }
            }
        } finally {
            this.f2834d = false;
        }
    }

    public void n() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f2833c);
        }
        this.f2833c.performPause();
        this.f2831a.f(this.f2833c, false);
    }

    public void o(ClassLoader classLoader) {
        Bundle bundle = this.f2833c.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f2833c;
        fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f2833c;
        fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle("android:view_registry_state");
        Fragment fragment3 = this.f2833c;
        fragment3.mTargetWho = fragment3.mSavedFragmentState.getString("android:target_state");
        Fragment fragment4 = this.f2833c;
        if (fragment4.mTargetWho != null) {
            fragment4.mTargetRequestCode = fragment4.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.f2833c;
        Boolean bool = fragment5.mSavedUserVisibleHint;
        if (bool != null) {
            fragment5.mUserVisibleHint = bool.booleanValue();
            this.f2833c.mSavedUserVisibleHint = null;
        } else {
            fragment5.mUserVisibleHint = fragment5.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.f2833c;
        if (fragment6.mUserVisibleHint) {
            return;
        }
        fragment6.mDeferStart = true;
    }

    public void p() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f2833c);
        }
        View focusedView = this.f2833c.getFocusedView();
        if (focusedView != null && l(focusedView)) {
            boolean zRequestFocus = focusedView.requestFocus();
            if (FragmentManager.G0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(focusedView);
                sb.append(" ");
                sb.append(zRequestFocus ? "succeeded" : "failed");
                sb.append(" on Fragment ");
                sb.append(this.f2833c);
                sb.append(" resulting in focused view ");
                sb.append(this.f2833c.mView.findFocus());
                Log.v("FragmentManager", sb.toString());
            }
        }
        this.f2833c.setFocusedView(null);
        this.f2833c.performResume();
        this.f2831a.i(this.f2833c, false);
        Fragment fragment = this.f2833c;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
    }

    public final Bundle q() {
        Bundle bundle = new Bundle();
        this.f2833c.performSaveInstanceState(bundle);
        this.f2831a.j(this.f2833c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f2833c.mView != null) {
            s();
        }
        if (this.f2833c.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f2833c.mSavedViewState);
        }
        if (this.f2833c.mSavedViewRegistryState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.f2833c.mSavedViewRegistryState);
        }
        if (!this.f2833c.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f2833c.mUserVisibleHint);
        }
        return bundle;
    }

    public FragmentState r() {
        FragmentState fragmentState = new FragmentState(this.f2833c);
        Fragment fragment = this.f2833c;
        if (fragment.mState <= -1 || fragmentState.n != null) {
            fragmentState.n = fragment.mSavedFragmentState;
        } else {
            Bundle bundleQ = q();
            fragmentState.n = bundleQ;
            if (this.f2833c.mTargetWho != null) {
                if (bundleQ == null) {
                    fragmentState.n = new Bundle();
                }
                fragmentState.n.putString("android:target_state", this.f2833c.mTargetWho);
                int i2 = this.f2833c.mTargetRequestCode;
                if (i2 != 0) {
                    fragmentState.n.putInt("android:target_req_state", i2);
                }
            }
        }
        return fragmentState;
    }

    public void s() {
        if (this.f2833c.mView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f2833c.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f2833c.mSavedViewState = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f2833c.mViewLifecycleOwner.e(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f2833c.mSavedViewRegistryState = bundle;
    }

    public void t(int i2) {
        this.f2835e = i2;
    }

    public void u() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f2833c);
        }
        this.f2833c.performStart();
        this.f2831a.k(this.f2833c, false);
    }

    public void v() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f2833c);
        }
        this.f2833c.performStop();
        this.f2831a.l(this.f2833c, false);
    }

    public n(i iVar, o oVar, ClassLoader classLoader, f fVar, FragmentState fragmentState) {
        this.f2831a = iVar;
        this.f2832b = oVar;
        Fragment fragmentA = fVar.a(classLoader, fragmentState.f965b);
        this.f2833c = fragmentA;
        Bundle bundle = fragmentState.k;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        fragmentA.setArguments(fragmentState.k);
        fragmentA.mWho = fragmentState.f966c;
        fragmentA.mFromLayout = fragmentState.f967d;
        fragmentA.mRestored = true;
        fragmentA.mFragmentId = fragmentState.f968e;
        fragmentA.mContainerId = fragmentState.f969f;
        fragmentA.mTag = fragmentState.f970g;
        fragmentA.mRetainInstance = fragmentState.f971h;
        fragmentA.mRemoving = fragmentState.f972i;
        fragmentA.mDetached = fragmentState.j;
        fragmentA.mHidden = fragmentState.l;
        fragmentA.mMaxState = Lifecycle.State.values()[fragmentState.m];
        Bundle bundle2 = fragmentState.n;
        if (bundle2 != null) {
            fragmentA.mSavedFragmentState = bundle2;
        } else {
            fragmentA.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + fragmentA);
        }
    }

    public n(i iVar, o oVar, Fragment fragment, FragmentState fragmentState) {
        this.f2831a = iVar;
        this.f2832b = oVar;
        this.f2833c = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        Bundle bundle = fragmentState.n;
        if (bundle != null) {
            fragment.mSavedFragmentState = bundle;
        } else {
            fragment.mSavedFragmentState = new Bundle();
        }
    }
}
