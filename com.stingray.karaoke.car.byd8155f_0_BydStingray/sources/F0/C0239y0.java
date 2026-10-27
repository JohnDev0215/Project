package F0;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: F0.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0239y0 extends s7.i implements InterfaceC2785n {
    public U8.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public U8.d f2297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2298n;
    public final /* synthetic */ U8.l o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0239y0(U8.l lVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = lVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0239y0(this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0239y0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c A[Catch: all -> 0x0012, TryCatch #1 {all -> 0x0012, blocks: (B:6:0x000e, B:17:0x0034, B:19:0x003c, B:20:0x004a, B:28:0x0061, B:14:0x0027, B:30:0x0064, B:31:0x0068, B:32:0x0069, B:13:0x0021, B:21:0x004b, B:23:0x0057), top: B:46:0x0006, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0031 -> B:17:0x0034). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f2298n
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 != r3) goto L14
            U8.d r1 = r7.f2297m
            U8.z r4 = r7.l
            h8.AbstractC1386c.u(r8)     // Catch: java.lang.Throwable -> L12
            goto L34
        L12:
            r7 = move-exception
            goto L70
        L14:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1c:
            h8.AbstractC1386c.u(r8)
            U8.l r4 = r7.o
            U8.d r8 = new U8.d     // Catch: java.lang.Throwable -> L12
            r8.<init>(r4)     // Catch: java.lang.Throwable -> L12
            r1 = r8
        L27:
            r7.l = r4     // Catch: java.lang.Throwable -> L12
            r7.f2297m = r1     // Catch: java.lang.Throwable -> L12
            r7.f2298n = r3     // Catch: java.lang.Throwable -> L12
            java.lang.Object r8 = r1.a(r7)     // Catch: java.lang.Throwable -> L12
            if (r8 != r0) goto L34
            return r0
        L34:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L12
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L12
            if (r8 == 0) goto L6a
            java.lang.Object r8 = r1.c()     // Catch: java.lang.Throwable -> L12
            m7.q r8 = (m7.q) r8     // Catch: java.lang.Throwable -> L12
            java.util.concurrent.atomic.AtomicBoolean r8 = F0.AbstractC0241z0.f2302b     // Catch: java.lang.Throwable -> L12
            r5 = 0
            r8.set(r5)     // Catch: java.lang.Throwable -> L12
            java.lang.Object r8 = e0.AbstractC1174n.f13721b     // Catch: java.lang.Throwable -> L12
            monitor-enter(r8)     // Catch: java.lang.Throwable -> L12
            java.util.concurrent.atomic.AtomicReference r6 = e0.AbstractC1174n.f13726i     // Catch: java.lang.Throwable -> L5f
            java.lang.Object r6 = r6.get()     // Catch: java.lang.Throwable -> L5f
            e0.b r6 = (e0.C1162b) r6     // Catch: java.lang.Throwable -> L5f
            u.A r6 = r6.f13696h     // Catch: java.lang.Throwable -> L5f
            if (r6 == 0) goto L61
            boolean r6 = r6.h()     // Catch: java.lang.Throwable -> L5f
            if (r6 != r3) goto L61
            r5 = r3
            goto L61
        L5f:
            r7 = move-exception
            goto L68
        L61:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L12
            if (r5 == 0) goto L27
            e0.AbstractC1174n.a()     // Catch: java.lang.Throwable -> L12
            goto L27
        L68:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L12
            throw r7     // Catch: java.lang.Throwable -> L12
        L6a:
            r4.g(r2)
            m7.q r7 = m7.q.f19267a
            return r7
        L70:
            throw r7     // Catch: java.lang.Throwable -> L71
        L71:
            r8 = move-exception
            boolean r0 = r7 instanceof java.util.concurrent.CancellationException
            if (r0 == 0) goto L79
            r2 = r7
            java.util.concurrent.CancellationException r2 = (java.util.concurrent.CancellationException) r2
        L79:
            if (r2 != 0) goto L85
            java.util.concurrent.CancellationException r2 = new java.util.concurrent.CancellationException
            java.lang.String r0 = "Channel was consumed, consumer had failed"
            r2.<init>(r0)
            r2.initCause(r7)
        L85:
            r4.g(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.C0239y0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
