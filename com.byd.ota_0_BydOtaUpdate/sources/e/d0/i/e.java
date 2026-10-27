package e.d0.i;

import e.d0.i.f;
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
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;

/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Closeable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ExecutorService f3469c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), e.d0.c.x("OkHttp Http2Connection", true));
    public int E3;
    public int F3;
    public boolean G3;
    public final ExecutorService H3;
    public Map<Integer, e.d0.i.j> I3;
    public final k J3;
    public int K3;
    public long M3;
    public final l O3;
    public boolean P3;
    public final Socket Q3;
    public final e.d0.i.h R3;
    public final j S3;
    public final Set<Integer> T3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f3470d;
    public final i s;
    public final String y;
    public final Map<Integer, e.d0.i.g> x = new LinkedHashMap();
    public long L3 = 0;
    public l N3 = new l();

    /* JADX INFO: compiled from: Http2Connection.java */
    public class a extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3471d;
        public final /* synthetic */ ErrorCode s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Object[] objArr, int i2, ErrorCode errorCode) {
            super(str, objArr);
            this.f3471d = i2;
            this.s = errorCode;
        }

        @Override // e.d0.b
        public void k() {
            try {
                e.this.X(this.f3471d, this.s);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class b extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3472d;
        public final /* synthetic */ long s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Object[] objArr, int i2, long j) {
            super(str, objArr);
            this.f3472d = i2;
            this.s = j;
        }

        @Override // e.d0.b
        public void k() {
            try {
                e.this.R3.Q(this.f3472d, this.s);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class c extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f3473d;
        public final /* synthetic */ int s;
        public final /* synthetic */ int x;
        public final /* synthetic */ e.d0.i.j y;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, Object[] objArr, boolean z, int i2, int i3, e.d0.i.j jVar) {
            super(str, objArr);
            this.f3473d = z;
            this.s = i2;
            this.x = i3;
            this.y = jVar;
        }

        @Override // e.d0.b
        public void k() {
            try {
                e.this.V(this.f3473d, this.s, this.x, this.y);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class d extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3474d;
        public final /* synthetic */ List s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Object[] objArr, int i2, List list) {
            super(str, objArr);
            this.f3474d = i2;
            this.s = list;
        }

        @Override // e.d0.b
        public void k() {
            if (e.this.J3.a(this.f3474d, this.s)) {
                try {
                    e.this.R3.N(this.f3474d, ErrorCode.CANCEL);
                    synchronized (e.this) {
                        e.this.T3.remove(Integer.valueOf(this.f3474d));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: renamed from: e.d0.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Http2Connection.java */
    public class C0083e extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3475d;
        public final /* synthetic */ List s;
        public final /* synthetic */ boolean x;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0083e(String str, Object[] objArr, int i2, List list, boolean z) {
            super(str, objArr);
            this.f3475d = i2;
            this.s = list;
            this.x = z;
        }

        @Override // e.d0.b
        public void k() {
            boolean zB = e.this.J3.b(this.f3475d, this.s, this.x);
            if (zB) {
                try {
                    e.this.R3.N(this.f3475d, ErrorCode.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zB || this.x) {
                synchronized (e.this) {
                    e.this.T3.remove(Integer.valueOf(this.f3475d));
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class f extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3476d;
        public final /* synthetic */ f.c s;
        public final /* synthetic */ int x;
        public final /* synthetic */ boolean y;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Object[] objArr, int i2, f.c cVar, int i3, boolean z) {
            super(str, objArr);
            this.f3476d = i2;
            this.s = cVar;
            this.x = i3;
            this.y = z;
        }

        @Override // e.d0.b
        public void k() {
            try {
                boolean zD = e.this.J3.d(this.f3476d, this.s, this.x, this.y);
                if (zD) {
                    e.this.R3.N(this.f3476d, ErrorCode.CANCEL);
                }
                if (zD || this.y) {
                    synchronized (e.this) {
                        e.this.T3.remove(Integer.valueOf(this.f3476d));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class g extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3477d;
        public final /* synthetic */ ErrorCode s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Object[] objArr, int i2, ErrorCode errorCode) {
            super(str, objArr);
            this.f3477d = i2;
            this.s = errorCode;
        }

        @Override // e.d0.b
        public void k() {
            e.this.J3.c(this.f3477d, this.s);
            synchronized (e.this) {
                e.this.T3.remove(Integer.valueOf(this.f3477d));
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Socket f3478a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3479b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public f.e f3480c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public f.d f3481d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public i f3482e = i.f3485a;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public k f3483f = k.f3519a;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3484g;

        public h(boolean z) {
            this.f3484g = z;
        }

        public e a() throws IOException {
            return new e(this);
        }

        public h b(i iVar) {
            this.f3482e = iVar;
            return this;
        }

        public h c(Socket socket, String str, f.e eVar, f.d dVar) {
            this.f3478a = socket;
            this.f3479b = str;
            this.f3480c = eVar;
            this.f3481d = dVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static abstract class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f3485a = new a();

        /* JADX INFO: compiled from: Http2Connection.java */
        public class a extends i {
            @Override // e.d0.i.e.i
            public void d(e.d0.i.g gVar) throws IOException {
                gVar.d(ErrorCode.REFUSED_STREAM);
            }
        }

        public void c(e eVar) {
        }

        public abstract void d(e.d0.i.g gVar) throws IOException;
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class j extends e.d0.b implements f.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final e.d0.i.f f3486d;

        /* JADX INFO: compiled from: Http2Connection.java */
        public class a extends e.d0.b {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ e.d0.i.g f3487d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, Object[] objArr, e.d0.i.g gVar) {
                super(str, objArr);
                this.f3487d = gVar;
            }

            @Override // e.d0.b
            public void k() {
                try {
                    e.this.s.d(this.f3487d);
                } catch (IOException e2) {
                    e.d0.j.e.h().l(4, "Http2Connection.Listener failure for " + e.this.y, e2);
                    try {
                        this.f3487d.d(ErrorCode.PROTOCOL_ERROR);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class b extends e.d0.b {
            public b(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // e.d0.b
            public void k() {
                e eVar = e.this;
                eVar.s.c(eVar);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class c extends e.d0.b {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ l f3489d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str, Object[] objArr, l lVar) {
                super(str, objArr);
                this.f3489d = lVar;
            }

            @Override // e.d0.b
            public void k() {
                try {
                    e.this.R3.c(this.f3489d);
                } catch (IOException unused) {
                }
            }
        }

        public j(e.d0.i.f fVar) {
            super("OkHttp %s", e.this.y);
            this.f3486d = fVar;
        }

        @Override // e.d0.i.f.b
        public void a(boolean z, int i2, int i3, List<e.d0.i.a> list) {
            if (e.this.O(i2)) {
                e.this.L(i2, list, z);
                return;
            }
            synchronized (e.this) {
                e eVar = e.this;
                if (eVar.G3) {
                    return;
                }
                e.d0.i.g gVarS = eVar.s(i2);
                if (gVarS != null) {
                    gVarS.o(list);
                    if (z) {
                        gVarS.n();
                        return;
                    }
                    return;
                }
                e eVar2 = e.this;
                if (i2 <= eVar2.E3) {
                    return;
                }
                if (i2 % 2 == eVar2.F3 % 2) {
                    return;
                }
                e.d0.i.g gVar = new e.d0.i.g(i2, e.this, false, z, list);
                e eVar3 = e.this;
                eVar3.E3 = i2;
                eVar3.x.put(Integer.valueOf(i2), gVar);
                e.f3469c.execute(new a("OkHttp %s stream %d", new Object[]{e.this.y, Integer.valueOf(i2)}, gVar));
            }
        }

        @Override // e.d0.i.f.b
        public void b() {
        }

        @Override // e.d0.i.f.b
        public void c(int i2, long j) {
            if (i2 == 0) {
                synchronized (e.this) {
                    e eVar = e.this;
                    eVar.M3 += j;
                    eVar.notifyAll();
                }
                return;
            }
            e.d0.i.g gVarS = e.this.s(i2);
            if (gVarS != null) {
                synchronized (gVarS) {
                    gVarS.a(j);
                }
            }
        }

        @Override // e.d0.i.f.b
        public void d(boolean z, l lVar) {
            e.d0.i.g[] gVarArr;
            long j;
            int i2;
            synchronized (e.this) {
                int iD = e.this.O3.d();
                if (z) {
                    e.this.O3.a();
                }
                e.this.O3.h(lVar);
                l(lVar);
                int iD2 = e.this.O3.d();
                gVarArr = null;
                if (iD2 == -1 || iD2 == iD) {
                    j = 0;
                } else {
                    j = iD2 - iD;
                    e eVar = e.this;
                    if (!eVar.P3) {
                        eVar.c(j);
                        e.this.P3 = true;
                    }
                    if (!e.this.x.isEmpty()) {
                        gVarArr = (e.d0.i.g[]) e.this.x.values().toArray(new e.d0.i.g[e.this.x.size()]);
                    }
                }
                e.f3469c.execute(new b("OkHttp %s settings", e.this.y));
            }
            if (gVarArr == null || j == 0) {
                return;
            }
            for (e.d0.i.g gVar : gVarArr) {
                synchronized (gVar) {
                    gVar.a(j);
                }
            }
        }

        @Override // e.d0.i.f.b
        public void e(int i2, int i3, List<e.d0.i.a> list) {
            e.this.M(i3, list);
        }

        @Override // e.d0.i.f.b
        public void f(boolean z, int i2, f.e eVar, int i3) throws IOException {
            if (e.this.O(i2)) {
                e.this.K(i2, eVar, i3, z);
                return;
            }
            e.d0.i.g gVarS = e.this.s(i2);
            if (gVarS == null) {
                e.this.Y(i2, ErrorCode.PROTOCOL_ERROR);
                eVar.skip(i3);
            } else {
                gVarS.m(eVar, i3);
                if (z) {
                    gVarS.n();
                }
            }
        }

        @Override // e.d0.i.f.b
        public void g(boolean z, int i2, int i3) {
            if (!z) {
                e.this.W(true, i2, i3, null);
                return;
            }
            e.d0.i.j jVarP = e.this.P(i2);
            if (jVarP != null) {
                jVarP.b();
            }
        }

        @Override // e.d0.i.f.b
        public void h(int i2, ErrorCode errorCode, ByteString byteString) {
            e.d0.i.g[] gVarArr;
            byteString.size();
            synchronized (e.this) {
                gVarArr = (e.d0.i.g[]) e.this.x.values().toArray(new e.d0.i.g[e.this.x.size()]);
                e.this.G3 = true;
            }
            for (e.d0.i.g gVar : gVarArr) {
                if (gVar.g() > i2 && gVar.j()) {
                    gVar.p(ErrorCode.REFUSED_STREAM);
                    e.this.Q(gVar.g());
                }
            }
        }

        @Override // e.d0.i.f.b
        public void i(int i2, int i3, int i4, boolean z) {
        }

        @Override // e.d0.i.f.b
        public void j(int i2, ErrorCode errorCode) {
            if (e.this.O(i2)) {
                e.this.N(i2, errorCode);
                return;
            }
            e.d0.i.g gVarQ = e.this.Q(i2);
            if (gVarQ != null) {
                gVarQ.p(errorCode);
            }
        }

        @Override // e.d0.b
        public void k() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    this.f3486d.s(this);
                    while (this.f3486d.d(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                    try {
                        try {
                            e.this.d(errorCode, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                            e.this.d(errorCode3, errorCode3);
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            e.this.d(errorCode, errorCode2);
                        } catch (IOException unused2) {
                        }
                        e.d0.c.c(this.f3486d);
                        throw th;
                    }
                } catch (IOException unused3) {
                }
            } catch (IOException unused4) {
                errorCode = errorCode2;
            } catch (Throwable th2) {
                th = th2;
                errorCode = errorCode2;
                e.this.d(errorCode, errorCode2);
                e.d0.c.c(this.f3486d);
                throw th;
            }
            e.d0.c.c(this.f3486d);
        }

        public final void l(l lVar) {
            e.f3469c.execute(new c("OkHttp %s ACK Settings", new Object[]{e.this.y}, lVar));
        }
    }

    public e(h hVar) {
        l lVar = new l();
        this.O3 = lVar;
        this.P3 = false;
        this.T3 = new LinkedHashSet();
        this.J3 = hVar.f3483f;
        boolean z = hVar.f3484g;
        this.f3470d = z;
        this.s = hVar.f3482e;
        int i2 = z ? 1 : 2;
        this.F3 = i2;
        if (z) {
            this.F3 = i2 + 2;
        }
        this.K3 = z ? 1 : 2;
        if (z) {
            this.N3.i(7, 16777216);
        }
        String str = hVar.f3479b;
        this.y = str;
        this.H3 = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), e.d0.c.x(e.d0.c.l("OkHttp %s Push Observer", str), true));
        lVar.i(7, 65535);
        lVar.i(5, 16384);
        this.M3 = lVar.d();
        this.Q3 = hVar.f3478a;
        this.R3 = new e.d0.i.h(hVar.f3481d, z);
        this.S3 = new j(new e.d0.i.f(hVar.f3480c, z));
    }

    public synchronized int H() {
        return this.O3.e(Integer.MAX_VALUE);
    }

    public final e.d0.i.g I(int i2, List<e.d0.i.a> list, boolean z) throws IOException {
        int i3;
        e.d0.i.g gVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.R3) {
            synchronized (this) {
                if (this.G3) {
                    throw new ConnectionShutdownException();
                }
                i3 = this.F3;
                this.F3 = i3 + 2;
                gVar = new e.d0.i.g(i3, this, z3, false, list);
                z2 = !z || this.M3 == 0 || gVar.f3495b == 0;
                if (gVar.k()) {
                    this.x.put(Integer.valueOf(i3), gVar);
                }
            }
            if (i2 == 0) {
                this.R3.P(z3, i3, i2, list);
            } else {
                if (this.f3470d) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.R3.M(i2, i3, list);
            }
        }
        if (z2) {
            this.R3.flush();
        }
        return gVar;
    }

    public e.d0.i.g J(List<e.d0.i.a> list, boolean z) throws IOException {
        return I(0, list, z);
    }

    public void K(int i2, f.e eVar, int i3, boolean z) throws IOException {
        f.c cVar = new f.c();
        long j2 = i3;
        eVar.u(j2);
        eVar.n(cVar, j2);
        if (cVar.Y() == j2) {
            this.H3.execute(new f("OkHttp %s Push Data[%s]", new Object[]{this.y, Integer.valueOf(i2)}, i2, cVar, i3, z));
            return;
        }
        throw new IOException(cVar.Y() + " != " + i3);
    }

    public void L(int i2, List<e.d0.i.a> list, boolean z) {
        this.H3.execute(new C0083e("OkHttp %s Push Headers[%s]", new Object[]{this.y, Integer.valueOf(i2)}, i2, list, z));
    }

    public void M(int i2, List<e.d0.i.a> list) {
        synchronized (this) {
            if (this.T3.contains(Integer.valueOf(i2))) {
                Y(i2, ErrorCode.PROTOCOL_ERROR);
            } else {
                this.T3.add(Integer.valueOf(i2));
                this.H3.execute(new d("OkHttp %s Push Request[%s]", new Object[]{this.y, Integer.valueOf(i2)}, i2, list));
            }
        }
    }

    public void N(int i2, ErrorCode errorCode) {
        this.H3.execute(new g("OkHttp %s Push Reset[%s]", new Object[]{this.y, Integer.valueOf(i2)}, i2, errorCode));
    }

    public boolean O(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    public synchronized e.d0.i.j P(int i2) {
        Map<Integer, e.d0.i.j> map;
        map = this.I3;
        return map != null ? map.remove(Integer.valueOf(i2)) : null;
    }

    public synchronized e.d0.i.g Q(int i2) {
        e.d0.i.g gVarRemove;
        gVarRemove = this.x.remove(Integer.valueOf(i2));
        notifyAll();
        return gVarRemove;
    }

    public void R(ErrorCode errorCode) throws IOException {
        synchronized (this.R3) {
            synchronized (this) {
                if (this.G3) {
                    return;
                }
                this.G3 = true;
                this.R3.I(this.E3, errorCode, e.d0.c.f3336a);
            }
        }
    }

    public void S() throws IOException {
        T(true);
    }

    public void T(boolean z) throws IOException {
        if (z) {
            this.R3.d();
            this.R3.O(this.N3);
            if (this.N3.d() != 65535) {
                this.R3.Q(0, r6 - 65535);
            }
        }
        new Thread(this.S3).start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r4), r8.R3.K());
        r6 = r2;
        r8.M3 -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void U(int r9, boolean r10, f.c r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            e.d0.i.h r12 = r8.R3
            r12.s(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L60
            monitor-enter(r8)
        L12:
            long r4 = r8.M3     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L30
            java.util.Map<java.lang.Integer, e.d0.i.g> r2 = r8.x     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
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
            e.d0.i.h r4 = r8.R3     // Catch: java.lang.Throwable -> L56
            int r4 = r4.K()     // Catch: java.lang.Throwable -> L56
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.M3     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.M3 = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            e.d0.i.h r4 = r8.R3
            if (r10 == 0) goto L51
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = r3
        L52:
            r4.s(r5, r9, r11, r2)
            goto Ld
        L56:
            r9 = move-exception
            goto L5e
        L58:
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L56
            r9.<init>()     // Catch: java.lang.Throwable -> L56
            throw r9     // Catch: java.lang.Throwable -> L56
        L5e:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            throw r9
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: e.d0.i.e.U(int, boolean, f.c, long):void");
    }

    public void V(boolean z, int i2, int i3, e.d0.i.j jVar) throws IOException {
        synchronized (this.R3) {
            if (jVar != null) {
                jVar.c();
                this.R3.L(z, i2, i3);
            } else {
                this.R3.L(z, i2, i3);
            }
        }
    }

    public void W(boolean z, int i2, int i3, e.d0.i.j jVar) {
        f3469c.execute(new c("OkHttp %s ping %08x%08x", new Object[]{this.y, Integer.valueOf(i2), Integer.valueOf(i3)}, z, i2, i3, jVar));
    }

    public void X(int i2, ErrorCode errorCode) throws IOException {
        this.R3.N(i2, errorCode);
    }

    public void Y(int i2, ErrorCode errorCode) {
        f3469c.execute(new a("OkHttp %s stream %d", new Object[]{this.y, Integer.valueOf(i2)}, i2, errorCode));
    }

    public void Z(int i2, long j2) {
        f3469c.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.y, Integer.valueOf(i2)}, i2, j2));
    }

    public void c(long j2) {
        this.M3 += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        d(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    public void d(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        e.d0.i.g[] gVarArr;
        e.d0.i.j[] jVarArr = null;
        try {
            R(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (this.x.isEmpty()) {
                gVarArr = null;
            } else {
                gVarArr = (e.d0.i.g[]) this.x.values().toArray(new e.d0.i.g[this.x.size()]);
                this.x.clear();
            }
            Map<Integer, e.d0.i.j> map = this.I3;
            if (map != null) {
                e.d0.i.j[] jVarArr2 = (e.d0.i.j[]) map.values().toArray(new e.d0.i.j[this.I3.size()]);
                this.I3 = null;
                jVarArr = jVarArr2;
            }
        }
        if (gVarArr != null) {
            for (e.d0.i.g gVar : gVarArr) {
                try {
                    gVar.d(errorCode2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        if (jVarArr != null) {
            for (e.d0.i.j jVar : jVarArr) {
                jVar.a();
            }
        }
        try {
            this.R3.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.Q3.close();
        } catch (IOException e5) {
            e = e5;
        }
        if (e != null) {
            throw e;
        }
    }

    public void flush() throws IOException {
        this.R3.flush();
    }

    public synchronized e.d0.i.g s(int i2) {
        return this.x.get(Integer.valueOf(i2));
    }

    public synchronized boolean v() {
        return this.G3;
    }
}
