package defpackage;

import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class St extends Xt {
    public final Wt j;
    public final Wt k;

    public St(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, int i, Rt rt) {
        super(handler, runnable, str, str2, z, z2, z3, null);
        this.j = new Wt(handler, runnable, str, Z7.c(str2, "0"), z, z2, z3, i, null);
        this.k = new Wt(handler, runnable, str, Z7.c(str2, "1"), z, z2, z3, i, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    @Override // defpackage.Xt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.C1406qu c(android.content.Context r6, android.os.Bundle r7, defpackage.InterfaceC1351pu r8) {
        /*
            r5 = this;
            Wt r0 = r5.j
            qu r1 = r0.f(r6, r7)
            r2 = 0
            if (r1 != 0) goto La
            goto L24
        La:
            boolean r3 = r0.h
            java.lang.String r4 = "ChildProcessConnection.tryStart"
            org.chromium.base.TraceEvent.x(r4, r2)     // Catch: java.lang.Throwable -> L43
            boolean r3 = r1.a(r3)     // Catch: java.lang.Throwable -> L43
            if (r3 != 0) goto L1c
            r3 = 0
            org.chromium.base.TraceEvent.A(r4)
            goto L22
        L1c:
            r1.e = r8     // Catch: java.lang.Throwable -> L43
            org.chromium.base.TraceEvent.A(r4)
            r3 = 1
        L22:
            if (r3 != 0) goto L26
        L24:
            r1 = r2
            goto L2b
        L26:
            l1 r0 = r0.k
            r0.add(r1)
        L2b:
            if (r1 == 0) goto L2e
            return r1
        L2e:
            Wt r0 = r5.k
            qu r6 = r0.f(r6, r7)
            if (r6 != 0) goto L37
            goto L42
        L37:
            l1 r7 = r0.k
            r7.add(r6)
            boolean r7 = r0.h
            r6.i(r7, r8)
            r2 = r6
        L42:
            return r2
        L43:
            r6 = move-exception
            org.chromium.base.TraceEvent.A(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.St.c(android.content.Context, android.os.Bundle, pu):qu");
    }

    @Override // defpackage.Xt
    public void d(C1406qu c1406qu) {
        if (this.j.k.indexOf(c1406qu) >= 0) {
            this.j.k.remove(c1406qu);
            return;
        }
        if (this.k.k.indexOf(c1406qu) >= 0) {
            this.k.k.remove(c1406qu);
        }
    }

    @Override // defpackage.Xt
    public int e() {
        return -1;
    }
}
