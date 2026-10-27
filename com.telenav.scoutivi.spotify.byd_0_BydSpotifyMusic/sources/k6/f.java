package k6;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import g5.r;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import k6.h;
import kotlin.jvm.internal.q;

/* JADX INFO: compiled from: Http2Connection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Closeable {
    private static final m H;
    public static final c I = new c(null);
    private long A;
    private long B;
    private long C;
    private final Socket D;
    private final k6.j E;
    private final e F;
    private final Set<Integer> G;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f7315e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final d f7316g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Map<Integer, k6.i> f7317h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f7318i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f7319j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f7320k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f7321l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final g6.e f7322m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final g6.d f7323n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final g6.d f7324o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final g6.d f7325p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final k6.l f7326q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private long f7327r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private long f7328s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private long f7329t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private long f7330u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private long f7331v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private long f7332w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final m f7333x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private m f7334y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private long f7335z;

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class a extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7336e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ f f7337f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ long f7338g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, String str2, f fVar, long j7) {
            super(str2, false, 2, null);
            this.f7336e = str;
            this.f7337f = fVar;
            this.f7338g = j7;
        }

        @Override // g6.a
        public long f() {
            boolean z6;
            synchronized (this.f7337f) {
                if (this.f7337f.f7328s < this.f7337f.f7327r) {
                    z6 = true;
                } else {
                    this.f7337f.f7327r++;
                    z6 = false;
                }
            }
            if (z6) {
                this.f7337f.i0(null);
                return -1L;
            }
            this.f7337f.M0(false, 1, 0);
            return this.f7338g;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Socket f7339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f7340b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public p6.g f7341c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public p6.f f7342d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private d f7343e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private k6.l f7344f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f7345g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f7346h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final g6.e f7347i;

        public b(boolean z6, g6.e taskRunner) {
            kotlin.jvm.internal.l.e(taskRunner, "taskRunner");
            this.f7346h = z6;
            this.f7347i = taskRunner;
            this.f7343e = d.f7348a;
            this.f7344f = k6.l.f7478a;
        }

        public final f a() {
            return new f(this);
        }

        public final boolean b() {
            return this.f7346h;
        }

        public final String c() {
            String str = this.f7340b;
            if (str == null) {
                kotlin.jvm.internal.l.u("connectionName");
            }
            return str;
        }

        public final d d() {
            return this.f7343e;
        }

        public final int e() {
            return this.f7345g;
        }

        public final k6.l f() {
            return this.f7344f;
        }

        public final p6.f g() {
            p6.f fVar = this.f7342d;
            if (fVar == null) {
                kotlin.jvm.internal.l.u("sink");
            }
            return fVar;
        }

        public final Socket h() {
            Socket socket = this.f7339a;
            if (socket == null) {
                kotlin.jvm.internal.l.u("socket");
            }
            return socket;
        }

        public final p6.g i() {
            p6.g gVar = this.f7341c;
            if (gVar == null) {
                kotlin.jvm.internal.l.u(BYDAutoMultimediaDevice.PARAM_SOURCE);
            }
            return gVar;
        }

        public final g6.e j() {
            return this.f7347i;
        }

        public final b k(d listener) {
            kotlin.jvm.internal.l.e(listener, "listener");
            this.f7343e = listener;
            return this;
        }

        public final b l(int i7) {
            this.f7345g = i7;
            return this;
        }

        public final b m(Socket socket, String peerName, p6.g source, p6.f sink) {
            String str;
            kotlin.jvm.internal.l.e(socket, "socket");
            kotlin.jvm.internal.l.e(peerName, "peerName");
            kotlin.jvm.internal.l.e(source, "source");
            kotlin.jvm.internal.l.e(sink, "sink");
            this.f7339a = socket;
            if (this.f7346h) {
                str = d6.b.f5968i + ' ' + peerName;
            } else {
                str = "MockWebServer " + peerName;
            }
            this.f7340b = str;
            this.f7341c = source;
            this.f7342d = sink;
            return this;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    public static final class c {
        private c() {
        }

        public /* synthetic */ c(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final m a() {
            return f.H;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    public static abstract class d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f7349b = new b(null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f7348a = new a();

        /* JADX INFO: compiled from: Http2Connection.kt */
        public static final class a extends d {
            a() {
            }

            @Override // k6.f.d
            public void b(k6.i stream) {
                kotlin.jvm.internal.l.e(stream, "stream");
                stream.d(k6.b.REFUSED_STREAM, null);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.kt */
        public static final class b {
            private b() {
            }

            public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
                this();
            }
        }

        public void a(f connection, m settings) {
            kotlin.jvm.internal.l.e(connection, "connection");
            kotlin.jvm.internal.l.e(settings, "settings");
        }

        public abstract void b(k6.i iVar);
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    public final class e implements h.c, r5.a<r> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final k6.h f7350e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7351g;

        /* JADX INFO: compiled from: TaskQueue.kt */
        public static final class a extends g6.a {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f7352e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ boolean f7353f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ e f7354g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ kotlin.jvm.internal.r f7355h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ boolean f7356i;

            /* JADX INFO: renamed from: j, reason: collision with root package name */
            final /* synthetic */ m f7357j;

            /* JADX INFO: renamed from: k, reason: collision with root package name */
            final /* synthetic */ q f7358k;

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            final /* synthetic */ kotlin.jvm.internal.r f7359l;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, boolean z6, String str2, boolean z7, e eVar, kotlin.jvm.internal.r rVar, boolean z8, m mVar, q qVar, kotlin.jvm.internal.r rVar2) {
                super(str2, z7);
                this.f7352e = str;
                this.f7353f = z6;
                this.f7354g = eVar;
                this.f7355h = rVar;
                this.f7356i = z8;
                this.f7357j = mVar;
                this.f7358k = qVar;
                this.f7359l = rVar2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // g6.a
            public long f() {
                this.f7354g.f7351g.m0().a(this.f7354g.f7351g, (m) this.f7355h.f7508e);
                return -1L;
            }
        }

        /* JADX INFO: compiled from: TaskQueue.kt */
        public static final class b extends g6.a {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f7360e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ boolean f7361f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ k6.i f7362g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ e f7363h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ k6.i f7364i;

            /* JADX INFO: renamed from: j, reason: collision with root package name */
            final /* synthetic */ int f7365j;

            /* JADX INFO: renamed from: k, reason: collision with root package name */
            final /* synthetic */ List f7366k;

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            final /* synthetic */ boolean f7367l;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(String str, boolean z6, String str2, boolean z7, k6.i iVar, e eVar, k6.i iVar2, int i7, List list, boolean z8) {
                super(str2, z7);
                this.f7360e = str;
                this.f7361f = z6;
                this.f7362g = iVar;
                this.f7363h = eVar;
                this.f7364i = iVar2;
                this.f7365j = i7;
                this.f7366k = list;
                this.f7367l = z8;
            }

            @Override // g6.a
            public long f() {
                try {
                    this.f7363h.f7351g.m0().b(this.f7362g);
                    return -1L;
                } catch (IOException e7) {
                    l6.h.f8004c.g().j("Http2Connection.Listener failure for " + this.f7363h.f7351g.k0(), 4, e7);
                    try {
                        this.f7362g.d(k6.b.PROTOCOL_ERROR, e7);
                        return -1L;
                    } catch (IOException unused) {
                        return -1L;
                    }
                }
            }
        }

        /* JADX INFO: compiled from: TaskQueue.kt */
        public static final class c extends g6.a {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f7368e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ boolean f7369f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ e f7370g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ int f7371h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ int f7372i;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str, boolean z6, String str2, boolean z7, e eVar, int i7, int i8) {
                super(str2, z7);
                this.f7368e = str;
                this.f7369f = z6;
                this.f7370g = eVar;
                this.f7371h = i7;
                this.f7372i = i8;
            }

            @Override // g6.a
            public long f() {
                this.f7370g.f7351g.M0(true, this.f7371h, this.f7372i);
                return -1L;
            }
        }

        /* JADX INFO: compiled from: TaskQueue.kt */
        public static final class d extends g6.a {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ String f7373e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ boolean f7374f;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ e f7375g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ boolean f7376h;

            /* JADX INFO: renamed from: i, reason: collision with root package name */
            final /* synthetic */ m f7377i;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(String str, boolean z6, String str2, boolean z7, e eVar, boolean z8, m mVar) {
                super(str2, z7);
                this.f7373e = str;
                this.f7374f = z6;
                this.f7375g = eVar;
                this.f7376h = z8;
                this.f7377i = mVar;
            }

            @Override // g6.a
            public long f() {
                this.f7375g.l(this.f7376h, this.f7377i);
                return -1L;
            }
        }

        public e(f fVar, k6.h reader) {
            kotlin.jvm.internal.l.e(reader, "reader");
            this.f7351g = fVar;
            this.f7350e = reader;
        }

        @Override // k6.h.c
        public void a(boolean z6, int i7, int i8, List<k6.c> headerBlock) {
            kotlin.jvm.internal.l.e(headerBlock, "headerBlock");
            if (this.f7351g.B0(i7)) {
                this.f7351g.y0(i7, headerBlock, z6);
                return;
            }
            synchronized (this.f7351g) {
                k6.i iVarQ0 = this.f7351g.q0(i7);
                if (iVarQ0 != null) {
                    r rVar = r.f6563a;
                    iVarQ0.x(d6.b.J(headerBlock), z6);
                    return;
                }
                if (this.f7351g.f7321l) {
                    return;
                }
                if (i7 <= this.f7351g.l0()) {
                    return;
                }
                if (i7 % 2 == this.f7351g.n0() % 2) {
                    return;
                }
                k6.i iVar = new k6.i(i7, this.f7351g, false, z6, d6.b.J(headerBlock));
                this.f7351g.E0(i7);
                this.f7351g.r0().put(Integer.valueOf(i7), iVar);
                g6.d dVarI = this.f7351g.f7322m.i();
                String str = this.f7351g.k0() + '[' + i7 + "] onStream";
                dVarI.i(new b(str, true, str, true, iVar, this, iVarQ0, i7, headerBlock, z6), 0L);
            }
        }

        @Override // k6.h.c
        public void b(int i7, k6.b errorCode) {
            kotlin.jvm.internal.l.e(errorCode, "errorCode");
            if (this.f7351g.B0(i7)) {
                this.f7351g.A0(i7, errorCode);
                return;
            }
            k6.i iVarC0 = this.f7351g.C0(i7);
            if (iVarC0 != null) {
                iVarC0.y(errorCode);
            }
        }

        @Override // k6.h.c
        public void d() {
        }

        @Override // k6.h.c
        public void e(boolean z6, int i7, p6.g source, int i8) {
            kotlin.jvm.internal.l.e(source, "source");
            if (this.f7351g.B0(i7)) {
                this.f7351g.x0(i7, source, i8, z6);
                return;
            }
            k6.i iVarQ0 = this.f7351g.q0(i7);
            if (iVarQ0 == null) {
                this.f7351g.O0(i7, k6.b.PROTOCOL_ERROR);
                long j7 = i8;
                this.f7351g.J0(j7);
                source.a(j7);
                return;
            }
            iVarQ0.w(source, i8);
            if (z6) {
                iVarQ0.x(d6.b.f5961b, true);
            }
        }

        @Override // k6.h.c
        public void f(int i7, long j7) {
            if (i7 != 0) {
                k6.i iVarQ0 = this.f7351g.q0(i7);
                if (iVarQ0 != null) {
                    synchronized (iVarQ0) {
                        iVarQ0.a(j7);
                        r rVar = r.f6563a;
                    }
                    return;
                }
                return;
            }
            synchronized (this.f7351g) {
                f fVar = this.f7351g;
                fVar.C = fVar.s0() + j7;
                f fVar2 = this.f7351g;
                if (fVar2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                }
                fVar2.notifyAll();
                r rVar2 = r.f6563a;
            }
        }

        @Override // k6.h.c
        public void g(int i7, k6.b errorCode, p6.h debugData) {
            int i8;
            k6.i[] iVarArr;
            kotlin.jvm.internal.l.e(errorCode, "errorCode");
            kotlin.jvm.internal.l.e(debugData, "debugData");
            debugData.r();
            synchronized (this.f7351g) {
                Object[] array = this.f7351g.r0().values().toArray(new k6.i[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                iVarArr = (k6.i[]) array;
                this.f7351g.f7321l = true;
                r rVar = r.f6563a;
            }
            for (k6.i iVar : iVarArr) {
                if (iVar.j() > i7 && iVar.t()) {
                    iVar.y(k6.b.REFUSED_STREAM);
                    this.f7351g.C0(iVar.j());
                }
            }
        }

        @Override // k6.h.c
        public void h(int i7, int i8, List<k6.c> requestHeaders) {
            kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
            this.f7351g.z0(i8, requestHeaders);
        }

        @Override // k6.h.c
        public void i(boolean z6, int i7, int i8) {
            if (!z6) {
                g6.d dVar = this.f7351g.f7323n;
                String str = this.f7351g.k0() + " ping";
                dVar.i(new c(str, true, str, true, this, i7, i8), 0L);
                return;
            }
            synchronized (this.f7351g) {
                if (i7 == 1) {
                    this.f7351g.f7328s++;
                } else if (i7 != 2) {
                    if (i7 == 3) {
                        this.f7351g.f7331v++;
                        f fVar = this.f7351g;
                        if (fVar == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
                        }
                        fVar.notifyAll();
                    }
                    r rVar = r.f6563a;
                } else {
                    this.f7351g.f7330u++;
                }
            }
        }

        @Override // r5.a
        public /* bridge */ /* synthetic */ r invoke() throws Throwable {
            n();
            return r.f6563a;
        }

        @Override // k6.h.c
        public void j(int i7, int i8, int i9, boolean z6) {
        }

        @Override // k6.h.c
        public void k(boolean z6, m settings) {
            kotlin.jvm.internal.l.e(settings, "settings");
            g6.d dVar = this.f7351g.f7323n;
            String str = this.f7351g.k0() + " applyAndAckSettings";
            dVar.i(new d(str, true, str, true, this, z6, settings), 0L);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v1, types: [T, k6.m] */
        /* JADX WARN: Type inference failed for: r2v14 */
        /* JADX WARN: Type inference failed for: r2v15 */
        public final void l(boolean z6, m settings) {
            f fVar;
            ?? r22;
            T t6;
            g6.d dVar;
            String str;
            kotlin.jvm.internal.l.e(settings, "settings");
            q qVar = new q();
            kotlin.jvm.internal.r rVar = new kotlin.jvm.internal.r();
            kotlin.jvm.internal.r rVar2 = new kotlin.jvm.internal.r();
            synchronized (this.f7351g.t0()) {
                f fVar2 = this.f7351g;
                synchronized (fVar2) {
                    try {
                        m mVarP0 = this.f7351g.p0();
                        if (z6) {
                            r22 = settings;
                        } else {
                            m mVar = new m();
                            mVar.g(mVarP0);
                            mVar.g(settings);
                            r rVar3 = r.f6563a;
                            r22 = mVar;
                        }
                        rVar2.f7508e = r22;
                        long jC = ((long) r22.c()) - ((long) mVarP0.c());
                        qVar.f7507e = jC;
                        if (jC == 0 || this.f7351g.r0().isEmpty()) {
                            t6 = 0;
                        } else {
                            Object[] array = this.f7351g.r0().values().toArray(new k6.i[0]);
                            if (array == null) {
                                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                            t6 = (k6.i[]) array;
                        }
                        rVar.f7508e = t6;
                        this.f7351g.F0((m) rVar2.f7508e);
                        dVar = this.f7351g.f7325p;
                        str = this.f7351g.k0() + " onSettings";
                        fVar = fVar2;
                    } catch (Throwable th) {
                        th = th;
                        fVar = fVar2;
                    }
                    try {
                        dVar.i(new a(str, true, str, true, this, rVar2, z6, settings, qVar, rVar), 0L);
                        r rVar4 = r.f6563a;
                        try {
                            this.f7351g.t0().m((m) rVar2.f7508e);
                        } catch (IOException e7) {
                            this.f7351g.i0(e7);
                        }
                        r rVar5 = r.f6563a;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
            }
            T t7 = rVar.f7508e;
            if (((k6.i[]) t7) != null) {
                k6.i[] iVarArr = (k6.i[]) t7;
                kotlin.jvm.internal.l.c(iVarArr);
                for (k6.i iVar : iVarArr) {
                    synchronized (iVar) {
                        iVar.a(qVar.f7507e);
                        r rVar6 = r.f6563a;
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [k6.b] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable, k6.h] */
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
        public void n() throws Throwable {
            k6.b bVar;
            k6.b bVar2;
            k6.b bVar3 = k6.b.INTERNAL_ERROR;
            IOException e7 = null;
            try {
                try {
                    this.f7350e.A(this);
                    while (this.f7350e.z(false, this)) {
                    }
                    bVar2 = k6.b.NO_ERROR;
                } catch (IOException e8) {
                    e7 = e8;
                } catch (Throwable th) {
                    th = th;
                    bVar = bVar3;
                    this.f7351g.h0(bVar, bVar3, e7);
                    d6.b.i(this.f7350e);
                    throw th;
                }
                try {
                    this.f7351g.h0(bVar2, k6.b.CANCEL, null);
                    bVar = bVar2;
                } catch (IOException e9) {
                    e7 = e9;
                    k6.b bVar4 = k6.b.PROTOCOL_ERROR;
                    f fVar = this.f7351g;
                    fVar.h0(bVar4, bVar4, e7);
                    bVar = fVar;
                }
                bVar3 = this.f7350e;
                d6.b.i(bVar3);
            } catch (Throwable th2) {
                th = th2;
                this.f7351g.h0(bVar, bVar3, e7);
                d6.b.i(this.f7350e);
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: k6.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class C0102f extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7378e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7379f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7380g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7381h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ p6.e f7382i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final /* synthetic */ int f7383j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        final /* synthetic */ boolean f7384k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0102f(String str, boolean z6, String str2, boolean z7, f fVar, int i7, p6.e eVar, int i8, boolean z8) {
            super(str2, z7);
            this.f7378e = str;
            this.f7379f = z6;
            this.f7380g = fVar;
            this.f7381h = i7;
            this.f7382i = eVar;
            this.f7383j = i8;
            this.f7384k = z8;
        }

        @Override // g6.a
        public long f() {
            try {
                boolean zD = this.f7380g.f7326q.d(this.f7381h, this.f7382i, this.f7383j, this.f7384k);
                if (zD) {
                    this.f7380g.t0().Z(this.f7381h, k6.b.CANCEL);
                }
                if (!zD && !this.f7384k) {
                    return -1L;
                }
                synchronized (this.f7380g) {
                    this.f7380g.G.remove(Integer.valueOf(this.f7381h));
                }
                return -1L;
            } catch (IOException unused) {
                return -1L;
            }
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class g extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7385e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7386f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7387g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7388h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ List f7389i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        final /* synthetic */ boolean f7390j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, boolean z6, String str2, boolean z7, f fVar, int i7, List list, boolean z8) {
            super(str2, z7);
            this.f7385e = str;
            this.f7386f = z6;
            this.f7387g = fVar;
            this.f7388h = i7;
            this.f7389i = list;
            this.f7390j = z8;
        }

        @Override // g6.a
        public long f() {
            boolean zB = this.f7387g.f7326q.b(this.f7388h, this.f7389i, this.f7390j);
            if (zB) {
                try {
                    this.f7387g.t0().Z(this.f7388h, k6.b.CANCEL);
                } catch (IOException unused) {
                    return -1L;
                }
            }
            if (!zB && !this.f7390j) {
                return -1L;
            }
            synchronized (this.f7387g) {
                this.f7387g.G.remove(Integer.valueOf(this.f7388h));
            }
            return -1L;
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class h extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7391e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7392f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7393g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7394h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ List f7395i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(String str, boolean z6, String str2, boolean z7, f fVar, int i7, List list) {
            super(str2, z7);
            this.f7391e = str;
            this.f7392f = z6;
            this.f7393g = fVar;
            this.f7394h = i7;
            this.f7395i = list;
        }

        @Override // g6.a
        public long f() {
            if (!this.f7393g.f7326q.a(this.f7394h, this.f7395i)) {
                return -1L;
            }
            try {
                this.f7393g.t0().Z(this.f7394h, k6.b.CANCEL);
                synchronized (this.f7393g) {
                    this.f7393g.G.remove(Integer.valueOf(this.f7394h));
                }
                return -1L;
            } catch (IOException unused) {
                return -1L;
            }
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class i extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7396e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7397f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7398g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7399h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ k6.b f7400i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(String str, boolean z6, String str2, boolean z7, f fVar, int i7, k6.b bVar) {
            super(str2, z7);
            this.f7396e = str;
            this.f7397f = z6;
            this.f7398g = fVar;
            this.f7399h = i7;
            this.f7400i = bVar;
        }

        @Override // g6.a
        public long f() {
            this.f7398g.f7326q.c(this.f7399h, this.f7400i);
            synchronized (this.f7398g) {
                this.f7398g.G.remove(Integer.valueOf(this.f7399h));
                r rVar = r.f6563a;
            }
            return -1L;
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class j extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7401e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7402f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7403g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(String str, boolean z6, String str2, boolean z7, f fVar) {
            super(str2, z7);
            this.f7401e = str;
            this.f7402f = z6;
            this.f7403g = fVar;
        }

        @Override // g6.a
        public long f() {
            this.f7403g.M0(false, 2, 0);
            return -1L;
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class k extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7404e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7405f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7406g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7407h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ k6.b f7408i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(String str, boolean z6, String str2, boolean z7, f fVar, int i7, k6.b bVar) {
            super(str2, z7);
            this.f7404e = str;
            this.f7405f = z6;
            this.f7406g = fVar;
            this.f7407h = i7;
            this.f7408i = bVar;
        }

        @Override // g6.a
        public long f() {
            try {
                this.f7406g.N0(this.f7407h, this.f7408i);
                return -1L;
            } catch (IOException e7) {
                this.f7406g.i0(e7);
                return -1L;
            }
        }
    }

    /* JADX INFO: compiled from: TaskQueue.kt */
    public static final class l extends g6.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f7409e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f7410f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ f f7411g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f7412h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ long f7413i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(String str, boolean z6, String str2, boolean z7, f fVar, int i7, long j7) {
            super(str2, z7);
            this.f7409e = str;
            this.f7410f = z6;
            this.f7411g = fVar;
            this.f7412h = i7;
            this.f7413i = j7;
        }

        @Override // g6.a
        public long f() {
            try {
                this.f7411g.t0().b0(this.f7412h, this.f7413i);
                return -1L;
            } catch (IOException e7) {
                this.f7411g.i0(e7);
                return -1L;
            }
        }
    }

    static {
        m mVar = new m();
        mVar.h(7, 65535);
        mVar.h(5, 16384);
        H = mVar;
    }

    public f(b builder) {
        kotlin.jvm.internal.l.e(builder, "builder");
        boolean zB = builder.b();
        this.f7315e = zB;
        this.f7316g = builder.d();
        this.f7317h = new LinkedHashMap();
        String strC = builder.c();
        this.f7318i = strC;
        this.f7320k = builder.b() ? 3 : 2;
        g6.e eVarJ = builder.j();
        this.f7322m = eVarJ;
        g6.d dVarI = eVarJ.i();
        this.f7323n = dVarI;
        this.f7324o = eVarJ.i();
        this.f7325p = eVarJ.i();
        this.f7326q = builder.f();
        m mVar = new m();
        if (builder.b()) {
            mVar.h(7, 16777216);
        }
        r rVar = r.f6563a;
        this.f7333x = mVar;
        this.f7334y = H;
        this.C = r2.c();
        this.D = builder.h();
        this.E = new k6.j(builder.g(), zB);
        this.F = new e(this, new k6.h(builder.i(), zB));
        this.G = new LinkedHashSet();
        if (builder.e() != 0) {
            long nanos = TimeUnit.MILLISECONDS.toNanos(builder.e());
            String str = strC + " ping";
            dVarI.i(new a(str, str, this, nanos), nanos);
        }
    }

    public static /* synthetic */ void I0(f fVar, boolean z6, g6.e eVar, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            z6 = true;
        }
        if ((i7 & 2) != 0) {
            eVar = g6.e.f6577h;
        }
        fVar.H0(z6, eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i0(IOException iOException) {
        k6.b bVar = k6.b.PROTOCOL_ERROR;
        h0(bVar, bVar, iOException);
    }

    private final k6.i v0(int i7, List<k6.c> list, boolean z6) {
        int i8;
        k6.i iVar;
        boolean z7;
        boolean z8 = !z6;
        synchronized (this.E) {
            synchronized (this) {
                if (this.f7320k > 1073741823) {
                    G0(k6.b.REFUSED_STREAM);
                }
                if (this.f7321l) {
                    throw new k6.a();
                }
                i8 = this.f7320k;
                this.f7320k = i8 + 2;
                iVar = new k6.i(i8, this, z8, false, null);
                z7 = !z6 || this.B >= this.C || iVar.r() >= iVar.q();
                if (iVar.u()) {
                    this.f7317h.put(Integer.valueOf(i8), iVar);
                }
                r rVar = r.f6563a;
            }
            if (i7 == 0) {
                this.E.N(z8, i8, list);
            } else {
                if (!(true ^ this.f7315e)) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs".toString());
                }
                this.E.V(i7, i8, list);
            }
        }
        if (z7) {
            this.E.flush();
        }
        return iVar;
    }

    public final void A0(int i7, k6.b errorCode) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        g6.d dVar = this.f7324o;
        String str = this.f7318i + '[' + i7 + "] onReset";
        dVar.i(new i(str, true, str, true, this, i7, errorCode), 0L);
    }

    public final boolean B0(int i7) {
        return i7 != 0 && (i7 & 1) == 0;
    }

    public final synchronized k6.i C0(int i7) {
        k6.i iVarRemove;
        iVarRemove = this.f7317h.remove(Integer.valueOf(i7));
        notifyAll();
        return iVarRemove;
    }

    public final void D0() {
        synchronized (this) {
            long j7 = this.f7330u;
            long j8 = this.f7329t;
            if (j7 < j8) {
                return;
            }
            this.f7329t = j8 + 1;
            this.f7332w = System.nanoTime() + ((long) 1000000000);
            r rVar = r.f6563a;
            g6.d dVar = this.f7323n;
            String str = this.f7318i + " ping";
            dVar.i(new j(str, true, str, true, this), 0L);
        }
    }

    public final void E0(int i7) {
        this.f7319j = i7;
    }

    public final void F0(m mVar) {
        kotlin.jvm.internal.l.e(mVar, "<set-?>");
        this.f7334y = mVar;
    }

    public final void G0(k6.b statusCode) {
        kotlin.jvm.internal.l.e(statusCode, "statusCode");
        synchronized (this.E) {
            synchronized (this) {
                if (this.f7321l) {
                    return;
                }
                this.f7321l = true;
                int i7 = this.f7319j;
                r rVar = r.f6563a;
                this.E.K(i7, statusCode, d6.b.f5960a);
            }
        }
    }

    public final void H0(boolean z6, g6.e taskRunner) {
        kotlin.jvm.internal.l.e(taskRunner, "taskRunner");
        if (z6) {
            this.E.z();
            this.E.a0(this.f7333x);
            if (this.f7333x.c() != 65535) {
                this.E.b0(0, r9 - 65535);
            }
        }
        g6.d dVarI = taskRunner.i();
        String str = this.f7318i;
        dVarI.i(new g6.c(this.F, str, true, str, true), 0L);
    }

    public final synchronized void J0(long j7) {
        long j8 = this.f7335z + j7;
        this.f7335z = j8;
        long j9 = j8 - this.A;
        if (j9 >= this.f7333x.c() / 2) {
            P0(0, j9);
            this.A += j9;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.E.P());
        r6 = r2;
        r8.B += r6;
        r4 = g5.r.f6563a;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void K0(int r9, boolean r10, p6.e r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            k6.j r12 = r8.E
            r12.A(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L6c
            monitor-enter(r8)
        L12:
            long r4 = r8.B     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            long r6 = r8.C     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L32
            java.util.Map<java.lang.Integer, k6.i> r2 = r8.f7317h     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            if (r2 == 0) goto L2a
            r8.wait()     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            goto L12
        L2a:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
            throw r9     // Catch: java.lang.Throwable -> L5b java.lang.InterruptedException -> L5d
        L32:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L5b
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L5b
            k6.j r4 = r8.E     // Catch: java.lang.Throwable -> L5b
            int r4 = r4.P()     // Catch: java.lang.Throwable -> L5b
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L5b
            long r4 = r8.B     // Catch: java.lang.Throwable -> L5b
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L5b
            long r4 = r4 + r6
            r8.B = r4     // Catch: java.lang.Throwable -> L5b
            g5.r r4 = g5.r.f6563a     // Catch: java.lang.Throwable -> L5b
            monitor-exit(r8)
            long r12 = r12 - r6
            k6.j r4 = r8.E
            if (r10 == 0) goto L56
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L56
            r5 = 1
            goto L57
        L56:
            r5 = r3
        L57:
            r4.A(r5, r9, r11, r2)
            goto Ld
        L5b:
            r9 = move-exception
            goto L6a
        L5d:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L5b
            r9.interrupt()     // Catch: java.lang.Throwable -> L5b
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L5b
            r9.<init>()     // Catch: java.lang.Throwable -> L5b
            throw r9     // Catch: java.lang.Throwable -> L5b
        L6a:
            monitor-exit(r8)
            throw r9
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: k6.f.K0(int, boolean, p6.e, long):void");
    }

    public final void L0(int i7, boolean z6, List<k6.c> alternating) {
        kotlin.jvm.internal.l.e(alternating, "alternating");
        this.E.N(z6, i7, alternating);
    }

    public final void M0(boolean z6, int i7, int i8) {
        try {
            this.E.U(z6, i7, i8);
        } catch (IOException e7) {
            i0(e7);
        }
    }

    public final void N0(int i7, k6.b statusCode) {
        kotlin.jvm.internal.l.e(statusCode, "statusCode");
        this.E.Z(i7, statusCode);
    }

    public final void O0(int i7, k6.b errorCode) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        g6.d dVar = this.f7323n;
        String str = this.f7318i + '[' + i7 + "] writeSynReset";
        dVar.i(new k(str, true, str, true, this, i7, errorCode), 0L);
    }

    public final void P0(int i7, long j7) {
        g6.d dVar = this.f7323n;
        String str = this.f7318i + '[' + i7 + "] windowUpdate";
        dVar.i(new l(str, true, str, true, this, i7, j7), 0L);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        h0(k6.b.NO_ERROR, k6.b.CANCEL, null);
    }

    public final void flush() {
        this.E.flush();
    }

    public final void h0(k6.b connectionCode, k6.b streamCode, IOException iOException) {
        int i7;
        kotlin.jvm.internal.l.e(connectionCode, "connectionCode");
        kotlin.jvm.internal.l.e(streamCode, "streamCode");
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        try {
            G0(connectionCode);
        } catch (IOException unused) {
        }
        k6.i[] iVarArr = null;
        synchronized (this) {
            if (!this.f7317h.isEmpty()) {
                Object[] array = this.f7317h.values().toArray(new k6.i[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                iVarArr = (k6.i[]) array;
                this.f7317h.clear();
            }
            r rVar = r.f6563a;
        }
        if (iVarArr != null) {
            for (k6.i iVar : iVarArr) {
                try {
                    iVar.d(streamCode, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.E.close();
        } catch (IOException unused3) {
        }
        try {
            this.D.close();
        } catch (IOException unused4) {
        }
        this.f7323n.n();
        this.f7324o.n();
        this.f7325p.n();
    }

    public final boolean j0() {
        return this.f7315e;
    }

    public final String k0() {
        return this.f7318i;
    }

    public final int l0() {
        return this.f7319j;
    }

    public final d m0() {
        return this.f7316g;
    }

    public final int n0() {
        return this.f7320k;
    }

    public final m o0() {
        return this.f7333x;
    }

    public final m p0() {
        return this.f7334y;
    }

    public final synchronized k6.i q0(int i7) {
        return this.f7317h.get(Integer.valueOf(i7));
    }

    public final Map<Integer, k6.i> r0() {
        return this.f7317h;
    }

    public final long s0() {
        return this.C;
    }

    public final k6.j t0() {
        return this.E;
    }

    public final synchronized boolean u0(long j7) {
        if (this.f7321l) {
            return false;
        }
        if (this.f7330u < this.f7329t) {
            if (j7 >= this.f7332w) {
                return false;
            }
        }
        return true;
    }

    public final k6.i w0(List<k6.c> requestHeaders, boolean z6) {
        kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
        return v0(0, requestHeaders, z6);
    }

    public final void x0(int i7, p6.g source, int i8, boolean z6) {
        kotlin.jvm.internal.l.e(source, "source");
        p6.e eVar = new p6.e();
        long j7 = i8;
        source.F(j7);
        source.i(eVar, j7);
        g6.d dVar = this.f7324o;
        String str = this.f7318i + '[' + i7 + "] onData";
        dVar.i(new C0102f(str, true, str, true, this, i7, eVar, i8, z6), 0L);
    }

    public final void y0(int i7, List<k6.c> requestHeaders, boolean z6) {
        kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
        g6.d dVar = this.f7324o;
        String str = this.f7318i + '[' + i7 + "] onHeaders";
        dVar.i(new g(str, true, str, true, this, i7, requestHeaders, z6), 0L);
    }

    public final void z0(int i7, List<k6.c> requestHeaders) {
        kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
        synchronized (this) {
            if (this.G.contains(Integer.valueOf(i7))) {
                O0(i7, k6.b.PROTOCOL_ERROR);
                return;
            }
            this.G.add(Integer.valueOf(i7));
            g6.d dVar = this.f7324o;
            String str = this.f7318i + '[' + i7 + "] onRequest";
            dVar.i(new h(str, true, str, true, this, i7, requestHeaders), 0L);
        }
    }
}
