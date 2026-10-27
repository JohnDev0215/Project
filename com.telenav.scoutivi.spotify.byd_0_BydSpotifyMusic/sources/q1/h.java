package q1;

import android.util.Log;
import com.bumptech.glide.i;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import l2.a;
import q1.f;
import q1.i;

/* JADX INFO: compiled from: DecodeJob.java */
/* JADX INFO: loaded from: classes.dex */
class h<R> implements f.a, Runnable, Comparable<h<?>>, a.f {
    private Object A;
    private Thread B;
    private n1.f C;
    private n1.f D;
    private Object E;
    private n1.a F;
    private o1.d<?> G;
    private volatile q1.f H;
    private volatile boolean I;
    private volatile boolean J;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final e f8985i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final h0.e<h<?>> f8986j;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private com.bumptech.glide.e f8989m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private n1.f f8990n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private com.bumptech.glide.g f8991o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private n f8992p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f8993q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f8994r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private j f8995s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private n1.i f8996t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private b<R> f8997u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f8998v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private EnumC0139h f8999w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private g f9000x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private long f9001y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f9002z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final q1.g<R> f8982e = new q1.g<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<Throwable> f8983g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final l2.c f8984h = l2.c.a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final d<?> f8987k = new d<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final f f8988l = new f();

    /* JADX INFO: compiled from: DecodeJob.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9003a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f9004b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f9005c;

        static {
            int[] iArr = new int[n1.c.values().length];
            f9005c = iArr;
            try {
                iArr[n1.c.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9005c[n1.c.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[EnumC0139h.values().length];
            f9004b = iArr2;
            try {
                iArr2[EnumC0139h.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9004b[EnumC0139h.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9004b[EnumC0139h.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9004b[EnumC0139h.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9004b[EnumC0139h.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[g.values().length];
            f9003a = iArr3;
            try {
                iArr3[g.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9003a[g.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9003a[g.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    interface b<R> {
        void a(v<R> vVar, n1.a aVar);

        void b(q qVar);

        void c(h<?> hVar);
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    private final class c<Z> implements i.a<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final n1.a f9006a;

        c(n1.a aVar) {
            this.f9006a = aVar;
        }

        @Override // q1.i.a
        public v<Z> a(v<Z> vVar) {
            return h.this.v(this.f9006a, vVar);
        }
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    private static class d<Z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private n1.f f9008a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private n1.l<Z> f9009b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private u<Z> f9010c;

        d() {
        }

        void a() {
            this.f9008a = null;
            this.f9009b = null;
            this.f9010c = null;
        }

        void b(e eVar, n1.i iVar) {
            l2.b.a("DecodeJob.encode");
            try {
                eVar.a().a(this.f9008a, new q1.e(this.f9009b, this.f9010c, iVar));
            } finally {
                this.f9010c.h();
                l2.b.d();
            }
        }

        boolean c() {
            return this.f9010c != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        <X> void d(n1.f fVar, n1.l<X> lVar, u<X> uVar) {
            this.f9008a = fVar;
            this.f9009b = lVar;
            this.f9010c = uVar;
        }
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    interface e {
        s1.a a();
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    private static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f9011a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f9012b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f9013c;

        f() {
        }

        private boolean a(boolean z6) {
            return (this.f9013c || z6 || this.f9012b) && this.f9011a;
        }

        synchronized boolean b() {
            this.f9012b = true;
            return a(false);
        }

        synchronized boolean c() {
            this.f9013c = true;
            return a(false);
        }

        synchronized boolean d(boolean z6) {
            this.f9011a = true;
            return a(z6);
        }

        synchronized void e() {
            this.f9012b = false;
            this.f9011a = false;
            this.f9013c = false;
        }
    }

    /* JADX INFO: compiled from: DecodeJob.java */
    private enum g {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    /* JADX INFO: renamed from: q1.h$h, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DecodeJob.java */
    private enum EnumC0139h {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    h(e eVar, h0.e<h<?>> eVar2) {
        this.f8985i = eVar;
        this.f8986j = eVar2;
    }

    private void A() {
        int i7 = a.f9003a[this.f9000x.ordinal()];
        if (i7 == 1) {
            this.f8999w = k(EnumC0139h.INITIALIZE);
            this.H = j();
            y();
        } else if (i7 == 2) {
            y();
        } else {
            if (i7 == 3) {
                i();
                return;
            }
            throw new IllegalStateException("Unrecognized run reason: " + this.f9000x);
        }
    }

    private void B() {
        Throwable th;
        this.f8984h.c();
        if (!this.I) {
            this.I = true;
            return;
        }
        if (this.f8983g.isEmpty()) {
            th = null;
        } else {
            List<Throwable> list = this.f8983g;
            th = list.get(list.size() - 1);
        }
        throw new IllegalStateException("Already notified", th);
    }

    private <Data> v<R> g(o1.d<?> dVar, Data data, n1.a aVar) {
        if (data == null) {
            return null;
        }
        try {
            long jB = k2.f.b();
            v<R> vVarH = h(data, aVar);
            if (Log.isLoggable("DecodeJob", 2)) {
                o("Decoded result " + vVarH, jB);
            }
            return vVarH;
        } finally {
            dVar.b();
        }
    }

    private <Data> v<R> h(Data data, n1.a aVar) {
        return z(data, aVar, this.f8982e.h(data.getClass()));
    }

    private void i() {
        if (Log.isLoggable("DecodeJob", 2)) {
            p("Retrieved data", this.f9001y, "data: " + this.E + ", cache key: " + this.C + ", fetcher: " + this.G);
        }
        v<R> vVarG = null;
        try {
            vVarG = g(this.G, this.E, this.F);
        } catch (q e7) {
            e7.i(this.D, this.F);
            this.f8983g.add(e7);
        }
        if (vVarG != null) {
            r(vVarG, this.F);
        } else {
            y();
        }
    }

    private q1.f j() {
        int i7 = a.f9004b[this.f8999w.ordinal()];
        if (i7 == 1) {
            return new w(this.f8982e, this);
        }
        if (i7 == 2) {
            return new q1.c(this.f8982e, this);
        }
        if (i7 == 3) {
            return new z(this.f8982e, this);
        }
        if (i7 == 4) {
            return null;
        }
        throw new IllegalStateException("Unrecognized stage: " + this.f8999w);
    }

    private EnumC0139h k(EnumC0139h enumC0139h) {
        int i7 = a.f9004b[enumC0139h.ordinal()];
        if (i7 == 1) {
            return this.f8995s.a() ? EnumC0139h.DATA_CACHE : k(EnumC0139h.DATA_CACHE);
        }
        if (i7 == 2) {
            return this.f9002z ? EnumC0139h.FINISHED : EnumC0139h.SOURCE;
        }
        if (i7 == 3 || i7 == 4) {
            return EnumC0139h.FINISHED;
        }
        if (i7 == 5) {
            return this.f8995s.b() ? EnumC0139h.RESOURCE_CACHE : k(EnumC0139h.RESOURCE_CACHE);
        }
        throw new IllegalArgumentException("Unrecognized stage: " + enumC0139h);
    }

    private n1.i l(n1.a aVar) {
        n1.i iVar = this.f8996t;
        boolean z6 = aVar == n1.a.RESOURCE_DISK_CACHE || this.f8982e.w();
        n1.h<Boolean> hVar = x1.m.f9778j;
        Boolean bool = (Boolean) iVar.c(hVar);
        if (bool != null && (!bool.booleanValue() || z6)) {
            return iVar;
        }
        n1.i iVar2 = new n1.i();
        iVar2.d(this.f8996t);
        iVar2.e(hVar, Boolean.valueOf(z6));
        return iVar2;
    }

    private int m() {
        return this.f8991o.ordinal();
    }

    private void o(String str, long j7) {
        p(str, j7, null);
    }

    private void p(String str, long j7, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(k2.f.a(j7));
        sb.append(", load key: ");
        sb.append(this.f8992p);
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

    private void q(v<R> vVar, n1.a aVar) {
        B();
        this.f8997u.a(vVar, aVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void r(v<R> vVar, n1.a aVar) {
        if (vVar instanceof r) {
            ((r) vVar).b();
        }
        u uVar = 0;
        if (this.f8987k.c()) {
            vVar = u.f(vVar);
            uVar = vVar;
        }
        q(vVar, aVar);
        this.f8999w = EnumC0139h.ENCODE;
        try {
            if (this.f8987k.c()) {
                this.f8987k.b(this.f8985i, this.f8996t);
            }
            t();
        } finally {
            if (uVar != 0) {
                uVar.h();
            }
        }
    }

    private void s() {
        B();
        this.f8997u.b(new q("Failed to load resource", new ArrayList(this.f8983g)));
        u();
    }

    private void t() {
        if (this.f8988l.b()) {
            x();
        }
    }

    private void u() {
        if (this.f8988l.c()) {
            x();
        }
    }

    private void x() {
        this.f8988l.e();
        this.f8987k.a();
        this.f8982e.a();
        this.I = false;
        this.f8989m = null;
        this.f8990n = null;
        this.f8996t = null;
        this.f8991o = null;
        this.f8992p = null;
        this.f8997u = null;
        this.f8999w = null;
        this.H = null;
        this.B = null;
        this.C = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.f9001y = 0L;
        this.J = false;
        this.A = null;
        this.f8983g.clear();
        this.f8986j.a(this);
    }

    private void y() {
        this.B = Thread.currentThread();
        this.f9001y = k2.f.b();
        boolean zB = false;
        while (!this.J && this.H != null && !(zB = this.H.b())) {
            this.f8999w = k(this.f8999w);
            this.H = j();
            if (this.f8999w == EnumC0139h.SOURCE) {
                c();
                return;
            }
        }
        if ((this.f8999w == EnumC0139h.FINISHED || this.J) && !zB) {
            s();
        }
    }

    private <Data, ResourceType> v<R> z(Data data, n1.a aVar, t<Data, ResourceType, R> tVar) {
        n1.i iVarL = l(aVar);
        o1.e<Data> eVarL = this.f8989m.h().l(data);
        try {
            return tVar.a(eVarL, iVarL, this.f8993q, this.f8994r, new c(aVar));
        } finally {
            eVarL.b();
        }
    }

    boolean C() {
        EnumC0139h enumC0139hK = k(EnumC0139h.INITIALIZE);
        return enumC0139hK == EnumC0139h.RESOURCE_CACHE || enumC0139hK == EnumC0139h.DATA_CACHE;
    }

    @Override // q1.f.a
    public void a(n1.f fVar, Object obj, o1.d<?> dVar, n1.a aVar, n1.f fVar2) {
        this.C = fVar;
        this.E = obj;
        this.G = dVar;
        this.F = aVar;
        this.D = fVar2;
        if (Thread.currentThread() != this.B) {
            this.f9000x = g.DECODE_DATA;
            this.f8997u.c(this);
        } else {
            l2.b.a("DecodeJob.decodeFromRetrievedData");
            try {
                i();
            } finally {
                l2.b.d();
            }
        }
    }

    public void b() {
        this.J = true;
        q1.f fVar = this.H;
        if (fVar != null) {
            fVar.cancel();
        }
    }

    @Override // q1.f.a
    public void c() {
        this.f9000x = g.SWITCH_TO_SOURCE_SERVICE;
        this.f8997u.c(this);
    }

    @Override // l2.a.f
    public l2.c d() {
        return this.f8984h;
    }

    @Override // q1.f.a
    public void e(n1.f fVar, Exception exc, o1.d<?> dVar, n1.a aVar) {
        dVar.b();
        q qVar = new q("Fetching data failed", exc);
        qVar.j(fVar, aVar, dVar.a());
        this.f8983g.add(qVar);
        if (Thread.currentThread() == this.B) {
            y();
        } else {
            this.f9000x = g.SWITCH_TO_SOURCE_SERVICE;
            this.f8997u.c(this);
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(h<?> hVar) {
        int iM = m() - hVar.m();
        return iM == 0 ? this.f8998v - hVar.f8998v : iM;
    }

    h<R> n(com.bumptech.glide.e eVar, Object obj, n nVar, n1.f fVar, int i7, int i8, Class<?> cls, Class<R> cls2, com.bumptech.glide.g gVar, j jVar, Map<Class<?>, n1.m<?>> map, boolean z6, boolean z7, boolean z8, n1.i iVar, b<R> bVar, int i9) {
        this.f8982e.u(eVar, obj, fVar, i7, i8, jVar, cls, cls2, gVar, iVar, map, z6, z7, this.f8985i);
        this.f8989m = eVar;
        this.f8990n = fVar;
        this.f8991o = gVar;
        this.f8992p = nVar;
        this.f8993q = i7;
        this.f8994r = i8;
        this.f8995s = jVar;
        this.f9002z = z8;
        this.f8996t = iVar;
        this.f8997u = bVar;
        this.f8998v = i9;
        this.f9000x = g.INITIALIZE;
        this.A = obj;
        return this;
    }

    @Override // java.lang.Runnable
    public void run() {
        l2.b.b("DecodeJob#run(model=%s)", this.A);
        o1.d<?> dVar = this.G;
        try {
            try {
                try {
                    if (this.J) {
                        s();
                        if (dVar != null) {
                            dVar.b();
                        }
                        l2.b.d();
                        return;
                    }
                    A();
                    if (dVar != null) {
                        dVar.b();
                    }
                    l2.b.d();
                } catch (q1.b e7) {
                    throw e7;
                }
            } catch (Throwable th) {
                if (Log.isLoggable("DecodeJob", 3)) {
                    Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.J + ", stage: " + this.f8999w, th);
                }
                if (this.f8999w != EnumC0139h.ENCODE) {
                    this.f8983g.add(th);
                    s();
                }
                if (!this.J) {
                    throw th;
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (dVar != null) {
                dVar.b();
            }
            l2.b.d();
            throw th2;
        }
    }

    <Z> v<Z> v(n1.a aVar, v<Z> vVar) {
        v<Z> vVarB;
        n1.m<Z> mVar;
        n1.c cVarA;
        n1.f dVar;
        Class<?> cls = vVar.get().getClass();
        n1.l<Z> lVarN = null;
        if (aVar != n1.a.RESOURCE_DISK_CACHE) {
            n1.m<Z> mVarR = this.f8982e.r(cls);
            mVar = mVarR;
            vVarB = mVarR.b(this.f8989m, vVar, this.f8993q, this.f8994r);
        } else {
            vVarB = vVar;
            mVar = null;
        }
        if (!vVar.equals(vVarB)) {
            vVar.a();
        }
        if (this.f8982e.v(vVarB)) {
            lVarN = this.f8982e.n(vVarB);
            cVarA = lVarN.a(this.f8996t);
        } else {
            cVarA = n1.c.NONE;
        }
        n1.l lVar = lVarN;
        if (!this.f8995s.d(!this.f8982e.x(this.C), aVar, cVarA)) {
            return vVarB;
        }
        if (lVar == null) {
            throw new i.d(vVarB.get().getClass());
        }
        int i7 = a.f9005c[cVarA.ordinal()];
        if (i7 == 1) {
            dVar = new q1.d(this.C, this.f8990n);
        } else {
            if (i7 != 2) {
                throw new IllegalArgumentException("Unknown strategy: " + cVarA);
            }
            dVar = new x(this.f8982e.b(), this.C, this.f8990n, this.f8993q, this.f8994r, mVar, cls, this.f8996t);
        }
        u uVarF = u.f(vVarB);
        this.f8987k.d(dVar, lVar, uVarF);
        return uVarF;
    }

    void w(boolean z6) {
        if (this.f8988l.d(z6)) {
            x();
        }
    }
}
