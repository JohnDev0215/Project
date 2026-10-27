package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.d0;
import androidx.lifecycle.i;
import androidx.navigation.j;
import androidx.navigation.o;
import androidx.navigation.r;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NavController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Activity f2613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private n f2614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    k f2615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bundle f2616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Parcelable[] f2617f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2618g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private androidx.lifecycle.o f2620i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private f f2621j;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Deque<e> f2619h = new ArrayDeque();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private s f2622k = new s();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final CopyOnWriteArrayList<b> f2623l = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final androidx.lifecycle.n f2624m = new androidx.lifecycle.m() { // from class: androidx.navigation.NavController.1
        @Override // androidx.lifecycle.m
        public void d(androidx.lifecycle.o oVar, i.b bVar) {
            NavController navController = NavController.this;
            if (navController.f2615d != null) {
                Iterator<e> it = navController.f2619h.iterator();
                while (it.hasNext()) {
                    it.next().i(bVar);
                }
            }
        }
    };

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final androidx.activity.b f2625n = new a(false);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f2626o = true;

    class a extends androidx.activity.b {
        a(boolean z6) {
            super(z6);
        }

        @Override // androidx.activity.b
        public void b() {
            NavController.this.u();
        }
    }

    public interface b {
        void a(NavController navController, j jVar, Bundle bundle);
    }

    public NavController(Context context) {
        this.f2612a = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            }
            if (context instanceof Activity) {
                this.f2613b = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        s sVar = this.f2622k;
        sVar.a(new l(sVar));
        this.f2622k.a(new androidx.navigation.a(this.f2612a));
    }

    private void F() {
        this.f2625n.f(this.f2626o && h() > 1);
    }

    private boolean a() {
        while (!this.f2619h.isEmpty() && (this.f2619h.peekLast().e() instanceof k) && w(this.f2619h.peekLast().e().k(), true)) {
        }
        if (this.f2619h.isEmpty()) {
            return false;
        }
        j jVarE = this.f2619h.peekLast().e();
        j jVarN = null;
        if (jVarE instanceof androidx.navigation.b) {
            Iterator<e> itDescendingIterator = this.f2619h.descendingIterator();
            while (true) {
                if (!itDescendingIterator.hasNext()) {
                    break;
                }
                j jVarE2 = itDescendingIterator.next().e();
                if (!(jVarE2 instanceof k) && !(jVarE2 instanceof androidx.navigation.b)) {
                    jVarN = jVarE2;
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        Iterator<e> itDescendingIterator2 = this.f2619h.descendingIterator();
        while (itDescendingIterator2.hasNext()) {
            e next = itDescendingIterator2.next();
            i.c cVarF = next.f();
            j jVarE3 = next.e();
            if (jVarE != null && jVarE3.k() == jVarE.k()) {
                i.c cVar = i.c.RESUMED;
                if (cVarF != cVar) {
                    map.put(next, cVar);
                }
                jVarE = jVarE.n();
            } else if (jVarN == null || jVarE3.k() != jVarN.k()) {
                next.n(i.c.CREATED);
            } else {
                if (cVarF == i.c.RESUMED) {
                    next.n(i.c.STARTED);
                } else {
                    i.c cVar2 = i.c.STARTED;
                    if (cVarF != cVar2) {
                        map.put(next, cVar2);
                    }
                }
                jVarN = jVarN.n();
            }
        }
        for (e eVar : this.f2619h) {
            i.c cVar3 = (i.c) map.get(eVar);
            if (cVar3 != null) {
                eVar.n(cVar3);
            } else {
                eVar.o();
            }
        }
        e eVarPeekLast = this.f2619h.peekLast();
        Iterator<b> it = this.f2623l.iterator();
        while (it.hasNext()) {
            it.next().a(this, eVarPeekLast.e(), eVarPeekLast.b());
        }
        return true;
    }

    private String d(int[] iArr) {
        k kVar;
        k kVar2 = this.f2615d;
        int i7 = 0;
        while (true) {
            j jVarW = null;
            if (i7 >= iArr.length) {
                return null;
            }
            int i8 = iArr[i7];
            if (i7 != 0) {
                jVarW = kVar2.w(i8);
            } else if (this.f2615d.k() == i8) {
                jVarW = this.f2615d;
            }
            if (jVarW == null) {
                return j.j(this.f2612a, i8);
            }
            if (i7 != iArr.length - 1) {
                while (true) {
                    kVar = (k) jVarW;
                    if (!(kVar.w(kVar.z()) instanceof k)) {
                        break;
                    }
                    jVarW = kVar.w(kVar.z());
                }
                kVar2 = kVar;
            }
            i7++;
        }
    }

    private int h() {
        Iterator<e> it = this.f2619h.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            if (!(it.next().e() instanceof k)) {
                i7++;
            }
        }
        return i7;
    }

    private void r(j jVar, Bundle bundle, o oVar, r.a aVar) {
        boolean z6 = false;
        boolean zW = (oVar == null || oVar.e() == -1) ? false : w(oVar.e(), oVar.f());
        r rVarE = this.f2622k.e(jVar.m());
        Bundle bundleE = jVar.e(bundle);
        j jVarB = rVarE.b(jVar, bundleE, oVar, aVar);
        if (jVarB != null) {
            if (!(jVarB instanceof androidx.navigation.b)) {
                while (!this.f2619h.isEmpty() && (this.f2619h.peekLast().e() instanceof androidx.navigation.b) && w(this.f2619h.peekLast().e().k(), true)) {
                }
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            if (jVar instanceof k) {
                j jVar2 = jVarB;
                while (true) {
                    k kVarN = jVar2.n();
                    if (kVarN != null) {
                        arrayDeque.addFirst(new e(this.f2612a, kVarN, bundleE, this.f2620i, this.f2621j));
                        if (!this.f2619h.isEmpty() && this.f2619h.getLast().e() == kVarN) {
                            w(kVarN.k(), true);
                        }
                    }
                    if (kVarN == null || kVarN == jVar) {
                        break;
                    } else {
                        jVar2 = kVarN;
                    }
                }
            }
            j jVarE = arrayDeque.isEmpty() ? jVarB : ((e) arrayDeque.getFirst()).e();
            while (jVarE != null && c(jVarE.k()) == null) {
                jVarE = jVarE.n();
                if (jVarE != null) {
                    arrayDeque.addFirst(new e(this.f2612a, jVarE, bundleE, this.f2620i, this.f2621j));
                }
            }
            j jVarE2 = arrayDeque.isEmpty() ? jVarB : ((e) arrayDeque.getLast()).e();
            while (!this.f2619h.isEmpty() && (this.f2619h.getLast().e() instanceof k) && ((k) this.f2619h.getLast().e()).x(jVarE2.k(), false) == null && w(this.f2619h.getLast().e().k(), true)) {
            }
            this.f2619h.addAll(arrayDeque);
            if (this.f2619h.isEmpty() || this.f2619h.getFirst().e() != this.f2615d) {
                this.f2619h.addFirst(new e(this.f2612a, this.f2615d, bundleE, this.f2620i, this.f2621j));
            }
            this.f2619h.add(new e(this.f2612a, jVarB, jVarB.e(bundleE), this.f2620i, this.f2621j));
        } else if (oVar != null && oVar.g()) {
            e eVarPeekLast = this.f2619h.peekLast();
            if (eVarPeekLast != null) {
                eVarPeekLast.j(bundleE);
            }
            z6 = true;
        }
        F();
        if (zW || jVarB != null || z6) {
            a();
        }
    }

    private void t(Bundle bundle) {
        Activity activity;
        ArrayList<String> stringArrayList;
        Bundle bundle2 = this.f2616e;
        if (bundle2 != null && (stringArrayList = bundle2.getStringArrayList("android-support-nav:controller:navigatorState:names")) != null) {
            for (String str : stringArrayList) {
                r rVarE = this.f2622k.e(str);
                Bundle bundle3 = this.f2616e.getBundle(str);
                if (bundle3 != null) {
                    rVarE.c(bundle3);
                }
            }
        }
        Parcelable[] parcelableArr = this.f2617f;
        boolean z6 = false;
        if (parcelableArr != null) {
            for (Parcelable parcelable : parcelableArr) {
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) parcelable;
                j jVarC = c(navBackStackEntryState.c());
                if (jVarC == null) {
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + j.j(this.f2612a, navBackStackEntryState.c()) + " cannot be found from the current destination " + g());
                }
                Bundle bundleB = navBackStackEntryState.b();
                if (bundleB != null) {
                    bundleB.setClassLoader(this.f2612a.getClassLoader());
                }
                this.f2619h.add(new e(this.f2612a, jVarC, bundleB, this.f2620i, this.f2621j, navBackStackEntryState.j(), navBackStackEntryState.h()));
            }
            F();
            this.f2617f = null;
        }
        if (this.f2615d == null || !this.f2619h.isEmpty()) {
            a();
            return;
        }
        if (!this.f2618g && (activity = this.f2613b) != null && m(activity.getIntent())) {
            z6 = true;
        }
        if (z6) {
            return;
        }
        r(this.f2615d, bundle, null, null);
    }

    public void A(int i7, Bundle bundle) {
        B(j().c(i7), bundle);
    }

    public void B(k kVar, Bundle bundle) {
        k kVar2 = this.f2615d;
        if (kVar2 != null) {
            w(kVar2.k(), true);
        }
        this.f2615d = kVar;
        t(bundle);
    }

    void C(androidx.lifecycle.o oVar) {
        if (oVar == this.f2620i) {
            return;
        }
        this.f2620i = oVar;
        oVar.a().a(this.f2624m);
    }

    void D(OnBackPressedDispatcher onBackPressedDispatcher) {
        if (this.f2620i == null) {
            throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
        }
        this.f2625n.d();
        onBackPressedDispatcher.a(this.f2620i, this.f2625n);
        this.f2620i.a().c(this.f2624m);
        this.f2620i.a().a(this.f2624m);
    }

    void E(d0 d0Var) {
        if (this.f2621j == f.i(d0Var)) {
            return;
        }
        if (!this.f2619h.isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call");
        }
        this.f2621j = f.i(d0Var);
    }

    void b(boolean z6) {
        this.f2626o = z6;
        F();
    }

    j c(int i7) {
        k kVar = this.f2615d;
        if (kVar == null) {
            return null;
        }
        if (kVar.k() == i7) {
            return this.f2615d;
        }
        k kVarE = this.f2619h.isEmpty() ? this.f2615d : this.f2619h.getLast().e();
        return (kVarE instanceof k ? kVarE : kVarE.n()).w(i7);
    }

    Context e() {
        return this.f2612a;
    }

    public e f() {
        if (this.f2619h.isEmpty()) {
            return null;
        }
        return this.f2619h.getLast();
    }

    public j g() {
        e eVarF = f();
        if (eVarF != null) {
            return eVarF.e();
        }
        return null;
    }

    public k i() {
        k kVar = this.f2615d;
        if (kVar != null) {
            return kVar;
        }
        throw new IllegalStateException("You must call setGraph() before calling getGraph()");
    }

    public n j() {
        if (this.f2614c == null) {
            this.f2614c = new n(this.f2612a, this.f2622k);
        }
        return this.f2614c;
    }

    public s k() {
        return this.f2622k;
    }

    public e l() {
        Iterator<e> itDescendingIterator = this.f2619h.descendingIterator();
        if (itDescendingIterator.hasNext()) {
            itDescendingIterator.next();
        }
        while (itDescendingIterator.hasNext()) {
            e next = itDescendingIterator.next();
            if (!(next.e() instanceof k)) {
                return next;
            }
        }
        return null;
    }

    public boolean m(Intent intent) {
        j.a aVarO;
        k kVar;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] intArray = extras != null ? extras.getIntArray("android-support-nav:controller:deepLinkIds") : null;
        Bundle bundle = new Bundle();
        Bundle bundle2 = extras != null ? extras.getBundle("android-support-nav:controller:deepLinkExtras") : null;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if ((intArray == null || intArray.length == 0) && intent.getData() != null && (aVarO = this.f2615d.o(new i(intent))) != null) {
            j jVarB = aVarO.b();
            int[] iArrF = jVarB.f();
            bundle.putAll(jVarB.e(aVarO.c()));
            intArray = iArrF;
        }
        if (intArray == null || intArray.length == 0) {
            return false;
        }
        String strD = d(intArray);
        if (strD != null) {
            Log.i("NavController", "Could not find destination " + strD + " in the navigation graph, ignoring the deep link from " + intent);
            return false;
        }
        bundle.putParcelable("android-support-nav:controller:deepLinkIntent", intent);
        int flags = intent.getFlags();
        int i7 = 268435456 & flags;
        if (i7 != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            w.i.g(this.f2612a).d(intent).j();
            Activity activity = this.f2613b;
            if (activity != null) {
                activity.finish();
                this.f2613b.overridePendingTransition(0, 0);
            }
            return true;
        }
        if (i7 != 0) {
            if (!this.f2619h.isEmpty()) {
                w(this.f2615d.k(), true);
            }
            int i8 = 0;
            while (i8 < intArray.length) {
                int i9 = i8 + 1;
                int i10 = intArray[i8];
                j jVarC = c(i10);
                if (jVarC == null) {
                    throw new IllegalStateException("Deep Linking failed: destination " + j.j(this.f2612a, i10) + " cannot be found from the current destination " + g());
                }
                r(jVarC, bundle, new o.a().b(0).c(0).a(), null);
                i8 = i9;
            }
            return true;
        }
        k kVar2 = this.f2615d;
        int i11 = 0;
        while (i11 < intArray.length) {
            int i12 = intArray[i11];
            j jVarW = i11 == 0 ? this.f2615d : kVar2.w(i12);
            if (jVarW == null) {
                throw new IllegalStateException("Deep Linking failed: destination " + j.j(this.f2612a, i12) + " cannot be found in graph " + kVar2);
            }
            if (i11 != intArray.length - 1) {
                while (true) {
                    kVar = (k) jVarW;
                    if (!(kVar.w(kVar.z()) instanceof k)) {
                        break;
                    }
                    jVarW = kVar.w(kVar.z());
                }
                kVar2 = kVar;
            } else {
                r(jVarW, jVarW.e(bundle), new o.a().g(this.f2615d.k(), true).b(0).c(0).a(), null);
            }
            i11++;
        }
        this.f2618g = true;
        return true;
    }

    public void n(int i7) {
        o(i7, null);
    }

    public void o(int i7, Bundle bundle) {
        p(i7, bundle, null);
    }

    public void p(int i7, Bundle bundle, o oVar) {
        q(i7, bundle, oVar, null);
    }

    public void q(int i7, Bundle bundle, o oVar, r.a aVar) {
        int iB;
        j jVarE = this.f2619h.isEmpty() ? this.f2615d : this.f2619h.getLast().e();
        if (jVarE == null) {
            throw new IllegalStateException("no current navigation node");
        }
        c cVarG = jVarE.g(i7);
        Bundle bundle2 = null;
        if (cVarG != null) {
            if (oVar == null) {
                oVar = cVarG.c();
            }
            iB = cVarG.b();
            Bundle bundleA = cVarG.a();
            if (bundleA != null) {
                bundle2 = new Bundle();
                bundle2.putAll(bundleA);
            }
        } else {
            iB = i7;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putAll(bundle);
        }
        if (iB == 0 && oVar != null && oVar.e() != -1) {
            v(oVar.e(), oVar.f());
            return;
        }
        if (iB == 0) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
        }
        j jVarC = c(iB);
        if (jVarC != null) {
            r(jVarC, bundle2, oVar, aVar);
            return;
        }
        String strJ = j.j(this.f2612a, iB);
        if (cVarG == null) {
            throw new IllegalArgumentException("Navigation action/destination " + strJ + " cannot be found from the current destination " + jVarE);
        }
        throw new IllegalArgumentException("Navigation destination " + strJ + " referenced from action " + j.j(this.f2612a, i7) + " cannot be found from the current destination " + jVarE);
    }

    public boolean s() {
        if (h() != 1) {
            return u();
        }
        j jVarG = g();
        int iK = jVarG.k();
        for (k kVarN = jVarG.n(); kVarN != null; kVarN = kVarN.n()) {
            if (kVarN.z() != iK) {
                Bundle bundle = new Bundle();
                Activity activity = this.f2613b;
                if (activity != null && activity.getIntent() != null && this.f2613b.getIntent().getData() != null) {
                    bundle.putParcelable("android-support-nav:controller:deepLinkIntent", this.f2613b.getIntent());
                    j.a aVarO = this.f2615d.o(new i(this.f2613b.getIntent()));
                    if (aVarO != null) {
                        bundle.putAll(aVarO.b().e(aVarO.c()));
                    }
                }
                new h(this).d(kVarN.k()).c(bundle).a().j();
                Activity activity2 = this.f2613b;
                if (activity2 != null) {
                    activity2.finish();
                }
                return true;
            }
            iK = kVarN.k();
        }
        return false;
    }

    public boolean u() {
        if (this.f2619h.isEmpty()) {
            return false;
        }
        return v(g().k(), true);
    }

    public boolean v(int i7, boolean z6) {
        return w(i7, z6) && a();
    }

    boolean w(int i7, boolean z6) {
        boolean z7;
        boolean z8 = false;
        if (this.f2619h.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<e> itDescendingIterator = this.f2619h.descendingIterator();
        while (true) {
            if (!itDescendingIterator.hasNext()) {
                z7 = false;
                break;
            }
            j jVarE = itDescendingIterator.next().e();
            r rVarE = this.f2622k.e(jVarE.m());
            if (z6 || jVarE.k() != i7) {
                arrayList.add(rVarE);
            }
            if (jVarE.k() == i7) {
                z7 = true;
                break;
            }
        }
        if (!z7) {
            Log.i("NavController", "Ignoring popBackStack to destination " + j.j(this.f2612a, i7) + " as it was not found on the current back stack");
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((r) it.next()).e()) {
            e eVarRemoveLast = this.f2619h.removeLast();
            if (eVarRemoveLast.a().b().isAtLeast(i.c.CREATED)) {
                eVarRemoveLast.n(i.c.DESTROYED);
            }
            f fVar = this.f2621j;
            if (fVar != null) {
                fVar.h(eVarRemoveLast.f2650k);
            }
            z8 = true;
        }
        F();
        return z8;
    }

    public void x(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(this.f2612a.getClassLoader());
        this.f2616e = bundle.getBundle("android-support-nav:controller:navigatorState");
        this.f2617f = bundle.getParcelableArray("android-support-nav:controller:backStack");
        this.f2618g = bundle.getBoolean("android-support-nav:controller:deepLinkHandled");
    }

    public Bundle y() {
        Bundle bundle;
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, r<? extends j>> entry : this.f2622k.f().entrySet()) {
            String key = entry.getKey();
            Bundle bundleD = entry.getValue().d();
            if (bundleD != null) {
                arrayList.add(key);
                bundle2.putBundle(key, bundleD);
            }
        }
        if (arrayList.isEmpty()) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        }
        if (!this.f2619h.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[this.f2619h.size()];
            int i7 = 0;
            Iterator<e> it = this.f2619h.iterator();
            while (it.hasNext()) {
                parcelableArr[i7] = new NavBackStackEntryState(it.next());
                i7++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (this.f2618g) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", this.f2618g);
        }
        return bundle;
    }

    public void z(int i7) {
        A(i7, null);
    }
}
