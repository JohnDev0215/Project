package v0;

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
import v0.g;
import x0.p;
import x0.q;

/* JADX INFO: compiled from: FragmentManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends v0.g implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f8366f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Interpolator f8367g = new DecelerateInterpolator(2.5f);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Interpolator f8368h = new DecelerateInterpolator(1.5f);
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public ArrayList<v0.a> F;
    public ArrayList<Boolean> G;
    public ArrayList<Fragment> H;
    public ArrayList<l> K;
    public v0.i L;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<k> f8369i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8370j;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList<v0.a> f8374n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<Fragment> f8375o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public OnBackPressedDispatcher f8376p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList<v0.a> f8378r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ArrayList<Integer> f8379s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ArrayList<g.b> f8380t;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public v0.f f8383w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public v0.c f8384x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Fragment f8385y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Fragment f8386z;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8371k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList<Fragment> f8372l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap<String, Fragment> f8373m = new HashMap<>();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final k.b f8377q = new a(false);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList<i> f8381u = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f8382v = 0;
    public Bundle I = null;
    public SparseArray<Parcelable> J = null;
    public Runnable M = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class a extends k.b {
        public a(boolean z7) {
            super(z7);
        }

        @Override // k.b
        public void b() {
            h.this.H0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.r0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f8389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f8390b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f8390b.getAnimatingAway() != null) {
                    c.this.f8390b.setAnimatingAway(null);
                    c cVar = c.this;
                    h hVar = h.this;
                    Fragment fragment = cVar.f8390b;
                    hVar.V0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.f8389a = viewGroup;
            this.f8390b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f8389a.post(new a());
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f8393d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f8394e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Fragment f8395f;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f8393d = viewGroup;
            this.f8394e = view;
            this.f8395f = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f8393d.endViewTransition(this.f8394e);
            Animator animator2 = this.f8395f.getAnimator();
            this.f8395f.setAnimator(null);
            if (animator2 == null || this.f8393d.indexOfChild(this.f8394e) >= 0) {
                return;
            }
            h hVar = h.this;
            Fragment fragment = this.f8395f;
            hVar.V0(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f8397d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f8398e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Fragment f8399f;

        public e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f8397d = viewGroup;
            this.f8398e = view;
            this.f8399f = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f8397d.endViewTransition(this.f8398e);
            animator.removeListener(this);
            Fragment fragment = this.f8399f;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class f extends v0.e {
        public f() {
        }

        @Override // v0.e
        public Fragment a(ClassLoader classLoader, String str) {
            v0.f fVar = h.this.f8383w;
            return fVar.a(fVar.e(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g.a f8409a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f8410b;

        public i(g.a aVar, boolean z7) {
            this.f8409a = aVar;
            this.f8410b = z7;
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f8411a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public interface k {
        boolean a(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class l implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f8412a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final v0.a f8413b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f8414c;

        public l(v0.a aVar, boolean z7) {
            this.f8412a = z7;
            this.f8413b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            int i8 = this.f8414c - 1;
            this.f8414c = i8;
            if (i8 != 0) {
                return;
            }
            this.f8413b.f8354s.k1();
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            this.f8414c++;
        }

        public void c() {
            v0.a aVar = this.f8413b;
            aVar.f8354s.B(aVar, this.f8412a, false, false);
        }

        public void d() {
            boolean z7 = this.f8414c > 0;
            h hVar = this.f8413b.f8354s;
            int size = hVar.f8372l.size();
            for (int i8 = 0; i8 < size; i8++) {
                Fragment fragment = hVar.f8372l.get(i8);
                fragment.setOnStartEnterTransitionListener(null);
                if (z7 && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            v0.a aVar = this.f8413b;
            aVar.f8354s.B(aVar, this.f8412a, !z7, true);
        }

        public boolean e() {
            return this.f8414c == 0;
        }
    }

    public static g O0(float f8, float f9) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f8, f9);
        alphaAnimation.setInterpolator(f8368h);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    public static g Q0(float f8, float f9, float f10, float f11) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f8, f9, f8, f9, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f8367g);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f10, f11);
        alphaAnimation.setInterpolator(f8368h);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    public static int g1(int i8) {
        if (i8 == 4097) {
            return 8194;
        }
        if (i8 != 4099) {
            return i8 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public static int r1(int i8, boolean z7) {
        if (i8 == 4097) {
            return z7 ? 1 : 2;
        }
        if (i8 == 4099) {
            return z7 ? 5 : 6;
        }
        if (i8 != 8194) {
            return -1;
        }
        return z7 ? 3 : 4;
    }

    public static void t0(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, int i8, int i9) {
        while (i8 < i9) {
            v0.a aVar = arrayList.get(i8);
            if (arrayList2.get(i8).booleanValue()) {
                aVar.n(-1);
                aVar.s(i8 == i9 + (-1));
            } else {
                aVar.n(1);
                aVar.r();
            }
            i8++;
        }
    }

    public final void A() {
        this.f8370j = false;
        this.G.clear();
        this.F.clear();
    }

    public void A0(int i8) {
        synchronized (this) {
            this.f8378r.set(i8, null);
            if (this.f8379s == null) {
                this.f8379s = new ArrayList<>();
            }
            if (f8366f) {
                Log.v("FragmentManager", "Freeing back stack index " + i8);
            }
            this.f8379s.add(Integer.valueOf(i8));
        }
    }

    public void B(v0.a aVar, boolean z7, boolean z8, boolean z9) {
        if (z7) {
            aVar.s(z9);
        } else {
            aVar.r();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z7));
        if (z8) {
            v0.k.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z9) {
            T0(this.f8382v, true);
        }
        for (Fragment fragment : this.f8373m.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && aVar.v(fragment.mContainerId)) {
                float f8 = fragment.mPostponedAlpha;
                if (f8 > 0.0f) {
                    fragment.mView.setAlpha(f8);
                }
                if (z9) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public final boolean B0(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.f8369i;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.f8369i.size();
                boolean zA = false;
                for (int i8 = 0; i8 < size; i8++) {
                    zA |= this.f8369i.get(i8).a(arrayList, arrayList2);
                }
                this.f8369i.clear();
                this.f8383w.f().removeCallbacks(this.M);
                return zA;
            }
            return false;
        }
    }

    public void C(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            g gVarM0 = M0(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (gVarM0 == null || (animator = gVarM0.f8403b) == null) {
                if (gVarM0 != null) {
                    fragment.mView.startAnimation(gVarM0.f8402a);
                    gVarM0.f8402a.start();
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
                    gVarM0.f8403b.addListener(new e(viewGroup, view, fragment));
                }
                gVarM0.f8403b.start();
            }
        }
        if (fragment.mAdded && J0(fragment)) {
            this.A = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public int C0() {
        ArrayList<v0.a> arrayList = this.f8374n;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void D(Fragment fragment) {
        if (f8366f) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (f8366f) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f8372l) {
                this.f8372l.remove(fragment);
            }
            if (J0(fragment)) {
                this.A = true;
            }
            fragment.mAdded = false;
        }
    }

    public v0.i D0(Fragment fragment) {
        return this.L.f(fragment);
    }

    public void E() {
        this.B = false;
        this.C = false;
        k0(2);
    }

    public LayoutInflater.Factory2 E0() {
        return this;
    }

    public void F(Configuration configuration) {
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public Fragment F0() {
        return this.f8386z;
    }

    public boolean G(MenuItem menuItem) {
        if (this.f8382v < 1) {
            return false;
        }
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public p G0(Fragment fragment) {
        return this.L.i(fragment);
    }

    public void H() {
        this.B = false;
        this.C = false;
        k0(1);
    }

    public void H0() {
        r0();
        if (this.f8377q.c()) {
            j();
        } else {
            this.f8376p.c();
        }
    }

    public boolean I(Menu menu, MenuInflater menuInflater) {
        if (this.f8382v < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z7 = false;
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z7 = true;
            }
        }
        if (this.f8375o != null) {
            for (int i9 = 0; i9 < this.f8375o.size(); i9++) {
                Fragment fragment2 = this.f8375o.get(i9);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.f8375o = arrayList;
        return z7;
    }

    public void I0(Fragment fragment) {
        if (f8366f) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
    }

    public void J() {
        this.D = true;
        r0();
        k0(0);
        this.f8383w = null;
        this.f8384x = null;
        this.f8385y = null;
        if (this.f8376p != null) {
            this.f8377q.d();
            this.f8376p = null;
        }
    }

    public final boolean J0(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.y();
    }

    public void K() {
        k0(1);
    }

    public boolean K0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        h hVar = fragment.mFragmentManager;
        return fragment == hVar.F0() && K0(hVar.f8385y);
    }

    public void L() {
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public boolean L0(int i8) {
        return this.f8382v >= i8;
    }

    public void M(boolean z7) {
        for (int size = this.f8372l.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f8372l.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z7);
            }
        }
    }

    public g M0(Fragment fragment, int i8, boolean z7, int i9) {
        int iR1;
        int nextAnim = fragment.getNextAnim();
        boolean z8 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i8, z7, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new g(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i8, z7, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new g(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.f8383w.e().getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.f8383w.e(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z8 = true;
                } catch (Resources.NotFoundException e8) {
                    throw e8;
                } catch (RuntimeException unused) {
                }
            }
            if (!z8) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.f8383w.e(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e9) {
                    if (zEquals) {
                        throw e9;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.f8383w.e(), nextAnim);
                    if (animationLoadAnimation2 != null) {
                        return new g(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i8 == 0 || (iR1 = r1(i8, z7)) < 0) {
            return null;
        }
        switch (iR1) {
            case 1:
                return Q0(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return Q0(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return Q0(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return Q0(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return O0(0.0f, 1.0f);
            case 6:
                return O0(1.0f, 0.0f);
            default:
                if (i9 == 0 && this.f8383w.l()) {
                    i9 = this.f8383w.k();
                }
                if (i9 == 0) {
                }
                return null;
        }
    }

    public void N(Fragment fragment, Bundle bundle, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).N(fragment, bundle, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentActivityCreated(this, fragment, bundle);
            }
        }
    }

    public void N0(Fragment fragment) {
        if (this.f8373m.get(fragment.mWho) != null) {
            return;
        }
        this.f8373m.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                s(fragment);
            } else {
                d1(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (f8366f) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    public void O(Fragment fragment, Context context, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).O(fragment, context, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentAttached(this, fragment, context);
            }
        }
    }

    public void P(Fragment fragment, Bundle bundle, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).P(fragment, bundle, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentCreated(this, fragment, bundle);
            }
        }
    }

    public void P0(Fragment fragment) {
        if (this.f8373m.get(fragment.mWho) == null) {
            return;
        }
        if (f8366f) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.f8373m.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.f8373m.put(fragment.mWho, null);
        d1(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = this.f8373m.get(str);
        }
        fragment.initState();
    }

    public void Q(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).Q(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentDestroyed(this, fragment);
            }
        }
    }

    public void R(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).R(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentDetached(this, fragment);
            }
        }
    }

    public final void R0(v.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i8 = 0; i8 < size; i8++) {
            Fragment fragmentH = bVar.h(i8);
            if (!fragmentH.mAdded) {
                View viewRequireView = fragmentH.requireView();
                fragmentH.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    public void S(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).S(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentPaused(this, fragment);
            }
        }
    }

    public void S0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.f8373m.containsKey(fragment.mWho)) {
            if (f8366f) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.f8382v + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.f8382v;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        V0(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment fragmentY0 = y0(fragment);
            if (fragmentY0 != null) {
                View view = fragmentY0.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f8 = fragment.mPostponedAlpha;
                if (f8 > 0.0f) {
                    fragment.mView.setAlpha(f8);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                g gVarM0 = M0(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (gVarM0 != null) {
                    Animation animation = gVarM0.f8402a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        gVarM0.f8403b.setTarget(fragment.mView);
                        gVarM0.f8403b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            C(fragment);
        }
    }

    public void T(Fragment fragment, Context context, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).T(fragment, context, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentPreAttached(this, fragment, context);
            }
        }
    }

    public void T0(int i8, boolean z7) {
        v0.f fVar;
        if (this.f8383w == null && i8 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z7 || i8 != this.f8382v) {
            this.f8382v = i8;
            int size = this.f8372l.size();
            for (int i9 = 0; i9 < size; i9++) {
                S0(this.f8372l.get(i9));
            }
            for (Fragment fragment : this.f8373m.values()) {
                if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                    if (!fragment.mIsNewlyAdded) {
                        S0(fragment);
                    }
                }
            }
            p1();
            if (this.A && (fVar = this.f8383w) != null && this.f8382v == 4) {
                fVar.r();
                this.A = false;
            }
        }
    }

    public void U(Fragment fragment, Bundle bundle, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).U(fragment, bundle, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentPreCreated(this, fragment, bundle);
            }
        }
    }

    public void U0(Fragment fragment) {
        V0(fragment, this.f8382v, 0, 0, false);
    }

    public void V(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).V(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentResumed(this, fragment);
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
    public void V0(androidx.fragment.app.Fragment r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.h.V0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public void W(Fragment fragment, Bundle bundle, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).W(fragment, bundle, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentSaveInstanceState(this, fragment, bundle);
            }
        }
    }

    public void W0() {
        this.B = false;
        this.C = false;
        int size = this.f8372l.size();
        for (int i8 = 0; i8 < size; i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void X(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).X(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentStarted(this, fragment);
            }
        }
    }

    public void X0(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.f8370j) {
                this.E = true;
            } else {
                fragment.mDeferStart = false;
                V0(fragment, this.f8382v, 0, 0, false);
            }
        }
    }

    public void Y(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).Y(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentStopped(this, fragment);
            }
        }
    }

    public final boolean Y0(String str, int i8, int i9) {
        r0();
        p0(true);
        Fragment fragment = this.f8386z;
        if (fragment != null && i8 < 0 && str == null && fragment.getChildFragmentManager().j()) {
            return true;
        }
        boolean zZ0 = Z0(this.F, this.G, str, i8, i9);
        if (zZ0) {
            this.f8370j = true;
            try {
                c1(this.F, this.G);
            } finally {
                A();
            }
        }
        s1();
        m0();
        x();
        return zZ0;
    }

    public void Z(Fragment fragment, View view, Bundle bundle, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).Z(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentViewCreated(this, fragment, view, bundle);
            }
        }
    }

    public boolean Z0(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i8, int i9) {
        int i10;
        ArrayList<v0.a> arrayList3 = this.f8374n;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i8 < 0 && (i9 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f8374n.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i8 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    v0.a aVar = this.f8374n.get(size2);
                    if ((str != null && str.equals(aVar.u())) || (i8 >= 0 && i8 == aVar.f8356u)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i9 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        v0.a aVar2 = this.f8374n.get(size2);
                        if (str == null || !str.equals(aVar2.u())) {
                            if (i8 < 0 || i8 != aVar2.f8356u) {
                                break;
                            }
                        }
                    }
                }
                i10 = size2;
            } else {
                i10 = -1;
            }
            if (i10 == this.f8374n.size() - 1) {
                return false;
            }
            for (int size3 = this.f8374n.size() - 1; size3 > i10; size3--) {
                arrayList.add(this.f8374n.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    @Override // v0.g
    public v0.j a() {
        return new v0.a(this);
    }

    public void a0(Fragment fragment, boolean z7) {
        Fragment fragment2 = this.f8385y;
        if (fragment2 != null) {
            v0.g fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof h) {
                ((h) fragmentManager).a0(fragment, true);
            }
        }
        for (i iVar : this.f8381u) {
            if (!z7 || iVar.f8410b) {
                iVar.f8409a.onFragmentViewDestroyed(this, fragment);
            }
        }
    }

    public final int a1(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, int i8, int i9, v.b<Fragment> bVar) {
        int i10 = i9;
        for (int i11 = i9 - 1; i11 >= i8; i11--) {
            v0.a aVar = arrayList.get(i11);
            boolean zBooleanValue = arrayList2.get(i11).booleanValue();
            if (aVar.y() && !aVar.w(arrayList, i11 + 1, i9)) {
                if (this.K == null) {
                    this.K = new ArrayList<>();
                }
                l lVar = new l(aVar, zBooleanValue);
                this.K.add(lVar);
                aVar.A(lVar);
                if (zBooleanValue) {
                    aVar.r();
                } else {
                    aVar.s(false);
                }
                i10--;
                if (i11 != i10) {
                    arrayList.remove(i11);
                    arrayList.add(i10, aVar);
                }
                p(bVar);
            }
        }
        return i10;
    }

    @Override // v0.g
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.f8373m.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.f8373m.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f8372l.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i8 = 0; i8 < size5; i8++) {
                Fragment fragment2 = this.f8372l.get(i8);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.f8375o;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i9 = 0; i9 < size4; i9++) {
                Fragment fragment3 = this.f8375o.get(i9);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i9);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<v0.a> arrayList2 = this.f8374n;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i10 = 0; i10 < size3; i10++) {
                v0.a aVar = this.f8374n.get(i10);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i10);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.p(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList<v0.a> arrayList3 = this.f8378r;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i11 = 0; i11 < size2; i11++) {
                    Object obj = (v0.a) this.f8378r.get(i11);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i11);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.f8379s;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f8379s.toArray()));
            }
        }
        ArrayList<k> arrayList5 = this.f8369i;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i12 = 0; i12 < size; i12++) {
                Object obj2 = (k) this.f8369i.get(i12);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i12);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f8383w);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f8384x);
        if (this.f8385y != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f8385y);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f8382v);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.B);
        printWriter.print(" mStopped=");
        printWriter.print(this.C);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.D);
        if (this.A) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.A);
        }
    }

    public boolean b0(MenuItem menuItem) {
        if (this.f8382v < 1) {
            return false;
        }
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void b1(Fragment fragment) {
        if (f8366f) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z7 = !fragment.isInBackStack();
        if (!fragment.mDetached || z7) {
            synchronized (this.f8372l) {
                this.f8372l.remove(fragment);
            }
            if (J0(fragment)) {
                this.A = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    @Override // v0.g
    public boolean c() {
        boolean zR0 = r0();
        z0();
        return zR0;
    }

    public void c0(Menu menu) {
        if (this.f8382v < 1) {
            return;
        }
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public final void c1(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        v0(arrayList, arrayList2);
        int size = arrayList.size();
        int i8 = 0;
        int i9 = 0;
        while (i8 < size) {
            if (!arrayList.get(i8).f8438q) {
                if (i9 != i8) {
                    u0(arrayList, arrayList2, i9, i8);
                }
                i9 = i8 + 1;
                if (arrayList2.get(i8).booleanValue()) {
                    while (i9 < size && arrayList2.get(i9).booleanValue() && !arrayList.get(i9).f8438q) {
                        i9++;
                    }
                }
                u0(arrayList, arrayList2, i8, i9);
                i8 = i9 - 1;
            }
            i8++;
        }
        if (i9 != size) {
            u0(arrayList, arrayList2, i9, size);
        }
    }

    @Override // v0.g
    public Fragment d(String str) {
        if (str != null) {
            for (int size = this.f8372l.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f8372l.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.f8373m.values()) {
            if (fragment2 != null && str.equals(fragment2.mTag)) {
                return fragment2;
            }
        }
        return null;
    }

    public final void d0(Fragment fragment) {
        if (fragment == null || this.f8373m.get(fragment.mWho) != fragment) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    public void d1(Fragment fragment) {
        if (i()) {
            if (f8366f) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.L.k(fragment) && f8366f) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // v0.g
    public Fragment e(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.f8373m.get(string);
        if (fragment == null) {
            q1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void e0() {
        k0(3);
    }

    public void e1() {
        if (this.f8380t != null) {
            for (int i8 = 0; i8 < this.f8380t.size(); i8++) {
                this.f8380t.get(i8).a();
            }
        }
    }

    @Override // v0.g
    public v0.e f() {
        if (super.f() == v0.g.f8364d) {
            Fragment fragment = this.f8385y;
            if (fragment != null) {
                return fragment.mFragmentManager.f();
            }
            n(new f());
        }
        return super.f();
    }

    public void f0(boolean z7) {
        for (int size = this.f8372l.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f8372l.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z7);
            }
        }
    }

    public void f1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1400d == null) {
            return;
        }
        for (Fragment fragment : this.L.h()) {
            if (f8366f) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f1400d.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f1406e.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f8366f) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f1400d);
                }
                V0(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                V0(fragment, 0, 0, 0, false);
            } else {
                next.f1418q = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
                fragment.mTarget = null;
                Bundle bundle = next.f1417p;
                if (bundle != null) {
                    bundle.setClassLoader(this.f8383w.e().getClassLoader());
                    fragment.mSavedViewState = next.f1417p.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = next.f1417p;
                }
            }
        }
        this.f8373m.clear();
        for (FragmentState fragmentState : fragmentManagerState.f1400d) {
            if (fragmentState != null) {
                Fragment fragmentF = fragmentState.f(this.f8383w.e().getClassLoader(), f());
                fragmentF.mFragmentManager = this;
                if (f8366f) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentF.mWho + "): " + fragmentF);
                }
                this.f8373m.put(fragmentF.mWho, fragmentF);
                fragmentState.f1418q = null;
            }
        }
        this.f8372l.clear();
        ArrayList<String> arrayList = fragmentManagerState.f1401e;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.f8373m.get(str);
                if (fragment3 == null) {
                    q1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.mAdded = true;
                if (f8366f) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                }
                if (this.f8372l.contains(fragment3)) {
                    throw new IllegalStateException("Already added " + fragment3);
                }
                synchronized (this.f8372l) {
                    this.f8372l.add(fragment3);
                }
            }
        }
        if (fragmentManagerState.f1402f != null) {
            this.f8374n = new ArrayList<>(fragmentManagerState.f1402f.length);
            int i8 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f1402f;
                if (i8 >= backStackStateArr.length) {
                    break;
                }
                v0.a aVarF = backStackStateArr[i8].f(this);
                if (f8366f) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i8 + " (index " + aVarF.f8356u + "): " + aVarF);
                    PrintWriter printWriter = new PrintWriter(new n0.b("FragmentManager"));
                    aVarF.q("  ", printWriter, false);
                    printWriter.close();
                }
                this.f8374n.add(aVarF);
                int i9 = aVarF.f8356u;
                if (i9 >= 0) {
                    l1(i9, aVarF);
                }
                i8++;
            }
        } else {
            this.f8374n = null;
        }
        String str2 = fragmentManagerState.f1403g;
        if (str2 != null) {
            Fragment fragment4 = this.f8373m.get(str2);
            this.f8386z = fragment4;
            d0(fragment4);
        }
        this.f8371k = fragmentManagerState.f1404h;
    }

    @Override // v0.g
    public List<Fragment> g() {
        List<Fragment> list;
        if (this.f8372l.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f8372l) {
            list = (List) this.f8372l.clone();
        }
        return list;
    }

    public boolean g0(Menu menu) {
        if (this.f8382v < 1) {
            return false;
        }
        boolean z7 = false;
        for (int i8 = 0; i8 < this.f8372l.size(); i8++) {
            Fragment fragment = this.f8372l.get(i8);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z7 = true;
            }
        }
        return z7;
    }

    @Override // v0.g
    public boolean h() {
        return this.D;
    }

    public void h0() {
        s1();
        d0(this.f8386z);
    }

    public Parcelable h1() {
        ArrayList<String> arrayList;
        int size;
        z0();
        n0();
        r0();
        this.B = true;
        BackStackState[] backStackStateArr = null;
        if (this.f8373m.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.f8373m.size());
        boolean z7 = false;
        for (Fragment fragment : this.f8373m.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    q1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.mState <= 0 || fragmentState.f1417p != null) {
                    fragmentState.f1417p = fragment.mSavedFragmentState;
                } else {
                    fragmentState.f1417p = i1(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = this.f8373m.get(str);
                        if (fragment2 == null) {
                            q1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                        }
                        if (fragmentState.f1417p == null) {
                            fragmentState.f1417p = new Bundle();
                        }
                        k(fragmentState.f1417p, "android:target_state", fragment2);
                        int i8 = fragment.mTargetRequestCode;
                        if (i8 != 0) {
                            fragmentState.f1417p.putInt("android:target_req_state", i8);
                        }
                    }
                }
                if (f8366f) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f1417p);
                }
                z7 = true;
            }
        }
        if (!z7) {
            if (f8366f) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f8372l.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.f8372l) {
                arrayList.add(fragment3.mWho);
                if (fragment3.mFragmentManager != this) {
                    q1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (f8366f) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.mWho + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<v0.a> arrayList3 = this.f8374n;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i9 = 0; i9 < size; i9++) {
                backStackStateArr[i9] = new BackStackState(this.f8374n.get(i9));
                if (f8366f) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i9 + ": " + this.f8374n.get(i9));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1400d = arrayList2;
        fragmentManagerState.f1401e = arrayList;
        fragmentManagerState.f1402f = backStackStateArr;
        Fragment fragment4 = this.f8386z;
        if (fragment4 != null) {
            fragmentManagerState.f1403g = fragment4.mWho;
        }
        fragmentManagerState.f1404h = this.f8371k;
        return fragmentManagerState;
    }

    @Override // v0.g
    public boolean i() {
        return this.B || this.C;
    }

    public void i0() {
        this.B = false;
        this.C = false;
        k0(4);
    }

    public Bundle i1(Fragment fragment) {
        if (this.I == null) {
            this.I = new Bundle();
        }
        fragment.performSaveInstanceState(this.I);
        W(fragment, this.I, false);
        Bundle bundle = null;
        if (!this.I.isEmpty()) {
            Bundle bundle2 = this.I;
            this.I = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            j1(fragment);
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

    @Override // v0.g
    public boolean j() {
        z();
        return Y0(null, -1, 0);
    }

    public void j0() {
        this.B = false;
        this.C = false;
        k0(3);
    }

    public void j1(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.J;
        if (sparseArray == null) {
            this.J = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.J);
        if (this.J.size() > 0) {
            fragment.mSavedViewState = this.J;
            this.J = null;
        }
    }

    @Override // v0.g
    public void k(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager != this) {
            q1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.mWho);
    }

    public final void k0(int i8) {
        try {
            this.f8370j = true;
            T0(i8, false);
            this.f8370j = false;
            r0();
        } catch (Throwable th) {
            this.f8370j = false;
            throw th;
        }
    }

    public void k1() {
        synchronized (this) {
            ArrayList<l> arrayList = this.K;
            boolean z7 = false;
            boolean z8 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.f8369i;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z7 = true;
            }
            if (z8 || z7) {
                this.f8383w.f().removeCallbacks(this.M);
                this.f8383w.f().post(this.M);
                s1();
            }
        }
    }

    @Override // v0.g
    public void l(g.a aVar, boolean z7) {
        this.f8381u.add(new i(aVar, z7));
    }

    public void l0() {
        this.C = true;
        k0(2);
    }

    public void l1(int i8, v0.a aVar) {
        synchronized (this) {
            if (this.f8378r == null) {
                this.f8378r = new ArrayList<>();
            }
            int size = this.f8378r.size();
            if (i8 < size) {
                if (f8366f) {
                    Log.v("FragmentManager", "Setting back stack index " + i8 + " to " + aVar);
                }
                this.f8378r.set(i8, aVar);
            } else {
                while (size < i8) {
                    this.f8378r.add(null);
                    if (this.f8379s == null) {
                        this.f8379s = new ArrayList<>();
                    }
                    if (f8366f) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f8379s.add(Integer.valueOf(size));
                    size++;
                }
                if (f8366f) {
                    Log.v("FragmentManager", "Adding back stack index " + i8 + " with " + aVar);
                }
                this.f8378r.add(aVar);
            }
        }
    }

    @Override // v0.g
    public Fragment.SavedState m(Fragment fragment) {
        Bundle bundleI1;
        if (fragment.mFragmentManager != this) {
            q1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0 || (bundleI1 = i1(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleI1);
    }

    public void m0() {
        if (this.E) {
            this.E = false;
            p1();
        }
    }

    public void m1(Fragment fragment, Lifecycle.State state) {
        if (this.f8373m.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void n0() {
        for (Fragment fragment : this.f8373m.values()) {
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
                    V0(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    public void n1(Fragment fragment) {
        if (fragment == null || (this.f8373m.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            Fragment fragment2 = this.f8386z;
            this.f8386z = fragment;
            d0(fragment2);
            d0(this.f8386z);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    @Override // v0.g
    public void o(g.a aVar) {
        synchronized (this.f8381u) {
            int i8 = 0;
            int size = this.f8381u.size();
            while (true) {
                if (i8 >= size) {
                    break;
                }
                if (this.f8381u.get(i8).f8409a == aVar) {
                    this.f8381u.remove(i8);
                    break;
                }
                i8++;
            }
        }
    }

    public void o0(k kVar, boolean z7) {
        if (!z7) {
            z();
        }
        synchronized (this) {
            if (!this.D && this.f8383w != null) {
                if (this.f8369i == null) {
                    this.f8369i = new ArrayList<>();
                }
                this.f8369i.add(kVar);
                k1();
                return;
            }
            if (!z7) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void o1(Fragment fragment) {
        if (f8366f) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f8411a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !v0.e.b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentW0 = resourceId != -1 ? w0(resourceId) : null;
        if (fragmentW0 == null && string != null) {
            fragmentW0 = d(string);
        }
        if (fragmentW0 == null && id != -1) {
            fragmentW0 = w0(id);
        }
        if (f8366f) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentW0);
        }
        if (fragmentW0 == null) {
            fragmentW0 = f().a(context.getClassLoader(), str2);
            fragmentW0.mFromLayout = true;
            fragmentW0.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentW0.mContainerId = id;
            fragmentW0.mTag = string;
            fragmentW0.mInLayout = true;
            fragmentW0.mFragmentManager = this;
            v0.f fVar = this.f8383w;
            fragmentW0.mHost = fVar;
            fragmentW0.onInflate(fVar.e(), attributeSet, fragmentW0.mSavedFragmentState);
            r(fragmentW0, true);
        } else {
            if (fragmentW0.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentW0.mInLayout = true;
            v0.f fVar2 = this.f8383w;
            fragmentW0.mHost = fVar2;
            fragmentW0.onInflate(fVar2.e(), attributeSet, fragmentW0.mSavedFragmentState);
        }
        Fragment fragment = fragmentW0;
        if (this.f8382v >= 1 || !fragment.mFromLayout) {
            U0(fragment);
        } else {
            V0(fragment, 1, 0, 0, false);
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

    public final void p(v.b<Fragment> bVar) {
        int i8 = this.f8382v;
        if (i8 < 1) {
            return;
        }
        int iMin = Math.min(i8, 3);
        int size = this.f8372l.size();
        for (int i9 = 0; i9 < size; i9++) {
            Fragment fragment = this.f8372l.get(i9);
            if (fragment.mState < iMin) {
                V0(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    bVar.add(fragment);
                }
            }
        }
    }

    public final void p0(boolean z7) {
        if (this.f8370j) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f8383w == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.f8383w.f().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z7) {
            z();
        }
        if (this.F == null) {
            this.F = new ArrayList<>();
            this.G = new ArrayList<>();
        }
        this.f8370j = true;
        try {
            v0(null, null);
        } finally {
            this.f8370j = false;
        }
    }

    public void p1() {
        for (Fragment fragment : this.f8373m.values()) {
            if (fragment != null) {
                X0(fragment);
            }
        }
    }

    public void q(v0.a aVar) {
        if (this.f8374n == null) {
            this.f8374n = new ArrayList<>();
        }
        this.f8374n.add(aVar);
    }

    public void q0(Fragment fragment) {
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
        Z(fragment, fragment.mView, fragment.mSavedFragmentState, false);
    }

    public final void q1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new n0.b("FragmentManager"));
        v0.f fVar = this.f8383w;
        if (fVar != null) {
            try {
                fVar.h("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e8) {
                Log.e("FragmentManager", "Failed dumping state", e8);
                throw runtimeException;
            }
        }
        try {
            b("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e9) {
            Log.e("FragmentManager", "Failed dumping state", e9);
            throw runtimeException;
        }
    }

    public void r(Fragment fragment, boolean z7) {
        if (f8366f) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        N0(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.f8372l.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f8372l) {
            this.f8372l.add(fragment);
        }
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (J0(fragment)) {
            this.A = true;
        }
        if (z7) {
            U0(fragment);
        }
    }

    public boolean r0() {
        p0(true);
        boolean z7 = false;
        while (B0(this.F, this.G)) {
            this.f8370j = true;
            try {
                c1(this.F, this.G);
                A();
                z7 = true;
            } catch (Throwable th) {
                A();
                throw th;
            }
        }
        s1();
        m0();
        x();
        return z7;
    }

    public void s(Fragment fragment) {
        if (i()) {
            if (f8366f) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.L.d(fragment) && f8366f) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    public void s0(k kVar, boolean z7) {
        if (z7 && (this.f8383w == null || this.D)) {
            return;
        }
        p0(z7);
        if (kVar.a(this.F, this.G)) {
            this.f8370j = true;
            try {
                c1(this.F, this.G);
            } finally {
                A();
            }
        }
        s1();
        m0();
        x();
    }

    public final void s1() {
        ArrayList<k> arrayList = this.f8369i;
        if (arrayList == null || arrayList.isEmpty()) {
            this.f8377q.f(C0() > 0 && K0(this.f8385y));
        } else {
            this.f8377q.f(true);
        }
    }

    public int t(v0.a aVar) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.f8379s;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.f8379s.remove(r0.size() - 1).intValue();
                if (f8366f) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.f8378r.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.f8378r == null) {
                this.f8378r = new ArrayList<>();
            }
            int size = this.f8378r.size();
            if (f8366f) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.f8378r.add(aVar);
            return size;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f8385y;
        if (fragment != null) {
            n0.a.a(fragment, sb);
        } else {
            n0.a.a(this.f8383w, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(Fragment fragment, g gVar, int i8) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i8);
        Animation animation = gVar.f8402a;
        if (animation != null) {
            RunnableC0133h runnableC0133h = new RunnableC0133h(animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            runnableC0133h.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(runnableC0133h);
            return;
        }
        Animator animator = gVar.f8403b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public final void u0(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2, int i8, int i9) {
        int i10;
        int i11;
        int i12 = i8;
        boolean z7 = arrayList.get(i12).f8438q;
        ArrayList<Fragment> arrayList3 = this.H;
        if (arrayList3 == null) {
            this.H = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.H.addAll(this.f8372l);
        Fragment fragmentF0 = F0();
        boolean z8 = false;
        for (int i13 = i12; i13 < i9; i13++) {
            v0.a aVar = arrayList.get(i13);
            fragmentF0 = !arrayList2.get(i13).booleanValue() ? aVar.t(this.H, fragmentF0) : aVar.B(this.H, fragmentF0);
            z8 = z8 || aVar.f8429h;
        }
        this.H.clear();
        if (!z7) {
            v0.k.B(this, arrayList, arrayList2, i8, i9, false);
        }
        t0(arrayList, arrayList2, i8, i9);
        if (z7) {
            v.b<Fragment> bVar = new v.b<>();
            p(bVar);
            int iA1 = a1(arrayList, arrayList2, i8, i9, bVar);
            R0(bVar);
            i10 = iA1;
        } else {
            i10 = i9;
        }
        if (i10 != i12 && z7) {
            v0.k.B(this, arrayList, arrayList2, i8, i10, true);
            T0(this.f8382v, true);
        }
        while (i12 < i9) {
            v0.a aVar2 = arrayList.get(i12);
            if (arrayList2.get(i12).booleanValue() && (i11 = aVar2.f8356u) >= 0) {
                A0(i11);
                aVar2.f8356u = -1;
            }
            aVar2.z();
            i12++;
        }
        if (z8) {
            e1();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void v(v0.f fVar, v0.c cVar, Fragment fragment) {
        if (this.f8383w != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f8383w = fVar;
        this.f8384x = cVar;
        this.f8385y = fragment;
        if (fragment != null) {
            s1();
        }
        if (fVar instanceof k.c) {
            k.c cVar2 = (k.c) fVar;
            OnBackPressedDispatcher onBackPressedDispatcher = cVar2.getOnBackPressedDispatcher();
            this.f8376p = onBackPressedDispatcher;
            x0.f fVar2 = cVar2;
            if (fragment != null) {
                fVar2 = fragment;
            }
            onBackPressedDispatcher.a(fVar2, this.f8377q);
        }
        if (fragment != null) {
            this.L = fragment.mFragmentManager.D0(fragment);
        } else if (fVar instanceof q) {
            this.L = v0.i.g(((q) fVar).getViewModelStore());
        } else {
            this.L = new v0.i(false);
        }
    }

    public final void v0(ArrayList<v0.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<l> arrayList3 = this.K;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i8 = 0;
        while (i8 < size) {
            l lVar = this.K.get(i8);
            if (arrayList != null && !lVar.f8412a && (iIndexOf2 = arrayList.indexOf(lVar.f8413b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.K.remove(i8);
                i8--;
                size--;
                lVar.c();
            } else if (lVar.e() || (arrayList != null && lVar.f8413b.w(arrayList, 0, arrayList.size()))) {
                this.K.remove(i8);
                i8--;
                size--;
                if (arrayList == null || lVar.f8412a || (iIndexOf = arrayList.indexOf(lVar.f8413b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    lVar.d();
                } else {
                    lVar.c();
                }
            }
            i8++;
        }
    }

    public void w(Fragment fragment) {
        if (f8366f) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.f8372l.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (f8366f) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.f8372l) {
                this.f8372l.add(fragment);
            }
            fragment.mAdded = true;
            if (J0(fragment)) {
                this.A = true;
            }
        }
    }

    public Fragment w0(int i8) {
        for (int size = this.f8372l.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f8372l.get(size);
            if (fragment != null && fragment.mFragmentId == i8) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.f8373m.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i8) {
                return fragment2;
            }
        }
        return null;
    }

    public final void x() {
        this.f8373m.values().removeAll(Collections.singleton(null));
    }

    public Fragment x0(String str) {
        Fragment fragmentFindFragmentByWho;
        for (Fragment fragment : this.f8373m.values()) {
            if (fragment != null && (fragmentFindFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    public boolean y() {
        boolean zJ0 = false;
        for (Fragment fragment : this.f8373m.values()) {
            if (fragment != null) {
                zJ0 = J0(fragment);
            }
            if (zJ0) {
                return true;
            }
        }
        return false;
    }

    public final Fragment y0(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f8372l.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f8372l.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    public final void z() {
        if (i()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public final void z0() {
        if (this.K != null) {
            while (!this.K.isEmpty()) {
                this.K.remove(0).d();
            }
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f8402a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f8403b;

        public g(Animation animation) {
            this.f8402a = animation;
            this.f8403b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public g(Animator animator) {
            this.f8402a = null;
            this.f8403b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: renamed from: v0.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class RunnableC0133h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ViewGroup f8404d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final View f8405e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f8406f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f8407g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f8408h;

        public RunnableC0133h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f8408h = true;
            this.f8404d = viewGroup;
            this.f8405e = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j8, Transformation transformation) {
            this.f8408h = true;
            if (this.f8406f) {
                return !this.f8407g;
            }
            if (!super.getTransformation(j8, transformation)) {
                this.f8406f = true;
                o0.q.a(this.f8404d, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f8406f || !this.f8408h) {
                this.f8404d.endViewTransition(this.f8405e);
                this.f8407g = true;
            } else {
                this.f8408h = false;
                this.f8404d.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j8, Transformation transformation, float f8) {
            this.f8408h = true;
            if (this.f8406f) {
                return !this.f8407g;
            }
            if (!super.getTransformation(j8, transformation, f8)) {
                this.f8406f = true;
                o0.q.a(this.f8404d, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
