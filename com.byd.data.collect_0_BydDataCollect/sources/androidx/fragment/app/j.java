package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Configuration;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.c;
import androidx.fragment.app.p;
import androidx.lifecycle.d;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a */
    private static boolean f1445a = false;
    private ArrayList<androidx.fragment.app.a> A;
    private ArrayList<Boolean> B;
    private ArrayList<Fragment> C;
    private ArrayList<C0021j> D;
    private l E;

    /* JADX INFO: renamed from: c */
    private boolean f1447c;

    /* JADX INFO: renamed from: e */
    ArrayList<androidx.fragment.app.a> f1449e;

    /* JADX INFO: renamed from: f */
    private ArrayList<Fragment> f1450f;

    /* JADX INFO: renamed from: h */
    private OnBackPressedDispatcher f1452h;
    private ArrayList<g> k;
    androidx.fragment.app.g<?> p;
    androidx.fragment.app.d q;
    private Fragment r;
    Fragment s;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* JADX INFO: renamed from: b */
    private final ArrayList<h> f1446b = new ArrayList<>();

    /* JADX INFO: renamed from: d */
    private final n f1448d = new n();

    /* JADX INFO: renamed from: g */
    private final androidx.fragment.app.h f1451g = new androidx.fragment.app.h(this);

    /* JADX INFO: renamed from: i */
    private final androidx.activity.b f1453i = new a(false);
    private final AtomicInteger j = new AtomicInteger();
    private ConcurrentHashMap<Fragment, HashSet<b.g.g.a>> l = new ConcurrentHashMap<>();
    private final p.g m = new b();
    private final androidx.fragment.app.i n = new androidx.fragment.app.i(this);
    int o = -1;
    private androidx.fragment.app.f t = null;
    private androidx.fragment.app.f u = new c();
    private Runnable F = new d();

    /* JADX INFO: compiled from: FragmentManager.java */
    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            j.this.n0();
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    class b implements p.g {
        b() {
        }

        @Override // androidx.fragment.app.p.g
        public void a(Fragment fragment, b.g.g.a aVar) {
            if (aVar.b()) {
                return;
            }
            j.this.K0(fragment, aVar);
        }

        @Override // androidx.fragment.app.p.g
        public void b(Fragment fragment, b.g.g.a aVar) {
            j.this.c(fragment, aVar);
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    class c extends androidx.fragment.app.f {
        c() {
        }

        @Override // androidx.fragment.app.f
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.g<?> gVar = j.this.p;
            return gVar.b(gVar.h(), str, null);
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.Q(true);
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ ViewGroup f1458a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ View f1459b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ Fragment f1460c;

        e(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1458a = viewGroup;
            this.f1459b = view;
            this.f1460c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1458a.endViewTransition(this.f1459b);
            animator.removeListener(this);
            Fragment fragment = this.f1460c;
            View view = fragment.I;
            if (view == null || !fragment.A) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class f {
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface g {
        void a();
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    interface h {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    private class i implements h {

        /* JADX INFO: renamed from: a */
        final String f1462a;

        /* JADX INFO: renamed from: b */
        final int f1463b;

        /* JADX INFO: renamed from: c */
        final int f1464c;

        i(String str, int i2, int i3) {
            this.f1462a = str;
            this.f1463b = i2;
            this.f1464c = i3;
        }

        @Override // androidx.fragment.app.j.h
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = j.this.s;
            if (fragment == null || this.f1463b >= 0 || this.f1462a != null || !fragment.o().G0()) {
                return j.this.I0(arrayList, arrayList2, this.f1462a, this.f1463b, this.f1464c);
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.j$j */
    /* JADX INFO: compiled from: FragmentManager.java */
    static class C0021j implements Fragment.f {

        /* JADX INFO: renamed from: a */
        final boolean f1466a;

        /* JADX INFO: renamed from: b */
        final androidx.fragment.app.a f1467b;

        /* JADX INFO: renamed from: c */
        private int f1468c;

        C0021j(androidx.fragment.app.a aVar, boolean z) {
            this.f1466a = z;
            this.f1467b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void a() {
            int i2 = this.f1468c - 1;
            this.f1468c = i2;
            if (i2 != 0) {
                return;
            }
            this.f1467b.t.S0();
        }

        @Override // androidx.fragment.app.Fragment.f
        public void b() {
            this.f1468c++;
        }

        void c() {
            androidx.fragment.app.a aVar = this.f1467b;
            aVar.t.n(aVar, this.f1466a, false, false);
        }

        void d() {
            boolean z = this.f1468c > 0;
            for (Fragment fragment : this.f1467b.t.g0()) {
                fragment.m1(null);
                if (z && fragment.S()) {
                    fragment.o1();
                }
            }
            androidx.fragment.app.a aVar = this.f1467b;
            aVar.t.n(aVar, this.f1466a, !z, true);
        }

        public boolean e() {
            return this.f1468c == 0;
        }
    }

    private void C(Fragment fragment) {
        if (fragment == null || !fragment.equals(W(fragment.f1366g))) {
            return;
        }
        fragment.W0();
    }

    private boolean H0(String str, int i2, int i3) {
        Q(false);
        P(true);
        Fragment fragment = this.s;
        if (fragment != null && i2 < 0 && str == null && fragment.o().G0()) {
            return true;
        }
        boolean zI0 = I0(this.A, this.B, str, i2, i3);
        if (zI0) {
            this.f1447c = true;
            try {
                M0(this.A, this.B);
            } finally {
                m();
            }
        }
        Z0();
        L();
        this.f1448d.b();
        return zI0;
    }

    private void J(int i2) {
        try {
            this.f1447c = true;
            this.f1448d.d(i2);
            z0(i2, false);
            this.f1447c = false;
            Q(true);
        } catch (Throwable th) {
            this.f1447c = false;
            throw th;
        }
    }

    private int J0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, b.d.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.G() && !aVar.E(arrayList, i5 + 1, i3)) {
                if (this.D == null) {
                    this.D = new ArrayList<>();
                }
                C0021j c0021j = new C0021j(aVar, zBooleanValue);
                this.D.add(c0021j);
                aVar.I(c0021j);
                if (zBooleanValue) {
                    aVar.z();
                } else {
                    aVar.A(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                a(bVar);
            }
        }
        return i4;
    }

    private void L() {
        if (this.z) {
            this.z = false;
            Y0();
        }
    }

    private void M0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        V(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).r) {
                if (i3 != i2) {
                    T(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).r) {
                        i3++;
                    }
                }
                T(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            T(arrayList, arrayList2, i3, size);
        }
    }

    private void N() {
        if (this.l.isEmpty()) {
            return;
        }
        for (Fragment fragment : this.l.keySet()) {
            j(fragment);
            B0(fragment, fragment.H());
        }
    }

    private void O0() {
        if (this.k != null) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                this.k.get(i2).a();
            }
        }
    }

    private void P(boolean z) {
        if (this.f1447c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.p == null) {
            if (!this.y) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.p.i().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            l();
        }
        if (this.A == null) {
            this.A = new ArrayList<>();
            this.B = new ArrayList<>();
        }
        this.f1447c = true;
        try {
            V(null, null);
        } finally {
            this.f1447c = false;
        }
    }

    static int Q0(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private static void S(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.v(-1);
                aVar.A(i2 == i3 + (-1));
            } else {
                aVar.v(1);
                aVar.z();
            }
            i2++;
        }
    }

    private void T(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5 = i2;
        boolean z = arrayList.get(i5).r;
        ArrayList<Fragment> arrayList3 = this.C;
        if (arrayList3 == null) {
            this.C = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.C.addAll(this.f1448d.m());
        Fragment fragmentK0 = k0();
        boolean z2 = false;
        for (int i6 = i5; i6 < i3; i6++) {
            androidx.fragment.app.a aVar = arrayList.get(i6);
            fragmentK0 = !arrayList2.get(i6).booleanValue() ? aVar.B(this.C, fragmentK0) : aVar.J(this.C, fragmentK0);
            z2 = z2 || aVar.f1490i;
        }
        this.C.clear();
        if (!z) {
            p.B(this, arrayList, arrayList2, i2, i3, false, this.m);
        }
        S(arrayList, arrayList2, i2, i3);
        if (z) {
            b.d.b<Fragment> bVar = new b.d.b<>();
            a(bVar);
            int iJ0 = J0(arrayList, arrayList2, i2, i3, bVar);
            x0(bVar);
            i4 = iJ0;
        } else {
            i4 = i3;
        }
        if (i4 != i5 && z) {
            p.B(this, arrayList, arrayList2, i2, i4, true, this.m);
            z0(this.o, true);
        }
        while (i5 < i3) {
            androidx.fragment.app.a aVar2 = arrayList.get(i5);
            if (arrayList2.get(i5).booleanValue() && aVar2.v >= 0) {
                aVar2.v = -1;
            }
            aVar2.H();
            i5++;
        }
        if (z2) {
            O0();
        }
    }

    private void V(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<C0021j> arrayList3 = this.D;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            C0021j c0021j = this.D.get(i2);
            if (arrayList != null && !c0021j.f1466a && (iIndexOf2 = arrayList.indexOf(c0021j.f1467b)) != -1 && arrayList2 != null && arrayList2.get(iIndexOf2).booleanValue()) {
                this.D.remove(i2);
                i2--;
                size--;
                c0021j.c();
            } else if (c0021j.e() || (arrayList != null && c0021j.f1467b.E(arrayList, 0, arrayList.size()))) {
                this.D.remove(i2);
                i2--;
                size--;
                if (arrayList == null || c0021j.f1466a || (iIndexOf = arrayList.indexOf(c0021j.f1467b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                    c0021j.d();
                } else {
                    c0021j.c();
                }
            }
            i2++;
        }
    }

    private void W0(Fragment fragment) {
        ViewGroup viewGroupE0 = e0(fragment);
        if (viewGroupE0 != null) {
            int i2 = b.k.b.visible_removing_fragment_view_tag;
            if (viewGroupE0.getTag(i2) == null) {
                viewGroupE0.setTag(i2, fragment);
            }
            ((Fragment) viewGroupE0.getTag(i2)).k1(fragment.x());
        }
    }

    private void Y0() {
        for (Fragment fragment : this.f1448d.k()) {
            if (fragment != null) {
                D0(fragment);
            }
        }
    }

    private void Z0() {
        synchronized (this.f1446b) {
            if (this.f1446b.isEmpty()) {
                this.f1453i.f(c0() > 0 && s0(this.r));
            } else {
                this.f1453i.f(true);
            }
        }
    }

    private void a(b.d.b<Fragment> bVar) {
        int i2 = this.o;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment.f1362c < iMin) {
                B0(fragment, iMin);
                if (fragment.I != null && !fragment.A && fragment.N) {
                    bVar.add(fragment);
                }
            }
        }
    }

    private void a0() {
        if (this.D != null) {
            while (!this.D.isEmpty()) {
                this.D.remove(0).d();
            }
        }
    }

    private boolean b0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this.f1446b) {
            if (this.f1446b.isEmpty()) {
                return false;
            }
            int size = this.f1446b.size();
            boolean zA = false;
            for (int i2 = 0; i2 < size; i2++) {
                zA |= this.f1446b.get(i2).a(arrayList, arrayList2);
            }
            this.f1446b.clear();
            this.p.i().removeCallbacks(this.F);
            return zA;
        }
    }

    private l d0(Fragment fragment) {
        return this.E.h(fragment);
    }

    private ViewGroup e0(Fragment fragment) {
        if (fragment.y > 0 && this.q.f()) {
            View viewC = this.q.c(fragment.y);
            if (viewC instanceof ViewGroup) {
                return (ViewGroup) viewC;
            }
        }
        return null;
    }

    private void j(Fragment fragment) {
        HashSet<b.g.g.a> hashSet = this.l.get(fragment);
        if (hashSet != null) {
            Iterator<b.g.g.a> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            hashSet.clear();
            p(fragment);
            this.l.remove(fragment);
        }
    }

    private void l() {
        if (u0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    static Fragment l0(View view) {
        Object tag = view.getTag(b.k.b.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    private void m() {
        this.f1447c = false;
        this.B.clear();
        this.A.clear();
    }

    private void o(Fragment fragment) {
        Animator animator;
        if (fragment.I != null) {
            c.d dVarB = androidx.fragment.app.c.b(this.p.h(), this.q, fragment, !fragment.A);
            if (dVarB == null || (animator = dVarB.f1427b) == null) {
                if (dVarB != null) {
                    fragment.I.startAnimation(dVarB.f1426a);
                    dVarB.f1426a.start();
                }
                fragment.I.setVisibility((!fragment.A || fragment.Q()) ? 0 : 8);
                if (fragment.Q()) {
                    fragment.j1(false);
                }
            } else {
                animator.setTarget(fragment.I);
                if (!fragment.A) {
                    fragment.I.setVisibility(0);
                } else if (fragment.Q()) {
                    fragment.j1(false);
                } else {
                    ViewGroup viewGroup = fragment.H;
                    View view = fragment.I;
                    viewGroup.startViewTransition(view);
                    dVarB.f1427b.addListener(new e(viewGroup, view, fragment));
                }
                dVarB.f1427b.start();
            }
        }
        if (fragment.m && r0(fragment)) {
            this.v = true;
        }
        fragment.O = false;
        fragment.n0(fragment.A);
    }

    private void p(Fragment fragment) {
        fragment.M0();
        this.n.n(fragment, false);
        fragment.H = null;
        fragment.I = null;
        fragment.U = null;
        fragment.V.h(null);
        fragment.p = false;
    }

    static boolean q0(int i2) {
        return f1445a || Log.isLoggable("FragmentManager", i2);
    }

    private boolean r0(Fragment fragment) {
        return (fragment.E && fragment.F) || fragment.v.k();
    }

    private void w0(m mVar) {
        Fragment fragmentI = mVar.i();
        if (this.f1448d.c(fragmentI.f1366g)) {
            if (q0(2)) {
                Log.v("FragmentManager", "Removed fragment from active set " + fragmentI);
            }
            this.f1448d.o(mVar);
            N0(fragmentI);
        }
    }

    private void x0(b.d.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentH = bVar.h(i2);
            if (!fragmentH.m) {
                View viewD1 = fragmentH.d1();
                fragmentH.P = viewD1.getAlpha();
                viewD1.setAlpha(0.0f);
            }
        }
    }

    boolean A(MenuItem menuItem) {
        if (this.o < 1) {
            return false;
        }
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null && fragment.R0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void A0(Fragment fragment) {
        B0(fragment, this.o);
    }

    void B(Menu menu) {
        if (this.o < 1) {
            return;
        }
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.S0(menu);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:180:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x01ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void B0(androidx.fragment.app.Fragment r13, int r14) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.B0(androidx.fragment.app.Fragment, int):void");
    }

    void C0() {
        if (this.p == null) {
            return;
        }
        this.w = false;
        this.x = false;
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.W();
            }
        }
    }

    void D() {
        J(3);
    }

    void D0(Fragment fragment) {
        if (fragment.J) {
            if (this.f1447c) {
                this.z = true;
            } else {
                fragment.J = false;
                B0(fragment, this.o);
            }
        }
    }

    void E(boolean z) {
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.U0(z);
            }
        }
    }

    public void E0(int i2, int i3) {
        if (i2 >= 0) {
            O(new i(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    boolean F(Menu menu) {
        boolean z = false;
        if (this.o < 1) {
            return false;
        }
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null && fragment.V0(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void F0(String str, int i2) {
        O(new i(str, -1, i2), false);
    }

    void G() {
        Z0();
        C(this.s);
    }

    public boolean G0() {
        return H0(null, -1, 0);
    }

    void H() {
        this.w = false;
        this.x = false;
        J(4);
    }

    void I() {
        this.w = false;
        this.x = false;
        J(3);
    }

    boolean I0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.f1449e;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f1449e.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.f1449e.get(size2);
                    if ((str != null && str.equals(aVar.C())) || (i2 >= 0 && i2 == aVar.v)) {
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
                        androidx.fragment.app.a aVar2 = this.f1449e.get(size2);
                        if (str == null || !str.equals(aVar2.C())) {
                            if (i2 < 0 || i2 != aVar2.v) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.f1449e.size() - 1) {
                return false;
            }
            for (int size3 = this.f1449e.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.f1449e.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    void K() {
        this.x = true;
        J(2);
    }

    void K0(Fragment fragment, b.g.g.a aVar) {
        HashSet<b.g.g.a> hashSet = this.l.get(fragment);
        if (hashSet != null && hashSet.remove(aVar) && hashSet.isEmpty()) {
            this.l.remove(fragment);
            if (fragment.f1362c < 3) {
                p(fragment);
                B0(fragment, fragment.H());
            }
        }
    }

    void L0(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.s);
        }
        boolean z = !fragment.R();
        if (!fragment.B || z) {
            this.f1448d.p(fragment);
            if (r0(fragment)) {
                this.v = true;
            }
            fragment.n = true;
            W0(fragment);
        }
    }

    public void M(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f1448d.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.f1450f;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = this.f1450f.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.f1449e;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                androidx.fragment.app.a aVar = this.f1449e.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.x(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.j.get());
        synchronized (this.f1446b) {
            int size3 = this.f1446b.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    h hVar = this.f1446b.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(hVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.p);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.q);
        if (this.r != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.r);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.o);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.w);
        printWriter.print(" mStopped=");
        printWriter.print(this.x);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.y);
        if (this.v) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.v);
        }
    }

    void N0(Fragment fragment) {
        if (u0()) {
            if (q0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.E.m(fragment) && q0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    void O(h hVar, boolean z) {
        if (!z) {
            if (this.p == null) {
                if (!this.y) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            l();
        }
        synchronized (this.f1446b) {
            if (this.p == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.f1446b.add(hVar);
                S0();
            }
        }
    }

    void P0(Parcelable parcelable) {
        m mVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1387b == null) {
            return;
        }
        this.f1448d.q();
        for (FragmentState fragmentState : fragmentManagerState.f1387b) {
            if (fragmentState != null) {
                Fragment fragmentG = this.E.g(fragmentState.f1393c);
                if (fragmentG != null) {
                    if (q0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragmentG);
                    }
                    mVar = new m(this.n, fragmentG, fragmentState);
                } else {
                    mVar = new m(this.n, this.p.h().getClassLoader(), f0(), fragmentState);
                }
                Fragment fragmentI = mVar.i();
                fragmentI.t = this;
                if (q0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentI.f1366g + "): " + fragmentI);
                }
                mVar.k(this.p.h().getClassLoader());
                this.f1448d.n(mVar);
                mVar.q(this.o);
            }
        }
        for (Fragment fragment : this.E.j()) {
            if (!this.f1448d.c(fragment.f1366g)) {
                if (q0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f1387b);
                }
                B0(fragment, 1);
                fragment.n = true;
                B0(fragment, -1);
            }
        }
        this.f1448d.r(fragmentManagerState.f1388c);
        if (fragmentManagerState.f1389d != null) {
            this.f1449e = new ArrayList<>(fragmentManagerState.f1389d.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f1389d;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarA = backStackStateArr[i2].a(this);
                if (q0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarA.v + "): " + aVarA);
                    PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
                    aVarA.y("  ", printWriter, false);
                    printWriter.close();
                }
                this.f1449e.add(aVarA);
                i2++;
            }
        } else {
            this.f1449e = null;
        }
        this.j.set(fragmentManagerState.f1390e);
        String str = fragmentManagerState.f1391f;
        if (str != null) {
            Fragment fragmentW = W(str);
            this.s = fragmentW;
            C(fragmentW);
        }
    }

    boolean Q(boolean z) {
        P(z);
        boolean z2 = false;
        while (b0(this.A, this.B)) {
            this.f1447c = true;
            try {
                M0(this.A, this.B);
                m();
                z2 = true;
            } catch (Throwable th) {
                m();
                throw th;
            }
        }
        Z0();
        L();
        this.f1448d.b();
        return z2;
    }

    void R(h hVar, boolean z) {
        if (z && (this.p == null || this.y)) {
            return;
        }
        P(z);
        if (hVar.a(this.A, this.B)) {
            this.f1447c = true;
            try {
                M0(this.A, this.B);
            } finally {
                m();
            }
        }
        Z0();
        L();
        this.f1448d.b();
    }

    Parcelable R0() {
        int size;
        a0();
        N();
        Q(true);
        this.w = true;
        ArrayList<FragmentState> arrayListS = this.f1448d.s();
        BackStackState[] backStackStateArr = null;
        if (arrayListS.isEmpty()) {
            if (q0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> arrayListT = this.f1448d.t();
        ArrayList<androidx.fragment.app.a> arrayList = this.f1449e;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.f1449e.get(i2));
                if (q0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f1449e.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1387b = arrayListS;
        fragmentManagerState.f1388c = arrayListT;
        fragmentManagerState.f1389d = backStackStateArr;
        fragmentManagerState.f1390e = this.j.get();
        Fragment fragment = this.s;
        if (fragment != null) {
            fragmentManagerState.f1391f = fragment.f1366g;
        }
        return fragmentManagerState;
    }

    void S0() {
        synchronized (this.f1446b) {
            ArrayList<C0021j> arrayList = this.D;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.f1446b.size() == 1;
            if (z || z2) {
                this.p.i().removeCallbacks(this.F);
                this.p.i().post(this.F);
                Z0();
            }
        }
    }

    void T0(Fragment fragment, boolean z) {
        ViewGroup viewGroupE0 = e0(fragment);
        if (viewGroupE0 == null || !(viewGroupE0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupE0).setDrawDisappearingViewsLast(!z);
    }

    public boolean U() {
        boolean zQ = Q(true);
        a0();
        return zQ;
    }

    void U0(Fragment fragment, d.b bVar) {
        if (fragment.equals(W(fragment.f1366g)) && (fragment.u == null || fragment.t == this)) {
            fragment.S = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void V0(Fragment fragment) {
        if (fragment == null || (fragment.equals(W(fragment.f1366g)) && (fragment.u == null || fragment.t == this))) {
            Fragment fragment2 = this.s;
            this.s = fragment;
            C(fragment2);
            C(this.s);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    Fragment W(String str) {
        return this.f1448d.f(str);
    }

    public Fragment X(int i2) {
        return this.f1448d.g(i2);
    }

    void X0(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.A) {
            fragment.A = false;
            fragment.O = !fragment.O;
        }
    }

    public Fragment Y(String str) {
        return this.f1448d.h(str);
    }

    Fragment Z(String str) {
        return this.f1448d.i(str);
    }

    void b(androidx.fragment.app.a aVar) {
        if (this.f1449e == null) {
            this.f1449e = new ArrayList<>();
        }
        this.f1449e.add(aVar);
    }

    void c(Fragment fragment, b.g.g.a aVar) {
        if (this.l.get(fragment) == null) {
            this.l.put(fragment, new HashSet<>());
        }
        this.l.get(fragment).add(aVar);
    }

    public int c0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.f1449e;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void d(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        v0(fragment);
        if (fragment.B) {
            return;
        }
        this.f1448d.a(fragment);
        fragment.n = false;
        if (fragment.I == null) {
            fragment.O = false;
        }
        if (r0(fragment)) {
            this.v = true;
        }
    }

    void e(Fragment fragment) {
        if (u0()) {
            if (q0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.E.e(fragment) && q0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    int f() {
        return this.j.getAndIncrement();
    }

    public androidx.fragment.app.f f0() {
        androidx.fragment.app.f fVar = this.t;
        if (fVar != null) {
            return fVar;
        }
        Fragment fragment = this.r;
        return fragment != null ? fragment.t.f0() : this.u;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void g(androidx.fragment.app.g<?> gVar, androidx.fragment.app.d dVar, Fragment fragment) {
        if (this.p != null) {
            throw new IllegalStateException("Already attached");
        }
        this.p = gVar;
        this.q = dVar;
        this.r = fragment;
        if (fragment != null) {
            Z0();
        }
        if (gVar instanceof androidx.activity.c) {
            androidx.activity.c cVar = (androidx.activity.c) gVar;
            OnBackPressedDispatcher onBackPressedDispatcherD = cVar.d();
            this.f1452h = onBackPressedDispatcherD;
            androidx.lifecycle.g gVar2 = cVar;
            if (fragment != null) {
                gVar2 = fragment;
            }
            onBackPressedDispatcherD.a(gVar2, this.f1453i);
        }
        if (fragment != null) {
            this.E = fragment.t.d0(fragment);
        } else if (gVar instanceof androidx.lifecycle.s) {
            this.E = l.i(((androidx.lifecycle.s) gVar).j());
        } else {
            this.E = new l(false);
        }
    }

    public List<Fragment> g0() {
        return this.f1448d.m();
    }

    void h(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.B) {
            fragment.B = false;
            if (fragment.m) {
                return;
            }
            this.f1448d.a(fragment);
            if (q0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (r0(fragment)) {
                this.v = true;
            }
        }
    }

    LayoutInflater.Factory2 h0() {
        return this.f1451g;
    }

    public o i() {
        return new androidx.fragment.app.a(this);
    }

    androidx.fragment.app.i i0() {
        return this.n;
    }

    Fragment j0() {
        return this.r;
    }

    boolean k() {
        boolean zR0 = false;
        for (Fragment fragment : this.f1448d.k()) {
            if (fragment != null) {
                zR0 = r0(fragment);
            }
            if (zR0) {
                return true;
            }
        }
        return false;
    }

    public Fragment k0() {
        return this.s;
    }

    androidx.lifecycle.r m0(Fragment fragment) {
        return this.E.k(fragment);
    }

    void n(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.A(z3);
        } else {
            aVar.z();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            p.B(this, arrayList, arrayList2, 0, 1, true, this.m);
        }
        if (z3) {
            z0(this.o, true);
        }
        for (Fragment fragment : this.f1448d.k()) {
            if (fragment != null && fragment.I != null && fragment.N && aVar.D(fragment.y)) {
                float f2 = fragment.P;
                if (f2 > 0.0f) {
                    fragment.I.setAlpha(f2);
                }
                if (z3) {
                    fragment.P = 0.0f;
                } else {
                    fragment.P = -1.0f;
                    fragment.N = false;
                }
            }
        }
    }

    void n0() {
        Q(true);
        if (this.f1453i.c()) {
            G0();
        } else {
            this.f1452h.c();
        }
    }

    void o0(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.A) {
            return;
        }
        fragment.A = true;
        fragment.O = true ^ fragment.O;
        W0(fragment);
    }

    public boolean p0() {
        return this.y;
    }

    void q(Fragment fragment) {
        if (q0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.B) {
            return;
        }
        fragment.B = true;
        if (fragment.m) {
            if (q0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f1448d.p(fragment);
            if (r0(fragment)) {
                this.v = true;
            }
            W0(fragment);
        }
    }

    void r() {
        this.w = false;
        this.x = false;
        J(2);
    }

    void s(Configuration configuration) {
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.G0(configuration);
            }
        }
    }

    boolean s0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        j jVar = fragment.t;
        return fragment.equals(jVar.k0()) && s0(jVar.r);
    }

    boolean t(MenuItem menuItem) {
        if (this.o < 1) {
            return false;
        }
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null && fragment.H0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean t0(int i2) {
        return this.o >= i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.r;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.r)));
            sb.append("}");
        } else {
            androidx.fragment.app.g<?> gVar = this.p;
            if (gVar != null) {
                sb.append(gVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.p)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    void u() {
        this.w = false;
        this.x = false;
        J(1);
    }

    public boolean u0() {
        return this.w || this.x;
    }

    boolean v(Menu menu, MenuInflater menuInflater) {
        if (this.o < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null && fragment.J0(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.f1450f != null) {
            for (int i2 = 0; i2 < this.f1450f.size(); i2++) {
                Fragment fragment2 = this.f1450f.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.j0();
                }
            }
        }
        this.f1450f = arrayList;
        return z;
    }

    void v0(Fragment fragment) {
        if (this.f1448d.c(fragment.f1366g)) {
            return;
        }
        m mVar = new m(this.n, fragment);
        mVar.k(this.p.h().getClassLoader());
        this.f1448d.n(mVar);
        if (fragment.D) {
            if (fragment.C) {
                e(fragment);
            } else {
                N0(fragment);
            }
            fragment.D = false;
        }
        mVar.q(this.o);
        if (q0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    void w() {
        this.y = true;
        Q(true);
        N();
        J(-1);
        this.p = null;
        this.q = null;
        this.r = null;
        if (this.f1452h != null) {
            this.f1453i.d();
            this.f1452h = null;
        }
    }

    void x() {
        J(1);
    }

    void y() {
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.P0();
            }
        }
    }

    void y0(Fragment fragment) {
        if (!this.f1448d.c(fragment.f1366g)) {
            if (q0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.o + "since it is not added to " + this);
                return;
            }
            return;
        }
        A0(fragment);
        if (fragment.I != null) {
            Fragment fragmentJ = this.f1448d.j(fragment);
            if (fragmentJ != null) {
                View view = fragmentJ.I;
                ViewGroup viewGroup = fragment.H;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.I);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.I, iIndexOfChild);
                }
            }
            if (fragment.N && fragment.H != null) {
                float f2 = fragment.P;
                if (f2 > 0.0f) {
                    fragment.I.setAlpha(f2);
                }
                fragment.P = 0.0f;
                fragment.N = false;
                c.d dVarB = androidx.fragment.app.c.b(this.p.h(), this.q, fragment, true);
                if (dVarB != null) {
                    Animation animation = dVarB.f1426a;
                    if (animation != null) {
                        fragment.I.startAnimation(animation);
                    } else {
                        dVarB.f1427b.setTarget(fragment.I);
                        dVarB.f1427b.start();
                    }
                }
            }
        }
        if (fragment.O) {
            o(fragment);
        }
    }

    void z(boolean z) {
        for (Fragment fragment : this.f1448d.m()) {
            if (fragment != null) {
                fragment.Q0(z);
            }
        }
    }

    void z0(int i2, boolean z) {
        androidx.fragment.app.g<?> gVar;
        if (this.p == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.o) {
            this.o = i2;
            Iterator<Fragment> it = this.f1448d.m().iterator();
            while (it.hasNext()) {
                y0(it.next());
            }
            for (Fragment fragment : this.f1448d.k()) {
                if (fragment != null && !fragment.N) {
                    y0(fragment);
                }
            }
            Y0();
            if (this.v && (gVar = this.p) != null && this.o == 4) {
                gVar.o();
                this.v = false;
            }
        }
    }
}
