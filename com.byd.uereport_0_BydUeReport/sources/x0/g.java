package x0;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Lifecycle {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference<f> f9057d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t.a<e, b> f9055b = new t.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9058e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9059f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f9060g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Lifecycle.State> f9061h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Lifecycle.State f9056c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9062a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f9063b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f9063b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9063b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9063b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9063b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9063b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f9062a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9062a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9062a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9062a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9062a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9062a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9062a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Lifecycle.State f9064a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f9065b;

        public b(e eVar, Lifecycle.State state) {
            this.f9065b = i.f(eVar);
            this.f9064a = state;
        }

        public void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateH = g.h(event);
            this.f9064a = g.l(this.f9064a, stateH);
            this.f9065b.onStateChanged(fVar, event);
            this.f9064a = stateH;
        }
    }

    public g(f fVar) {
        this.f9057d = new WeakReference<>(fVar);
    }

    public static Lifecycle.Event f(Lifecycle.State state) {
        int i8 = a.f9063b[state.ordinal()];
        if (i8 == 1) {
            throw new IllegalArgumentException();
        }
        if (i8 == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i8 == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i8 == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i8 == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    public static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.f9062a[event.ordinal()]) {
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
        int i8 = a.f9063b[state.ordinal()];
        if (i8 != 1) {
            if (i8 == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i8 == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i8 == 4) {
                throw new IllegalArgumentException();
            }
            if (i8 != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(e eVar) {
        f fVar;
        Lifecycle.State state = this.f9056c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(eVar, state2);
        if (this.f9055b.g(eVar, bVar) == null && (fVar = this.f9057d.get()) != null) {
            boolean z7 = this.f9058e != 0 || this.f9059f;
            Lifecycle.State stateE = e(eVar);
            this.f9058e++;
            while (bVar.f9064a.compareTo(stateE) < 0 && this.f9055b.contains(eVar)) {
                o(bVar.f9064a);
                bVar.a(fVar, r(bVar.f9064a));
                n();
                stateE = e(eVar);
            }
            if (!z7) {
                q();
            }
            this.f9058e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f9056c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        this.f9055b.h(eVar);
    }

    public final void d(f fVar) {
        Iterator<Map.Entry<e, b>> itA = this.f9055b.a();
        while (itA.hasNext() && !this.f9060g) {
            Map.Entry<e, b> next = itA.next();
            b value = next.getValue();
            while (value.f9064a.compareTo(this.f9056c) > 0 && !this.f9060g && this.f9055b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.f9064a);
                o(h(eventF));
                value.a(fVar, eventF);
                n();
            }
        }
    }

    public final Lifecycle.State e(e eVar) {
        Map.Entry<e, b> entryI = this.f9055b.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f9064a : null;
        if (!this.f9061h.isEmpty()) {
            state = this.f9061h.get(r0.size() - 1);
        }
        return l(l(this.f9056c, state2), state);
    }

    public final void g(f fVar) {
        t.b<e, b>.d dVarD = this.f9055b.d();
        while (dVarD.hasNext() && !this.f9060g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f9064a.compareTo(this.f9056c) < 0 && !this.f9060g && this.f9055b.contains((e) next.getKey())) {
                o(bVar.f9064a);
                bVar.a(fVar, r(bVar.f9064a));
                n();
            }
        }
    }

    public void i(Lifecycle.Event event) {
        m(h(event));
    }

    public final boolean j() {
        if (this.f9055b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f9055b.b().getValue().f9064a;
        Lifecycle.State state2 = this.f9055b.e().getValue().f9064a;
        return state == state2 && this.f9056c == state2;
    }

    @Deprecated
    public void k(Lifecycle.State state) {
        p(state);
    }

    public final void m(Lifecycle.State state) {
        if (this.f9056c == state) {
            return;
        }
        this.f9056c = state;
        if (this.f9059f || this.f9058e != 0) {
            this.f9060g = true;
            return;
        }
        this.f9059f = true;
        q();
        this.f9059f = false;
    }

    public final void n() {
        this.f9061h.remove(r0.size() - 1);
    }

    public final void o(Lifecycle.State state) {
        this.f9061h.add(state);
    }

    public void p(Lifecycle.State state) {
        m(state);
    }

    public final void q() {
        f fVar = this.f9057d.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.f9060g = false;
            if (this.f9056c.compareTo(this.f9055b.b().getValue().f9064a) < 0) {
                d(fVar);
            }
            Map.Entry<e, b> entryE = this.f9055b.e();
            if (!this.f9060g && entryE != null && this.f9056c.compareTo(entryE.getValue().f9064a) > 0) {
                g(fVar);
            }
        }
        this.f9060g = false;
    }
}
