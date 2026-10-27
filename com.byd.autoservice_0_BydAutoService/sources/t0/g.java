package t0;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Lifecycle {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<f> f4999d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q.a<e, b> f4997b = new q.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5000e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5001f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5002g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Lifecycle.State> f5003h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Lifecycle.State f4998c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5004a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f5005b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f5005b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5005b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5005b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5005b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5005b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f5004a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5004a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5004a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5004a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5004a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5004a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f5004a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Lifecycle.State f5006a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f5007b;

        public b(e eVar, Lifecycle.State state) {
            this.f5007b = i.f(eVar);
            this.f5006a = state;
        }

        public void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateH = g.h(event);
            this.f5006a = g.l(this.f5006a, stateH);
            this.f5007b.d(fVar, event);
            this.f5006a = stateH;
        }
    }

    public g(f fVar) {
        this.f4999d = new WeakReference<>(fVar);
    }

    public static Lifecycle.Event f(Lifecycle.State state) {
        int i3 = a.f5005b[state.ordinal()];
        if (i3 == 1) {
            throw new IllegalArgumentException();
        }
        if (i3 == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i3 == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i3 == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i3 == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    public static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.f5004a[event.ordinal()]) {
            case 1:
            case 2:
                return Lifecycle.State.CREATED;
            case 3:
            case 4:
                return Lifecycle.State.STARTED;
            case 5:
                return Lifecycle.State.RESUMED;
            case 6:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    public static Lifecycle.State l(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    public static Lifecycle.Event r(Lifecycle.State state) {
        int i3 = a.f5005b[state.ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i3 == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i3 == 4) {
                throw new IllegalArgumentException();
            }
            if (i3 != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(e eVar) {
        f fVar;
        Lifecycle.State state = this.f4998c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(eVar, state2);
        if (this.f4997b.g(eVar, bVar) == null && (fVar = this.f4999d.get()) != null) {
            boolean z3 = this.f5000e != 0 || this.f5001f;
            Lifecycle.State stateE = e(eVar);
            this.f5000e++;
            while (bVar.f5006a.compareTo(stateE) < 0 && this.f4997b.contains(eVar)) {
                o(bVar.f5006a);
                bVar.a(fVar, r(bVar.f5006a));
                n();
                stateE = e(eVar);
            }
            if (!z3) {
                q();
            }
            this.f5000e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f4998c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        this.f4997b.h(eVar);
    }

    public final void d(f fVar) {
        Iterator<Map.Entry<e, b>> itA = this.f4997b.a();
        while (itA.hasNext() && !this.f5002g) {
            Map.Entry<e, b> next = itA.next();
            b value = next.getValue();
            while (value.f5006a.compareTo(this.f4998c) > 0 && !this.f5002g && this.f4997b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.f5006a);
                o(h(eventF));
                value.a(fVar, eventF);
                n();
            }
        }
    }

    public final Lifecycle.State e(e eVar) {
        Map.Entry<e, b> entryI = this.f4997b.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f5006a : null;
        if (!this.f5003h.isEmpty()) {
            state = this.f5003h.get(r0.size() - 1);
        }
        return l(l(this.f4998c, state2), state);
    }

    public final void g(f fVar) {
        q.b<e, b>.d dVarD = this.f4997b.d();
        while (dVarD.hasNext() && !this.f5002g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f5006a.compareTo(this.f4998c) < 0 && !this.f5002g && this.f4997b.contains((e) next.getKey())) {
                o(bVar.f5006a);
                bVar.a(fVar, r(bVar.f5006a));
                n();
            }
        }
    }

    public void i(Lifecycle.Event event) {
        m(h(event));
    }

    public final boolean j() {
        if (this.f4997b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f4997b.b().getValue().f5006a;
        Lifecycle.State state2 = this.f4997b.e().getValue().f5006a;
        return state == state2 && this.f4998c == state2;
    }

    @Deprecated
    public void k(Lifecycle.State state) {
        p(state);
    }

    public final void m(Lifecycle.State state) {
        if (this.f4998c == state) {
            return;
        }
        this.f4998c = state;
        if (this.f5001f || this.f5000e != 0) {
            this.f5002g = true;
            return;
        }
        this.f5001f = true;
        q();
        this.f5001f = false;
    }

    public final void n() {
        this.f5003h.remove(r0.size() - 1);
    }

    public final void o(Lifecycle.State state) {
        this.f5003h.add(state);
    }

    public void p(Lifecycle.State state) {
        m(state);
    }

    public final void q() {
        f fVar = this.f4999d.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.f5002g = false;
            if (this.f4998c.compareTo(this.f4997b.b().getValue().f5006a) < 0) {
                d(fVar);
            }
            Map.Entry<e, b> entryE = this.f4997b.e();
            if (!this.f5002g && entryE != null && this.f4998c.compareTo(entryE.getValue().f5006a) > 0) {
                g(fVar);
            }
        }
        this.f5002g = false;
    }
}
