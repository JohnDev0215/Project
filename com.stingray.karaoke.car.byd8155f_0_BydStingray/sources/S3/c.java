package S3;

import S8.InterfaceC0725w;
import S8.W;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f7883m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ W f7884n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f7885p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ h f7886q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(l lVar, W w10, int i7, int i10, h hVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7883m = lVar;
        this.f7884n = w10;
        this.o = i7;
        this.f7885p = i10;
        this.f7886q = hVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new c(this.f7883m, this.f7884n, this.o, this.f7885p, this.f7886q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((c) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r5.l
            r2 = 1
            if (r1 == 0) goto L15
            if (r1 != r2) goto Ld
            h8.AbstractC1386c.u(r6)
            goto L5d
        Ld:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L15:
            h8.AbstractC1386c.u(r6)
        L18:
            int[] r6 = S3.b.f7882a
            S3.l r1 = r5.f7883m
            int r1 = r1.ordinal()
            r6 = r6[r1]
            int r1 = r5.o
            if (r6 != r2) goto L31
            S8.W r6 = r5.f7884n
            boolean r6 = r6.b()
            if (r6 == 0) goto L2f
            goto L31
        L2f:
            int r1 = r5.f7885p
        L31:
            r5.l = r2
            S3.h r6 = r5.f7886q
            r6.getClass()
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r3) goto L48
            S3.e r3 = new S3.e
            r4 = 0
            r3.<init>(r6, r1, r4)
            java.lang.Object r6 = x.AbstractC2631e.s(r3, r5)
            goto L5a
        L48:
            S3.e r3 = new S3.e
            r4 = 1
            r3.<init>(r6, r1, r4)
            q7.h r6 = r5.getContext()
            T.T r6 = T.C0735d.I(r6)
            java.lang.Object r6 = r6.o(r3, r5)
        L5a:
            if (r6 != r0) goto L5d
            return r0
        L5d:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L18
            m7.q r5 = m7.q.f19267a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: S3.c.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
