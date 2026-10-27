package defpackage;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class fc extends Lifecycle {
    public final WeakReference<ec> d;
    public r3<dc, b> b = new r3<>();
    public int e = 0;
    public boolean f = false;
    public boolean g = false;
    public ArrayList<Lifecycle.State> h = new ArrayList<>();
    public Lifecycle.State c = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class b {
        public Lifecycle.State a;
        public cc b;

        public b(dc dcVar, Lifecycle.State state) {
            this.b = hc.f(dcVar);
            this.a = state;
        }

        public void a(ec ecVar, Lifecycle.Event event) {
            Lifecycle.State stateH = fc.h(event);
            this.a = fc.l(this.a, stateH);
            this.b.d(ecVar, event);
            this.a = stateH;
        }
    }

    public fc(ec ecVar) {
        this.d = new WeakReference<>(ecVar);
    }

    public static Lifecycle.Event f(Lifecycle.State state) {
        int i = a.b[state.ordinal()];
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

    public static Lifecycle.State h(Lifecycle.Event event) {
        switch (a.a[event.ordinal()]) {
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
        int i = a.b[state.ordinal()];
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
    public void a(dc dcVar) {
        ec ecVar;
        Lifecycle.State state = this.c;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(dcVar, state2);
        if (this.b.g(dcVar, bVar) == null && (ecVar = this.d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            Lifecycle.State stateE = e(dcVar);
            this.e++;
            while (bVar.a.compareTo(stateE) < 0 && this.b.contains(dcVar)) {
                o(bVar.a);
                bVar.a(ecVar, r(bVar.a));
                n();
                stateE = e(dcVar);
            }
            if (!z) {
                q();
            }
            this.e--;
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State b() {
        return this.c;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void c(dc dcVar) {
        this.b.h(dcVar);
    }

    public final void d(ec ecVar) {
        Iterator<Map.Entry<dc, b>> itA = this.b.a();
        while (itA.hasNext() && !this.g) {
            Map.Entry<dc, b> next = itA.next();
            b value = next.getValue();
            while (value.a.compareTo(this.c) > 0 && !this.g && this.b.contains(next.getKey())) {
                Lifecycle.Event eventF = f(value.a);
                o(h(eventF));
                value.a(ecVar, eventF);
                n();
            }
        }
    }

    public final Lifecycle.State e(dc dcVar) {
        Map.Entry<dc, b> entryI = this.b.i(dcVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryI != null ? entryI.getValue().a : null;
        if (!this.h.isEmpty()) {
            state = this.h.get(r0.size() - 1);
        }
        return l(l(this.c, state2), state);
    }

    public final void g(ec ecVar) {
        s3<dc, b>.d dVarD = this.b.d();
        while (dVarD.hasNext() && !this.g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.a.compareTo(this.c) < 0 && !this.g && this.b.contains((dc) next.getKey())) {
                o(bVar.a);
                bVar.a(ecVar, r(bVar.a));
                n();
            }
        }
    }

    public void i(Lifecycle.Event event) {
        m(h(event));
    }

    public final boolean j() {
        if (this.b.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.b.b().getValue().a;
        Lifecycle.State state2 = this.b.e().getValue().a;
        return state == state2 && this.c == state2;
    }

    @Deprecated
    public void k(Lifecycle.State state) {
        p(state);
    }

    public final void m(Lifecycle.State state) {
        if (this.c == state) {
            return;
        }
        this.c = state;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        q();
        this.f = false;
    }

    public final void n() {
        this.h.remove(r0.size() - 1);
    }

    public final void o(Lifecycle.State state) {
        this.h.add(state);
    }

    public void p(Lifecycle.State state) {
        m(state);
    }

    public final void q() {
        ec ecVar = this.d.get();
        if (ecVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!j()) {
            this.g = false;
            if (this.c.compareTo(this.b.b().getValue().a) < 0) {
                d(ecVar);
            }
            Map.Entry<dc, b> entryE = this.b.e();
            if (!this.g && entryE != null && this.c.compareTo(entryE.getValue().a) > 0) {
                g(ecVar);
            }
        }
        this.g = false;
    }
}
