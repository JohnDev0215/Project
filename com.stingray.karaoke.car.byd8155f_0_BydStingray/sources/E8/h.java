package E8;

import A.C0050y0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public class h implements InterfaceC2772a {
    public final l l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2772a f1853m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile Object f1854n;

    public h(l lVar, InterfaceC2772a interfaceC2772a) {
        if (lVar == null) {
            f(0);
            throw null;
        }
        this.f1854n = k.l;
        this.l = lVar;
        this.f1853m = interfaceC2772a;
    }

    public static /* synthetic */ void f(int i7) {
        String str = (i7 == 2 || i7 == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 2 || i7 == 3) ? 2 : 3];
        if (i7 == 1) {
            objArr[0] = "computable";
        } else if (i7 == 2 || i7 == 3) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
        } else {
            objArr[0] = "storageManager";
        }
        if (i7 == 2) {
            objArr[1] = "recursionDetected";
        } else if (i7 != 3) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedLazyValue";
        } else {
            objArr[1] = "renderDebugInformation";
        }
        if (i7 != 2 && i7 != 3) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 2 && i7 != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046 A[Catch: all -> 0x0022, TRY_LEAVE, TryCatch #0 {all -> 0x0022, blocks: (B:7:0x0011, B:9:0x0017, B:16:0x002a, B:18:0x0035, B:20:0x003a, B:22:0x0043, B:23:0x0046, B:27:0x0055, B:29:0x005b, B:31:0x005f, B:32:0x0066, B:33:0x006d, B:34:0x006e, B:35:0x0074, B:24:0x0048), top: B:38:0x0011, inners: #1 }] */
    @Override // z7.InterfaceC2772a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f1854n
            boolean r1 = r0 instanceof E8.k
            if (r1 != 0) goto La
            O8.l.j(r0)
            return r0
        La:
            E8.l r0 = r5.l
            E8.n r0 = r0.f1860a
            r0.lock()
            java.lang.Object r0 = r5.f1854n     // Catch: java.lang.Throwable -> L22
            boolean r1 = r0 instanceof E8.k     // Catch: java.lang.Throwable -> L22
            if (r1 != 0) goto L24
            O8.l.j(r0)     // Catch: java.lang.Throwable -> L22
        L1a:
            E8.l r5 = r5.l
            E8.n r5 = r5.f1860a
            r5.unlock()
            return r0
        L22:
            r0 = move-exception
            goto L75
        L24:
            E8.k r1 = E8.k.f1857m
            E8.k r2 = E8.k.f1858n
            if (r0 != r1) goto L38
            r5.f1854n = r2     // Catch: java.lang.Throwable -> L22
            r3 = 1
            A.y0 r3 = r5.i(r3)     // Catch: java.lang.Throwable -> L22
            boolean r4 = r3.f300m     // Catch: java.lang.Throwable -> L22
            if (r4 != 0) goto L38
            java.lang.Object r0 = r3.f301n     // Catch: java.lang.Throwable -> L22
            goto L1a
        L38:
            if (r0 != r2) goto L46
            r0 = 0
            A.y0 r0 = r5.i(r0)     // Catch: java.lang.Throwable -> L22
            boolean r2 = r0.f300m     // Catch: java.lang.Throwable -> L22
            if (r2 != 0) goto L46
            java.lang.Object r0 = r0.f301n     // Catch: java.lang.Throwable -> L22
            goto L1a
        L46:
            r5.f1854n = r1     // Catch: java.lang.Throwable -> L22
            z7.a r0 = r5.f1853m     // Catch: java.lang.Throwable -> L54
            java.lang.Object r0 = r0.a()     // Catch: java.lang.Throwable -> L54
            r5.h(r0)     // Catch: java.lang.Throwable -> L54
            r5.f1854n = r0     // Catch: java.lang.Throwable -> L54
            goto L1a
        L54:
            r0 = move-exception
            boolean r2 = O8.l.h(r0)     // Catch: java.lang.Throwable -> L22
            if (r2 != 0) goto L6e
            java.lang.Object r2 = r5.f1854n     // Catch: java.lang.Throwable -> L22
            if (r2 != r1) goto L66
            O8.k r1 = new O8.k     // Catch: java.lang.Throwable -> L22
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L22
            r5.f1854n = r1     // Catch: java.lang.Throwable -> L22
        L66:
            E8.l r1 = r5.l     // Catch: java.lang.Throwable -> L22
            E8.a r1 = r1.f1861b     // Catch: java.lang.Throwable -> L22
            r1.getClass()     // Catch: java.lang.Throwable -> L22
            throw r0     // Catch: java.lang.Throwable -> L22
        L6e:
            E8.k r1 = E8.k.l     // Catch: java.lang.Throwable -> L22
            r5.f1854n = r1     // Catch: java.lang.Throwable -> L22
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0     // Catch: java.lang.Throwable -> L22
            throw r0     // Catch: java.lang.Throwable -> L22
        L75:
            E8.l r5 = r5.l
            E8.n r5 = r5.f1860a
            r5.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: E8.h.a():java.lang.Object");
    }

    public void h(Object obj) {
    }

    public C0050y0 i(boolean z6) {
        C0050y0 c0050y0D = this.l.d(null, "in a lazy value");
        if (c0050y0D != null) {
            return c0050y0D;
        }
        f(2);
        throw null;
    }
}
