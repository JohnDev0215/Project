package com.bumptech.glide.load.engine;

import android.os.Build;
import android.util.Log;
import com.android.volley.BuildConfig;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.m.l.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class DecodeJob<R> implements e.a, Runnable, Comparable<DecodeJob<?>>, a.f {
    private Object A;
    private DataSource B;
    private com.bumptech.glide.load.i.d<?> C;
    private volatile com.bumptech.glide.load.engine.e D;
    private volatile boolean E;
    private volatile boolean F;
    private final e e;
    private final c.g.j.e<DecodeJob<?>> f;
    private com.bumptech.glide.d i;
    private com.bumptech.glide.load.c j;
    private Priority k;
    private l l;
    private int m;
    private int n;
    private h o;
    private com.bumptech.glide.load.e p;
    private b<R> q;
    private int r;
    private Stage s;
    private RunReason t;
    private long u;
    private boolean v;
    private Object w;
    private Thread x;
    private com.bumptech.glide.load.c y;
    private com.bumptech.glide.load.c z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.f<R> f2739b = new com.bumptech.glide.load.engine.f<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Throwable> f2740c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.m.l.c f2741d = com.bumptech.glide.m.l.c.a();
    private final d<?> g = new d<>();
    private final f h = new f();

    private enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    private enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2744a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2745b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f2746c;

        static {
            int[] iArr = new int[EncodeStrategy.values().length];
            f2746c = iArr;
            try {
                iArr[EncodeStrategy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2746c[EncodeStrategy.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Stage.values().length];
            f2745b = iArr2;
            try {
                iArr2[Stage.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2745b[Stage.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2745b[Stage.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2745b[Stage.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2745b[Stage.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[RunReason.values().length];
            f2744a = iArr3;
            try {
                iArr3[RunReason.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2744a[RunReason.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2744a[RunReason.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    interface b<R> {
        void a(GlideException glideException);

        void c(s<R> sVar, DataSource dataSource);

        void e(DecodeJob<?> decodeJob);
    }

    private final class c<Z> implements g.a<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final DataSource f2747a;

        c(DataSource dataSource) {
            this.f2747a = dataSource;
        }

        @Override // com.bumptech.glide.load.engine.g.a
        public s<Z> a(s<Z> sVar) {
            return DecodeJob.this.v(this.f2747a, sVar);
        }
    }

    private static class d<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private com.bumptech.glide.load.c f2749a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.bumptech.glide.load.g<Z> f2750b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private r<Z> f2751c;

        d() {
        }

        void a() {
            this.f2749a = null;
            this.f2750b = null;
            this.f2751c = null;
        }

        void b(e eVar, com.bumptech.glide.load.e eVar2) {
            com.bumptech.glide.m.l.b.a("DecodeJob.encode");
            try {
                eVar.a().a(this.f2749a, new com.bumptech.glide.load.engine.d(this.f2750b, this.f2751c, eVar2));
            } finally {
                this.f2751c.h();
                com.bumptech.glide.m.l.b.d();
            }
        }

        boolean c() {
            return this.f2751c != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        <X> void d(com.bumptech.glide.load.c cVar, com.bumptech.glide.load.g<X> gVar, r<X> rVar) {
            this.f2749a = cVar;
            this.f2750b = gVar;
            this.f2751c = rVar;
        }
    }

    interface e {
        com.bumptech.glide.load.engine.y.a a();
    }

    private static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2752a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f2753b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2754c;

        f() {
        }

        private boolean a(boolean z) {
            return (this.f2754c || z || this.f2753b) && this.f2752a;
        }

        synchronized boolean b() {
            this.f2753b = true;
            return a(false);
        }

        synchronized boolean c() {
            this.f2754c = true;
            return a(false);
        }

        synchronized boolean d(boolean z) {
            this.f2752a = true;
            return a(z);
        }

        synchronized void e() {
            this.f2753b = false;
            this.f2752a = false;
            this.f2754c = false;
        }
    }

    DecodeJob(e eVar, c.g.j.e<DecodeJob<?>> eVar2) {
        this.e = eVar;
        this.f = eVar2;
    }

    private void A() {
        int i = a.f2744a[this.t.ordinal()];
        if (i == 1) {
            this.s = k(Stage.INITIALIZE);
            this.D = j();
            y();
        } else if (i == 2) {
            y();
        } else {
            if (i == 3) {
                i();
                return;
            }
            throw new IllegalStateException("Unrecognized run reason: " + this.t);
        }
    }

    private void B() {
        Throwable th;
        this.f2741d.c();
        if (!this.E) {
            this.E = true;
            return;
        }
        if (this.f2740c.isEmpty()) {
            th = null;
        } else {
            List<Throwable> list = this.f2740c;
            th = list.get(list.size() - 1);
        }
        throw new IllegalStateException("Already notified", th);
    }

    private <Data> s<R> g(com.bumptech.glide.load.i.d<?> dVar, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            return null;
        }
        try {
            long jB = com.bumptech.glide.m.f.b();
            s<R> sVarH = h(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                o("Decoded result " + sVarH, jB);
            }
            return sVarH;
        } finally {
            dVar.b();
        }
    }

    private <Data> s<R> h(Data data, DataSource dataSource) throws GlideException {
        return z(data, dataSource, this.f2739b.h(data.getClass()));
    }

    private void i() {
        if (Log.isLoggable("DecodeJob", 2)) {
            p("Retrieved data", this.u, "data: " + this.A + ", cache key: " + this.y + ", fetcher: " + this.C);
        }
        s<R> sVarG = null;
        try {
            sVarG = g(this.C, this.A, this.B);
        } catch (GlideException e2) {
            e2.setLoggingDetails(this.z, this.B);
            this.f2740c.add(e2);
        }
        if (sVarG != null) {
            r(sVarG, this.B);
        } else {
            y();
        }
    }

    private com.bumptech.glide.load.engine.e j() {
        int i = a.f2745b[this.s.ordinal()];
        if (i == 1) {
            return new t(this.f2739b, this);
        }
        if (i == 2) {
            return new com.bumptech.glide.load.engine.b(this.f2739b, this);
        }
        if (i == 3) {
            return new w(this.f2739b, this);
        }
        if (i == 4) {
            return null;
        }
        throw new IllegalStateException("Unrecognized stage: " + this.s);
    }

    private Stage k(Stage stage) {
        int i = a.f2745b[stage.ordinal()];
        if (i == 1) {
            return this.o.a() ? Stage.DATA_CACHE : k(Stage.DATA_CACHE);
        }
        if (i == 2) {
            return this.v ? Stage.FINISHED : Stage.SOURCE;
        }
        if (i == 3 || i == 4) {
            return Stage.FINISHED;
        }
        if (i == 5) {
            return this.o.b() ? Stage.RESOURCE_CACHE : k(Stage.RESOURCE_CACHE);
        }
        throw new IllegalArgumentException("Unrecognized stage: " + stage);
    }

    private com.bumptech.glide.load.e l(DataSource dataSource) {
        com.bumptech.glide.load.e eVar = this.p;
        if (Build.VERSION.SDK_INT < 26) {
            return eVar;
        }
        boolean z = dataSource == DataSource.RESOURCE_DISK_CACHE || this.f2739b.w();
        com.bumptech.glide.load.d<Boolean> dVar = com.bumptech.glide.load.resource.bitmap.h.e;
        Boolean bool = (Boolean) eVar.c(dVar);
        if (bool != null && (!bool.booleanValue() || z)) {
            return eVar;
        }
        com.bumptech.glide.load.e eVar2 = new com.bumptech.glide.load.e();
        eVar2.d(this.p);
        eVar2.e(dVar, Boolean.valueOf(z));
        return eVar2;
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
        sb.append(com.bumptech.glide.m.f.a(j));
        sb.append(", load key: ");
        sb.append(this.l);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = BuildConfig.FLAVOR;
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sb.toString());
    }

    private void q(s<R> sVar, DataSource dataSource) {
        B();
        this.q.c(sVar, dataSource);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void r(s<R> sVar, DataSource dataSource) {
        if (sVar instanceof o) {
            ((o) sVar).initialize();
        }
        r rVar = 0;
        if (this.g.c()) {
            sVar = r.f(sVar);
            rVar = sVar;
        }
        q(sVar, dataSource);
        this.s = Stage.ENCODE;
        try {
            if (this.g.c()) {
                this.g.b(this.e, this.p);
            }
            t();
        } finally {
            if (rVar != 0) {
                rVar.h();
            }
        }
    }

    private void s() {
        B();
        this.q.a(new GlideException("Failed to load resource", new ArrayList(this.f2740c)));
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
        this.f2739b.a();
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
        this.f2740c.clear();
        this.f.a(this);
    }

    private void y() {
        this.x = Thread.currentThread();
        this.u = com.bumptech.glide.m.f.b();
        boolean zE = false;
        while (!this.F && this.D != null && !(zE = this.D.e())) {
            this.s = k(this.s);
            this.D = j();
            if (this.s == Stage.SOURCE) {
                a();
                return;
            }
        }
        if ((this.s == Stage.FINISHED || this.F) && !zE) {
            s();
        }
    }

    private <Data, ResourceType> s<R> z(Data data, DataSource dataSource, q<Data, ResourceType, R> qVar) throws GlideException {
        com.bumptech.glide.load.e eVarL = l(dataSource);
        com.bumptech.glide.load.i.e<Data> eVarL2 = this.i.g().l(data);
        try {
            return qVar.a(eVarL2, eVarL, this.m, this.n, new c(dataSource));
        } finally {
            eVarL2.b();
        }
    }

    boolean C() {
        Stage stageK = k(Stage.INITIALIZE);
        return stageK == Stage.RESOURCE_CACHE || stageK == Stage.DATA_CACHE;
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void a() {
        this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.q.e(this);
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void b(com.bumptech.glide.load.c cVar, Exception exc, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource) {
        dVar.b();
        GlideException glideException = new GlideException("Fetching data failed", exc);
        glideException.setLoggingDetails(cVar, dataSource, dVar.a());
        this.f2740c.add(glideException);
        if (Thread.currentThread() == this.x) {
            y();
        } else {
            this.t = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.q.e(this);
        }
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void c(com.bumptech.glide.load.c cVar, Object obj, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource, com.bumptech.glide.load.c cVar2) {
        this.y = cVar;
        this.A = obj;
        this.C = dVar;
        this.B = dataSource;
        this.z = cVar2;
        if (Thread.currentThread() != this.x) {
            this.t = RunReason.DECODE_DATA;
            this.q.e(this);
        } else {
            com.bumptech.glide.m.l.b.a("DecodeJob.decodeFromRetrievedData");
            try {
                i();
            } finally {
                com.bumptech.glide.m.l.b.d();
            }
        }
    }

    @Override // com.bumptech.glide.m.l.a.f
    public com.bumptech.glide.m.l.c d() {
        return this.f2741d;
    }

    public void e() {
        this.F = true;
        com.bumptech.glide.load.engine.e eVar = this.D;
        if (eVar != null) {
            eVar.cancel();
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(DecodeJob<?> decodeJob) {
        int iM = m() - decodeJob.m();
        return iM == 0 ? this.r - decodeJob.r : iM;
    }

    DecodeJob<R> n(com.bumptech.glide.d dVar, Object obj, l lVar, com.bumptech.glide.load.c cVar, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, h hVar, Map<Class<?>, com.bumptech.glide.load.h<?>> map, boolean z, boolean z2, boolean z3, com.bumptech.glide.load.e eVar, b<R> bVar, int i3) {
        this.f2739b.u(dVar, obj, cVar, i, i2, hVar, cls, cls2, priority, eVar, map, z, z2, this.e);
        this.i = dVar;
        this.j = cVar;
        this.k = priority;
        this.l = lVar;
        this.m = i;
        this.n = i2;
        this.o = hVar;
        this.v = z3;
        this.p = eVar;
        this.q = bVar;
        this.r = i3;
        this.t = RunReason.INITIALIZE;
        this.w = obj;
        return this;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.bumptech.glide.m.l.b.b("DecodeJob#run(model=%s)", this.w);
        com.bumptech.glide.load.i.d<?> dVar = this.C;
        try {
            try {
                try {
                    if (this.F) {
                        s();
                        if (dVar != null) {
                            dVar.b();
                        }
                        com.bumptech.glide.m.l.b.d();
                        return;
                    }
                    A();
                    if (dVar != null) {
                        dVar.b();
                    }
                    com.bumptech.glide.m.l.b.d();
                } catch (Throwable th) {
                    if (Log.isLoggable("DecodeJob", 3)) {
                        Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.F + ", stage: " + this.s, th);
                    }
                    if (this.s != Stage.ENCODE) {
                        this.f2740c.add(th);
                        s();
                    }
                    if (!this.F) {
                        throw th;
                    }
                    throw th;
                }
            } catch (CallbackException e2) {
                throw e2;
            }
        } catch (Throwable th2) {
            if (dVar != null) {
                dVar.b();
            }
            com.bumptech.glide.m.l.b.d();
            throw th2;
        }
    }

    <Z> s<Z> v(DataSource dataSource, s<Z> sVar) {
        s<Z> sVarA;
        com.bumptech.glide.load.h<Z> hVar;
        EncodeStrategy encodeStrategyB;
        com.bumptech.glide.load.c cVar;
        Class<?> cls = sVar.get().getClass();
        com.bumptech.glide.load.g<Z> gVarN = null;
        if (dataSource != DataSource.RESOURCE_DISK_CACHE) {
            com.bumptech.glide.load.h<Z> hVarR = this.f2739b.r(cls);
            hVar = hVarR;
            sVarA = hVarR.a(this.i, sVar, this.m, this.n);
        } else {
            sVarA = sVar;
            hVar = null;
        }
        if (!sVar.equals(sVarA)) {
            sVar.a();
        }
        if (this.f2739b.v(sVarA)) {
            gVarN = this.f2739b.n(sVarA);
            encodeStrategyB = gVarN.b(this.p);
        } else {
            encodeStrategyB = EncodeStrategy.NONE;
        }
        com.bumptech.glide.load.g gVar = gVarN;
        if (!this.o.d(!this.f2739b.x(this.y), dataSource, encodeStrategyB)) {
            return sVarA;
        }
        if (gVar == null) {
            throw new Registry.NoResultEncoderAvailableException(sVarA.get().getClass());
        }
        int i = a.f2746c[encodeStrategyB.ordinal()];
        if (i == 1) {
            cVar = new com.bumptech.glide.load.engine.c(this.y, this.j);
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("Unknown strategy: " + encodeStrategyB);
            }
            cVar = new u(this.f2739b.b(), this.y, this.j, this.m, this.n, hVar, cls, this.p);
        }
        r rVarF = r.f(sVarA);
        this.g.d(cVar, gVar, rVarF);
        return rVarF;
    }

    void w(boolean z) {
        if (this.h.d(z)) {
            x();
        }
    }
}
