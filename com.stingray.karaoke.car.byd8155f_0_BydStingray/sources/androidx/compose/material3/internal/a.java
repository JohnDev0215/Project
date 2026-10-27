package androidx.compose.material3.internal;

import R.w;
import g0.o;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(z7.InterfaceC2772a r4, z7.InterfaceC2785n r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof R.C0681i
            if (r0 == 0) goto L13
            r0 = r6
            R.i r0 = (R.C0681i) r0
            int r1 = r0.f7299m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f7299m = r1
            goto L18
        L13:
            R.i r0 = new R.i
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f7299m
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            h8.AbstractC1386c.u(r6)     // Catch: R.C0676d -> L41
            goto L41
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            h8.AbstractC1386c.u(r6)
            R.m r6 = new R.m     // Catch: R.C0676d -> L41
            r2 = 0
            r6.<init>(r4, r5, r2)     // Catch: R.C0676d -> L41
            r0.f7299m = r3     // Catch: R.C0676d -> L41
            java.lang.Object r4 = S8.AbstractC0727y.h(r6, r0)     // Catch: R.C0676d -> L41
            if (r4 != r1) goto L41
            goto L43
        L41:
            m7.q r1 = m7.q.f19267a
        L43:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.a.a(z7.a, z7.n, s7.c):java.lang.Object");
    }

    public static final o b(w wVar, InterfaceC2785n interfaceC2785n) {
        return new DraggableAnchorsElement(wVar, interfaceC2785n);
    }
}
