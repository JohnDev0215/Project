package x;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.K, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2622K extends s7.i implements InterfaceC2785n {
    public A7.w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23421m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23422n;
    public final /* synthetic */ T.X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ L f23423p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2622K(T.X x8, L l, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = x8;
        this.f23423p = l;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2622K c2622k = new C2622K(this.o, this.f23423p, interfaceC2189c);
        c2622k.f23422n = obj;
        return c2622k;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C2622K) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0055 -> B:11:0x003a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0072 -> B:11:0x003a). Please report as a decompilation issue!!! */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f23421m
            r2 = 1
            r3 = 2
            if (r1 == 0) goto L2a
            if (r1 == r2) goto L1f
            if (r1 != r3) goto L17
            A7.w r1 = r7.l
            java.lang.Object r4 = r7.f23422n
            S8.w r4 = (S8.InterfaceC0725w) r4
            h8.AbstractC1386c.u(r8)
            r8 = r4
            goto L3a
        L17:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1f:
            A7.w r1 = r7.l
            java.lang.Object r4 = r7.f23422n
            S8.w r4 = (S8.InterfaceC0725w) r4
            h8.AbstractC1386c.u(r8)
            r8 = r4
            goto L50
        L2a:
            h8.AbstractC1386c.u(r8)
            java.lang.Object r8 = r7.f23422n
            S8.w r8 = (S8.InterfaceC0725w) r8
            A7.w r1 = new A7.w
            r1.<init>()
            r4 = 1065353216(0x3f800000, float:1.0)
            r1.l = r4
        L3a:
            A.p r4 = new A.p
            T.X r5 = r7.o
            x.L r6 = r7.f23423p
            r4.<init>(r5, r6, r1, r8)
            r7.f23422n = r8
            r7.l = r1
            r7.f23421m = r2
            java.lang.Object r4 = x.AbstractC2631e.s(r4, r7)
            if (r4 != r0) goto L50
            return r0
        L50:
            float r4 = r1.l
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L3a
            e0.w r4 = new e0.w
            r5 = 12
            r4.<init>(r8, r5)
            V8.h r4 = T.C0735d.W(r4)
            x.J r5 = new x.J
            r6 = 0
            r5.<init>(r3, r6)
            r7.f23422n = r8
            r7.l = r1
            r7.f23421m = r3
            java.lang.Object r4 = V8.AbstractC0828q.k(r4, r5, r7)
            if (r4 != r0) goto L3a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2622K.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
