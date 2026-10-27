package com.bumptech.glide.load.n;

import android.os.Build;
import android.util.Log;
import com.bumptech.glide.i;
import com.bumptech.glide.load.n.f;
import com.bumptech.glide.load.n.i;
import com.bumptech.glide.t.l.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class h<R> implements f.a, Runnable, Comparable<h<?>>, a.f {
    private Object A;
    private com.bumptech.glide.load.a B;
    private com.bumptech.glide.load.data.d<?> C;
    private volatile com.bumptech.glide.load.n.f D;
    private volatile boolean E;
    private volatile boolean F;
    private final e e;
    private final a.g.j.e<h<?>> f;
    private com.bumptech.glide.e i;
    private com.bumptech.glide.load.g j;
    private com.bumptech.glide.g k;
    private n l;
    private int m;
    private int n;
    private j o;
    private com.bumptech.glide.load.i p;
    private b<R> q;
    private int r;
    private EnumC0072h s;
    private g t;
    private long u;
    private boolean v;
    private Object w;
    private Thread x;
    private com.bumptech.glide.load.g y;
    private com.bumptech.glide.load.g z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.n.g<R> f2117b = new com.bumptech.glide.load.n.g<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Throwable> f2118c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.t.l.c f2119d = com.bumptech.glide.t.l.c.a();
    private final d<?> g = new d<>();
    private final f h = new f();

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2120a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2121b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f2122c;

        static {
            int[] iArr = new int[com.bumptech.glide.load.c.values().length];
            f2122c = iArr;
            try {
                iArr[com.bumptech.glide.load.c.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2122c[com.bumptech.glide.load.c.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[EnumC0072h.values().length];
            f2121b = iArr2;
            try {
                iArr2[EnumC0072h.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2121b[EnumC0072h.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2121b[EnumC0072h.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2121b[EnumC0072h.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2121b[EnumC0072h.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[g.values().length];
            f2120a = iArr3;
            try {
                iArr3[g.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2120a[g.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2120a[g.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    interface b<R> {
        void a(q qVar);

        void c(v<R> vVar, com.bumptech.glide.load.a aVar);

        void d(h<?> hVar);
    }

    private final class c<Z> implements i.a<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.bumptech.glide.load.a f2123a;

        c(com.bumptech.glide.load.a aVar) {
            this.f2123a = aVar;
        }

        @Override // com.bumptech.glide.load.n.i.a
        public v<Z> a(v<Z> vVar) {
            return h.this.v(this.f2123a, vVar);
        }
    }

    private static class d<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.bumptech.glide.load.g f2125a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.bumptech.glide.load.l<Z> f2126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private u<Z> f2127c;

        d() {
        }

        void a() {
            this.f2125a = null;
            this.f2126b = null;
            this.f2127c = null;
        }

        void b(e eVar, com.bumptech.glide.load.i iVar) {
            com.bumptech.glide.t.l.b.a("DecodeJob.encode");
            try {
                eVar.a().a(this.f2125a, new com.bumptech.glide.load.n.e(this.f2126b, this.f2127c, iVar));
            } finally {
                this.f2127c.i();
                com.bumptech.glide.t.l.b.d();
            }
        }

        boolean c() {
            return this.f2127c != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        <X> void d(com.bumptech.glide.load.g gVar, com.bumptech.glide.load.l<X> lVar, u<X> uVar) {
            this.f2125a = gVar;
            this.f2126b = lVar;
            this.f2127c = uVar;
        }
    }

    interface e {
        com.bumptech.glide.load.n.b0.a a();
    }

    private static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2128a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2129b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2130c;

        f() {
        }

        private boolean a(boolean z) {
            return (this.f2130c || z || this.f2129b) && this.f2128a;
        }

        synchronized boolean b() {
            this.f2129b = true;
            return a(false);
        }

        synchronized boolean c() {
            this.f2130c = true;
            return a(false);
        }

        synchronized boolean d(boolean z) {
            this.f2128a = true;
            return a(z);
        }

        synchronized void e() {
            this.f2129b = false;
            this.f2128a = false;
            this.f2130c = false;
        }
    }

    private enum g {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    /* JADX INFO: renamed from: com.bumptech.glide.load.n.h$h, reason: collision with other inner class name */
    private enum EnumC0072h {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    h(e eVar, a.g.j.e<h<?>> eVar2) {
        this.e = eVar;
        this.f = eVar2;
    }

    private void A() {
        int i = a.f2120a[this.t.ordinal()];
        if (i == 1) {
            this.s = k(EnumC0072h.INITIALIZE);
            this.D = j();
        } else if (i != 2) {
            if (i == 3) {
                i();
                return;
            }
            throw new IllegalStateException("Unrecognized run reason: " + this.t);
        }
        y();
    }

    private void B() {
        Throwable th;
        this.f2119d.c();
        if (!this.E) {
            this.E = true;
            return;
        }
        if (this.f2118c.isEmpty()) {
            th = null;
        } else {
            List<Throwable> list = this.f2118c;
            th = list.get(list.size() - 1);
        }
        throw new IllegalStateException("Already notified", th);
    }

    private <Data> v<R> g(com.bumptech.glide.load.data.d<?> dVar, Data data, com.bumptech.glide.load.a aVar) {
        if (data == null) {
            return null;
        }
        try {
            long jB = com.bumptech.glide.t.f.b();
            v<R> vVarH = h(data, aVar);
            if (Log.isLoggable("DecodeJob", 2)) {
                o("Decoded result " + vVarH, jB);
            }
            return vVarH;
        } finally {
            dVar.b();
        }
    }

    private <Data> v<R> h(Data data, com.bumptech.glide.load.a aVar) {
        return z(data, aVar, this.f2117b.h(data.getClass()));
    }

    private void i() {
        if (Log.isLoggable("DecodeJob", 2)) {
            p("Retrieved data", this.u, "data: " + this.A + ", cache key: " + this.y + ", fetcher: " + this.C);
        }
        v<R> vVarG = null;
        try {
            vVarG = g(this.C, this.A, this.B);
        } catch (q e2) {
            e2.i(this.z, this.B);
            this.f2118c.add(e2);
        }
        if (vVarG != null) {
            r(vVarG, this.B);
        } else {
            y();
        }
    }

    private com.bumptech.glide.load.n.f j() {
        int i = a.f2121b[this.s.ordinal()];
        if (i == 1) {
            return new w(this.f2117b, this);
        }
        if (i == 2) {
            return new com.bumptech.glide.load.n.c(this.f2117b, this);
        }
        if (i == 3) {
            return new z(this.f2117b, this);
        }
        if (i == 4) {
            return null;
        }
        throw new IllegalStateException("Unrecognized stage: " + this.s);
    }

    private EnumC0072h k(EnumC0072h enumC0072h) {
        int i = a.f2121b[enumC0072h.ordinal()];
        if (i == 1) {
            return this.o.a() ? EnumC0072h.DATA_CACHE : k(EnumC0072h.DATA_CACHE);
        }
        if (i == 2) {
            return this.v ? EnumC0072h.FINISHED : EnumC0072h.SOURCE;
        }
        if (i == 3 || i == 4) {
            return EnumC0072h.FINISHED;
        }
        if (i == 5) {
            return this.o.b() ? EnumC0072h.RESOURCE_CACHE : k(EnumC0072h.RESOURCE_CACHE);
        }
        throw new IllegalArgumentException("Unrecognized stage: " + enumC0072h);
    }

    private com.bumptech.glide.load.i l(com.bumptech.glide.load.a aVar) {
        com.bumptech.glide.load.i iVar = this.p;
        if (Build.VERSION.SDK_INT < 26) {
            return iVar;
        }
        boolean z = aVar == com.bumptech.glide.load.a.RESOURCE_DISK_CACHE || this.f2117b.w();
        com.bumptech.glide.load.h<Boolean> hVar = com.bumptech.glide.load.p.d.j.e;
        Boolean bool = (Boolean) iVar.c(hVar);
        if (bool != null && (!bool.booleanValue() || z)) {
            return iVar;
        }
        com.bumptech.glide.load.i iVar2 = new com.bumptech.glide.load.i();
        iVar2.d(this.p);
        iVar2.e(hVar, Boolean.valueOf(z));
        return iVar2;
    }

    private int m() {
        return this.k.ordinal();
    }

    private void o(String str, long j) {
        p(str, j, null);
    }

    private void p(String str, long j, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(com.bumptech.glide.t.f.a(j));
        sb.append(", load key: ");
        sb.append(this.l);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sb.toString());
    }

    private void q(v<R> vVar, com.bumptech.glide.load.a aVar) {
        B();
        this.q.c(vVar, aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void r(v<R> vVar, com.bumptech.glide.load.a aVar) {
        if (vVar instanceof r) {
            ((r) vVar).a();
        }
        u uVar = 0;
        if (this.g.c()) {
            vVar = u.g(vVar);
            uVar = vVar;
        }
        q(vVar, aVar);
        this.s = EnumC0072h.ENCODE;
        try {
            if (this.g.c()) {
                this.g.b(this.e, this.p);
            }
            t();
        } finally {
            if (uVar != 0) {
                uVar.i();
            }
        }
    }

    private void s() {
        B();
        this.q.a(new q("Failed to load resource", new ArrayList(this.f2118c)));
        u();
    }

    private void t() {
        if (this.h.b()) {
            x();
        }
    }

    private void u() {
        if (this.h.c()) {
            x();
        }
    }

    private void x() {
        this.h.e();
        this.g.a();
        this.f2117b.a();
        this.E = false;
        this.i = null;
        this.j = null;
        this.p = null;
        this.k = null;
        this.l = null;
        this.q = null;
        this.s = null;
        this.D = null;
        this.x = null;
        this.y = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.u = 0L;
        this.F = false;
        this.w = null;
        this.f2118c.clear();
        this.f.a(this);
    }

    private void y() {
        this.x = Thread.currentThread();
        this.u = com.bumptech.glide.t.f.b();
        boolean zA = false;
        while (!this.F && this.D != null && !(zA = this.D.a())) {
            this.s = k(this.s);
            this.D = j();
            if (this.s == EnumC0072h.SOURCE) {
                b();
                return;
            }
        }
        if ((this.s == EnumC0072h.FINISHED || this.F) && !zA) {
            s();
        }
    }

    private <Data, ResourceType> v<R> z(Data data, com.bumptech.glide.load.a aVar, t<Data, ResourceType, R> tVar) {
        com.bumptech.glide.load.i iVarL = l(aVar);
        com.bumptech.glide.load.data.e<Data> eVarL = this.i.g().l(data);
        try {
            return tVar.a(eVarL, iVarL, this.m, this.n, new c(aVar));
        } finally {
            eVarL.b();
        }
    }

    boolean C() {
        EnumC0072h enumC0072hK = k(EnumC0072h.INITIALIZE);
        return enumC0072hK == EnumC0072h.RESOURCE_CACHE || enumC0072hK == EnumC0072h.DATA_CACHE;
    }

    public void a() {
        this.F = true;
        com.bumptech.glide.load.n.f fVar = this.D;
        if (fVar != null) {
            fVar.cancel();
        }
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void b() {
        this.t = g.SWITCH_TO_SOURCE_SERVICE;
        this.q.d(this);
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void c(com.bumptech.glide.load.g gVar, Object obj, com.bumptech.glide.load.data.d<?> dVar, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.g gVar2) {
        this.y = gVar;
        this.A = obj;
        this.C = dVar;
        this.B = aVar;
        this.z = gVar2;
        if (Thread.currentThread() != this.x) {
            this.t = g.DECODE_DATA;
            this.q.d(this);
        } else {
            com.bumptech.glide.t.l.b.a("DecodeJob.decodeFromRetrievedData");
            try {
                i();
            } finally {
                com.bumptech.glide.t.l.b.d();
            }
        }
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void d(com.bumptech.glide.load.g gVar, Exception exc, com.bumptech.glide.load.data.d<?> dVar, com.bumptech.glide.load.a aVar) {
        dVar.b();
        q qVar = new q("Fetching data failed", exc);
        qVar.j(gVar, aVar, dVar.a());
        this.f2118c.add(qVar);
        if (Thread.currentThread() == this.x) {
            y();
        } else {
            this.t = g.SWITCH_TO_SOURCE_SERVICE;
            this.q.d(this);
        }
    }

    @Override // com.bumptech.glide.t.l.a.f
    public com.bumptech.glide.t.l.c e() {
        return this.f2119d;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(h<?> hVar) {
        int iM = m() - hVar.m();
        return iM == 0 ? this.r - hVar.r : iM;
    }

    h<R> n(com.bumptech.glide.e eVar, Object obj, n nVar, com.bumptech.glide.load.g gVar, int i, int i2, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar2, j jVar, Map<Class<?>, com.bumptech.glide.load.m<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.i iVar, b<R> bVar, int i3) {
        this.f2117b.u(eVar, obj, gVar, i, i2, jVar, cls, cls2, gVar2, iVar, map, z, z2, this.e);
        this.i = eVar;
        this.j = gVar;
        this.k = gVar2;
        this.l = nVar;
        this.m = i;
        this.n = i2;
        this.o = jVar;
        this.v = z3;
        this.p = iVar;
        this.q = bVar;
        this.r = i3;
        this.t = g.INITIALIZE;
        this.w = obj;
        return this;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.bumptech.glide.t.l.b.b("DecodeJob#run(model=%s)", this.w);
        com.bumptech.glide.load.data.d<?> dVar = this.C;
        try {
            try {
                try {
                    if (this.F) {
                        s();
                        if (dVar != null) {
                            dVar.b();
                        }
                        com.bumptech.glide.t.l.b.d();
                        return;
                    }
                    A();
                    if (dVar != null) {
                        dVar.b();
                    }
                    com.bumptech.glide.t.l.b.d();
                } catch (Throwable th) {
                    if (Log.isLoggable("DecodeJob", 3)) {
                        Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.F + ", stage: " + this.s, th);
                    }
                    if (this.s != EnumC0072h.ENCODE) {
                        this.f2118c.add(th);
                        s();
                    }
                    if (!this.F) {
                        throw th;
                    }
                    throw th;
                }
            } catch (com.bumptech.glide.load.n.b e2) {
                throw e2;
            }
        } catch (Throwable th2) {
            if (dVar != null) {
                dVar.b();
            }
            com.bumptech.glide.t.l.b.d();
            throw th2;
        }
    }

    <Z> v<Z> v(com.bumptech.glide.load.a aVar, v<Z> vVar) {
        v<Z> vVarB;
        com.bumptech.glide.load.m<Z> mVar;
        com.bumptech.glide.load.c cVarB;
        com.bumptech.glide.load.g dVar;
        Class<?> cls = vVar.b().getClass();
        com.bumptech.glide.load.l<Z> lVarN = null;
        if (aVar != com.bumptech.glide.load.a.RESOURCE_DISK_CACHE) {
            com.bumptech.glide.load.m<Z> mVarR = this.f2117b.r(cls);
            mVar = mVarR;
            vVarB = mVarR.b(this.i, vVar, this.m, this.n);
        } else {
            vVarB = vVar;
            mVar = null;
        }
        if (!vVar.equals(vVarB)) {
            vVar.f();
        }
        if (this.f2117b.v(vVarB)) {
            lVarN = this.f2117b.n(vVarB);
            cVarB = lVarN.b(this.p);
        } else {
            cVarB = com.bumptech.glide.load.c.NONE;
        }
        com.bumptech.glide.load.l lVar = lVarN;
        if (!this.o.d(!this.f2117b.x(this.y), aVar, cVarB)) {
            return vVarB;
        }
        if (lVar == null) {
            throw new i.d(vVarB.b().getClass());
        }
        int i = a.f2122c[cVarB.ordinal()];
        if (i == 1) {
            dVar = new com.bumptech.glide.load.n.d(this.y, this.j);
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("Unknown strategy: " + cVarB);
            }
            dVar = new x(this.f2117b.b(), this.y, this.j, this.m, this.n, mVar, cls, this.p);
        }
        u uVarG = u.g(vVarB);
        this.g.d(dVar, lVar, uVarG);
        return uVarG;
    }

    void w(boolean z) {
        if (this.h.d(z)) {
            x();
        }
    }
}
