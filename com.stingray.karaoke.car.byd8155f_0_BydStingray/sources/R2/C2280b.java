package r2;

import Y2.l;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import m2.i;

/* JADX INFO: renamed from: r2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2280b implements Y2.e, m2.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f21773a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f21774b;
    public final ArrayDeque c;
    public final ArrayDeque d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m2.g[] f21775e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m2.h[] f21776f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f21777g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f21778h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m2.g f21779i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public m2.e f21780j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f21781k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f21782m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f21783n;
    public final Object o;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2280b(l lVar) {
        this(new Y2.i[2], new Y2.c[2]);
        this.f21783n = 1;
        int i7 = this.f21777g;
        m2.g[] gVarArr = this.f21775e;
        AbstractC1346c.j(i7 == gVarArr.length);
        for (m2.g gVar : gVarArr) {
            gVar.j(1024);
        }
        this.o = lVar;
    }

    @Override // m2.d
    public final void a() {
        synchronized (this.f21774b) {
            this.l = true;
            this.f21774b.notify();
        }
        try {
            this.f21773a.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    @Override // m2.d
    public final void c(long j3) {
        synchronized (this.f21774b) {
            try {
                AbstractC1346c.j(this.f21777g == this.f21775e.length || this.f21781k);
                this.f21782m = j3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // Y2.e
    public void d(long j3) {
    }

    @Override // m2.d
    public final Object f() {
        m2.g gVar;
        synchronized (this.f21774b) {
            try {
                m2.e eVar = this.f21780j;
                if (eVar != null) {
                    throw eVar;
                }
                AbstractC1346c.j(this.f21779i == null);
                int i7 = this.f21777g;
                if (i7 == 0) {
                    gVar = null;
                } else {
                    m2.g[] gVarArr = this.f21775e;
                    int i10 = i7 - 1;
                    this.f21777g = i10;
                    gVar = gVarArr[i10];
                }
                this.f21779i = gVar;
            } catch (Throwable th) {
                throw th;
            }
        }
        return gVar;
    }

    @Override // m2.d
    public final void flush() {
        synchronized (this.f21774b) {
            try {
                this.f21781k = true;
                m2.g gVar = this.f21779i;
                if (gVar != null) {
                    gVar.f();
                    int i7 = this.f21777g;
                    this.f21777g = i7 + 1;
                    this.f21775e[i7] = gVar;
                    this.f21779i = null;
                }
                while (!this.c.isEmpty()) {
                    m2.g gVar2 = (m2.g) this.c.removeFirst();
                    gVar2.f();
                    int i10 = this.f21777g;
                    this.f21777g = i10 + 1;
                    this.f21775e[i10] = gVar2;
                }
                while (!this.d.isEmpty()) {
                    ((m2.h) this.d.removeFirst()).i();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final m2.e g(Throwable th) {
        switch (this.f21783n) {
            case 0:
                return new C2282d("Unexpected decode error", th);
            default:
                return new Y2.f("Unexpected decode error", th);
        }
    }

    public final m2.e h(m2.g gVar, m2.h hVar, boolean z6) {
        switch (this.f21783n) {
            case 0:
                C2279a c2279a = (C2279a) hVar;
                try {
                    ByteBuffer byteBuffer = gVar.f18955p;
                    byteBuffer.getClass();
                    AbstractC1346c.j(byteBuffer.hasArray());
                    AbstractC1346c.c(byteBuffer.arrayOffset() == 0);
                    o2.d dVar = (o2.d) this.o;
                    byte[] bArrArray = byteBuffer.array();
                    int iRemaining = byteBuffer.remaining();
                    dVar.getClass();
                    c2279a.f21771p = o2.d.c(iRemaining, bArrArray);
                    c2279a.f18960n = gVar.f18957r;
                    return null;
                } catch (C2282d e10) {
                    return e10;
                }
            default:
                Y2.i iVar = (Y2.i) gVar;
                Y2.c cVar = (Y2.c) hVar;
                try {
                    ByteBuffer byteBuffer2 = iVar.f18955p;
                    byteBuffer2.getClass();
                    byte[] bArrArray2 = byteBuffer2.array();
                    int iLimit = byteBuffer2.limit();
                    l lVar = (l) this.o;
                    if (z6) {
                        lVar.d();
                    }
                    Y2.d dVarX = lVar.x(0, iLimit, bArrArray2);
                    long j3 = iVar.f18957r;
                    long j10 = iVar.f10646u;
                    cVar.f18960n = j3;
                    cVar.f10633p = dVarX;
                    if (j10 != Long.MAX_VALUE) {
                        j3 = j10;
                    }
                    cVar.f10634q = j3;
                    cVar.o = false;
                    return null;
                } catch (Y2.f e11) {
                    return e11;
                }
        }
    }

    public final boolean i() {
        boolean z6;
        m2.e eVarG;
        synchronized (this.f21774b) {
            while (!this.l) {
                try {
                    if (!this.c.isEmpty() && this.f21778h > 0) {
                        break;
                    }
                    this.f21774b.wait();
                } finally {
                }
            }
            if (this.l) {
                return false;
            }
            m2.g gVar = (m2.g) this.c.removeFirst();
            m2.h[] hVarArr = this.f21776f;
            int i7 = this.f21778h - 1;
            this.f21778h = i7;
            m2.h hVar = hVarArr[i7];
            boolean z10 = this.f21781k;
            this.f21781k = false;
            if (gVar.e(4)) {
                hVar.b(4);
            } else {
                hVar.f18960n = gVar.f18957r;
                if (gVar.e(134217728)) {
                    hVar.b(134217728);
                }
                long j3 = gVar.f18957r;
                synchronized (this.f21774b) {
                    long j10 = this.f21782m;
                    z6 = j10 == -9223372036854775807L || j3 >= j10;
                }
                if (!z6) {
                    hVar.o = true;
                }
                try {
                    eVarG = h(gVar, hVar, z10);
                } catch (OutOfMemoryError e10) {
                    eVarG = g(e10);
                } catch (RuntimeException e11) {
                    eVarG = g(e11);
                }
                if (eVarG != null) {
                    synchronized (this.f21774b) {
                        this.f21780j = eVarG;
                    }
                    return false;
                }
            }
            synchronized (this.f21774b) {
                try {
                    if (this.f21781k || hVar.o) {
                        hVar.i();
                    } else {
                        this.d.addLast(hVar);
                    }
                    gVar.f();
                    int i10 = this.f21777g;
                    this.f21777g = i10 + 1;
                    this.f21775e[i10] = gVar;
                } finally {
                }
            }
            return true;
        }
    }

    @Override // m2.d
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final m2.h e() {
        synchronized (this.f21774b) {
            try {
                m2.e eVar = this.f21780j;
                if (eVar != null) {
                    throw eVar;
                }
                if (this.d.isEmpty()) {
                    return null;
                }
                return (m2.h) this.d.removeFirst();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // m2.d
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void b(m2.g gVar) {
        synchronized (this.f21774b) {
            try {
                m2.e eVar = this.f21780j;
                if (eVar != null) {
                    throw eVar;
                }
                AbstractC1346c.c(gVar == this.f21779i);
                this.c.addLast(gVar);
                if (!this.c.isEmpty() && this.f21778h > 0) {
                    this.f21774b.notify();
                }
                this.f21779i = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void l(m2.h hVar) {
        synchronized (this.f21774b) {
            hVar.f();
            int i7 = this.f21778h;
            this.f21778h = i7 + 1;
            this.f21776f[i7] = hVar;
            if (!this.c.isEmpty() && this.f21778h > 0) {
                this.f21774b.notify();
            }
        }
    }

    public C2280b(m2.g[] gVarArr, m2.h[] hVarArr) {
        m2.h c2279a;
        m2.g gVar;
        this.f21774b = new Object();
        this.f21782m = -9223372036854775807L;
        this.c = new ArrayDeque();
        this.d = new ArrayDeque();
        this.f21775e = gVarArr;
        this.f21777g = gVarArr.length;
        for (int i7 = 0; i7 < this.f21777g; i7++) {
            m2.g[] gVarArr2 = this.f21775e;
            switch (this.f21783n) {
                case 0:
                    gVar = new m2.g(1);
                    break;
                default:
                    gVar = new Y2.i(1);
                    break;
            }
            gVarArr2[i7] = gVar;
        }
        this.f21776f = hVarArr;
        this.f21778h = hVarArr.length;
        for (int i10 = 0; i10 < this.f21778h; i10++) {
            m2.h[] hVarArr2 = this.f21776f;
            switch (this.f21783n) {
                case 0:
                    c2279a = new C2279a(this);
                    break;
                default:
                    c2279a = new Y2.c(this);
                    break;
            }
            hVarArr2[i10] = c2279a;
        }
        i iVar = new i(this);
        this.f21773a = iVar;
        iVar.start();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2280b(o2.d dVar) {
        this(new m2.g[1], new C2279a[1]);
        this.f21783n = 0;
        this.o = dVar;
    }
}
