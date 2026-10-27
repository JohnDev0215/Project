package O;

import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4996m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4997n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = interfaceC2782k;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        u uVar = new u(this.o, interfaceC2189c);
        uVar.f4997n = obj;
        return uVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((u) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002b -> B:12:0x002e). Please report as a decompilation issue!!! */
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
    public final java.lang.Object invokeSuspend(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r4.f4996m
            r2 = 1
            if (r1 == 0) goto L19
            if (r1 != r2) goto L11
            java.lang.Object r1 = r4.f4997n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r5)
            goto L2e
        L11:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L19:
            h8.AbstractC1386c.u(r5)
            java.lang.Object r5 = r4.f4997n
            y0.z r5 = (y0.z) r5
            r1 = r5
        L21:
            y0.i r5 = y0.EnumC2731i.l
            r4.f4997n = r1
            r4.f4996m = r2
            java.lang.Object r5 = r1.a(r5, r4)
            if (r5 != r0) goto L2e
            return r0
        L2e:
            y0.h r5 = (y0.C2730h) r5
            boolean r5 = com.bumptech.glide.d.G(r5)
            r5 = r5 ^ r2
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            z7.k r3 = r4.o
            r3.b(r5)
            goto L21
        */
        throw new UnsupportedOperationException("Method not decompiled: O.u.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
