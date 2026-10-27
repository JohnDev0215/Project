package A;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class U0 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f118n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ y0.q f119p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U0(y0.q qVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f119p = qVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        U0 u02 = new U0(this.f119p, interfaceC2189c);
        u02.o = obj;
        return u02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((U0) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003d -> B:12:0x0040). Please report as a decompilation issue!!! */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f118n
            r2 = 1
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            long r3 = r7.f117m
            java.lang.Object r1 = r7.o
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r8)
            goto L40
        L13:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1b:
            h8.AbstractC1386c.u(r8)
            java.lang.Object r8 = r7.o
            y0.z r8 = (y0.z) r8
            y0.q r1 = r7.f119p
            long r3 = r1.f24078b
            F0.W0 r1 = r8.c()
            r1.getClass()
            r5 = 40
            long r5 = r5 + r3
            r1 = r8
            r3 = r5
        L32:
            r7.o = r1
            r7.f117m = r3
            r7.f118n = r2
            r8 = 3
            java.lang.Object r8 = A.r1.c(r1, r7, r8)
            if (r8 != r0) goto L40
            return r0
        L40:
            y0.q r8 = (y0.q) r8
            long r5 = r8.f24078b
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 < 0) goto L32
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: A.U0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
