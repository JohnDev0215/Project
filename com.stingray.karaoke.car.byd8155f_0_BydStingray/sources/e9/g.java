package e9;

import A7.m;
import Q8.h;
import S0.p;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import l9.o;
import n7.AbstractC1985B;
import r9.AbstractC2312b;
import r9.C;
import r9.C2313c;
import r9.D;
import r9.L;
import r9.x;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Closeable, Flushable {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final Q8.g f14507D = new Q8.g("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f14508E = "CLEAN";

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final String f14509F = "DIRTY";

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final String f14510G = "REMOVE";

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final String f14511H = "READ";

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public long f14512A;
    public final f9.b B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final f f14513C;
    public final File l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f14514m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final File f14515n;
    public final File o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final File f14516p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f14517q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C f14518r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final LinkedHashMap f14519s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f14520t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f14521u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f14522v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f14523w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f14524x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f14525y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f14526z;

    public g(File file, long j3, f9.c cVar) {
        m.f(cVar, "taskRunner");
        this.l = file;
        this.f14514m = j3;
        this.f14519s = new LinkedHashMap(0, 0.75f, true);
        this.B = cVar.e();
        this.f14513C = new f(this, android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), d9.b.f13650h, " Cache"), 0);
        if (j3 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f14515n = new File(file, "journal");
        this.o = new File(file, "journal.tmp");
        this.f14516p = new File(file, "journal.bkp");
    }

    public static void G(String str) {
        if (f14507D.b(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
    }

    public final void C(d dVar) throws IOException {
        C c;
        m.f(dVar, "entry");
        boolean z6 = this.f14522v;
        String str = dVar.f14495a;
        if (!z6) {
            if (dVar.f14500h > 0 && (c = this.f14518r) != null) {
                c.D(f14509F);
                c.s(32);
                c.D(str);
                c.s(10);
                c.flush();
            }
            if (dVar.f14500h > 0 || dVar.f14499g != null) {
                dVar.f14498f = true;
                return;
            }
        }
        A2.c cVar = dVar.f14499g;
        if (cVar != null) {
            cVar.f();
        }
        for (int i7 = 0; i7 < 2; i7++) {
            File file = (File) dVar.c.get(i7);
            m.f(file, "file");
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
            long j3 = this.f14517q;
            long[] jArr = dVar.f14496b;
            this.f14517q = j3 - jArr[i7];
            jArr[i7] = 0;
        }
        this.f14520t++;
        C c10 = this.f14518r;
        if (c10 != null) {
            c10.D(f14510G);
            c10.s(32);
            c10.D(str);
            c10.s(10);
        }
        this.f14519s.remove(str);
        if (m()) {
            this.B.c(this.f14513C, 0L);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        C(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            long r0 = r4.f14517q
            long r2 = r4.f14514m
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L27
            java.util.LinkedHashMap r0 = r4.f14519s
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L26
            java.lang.Object r1 = r0.next()
            e9.d r1 = (e9.d) r1
            boolean r2 = r1.f14498f
            if (r2 != 0) goto L12
            r4.C(r1)
            goto L0
        L26:
            return
        L27:
            r0 = 0
            r4.f14525y = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: e9.g.F():void");
    }

    public final synchronized void b() {
        if (this.f14524x) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.f14523w && !this.f14524x) {
                Collection collectionValues = this.f14519s.values();
                m.e(collectionValues, "lruEntries.values");
                for (d dVar : (d[]) collectionValues.toArray(new d[0])) {
                    A2.c cVar = dVar.f14499g;
                    if (cVar != null) {
                        cVar.f();
                    }
                }
                F();
                C c = this.f14518r;
                m.c(c);
                c.close();
                this.f14518r = null;
                this.f14524x = true;
                return;
            }
            this.f14524x = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.f14523w) {
            b();
            F();
            C c = this.f14518r;
            m.c(c);
            c.flush();
        }
    }

    public final synchronized void g(A2.c cVar, boolean z6) {
        m.f(cVar, "editor");
        d dVar = (d) cVar.c;
        if (!m.a(dVar.f14499g, cVar)) {
            throw new IllegalStateException("Check failed.");
        }
        if (z6 && !dVar.f14497e) {
            for (int i7 = 0; i7 < 2; i7++) {
                boolean[] zArr = (boolean[]) cVar.d;
                m.c(zArr);
                if (!zArr[i7]) {
                    cVar.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i7);
                }
                File file = (File) dVar.d.get(i7);
                m.f(file, "file");
                if (!file.exists()) {
                    cVar.a();
                    return;
                }
            }
        }
        for (int i10 = 0; i10 < 2; i10++) {
            File file2 = (File) dVar.d.get(i10);
            if (!z6 || dVar.f14498f) {
                m.f(file2, "file");
                if (!file2.delete() && file2.exists()) {
                    throw new IOException("failed to delete " + file2);
                }
            } else {
                k9.a aVar = k9.a.f18322a;
                if (aVar.c(file2)) {
                    File file3 = (File) dVar.c.get(i10);
                    aVar.d(file2, file3);
                    long j3 = dVar.f14496b[i10];
                    long length = file3.length();
                    dVar.f14496b[i10] = length;
                    this.f14517q = (this.f14517q - j3) + length;
                }
            }
        }
        dVar.f14499g = null;
        if (dVar.f14498f) {
            C(dVar);
            return;
        }
        this.f14520t++;
        C c = this.f14518r;
        m.c(c);
        if (dVar.f14497e || z6) {
            dVar.f14497e = true;
            c.D(f14508E);
            c.s(32);
            c.D(dVar.f14495a);
            for (long j10 : dVar.f14496b) {
                c.s(32);
                c.E(j10);
            }
            c.s(10);
            if (z6) {
                long j11 = this.f14512A;
                this.f14512A = 1 + j11;
                dVar.f14501i = j11;
            }
        } else {
            this.f14519s.remove(dVar.f14495a);
            c.D(f14510G);
            c.s(32);
            c.D(dVar.f14495a);
            c.s(10);
        }
        c.flush();
        if (this.f14517q > this.f14514m || m()) {
            this.B.c(this.f14513C, 0L);
        }
    }

    public final synchronized A2.c i(String str, long j3) {
        try {
            m.f(str, "key");
            k();
            b();
            G(str);
            d dVar = (d) this.f14519s.get(str);
            if (j3 != -1 && (dVar == null || dVar.f14501i != j3)) {
                return null;
            }
            if ((dVar != null ? dVar.f14499g : null) != null) {
                return null;
            }
            if (dVar != null && dVar.f14500h != 0) {
                return null;
            }
            if (!this.f14525y && !this.f14526z) {
                C c = this.f14518r;
                m.c(c);
                c.D(f14509F);
                c.s(32);
                c.D(str);
                c.s(10);
                c.flush();
                if (this.f14521u) {
                    return null;
                }
                if (dVar == null) {
                    dVar = new d(this, str);
                    this.f14519s.put(str, dVar);
                }
                A2.c cVar = new A2.c(this, dVar);
                dVar.f14499g = cVar;
                return cVar;
            }
            this.B.c(this.f14513C, 0L);
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized e j(String str) {
        m.f(str, "key");
        k();
        b();
        G(str);
        d dVar = (d) this.f14519s.get(str);
        if (dVar == null) {
            return null;
        }
        e eVarA = dVar.a();
        if (eVarA == null) {
            return null;
        }
        this.f14520t++;
        C c = this.f14518r;
        m.c(c);
        c.D(f14511H);
        c.s(32);
        c.D(str);
        c.s(10);
        if (m()) {
            this.B.c(this.f14513C, 0L);
        }
        return eVarA;
    }

    public final synchronized void k() {
        boolean z6;
        try {
            byte[] bArr = d9.b.f13645a;
            if (this.f14523w) {
                return;
            }
            k9.a aVar = k9.a.f18322a;
            if (aVar.c(this.f14516p)) {
                if (aVar.c(this.f14515n)) {
                    aVar.a(this.f14516p);
                } else {
                    aVar.d(this.f14516p, this.f14515n);
                }
            }
            File file = this.f14516p;
            m.f(file, "file");
            C2313c c2313cE = aVar.e(file);
            try {
                aVar.a(file);
                AbstractC1985B.c(c2313cE, null);
                z6 = true;
            } catch (IOException unused) {
                AbstractC1985B.c(c2313cE, null);
                aVar.a(file);
                z6 = false;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AbstractC1985B.c(c2313cE, th);
                    throw th2;
                }
            }
            this.f14522v = z6;
            File file2 = this.f14515n;
            m.f(file2, "file");
            if (file2.exists()) {
                try {
                    v();
                    q();
                    this.f14523w = true;
                    return;
                } catch (IOException e10) {
                    o oVar = o.f18874a;
                    o oVar2 = o.f18874a;
                    String str = "DiskLruCache " + this.l + " is corrupt: " + e10.getMessage() + ", removing";
                    oVar2.getClass();
                    o.i(str, 5, e10);
                    try {
                        close();
                        k9.a.f18322a.b(this.l);
                        this.f14524x = false;
                        z();
                        this.f14523w = true;
                    } catch (Throwable th3) {
                        this.f14524x = false;
                        throw th3;
                    }
                }
            }
            z();
            this.f14523w = true;
        } catch (Throwable th4) {
            throw th4;
        }
    }

    public final boolean m() {
        int i7 = this.f14520t;
        return i7 >= 2000 && i7 >= this.f14519s.size();
    }

    public final C o() {
        C2313c c2313c;
        int i7 = 1;
        File file = this.f14515n;
        m.f(file, "file");
        try {
            Logger logger = x.f21949a;
            c2313c = new C2313c(h6.m.n(new FileOutputStream(file, true), file, true), i7, new L());
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            Logger logger2 = x.f21949a;
            c2313c = new C2313c(h6.m.n(new FileOutputStream(file, true), file, true), i7, new L());
        }
        return AbstractC2312b.b(new C3.f(c2313c, new p(this, 17)));
    }

    public final void q() {
        File file = this.o;
        k9.a aVar = k9.a.f18322a;
        aVar.a(file);
        Iterator it = this.f14519s.values().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            m.e(next, "i.next()");
            d dVar = (d) next;
            int i7 = 0;
            if (dVar.f14499g == null) {
                while (i7 < 2) {
                    this.f14517q += dVar.f14496b[i7];
                    i7++;
                }
            } else {
                dVar.f14499g = null;
                while (i7 < 2) {
                    aVar.a((File) dVar.c.get(i7));
                    aVar.a((File) dVar.d.get(i7));
                    i7++;
                }
                it.remove();
            }
        }
    }

    public final void v() throws IllegalAccessException, IOException, InvocationTargetException {
        File file = this.f14515n;
        m.f(file, "file");
        D dC = AbstractC2312b.c(AbstractC2312b.i(file));
        try {
            String strG = dC.G(Long.MAX_VALUE);
            String strG2 = dC.G(Long.MAX_VALUE);
            String strG3 = dC.G(Long.MAX_VALUE);
            String strG4 = dC.G(Long.MAX_VALUE);
            String strG5 = dC.G(Long.MAX_VALUE);
            if (!"libcore.io.DiskLruCache".equals(strG) || !"1".equals(strG2) || !m.a(String.valueOf(201105), strG3) || !m.a(String.valueOf(2), strG4) || strG5.length() > 0) {
                throw new IOException("unexpected journal header: [" + strG + ", " + strG2 + ", " + strG4 + ", " + strG5 + ']');
            }
            int i7 = 0;
            while (true) {
                try {
                    y(dC.G(Long.MAX_VALUE));
                    i7++;
                } catch (EOFException unused) {
                    this.f14520t = i7 - this.f14519s.size();
                    if (dC.b()) {
                        this.f14518r = o();
                    } else {
                        z();
                    }
                    AbstractC1985B.c(dC, null);
                    return;
                }
            }
        } finally {
        }
    }

    public final void y(String str) throws IOException {
        String strSubstring;
        int iJ0 = h.J0(str, ' ', 0, false, 6);
        if (iJ0 == -1) {
            throw new IOException("unexpected journal line: ".concat(str));
        }
        int i7 = iJ0 + 1;
        int iJ02 = h.J0(str, ' ', i7, false, 4);
        LinkedHashMap linkedHashMap = this.f14519s;
        if (iJ02 == -1) {
            strSubstring = str.substring(i7);
            m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
            String str2 = f14510G;
            if (iJ0 == str2.length() && Q8.o.z0(str, str2, false)) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i7, iJ02);
            m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        d dVar = (d) linkedHashMap.get(strSubstring);
        if (dVar == null) {
            dVar = new d(this, strSubstring);
            linkedHashMap.put(strSubstring, dVar);
        }
        if (iJ02 != -1) {
            String str3 = f14508E;
            if (iJ0 == str3.length() && Q8.o.z0(str, str3, false)) {
                String strSubstring2 = str.substring(iJ02 + 1);
                m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
                List listW0 = h.W0(strSubstring2, new char[]{' '});
                dVar.f14497e = true;
                dVar.f14499g = null;
                int size = listW0.size();
                dVar.f14502j.getClass();
                if (size != 2) {
                    throw new IOException("unexpected journal line: " + listW0);
                }
                try {
                    int size2 = listW0.size();
                    for (int i10 = 0; i10 < size2; i10++) {
                        dVar.f14496b[i10] = Long.parseLong((String) listW0.get(i10));
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw new IOException("unexpected journal line: " + listW0);
                }
            }
        }
        if (iJ02 == -1) {
            String str4 = f14509F;
            if (iJ0 == str4.length() && Q8.o.z0(str, str4, false)) {
                dVar.f14499g = new A2.c(this, dVar);
                return;
            }
        }
        if (iJ02 == -1) {
            String str5 = f14511H;
            if (iJ0 == str5.length() && Q8.o.z0(str, str5, false)) {
                return;
            }
        }
        throw new IOException("unexpected journal line: ".concat(str));
    }

    public final synchronized void z() {
        try {
            C c = this.f14518r;
            if (c != null) {
                c.close();
            }
            C cB = AbstractC2312b.b(k9.a.f18322a.e(this.o));
            try {
                cB.D("libcore.io.DiskLruCache");
                cB.s(10);
                cB.D("1");
                cB.s(10);
                cB.E(201105);
                cB.s(10);
                cB.E(2);
                cB.s(10);
                cB.s(10);
                Iterator it = this.f14519s.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    d dVar = (d) it.next();
                    if (dVar.f14499g != null) {
                        cB.D(f14509F);
                        cB.s(32);
                        cB.D(dVar.f14495a);
                        cB.s(10);
                    } else {
                        cB.D(f14508E);
                        cB.s(32);
                        cB.D(dVar.f14495a);
                        for (long j3 : dVar.f14496b) {
                            cB.s(32);
                            cB.E(j3);
                        }
                        cB.s(10);
                    }
                }
                AbstractC1985B.c(cB, null);
                k9.a aVar = k9.a.f18322a;
                if (aVar.c(this.f14515n)) {
                    aVar.d(this.f14515n, this.f14516p);
                }
                aVar.d(this.o, this.f14515n);
                aVar.a(this.f14516p);
                this.f14518r = o();
                this.f14521u = false;
                this.f14526z = false;
            } finally {
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
