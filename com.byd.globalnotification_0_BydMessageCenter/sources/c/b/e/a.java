package c.b.e;

import a.b.k.l;
import c.b.h.f.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b, c.b.h.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d<b> f2217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f2218b;

    @Override // c.b.h.a.a
    public boolean a(b bVar) {
        c.b.h.b.b.a(bVar, "disposable is null");
        if (!this.f2218b) {
            synchronized (this) {
                if (!this.f2218b) {
                    d<b> dVar = this.f2217a;
                    if (dVar == null) {
                        dVar = new d<>();
                        this.f2217a = dVar;
                    }
                    dVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.d();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046 A[Catch: all -> 0x004a, DONT_GENERATE, TryCatch #0 {, blocks: (B:6:0x000c, B:8:0x0010, B:10:0x0012, B:12:0x0016, B:26:0x0046, B:15:0x002a, B:17:0x0030, B:18:0x0035, B:21:0x003c, B:28:0x0048), top: B:33:0x000c }] */
    @Override // c.b.h.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(c.b.e.b r8) {
        /*
            r7 = this;
            java.lang.String r0 = "disposables is null"
            c.b.h.b.b.a(r8, r0)
            boolean r0 = r7.f2218b
            r1 = 0
            if (r0 == 0) goto Lb
            return r1
        Lb:
            monitor-enter(r7)
            boolean r0 = r7.f2218b     // Catch: java.lang.Throwable -> L4a
            if (r0 == 0) goto L12
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4a
            return r1
        L12:
            c.b.h.f.d<c.b.e.b> r0 = r7.f2217a     // Catch: java.lang.Throwable -> L4a
            if (r0 == 0) goto L48
            T[] r2 = r0.f2330e     // Catch: java.lang.Throwable -> L4a
            int r3 = r0.f2327b     // Catch: java.lang.Throwable -> L4a
            int r4 = r8.hashCode()     // Catch: java.lang.Throwable -> L4a
            int r4 = c.b.h.f.d.b(r4)     // Catch: java.lang.Throwable -> L4a
            r4 = r4 & r3
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L4a
            r6 = 1
            if (r5 != 0) goto L2a
        L28:
            r8 = r1
            goto L43
        L2a:
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> L4a
            if (r5 == 0) goto L35
        L30:
            r0.c(r4, r2, r3)     // Catch: java.lang.Throwable -> L4a
            r8 = r6
            goto L43
        L35:
            int r4 = r4 + r6
            r4 = r4 & r3
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L4a
            if (r5 != 0) goto L3c
            goto L28
        L3c:
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> L4a
            if (r5 == 0) goto L35
            goto L30
        L43:
            if (r8 != 0) goto L46
            goto L48
        L46:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4a
            return r6
        L48:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4a
            return r1
        L4a:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L4a
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.e.a.b(c.b.e.b):boolean");
    }

    @Override // c.b.h.a.a
    public boolean c(b bVar) {
        if (!b(bVar)) {
            return false;
        }
        bVar.d();
        return true;
    }

    @Override // c.b.e.b
    public void d() {
        if (this.f2218b) {
            return;
        }
        synchronized (this) {
            if (this.f2218b) {
                return;
            }
            this.f2218b = true;
            d<b> dVar = this.f2217a;
            ArrayList arrayList = null;
            this.f2217a = null;
            if (dVar == null) {
                return;
            }
            for (b bVar : dVar.f2330e) {
                if (bVar instanceof b) {
                    try {
                        bVar.d();
                    } catch (Throwable th) {
                        l.i.C0(th);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(th);
                    }
                }
            }
            if (arrayList != null) {
                if (arrayList.size() != 1) {
                    throw new c.b.f.a(arrayList);
                }
                throw c.b.h.f.b.a((Throwable) arrayList.get(0));
            }
        }
    }
}
