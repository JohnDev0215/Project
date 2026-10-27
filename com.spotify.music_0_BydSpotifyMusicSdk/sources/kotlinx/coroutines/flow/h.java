package kotlinx.coroutines.flow;

import p.c00;
import p.fv0;
import p.fz0;

/* JADX INFO: loaded from: classes.dex */
public final class h implements fv0 {
    public final fv0 c;
    public final fz0 e;

    public h(fv0 fv0Var, fz0 fz0Var) {
        this.c = fv0Var;
        this.e = fz0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.internal.SafeCollector] */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(p.c00 r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.SubscribedFlowCollector$onSubscription$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.SubscribedFlowCollector$onSubscription$1 r0 = (kotlinx.coroutines.flow.SubscribedFlowCollector$onSubscription$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.SubscribedFlowCollector$onSubscription$1 r0 = new kotlinx.coroutines.flow.SubscribedFlowCollector$onSubscription$1
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            p.lq2 r3 = p.lq2.a
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L42
            if (r2 == r5) goto L34
            if (r2 != r4) goto L2c
            kotlin.a.d(r8)
            goto L79
        L2c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L34:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.flow.internal.SafeCollector r2 = (kotlinx.coroutines.flow.internal.SafeCollector) r2
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.h r5 = (kotlinx.coroutines.flow.h) r5
            kotlin.a.d(r8)     // Catch: java.lang.Throwable -> L40
            goto L60
        L40:
            r8 = move-exception
            goto L7a
        L42:
            kotlin.a.d(r8)
            kotlinx.coroutines.flow.internal.SafeCollector r2 = new kotlinx.coroutines.flow.internal.SafeCollector
            p.y10 r8 = r0.getContext()
            p.fv0 r6 = r7.c
            r2.<init>(r6, r8)
            p.fz0 r8 = r7.e     // Catch: java.lang.Throwable -> L40
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L40
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L40
            r0.label = r5     // Catch: java.lang.Throwable -> L40
            java.lang.Object r8 = r8.g(r2, r0)     // Catch: java.lang.Throwable -> L40
            if (r8 != r1) goto L5f
            return r1
        L5f:
            r5 = r7
        L60:
            r2.j()
            p.fv0 r8 = r5.c
            boolean r2 = r8 instanceof kotlinx.coroutines.flow.h
            if (r2 == 0) goto L79
            kotlinx.coroutines.flow.h r8 = (kotlinx.coroutines.flow.h) r8
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r8 = r8.a(r0)
            if (r8 != r1) goto L79
            return r1
        L79:
            return r3
        L7a:
            r2.j()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.h.a(p.c00):java.lang.Object");
    }

    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        return this.c.b(obj, c00Var);
    }
}
