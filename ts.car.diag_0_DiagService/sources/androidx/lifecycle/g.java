package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g extends Lifecycle {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final WeakReference<f> f772c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.arch.core.b.a<e, a> f770a = new androidx.arch.core.b.a<>();
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<Lifecycle.State> g = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Lifecycle.State f771b = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Lifecycle.State f773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d f774b;

        a(e eVar, Lifecycle.State state) {
            this.f774b = i.f(eVar);
            this.f773a = state;
        }

        void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            this.f773a = g.j(this.f773a, targetState);
            this.f774b.f(fVar, event);
            this.f773a = targetState;
        }
    }

    public g(@NonNull f fVar) {
        this.f772c = new WeakReference<>(fVar);
    }

    private void d(f fVar) {
        Iterator<Map.Entry<e, a>> itA = this.f770a.a();
        while (itA.hasNext() && !this.f) {
            Map.Entry<e, a> next = itA.next();
            a value = next.getValue();
            while (value.f773a.compareTo(this.f771b) > 0 && !this.f && this.f770a.contains(next.getKey())) {
                Lifecycle.Event eventDownFrom = Lifecycle.Event.downFrom(value.f773a);
                if (eventDownFrom == null) {
                    throw new IllegalStateException("no event down from " + value.f773a);
                }
                m(eventDownFrom.getTargetState());
                value.a(fVar, eventDownFrom);
                l();
            }
        }
    }

    private Lifecycle.State e(e eVar) {
        Map.Entry<e, a> entryI = this.f770a.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f773a : null;
        if (!this.g.isEmpty()) {
            state = this.g.get(r0.size() - 1);
        }
        return j(j(this.f771b, state2), state);
    }

    private void f(f fVar) {
        androidx.arch.core.b.b<e, a>.d dVarD = this.f770a.d();
        while (dVarD.hasNext() && !this.f) {
            Map.Entry next = dVarD.next();
            a aVar = (a) next.getValue();
            while (aVar.f773a.compareTo(this.f771b) < 0 && !this.f && this.f770a.contains((e) next.getKey())) {
                m(aVar.f773a);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.upFrom(aVar.f773a);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f773a);
                }
                aVar.a(fVar, eventUpFrom);
                l();
            }
        }
    }

    private boolean h() {
        if (this.f770a.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f770a.b().getValue().f773a;
        Lifecycle.State state2 = this.f770a.e().getValue().f773a;
        return state == state2 && this.f771b == state2;
    }

    static Lifecycle.State j(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void k(Lifecycle.State state) {
        if (this.f771b == state) {
            return;
        }
        this.f771b = state;
        if (this.e || this.d != 0) {
            this.f = true;
            return;
        }
        this.e = true;
        o();
        this.e = false;
    }

    private void l() {
        this.g.remove(r1.size() - 1);
    }

    private void m(Lifecycle.State state) {
        this.g.add(state);
    }

    private void o() {
        f fVar = this.f772c.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!h()) {
            this.f = false;
            if (this.f771b.compareTo(this.f770a.b().getValue().f773a) < 0) {
                d(fVar);
            }
            Map.Entry<e, a> entryE = this.f770a.e();
            if (!this.f && entryE != null && this.f771b.compareTo(entryE.getValue().f773a) > 0) {
                f(fVar);
            }
        }
        this.f = false;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(@NonNull e eVar) {
        f fVar;
        Lifecycle.State state = this.f771b;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        a aVar = new a(eVar, state2);
        if (this.f770a.g(eVar, aVar) == null && (fVar = this.f772c.get()) != null) {
            boolean z = this.d != 0 || this.e;
            Lifecycle.State stateE = e(eVar);
            this.d++;
            while (aVar.f773a.compareTo(stateE) < 0 && this.f770a.contains(eVar)) {
                m(aVar.f773a);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.upFrom(aVar.f773a);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f773a);
                }
                aVar.a(fVar, eventUpFrom);
                l();
                stateE = e(eVar);
            }
            if (!z) {
                o();
            }
            this.d--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    @NonNull
    public Lifecycle.State b() {
        return this.f771b;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(@NonNull e eVar) {
        this.f770a.h(eVar);
    }

    public void g(@NonNull Lifecycle.Event event) {
        k(event.getTargetState());
    }

    @MainThread
    @Deprecated
    public void i(@NonNull Lifecycle.State state) {
        n(state);
    }

    @MainThread
    public void n(@NonNull Lifecycle.State state) {
        k(state);
    }
}
