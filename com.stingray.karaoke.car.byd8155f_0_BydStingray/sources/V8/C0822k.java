package V8;

import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0822k extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9630m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f9631n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0822k(long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9631n = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0822k c0822k = new C0822k(this.f9631n, interfaceC2189c);
        c0822k.f9630m = obj;
        return c0822k;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((C0822k) create((U8.x) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x005a -> B:15:0x003d). Please report as a decompilation issue!!! */
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
            int r1 = r7.l
            long r2 = r7.f9631n
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L2a
            if (r1 == r6) goto L22
            if (r1 == r5) goto L1a
            if (r1 != r4) goto L12
            goto L22
        L12:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1a:
            java.lang.Object r1 = r7.f9630m
            U8.x r1 = (U8.x) r1
            h8.AbstractC1386c.u(r8)
            goto L52
        L22:
            java.lang.Object r1 = r7.f9630m
            U8.x r1 = (U8.x) r1
            h8.AbstractC1386c.u(r8)
            goto L3d
        L2a:
            h8.AbstractC1386c.u(r8)
            java.lang.Object r8 = r7.f9630m
            r1 = r8
            U8.x r1 = (U8.x) r1
            r7.f9630m = r1
            r7.l = r6
            java.lang.Object r8 = S8.AbstractC0727y.i(r2, r7)
            if (r8 != r0) goto L3d
            return r0
        L3d:
            r8 = r1
            U8.w r8 = (U8.w) r8
            r8.getClass()
            m7.q r6 = m7.q.f19267a
            r7.f9630m = r1
            r7.l = r5
            U8.l r8 = r8.o
            java.lang.Object r8 = r8.a(r6, r7)
            if (r8 != r0) goto L52
            return r0
        L52:
            r7.f9630m = r1
            r7.l = r4
            java.lang.Object r8 = S8.AbstractC0727y.i(r2, r7)
            if (r8 != r0) goto L3d
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.C0822k.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
