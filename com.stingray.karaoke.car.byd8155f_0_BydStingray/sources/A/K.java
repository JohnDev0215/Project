package A;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class K extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f59m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ V f60n;
    public final /* synthetic */ C2722B o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ N f61p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0004b f62q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ M f63r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ M f64s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ L f65t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(V v10, C2722B c2722b, N n10, C0004b c0004b, M m10, M m11, L l, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f60n = v10;
        this.o = c2722b;
        this.f61p = n10;
        this.f62q = c0004b;
        this.f63r = m10;
        this.f64s = m11;
        this.f65t = l;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        M m10 = this.f64s;
        L l = this.f65t;
        K k10 = new K(this.f60n, this.o, this.f61p, this.f62q, this.f63r, m10, l, interfaceC2189c);
        k10.f59m = obj;
        return k10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((K) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0068  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
        /*
            r16 = this;
            r0 = r16
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.l
            m7.q r3 = m7.q.f19267a
            A.V r4 = r0.f60n
            r5 = 1
            if (r2 == 0) goto L22
            if (r2 != r5) goto L1a
            java.lang.Object r0 = r0.f59m
            r1 = r0
            S8.w r1 = (S8.InterfaceC0725w) r1
            h8.AbstractC1386c.u(r17)     // Catch: java.util.concurrent.CancellationException -> L18
            goto L67
        L18:
            r0 = move-exception
            goto L58
        L1a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L22:
            h8.AbstractC1386c.u(r17)
            java.lang.Object r2 = r0.f59m
            S8.w r2 = (S8.InterfaceC0725w) r2
            A.h0 r9 = r4.f120A     // Catch: java.util.concurrent.CancellationException -> L56
            y0.B r15 = r0.o     // Catch: java.util.concurrent.CancellationException -> L56
            A.N r10 = r0.f61p     // Catch: java.util.concurrent.CancellationException -> L56
            A.b r13 = r0.f62q     // Catch: java.util.concurrent.CancellationException -> L56
            A.M r12 = r0.f63r     // Catch: java.util.concurrent.CancellationException -> L56
            A.M r7 = r0.f64s     // Catch: java.util.concurrent.CancellationException -> L56
            A.L r11 = r0.f65t     // Catch: java.util.concurrent.CancellationException -> L56
            r0.f59m = r2     // Catch: java.util.concurrent.CancellationException -> L56
            r0.l = r5     // Catch: java.util.concurrent.CancellationException -> L56
            float r5 = A.J.f47a     // Catch: java.util.concurrent.CancellationException -> L56
            A7.y r8 = new A7.y     // Catch: java.util.concurrent.CancellationException -> L56
            r8.<init>()     // Catch: java.util.concurrent.CancellationException -> L56
            A.H r5 = new A.H     // Catch: java.util.concurrent.CancellationException -> L56
            r14 = 0
            r6 = r5
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: java.util.concurrent.CancellationException -> L56
            java.lang.Object r0 = C7.a.w(r15, r5, r0)     // Catch: java.util.concurrent.CancellationException -> L56
            if (r0 != r1) goto L50
            goto L51
        L50:
            r0 = r3
        L51:
            if (r0 != r1) goto L67
            return r1
        L54:
            r1 = r2
            goto L58
        L56:
            r0 = move-exception
            goto L54
        L58:
            U8.l r2 = r4.f123E
            if (r2 == 0) goto L61
            A.w r4 = A.C0045w.f292a
            r2.p(r4)
        L61:
            boolean r1 = S8.AbstractC0727y.s(r1)
            if (r1 == 0) goto L68
        L67:
            return r3
        L68:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: A.K.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
