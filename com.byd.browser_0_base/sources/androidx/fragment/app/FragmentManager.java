package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.a;
import androidx.fragment.R$id;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.i;
import androidx.lifecycle.d;
import defpackage.C0175b;
import defpackage.b71;
import defpackage.bn3;
import defpackage.cp0;
import defpackage.cs1;
import defpackage.dp0;
import defpackage.ep0;
import defpackage.es1;
import defpackage.fs1;
import defpackage.ge2;
import defpackage.gf1;
import defpackage.gq2;
import defpackage.ha1;
import defpackage.hp0;
import defpackage.i21;
import defpackage.ip0;
import defpackage.j91;
import defpackage.jp0;
import defpackage.kf1;
import defpackage.kp0;
import defpackage.la0;
import defpackage.lp0;
import defpackage.ls1;
import defpackage.mp0;
import defpackage.np0;
import defpackage.nq2;
import defpackage.qq2;
import defpackage.s2;
import defpackage.t2;
import defpackage.tp0;
import defpackage.u2;
import defpackage.v2;
import defpackage.vd1;
import defpackage.w2;
import defpackage.wx;
import defpackage.x2;
import defpackage.xr1;
import defpackage.yl;
import defpackage.zr1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentManager {
    public w2 A;
    public w2 B;
    public w2 C;
    public ArrayDeque<LaunchedFragmentInfo> D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ArrayList<androidx.fragment.app.a> f13J;
    public ArrayList<Boolean> K;
    public ArrayList<Fragment> L;
    public mp0 M;
    public final f N;
    public boolean b;
    public ArrayList<androidx.fragment.app.a> d;
    public ArrayList<Fragment> e;
    public OnBackPressedDispatcher g;
    public ArrayList<l> l;
    public final androidx.fragment.app.g m;
    public final CopyOnWriteArrayList<np0> n;
    public final jp0 o;
    public final dp0 p;
    public final ep0 q;
    public final kp0 r;
    public final c s;
    public int t;
    public hp0<?> u;
    public vd1 v;
    public Fragment w;
    public Fragment x;
    public final d y;
    public final e z;
    public final ArrayList<m> a = new ArrayList<>();
    public final la0 c = new la0(1);
    public final ip0 f = new ip0(this);
    public final b h = new b();
    public final AtomicInteger i = new AtomicInteger();
    public final Map<String, BackStackState> j = Collections.synchronizedMap(new HashMap());
    public final Map<String, Bundle> k = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentManager$6, reason: invalid class name */
    class AnonymousClass6 implements androidx.lifecycle.f {
        @Override // androidx.lifecycle.f
        public final void a(b71 b71Var, d.a aVar) {
            if (aVar == d.a.ON_START || aVar == d.a.ON_DESTROY) {
                throw null;
            }
        }
    }

    public class a implements s2<Map<String, Boolean>> {
        public a() {
        }

        @Override // defpackage.s2
        @SuppressLint({"SyntheticAccessor"})
        public final void a(Map<String, Boolean> map) {
            Map<String, Boolean> map2 = map;
            ArrayList arrayList = new ArrayList(map2.values());
            int[] iArr = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
            }
            FragmentManager fragmentManager = FragmentManager.this;
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = fragmentManager.D.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No permissions were requested for " + this);
                return;
            }
            la0 la0Var = fragmentManager.c;
            String str = launchedFragmentInfoPollFirst.e;
            if (la0Var.g(str) == null) {
                Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
            }
        }
    }

    public class b extends xr1 {
        public b() {
            super(false);
        }

        @Override // defpackage.xr1
        public final void a() {
            FragmentManager fragmentManager = FragmentManager.this;
            fragmentManager.w(true);
            if (fragmentManager.h.a) {
                fragmentManager.O();
            } else {
                fragmentManager.g.c();
            }
        }
    }

    public class c implements kf1 {
        public c() {
        }

        @Override // defpackage.kf1
        public final boolean a(MenuItem menuItem) {
            return FragmentManager.this.m(menuItem);
        }

        @Override // defpackage.kf1
        public final void b(Menu menu) {
            FragmentManager.this.n();
        }

        @Override // defpackage.kf1
        public final void c(Menu menu, MenuInflater menuInflater) {
            FragmentManager.this.h(menu, menuInflater);
        }

        @Override // defpackage.kf1
        public final void d(Menu menu) {
            FragmentManager.this.q();
        }
    }

    public class d extends androidx.fragment.app.f {
        public d() {
        }

        @Override // androidx.fragment.app.f
        public final Fragment a(String str) {
            return Fragment.U(FragmentManager.this.u.l, str, null);
        }
    }

    public class e implements qq2 {
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            FragmentManager.this.w(true);
        }
    }

    public class g implements np0 {
        public final /* synthetic */ Fragment e;

        public g(Fragment fragment) {
            this.e = fragment;
        }

        @Override // defpackage.np0
        public final void a(Fragment fragment) {
            this.e.b0(fragment);
        }
    }

    public class h implements s2<ActivityResult> {
        public h() {
        }

        @Override // defpackage.s2
        public final void a(ActivityResult activityResult) {
            ActivityResult activityResult2 = activityResult;
            FragmentManager fragmentManager = FragmentManager.this;
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = fragmentManager.D.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            la0 la0Var = fragmentManager.c;
            String str = launchedFragmentInfoPollFirst.e;
            Fragment fragmentG = la0Var.g(str);
            if (fragmentG != null) {
                fragmentG.Z(launchedFragmentInfoPollFirst.f, activityResult2.e, activityResult2.f);
            } else {
                Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
            }
        }
    }

    public class i implements s2<ActivityResult> {
        public i() {
        }

        @Override // defpackage.s2
        public final void a(ActivityResult activityResult) {
            ActivityResult activityResult2 = activityResult;
            FragmentManager fragmentManager = FragmentManager.this;
            LaunchedFragmentInfo launchedFragmentInfoPollFirst = fragmentManager.D.pollFirst();
            if (launchedFragmentInfoPollFirst == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            la0 la0Var = fragmentManager.c;
            String str = launchedFragmentInfoPollFirst.e;
            Fragment fragmentG = la0Var.g(str);
            if (fragmentG != null) {
                fragmentG.Z(launchedFragmentInfoPollFirst.f, activityResult2.e, activityResult2.f);
            } else {
                Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
            }
        }
    }

    public static class j extends t2<IntentSenderRequest, ActivityResult> {
        @Override // defpackage.t2
        public final Intent a(ComponentActivity componentActivity, Object obj) {
            Bundle bundleExtra;
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) obj;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intent2 = intentSenderRequest.f;
            if (intent2 != null && (bundleExtra = intent2.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intent2.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intent2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    IntentSender intentSender = intentSenderRequest.e;
                    i21.e(intentSender, "intentSender");
                    intentSenderRequest = new IntentSenderRequest(intentSender, null, intentSenderRequest.g, intentSenderRequest.h);
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            return intent;
        }

        @Override // defpackage.t2
        public final Object c(Intent intent, int i) {
            return new ActivityResult(intent, i);
        }
    }

    public static abstract class k {
    }

    public interface l {
        void onBackStackChanged();
    }

    public interface m {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    public class n implements m {
        public final int a;
        public final int b;

        public n(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // androidx.fragment.app.FragmentManager.m
        public final boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            FragmentManager fragmentManager = FragmentManager.this;
            Fragment fragment = fragmentManager.x;
            int i = this.a;
            if (fragment == null || i >= 0 || !fragment.J().O()) {
                return fragmentManager.Q(arrayList, arrayList2, i, this.b);
            }
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [jp0] */
    /* JADX WARN: Type inference failed for: r0v15, types: [kp0] */
    public FragmentManager() {
        Collections.synchronizedMap(new HashMap());
        this.m = new androidx.fragment.app.g(this);
        this.n = new CopyOnWriteArrayList<>();
        this.o = new wx() { // from class: jp0
            @Override // defpackage.wx
            public final void accept(Object obj) {
                Configuration configuration = (Configuration) obj;
                FragmentManager fragmentManager = this.a;
                if (fragmentManager.I()) {
                    fragmentManager.f(false, configuration);
                }
            }
        };
        this.p = new dp0(1, this);
        this.q = new ep0(1, this);
        this.r = new wx() { // from class: kp0
            @Override // defpackage.wx
            public final void accept(Object obj) {
                k12 k12Var = (k12) obj;
                FragmentManager fragmentManager = this.a;
                if (fragmentManager.I()) {
                    fragmentManager.p(k12Var.a, false);
                }
            }
        };
        this.s = new c();
        this.t = -1;
        this.y = new d();
        this.z = new e();
        this.D = new ArrayDeque<>();
        this.N = new f();
    }

    public static HashSet D(androidx.fragment.app.a aVar) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < aVar.a.size(); i2++) {
            Fragment fragment = aVar.a.get(i2).b;
            if (fragment != null) {
                hashSet.add(fragment);
            }
        }
        return hashSet;
    }

    public static boolean H(Fragment fragment) {
        boolean z;
        if (fragment.F && fragment.G) {
            return true;
        }
        Iterator it = fragment.x.c.i().iterator();
        boolean zH = false;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Fragment fragment2 = (Fragment) it.next();
            if (fragment2 != null) {
                zH = H(fragment2);
            }
            if (zH) {
                z = true;
                break;
            }
        }
        return z;
    }

    public static boolean J(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.G && (fragment.v == null || J(fragment.y));
    }

    public static boolean K(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.v;
        return fragment.equals(fragmentManager.x) && K(fragmentManager.w);
    }

    public final Fragment A(int i2) {
        la0 la0Var = this.c;
        ArrayList arrayList = (ArrayList) la0Var.e;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                for (androidx.fragment.app.h hVar : ((HashMap) la0Var.f).values()) {
                    if (hVar != null) {
                        Fragment fragment = hVar.c;
                        if (fragment.z == i2) {
                            return fragment;
                        }
                    }
                }
                return null;
            }
            Fragment fragment2 = (Fragment) arrayList.get(size);
            if (fragment2 != null && fragment2.z == i2) {
                return fragment2;
            }
        }
    }

    public final Fragment B(String str) {
        la0 la0Var = this.c;
        ArrayList arrayList = (ArrayList) la0Var.e;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                for (androidx.fragment.app.h hVar : ((HashMap) la0Var.f).values()) {
                    if (hVar != null) {
                        Fragment fragment = hVar.c;
                        if (str.equals(fragment.B)) {
                            return fragment;
                        }
                    }
                }
                return null;
            }
            Fragment fragment2 = (Fragment) arrayList.get(size);
            if (fragment2 != null && str.equals(fragment2.B)) {
                return fragment2;
            }
        }
    }

    public final void C() {
        for (androidx.fragment.app.j jVar : d()) {
            if (jVar.e) {
                jVar.e = false;
                jVar.c();
            }
        }
    }

    public final ViewGroup E(Fragment fragment) {
        ViewGroup viewGroup = fragment.I;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.A > 0 && this.v.x()) {
            View viewR = this.v.r(fragment.A);
            if (viewR instanceof ViewGroup) {
                return (ViewGroup) viewR;
            }
        }
        return null;
    }

    public final androidx.fragment.app.f F() {
        Fragment fragment = this.w;
        return fragment != null ? fragment.v.F() : this.y;
    }

    public final qq2 G() {
        Fragment fragment = this.w;
        return fragment != null ? fragment.v.G() : this.z;
    }

    public final boolean I() {
        Fragment fragment = this.w;
        if (fragment == null) {
            return true;
        }
        return fragment.V() && this.w.M().I();
    }

    public final boolean L() {
        return this.F || this.G;
    }

    public final void M(int i2, boolean z) {
        Object obj;
        hp0<?> hp0Var;
        if (this.u == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.t) {
            this.t = i2;
            la0 la0Var = this.c;
            Iterator it = ((ArrayList) la0Var.e).iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                obj = la0Var.f;
                if (!zHasNext) {
                    break;
                }
                androidx.fragment.app.h hVar = (androidx.fragment.app.h) ((HashMap) obj).get(((Fragment) it.next()).i);
                if (hVar != null) {
                    hVar.k();
                }
            }
            Iterator it2 = ((HashMap) obj).values().iterator();
            while (true) {
                boolean z2 = false;
                if (!it2.hasNext()) {
                    break;
                }
                androidx.fragment.app.h hVar2 = (androidx.fragment.app.h) it2.next();
                if (hVar2 != null) {
                    hVar2.k();
                    Fragment fragment = hVar2.c;
                    if (fragment.p && !fragment.X()) {
                        z2 = true;
                    }
                    if (z2) {
                        la0Var.l(hVar2);
                    }
                }
            }
            a0();
            if (this.E && (hp0Var = this.u) != null && this.t == 7) {
                hp0Var.M();
                this.E = false;
            }
        }
    }

    public final void N() {
        if (this.u == null) {
            return;
        }
        this.F = false;
        this.G = false;
        this.M.h = false;
        for (Fragment fragment : this.c.j()) {
            if (fragment != null) {
                fragment.x.N();
            }
        }
    }

    public final boolean O() {
        return P(-1, 0);
    }

    public final boolean P(int i2, int i3) {
        w(false);
        v(true);
        Fragment fragment = this.x;
        if (fragment != null && i2 < 0 && fragment.J().O()) {
            return true;
        }
        boolean zQ = Q(this.f13J, this.K, i2, i3);
        if (zQ) {
            this.b = true;
            try {
                S(this.f13J, this.K);
            } finally {
                c();
            }
        }
        c0();
        s();
        this.c.c();
        return zQ;
    }

    public final boolean Q(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        boolean z = (i3 & 1) != 0;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.d;
        int size = -1;
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            if (i2 < 0) {
                size = z ? 0 : (-1) + this.d.size();
            } else {
                int size2 = this.d.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.d.get(size2);
                    if (i2 >= 0 && i2 == aVar.t) {
                        break;
                    }
                    size2--;
                }
                if (size2 >= 0) {
                    if (z) {
                        while (size2 > 0) {
                            int i4 = size2 - 1;
                            androidx.fragment.app.a aVar2 = this.d.get(i4);
                            if (i2 < 0 || i2 != aVar2.t) {
                                break;
                            }
                            size2 = i4;
                        }
                    } else if (size2 != this.d.size() - 1) {
                        size2++;
                    }
                    size = size2;
                } else {
                    size = size2;
                }
            }
        }
        if (size < 0) {
            return false;
        }
        for (int size3 = this.d.size() - 1; size3 >= size; size3--) {
            arrayList.add(this.d.remove(size3));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void R(Fragment fragment) {
        boolean z = !fragment.X();
        if (!fragment.D || z) {
            la0 la0Var = this.c;
            synchronized (((ArrayList) la0Var.e)) {
                ((ArrayList) la0Var.e).remove(fragment);
            }
            fragment.o = false;
            if (H(fragment)) {
                this.E = true;
            }
            fragment.p = true;
            Z(fragment);
        }
    }

    public final void S(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).p) {
                if (i3 != i2) {
                    y(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).p) {
                        i3++;
                    }
                }
                y(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            y(arrayList, arrayList2, i3, size);
        }
    }

    public final void T(Bundle bundle) {
        androidx.fragment.app.g gVar;
        int i2;
        Bundle bundle2;
        Bundle bundle3;
        for (String str : bundle.keySet()) {
            if (str.startsWith("result_") && (bundle3 = bundle.getBundle(str)) != null) {
                bundle3.setClassLoader(this.u.l.getClassLoader());
                this.k.put(str.substring(7), bundle3);
            }
        }
        HashMap map = new HashMap();
        for (String str2 : bundle.keySet()) {
            if (str2.startsWith("fragment_") && (bundle2 = bundle.getBundle(str2)) != null) {
                bundle2.setClassLoader(this.u.l.getClassLoader());
                map.put(str2.substring(9), bundle2);
            }
        }
        la0 la0Var = this.c;
        HashMap map2 = (HashMap) la0Var.g;
        map2.clear();
        map2.putAll(map);
        FragmentManagerState fragmentManagerState = (FragmentManagerState) bundle.getParcelable("state");
        if (fragmentManagerState == null) {
            return;
        }
        Object obj = la0Var.f;
        ((HashMap) obj).clear();
        Iterator<String> it = fragmentManagerState.e.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            gVar = this.m;
            if (!zHasNext) {
                break;
            }
            Bundle bundleM = la0Var.m(null, it.next());
            if (bundleM != null) {
                Fragment fragment = this.M.c.get(((FragmentState) bundleM.getParcelable("state")).f);
                androidx.fragment.app.h hVar = fragment != null ? new androidx.fragment.app.h(gVar, la0Var, fragment, bundleM) : new androidx.fragment.app.h(this.m, this.c, this.u.l.getClassLoader(), F(), bundleM);
                Fragment fragment2 = hVar.c;
                fragment2.f = bundleM;
                fragment2.v = this;
                hVar.m(this.u.l.getClassLoader());
                la0Var.k(hVar);
                hVar.e = this.t;
            }
        }
        mp0 mp0Var = this.M;
        mp0Var.getClass();
        Iterator it2 = new ArrayList(mp0Var.c.values()).iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Fragment fragment3 = (Fragment) it2.next();
            if ((((HashMap) obj).get(fragment3.i) != null ? 1 : 0) == 0) {
                mp0 mp0Var2 = this.M;
                if (!mp0Var2.h) {
                    mp0Var2.c.remove(fragment3.i);
                }
                fragment3.v = this;
                androidx.fragment.app.h hVar2 = new androidx.fragment.app.h(gVar, la0Var, fragment3);
                hVar2.e = 1;
                hVar2.k();
                fragment3.p = true;
                hVar2.k();
            }
        }
        ArrayList<String> arrayList = fragmentManagerState.f;
        ((ArrayList) la0Var.e).clear();
        if (arrayList != null) {
            for (String str3 : arrayList) {
                Fragment fragmentF = la0Var.f(str3);
                if (fragmentF == null) {
                    throw new IllegalStateException(gq2.g("No instantiated fragment for (", str3, ")"));
                }
                la0Var.b(fragmentF);
            }
        }
        if (fragmentManagerState.g != null) {
            this.d = new ArrayList<>(fragmentManagerState.g.length);
            int i3 = 0;
            while (true) {
                BackStackRecordState[] backStackRecordStateArr = fragmentManagerState.g;
                if (i3 >= backStackRecordStateArr.length) {
                    break;
                }
                BackStackRecordState backStackRecordState = backStackRecordStateArr[i3];
                backStackRecordState.getClass();
                androidx.fragment.app.a aVar = new androidx.fragment.app.a(this);
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    int[] iArr = backStackRecordState.e;
                    if (i4 >= iArr.length) {
                        break;
                    }
                    i.a aVar2 = new i.a();
                    int i6 = i4 + 1;
                    aVar2.a = iArr[i4];
                    aVar2.h = d.b.values()[backStackRecordState.g[i5]];
                    aVar2.i = d.b.values()[backStackRecordState.h[i5]];
                    int i7 = i6 + 1;
                    aVar2.c = iArr[i6] != 0;
                    int i8 = i7 + 1;
                    int i9 = iArr[i7];
                    aVar2.d = i9;
                    int i10 = i8 + 1;
                    int i11 = iArr[i8];
                    aVar2.e = i11;
                    int i12 = i10 + 1;
                    int i13 = iArr[i10];
                    aVar2.f = i13;
                    int i14 = iArr[i12];
                    aVar2.g = i14;
                    aVar.b = i9;
                    aVar.c = i11;
                    aVar.d = i13;
                    aVar.e = i14;
                    aVar.b(aVar2);
                    i5++;
                    i4 = i12 + 1;
                }
                aVar.f = backStackRecordState.i;
                aVar.i = backStackRecordState.j;
                aVar.g = true;
                aVar.j = backStackRecordState.l;
                aVar.k = backStackRecordState.m;
                aVar.l = backStackRecordState.n;
                aVar.m = backStackRecordState.o;
                aVar.n = backStackRecordState.p;
                aVar.o = backStackRecordState.q;
                aVar.p = backStackRecordState.r;
                aVar.t = backStackRecordState.k;
                int i15 = 0;
                while (true) {
                    ArrayList<String> arrayList2 = backStackRecordState.f;
                    if (i15 < arrayList2.size()) {
                        String str4 = arrayList2.get(i15);
                        if (str4 != null) {
                            aVar.a.get(i15).b = z(str4);
                        }
                        i15++;
                    }
                }
                aVar.f(1);
                this.d.add(aVar);
                i3++;
            }
        } else {
            this.d = null;
        }
        this.i.set(fragmentManagerState.h);
        String str5 = fragmentManagerState.i;
        if (str5 != null) {
            Fragment fragmentZ = z(str5);
            this.x = fragmentZ;
            o(fragmentZ);
        }
        ArrayList<String> arrayList3 = fragmentManagerState.j;
        if (arrayList3 != null) {
            while (i2 < arrayList3.size()) {
                this.j.put(arrayList3.get(i2), fragmentManagerState.k.get(i2));
                i2++;
            }
        }
        this.D = new ArrayDeque<>(fragmentManagerState.l);
    }

    public final Bundle U() {
        BackStackRecordState[] backStackRecordStateArr;
        ArrayList<String> arrayList;
        int size;
        Bundle bundle = new Bundle();
        C();
        Iterator it = d().iterator();
        while (it.hasNext()) {
            ((androidx.fragment.app.j) it.next()).e();
        }
        w(true);
        this.F = true;
        this.M.h = true;
        la0 la0Var = this.c;
        la0Var.getClass();
        HashMap map = (HashMap) la0Var.f;
        ArrayList<String> arrayList2 = new ArrayList<>(map.size());
        for (androidx.fragment.app.h hVar : map.values()) {
            if (hVar != null) {
                Fragment fragment = hVar.c;
                la0Var.m(hVar.o(), fragment.i);
                arrayList2.add(fragment.i);
            }
        }
        HashMap map2 = (HashMap) this.c.g;
        if (!map2.isEmpty()) {
            la0 la0Var2 = this.c;
            synchronized (((ArrayList) la0Var2.e)) {
                backStackRecordStateArr = null;
                if (((ArrayList) la0Var2.e).isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList<>(((ArrayList) la0Var2.e).size());
                    Iterator it2 = ((ArrayList) la0Var2.e).iterator();
                    while (it2.hasNext()) {
                        arrayList.add(((Fragment) it2.next()).i);
                    }
                }
            }
            ArrayList<androidx.fragment.app.a> arrayList3 = this.d;
            if (arrayList3 != null && (size = arrayList3.size()) > 0) {
                backStackRecordStateArr = new BackStackRecordState[size];
                for (int i2 = 0; i2 < size; i2++) {
                    backStackRecordStateArr[i2] = new BackStackRecordState(this.d.get(i2));
                }
            }
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.e = arrayList2;
            fragmentManagerState.f = arrayList;
            fragmentManagerState.g = backStackRecordStateArr;
            fragmentManagerState.h = this.i.get();
            Fragment fragment2 = this.x;
            if (fragment2 != null) {
                fragmentManagerState.i = fragment2.i;
            }
            fragmentManagerState.j.addAll(this.j.keySet());
            fragmentManagerState.k.addAll(this.j.values());
            fragmentManagerState.l = new ArrayList<>(this.D);
            bundle.putParcelable("state", fragmentManagerState);
            for (String str : this.k.keySet()) {
                bundle.putBundle(C0175b.j("result_", str), this.k.get(str));
            }
            for (String str2 : map2.keySet()) {
                bundle.putBundle(C0175b.j("fragment_", str2), (Bundle) map2.get(str2));
            }
        }
        return bundle;
    }

    public final void V() {
        synchronized (this.a) {
            boolean z = true;
            if (this.a.size() != 1) {
                z = false;
            }
            if (z) {
                this.u.m.removeCallbacks(this.N);
                this.u.m.post(this.N);
                c0();
            }
        }
    }

    public final void W(Fragment fragment, boolean z) {
        ViewGroup viewGroupE = E(fragment);
        if (viewGroupE == null || !(viewGroupE instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) viewGroupE).setDrawDisappearingViewsLast(!z);
    }

    public final void X(Fragment fragment, d.b bVar) {
        if (fragment.equals(z(fragment.i)) && (fragment.w == null || fragment.v == this)) {
            fragment.R = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void Y(Fragment fragment) {
        if (fragment == null || (fragment.equals(z(fragment.i)) && (fragment.w == null || fragment.v == this))) {
            Fragment fragment2 = this.x;
            this.x = fragment;
            o(fragment2);
            o(this.x);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void Z(Fragment fragment) {
        ViewGroup viewGroupE = E(fragment);
        if (viewGroupE != null) {
            Fragment.c cVar = fragment.M;
            if ((cVar == null ? 0 : cVar.e) + (cVar == null ? 0 : cVar.d) + (cVar == null ? 0 : cVar.c) + (cVar == null ? 0 : cVar.b) > 0) {
                int i2 = R$id.visible_removing_fragment_view_tag;
                if (viewGroupE.getTag(i2) == null) {
                    viewGroupE.setTag(i2, fragment);
                }
                Fragment fragment2 = (Fragment) viewGroupE.getTag(i2);
                Fragment.c cVar2 = fragment.M;
                boolean z = cVar2 != null ? cVar2.a : false;
                if (fragment2.M == null) {
                    return;
                }
                fragment2.H().a = z;
            }
        }
    }

    public final androidx.fragment.app.h a(Fragment fragment) {
        String str = fragment.Q;
        if (str != null) {
            tp0.c(fragment, str);
        }
        androidx.fragment.app.h hVarE = e(fragment);
        fragment.v = this;
        la0 la0Var = this.c;
        la0Var.k(hVarE);
        if (!fragment.D) {
            la0Var.b(fragment);
            fragment.p = false;
            if (fragment.f12J == null) {
                fragment.N = false;
            }
            if (H(fragment)) {
                this.E = true;
            }
        }
        return hVarE;
    }

    public final void a0() {
        for (androidx.fragment.app.h hVar : this.c.h()) {
            Fragment fragment = hVar.c;
            if (fragment.K) {
                if (this.b) {
                    this.I = true;
                } else {
                    fragment.K = false;
                    hVar.k();
                }
            }
        }
    }

    public void addFragmentOnAttachListener(np0 np0Var) {
        this.n.add(np0Var);
    }

    public void addOnBackStackChangedListener(l lVar) {
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        this.l.add(lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    public final void b(hp0<?> hp0Var, vd1 vd1Var, Fragment fragment) {
        if (this.u != null) {
            throw new IllegalStateException("Already attached");
        }
        this.u = hp0Var;
        this.v = vd1Var;
        this.w = fragment;
        if (fragment != null) {
            addFragmentOnAttachListener(new g(fragment));
        } else if (hp0Var instanceof np0) {
            addFragmentOnAttachListener((np0) hp0Var);
        }
        if (this.w != null) {
            c0();
        }
        if (hp0Var instanceof zr1) {
            zr1 zr1Var = (zr1) hp0Var;
            OnBackPressedDispatcher onBackPressedDispatcherV = zr1Var.v();
            this.g = onBackPressedDispatcherV;
            b71 b71Var = zr1Var;
            if (fragment != null) {
                b71Var = fragment;
            }
            onBackPressedDispatcherV.a(b71Var, this.h);
        }
        if (fragment != null) {
            mp0 mp0Var = fragment.v.M;
            HashMap<String, mp0> map = mp0Var.d;
            mp0 mp0Var2 = map.get(fragment.i);
            if (mp0Var2 == null) {
                mp0Var2 = new mp0(mp0Var.f);
                map.put(fragment.i, mp0Var2);
            }
            this.M = mp0Var2;
        } else if (hp0Var instanceof bn3) {
            this.M = (mp0) new androidx.lifecycle.l(((bn3) hp0Var).h0(), mp0.i).a(mp0.class);
        } else {
            this.M = new mp0(false);
        }
        this.M.h = L();
        this.c.h = this.M;
        Object obj = this.u;
        if ((obj instanceof ge2) && fragment == null) {
            androidx.savedstate.a aVarS0 = ((ge2) obj).s0();
            aVarS0.b("android:support:fragments", new cp0(1, this));
            Bundle bundleA = aVarS0.a("android:support:fragments");
            if (bundleA != null) {
                T(bundleA);
            }
        }
        Object obj2 = this.u;
        if (obj2 instanceof x2) {
            androidx.activity.result.a aVarX = ((x2) obj2).X();
            String strJ = C0175b.j("FragmentManager:", fragment != null ? gq2.i(new StringBuilder(), fragment.i, ":") : "");
            this.A = aVarX.c(gq2.f(strJ, "StartActivityForResult"), new v2(), new h());
            this.B = aVarX.c(gq2.f(strJ, "StartIntentSenderForResult"), new j(), new i());
            this.C = aVarX.c(gq2.f(strJ, "RequestPermissions"), new u2(), new a());
        }
        Object obj3 = this.u;
        if (obj3 instanceof cs1) {
            ((cs1) obj3).H0(this.o);
        }
        Object obj4 = this.u;
        if (obj4 instanceof ls1) {
            ((ls1) obj4).D(this.p);
        }
        Object obj5 = this.u;
        if (obj5 instanceof es1) {
            ((es1) obj5).Y(this.q);
        }
        Object obj6 = this.u;
        if (obj6 instanceof fs1) {
            ((fs1) obj6).t(this.r);
        }
        Object obj7 = this.u;
        if ((obj7 instanceof gf1) && fragment == null) {
            ((gf1) obj7).A0(this.s);
        }
    }

    public final void b0(IllegalStateException illegalStateException) {
        Log.e("FragmentManager", illegalStateException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new ha1());
        hp0<?> hp0Var = this.u;
        if (hp0Var != null) {
            try {
                hp0Var.J(printWriter, new String[0]);
                throw illegalStateException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw illegalStateException;
            }
        }
        try {
            t("  ", null, printWriter, new String[0]);
            throw illegalStateException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw illegalStateException;
        }
    }

    public final void c() {
        this.b = false;
        this.K.clear();
        this.f13J.clear();
    }

    public final void c0() {
        synchronized (this.a) {
            if (!this.a.isEmpty()) {
                this.h.b(true);
                return;
            }
            b bVar = this.h;
            ArrayList<androidx.fragment.app.a> arrayList = this.d;
            bVar.b((arrayList != null ? arrayList.size() : 0) > 0 && K(this.w));
        }
    }

    public final HashSet d() {
        Object bVar;
        HashSet hashSet = new HashSet();
        Iterator it = this.c.h().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((androidx.fragment.app.h) it.next()).c.I;
            if (viewGroup != null) {
                i21.e(G(), "factory");
                int i2 = R$id.special_effects_controller_view_tag;
                Object tag = viewGroup.getTag(i2);
                if (tag instanceof androidx.fragment.app.j) {
                    bVar = (androidx.fragment.app.j) tag;
                } else {
                    bVar = new androidx.fragment.app.b(viewGroup);
                    viewGroup.setTag(i2, bVar);
                }
                hashSet.add(bVar);
            }
        }
        return hashSet;
    }

    public final androidx.fragment.app.h e(Fragment fragment) {
        String str = fragment.i;
        la0 la0Var = this.c;
        androidx.fragment.app.h hVar = (androidx.fragment.app.h) ((HashMap) la0Var.f).get(str);
        if (hVar != null) {
            return hVar;
        }
        androidx.fragment.app.h hVar2 = new androidx.fragment.app.h(this.m, la0Var, fragment);
        hVar2.m(this.u.l.getClassLoader());
        hVar2.e = this.t;
        return hVar2;
    }

    public final void f(boolean z, Configuration configuration) {
        if (z && (this.u instanceof cs1)) {
            b0(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
            throw null;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null) {
                fragment.onConfigurationChanged(configuration);
                if (z) {
                    fragment.x.f(true, configuration);
                }
            }
        }
    }

    public final boolean g() {
        if (this.t < 1) {
            return false;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null) {
                if (!fragment.C ? fragment.x.g() : false) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean h(Menu menu, MenuInflater menuInflater) {
        boolean zH;
        boolean z;
        if (this.t < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z2 = false;
        for (Fragment fragment : this.c.j()) {
            if (fragment != null && J(fragment)) {
                if (fragment.C) {
                    zH = false;
                } else {
                    if (fragment.F && fragment.G) {
                        fragment.d0(menu, menuInflater);
                        z = true;
                    } else {
                        z = false;
                    }
                    zH = z | fragment.x.h(menu, menuInflater);
                }
                if (zH) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(fragment);
                    z2 = true;
                }
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment2 = this.e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.getClass();
                }
            }
        }
        this.e = arrayList;
        return z2;
    }

    public final void i() {
        Integer num;
        Integer num2;
        Integer num3;
        boolean zIsChangingConfigurations = true;
        this.H = true;
        w(true);
        Iterator it = d().iterator();
        while (it.hasNext()) {
            ((androidx.fragment.app.j) it.next()).e();
        }
        hp0<?> hp0Var = this.u;
        boolean z = hp0Var instanceof bn3;
        la0 la0Var = this.c;
        if (z) {
            zIsChangingConfigurations = ((mp0) la0Var.h).g;
        } else {
            Context context = hp0Var.l;
            if (context instanceof Activity) {
                zIsChangingConfigurations = true ^ ((Activity) context).isChangingConfigurations();
            }
        }
        if (zIsChangingConfigurations) {
            Iterator<BackStackState> it2 = this.j.values().iterator();
            while (it2.hasNext()) {
                Iterator it3 = it2.next().e.iterator();
                while (it3.hasNext()) {
                    ((mp0) la0Var.h).b((String) it3.next());
                }
            }
        }
        r(-1);
        Object obj = this.u;
        if (obj instanceof ls1) {
            ((ls1) obj).F0(this.p);
        }
        Object obj2 = this.u;
        if (obj2 instanceof cs1) {
            ((cs1) obj2).u(this.o);
        }
        Object obj3 = this.u;
        if (obj3 instanceof es1) {
            ((es1) obj3).o1(this.q);
        }
        Object obj4 = this.u;
        if (obj4 instanceof fs1) {
            ((fs1) obj4).l0(this.r);
        }
        Object obj5 = this.u;
        if ((obj5 instanceof gf1) && this.w == null) {
            ((gf1) obj5).U0(this.s);
        }
        this.u = null;
        this.v = null;
        this.w = null;
        if (this.g != null) {
            Iterator<yl> it4 = this.h.b.iterator();
            while (it4.hasNext()) {
                it4.next().cancel();
            }
            this.g = null;
        }
        w2 w2Var = this.A;
        if (w2Var != null) {
            androidx.activity.result.a aVar = w2Var.m;
            ArrayList<String> arrayList = aVar.d;
            String str = w2Var.k;
            if (!arrayList.contains(str) && (num3 = (Integer) aVar.b.remove(str)) != null) {
                aVar.a.remove(num3);
            }
            aVar.e.remove(str);
            HashMap map = aVar.f;
            if (map.containsKey(str)) {
                StringBuilder sbN = C0175b.n("Dropping pending result for request ", str, ": ");
                sbN.append(map.get(str));
                Log.w("ActivityResultRegistry", sbN.toString());
                map.remove(str);
            }
            Bundle bundle = aVar.g;
            if (bundle.containsKey(str)) {
                StringBuilder sbN2 = C0175b.n("Dropping pending result for request ", str, ": ");
                sbN2.append(bundle.getParcelable(str));
                Log.w("ActivityResultRegistry", sbN2.toString());
                bundle.remove(str);
            }
            if (((a.b) aVar.c.get(str)) != null) {
                throw null;
            }
            w2 w2Var2 = this.B;
            androidx.activity.result.a aVar2 = w2Var2.m;
            ArrayList<String> arrayList2 = aVar2.d;
            String str2 = w2Var2.k;
            if (!arrayList2.contains(str2) && (num2 = (Integer) aVar2.b.remove(str2)) != null) {
                aVar2.a.remove(num2);
            }
            aVar2.e.remove(str2);
            HashMap map2 = aVar2.f;
            if (map2.containsKey(str2)) {
                StringBuilder sbN3 = C0175b.n("Dropping pending result for request ", str2, ": ");
                sbN3.append(map2.get(str2));
                Log.w("ActivityResultRegistry", sbN3.toString());
                map2.remove(str2);
            }
            Bundle bundle2 = aVar2.g;
            if (bundle2.containsKey(str2)) {
                StringBuilder sbN4 = C0175b.n("Dropping pending result for request ", str2, ": ");
                sbN4.append(bundle2.getParcelable(str2));
                Log.w("ActivityResultRegistry", sbN4.toString());
                bundle2.remove(str2);
            }
            if (((a.b) aVar2.c.get(str2)) != null) {
                throw null;
            }
            w2 w2Var3 = this.C;
            androidx.activity.result.a aVar3 = w2Var3.m;
            ArrayList<String> arrayList3 = aVar3.d;
            String str3 = w2Var3.k;
            if (!arrayList3.contains(str3) && (num = (Integer) aVar3.b.remove(str3)) != null) {
                aVar3.a.remove(num);
            }
            aVar3.e.remove(str3);
            HashMap map3 = aVar3.f;
            if (map3.containsKey(str3)) {
                StringBuilder sbN5 = C0175b.n("Dropping pending result for request ", str3, ": ");
                sbN5.append(map3.get(str3));
                Log.w("ActivityResultRegistry", sbN5.toString());
                map3.remove(str3);
            }
            Bundle bundle3 = aVar3.g;
            if (bundle3.containsKey(str3)) {
                StringBuilder sbN6 = C0175b.n("Dropping pending result for request ", str3, ": ");
                sbN6.append(bundle3.getParcelable(str3));
                Log.w("ActivityResultRegistry", sbN6.toString());
                bundle3.remove(str3);
            }
            if (((a.b) aVar3.c.get(str3)) != null) {
                throw null;
            }
        }
    }

    public final void j(boolean z) {
        if (z && (this.u instanceof ls1)) {
            b0(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
            throw null;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null) {
                fragment.onLowMemory();
                if (z) {
                    fragment.x.j(true);
                }
            }
        }
    }

    public final void k(boolean z, boolean z2) {
        if (z2 && (this.u instanceof es1)) {
            b0(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
            throw null;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null && z2) {
                fragment.x.k(z, true);
            }
        }
    }

    public final void l() {
        for (Fragment fragment : this.c.i()) {
            if (fragment != null) {
                fragment.W();
                fragment.x.l();
            }
        }
    }

    public final boolean m(MenuItem menuItem) {
        if (this.t < 1) {
            return false;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null) {
                if (!fragment.C ? (fragment.F && fragment.G && fragment.k0(menuItem)) ? true : fragment.x.m(menuItem) : false) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void n() {
        if (this.t < 1) {
            return;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null && !fragment.C) {
                fragment.x.n();
            }
        }
    }

    public final void o(Fragment fragment) {
        if (fragment == null || !fragment.equals(z(fragment.i))) {
            return;
        }
        fragment.v.getClass();
        boolean zK = K(fragment);
        Boolean bool = fragment.n;
        if (bool == null || bool.booleanValue() != zK) {
            fragment.n = Boolean.valueOf(zK);
            lp0 lp0Var = fragment.x;
            lp0Var.c0();
            lp0Var.o(lp0Var.x);
        }
    }

    public final void p(boolean z, boolean z2) {
        if (z2 && (this.u instanceof fs1)) {
            b0(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
            throw null;
        }
        for (Fragment fragment : this.c.j()) {
            if (fragment != null && z2) {
                fragment.x.p(z, true);
            }
        }
    }

    public final boolean q() {
        if (this.t < 1) {
            return false;
        }
        boolean z = false;
        for (Fragment fragment : this.c.j()) {
            if (fragment != null && J(fragment)) {
                if (fragment.C ? false : fragment.x.q() | (fragment.F && fragment.G)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void r(int i2) {
        try {
            this.b = true;
            for (androidx.fragment.app.h hVar : ((HashMap) this.c.f).values()) {
                if (hVar != null) {
                    hVar.e = i2;
                }
            }
            M(i2, false);
            Iterator it = d().iterator();
            while (it.hasNext()) {
                ((androidx.fragment.app.j) it.next()).e();
            }
            this.b = false;
            w(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public void removeFragmentOnAttachListener(np0 np0Var) {
        this.n.remove(np0Var);
    }

    public void removeOnBackStackChangedListener(l lVar) {
        ArrayList<l> arrayList = this.l;
        if (arrayList != null) {
            arrayList.remove(lVar);
        }
    }

    public final void s() {
        if (this.I) {
            this.I = false;
            a0();
        }
    }

    public final void t(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String strF = gq2.f(str, "    ");
        la0 la0Var = this.c;
        la0Var.getClass();
        String str2 = str + "    ";
        HashMap map = (HashMap) la0Var.f;
        if (!map.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (androidx.fragment.app.h hVar : map.values()) {
                printWriter.print(str);
                if (hVar != null) {
                    Fragment fragment = hVar.c;
                    printWriter.println(fragment);
                    fragment.getClass();
                    printWriter.print(str2);
                    printWriter.print("mFragmentId=#");
                    printWriter.print(Integer.toHexString(fragment.z));
                    printWriter.print(" mContainerId=#");
                    printWriter.print(Integer.toHexString(fragment.A));
                    printWriter.print(" mTag=");
                    printWriter.println(fragment.B);
                    printWriter.print(str2);
                    printWriter.print("mState=");
                    printWriter.print(fragment.e);
                    printWriter.print(" mWho=");
                    printWriter.print(fragment.i);
                    printWriter.print(" mBackStackNesting=");
                    printWriter.println(fragment.u);
                    printWriter.print(str2);
                    printWriter.print("mAdded=");
                    printWriter.print(fragment.o);
                    printWriter.print(" mRemoving=");
                    printWriter.print(fragment.p);
                    printWriter.print(" mFromLayout=");
                    printWriter.print(fragment.q);
                    printWriter.print(" mInLayout=");
                    printWriter.println(fragment.r);
                    printWriter.print(str2);
                    printWriter.print("mHidden=");
                    printWriter.print(fragment.C);
                    printWriter.print(" mDetached=");
                    printWriter.print(fragment.D);
                    printWriter.print(" mMenuVisible=");
                    printWriter.print(fragment.G);
                    printWriter.print(" mHasMenu=");
                    printWriter.println(fragment.F);
                    printWriter.print(str2);
                    printWriter.print("mRetainInstance=");
                    printWriter.print(fragment.E);
                    printWriter.print(" mUserVisibleHint=");
                    printWriter.println(fragment.L);
                    if (fragment.v != null) {
                        printWriter.print(str2);
                        printWriter.print("mFragmentManager=");
                        printWriter.println(fragment.v);
                    }
                    if (fragment.w != null) {
                        printWriter.print(str2);
                        printWriter.print("mHost=");
                        printWriter.println(fragment.w);
                    }
                    if (fragment.y != null) {
                        printWriter.print(str2);
                        printWriter.print("mParentFragment=");
                        printWriter.println(fragment.y);
                    }
                    if (fragment.j != null) {
                        printWriter.print(str2);
                        printWriter.print("mArguments=");
                        printWriter.println(fragment.j);
                    }
                    if (fragment.f != null) {
                        printWriter.print(str2);
                        printWriter.print("mSavedFragmentState=");
                        printWriter.println(fragment.f);
                    }
                    if (fragment.g != null) {
                        printWriter.print(str2);
                        printWriter.print("mSavedViewState=");
                        printWriter.println(fragment.g);
                    }
                    if (fragment.h != null) {
                        printWriter.print(str2);
                        printWriter.print("mSavedViewRegistryState=");
                        printWriter.println(fragment.h);
                    }
                    Object objQ = fragment.Q(false);
                    if (objQ != null) {
                        printWriter.print(str2);
                        printWriter.print("mTarget=");
                        printWriter.print(objQ);
                        printWriter.print(" mTargetRequestCode=");
                        printWriter.println(fragment.m);
                    }
                    printWriter.print(str2);
                    printWriter.print("mPopDirection=");
                    Fragment.c cVar = fragment.M;
                    printWriter.println(cVar == null ? false : cVar.a);
                    Fragment.c cVar2 = fragment.M;
                    if ((cVar2 == null ? 0 : cVar2.b) != 0) {
                        printWriter.print(str2);
                        printWriter.print("getEnterAnim=");
                        Fragment.c cVar3 = fragment.M;
                        printWriter.println(cVar3 == null ? 0 : cVar3.b);
                    }
                    Fragment.c cVar4 = fragment.M;
                    if ((cVar4 == null ? 0 : cVar4.c) != 0) {
                        printWriter.print(str2);
                        printWriter.print("getExitAnim=");
                        Fragment.c cVar5 = fragment.M;
                        printWriter.println(cVar5 == null ? 0 : cVar5.c);
                    }
                    Fragment.c cVar6 = fragment.M;
                    if ((cVar6 == null ? 0 : cVar6.d) != 0) {
                        printWriter.print(str2);
                        printWriter.print("getPopEnterAnim=");
                        Fragment.c cVar7 = fragment.M;
                        printWriter.println(cVar7 == null ? 0 : cVar7.d);
                    }
                    Fragment.c cVar8 = fragment.M;
                    if ((cVar8 == null ? 0 : cVar8.e) != 0) {
                        printWriter.print(str2);
                        printWriter.print("getPopExitAnim=");
                        Fragment.c cVar9 = fragment.M;
                        printWriter.println(cVar9 == null ? 0 : cVar9.e);
                    }
                    if (fragment.I != null) {
                        printWriter.print(str2);
                        printWriter.print("mContainer=");
                        printWriter.println(fragment.I);
                    }
                    if (fragment.f12J != null) {
                        printWriter.print(str2);
                        printWriter.print("mView=");
                        printWriter.println(fragment.f12J);
                    }
                    if (fragment.K() != null) {
                        nq2<j91.a> nq2Var = ((j91.b) new androidx.lifecycle.l(fragment.h0(), j91.b.d).a(j91.b.class)).c;
                        if (nq2Var.g > 0) {
                            printWriter.print(str2);
                            printWriter.println("Loaders:");
                            if (nq2Var.g > 0) {
                                j91.a aVar = (j91.a) nq2Var.f[0];
                                printWriter.print(str2);
                                printWriter.print("  #");
                                printWriter.print(nq2Var.e[0]);
                                printWriter.print(": ");
                                aVar.toString();
                                throw null;
                            }
                        }
                    }
                    printWriter.print(str2);
                    printWriter.println("Child " + fragment.x + ":");
                    fragment.x.t(gq2.f(str2, "  "), fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        ArrayList arrayList = (ArrayList) la0Var.e;
        int size3 = arrayList.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size3; i2++) {
                Fragment fragment2 = (Fragment) arrayList.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList2 = this.e;
        if (arrayList2 != null && (size2 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size2; i3++) {
                Fragment fragment3 = this.e.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList3 = this.d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size; i4++) {
                androidx.fragment.app.a aVar2 = this.d.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(aVar2.toString());
                aVar2.j(strF, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.a) {
            int size4 = this.a.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i5 = 0; i5 < size4; i5++) {
                    Object obj = (m) this.a.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
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
        printWriter.print(this.F);
        printWriter.print(" mStopped=");
        printWriter.print(this.G);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.H);
        if (this.E) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.E);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.w;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.w)));
            sb.append("}");
        } else {
            hp0<?> hp0Var = this.u;
            if (hp0Var != null) {
                sb.append(hp0Var.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.u)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(m mVar, boolean z) {
        if (!z) {
            if (this.u == null) {
                if (!this.H) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            if (L()) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.a) {
            if (this.u == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.a.add(mVar);
                V();
            }
        }
    }

    public final void v(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.u == null) {
            if (!this.H) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.u.m.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && L()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f13J == null) {
            this.f13J = new ArrayList<>();
            this.K = new ArrayList<>();
        }
    }

    public final boolean w(boolean z) {
        boolean zA;
        v(z);
        boolean z2 = false;
        while (true) {
            ArrayList<androidx.fragment.app.a> arrayList = this.f13J;
            ArrayList<Boolean> arrayList2 = this.K;
            synchronized (this.a) {
                if (this.a.isEmpty()) {
                    zA = false;
                } else {
                    try {
                        int size = this.a.size();
                        zA = false;
                        for (int i2 = 0; i2 < size; i2++) {
                            zA |= this.a.get(i2).a(arrayList, arrayList2);
                        }
                    } finally {
                    }
                }
            }
            if (!zA) {
                c0();
                s();
                this.c.c();
                return z2;
            }
            z2 = true;
            this.b = true;
            try {
                S(this.f13J, this.K);
            } finally {
                c();
            }
        }
    }

    public final void x(m mVar, boolean z) {
        if (z && (this.u == null || this.H)) {
            return;
        }
        v(z);
        if (mVar.a(this.f13J, this.K)) {
            this.b = true;
            try {
                S(this.f13J, this.K);
            } finally {
                c();
            }
        }
        c0();
        s();
        this.c.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x022b A[PHI: r14
  0x022b: PHI (r14v13 int) = (r14v11 int), (r14v14 int) binds: [B:106:0x0219, B:108:0x021f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0163  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y(java.util.ArrayList<androidx.fragment.app.a> r24, java.util.ArrayList<java.lang.Boolean> r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 1604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.y(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public final Fragment z(String str) {
        return this.c.f(str);
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();
        public final String e;
        public final int f;

        public class a implements Parcelable.Creator<LaunchedFragmentInfo> {
            @Override // android.os.Parcelable.Creator
            public final LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final LaunchedFragmentInfo[] newArray(int i) {
                return new LaunchedFragmentInfo[i];
            }
        }

        public LaunchedFragmentInfo(String str, int i) {
            this.e = str;
            this.f = i;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.e);
            parcel.writeInt(this.f);
        }

        public LaunchedFragmentInfo(Parcel parcel) {
            this.e = parcel.readString();
            this.f = parcel.readInt();
        }
    }
}
