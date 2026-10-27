package kotlinx.coroutines.flow;

import p.dv0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements dv0 {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // p.dv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(p.fv0 r7, p.c00 r8) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.AbstractFlow$collect$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = (kotlinx.coroutines.flow.AbstractFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = new kotlinx.coroutines.flow.AbstractFlow$collect$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            p.lq2 r3 = p.lq2.a
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r7 = (kotlinx.coroutines.flow.internal.SafeCollector) r7
            kotlin.a.d(r8)     // Catch: java.lang.Throwable -> L2d
            goto L58
        L2d:
            r8 = move-exception
            goto L62
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            kotlin.a.d(r8)
            kotlinx.coroutines.flow.internal.SafeCollector r8 = new kotlinx.coroutines.flow.internal.SafeCollector
            p.y10 r2 = r0.getContext()
            r8.<init>(r7, r2)
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L60
            r0.label = r4     // Catch: java.lang.Throwable -> L60
            r7 = r6
            p.k62 r7 = (p.k62) r7     // Catch: java.lang.Throwable -> L60
            p.fz0 r7 = r7.c     // Catch: java.lang.Throwable -> L60
            java.lang.Object r7 = r7.g(r8, r0)     // Catch: java.lang.Throwable -> L60
            if (r7 != r1) goto L53
            goto L54
        L53:
            r7 = r3
        L54:
            if (r7 != r1) goto L57
            return r1
        L57:
            r7 = r8
        L58:
            r7.j()
            return r3
        L5c:
            r5 = r8
            r8 = r7
            r7 = r5
            goto L62
        L60:
            r7 = move-exception
            goto L5c
        L62:
            r7.j()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.a.c(p.fv0, p.c00):java.lang.Object");
    }
}
