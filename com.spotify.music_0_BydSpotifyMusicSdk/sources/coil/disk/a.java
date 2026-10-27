package coil.disk;

import java.io.Closeable;
import java.io.EOFException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.text.Regex;
import kotlin.text.b;
import p.aa0;
import p.ba0;
import p.c81;
import p.ca0;
import p.da0;
import p.eh2;
import p.f70;
import p.he1;
import p.hm0;
import p.i;
import p.io;
import p.lq2;
import p.ng2;
import p.os1;
import p.q30;
import p.ry0;
import p.v02;
import p.v03;
import p.vr2;
import p.w02;
import p.yz;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Closeable, Flushable {
    public static final Regex y = new Regex("[a-z0-9_-]{1,120}");
    public final os1 c;
    public final long e;
    public final os1 k;
    public final os1 l;
    public final os1 m;
    public final LinkedHashMap n;
    public final yz o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f25p;
    public int q;
    public io r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public final da0 x;

    public a(c81 c81Var, os1 os1Var, f70 f70Var, long j) {
        this.c = os1Var;
        this.e = j;
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        this.k = os1Var.d("journal");
        this.l = os1Var.d("journal.tmp");
        this.m = os1Var.d("journal.bkp");
        this.n = new LinkedHashMap(0, 0.75f, true);
        this.o = q30.b(kotlin.coroutines.a.a(new eh2(null), f70Var.J0(1)));
        this.x = new da0(c81Var);
    }

    public static void J0(String str) {
        if (y.a(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
    }

    public static final void b(a aVar, aa0 aa0Var, boolean z) {
        synchronized (aVar) {
            ba0 ba0Var = (ba0) aa0Var.b;
            if (!v03.b(ba0Var.g, aa0Var)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!z || ba0Var.f) {
                for (int i = 0; i < 2; i++) {
                    aVar.x.e((os1) ba0Var.d.get(i));
                }
            } else {
                for (int i2 = 0; i2 < 2; i2++) {
                    if (((boolean[]) aa0Var.c)[i2] && !aVar.x.f((os1) ba0Var.d.get(i2))) {
                        aa0Var.a(false);
                        return;
                    }
                }
                for (int i3 = 0; i3 < 2; i3++) {
                    os1 os1Var = (os1) ba0Var.d.get(i3);
                    os1 os1Var2 = (os1) ba0Var.c.get(i3);
                    if (aVar.x.f(os1Var)) {
                        aVar.x.b(os1Var, os1Var2);
                    } else {
                        da0 da0Var = aVar.x;
                        os1 os1Var3 = (os1) ba0Var.c.get(i3);
                        if (!da0Var.f(os1Var3)) {
                            i.a(da0Var.k(os1Var3));
                        }
                    }
                    long j = ba0Var.b[i3];
                    Long l = (Long) aVar.x.h(os1Var2).e;
                    long jLongValue = l != null ? l.longValue() : 0L;
                    ba0Var.b[i3] = jLongValue;
                    aVar.f25p = (aVar.f25p - j) + jLongValue;
                }
            }
            ba0Var.g = null;
            if (ba0Var.f) {
                aVar.H0(ba0Var);
                return;
            }
            aVar.q++;
            io ioVar = aVar.r;
            v03.h(ioVar);
            if (z || ba0Var.e) {
                ba0Var.e = true;
                ioVar.s0("CLEAN");
                ioVar.R(32);
                ioVar.s0(ba0Var.a);
                for (long j2 : ba0Var.b) {
                    ioVar.R(32).t0(j2);
                }
                ioVar.R(10);
            } else {
                aVar.n.remove(ba0Var.a);
                ioVar.s0("REMOVE");
                ioVar.R(32);
                ioVar.s0(ba0Var.a);
                ioVar.R(10);
            }
            ioVar.flush();
            if (aVar.f25p > aVar.e || aVar.q >= 2000) {
                aVar.J();
            }
        }
    }

    public final synchronized void E() {
        try {
            if (this.t) {
                return;
            }
            this.x.e(this.l);
            if (this.x.f(this.m)) {
                if (this.x.f(this.k)) {
                    this.x.e(this.m);
                } else {
                    this.x.b(this.m, this.k);
                }
            }
            if (this.x.f(this.k)) {
                try {
                    E0();
                    o0();
                    this.t = true;
                    return;
                } catch (IOException unused) {
                    try {
                        close();
                        he1.o(this.x, this.c);
                        this.u = false;
                        K0();
                        this.t = true;
                    } catch (Throwable th) {
                        this.u = false;
                        throw th;
                    }
                }
            }
            K0();
            this.t = true;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final void E0() throws Throwable {
        lq2 lq2Var;
        w02 w02VarD = v03.d(this.x.l(this.k));
        Throwable th = null;
        try {
            String strG0 = w02VarD.g0(Long.MAX_VALUE);
            String strG02 = w02VarD.g0(Long.MAX_VALUE);
            String strG03 = w02VarD.g0(Long.MAX_VALUE);
            String strG04 = w02VarD.g0(Long.MAX_VALUE);
            String strG05 = w02VarD.g0(Long.MAX_VALUE);
            if (!v03.b("libcore.io.DiskLruCache", strG0) || !v03.b("1", strG02) || !v03.b(String.valueOf(1), strG03) || !v03.b(String.valueOf(2), strG04) || strG05.length() > 0) {
                throw new IOException("unexpected journal header: [" + strG0 + ", " + strG02 + ", " + strG03 + ", " + strG04 + ", " + strG05 + ']');
            }
            int i = 0;
            while (true) {
                try {
                    G0(w02VarD.g0(Long.MAX_VALUE));
                    i++;
                } catch (EOFException unused) {
                    this.q = i - this.n.size();
                    if (w02VarD.Q()) {
                        this.r = X();
                    } else {
                        K0();
                    }
                    lq2Var = lq2.a;
                    try {
                        w02VarD.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        } catch (Throwable th3) {
            try {
                w02VarD.close();
            } catch (Throwable th4) {
                kotlin.a.a(th3, th4);
            }
            th = th3;
            lq2Var = null;
        }
        if (th != null) {
            throw th;
        }
        v03.h(lq2Var);
    }

    public final void G0(String str) throws IOException {
        String strSubstring;
        int iN0 = b.n0(str, ' ', 0, false, 6);
        if (iN0 == -1) {
            throw new IOException("unexpected journal line: ".concat(str));
        }
        int i = iN0 + 1;
        int iN02 = b.n0(str, ' ', i, false, 4);
        LinkedHashMap linkedHashMap = this.n;
        if (iN02 == -1) {
            strSubstring = str.substring(i);
            v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
            if (iN0 == 6 && ng2.g0(str, "REMOVE", false)) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iN02);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        Object ba0Var = linkedHashMap.get(strSubstring);
        if (ba0Var == null) {
            ba0Var = new ba0(this, strSubstring);
            linkedHashMap.put(strSubstring, ba0Var);
        }
        ba0 ba0Var2 = (ba0) ba0Var;
        if (iN02 == -1 || iN0 != 5 || !ng2.g0(str, "CLEAN", false)) {
            if (iN02 == -1 && iN0 == 5 && ng2.g0(str, "DIRTY", false)) {
                ba0Var2.g = new aa0(this, ba0Var2);
                return;
            } else {
                if (iN02 != -1 || iN0 != 4 || !ng2.g0(str, "READ", false)) {
                    throw new IOException("unexpected journal line: ".concat(str));
                }
                return;
            }
        }
        String strSubstring2 = str.substring(iN02 + 1);
        v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
        List listY0 = b.y0(strSubstring2, new char[]{' '});
        ba0Var2.e = true;
        ba0Var2.g = null;
        int size = listY0.size();
        ba0Var2.i.getClass();
        if (size != 2) {
            throw new IOException("unexpected journal line: " + listY0);
        }
        try {
            int size2 = listY0.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ba0Var2.b[i2] = Long.parseLong((String) listY0.get(i2));
            }
        } catch (NumberFormatException unused) {
            throw new IOException("unexpected journal line: " + listY0);
        }
    }

    public final void H0(ba0 ba0Var) {
        io ioVar;
        int i = ba0Var.h;
        String str = ba0Var.a;
        if (i > 0 && (ioVar = this.r) != null) {
            ioVar.s0("DIRTY");
            ioVar.R(32);
            ioVar.s0(str);
            ioVar.R(10);
            ioVar.flush();
        }
        if (ba0Var.h > 0 || ba0Var.g != null) {
            ba0Var.f = true;
            return;
        }
        for (int i2 = 0; i2 < 2; i2++) {
            this.x.e((os1) ba0Var.c.get(i2));
            long j = this.f25p;
            long[] jArr = ba0Var.b;
            this.f25p = j - jArr[i2];
            jArr[i2] = 0;
        }
        this.q++;
        io ioVar2 = this.r;
        if (ioVar2 != null) {
            ioVar2.s0("REMOVE");
            ioVar2.R(32);
            ioVar2.s0(str);
            ioVar2.R(10);
        }
        this.n.remove(str);
        if (this.q >= 2000) {
            J();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        H0(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I0() {
        /*
            r4 = this;
        L0:
            long r0 = r4.f25p
            long r2 = r4.e
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L27
            java.util.LinkedHashMap r0 = r4.n
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L26
            java.lang.Object r1 = r0.next()
            p.ba0 r1 = (p.ba0) r1
            boolean r2 = r1.f
            if (r2 != 0) goto L12
            r4.H0(r1)
            goto L0
        L26:
            return
        L27:
            r0 = 0
            r4.v = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.disk.a.I0():void");
    }

    public final void J() {
        vr2.l(this.o, null, null, new DiskLruCache$launchCleanup$1(this, null), 3);
    }

    public final synchronized void K0() {
        lq2 lq2Var;
        try {
            io ioVar = this.r;
            if (ioVar != null) {
                ioVar.close();
            }
            v02 v02VarC = v03.c(this.x.k(this.l));
            Throwable th = null;
            try {
                v02VarC.s0("libcore.io.DiskLruCache");
                v02VarC.R(10);
                v02VarC.s0("1");
                v02VarC.R(10);
                v02VarC.t0(1);
                v02VarC.R(10);
                v02VarC.t0(2);
                v02VarC.R(10);
                v02VarC.R(10);
                for (ba0 ba0Var : this.n.values()) {
                    if (ba0Var.g != null) {
                        v02VarC.s0("DIRTY");
                        v02VarC.R(32);
                        v02VarC.s0(ba0Var.a);
                        v02VarC.R(10);
                    } else {
                        v02VarC.s0("CLEAN");
                        v02VarC.R(32);
                        v02VarC.s0(ba0Var.a);
                        for (long j : ba0Var.b) {
                            v02VarC.R(32);
                            v02VarC.t0(j);
                        }
                        v02VarC.R(10);
                    }
                }
                lq2Var = lq2.a;
                try {
                    v02VarC.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    v02VarC.close();
                } catch (Throwable th4) {
                    kotlin.a.a(th3, th4);
                }
                lq2Var = null;
                th = th3;
            }
            if (th != null) {
                throw th;
            }
            v03.h(lq2Var);
            if (this.x.f(this.k)) {
                this.x.b(this.k, this.m);
                this.x.b(this.l, this.k);
                this.x.e(this.m);
            } else {
                this.x.b(this.l, this.k);
            }
            this.r = X();
            this.q = 0;
            this.s = false;
            this.w = false;
        } catch (Throwable th5) {
            throw th5;
        }
    }

    public final v02 X() {
        da0 da0Var = this.x;
        da0Var.getClass();
        os1 os1Var = this.k;
        v03.j(os1Var, "file");
        return v03.c(new hm0(da0Var.b.a(os1Var), new ry0() { // from class: coil.disk.DiskLruCache$newJournalWriter$faultHidingSink$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                this.this$0.s = true;
                return lq2.a;
            }
        }));
    }

    public final void c() {
        if (!(!this.u)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.t && !this.u) {
                for (ba0 ba0Var : (ba0[]) this.n.values().toArray(new ba0[0])) {
                    aa0 aa0Var = ba0Var.g;
                    if (aa0Var != null) {
                        Object obj = aa0Var.b;
                        if (v03.b(((ba0) obj).g, aa0Var)) {
                            ((ba0) obj).f = true;
                        }
                    }
                }
                I0();
                q30.e(this.o);
                io ioVar = this.r;
                v03.h(ioVar);
                ioVar.close();
                this.r = null;
                this.u = true;
                return;
            }
            this.u = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.t) {
            c();
            I0();
            io ioVar = this.r;
            v03.h(ioVar);
            ioVar.flush();
        }
    }

    public final synchronized aa0 i(String str) {
        try {
            c();
            J0(str);
            E();
            ba0 ba0Var = (ba0) this.n.get(str);
            if ((ba0Var != null ? ba0Var.g : null) != null) {
                return null;
            }
            if (ba0Var != null && ba0Var.h != 0) {
                return null;
            }
            if (!this.v && !this.w) {
                io ioVar = this.r;
                v03.h(ioVar);
                ioVar.s0("DIRTY");
                ioVar.R(32);
                ioVar.s0(str);
                ioVar.R(10);
                ioVar.flush();
                if (this.s) {
                    return null;
                }
                if (ba0Var == null) {
                    ba0Var = new ba0(this, str);
                    this.n.put(str, ba0Var);
                }
                aa0 aa0Var = new aa0(this, ba0Var);
                ba0Var.g = aa0Var;
                return aa0Var;
            }
            J();
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void o0() {
        Iterator it = this.n.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            ba0 ba0Var = (ba0) it.next();
            int i = 0;
            if (ba0Var.g == null) {
                while (i < 2) {
                    j += ba0Var.b[i];
                    i++;
                }
            } else {
                ba0Var.g = null;
                while (i < 2) {
                    os1 os1Var = (os1) ba0Var.c.get(i);
                    da0 da0Var = this.x;
                    da0Var.e(os1Var);
                    da0Var.e((os1) ba0Var.d.get(i));
                    i++;
                }
                it.remove();
            }
        }
        this.f25p = j;
    }

    public final synchronized ca0 q(String str) {
        ca0 ca0VarA;
        c();
        J0(str);
        E();
        ba0 ba0Var = (ba0) this.n.get(str);
        if (ba0Var != null && (ca0VarA = ba0Var.a()) != null) {
            this.q++;
            io ioVar = this.r;
            v03.h(ioVar);
            ioVar.s0("READ");
            ioVar.R(32);
            ioVar.s0(str);
            ioVar.R(10);
            if (this.q >= 2000) {
                J();
            }
            return ca0VarA;
        }
        return null;
    }
}
