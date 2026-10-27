package androidx.lifecycle;

import androidx.lifecycle.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h extends d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakReference<g> f1407d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.b.a.b.a<f, b> f1405b = new a.b.a.b.a<>();
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private ArrayList<d.b> h = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d.b f1406c = d.b.INITIALIZED;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1408a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1409b;

        static {
            int[] iArr = new int[d.b.values().length];
            f1409b = iArr;
            try {
                iArr[d.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1409b[d.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1409b[d.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1409b[d.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1409b[d.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[d.a.values().length];
            f1408a = iArr2;
            try {
                iArr2[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1408a[d.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1408a[d.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1408a[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1408a[d.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1408a[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1408a[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        d.b f1410a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e f1411b;

        b(f fVar, d.b bVar) {
            this.f1411b = j.f(fVar);
            this.f1410a = bVar;
        }

        void a(g gVar, d.a aVar) {
            d.b bVarH = h.h(aVar);
            this.f1410a = h.l(this.f1410a, bVarH);
            this.f1411b.d(gVar, aVar);
            this.f1410a = bVarH;
        }
    }

    public h(g gVar) {
        this.f1407d = new WeakReference<>(gVar);
    }

    private void d(g gVar) {
        Iterator<Map.Entry<f, b>> itA = this.f1405b.a();
        while (itA.hasNext() && !this.g) {
            Map.Entry<f, b> next = itA.next();
            b value = next.getValue();
            while (value.f1410a.compareTo(this.f1406c) > 0 && !this.g && this.f1405b.contains(next.getKey())) {
                d.a aVarF = f(value.f1410a);
                o(h(aVarF));
                value.a(gVar, aVarF);
                n();
            }
        }
    }

    private d.b e(f fVar) {
        Map.Entry<f, b> entryI = this.f1405b.i(fVar);
        d.b bVar = null;
        d.b bVar2 = entryI != null ? entryI.getValue().f1410a : null;
        if (!this.h.isEmpty()) {
            bVar = this.h.get(r0.size() - 1);
        }
        return l(l(this.f1406c, bVar2), bVar);
    }

    private static d.a f(d.b bVar) {
        int i = a.f1409b[bVar.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return d.a.ON_DESTROY;
        }
        if (i == 3) {
            return d.a.ON_STOP;
        }
        if (i == 4) {
            return d.a.ON_PAUSE;
        }
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + bVar);
    }

    private void g(g gVar) {
        a.b.a.b.b<f, b>.d dVarD = this.f1405b.d();
        while (dVarD.hasNext() && !this.g) {
            Map.Entry next = dVarD.next();
            b bVar = (b) next.getValue();
            while (bVar.f1410a.compareTo(this.f1406c) < 0 && !this.g && this.f1405b.contains((f) next.getKey())) {
                o(bVar.f1410a);
                bVar.a(gVar, r(bVar.f1410a));
                n();
            }
        }
    }

    static d.b h(d.a aVar) {
        switch (a.f1408a[aVar.ordinal()]) {
            case 1:
            case 2:
                return d.b.CREATED;
            case 3:
            case 4:
                return d.b.STARTED;
            case 5:
                return d.b.RESUMED;
            case 6:
                return d.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private boolean j() {
        if (this.f1405b.size() == 0) {
            return true;
        }
        d.b bVar = this.f1405b.b().getValue().f1410a;
        d.b bVar2 = this.f1405b.e().getValue().f1410a;
        return bVar == bVar2 && this.f1406c == bVar2;
    }

    static d.b l(d.b bVar, d.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    private void m(d.b bVar) {
        if (this.f1406c == bVar) {
            return;
        }
        this.f1406c = bVar;
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

    private void o(d.b bVar) {
        this.h.add(bVar);
    }

    private void q() {
        g gVar = this.f1407d.get();
        if (gVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            boolean zJ = j();
            this.g = false;
            if (zJ) {
                return;
            }
            if (this.f1406c.compareTo(this.f1405b.b().getValue().f1410a) < 0) {
                d(gVar);
            }
            Map.Entry<f, b> entryE = this.f1405b.e();
            if (!this.g && entryE != null && this.f1406c.compareTo(entryE.getValue().f1410a) > 0) {
                g(gVar);
            }
        }
    }

    private static d.a r(d.b bVar) {
        int i = a.f1409b[bVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return d.a.ON_START;
            }
            if (i == 3) {
                return d.a.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + bVar);
            }
        }
        return d.a.ON_CREATE;
    }

    @Override // androidx.lifecycle.d
    public void a(f fVar) {
        g gVar;
        d.b bVar = this.f1406c;
        d.b bVar2 = d.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = d.b.INITIALIZED;
        }
        b bVar3 = new b(fVar, bVar2);
        if (this.f1405b.g(fVar, bVar3) == null && (gVar = this.f1407d.get()) != null) {
            boolean z = this.e != 0 || this.f;
            d.b bVarE = e(fVar);
            this.e++;
            while (bVar3.f1410a.compareTo(bVarE) < 0 && this.f1405b.contains(fVar)) {
                o(bVar3.f1410a);
                bVar3.a(gVar, r(bVar3.f1410a));
                n();
                bVarE = e(fVar);
            }
            if (!z) {
                q();
            }
            this.e--;
        }
    }

    @Override // androidx.lifecycle.d
    public d.b b() {
        return this.f1406c;
    }

    @Override // androidx.lifecycle.d
    public void c(f fVar) {
        this.f1405b.h(fVar);
    }

    public void i(d.a aVar) {
        m(h(aVar));
    }

    @Deprecated
    public void k(d.b bVar) {
        p(bVar);
    }

    public void p(d.b bVar) {
        m(bVar);
    }
}
