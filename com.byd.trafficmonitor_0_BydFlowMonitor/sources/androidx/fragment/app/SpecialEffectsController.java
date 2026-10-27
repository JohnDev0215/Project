package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import b.h.i.a;
import b.h.m.t;
import b.l.d.n;
import b.l.d.v;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class SpecialEffectsController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroup f985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<Operation> f986b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<Operation> f987c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f988d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f989e = false;

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f1000b;

        public a(d dVar) {
            this.f1000b = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SpecialEffectsController.this.f986b.contains(this.f1000b)) {
                this.f1000b.e().applyState(this.f1000b.f().mView);
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d f1002b;

        public b(d dVar) {
            this.f1002b = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            SpecialEffectsController.this.f986b.remove(this.f1002b);
            SpecialEffectsController.this.f987c.remove(this.f1002b);
        }
    }

    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1004a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1005b;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            f1005b = iArr;
            try {
                iArr[Operation.LifecycleImpact.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1005b[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1005b[Operation.LifecycleImpact.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Operation.State.values().length];
            f1004a = iArr2;
            try {
                iArr2[Operation.State.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1004a[Operation.State.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1004a[Operation.State.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1004a[Operation.State.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static class d extends Operation {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final n f1006h;

        public d(Operation.State state, Operation.LifecycleImpact lifecycleImpact, n nVar, b.h.i.a aVar) {
            super(state, lifecycleImpact, nVar.k(), aVar);
            this.f1006h = nVar;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void c() {
            super.c();
            this.f1006h.m();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void l() {
            if (g() == Operation.LifecycleImpact.ADDING) {
                Fragment fragmentK = this.f1006h.k();
                View viewFindFocus = fragmentK.mView.findFocus();
                if (viewFindFocus != null) {
                    fragmentK.setFocusedView(viewFindFocus);
                    if (FragmentManager.G0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + fragmentK);
                    }
                }
                View viewRequireView = f().requireView();
                if (viewRequireView.getParent() == null) {
                    this.f1006h.b();
                    viewRequireView.setAlpha(0.0f);
                }
                if (viewRequireView.getAlpha() == 0.0f && viewRequireView.getVisibility() == 0) {
                    viewRequireView.setVisibility(4);
                }
                viewRequireView.setAlpha(fragmentK.getPostOnViewCreatedAlpha());
            }
        }
    }

    public SpecialEffectsController(ViewGroup viewGroup) {
        this.f985a = viewGroup;
    }

    public static SpecialEffectsController n(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return o(viewGroup, fragmentManager.z0());
    }

    public static SpecialEffectsController o(ViewGroup viewGroup, v vVar) {
        int i2 = b.l.b.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i2);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController specialEffectsControllerA = vVar.a(viewGroup);
        viewGroup.setTag(i2, specialEffectsControllerA);
        return specialEffectsControllerA;
    }

    public final void a(Operation.State state, Operation.LifecycleImpact lifecycleImpact, n nVar) {
        synchronized (this.f986b) {
            b.h.i.a aVar = new b.h.i.a();
            Operation operationH = h(nVar.k());
            if (operationH != null) {
                operationH.k(state, lifecycleImpact);
                return;
            }
            d dVar = new d(state, lifecycleImpact, nVar, aVar);
            this.f986b.add(dVar);
            dVar.a(new a(dVar));
            dVar.a(new b(dVar));
        }
    }

    public void b(Operation.State state, n nVar) {
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + nVar.k());
        }
        a(state, Operation.LifecycleImpact.ADDING, nVar);
    }

    public void c(n nVar) {
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + nVar.k());
        }
        a(Operation.State.GONE, Operation.LifecycleImpact.NONE, nVar);
    }

    public void d(n nVar) {
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + nVar.k());
        }
        a(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, nVar);
    }

    public void e(n nVar) {
        if (FragmentManager.G0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + nVar.k());
        }
        a(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, nVar);
    }

    public abstract void f(List<Operation> list, boolean z);

    public void g() {
        if (this.f989e) {
            return;
        }
        if (!t.O(this.f985a)) {
            j();
            this.f988d = false;
            return;
        }
        synchronized (this.f986b) {
            if (!this.f986b.isEmpty()) {
                ArrayList<Operation> arrayList = new ArrayList(this.f987c);
                this.f987c.clear();
                for (Operation operation : arrayList) {
                    if (FragmentManager.G0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + operation);
                    }
                    operation.b();
                    if (!operation.i()) {
                        this.f987c.add(operation);
                    }
                }
                q();
                ArrayList arrayList2 = new ArrayList(this.f986b);
                this.f986b.clear();
                this.f987c.addAll(arrayList2);
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Operation) it.next()).l();
                }
                f(arrayList2, this.f988d);
                this.f988d = false;
            }
        }
    }

    public final Operation h(Fragment fragment) {
        for (Operation operation : this.f986b) {
            if (operation.f().equals(fragment) && !operation.h()) {
                return operation;
            }
        }
        return null;
    }

    public final Operation i(Fragment fragment) {
        for (Operation operation : this.f987c) {
            if (operation.f().equals(fragment) && !operation.h()) {
                return operation;
            }
        }
        return null;
    }

    public void j() {
        boolean zO = t.O(this.f985a);
        synchronized (this.f986b) {
            q();
            Iterator<Operation> it = this.f986b.iterator();
            while (it.hasNext()) {
                it.next().l();
            }
            for (Operation operation : new ArrayList(this.f987c)) {
                if (FragmentManager.G0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    sb.append(zO ? "" : "Container " + this.f985a + " is not attached to window. ");
                    sb.append("Cancelling running operation ");
                    sb.append(operation);
                    Log.v("FragmentManager", sb.toString());
                }
                operation.b();
            }
            for (Operation operation2 : new ArrayList(this.f986b)) {
                if (FragmentManager.G0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    sb2.append(zO ? "" : "Container " + this.f985a + " is not attached to window. ");
                    sb2.append("Cancelling pending operation ");
                    sb2.append(operation2);
                    Log.v("FragmentManager", sb2.toString());
                }
                operation2.b();
            }
        }
    }

    public void k() {
        if (this.f989e) {
            this.f989e = false;
            g();
        }
    }

    public Operation.LifecycleImpact l(n nVar) {
        Operation operationH = h(nVar.k());
        if (operationH != null) {
            return operationH.g();
        }
        Operation operationI = i(nVar.k());
        if (operationI != null) {
            return operationI.g();
        }
        return null;
    }

    public ViewGroup m() {
        return this.f985a;
    }

    public void p() {
        synchronized (this.f986b) {
            q();
            this.f989e = false;
            int size = this.f986b.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Operation operation = this.f986b.get(size);
                Operation.State stateFrom = Operation.State.from(operation.f().mView);
                Operation.State stateE = operation.e();
                Operation.State state = Operation.State.VISIBLE;
                if (stateE == state && stateFrom != state) {
                    this.f989e = operation.f().isPostponed();
                    break;
                }
                size--;
            }
        }
    }

    public final void q() {
        for (Operation operation : this.f986b) {
            if (operation.g() == Operation.LifecycleImpact.ADDING) {
                operation.k(Operation.State.from(operation.f().requireView().getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    public void r(boolean z) {
        this.f988d = z;
    }

    public static class Operation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public State f990a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public LifecycleImpact f991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Fragment f992c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<Runnable> f993d = new ArrayList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final HashSet<b.h.i.a> f994e = new HashSet<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f995f = false;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f996g = false;

        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        public class a implements a.InterfaceC0036a {
            public a() {
            }

            @Override // b.h.i.a.InterfaceC0036a
            public void a() {
                Operation.this.b();
            }
        }

        public Operation(State state, LifecycleImpact lifecycleImpact, Fragment fragment, b.h.i.a aVar) {
            this.f990a = state;
            this.f991b = lifecycleImpact;
            this.f992c = fragment;
            aVar.c(new a());
        }

        public final void a(Runnable runnable) {
            this.f993d.add(runnable);
        }

        public final void b() {
            if (h()) {
                return;
            }
            this.f995f = true;
            if (this.f994e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.f994e).iterator();
            while (it.hasNext()) {
                ((b.h.i.a) it.next()).a();
            }
        }

        public void c() {
            if (this.f996g) {
                return;
            }
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.f996g = true;
            Iterator<Runnable> it = this.f993d.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        public final void d(b.h.i.a aVar) {
            if (this.f994e.remove(aVar) && this.f994e.isEmpty()) {
                c();
            }
        }

        public State e() {
            return this.f990a;
        }

        public final Fragment f() {
            return this.f992c;
        }

        public LifecycleImpact g() {
            return this.f991b;
        }

        public final boolean h() {
            return this.f995f;
        }

        public final boolean i() {
            return this.f996g;
        }

        public final void j(b.h.i.a aVar) {
            l();
            this.f994e.add(aVar);
        }

        public final void k(State state, LifecycleImpact lifecycleImpact) {
            int i2 = c.f1005b[lifecycleImpact.ordinal()];
            if (i2 == 1) {
                if (this.f990a == State.REMOVED) {
                    if (FragmentManager.G0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f992c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.f991b + " to ADDING.");
                    }
                    this.f990a = State.VISIBLE;
                    this.f991b = LifecycleImpact.ADDING;
                    return;
                }
                return;
            }
            if (i2 == 2) {
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f992c + " mFinalState = " + this.f990a + " -> REMOVED. mLifecycleImpact  = " + this.f991b + " to REMOVING.");
                }
                this.f990a = State.REMOVED;
                this.f991b = LifecycleImpact.REMOVING;
                return;
            }
            if (i2 == 3 && this.f990a != State.REMOVED) {
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f992c + " mFinalState = " + this.f990a + " -> " + state + ". ");
                }
                this.f990a = state;
            }
        }

        public void l() {
        }

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.f990a + "} {mLifecycleImpact = " + this.f991b + "} {mFragment = " + this.f992c + "}";
        }

        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            public static State from(View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : from(view.getVisibility());
            }

            public void applyState(View view) {
                int i2 = c.f1004a[ordinal()];
                if (i2 == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.G0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    if (FragmentManager.G0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                    return;
                }
                if (i2 == 3) {
                    if (FragmentManager.G0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                    return;
                }
                if (i2 != 4) {
                    return;
                }
                if (FragmentManager.G0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                }
                view.setVisibility(4);
            }

            public static State from(int i2) {
                if (i2 == 0) {
                    return VISIBLE;
                }
                if (i2 == 4) {
                    return INVISIBLE;
                }
                if (i2 == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i2);
            }
        }
    }
}
