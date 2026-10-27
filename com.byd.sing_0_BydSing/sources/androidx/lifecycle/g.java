package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Lifecycle {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<f> f1637d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c.b.a.b.a<e, b> f1635b = new c.b.a.b.a<>();
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private ArrayList<Lifecycle.State> h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Lifecycle.State f1636c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1638a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1639b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f1639b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1639b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1639b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1639b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1639b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f1638a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1638a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1638a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1638a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1638a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1638a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1638a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Lifecycle.State f1640a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d f1641b;

        b(e eVar, Lifecycle.State state) {
            this.f1641b = i.f(eVar);
            this.f1640a = state;
        }

        void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateH = g.h(event);
            this.f1640a = g.l(this.f1640a, stateH);
            this.f1641b.d(fVar, event);
            this.f1640a = stateH;
        }
    }

    public g(f fVar) {
        this.f1637d = new WeakReference<>(fVar);
    }

    private void d(f fVar) {
        Iterator<Map.Entry<e, b>> itA = this.f1635b.a();
        while (itA.hasNext() && !this.g) {
            Map.Entry<e, b> next = itA.next();
            b value = next.getValue();
            while (value.f1640a.compareTo(this.f1636c) > 0 && !this.g && this.f1635b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.f1640a);
                o(h(eventF));
                value.a(fVar, eventF);
                n();
            }
        }
    }

    private Lifecycle.State e(e eVar) {
        Map.Entry<e, b> entryI = this.f1635b.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f1640a : null;
        if (!this.h.isEmpty()) {
            state = this.h.get(r0.size() - 1);
        }
        return l(l(this.f1636c, state2), state);
    }

    private static Lifecycle.Event f(Lifecycle.State state) {
        int i = a.f1639b[state.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    private void g(f fVar) {
        c.b.a.b.b<e, b>.d dVarD = this.f1635b.d();
        while (dVarD.hasNext() && !this.g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f1640a.compareTo(this.f1636c) < 0 && !this.g && this.f1635b.contains((e) next.getKey())) {
                o(bVar.f1640a);
                bVar.a(fVar, r(bVar.f1640a));
                n();
            }
        }
    }

    static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.f1638a[event.ordinal()]) {
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

    private boolean j() {
        if (this.f1635b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f1635b.b().getValue().f1640a;
        Lifecycle.State state2 = this.f1635b.e().getValue().f1640a;
        return state == state2 && this.f1636c == state2;
    }

    static Lifecycle.State l(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void m(Lifecycle.State state) {
        if (this.f1636c == state) {
            return;
        }
        this.f1636c = state;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        q();
        this.f = false;
    }

    private void n() {
        this.h.remove(r0.size() - 1);
    }

    private void o(Lifecycle.State state) {
        this.h.add(state);
    }

    private void q() {
        f fVar = this.f1637d.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.g = false;
            if (this.f1636c.compareTo(this.f1635b.b().getValue().f1640a) < 0) {
                d(fVar);
            }
            Map.Entry<e, b> entryE = this.f1635b.e();
            if (!this.g && entryE != null && this.f1636c.compareTo(entryE.getValue().f1640a) > 0) {
                g(fVar);
            }
        }
        this.g = false;
    }

    private static Lifecycle.Event r(Lifecycle.State state) {
        int i = a.f1639b[state.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(e eVar) {
        f fVar;
        Lifecycle.State state = this.f1636c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(eVar, state2);
        if (this.f1635b.g(eVar, bVar) == null && (fVar = this.f1637d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            Lifecycle.State stateE = e(eVar);
            this.e++;
            while (bVar.f1640a.compareTo(stateE) < 0 && this.f1635b.contains(eVar)) {
                o(bVar.f1640a);
                bVar.a(fVar, r(bVar.f1640a));
                n();
                stateE = e(eVar);
            }
            if (!z) {
                q();
            }
            this.e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f1636c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        this.f1635b.h(eVar);
    }

    public void i(Lifecycle.Event event) {
        m(h(event));
    }

    @Deprecated
    public void k(Lifecycle.State state) {
        p(state);
    }

    public void p(Lifecycle.State state) {
        m(state);
    }
}
