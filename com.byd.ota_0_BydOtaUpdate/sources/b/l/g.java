package b.l;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Lifecycle {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<f> f1972d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.c.a.b.a<e, b> f1970b = new b.c.a.b.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1973e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1974f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1975g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Lifecycle.State> f1976h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Lifecycle.State f1971c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1977a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1978b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f1978b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1978b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1978b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1978b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1978b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f1977a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1977a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1977a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1977a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1977a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1977a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1977a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Lifecycle.State f1979a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f1980b;

        public b(e eVar, Lifecycle.State state) {
            this.f1980b = i.f(eVar);
            this.f1979a = state;
        }

        public void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateH = g.h(event);
            this.f1979a = g.l(this.f1979a, stateH);
            this.f1980b.d(fVar, event);
            this.f1979a = stateH;
        }
    }

    public g(f fVar) {
        this.f1972d = new WeakReference<>(fVar);
    }

    public static Lifecycle.Event f(Lifecycle.State state) {
        int i2 = a.f1978b[state.ordinal()];
        if (i2 == 1) {
            throw new IllegalArgumentException();
        }
        if (i2 == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i2 == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i2 == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i2 == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    public static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.f1977a[event.ordinal()]) {
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
        int i2 = a.f1978b[state.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i2 == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i2 == 4) {
                throw new IllegalArgumentException();
            }
            if (i2 != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(e eVar) {
        f fVar;
        Lifecycle.State state = this.f1971c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(eVar, state2);
        if (this.f1970b.g(eVar, bVar) == null && (fVar = this.f1972d.get()) != null) {
            boolean z = this.f1973e != 0 || this.f1974f;
            Lifecycle.State stateE = e(eVar);
            this.f1973e++;
            while (bVar.f1979a.compareTo(stateE) < 0 && this.f1970b.contains(eVar)) {
                o(bVar.f1979a);
                bVar.a(fVar, r(bVar.f1979a));
                n();
                stateE = e(eVar);
            }
            if (!z) {
                q();
            }
            this.f1973e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f1971c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        this.f1970b.h(eVar);
    }

    public final void d(f fVar) {
        Iterator<Map.Entry<e, b>> itA = this.f1970b.a();
        while (itA.hasNext() && !this.f1975g) {
            Map.Entry<e, b> next = itA.next();
            b value = next.getValue();
            while (value.f1979a.compareTo(this.f1971c) > 0 && !this.f1975g && this.f1970b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.f1979a);
                o(h(eventF));
                value.a(fVar, eventF);
                n();
            }
        }
    }

    public final Lifecycle.State e(e eVar) {
        Map.Entry<e, b> entryI = this.f1970b.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f1979a : null;
        if (!this.f1976h.isEmpty()) {
            state = this.f1976h.get(r0.size() - 1);
        }
        return l(l(this.f1971c, state2), state);
    }

    public final void g(f fVar) {
        b.c.a.b.b<e, b>.d dVarD = this.f1970b.d();
        while (dVarD.hasNext() && !this.f1975g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f1979a.compareTo(this.f1971c) < 0 && !this.f1975g && this.f1970b.contains((e) next.getKey())) {
                o(bVar.f1979a);
                bVar.a(fVar, r(bVar.f1979a));
                n();
            }
        }
    }

    public void i(Lifecycle.Event event) {
        m(h(event));
    }

    public final boolean j() {
        if (this.f1970b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f1970b.b().getValue().f1979a;
        Lifecycle.State state2 = this.f1970b.e().getValue().f1979a;
        return state == state2 && this.f1971c == state2;
    }

    @Deprecated
    public void k(Lifecycle.State state) {
        p(state);
    }

    public final void m(Lifecycle.State state) {
        if (this.f1971c == state) {
            return;
        }
        this.f1971c = state;
        if (this.f1974f || this.f1973e != 0) {
            this.f1975g = true;
            return;
        }
        this.f1974f = true;
        q();
        this.f1974f = false;
    }

    public final void n() {
        this.f1976h.remove(r0.size() - 1);
    }

    public final void o(Lifecycle.State state) {
        this.f1976h.add(state);
    }

    public void p(Lifecycle.State state) {
        m(state);
    }

    public final void q() {
        f fVar = this.f1972d.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.f1975g = false;
            if (this.f1971c.compareTo(this.f1970b.b().getValue().f1979a) < 0) {
                d(fVar);
            }
            Map.Entry<e, b> entryE = this.f1970b.e();
            if (!this.f1975g && entryE != null && this.f1971c.compareTo(entryE.getValue().f1979a) > 0) {
                g(fVar);
            }
        }
        this.f1975g = false;
    }
}
