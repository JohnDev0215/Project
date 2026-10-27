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
    private final WeakReference<f> f1660d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.b.a.b.a<e, b> f1658b = new b.b.a.b.a<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1661e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1662f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1663g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<Lifecycle.State> f1664h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Lifecycle.State f1659c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1665a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1666b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f1666b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1666b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1666b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1666b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1666b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f1665a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1665a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1665a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1665a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1665a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1665a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1665a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Lifecycle.State f1667a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d f1668b;

        b(e eVar, Lifecycle.State state) {
            this.f1668b = i.f(eVar);
            this.f1667a = state;
        }

        void a(f fVar, Lifecycle.Event event) {
            Lifecycle.State stateH = g.h(event);
            this.f1667a = g.l(this.f1667a, stateH);
            this.f1668b.d(fVar, event);
            this.f1667a = stateH;
        }
    }

    public g(f fVar) {
        this.f1660d = new WeakReference<>(fVar);
    }

    private void d(f fVar) {
        Iterator<Map.Entry<e, b>> itA = this.f1658b.a();
        while (itA.hasNext() && !this.f1663g) {
            Map.Entry<e, b> next = itA.next();
            b value = next.getValue();
            while (value.f1667a.compareTo(this.f1659c) > 0 && !this.f1663g && this.f1658b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.f1667a);
                o(h(eventF));
                value.a(fVar, eventF);
                n();
            }
        }
    }

    private Lifecycle.State e(e eVar) {
        Map.Entry<e, b> entryI = this.f1658b.i(eVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().f1667a : null;
        if (!this.f1664h.isEmpty()) {
            state = this.f1664h.get(r0.size() - 1);
        }
        return l(l(this.f1659c, state2), state);
    }

    private static Lifecycle.Event f(Lifecycle.State state) {
        int i = a.f1666b[state.ordinal()];
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
        b.b.a.b.b<e, b>.d dVarD = this.f1658b.d();
        while (dVarD.hasNext() && !this.f1663g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f1667a.compareTo(this.f1659c) < 0 && !this.f1663g && this.f1658b.contains((e) next.getKey())) {
                o(bVar.f1667a);
                bVar.a(fVar, r(bVar.f1667a));
                n();
            }
        }
    }

    static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.f1665a[event.ordinal()]) {
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
        if (this.f1658b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f1658b.b().getValue().f1667a;
        Lifecycle.State state2 = this.f1658b.e().getValue().f1667a;
        return state == state2 && this.f1659c == state2;
    }

    static Lifecycle.State l(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    private void m(Lifecycle.State state) {
        if (this.f1659c == state) {
            return;
        }
        this.f1659c = state;
        if (this.f1662f || this.f1661e != 0) {
            this.f1663g = true;
            return;
        }
        this.f1662f = true;
        q();
        this.f1662f = false;
    }

    private void n() {
        this.f1664h.remove(r0.size() - 1);
    }

    private void o(Lifecycle.State state) {
        this.f1664h.add(state);
    }

    private void q() {
        f fVar = this.f1660d.get();
        if (fVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.f1663g = false;
            if (this.f1659c.compareTo(this.f1658b.b().getValue().f1667a) < 0) {
                d(fVar);
            }
            Map.Entry<e, b> entryE = this.f1658b.e();
            if (!this.f1663g && entryE != null && this.f1659c.compareTo(entryE.getValue().f1667a) > 0) {
                g(fVar);
            }
        }
        this.f1663g = false;
    }

    private static Lifecycle.Event r(Lifecycle.State state) {
        int i = a.f1666b[state.ordinal()];
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
        Lifecycle.State state = this.f1659c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(eVar, state2);
        if (this.f1658b.g(eVar, bVar) == null && (fVar = this.f1660d.get()) != null) {
            boolean z = this.f1661e != 0 || this.f1662f;
            Lifecycle.State stateE = e(eVar);
            this.f1661e++;
            while (bVar.f1667a.compareTo(stateE) < 0 && this.f1658b.contains(eVar)) {
                o(bVar.f1667a);
                bVar.a(fVar, r(bVar.f1667a));
                n();
                stateE = e(eVar);
            }
            if (!z) {
                q();
            }
            this.f1661e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.f1659c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(e eVar) {
        this.f1658b.h(eVar);
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
