package S8;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class U extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ A3.g f8097m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U(A3.g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8097m = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        U u4 = new U(this.f8097m, interfaceC2189c);
        u4.l = obj;
        return u4;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((U) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0041, code lost:
    
        return r3.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
    
        r0.m();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        throw r3;
     */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) throws java.lang.Throwable {
        /*
            r3 = this;
            r7.a r0 = r7.EnumC2303a.l
            h8.AbstractC1386c.u(r4)
            java.lang.Object r4 = r3.l
            S8.w r4 = (S8.InterfaceC0725w) r4
            q7.h r4 = r4.getCoroutineContext()
            A3.g r3 = r3.f8097m
            S8.o0 r0 = new S8.o0     // Catch: java.lang.InterruptedException -> L42
            r0.<init>()     // Catch: java.lang.InterruptedException -> L42
            S8.W r4 = S8.AbstractC0727y.o(r4)     // Catch: java.lang.InterruptedException -> L42
            r1 = 1
            S8.F r4 = S8.AbstractC0727y.r(r4, r1, r0)     // Catch: java.lang.InterruptedException -> L42
            r0.f8125q = r4     // Catch: java.lang.InterruptedException -> L42
        L1f:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = S8.o0.f8123r     // Catch: java.lang.InterruptedException -> L42
            int r1 = r4.get(r0)     // Catch: java.lang.InterruptedException -> L42
            if (r1 == 0) goto L33
            r4 = 2
            if (r1 == r4) goto L3a
            r4 = 3
            if (r1 != r4) goto L2e
            goto L3a
        L2e:
            S8.o0.n(r1)     // Catch: java.lang.InterruptedException -> L42
            r3 = 0
            throw r3     // Catch: java.lang.InterruptedException -> L42
        L33:
            r2 = 0
            boolean r4 = r4.compareAndSet(r0, r1, r2)     // Catch: java.lang.InterruptedException -> L42
            if (r4 == 0) goto L1f
        L3a:
            java.lang.Object r3 = r3.a()     // Catch: java.lang.Throwable -> L44
            r0.m()     // Catch: java.lang.InterruptedException -> L42
            return r3
        L42:
            r3 = move-exception
            goto L49
        L44:
            r3 = move-exception
            r0.m()     // Catch: java.lang.InterruptedException -> L42
            throw r3     // Catch: java.lang.InterruptedException -> L42
        L49:
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r0 = "Blocking call was interrupted due to parent cancellation"
            r4.<init>(r0)
            java.lang.Throwable r3 = r4.initCause(r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: S8.U.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
