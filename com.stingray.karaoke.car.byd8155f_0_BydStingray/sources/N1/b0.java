package N1;

import V8.C0819h;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b9.c f4513a = b9.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0420a f4514b = new C0420a(0);
    public final C0819h c = new C0819h(new a0(2, null));

    public b0(String str) {
    }

    public final Integer a() {
        return new Integer(((AtomicInteger) this.f4514b.l).get());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v0, types: [N1.b0] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v6, types: [b9.c] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(z7.InterfaceC2782k r7, s7.AbstractC2410c r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof N1.Y
            if (r0 == 0) goto L13
            r0 = r8
            N1.Y r0 = (N1.Y) r0
            int r1 = r0.f4509p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4509p = r1
            goto L18
        L13:
            N1.Y r0 = new N1.Y
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.f4508n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f4509p
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L43
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.l
            b9.a r6 = (b9.InterfaceC1014a) r6
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Throwable -> L2f
            goto L62
        L2f:
            r7 = move-exception
            goto L68
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            b9.c r6 = r0.f4507m
            java.lang.Object r7 = r0.l
            z7.k r7 = (z7.InterfaceC2782k) r7
            h8.AbstractC1386c.u(r8)
            goto L55
        L43:
            h8.AbstractC1386c.u(r8)
            r0.l = r7
            b9.c r6 = r6.f4513a
            r0.f4507m = r6
            r0.f4509p = r4
            java.lang.Object r8 = r6.c(r5, r0)
            if (r8 != r1) goto L55
            return r1
        L55:
            r0.l = r6     // Catch: java.lang.Throwable -> L2f
            r0.f4507m = r5     // Catch: java.lang.Throwable -> L2f
            r0.f4509p = r3     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r8 = r7.b(r0)     // Catch: java.lang.Throwable -> L2f
            if (r8 != r1) goto L62
            return r1
        L62:
            b9.c r6 = (b9.c) r6
            r6.e(r5)
            return r8
        L68:
            b9.c r6 = (b9.c) r6
            r6.e(r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.b0.b(z7.k, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(z7.InterfaceC2785n r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r8 instanceof N1.Z
            if (r0 == 0) goto L13
            r0 = r8
            N1.Z r0 = (N1.Z) r0
            int r1 = r0.f4512p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4512p = r1
            goto L18
        L13:
            N1.Z r0 = new N1.Z
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.f4511n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f4512p
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            boolean r6 = r0.f4510m
            b9.c r7 = r0.l
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Throwable -> L2c
            goto L54
        L2c:
            r8 = move-exception
            goto L5f
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            h8.AbstractC1386c.u(r8)
            b9.c r6 = r6.f4513a
            boolean r8 = r6.d(r4)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Throwable -> L5a
            r0.l = r6     // Catch: java.lang.Throwable -> L5a
            r0.f4510m = r8     // Catch: java.lang.Throwable -> L5a
            r0.f4512p = r3     // Catch: java.lang.Throwable -> L5a
            java.lang.Object r7 = r7.invoke(r2, r0)     // Catch: java.lang.Throwable -> L5a
            if (r7 != r1) goto L50
            return r1
        L50:
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L54:
            if (r6 == 0) goto L59
            r7.e(r4)
        L59:
            return r8
        L5a:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r8
            r8 = r5
        L5f:
            if (r6 == 0) goto L64
            r7.e(r4)
        L64:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.b0.c(z7.n, s7.c):java.lang.Object");
    }
}
