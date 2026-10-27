package f.k0.k;

import f.k0.k.h;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ExecutorService f5313b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), f.k0.e.H("OkHttp Http2Connection", true));
    final Set<Integer> A;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f5314c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final j f5315d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final String f5317f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f5318g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f5319h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f5320i;
    private final ScheduledExecutorService j;
    private final ExecutorService k;
    final f.k0.k.l l;
    long u;
    final m w;
    final Socket x;
    final f.k0.k.j y;
    final l z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Map<Integer, f.k0.k.i> f5316e = new LinkedHashMap();
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private long p = 0;
    private long q = 0;
    private long r = 0;
    private long s = 0;
    long t = 0;
    m v = new m();

    /* JADX INFO: compiled from: Http2Connection.java */
    class a extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ f.k0.k.b f5322d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, int i2, f.k0.k.b bVar) {
            super(str, objArr);
            this.f5321c = i2;
            this.f5322d = bVar;
        }

        @Override // f.k0.d
        public void k() {
            try {
                f.this.t0(this.f5321c, this.f5322d);
            } catch (IOException e2) {
                f.this.Y(e2);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class b extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5324c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ long f5325d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, int i2, long j) {
            super(str, objArr);
            this.f5324c = i2;
            this.f5325d = j;
        }

        @Override // f.k0.d
        public void k() {
            try {
                f.this.y.Y(this.f5324c, this.f5325d);
            } catch (IOException e2) {
                f.this.Y(e2);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class c extends f.k0.d {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // f.k0.d
        public void k() {
            f.this.s0(false, 2, 0);
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class d extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5328c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ List f5329d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, int i2, List list) {
            super(str, objArr);
            this.f5328c = i2;
            this.f5329d = list;
        }

        @Override // f.k0.d
        public void k() {
            if (f.this.l.a(this.f5328c, this.f5329d)) {
                try {
                    f.this.y.W(this.f5328c, f.k0.k.b.CANCEL);
                    synchronized (f.this) {
                        f.this.A.remove(Integer.valueOf(this.f5328c));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class e extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5331c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ List f5332d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f5333e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, int i2, List list, boolean z) {
            super(str, objArr);
            this.f5331c = i2;
            this.f5332d = list;
            this.f5333e = z;
        }

        @Override // f.k0.d
        public void k() {
            boolean zB = f.this.l.b(this.f5331c, this.f5332d, this.f5333e);
            if (zB) {
                try {
                    f.this.y.W(this.f5331c, f.k0.k.b.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zB || this.f5333e) {
                synchronized (f.this) {
                    f.this.A.remove(Integer.valueOf(this.f5331c));
                }
            }
        }
    }

    /* JADX INFO: renamed from: f.k0.k.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Http2Connection.java */
    class C0100f extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5335c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Buffer f5336d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f5337e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f5338f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0100f(String str, Object[] objArr, int i2, Buffer buffer, int i3, boolean z) {
            super(str, objArr);
            this.f5335c = i2;
            this.f5336d = buffer;
            this.f5337e = i3;
            this.f5338f = z;
        }

        @Override // f.k0.d
        public void k() {
            try {
                boolean zD = f.this.l.d(this.f5335c, this.f5336d, this.f5337e, this.f5338f);
                if (zD) {
                    f.this.y.W(this.f5335c, f.k0.k.b.CANCEL);
                }
                if (zD || this.f5338f) {
                    synchronized (f.this) {
                        f.this.A.remove(Integer.valueOf(this.f5335c));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class g extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f5340c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ f.k0.k.b f5341d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str, Object[] objArr, int i2, f.k0.k.b bVar) {
            super(str, objArr);
            this.f5340c = i2;
            this.f5341d = bVar;
        }

        @Override // f.k0.d
        public void k() {
            f.this.l.c(this.f5340c, this.f5341d);
            synchronized (f.this) {
                f.this.A.remove(Integer.valueOf(this.f5340c));
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Socket f5343a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f5344b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        BufferedSource f5345c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        BufferedSink f5346d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        j f5347e = j.f5352a;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        f.k0.k.l f5348f = f.k0.k.l.f5419a;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        boolean f5349g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f5350h;

        public h(boolean z) {
            this.f5349g = z;
        }

        public f a() {
            return new f(this);
        }

        public h b(j jVar) {
            this.f5347e = jVar;
            return this;
        }

        public h c(int i2) {
            this.f5350h = i2;
            return this;
        }

        public h d(Socket socket, String str, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.f5343a = socket;
            this.f5344b = str;
            this.f5345c = bufferedSource;
            this.f5346d = bufferedSink;
            return this;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    final class i extends f.k0.d {
        i() {
            super("OkHttp %s ping", f.this.f5317f);
        }

        @Override // f.k0.d
        public void k() {
            boolean z;
            synchronized (f.this) {
                if (f.this.n < f.this.m) {
                    z = true;
                } else {
                    f.C(f.this);
                    z = false;
                }
            }
            if (z) {
                f.this.Y(null);
            } else {
                f.this.s0(false, 1, 0);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static abstract class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f5352a = new a();

        /* JADX INFO: compiled from: Http2Connection.java */
        class a extends j {
            a() {
            }

            @Override // f.k0.k.f.j
            public void c(f.k0.k.i iVar) {
                iVar.d(f.k0.k.b.REFUSED_STREAM, null);
            }
        }

        public void b(f fVar) {
        }

        public abstract void c(f.k0.k.i iVar);
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    final class k extends f.k0.d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f5353c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f5354d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f5355e;

        k(boolean z, int i2, int i3) {
            super("OkHttp %s ping %08x%08x", f.this.f5317f, Integer.valueOf(i2), Integer.valueOf(i3));
            this.f5353c = z;
            this.f5354d = i2;
            this.f5355e = i3;
        }

        @Override // f.k0.d
        public void k() {
            f.this.s0(this.f5353c, this.f5354d, this.f5355e);
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class l extends f.k0.d implements h.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final f.k0.k.h f5357c;

        /* JADX INFO: compiled from: Http2Connection.java */
        class a extends f.k0.d {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ f.k0.k.i f5359c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, Object[] objArr, f.k0.k.i iVar) {
                super(str, objArr);
                this.f5359c = iVar;
            }

            @Override // f.k0.d
            public void k() {
                try {
                    f.this.f5315d.c(this.f5359c);
                } catch (IOException e2) {
                    f.k0.l.f.j().p(4, "Http2Connection.Listener failure for " + f.this.f5317f, e2);
                    try {
                        this.f5359c.d(f.k0.k.b.PROTOCOL_ERROR, e2);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        class b extends f.k0.d {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ boolean f5361c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ m f5362d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(String str, Object[] objArr, boolean z, m mVar) {
                super(str, objArr);
                this.f5361c = z;
                this.f5362d = mVar;
            }

            @Override // f.k0.d
            public void k() {
                l.this.l(this.f5361c, this.f5362d);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        class c extends f.k0.d {
            c(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // f.k0.d
            public void k() {
                f fVar = f.this;
                fVar.f5315d.b(fVar);
            }
        }

        l(f.k0.k.h hVar) {
            super("OkHttp %s", f.this.f5317f);
            this.f5357c = hVar;
        }

        @Override // f.k0.k.h.b
        public void a(boolean z, int i2, int i3, List<f.k0.k.c> list) {
            if (f.this.j0(i2)) {
                f.this.g0(i2, list, z);
                return;
            }
            synchronized (f.this) {
                f.k0.k.i iVarZ = f.this.Z(i2);
                if (iVarZ != null) {
                    iVarZ.n(f.k0.e.J(list), z);
                    return;
                }
                if (f.this.f5320i) {
                    return;
                }
                f fVar = f.this;
                if (i2 <= fVar.f5318g) {
                    return;
                }
                if (i2 % 2 == fVar.f5319h % 2) {
                    return;
                }
                f.k0.k.i iVar = new f.k0.k.i(i2, f.this, false, z, f.k0.e.J(list));
                f fVar2 = f.this;
                fVar2.f5318g = i2;
                fVar2.f5316e.put(Integer.valueOf(i2), iVar);
                f.f5313b.execute(new a("OkHttp %s stream %d", new Object[]{f.this.f5317f, Integer.valueOf(i2)}, iVar));
            }
        }

        @Override // f.k0.k.h.b
        public void b() {
        }

        @Override // f.k0.k.h.b
        public void c(int i2, long j) {
            if (i2 == 0) {
                synchronized (f.this) {
                    f fVar = f.this;
                    fVar.u += j;
                    fVar.notifyAll();
                }
                return;
            }
            f.k0.k.i iVarZ = f.this.Z(i2);
            if (iVarZ != null) {
                synchronized (iVarZ) {
                    iVarZ.a(j);
                }
            }
        }

        @Override // f.k0.k.h.b
        public void d(boolean z, m mVar) {
            try {
                f.this.j.execute(new b("OkHttp %s ACK Settings", new Object[]{f.this.f5317f}, z, mVar));
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // f.k0.k.h.b
        public void e(int i2, int i3, List<f.k0.k.c> list) {
            f.this.h0(i3, list);
        }

        @Override // f.k0.k.h.b
        public void f(boolean z, int i2, BufferedSource bufferedSource, int i3) throws IOException {
            if (f.this.j0(i2)) {
                f.this.e0(i2, bufferedSource, i3, z);
                return;
            }
            f.k0.k.i iVarZ = f.this.Z(i2);
            if (iVarZ == null) {
                f.this.u0(i2, f.k0.k.b.PROTOCOL_ERROR);
                long j = i3;
                f.this.p0(j);
                bufferedSource.s(j);
                return;
            }
            iVarZ.m(bufferedSource, i3);
            if (z) {
                iVarZ.n(f.k0.e.f5135c, true);
            }
        }

        @Override // f.k0.k.h.b
        public void g(boolean z, int i2, int i3) {
            if (!z) {
                try {
                    f.this.j.execute(f.this.new k(true, i2, i3));
                    return;
                } catch (RejectedExecutionException unused) {
                    return;
                }
            }
            synchronized (f.this) {
                try {
                    if (i2 == 1) {
                        f.j(f.this);
                    } else if (i2 == 2) {
                        f.V(f.this);
                    } else if (i2 == 3) {
                        f.W(f.this);
                        f.this.notifyAll();
                    }
                } finally {
                }
            }
        }

        @Override // f.k0.k.h.b
        public void h(int i2, f.k0.k.b bVar, ByteString byteString) {
            f.k0.k.i[] iVarArr;
            byteString.s();
            synchronized (f.this) {
                iVarArr = (f.k0.k.i[]) f.this.f5316e.values().toArray(new f.k0.k.i[f.this.f5316e.size()]);
                f.this.f5320i = true;
            }
            for (f.k0.k.i iVar : iVarArr) {
                if (iVar.g() > i2 && iVar.j()) {
                    iVar.o(f.k0.k.b.REFUSED_STREAM);
                    f.this.k0(iVar.g());
                }
            }
        }

        @Override // f.k0.k.h.b
        public void i(int i2, int i3, int i4, boolean z) {
        }

        @Override // f.k0.k.h.b
        public void j(int i2, f.k0.k.b bVar) {
            if (f.this.j0(i2)) {
                f.this.i0(i2, bVar);
                return;
            }
            f.k0.k.i iVarK0 = f.this.k0(i2);
            if (iVarK0 != null) {
                iVarK0.o(bVar);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [f.k0.k.b] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5, types: [f.k0.k.h, java.io.Closeable] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // f.k0.d
        protected void k() throws Throwable {
            f.k0.k.b bVar;
            f.k0.k.b bVar2;
            f.k0.k.b bVar3 = f.k0.k.b.INTERNAL_ERROR;
            IOException e2 = null;
            try {
                try {
                    this.f5357c.j(this);
                    while (this.f5357c.d(false, this)) {
                    }
                    bVar2 = f.k0.k.b.NO_ERROR;
                } catch (IOException e3) {
                    e2 = e3;
                } catch (Throwable th) {
                    th = th;
                    bVar = bVar3;
                    f.this.X(bVar, bVar3, e2);
                    f.k0.e.f(this.f5357c);
                    throw th;
                }
                try {
                    f.this.X(bVar2, f.k0.k.b.CANCEL, null);
                    bVar = bVar2;
                } catch (IOException e4) {
                    e2 = e4;
                    f.k0.k.b bVar4 = f.k0.k.b.PROTOCOL_ERROR;
                    f fVar = f.this;
                    fVar.X(bVar4, bVar4, e2);
                    bVar = fVar;
                }
                bVar3 = this.f5357c;
                f.k0.e.f(bVar3);
            } catch (Throwable th2) {
                th = th2;
                f.this.X(bVar, bVar3, e2);
                f.k0.e.f(this.f5357c);
                throw th;
            }
        }

        void l(boolean z, m mVar) {
            f.k0.k.i[] iVarArr;
            long j;
            synchronized (f.this.y) {
                synchronized (f.this) {
                    int iD = f.this.w.d();
                    if (z) {
                        f.this.w.a();
                    }
                    f.this.w.h(mVar);
                    int iD2 = f.this.w.d();
                    iVarArr = null;
                    if (iD2 == -1 || iD2 == iD) {
                        j = 0;
                    } else {
                        j = iD2 - iD;
                        if (!f.this.f5316e.isEmpty()) {
                            iVarArr = (f.k0.k.i[]) f.this.f5316e.values().toArray(new f.k0.k.i[f.this.f5316e.size()]);
                        }
                    }
                }
                try {
                    f fVar = f.this;
                    fVar.y.a(fVar.w);
                } catch (IOException e2) {
                    f.this.Y(e2);
                }
            }
            if (iVarArr != null) {
                for (f.k0.k.i iVar : iVarArr) {
                    synchronized (iVar) {
                        iVar.a(j);
                    }
                }
            }
            f.f5313b.execute(new c("OkHttp %s settings", f.this.f5317f));
        }
    }

    f(h hVar) {
        m mVar = new m();
        this.w = mVar;
        this.A = new LinkedHashSet();
        this.l = hVar.f5348f;
        boolean z = hVar.f5349g;
        this.f5314c = z;
        this.f5315d = hVar.f5347e;
        int i2 = z ? 1 : 2;
        this.f5319h = i2;
        if (z) {
            this.f5319h = i2 + 2;
        }
        if (z) {
            this.v.i(7, 16777216);
        }
        String str = hVar.f5344b;
        this.f5317f = str;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, f.k0.e.H(f.k0.e.p("OkHttp %s Writer", str), false));
        this.j = scheduledThreadPoolExecutor;
        if (hVar.f5350h != 0) {
            i iVar = new i();
            int i3 = hVar.f5350h;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(iVar, i3, i3, TimeUnit.MILLISECONDS);
        }
        this.k = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f.k0.e.H(f.k0.e.p("OkHttp %s Push Observer", str), true));
        mVar.i(7, 65535);
        mVar.i(5, 16384);
        this.u = mVar.d();
        this.x = hVar.f5343a;
        this.y = new f.k0.k.j(hVar.f5346d, z);
        this.z = new l(new f.k0.k.h(hVar.f5345c, z));
    }

    static /* synthetic */ long C(f fVar) {
        long j2 = fVar.m;
        fVar.m = 1 + j2;
        return j2;
    }

    static /* synthetic */ long V(f fVar) {
        long j2 = fVar.p;
        fVar.p = 1 + j2;
        return j2;
    }

    static /* synthetic */ long W(f fVar) {
        long j2 = fVar.r;
        fVar.r = 1 + j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(@Nullable IOException iOException) {
        f.k0.k.b bVar = f.k0.k.b.PROTOCOL_ERROR;
        X(bVar, bVar, iOException);
    }

    private f.k0.k.i c0(int i2, List<f.k0.k.c> list, boolean z) {
        int i3;
        f.k0.k.i iVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.y) {
            synchronized (this) {
                if (this.f5319h > 1073741823) {
                    m0(f.k0.k.b.REFUSED_STREAM);
                }
                if (this.f5320i) {
                    throw new f.k0.k.a();
                }
                i3 = this.f5319h;
                this.f5319h = i3 + 2;
                iVar = new f.k0.k.i(i3, this, z3, false, null);
                z2 = !z || this.u == 0 || iVar.f5385b == 0;
                if (iVar.k()) {
                    this.f5316e.put(Integer.valueOf(i3), iVar);
                }
            }
            if (i2 == 0) {
                this.y.S(z3, i3, list);
            } else {
                if (this.f5314c) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.y.V(i2, i3, list);
            }
        }
        if (z2) {
            this.y.flush();
        }
        return iVar;
    }

    private synchronized void f0(f.k0.d dVar) {
        if (!this.f5320i) {
            this.k.execute(dVar);
        }
    }

    static /* synthetic */ long j(f fVar) {
        long j2 = fVar.n;
        fVar.n = 1 + j2;
        return j2;
    }

    void X(f.k0.k.b bVar, f.k0.k.b bVar2, @Nullable IOException iOException) {
        try {
            m0(bVar);
        } catch (IOException unused) {
        }
        f.k0.k.i[] iVarArr = null;
        synchronized (this) {
            if (!this.f5316e.isEmpty()) {
                iVarArr = (f.k0.k.i[]) this.f5316e.values().toArray(new f.k0.k.i[this.f5316e.size()]);
                this.f5316e.clear();
            }
        }
        if (iVarArr != null) {
            for (f.k0.k.i iVar : iVarArr) {
                try {
                    iVar.d(bVar2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.y.close();
        } catch (IOException unused3) {
        }
        try {
            this.x.close();
        } catch (IOException unused4) {
        }
        this.j.shutdown();
        this.k.shutdown();
    }

    synchronized f.k0.k.i Z(int i2) {
        return this.f5316e.get(Integer.valueOf(i2));
    }

    public synchronized boolean a0(long j2) {
        if (this.f5320i) {
            return false;
        }
        if (this.p < this.o) {
            if (j2 >= this.s) {
                return false;
            }
        }
        return true;
    }

    public synchronized int b0() {
        return this.w.e(Integer.MAX_VALUE);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        X(f.k0.k.b.NO_ERROR, f.k0.k.b.CANCEL, null);
    }

    public f.k0.k.i d0(List<f.k0.k.c> list, boolean z) {
        return c0(0, list, z);
    }

    void e0(int i2, BufferedSource bufferedSource, int i3, boolean z) throws IOException {
        Buffer buffer = new Buffer();
        long j2 = i3;
        bufferedSource.B(j2);
        bufferedSource.p(buffer, j2);
        if (buffer.g0() == j2) {
            f0(new C0100f("OkHttp %s Push Data[%s]", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, buffer, i3, z));
            return;
        }
        throw new IOException(buffer.g0() + " != " + i3);
    }

    public void flush() {
        this.y.flush();
    }

    void g0(int i2, List<f.k0.k.c> list, boolean z) {
        try {
            f0(new e("OkHttp %s Push Headers[%s]", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, list, z));
        } catch (RejectedExecutionException unused) {
        }
    }

    void h0(int i2, List<f.k0.k.c> list) {
        synchronized (this) {
            if (this.A.contains(Integer.valueOf(i2))) {
                u0(i2, f.k0.k.b.PROTOCOL_ERROR);
                return;
            }
            this.A.add(Integer.valueOf(i2));
            try {
                f0(new d("OkHttp %s Push Request[%s]", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, list));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void i0(int i2, f.k0.k.b bVar) {
        f0(new g("OkHttp %s Push Reset[%s]", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, bVar));
    }

    boolean j0(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    synchronized f.k0.k.i k0(int i2) {
        f.k0.k.i iVarRemove;
        iVarRemove = this.f5316e.remove(Integer.valueOf(i2));
        notifyAll();
        return iVarRemove;
    }

    void l0() {
        synchronized (this) {
            long j2 = this.p;
            long j3 = this.o;
            if (j2 < j3) {
                return;
            }
            this.o = j3 + 1;
            this.s = System.nanoTime() + 1000000000;
            try {
                this.j.execute(new c("OkHttp %s ping", this.f5317f));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void m0(f.k0.k.b bVar) {
        synchronized (this.y) {
            synchronized (this) {
                if (this.f5320i) {
                    return;
                }
                this.f5320i = true;
                this.y.R(this.f5318g, bVar, f.k0.e.f5133a);
            }
        }
    }

    public void n0() {
        o0(true);
    }

    void o0(boolean z) {
        if (z) {
            this.y.d();
            this.y.X(this.v);
            if (this.v.d() != 65535) {
                this.y.Y(0, r6 - 65535);
            }
        }
        new Thread(this.z).start();
    }

    synchronized void p0(long j2) {
        long j3 = this.t + j2;
        this.t = j3;
        if (j3 >= this.v.d() / 2) {
            v0(0, this.t);
            this.t = 0L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r4), r8.y.T());
        r6 = r2;
        r8.u -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void q0(int r9, boolean r10, okio.Buffer r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            f.k0.k.j r12 = r8.y
            r12.j(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L67
            monitor-enter(r8)
        L12:
            long r4 = r8.u     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L30
            java.util.Map<java.lang.Integer, f.k0.k.i> r2 = r8.f5316e     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            if (r2 == 0) goto L28
            r8.wait()     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            goto L12
        L28:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            throw r9     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
        L30:
            long r4 = java.lang.Math.min(r12, r4)     // Catch: java.lang.Throwable -> L56
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L56
            f.k0.k.j r4 = r8.y     // Catch: java.lang.Throwable -> L56
            int r4 = r4.T()     // Catch: java.lang.Throwable -> L56
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.u     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.u = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            f.k0.k.j r4 = r8.y
            if (r10 == 0) goto L51
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = r3
        L52:
            r4.j(r5, r9, r11, r2)
            goto Ld
        L56:
            r9 = move-exception
            goto L65
        L58:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L56
            r9.interrupt()     // Catch: java.lang.Throwable -> L56
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L65:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: f.k0.k.f.q0(int, boolean, okio.Buffer, long):void");
    }

    void r0(int i2, boolean z, List<f.k0.k.c> list) {
        this.y.S(z, i2, list);
    }

    void s0(boolean z, int i2, int i3) {
        try {
            this.y.U(z, i2, i3);
        } catch (IOException e2) {
            Y(e2);
        }
    }

    void t0(int i2, f.k0.k.b bVar) {
        this.y.W(i2, bVar);
    }

    void u0(int i2, f.k0.k.b bVar) {
        try {
            this.j.execute(new a("OkHttp %s stream %d", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, bVar));
        } catch (RejectedExecutionException unused) {
        }
    }

    void v0(int i2, long j2) {
        try {
            this.j.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.f5317f, Integer.valueOf(i2)}, i2, j2));
        } catch (RejectedExecutionException unused) {
        }
    }
}
