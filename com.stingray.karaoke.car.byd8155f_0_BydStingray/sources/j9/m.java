package j9;

import Q5.C0606p0;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import r9.C;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Closeable {

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final y f17538K;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final y f17539A;
    public y B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f17540C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f17541D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public long f17542E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public long f17543F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Socket f17544G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final v f17545H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C0606p0 f17546I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final LinkedHashSet f17547J;
    public final g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashMap f17548m = new LinkedHashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f17549n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17550p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f17551q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final f9.c f17552r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f9.b f17553s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final f9.b f17554t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final f9.b f17555u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final x f17556v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f17557w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f17558x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f17559y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f17560z;

    static {
        y yVar = new y();
        yVar.c(7, 65535);
        yVar.c(5, 16384);
        f17538K = yVar;
    }

    public m(H0.b bVar) {
        this.l = (g) bVar.f2769r;
        String str = (String) bVar.o;
        if (str == null) {
            A7.m.j("connectionName");
            throw null;
        }
        this.f17549n = str;
        this.f17550p = 3;
        f9.c cVar = (f9.c) bVar.f2765m;
        this.f17552r = cVar;
        this.f17553s = cVar.e();
        this.f17554t = cVar.e();
        this.f17555u = cVar.e();
        this.f17556v = x.f17597a;
        y yVar = new y();
        yVar.c(7, 16777216);
        this.f17539A = yVar;
        this.B = f17538K;
        this.f17543F = r0.a();
        Socket socket = (Socket) bVar.f2766n;
        if (socket == null) {
            A7.m.j("socket");
            throw null;
        }
        this.f17544G = socket;
        C c = (C) bVar.f2768q;
        if (c == null) {
            A7.m.j("sink");
            throw null;
        }
        this.f17545H = new v(c);
        D d = (D) bVar.f2767p;
        if (d == null) {
            A7.m.j(BYDAutoMultimediaDevice.PARAM_SOURCE);
            throw null;
        }
        this.f17546I = new C0606p0(this, 5, new q(d));
        this.f17547J = new LinkedHashSet();
    }

    public final void b(int i7, int i10, IOException iOException) {
        int i11;
        Object[] array;
        A7.k.p(i7, "connectionCode");
        A7.k.p(i10, "streamCode");
        byte[] bArr = d9.b.f13645a;
        try {
            j(i7);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (this.f17548m.isEmpty()) {
                array = null;
            } else {
                array = this.f17548m.values().toArray(new u[0]);
                this.f17548m.clear();
            }
        }
        u[] uVarArr = (u[]) array;
        if (uVarArr != null) {
            for (u uVar : uVarArr) {
                try {
                    uVar.c(i10, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.f17545H.close();
        } catch (IOException unused3) {
        }
        try {
            this.f17544G.close();
        } catch (IOException unused4) {
        }
        this.f17553s.f();
        this.f17554t.f();
        this.f17555u.f();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        b(1, 9, null);
    }

    public final void flush() {
        this.f17545H.flush();
    }

    public final synchronized u g(int i7) {
        return (u) this.f17548m.get(Integer.valueOf(i7));
    }

    public final synchronized u i(int i7) {
        u uVar;
        uVar = (u) this.f17548m.remove(Integer.valueOf(i7));
        notifyAll();
        return uVar;
    }

    public final void j(int i7) {
        A7.k.p(i7, "statusCode");
        synchronized (this.f17545H) {
            synchronized (this) {
                if (this.f17551q) {
                    return;
                }
                this.f17551q = true;
                this.f17545H.j(this.o, i7, d9.b.f13645a);
            }
        }
    }

    public final synchronized void k(long j3) {
        long j10 = this.f17540C + j3;
        this.f17540C = j10;
        long j11 = j10 - this.f17541D;
        if (j11 >= this.f17539A.a() / 2) {
            q(j11, 0);
            this.f17541D += j11;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        r2 = java.lang.Math.min((int) java.lang.Math.min(r12, r6 - r4), r8.f17545H.f17593n);
        r6 = r2;
        r8.f17542E += r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(int r9, boolean r10, r9.C2319i r11, long r12) {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto Ld
            j9.v r8 = r8.f17545H
            r8.g(r10, r9, r11, r3)
            return
        Ld:
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 <= 0) goto L68
            monitor-enter(r8)
        L12:
            long r4 = r8.f17542E     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            long r6 = r8.f17543F     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L34
            java.util.LinkedHashMap r2 = r8.f17548m     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            boolean r2 = r2.containsKey(r4)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            if (r2 == 0) goto L2c
            r8.wait()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            goto L12
        L2a:
            r9 = move-exception
            goto L66
        L2c:
            java.io.IOException r9 = new java.io.IOException     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            java.lang.String r10 = "stream closed"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
            throw r9     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L59
        L34:
            long r6 = r6 - r4
            long r4 = java.lang.Math.min(r12, r6)     // Catch: java.lang.Throwable -> L2a
            int r2 = (int) r4     // Catch: java.lang.Throwable -> L2a
            j9.v r4 = r8.f17545H     // Catch: java.lang.Throwable -> L2a
            int r4 = r4.f17593n     // Catch: java.lang.Throwable -> L2a
            int r2 = java.lang.Math.min(r2, r4)     // Catch: java.lang.Throwable -> L2a
            long r4 = r8.f17542E     // Catch: java.lang.Throwable -> L2a
            long r6 = (long) r2     // Catch: java.lang.Throwable -> L2a
            long r4 = r4 + r6
            r8.f17542E = r4     // Catch: java.lang.Throwable -> L2a
            monitor-exit(r8)
            long r12 = r12 - r6
            j9.v r4 = r8.f17545H
            if (r10 == 0) goto L54
            int r5 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r5 != 0) goto L54
            r5 = 1
            goto L55
        L54:
            r5 = r3
        L55:
            r4.g(r5, r9, r11, r2)
            goto Ld
        L59:
            java.lang.Thread r9 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L2a
            r9.interrupt()     // Catch: java.lang.Throwable -> L2a
            java.io.InterruptedIOException r9 = new java.io.InterruptedIOException     // Catch: java.lang.Throwable -> L2a
            r9.<init>()     // Catch: java.lang.Throwable -> L2a
            throw r9     // Catch: java.lang.Throwable -> L2a
        L66:
            monitor-exit(r8)
            throw r9
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.m.m(int, boolean, r9.i, long):void");
    }

    public final void o(int i7, int i10) {
        A7.k.p(i10, "errorCode");
        this.f17553s.c(new i(this.f17549n + '[' + i7 + "] writeSynReset", this, i7, i10, 2), 0L);
    }

    public final void q(long j3, int i7) {
        this.f17553s.c(new l(this.f17549n + '[' + i7 + "] windowUpdate", this, i7, j3), 0L);
    }
}
