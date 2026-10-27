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
    static boolean f985d = false;
    static final Interpolator e = new DecelerateInterpolator(2.5f);
    static final Interpolator f = new DecelerateInterpolator(1.5f);
    boolean A;
    boolean B;
    boolean C;
    ArrayList<androidx.fragment.app.a> D;
    ArrayList<Boolean> E;
    ArrayList<Fragment> F;
    ArrayList<m> I;
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
            h.this.B0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.l0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f988a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f989b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f989b.l() != null) {
                    c.this.f989b.e1(null);
                    c cVar = c.this;
                    h hVar = h.this;
                    Fragment fragment = cVar.f989b;
                    hVar.R0(fragment, fragment.H(), 0, 0, false);
                }
            }
        }

        c(ViewGroup viewGroup, Fragment fragment) {
            this.f988a = viewGroup;
            this.f989b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f988a.post(new a());
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
        final /* synthetic */ ViewGroup f992a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f993b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f994c;

        d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f992a = viewGroup;
            this.f993b = view;
            this.f994c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f992a.endViewTransition(this.f993b);
            Animator animatorM = this.f994c.m();
            this.f994c.f1(null);
            if (animatorM == null || this.f992a.indexOfChild(this.f993b) >= 0) {
                return;
            }
            h hVar = h.this;
            Fragment fragment = this.f994c;
            hVar.R0(fragment, fragment.H(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f997b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f998c;

        e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f996a = viewGroup;
            this.f997b = view;
            this.f998c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f996a.endViewTransition(this.f997b);
            animator.removeListener(this);
            Fragment fragment = this.f998c;
            View view = fragment.I;
            if (view == null || !fragment.A) {
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
            return fVar.b(fVar.i(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g.a f1006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1007b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1008a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    interface k {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private class l implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1009a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1010b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f1011c;

        l(String str, int i, int i2) {
            this.f1009a = str;
            this.f1010b = i;
            this.f1011c = i2;
        }

        @Override // androidx.fragment.app.h.k
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = h.this.x;
            if (fragment == null || this.f1010b >= 0 || this.f1009a != null || !fragment.o().h()) {
                return h.this.V0(arrayList, arrayList2, this.f1009a, this.f1010b, this.f1011c);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class m implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f1013a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f1014b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1015c;

        m(androidx.fragment.app.a aVar, boolean z) {
            this.f1013a = z;
            this.f1014b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            int i = this.f1015c - 1;
            this.f1015c = i;
            if (i != 0) {
                return;
            }
            this.f1014b.s.h1();
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            this.f1015c++;
        }

        public void c() {
            androidx.fragment.app.a aVar = this.f1014b;
            aVar.s.v(aVar, this.f1013a, false, false);
        }

        public void d() {
            boolean z = this.f1015c > 0;
            h hVar = this.f1014b.s;
            int size = hVar.j.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = hVar.j.get(i);
                fragment.k1(null);
                if (z && fragment.R()) {
                    fragment.m1();
                }
            }
            androidx.fragment.app.a aVar = this.f1014b;
            aVar.s.v(aVar, this.f1013a, !z, true);
        }

        public boolean e() {
            return this.f1015c == 0;
        }
    }

    h() {
    }

    private boolean E0(Fragment fragment) {
        return (fragment.E && fragment.F) || fragment.v.s();
    }

    static g K0(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(f);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    static g M0(float f2, float f3, float f4, float f5) {
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

    private void N0(b.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentI = bVar.i(i2);
            if (!fragmentI.m) {
                View viewB1 = fragmentI.b1();
                fragmentI.Q = viewB1.getAlpha();
                viewB1.setAlpha(0.0f);
            }
        }
    }

    private boolean U0(String str, int i2, int i3) {
        l0();
        j0(true);
        Fragment fragment = this.x;
        if (fragment != null && i2 < 0 && str == null && fragment.o().h()) {
            return true;
        }
        boolean zV0 = V0(this.D, this.E, str, i2, i3);
        if (zV0) {
            this.h = true;
            try {
                Z0(this.D, this.E);
            } finally {
                u();
            }
        }
        p1();
        g0();
        r();
        return zV0;
    }

    private int W0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, b.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.v() && !aVar.t(arrayList, i5 + 1, i3)) {
                if (this.I == null) {
                    this.I = new ArrayList<>();
                }
                m mVar = new m(aVar, zBooleanValue);
                this.I.add(mVar);
                aVar.x(mVar);
                if (zBooleanValue) {
                    aVar.o();
                } else {
                    aVar.p(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                j(bVar);
            }
        }
        return i4;
    }

    private void X(Fragment fragment) {
        if (fragment == null || this.k.get(fragment.g) != fragment) {
            return;
        }
        fragment.T0();
    }

    private void Z0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        o0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).q) {
                if (i3 != i2) {
                    n0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).q) {
                        i3++;
                    }
                }
                n0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            n0(arrayList, arrayList2, i3, size);
        }
    }

    public static int d1(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private void e0(int i2) {
        try {
            this.h = true;
            P0(i2, false);
            this.h = false;
            l0();
        } catch (Throwable th) {
            this.h = false;
            throw th;
        }
    }

    private void h0() {
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                if (fragment.l() != null) {
                    int iH = fragment.H();
                    View viewL = fragment.l();
                    Animation animation = viewL.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        viewL.clearAnimation();
                    }
                    fragment.e1(null);
                    R0(fragment, iH, 0, 0, false);
                } else if (fragment.m() != null) {
                    fragment.m().end();
                }
            }
        }
    }

    private void j(b.d.b<Fragment> bVar) {
        int i2 = this.t;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.j.get(i3);
            if (fragment.f952c < iMin) {
                R0(fragment, iMin, fragment.x(), fragment.y(), false);
                if (fragment.I != null && !fragment.A && fragment.O) {
                    bVar.add(fragment);
                }
            }
        }
    }

    private void j0(boolean z) {
        if (this.h) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.u == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.u.j().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            t();
        }
        if (this.D == null) {
            this.D = new ArrayList<>();
            this.E = new ArrayList<>();
        }
        this.h = true;
        try {
            o0(null, null);
        } finally {
            this.h = false;
        }
    }

    private static void m0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.k(-1);
                aVar.p(i2 == i3 + (-1));
            } else {
                aVar.k(1);
                aVar.o();
            }
            i2++;
        }
    }

    private void n0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
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
        Fragment fragmentZ0 = z0();
        boolean z2 = false;
        for (int i7 = i6; i7 < i3; i7++) {
            androidx.fragment.app.a aVar = arrayList.get(i7);
            fragmentZ0 = !arrayList2.get(i7).booleanValue() ? aVar.q(this.F, fragmentZ0) : aVar.y(this.F, fragmentZ0);
            z2 = z2 || aVar.h;
        }
        this.F.clear();
        if (!z) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, i2, i3, false);
        }
        m0(arrayList, arrayList2, i2, i3);
        if (z) {
            b.d.b<Fragment> bVar = new b.d.b<>();
            j(bVar);
            int iW0 = W0(arrayList, arrayList2, i2, i3, bVar);
            N0(bVar);
            i4 = iW0;
        } else {
            i4 = i3;
        }
        if (i4 != i6 && z) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, i2, i4, true);
            P0(this.t, true);
        }
        while (i6 < i3) {
            androidx.fragment.app.a aVar2 = arrayList.get(i6);
            if (arrayList2.get(i6).booleanValue() && (i5 = aVar2.u) >= 0) {
                t0(i5);
                aVar2.u = -1;
            }
            aVar2.w();
            i6++;
        }
        if (z2) {
            b1();
        }
    }

    private void n1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
        androidx.fragment.app.f fVar = this.u;
        if (fVar != null) {
            try {
                fVar.l("  ", null, printWriter, new String[0]);
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

    private void o(Fragment fragment, g gVar, int i2) {
        View view = fragment.I;
        ViewGroup viewGroup = fragment.H;
        viewGroup.startViewTransition(view);
        fragment.l1(i2);
        Animation animation = gVar.f1001a;
        if (animation != null) {
            RunnableC0018h runnableC0018h = new RunnableC0018h(animation, viewGroup, view);
            fragment.e1(fragment.I);
            runnableC0018h.setAnimationListener(new c(viewGroup, fragment));
            fragment.I.startAnimation(runnableC0018h);
            return;
        }
        Animator animator = gVar.f1002b;
        fragment.f1(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.I);
        animator.start();
    }

    private void o0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<m> arrayList3 = this.I;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            m mVar = this.I.get(i2);
            if (arrayList != null && !mVar.f1013a && (iIndexOf2 = arrayList.indexOf(mVar.f1014b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.I.remove(i2);
                i2--;
                size--;
                mVar.c();
            } else if (mVar.e() || (arrayList != null && mVar.f1014b.t(arrayList, 0, arrayList.size()))) {
                this.I.remove(i2);
                i2--;
                size--;
                if (arrayList == null || mVar.f1013a || (iIndexOf = arrayList.indexOf(mVar.f1014b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    mVar.d();
                } else {
                    mVar.c();
                }
            }
            i2++;
        }
    }

    public static int o1(int i2, boolean z) {
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

    private void p1() {
        ArrayList<k> arrayList = this.g;
        if (arrayList == null || arrayList.isEmpty()) {
            this.o.f(v0() > 0 && F0(this.w));
        } else {
            this.o.f(true);
        }
    }

    private void r() {
        this.k.values().removeAll(Collections.singleton(null));
    }

    private Fragment r0(Fragment fragment) {
        ViewGroup viewGroup = fragment.H;
        View view = fragment.I;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.j.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.j.get(iIndexOf);
                if (fragment2.H == viewGroup && fragment2.I != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    private void s0() {
        if (this.I != null) {
            while (!this.I.isEmpty()) {
                this.I.remove(0).d();
            }
        }
    }

    private void t() {
        if (H0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void u() {
        this.h = false;
        this.E.clear();
        this.D.clear();
    }

    private boolean u0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.g;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.g.size();
                boolean zA = false;
                for (int i2 = 0; i2 < size; i2++) {
                    zA |= this.g.get(i2).a(arrayList, arrayList2);
                }
                this.g.clear();
                this.u.j().removeCallbacks(this.K);
                return zA;
            }
            return false;
        }
    }

    public boolean A(MenuItem menuItem) {
        if (this.t < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.E0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    p A0(Fragment fragment) {
        return this.J.i(fragment);
    }

    public void B() {
        this.z = false;
        this.A = false;
        e0(1);
    }

    void B0() {
        l0();
        if (this.o.c()) {
            h();
        } else {
            this.n.c();
        }
    }

    public boolean C(Menu menu, MenuInflater menuInflater) {
        if (this.t < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.G0(menu, menuInflater)) {
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
                    fragment2.g0();
                }
            }
        }
        this.m = arrayList;
        return z;
    }

    public void C0(Fragment fragment) {
        if (f985d) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.A) {
            return;
        }
        fragment.A = true;
        fragment.P = true ^ fragment.P;
    }

    public void D() {
        this.B = true;
        l0();
        e0(0);
        this.u = null;
        this.v = null;
        this.w = null;
        if (this.n != null) {
            this.o.d();
            this.n = null;
        }
    }

    public boolean D0() {
        return this.B;
    }

    public void E() {
        e0(1);
    }

    public void F() {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.M0();
            }
        }
    }

    boolean F0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        h hVar = fragment.t;
        return fragment == hVar.z0() && F0(hVar.w);
    }

    public void G(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.N0(z);
            }
        }
    }

    boolean G0(int i2) {
        return this.t >= i2;
    }

    void H(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).H(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    public boolean H0() {
        return this.z || this.A;
    }

    void I(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).I(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    g I0(Fragment fragment, int i2, boolean z, int i3) {
        int iO1;
        int iX = fragment.x();
        boolean z2 = false;
        fragment.i1(0);
        ViewGroup viewGroup = fragment.H;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationB0 = fragment.b0(i2, z, iX);
        if (animationB0 != null) {
            return new g(animationB0);
        }
        Animator animatorC0 = fragment.c0(i2, z, iX);
        if (animatorC0 != null) {
            return new g(animatorC0);
        }
        if (iX != 0) {
            boolean zEquals = "anim".equals(this.u.i().getResources().getResourceTypeName(iX));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.u.i(), iX);
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
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.u.i(), iX);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.u.i(), iX);
                    if (animationLoadAnimation2 != null) {
                        return new g(animationLoadAnimation2);
                    }
                }
            }
        }
        if (i2 == 0 || (iO1 = o1(i2, z)) < 0) {
            return null;
        }
        switch (iO1) {
            case 1:
                return M0(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return M0(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return M0(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return M0(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return K0(0.0f, 1.0f);
            case 6:
                return K0(1.0f, 0.0f);
            default:
                if (i3 == 0 && this.u.p()) {
                    i3 = this.u.o();
                }
                if (i3 == 0) {
                }
                return null;
        }
    }

    void J(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).J(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void J0(Fragment fragment) {
        if (this.k.get(fragment.g) != null) {
            return;
        }
        this.k.put(fragment.g, fragment);
        if (fragment.D) {
            if (fragment.C) {
                m(fragment);
            } else {
                a1(fragment);
            }
            fragment.D = false;
        }
        if (f985d) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    void K(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).K(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void L(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).L(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void L0(Fragment fragment) {
        if (this.k.get(fragment.g) == null) {
            return;
        }
        if (f985d) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && fragment.g.equals(fragment2.j)) {
                fragment2.i = fragment;
                fragment2.j = null;
            }
        }
        this.k.put(fragment.g, null);
        a1(fragment);
        String str = fragment.j;
        if (str != null) {
            fragment.i = this.k.get(str);
        }
        fragment.M();
    }

    void M(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).M(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void N(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).N(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void O(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).O(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void O0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.k.containsKey(fragment.g)) {
            if (f985d) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.t + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.t;
        if (fragment.n) {
            iMin = fragment.Q() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        R0(fragment, iMin, fragment.y(), fragment.z(), false);
        if (fragment.I != null) {
            Fragment fragmentR0 = r0(fragment);
            if (fragmentR0 != null) {
                View view = fragmentR0.I;
                ViewGroup viewGroup = fragment.H;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.I);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.I, iIndexOfChild);
                }
            }
            if (fragment.O && fragment.H != null) {
                float f2 = fragment.Q;
                if (f2 > 0.0f) {
                    fragment.I.setAlpha(f2);
                }
                fragment.Q = 0.0f;
                fragment.O = false;
                g gVarI0 = I0(fragment, fragment.y(), true, fragment.z());
                if (gVarI0 != null) {
                    Animation animation = gVarI0.f1001a;
                    if (animation != null) {
                        fragment.I.startAnimation(animation);
                    } else {
                        gVarI0.f1002b.setTarget(fragment.I);
                        gVarI0.f1002b.start();
                    }
                }
            }
        }
        if (fragment.P) {
            w(fragment);
        }
    }

    void P(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).P(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void P0(int i2, boolean z) {
        androidx.fragment.app.f fVar;
        if (this.u == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.t) {
            this.t = i2;
            int size = this.j.size();
            for (int i3 = 0; i3 < size; i3++) {
                O0(this.j.get(i3));
            }
            for (Fragment fragment : this.k.values()) {
                if (fragment != null && (fragment.n || fragment.B)) {
                    if (!fragment.O) {
                        O0(fragment);
                    }
                }
            }
            m1();
            if (this.y && (fVar = this.u) != null && this.t == 4) {
                fVar.r();
                this.y = false;
            }
        }
    }

    void Q(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).Q(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    void Q0(Fragment fragment) {
        R0(fragment, this.t, 0, 0, false);
    }

    void R(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).R(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
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
    void R0(androidx.fragment.app.Fragment r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.h.R0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    void S(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).S(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    public void S0() {
        this.z = false;
        this.A = false;
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.T();
            }
        }
    }

    void T(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).T(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    public void T0(Fragment fragment) {
        if (fragment.K) {
            if (this.h) {
                this.C = true;
            } else {
                fragment.K = false;
                R0(fragment, this.t, 0, 0, false);
            }
        }
    }

    void U(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.g gVarU = fragment2.u();
            if (gVarU instanceof h) {
                ((h) gVarU).U(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1007b) {
                g.a aVar = iVar.f1006a;
                throw null;
            }
        }
    }

    public boolean V(MenuItem menuItem) {
        if (this.t < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.O0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean V0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
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
                    if ((str != null && str.equals(aVar.r())) || (i2 >= 0 && i2 == aVar.u)) {
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
                        if (str == null || !str.equals(aVar2.r())) {
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

    public void W(Menu menu) {
        if (this.t < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.P0(menu);
            }
        }
    }

    public void X0(Bundle bundle, String str, Fragment fragment) {
        if (fragment.t != this) {
            n1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.g);
    }

    public void Y() {
        e0(3);
    }

    public void Y0(Fragment fragment) {
        if (f985d) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.s);
        }
        boolean z = !fragment.Q();
        if (!fragment.B || z) {
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (E0(fragment)) {
                this.y = true;
            }
            fragment.m = false;
            fragment.n = true;
        }
    }

    public void Z(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.R0(z);
            }
        }
    }

    @Override // androidx.fragment.app.g
    public androidx.fragment.app.j a() {
        return new androidx.fragment.app.a(this);
    }

    public boolean a0(Menu menu) {
        if (this.t < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null && fragment.S0(menu)) {
                z = true;
            }
        }
        return z;
    }

    void a1(Fragment fragment) {
        if (H0()) {
            if (f985d) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.J.k(fragment) && f985d) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
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
                    fragment.e(str2, fileDescriptor, printWriter, strArr);
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
                aVar.m(str2, printWriter);
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

    void b0() {
        p1();
        X(this.x);
    }

    void b1() {
        if (this.r != null) {
            for (int i2 = 0; i2 < this.r.size(); i2++) {
                this.r.get(i2).a();
            }
        }
    }

    @Override // androidx.fragment.app.g
    public boolean c() {
        boolean zL0 = l0();
        s0();
        return zL0;
    }

    public void c0() {
        this.z = false;
        this.A = false;
        e0(4);
    }

    void c1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f963b == null) {
            return;
        }
        for (Fragment fragment : this.J.h()) {
            if (f985d) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f963b.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f967c.equals(fragment.g)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f985d) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f963b);
                }
                R0(fragment, 1, 0, 0, false);
                fragment.n = true;
                R0(fragment, 0, 0, 0, false);
            } else {
                next.o = fragment;
                fragment.e = null;
                fragment.s = 0;
                fragment.p = false;
                fragment.m = false;
                Fragment fragment2 = fragment.i;
                fragment.j = fragment2 != null ? fragment2.g : null;
                fragment.i = null;
                Bundle bundle = next.n;
                if (bundle != null) {
                    bundle.setClassLoader(this.u.i().getClassLoader());
                    fragment.e = next.n.getSparseParcelableArray("android:view_state");
                    fragment.f953d = next.n;
                }
            }
        }
        this.k.clear();
        for (FragmentState fragmentState : fragmentManagerState.f963b) {
            if (fragmentState != null) {
                Fragment fragmentF = fragmentState.f(this.u.i().getClassLoader(), e());
                fragmentF.t = this;
                if (f985d) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentF.g + "): " + fragmentF);
                }
                this.k.put(fragmentF.g, fragmentF);
                fragmentState.o = null;
            }
        }
        this.j.clear();
        ArrayList<String> arrayList = fragmentManagerState.f964c;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.k.get(str);
                if (fragment3 == null) {
                    n1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.m = true;
                if (f985d) {
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
        if (fragmentManagerState.f965d != null) {
            this.l = new ArrayList<>(fragmentManagerState.f965d.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f965d;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarF = backStackStateArr[i2].f(this);
                if (f985d) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarF.u + "): " + aVarF);
                    PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
                    aVarF.n("  ", printWriter, false);
                    printWriter.close();
                }
                this.l.add(aVarF);
                int i3 = aVarF.u;
                if (i3 >= 0) {
                    i1(i3, aVarF);
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
            X(fragment4);
        }
        this.i = fragmentManagerState.f;
    }

    @Override // androidx.fragment.app.g
    public Fragment d(String str) {
        if (str != null) {
            for (int size = this.j.size() - 1; size >= 0; size--) {
                Fragment fragment = this.j.get(size);
                if (fragment != null && str.equals(fragment.z)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && str.equals(fragment2.z)) {
                return fragment2;
            }
        }
        return null;
    }

    public void d0() {
        this.z = false;
        this.A = false;
        e0(3);
    }

    @Override // androidx.fragment.app.g
    public androidx.fragment.app.e e() {
        if (super.e() == androidx.fragment.app.g.f983b) {
            Fragment fragment = this.w;
            if (fragment != null) {
                return fragment.t.e();
            }
            i(new f());
        }
        return super.e();
    }

    Parcelable e1() {
        ArrayList<String> arrayList;
        int size;
        s0();
        h0();
        l0();
        this.z = true;
        BackStackState[] backStackStateArr = null;
        if (this.k.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.k.size());
        boolean z = false;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                if (fragment.t != this) {
                    n1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.f952c <= 0 || fragmentState.n != null) {
                    fragmentState.n = fragment.f953d;
                } else {
                    fragmentState.n = f1(fragment);
                    String str = fragment.j;
                    if (str != null) {
                        Fragment fragment2 = this.k.get(str);
                        if (fragment2 == null) {
                            n1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.j));
                        }
                        if (fragmentState.n == null) {
                            fragmentState.n = new Bundle();
                        }
                        X0(fragmentState.n, "android:target_state", fragment2);
                        int i2 = fragment.k;
                        if (i2 != 0) {
                            fragmentState.n.putInt("android:target_req_state", i2);
                        }
                    }
                }
                if (f985d) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.n);
                }
                z = true;
            }
        }
        if (!z) {
            if (f985d) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.j.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.j) {
                arrayList.add(fragment3.g);
                if (fragment3.t != this) {
                    n1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (f985d) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.g + "): " + fragment3);
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
                if (f985d) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.l.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f963b = arrayList2;
        fragmentManagerState.f964c = arrayList;
        fragmentManagerState.f965d = backStackStateArr;
        Fragment fragment4 = this.x;
        if (fragment4 != null) {
            fragmentManagerState.e = fragment4.g;
        }
        fragmentManagerState.f = this.i;
        return fragmentManagerState;
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

    public void f0() {
        this.A = true;
        e0(2);
    }

    Bundle f1(Fragment fragment) {
        if (this.G == null) {
            this.G = new Bundle();
        }
        fragment.V0(this.G);
        Q(fragment, this.G, false);
        Bundle bundle = null;
        if (!this.G.isEmpty()) {
            Bundle bundle2 = this.G;
            this.G = null;
            bundle = bundle2;
        }
        if (fragment.I != null) {
            g1(fragment);
        }
        if (fragment.e != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.e);
        }
        if (!fragment.L) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.L);
        }
        return bundle;
    }

    @Override // androidx.fragment.app.g
    public void g(int i2, int i3) {
        if (i2 >= 0) {
            i0(new l(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    void g0() {
        if (this.C) {
            this.C = false;
            m1();
        }
    }

    void g1(Fragment fragment) {
        if (fragment.J == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.H;
        if (sparseArray == null) {
            this.H = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.J.saveHierarchyState(this.H);
        if (this.H.size() > 0) {
            fragment.e = this.H;
            this.H = null;
        }
    }

    @Override // androidx.fragment.app.g
    public boolean h() {
        t();
        return U0(null, -1, 0);
    }

    void h1() {
        synchronized (this) {
            ArrayList<m> arrayList = this.I;
            boolean z = false;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.g;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.u.j().removeCallbacks(this.K);
                this.u.j().post(this.K);
                p1();
            }
        }
    }

    public void i0(k kVar, boolean z) {
        if (!z) {
            t();
        }
        synchronized (this) {
            if (!this.B && this.u != null) {
                if (this.g == null) {
                    this.g = new ArrayList<>();
                }
                this.g.add(kVar);
                h1();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void i1(int i2, androidx.fragment.app.a aVar) {
        synchronized (this) {
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            int size = this.p.size();
            if (i2 < size) {
                if (f985d) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + aVar);
                }
                this.p.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.p.add(null);
                    if (this.q == null) {
                        this.q = new ArrayList<>();
                    }
                    if (f985d) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.q.add(Integer.valueOf(size));
                    size++;
                }
                if (f985d) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + aVar);
                }
                this.p.add(aVar);
            }
        }
    }

    public void j1(Fragment fragment, Lifecycle.State state) {
        if (this.k.get(fragment.g) == fragment && (fragment.u == null || fragment.u() == this)) {
            fragment.T = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void k(androidx.fragment.app.a aVar) {
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        this.l.add(aVar);
    }

    void k0(Fragment fragment) {
        if (!fragment.o || fragment.r) {
            return;
        }
        fragment.H0(fragment.L0(fragment.f953d), null, fragment.f953d);
        View view = fragment.I;
        if (view == null) {
            fragment.J = null;
            return;
        }
        fragment.J = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.A) {
            fragment.I.setVisibility(8);
        }
        fragment.z0(fragment.I, fragment.f953d);
        T(fragment, fragment.I, fragment.f953d, false);
    }

    public void k1(Fragment fragment) {
        if (fragment == null || (this.k.get(fragment.g) == fragment && (fragment.u == null || fragment.u() == this))) {
            Fragment fragment2 = this.x;
            this.x = fragment;
            X(fragment2);
            X(this.x);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void l(Fragment fragment, boolean z) {
        if (f985d) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        J0(fragment);
        if (fragment.B) {
            return;
        }
        if (this.j.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.j) {
            this.j.add(fragment);
        }
        fragment.m = true;
        fragment.n = false;
        if (fragment.I == null) {
            fragment.P = false;
        }
        if (E0(fragment)) {
            this.y = true;
        }
        if (z) {
            Q0(fragment);
        }
    }

    public boolean l0() {
        j0(true);
        boolean z = false;
        while (u0(this.D, this.E)) {
            this.h = true;
            try {
                Z0(this.D, this.E);
                u();
                z = true;
            } catch (Throwable th) {
                u();
                throw th;
            }
        }
        p1();
        g0();
        r();
        return z;
    }

    public void l1(Fragment fragment) {
        if (f985d) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.A) {
            fragment.A = false;
            fragment.P = !fragment.P;
        }
    }

    void m(Fragment fragment) {
        if (H0()) {
            if (f985d) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.J.d(fragment) && f985d) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    void m1() {
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                T0(fragment);
            }
        }
    }

    public int n(androidx.fragment.app.a aVar) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.q;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.q.remove(r0.size() - 1).intValue();
                if (f985d) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.p.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            int size = this.p.size();
            if (f985d) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.p.add(aVar);
            return size;
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f1008a);
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
        Fragment fragmentP0 = resourceId != -1 ? p0(resourceId) : null;
        if (fragmentP0 == null && string != null) {
            fragmentP0 = d(string);
        }
        if (fragmentP0 == null && id != -1) {
            fragmentP0 = p0(id);
        }
        if (f985d) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentP0);
        }
        if (fragmentP0 == null) {
            fragmentP0 = e().a(context.getClassLoader(), str2);
            fragmentP0.o = true;
            fragmentP0.x = resourceId != 0 ? resourceId : id;
            fragmentP0.y = id;
            fragmentP0.z = string;
            fragmentP0.p = true;
            fragmentP0.t = this;
            androidx.fragment.app.f fVar = this.u;
            fragmentP0.u = fVar;
            fragmentP0.m0(fVar.i(), attributeSet, fragmentP0.f953d);
            l(fragmentP0, true);
        } else {
            if (fragmentP0.p) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentP0.p = true;
            androidx.fragment.app.f fVar2 = this.u;
            fragmentP0.u = fVar2;
            fragmentP0.m0(fVar2.i(), attributeSet, fragmentP0.f953d);
        }
        Fragment fragment = fragmentP0;
        if (this.t >= 1 || !fragment.o) {
            Q0(fragment);
        } else {
            R0(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.I;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.I.getTag() == null) {
                fragment.I.setTag(string);
            }
            return fragment.I;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void p(androidx.fragment.app.f fVar, androidx.fragment.app.c cVar, Fragment fragment) {
        if (this.u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.u = fVar;
        this.v = cVar;
        this.w = fragment;
        if (fragment != null) {
            p1();
        }
        if (fVar instanceof androidx.activity.c) {
            androidx.activity.c cVar2 = (androidx.activity.c) fVar;
            OnBackPressedDispatcher onBackPressedDispatcherC = cVar2.c();
            this.n = onBackPressedDispatcherC;
            androidx.lifecycle.f fVar2 = cVar2;
            if (fragment != null) {
                fVar2 = fragment;
            }
            onBackPressedDispatcherC.a(fVar2, this.o);
        }
        if (fragment != null) {
            this.J = fragment.t.w0(fragment);
        } else if (fVar instanceof q) {
            this.J = androidx.fragment.app.i.g(((q) fVar).h());
        } else {
            this.J = new androidx.fragment.app.i(false);
        }
    }

    public Fragment p0(int i2) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null && fragment.x == i2) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && fragment2.x == i2) {
                return fragment2;
            }
        }
        return null;
    }

    public void q(Fragment fragment) {
        if (f985d) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.B) {
            fragment.B = false;
            if (fragment.m) {
                return;
            }
            if (this.j.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (f985d) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.j) {
                this.j.add(fragment);
            }
            fragment.m = true;
            if (E0(fragment)) {
                this.y = true;
            }
        }
    }

    public Fragment q0(String str) {
        Fragment fragmentG;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && (fragmentG = fragment.g(str)) != null) {
                return fragmentG;
            }
        }
        return null;
    }

    boolean s() {
        boolean zE0 = false;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                zE0 = E0(fragment);
            }
            if (zE0) {
                return true;
            }
        }
        return false;
    }

    public void t0(int i2) {
        synchronized (this) {
            this.p.set(i2, null);
            if (this.q == null) {
                this.q = new ArrayList<>();
            }
            if (f985d) {
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

    void v(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.p(z3);
        } else {
            aVar.o();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            androidx.fragment.app.k.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            P0(this.t, true);
        }
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && fragment.I != null && fragment.O && aVar.s(fragment.y)) {
                float f2 = fragment.Q;
                if (f2 > 0.0f) {
                    fragment.I.setAlpha(f2);
                }
                if (z3) {
                    fragment.Q = 0.0f;
                } else {
                    fragment.Q = -1.0f;
                    fragment.O = false;
                }
            }
        }
    }

    public int v0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.l;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void w(Fragment fragment) {
        Animator animator;
        if (fragment.I != null) {
            g gVarI0 = I0(fragment, fragment.y(), !fragment.A, fragment.z());
            if (gVarI0 == null || (animator = gVarI0.f1002b) == null) {
                if (gVarI0 != null) {
                    fragment.I.startAnimation(gVarI0.f1001a);
                    gVarI0.f1001a.start();
                }
                fragment.I.setVisibility((!fragment.A || fragment.P()) ? 0 : 8);
                if (fragment.P()) {
                    fragment.h1(false);
                }
            } else {
                animator.setTarget(fragment.I);
                if (!fragment.A) {
                    fragment.I.setVisibility(0);
                } else if (fragment.P()) {
                    fragment.h1(false);
                } else {
                    ViewGroup viewGroup = fragment.H;
                    View view = fragment.I;
                    viewGroup.startViewTransition(view);
                    gVarI0.f1002b.addListener(new e(viewGroup, view, fragment));
                }
                gVarI0.f1002b.start();
            }
        }
        if (fragment.m && E0(fragment)) {
            this.y = true;
        }
        fragment.P = false;
        fragment.k0(fragment.A);
    }

    androidx.fragment.app.i w0(Fragment fragment) {
        return this.J.f(fragment);
    }

    public void x(Fragment fragment) {
        if (f985d) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.B) {
            return;
        }
        fragment.B = true;
        if (fragment.m) {
            if (f985d) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (E0(fragment)) {
                this.y = true;
            }
            fragment.m = false;
        }
    }

    public Fragment x0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.k.get(string);
        if (fragment == null) {
            n1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void y() {
        this.z = false;
        this.A = false;
        e0(2);
    }

    LayoutInflater.Factory2 y0() {
        return this;
    }

    public void z(Configuration configuration) {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            Fragment fragment = this.j.get(i2);
            if (fragment != null) {
                fragment.D0(configuration);
            }
        }
    }

    public Fragment z0() {
        return this.x;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1001a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1002b;

        g(Animation animation) {
            this.f1001a = animation;
            this.f1002b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        g(Animator animator) {
            this.f1001a = null;
            this.f1002b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class RunnableC0018h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ViewGroup f1003b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final View f1004c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1005d;
        private boolean e;
        private boolean f;

        RunnableC0018h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f = true;
            this.f1003b = viewGroup;
            this.f1004c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f = true;
            if (this.f1005d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1005d = true;
                b.g.k.q.a(this.f1003b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1005d || !this.f) {
                this.f1003b.endViewTransition(this.f1004c);
                this.e = true;
            } else {
                this.f = false;
                this.f1003b.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.f = true;
            if (this.f1005d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f1005d = true;
                b.g.k.q.a(this.f1003b, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
