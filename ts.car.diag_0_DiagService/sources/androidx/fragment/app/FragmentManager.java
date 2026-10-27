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
import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.R$id;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.d;
import androidx.fragment.app.r;
import androidx.fragment.app.s;
import androidx.lifecycle.Lifecycle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentManager {
    private static boolean O = false;
    static boolean P = false;
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
    private boolean f622b;
    ArrayList<androidx.fragment.app.a> d;
    private ArrayList<Fragment> e;
    private OnBackPressedDispatcher g;
    private ArrayList<m> l;
    private androidx.fragment.app.i<?> r;
    private androidx.fragment.app.e s;
    private Fragment t;

    @Nullable
    Fragment u;
    private androidx.activity.result.b<Intent> z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<n> f621a = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final q f623c = new q();
    private final androidx.fragment.app.j f = new androidx.fragment.app.j(this);
    private final androidx.activity.b h = new c(false);
    private final AtomicInteger i = new AtomicInteger();
    private final ConcurrentHashMap<String, Bundle> j = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> k = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Fragment, HashSet<androidx.core.os.b>> m = new ConcurrentHashMap<>();
    private final s.g n = new d();
    private final androidx.fragment.app.k o = new androidx.fragment.app.k(this);
    private final CopyOnWriteArrayList<androidx.fragment.app.n> p = new CopyOnWriteArrayList<>();
    int q = -1;
    private androidx.fragment.app.h v = null;
    private androidx.fragment.app.h w = new e();
    private x x = null;
    private x y = new f(this);
    ArrayDeque<LaunchedFragmentInfo> C = new ArrayDeque<>();
    private Runnable N = new g();

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentManager$6, reason: invalid class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class AnonymousClass6 implements androidx.lifecycle.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f624c;
        final /* synthetic */ androidx.fragment.app.o d;
        final /* synthetic */ Lifecycle e;
        final /* synthetic */ FragmentManager f;

        @Override // androidx.lifecycle.d
        public void f(@NonNull androidx.lifecycle.f fVar, @NonNull Lifecycle.Event event) {
            Bundle bundle;
            if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) this.f.j.get(this.f624c)) != null) {
                this.d.a(this.f624c, bundle);
                this.f.q(this.f624c);
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.e.c(this);
                this.f.k.remove(this.f624c);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @SuppressLint({"BanParcelableUsage"})
    static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f625c;
        int d;

        /* JADX INFO: compiled from: caradapter-bug */
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
            public LaunchedFragmentInfo[] newArray(int i) {
                return new LaunchedFragmentInfo[i];
            }
        }

        LaunchedFragmentInfo(@NonNull Parcel parcel) {
            this.f625c = parcel.readString();
            this.d = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f625c);
            parcel.writeInt(this.d);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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
            String str = launchedFragmentInfoPollFirst.f625c;
            int i = launchedFragmentInfoPollFirst.d;
            Fragment fragmentI = FragmentManager.this.f623c.i(str);
            if (fragmentI != null) {
                fragmentI.Y(i, activityResult.b(), activityResult.a());
                return;
            }
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
            }
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = FragmentManager.this.C.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No permissions were requested for " + this);
                return;
            }
            String str = launchedFragmentInfoPollFirst.f625c;
            int i2 = launchedFragmentInfoPollFirst.d;
            Fragment fragmentI = FragmentManager.this.f623c.i(str);
            if (fragmentI != null) {
                fragmentI.x0(i2, strArr, iArr);
                return;
            }
            Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c extends androidx.activity.b {
        c(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            FragmentManager.this.A0();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements s.g {
        d() {
        }

        @Override // androidx.fragment.app.s.g
        public void a(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar) {
            if (bVar.b()) {
                return;
            }
            FragmentManager.this.V0(fragment, bVar);
        }

        @Override // androidx.fragment.app.s.g
        public void b(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar) {
            FragmentManager.this.f(fragment, bVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e extends androidx.fragment.app.h {
        e() {
        }

        @Override // androidx.fragment.app.h
        @NonNull
        public Fragment a(@NonNull ClassLoader classLoader, @NonNull String str) {
            return FragmentManager.this.s0().f(FragmentManager.this.s0().k(), str, null);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements x {
        f(FragmentManager fragmentManager) {
        }

        @Override // androidx.fragment.app.x
        @NonNull
        public SpecialEffectsController a(@NonNull ViewGroup viewGroup) {
            return new androidx.fragment.app.b(viewGroup);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.a0(true);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f632a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f633b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f634c;

        h(FragmentManager fragmentManager, ViewGroup viewGroup, View view, Fragment fragment) {
            this.f632a = viewGroup;
            this.f633b = view;
            this.f634c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f632a.endViewTransition(this.f633b);
            animator.removeListener(this);
            Fragment fragment = this.f634c;
            View view = fragment.J;
            if (view == null || !fragment.B) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class i implements androidx.fragment.app.n {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f635c;

        i(FragmentManager fragmentManager, Fragment fragment) {
            this.f635c = fragment;
        }

        @Override // androidx.fragment.app.n
        public void e(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            this.f635c.b0(fragment);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
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
            String str = launchedFragmentInfoPollFirst.f625c;
            int i = launchedFragmentInfoPollFirst.d;
            Fragment fragmentI = FragmentManager.this.f623c.i(str);
            if (fragmentI != null) {
                fragmentI.Y(i, activityResult.b(), activityResult.a());
                return;
            }
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class k extends androidx.activity.result.d.a<IntentSenderRequest, ActivityResult> {
        k() {
        }

        @Override // androidx.activity.result.d.a
        @NonNull
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ActivityResult a(int i, @Nullable Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static abstract class l {
        @Deprecated
        public abstract void a(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle);

        public abstract void b(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context);

        public abstract void c(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle);

        public abstract void d(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void e(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void f(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void g(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context);

        public abstract void h(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle);

        public abstract void i(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void j(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle);

        public abstract void k(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void l(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);

        public abstract void m(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle);

        public abstract void n(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface m {
        @MainThread
        void a();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface n {
        boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class o implements n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f637a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f638b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f639c;

        o(@Nullable String str, int i, int i2) {
            this.f637a = str;
            this.f638b = i;
            this.f639c = i2;
        }

        @Override // androidx.fragment.app.FragmentManager.n
        public boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.u;
            if (fragment == null || this.f638b >= 0 || this.f637a != null || !fragment.q().R0()) {
                return FragmentManager.this.T0(arrayList, arrayList2, this.f637a, this.f638b, this.f639c);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class p implements Fragment.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f640a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f641b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f642c;

        p(@NonNull androidx.fragment.app.a aVar, boolean z) {
            this.f640a = z;
            this.f641b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.d
        public void a() {
            int i = this.f642c - 1;
            this.f642c = i;
            if (i != 0) {
                return;
            }
            this.f641b.q.c1();
        }

        @Override // androidx.fragment.app.Fragment.d
        public void b() {
            this.f642c++;
        }

        void c() {
            androidx.fragment.app.a aVar = this.f641b;
            aVar.q.t(aVar, this.f640a, false, false);
        }

        void d() {
            boolean z = this.f642c > 0;
            for (Fragment fragment : this.f641b.q.r0()) {
                fragment.o1(null);
                if (z && fragment.S()) {
                    fragment.q1();
                }
            }
            androidx.fragment.app.a aVar = this.f641b;
            aVar.q.t(aVar, this.f640a, !z, true);
        }

        public boolean e() {
            return this.f642c == 0;
        }
    }

    static boolean D0(int i2) {
        return O || Log.isLoggable("FragmentManager", i2);
    }

    private boolean E0(@NonNull Fragment fragment) {
        return (fragment.F && fragment.G) || fragment.w.n();
    }

    private void J0(@NonNull b.a.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentH = bVar.h(i2);
            if (!fragmentH.n) {
                View viewE1 = fragmentH.e1();
                fragmentH.P = viewE1.getAlpha();
                viewE1.setAlpha(0.0f);
            }
        }
    }

    private void L(@Nullable Fragment fragment) {
        if (fragment == null || !fragment.equals(f0(fragment.h))) {
            return;
        }
        fragment.W0();
    }

    private void S(int i2) {
        try {
            this.f622b = true;
            this.f623c.d(i2);
            L0(i2, false);
            if (P) {
                Iterator<SpecialEffectsController> it = r().iterator();
                while (it.hasNext()) {
                    it.next().h();
                }
            }
            this.f622b = false;
            a0(true);
        } catch (Throwable th) {
            this.f622b = false;
            throw th;
        }
    }

    private boolean S0(@Nullable String str, int i2, int i3) {
        a0(false);
        Z(true);
        Fragment fragment = this.u;
        if (fragment != null && i2 < 0 && str == null && fragment.q().R0()) {
            return true;
        }
        boolean zT0 = T0(this.I, this.J, str, i2, i3);
        if (zT0) {
            this.f622b = true;
            try {
                X0(this.I, this.J);
            } finally {
                p();
            }
        }
        j1();
        V();
        this.f623c.b();
        return zT0;
    }

    private int U0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3, @NonNull b.a.b<Fragment> bVar) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.x() && !aVar.v(arrayList, i5 + 1, i3)) {
                if (this.L == null) {
                    this.L = new ArrayList<>();
                }
                p pVar = new p(aVar, zBooleanValue);
                this.L.add(pVar);
                aVar.z(pVar);
                if (zBooleanValue) {
                    aVar.q();
                } else {
                    aVar.r(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                d(bVar);
            }
        }
        return i4;
    }

    private void V() {
        if (this.H) {
            this.H = false;
            i1();
        }
    }

    private void X() {
        if (P) {
            Iterator<SpecialEffectsController> it = r().iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        } else {
            if (this.m.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.m.keySet()) {
                m(fragment);
                M0(fragment);
            }
        }
    }

    private void X0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        e0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).o) {
                if (i3 != i2) {
                    d0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).o) {
                        i3++;
                    }
                }
                d0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            d0(arrayList, arrayList2, i3, size);
        }
    }

    private void Y0() {
        if (this.l != null) {
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                this.l.get(i2).a();
            }
        }
    }

    private void Z(boolean z) {
        if (this.f622b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.r == null) {
            if (!this.G) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.r.l().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            o();
        }
        if (this.I == null) {
            this.I = new ArrayList<>();
            this.J = new ArrayList<>();
        }
        this.f622b = true;
        try {
            e0(null, null);
        } finally {
            this.f622b = false;
        }
    }

    static int a1(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private static void c0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.m(-1);
                aVar.r(i2 == i3 + (-1));
            } else {
                aVar.m(1);
                aVar.q();
            }
            i2++;
        }
    }

    private void d(@NonNull b.a.b<Fragment> bVar) {
        int i2 = this.q;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 5);
        for (Fragment fragment : this.f623c.n()) {
            if (fragment.f612c < iMin) {
                N0(fragment, iMin);
                if (fragment.J != null && !fragment.B && fragment.N) {
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
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
    private void d0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        boolean z;
        ?? r14;
        boolean z2;
        int i4;
        int i5;
        ArrayList<Boolean> arrayList3;
        int iU0;
        ArrayList<Boolean> arrayList4;
        int i6;
        ?? r1;
        boolean z3 = arrayList.get(i2).o;
        ArrayList<Fragment> arrayList5 = this.K;
        if (arrayList5 == null) {
            this.K = new ArrayList<>();
        } else {
            arrayList5.clear();
        }
        this.K.addAll(this.f623c.n());
        Fragment fragmentW0 = w0();
        boolean z4 = false;
        for (int i7 = i2; i7 < i3; i7++) {
            androidx.fragment.app.a aVar = arrayList.get(i7);
            fragmentW0 = !arrayList2.get(i7).booleanValue() ? aVar.s(this.K, fragmentW0) : aVar.A(this.K, fragmentW0);
            z4 = z4 || aVar.g;
        }
        this.K.clear();
        if (z3 || this.q < 1) {
            z = true;
        } else if (P) {
            for (int i8 = i2; i8 < i3; i8++) {
                Iterator<r.a> it = arrayList.get(i8).f711a.iterator();
                while (it.hasNext()) {
                    Fragment fragment = it.next().f715b;
                    if (fragment != null) {
                        this.f623c.p(v(fragment));
                    }
                }
            }
            z = true;
        } else {
            z = true;
            s.C(this.r.k(), this.s, arrayList, arrayList2, i2, i3, false, this.n);
        }
        c0(arrayList, arrayList2, i2, i3);
        if (P) {
            if (z3) {
                L0(this.q, z);
            }
            boolean zBooleanValue = arrayList2.get(i3 - 1).booleanValue();
            for (SpecialEffectsController specialEffectsController : s(arrayList, i2, i3)) {
                specialEffectsController.o(zBooleanValue);
                specialEffectsController.n();
                specialEffectsController.h();
            }
            i6 = i3;
            arrayList4 = arrayList2;
        } else {
            if (z3) {
                b.a.b bVar = new b.a.b();
                d(bVar);
                r14 = z;
                z2 = z3;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iU0 = U0(arrayList, arrayList2, i2, i3, bVar);
                J0(bVar);
            } else {
                r14 = z;
                z2 = z3;
                i4 = i3;
                i5 = i2;
                arrayList3 = arrayList2;
                iU0 = i4;
            }
            if (iU0 == i5 || !z2) {
                arrayList4 = arrayList3;
                i6 = i4;
            } else {
                if (this.q >= r14) {
                    arrayList4 = arrayList3;
                    int i9 = iU0;
                    i6 = i4;
                    r1 = r14;
                    s.C(this.r.k(), this.s, arrayList, arrayList2, i2, i9, true, this.n);
                } else {
                    arrayList4 = arrayList3;
                    i6 = i4;
                    r1 = r14;
                }
                L0(this.q, r1);
            }
        }
        for (int i10 = i2; i10 < i6; i10++) {
            androidx.fragment.app.a aVar2 = arrayList.get(i10);
            if (arrayList4.get(i10).booleanValue() && aVar2.s >= 0) {
                aVar2.s = -1;
            }
            aVar2.y();
        }
        if (z4) {
            Y0();
        }
    }

    private void e0(@Nullable ArrayList<androidx.fragment.app.a> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<p> arrayList3 = this.L;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            p pVar = this.L.get(i2);
            if (arrayList != null && !pVar.f640a && (iIndexOf2 = arrayList.indexOf(pVar.f641b)) != -1 && arrayList2 != null && arrayList2.get(iIndexOf2).booleanValue()) {
                this.L.remove(i2);
                i2--;
                size--;
                pVar.c();
            } else if (pVar.e() || (arrayList != null && pVar.f641b.v(arrayList, 0, arrayList.size()))) {
                this.L.remove(i2);
                i2--;
                size--;
                if (arrayList == null || pVar.f640a || (iIndexOf = arrayList.indexOf(pVar.f641b)) == -1 || arrayList2 == null || !arrayList2.get(iIndexOf).booleanValue()) {
                    pVar.d();
                } else {
                    pVar.c();
                }
            }
            i2++;
        }
    }

    private void g1(@NonNull Fragment fragment) {
        ViewGroup viewGroupO0 = o0(fragment);
        if (viewGroupO0 != null) {
            if (viewGroupO0.getTag(R$id.visible_removing_fragment_view_tag) == null) {
                viewGroupO0.setTag(R$id.visible_removing_fragment_view_tag, fragment);
            }
            ((Fragment) viewGroupO0.getTag(R$id.visible_removing_fragment_view_tag)).m1(fragment.y());
        }
    }

    private void i1() {
        Iterator<androidx.fragment.app.p> it = this.f623c.k().iterator();
        while (it.hasNext()) {
            P0(it.next());
        }
    }

    private void j0() {
        if (P) {
            Iterator<SpecialEffectsController> it = r().iterator();
            while (it.hasNext()) {
                it.next().i();
            }
        } else if (this.L != null) {
            while (!this.L.isEmpty()) {
                this.L.remove(0).d();
            }
        }
    }

    private void j1() {
        synchronized (this.f621a) {
            if (this.f621a.isEmpty()) {
                this.h.f(l0() > 0 && G0(this.t));
            } else {
                this.h.f(true);
            }
        }
    }

    private boolean k0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        synchronized (this.f621a) {
            if (this.f621a.isEmpty()) {
                return false;
            }
            int size = this.f621a.size();
            boolean zA = false;
            for (int i2 = 0; i2 < size; i2++) {
                zA |= this.f621a.get(i2).a(arrayList, arrayList2);
            }
            this.f621a.clear();
            this.r.l().removeCallbacks(this.N);
            return zA;
        }
    }

    private void m(@NonNull Fragment fragment) {
        HashSet<androidx.core.os.b> hashSet = this.m.get(fragment);
        if (hashSet != null) {
            Iterator<androidx.core.os.b> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            hashSet.clear();
            w(fragment);
            this.m.remove(fragment);
        }
    }

    @NonNull
    private androidx.fragment.app.m m0(@NonNull Fragment fragment) {
        return this.M.h(fragment);
    }

    private void o() {
        if (I0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private ViewGroup o0(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.I;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.z > 0 && this.s.i()) {
            View viewG = this.s.g(fragment.z);
            if (viewG instanceof ViewGroup) {
                return (ViewGroup) viewG;
            }
        }
        return null;
    }

    private void p() {
        this.f622b = false;
        this.J.clear();
        this.I.clear();
    }

    private Set<SpecialEffectsController> r() {
        HashSet hashSet = new HashSet();
        Iterator<androidx.fragment.app.p> it = this.f623c.k().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = it.next().j().I;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.m(viewGroup, x0()));
            }
        }
        return hashSet;
    }

    private Set<SpecialEffectsController> s(@NonNull ArrayList<androidx.fragment.app.a> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator<r.a> it = arrayList.get(i2).f711a.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().f715b;
                if (fragment != null && (viewGroup = fragment.I) != null) {
                    hashSet.add(SpecialEffectsController.l(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    private void u(@NonNull Fragment fragment) {
        Animator animator;
        if (fragment.J != null) {
            d.C0023d c0023dB = androidx.fragment.app.d.b(this.r.k(), fragment, !fragment.B);
            if (c0023dB == null || (animator = c0023dB.f690b) == null) {
                if (c0023dB != null) {
                    fragment.J.startAnimation(c0023dB.f689a);
                    c0023dB.f689a.start();
                }
                fragment.J.setVisibility((!fragment.B || fragment.P()) ? 0 : 8);
                if (fragment.P()) {
                    fragment.l1(false);
                }
            } else {
                animator.setTarget(fragment.J);
                if (!fragment.B) {
                    fragment.J.setVisibility(0);
                } else if (fragment.P()) {
                    fragment.l1(false);
                } else {
                    ViewGroup viewGroup = fragment.I;
                    View view = fragment.J;
                    viewGroup.startViewTransition(view);
                    c0023dB.f690b.addListener(new h(this, viewGroup, view, fragment));
                }
                c0023dB.f690b.start();
            }
        }
        if (fragment.n && E0(fragment)) {
            this.D = true;
        }
        fragment.O = false;
        fragment.n0(fragment.B);
    }

    private void w(@NonNull Fragment fragment) {
        fragment.M0();
        this.o.n(fragment, false);
        fragment.I = null;
        fragment.J = null;
        fragment.U = null;
        fragment.V.l(null);
        fragment.q = false;
    }

    @Nullable
    static Fragment y0(@NonNull View view) {
        Object tag = view.getTag(R$id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    void A(@NonNull Configuration configuration) {
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.G0(configuration);
            }
        }
    }

    void A0() {
        a0(true);
        if (this.h.c()) {
            R0();
        } else {
            this.g.c();
        }
    }

    boolean B(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null && fragment.H0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void B0(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.B) {
            return;
        }
        fragment.B = true;
        fragment.O = true ^ fragment.O;
        g1(fragment);
    }

    void C() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        S(1);
    }

    public boolean C0() {
        return this.G;
    }

    boolean D(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (this.q < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null && F0(fragment) && fragment.J0(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment2 = this.e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.j0();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    void E() {
        this.G = true;
        a0(true);
        X();
        S(-1);
        this.r = null;
        this.s = null;
        this.t = null;
        if (this.g != null) {
            this.h.d();
            this.g = null;
        }
        androidx.activity.result.b<Intent> bVar = this.z;
        if (bVar != null) {
            bVar.a();
            this.A.a();
            this.B.a();
        }
    }

    void F() {
        S(1);
    }

    boolean F0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.R();
    }

    void G() {
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.P0();
            }
        }
    }

    boolean G0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.u;
        return fragment.equals(fragmentManager.w0()) && G0(fragmentManager.t);
    }

    void H(boolean z) {
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.Q0(z);
            }
        }
    }

    boolean H0(int i2) {
        return this.q >= i2;
    }

    void I(@NonNull Fragment fragment) {
        Iterator<androidx.fragment.app.n> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().e(this, fragment);
        }
    }

    public boolean I0() {
        return this.E || this.F;
    }

    boolean J(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null && fragment.R0(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void K(@NonNull Menu menu) {
        if (this.q < 1) {
            return;
        }
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.S0(menu);
            }
        }
    }

    void K0(@NonNull Fragment fragment) {
        if (!this.f623c.c(fragment.h)) {
            if (D0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.q + "since it is not added to " + this);
                return;
            }
            return;
        }
        M0(fragment);
        View view = fragment.J;
        if (view != null && fragment.N && fragment.I != null) {
            float f2 = fragment.P;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.P = 0.0f;
            fragment.N = false;
            d.C0023d c0023dB = androidx.fragment.app.d.b(this.r.k(), fragment, true);
            if (c0023dB != null) {
                Animation animation = c0023dB.f689a;
                if (animation != null) {
                    fragment.J.startAnimation(animation);
                } else {
                    c0023dB.f690b.setTarget(fragment.J);
                    c0023dB.f690b.start();
                }
            }
        }
        if (fragment.O) {
            u(fragment);
        }
    }

    void L0(int i2, boolean z) {
        androidx.fragment.app.i<?> iVar;
        if (this.r == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.q) {
            this.q = i2;
            if (P) {
                this.f623c.r();
            } else {
                Iterator<Fragment> it = this.f623c.n().iterator();
                while (it.hasNext()) {
                    K0(it.next());
                }
                for (androidx.fragment.app.p pVar : this.f623c.k()) {
                    Fragment fragmentJ = pVar.j();
                    if (!fragmentJ.N) {
                        K0(fragmentJ);
                    }
                    if (fragmentJ.o && !fragmentJ.Q()) {
                        this.f623c.q(pVar);
                    }
                }
            }
            i1();
            if (this.D && (iVar = this.r) != null && this.q == 7) {
                iVar.p();
                this.D = false;
            }
        }
    }

    void M() {
        S(5);
    }

    void M0(@NonNull Fragment fragment) {
        N0(fragment, this.q);
    }

    void N(boolean z) {
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.U0(z);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void N0(@androidx.annotation.NonNull androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.N0(androidx.fragment.app.Fragment, int):void");
    }

    boolean O(@NonNull Menu menu) {
        boolean z = false;
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null && fragment.V0(menu)) {
                z = true;
            }
        }
        return z;
    }

    void O0() {
        if (this.r == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.M.n(false);
        for (Fragment fragment : this.f623c.n()) {
            if (fragment != null) {
                fragment.W();
            }
        }
    }

    void P() {
        j1();
        L(this.u);
    }

    void P0(@NonNull androidx.fragment.app.p pVar) {
        Fragment fragmentJ = pVar.j();
        if (fragmentJ.K) {
            if (this.f622b) {
                this.H = true;
                return;
            }
            fragmentJ.K = false;
            if (P) {
                pVar.k();
            } else {
                M0(fragmentJ);
            }
        }
    }

    void Q() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        S(7);
    }

    public void Q0(int i2, int i3) {
        if (i2 >= 0) {
            Y(new o(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    void R() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        S(5);
    }

    public boolean R0() {
        return S0(null, -1, 0);
    }

    void T() {
        this.F = true;
        this.M.n(true);
        S(4);
    }

    boolean T0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, @Nullable String str, int i2, int i3) {
        int i4;
        Boolean bool = Boolean.TRUE;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.d.remove(size));
            arrayList2.add(bool);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = this.d.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.d.get(size2);
                    if ((str != null && str.equals(aVar.t())) || (i2 >= 0 && i2 == aVar.s)) {
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
                        androidx.fragment.app.a aVar2 = this.d.get(size2);
                        if (str == null || !str.equals(aVar2.t())) {
                            if (i2 < 0 || i2 != aVar2.s) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.d.size() - 1) {
                return false;
            }
            for (int size3 = this.d.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.d.remove(size3));
                arrayList2.add(bool);
            }
        }
        return true;
    }

    void U() {
        S(2);
    }

    void V0(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar) {
        HashSet<androidx.core.os.b> hashSet = this.m.get(fragment);
        if (hashSet != null && hashSet.remove(bVar) && hashSet.isEmpty()) {
            this.m.remove(fragment);
            if (fragment.f612c < 5) {
                w(fragment);
                M0(fragment);
            }
        }
    }

    public void W(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f623c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                androidx.fragment.app.a aVar = this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.o(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.f621a) {
            int size3 = this.f621a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    n nVar = this.f621a.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(nVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.s);
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.q);
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

    void W0(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.t);
        }
        boolean z = !fragment.Q();
        if (!fragment.C || z) {
            this.f623c.s(fragment);
            if (E0(fragment)) {
                this.D = true;
            }
            fragment.o = true;
            g1(fragment);
        }
    }

    void Y(@NonNull n nVar, boolean z) {
        if (!z) {
            if (this.r == null) {
                if (!this.G) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            o();
        }
        synchronized (this.f621a) {
            if (this.r == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.f621a.add(nVar);
                c1();
            }
        }
    }

    void Z0(@Nullable Parcelable parcelable) {
        androidx.fragment.app.p pVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f643c == null) {
            return;
        }
        this.f623c.t();
        for (FragmentState fragmentState : fragmentManagerState.f643c) {
            if (fragmentState != null) {
                Fragment fragmentG = this.M.g(fragmentState.d);
                if (fragmentG != null) {
                    if (D0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragmentG);
                    }
                    pVar = new androidx.fragment.app.p(this.o, this.f623c, fragmentG, fragmentState);
                } else {
                    pVar = new androidx.fragment.app.p(this.o, this.f623c, this.r.k().getClassLoader(), p0(), fragmentState);
                }
                Fragment fragmentJ = pVar.j();
                fragmentJ.u = this;
                if (D0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentJ.h + "): " + fragmentJ);
                }
                pVar.m(this.r.k().getClassLoader());
                this.f623c.p(pVar);
                pVar.r(this.q);
            }
        }
        for (Fragment fragment : this.M.j()) {
            if (!this.f623c.c(fragment.h)) {
                if (D0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f643c);
                }
                this.M.m(fragment);
                fragment.u = this;
                androidx.fragment.app.p pVar2 = new androidx.fragment.app.p(this.o, this.f623c, fragment);
                pVar2.r(1);
                pVar2.k();
                fragment.o = true;
                pVar2.k();
            }
        }
        this.f623c.u(fragmentManagerState.d);
        if (fragmentManagerState.e != null) {
            this.d = new ArrayList<>(fragmentManagerState.e.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.e;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a aVarA = backStackStateArr[i2].a(this);
                if (D0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVarA.s + "): " + aVarA);
                    PrintWriter printWriter = new PrintWriter(new w("FragmentManager"));
                    aVarA.p("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(aVarA);
                i2++;
            }
        } else {
            this.d = null;
        }
        this.i.set(fragmentManagerState.f);
        String str = fragmentManagerState.g;
        if (str != null) {
            Fragment fragmentF0 = f0(str);
            this.u = fragmentF0;
            L(fragmentF0);
        }
        ArrayList<String> arrayList = fragmentManagerState.h;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                this.j.put(arrayList.get(i3), fragmentManagerState.i.get(i3));
            }
        }
        this.C = new ArrayDeque<>(fragmentManagerState.j);
    }

    boolean a0(boolean z) {
        Z(z);
        boolean z2 = false;
        while (k0(this.I, this.J)) {
            this.f622b = true;
            try {
                X0(this.I, this.J);
                p();
                z2 = true;
            } catch (Throwable th) {
                p();
                throw th;
            }
        }
        j1();
        V();
        this.f623c.b();
        return z2;
    }

    void b0(@NonNull n nVar, boolean z) {
        if (z && (this.r == null || this.G)) {
            return;
        }
        Z(z);
        if (nVar.a(this.I, this.J)) {
            this.f622b = true;
            try {
                X0(this.I, this.J);
            } finally {
                p();
            }
        }
        j1();
        V();
        this.f623c.b();
    }

    Parcelable b1() {
        int size;
        j0();
        X();
        a0(true);
        this.E = true;
        this.M.n(true);
        ArrayList<FragmentState> arrayListV = this.f623c.v();
        BackStackState[] backStackStateArr = null;
        if (arrayListV.isEmpty()) {
            if (D0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> arrayListW = this.f623c.w();
        ArrayList<androidx.fragment.app.a> arrayList = this.d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.d.get(i2));
                if (D0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.d.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f643c = arrayListV;
        fragmentManagerState.d = arrayListW;
        fragmentManagerState.e = backStackStateArr;
        fragmentManagerState.f = this.i.get();
        Fragment fragment = this.u;
        if (fragment != null) {
            fragmentManagerState.g = fragment.h;
        }
        fragmentManagerState.h.addAll(this.j.keySet());
        fragmentManagerState.i.addAll(this.j.values());
        fragmentManagerState.j = new ArrayList<>(this.C);
        return fragmentManagerState;
    }

    void c1() {
        synchronized (this.f621a) {
            boolean z = (this.L == null || this.L.isEmpty()) ? false : true;
            boolean z2 = this.f621a.size() == 1;
            if (z || z2) {
                this.r.l().removeCallbacks(this.N);
                this.r.l().post(this.N);
                j1();
            }
        }
    }

    void d1(@NonNull Fragment fragment, boolean z) {
        ViewGroup viewGroupO0 = o0(fragment);
        if (viewGroupO0 == null || !(viewGroupO0 instanceof androidx.fragment.app.f)) {
            return;
        }
        ((androidx.fragment.app.f) viewGroupO0).b(!z);
    }

    void e(androidx.fragment.app.a aVar) {
        if (this.d == null) {
            this.d = new ArrayList<>();
        }
        this.d.add(aVar);
    }

    void e1(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.equals(f0(fragment.h)) && (fragment.v == null || fragment.u == this)) {
            fragment.S = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void f(@NonNull Fragment fragment, @NonNull androidx.core.os.b bVar) {
        if (this.m.get(fragment) == null) {
            this.m.put(fragment, new HashSet<>());
        }
        this.m.get(fragment).add(bVar);
    }

    @Nullable
    Fragment f0(@NonNull String str) {
        return this.f623c.f(str);
    }

    void f1(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(f0(fragment.h)) && (fragment.v == null || fragment.u == this))) {
            Fragment fragment2 = this.u;
            this.u = fragment;
            L(fragment2);
            L(this.u);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void g(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        this.f623c.p(v(fragment));
        if (fragment.C) {
            return;
        }
        this.f623c.a(fragment);
        fragment.o = false;
        if (fragment.J == null) {
            fragment.O = false;
        }
        if (E0(fragment)) {
            this.D = true;
        }
    }

    @Nullable
    public Fragment g0(@IdRes int i2) {
        return this.f623c.g(i2);
    }

    public void h(@NonNull androidx.fragment.app.n nVar) {
        this.p.add(nVar);
    }

    @Nullable
    public Fragment h0(@Nullable String str) {
        return this.f623c.h(str);
    }

    void h1(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.B) {
            fragment.B = false;
            fragment.O = !fragment.O;
        }
    }

    int i() {
        return this.i.getAndIncrement();
    }

    Fragment i0(@NonNull String str) {
        return this.f623c.i(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @SuppressLint({"SyntheticAccessor"})
    void j(@NonNull androidx.fragment.app.i<?> iVar, @NonNull androidx.fragment.app.e eVar, @Nullable Fragment fragment) {
        String str;
        if (this.r != null) {
            throw new IllegalStateException("Already attached");
        }
        this.r = iVar;
        this.s = eVar;
        this.t = fragment;
        if (fragment != null) {
            h(new i(this, fragment));
        } else if (iVar instanceof androidx.fragment.app.n) {
            h((androidx.fragment.app.n) iVar);
        }
        if (this.t != null) {
            j1();
        }
        if (iVar instanceof androidx.activity.c) {
            androidx.activity.c cVar = (androidx.activity.c) iVar;
            this.g = cVar.b();
            androidx.lifecycle.f fVar = cVar;
            if (fragment != null) {
                fVar = fragment;
            }
            this.g.a(fVar, this.h);
        }
        if (fragment != null) {
            this.M = fragment.u.m0(fragment);
        } else if (iVar instanceof androidx.lifecycle.q) {
            this.M = androidx.fragment.app.m.i(((androidx.lifecycle.q) iVar).h());
        } else {
            this.M = new androidx.fragment.app.m(false);
        }
        this.M.n(I0());
        this.f623c.x(this.M);
        Object obj = this.r;
        if (obj instanceof androidx.activity.result.c) {
            ActivityResultRegistry activityResultRegistryD = ((androidx.activity.result.c) obj).d();
            if (fragment != null) {
                str = fragment.h + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.z = activityResultRegistryD.f(str2 + "StartActivityForResult", new androidx.activity.result.d.c(), new j());
            this.A = activityResultRegistryD.f(str2 + "StartIntentSenderForResult", new k(), new a());
            this.B = activityResultRegistryD.f(str2 + "RequestPermissions", new androidx.activity.result.d.b(), new b());
        }
    }

    void k(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.C) {
            fragment.C = false;
            if (fragment.n) {
                return;
            }
            this.f623c.a(fragment);
            if (D0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (E0(fragment)) {
                this.D = true;
            }
        }
    }

    @NonNull
    public r l() {
        return new androidx.fragment.app.a(this);
    }

    public int l0() {
        ArrayList<androidx.fragment.app.a> arrayList = this.d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    boolean n() {
        boolean zE0 = false;
        for (Fragment fragment : this.f623c.l()) {
            if (fragment != null) {
                zE0 = E0(fragment);
            }
            if (zE0) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    androidx.fragment.app.e n0() {
        return this.s;
    }

    @NonNull
    public androidx.fragment.app.h p0() {
        androidx.fragment.app.h hVar = this.v;
        if (hVar != null) {
            return hVar;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.u.p0() : this.w;
    }

    public final void q(@NonNull String str) {
        this.j.remove(str);
    }

    @NonNull
    q q0() {
        return this.f623c;
    }

    @NonNull
    public List<Fragment> r0() {
        return this.f623c.n();
    }

    @NonNull
    androidx.fragment.app.i<?> s0() {
        return this.r;
    }

    void t(@NonNull androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.r(z3);
        } else {
            aVar.q();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.q >= 1) {
            s.C(this.r.k(), this.s, arrayList, arrayList2, 0, 1, true, this.n);
        }
        if (z3) {
            L0(this.q, true);
        }
        for (Fragment fragment : this.f623c.l()) {
            if (fragment != null && fragment.J != null && fragment.N && aVar.u(fragment.z)) {
                float f2 = fragment.P;
                if (f2 > 0.0f) {
                    fragment.J.setAlpha(f2);
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

    @NonNull
    LayoutInflater.Factory2 t0() {
        return this.f;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.t;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.t)));
            sb.append("}");
        } else {
            androidx.fragment.app.i<?> iVar = this.r;
            if (iVar != null) {
                sb.append(iVar.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.r)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    @NonNull
    androidx.fragment.app.k u0() {
        return this.o;
    }

    @NonNull
    androidx.fragment.app.p v(@NonNull Fragment fragment) {
        androidx.fragment.app.p pVarM = this.f623c.m(fragment.h);
        if (pVarM != null) {
            return pVarM;
        }
        androidx.fragment.app.p pVar = new androidx.fragment.app.p(this.o, this.f623c, fragment);
        pVar.m(this.r.k().getClassLoader());
        pVar.r(this.q);
        return pVar;
    }

    @Nullable
    Fragment v0() {
        return this.t;
    }

    @Nullable
    public Fragment w0() {
        return this.u;
    }

    void x(@NonNull Fragment fragment) {
        if (D0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.C) {
            return;
        }
        fragment.C = true;
        if (fragment.n) {
            if (D0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f623c.s(fragment);
            if (E0(fragment)) {
                this.D = true;
            }
            g1(fragment);
        }
    }

    @NonNull
    x x0() {
        x xVar = this.x;
        if (xVar != null) {
            return xVar;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.u.x0() : this.y;
    }

    void y() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        S(4);
    }

    void z() {
        this.E = false;
        this.F = false;
        this.M.n(false);
        S(0);
    }

    @NonNull
    androidx.lifecycle.p z0(@NonNull Fragment fragment) {
        return this.M.k(fragment);
    }
}
