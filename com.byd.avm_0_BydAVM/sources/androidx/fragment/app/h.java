package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.g;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
final class h extends androidx.fragment.app.g implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static boolean f1007d = false;
    static final Interpolator e = new DecelerateInterpolator(2.5f);
    static final Interpolator f = new DecelerateInterpolator(1.5f);
    boolean A;
    boolean B;
    boolean C;
    ArrayList<androidx.fragment.app.a> D;
    ArrayList<Boolean> E;
    ArrayList<Fragment> F;
    ArrayList<l> I;
    private androidx.fragment.app.i J;
    ArrayList<k> g;
    boolean h;
    ArrayList<androidx.fragment.app.a> l;
    ArrayList<Fragment> m;
    private OnBackPressedDispatcher n;
    ArrayList<androidx.fragment.app.a> p;
    ArrayList<Integer> q;
    ArrayList<g.b> r;
    androidx.fragment.app.f u;
    androidx.fragment.app.c v;
    Fragment w;
    Fragment x;
    boolean y;
    boolean z;
    int i = 0;
    final ArrayList<Fragment> j = new ArrayList<>();
    final HashMap<String, Fragment> k = new HashMap<>();
    private final androidx.activity.b o = new a(false);
    private final CopyOnWriteArrayList<i> s = new CopyOnWriteArrayList<>();
    int t = 0;
    Bundle G = null;
    SparseArray<Parcelable> H = null;
    Runnable K = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            h.this.A0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.k0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1010a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1011b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f1011b.getAnimatingAway() != null) {
                    c.this.f1011b.setAnimatingAway(null);
                    c cVar = c.this;
                    h hVar = h.this;
                    Fragment fragment = cVar.f1011b;
                    hVar.Q0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        c(ViewGroup viewGroup, Fragment fragment) {
            this.f1010a = viewGroup;
            this.f1011b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1010a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1015b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1016c;

        d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1014a = viewGroup;
            this.f1015b = view;
            this.f1016c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1014a.endViewTransition(this.f1015b);
            Animator animator2 = this.f1016c.getAnimator();
            this.f1016c.setAnimator(null);
            if (animator2 == null || this.f1014a.indexOfChild(this.f1015b) >= 0) {
                return;
            }
            h hVar = h.this;
            Fragment fragment = this.f1016c;
            hVar.Q0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1018a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1019b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1020c;

        e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1018a = viewGroup;
            this.f1019b = view;
            this.f1020c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1018a.endViewTransition(this.f1019b);
            animator.removeListener(this);
            Fragment fragment = this.f1020c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class f extends androidx.fragment.app.e {
        f() {
        }

        @Override // androidx.fragment.app.e
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.f fVar = h.this.u;
            return fVar.a(fVar.e(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g.a f1028a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1029b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1030a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    interface k {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class l implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f1031a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f1032b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1033c;

        l(androidx.fragment.app.a aVar, boolean z) {
            this.f1031a = z;
            this.f1032b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            int i = this.f1033c - 1;
            this.f1033c = i;
            if (i != 0) {
                return;
            }
            this.f1032b.s.g1();
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            this.f1033c++;
        }

        public void c() {
            androidx.fragment.app.a aVar = this.f1032b;
            aVar.s.u(aVar, this.f1031a, false, false);
        }

        public void d() {
            boolean z = this.f1033c > 0;
            h hVar = this.f1032b.s;
            int size = hVar.j.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = hVar.j.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.f1032b;
            aVar.s.u(aVar, this.f1031a, !z, true);
        }

        public boolean e() {
            return this.f1033c == 0;
        }
    }

    h() {
    }

    private boolean D0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.r();
    }

    static g J0(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(f);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    static g L0(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(e);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(f);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    private void M0(b.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentI = bVar.i(i2);
            if (!fragmentI.mAdded) {
                View viewRequireView = fragmentI.requireView();
                fragmentI.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    private boolean T0(String str, int i2, int i3) {
        k0();
        i0(true);
        Fragment fragment = this.x;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().g()) {
            return true;
        }
        boolean zU0 = U0(this.D, this.E, str, i2, i3);
        if (zU0) {
            this.h = true;
            try {
                Y0(this.D, this.E);
            } finally {
                t();
            }
        }
        o1();
        f0();
        q();
        return zU0;
    }

    private int V0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, b.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.s() && !aVar.q(arrayList, i5 + 1, i3)) {
                if (this.I == null) {
                    this.I = new ArrayList<>();
                }
                l lVar = new l(aVar, zBooleanValue);
                this.I.add(lVar);
                aVar.u(lVar);
                if (zBooleanValue) {
                    aVar.l();
                } else {
                    aVar.m(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                i(bVar);
            }
        }
        return i4;
    }

    private void W(Fragment fragment) {
        if (fragment == null || this.k.get(fragment.mWho) != fragment) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    private void Y0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        n0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).q) {
                if (i3 != i2) {
                    m0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).q) {
                        i3++;
                    }
                }
                m0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            m0(arrayList, arrayList2, i3, size);
        }
    }

    public static int c1(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private void d0(int i2) {
        try {
            this.h = true;
            O0(i2, false);
            this.h = false;
            k0();
        } catch (Throwable th) {
            this.h = false;
            throw th;
        }
    }

    private void g0() {
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway(null);
                    Q0(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    private void i(b.d.b<Fragment> bVar) {
        int i2 = this.t;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.j.get(i3);
            if (fragment.mState < iMin) {
                Q0(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    private void i0(boolean z) {
        if (this.h) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.u == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.u.f().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            s();
        }
        if (this.D == null) {
            this.D = new ArrayList<>();
            this.E = new ArrayList<>();
        }
        this.h = true;
        try {
            n0(null, null);
        } finally {
            this.h = false;
        }
    }

    private static void l0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.h(-1);
                aVar.m(i2 == i3 + (-1));
            } else {
                aVar.h(1);
                aVar.l();
            }
            i2++;
        }
    }

    private void m0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i2;
        boolean z = arrayList.get(i6).q;
        ArrayList<Fragment> arrayList3 = this.F;
        if (arrayList3 == null) {
            this.F = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.F.addAll(this.j);
        Fragment fragmentY0 = y0();
        boolean z2 = false;
        for (int i7 = i6; i7 < i3; i7++) {
            androidx.fragment.app.a aVar = arrayList.get(i7);
            fragmentY0 = !arrayList2.get(i7).booleanValue() ? aVar.n(this.F, fragmentY0) : aVar.v(this.F, fragmentY0);
            z2 = z2 || aVar.h;
        }
        this.F.clear();
        if (!z) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, i2, i3, false);
        }
        l0(arrayList, arrayList2, i2, i3);
        if (z) {
            b.d.b<Fragment> bVar = new b.d.b<>();
            i(bVar);
            int iV0 = V0(arrayList, arrayList2, i2, i3, bVar);
            M0(bVar);
            i4 = iV0;
        } else {
            i4 = i3;
        }
        if (i4 != i6 && z) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, i2, i4, true);
            O0(this.t, true);
        }
        while (i6 < i3) {
            androidx.fragment.app.a aVar2 = arrayList.get(i6);
            if (arrayList2.get(i6).booleanValue() && (i5 = aVar2.u) >= 0) {
                s0(i5);
                aVar2.u = -1;
            }
            aVar2.t();
            i6++;
        }
        if (z2) {
            a1();
        }
    }

    private void m1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
        androidx.fragment.app.f fVar = this.u;
        if (fVar != null) {
            try {
                fVar.h("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            b("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    private void n(Fragment fragment, g gVar, int i2) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i2);
        Animation animation = gVar.f1023a;
        if (animation != null) {
            RunnableC0019h runnableC0019h = new RunnableC0019h(animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            runnableC0019h.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(runnableC0019h);
            return;
        }
        Animator animator = gVar.f1024b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    private void n0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<l> arrayList3 = this.I;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            l lVar = this.I.get(i2);
            if (arrayList != null && !lVar.f1031a && (iIndexOf2 = arrayList.indexOf(lVar.f1032b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.I.remove(i2);
                i2--;
                size--;
                lVar.c();
            } else if (lVar.e() || (arrayList != null && lVar.f1032b.q(arrayList, 0, arrayList.size()))) {
                this.I.remove(i2);
                i2--;
                size--;
                if (arrayList == null || lVar.f1031a || (iIndexOf = arrayList.indexOf(lVar.f1032b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    lVar.d();
                } else {
                    lVar.c();
                }
            }
            i2++;
        }
    }

    public static int n1(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? 1 : 2;
        }
        if (i2 == 4099) {
            return z ? 5 : 6;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z ? 3 : 4;
    }

    private void o1() {
        ArrayList<k> arrayList = this.g;
        if (arrayList == null || arrayList.isEmpty()) {
            this.o.f(u0() > 0 && E0(this.w));
        } else {
            this.o.f(true);
        }
    }

    private void q() {
        this.k.values().removeAll(Collections.singleton(null));
    }

    private Fragment q0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.j.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.j.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    private void r0() {
        if (this.I != null) {
            while (!this.I.isEmpty()) {
                this.I.remove(0).d();
            }
        }
    }

    private void s() {
        if (G0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void t() {
        this.h = false;
        this.E.clear();
        this.D.clear();
    }

    private boolean t0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.g;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.g.size();
                boolean zA = false;
                for (int i2 = 0; i2 < size; i2++) {
                    zA |= this.g.get(i2).a(arrayList, arrayList2);
                }
                this.g.clear();
                this.u.f().removeCallbacks(this.K);
                return zA;
            }
            return false;
        }
    }

    public void A() {
        this.z = false;
        this.A = false;
        d0(1);
    }

    void A0() {
        k0();
        if (this.o.c()) {
            g();
        } else {
            this.n.c();
        }
    }

    public boolean B(Menu menu, MenuInflater menuInflater) {
        if (this.t < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.m != null) {
            for (int i3 = 0; i3 < this.m.size(); i3++) {
                Fragment fragment2 = this.m.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.m = arrayList;
        return z;
    }

    public void B0(Fragment fragment) {
        if (f1007d) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
    }

    public void C() {
        this.B = true;
        k0();
        d0(0);
        this.u = null;
        this.v = null;
        this.w = null;
        if (this.n != null) {
            this.o.d();
            this.n = null;
        }
    }

    public boolean C0() {
        return this.B;
    }

    public void D() {
        d0(1);
    }

    public void E() {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    boolean E0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        h hVar = fragment.mFragmentManager;
        return fragment == hVar.y0() && E0(hVar.w);
    }

    public void F(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    boolean F0(int i2) {
        return this.t >= i2;
    }

    void G(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).G(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    public boolean G0() {
        return this.z || this.A;
    }

    void H(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).H(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    g H0(Fragment fragment, int i2, boolean z, int i3) {
        int iN1;
        int nextAnim = fragment.getNextAnim();
        boolean z2 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i2, z, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new g(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i2, z, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new g(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.u.e().getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.u.e(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.u.e(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.u.e(), nextAnim);
                    if (animationLoadAnimation2 != null) {
                        return new g(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i2 == 0 || (iN1 = n1(i2, z)) < 0) {
            return null;
        }
        switch (iN1) {
            case 1:
                return L0(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return L0(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return L0(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return L0(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return J0(0.0f, 1.0f);
            case 6:
                return J0(1.0f, 0.0f);
            default:
                if (i3 == 0 && this.u.l()) {
                    i3 = this.u.k();
                }
                if (i3 == 0) {
                }
                return null;
        }
    }

    void I(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).I(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void I0(Fragment fragment) {
        if (this.k.get(fragment.mWho) != null) {
            return;
        }
        this.k.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                l(fragment);
            } else {
                Z0(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (f1007d) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    void J(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).J(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void K(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).K(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void K0(Fragment fragment) {
        if (this.k.get(fragment.mWho) == null) {
            return;
        }
        if (f1007d) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.k.put(fragment.mWho, null);
        Z0(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = this.k.get(str);
        }
        fragment.initState();
    }

    void L(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).L(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void M(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).M(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void N(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).N(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void N0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.k.containsKey(fragment.mWho)) {
            if (f1007d) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.t + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.t;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        Q0(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment fragmentQ0 = q0(fragment);
            if (fragmentQ0 != null) {
                View view = fragmentQ0.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                g gVarH0 = H0(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (gVarH0 != null) {
                    Animation animation = gVarH0.f1023a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        gVarH0.f1024b.setTarget(fragment.mView);
                        gVarH0.f1024b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            v(fragment);
        }
    }

    void O(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).O(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void O0(int i2, boolean z) {
        androidx.fragment.app.f fVar;
        if (this.u == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.t) {
            this.t = i2;
            int size = this.j.size();
            for (int i3 = 0; i3 < size; i3++) {
                N0(this.j.get(i3));
            }
            for (Fragment fragment : this.k.values()) {
                if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                    if (!fragment.mIsNewlyAdded) {
                        N0(fragment);
                    }
                }
            }
            l1();
            if (this.y && (fVar = this.u) != null && this.t == 4) {
                fVar.r();
                this.y = false;
            }
        }
    }

    void P(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).P(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    void P0(Fragment fragment) {
        Q0(fragment, this.t, 0, 0, false);
    }

    void Q(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).Q(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:276:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void Q0(androidx.fragment.app.Fragment r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.h.Q0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    void R(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).R(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    public void R0() {
        this.z = false;
        this.A = false;
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    void S(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).S(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    public void S0(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.h) {
                this.C = true;
            } else {
                fragment.mDeferStart = false;
                Q0(fragment, this.t, 0, 0, false);
            }
        }
    }

    void T(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).T(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1029b) {
                g.a aVar = iVar.f1028a;
                throw null;
            }
        }
    }

    public boolean U(MenuItem menuItem) {
        if (this.t < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean U0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.l;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.l.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.l.get(size2);
                    if ((str != null && str.equals(aVar.o())) || (i2 >= 0 && i2 == aVar.u)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.l.get(size2);
                        if (str == null || !str.equals(aVar2.o())) {
                            if (i2 < 0 || i2 != aVar2.u) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.l.size() - 1) {
                return false;
            }
            for (int size3 = this.l.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.l.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void V(Menu menu) {
        if (this.t < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public void W0(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            m1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    public void X() {
        d0(3);
    }

    public void X0(Fragment fragment) {
        if (f1007d) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (D0(fragment)) {
                this.y = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void Y(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public boolean Z(Menu menu) {
        if (this.t < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    void Z0(Fragment fragment) {
        if (G0()) {
            if (f1007d) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.J.k(fragment) && f1007d) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // androidx.fragment.app.g
    public androidx.fragment.app.j a() {
        return new androidx.fragment.app.a(this);
    }

    void a0() {
        o1();
        W(this.x);
    }

    void a1() {
        if (this.r != null) {
            for (int i2 = 0; i2 < this.r.size(); i2++) {
                this.r.get(i2).a();
            }
        }
    }

    @Override // androidx.fragment.app.g
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.k.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.k.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.j.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment fragment2 = this.j.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.m;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                Fragment fragment3 = this.m.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.l;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                androidx.fragment.app.a aVar = this.l.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.j(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList<androidx.fragment.app.a> arrayList3 = this.p;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    Object obj = (androidx.fragment.app.a) this.p.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.q;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.q.toArray()));
            }
        }
        ArrayList<k> arrayList5 = this.g;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                Object obj2 = (k) this.g.get(i6);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.u);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.v);
        if (this.w != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.w);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.t);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.z);
        printWriter.print(" mStopped=");
        printWriter.print(this.A);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.B);
        if (this.y) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.y);
        }
    }

    public void b0() {
        this.z = false;
        this.A = false;
        d0(4);
    }

    void b1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f986b == null) {
            return;
        }
        for (Fragment fragment : this.J.h()) {
            if (f1007d) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f986b.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f990c.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f1007d) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f986b);
                }
                Q0(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                Q0(fragment, 0, 0, 0, false);
            } else {
                next.o = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
                fragment.mTarget = null;
                Bundle bundle = next.n;
                if (bundle != null) {
                    bundle.setClassLoader(this.u.e().getClassLoader());
                    fragment.mSavedViewState = next.n.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = next.n;
                }
            }
        }
        this.k.clear();
        for (FragmentState fragmentState : fragmentManagerState.f986b) {
            if (fragmentState != null) {
                Fragment fragmentF = fragmentState.f(this.u.e().getClassLoader(), e());
                fragmentF.mFragmentManager = this;
                if (f1007d) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentF.mWho + "): " + fragmentF);
                }
                this.k.put(fragmentF.mWho, fragmentF);
                fragmentState.o = null;
            }
        }
        this.j.clear();
        ArrayList<String> arrayList = fragmentManagerState.f987c;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.k.get(str);
                if (fragment3 == null) {
                    m1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.mAdded = true;
                if (f1007d) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                }
                if (this.j.contains(fragment3)) {
                    throw new IllegalStateException("Already added " + fragment3);
                }
                synchronized (this.j) {
                    this.j.add(fragment3);
                }
            }
        }
        if (fragmentManagerState.f988d != null) {
            this.l = new ArrayList<>(fragmentManagerState.f988d.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f988d;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarF = backStackStateArr[i2].f(this);
                if (f1007d) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarF.u + "): " + aVarF);
                    PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
                    aVarF.k("  ", printWriter, false);
                    printWriter.close();
                }
                this.l.add(aVarF);
                int i3 = aVarF.u;
                if (i3 >= 0) {
                    h1(i3, aVarF);
                }
                i2++;
            }
        } else {
            this.l = null;
        }
        String str2 = fragmentManagerState.e;
        if (str2 != null) {
            Fragment fragment4 = this.k.get(str2);
            this.x = fragment4;
            W(fragment4);
        }
        this.i = fragmentManagerState.f;
    }

    @Override // androidx.fragment.app.g
    public boolean c() {
        boolean zK0 = k0();
        r0();
        return zK0;
    }

    public void c0() {
        this.z = false;
        this.A = false;
        d0(3);
    }

    @Override // androidx.fragment.app.g
    public Fragment d(String str) {
        if (str != null) {
            for (int size = this.j.size() - 1; size >= 0; size--) {
                Fragment fragment = this.j.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && str.equals(fragment2.mTag)) {
                return fragment2;
            }
        }
        return null;
    }

    Parcelable d1() {
        ArrayList<String> arrayList;
        int size;
        r0();
        g0();
        k0();
        this.z = true;
        BackStackState[] backStackStateArr = null;
        if (this.k.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.k.size());
        boolean z = false;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.mState <= 0 || fragmentState.n != null) {
                    fragmentState.n = fragment.mSavedFragmentState;
                } else {
                    fragmentState.n = e1(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = this.k.get(str);
                        if (fragment2 == null) {
                            m1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                        }
                        if (fragmentState.n == null) {
                            fragmentState.n = new Bundle();
                        }
                        W0(fragmentState.n, "android:target_state", fragment2);
                        int i2 = fragment.mTargetRequestCode;
                        if (i2 != 0) {
                            fragmentState.n.putInt("android:target_req_state", i2);
                        }
                    }
                }
                if (f1007d) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.n);
                }
                z = true;
            }
        }
        if (!z) {
            if (f1007d) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.j.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.j) {
                arrayList.add(fragment3.mWho);
                if (fragment3.mFragmentManager != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (f1007d) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.mWho + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<androidx.fragment.app.a> arrayList3 = this.l;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState(this.l.get(i3));
                if (f1007d) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.l.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f986b = arrayList2;
        fragmentManagerState.f987c = arrayList;
        fragmentManagerState.f988d = backStackStateArr;
        Fragment fragment4 = this.x;
        if (fragment4 != null) {
            fragmentManagerState.e = fragment4.mWho;
        }
        fragmentManagerState.f = this.i;
        return fragmentManagerState;
    }

    @Override // androidx.fragment.app.g
    public androidx.fragment.app.e e() {
        if (super.e() == androidx.fragment.app.g.f1005b) {
            Fragment fragment = this.w;
            if (fragment != null) {
                return fragment.mFragmentManager.e();
            }
            h(new f());
        }
        return super.e();
    }

    public void e0() {
        this.A = true;
        d0(2);
    }

    Bundle e1(Fragment fragment) {
        if (this.G == null) {
            this.G = new Bundle();
        }
        fragment.performSaveInstanceState(this.G);
        P(fragment, this.G, false);
        Bundle bundle = null;
        if (!this.G.isEmpty()) {
            Bundle bundle2 = this.G;
            this.G = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            f1(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    @Override // androidx.fragment.app.g
    public List<Fragment> f() {
        List<Fragment> list;
        if (this.j.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.j) {
            list = (List) this.j.clone();
        }
        return list;
    }

    void f0() {
        if (this.C) {
            this.C = false;
            l1();
        }
    }

    void f1(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.H;
        if (sparseArray == null) {
            this.H = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.H);
        if (this.H.size() > 0) {
            fragment.mSavedViewState = this.H;
            this.H = null;
        }
    }

    @Override // androidx.fragment.app.g
    public boolean g() {
        s();
        return T0(null, -1, 0);
    }

    void g1() {
        synchronized (this) {
            ArrayList<l> arrayList = this.I;
            boolean z = false;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.g;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.u.f().removeCallbacks(this.K);
                this.u.f().post(this.K);
                o1();
            }
        }
    }

    public void h0(k kVar, boolean z) {
        if (!z) {
            s();
        }
        synchronized (this) {
            if (!this.B && this.u != null) {
                if (this.g == null) {
                    this.g = new ArrayList<>();
                }
                this.g.add(kVar);
                g1();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void h1(int i2, androidx.fragment.app.a aVar) {
        synchronized (this) {
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            int size = this.p.size();
            if (i2 < size) {
                if (f1007d) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + aVar);
                }
                this.p.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.p.add(null);
                    if (this.q == null) {
                        this.q = new ArrayList<>();
                    }
                    if (f1007d) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.q.add(Integer.valueOf(size));
                    size++;
                }
                if (f1007d) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + aVar);
                }
                this.p.add(aVar);
            }
        }
    }

    public void i1(Fragment fragment, Lifecycle.State state) {
        if (this.k.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void j(androidx.fragment.app.a aVar) {
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        this.l.add(aVar);
    }

    void j0(Fragment fragment) {
        if (!fragment.mFromLayout || fragment.mPerformedCreateView) {
            return;
        }
        fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
        View view = fragment.mView;
        if (view == null) {
            fragment.mInnerView = null;
            return;
        }
        fragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.mHidden) {
            fragment.mView.setVisibility(8);
        }
        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
        S(fragment, fragment.mView, fragment.mSavedFragmentState, false);
    }

    public void j1(Fragment fragment) {
        if (fragment == null || (this.k.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            Fragment fragment2 = this.x;
            this.x = fragment;
            W(fragment2);
            W(this.x);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void k(Fragment fragment, boolean z) {
        if (f1007d) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        I0(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.j.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.j) {
            this.j.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (D0(fragment)) {
            this.y = true;
        }
        if (z) {
            P0(fragment);
        }
    }

    public boolean k0() {
        i0(true);
        boolean z = false;
        while (t0(this.D, this.E)) {
            this.h = true;
            try {
                Y0(this.D, this.E);
                t();
                z = true;
            } catch (Throwable th) {
                t();
                throw th;
            }
        }
        o1();
        f0();
        q();
        return z;
    }

    public void k1(Fragment fragment) {
        if (f1007d) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    void l(Fragment fragment) {
        if (G0()) {
            if (f1007d) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.J.d(fragment) && f1007d) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    void l1() {
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                S0(fragment);
            }
        }
    }

    public int m(androidx.fragment.app.a aVar) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.q;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.q.remove(r0.size() - 1).intValue();
                if (f1007d) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.p.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            int size = this.p.size();
            if (f1007d) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.p.add(aVar);
            return size;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void o(androidx.fragment.app.f fVar, androidx.fragment.app.c cVar, Fragment fragment) {
        if (this.u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.u = fVar;
        this.v = cVar;
        this.w = fragment;
        if (fragment != null) {
            o1();
        }
        if (fVar instanceof androidx.activity.c) {
            androidx.activity.c cVar2 = (androidx.activity.c) fVar;
            OnBackPressedDispatcher onBackPressedDispatcher = cVar2.getOnBackPressedDispatcher();
            this.n = onBackPressedDispatcher;
            androidx.lifecycle.f fVar2 = cVar2;
            if (fragment != null) {
                fVar2 = fragment;
            }
            onBackPressedDispatcher.a(fVar2, this.o);
        }
        if (fragment != null) {
            this.J = fragment.mFragmentManager.v0(fragment);
        } else if (fVar instanceof q) {
            this.J = androidx.fragment.app.i.g(((q) fVar).getViewModelStore());
        } else {
            this.J = new androidx.fragment.app.i(false);
        }
    }

    public Fragment o0(int i2) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i2) {
                return fragment2;
            }
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f1030a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !androidx.fragment.app.e.b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentO0 = resourceId != -1 ? o0(resourceId) : null;
        if (fragmentO0 == null && string != null) {
            fragmentO0 = d(string);
        }
        if (fragmentO0 == null && id != -1) {
            fragmentO0 = o0(id);
        }
        if (f1007d) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentO0);
        }
        if (fragmentO0 == null) {
            fragmentO0 = e().a(context.getClassLoader(), str2);
            fragmentO0.mFromLayout = true;
            fragmentO0.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentO0.mContainerId = id;
            fragmentO0.mTag = string;
            fragmentO0.mInLayout = true;
            fragmentO0.mFragmentManager = this;
            androidx.fragment.app.f fVar = this.u;
            fragmentO0.mHost = fVar;
            fragmentO0.onInflate(fVar.e(), attributeSet, fragmentO0.mSavedFragmentState);
            k(fragmentO0, true);
        } else {
            if (fragmentO0.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentO0.mInLayout = true;
            androidx.fragment.app.f fVar2 = this.u;
            fragmentO0.mHost = fVar2;
            fragmentO0.onInflate(fVar2.e(), attributeSet, fragmentO0.mSavedFragmentState);
        }
        Fragment fragment = fragmentO0;
        if (this.t >= 1 || !fragment.mFromLayout) {
            P0(fragment);
        } else {
            Q0(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    public void p(Fragment fragment) {
        if (f1007d) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.j.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (f1007d) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.j) {
                this.j.add(fragment);
            }
            fragment.mAdded = true;
            if (D0(fragment)) {
                this.y = true;
            }
        }
    }

    public Fragment p0(String str) {
        Fragment fragmentFindFragmentByWho;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && (fragmentFindFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    boolean r() {
        boolean zD0 = false;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                zD0 = D0(fragment);
            }
            if (zD0) {
                return true;
            }
        }
        return false;
    }

    public void s0(int i2) {
        synchronized (this) {
            this.p.set(i2, null);
            if (this.q == null) {
                this.q = new ArrayList<>();
            }
            if (f1007d) {
                Log.v("FragmentManager", "Freeing back stack index " + i2);
            }
            this.q.add(Integer.valueOf(i2));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.w;
        if (fragment != null) {
            b.g.j.a.a(fragment, sb);
        } else {
            b.g.j.a.a(this.u, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    void u(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.m(z3);
        } else {
            aVar.l();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            O0(this.t, true);
        }
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.p(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public int u0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.l;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void v(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            g gVarH0 = H0(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (gVarH0 == null || (animator = gVarH0.f1024b) == null) {
                if (gVarH0 != null) {
                    fragment.mView.startAnimation(gVarH0.f1023a);
                    gVarH0.f1023a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    ViewGroup viewGroup = fragment.mContainer;
                    View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    gVarH0.f1024b.addListener(new e(viewGroup, view, fragment));
                }
                gVarH0.f1024b.start();
            }
        }
        if (fragment.mAdded && D0(fragment)) {
            this.y = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    androidx.fragment.app.i v0(Fragment fragment) {
        return this.J.f(fragment);
    }

    public void w(Fragment fragment) {
        if (f1007d) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (f1007d) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (D0(fragment)) {
                this.y = true;
            }
            fragment.mAdded = false;
        }
    }

    public Fragment w0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.k.get(string);
        if (fragment == null) {
            m1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void x() {
        this.z = false;
        this.A = false;
        d0(2);
    }

    LayoutInflater.Factory2 x0() {
        return this;
    }

    public void y(Configuration configuration) {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public Fragment y0() {
        return this.x;
    }

    public boolean z(MenuItem menuItem) {
        if (this.t < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    p z0(Fragment fragment) {
        return this.J.i(fragment);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1023a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1024b;

        g(Animation animation) {
            this.f1023a = animation;
            this.f1024b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        g(Animator animator) {
            this.f1023a = null;
            this.f1024b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class RunnableC0019h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ViewGroup f1025b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final View f1026c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1027d;
        private boolean e;
        private boolean f;

        RunnableC0019h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f = true;
            this.f1025b = viewGroup;
            this.f1026c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f = true;
            if (this.f1027d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1027d = true;
                b.g.k.q.a(this.f1025b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1027d || !this.f) {
                this.f1025b.endViewTransition(this.f1026c);
                this.e = true;
            } else {
                this.f = false;
                this.f1025b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.f = true;
            if (this.f1027d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f1027d = true;
                b.g.k.q.a(this.f1025b, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
