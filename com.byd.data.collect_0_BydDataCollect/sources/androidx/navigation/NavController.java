package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.d;
import androidx.navigation.i;
import androidx.navigation.n;
import androidx.navigation.q;
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
    private final Context f1632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Activity f1633b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f1634c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    j f1635d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bundle f1636e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Parcelable[] f1637f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1638g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private androidx.lifecycle.g f1640i;
    private f j;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Deque<e> f1639h = new ArrayDeque();
    private r k = new r();
    private final CopyOnWriteArrayList<b> l = new CopyOnWriteArrayList<>();
    private final androidx.lifecycle.f m = new androidx.lifecycle.e() { // from class: androidx.navigation.NavController.1
        @Override // androidx.lifecycle.e
        public void d(androidx.lifecycle.g gVar, d.a aVar) {
            NavController navController = NavController.this;
            if (navController.f1635d != null) {
                Iterator<e> it = navController.f1639h.iterator();
                while (it.hasNext()) {
                    it.next().h(aVar);
                }
            }
        }
    };
    private final androidx.activity.b n = new a(false);
    private boolean o = true;

    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void b() {
            NavController.this.q();
        }
    }

    public interface b {
        void a(NavController navController, i iVar, Bundle bundle);
    }

    public NavController(Context context) {
        this.f1632a = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            }
            if (context instanceof Activity) {
                this.f1633b = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        r rVar = this.k;
        rVar.a(new k(rVar));
        this.k.a(new androidx.navigation.a(this.f1632a));
    }

    private void B() {
        this.n.f(this.o && g() > 1);
    }

    private boolean a() {
        while (!this.f1639h.isEmpty() && (this.f1639h.peekLast().c() instanceof j) && s(this.f1639h.peekLast().c().i(), true)) {
        }
        if (this.f1639h.isEmpty()) {
            return false;
        }
        i iVarC = this.f1639h.peekLast().c();
        i iVarK = null;
        if (iVarC instanceof androidx.navigation.b) {
            Iterator<e> itDescendingIterator = this.f1639h.descendingIterator();
            while (true) {
                if (!itDescendingIterator.hasNext()) {
                    break;
                }
                i iVarC2 = itDescendingIterator.next().c();
                if (!(iVarC2 instanceof j) && !(iVarC2 instanceof androidx.navigation.b)) {
                    iVarK = iVarC2;
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        Iterator<e> itDescendingIterator2 = this.f1639h.descendingIterator();
        while (itDescendingIterator2.hasNext()) {
            e next = itDescendingIterator2.next();
            d.b bVarF = next.f();
            i iVarC3 = next.c();
            if (iVarC != null && iVarC3.i() == iVarC.i()) {
                d.b bVar = d.b.RESUMED;
                if (bVarF != bVar) {
                    map.put(next, bVar);
                }
                iVarC = iVarC.k();
            } else if (iVarK == null || iVarC3.i() != iVarK.i()) {
                next.l(d.b.CREATED);
            } else {
                if (bVarF == d.b.RESUMED) {
                    next.l(d.b.STARTED);
                } else {
                    d.b bVar2 = d.b.STARTED;
                    if (bVarF != bVar2) {
                        map.put(next, bVar2);
                    }
                }
                iVarK = iVarK.k();
            }
        }
        for (e eVar : this.f1639h) {
            d.b bVar3 = (d.b) map.get(eVar);
            if (bVar3 != null) {
                eVar.l(bVar3);
            } else {
                eVar.m();
            }
        }
        e eVarPeekLast = this.f1639h.peekLast();
        Iterator<b> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().a(this, eVarPeekLast.c(), eVarPeekLast.b());
        }
        return true;
    }

    private String d(int[] iArr) {
        j jVar;
        j jVar2 = this.f1635d;
        int i2 = 0;
        while (true) {
            i iVarT = null;
            if (i2 >= iArr.length) {
                return null;
            }
            int i3 = iArr[i2];
            if (i2 != 0) {
                iVarT = jVar2.t(i3);
            } else if (this.f1635d.i() == i3) {
                iVarT = this.f1635d;
            }
            if (iVarT == null) {
                return i.h(this.f1632a, i3);
            }
            if (i2 != iArr.length - 1) {
                while (true) {
                    jVar = (j) iVarT;
                    if (!(jVar.t(jVar.w()) instanceof j)) {
                        break;
                    }
                    iVarT = jVar.t(jVar.w());
                }
                jVar2 = jVar;
            }
            i2++;
        }
    }

    private int g() {
        Iterator<e> it = this.f1639h.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (!(it.next().c() instanceof j)) {
                i2++;
            }
        }
        return i2;
    }

    private void o(i iVar, Bundle bundle, n nVar, q.a aVar) {
        boolean z = false;
        boolean zS = (nVar == null || nVar.e() == -1) ? false : s(nVar.e(), nVar.f());
        q qVarE = this.k.e(iVar.j());
        Bundle bundleC = iVar.c(bundle);
        i iVarB = qVarE.b(iVar, bundleC, nVar, aVar);
        if (iVarB != null) {
            if (!(iVarB instanceof androidx.navigation.b)) {
                while (!this.f1639h.isEmpty() && (this.f1639h.peekLast().c() instanceof androidx.navigation.b) && s(this.f1639h.peekLast().c().i(), true)) {
                }
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            if (iVar instanceof j) {
                i iVar2 = iVarB;
                while (true) {
                    j jVarK = iVar2.k();
                    if (jVarK != null) {
                        arrayDeque.addFirst(new e(this.f1632a, jVarK, bundleC, this.f1640i, this.j));
                        if (!this.f1639h.isEmpty() && this.f1639h.getLast().c() == jVarK) {
                            s(jVarK.i(), true);
                        }
                    }
                    if (jVarK == null || jVarK == iVar) {
                        break;
                    } else {
                        iVar2 = jVarK;
                    }
                }
            }
            i iVarC = arrayDeque.isEmpty() ? iVarB : ((e) arrayDeque.getFirst()).c();
            while (iVarC != null && c(iVarC.i()) == null) {
                iVarC = iVarC.k();
                if (iVarC != null) {
                    arrayDeque.addFirst(new e(this.f1632a, iVarC, bundleC, this.f1640i, this.j));
                }
            }
            i iVarC2 = arrayDeque.isEmpty() ? iVarB : ((e) arrayDeque.getLast()).c();
            while (!this.f1639h.isEmpty() && (this.f1639h.getLast().c() instanceof j) && ((j) this.f1639h.getLast().c()).u(iVarC2.i(), false) == null && s(this.f1639h.getLast().c().i(), true)) {
            }
            this.f1639h.addAll(arrayDeque);
            if (this.f1639h.isEmpty() || this.f1639h.getFirst().c() != this.f1635d) {
                this.f1639h.addFirst(new e(this.f1632a, this.f1635d, bundleC, this.f1640i, this.j));
            }
            this.f1639h.add(new e(this.f1632a, iVarB, iVarB.c(bundleC), this.f1640i, this.j));
        } else if (nVar != null && nVar.g()) {
            e eVarPeekLast = this.f1639h.peekLast();
            if (eVarPeekLast != null) {
                eVarPeekLast.i(bundleC);
            }
            z = true;
        }
        B();
        if (zS || iVarB != null || z) {
            a();
        }
    }

    private void p(Bundle bundle) {
        Activity activity;
        ArrayList<String> stringArrayList;
        Bundle bundle2 = this.f1636e;
        if (bundle2 != null && (stringArrayList = bundle2.getStringArrayList("android-support-nav:controller:navigatorState:names")) != null) {
            for (String str : stringArrayList) {
                q qVarE = this.k.e(str);
                Bundle bundle3 = this.f1636e.getBundle(str);
                if (bundle3 != null) {
                    qVarE.c(bundle3);
                }
            }
        }
        Parcelable[] parcelableArr = this.f1637f;
        boolean z = false;
        if (parcelableArr != null) {
            for (Parcelable parcelable : parcelableArr) {
                NavBackStackEntryState navBackStackEntryState = (NavBackStackEntryState) parcelable;
                i iVarC = c(navBackStackEntryState.c());
                if (iVarC == null) {
                    throw new IllegalStateException("Restoring the Navigation back stack failed: destination " + i.h(this.f1632a, navBackStackEntryState.c()) + " cannot be found from the current destination " + f());
                }
                Bundle bundleA = navBackStackEntryState.a();
                if (bundleA != null) {
                    bundleA.setClassLoader(this.f1632a.getClassLoader());
                }
                this.f1639h.add(new e(this.f1632a, iVarC, bundleA, this.f1640i, this.j, navBackStackEntryState.f(), navBackStackEntryState.d()));
            }
            B();
            this.f1637f = null;
        }
        if (this.f1635d == null || !this.f1639h.isEmpty()) {
            a();
            return;
        }
        if (!this.f1638g && (activity = this.f1633b) != null && j(activity.getIntent())) {
            z = true;
        }
        if (z) {
            return;
        }
        o(this.f1635d, bundle, null, null);
    }

    void A(androidx.lifecycle.r rVar) {
        if (this.j == f.f(rVar)) {
            return;
        }
        if (!this.f1639h.isEmpty()) {
            throw new IllegalStateException("ViewModelStore should be set before setGraph call");
        }
        this.j = f.f(rVar);
    }

    void b(boolean z) {
        this.o = z;
        B();
    }

    i c(int i2) {
        j jVar = this.f1635d;
        if (jVar == null) {
            return null;
        }
        if (jVar.i() == i2) {
            return this.f1635d;
        }
        j jVarC = this.f1639h.isEmpty() ? this.f1635d : this.f1639h.getLast().c();
        return (jVarC instanceof j ? jVarC : jVarC.k()).t(i2);
    }

    public e e() {
        if (this.f1639h.isEmpty()) {
            return null;
        }
        return this.f1639h.getLast();
    }

    public i f() {
        e eVarE = e();
        if (eVarE != null) {
            return eVarE.c();
        }
        return null;
    }

    public m h() {
        if (this.f1634c == null) {
            this.f1634c = new m(this.f1632a, this.k);
        }
        return this.f1634c;
    }

    public r i() {
        return this.k;
    }

    public boolean j(Intent intent) {
        i.a aVarL;
        j jVar;
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
        if ((intArray == null || intArray.length == 0) && intent.getData() != null && (aVarL = this.f1635d.l(new h(intent))) != null) {
            i iVarB = aVarL.b();
            int[] iArrD = iVarB.d();
            bundle.putAll(iVarB.c(aVarL.c()));
            intArray = iArrD;
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
        int i2 = 268435456 & flags;
        if (i2 != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            androidx.core.app.g.e(this.f1632a).b(intent).f();
            Activity activity = this.f1633b;
            if (activity != null) {
                activity.finish();
                this.f1633b.overridePendingTransition(0, 0);
            }
            return true;
        }
        if (i2 != 0) {
            if (!this.f1639h.isEmpty()) {
                s(this.f1635d.i(), true);
            }
            int i3 = 0;
            while (i3 < intArray.length) {
                int i4 = i3 + 1;
                int i5 = intArray[i3];
                i iVarC = c(i5);
                if (iVarC == null) {
                    throw new IllegalStateException("Deep Linking failed: destination " + i.h(this.f1632a, i5) + " cannot be found from the current destination " + f());
                }
                o(iVarC, bundle, new n.a().b(0).c(0).a(), null);
                i3 = i4;
            }
            return true;
        }
        j jVar2 = this.f1635d;
        int i6 = 0;
        while (i6 < intArray.length) {
            int i7 = intArray[i6];
            i iVarT = i6 == 0 ? this.f1635d : jVar2.t(i7);
            if (iVarT == null) {
                throw new IllegalStateException("Deep Linking failed: destination " + i.h(this.f1632a, i7) + " cannot be found in graph " + jVar2);
            }
            if (i6 != intArray.length - 1) {
                while (true) {
                    jVar = (j) iVarT;
                    if (!(jVar.t(jVar.w()) instanceof j)) {
                        break;
                    }
                    iVarT = jVar.t(jVar.w());
                }
                jVar2 = jVar;
            } else {
                o(iVarT, iVarT.c(bundle), new n.a().g(this.f1635d.i(), true).b(0).c(0).a(), null);
            }
            i6++;
        }
        this.f1638g = true;
        return true;
    }

    public void k(int i2) {
        l(i2, null);
    }

    public void l(int i2, Bundle bundle) {
        m(i2, bundle, null);
    }

    public void m(int i2, Bundle bundle, n nVar) {
        n(i2, bundle, nVar, null);
    }

    public void n(int i2, Bundle bundle, n nVar, q.a aVar) {
        int iB;
        i iVarC = this.f1639h.isEmpty() ? this.f1635d : this.f1639h.getLast().c();
        if (iVarC == null) {
            throw new IllegalStateException("no current navigation node");
        }
        c cVarE = iVarC.e(i2);
        Bundle bundle2 = null;
        if (cVarE != null) {
            if (nVar == null) {
                nVar = cVarE.c();
            }
            iB = cVarE.b();
            Bundle bundleA = cVarE.a();
            if (bundleA != null) {
                bundle2 = new Bundle();
                bundle2.putAll(bundleA);
            }
        } else {
            iB = i2;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putAll(bundle);
        }
        if (iB == 0 && nVar != null && nVar.e() != -1) {
            r(nVar.e(), nVar.f());
            return;
        }
        if (iB == 0) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
        }
        i iVarC2 = c(iB);
        if (iVarC2 != null) {
            o(iVarC2, bundle2, nVar, aVar);
            return;
        }
        String strH = i.h(this.f1632a, iB);
        if (cVarE == null) {
            throw new IllegalArgumentException("Navigation action/destination " + strH + " cannot be found from the current destination " + iVarC);
        }
        throw new IllegalArgumentException("Navigation destination " + strH + " referenced from action " + i.h(this.f1632a, i2) + " cannot be found from the current destination " + iVarC);
    }

    public boolean q() {
        if (this.f1639h.isEmpty()) {
            return false;
        }
        return r(f().i(), true);
    }

    public boolean r(int i2, boolean z) {
        return s(i2, z) && a();
    }

    boolean s(int i2, boolean z) {
        boolean z2;
        boolean z3 = false;
        if (this.f1639h.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<e> itDescendingIterator = this.f1639h.descendingIterator();
        while (true) {
            if (!itDescendingIterator.hasNext()) {
                z2 = false;
                break;
            }
            i iVarC = itDescendingIterator.next().c();
            q qVarE = this.k.e(iVarC.j());
            if (z || iVarC.i() != i2) {
                arrayList.add(qVarE);
            }
            if (iVarC.i() == i2) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            Log.i("NavController", "Ignoring popBackStack to destination " + i.h(this.f1632a, i2) + " as it was not found on the current back stack");
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext() && ((q) it.next()).e()) {
            e eVarRemoveLast = this.f1639h.removeLast();
            if (eVarRemoveLast.a().b().a(d.b.CREATED)) {
                eVarRemoveLast.l(d.b.DESTROYED);
            }
            f fVar = this.j;
            if (fVar != null) {
                fVar.e(eVarRemoveLast.f1663g);
            }
            z3 = true;
        }
        B();
        return z3;
    }

    public void t(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(this.f1632a.getClassLoader());
        this.f1636e = bundle.getBundle("android-support-nav:controller:navigatorState");
        this.f1637f = bundle.getParcelableArray("android-support-nav:controller:backStack");
        this.f1638g = bundle.getBoolean("android-support-nav:controller:deepLinkHandled");
    }

    public Bundle u() {
        Bundle bundle;
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry<String, q<? extends i>> entry : this.k.f().entrySet()) {
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
        if (!this.f1639h.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[this.f1639h.size()];
            int i2 = 0;
            Iterator<e> it = this.f1639h.iterator();
            while (it.hasNext()) {
                parcelableArr[i2] = new NavBackStackEntryState(it.next());
                i2++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (this.f1638g) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", this.f1638g);
        }
        return bundle;
    }

    public void v(int i2) {
        w(i2, null);
    }

    public void w(int i2, Bundle bundle) {
        x(h().c(i2), bundle);
    }

    public void x(j jVar, Bundle bundle) {
        j jVar2 = this.f1635d;
        if (jVar2 != null) {
            s(jVar2.i(), true);
        }
        this.f1635d = jVar;
        p(bundle);
    }

    void y(androidx.lifecycle.g gVar) {
        if (gVar == this.f1640i) {
            return;
        }
        this.f1640i = gVar;
        gVar.a().a(this.m);
    }

    void z(OnBackPressedDispatcher onBackPressedDispatcher) {
        if (this.f1640i == null) {
            throw new IllegalStateException("You must call setLifecycleOwner() before calling setOnBackPressedDispatcher()");
        }
        this.n.d();
        onBackPressedDispatcher.a(this.f1640i, this.n);
        this.f1640i.a().c(this.m);
        this.f1640i.a().a(this.m);
    }
}
