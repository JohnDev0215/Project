package j9;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f17580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f17581b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f17582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f17583f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayDeque f17584g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f17585h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s f17586i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r f17587j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final t f17588k;
    public final t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17589m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public IOException f17590n;

    public u(int i7, m mVar, boolean z6, boolean z10, c9.u uVar) {
        A7.m.f(mVar, "connection");
        this.f17580a = i7;
        this.f17581b = mVar;
        this.f17583f = mVar.B.a();
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f17584g = arrayDeque;
        this.f17586i = new s(this, mVar.f17539A.a(), z10);
        this.f17587j = new r(this, z6);
        this.f17588k = new t(this);
        this.l = new t(this);
        if (uVar == null) {
            if (!g()) {
                throw new IllegalStateException("remotely-initiated streams should have headers");
            }
        } else {
            if (g()) {
                throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
            }
            arrayDeque.add(uVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r2 = this;
            byte[] r0 = d9.b.f13645a
            monitor-enter(r2)
            j9.s r0 = r2.f17586i     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.f17575m     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L1b
            boolean r0 = r0.f17577p     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
            j9.r r0 = r2.f17587j     // Catch: java.lang.Throwable -> L19
            boolean r1 = r0.l     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L17
            boolean r0 = r0.f17574n     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L1b
        L17:
            r0 = 1
            goto L1c
        L19:
            r0 = move-exception
            goto L34
        L1b:
            r0 = 0
        L1c:
            boolean r1 = r2.h()     // Catch: java.lang.Throwable -> L19
            monitor-exit(r2)
            if (r0 == 0) goto L2a
            r0 = 9
            r1 = 0
            r2.c(r0, r1)
            goto L33
        L2a:
            if (r1 != 0) goto L33
            j9.m r0 = r2.f17581b
            int r2 = r2.f17580a
            r0.i(r2)
        L33:
            return
        L34:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.u.a():void");
    }

    public final void b() throws IOException {
        r rVar = this.f17587j;
        if (rVar.f17574n) {
            throw new IOException("stream closed");
        }
        if (rVar.l) {
            throw new IOException("stream finished");
        }
        if (this.f17589m != 0) {
            IOException iOException = this.f17590n;
            if (iOException != null) {
                throw iOException;
            }
            int i7 = this.f17589m;
            A7.k.n(i7);
            throw new z(i7);
        }
    }

    public final void c(int i7, IOException iOException) {
        A7.k.p(i7, "rstStatusCode");
        if (d(i7, iOException)) {
            m mVar = this.f17581b;
            mVar.getClass();
            A7.k.p(i7, "statusCode");
            mVar.f17545H.o(this.f17580a, i7);
        }
    }

    public final boolean d(int i7, IOException iOException) {
        byte[] bArr = d9.b.f13645a;
        synchronized (this) {
            if (this.f17589m != 0) {
                return false;
            }
            this.f17589m = i7;
            this.f17590n = iOException;
            notifyAll();
            if (this.f17586i.f17575m) {
                if (this.f17587j.l) {
                    return false;
                }
            }
            this.f17581b.i(this.f17580a);
            return true;
        }
    }

    public final void e(int i7) {
        A7.k.p(i7, "errorCode");
        if (d(i7, null)) {
            this.f17581b.o(this.f17580a, i7);
        }
    }

    public final r f() {
        synchronized (this) {
            if (!this.f17585h && !g()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.f17587j;
    }

    public final boolean g() {
        boolean z6 = (this.f17580a & 1) == 1;
        this.f17581b.getClass();
        return true == z6;
    }

    public final synchronized boolean h() {
        if (this.f17589m != 0) {
            return false;
        }
        s sVar = this.f17586i;
        if (sVar.f17575m || sVar.f17577p) {
            r rVar = this.f17587j;
            if (rVar.l || rVar.f17574n) {
                if (this.f17585h) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void i(c9.u uVar, boolean z6) {
        boolean zH;
        A7.m.f(uVar, "headers");
        byte[] bArr = d9.b.f13645a;
        synchronized (this) {
            try {
                if (this.f17585h && z6) {
                    this.f17586i.getClass();
                } else {
                    this.f17585h = true;
                    this.f17584g.add(uVar);
                }
                if (z6) {
                    this.f17586i.f17575m = true;
                }
                zH = h();
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zH) {
            return;
        }
        this.f17581b.i(this.f17580a);
    }

    public final synchronized void j(int i7) {
        A7.k.p(i7, "errorCode");
        if (this.f17589m == 0) {
            this.f17589m = i7;
            notifyAll();
        }
    }

    public final void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
