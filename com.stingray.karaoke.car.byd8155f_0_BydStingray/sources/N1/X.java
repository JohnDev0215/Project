package N1;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class X extends s7.i implements InterfaceC2785n {
    public N l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4505m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ B8.t f4506n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(B8.t tVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4506n = tVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new X(this.f4506n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((X) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0059 -> B:18:0x005c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.f4505m
            r2 = 2
            r3 = 1
            B8.t r4 = r6.f4506n
            if (r1 == 0) goto L20
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            h8.AbstractC1386c.u(r7)
            goto L5c
        L12:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1a:
            N1.N r1 = r6.l
            h8.AbstractC1386c.u(r7)
            goto L50
        L20:
            h8.AbstractC1386c.u(r7)
            java.lang.Object r7 = r4.f807m
            N1.a r7 = (N1.C0420a) r7
            java.lang.Object r7 = r7.l
            java.util.concurrent.atomic.AtomicInteger r7 = (java.util.concurrent.atomic.AtomicInteger) r7
            int r7 = r7.get()
            if (r7 <= 0) goto L6d
        L31:
            java.lang.Object r7 = r4.f808n
            S8.w r7 = (S8.InterfaceC0725w) r7
            q7.h r7 = r7.getCoroutineContext()
            S8.AbstractC0727y.k(r7)
            java.lang.Object r7 = r4.o
            r1 = r7
            N1.N r1 = (N1.N) r1
            r6.l = r1
            r6.f4505m = r3
            java.lang.Object r7 = r4.f809p
            U8.l r7 = (U8.l) r7
            java.lang.Object r7 = r7.c(r6)
            if (r7 != r0) goto L50
            return r0
        L50:
            r5 = 0
            r6.l = r5
            r6.f4505m = r2
            java.lang.Object r7 = r1.invoke(r7, r6)
            if (r7 != r0) goto L5c
            return r0
        L5c:
            java.lang.Object r7 = r4.f807m
            N1.a r7 = (N1.C0420a) r7
            java.lang.Object r7 = r7.l
            java.util.concurrent.atomic.AtomicInteger r7 = (java.util.concurrent.atomic.AtomicInteger) r7
            int r7 = r7.decrementAndGet()
            if (r7 != 0) goto L31
            m7.q r6 = m7.q.f19267a
            return r6
        L6d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Check failed."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.X.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
