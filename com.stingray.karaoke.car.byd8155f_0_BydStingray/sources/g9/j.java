package g9;

import B8.t;
import c9.C;
import c9.H;
import c9.InterfaceC1048i;
import c9.q;
import c9.w;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Cloneable {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public volatile boolean f15058A;
    public volatile e B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public volatile m f15059C;
    public final C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final H0.b f15060m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f15061n;
    public final n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final q f15062p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i f15063q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AtomicBoolean f15064r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object f15065s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public f f15066t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m f15067u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f15068v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public e f15069w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f15070x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f15071y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f15072z;

    public j(C c, H0.b bVar, boolean z6) {
        A7.m.f(c, "client");
        A7.m.f(bVar, "originalRequest");
        this.l = c;
        this.f15060m = bVar;
        this.f15061n = z6;
        this.o = (n) c.f12479m.l;
        this.f15062p = (q) c.f12481p.f2649m;
        i iVar = new i(this);
        iVar.g(0, TimeUnit.MILLISECONDS);
        this.f15063q = iVar;
        this.f15064r = new AtomicBoolean();
        this.f15072z = true;
    }

    public static final String a(j jVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(jVar.f15058A ? "canceled " : "");
        sb.append(jVar.f15061n ? "web socket" : "call");
        sb.append(" to ");
        sb.append(((w) jVar.f15060m.f2765m).g());
        return sb.toString();
    }

    public final void b(m mVar) {
        byte[] bArr = d9.b.f13645a;
        if (this.f15067u != null) {
            throw new IllegalStateException("Check failed.");
        }
        this.f15067u = mVar;
        mVar.f15086p.add(new h(this, this.f15065s));
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket socketK;
        byte[] bArr = d9.b.f13645a;
        m mVar = this.f15067u;
        if (mVar != null) {
            synchronized (mVar) {
                socketK = k();
            }
            if (this.f15067u == null) {
                if (socketK != null) {
                    d9.b.d(socketK);
                }
                this.f15062p.l(this, mVar);
            } else if (socketK != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        if (!this.f15068v && this.f15063q.j()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            q qVar = this.f15062p;
            A7.m.c(interruptedIOException);
            qVar.e(this, interruptedIOException);
        } else {
            this.f15062p.d(this);
        }
        return interruptedIOException;
    }

    public final Object clone() {
        return new j(this.l, this.f15060m, this.f15061n);
    }

    public final void d() {
        Socket socket;
        if (this.f15058A) {
            return;
        }
        this.f15058A = true;
        e eVar = this.B;
        if (eVar != null) {
            ((h9.c) eVar.f15044e).cancel();
        }
        m mVar = this.f15059C;
        if (mVar != null && (socket = mVar.c) != null) {
            d9.b.d(socket);
        }
        this.f15062p.g(this);
    }

    public final void e(InterfaceC1048i interfaceC1048i) {
        g gVar;
        if (!this.f15064r.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        l9.o oVar = l9.o.f18874a;
        this.f15065s = l9.o.f18874a.g();
        this.f15062p.f(this);
        t tVar = this.l.l;
        g gVar2 = new g(this, interfaceC1048i);
        tVar.getClass();
        synchronized (tVar) {
            ((ArrayDeque) tVar.o).add(gVar2);
            if (!this.f15061n) {
                String str = ((w) this.f15060m.f2765m).d;
                Iterator it = ((ArrayDeque) tVar.f809p).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        Iterator it2 = ((ArrayDeque) tVar.o).iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                gVar = null;
                                break;
                            } else {
                                gVar = (g) it2.next();
                                if (A7.m.a(((w) gVar.f15055n.f15060m.f2765m).d, str)) {
                                    break;
                                }
                            }
                        }
                    } else {
                        gVar = (g) it.next();
                        if (A7.m.a(((w) gVar.f15055n.f15060m.f2765m).d, str)) {
                            break;
                        }
                    }
                }
                if (gVar != null) {
                    gVar2.f15054m = gVar.f15054m;
                }
            }
        }
        tVar.j0();
    }

    public final H f() {
        if (!this.f15064r.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.f15063q.i();
        l9.o oVar = l9.o.f18874a;
        this.f15065s = l9.o.f18874a.g();
        this.f15062p.f(this);
        try {
            t tVar = this.l.l;
            synchronized (tVar) {
                ((ArrayDeque) tVar.f807m).add(this);
            }
            return h();
        } finally {
            t tVar2 = this.l.l;
            tVar2.getClass();
            tVar2.N((ArrayDeque) tVar2.f807m, this);
        }
    }

    public final void g(boolean z6) {
        e eVar;
        synchronized (this) {
            if (!this.f15072z) {
                throw new IllegalStateException("released");
            }
        }
        if (z6 && (eVar = this.B) != null) {
            ((h9.c) eVar.f15044e).cancel();
            ((j) eVar.f15043b).i(eVar, true, true, null);
        }
        this.f15069w = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final c9.H h() throws java.lang.Throwable {
        /*
            r12 = this;
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            c9.C r0 = r12.l
            java.util.List r0 = r0.f12480n
            n7.AbstractC2004s.L(r2, r0)
            java.util.Iterator r0 = r2.iterator()
        L10:
            boolean r1 = r0.hasNext()
            r9 = 0
            if (r1 == 0) goto L23
            java.lang.Object r1 = r0.next()
            r3 = r1
            c9.x r3 = (c9.x) r3
            boolean r3 = r3 instanceof io.sentry.okhttp.e
            if (r3 == 0) goto L10
            goto L24
        L23:
            r1 = r9
        L24:
            if (r1 != 0) goto L2e
            io.sentry.okhttp.e r0 = new io.sentry.okhttp.e
            r0.<init>()
            r2.add(r0)
        L2e:
            J5.i r0 = new J5.i
            c9.C r1 = r12.l
            r0.<init>(r1)
            r2.add(r0)
            J5.i r0 = new J5.i
            c9.C r1 = r12.l
            c9.b r1 = r1.f12486u
            r0.<init>(r1)
            r2.add(r0)
            e9.b r0 = new e9.b
            c9.C r1 = r12.l
            c9.f r1 = r1.f12487v
            r0.<init>(r1)
            r2.add(r0)
            g9.a r0 = g9.a.f15030a
            r2.add(r0)
            boolean r0 = r12.f15061n
            if (r0 != 0) goto L60
            c9.C r0 = r12.l
            java.util.List r0 = r0.o
            n7.AbstractC2004s.L(r2, r0)
        L60:
            h9.a r0 = new h9.a
            boolean r1 = r12.f15061n
            r0.<init>(r1)
            r2.add(r0)
            F.u r10 = new F.u
            H0.b r5 = r12.f15060m
            c9.C r0 = r12.l
            int r6 = r0.f12474H
            int r7 = r0.f12475I
            int r8 = r0.f12476J
            r3 = 0
            r4 = 0
            r0 = r10
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            H0.b r1 = r12.f15060m     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            c9.H r1 = r10.f(r1)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            boolean r2 = r12.f15058A     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            if (r2 != 0) goto L8c
            r12.j(r9)
            return r1
        L8c:
            d9.b.c(r1)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            java.lang.String r2 = "Canceled"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
            throw r1     // Catch: java.lang.Throwable -> L97 java.io.IOException -> L99
        L97:
            r1 = move-exception
            goto La9
        L99:
            r0 = move-exception
            r1 = 1
            java.io.IOException r0 = r12.j(r0)     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Throwable"
            A7.m.d(r0, r2)     // Catch: java.lang.Throwable -> La5
            throw r0     // Catch: java.lang.Throwable -> La5
        La5:
            r0 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        La9:
            if (r0 != 0) goto Lae
            r12.j(r9)
        Lae:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: g9.j.h():c9.H");
    }

    public final IOException i(e eVar, boolean z6, boolean z10, IOException iOException) {
        boolean z11;
        boolean z12;
        A7.m.f(eVar, "exchange");
        if (!eVar.equals(this.B)) {
            return iOException;
        }
        synchronized (this) {
            z11 = false;
            if (z6) {
                try {
                    if (!this.f15070x) {
                        if (z10 || !this.f15071y) {
                            z12 = false;
                        }
                    }
                    if (z6) {
                        this.f15070x = false;
                    }
                    if (z10) {
                        this.f15071y = false;
                    }
                    boolean z13 = this.f15070x;
                    boolean z14 = (z13 || this.f15071y) ? false : true;
                    if (!z13 && !this.f15071y) {
                        if (!this.f15072z) {
                            z11 = true;
                        }
                    }
                    z12 = z11;
                    z11 = z14;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z10) {
                }
                z12 = false;
            }
        }
        if (z11) {
            this.B = null;
            m mVar = this.f15067u;
            if (mVar != null) {
                synchronized (mVar) {
                    mVar.f15084m++;
                }
            }
        }
        return z12 ? c(iOException) : iOException;
    }

    public final IOException j(IOException iOException) {
        boolean z6;
        synchronized (this) {
            z6 = false;
            if (this.f15072z) {
                this.f15072z = false;
                if (!this.f15070x) {
                    if (!this.f15071y) {
                        z6 = true;
                    }
                }
            }
        }
        return z6 ? c(iOException) : iOException;
    }

    public final Socket k() {
        m mVar = this.f15067u;
        A7.m.c(mVar);
        byte[] bArr = d9.b.f13645a;
        ArrayList arrayList = mVar.f15086p;
        Iterator it = arrayList.iterator();
        int i7 = 0;
        while (true) {
            if (!it.hasNext()) {
                i7 = -1;
                break;
            }
            if (A7.m.a(((Reference) it.next()).get(), this)) {
                break;
            }
            i7++;
        }
        if (i7 == -1) {
            throw new IllegalStateException("Check failed.");
        }
        arrayList.remove(i7);
        this.f15067u = null;
        if (arrayList.isEmpty()) {
            mVar.f15087q = System.nanoTime();
            n nVar = this.o;
            nVar.getClass();
            byte[] bArr2 = d9.b.f13645a;
            boolean z6 = mVar.f15082j;
            f9.b bVar = nVar.f15089b;
            if (z6) {
                mVar.f15082j = true;
                ConcurrentLinkedQueue concurrentLinkedQueue = nVar.d;
                concurrentLinkedQueue.remove(mVar);
                if (concurrentLinkedQueue.isEmpty()) {
                    bVar.a();
                }
                Socket socket = mVar.d;
                A7.m.c(socket);
                return socket;
            }
            bVar.c(nVar.c, 0L);
        }
        return null;
    }
}
