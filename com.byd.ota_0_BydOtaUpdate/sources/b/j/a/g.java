package b.j.a;

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
import b.j.a.f;
import b.l.p;
import b.l.q;
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
public final class g extends b.j.a.f implements LayoutInflater.Factory2 {
    public static boolean s = false;
    public static final Interpolator x = new DecelerateInterpolator(2.5f);
    public static final Interpolator y = new DecelerateInterpolator(1.5f);
    public ArrayList<k> E3;
    public boolean F3;
    public ArrayList<b.j.a.a> J3;
    public ArrayList<Fragment> K3;
    public OnBackPressedDispatcher L3;
    public ArrayList<b.j.a.a> N3;
    public ArrayList<Integer> O3;
    public ArrayList<f.b> P3;
    public b.j.a.e S3;
    public b.j.a.b T3;
    public Fragment U3;
    public Fragment V3;
    public boolean W3;
    public boolean X3;
    public boolean Y3;
    public boolean Z3;
    public boolean a4;
    public ArrayList<b.j.a.a> b4;
    public ArrayList<Boolean> c4;
    public ArrayList<Fragment> d4;
    public ArrayList<l> g4;
    public b.j.a.h h4;
    public int G3 = 0;
    public final ArrayList<Fragment> H3 = new ArrayList<>();
    public final HashMap<String, Fragment> I3 = new HashMap<>();
    public final b.a.b M3 = new a(false);
    public final CopyOnWriteArrayList<i> Q3 = new CopyOnWriteArrayList<>();
    public int R3 = 0;
    public Bundle e4 = null;
    public SparseArray<Parcelable> f4 = null;
    public Runnable i4 = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class a extends b.a.b {
        public a(boolean z) {
            super(z);
        }

        @Override // b.a.b
        public void b() {
            g.this.A0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.k0();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1874a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Fragment f1875b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f1875b.l() != null) {
                    c.this.f1875b.b1(null);
                    c cVar = c.this;
                    g gVar = g.this;
                    Fragment fragment = cVar.f1875b;
                    gVar.Q0(fragment, fragment.G(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.f1874a = viewGroup;
            this.f1875b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1874a.post(new a());
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
        public final /* synthetic */ ViewGroup f1878a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1879b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f1880c;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1878a = viewGroup;
            this.f1879b = view;
            this.f1880c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1878a.endViewTransition(this.f1879b);
            Animator animatorM = this.f1880c.m();
            this.f1880c.c1(null);
            if (animatorM == null || this.f1878a.indexOfChild(this.f1879b) >= 0) {
                return;
            }
            g gVar = g.this;
            Fragment fragment = this.f1880c;
            gVar.Q0(fragment, fragment.G(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1882a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1883b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Fragment f1884c;

        public e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1882a = viewGroup;
            this.f1883b = view;
            this.f1884c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1882a.endViewTransition(this.f1883b);
            animator.removeListener(this);
            Fragment fragment = this.f1884c;
            View view = fragment.g4;
            if (view == null || !fragment.Y3) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class f extends b.j.a.d {
        public f() {
        }

        @Override // b.j.a.d
        public Fragment a(ClassLoader classLoader, String str) {
            b.j.a.e eVar = g.this.S3;
            return eVar.d(eVar.i(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f.a f1891a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1892b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1893a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public interface k {
        boolean a(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class l implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f1894a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b.j.a.a f1895b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1896c;

        public l(b.j.a.a aVar, boolean z) {
            this.f1894a = z;
            this.f1895b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            int i2 = this.f1896c - 1;
            this.f1896c = i2;
            if (i2 != 0) {
                return;
            }
            this.f1895b.s.g1();
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            this.f1896c++;
        }

        public void c() {
            b.j.a.a aVar = this.f1895b;
            aVar.s.u(aVar, this.f1894a, false, false);
        }

        public void d() {
            boolean z = this.f1896c > 0;
            g gVar = this.f1895b.s;
            int size = gVar.H3.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = gVar.H3.get(i2);
                fragment.h1(null);
                if (z && fragment.P()) {
                    fragment.j1();
                }
            }
            b.j.a.a aVar = this.f1895b;
            aVar.s.u(aVar, this.f1894a, !z, true);
        }

        public boolean e() {
            return this.f1896c == 0;
        }
    }

    public static C0035g J0(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(y);
        alphaAnimation.setDuration(220L);
        return new C0035g(alphaAnimation);
    }

    public static C0035g L0(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(x);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(y);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new C0035g(animationSet);
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

    public static void l0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            b.j.a.a aVar = arrayList.get(i2);
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

    public void A() {
        this.X3 = false;
        this.Y3 = false;
        d0(1);
    }

    public void A0() {
        k0();
        if (this.M3.c()) {
            g();
        } else {
            this.L3.c();
        }
    }

    public boolean B(Menu menu, MenuInflater menuInflater) {
        if (this.R3 < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null && fragment.E0(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.K3 != null) {
            for (int i3 = 0; i3 < this.K3.size(); i3++) {
                Fragment fragment2 = this.K3.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.e0();
                }
            }
        }
        this.K3 = arrayList;
        return z;
    }

    public void B0(Fragment fragment) {
        if (s) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.Y3) {
            return;
        }
        fragment.Y3 = true;
        fragment.n4 = true ^ fragment.n4;
    }

    public void C() {
        this.Z3 = true;
        k0();
        d0(0);
        this.S3 = null;
        this.T3 = null;
        this.U3 = null;
        if (this.L3 != null) {
            this.M3.d();
            this.L3 = null;
        }
    }

    public boolean C0() {
        return this.Z3;
    }

    public void D() {
        d0(1);
    }

    public final boolean D0(Fragment fragment) {
        return (fragment.c4 && fragment.d4) || fragment.T3.r();
    }

    public void E() {
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null) {
                fragment.K0();
            }
        }
    }

    public boolean E0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        g gVar = fragment.R3;
        return fragment == gVar.y0() && E0(gVar.U3);
    }

    public void F(boolean z) {
        for (int size = this.H3.size() - 1; size >= 0; size--) {
            Fragment fragment = this.H3.get(size);
            if (fragment != null) {
                fragment.L0(z);
            }
        }
    }

    public boolean F0(int i2) {
        return this.R3 >= i2;
    }

    public void G(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).G(fragment, bundle, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public boolean G0() {
        return this.X3 || this.Y3;
    }

    public void H(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).H(fragment, context, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public C0035g H0(Fragment fragment, int i2, boolean z, int i3) {
        int iN1;
        int iW = fragment.w();
        boolean z2 = false;
        fragment.f1(0);
        ViewGroup viewGroup = fragment.f4;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationZ = fragment.Z(i2, z, iW);
        if (animationZ != null) {
            return new C0035g(animationZ);
        }
        Animator animatorA0 = fragment.a0(i2, z, iW);
        if (animatorA0 != null) {
            return new C0035g(animatorA0);
        }
        if (iW != 0) {
            boolean zEquals = "anim".equals(this.S3.i().getResources().getResourceTypeName(iW));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.S3.i(), iW);
                    if (animationLoadAnimation != null) {
                        return new C0035g(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.S3.i(), iW);
                    if (animatorLoadAnimator != null) {
                        return new C0035g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (zEquals) {
                        throw e3;
                    }
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.S3.i(), iW);
                    if (animationLoadAnimation2 != null) {
                        return new C0035g(animationLoadAnimation2);
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
                if (i3 == 0 && this.S3.p()) {
                    i3 = this.S3.o();
                }
                if (i3 == 0) {
                }
                return null;
        }
    }

    public void I(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).I(fragment, bundle, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void I0(Fragment fragment) {
        if (this.I3.get(fragment.E3) != null) {
            return;
        }
        this.I3.put(fragment.E3, fragment);
        if (fragment.b4) {
            if (fragment.a4) {
                l(fragment);
            } else {
                Z0(fragment);
            }
            fragment.b4 = false;
        }
        if (s) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    public void J(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).J(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void K(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).K(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void K0(Fragment fragment) {
        if (this.I3.get(fragment.E3) == null) {
            return;
        }
        if (s) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.I3.values()) {
            if (fragment2 != null && fragment.E3.equals(fragment2.H3)) {
                fragment2.G3 = fragment;
                fragment2.H3 = null;
            }
        }
        this.I3.put(fragment.E3, null);
        Z0(fragment);
        String str = fragment.H3;
        if (str != null) {
            fragment.G3 = this.I3.get(str);
        }
        fragment.K();
    }

    public void L(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).L(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void M(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).M(fragment, context, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public final void M0(b.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentI = bVar.i(i2);
            if (!fragmentI.K3) {
                View viewY0 = fragmentI.Y0();
                fragmentI.o4 = viewY0.getAlpha();
                viewY0.setAlpha(0.0f);
            }
        }
    }

    public void N(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).N(fragment, bundle, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void N0(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.I3.containsKey(fragment.E3)) {
            if (s) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.R3 + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.R3;
        if (fragment.L3) {
            iMin = fragment.O() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        Q0(fragment, iMin, fragment.x(), fragment.y(), false);
        if (fragment.g4 != null) {
            Fragment fragmentQ0 = q0(fragment);
            if (fragmentQ0 != null) {
                View view = fragmentQ0.g4;
                ViewGroup viewGroup = fragment.f4;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.g4);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.g4, iIndexOfChild);
                }
            }
            if (fragment.m4 && fragment.f4 != null) {
                float f2 = fragment.o4;
                if (f2 > 0.0f) {
                    fragment.g4.setAlpha(f2);
                }
                fragment.o4 = 0.0f;
                fragment.m4 = false;
                C0035g c0035gH0 = H0(fragment, fragment.x(), true, fragment.y());
                if (c0035gH0 != null) {
                    Animation animation = c0035gH0.f1887a;
                    if (animation != null) {
                        fragment.g4.startAnimation(animation);
                    } else {
                        c0035gH0.f1888b.setTarget(fragment.g4);
                        c0035gH0.f1888b.start();
                    }
                }
            }
        }
        if (fragment.n4) {
            v(fragment);
        }
    }

    public void O(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).O(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void O0(int i2, boolean z) {
        b.j.a.e eVar;
        if (this.S3 == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.R3) {
            this.R3 = i2;
            int size = this.H3.size();
            for (int i3 = 0; i3 < size; i3++) {
                N0(this.H3.get(i3));
            }
            for (Fragment fragment : this.I3.values()) {
                if (fragment != null && (fragment.L3 || fragment.Z3)) {
                    if (!fragment.m4) {
                        N0(fragment);
                    }
                }
            }
            l1();
            if (this.W3 && (eVar = this.S3) != null && this.R3 == 4) {
                eVar.r();
                this.W3 = false;
            }
        }
    }

    public void P(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).P(fragment, bundle, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void P0(Fragment fragment) {
        Q0(fragment, this.R3, 0, 0, false);
    }

    public void Q(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).Q(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
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
        throw new UnsupportedOperationException("Method not decompiled: b.j.a.g.Q0(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public void R(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).R(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void R0() {
        this.X3 = false;
        this.Y3 = false;
        int size = this.H3.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null) {
                fragment.R();
            }
        }
    }

    public void S(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).S(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public void S0(Fragment fragment) {
        if (fragment.i4) {
            if (this.F3) {
                this.a4 = true;
            } else {
                fragment.i4 = false;
                Q0(fragment, this.R3, 0, 0, false);
            }
        }
    }

    public void T(Fragment fragment, boolean z) {
        Fragment fragment2 = this.U3;
        if (fragment2 != null) {
            b.j.a.f fVarT = fragment2.t();
            if (fVarT instanceof g) {
                ((g) fVarT).T(fragment, true);
            }
        }
        for (i iVar : this.Q3) {
            if (!z || iVar.f1892b) {
                f.a aVar = iVar.f1891a;
                throw null;
            }
        }
    }

    public final boolean T0(String str, int i2, int i3) {
        k0();
        i0(true);
        Fragment fragment = this.V3;
        if (fragment != null && i2 < 0 && str == null && fragment.n().g()) {
            return true;
        }
        boolean zU0 = U0(this.b4, this.c4, str, i2, i3);
        if (zU0) {
            this.F3 = true;
            try {
                Y0(this.b4, this.c4);
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
        if (this.R3 < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null && fragment.M0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean U0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<b.j.a.a> arrayList3 = this.J3;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.J3.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    b.j.a.a aVar = this.J3.get(size2);
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
                        b.j.a.a aVar2 = this.J3.get(size2);
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
            if (i4 == this.J3.size() - 1) {
                return false;
            }
            for (int size3 = this.J3.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.J3.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void V(Menu menu) {
        if (this.R3 < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null) {
                fragment.N0(menu);
            }
        }
    }

    public final int V0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, b.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            b.j.a.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.s() && !aVar.q(arrayList, i5 + 1, i3)) {
                if (this.g4 == null) {
                    this.g4 = new ArrayList<>();
                }
                l lVar = new l(aVar, zBooleanValue);
                this.g4.add(lVar);
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

    public final void W(Fragment fragment) {
        if (fragment == null || this.I3.get(fragment.E3) != fragment) {
            return;
        }
        fragment.R0();
    }

    public void W0(Bundle bundle, String str, Fragment fragment) {
        if (fragment.R3 != this) {
            m1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.E3);
    }

    public void X() {
        d0(3);
    }

    public void X0(Fragment fragment) {
        if (s) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.Q3);
        }
        boolean z = !fragment.O();
        if (!fragment.Z3 || z) {
            synchronized (this.H3) {
                this.H3.remove(fragment);
            }
            if (D0(fragment)) {
                this.W3 = true;
            }
            fragment.K3 = false;
            fragment.L3 = true;
        }
    }

    public void Y(boolean z) {
        for (int size = this.H3.size() - 1; size >= 0; size--) {
            Fragment fragment = this.H3.get(size);
            if (fragment != null) {
                fragment.P0(z);
            }
        }
    }

    public final void Y0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2) {
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

    public boolean Z(Menu menu) {
        if (this.R3 < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null && fragment.Q0(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void Z0(Fragment fragment) {
        if (G0()) {
            if (s) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.h4.k(fragment) && s) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    @Override // b.j.a.f
    public b.j.a.i a() {
        return new b.j.a.a(this);
    }

    public void a0() {
        o1();
        W(this.V3);
    }

    public void a1() {
        if (this.P3 != null) {
            for (int i2 = 0; i2 < this.P3.size(); i2++) {
                this.P3.get(i2).a();
            }
        }
    }

    @Override // b.j.a.f
    public void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.I3.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (Fragment fragment : this.I3.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.e(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.H3.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment fragment2 = this.H3.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.K3;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                Fragment fragment3 = this.K3.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<b.j.a.a> arrayList2 = this.J3;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                b.j.a.a aVar = this.J3.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.j(str2, printWriter);
            }
        }
        synchronized (this) {
            ArrayList<b.j.a.a> arrayList3 = this.N3;
            if (arrayList3 != null && (size2 = arrayList3.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    Object obj = (b.j.a.a) this.N3.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            ArrayList<Integer> arrayList4 = this.O3;
            if (arrayList4 != null && arrayList4.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.O3.toArray()));
            }
        }
        ArrayList<k> arrayList5 = this.E3;
        if (arrayList5 != null && (size = arrayList5.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                Object obj2 = (k) this.E3.get(i6);
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
        printWriter.println(this.S3);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.T3);
        if (this.U3 != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.U3);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.R3);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.X3);
        printWriter.print(" mStopped=");
        printWriter.print(this.Y3);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.Z3);
        if (this.W3) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.W3);
        }
    }

    public void b0() {
        this.X3 = false;
        this.Y3 = false;
        d0(4);
    }

    public void b1(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f509c == null) {
            return;
        }
        for (Fragment fragment : this.h4.h()) {
            if (s) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f509c.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.f512d.equals(fragment.E3)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (s) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f509c);
                }
                Q0(fragment, 1, 0, 0, false);
                fragment.L3 = true;
                Q0(fragment, 0, 0, 0, false);
            } else {
                next.M3 = fragment;
                fragment.x = null;
                fragment.Q3 = 0;
                fragment.N3 = false;
                fragment.K3 = false;
                Fragment fragment2 = fragment.G3;
                fragment.H3 = fragment2 != null ? fragment2.E3 : null;
                fragment.G3 = null;
                Bundle bundle = next.L3;
                if (bundle != null) {
                    bundle.setClassLoader(this.S3.i().getClassLoader());
                    fragment.x = next.L3.getSparseParcelableArray("android:view_state");
                    fragment.s = next.L3;
                }
            }
        }
        this.I3.clear();
        for (FragmentState fragmentState : fragmentManagerState.f509c) {
            if (fragmentState != null) {
                Fragment fragmentA = fragmentState.a(this.S3.i().getClassLoader(), e());
                fragmentA.R3 = this;
                if (s) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentA.E3 + "): " + fragmentA);
                }
                this.I3.put(fragmentA.E3, fragmentA);
                fragmentState.M3 = null;
            }
        }
        this.H3.clear();
        ArrayList<String> arrayList = fragmentManagerState.f510d;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.I3.get(str);
                if (fragment3 == null) {
                    m1(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                }
                fragment3.K3 = true;
                if (s) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                }
                if (this.H3.contains(fragment3)) {
                    throw new IllegalStateException("Already added " + fragment3);
                }
                synchronized (this.H3) {
                    this.H3.add(fragment3);
                }
            }
        }
        if (fragmentManagerState.s != null) {
            this.J3 = new ArrayList<>(fragmentManagerState.s.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.s;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                b.j.a.a aVarA = backStackStateArr[i2].a(this);
                if (s) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarA.u + "): " + aVarA);
                    PrintWriter printWriter = new PrintWriter(new b.f.l.b("FragmentManager"));
                    aVarA.k("  ", printWriter, false);
                    printWriter.close();
                }
                this.J3.add(aVarA);
                int i3 = aVarA.u;
                if (i3 >= 0) {
                    h1(i3, aVarA);
                }
                i2++;
            }
        } else {
            this.J3 = null;
        }
        String str2 = fragmentManagerState.x;
        if (str2 != null) {
            Fragment fragment4 = this.I3.get(str2);
            this.V3 = fragment4;
            W(fragment4);
        }
        this.G3 = fragmentManagerState.y;
    }

    @Override // b.j.a.f
    public boolean c() {
        boolean zK0 = k0();
        r0();
        return zK0;
    }

    public void c0() {
        this.X3 = false;
        this.Y3 = false;
        d0(3);
    }

    @Override // b.j.a.f
    public Fragment d(String str) {
        if (str != null) {
            for (int size = this.H3.size() - 1; size >= 0; size--) {
                Fragment fragment = this.H3.get(size);
                if (fragment != null && str.equals(fragment.X3)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.I3.values()) {
            if (fragment2 != null && str.equals(fragment2.X3)) {
                return fragment2;
            }
        }
        return null;
    }

    public final void d0(int i2) {
        try {
            this.F3 = true;
            O0(i2, false);
            this.F3 = false;
            k0();
        } catch (Throwable th) {
            this.F3 = false;
            throw th;
        }
    }

    public Parcelable d1() {
        ArrayList<String> arrayList;
        int size;
        r0();
        g0();
        k0();
        this.X3 = true;
        BackStackState[] backStackStateArr = null;
        if (this.I3.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.I3.size());
        boolean z = false;
        for (Fragment fragment : this.I3.values()) {
            if (fragment != null) {
                if (fragment.R3 != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.f494d <= 0 || fragmentState.L3 != null) {
                    fragmentState.L3 = fragment.s;
                } else {
                    fragmentState.L3 = e1(fragment);
                    String str = fragment.H3;
                    if (str != null) {
                        Fragment fragment2 = this.I3.get(str);
                        if (fragment2 == null) {
                            m1(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.H3));
                        }
                        if (fragmentState.L3 == null) {
                            fragmentState.L3 = new Bundle();
                        }
                        W0(fragmentState.L3, "android:target_state", fragment2);
                        int i2 = fragment.I3;
                        if (i2 != 0) {
                            fragmentState.L3.putInt("android:target_req_state", i2);
                        }
                    }
                }
                if (s) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.L3);
                }
                z = true;
            }
        }
        if (!z) {
            if (s) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.H3.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.H3) {
                arrayList.add(fragment3.E3);
                if (fragment3.R3 != this) {
                    m1(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                }
                if (s) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.E3 + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<b.j.a.a> arrayList3 = this.J3;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState(this.J3.get(i3));
                if (s) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.J3.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f509c = arrayList2;
        fragmentManagerState.f510d = arrayList;
        fragmentManagerState.s = backStackStateArr;
        Fragment fragment4 = this.V3;
        if (fragment4 != null) {
            fragmentManagerState.x = fragment4.E3;
        }
        fragmentManagerState.y = this.G3;
        return fragmentManagerState;
    }

    @Override // b.j.a.f
    public b.j.a.d e() {
        if (super.e() == b.j.a.f.f1870c) {
            Fragment fragment = this.U3;
            if (fragment != null) {
                return fragment.R3.e();
            }
            h(new f());
        }
        return super.e();
    }

    public void e0() {
        this.Y3 = true;
        d0(2);
    }

    public Bundle e1(Fragment fragment) {
        if (this.e4 == null) {
            this.e4 = new Bundle();
        }
        fragment.T0(this.e4);
        P(fragment, this.e4, false);
        Bundle bundle = null;
        if (!this.e4.isEmpty()) {
            Bundle bundle2 = this.e4;
            this.e4 = null;
            bundle = bundle2;
        }
        if (fragment.g4 != null) {
            f1(fragment);
        }
        if (fragment.x != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.x);
        }
        if (!fragment.j4) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.j4);
        }
        return bundle;
    }

    @Override // b.j.a.f
    public List<Fragment> f() {
        List<Fragment> list;
        if (this.H3.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.H3) {
            list = (List) this.H3.clone();
        }
        return list;
    }

    public void f0() {
        if (this.a4) {
            this.a4 = false;
            l1();
        }
    }

    public void f1(Fragment fragment) {
        if (fragment.h4 == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.f4;
        if (sparseArray == null) {
            this.f4 = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.h4.saveHierarchyState(this.f4);
        if (this.f4.size() > 0) {
            fragment.x = this.f4;
            this.f4 = null;
        }
    }

    @Override // b.j.a.f
    public boolean g() {
        s();
        return T0(null, -1, 0);
    }

    public final void g0() {
        for (Fragment fragment : this.I3.values()) {
            if (fragment != null) {
                if (fragment.l() != null) {
                    int iG = fragment.G();
                    View viewL = fragment.l();
                    Animation animation = viewL.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        viewL.clearAnimation();
                    }
                    fragment.b1(null);
                    Q0(fragment, iG, 0, 0, false);
                } else if (fragment.m() != null) {
                    fragment.m().end();
                }
            }
        }
    }

    public void g1() {
        synchronized (this) {
            ArrayList<l> arrayList = this.g4;
            boolean z = false;
            boolean z2 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            ArrayList<k> arrayList2 = this.E3;
            if (arrayList2 != null && arrayList2.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.S3.j().removeCallbacks(this.i4);
                this.S3.j().post(this.i4);
                o1();
            }
        }
    }

    public void h0(k kVar, boolean z) {
        if (!z) {
            s();
        }
        synchronized (this) {
            if (!this.Z3 && this.S3 != null) {
                if (this.E3 == null) {
                    this.E3 = new ArrayList<>();
                }
                this.E3.add(kVar);
                g1();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void h1(int i2, b.j.a.a aVar) {
        synchronized (this) {
            if (this.N3 == null) {
                this.N3 = new ArrayList<>();
            }
            int size = this.N3.size();
            if (i2 < size) {
                if (s) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + aVar);
                }
                this.N3.set(i2, aVar);
            } else {
                while (size < i2) {
                    this.N3.add(null);
                    if (this.O3 == null) {
                        this.O3 = new ArrayList<>();
                    }
                    if (s) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.O3.add(Integer.valueOf(size));
                    size++;
                }
                if (s) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + aVar);
                }
                this.N3.add(aVar);
            }
        }
    }

    public final void i(b.d.b<Fragment> bVar) {
        int i2 = this.R3;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        int size = this.H3.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.H3.get(i3);
            if (fragment.f494d < iMin) {
                Q0(fragment, iMin, fragment.w(), fragment.x(), false);
                if (fragment.g4 != null && !fragment.Y3 && fragment.m4) {
                    bVar.add(fragment);
                }
            }
        }
    }

    public final void i0(boolean z) {
        if (this.F3) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.S3 == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.S3.j().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            s();
        }
        if (this.b4 == null) {
            this.b4 = new ArrayList<>();
            this.c4 = new ArrayList<>();
        }
        this.F3 = true;
        try {
            n0(null, null);
        } finally {
            this.F3 = false;
        }
    }

    public void i1(Fragment fragment, Lifecycle.State state) {
        if (this.I3.get(fragment.E3) == fragment && (fragment.S3 == null || fragment.t() == this)) {
            fragment.r4 = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void j(b.j.a.a aVar) {
        if (this.J3 == null) {
            this.J3 = new ArrayList<>();
        }
        this.J3.add(aVar);
    }

    public void j0(Fragment fragment) {
        if (!fragment.M3 || fragment.P3) {
            return;
        }
        fragment.F0(fragment.J0(fragment.s), null, fragment.s);
        View view = fragment.g4;
        if (view == null) {
            fragment.h4 = null;
            return;
        }
        fragment.h4 = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.Y3) {
            fragment.g4.setVisibility(8);
        }
        fragment.x0(fragment.g4, fragment.s);
        S(fragment, fragment.g4, fragment.s, false);
    }

    public void j1(Fragment fragment) {
        if (fragment == null || (this.I3.get(fragment.E3) == fragment && (fragment.S3 == null || fragment.t() == this))) {
            Fragment fragment2 = this.V3;
            this.V3 = fragment;
            W(fragment2);
            W(this.V3);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void k(Fragment fragment, boolean z) {
        if (s) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        I0(fragment);
        if (fragment.Z3) {
            return;
        }
        if (this.H3.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.H3) {
            this.H3.add(fragment);
        }
        fragment.K3 = true;
        fragment.L3 = false;
        if (fragment.g4 == null) {
            fragment.n4 = false;
        }
        if (D0(fragment)) {
            this.W3 = true;
        }
        if (z) {
            P0(fragment);
        }
    }

    public boolean k0() {
        i0(true);
        boolean z = false;
        while (t0(this.b4, this.c4)) {
            this.F3 = true;
            try {
                Y0(this.b4, this.c4);
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
        if (s) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.Y3) {
            fragment.Y3 = false;
            fragment.n4 = !fragment.n4;
        }
    }

    public void l(Fragment fragment) {
        if (G0()) {
            if (s) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.h4.d(fragment) && s) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    public void l1() {
        for (Fragment fragment : this.I3.values()) {
            if (fragment != null) {
                S0(fragment);
            }
        }
    }

    public int m(b.j.a.a aVar) {
        synchronized (this) {
            ArrayList<Integer> arrayList = this.O3;
            if (arrayList != null && arrayList.size() > 0) {
                int iIntValue = this.O3.remove(r0.size() - 1).intValue();
                if (s) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.N3.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.N3 == null) {
                this.N3 = new ArrayList<>();
            }
            int size = this.N3.size();
            if (s) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.N3.add(aVar);
            return size;
        }
    }

    public final void m0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i2;
        boolean z = arrayList.get(i6).q;
        ArrayList<Fragment> arrayList3 = this.d4;
        if (arrayList3 == null) {
            this.d4 = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.d4.addAll(this.H3);
        Fragment fragmentY0 = y0();
        boolean z2 = false;
        for (int i7 = i6; i7 < i3; i7++) {
            b.j.a.a aVar = arrayList.get(i7);
            fragmentY0 = !arrayList2.get(i7).booleanValue() ? aVar.n(this.d4, fragmentY0) : aVar.v(this.d4, fragmentY0);
            z2 = z2 || aVar.f1911h;
        }
        this.d4.clear();
        if (!z) {
            b.j.a.j.B(this, arrayList, arrayList2, i2, i3, false);
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
            b.j.a.j.B(this, arrayList, arrayList2, i2, i4, true);
            O0(this.R3, true);
        }
        while (i6 < i3) {
            b.j.a.a aVar2 = arrayList.get(i6);
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

    public final void m1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new b.f.l.b("FragmentManager"));
        b.j.a.e eVar = this.S3;
        if (eVar != null) {
            try {
                eVar.l("  ", null, printWriter, new String[0]);
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

    public final void n(Fragment fragment, C0035g c0035g, int i2) {
        View view = fragment.g4;
        ViewGroup viewGroup = fragment.f4;
        viewGroup.startViewTransition(view);
        fragment.i1(i2);
        Animation animation = c0035g.f1887a;
        if (animation != null) {
            h hVar = new h(animation, viewGroup, view);
            fragment.b1(fragment.g4);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.g4.startAnimation(hVar);
            return;
        }
        Animator animator = c0035g.f1888b;
        fragment.c1(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.g4);
        animator.start();
    }

    public final void n0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<l> arrayList3 = this.g4;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            l lVar = this.g4.get(i2);
            if (arrayList != null && !lVar.f1894a && (iIndexOf2 = arrayList.indexOf(lVar.f1895b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.g4.remove(i2);
                i2--;
                size--;
                lVar.c();
            } else if (lVar.e() || (arrayList != null && lVar.f1895b.q(arrayList, 0, arrayList.size()))) {
                this.g4.remove(i2);
                i2--;
                size--;
                if (arrayList == null || lVar.f1894a || (iIndexOf = arrayList.indexOf(lVar.f1895b)) == -1 || !arrayList2.get(iIndexOf).booleanValue()) {
                    lVar.d();
                } else {
                    lVar.c();
                }
            }
            i2++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void o(b.j.a.e eVar, b.j.a.b bVar, Fragment fragment) {
        if (this.S3 != null) {
            throw new IllegalStateException("Already attached");
        }
        this.S3 = eVar;
        this.T3 = bVar;
        this.U3 = fragment;
        if (fragment != null) {
            o1();
        }
        if (eVar instanceof b.a.c) {
            b.a.c cVar = (b.a.c) eVar;
            OnBackPressedDispatcher onBackPressedDispatcherB = cVar.b();
            this.L3 = onBackPressedDispatcherB;
            b.l.f fVar = cVar;
            if (fragment != null) {
                fVar = fragment;
            }
            onBackPressedDispatcherB.a(fVar, this.M3);
        }
        if (fragment != null) {
            this.h4 = fragment.R3.v0(fragment);
        } else if (eVar instanceof q) {
            this.h4 = b.j.a.h.g(((q) eVar).h());
        } else {
            this.h4 = new b.j.a.h(false);
        }
    }

    public Fragment o0(int i2) {
        for (int size = this.H3.size() - 1; size >= 0; size--) {
            Fragment fragment = this.H3.get(size);
            if (fragment != null && fragment.V3 == i2) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.I3.values()) {
            if (fragment2 != null && fragment2.V3 == i2) {
                return fragment2;
            }
        }
        return null;
    }

    public final void o1() {
        ArrayList<k> arrayList = this.E3;
        if (arrayList == null || arrayList.isEmpty()) {
            this.M3.f(u0() > 0 && E0(this.U3));
        } else {
            this.M3.f(true);
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f1893a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !b.j.a.d.b(context.getClassLoader(), str2)) {
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
        if (s) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentO0);
        }
        if (fragmentO0 == null) {
            fragmentO0 = e().a(context.getClassLoader(), str2);
            fragmentO0.M3 = true;
            fragmentO0.V3 = resourceId != 0 ? resourceId : id;
            fragmentO0.W3 = id;
            fragmentO0.X3 = string;
            fragmentO0.N3 = true;
            fragmentO0.R3 = this;
            b.j.a.e eVar = this.S3;
            fragmentO0.S3 = eVar;
            fragmentO0.k0(eVar.i(), attributeSet, fragmentO0.s);
            k(fragmentO0, true);
        } else {
            if (fragmentO0.N3) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentO0.N3 = true;
            b.j.a.e eVar2 = this.S3;
            fragmentO0.S3 = eVar2;
            fragmentO0.k0(eVar2.i(), attributeSet, fragmentO0.s);
        }
        Fragment fragment = fragmentO0;
        if (this.R3 >= 1 || !fragment.M3) {
            P0(fragment);
        } else {
            Q0(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.g4;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.g4.getTag() == null) {
                fragment.g4.setTag(string);
            }
            return fragment.g4;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    public void p(Fragment fragment) {
        if (s) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.Z3) {
            fragment.Z3 = false;
            if (fragment.K3) {
                return;
            }
            if (this.H3.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (s) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.H3) {
                this.H3.add(fragment);
            }
            fragment.K3 = true;
            if (D0(fragment)) {
                this.W3 = true;
            }
        }
    }

    public Fragment p0(String str) {
        Fragment fragmentG;
        for (Fragment fragment : this.I3.values()) {
            if (fragment != null && (fragmentG = fragment.g(str)) != null) {
                return fragmentG;
            }
        }
        return null;
    }

    public final void q() {
        this.I3.values().removeAll(Collections.singleton(null));
    }

    public final Fragment q0(Fragment fragment) {
        ViewGroup viewGroup = fragment.f4;
        View view = fragment.g4;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.H3.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.H3.get(iIndexOf);
                if (fragment2.f4 == viewGroup && fragment2.g4 != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    public boolean r() {
        boolean zD0 = false;
        for (Fragment fragment : this.I3.values()) {
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
        if (this.g4 != null) {
            while (!this.g4.isEmpty()) {
                this.g4.remove(0).d();
            }
        }
    }

    public final void s() {
        if (G0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public void s0(int i2) {
        synchronized (this) {
            this.N3.set(i2, null);
            if (this.O3 == null) {
                this.O3 = new ArrayList<>();
            }
            if (s) {
                Log.v("FragmentManager", "Freeing back stack index " + i2);
            }
            this.O3.add(Integer.valueOf(i2));
        }
    }

    public final void t() {
        this.F3 = false;
        this.c4.clear();
        this.b4.clear();
    }

    public final boolean t0(ArrayList<b.j.a.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            ArrayList<k> arrayList3 = this.E3;
            if (arrayList3 != null && arrayList3.size() != 0) {
                int size = this.E3.size();
                boolean zA = false;
                for (int i2 = 0; i2 < size; i2++) {
                    zA |= this.E3.get(i2).a(arrayList, arrayList2);
                }
                this.E3.clear();
                this.S3.j().removeCallbacks(this.i4);
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
        Fragment fragment = this.U3;
        if (fragment != null) {
            b.f.l.a.a(fragment, sb);
        } else {
            b.f.l.a.a(this.S3, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u(b.j.a.a aVar, boolean z, boolean z2, boolean z3) {
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
            b.j.a.j.B(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            O0(this.R3, true);
        }
        for (Fragment fragment : this.I3.values()) {
            if (fragment != null && fragment.g4 != null && fragment.m4 && aVar.p(fragment.W3)) {
                float f2 = fragment.o4;
                if (f2 > 0.0f) {
                    fragment.g4.setAlpha(f2);
                }
                if (z3) {
                    fragment.o4 = 0.0f;
                } else {
                    fragment.o4 = -1.0f;
                    fragment.m4 = false;
                }
            }
        }
    }

    public int u0() {
        ArrayList<b.j.a.a> arrayList = this.J3;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void v(Fragment fragment) {
        Animator animator;
        if (fragment.g4 != null) {
            C0035g c0035gH0 = H0(fragment, fragment.x(), !fragment.Y3, fragment.y());
            if (c0035gH0 == null || (animator = c0035gH0.f1888b) == null) {
                if (c0035gH0 != null) {
                    fragment.g4.startAnimation(c0035gH0.f1887a);
                    c0035gH0.f1887a.start();
                }
                fragment.g4.setVisibility((!fragment.Y3 || fragment.N()) ? 0 : 8);
                if (fragment.N()) {
                    fragment.e1(false);
                }
            } else {
                animator.setTarget(fragment.g4);
                if (!fragment.Y3) {
                    fragment.g4.setVisibility(0);
                } else if (fragment.N()) {
                    fragment.e1(false);
                } else {
                    ViewGroup viewGroup = fragment.f4;
                    View view = fragment.g4;
                    viewGroup.startViewTransition(view);
                    c0035gH0.f1888b.addListener(new e(viewGroup, view, fragment));
                }
                c0035gH0.f1888b.start();
            }
        }
        if (fragment.K3 && D0(fragment)) {
            this.W3 = true;
        }
        fragment.n4 = false;
        fragment.i0(fragment.Y3);
    }

    public b.j.a.h v0(Fragment fragment) {
        return this.h4.f(fragment);
    }

    public void w(Fragment fragment) {
        if (s) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.Z3) {
            return;
        }
        fragment.Z3 = true;
        if (fragment.K3) {
            if (s) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.H3) {
                this.H3.remove(fragment);
            }
            if (D0(fragment)) {
                this.W3 = true;
            }
            fragment.K3 = false;
        }
    }

    public Fragment w0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.I3.get(string);
        if (fragment == null) {
            m1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragment;
    }

    public void x() {
        this.X3 = false;
        this.Y3 = false;
        d0(2);
    }

    public LayoutInflater.Factory2 x0() {
        return this;
    }

    public void y(Configuration configuration) {
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null) {
                fragment.B0(configuration);
            }
        }
    }

    public Fragment y0() {
        return this.V3;
    }

    public boolean z(MenuItem menuItem) {
        if (this.R3 < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.H3.size(); i2++) {
            Fragment fragment = this.H3.get(i2);
            if (fragment != null && fragment.C0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public p z0(Fragment fragment) {
        return this.h4.i(fragment);
    }

    /* JADX INFO: renamed from: b.j.a.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class C0035g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1887a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1888b;

        public C0035g(Animation animation) {
            this.f1887a = animation;
            this.f1888b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public C0035g(Animator animator) {
            this.f1887a = null;
            this.f1888b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ViewGroup f1889c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final View f1890d;
        public boolean s;
        public boolean x;
        public boolean y;

        public h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.y = true;
            this.f1889c = viewGroup;
            this.f1890d = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.y = true;
            if (this.s) {
                return !this.x;
            }
            if (!super.getTransformation(j, transformation)) {
                this.s = true;
                b.f.m.q.a(this.f1889c, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.s || !this.y) {
                this.f1889c.endViewTransition(this.f1890d);
                this.x = true;
            } else {
                this.y = false;
                this.f1889c.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f2) {
            this.y = true;
            if (this.s) {
                return !this.x;
            }
            if (!super.getTransformation(j, transformation, f2)) {
                this.s = true;
                b.f.m.q.a(this.f1889c, this);
            }
            return true;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
