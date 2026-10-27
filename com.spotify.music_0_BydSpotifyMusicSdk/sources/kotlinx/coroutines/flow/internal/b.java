package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import p.ar;
import p.c00;
import p.dv0;
import p.ib2;
import p.lq2;
import p.sx1;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends a {
    public final dv0 l;

    public b(dv0 dv0Var, y10 y10Var, int i, BufferOverflow bufferOverflow) {
        super(y10Var, i, bufferOverflow);
        this.l = dv0Var;
    }

    @Override // kotlinx.coroutines.flow.internal.a
    public final Object b(sx1 sx1Var, c00 c00Var) {
        Object objC = ((ar) this).l.c(new ib2(sx1Var), c00Var);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        lq2 lq2Var = lq2.a;
        if (objC != coroutineSingletons) {
            objC = lq2Var;
        }
        return objC == coroutineSingletons ? objC : lq2Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    @Override // kotlinx.coroutines.flow.internal.a, p.dv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(p.fv0 r7, p.c00 r8) throws java.lang.Throwable {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            p.lq2 r1 = p.lq2.a
            int r2 = r6.e
            r3 = -3
            if (r2 != r3) goto L5e
            p.y10 r2 = r8.getContext()
            p.y10 r3 = r6.c
            p.y10 r3 = kotlinx.coroutines.a.b(r2, r3)
            boolean r4 = p.v03.b(r3, r2)
            if (r4 == 0) goto L2a
            r2 = r6
            p.ar r2 = (p.ar) r2
            p.dv0 r2 = r2.l
            java.lang.Object r7 = r2.c(r7, r8)
            if (r7 != r0) goto L25
            goto L26
        L25:
            r7 = r1
        L26:
            if (r7 != r0) goto L65
        L28:
            r1 = r7
            goto L65
        L2a:
            p.qo r4 = p.qo.l
            p.w10 r5 = r3.E0(r4)
            p.w10 r2 = r2.E0(r4)
            boolean r2 = p.v03.b(r5, r2)
            if (r2 == 0) goto L5e
            p.y10 r2 = r8.getContext()
            boolean r4 = r7 instanceof p.ib2
            if (r4 == 0) goto L43
            goto L49
        L43:
            kotlinx.coroutines.flow.internal.f r4 = new kotlinx.coroutines.flow.internal.f
            r4.<init>(r7, r2)
            r7 = r4
        L49:
            kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2 r2 = new kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2
            r4 = 0
            r2.<init>(r6, r4)
            java.lang.Object r4 = kotlinx.coroutines.internal.c.b(r3)
            java.lang.Object r7 = p.bk0.W(r3, r7, r4, r2, r8)
            if (r7 != r0) goto L5a
            goto L5b
        L5a:
            r7 = r1
        L5b:
            if (r7 != r0) goto L65
            goto L28
        L5e:
            java.lang.Object r7 = super.c(r7, r8)
            if (r7 != r0) goto L65
            goto L28
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.b.c(p.fv0, p.c00):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.internal.a
    public final String toString() {
        return this.l + " -> " + super.toString();
    }
}
