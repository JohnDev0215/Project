package a.n;

import a.c.a.b.b;
import a.n.g;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class l extends g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WeakReference<k> f1095c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.c.a.b.a<j, a> f1093a = new a.c.a.b.a<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1096d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1097e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1098f = false;
    public ArrayList<g.b> g = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.b f1094b = g.b.INITIALIZED;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g.b f1099a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public i f1100b;

        public a(j jVar, g.b bVar) {
            this.f1100b = n.d(jVar);
            this.f1099a = bVar;
        }

        public void a(k kVar, g.a aVar) {
            g.b bVarC = l.c(aVar);
            this.f1099a = l.e(this.f1099a, bVarC);
            this.f1100b.g(kVar, aVar);
            this.f1099a = bVarC;
        }
    }

    public l(k kVar) {
        this.f1095c = new WeakReference<>(kVar);
    }

    public static g.b c(g.a aVar) {
        int iOrdinal = aVar.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    return g.b.RESUMED;
                }
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        if (iOrdinal == 5) {
                            return g.b.DESTROYED;
                        }
                        throw new IllegalArgumentException("Unexpected event value " + aVar);
                    }
                }
            }
            return g.b.STARTED;
        }
        return g.b.CREATED;
    }

    public static g.b e(g.b bVar, g.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    public static g.a i(g.b bVar) {
        int iOrdinal = bVar.ordinal();
        if (iOrdinal == 0 || iOrdinal == 1) {
            return g.a.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return g.a.ON_START;
        }
        if (iOrdinal == 3) {
            return g.a.ON_RESUME;
        }
        if (iOrdinal == 4) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + bVar);
    }

    @Override // a.n.g
    public void a(j jVar) {
        k kVar;
        g.b bVar = this.f1094b;
        g.b bVar2 = g.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = g.b.INITIALIZED;
        }
        a aVar = new a(jVar, bVar2);
        if (this.f1093a.f(jVar, aVar) == null && (kVar = this.f1095c.get()) != null) {
            boolean z = this.f1096d != 0 || this.f1097e;
            g.b bVarB = b(jVar);
            this.f1096d++;
            while (aVar.f1099a.compareTo(bVarB) < 0 && this.f1093a.f424e.containsKey(jVar)) {
                this.g.add(aVar.f1099a);
                aVar.a(kVar, i(aVar.f1099a));
                g();
                bVarB = b(jVar);
            }
            if (!z) {
                h();
            }
            this.f1096d--;
        }
    }

    public final g.b b(j jVar) {
        a.c.a.b.a<j, a> aVar = this.f1093a;
        g.b bVar = null;
        b.c<j, a> cVar = aVar.f424e.containsKey(jVar) ? aVar.f424e.get(jVar).f432d : null;
        g.b bVar2 = cVar != null ? cVar.getValue().f1099a : null;
        if (!this.g.isEmpty()) {
            bVar = this.g.get(r0.size() - 1);
        }
        return e(e(this.f1094b, bVar2), bVar);
    }

    public void d(g.a aVar) {
        f(c(aVar));
    }

    public final void f(g.b bVar) {
        if (this.f1094b == bVar) {
            return;
        }
        this.f1094b = bVar;
        if (this.f1097e || this.f1096d != 0) {
            this.f1098f = true;
            return;
        }
        this.f1097e = true;
        h();
        this.f1097e = false;
    }

    public final void g() {
        this.g.remove(r1.size() - 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.n.l.h():void");
    }
}
