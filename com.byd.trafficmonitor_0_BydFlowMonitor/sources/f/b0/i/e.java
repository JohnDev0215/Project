package f.b0.i;

import f.b0.i.f;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ExecutorService f5161b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), f.b0.c.x("OkHttp Http2Connection", true));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5162c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i f5163d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f5165f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5166g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5167h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5168i;
    public final ExecutorService j;
    public Map<Integer, f.b0.i.j> k;
    public final k l;
    public int m;
    public long o;
    public final l q;
    public boolean r;
    public final Socket s;
    public final f.b0.i.h t;
    public final j u;
    public final Set<Integer> v;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<Integer, f.b0.i.g> f5164e = new LinkedHashMap();
    public long n = 0;
    public l p = new l();

    /* JADX INFO: compiled from: Http2Connection.java */
    public class a extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5169c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ErrorCode f5170d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, Object[] objArr, int i2, ErrorCode errorCode) {
            super(str, objArr);
            this.f5169c = i2;
            this.f5170d = errorCode;
        }

        @Override // f.b0.b
        public void k() {
            try {
                e.this.g0(this.f5169c, this.f5170d);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class b extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5172c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f5173d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Object[] objArr, int i2, long j) {
            super(str, objArr);
            this.f5172c = i2;
            this.f5173d = j;
        }

        @Override // f.b0.b
        public void k() {
            try {
                e.this.t.Z(this.f5172c, this.f5173d);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class c extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f5175c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5176d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f5177e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ f.b0.i.j f5178f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, Object[] objArr, boolean z, int i2, int i3, f.b0.i.j jVar) {
            super(str, objArr);
            this.f5175c = z;
            this.f5176d = i2;
            this.f5177e = i3;
            this.f5178f = jVar;
        }

        @Override // f.b0.b
        public void k() {
            try {
                e.this.e0(this.f5175c, this.f5176d, this.f5177e, this.f5178f);
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class d extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5180c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f5181d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(String str, Object[] objArr, int i2, List list) {
            super(str, objArr);
            this.f5180c = i2;
            this.f5181d = list;
        }

        @Override // f.b0.b
        public void k() {
            if (e.this.l.a(this.f5180c, this.f5181d)) {
                try {
                    e.this.t.W(this.f5180c, ErrorCode.CANCEL);
                    synchronized (e.this) {
                        e.this.v.remove(Integer.valueOf(this.f5180c));
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: renamed from: f.b0.i.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Http2Connection.java */
    public class C0103e extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5183c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ List f5184d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f5185e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0103e(String str, Object[] objArr, int i2, List list, boolean z) {
            super(str, objArr);
            this.f5183c = i2;
            this.f5184d = list;
            this.f5185e = z;
        }

        @Override // f.b0.b
        public void k() {
            boolean zB = e.this.l.b(this.f5183c, this.f5184d, this.f5185e);
            if (zB) {
                try {
                    e.this.t.W(this.f5183c, ErrorCode.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zB || this.f5185e) {
                synchronized (e.this) {
                    e.this.v.remove(Integer.valueOf(this.f5183c));
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class f extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5187c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ g.c f5188d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f5189e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ boolean f5190f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(String str, Object[] objArr, int i2, g.c cVar, int i3, boolean z) {
            super(str, objArr);
            this.f5187c = i2;
            this.f5188d = cVar;
            this.f5189e = i3;
            this.f5190f = z;
        }

        @Override // f.b0.b
        public void k() {
            try {
                boolean zD = e.this.l.d(this.f5187c, this.f5188d, this.f5189e, this.f5190f);
                if (zD) {
                    e.this.t.W(this.f5187c, ErrorCode.CANCEL);
                }
                if (zD || this.f5190f) {
                    synchronized (e.this) {
                        e.this.v.remove(Integer.valueOf(this.f5187c));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class g extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f5192c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ErrorCode f5193d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Object[] objArr, int i2, ErrorCode errorCode) {
            super(str, objArr);
            this.f5192c = i2;
            this.f5193d = errorCode;
        }

        @Override // f.b0.b
        public void k() {
            e.this.l.c(this.f5192c, this.f5193d);
            synchronized (e.this) {
                e.this.v.remove(Integer.valueOf(this.f5192c));
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Socket f5195a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5196b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public g.e f5197c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g.d f5198d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public i f5199e = i.f5202a;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public k f5200f = k.f5257a;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5201g;

        public h(boolean z) {
            this.f5201g = z;
        }

        public e a() throws IOException {
            return new e(this);
        }

        public h b(i iVar) {
            this.f5199e = iVar;
            return this;
        }

        public h c(Socket socket, String str, g.e eVar, g.d dVar) {
            this.f5195a = socket;
            this.f5196b = str;
            this.f5197c = eVar;
            this.f5198d = dVar;
            return this;
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static abstract class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f5202a = new a();

        /* JADX INFO: compiled from: Http2Connection.java */
        public static class a extends i {
            @Override // f.b0.i.e.i
            public void c(f.b0.i.g gVar) throws IOException {
                gVar.d(ErrorCode.REFUSED_STREAM);
            }
        }

        public void b(e eVar) {
        }

        public abstract void c(f.b0.i.g gVar) throws IOException;
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public class j extends f.b0.b implements f.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.b0.i.f f5203c;

        /* JADX INFO: compiled from: Http2Connection.java */
        public class a extends f.b0.b {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ f.b0.i.g f5205c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(String str, Object[] objArr, f.b0.i.g gVar) {
                super(str, objArr);
                this.f5205c = gVar;
            }

            @Override // f.b0.b
            public void k() {
                try {
                    e.this.f5163d.c(this.f5205c);
                } catch (IOException e2) {
                    f.b0.j.e.h().l(4, "Http2Connection.Listener failure for " + e.this.f5165f, e2);
                    try {
                        this.f5205c.d(ErrorCode.PROTOCOL_ERROR);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class b extends f.b0.b {
            public b(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // f.b0.b
            public void k() {
                e eVar = e.this;
                eVar.f5163d.b(eVar);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        public class c extends f.b0.b {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ l f5208c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(String str, Object[] objArr, l lVar) {
                super(str, objArr);
                this.f5208c = lVar;
            }

            @Override // f.b0.b
            public void k() {
                try {
                    e.this.t.w(this.f5208c);
                } catch (IOException unused) {
                }
            }
        }

        public j(f.b0.i.f fVar) {
            super("OkHttp %s", e.this.f5165f);
            this.f5203c = fVar;
        }

        @Override // f.b0.i.f.b
        public void a(boolean z, int i2, int i3, List<f.b0.i.a> list) {
            if (e.this.X(i2)) {
                e.this.U(i2, list, z);
                return;
            }
            synchronized (e.this) {
                e eVar = e.this;
                if (eVar.f5168i) {
                    return;
                }
                f.b0.i.g gVarO = eVar.O(i2);
                if (gVarO != null) {
                    gVarO.o(list);
                    if (z) {
                        gVarO.n();
                        return;
                    }
                    return;
                }
                e eVar2 = e.this;
                if (i2 <= eVar2.f5166g) {
                    return;
                }
                if (i2 % 2 == eVar2.f5167h % 2) {
                    return;
                }
                f.b0.i.g gVar = new f.b0.i.g(i2, e.this, false, z, list);
                e eVar3 = e.this;
                eVar3.f5166g = i2;
                eVar3.f5164e.put(Integer.valueOf(i2), gVar);
                e.f5161b.execute(new a("OkHttp %s stream %d", new Object[]{e.this.f5165f, Integer.valueOf(i2)}, gVar));
            }
        }

        @Override // f.b0.i.f.b
        public void b() {
        }

        @Override // f.b0.i.f.b
        public void c(int i2, long j) {
            if (i2 == 0) {
                synchronized (e.this) {
                    e eVar = e.this;
                    eVar.o += j;
                    eVar.notifyAll();
                }
                return;
            }
            f.b0.i.g gVarO = e.this.O(i2);
            if (gVarO != null) {
                synchronized (gVarO) {
                    gVarO.a(j);
                }
            }
        }

        @Override // f.b0.i.f.b
        public void d(boolean z, l lVar) {
            f.b0.i.g[] gVarArr;
            long j;
            int i2;
            synchronized (e.this) {
                int iD = e.this.q.d();
                if (z) {
                    e.this.q.a();
                }
                e.this.q.h(lVar);
                l(lVar);
                int iD2 = e.this.q.d();
                gVarArr = null;
                if (iD2 == -1 || iD2 == iD) {
                    j = 0;
                } else {
                    j = iD2 - iD;
                    e eVar = e.this;
                    if (!eVar.r) {
                        eVar.w(j);
                        e.this.r = true;
                    }
                    if (!e.this.f5164e.isEmpty()) {
                        gVarArr = (f.b0.i.g[]) e.this.f5164e.values().toArray(new f.b0.i.g[e.this.f5164e.size()]);
                    }
                }
                e.f5161b.execute(new b("OkHttp %s settings", e.this.f5165f));
            }
            if (gVarArr == null || j == 0) {
                return;
            }
            for (f.b0.i.g gVar : gVarArr) {
                synchronized (gVar) {
                    gVar.a(j);
                }
            }
        }

        @Override // f.b0.i.f.b
        public void e(int i2, int i3, List<f.b0.i.a> list) {
            e.this.V(i3, list);
        }

        @Override // f.b0.i.f.b
        public void f(boolean z, int i2, g.e eVar, int i3) throws IOException {
            if (e.this.X(i2)) {
                e.this.T(i2, eVar, i3, z);
                return;
            }
            f.b0.i.g gVarO = e.this.O(i2);
            if (gVarO == null) {
                e.this.h0(i2, ErrorCode.PROTOCOL_ERROR);
                eVar.o(i3);
            } else {
                gVarO.m(eVar, i3);
                if (z) {
                    gVarO.n();
                }
            }
        }

        @Override // f.b0.i.f.b
        public void g(boolean z, int i2, int i3) {
            if (!z) {
                e.this.f0(true, i2, i3, null);
                return;
            }
            f.b0.i.j jVarY = e.this.Y(i2);
            if (jVarY != null) {
                jVarY.b();
            }
        }

        @Override // f.b0.i.f.b
        public void h(int i2, ErrorCode errorCode, ByteString byteString) {
            f.b0.i.g[] gVarArr;
            byteString.size();
            synchronized (e.this) {
                gVarArr = (f.b0.i.g[]) e.this.f5164e.values().toArray(new f.b0.i.g[e.this.f5164e.size()]);
                e.this.f5168i = true;
            }
            for (f.b0.i.g gVar : gVarArr) {
                if (gVar.g() > i2 && gVar.j()) {
                    gVar.p(ErrorCode.REFUSED_STREAM);
                    e.this.Z(gVar.g());
                }
            }
        }

        @Override // f.b0.i.f.b
        public void i(int i2, int i3, int i4, boolean z) {
        }

        @Override // f.b0.i.f.b
        public void j(int i2, ErrorCode errorCode) {
            if (e.this.X(i2)) {
                e.this.W(i2, errorCode);
                return;
            }
            f.b0.i.g gVarZ = e.this.Z(i2);
            if (gVarZ != null) {
                gVarZ.p(errorCode);
            }
        }

        @Override // f.b0.b
        public void k() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
            try {
                try {
                    this.f5203c.O(this);
                    while (this.f5203c.z(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                } catch (IOException unused) {
                    errorCode = errorCode2;
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode2;
                    try {
                        e.this.z(errorCode, errorCode2);
                    } catch (IOException unused2) {
                    }
                    f.b0.c.c(this.f5203c);
                    throw th;
                }
                try {
                    try {
                        e.this.z(errorCode, ErrorCode.CANCEL);
                    } catch (Throwable th2) {
                        th = th2;
                        e.this.z(errorCode, errorCode2);
                        f.b0.c.c(this.f5203c);
                        throw th;
                    }
                } catch (IOException unused3) {
                    ErrorCode errorCode3 = ErrorCode.PROTOCOL_ERROR;
                    e.this.z(errorCode3, errorCode3);
                }
            } catch (IOException unused4) {
            }
            f.b0.c.c(this.f5203c);
        }

        public final void l(l lVar) {
            e.f5161b.execute(new c("OkHttp %s ACK Settings", new Object[]{e.this.f5165f}, lVar));
        }
    }

    public e(h hVar) {
        l lVar = new l();
        this.q = lVar;
        this.r = false;
        this.v = new LinkedHashSet();
        this.l = hVar.f5200f;
        boolean z = hVar.f5201g;
        this.f5162c = z;
        this.f5163d = hVar.f5199e;
        int i2 = z ? 1 : 2;
        this.f5167h = i2;
        if (z) {
            this.f5167h = i2 + 2;
        }
        this.m = z ? 1 : 2;
        if (z) {
            this.p.i(7, 16777216);
        }
        String str = hVar.f5196b;
        this.f5165f = str;
        this.j = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f.b0.c.x(f.b0.c.l("OkHttp %s Push Observer", str), true));
        lVar.i(7, 65535);
        lVar.i(5, 16384);
        this.o = lVar.d();
        this.s = hVar.f5195a;
        this.t = new f.b0.i.h(hVar.f5198d, z);
        this.u = new j(new f.b0.i.f(hVar.f5197c, z));
    }

    public synchronized f.b0.i.g O(int i2) {
        return this.f5164e.get(Integer.valueOf(i2));
    }

    public synchronized boolean P() {
        return this.f5168i;
    }

    public synchronized int Q() {
        return this.q.e(Integer.MAX_VALUE);
    }

    public final f.b0.i.g R(int i2, List<f.b0.i.a> list, boolean z) throws IOException {
        int i3;
        f.b0.i.g gVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.t) {
            synchronized (this) {
                if (this.f5168i) {
                    throw new ConnectionShutdownException();
                }
                i3 = this.f5167h;
                this.f5167h = i3 + 2;
                gVar = new f.b0.i.g(i3, this, z3, false, list);
                z2 = !z || this.o == 0 || gVar.f5222b == 0;
                if (gVar.k()) {
                    this.f5164e.put(Integer.valueOf(i3), gVar);
                }
            }
            if (i2 == 0) {
                this.t.Y(z3, i3, i2, list);
            } else {
                if (this.f5162c) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.t.V(i2, i3, list);
            }
        }
        if (z2) {
            this.t.flush();
        }
        return gVar;
    }

    public f.b0.i.g S(List<f.b0.i.a> list, boolean z) throws IOException {
        return R(0, list, z);
    }

    public void T(int i2, g.e eVar, int i3, boolean z) throws IOException {
        g.c cVar = new g.c();
        long j2 = i3;
        eVar.y(j2);
        eVar.m(cVar, j2);
        if (cVar.f0() == j2) {
            this.j.execute(new f("OkHttp %s Push Data[%s]", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, cVar, i3, z));
            return;
        }
        throw new IOException(cVar.f0() + " != " + i3);
    }

    public void U(int i2, List<f.b0.i.a> list, boolean z) {
        this.j.execute(new C0103e("OkHttp %s Push Headers[%s]", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, list, z));
    }

    public void V(int i2, List<f.b0.i.a> list) {
        synchronized (this) {
            if (this.v.contains(Integer.valueOf(i2))) {
                h0(i2, ErrorCode.PROTOCOL_ERROR);
            } else {
                this.v.add(Integer.valueOf(i2));
                this.j.execute(new d("OkHttp %s Push Request[%s]", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, list));
            }
        }
    }

    public void W(int i2, ErrorCode errorCode) {
        this.j.execute(new g("OkHttp %s Push Reset[%s]", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, errorCode));
    }

    public boolean X(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    public synchronized f.b0.i.j Y(int i2) {
        Map<Integer, f.b0.i.j> map;
        map = this.k;
        return map != null ? map.remove(Integer.valueOf(i2)) : null;
    }

    public synchronized f.b0.i.g Z(int i2) {
        f.b0.i.g gVarRemove;
        gVarRemove = this.f5164e.remove(Integer.valueOf(i2));
        notifyAll();
        return gVarRemove;
    }

    public void a0(ErrorCode errorCode) throws IOException {
        synchronized (this.t) {
            synchronized (this) {
                if (this.f5168i) {
                    return;
                }
                this.f5168i = true;
                this.t.R(this.f5166g, errorCode, f.b0.c.f5012a);
            }
        }
    }

    public void b0() throws IOException {
        c0(true);
    }

    public void c0(boolean z) throws IOException {
        if (z) {
            this.t.z();
            this.t.X(this.p);
            if (this.p.d() != 65535) {
                this.t.Z(0, r6 - 65535);
            }
        }
        new Thread(this.u).start();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        z(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r4), r8.t.T());
        r6 = r2;
        r8.o -= r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d0(int r9, boolean r10, g.c r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            f.b0.i.h r12 = r8.t
            r12.O(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L60
            monitor-enter(r8)
        L12:
            long r4 = r8.o     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L30
            java.util.Map<java.lang.Integer, f.b0.i.g> r2 = r8.f5164e     // Catch: java.lang.Throwable -> L56 java.lang.InterruptedException -> L58
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
            f.b0.i.h r4 = r8.t     // Catch: java.lang.Throwable -> L56
            int r4 = r4.T()     // Catch: java.lang.Throwable -> L56
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L56
            long r4 = r8.o     // Catch: java.lang.Throwable -> L56
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L56
            long r4 = r4 - r6
            r8.o = r4     // Catch: java.lang.Throwable -> L56
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L56
            long r12 = r12 - r6
            f.b0.i.h r4 = r8.t
            if (r10 == 0) goto L51
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L51
            r5 = 1
            goto L52
        L51:
            r5 = r3
        L52:
            r4.O(r5, r9, r11, r2)
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
        throw new UnsupportedOperationException("Method not decompiled: f.b0.i.e.d0(int, boolean, g.c, long):void");
    }

    public void e0(boolean z, int i2, int i3, f.b0.i.j jVar) throws IOException {
        synchronized (this.t) {
            if (jVar != null) {
                jVar.c();
                this.t.U(z, i2, i3);
            } else {
                this.t.U(z, i2, i3);
            }
        }
    }

    public void f0(boolean z, int i2, int i3, f.b0.i.j jVar) {
        f5161b.execute(new c("OkHttp %s ping %08x%08x", new Object[]{this.f5165f, Integer.valueOf(i2), Integer.valueOf(i3)}, z, i2, i3, jVar));
    }

    public void flush() throws IOException {
        this.t.flush();
    }

    public void g0(int i2, ErrorCode errorCode) throws IOException {
        this.t.W(i2, errorCode);
    }

    public void h0(int i2, ErrorCode errorCode) {
        f5161b.execute(new a("OkHttp %s stream %d", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, errorCode));
    }

    public void i0(int i2, long j2) {
        f5161b.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.f5165f, Integer.valueOf(i2)}, i2, j2));
    }

    public void w(long j2) {
        this.o += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    public void z(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        f.b0.i.g[] gVarArr;
        f.b0.i.j[] jVarArr = null;
        try {
            a0(errorCode);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (this.f5164e.isEmpty()) {
                gVarArr = null;
            } else {
                gVarArr = (f.b0.i.g[]) this.f5164e.values().toArray(new f.b0.i.g[this.f5164e.size()]);
                this.f5164e.clear();
            }
            Map<Integer, f.b0.i.j> map = this.k;
            if (map != null) {
                f.b0.i.j[] jVarArr2 = (f.b0.i.j[]) map.values().toArray(new f.b0.i.j[this.k.size()]);
                this.k = null;
                jVarArr = jVarArr2;
            }
        }
        if (gVarArr != null) {
            for (f.b0.i.g gVar : gVarArr) {
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
            for (f.b0.i.j jVar : jVarArr) {
                jVar.a();
            }
        }
        try {
            this.t.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.s.close();
        } catch (IOException e5) {
            e = e5;
        }
        if (e != null) {
            throw e;
        }
    }
}
