package b.n;

import android.annotation.SuppressLint;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends Lifecycle {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.c.a.b.a<f, a> f2944b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Lifecycle.State f2945c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<g> f2946d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2947e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2948f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2949g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Lifecycle.State> f2950h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f2951i;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Lifecycle.State f2952a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f2953b;

        public a(f fVar, Lifecycle.State state) {
            this.f2953b = j.f(fVar);
            this.f2952a = state;
        }

        public void a(g gVar, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            this.f2952a = h.k(this.f2952a, targetState);
            this.f2953b.d(gVar, event);
            this.f2952a = targetState;
        }
    }

    public h(g gVar) {
        this(gVar, true);
    }

    public static Lifecycle.State k(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(f fVar) {
        g gVar;
        f("addObserver");
        Lifecycle.State state = this.f2945c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        a aVar = new a(fVar, state2);
        if (this.f2944b.g(fVar, aVar) == null && (gVar = this.f2946d.get()) != null) {
            boolean z = this.f2947e != 0 || this.f2948f;
            Lifecycle.State stateE = e(fVar);
            this.f2947e++;
            while (aVar.f2952a.compareTo(stateE) < 0 && this.f2944b.contains(fVar)) {
                n(aVar.f2952a);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.upFrom(aVar.f2952a);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f2952a);
                }
                aVar.a(gVar, eventUpFrom);
                m();
                stateE = e(fVar);
            }
            if (!z) {
                p();
            }
            this.f2947e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f2945c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(f fVar) {
        f("removeObserver");
        this.f2944b.h(fVar);
    }

    public final void d(g gVar) {
        Iterator<Map.Entry<f, a>> itA = this.f2944b.a();
        while (itA.hasNext() && !this.f2949g) {
            Map.Entry<f, a> next = itA.next();
            a value = next.getValue();
            while (value.f2952a.compareTo(this.f2945c) > 0 && !this.f2949g && this.f2944b.contains(next.getKey())) {
                Lifecycle.Event eventDownFrom = Lifecycle.Event.downFrom(value.f2952a);
                if (eventDownFrom == null) {
                    throw new IllegalStateException("no event down from " + value.f2952a);
                }
                n(eventDownFrom.getTargetState());
                value.a(gVar, eventDownFrom);
                m();
            }
        }
    }

    public final Lifecycle.State e(f fVar) {
        Map.Entry<f, a> entryI = this.f2944b.i(fVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f2952a : null;
        if (!this.f2950h.isEmpty()) {
            state = this.f2950h.get(r0.size() - 1);
        }
        return k(k(this.f2945c, state2), state);
    }

    @SuppressLint({"RestrictedApi"})
    public final void f(String str) {
        if (!this.f2951i || b.c.a.a.a.d().b()) {
            return;
        }
        throw new IllegalStateException("Method " + str + " must be called on the main thread");
    }

    public final void g(g gVar) {
        b.c.a.b.b<f, a>.d dVarD = this.f2944b.d();
        while (dVarD.hasNext() && !this.f2949g) {
            Map.Entry next = dVarD.next();
            a aVar = (a) next.getValue();
            while (aVar.f2952a.compareTo(this.f2945c) < 0 && !this.f2949g && this.f2944b.contains((f) next.getKey())) {
                n(aVar.f2952a);
                Lifecycle.Event eventUpFrom = Lifecycle.Event.upFrom(aVar.f2952a);
                if (eventUpFrom == null) {
                    throw new IllegalStateException("no event up from " + aVar.f2952a);
                }
                aVar.a(gVar, eventUpFrom);
                m();
            }
        }
    }

    public void h(Lifecycle.Event event) {
        f("handleLifecycleEvent");
        l(event.getTargetState());
    }

    public final boolean i() {
        if (this.f2944b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f2944b.b().getValue().f2952a;
        Lifecycle.State state2 = this.f2944b.e().getValue().f2952a;
        return state == state2 && this.f2945c == state2;
    }

    @Deprecated
    public void j(Lifecycle.State state) {
        f("markState");
        o(state);
    }

    public final void l(Lifecycle.State state) {
        if (this.f2945c == state) {
            return;
        }
        this.f2945c = state;
        if (this.f2948f || this.f2947e != 0) {
            this.f2949g = true;
            return;
        }
        this.f2948f = true;
        p();
        this.f2948f = false;
    }

    public final void m() {
        this.f2950h.remove(r0.size() - 1);
    }

    public final void n(Lifecycle.State state) {
        this.f2950h.add(state);
    }

    public void o(Lifecycle.State state) {
        f("setCurrentState");
        l(state);
    }

    public final void p() {
        g gVar = this.f2946d.get();
        if (gVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!i()) {
            this.f2949g = false;
            if (this.f2945c.compareTo(this.f2944b.b().getValue().f2952a) < 0) {
                d(gVar);
            }
            Map.Entry<f, a> entryE = this.f2944b.e();
            if (!this.f2949g && entryE != null && this.f2945c.compareTo(entryE.getValue().f2952a) > 0) {
                g(gVar);
            }
        }
        this.f2949g = false;
    }

    public h(g gVar, boolean z) {
        this.f2944b = new b.c.a.b.a<>();
        this.f2947e = 0;
        this.f2948f = false;
        this.f2949g = false;
        this.f2950h = new ArrayList<>();
        this.f2946d = new WeakReference<>(gVar);
        this.f2945c = Lifecycle.State.INITIALIZED;
        this.f2951i = z;
    }
}
