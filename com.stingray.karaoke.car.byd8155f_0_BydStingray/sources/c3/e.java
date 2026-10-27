package C3;

import A7.F;
import A7.m;
import B5.G;
import Q8.o;
import S8.AbstractC0727y;
import h6.q;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import r9.AbstractC2312b;
import r9.C;
import r9.C2313c;
import r9.D;
import r9.H;
import r9.L;
import r9.v;
import r9.x;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Closeable, Flushable {
    public static final Q8.g B = new Q8.g("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final c f979A;
    public final z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f980m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final z f981n;
    public final z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final z f982p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final LinkedHashMap f983q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final X8.c f984r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f985s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f986t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C f987u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f988v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f989w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f990x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f991y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f992z;

    public e(long j3, Z8.d dVar, v vVar, z zVar) {
        this.l = zVar;
        this.f980m = j3;
        if (j3 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f981n = zVar.e("journal");
        this.o = zVar.e("journal.tmp");
        this.f982p = zVar.e("journal.bkp");
        this.f983q = new LinkedHashMap(0, 0.75f, true);
        this.f984r = AbstractC0727y.a(q.x(AbstractC0727y.c(), dVar.O(1, null)));
        this.f979A = new c(vVar);
    }

    public static void C(String str) {
        if (B.b(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x011c A[Catch: all -> 0x0036, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0011, B:11:0x0018, B:13:0x0020, B:15:0x0030, B:23:0x003f, B:25:0x0057, B:29:0x0074, B:31:0x0084, B:33:0x008b, B:26:0x005d, B:28:0x006d, B:37:0x00ab, B:39:0x00b2, B:42:0x00b7, B:44:0x00c8, B:47:0x00cd, B:52:0x0108, B:54:0x0113, B:58:0x011c, B:48:0x00e5, B:50:0x00fa, B:51:0x0105, B:36:0x009b, B:61:0x0121, B:62:0x0128), top: B:65:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(C3.e r9, A2.c r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C3.e.b(C3.e, A2.c, boolean):void");
    }

    public final synchronized void F() {
        Throwable th;
        try {
            C c = this.f987u;
            if (c != null) {
                c.close();
            }
            C cB = AbstractC2312b.b(this.f979A.j(this.o));
            try {
                cB.D("libcore.io.DiskLruCache");
                cB.s(10);
                cB.D("1");
                cB.s(10);
                cB.E(1);
                cB.s(10);
                cB.E(2);
                cB.s(10);
                cB.s(10);
                for (b bVar : this.f983q.values()) {
                    if (bVar.f975g != null) {
                        cB.D("DIRTY");
                        cB.s(32);
                        cB.D(bVar.f971a);
                        cB.s(10);
                    } else {
                        cB.D("CLEAN");
                        cB.s(32);
                        cB.D(bVar.f971a);
                        for (long j3 : bVar.f972b) {
                            cB.s(32);
                            cB.E(j3);
                        }
                        cB.s(10);
                    }
                }
                try {
                    cB.close();
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    cB.close();
                } catch (Throwable th4) {
                    q.l(th3, th4);
                }
                th = th3;
            }
            if (th != null) {
                throw th;
            }
            if (this.f979A.e(this.f981n)) {
                this.f979A.l(this.f981n, this.f982p);
                this.f979A.l(this.o, this.f981n);
                this.f979A.d(this.f982p);
            } else {
                this.f979A.l(this.o, this.f981n);
            }
            this.f987u = m();
            this.f986t = 0;
            this.f988v = false;
            this.f992z = false;
        } catch (Throwable th5) {
            throw th5;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.f989w && !this.f990x) {
                for (b bVar : (b[]) this.f983q.values().toArray(new b[0])) {
                    A2.c cVar = bVar.f975g;
                    if (cVar != null) {
                        b bVar2 = (b) cVar.c;
                        if (m.a(bVar2.f975g, cVar)) {
                            bVar2.f974f = true;
                        }
                    }
                }
                z();
                AbstractC0727y.d(this.f984r, null);
                C c = this.f987u;
                m.c(c);
                c.close();
                this.f987u = null;
                this.f990x = true;
                return;
            }
            this.f990x = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.f989w) {
            if (this.f990x) {
                throw new IllegalStateException("cache is closed");
            }
            z();
            C c = this.f987u;
            m.c(c);
            c.flush();
        }
    }

    public final synchronized A2.c g(String str) {
        try {
            if (this.f990x) {
                throw new IllegalStateException("cache is closed");
            }
            C(str);
            j();
            b bVar = (b) this.f983q.get(str);
            if ((bVar != null ? bVar.f975g : null) != null) {
                return null;
            }
            if (bVar != null && bVar.f976h != 0) {
                return null;
            }
            if (!this.f991y && !this.f992z) {
                C c = this.f987u;
                m.c(c);
                c.D("DIRTY");
                c.s(32);
                c.D(str);
                c.s(10);
                c.flush();
                if (this.f988v) {
                    return null;
                }
                if (bVar == null) {
                    bVar = new b(this, str);
                    this.f983q.put(str, bVar);
                }
                A2.c cVar = new A2.c(this, bVar);
                bVar.f975g = cVar;
                return cVar;
            }
            k();
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized G i(String str) {
        G gA;
        if (this.f990x) {
            throw new IllegalStateException("cache is closed");
        }
        C(str);
        j();
        b bVar = (b) this.f983q.get(str);
        if (bVar != null && (gA = bVar.a()) != null) {
            boolean z6 = true;
            this.f986t++;
            C c = this.f987u;
            m.c(c);
            c.D("READ");
            c.s(32);
            c.D(str);
            c.s(10);
            if (this.f986t < 2000) {
                z6 = false;
            }
            if (z6) {
                k();
            }
            return gA;
        }
        return null;
    }

    public final synchronized void j() {
        try {
            if (this.f989w) {
                return;
            }
            this.f979A.d(this.o);
            if (this.f979A.e(this.f982p)) {
                if (this.f979A.e(this.f981n)) {
                    this.f979A.d(this.f982p);
                } else {
                    this.f979A.l(this.f982p, this.f981n);
                }
            }
            if (this.f979A.e(this.f981n)) {
                try {
                    q();
                    o();
                    this.f989w = true;
                    return;
                } catch (IOException unused) {
                    try {
                        close();
                        io.sentry.config.a.X(this.f979A, this.l);
                        this.f990x = false;
                        F();
                        this.f989w = true;
                    } catch (Throwable th) {
                        this.f990x = false;
                        throw th;
                    }
                }
            }
            F();
            this.f989w = true;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final void k() {
        AbstractC0727y.w(this.f984r, null, null, new d(this, null), 3);
    }

    public final C m() {
        int i7 = 1;
        c cVar = this.f979A;
        cVar.getClass();
        z zVar = this.f981n;
        m.f(zVar, "file");
        cVar.getClass();
        m.f(zVar, "file");
        cVar.f978b.getClass();
        File fileF = zVar.f();
        Logger logger = x.f21949a;
        return AbstractC2312b.b(new f((H) new C2313c(h6.m.n(new FileOutputStream(fileF, true), fileF, true), i7, new L()), new F(this, i7)));
    }

    public final void o() {
        Iterator it = this.f983q.values().iterator();
        long j3 = 0;
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i7 = 0;
            if (bVar.f975g == null) {
                while (i7 < 2) {
                    j3 += bVar.f972b[i7];
                    i7++;
                }
            } else {
                bVar.f975g = null;
                while (i7 < 2) {
                    z zVar = (z) bVar.c.get(i7);
                    c cVar = this.f979A;
                    cVar.d(zVar);
                    cVar.d((z) bVar.d.get(i7));
                    i7++;
                }
                it.remove();
            }
        }
        this.f985s = j3;
    }

    public final void q() throws Throwable {
        D dC = AbstractC2312b.c(this.f979A.k(this.f981n));
        try {
            String strG = dC.G(Long.MAX_VALUE);
            String strG2 = dC.G(Long.MAX_VALUE);
            String strG3 = dC.G(Long.MAX_VALUE);
            String strG4 = dC.G(Long.MAX_VALUE);
            String strG5 = dC.G(Long.MAX_VALUE);
            if (!"libcore.io.DiskLruCache".equals(strG) || !"1".equals(strG2) || !m.a(String.valueOf(1), strG3) || !m.a(String.valueOf(2), strG4) || strG5.length() > 0) {
                throw new IOException("unexpected journal header: [" + strG + ", " + strG2 + ", " + strG3 + ", " + strG4 + ", " + strG5 + ']');
            }
            int i7 = 0;
            while (true) {
                try {
                    v(dC.G(Long.MAX_VALUE));
                    i7++;
                } catch (EOFException unused) {
                    this.f986t = i7 - this.f983q.size();
                    if (dC.b()) {
                        this.f987u = m();
                    } else {
                        F();
                    }
                    try {
                        dC.close();
                        th = null;
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                dC.close();
            } catch (Throwable th3) {
                q.l(th, th3);
            }
        }
        if (th != null) {
            throw th;
        }
    }

    public final void v(String str) throws IOException {
        String strSubstring;
        int iJ0 = Q8.h.J0(str, ' ', 0, false, 6);
        if (iJ0 == -1) {
            throw new IOException("unexpected journal line: ".concat(str));
        }
        int i7 = iJ0 + 1;
        int iJ02 = Q8.h.J0(str, ' ', i7, false, 4);
        LinkedHashMap linkedHashMap = this.f983q;
        if (iJ02 == -1) {
            strSubstring = str.substring(i7);
            m.e(strSubstring, "substring(...)");
            if (iJ0 == 6 && o.z0(str, "REMOVE", false)) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iJ02);
            m.e(strSubstring, "substring(...)");
        }
        Object bVar = linkedHashMap.get(strSubstring);
        if (bVar == null) {
            bVar = new b(this, strSubstring);
            linkedHashMap.put(strSubstring, bVar);
        }
        b bVar2 = (b) bVar;
        if (iJ02 == -1 || iJ0 != 5 || !o.z0(str, "CLEAN", false)) {
            if (iJ02 == -1 && iJ0 == 5 && o.z0(str, "DIRTY", false)) {
                bVar2.f975g = new A2.c(this, bVar2);
                return;
            } else {
                if (iJ02 != -1 || iJ0 != 4 || !o.z0(str, "READ", false)) {
                    throw new IOException("unexpected journal line: ".concat(str));
                }
                return;
            }
        }
        String strSubstring2 = str.substring(iJ02 + 1);
        m.e(strSubstring2, "substring(...)");
        List listW0 = Q8.h.W0(strSubstring2, new char[]{' '});
        bVar2.f973e = true;
        bVar2.f975g = null;
        int size = listW0.size();
        bVar2.f977i.getClass();
        if (size != 2) {
            throw new IOException("unexpected journal line: " + listW0);
        }
        try {
            int size2 = listW0.size();
            for (int i10 = 0; i10 < size2; i10++) {
                bVar2.f972b[i10] = Long.parseLong((String) listW0.get(i10));
            }
        } catch (NumberFormatException unused) {
            throw new IOException("unexpected journal line: " + listW0);
        }
    }

    public final void y(b bVar) {
        C c;
        int i7 = bVar.f976h;
        String str = bVar.f971a;
        if (i7 > 0 && (c = this.f987u) != null) {
            c.D("DIRTY");
            c.s(32);
            c.D(str);
            c.s(10);
            c.flush();
        }
        if (bVar.f976h > 0 || bVar.f975g != null) {
            bVar.f974f = true;
            return;
        }
        for (int i10 = 0; i10 < 2; i10++) {
            this.f979A.d((z) bVar.c.get(i10));
            long j3 = this.f985s;
            long[] jArr = bVar.f972b;
            this.f985s = j3 - jArr[i10];
            jArr[i10] = 0;
        }
        this.f986t++;
        C c10 = this.f987u;
        if (c10 != null) {
            c10.D("REMOVE");
            c10.s(32);
            c10.D(str);
            c10.s(10);
        }
        this.f983q.remove(str);
        if (this.f986t >= 2000) {
            k();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        y(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z() {
        /*
            r4 = this;
        L0:
            long r0 = r4.f985s
            long r2 = r4.f980m
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L27
            java.util.LinkedHashMap r0 = r4.f983q
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L26
            java.lang.Object r1 = r0.next()
            C3.b r1 = (C3.b) r1
            boolean r2 = r1.f974f
            if (r2 != 0) goto L12
            r4.y(r1)
            goto L0
        L26:
            return
        L27:
            r0 = 0
            r4.f991y = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: C3.e.z():void");
    }
}
