package q9;

import A7.m;
import c9.D;
import c9.H;
import g9.l;
import h5.t;
import h8.AbstractC1387d;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;
import k7.C1707a;
import r9.C2319i;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final List f21364w = AbstractC1387d.j(D.HTTP_1_1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e7.f f21365a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Random f21366b;
    public final long c;
    public h d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f21367e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f21368f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g9.j f21369g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e f21370h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i f21371i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public j f21372j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f9.b f21373k;
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public l f21374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayDeque f21375n;
    public final ArrayDeque o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f21376p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f21377q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f21378r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f21379s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f21380t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f21381u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21382v;

    public g(f9.c cVar, H0.b bVar, e7.f fVar, Random random, long j3, long j10) {
        m.f(cVar, "taskRunner");
        this.f21365a = fVar;
        this.f21366b = random;
        this.c = j3;
        this.d = null;
        this.f21367e = j10;
        this.f21373k = cVar.e();
        this.f21375n = new ArrayDeque();
        this.o = new ArrayDeque();
        this.f21378r = -1;
        String str = (String) bVar.f2766n;
        if (!"GET".equals(str)) {
            throw new IllegalArgumentException(M5.b.g("Request must be GET: ", str).toString());
        }
        C2322l c2322l = C2322l.o;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.f21368f = m6.b.G0(bArr).a();
    }

    public final void a(H h10, g9.e eVar) {
        int i7 = h10.o;
        if (i7 != 101) {
            StringBuilder sb = new StringBuilder("Expected HTTP 101 response but was '");
            sb.append(i7);
            sb.append(' ');
            throw new ProtocolException(android.hardware.bydauto.ac.tsimpl.a.k(sb, h10.f12515n, '\''));
        }
        String strG = H.g(h10, "Connection");
        if (!"Upgrade".equalsIgnoreCase(strG)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strG + '\'');
        }
        String strG2 = H.g(h10, "Upgrade");
        if (!"websocket".equalsIgnoreCase(strG2)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strG2 + '\'');
        }
        String strG3 = H.g(h10, "Sec-WebSocket-Accept");
        C2322l c2322l = C2322l.o;
        String strA = m6.b.p(this.f21368f + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").c("SHA-1").a();
        if (m.a(strA, strG3)) {
            if (eVar == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strA + "' but was '" + strG3 + '\'');
    }

    public final boolean b(int i7, String str) {
        String str2;
        synchronized (this) {
            C2322l c2322lP = null;
            try {
                if (i7 < 1000 || i7 >= 5000) {
                    str2 = "Code must be in range [1000,5000): " + i7;
                } else if ((1004 > i7 || i7 >= 1007) && (1015 > i7 || i7 >= 3000)) {
                    str2 = null;
                } else {
                    str2 = "Code " + i7 + " is reserved and may not be used.";
                }
                if (str2 != null) {
                    throw new IllegalArgumentException(str2.toString());
                }
                if (str != null) {
                    C2322l c2322l = C2322l.o;
                    c2322lP = m6.b.p(str);
                    if (c2322lP.l.length > 123) {
                        throw new IllegalArgumentException("reason.size() > 123: ".concat(str).toString());
                    }
                }
                if (!this.f21380t && !this.f21377q) {
                    this.f21377q = true;
                    this.o.add(new c(i7, c2322lP));
                    f();
                    return true;
                }
                return false;
            } finally {
            }
        }
    }

    public final void c(Exception exc) {
        synchronized (this) {
            if (this.f21380t) {
                return;
            }
            this.f21380t = true;
            l lVar = this.f21374m;
            this.f21374m = null;
            i iVar = this.f21371i;
            this.f21371i = null;
            j jVar = this.f21372j;
            this.f21372j = null;
            this.f21373k.f();
            try {
                C1707a.a(new t(29, this.f21365a, exc, false));
            } finally {
                if (lVar != null) {
                    d9.b.c(lVar);
                }
                if (iVar != null) {
                    d9.b.c(iVar);
                }
                if (jVar != null) {
                    d9.b.c(jVar);
                }
            }
        }
    }

    public final void d(String str, l lVar) {
        m.f(str, "name");
        h hVar = this.d;
        m.c(hVar);
        synchronized (this) {
            try {
                this.l = str;
                this.f21374m = lVar;
                this.f21372j = new j(lVar.f15074m, this.f21366b, hVar.f21383a, hVar.c, this.f21367e);
                this.f21370h = new e(this);
                long j3 = this.c;
                if (j3 != 0) {
                    long nanos = TimeUnit.MILLISECONDS.toNanos(j3);
                    this.f21373k.c(new f(str.concat(" ping"), this, nanos), nanos);
                }
                if (!this.o.isEmpty()) {
                    f();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f21371i = new i(lVar.l, this, hVar.f21383a, hVar.f21385e);
    }

    public final void e() {
        boolean z6 = false;
        while (this.f21378r == -1) {
            i iVar = this.f21371i;
            m.c(iVar);
            iVar.g();
            if (!iVar.f21393t) {
                int i7 = iVar.f21390q;
                if (i7 != 1 && i7 != 2) {
                    byte[] bArr = d9.b.f13645a;
                    String hexString = Integer.toHexString(i7);
                    m.e(hexString, "toHexString(this)");
                    throw new ProtocolException("Unknown opcode: ".concat(hexString));
                }
                while (!iVar.f21389p) {
                    long j3 = iVar.f21391r;
                    C2319i c2319i = iVar.f21396w;
                    if (j3 > 0) {
                        iVar.l.o(c2319i, j3);
                    }
                    if (iVar.f21392s) {
                        if (iVar.f21394u) {
                            a aVar = iVar.f21397x;
                            if (aVar == null) {
                                aVar = new a(1, iVar.o);
                                iVar.f21397x = aVar;
                            }
                            C2319i c2319i2 = aVar.f21353n;
                            if (c2319i2.f21932m != 0) {
                                throw new IllegalArgumentException("Failed requirement.");
                            }
                            Inflater inflater = (Inflater) aVar.o;
                            if (aVar.f21352m) {
                                inflater.reset();
                            }
                            c2319i2.r(c2319i);
                            c2319i2.a0(65535);
                            long bytesRead = inflater.getBytesRead() + c2319i2.f21932m;
                            do {
                                ((r9.t) aVar.f21354p).b(c2319i, Long.MAX_VALUE);
                            } while (inflater.getBytesRead() < bytesRead);
                        }
                        e7.f fVar = iVar.f21387m.f21365a;
                        if (i7 == 1) {
                            C1707a.a(new t(27, fVar, c2319i.Q(), z6));
                        } else {
                            C2322l c2322lF = c2319i.F(c2319i.f21932m);
                            m.f(c2322lF, "bytes");
                            C1707a.a(new t(28, fVar, c2322lF, z6));
                        }
                    } else {
                        while (!iVar.f21389p) {
                            iVar.g();
                            if (!iVar.f21393t) {
                                break;
                            } else {
                                iVar.b();
                            }
                        }
                        if (iVar.f21390q != 0) {
                            int i10 = iVar.f21390q;
                            byte[] bArr2 = d9.b.f13645a;
                            String hexString2 = Integer.toHexString(i10);
                            m.e(hexString2, "toHexString(this)");
                            throw new ProtocolException("Expected continuation opcode. Got: ".concat(hexString2));
                        }
                    }
                }
                throw new IOException("closed");
            }
            iVar.b();
        }
    }

    public final void f() {
        byte[] bArr = d9.b.f13645a;
        e eVar = this.f21370h;
        if (eVar != null) {
            this.f21373k.c(eVar, 0L);
        }
    }

    public final synchronized boolean g(int i7, C2322l c2322l) {
        if (!this.f21380t && !this.f21377q) {
            long j3 = this.f21376p;
            byte[] bArr = c2322l.l;
            if (((long) bArr.length) + j3 > 16777216) {
                b(1001, null);
                return false;
            }
            this.f21376p = j3 + ((long) bArr.length);
            this.o.add(new d(i7, c2322l));
            f();
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0079 A[Catch: all -> 0x0085, TRY_ENTER, TryCatch #1 {all -> 0x0085, blocks: (B:29:0x0079, B:32:0x0088, B:34:0x008c, B:35:0x0098, B:37:0x00a4, B:40:0x00a8, B:41:0x00a9, B:42:0x00aa, B:44:0x00ae, B:58:0x00d3, B:61:0x00f7, B:63:0x0101, B:64:0x0104, B:67:0x010f, B:69:0x0113, B:78:0x0133, B:79:0x0135, B:80:0x0136, B:81:0x013f, B:59:0x00e7, B:82:0x0140, B:83:0x0145, B:66:0x010c, B:36:0x0099), top: B:95:0x0077, inners: #0, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0088 A[Catch: all -> 0x0085, TryCatch #1 {all -> 0x0085, blocks: (B:29:0x0079, B:32:0x0088, B:34:0x008c, B:35:0x0098, B:37:0x00a4, B:40:0x00a8, B:41:0x00a9, B:42:0x00aa, B:44:0x00ae, B:58:0x00d3, B:61:0x00f7, B:63:0x0101, B:64:0x0104, B:67:0x010f, B:69:0x0113, B:78:0x0133, B:79:0x0135, B:80:0x0136, B:81:0x013f, B:59:0x00e7, B:82:0x0140, B:83:0x0145, B:66:0x010c, B:36:0x0099), top: B:95:0x0077, inners: #0, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0124 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0129 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012e A[DONT_GENERATE] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h() {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q9.g.h():boolean");
    }
}
