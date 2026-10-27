package F0;

import S8.InterfaceC0725w;
import android.view.View;
import java.util.concurrent.atomic.AtomicReference;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: F0.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0196c0 implements InterfaceC0725w {
    public final View l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final T0.z f2184m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final InterfaceC0725w f2185n;
    public final AtomicReference o = new AtomicReference(null);

    public C0196c0(View view, T0.z zVar, InterfaceC0725w interfaceC0725w) {
        this.l = view;
        this.f2184m = zVar;
        this.f2185n = interfaceC0725w;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(M.z r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof F0.C0192a0
            if (r0 == 0) goto L13
            r0 = r7
            F0.a0 r0 = (F0.C0192a0) r0
            int r1 = r0.f2175n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2175n = r1
            goto L18
        L13:
            F0.a0 r0 = new F0.a0
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f2175n
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 == r3) goto L2b
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2b:
            h8.AbstractC1386c.u(r7)
            goto L4f
        L2f:
            h8.AbstractC1386c.u(r7)
            java.util.concurrent.atomic.AtomicReference r7 = r5.o
            A.b r2 = new A.b
            r4 = 10
            r2.<init>(r6, r4, r5)
            F0.b0 r6 = new F0.b0
            r4 = 0
            r6.<init>(r5, r4)
            r0.f2175n = r3
            g0.r r5 = new g0.r
            r5.<init>(r2, r7, r6, r4)
            java.lang.Object r5 = S8.AbstractC0727y.h(r5, r0)
            if (r5 != r1) goto L4f
            return
        L4f:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.C0196c0.a(M.z, s7.c):void");
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.f2185n.getCoroutineContext();
    }
}
