package r0;

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
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import androidx.lifecycle.Lifecycle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import r0.g;
import t0.p;
import t0.q;

/* JADX INFO: compiled from: FragmentManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends r0.g implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f4686d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Interpolator f4687e = new DecelerateInterpolator(2.5f);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Interpolator f4688f = new DecelerateInterpolator(1.5f);
    public boolean A;
    public boolean B;
    public boolean C;
    public ArrayList<r0.a> D;
    public ArrayList<Boolean> E;
    public ArrayList<Fragment> F;
    public ArrayList<l> I;
    public r0.i J;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<k> f4689g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4690h;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList<r0.a> f4694l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<Fragment> f4695m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public OnBackPressedDispatcher f4696n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList<r0.a> f4698p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList<Integer> f4699q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<g.b> f4700r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public r0.f f4703u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public r0.c f4704v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Fragment f4705w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Fragment f4706x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4707y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f4708z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4691i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList<Fragment> f4692j = new ArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final HashMap<String, Fragment> f4693k = new HashMap<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h.b f4697o = new a(false);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final CopyOnWriteArrayList<i> f4701s = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4702t = 0;
    public Bundle G = null;
    public SparseArray<Parcelable> H = null;
    public Runnable K = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class a extends h.b {
        public a(boolean z3) {
            super(z3);
        }

        @Override // h.b
        public void b() {
            h.this.A0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.k0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f4711a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f4712b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f4712b.getAnimatingAway() != null) {
                    c.this.f4712b.setAnimatingAway(null);
                    c cVar = c.this;
                    h hVar = h.this;
                    Fragment fragment = cVar.f4712b;
                    hVar.Q0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.f4711a = viewGroup;
            this.f4712b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f4711a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f4715a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f4716b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f4717c;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f4715a = viewGroup;
            this.f4716b = view;
            this.f4717c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f4715a.endViewTransition(this.f4716b);
            Animator animator2 = this.f4717c.getAnimator();
            this.f4717c.setAnimator(null);
            if (animator2 == null || this.f4715a.indexOfChild(this.f4716b) >= 0) {
                return;
            }
            h hVar = h.this;
            Fragment fragment = this.f4717c;
            hVar.Q0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f4719a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f4720b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f4721c;

        public e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f4719a = viewGroup;
            this.f4720b = view;
            this.f4721c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f4719a.endViewTransition(this.f4720b);
            animator.removeListener(this);
            Fragment fragment = this.f4721c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class f extends r0.e {
        public f() {
        }

        @Override // r0.e
        public Fragment a(ClassLoader classLoader, String str) {
            r0.f fVar = h.this.f4703u;
            return fVar.a(fVar.e(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g.a f4731a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f4732b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f4733a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public interface k {
        boolean a(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class l implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f4734a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final r0.a f4735b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4736c;

        public l(r0.a aVar, boolean z3) {
            this.f4734a = z3;
            this.f4735b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            int i3 = this.f4736c - 1;
            this.f4736c = i3;
            if (i3 != 0) {
                return;
            }
            this.f4735b.f4674s.g1();
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            this.f4736c++;
        }

        public void c() {
            r0.a aVar = this.f4735b;
            aVar.f4674s.u(aVar, this.f4734a, false, false);
        }

        public void d() {
            boolean z3 = this.f4736c > 0;
            h hVar = this.f4735b.f4674s;
            int size = hVar.f4692j.size();
            for (int i3 = 0; i3 < size; i3++) {
                Fragment fragment = hVar.f4692j.get(i3);
                fragment.setOnStartEnterTransitionListener(null);
                if (z3 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            r0.a aVar = this.f4735b;
            aVar.f4674s.u(aVar, this.f4734a, !z3, true);
        }

        public boolean e() {
            return this.f4736c == 0;
        }
    }

    public static g J0(float f3, float f4) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(f4688f);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    public static g L0(float f3, float f4, float f5, float f6) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f3, f4, f3, f4, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f4687e);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f5, f6);
        alphaAnimation.setInterpolator(f4688f);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    public static int c1(int i3) {
        if (i3 == 4097) {
            return 8194;
        }
        if (i3 != 4099) {
            return i3 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public static void l0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        while (i3 < i4) {
            r0.a aVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                aVar.h(-1);
                aVar.m(i3 == i4 + (-1));
            } else {
                aVar.h(1);
                aVar.l();
            }
            i3++;
        }
    }

    public static int n1(int i3, boolean z3) {
        if (i3 == 4097) {
            return z3 ? 1 : 2;
        }
        if (i3 == 4099) {
            return z3 ? 5 : 6;
        }
        if (i3 != 8194) {
            return -1;
        }
        return z3 ? 3 : 4;
    }

    public void A() {
        this.f4708z = false;
        this.A = false;
        d0(1);
    }

    public void A0() {
        k0();
        if (this.f4697o.c()) {
            g();
        } else {
            this.f4696n.c();
        }
    }

    public boolean B(Menu menu, MenuInflater menuInflater) {
        if (this.f4702t < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z3 = false;
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z3 = true;
            }
        }
        if (this.f4695m != null) {
            for (int i4 = 0; i4 < this.f4695m.size(); i4++) {
                Fragment fragment2 = this.f4695m.get(i4);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f4695m = arrayList;
        return z3;
    }

    public void B0(Fragment fragment) {
        if (f4686d) {
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
        this.f4703u = null;
        this.f4704v = null;
        this.f4705w = null;
        if (this.f4696n != null) {
            this.f4697o.d();
            this.f4696n = null;
        }
    }

    public boolean C0() {
        return this.B;
    }

    public void D() {
        d0(1);
    }

    public final boolean D0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.r();
    }

    public void E() {
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public boolean E0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        h hVar = fragment.mFragmentManager;
        return fragment == hVar.y0() && E0(hVar.f4705w);
    }

    public void F(boolean z3) {
        for (int size = this.f4692j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f4692j.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z3);
            }
        }
    }

    public boolean F0(int i3) {
        return this.f4702t >= i3;
    }

    public void G(Fragment fragment, Bundle bundle, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).G(fragment, bundle, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public boolean G0() {
        return this.f4708z || this.A;
    }

    public void H(Fragment fragment, Context context, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).H(fragment, context, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public g H0(Fragment fragment, int i3, boolean z3, int i4) {
        int iN1;
        int nextAnim = fragment.getNextAnim();
        boolean z4 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i3, z3, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new g(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i3, z3, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new g(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.f4703u.e().getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.f4703u.e(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z4 = true;
                } catch (Resources.NotFoundException e3) {
                    throw e3;
                } catch (RuntimeException unused) {
                }
            }
            if (!z4) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.f4703u.e(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e4) {
                    if (zEquals) {
                        throw e4;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.f4703u.e(), nextAnim);
                    if (animationLoadAnimation2 != null) {
                        return new g(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i3 == 0 || (iN1 = n1(i3, z3)) < 0) {
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
                if (i4 == 0 && this.f4703u.l()) {
                    i4 = this.f4703u.k();
                }
                if (i4 == 0) {
                }
                return null;
        }
    }

    public void I(Fragment fragment, Bundle bundle, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).I(fragment, bundle, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void I0(Fragment fragment) {
        if (this.f4693k.get(fragment.mWho) != null) {
            return;
        }
        this.f4693k.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                l(fragment);
            } else {
                Z0(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (f4686d) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    public void J(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).J(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void K(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).K(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void K0(Fragment fragment) {
        if (this.f4693k.get(fragment.mWho) == null) {
            return;
        }
        if (f4686d) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.f4693k.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.f4693k.put(fragment.mWho, null);
        Z0(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = this.f4693k.get(str);
        }
        fragment.initState();
    }

    public void L(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).L(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void M(Fragment fragment, Context context, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).M(fragment, context, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public final void M0(s.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragmentI = bVar.i(i3);
            if (!fragmentI.mAdded) {
                View viewRequireView = fragmentI.requireView();
                fragmentI.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    public void N(Fragment fragment, Bundle bundle, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).N(fragment, bundle, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void N0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.f4693k.containsKey(fragment.mWho)) {
            if (f4686d) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.f4702t + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.f4702t;
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
                float f3 = fragment.mPostponedAlpha;
                if (f3 > 0.0f) {
                    fragment.mView.setAlpha(f3);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                g gVarH0 = H0(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (gVarH0 != null) {
                    Animation animation = gVarH0.f4724a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        gVarH0.f4725b.setTarget(fragment.mView);
                        gVarH0.f4725b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            v(fragment);
        }
    }

    public void O(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).O(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void O0(int i3, boolean z3) {
        r0.f fVar;
        if (this.f4703u == null && i3 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z3 || i3 != this.f4702t) {
            this.f4702t = i3;
            int size = this.f4692j.size();
            for (int i4 = 0; i4 < size; i4++) {
                N0(this.f4692j.get(i4));
            }
            for (Fragment fragment : this.f4693k.values()) {
                if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                    if (!fragment.mIsNewlyAdded) {
                        N0(fragment);
                    }
                }
            }
            l1();
            if (this.f4707y && (fVar = this.f4703u) != null && this.f4702t == 4) {
                fVar.r();
                this.f4707y = false;
            }
        }
    }

    public void P(Fragment fragment, Bundle bundle, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).P(fragment, bundle, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void P0(Fragment fragment) {
        Q0(fragment, this.f4702t, 0, 0, false);
    }

    public void Q(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).Q(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
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
    public void Q0(androidx.fragment.app.Fragment r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.h.Q0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public void R(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).R(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void R0() {
        this.f4708z = false;
        this.A = false;
        int size = this.f4692j.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void S(Fragment fragment, View view, Bundle bundle, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).S(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public void S0(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.f4690h) {
                this.C = true;
            } else {
                fragment.mDeferStart = false;
                Q0(fragment, this.f4702t, 0, 0, false);
            }
        }
    }

    public void T(Fragment fragment, boolean z3) {
        Fragment fragment2 = this.f4705w;
        if (fragment2 != null) {
            r0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).T(fragment, true);
            }
        }
        for (i iVar : this.f4701s) {
            if (!z3 || iVar.f4732b) {
                g.a aVar = iVar.f4731a;
                throw null;
            }
        }
    }

    public final boolean T0(String str, int i3, int i4) {
        k0();
        i0(true);
        Fragment fragment = this.f4706x;
        if (fragment != null && i3 < 0 && str == null && fragment.getChildFragmentManager().g()) {
            return true;
        }
        boolean zU0 = U0(this.D, this.E, str, i3, i4);
        if (zU0) {
            this.f4690h = true;
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

    public boolean U(MenuItem menuItem) {
        if (this.f4702t < 1) {
            return false;
        }
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean U0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i3, int i4) {
        int i5;
        ArrayList<r0.a> arrayList3 = this.f4694l;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i3 < 0 && (i4 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f4694l.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i3 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    r0.a aVar = this.f4694l.get(size2);
                    if ((str != null && str.equals(aVar.o())) || (i3 >= 0 && i3 == aVar.f4676u)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i4 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        r0.a aVar2 = this.f4694l.get(size2);
                        if (str == null || !str.equals(aVar2.o())) {
                            if (i3 < 0 || i3 != aVar2.f4676u) {
                                break;
                            }
                        }
                    }
                }
                i5 = size2;
            } else {
                i5 = -1;
            }
            if (i5 == this.f4694l.size() - 1) {
                return false;
            }
            for (int size3 = this.f4694l.size() - 1; size3 > i5; size3--) {
                arrayList.add(this.f4694l.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void V(Menu menu) {
        if (this.f4702t < 1) {
            return;
        }
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public final int V0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4, s.b<Fragment> bVar) {
        int i5 = i4;
        for (int i6 = i4 - 1; i6 >= i3; i6--) {
            r0.a aVar = arrayList.get(i6);
            boolean zBooleanValue = arrayList2.get(i6).booleanValue();
            if (aVar.s() && !aVar.q(arrayList, i6 + 1, i4)) {
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
                i5--;
                if (i6 != i5) {
                    arrayList.remove(i6);
                    arrayList.add(i5, aVar);
                }
                i(bVar);
            }
        }
        return i5;
    }

    public final void W(Fragment fragment) {
        if (fragment == null || this.f4693k.get(fragment.mWho) != fragment) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
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
        if (f4686d) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z3 = !fragment.isInBackStack();
        if (!fragment.mDetached || z3) {
            synchronized (this.f4692j) {
                this.f4692j.remove(fragment);
            }
            if (D0(fragment)) {
                this.f4707y = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void Y(boolean z3) {
        for (int size = this.f4692j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f4692j.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z3);
            }
        }
    }

    public final void Y0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        n0(arrayList, arrayList2);
        int size = arrayList.size();
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            if (!arrayList.get(i3).f4760q) {
                if (i4 != i3) {
                    m0(arrayList, arrayList2, i4, i3);
                }
                i4 = i3 + 1;
                if (arrayList2.get(i3).booleanValue()) {
                    while (i4 < size && arrayList2.get(i4).booleanValue() && !arrayList.get(i4).f4760q) {
                        i4++;
                    }
                }
                m0(arrayList, arrayList2, i3, i4);
                i3 = i4 - 1;
            }
            i3++;
        }
        if (i4 != size) {
            m0(arrayList, arrayList2, i4, size);
        }
    }

    public boolean Z(Menu menu) {
        if (this.f4702t < 1) {
            return false;
        }
        boolean z3 = false;
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z3 = true;
            }
        }
        return z3;
    }

    public void Z0(Fragment fragment) {
        if (G0()) {
            if (f4686d) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.J.k(fragment) && f4686d) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // r0.g
    public r0.j a() {
        return new r0.a(this);
    }

    public void a0() {
        o1();
        W(this.f4706x);
    }

    public void a1() {
        if (this.f4700r != null) {
            for (int i3 = 0; i3 < this.f4700r.size(); i3++) {
                this.f4700r.get(i3).a();
            }
        }
    }

    @Override // r0.g
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.f4693k.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.f4693k.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f4692j.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i3 = 0; i3 < size5; i3++) {
                Fragment fragment2 = this.f4692j.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f4695m;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i4 = 0; i4 < size4; i4++) {
                Fragment fragment3 = this.f4695m.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<r0.a> arrayList2 = this.f4694l;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i5 = 0; i5 < size3; i5++) {
                r0.a aVar = this.f4694l.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.j(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList<r0.a> arrayList3 = this.f4698p;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i6 = 0; i6 < size2; i6++) {
                    Object obj = (r0.a) this.f4698p.get(i6);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i6);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.f4699q;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f4699q.toArray()));
            }
        }
        ArrayList<k> arrayList5 = this.f4689g;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i7 = 0; i7 < size; i7++) {
                Object obj2 = (k) this.f4689g.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f4703u);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f4704v);
        if (this.f4705w != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f4705w);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f4702t);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f4708z);
        printWriter.print(" mStopped=");
        printWriter.print(this.A);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.B);
        if (this.f4707y) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f4707y);
        }
    }

    public void b0() {
        this.f4708z = false;
        this.A = false;
        d0(4);
    }

    public void b1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1315b == null) {
            return;
        }
        for (Fragment fragment : this.J.h()) {
            if (f4686d) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f1315b.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f1321c.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f4686d) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f1315b);
                }
                Q0(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                Q0(fragment, 0, 0, 0, false);
            } else {
                next.f1333o = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
                fragment.mTarget = null;
                Bundle bundle = next.f1332n;
                if (bundle != null) {
                    bundle.setClassLoader(this.f4703u.e().getClassLoader());
                    fragment.mSavedViewState = next.f1332n.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = next.f1332n;
                }
            }
        }
        this.f4693k.clear();
        for (FragmentState fragmentState : fragmentManagerState.f1315b) {
            if (fragmentState != null) {
                Fragment fragmentF = fragmentState.f(this.f4703u.e().getClassLoader(), e());
                fragmentF.mFragmentManager = this;
                if (f4686d) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentF.mWho + "): " + fragmentF);
                }
                this.f4693k.put(fragmentF.mWho, fragmentF);
                fragmentState.f1333o = null;
            }
        }
        this.f4692j.clear();
        ArrayList<String> arrayList = fragmentManagerState.f1316c;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.f4693k.get(str);
                if (fragment3 == null) {
                    m1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.mAdded = true;
                if (f4686d) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                }
                if (this.f4692j.contains(fragment3)) {
                    throw new IllegalStateException("Already added " + fragment3);
                }
                synchronized (this.f4692j) {
                    this.f4692j.add(fragment3);
                }
            }
        }
        if (fragmentManagerState.f1317d != null) {
            this.f4694l = new ArrayList<>(fragmentManagerState.f1317d.length);
            int i3 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f1317d;
                if (i3 >= backStackStateArr.length) {
                    break;
                }
                r0.a aVarF = backStackStateArr[i3].f(this);
                if (f4686d) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i3 + " (index " + aVarF.f4676u + "): " + aVarF);
                    PrintWriter printWriter = new PrintWriter(new k0.b("FragmentManager"));
                    aVarF.k("  ", printWriter, false);
                    printWriter.close();
                }
                this.f4694l.add(aVarF);
                int i4 = aVarF.f4676u;
                if (i4 >= 0) {
                    h1(i4, aVarF);
                }
                i3++;
            }
        } else {
            this.f4694l = null;
        }
        String str2 = fragmentManagerState.f1318e;
        if (str2 != null) {
            Fragment fragment4 = this.f4693k.get(str2);
            this.f4706x = fragment4;
            W(fragment4);
        }
        this.f4691i = fragmentManagerState.f1319f;
    }

    @Override // r0.g
    public boolean c() {
        boolean zK0 = k0();
        r0();
        return zK0;
    }

    public void c0() {
        this.f4708z = false;
        this.A = false;
        d0(3);
    }

    @Override // r0.g
    public Fragment d(String str) {
        if (str != null) {
            for (int size = this.f4692j.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f4692j.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.f4693k.values()) {
            if (fragment2 != null && str.equals(fragment2.mTag)) {
                return fragment2;
            }
        }
        return null;
    }

    public final void d0(int i3) {
        try {
            this.f4690h = true;
            O0(i3, false);
            this.f4690h = false;
            k0();
        } catch (Throwable th) {
            this.f4690h = false;
            throw th;
        }
    }

    public Parcelable d1() {
        ArrayList<String> arrayList;
        int size;
        r0();
        g0();
        k0();
        this.f4708z = true;
        BackStackState[] backStackStateArr = null;
        if (this.f4693k.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.f4693k.size());
        boolean z3 = false;
        for (Fragment fragment : this.f4693k.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.mState <= 0 || fragmentState.f1332n != null) {
                    fragmentState.f1332n = fragment.mSavedFragmentState;
                } else {
                    fragmentState.f1332n = e1(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = this.f4693k.get(str);
                        if (fragment2 == null) {
                            m1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                        }
                        if (fragmentState.f1332n == null) {
                            fragmentState.f1332n = new Bundle();
                        }
                        W0(fragmentState.f1332n, "android:target_state", fragment2);
                        int i3 = fragment.mTargetRequestCode;
                        if (i3 != 0) {
                            fragmentState.f1332n.putInt("android:target_req_state", i3);
                        }
                    }
                }
                if (f4686d) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f1332n);
                }
                z3 = true;
            }
        }
        if (!z3) {
            if (f4686d) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f4692j.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.f4692j) {
                arrayList.add(fragment3.mWho);
                if (fragment3.mFragmentManager != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (f4686d) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.mWho + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<r0.a> arrayList3 = this.f4694l;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i4 = 0; i4 < size; i4++) {
                backStackStateArr[i4] = new BackStackState(this.f4694l.get(i4));
                if (f4686d) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i4 + ": " + this.f4694l.get(i4));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1315b = arrayList2;
        fragmentManagerState.f1316c = arrayList;
        fragmentManagerState.f1317d = backStackStateArr;
        Fragment fragment4 = this.f4706x;
        if (fragment4 != null) {
            fragmentManagerState.f1318e = fragment4.mWho;
        }
        fragmentManagerState.f1319f = this.f4691i;
        return fragmentManagerState;
    }

    @Override // r0.g
    public r0.e e() {
        if (super.e() == r0.g.f4684b) {
            Fragment fragment = this.f4705w;
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

    public Bundle e1(Fragment fragment) {
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

    @Override // r0.g
    public List<Fragment> f() {
        List<Fragment> list;
        if (this.f4692j.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f4692j) {
            list = (List) this.f4692j.clone();
        }
        return list;
    }

    public void f0() {
        if (this.C) {
            this.C = false;
            l1();
        }
    }

    public void f1(Fragment fragment) {
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

    @Override // r0.g
    public boolean g() {
        s();
        return T0(null, -1, 0);
    }

    public final void g0() {
        for (Fragment fragment : this.f4693k.values()) {
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

    public void g1() {
        synchronized (this) {
            ArrayList<l> arrayList = this.I;
            boolean z3 = false;
            boolean z4 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.f4689g;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z3 = true;
            }
            if (z4 || z3) {
                this.f4703u.f().removeCallbacks(this.K);
                this.f4703u.f().post(this.K);
                o1();
            }
        }
    }

    public void h0(k kVar, boolean z3) {
        if (!z3) {
            s();
        }
        synchronized (this) {
            if (!this.B && this.f4703u != null) {
                if (this.f4689g == null) {
                    this.f4689g = new ArrayList<>();
                }
                this.f4689g.add(kVar);
                g1();
                return;
            }
            if (!z3) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void h1(int i3, r0.a aVar) {
        synchronized (this) {
            if (this.f4698p == null) {
                this.f4698p = new ArrayList<>();
            }
            int size = this.f4698p.size();
            if (i3 < size) {
                if (f4686d) {
                    Log.v("FragmentManager", "Setting back stack index " + i3 + " to " + aVar);
                }
                this.f4698p.set(i3, aVar);
            } else {
                while (size < i3) {
                    this.f4698p.add(null);
                    if (this.f4699q == null) {
                        this.f4699q = new ArrayList<>();
                    }
                    if (f4686d) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f4699q.add(Integer.valueOf(size));
                    size++;
                }
                if (f4686d) {
                    Log.v("FragmentManager", "Adding back stack index " + i3 + " with " + aVar);
                }
                this.f4698p.add(aVar);
            }
        }
    }

    public final void i(s.b<Fragment> bVar) {
        int i3 = this.f4702t;
        if (i3 < 1) {
            return;
        }
        int iMin = Math.min(i3, 3);
        int size = this.f4692j.size();
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f4692j.get(i4);
            if (fragment.mState < iMin) {
                Q0(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    public final void i0(boolean z3) {
        if (this.f4690h) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f4703u == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.f4703u.f().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z3) {
            s();
        }
        if (this.D == null) {
            this.D = new ArrayList<>();
            this.E = new ArrayList<>();
        }
        this.f4690h = true;
        try {
            n0(null, null);
        } finally {
            this.f4690h = false;
        }
    }

    public void i1(Fragment fragment, Lifecycle.State state) {
        if (this.f4693k.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void j(r0.a aVar) {
        if (this.f4694l == null) {
            this.f4694l = new ArrayList<>();
        }
        this.f4694l.add(aVar);
    }

    public void j0(Fragment fragment) {
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
        if (fragment == null || (this.f4693k.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            Fragment fragment2 = this.f4706x;
            this.f4706x = fragment;
            W(fragment2);
            W(this.f4706x);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void k(Fragment fragment, boolean z3) {
        if (f4686d) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        I0(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.f4692j.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f4692j) {
            this.f4692j.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (D0(fragment)) {
            this.f4707y = true;
        }
        if (z3) {
            P0(fragment);
        }
    }

    public boolean k0() {
        i0(true);
        boolean z3 = false;
        while (t0(this.D, this.E)) {
            this.f4690h = true;
            try {
                Y0(this.D, this.E);
                t();
                z3 = true;
            } catch (Throwable th) {
                t();
                throw th;
            }
        }
        o1();
        f0();
        q();
        return z3;
    }

    public void k1(Fragment fragment) {
        if (f4686d) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public void l(Fragment fragment) {
        if (G0()) {
            if (f4686d) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.J.d(fragment) && f4686d) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    public void l1() {
        for (Fragment fragment : this.f4693k.values()) {
            if (fragment != null) {
                S0(fragment);
            }
        }
    }

    public int m(r0.a aVar) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.f4699q;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.f4699q.remove(r0.size() - 1).intValue();
                if (f4686d) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.f4698p.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.f4698p == null) {
                this.f4698p = new ArrayList<>();
            }
            int size = this.f4698p.size();
            if (f4686d) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.f4698p.add(aVar);
            return size;
        }
    }

    public final void m0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3;
        boolean z3 = arrayList.get(i7).f4760q;
        ArrayList<Fragment> arrayList3 = this.F;
        if (arrayList3 == null) {
            this.F = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.F.addAll(this.f4692j);
        Fragment fragmentY0 = y0();
        boolean z4 = false;
        for (int i8 = i7; i8 < i4; i8++) {
            r0.a aVar = arrayList.get(i8);
            fragmentY0 = !arrayList2.get(i8).booleanValue() ? aVar.n(this.F, fragmentY0) : aVar.v(this.F, fragmentY0);
            z4 = z4 || aVar.f4751h;
        }
        this.F.clear();
        if (!z3) {
            r0.k.B(this, arrayList, arrayList2, i3, i4, false);
        }
        l0(arrayList, arrayList2, i3, i4);
        if (z3) {
            s.b<Fragment> bVar = new s.b<>();
            i(bVar);
            int iV0 = V0(arrayList, arrayList2, i3, i4, bVar);
            M0(bVar);
            i5 = iV0;
        } else {
            i5 = i4;
        }
        if (i5 != i7 && z3) {
            r0.k.B(this, arrayList, arrayList2, i3, i5, true);
            O0(this.f4702t, true);
        }
        while (i7 < i4) {
            r0.a aVar2 = arrayList.get(i7);
            if (arrayList2.get(i7).booleanValue() && (i6 = aVar2.f4676u) >= 0) {
                s0(i6);
                aVar2.f4676u = -1;
            }
            aVar2.t();
            i7++;
        }
        if (z4) {
            a1();
        }
    }

    public final void m1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new k0.b("FragmentManager"));
        r0.f fVar = this.f4703u;
        if (fVar != null) {
            try {
                fVar.h("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e3) {
                Log.e("FragmentManager", "Failed dumping state", e3);
                throw runtimeException;
            }
        }
        try {
            b("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e4) {
            Log.e("FragmentManager", "Failed dumping state", e4);
            throw runtimeException;
        }
    }

    public final void n(Fragment fragment, g gVar, int i3) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i3);
        Animation animation = gVar.f4724a;
        if (animation != null) {
            RunnableC0057h runnableC0057h = new RunnableC0057h(animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            runnableC0057h.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(runnableC0057h);
            return;
        }
        Animator animator = gVar.f4725b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public final void n0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<l> arrayList3 = this.I;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i3 = 0;
        while (i3 < size) {
            l lVar = this.I.get(i3);
            if (arrayList != null && !lVar.f4734a && (iIndexOf2 = arrayList.indexOf(lVar.f4735b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.I.remove(i3);
                i3--;
                size--;
                lVar.c();
            } else if (lVar.e() || (arrayList != null && lVar.f4735b.q(arrayList, 0, arrayList.size()))) {
                this.I.remove(i3);
                i3--;
                size--;
                if (arrayList == null || lVar.f4734a || (iIndexOf = arrayList.indexOf(lVar.f4735b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    lVar.d();
                } else {
                    lVar.c();
                }
            }
            i3++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void o(r0.f fVar, r0.c cVar, Fragment fragment) {
        if (this.f4703u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f4703u = fVar;
        this.f4704v = cVar;
        this.f4705w = fragment;
        if (fragment != null) {
            o1();
        }
        if (fVar instanceof h.c) {
            h.c cVar2 = (h.c) fVar;
            OnBackPressedDispatcher onBackPressedDispatcher = cVar2.getOnBackPressedDispatcher();
            this.f4696n = onBackPressedDispatcher;
            t0.f fVar2 = cVar2;
            if (fragment != null) {
                fVar2 = fragment;
            }
            onBackPressedDispatcher.a(fVar2, this.f4697o);
        }
        if (fragment != null) {
            this.J = fragment.mFragmentManager.v0(fragment);
        } else if (fVar instanceof q) {
            this.J = r0.i.g(((q) fVar).getViewModelStore());
        } else {
            this.J = new r0.i(false);
        }
    }

    public Fragment o0(int i3) {
        for (int size = this.f4692j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f4692j.get(size);
            if (fragment != null && fragment.mFragmentId == i3) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.f4693k.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i3) {
                return fragment2;
            }
        }
        return null;
    }

    public final void o1() {
        ArrayList<k> arrayList = this.f4689g;
        if (arrayList == null || arrayList.isEmpty()) {
            this.f4697o.f(u0() > 0 && E0(this.f4705w));
        } else {
            this.f4697o.f(true);
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f4733a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !r0.e.b(context.getClassLoader(), str2)) {
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
        if (f4686d) {
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
            r0.f fVar = this.f4703u;
            fragmentO0.mHost = fVar;
            fragmentO0.onInflate(fVar.e(), attributeSet, fragmentO0.mSavedFragmentState);
            k(fragmentO0, true);
        } else {
            if (fragmentO0.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentO0.mInLayout = true;
            r0.f fVar2 = this.f4703u;
            fragmentO0.mHost = fVar2;
            fragmentO0.onInflate(fVar2.e(), attributeSet, fragmentO0.mSavedFragmentState);
        }
        Fragment fragment = fragmentO0;
        if (this.f4702t >= 1 || !fragment.mFromLayout) {
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
        if (f4686d) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.f4692j.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (f4686d) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.f4692j) {
                this.f4692j.add(fragment);
            }
            fragment.mAdded = true;
            if (D0(fragment)) {
                this.f4707y = true;
            }
        }
    }

    public Fragment p0(String str) {
        Fragment fragmentFindFragmentByWho;
        for (Fragment fragment : this.f4693k.values()) {
            if (fragment != null && (fragmentFindFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    public final void q() {
        this.f4693k.values().removeAll(Collections.singleton(null));
    }

    public final Fragment q0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f4692j.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f4692j.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    public boolean r() {
        boolean zD0 = false;
        for (Fragment fragment : this.f4693k.values()) {
            if (fragment != null) {
                zD0 = D0(fragment);
            }
            if (zD0) {
                return true;
            }
        }
        return false;
    }

    public final void r0() {
        if (this.I != null) {
            while (!this.I.isEmpty()) {
                this.I.remove(0).d();
            }
        }
    }

    public final void s() {
        if (G0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public void s0(int i3) {
        synchronized (this) {
            this.f4698p.set(i3, null);
            if (this.f4699q == null) {
                this.f4699q = new ArrayList<>();
            }
            if (f4686d) {
                Log.v("FragmentManager", "Freeing back stack index " + i3);
            }
            this.f4699q.add(Integer.valueOf(i3));
        }
    }

    public final void t() {
        this.f4690h = false;
        this.E.clear();
        this.D.clear();
    }

    public final boolean t0(ArrayList<r0.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.f4689g;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.f4689g.size();
                boolean zA = false;
                for (int i3 = 0; i3 < size; i3++) {
                    zA |= this.f4689g.get(i3).a(arrayList, arrayList2);
                }
                this.f4689g.clear();
                this.f4703u.f().removeCallbacks(this.K);
                return zA;
            }
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f4705w;
        if (fragment != null) {
            k0.a.a(fragment, sb);
        } else {
            k0.a.a(this.f4703u, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u(r0.a aVar, boolean z3, boolean z4, boolean z5) {
        if (z3) {
            aVar.m(z5);
        } else {
            aVar.l();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z3));
        if (z4) {
            r0.k.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z5) {
            O0(this.f4702t, true);
        }
        for (Fragment fragment : this.f4693k.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.p(fragment.mContainerId)) {
                float f3 = fragment.mPostponedAlpha;
                if (f3 > 0.0f) {
                    fragment.mView.setAlpha(f3);
                }
                if (z5) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public int u0() {
        ArrayList<r0.a> arrayList = this.f4694l;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void v(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            g gVarH0 = H0(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (gVarH0 == null || (animator = gVarH0.f4725b) == null) {
                if (gVarH0 != null) {
                    fragment.mView.startAnimation(gVarH0.f4724a);
                    gVarH0.f4724a.start();
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
                    gVarH0.f4725b.addListener(new e(viewGroup, view, fragment));
                }
                gVarH0.f4725b.start();
            }
        }
        if (fragment.mAdded && D0(fragment)) {
            this.f4707y = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public r0.i v0(Fragment fragment) {
        return this.J.f(fragment);
    }

    public void w(Fragment fragment) {
        if (f4686d) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (f4686d) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f4692j) {
                this.f4692j.remove(fragment);
            }
            if (D0(fragment)) {
                this.f4707y = true;
            }
            fragment.mAdded = false;
        }
    }

    public Fragment w0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.f4693k.get(string);
        if (fragment == null) {
            m1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void x() {
        this.f4708z = false;
        this.A = false;
        d0(2);
    }

    public LayoutInflater.Factory2 x0() {
        return this;
    }

    public void y(Configuration configuration) {
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public Fragment y0() {
        return this.f4706x;
    }

    public boolean z(MenuItem menuItem) {
        if (this.f4702t < 1) {
            return false;
        }
        for (int i3 = 0; i3 < this.f4692j.size(); i3++) {
            Fragment fragment = this.f4692j.get(i3);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public p z0(Fragment fragment) {
        return this.J.i(fragment);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f4724a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f4725b;

        public g(Animation animation) {
            this.f4724a = animation;
            this.f4725b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public g(Animator animator) {
            this.f4724a = null;
            this.f4725b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: renamed from: r0.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class RunnableC0057h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ViewGroup f4726b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final View f4727c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f4728d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f4729e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f4730f;

        public RunnableC0057h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f4730f = true;
            this.f4726b = viewGroup;
            this.f4727c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j3, Transformation transformation) {
            this.f4730f = true;
            if (this.f4728d) {
                return !this.f4729e;
            }
            if (!super.getTransformation(j3, transformation)) {
                this.f4728d = true;
                l0.q.a(this.f4726b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f4728d || !this.f4730f) {
                this.f4726b.endViewTransition(this.f4727c);
                this.f4729e = true;
            } else {
                this.f4730f = false;
                this.f4726b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j3, Transformation transformation, float f3) {
            this.f4730f = true;
            if (this.f4728d) {
                return !this.f4729e;
            }
            if (!super.getTransformation(j3, transformation, f3)) {
                this.f4728d = true;
                l0.q.a(this.f4726b, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
