package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
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
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.IntentSenderRequest;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.e;
import androidx.fragment.app.r;
import androidx.fragment.app.s;
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.i;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentManager {
    private static boolean O = false;
    static boolean P = true;
    private androidx.activity.result.b<IntentSenderRequest> A;
    private androidx.activity.result.b<String[]> B;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private ArrayList<androidx.fragment.app.a> I;
    private ArrayList<Boolean> J;
    private ArrayList<Fragment> K;
    private ArrayList<p> L;
    private androidx.fragment.app.m M;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2166b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<androidx.fragment.app.a> f2168d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<Fragment> f2169e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private OnBackPressedDispatcher f2171g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ArrayList<m> f2176l;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private androidx.fragment.app.i<?> f2182r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private androidx.fragment.app.f f2183s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Fragment f2184t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    Fragment f2185u;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private androidx.activity.result.b<Intent> f2190z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<n> f2165a = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final q f2167c = new q();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final androidx.fragment.app.j f2170f = new androidx.fragment.app.j(this);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final androidx.activity.b f2172h = new c(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final AtomicInteger f2173i = new AtomicInteger();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Map<String, Bundle> f2174j = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Map<String, Object> f2175k = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Map<Fragment, HashSet<d0.b>> f2177m = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final s.g f2178n = new d();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final androidx.fragment.app.k f2179o = new androidx.fragment.app.k(this);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final CopyOnWriteArrayList<androidx.fragment.app.n> f2180p = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f2181q = -1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private androidx.fragment.app.h f2186v = null;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private androidx.fragment.app.h f2187w = new e();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private y f2188x = null;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private y f2189y = new f();
    ArrayDeque<LaunchedFragmentInfo> C = new ArrayDeque<>();
    private Runnable N = new g();

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentManager$6, reason: invalid class name */
    class AnonymousClass6 implements androidx.lifecycle.m {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f2191e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ androidx.fragment.app.o f2192g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ androidx.lifecycle.i f2193h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ FragmentManager f2194i;

        @Override // androidx.lifecycle.m
        public void d(androidx.lifecycle.o oVar, i.b bVar) {
            Bundle bundle;
            if (bVar == i.b.ON_START && (bundle = (Bundle) this.f2194i.f2174j.get(this.f2191e)) != null) {
                this.f2192g.a(this.f2191e, bundle);
                this.f2194i.q(this.f2191e);
            }
            if (bVar == i.b.ON_DESTROY) {
                this.f2193h.c(this);
                this.f2194i.f2175k.remove(this.f2191e);
            }
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f2195e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f2196g;

        class a implements Parcelable.Creator<LaunchedFragmentInfo> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo[] newArray(int i7) {
                return new LaunchedFragmentInfo[i7];
            }
        }

        LaunchedFragmentInfo(Parcel parcel) {
            this.f2195e = parcel.readString();
            this.f2196g = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            parcel.writeString(this.f2195e);
            parcel.writeInt(this.f2196g);
        }
    }

    class a implements androidx.activity.result.a<ActivityResult> {
        a() {
        }

        @Override // androidx.activity.result.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ActivityResult activityResult) {
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.C.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f2195e;
            int i7 = launchedFragmentInfoPollFirst.f2196g;
            Fragment fragmentI = FragmentManager.this.f2167c.i(str);
            if (fragmentI != null) {
                fragmentI.p0(i7, activityResult.c(), activityResult.b());
                return;
            }
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
        }
    }

    class b implements androidx.activity.result.a<Map<String, Boolean>> {
        b() {
        }

        @Override // androidx.activity.result.a
        @SuppressLint({"SyntheticAccessor"})
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Map<String, Boolean> map) {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                iArr[i7] = ((Boolean) arrayList.get(i7)).booleanValue() ? 0 : -1;
            }
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.C.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No permissions were requested for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f2195e;
            int i8 = launchedFragmentInfoPollFirst.f2196g;
            Fragment fragmentI = FragmentManager.this.f2167c.i(str);
            if (fragmentI != null) {
                fragmentI.O0(i8, strArr, iArr);
                return;
            }
            Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
        }
    }

    class c extends androidx.activity.b {
        c(boolean z6) {
            super(z6);
        }

        @Override // androidx.activity.b
        public void b() {
            FragmentManager.this.B0();
        }
    }

    class d implements s.g {
        d() {
        }

        @Override // androidx.fragment.app.s.g
        public void a(Fragment fragment, d0.b bVar) {
            if (bVar.b()) {
                return;
            }
            FragmentManager.this.b1(fragment, bVar);
        }

        @Override // androidx.fragment.app.s.g
        public void b(Fragment fragment, d0.b bVar) {
            FragmentManager.this.f(fragment, bVar);
        }
    }

    class e extends androidx.fragment.app.h {
        e() {
        }

        @Override // androidx.fragment.app.h
        public Fragment a(ClassLoader classLoader, String str) {
            return FragmentManager.this.t0().e(FragmentManager.this.t0().j(), str, null);
        }
    }

    class f implements y {
        f() {
        }

        @Override // androidx.fragment.app.y
        public x a(ViewGroup viewGroup) {
            return new androidx.fragment.app.b(viewGroup);
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.a0(true);
        }
    }

    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f2204a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2205b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f2206c;

        h(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f2204a = viewGroup;
            this.f2205b = view;
            this.f2206c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2204a.endViewTransition(this.f2205b);
            animator.removeListener(this);
            Fragment fragment = this.f2206c;
            View view = fragment.M;
            if (view == null || !fragment.E) {
                return;
            }
            view.setVisibility(8);
        }
    }

    class i implements androidx.fragment.app.n {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f2208e;

        i(Fragment fragment) {
            this.f2208e = fragment;
        }

        @Override // androidx.fragment.app.n
        public void b(FragmentManager fragmentManager, Fragment fragment) {
            this.f2208e.s0(fragment);
        }
    }

    class j implements androidx.activity.result.a<ActivityResult> {
        j() {
        }

        @Override // androidx.activity.result.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ActivityResult activityResult) {
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.C.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f2195e;
            int i7 = launchedFragmentInfoPollFirst.f2196g;
            Fragment fragmentI = FragmentManager.this.f2167c.i(str);
            if (fragmentI != null) {
                fragmentI.p0(i7, activityResult.c(), activityResult.b());
                return;
            }
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
        }
    }

    static class k extends d.a<IntentSenderRequest, ActivityResult> {
        k() {
        }

        @Override // d.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intentB = intentSenderRequest.b();
            if (intentB != null && (bundleExtra = intentB.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intentB.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intentB.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.b(intentSenderRequest.j()).b(null).c(intentSenderRequest.h(), intentSenderRequest.c()).a();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // d.a
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResult c(int i7, Intent intent) {
            return new ActivityResult(i7, intent);
        }
    }

    public static abstract class l {
        @Deprecated
        public void a(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void b(FragmentManager fragmentManager, Fragment fragment, Context context) {
        }

        public void c(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void d(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void e(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void f(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void g(FragmentManager fragmentManager, Fragment fragment, Context context) {
        }

        public void h(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void i(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void j(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void k(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void l(FragmentManager fragmentManager, Fragment fragment) {
        }

        public abstract void m(FragmentManager fragmentManager, Fragment fragment, View view, Bundle bundle);

        public void n(FragmentManager fragmentManager, Fragment fragment) {
        }
    }

    public interface m {
        void a();
    }

    interface n {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    private class o implements n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f2211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2213c;

        o(String str, int i7, int i8) {
            this.f2211a = str;
            this.f2212b = i7;
            this.f2213c = i8;
        }

        @Override // androidx.fragment.app.FragmentManager.n
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.f2185u;
            if (fragment == null || this.f2212b >= 0 || this.f2211a != null || !fragment.r().V0()) {
                return FragmentManager.this.X0(arrayList, arrayList2, this.f2211a, this.f2212b, this.f2213c);
            }
            return false;
        }
    }

    static class p implements Fragment.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f2215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f2216b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2217c;

        p(androidx.fragment.app.a aVar, boolean z6) {
            this.f2215a = z6;
            this.f2216b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.h
        public void a() {
            int i7 = this.f2217c - 1;
            this.f2217c = i7;
            if (i7 != 0) {
                return;
            }
            this.f2216b.f2240t.j1();
        }

        @Override // androidx.fragment.app.Fragment.h
        public void b() {
            this.f2217c++;
        }

        void c() {
            androidx.fragment.app.a aVar = this.f2216b;
            aVar.f2240t.t(aVar, this.f2215a, false, false);
        }

        void d() {
            boolean z6 = this.f2217c > 0;
            for (Fragment fragment : this.f2216b.f2240t.s0()) {
                fragment.I1(null);
                if (z6 && fragment.h0()) {
                    fragment.M1();
                }
            }
            androidx.fragment.app.a aVar = this.f2216b;
            aVar.f2240t.t(aVar, this.f2215a, !z6, true);
        }

        public boolean e() {
            return this.f2217c == 0;
        }
    }

    static boolean F0(int i7) {
        return O || Log.isLoggable("FragmentManager", i7);
    }

    private boolean G0(Fragment fragment) {
        return (fragment.I && fragment.J) || fragment.f2130z.n();
    }

    private void L(Fragment fragment) {
        if (fragment == null || !fragment.equals(f0(fragment.f2115k))) {
            return;
        }
        fragment.n1();
    }

    private void L0(n.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            Fragment fragmentJ = bVar.j(i7);
            if (!fragmentJ.f2121q) {
                View viewV1 = fragmentJ.v1();
                fragmentJ.T = viewV1.getAlpha();
                viewV1.setAlpha(0.0f);
            }
        }
    }

    private void S(int i7) {
        try {
            this.f2166b = true;
            this.f2167c.d(i7);
            N0(i7, false);
            if (P) {
                Iterator<x> it = r().iterator();
                while (it.hasNext()) {
                    it.next().j();
                }
            }
            this.f2166b = false;
            a0(true);
        } catch (Throwable th) {
            this.f2166b = false;
            throw th;
        }
    }

    private void V() {
        if (this.H) {
            this.H = false;
            p1();
        }
    }

    private boolean W0(String str, int i7, int i8) {
        a0(false);
        Z(true);
        Fragment fragment = this.f2185u;
        if (fragment != null && i7 < 0 && str == null && fragment.r().V0()) {
            return true;
        }
        boolean zX0 = X0(this.I, this.J, str, i7, i8);
        if (zX0) {
            this.f2166b = true;
            try {
                d1(this.I, this.J);
            } finally {
                p();
            }
        }
        s1();
        V();
        this.f2167c.b();
        return zX0;
    }

    private void X() {
        if (P) {
            Iterator<x> it = r().iterator();
            while (it.hasNext()) {
                it.next().j();
            }
        } else {
            if (this.f2177m.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.f2177m.keySet()) {
                m(fragment);
                O0(fragment);
            }
        }
    }

    private int Y0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8, n.b<Fragment> bVar) {
        int i9 = i8;
        for (int i10 = i8 - 1; i10 >= i7; i10--) {
            androidx.fragment.app.a aVar = arrayList.get(i10);
            boolean zBooleanValue = arrayList2.get(i10).booleanValue();
            if (aVar.I() && !aVar.G(arrayList, i10 + 1, i8)) {
                if (this.L == null) {
                    this.L = new ArrayList<>();
                }
                p pVar = new p(aVar, zBooleanValue);
                this.L.add(pVar);
                aVar.K(pVar);
                if (zBooleanValue) {
                    aVar.B();
                } else {
                    aVar.C(false);
                }
                i9--;
                if (i10 != i9) {
                    arrayList.remove(i10);
                    arrayList.add(i9, aVar);
                }
                d(bVar);
            }
        }
        return i9;
    }

    private void Z(boolean z6) {
        if (this.f2166b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f2182r == null) {
            if (!this.G) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.f2182r.l().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z6) {
            o();
        }
        if (this.I == null) {
            this.I = new ArrayList<>();
            this.J = new ArrayList<>();
        }
        this.f2166b = true;
        try {
            e0(null, null);
        } finally {
            this.f2166b = false;
        }
    }

    private static void c0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8) {
        while (i7 < i8) {
            androidx.fragment.app.a aVar = arrayList.get(i7);
            if (arrayList2.get(i7).booleanValue()) {
                aVar.x(-1);
                aVar.C(i7 == i8 + (-1));
            } else {
                aVar.x(1);
                aVar.B();
            }
            i7++;
        }
    }

    private void d(n.b<Fragment> bVar) {
        int i7 = this.f2181q;
        if (i7 < 1) {
            return;
        }
        int iMin = Math.min(i7, 5);
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment.f2109e < iMin) {
                P0(fragment, iMin);
                if (fragment.M != null && !fragment.E && fragment.R) {
                    bVar.add(fragment);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r17v0, types: [androidx.fragment.app.FragmentManager] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
    private void d0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i7, int i8) {
        ?? r12;
        ?? r14;
        boolean z6;
        int i9;
        int i10;
        ArrayList<Boolean> arrayList3;
        int iY0;
        ArrayList<Boolean> arrayList4;
        int i11;
        ?? r13;
        boolean z7 = arrayList.get(i7).f2383r;
        ArrayList<Fragment> arrayList5 = this.K;
        if (arrayList5 == null) {
            this.K = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.K.addAll(this.f2167c.n());
        Fragment fragmentX0 = x0();
        boolean z8 = false;
        for (int i12 = i7; i12 < i8; i12++) {
            androidx.fragment.app.a aVar = arrayList.get(i12);
            fragmentX0 = !arrayList2.get(i12).booleanValue() ? aVar.D(this.K, fragmentX0) : aVar.L(this.K, fragmentX0);
            z8 = z8 || aVar.f2374i;
        }
        this.K.clear();
        if (z7 || this.f2181q < 1) {
            r12 = 1;
        } else if (P) {
            for (int i13 = i7; i13 < i8; i13++) {
                Iterator<r.a> it = arrayList.get(i13).f2368c.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().f2386b;
                    if (fragment != null && fragment.f2128x != null) {
                        this.f2167c.p(v(fragment));
                    }
                }
            }
            r12 = 1;
        } else {
            r12 = 1;
            s.B(this.f2182r.j(), this.f2183s, arrayList, arrayList2, i7, i8, false, this.f2178n);
        }
        c0(arrayList, arrayList2, i7, i8);
        if (P) {
            boolean zBooleanValue = arrayList2.get(i8 - 1).booleanValue();
            for (int i14 = i7; i14 < i8; i14++) {
                androidx.fragment.app.a aVar2 = arrayList.get(i14);
                if (zBooleanValue) {
                    for (int size = aVar2.f2368c.size() - r12; size >= 0; size--) {
                        Fragment fragment2 = aVar2.f2368c.get(size).f2386b;
                        if (fragment2 != null) {
                            v(fragment2).m();
                        }
                    }
                } else {
                    Iterator<r.a> it2 = aVar2.f2368c.iterator();
                    while (it2.hasNext()) {
                        Fragment fragment3 = it2.next().f2386b;
                        if (fragment3 != null) {
                            v(fragment3).m();
                        }
                    }
                }
            }
            N0(this.f2181q, r12);
            for (x xVar : s(arrayList, i7, i8)) {
                xVar.r(zBooleanValue);
                xVar.p();
                xVar.g();
            }
            i11 = i8;
            arrayList4 = arrayList2;
        } else {
            if (z7) {
                n.b bVar = new n.b();
                d(bVar);
                r14 = r12;
                z6 = z7;
                i9 = i8;
                i10 = i7;
                arrayList3 = arrayList2;
                iY0 = Y0(arrayList, arrayList2, i7, i8, bVar);
                L0(bVar);
            } else {
                r14 = r12;
                z6 = z7;
                i9 = i8;
                i10 = i7;
                arrayList3 = arrayList2;
                iY0 = i9;
            }
            if (iY0 == i10 || !z6) {
                arrayList4 = arrayList3;
                i11 = i9;
            } else {
                if (this.f2181q >= r14) {
                    arrayList4 = arrayList3;
                    int i15 = iY0;
                    i11 = i9;
                    r13 = r14;
                    s.B(this.f2182r.j(), this.f2183s, arrayList, arrayList2, i7, i15, true, this.f2178n);
                } else {
                    arrayList4 = arrayList3;
                    i11 = i9;
                    r13 = r14;
                }
                N0(this.f2181q, r13);
            }
        }
        for (int i16 = i7; i16 < i11; i16++) {
            androidx.fragment.app.a aVar3 = arrayList.get(i16);
            if (arrayList4.get(i16).booleanValue() && aVar3.f2242v >= 0) {
                aVar3.f2242v = -1;
            }
            aVar3.J();
        }
        if (z8) {
            e1();
        }
    }

    private void d1(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        e0(arrayList, arrayList2);
        int size = arrayList.size();
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            if (!arrayList.get(i7).f2383r) {
                if (i8 != i7) {
                    d0(arrayList, arrayList2, i8, i7);
                }
                i8 = i7 + 1;
                if (arrayList2.get(i7).booleanValue()) {
                    while (i8 < size && arrayList2.get(i8).booleanValue() && !arrayList.get(i8).f2383r) {
                        i8++;
                    }
                }
                d0(arrayList, arrayList2, i7, i8);
                i7 = i8 - 1;
            }
            i7++;
        }
        if (i8 != size) {
            d0(arrayList, arrayList2, i8, size);
        }
    }

    private void e0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<p> arrayList3 = this.L;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i7 = 0;
        while (i7 < size) {
            p pVar = this.L.get(i7);
            if (arrayList != null && !pVar.f2215a && (iIndexOf2 = arrayList.indexOf(pVar.f2216b)) != -1 && arrayList2 != null && arrayList2.get(iIndexOf2).booleanValue()) {
                this.L.remove(i7);
                i7--;
                size--;
                pVar.c();
            } else if (pVar.e() || (arrayList != null && pVar.f2216b.G(arrayList, 0, arrayList.size()))) {
                this.L.remove(i7);
                i7--;
                size--;
                if (arrayList == null || pVar.f2215a || (iIndexOf = arrayList.indexOf(pVar.f2216b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                    pVar.d();
                } else {
                    pVar.c();
                }
            }
            i7++;
        }
    }

    private void e1() {
        if (this.f2176l != null) {
            for (int i7 = 0; i7 < this.f2176l.size(); i7++) {
                this.f2176l.get(i7).a();
            }
        }
    }

    static int g1(int i7) {
        if (i7 == 4097) {
            return 8194;
        }
        if (i7 != 4099) {
            return i7 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private void j0() {
        if (P) {
            Iterator<x> it = r().iterator();
            while (it.hasNext()) {
                it.next().k();
            }
        } else if (this.L != null) {
            while (!this.L.isEmpty()) {
                this.L.remove(0).d();
            }
        }
    }

    private boolean k0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this.f2165a) {
            if (this.f2165a.isEmpty()) {
                return false;
            }
            int size = this.f2165a.size();
            boolean zA = false;
            for (int i7 = 0; i7 < size; i7++) {
                zA |= this.f2165a.get(i7).a(arrayList, arrayList2);
            }
            this.f2165a.clear();
            this.f2182r.l().removeCallbacks(this.N);
            return zA;
        }
    }

    private void m(Fragment fragment) {
        HashSet<d0.b> hashSet = this.f2177m.get(fragment);
        if (hashSet != null) {
            Iterator<d0.b> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            hashSet.clear();
            w(fragment);
            this.f2177m.remove(fragment);
        }
    }

    private androidx.fragment.app.m m0(Fragment fragment) {
        return this.M.k(fragment);
    }

    private void n1(Fragment fragment) {
        ViewGroup viewGroupP0 = p0(fragment);
        if (viewGroupP0 == null || fragment.t() + fragment.w() + fragment.J() + fragment.K() <= 0) {
            return;
        }
        int i7 = q0.b.visible_removing_fragment_view_tag;
        if (viewGroupP0.getTag(i7) == null) {
            viewGroupP0.setTag(i7, fragment);
        }
        ((Fragment) viewGroupP0.getTag(i7)).J1(fragment.I());
    }

    private void o() {
        if (K0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void p() {
        this.f2166b = false;
        this.J.clear();
        this.I.clear();
    }

    private ViewGroup p0(Fragment fragment) {
        ViewGroup viewGroup = fragment.L;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.C > 0 && this.f2183s.h()) {
            View viewF = this.f2183s.f(fragment.C);
            if (viewF instanceof ViewGroup) {
                return (ViewGroup) viewF;
            }
        }
        return null;
    }

    private void p1() {
        Iterator<androidx.fragment.app.p> it = this.f2167c.k().iterator();
        while (it.hasNext()) {
            S0(it.next());
        }
    }

    private void q1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new w("FragmentManager"));
        androidx.fragment.app.i<?> iVar = this.f2182r;
        if (iVar != null) {
            try {
                iVar.m("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e7) {
                Log.e("FragmentManager", "Failed dumping state", e7);
                throw runtimeException;
            }
        }
        try {
            W("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e8) {
            Log.e("FragmentManager", "Failed dumping state", e8);
            throw runtimeException;
        }
    }

    private Set<x> r() {
        HashSet hashSet = new HashSet();
        Iterator<androidx.fragment.app.p> it = this.f2167c.k().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = it.next().k().L;
            if (viewGroup != null) {
                hashSet.add(x.o(viewGroup, y0()));
            }
        }
        return hashSet;
    }

    private Set<x> s(ArrayList<androidx.fragment.app.a> arrayList, int i7, int i8) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i7 < i8) {
            Iterator<r.a> it = arrayList.get(i7).f2368c.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().f2386b;
                if (fragment != null && (viewGroup = fragment.L) != null) {
                    hashSet.add(x.n(viewGroup, this));
                }
            }
            i7++;
        }
        return hashSet;
    }

    private void s1() {
        synchronized (this.f2165a) {
            if (this.f2165a.isEmpty()) {
                this.f2172h.f(l0() > 0 && I0(this.f2184t));
            } else {
                this.f2172h.f(true);
            }
        }
    }

    private void u(Fragment fragment) {
        Animator animator;
        if (fragment.M != null) {
            e.d dVarC = androidx.fragment.app.e.c(this.f2182r.j(), fragment, !fragment.E, fragment.I());
            if (dVarC == null || (animator = dVarC.f2327b) == null) {
                if (dVarC != null) {
                    fragment.M.startAnimation(dVarC.f2326a);
                    dVarC.f2326a.start();
                }
                fragment.M.setVisibility((!fragment.E || fragment.e0()) ? 0 : 8);
                if (fragment.e0()) {
                    fragment.E1(false);
                }
            } else {
                animator.setTarget(fragment.M);
                if (!fragment.E) {
                    fragment.M.setVisibility(0);
                } else if (fragment.e0()) {
                    fragment.E1(false);
                } else {
                    ViewGroup viewGroup = fragment.L;
                    View view = fragment.M;
                    viewGroup.startViewTransition(view);
                    dVarC.f2327b.addListener(new h(viewGroup, view, fragment));
                }
                dVarC.f2327b.start();
            }
        }
        D0(fragment);
        fragment.S = false;
        fragment.E0(fragment.E);
    }

    private void w(Fragment fragment) {
        fragment.d1();
        this.f2179o.n(fragment, false);
        fragment.L = null;
        fragment.M = null;
        fragment.Y = null;
        fragment.Z.m(null);
        fragment.f2124t = false;
    }

    static Fragment z0(View view) {
        Object tag = view.getTag(q0.b.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    void A(Configuration configuration) {
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.X0(configuration);
            }
        }
    }

    d0 A0(Fragment fragment) {
        return this.M.n(fragment);
    }

    boolean B(MenuItem menuItem) {
        if (this.f2181q < 1) {
            return false;
        }
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null && fragment.Y0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void B0() {
        a0(true);
        if (this.f2172h.c()) {
            V0();
        } else {
            this.f2171g.d();
        }
    }

    void C() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        S(1);
    }

    void C0(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.E) {
            return;
        }
        fragment.E = true;
        fragment.S = true ^ fragment.S;
        n1(fragment);
    }

    boolean D(Menu menu, MenuInflater menuInflater) {
        if (this.f2181q < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z6 = false;
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null && H0(fragment) && fragment.a1(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z6 = true;
            }
        }
        if (this.f2169e != null) {
            for (int i7 = 0; i7 < this.f2169e.size(); i7++) {
                Fragment fragment2 = this.f2169e.get(i7);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.A0();
                }
            }
        }
        this.f2169e = arrayList;
        return z6;
    }

    void D0(Fragment fragment) {
        if (fragment.f2121q && G0(fragment)) {
            this.D = true;
        }
    }

    void E() {
        this.G = true;
        a0(true);
        X();
        S(-1);
        this.f2182r = null;
        this.f2183s = null;
        this.f2184t = null;
        if (this.f2171g != null) {
            this.f2172h.d();
            this.f2171g = null;
        }
        androidx.activity.result.b<Intent> bVar = this.f2190z;
        if (bVar != null) {
            bVar.c();
            this.A.c();
            this.B.c();
        }
    }

    public boolean E0() {
        return this.G;
    }

    void F() {
        S(1);
    }

    void G() {
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.g1();
            }
        }
    }

    void H(boolean z6) {
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.h1(z6);
            }
        }
    }

    boolean H0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.g0();
    }

    void I(Fragment fragment) {
        Iterator<androidx.fragment.app.n> it = this.f2180p.iterator();
        while (it.hasNext()) {
            it.next().b(this, fragment);
        }
    }

    boolean I0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.f2128x;
        return fragment.equals(fragmentManager.x0()) && I0(fragmentManager.f2184t);
    }

    boolean J(MenuItem menuItem) {
        if (this.f2181q < 1) {
            return false;
        }
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null && fragment.i1(menuItem)) {
                return true;
            }
        }
        return false;
    }

    boolean J0(int i7) {
        return this.f2181q >= i7;
    }

    void K(Menu menu) {
        if (this.f2181q < 1) {
            return;
        }
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.j1(menu);
            }
        }
    }

    public boolean K0() {
        return this.E || this.F;
    }

    void M() {
        S(5);
    }

    void M0(Fragment fragment) {
        if (!this.f2167c.c(fragment.f2115k)) {
            if (F0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.f2181q + "since it is not added to " + this);
                return;
            }
            return;
        }
        O0(fragment);
        View view = fragment.M;
        if (view != null && fragment.R && fragment.L != null) {
            float f7 = fragment.T;
            if (f7 > 0.0f) {
                view.setAlpha(f7);
            }
            fragment.T = 0.0f;
            fragment.R = false;
            e.d dVarC = androidx.fragment.app.e.c(this.f2182r.j(), fragment, true, fragment.I());
            if (dVarC != null) {
                Animation animation = dVarC.f2326a;
                if (animation != null) {
                    fragment.M.startAnimation(animation);
                } else {
                    dVarC.f2327b.setTarget(fragment.M);
                    dVarC.f2327b.start();
                }
            }
        }
        if (fragment.S) {
            u(fragment);
        }
    }

    void N(boolean z6) {
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.l1(z6);
            }
        }
    }

    void N0(int i7, boolean z6) {
        androidx.fragment.app.i<?> iVar;
        if (this.f2182r == null && i7 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z6 || i7 != this.f2181q) {
            this.f2181q = i7;
            if (P) {
                this.f2167c.r();
            } else {
                Iterator<Fragment> it = this.f2167c.n().iterator();
                while (it.hasNext()) {
                    M0(it.next());
                }
                for (androidx.fragment.app.p pVar : this.f2167c.k()) {
                    Fragment fragmentK = pVar.k();
                    if (!fragmentK.R) {
                        M0(fragmentK);
                    }
                    if (fragmentK.f2122r && !fragmentK.f0()) {
                        this.f2167c.q(pVar);
                    }
                }
            }
            p1();
            if (this.D && (iVar = this.f2182r) != null && this.f2181q == 7) {
                iVar.q();
                this.D = false;
            }
        }
    }

    boolean O(Menu menu) {
        boolean z6 = false;
        if (this.f2181q < 1) {
            return false;
        }
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null && H0(fragment) && fragment.m1(menu)) {
                z6 = true;
            }
        }
        return z6;
    }

    void O0(Fragment fragment) {
        P0(fragment, this.f2181q);
    }

    void P() {
        s1();
        L(this.f2185u);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void P0(androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.P0(androidx.fragment.app.Fragment, int):void");
    }

    void Q() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        S(7);
    }

    void Q0() {
        if (this.f2182r == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.M.q(false);
        for (Fragment fragment : this.f2167c.n()) {
            if (fragment != null) {
                fragment.n0();
            }
        }
    }

    void R() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        S(5);
    }

    void R0(FragmentContainerView fragmentContainerView) {
        View view;
        for (androidx.fragment.app.p pVar : this.f2167c.k()) {
            Fragment fragmentK = pVar.k();
            if (fragmentK.C == fragmentContainerView.getId() && (view = fragmentK.M) != null && view.getParent() == null) {
                fragmentK.L = fragmentContainerView;
                pVar.b();
            }
        }
    }

    void S0(androidx.fragment.app.p pVar) {
        Fragment fragmentK = pVar.k();
        if (fragmentK.N) {
            if (this.f2166b) {
                this.H = true;
                return;
            }
            fragmentK.N = false;
            if (P) {
                pVar.m();
            } else {
                O0(fragmentK);
            }
        }
    }

    void T() {
        this.F = true;
        this.M.q(true);
        S(4);
    }

    public void T0(int i7, int i8) {
        if (i7 >= 0) {
            Y(new o(null, i7, i8), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i7);
    }

    void U() {
        S(2);
    }

    public void U0(String str, int i7) {
        Y(new o(str, -1, i7), false);
    }

    public boolean V0() {
        return W0(null, -1, 0);
    }

    public void W(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f2167c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.f2169e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i7 = 0; i7 < size2; i7++) {
                Fragment fragment = this.f2169e.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.f2168d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i8 = 0; i8 < size; i8++) {
                androidx.fragment.app.a aVar = this.f2168d.get(i8);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.z(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f2173i.get());
        synchronized (this.f2165a) {
            int size3 = this.f2165a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i9 = 0; i9 < size3; i9++) {
                    n nVar = this.f2165a.get(i9);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i9);
                    printWriter.print(": ");
                    printWriter.println(nVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f2182r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f2183s);
        if (this.f2184t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f2184t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f2181q);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.E);
        printWriter.print(" mStopped=");
        printWriter.print(this.F);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.G);
        if (this.D) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.D);
        }
    }

    boolean X0(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i7, int i8) {
        int i9;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.f2168d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i7 < 0 && (i8 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f2168d.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            if (str != null || i7 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.f2168d.get(size2);
                    if ((str != null && str.equals(aVar.E())) || (i7 >= 0 && i7 == aVar.f2242v)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i8 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.f2168d.get(size2);
                        if (str == null || !str.equals(aVar2.E())) {
                            if (i7 < 0 || i7 != aVar2.f2242v) {
                                break;
                            }
                        }
                    }
                }
                i9 = size2;
            } else {
                i9 = -1;
            }
            if (i9 == this.f2168d.size() - 1) {
                return false;
            }
            for (int size3 = this.f2168d.size() - 1; size3 > i9; size3--) {
                arrayList.add(this.f2168d.remove(size3));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    void Y(n nVar, boolean z6) {
        if (!z6) {
            if (this.f2182r == null) {
                if (!this.G) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            o();
        }
        synchronized (this.f2165a) {
            if (this.f2182r == null) {
                if (!z6) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.f2165a.add(nVar);
                j1();
            }
        }
    }

    public void Z0(Bundle bundle, String str, Fragment fragment) {
        if (fragment.f2128x != this) {
            q1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.f2115k);
    }

    boolean a0(boolean z6) {
        Z(z6);
        boolean z7 = false;
        while (k0(this.I, this.J)) {
            this.f2166b = true;
            try {
                d1(this.I, this.J);
                p();
                z7 = true;
            } catch (Throwable th) {
                p();
                throw th;
            }
        }
        s1();
        V();
        this.f2167c.b();
        return z7;
    }

    public void a1(l lVar, boolean z6) {
        this.f2179o.o(lVar, z6);
    }

    void b0(n nVar, boolean z6) {
        if (z6 && (this.f2182r == null || this.G)) {
            return;
        }
        Z(z6);
        if (nVar.a(this.I, this.J)) {
            this.f2166b = true;
            try {
                d1(this.I, this.J);
            } finally {
                p();
            }
        }
        s1();
        V();
        this.f2167c.b();
    }

    void b1(Fragment fragment, d0.b bVar) {
        HashSet<d0.b> hashSet = this.f2177m.get(fragment);
        if (hashSet != null && hashSet.remove(bVar) && hashSet.isEmpty()) {
            this.f2177m.remove(fragment);
            if (fragment.f2109e < 5) {
                w(fragment);
                O0(fragment);
            }
        }
    }

    void c1(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.f2127w);
        }
        boolean z6 = !fragment.f0();
        if (!fragment.F || z6) {
            this.f2167c.s(fragment);
            if (G0(fragment)) {
                this.D = true;
            }
            fragment.f2122r = true;
            n1(fragment);
        }
    }

    void e(androidx.fragment.app.a aVar) {
        if (this.f2168d == null) {
            this.f2168d = new ArrayList<>();
        }
        this.f2168d.add(aVar);
    }

    void f(Fragment fragment, d0.b bVar) {
        if (this.f2177m.get(fragment) == null) {
            this.f2177m.put(fragment, new HashSet<>());
        }
        this.f2177m.get(fragment).add(bVar);
    }

    Fragment f0(String str) {
        return this.f2167c.f(str);
    }

    void f1(Parcelable parcelable) {
        androidx.fragment.app.p pVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f2218e == null) {
            return;
        }
        this.f2167c.t();
        for (FragmentState fragmentState : fragmentManagerState.f2218e) {
            if (fragmentState != null) {
                Fragment fragmentJ = this.M.j(fragmentState.f2227g);
                if (fragmentJ != null) {
                    if (F0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragmentJ);
                    }
                    pVar = new androidx.fragment.app.p(this.f2179o, this.f2167c, fragmentJ, fragmentState);
                } else {
                    pVar = new androidx.fragment.app.p(this.f2179o, this.f2167c, this.f2182r.j().getClassLoader(), q0(), fragmentState);
                }
                Fragment fragmentK = pVar.k();
                fragmentK.f2128x = this;
                if (F0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentK.f2115k + "): " + fragmentK);
                }
                pVar.o(this.f2182r.j().getClassLoader());
                this.f2167c.p(pVar);
                pVar.u(this.f2181q);
            }
        }
        for (Fragment fragment : this.M.m()) {
            if (!this.f2167c.c(fragment.f2115k)) {
                if (F0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f2218e);
                }
                this.M.p(fragment);
                fragment.f2128x = this;
                androidx.fragment.app.p pVar2 = new androidx.fragment.app.p(this.f2179o, this.f2167c, fragment);
                pVar2.u(1);
                pVar2.m();
                fragment.f2122r = true;
                pVar2.m();
            }
        }
        this.f2167c.u(fragmentManagerState.f2219g);
        if (fragmentManagerState.f2220h != null) {
            this.f2168d = new ArrayList<>(fragmentManagerState.f2220h.length);
            int i7 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f2220h;
                if (i7 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarB = backStackStateArr[i7].b(this);
                if (F0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i7 + " (index " + aVarB.f2242v + "): " + aVarB);
                    PrintWriter printWriter = new PrintWriter(new w("FragmentManager"));
                    aVarB.A("  ", printWriter, false);
                    printWriter.close();
                }
                this.f2168d.add(aVarB);
                i7++;
            }
        } else {
            this.f2168d = null;
        }
        this.f2173i.set(fragmentManagerState.f2221i);
        String str = fragmentManagerState.f2222j;
        if (str != null) {
            Fragment fragmentF0 = f0(str);
            this.f2185u = fragmentF0;
            L(fragmentF0);
        }
        ArrayList<String> arrayList = fragmentManagerState.f2223k;
        if (arrayList != null) {
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                Bundle bundle = fragmentManagerState.f2224l.get(i8);
                bundle.setClassLoader(this.f2182r.j().getClassLoader());
                this.f2174j.put(arrayList.get(i8), bundle);
            }
        }
        this.C = new ArrayDeque<>(fragmentManagerState.f2225m);
    }

    androidx.fragment.app.p g(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        androidx.fragment.app.p pVarV = v(fragment);
        fragment.f2128x = this;
        this.f2167c.p(pVarV);
        if (!fragment.F) {
            this.f2167c.a(fragment);
            fragment.f2122r = false;
            if (fragment.M == null) {
                fragment.S = false;
            }
            if (G0(fragment)) {
                this.D = true;
            }
        }
        return pVarV;
    }

    public Fragment g0(int i7) {
        return this.f2167c.g(i7);
    }

    public void h(androidx.fragment.app.n nVar) {
        this.f2180p.add(nVar);
    }

    public Fragment h0(String str) {
        return this.f2167c.h(str);
    }

    Parcelable h1() {
        int size;
        j0();
        X();
        a0(true);
        this.E = true;
        this.M.q(true);
        ArrayList<FragmentState> arrayListV = this.f2167c.v();
        BackStackState[] backStackStateArr = null;
        if (arrayListV.isEmpty()) {
            if (F0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> arrayListW = this.f2167c.w();
        ArrayList<androidx.fragment.app.a> arrayList = this.f2168d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i7 = 0; i7 < size; i7++) {
                backStackStateArr[i7] = new BackStackState(this.f2168d.get(i7));
                if (F0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i7 + ": " + this.f2168d.get(i7));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f2218e = arrayListV;
        fragmentManagerState.f2219g = arrayListW;
        fragmentManagerState.f2220h = backStackStateArr;
        fragmentManagerState.f2221i = this.f2173i.get();
        Fragment fragment = this.f2185u;
        if (fragment != null) {
            fragmentManagerState.f2222j = fragment.f2115k;
        }
        fragmentManagerState.f2223k.addAll(this.f2174j.keySet());
        fragmentManagerState.f2224l.addAll(this.f2174j.values());
        fragmentManagerState.f2225m = new ArrayList<>(this.C);
        return fragmentManagerState;
    }

    int i() {
        return this.f2173i.getAndIncrement();
    }

    Fragment i0(String str) {
        return this.f2167c.i(str);
    }

    public Fragment.SavedState i1(Fragment fragment) {
        androidx.fragment.app.p pVarM = this.f2167c.m(fragment.f2115k);
        if (pVarM == null || !pVarM.k().equals(fragment)) {
            q1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return pVarM.r();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    void j(androidx.fragment.app.i<?> iVar, androidx.fragment.app.f fVar, Fragment fragment) {
        String str;
        if (this.f2182r != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f2182r = iVar;
        this.f2183s = fVar;
        this.f2184t = fragment;
        if (fragment != null) {
            h(new i(fragment));
        } else if (iVar instanceof androidx.fragment.app.n) {
            h((androidx.fragment.app.n) iVar);
        }
        if (this.f2184t != null) {
            s1();
        }
        if (iVar instanceof androidx.activity.c) {
            androidx.activity.c cVar = (androidx.activity.c) iVar;
            OnBackPressedDispatcher onBackPressedDispatcherC = cVar.c();
            this.f2171g = onBackPressedDispatcherC;
            androidx.lifecycle.o oVar = cVar;
            if (fragment != null) {
                oVar = fragment;
            }
            onBackPressedDispatcherC.a(oVar, this.f2172h);
        }
        if (fragment != null) {
            this.M = fragment.f2128x.m0(fragment);
        } else if (iVar instanceof e0) {
            this.M = androidx.fragment.app.m.l(((e0) iVar).k());
        } else {
            this.M = new androidx.fragment.app.m(false);
        }
        this.M.q(K0());
        this.f2167c.x(this.M);
        Object obj = this.f2182r;
        if (obj instanceof androidx.activity.result.c) {
            ActivityResultRegistry activityResultRegistryG = ((androidx.activity.result.c) obj).g();
            if (fragment != null) {
                str = fragment.f2115k + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.f2190z = activityResultRegistryG.j(str2 + "StartActivityForResult", new d.c(), new j());
            this.A = activityResultRegistryG.j(str2 + "StartIntentSenderForResult", new k(), new a());
            this.B = activityResultRegistryG.j(str2 + "RequestPermissions", new d.b(), new b());
        }
    }

    void j1() {
        synchronized (this.f2165a) {
            ArrayList<p> arrayList = this.L;
            boolean z6 = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z7 = this.f2165a.size() == 1;
            if (z6 || z7) {
                this.f2182r.l().removeCallbacks(this.N);
                this.f2182r.l().post(this.N);
                s1();
            }
        }
    }

    void k(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.F) {
            fragment.F = false;
            if (fragment.f2121q) {
                return;
            }
            this.f2167c.a(fragment);
            if (F0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (G0(fragment)) {
                this.D = true;
            }
        }
    }

    void k1(Fragment fragment, boolean z6) {
        ViewGroup viewGroupP0 = p0(fragment);
        if (viewGroupP0 == null || !(viewGroupP0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupP0).setDrawDisappearingViewsLast(!z6);
    }

    public r l() {
        return new androidx.fragment.app.a(this);
    }

    public int l0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.f2168d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    void l1(Fragment fragment, i.c cVar) {
        if (fragment.equals(f0(fragment.f2115k)) && (fragment.f2129y == null || fragment.f2128x == this)) {
            fragment.W = cVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void m1(Fragment fragment) {
        if (fragment == null || (fragment.equals(f0(fragment.f2115k)) && (fragment.f2129y == null || fragment.f2128x == this))) {
            Fragment fragment2 = this.f2185u;
            this.f2185u = fragment;
            L(fragment2);
            L(this.f2185u);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    boolean n() {
        boolean zG0 = false;
        for (Fragment fragment : this.f2167c.l()) {
            if (fragment != null) {
                zG0 = G0(fragment);
            }
            if (zG0) {
                return true;
            }
        }
        return false;
    }

    androidx.fragment.app.f n0() {
        return this.f2183s;
    }

    public Fragment o0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragmentF0 = f0(string);
        if (fragmentF0 == null) {
            q1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return fragmentF0;
    }

    void o1(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.E) {
            fragment.E = false;
            fragment.S = !fragment.S;
        }
    }

    public final void q(String str) {
        this.f2174j.remove(str);
    }

    public androidx.fragment.app.h q0() {
        androidx.fragment.app.h hVar = this.f2186v;
        if (hVar != null) {
            return hVar;
        }
        Fragment fragment = this.f2184t;
        return fragment != null ? fragment.f2128x.q0() : this.f2187w;
    }

    q r0() {
        return this.f2167c;
    }

    public void r1(l lVar) {
        this.f2179o.p(lVar);
    }

    public List<Fragment> s0() {
        return this.f2167c.n();
    }

    void t(androidx.fragment.app.a aVar, boolean z6, boolean z7, boolean z8) {
        if (z6) {
            aVar.C(z8);
        } else {
            aVar.B();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z6));
        if (z7 && this.f2181q >= 1) {
            s.B(this.f2182r.j(), this.f2183s, arrayList, arrayList2, 0, 1, true, this.f2178n);
        }
        if (z8) {
            N0(this.f2181q, true);
        }
        for (Fragment fragment : this.f2167c.l()) {
            if (fragment != null && fragment.M != null && fragment.R && aVar.F(fragment.C)) {
                float f7 = fragment.T;
                if (f7 > 0.0f) {
                    fragment.M.setAlpha(f7);
                }
                if (z8) {
                    fragment.T = 0.0f;
                } else {
                    fragment.T = -1.0f;
                    fragment.R = false;
                }
            }
        }
    }

    androidx.fragment.app.i<?> t0() {
        return this.f2182r;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.f2184t;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f2184t)));
            sb.append("}");
        } else {
            androidx.fragment.app.i<?> iVar = this.f2182r;
            if (iVar != null) {
                sb.append(iVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.f2182r)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    LayoutInflater.Factory2 u0() {
        return this.f2170f;
    }

    androidx.fragment.app.p v(Fragment fragment) {
        androidx.fragment.app.p pVarM = this.f2167c.m(fragment.f2115k);
        if (pVarM != null) {
            return pVarM;
        }
        androidx.fragment.app.p pVar = new androidx.fragment.app.p(this.f2179o, this.f2167c, fragment);
        pVar.o(this.f2182r.j().getClassLoader());
        pVar.u(this.f2181q);
        return pVar;
    }

    androidx.fragment.app.k v0() {
        return this.f2179o;
    }

    Fragment w0() {
        return this.f2184t;
    }

    void x(Fragment fragment) {
        if (F0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.F) {
            return;
        }
        fragment.F = true;
        if (fragment.f2121q) {
            if (F0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f2167c.s(fragment);
            if (G0(fragment)) {
                this.D = true;
            }
            n1(fragment);
        }
    }

    public Fragment x0() {
        return this.f2185u;
    }

    void y() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        S(4);
    }

    y y0() {
        y yVar = this.f2188x;
        if (yVar != null) {
            return yVar;
        }
        Fragment fragment = this.f2184t;
        return fragment != null ? fragment.f2128x.y0() : this.f2189y;
    }

    void z() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        S(0);
    }
}
