package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import d0.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SpecialEffectsController.java */
/* JADX INFO: loaded from: classes.dex */
abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ViewGroup f2471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<e> f2472b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<e> f2473c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f2474d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f2475e = false;

    /* JADX INFO: compiled from: SpecialEffectsController.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ d f2476e;

        a(d dVar) {
            this.f2476e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (x.this.f2472b.contains(this.f2476e)) {
                this.f2476e.e().a(this.f2476e.f().M);
            }
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ d f2478e;

        b(d dVar) {
            this.f2478e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            x.this.f2472b.remove(this.f2478e);
            x.this.f2473c.remove(this.f2478e);
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.java */
    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2480a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2481b;

        static {
            int[] iArr = new int[e.b.values().length];
            f2481b = iArr;
            try {
                iArr[e.b.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2481b[e.b.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2481b[e.b.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[e.c.values().length];
            f2480a = iArr2;
            try {
                iArr2[e.c.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2480a[e.c.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2480a[e.c.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2480a[e.c.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.java */
    private static class d extends e {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final p f2482h;

        d(e.c cVar, e.b bVar, p pVar, d0.b bVar2) {
            super(cVar, bVar, pVar.k(), bVar2);
            this.f2482h = pVar;
        }

        @Override // androidx.fragment.app.x.e
        public void c() {
            super.c();
            this.f2482h.m();
        }

        @Override // androidx.fragment.app.x.e
        void l() {
            if (g() == e.b.ADDING) {
                Fragment fragmentK = this.f2482h.k();
                View viewFindFocus = fragmentK.M.findFocus();
                if (viewFindFocus != null) {
                    fragmentK.D1(viewFindFocus);
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + fragmentK);
                    }
                }
                View viewV1 = f().v1();
                if (viewV1.getParent() == null) {
                    this.f2482h.b();
                    viewV1.setAlpha(0.0f);
                }
                if (viewV1.getAlpha() == 0.0f && viewV1.getVisibility() == 0) {
                    viewV1.setVisibility(4);
                }
                viewV1.setAlpha(fragmentK.L());
            }
        }
    }

    /* JADX INFO: compiled from: SpecialEffectsController.java */
    static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private c f2483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private b f2484b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Fragment f2485c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<Runnable> f2486d = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final HashSet<d0.b> f2487e = new HashSet<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2488f = false;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f2489g = false;

        /* JADX INFO: compiled from: SpecialEffectsController.java */
        class a implements b.a {
            a() {
            }

            @Override // d0.b.a
            public void a() {
                e.this.b();
            }
        }

        /* JADX INFO: compiled from: SpecialEffectsController.java */
        enum b {
            NONE,
            ADDING,
            REMOVING
        }

        /* JADX INFO: compiled from: SpecialEffectsController.java */
        enum c {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            static c b(int i7) {
                if (i7 == 0) {
                    return VISIBLE;
                }
                if (i7 == 4) {
                    return INVISIBLE;
                }
                if (i7 == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i7);
            }

            static c c(View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : b(view.getVisibility());
            }

            void a(View view) {
                int i7 = c.f2480a[ordinal()];
                if (i7 == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.F0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i7 == 2) {
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                    return;
                }
                if (i7 == 3) {
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                    return;
                }
                if (i7 != 4) {
                    return;
                }
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                }
                view.setVisibility(4);
            }
        }

        e(c cVar, b bVar, Fragment fragment, d0.b bVar2) {
            this.f2483a = cVar;
            this.f2484b = bVar;
            this.f2485c = fragment;
            bVar2.c(new a());
        }

        final void a(Runnable runnable) {
            this.f2486d.add(runnable);
        }

        final void b() {
            if (h()) {
                return;
            }
            this.f2488f = true;
            if (this.f2487e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.f2487e).iterator();
            while (it.hasNext()) {
                ((d0.b) it.next()).a();
            }
        }

        public void c() {
            if (this.f2489g) {
                return;
            }
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.f2489g = true;
            Iterator<Runnable> it = this.f2486d.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        public final void d(d0.b bVar) {
            if (this.f2487e.remove(bVar) && this.f2487e.isEmpty()) {
                c();
            }
        }

        public c e() {
            return this.f2483a;
        }

        public final Fragment f() {
            return this.f2485c;
        }

        b g() {
            return this.f2484b;
        }

        final boolean h() {
            return this.f2488f;
        }

        final boolean i() {
            return this.f2489g;
        }

        public final void j(d0.b bVar) {
            l();
            this.f2487e.add(bVar);
        }

        final void k(c cVar, b bVar) {
            int i7 = c.f2481b[bVar.ordinal()];
            if (i7 == 1) {
                if (this.f2483a == c.REMOVED) {
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f2485c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.f2484b + " to ADDING.");
                    }
                    this.f2483a = c.VISIBLE;
                    this.f2484b = b.ADDING;
                    return;
                }
                return;
            }
            if (i7 == 2) {
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f2485c + " mFinalState = " + this.f2483a + " -> REMOVED. mLifecycleImpact  = " + this.f2484b + " to REMOVING.");
                }
                this.f2483a = c.REMOVED;
                this.f2484b = b.REMOVING;
                return;
            }
            if (i7 == 3 && this.f2483a != c.REMOVED) {
                if (FragmentManager.F0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f2485c + " mFinalState = " + this.f2483a + " -> " + cVar + ". ");
                }
                this.f2483a = cVar;
            }
        }

        void l() {
        }

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.f2483a + "} {mLifecycleImpact = " + this.f2484b + "} {mFragment = " + this.f2485c + "}";
        }
    }

    x(ViewGroup viewGroup) {
        this.f2471a = viewGroup;
    }

    private void a(e.c cVar, e.b bVar, p pVar) {
        synchronized (this.f2472b) {
            d0.b bVar2 = new d0.b();
            e eVarH = h(pVar.k());
            if (eVarH != null) {
                eVarH.k(cVar, bVar);
                return;
            }
            d dVar = new d(cVar, bVar, pVar, bVar2);
            this.f2472b.add(dVar);
            dVar.a(new a(dVar));
            dVar.a(new b(dVar));
        }
    }

    private e h(Fragment fragment) {
        for (e eVar : this.f2472b) {
            if (eVar.f().equals(fragment) && !eVar.h()) {
                return eVar;
            }
        }
        return null;
    }

    private e i(Fragment fragment) {
        for (e eVar : this.f2473c) {
            if (eVar.f().equals(fragment) && !eVar.h()) {
                return eVar;
            }
        }
        return null;
    }

    static x n(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return o(viewGroup, fragmentManager.y0());
    }

    static x o(ViewGroup viewGroup, y yVar) {
        int i7 = q0.b.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i7);
        if (tag instanceof x) {
            return (x) tag;
        }
        x xVarA = yVar.a(viewGroup);
        viewGroup.setTag(i7, xVarA);
        return xVarA;
    }

    private void q() {
        for (e eVar : this.f2472b) {
            if (eVar.g() == e.b.ADDING) {
                eVar.k(e.c.b(eVar.f().v1().getVisibility()), e.b.NONE);
            }
        }
    }

    void b(e.c cVar, p pVar) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + pVar.k());
        }
        a(cVar, e.b.ADDING, pVar);
    }

    void c(p pVar) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + pVar.k());
        }
        a(e.c.GONE, e.b.NONE, pVar);
    }

    void d(p pVar) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + pVar.k());
        }
        a(e.c.REMOVED, e.b.REMOVING, pVar);
    }

    void e(p pVar) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + pVar.k());
        }
        a(e.c.VISIBLE, e.b.NONE, pVar);
    }

    abstract void f(List<e> list, boolean z6);

    void g() {
        if (this.f2475e) {
            return;
        }
        if (!i0.u.O(this.f2471a)) {
            j();
            this.f2474d = false;
            return;
        }
        synchronized (this.f2472b) {
            if (!this.f2472b.isEmpty()) {
                ArrayList<e> arrayList = new ArrayList(this.f2473c);
                this.f2473c.clear();
                for (e eVar : arrayList) {
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + eVar);
                    }
                    eVar.b();
                    if (!eVar.i()) {
                        this.f2473c.add(eVar);
                    }
                }
                q();
                ArrayList arrayList2 = new ArrayList(this.f2472b);
                this.f2472b.clear();
                this.f2473c.addAll(arrayList2);
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).l();
                }
                f(arrayList2, this.f2474d);
                this.f2474d = false;
            }
        }
    }

    void j() {
        boolean zO = i0.u.O(this.f2471a);
        synchronized (this.f2472b) {
            q();
            Iterator<e> it = this.f2472b.iterator();
            while (it.hasNext()) {
                it.next().l();
            }
            for (e eVar : new ArrayList(this.f2473c)) {
                if (FragmentManager.F0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    sb.append(zO ? "" : "Container " + this.f2471a + " is not attached to window. ");
                    sb.append("Cancelling running operation ");
                    sb.append(eVar);
                    Log.v("FragmentManager", sb.toString());
                }
                eVar.b();
            }
            for (e eVar2 : new ArrayList(this.f2472b)) {
                if (FragmentManager.F0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    sb2.append(zO ? "" : "Container " + this.f2471a + " is not attached to window. ");
                    sb2.append("Cancelling pending operation ");
                    sb2.append(eVar2);
                    Log.v("FragmentManager", sb2.toString());
                }
                eVar2.b();
            }
        }
    }

    void k() {
        if (this.f2475e) {
            this.f2475e = false;
            g();
        }
    }

    e.b l(p pVar) {
        e eVarH = h(pVar.k());
        if (eVarH != null) {
            return eVarH.g();
        }
        e eVarI = i(pVar.k());
        if (eVarI != null) {
            return eVarI.g();
        }
        return null;
    }

    public ViewGroup m() {
        return this.f2471a;
    }

    void p() {
        synchronized (this.f2472b) {
            q();
            this.f2475e = false;
            int size = this.f2472b.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                e eVar = this.f2472b.get(size);
                e.c cVarC = e.c.c(eVar.f().M);
                e.c cVarE = eVar.e();
                e.c cVar = e.c.VISIBLE;
                if (cVarE == cVar && cVarC != cVar) {
                    this.f2475e = eVar.f().h0();
                    break;
                }
                size--;
            }
        }
    }

    void r(boolean z6) {
        this.f2474d = z6;
    }
}
