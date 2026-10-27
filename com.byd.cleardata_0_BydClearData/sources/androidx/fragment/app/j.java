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
import androidx.fragment.app.i;
import androidx.lifecycle.d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
final class j extends androidx.fragment.app.i implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static boolean f1292d = false;
    static final Interpolator e = new DecelerateInterpolator(2.5f);
    static final Interpolator f = new DecelerateInterpolator(1.5f);
    boolean A;
    boolean B;
    boolean C;
    ArrayList<androidx.fragment.app.a> D;
    ArrayList<Boolean> E;
    ArrayList<Fragment> F;
    ArrayList<m> I;
    private androidx.fragment.app.l J;
    ArrayList<k> g;
    boolean h;
    ArrayList<androidx.fragment.app.a> l;
    ArrayList<Fragment> m;
    private OnBackPressedDispatcher n;
    ArrayList<androidx.fragment.app.a> p;
    ArrayList<Integer> q;
    ArrayList<i.b> r;
    androidx.fragment.app.h u;
    androidx.fragment.app.e v;
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

    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            j.this.A0();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.k0();
        }
    }

    class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1296b;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f1296b.u() != null) {
                    c.this.f1296b.o1(null);
                    c cVar = c.this;
                    j jVar = j.this;
                    Fragment fragment = cVar.f1296b;
                    jVar.Q0(fragment, fragment.Q(), 0, 0, false);
                }
            }
        }

        c(ViewGroup viewGroup, Fragment fragment) {
            this.f1295a = viewGroup;
            this.f1296b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1295a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1299a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1300b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1301c;

        d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1299a = viewGroup;
            this.f1300b = view;
            this.f1301c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1299a.endViewTransition(this.f1300b);
            Animator animatorV = this.f1301c.v();
            this.f1301c.p1(null);
            if (animatorV == null || this.f1299a.indexOfChild(this.f1300b) >= 0) {
                return;
            }
            j jVar = j.this;
            Fragment fragment = this.f1301c;
            jVar.Q0(fragment, fragment.Q(), 0, 0, false);
        }
    }

    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1303a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1304b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1305c;

        e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1303a = viewGroup;
            this.f1304b = view;
            this.f1305c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1303a.endViewTransition(this.f1304b);
            animator.removeListener(this);
            Fragment fragment = this.f1305c;
            View view = fragment.I;
            if (view == null || !fragment.A) {
                return;
            }
            view.setVisibility(8);
        }
    }

    class f extends androidx.fragment.app.g {
        f() {
        }

        @Override // androidx.fragment.app.g
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.h hVar = j.this.u;
            return hVar.b(hVar.h(), str, null);
        }
    }

    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1308a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1309b;

        g(Animator animator) {
            this.f1308a = null;
            this.f1309b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        g(Animation animation) {
            this.f1308a = animation;
            this.f1309b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    private static class h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ViewGroup f1310b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final View f1311c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1312d;
        private boolean e;
        private boolean f;

        h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f = true;
            this.f1310b = viewGroup;
            this.f1311c = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f = true;
            if (this.f1312d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1312d = true;
                a.g.k.q.a(this.f1310b, this);
            }
            return true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.f = true;
            if (this.f1312d) {
                return !this.e;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f1312d = true;
                a.g.k.q.a(this.f1310b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1312d || !this.f) {
                this.f1310b.endViewTransition(this.f1311c);
                this.e = true;
            } else {
                this.f = false;
                this.f1310b.post(this);
            }
        }
    }

    private static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final i.a f1313a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1314b;
    }

    /* JADX INFO: renamed from: androidx.fragment.app.j$j, reason: collision with other inner class name */
    static class C0042j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1315a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    interface k {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    private class l implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1316a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1317b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f1318c;

        l(String str, int i, int i2) {
            this.f1316a = str;
            this.f1317b = i;
            this.f1318c = i2;
        }

        @Override // androidx.fragment.app.j.k
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = j.this.x;
            if (fragment == null || this.f1317b >= 0 || this.f1316a != null || !fragment.x().g()) {
                return j.this.U0(arrayList, arrayList2, this.f1316a, this.f1317b, this.f1318c);
            }
            return false;
        }
    }

    static class m implements Fragment.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f1320a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f1321b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1322c;

        m(androidx.fragment.app.a aVar, boolean z) {
            this.f1320a = z;
            this.f1321b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void a() {
            int i = this.f1322c - 1;
            this.f1322c = i;
            if (i != 0) {
                return;
            }
            this.f1321b.s.g1();
        }

        @Override // androidx.fragment.app.Fragment.f
        public void b() {
            this.f1322c++;
        }

        public void c() {
            androidx.fragment.app.a aVar = this.f1321b;
            aVar.s.u(aVar, this.f1320a, false, false);
        }

        public void d() {
            boolean z = this.f1322c > 0;
            j jVar = this.f1321b.s;
            int size = jVar.j.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = jVar.j.get(i);
                fragment.u1(null);
                if (z && fragment.a0()) {
                    fragment.y1();
                }
            }
            androidx.fragment.app.a aVar = this.f1321b;
            aVar.s.u(aVar, this.f1320a, !z, true);
        }

        public boolean e() {
            return this.f1322c == 0;
        }
    }

    j() {
    }

    private boolean D0(Fragment fragment) {
        return (fragment.E && fragment.F) || fragment.v.r();
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

    private void M0(a.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentI = bVar.i(i2);
            if (!fragmentI.m) {
                View viewL1 = fragmentI.l1();
                fragmentI.Q = viewL1.getAlpha();
                viewL1.setAlpha(0.0f);
            }
        }
    }

    private boolean T0(String str, int i2, int i3) {
        k0();
        i0(true);
        Fragment fragment = this.x;
        if (fragment != null && i2 < 0 && str == null && fragment.x().g()) {
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

    private int V0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, a.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.u() && !aVar.s(arrayList, i5 + 1, i3)) {
                if (this.I == null) {
                    this.I = new ArrayList<>();
                }
                m mVar = new m(aVar, zBooleanValue);
                this.I.add(mVar);
                aVar.w(mVar);
                if (zBooleanValue) {
                    aVar.n();
                } else {
                    aVar.o(false);
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
        if (fragment == null || this.k.get(fragment.g) != fragment) {
            return;
        }
        fragment.d1();
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
                if (fragment.u() != null) {
                    int iQ = fragment.Q();
                    View viewU = fragment.u();
                    Animation animation = viewU.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        viewU.clearAnimation();
                    }
                    fragment.o1(null);
                    Q0(fragment, iQ, 0, 0, false);
                } else if (fragment.v() != null) {
                    fragment.v().end();
                }
            }
        }
    }

    private void i(a.d.b<Fragment> bVar) {
        int i2 = this.t;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        int size = this.j.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.j.get(i3);
            if (fragment.f1272c < iMin) {
                Q0(fragment, iMin, fragment.G(), fragment.H(), false);
                if (fragment.I != null && !fragment.A && fragment.O) {
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
        if (Looper.myLooper() != this.u.i().getLooper()) {
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
                aVar.j(-1);
                aVar.o(i2 == i3 + (-1));
            } else {
                aVar.j(1);
                aVar.n();
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
            fragmentY0 = !arrayList2.get(i7).booleanValue() ? aVar.p(this.F, fragmentY0) : aVar.x(this.F, fragmentY0);
            z2 = z2 || aVar.h;
        }
        this.F.clear();
        if (!z) {
            o.B(this, arrayList, arrayList2, i2, i3, false);
        }
        l0(arrayList, arrayList2, i2, i3);
        if (z) {
            a.d.b<Fragment> bVar = new a.d.b<>();
            i(bVar);
            int iV0 = V0(arrayList, arrayList2, i2, i3, bVar);
            M0(bVar);
            i4 = iV0;
        } else {
            i4 = i3;
        }
        if (i4 != i6 && z) {
            o.B(this, arrayList, arrayList2, i2, i4, true);
            O0(this.t, true);
        }
        while (i6 < i3) {
            androidx.fragment.app.a aVar2 = arrayList.get(i6);
            if (arrayList2.get(i6).booleanValue() && (i5 = aVar2.u) >= 0) {
                s0(i5);
                aVar2.u = -1;
            }
            aVar2.v();
            i6++;
        }
        if (z2) {
            a1();
        }
    }

    private void m1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new a.g.j.b("FragmentManager"));
        androidx.fragment.app.h hVar = this.u;
        try {
            if (hVar != null) {
                hVar.k("  ", null, printWriter, new String[0]);
            } else {
                b("  ", null, printWriter, new String[0]);
            }
            throw runtimeException;
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
            throw runtimeException;
        }
    }

    private void n(Fragment fragment, g gVar, int i2) {
        View view = fragment.I;
        ViewGroup viewGroup = fragment.H;
        viewGroup.startViewTransition(view);
        fragment.v1(i2);
        Animation animation = gVar.f1308a;
        if (animation != null) {
            h hVar = new h(animation, viewGroup, view);
            fragment.o1(fragment.I);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.I.startAnimation(hVar);
            return;
        }
        Animator animator = gVar.f1309b;
        fragment.p1(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.I);
        animator.start();
    }

    private void n0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<m> arrayList3 = this.I;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            m mVar = this.I.get(i2);
            if (arrayList == null || mVar.f1320a || (iIndexOf2 = arrayList.indexOf(mVar.f1321b)) == -1 || !arrayList2.get(iIndexOf2).booleanValue()) {
                if (mVar.e() || (arrayList != null && mVar.f1321b.s(arrayList, 0, arrayList.size()))) {
                    this.I.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || mVar.f1320a || (iIndexOf = arrayList.indexOf(mVar.f1321b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                        mVar.d();
                    }
                }
                i2++;
            } else {
                this.I.remove(i2);
                i2--;
                size--;
            }
            mVar.c();
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
                this.u.i().removeCallbacks(this.K);
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
            if (fragment != null && fragment.Q0(menu, menuInflater)) {
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
                    fragment2.q0();
                }
            }
        }
        this.m = arrayList;
        return z;
    }

    public void B0(Fragment fragment) {
        if (f1292d) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.A) {
            return;
        }
        fragment.A = true;
        fragment.P = true ^ fragment.P;
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
                fragment.W0();
            }
        }
    }

    boolean E0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        j jVar = fragment.t;
        return fragment == jVar.y0() && E0(jVar.w);
    }

    public void F(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.X0(z);
            }
        }
    }

    boolean F0(int i2) {
        return this.t >= i2;
    }

    void G(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).G(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
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
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).H(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    g H0(Fragment fragment, int i2, boolean z, int i3) {
        int iN1;
        int iG = fragment.G();
        boolean z2 = false;
        fragment.s1(0);
        ViewGroup viewGroup = fragment.H;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationL0 = fragment.l0(i2, z, iG);
        if (animationL0 != null) {
            return new g(animationL0);
        }
        Animator animatorM0 = fragment.m0(i2, z, iG);
        if (animatorM0 != null) {
            return new g(animatorM0);
        }
        if (iG != 0) {
            boolean zEquals = "anim".equals(this.u.h().getResources().getResourceTypeName(iG));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.u.h(), iG);
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
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.u.h(), iG);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.u.h(), iG);
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
                if (i3 == 0 && this.u.p()) {
                    this.u.n();
                }
                return null;
        }
    }

    void I(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).I(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void I0(Fragment fragment) {
        if (this.k.get(fragment.g) != null) {
            return;
        }
        this.k.put(fragment.g, fragment);
        if (fragment.D) {
            if (fragment.C) {
                l(fragment);
            } else {
                Z0(fragment);
            }
            fragment.D = false;
        }
        if (f1292d) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    void J(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).J(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void K(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).K(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void K0(Fragment fragment) {
        if (this.k.get(fragment.g) == null) {
            return;
        }
        if (f1292d) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.k.values()) {
            if (fragment2 != null && fragment.g.equals(fragment2.j)) {
                fragment2.i = fragment;
                fragment2.j = null;
            }
        }
        this.k.put(fragment.g, null);
        Z0(fragment);
        String str = fragment.j;
        if (str != null) {
            fragment.i = this.k.get(str);
        }
        fragment.W();
    }

    void L(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).L(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void M(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).M(fragment, context, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void N(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).N(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void N0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.k.containsKey(fragment.g)) {
            if (f1292d) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.t + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.t;
        if (fragment.n) {
            iMin = fragment.Z() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        Q0(fragment, iMin, fragment.H(), fragment.I(), false);
        if (fragment.I != null) {
            Fragment fragmentQ0 = q0(fragment);
            if (fragmentQ0 != null) {
                View view = fragmentQ0.I;
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
                g gVarH0 = H0(fragment, fragment.H(), true, fragment.I());
                if (gVarH0 != null) {
                    Animation animation = gVarH0.f1308a;
                    if (animation != null) {
                        fragment.I.startAnimation(animation);
                    } else {
                        gVarH0.f1309b.setTarget(fragment.I);
                        gVarH0.f1309b.start();
                    }
                }
            }
        }
        if (fragment.P) {
            v(fragment);
        }
    }

    void O(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).O(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    void O0(int i2, boolean z) {
        androidx.fragment.app.h hVar;
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
                if (fragment != null && (fragment.n || fragment.B)) {
                    if (!fragment.O) {
                        N0(fragment);
                    }
                }
            }
            l1();
            if (this.y && (hVar = this.u) != null && this.t == 4) {
                hVar.s();
                this.y = false;
            }
        }
    }

    void P(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).P(fragment, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
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
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).Q(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.Q0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    void R(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).R(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
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
                fragment.d0();
            }
        }
    }

    void S(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).S(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
                throw null;
            }
        }
    }

    public void S0(Fragment fragment) {
        if (fragment.K) {
            if (this.h) {
                this.C = true;
            } else {
                fragment.K = false;
                Q0(fragment, this.t, 0, 0, false);
            }
        }
    }

    void T(Fragment fragment, boolean z) {
        Fragment fragment2 = this.w;
        if (fragment2 != null) {
            androidx.fragment.app.i iVarD = fragment2.D();
            if (iVarD instanceof j) {
                ((j) iVarD).T(fragment, true);
            }
        }
        for (i iVar : this.s) {
            if (!z || iVar.f1314b) {
                i.a aVar = iVar.f1313a;
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
            if (fragment != null && fragment.Y0(menuItem)) {
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
                    if ((str != null && str.equals(aVar.q())) || (i2 >= 0 && i2 == aVar.u)) {
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
                        if (str == null || !str.equals(aVar2.q())) {
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
                fragment.Z0(menu);
            }
        }
    }

    public void W0(Bundle bundle, String str, Fragment fragment) {
        if (fragment.t != this) {
            m1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.g);
    }

    public void X() {
        d0(3);
    }

    public void X0(Fragment fragment) {
        if (f1292d) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.s);
        }
        boolean z = !fragment.Z();
        if (!fragment.B || z) {
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (D0(fragment)) {
                this.y = true;
            }
            fragment.m = false;
            fragment.n = true;
        }
    }

    public void Y(boolean z) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            Fragment fragment = this.j.get(size);
            if (fragment != null) {
                fragment.b1(z);
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
            if (fragment != null && fragment.c1(menu)) {
                z = true;
            }
        }
        return z;
    }

    void Z0(Fragment fragment) {
        if (G0()) {
            if (f1292d) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.J.k(fragment) && f1292d) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // androidx.fragment.app.i
    public n a() {
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

    @Override // androidx.fragment.app.i
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
                    fragment.i(str2, fileDescriptor, printWriter, strArr);
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
                aVar.l(str2, printWriter);
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
        androidx.fragment.app.m next;
        if (parcelable == null) {
            return;
        }
        androidx.fragment.app.k kVar = (androidx.fragment.app.k) parcelable;
        if (kVar.f1323b == null) {
            return;
        }
        for (Fragment fragment : this.J.h()) {
            if (f1292d) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<androidx.fragment.app.m> it = kVar.f1323b.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f1329c.equals(fragment.g)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (f1292d) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + kVar.f1323b);
                }
                Q0(fragment, 1, 0, 0, false);
                fragment.n = true;
                Q0(fragment, 0, 0, 0, false);
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
                    bundle.setClassLoader(this.u.h().getClassLoader());
                    fragment.e = next.n.getSparseParcelableArray("android:view_state");
                    fragment.f1273d = next.n;
                }
            }
        }
        this.k.clear();
        for (androidx.fragment.app.m mVar : kVar.f1323b) {
            if (mVar != null) {
                Fragment fragmentU = mVar.u(this.u.h().getClassLoader(), d());
                fragmentU.t = this;
                if (f1292d) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentU.g + "): " + fragmentU);
                }
                this.k.put(fragmentU.g, fragmentU);
                mVar.o = null;
            }
        }
        this.j.clear();
        ArrayList<String> arrayList = kVar.f1324c;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.k.get(str);
                if (fragment3 == null) {
                    m1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.m = true;
                if (f1292d) {
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
        if (kVar.f1325d != null) {
            this.l = new ArrayList<>(kVar.f1325d.length);
            int i2 = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr = kVar.f1325d;
                if (i2 >= bVarArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarU = bVarArr[i2].u(this);
                if (f1292d) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarU.u + "): " + aVarU);
                    PrintWriter printWriter = new PrintWriter(new a.g.j.b("FragmentManager"));
                    aVarU.m("  ", printWriter, false);
                    printWriter.close();
                }
                this.l.add(aVarU);
                int i3 = aVarU.u;
                if (i3 >= 0) {
                    h1(i3, aVarU);
                }
                i2++;
            }
        } else {
            this.l = null;
        }
        String str2 = kVar.e;
        if (str2 != null) {
            Fragment fragment4 = this.k.get(str2);
            this.x = fragment4;
            W(fragment4);
        }
        this.i = kVar.f;
    }

    @Override // androidx.fragment.app.i
    public Fragment c(String str) {
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

    public void c0() {
        this.z = false;
        this.A = false;
        d0(3);
    }

    @Override // androidx.fragment.app.i
    public androidx.fragment.app.g d() {
        if (super.d() == androidx.fragment.app.i.f1290b) {
            Fragment fragment = this.w;
            if (fragment != null) {
                return fragment.t.d();
            }
            h(new f());
        }
        return super.d();
    }

    Parcelable d1() {
        ArrayList<String> arrayList;
        int size;
        r0();
        g0();
        k0();
        this.z = true;
        androidx.fragment.app.b[] bVarArr = null;
        if (this.k.isEmpty()) {
            return null;
        }
        ArrayList<androidx.fragment.app.m> arrayList2 = new ArrayList<>(this.k.size());
        boolean z = false;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null) {
                if (fragment.t != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                androidx.fragment.app.m mVar = new androidx.fragment.app.m(fragment);
                arrayList2.add(mVar);
                if (fragment.f1272c <= 0 || mVar.n != null) {
                    mVar.n = fragment.f1273d;
                } else {
                    mVar.n = e1(fragment);
                    String str = fragment.j;
                    if (str != null) {
                        Fragment fragment2 = this.k.get(str);
                        if (fragment2 == null) {
                            m1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.j));
                        }
                        if (mVar.n == null) {
                            mVar.n = new Bundle();
                        }
                        W0(mVar.n, "android:target_state", fragment2);
                        int i2 = fragment.k;
                        if (i2 != 0) {
                            mVar.n.putInt("android:target_req_state", i2);
                        }
                    }
                }
                if (f1292d) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + mVar.n);
                }
                z = true;
            }
        }
        if (!z) {
            if (f1292d) {
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
                    m1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (f1292d) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.g + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<androidx.fragment.app.a> arrayList3 = this.l;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            bVarArr = new androidx.fragment.app.b[size];
            for (int i3 = 0; i3 < size; i3++) {
                bVarArr[i3] = new androidx.fragment.app.b(this.l.get(i3));
                if (f1292d) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.l.get(i3));
                }
            }
        }
        androidx.fragment.app.k kVar = new androidx.fragment.app.k();
        kVar.f1323b = arrayList2;
        kVar.f1324c = arrayList;
        kVar.f1325d = bVarArr;
        Fragment fragment4 = this.x;
        if (fragment4 != null) {
            kVar.e = fragment4.g;
        }
        kVar.f = this.i;
        return kVar;
    }

    @Override // androidx.fragment.app.i
    public List<Fragment> e() {
        List<Fragment> list;
        if (this.j.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.j) {
            list = (List) this.j.clone();
        }
        return list;
    }

    public void e0() {
        this.A = true;
        d0(2);
    }

    Bundle e1(Fragment fragment) {
        if (this.G == null) {
            this.G = new Bundle();
        }
        fragment.f1(this.G);
        P(fragment, this.G, false);
        Bundle bundle = null;
        if (!this.G.isEmpty()) {
            Bundle bundle2 = this.G;
            this.G = null;
            bundle = bundle2;
        }
        if (fragment.I != null) {
            f1(fragment);
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

    @Override // androidx.fragment.app.i
    public void f(int i2, int i3) {
        if (i2 >= 0) {
            h0(new l(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    void f0() {
        if (this.C) {
            this.C = false;
            l1();
        }
    }

    void f1(Fragment fragment) {
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

    @Override // androidx.fragment.app.i
    public boolean g() {
        s();
        return T0(null, -1, 0);
    }

    void g1() {
        synchronized (this) {
            ArrayList<m> arrayList = this.I;
            boolean z = false;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.g;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.u.i().removeCallbacks(this.K);
                this.u.i().post(this.K);
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
                if (f1292d) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + aVar);
                }
                this.p.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.p.add(null);
                    if (this.q == null) {
                        this.q = new ArrayList<>();
                    }
                    if (f1292d) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.q.add(Integer.valueOf(size));
                    size++;
                }
                if (f1292d) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + aVar);
                }
                this.p.add(aVar);
            }
        }
    }

    public void i1(Fragment fragment, d.b bVar) {
        if (this.k.get(fragment.g) == fragment && (fragment.u == null || fragment.D() == this)) {
            fragment.T = bVar;
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
        if (!fragment.o || fragment.r) {
            return;
        }
        fragment.R0(fragment.V0(fragment.f1273d), null, fragment.f1273d);
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
        fragment.J0(fragment.I, fragment.f1273d);
        S(fragment, fragment.I, fragment.f1273d, false);
    }

    public void j1(Fragment fragment) {
        if (fragment == null || (this.k.get(fragment.g) == fragment && (fragment.u == null || fragment.D() == this))) {
            Fragment fragment2 = this.x;
            this.x = fragment;
            W(fragment2);
            W(this.x);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void k(Fragment fragment, boolean z) {
        if (f1292d) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        I0(fragment);
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
        if (f1292d) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.A) {
            fragment.A = false;
            fragment.P = !fragment.P;
        }
    }

    void l(Fragment fragment) {
        if (G0()) {
            if (f1292d) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.J.d(fragment) && f1292d) {
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
                if (f1292d) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.p.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            int size = this.p.size();
            if (f1292d) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.p.add(aVar);
            return size;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void o(androidx.fragment.app.h hVar, androidx.fragment.app.e eVar, Fragment fragment) {
        if (this.u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.u = hVar;
        this.v = eVar;
        this.w = fragment;
        if (fragment != null) {
            o1();
        }
        if (hVar instanceof androidx.activity.c) {
            androidx.activity.c cVar = (androidx.activity.c) hVar;
            OnBackPressedDispatcher onBackPressedDispatcherC = cVar.c();
            this.n = onBackPressedDispatcherC;
            androidx.lifecycle.g gVar = cVar;
            if (fragment != null) {
                gVar = fragment;
            }
            onBackPressedDispatcherC.a(gVar, this.o);
        }
        this.J = fragment != null ? fragment.t.v0(fragment) : hVar instanceof androidx.lifecycle.s ? androidx.fragment.app.l.g(((androidx.lifecycle.s) hVar).o()) : new androidx.fragment.app.l(false);
    }

    public Fragment o0(int i2) {
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

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0042j.f1315a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !androidx.fragment.app.g.b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentO0 = resourceId != -1 ? o0(resourceId) : null;
        if (fragmentO0 == null && string != null) {
            fragmentO0 = c(string);
        }
        if (fragmentO0 == null && id != -1) {
            fragmentO0 = o0(id);
        }
        if (f1292d) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentO0);
        }
        if (fragmentO0 == null) {
            fragmentO0 = d().a(context.getClassLoader(), str2);
            fragmentO0.o = true;
            fragmentO0.x = resourceId != 0 ? resourceId : id;
            fragmentO0.y = id;
            fragmentO0.z = string;
            fragmentO0.p = true;
            fragmentO0.t = this;
            androidx.fragment.app.h hVar = this.u;
            fragmentO0.u = hVar;
            fragmentO0.w0(hVar.h(), attributeSet, fragmentO0.f1273d);
            k(fragmentO0, true);
        } else {
            if (fragmentO0.p) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentO0.p = true;
            androidx.fragment.app.h hVar2 = this.u;
            fragmentO0.u = hVar2;
            fragmentO0.w0(hVar2.h(), attributeSet, fragmentO0.f1273d);
        }
        Fragment fragment = fragmentO0;
        if (this.t >= 1 || !fragment.o) {
            P0(fragment);
        } else {
            Q0(fragment, 1, 0, 0, false);
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

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public void p(Fragment fragment) {
        if (f1292d) {
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
            if (f1292d) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.j) {
                this.j.add(fragment);
            }
            fragment.m = true;
            if (D0(fragment)) {
                this.y = true;
            }
        }
    }

    public Fragment p0(String str) {
        Fragment fragmentL;
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && (fragmentL = fragment.l(str)) != null) {
                return fragmentL;
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
            if (f1292d) {
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
        Object obj = this.w;
        if (obj == null) {
            obj = this.u;
        }
        a.g.j.a.a(obj, sb);
        sb.append("}}");
        return sb.toString();
    }

    void u(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.o(z3);
        } else {
            aVar.n();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            o.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            O0(this.t, true);
        }
        for (Fragment fragment : this.k.values()) {
            if (fragment != null && fragment.I != null && fragment.O && aVar.r(fragment.y)) {
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

    public int u0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.l;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void v(Fragment fragment) {
        Animator animator;
        if (fragment.I != null) {
            g gVarH0 = H0(fragment, fragment.H(), !fragment.A, fragment.I());
            if (gVarH0 == null || (animator = gVarH0.f1309b) == null) {
                if (gVarH0 != null) {
                    fragment.I.startAnimation(gVarH0.f1308a);
                    gVarH0.f1308a.start();
                }
                fragment.I.setVisibility((!fragment.A || fragment.Y()) ? 0 : 8);
                if (fragment.Y()) {
                    fragment.r1(false);
                }
            } else {
                animator.setTarget(fragment.I);
                if (!fragment.A) {
                    fragment.I.setVisibility(0);
                } else if (fragment.Y()) {
                    fragment.r1(false);
                } else {
                    ViewGroup viewGroup = fragment.H;
                    View view = fragment.I;
                    viewGroup.startViewTransition(view);
                    gVarH0.f1309b.addListener(new e(viewGroup, view, fragment));
                }
                gVarH0.f1309b.start();
            }
        }
        if (fragment.m && D0(fragment)) {
            this.y = true;
        }
        fragment.P = false;
        fragment.u0(fragment.A);
    }

    androidx.fragment.app.l v0(Fragment fragment) {
        return this.J.f(fragment);
    }

    public void w(Fragment fragment) {
        if (f1292d) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.B) {
            return;
        }
        fragment.B = true;
        if (fragment.m) {
            if (f1292d) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.j) {
                this.j.remove(fragment);
            }
            if (D0(fragment)) {
                this.y = true;
            }
            fragment.m = false;
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
                fragment.N0(configuration);
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
            if (fragment != null && fragment.O0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    androidx.lifecycle.r z0(Fragment fragment) {
        return this.J.i(fragment);
    }
}
