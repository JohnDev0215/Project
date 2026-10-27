package spi;

import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import spi.es;
import spi.j8;
import spi.n5;
import spi.n8;
import spi.ta;
import spi.x5;

/* JADX INFO: loaded from: classes.dex */
public final class w5<R> implements n5.a, Runnable, Comparable<w5<?>>, ta.d {
    public DataSource A;
    public m5<?> B;
    public volatile n5 C;
    public volatile boolean D;
    public volatile boolean E;
    public boolean F;
    public final e d;
    public final Pools.Pool<w5<?>> e;
    public com.bumptech.glide.c h;
    public ig i;
    public Priority j;
    public l8 k;
    public int l;
    public int m;
    public i6 n;
    public lk o;
    public b<R> p;
    public int q;
    public h r;
    public g s;
    public long t;
    public boolean u;
    public Object v;
    public Thread w;
    public ig x;
    public ig y;
    public Object z;
    public final v5<R> a = new v5<>();
    public final List<Throwable> b = new ArrayList();
    public final es.b c = new es.b();
    public final d<?> f = new d<>();
    public final f g = new f();

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[EncodeStrategy.values().length];
            c = iArr;
            try {
                iArr[EncodeStrategy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[EncodeStrategy.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[h.values().length];
            b = iArr2;
            try {
                iArr2[h.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[h.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[h.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[h.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[h.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[g.values().length];
            a = iArr3;
            try {
                iArr3[g.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[g.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[g.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public interface b<R> {
    }

    public final class c<Z> implements x5.a<Z> {
        public final DataSource a;

        public c(DataSource dataSource) {
            this.a = dataSource;
        }
    }

    public static class d<Z> {
        public ig a;
        public en<Z> b;
        public bh<Z> c;
    }

    public interface e {
    }

    public static class f {
        public boolean a;
        public boolean b;
        public boolean c;

        public final boolean a() {
            return (this.c || this.b) && this.a;
        }
    }

    public enum g {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    public enum h {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    public w5(e eVar, Pools.Pool<w5<?>> pool) {
        this.d = eVar;
        this.e = pool;
    }

    @Override // spi.n5.a
    public final void a(ig igVar, Object obj, m5<?> m5Var, DataSource dataSource, ig igVar2) {
        this.x = igVar;
        this.z = obj;
        this.B = m5Var;
        this.A = dataSource;
        this.y = igVar2;
        this.F = igVar != ((ArrayList) this.a.a()).get(0);
        if (Thread.currentThread() == this.w) {
            g();
        } else {
            this.s = g.DECODE_DATA;
            ((j8) this.p).i(this);
        }
    }

    @Override // spi.ta.d
    @NonNull
    public final es b() {
        return this.c;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.n5.a
    public final void c(ig igVar, Exception exc, m5<?> m5Var, DataSource dataSource) {
        m5Var.b();
        GlideException glideException = new GlideException("Fetching data failed", exc);
        glideException.setLoggingDetails(igVar, dataSource, m5Var.a());
        this.b.add(glideException);
        if (Thread.currentThread() == this.w) {
            n();
        } else {
            this.s = g.SWITCH_TO_SOURCE_SERVICE;
            ((j8) this.p).i(this);
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(@NonNull w5<?> w5Var) {
        w5<?> w5Var2 = w5Var;
        int iOrdinal = this.j.ordinal() - w5Var2.j.ordinal();
        return iOrdinal == 0 ? this.q - w5Var2.q : iOrdinal;
    }

    @Override // spi.n5.a
    public final void d() {
        this.s = g.SWITCH_TO_SOURCE_SERVICE;
        ((j8) this.p).i(this);
    }

    public final <Data> wm<R> e(m5<?> m5Var, Data data, DataSource dataSource) throws GlideException {
        if (data == null) {
            return null;
        }
        try {
            int i = ch.b;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            wm<R> wmVarF = f(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                j("Decoded result " + wmVarF, jElapsedRealtimeNanos, null);
            }
            return wmVarF;
        } finally {
            m5Var.b();
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final <Data> wm<R> f(Data data, DataSource dataSource) throws GlideException {
        vg<Data, ?, R> vgVarD = this.a.d(data.getClass());
        lk lkVar = this.o;
        boolean z = dataSource == DataSource.RESOURCE_DISK_CACHE || this.a.r;
        jk<Boolean> jkVar = com.bumptech.glide.load.resource.bitmap.a.i;
        Boolean bool = (Boolean) lkVar.c(jkVar);
        if (bool == null || (bool.booleanValue() && !z)) {
            lkVar = new lk();
            lkVar.d(this.o);
            lkVar.b.put(jkVar, Boolean.valueOf(z));
        }
        lk lkVar2 = lkVar;
        com.bumptech.glide.load.data.a<Data> aVarG = this.h.b.g(data);
        try {
            return vgVarD.a(aVarG, lkVar2, this.l, this.m, new c(dataSource));
        } finally {
            aVarG.b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a6 A[Catch: all -> 0x00c3, DONT_GENERATE, TRY_ENTER, TryCatch #1 {, blocks: (B:12:0x0053, B:14:0x0057, B:15:0x005d, B:20:0x006a, B:21:0x006f, B:33:0x00a6, B:34:0x00a9, B:35:0x00ab, B:37:0x00b2, B:39:0x00b5, B:41:0x00ba, B:42:0x00bb, B:45:0x00bf, B:46:0x00c2, B:36:0x00ac, B:22:0x0076, B:26:0x007f, B:28:0x0097, B:30:0x009e, B:31:0x00a3), top: B:53:0x0053, inners: #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g() {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.w5.g():void");
    }

    public final n5 h() {
        int i = a.b[this.r.ordinal()];
        if (i == 1) {
            return new ym(this.a, this);
        }
        if (i == 2) {
            return new j5(this.a, this);
        }
        if (i == 3) {
            return new xr(this.a, this);
        }
        if (i == 4) {
            return null;
        }
        StringBuilder sbK = spi.e.k("Unrecognized stage: ");
        sbK.append(this.r);
        throw new IllegalStateException(sbK.toString());
    }

    public final h i(h hVar) {
        int i = a.b[hVar.ordinal()];
        if (i == 1) {
            return this.n.a() ? h.DATA_CACHE : i(h.DATA_CACHE);
        }
        if (i == 2) {
            return this.u ? h.FINISHED : h.SOURCE;
        }
        if (i == 3 || i == 4) {
            return h.FINISHED;
        }
        if (i == 5) {
            return this.n.b() ? h.RESOURCE_CACHE : i(h.RESOURCE_CACHE);
        }
        throw new IllegalArgumentException("Unrecognized stage: " + hVar);
    }

    public final void j(String str, long j, String str2) {
        StringBuilder sbM = spi.e.m(str, " in ");
        sbM.append(ch.a(j));
        sbM.append(", load key: ");
        sbM.append(this.k);
        sbM.append(str2 != null ? spi.d.f(", ", str2) : "");
        sbM.append(", thread: ");
        sbM.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sbM.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void k(wm<R> wmVar, DataSource dataSource, boolean z) {
        p();
        j8<?> j8Var = (j8) this.p;
        synchronized (j8Var) {
            j8Var.q = wmVar;
            j8Var.r = dataSource;
            j8Var.y = z;
        }
        synchronized (j8Var) {
            j8Var.b.b();
            if (j8Var.x) {
                j8Var.q.recycle();
                j8Var.g();
                return;
            }
            if (j8Var.a.isEmpty()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (j8Var.s) {
                throw new IllegalStateException("Already have resource");
            }
            j8.c cVar = j8Var.e;
            wm<?> wmVar2 = j8Var.q;
            boolean z2 = j8Var.m;
            ig igVar = j8Var.l;
            n8.a aVar = j8Var.c;
            Objects.requireNonNull(cVar);
            j8Var.v = new n8<>(wmVar2, z2, true, igVar, aVar);
            j8Var.s = true;
            j8.e eVar = j8Var.a;
            Objects.requireNonNull(eVar);
            j8.e eVar2 = new j8.e(new ArrayList(eVar.a));
            j8Var.e(eVar2.a.size() + 1);
            ((i8) j8Var.f).e(j8Var, j8Var.l, j8Var.v);
            for (j8.d dVar : eVar2) {
                dVar.b.execute(new j8.b(dVar.a));
            }
            j8Var.d();
        }
    }

    public final void l() {
        boolean zA;
        p();
        GlideException glideException = new GlideException("Failed to load resource", new ArrayList(this.b));
        j8<?> j8Var = (j8) this.p;
        synchronized (j8Var) {
            j8Var.t = glideException;
        }
        synchronized (j8Var) {
            j8Var.b.b();
            if (j8Var.x) {
                j8Var.g();
            } else {
                if (j8Var.a.isEmpty()) {
                    throw new IllegalStateException("Received an exception without any callbacks to notify");
                }
                if (j8Var.u) {
                    throw new IllegalStateException("Already failed once");
                }
                j8Var.u = true;
                ig igVar = j8Var.l;
                j8.e eVar = j8Var.a;
                Objects.requireNonNull(eVar);
                j8.e eVar2 = new j8.e(new ArrayList(eVar.a));
                j8Var.e(eVar2.a.size() + 1);
                ((i8) j8Var.f).e(j8Var, igVar, null);
                for (j8.d dVar : eVar2) {
                    dVar.b.execute(new j8.a(dVar.a));
                }
                j8Var.d();
            }
        }
        f fVar = this.g;
        synchronized (fVar) {
            fVar.c = true;
            zA = fVar.a();
        }
        if (zA) {
            m();
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<java.lang.Throwable>] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.ArrayList, java.util.List<spi.ti$a<?>>] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.ArrayList, java.util.List<spi.ig>] */
    public final void m() {
        f fVar = this.g;
        synchronized (fVar) {
            fVar.b = false;
            fVar.a = false;
            fVar.c = false;
        }
        d<?> dVar = this.f;
        dVar.a = null;
        dVar.b = null;
        dVar.c = null;
        v5<R> v5Var = this.a;
        v5Var.c = null;
        v5Var.d = null;
        v5Var.n = null;
        v5Var.g = null;
        v5Var.k = null;
        v5Var.i = null;
        v5Var.o = null;
        v5Var.j = null;
        v5Var.p = null;
        v5Var.a.clear();
        v5Var.l = false;
        v5Var.b.clear();
        v5Var.m = false;
        this.D = false;
        this.h = null;
        this.i = null;
        this.o = null;
        this.j = null;
        this.k = null;
        this.p = null;
        this.r = null;
        this.C = null;
        this.w = null;
        this.x = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.t = 0L;
        this.E = false;
        this.v = null;
        this.b.clear();
        this.e.release(this);
    }

    public final void n() {
        this.w = Thread.currentThread();
        int i = ch.b;
        this.t = SystemClock.elapsedRealtimeNanos();
        boolean zB = false;
        while (!this.E && this.C != null && !(zB = this.C.b())) {
            this.r = i(this.r);
            this.C = h();
            if (this.r == h.SOURCE) {
                this.s = g.SWITCH_TO_SOURCE_SERVICE;
                ((j8) this.p).i(this);
                return;
            }
        }
        if ((this.r == h.FINISHED || this.E) && !zB) {
            l();
        }
    }

    public final void o() {
        int i = a.a[this.s.ordinal()];
        if (i == 1) {
            this.r = i(h.INITIALIZE);
            this.C = h();
        } else if (i != 2) {
            if (i == 3) {
                g();
                return;
            } else {
                StringBuilder sbK = spi.e.k("Unrecognized run reason: ");
                sbK.append(this.s);
                throw new IllegalStateException(sbK.toString());
            }
        }
        n();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<java.lang.Throwable>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<java.lang.Throwable>] */
    public final void p() {
        Throwable th;
        this.c.b();
        if (!this.D) {
            this.D = true;
            return;
        }
        if (this.b.isEmpty()) {
            th = null;
        } else {
            ?? r0 = this.b;
            th = (Throwable) r0.get(r0.size() - 1);
        }
        throw new IllegalStateException("Already notified", th);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.ArrayList, java.util.List<java.lang.Throwable>] */
    @Override // java.lang.Runnable
    public final void run() {
        m5<?> m5Var = this.B;
        try {
            try {
                if (this.E) {
                    l();
                    if (m5Var != null) {
                        m5Var.b();
                        return;
                    }
                    return;
                }
                o();
                if (m5Var != null) {
                    m5Var.b();
                }
            } catch (f3 e2) {
                throw e2;
            } catch (Throwable th) {
                if (Log.isLoggable("DecodeJob", 3)) {
                    Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.E + ", stage: " + this.r, th);
                }
                if (this.r != h.ENCODE) {
                    this.b.add(th);
                    l();
                }
                if (!this.E) {
                    throw th;
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (m5Var != null) {
                m5Var.b();
            }
            throw th2;
        }
    }
}
