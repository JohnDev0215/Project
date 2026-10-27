package y;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ h0 f23904m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(h0 h0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f23904m = h0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g0(this.f23904m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((g0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0030 -> B:11:0x0021). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0048 -> B:21:0x004b). Please report as a decompilation issue!!! */
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
            r2 = 2
            r3 = 1
            y.h0 r4 = r7.f23904m
            if (r1 == 0) goto L1e
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            h8.AbstractC1386c.u(r8)
            goto L4b
        L12:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1a:
            h8.AbstractC1386c.u(r8)
            goto L2e
        L1e:
            h8.AbstractC1386c.u(r8)
        L21:
            U8.l r8 = r4.f23919P
            if (r8 == 0) goto L2e
            r7.l = r3
            java.lang.Object r8 = r8.c(r7)
            if (r8 != r0) goto L2e
            return r0
        L2e:
            w8.b r8 = r4.f23914K
            if (r8 == 0) goto L21
            y.r r8 = y.r.f23991p
            r7.l = r2
            q7.h r1 = r7.getContext()
            T.T r1 = T.C0735d.I(r1)
            F8.u r5 = new F8.u
            r6 = 1
            r5.<init>(r6, r8)
            java.lang.Object r8 = r1.o(r5, r7)
            if (r8 != r0) goto L4b
            return r0
        L4b:
            w8.b r8 = r4.f23914K
            if (r8 == 0) goto L21
            java.lang.Object r8 = r8.f23345m
            android.widget.Magnifier r8 = (android.widget.Magnifier) r8
            r8.update()
            goto L21
        */
        throw new UnsupportedOperationException("Method not decompiled: y.g0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
