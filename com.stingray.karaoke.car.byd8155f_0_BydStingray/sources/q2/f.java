package q2;

import B5.H;
import android.media.ResourceBusyException;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import android.os.Looper;
import d5.C1078G;
import d5.I;
import d5.M;
import d5.d0;
import d5.s0;
import e2.AbstractC1199g;
import e2.C1203k;
import e2.C1204l;
import e2.C1208p;
import e2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import n2.RunnableC1918s;

/* JADX INFO: loaded from: classes.dex */
public final class f implements o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final UUID f21186b;
    public final o2.d c;
    public final A2.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f21187e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f21188f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f21189g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f21190h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m2.b f21191i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l9.n f21192j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C2172c f21193k;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f21194m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Set f21195n;
    public final Set o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21196p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public s f21197q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C2171b f21198r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2171b f21199s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Looper f21200t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Handler f21201u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte[] f21202v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public o2.l f21203w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public volatile H f21204x;

    public f(UUID uuid, A2.c cVar, HashMap map, boolean z6, int[] iArr, boolean z10, l9.n nVar) {
        o2.d dVar = v.o;
        uuid.getClass();
        AbstractC1346c.b("Use C.CLEARKEY_UUID instead", !AbstractC1199g.f14100b.equals(uuid));
        this.f21186b = uuid;
        this.c = dVar;
        this.d = cVar;
        this.f21187e = map;
        this.f21188f = z6;
        this.f21189g = iArr;
        this.f21190h = z10;
        this.f21192j = nVar;
        this.f21191i = new m2.b(3);
        this.f21193k = new C2172c(this);
        this.f21194m = new ArrayList();
        this.f21195n = Collections.newSetFromMap(new IdentityHashMap());
        this.o = Collections.newSetFromMap(new IdentityHashMap());
        this.l = 300000L;
    }

    public static boolean h(C2171b c2171b) {
        c2171b.n();
        if (c2171b.o != 1) {
            return false;
        }
        g gVarF = c2171b.f();
        gVarF.getClass();
        Throwable cause = gVarF.getCause();
        return (cause instanceof ResourceBusyException) || h6.n.n(cause);
    }

    public static ArrayList k(C1204l c1204l, UUID uuid, boolean z6) {
        ArrayList arrayList = new ArrayList(c1204l.o);
        for (int i7 = 0; i7 < c1204l.o; i7++) {
            C1203k c1203k = c1204l.l[i7];
            if ((c1203k.a(uuid) || (AbstractC1199g.c.equals(uuid) && c1203k.a(AbstractC1199g.f14100b))) && (c1203k.f14136p != null || z6)) {
                arrayList.add(c1203k);
            }
        }
        return arrayList;
    }

    @Override // q2.o
    public final void a() {
        m(true);
        int i7 = this.f21196p - 1;
        this.f21196p = i7;
        if (i7 != 0) {
            return;
        }
        if (this.l != -9223372036854775807L) {
            ArrayList arrayList = new ArrayList(this.f21194m);
            for (int i10 = 0; i10 < arrayList.size(); i10++) {
                ((C2171b) arrayList.get(i10)).d(null);
            }
        }
        s0 it = M.v(this.f21195n).iterator();
        while (it.hasNext()) {
            ((e) it.next()).a();
        }
        l();
    }

    @Override // q2.o
    public final h b(k kVar, C1208p c1208p) {
        m(false);
        AbstractC1346c.j(this.f21196p > 0);
        AbstractC1346c.k(this.f21200t);
        return g(this.f21200t, kVar, c1208p, true);
    }

    @Override // q2.o
    public final void c() {
        s nVar;
        m(true);
        int i7 = this.f21196p;
        this.f21196p = i7 + 1;
        if (i7 != 0) {
            return;
        }
        if (this.f21197q == null) {
            UUID uuid = this.f21186b;
            this.c.getClass();
            try {
                try {
                    nVar = new v(uuid);
                } catch (y unused) {
                    AbstractC1346c.p("FrameworkMediaDrm", "Failed to instantiate a FrameworkMediaDrm for uuid: " + uuid + ".");
                    nVar = new l9.n(5);
                }
                this.f21197q = nVar;
                nVar.m(new C2172c(this));
                return;
            } catch (UnsupportedSchemeException e10) {
                throw new y(e10);
            } catch (Exception e11) {
                throw new y(e11);
            }
        }
        if (this.l == -9223372036854775807L) {
            return;
        }
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f21194m;
            if (i10 >= arrayList.size()) {
                return;
            }
            ((C2171b) arrayList.get(i10)).c(null);
            i10++;
        }
    }

    @Override // q2.o
    public final n d(k kVar, C1208p c1208p) {
        AbstractC1346c.j(this.f21196p > 0);
        AbstractC1346c.k(this.f21200t);
        e eVar = new e(this, kVar);
        Handler handler = this.f21201u;
        handler.getClass();
        handler.post(new RunnableC1918s(eVar, 4, c1208p));
        return eVar;
    }

    @Override // q2.o
    public final int e(C1208p c1208p) {
        String str;
        m(false);
        s sVar = this.f21197q;
        sVar.getClass();
        int iO = sVar.o();
        C1204l c1204l = c1208p.f14319r;
        if (c1204l == null) {
            int iG = L.g(c1208p.f14316n);
            int i7 = 0;
            while (true) {
                int[] iArr = this.f21189g;
                if (i7 >= iArr.length) {
                    i7 = -1;
                    break;
                }
                if (iArr[i7] == iG) {
                    break;
                }
                i7++;
            }
            if (i7 != -1) {
                return iO;
            }
            return 0;
        }
        if (this.f21202v != null) {
            return iO;
        }
        UUID uuid = this.f21186b;
        if (!k(c1204l, uuid, true).isEmpty()) {
            str = c1204l.f14161n;
            if (str == null && !"cenc".equals(str)) {
                if ("cbcs".equals(str)) {
                    if (AbstractC1343A.f15195a >= 25) {
                        return iO;
                    }
                } else if (!"cbc1".equals(str) && !"cens".equals(str)) {
                    return iO;
                }
            }
        } else if (c1204l.o == 1 && c1204l.l[0].a(AbstractC1199g.f14100b)) {
            AbstractC1346c.z("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + uuid);
            str = c1204l.f14161n;
            return str == null ? iO : iO;
        }
        return 1;
    }

    @Override // q2.o
    public final void f(Looper looper, o2.l lVar) {
        synchronized (this) {
            try {
                Looper looper2 = this.f21200t;
                if (looper2 == null) {
                    this.f21200t = looper;
                    this.f21201u = new Handler(looper);
                } else {
                    AbstractC1346c.j(looper2 == looper);
                    this.f21201u.getClass();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f21203w = lVar;
    }

    public final h g(Looper looper, k kVar, C1208p c1208p, boolean z6) {
        ArrayList arrayListK;
        if (this.f21204x == null) {
            this.f21204x = new H(this, looper, 19);
        }
        C1204l c1204l = c1208p.f14319r;
        int i7 = 0;
        C2171b c2171bJ = null;
        if (c1204l == null) {
            int iG = L.g(c1208p.f14316n);
            s sVar = this.f21197q;
            sVar.getClass();
            if (sVar.o() == 2 && t.c) {
                return null;
            }
            int[] iArr = this.f21189g;
            while (true) {
                if (i7 >= iArr.length) {
                    i7 = -1;
                    break;
                }
                if (iArr[i7] == iG) {
                    break;
                }
                i7++;
            }
            if (i7 == -1 || sVar.o() == 1) {
                return null;
            }
            C2171b c2171b = this.f21198r;
            if (c2171b == null) {
                C1078G c1078g = I.f13384m;
                C2171b c2171bJ2 = j(d0.f13415p, true, null, z6);
                this.f21194m.add(c2171bJ2);
                this.f21198r = c2171bJ2;
            } else {
                c2171b.c(null);
            }
            return this.f21198r;
        }
        if (this.f21202v == null) {
            arrayListK = k(c1204l, this.f21186b, false);
            if (arrayListK.isEmpty()) {
                C2173d c2173d = new C2173d("Media does not support uuid: " + this.f21186b);
                AbstractC1346c.q("DefaultDrmSessionMgr", "DRM error", c2173d);
                kVar.d(c2173d);
                return new p(new g(c2173d, 6003));
            }
        } else {
            arrayListK = null;
        }
        if (this.f21188f) {
            Iterator it = this.f21194m.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C2171b c2171b2 = (C2171b) it.next();
                if (Objects.equals(c2171b2.f21163a, arrayListK)) {
                    c2171bJ = c2171b2;
                    break;
                }
            }
        } else {
            c2171bJ = this.f21199s;
        }
        if (c2171bJ == null) {
            c2171bJ = j(arrayListK, false, kVar, z6);
            if (!this.f21188f) {
                this.f21199s = c2171bJ;
            }
            this.f21194m.add(c2171bJ);
        } else {
            c2171bJ.c(kVar);
        }
        return c2171bJ;
    }

    public final C2171b i(List list, boolean z6, k kVar) {
        this.f21197q.getClass();
        boolean z10 = this.f21190h | z6;
        s sVar = this.f21197q;
        byte[] bArr = this.f21202v;
        Looper looper = this.f21200t;
        looper.getClass();
        o2.l lVar = this.f21203w;
        lVar.getClass();
        C2171b c2171b = new C2171b(this.f21186b, sVar, this.f21191i, this.f21193k, list, z10, z6, bArr, this.f21187e, this.d, looper, this.f21192j, lVar);
        c2171b.c(kVar);
        if (this.l != -9223372036854775807L) {
            c2171b.c(null);
        }
        return c2171b;
    }

    public final C2171b j(List list, boolean z6, k kVar, boolean z10) {
        C2171b c2171bI = i(list, z6, kVar);
        boolean zH = h(c2171bI);
        long j3 = this.l;
        Set set = this.o;
        if (zH && !set.isEmpty()) {
            s0 it = M.v(set).iterator();
            while (it.hasNext()) {
                ((h) it.next()).d(null);
            }
            c2171bI.d(kVar);
            if (j3 != -9223372036854775807L) {
                c2171bI.d(null);
            }
            c2171bI = i(list, z6, kVar);
        }
        if (!h(c2171bI) || !z10) {
            return c2171bI;
        }
        Set set2 = this.f21195n;
        if (set2.isEmpty()) {
            return c2171bI;
        }
        s0 it2 = M.v(set2).iterator();
        while (it2.hasNext()) {
            ((e) it2.next()).a();
        }
        if (!set.isEmpty()) {
            s0 it3 = M.v(set).iterator();
            while (it3.hasNext()) {
                ((h) it3.next()).d(null);
            }
        }
        c2171bI.d(kVar);
        if (j3 != -9223372036854775807L) {
            c2171bI.d(null);
        }
        return i(list, z6, kVar);
    }

    public final void l() {
        if (this.f21197q != null && this.f21196p == 0 && this.f21194m.isEmpty() && this.f21195n.isEmpty()) {
            s sVar = this.f21197q;
            sVar.getClass();
            sVar.a();
            this.f21197q = null;
        }
    }

    public final void m(boolean z6) {
        if (z6 && this.f21200t == null) {
            AbstractC1346c.A("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed before setPlayer(), possibly on the wrong thread.", new IllegalStateException());
            return;
        }
        Thread threadCurrentThread = Thread.currentThread();
        Looper looper = this.f21200t;
        looper.getClass();
        if (threadCurrentThread != looper.getThread()) {
            AbstractC1346c.A("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.f21200t.getThread().getName(), new IllegalStateException());
        }
    }
}
