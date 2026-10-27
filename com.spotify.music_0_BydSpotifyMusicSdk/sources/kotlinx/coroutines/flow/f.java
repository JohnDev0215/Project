package kotlinx.coroutines.flow;

import p.dv0;
import p.hz0;

/* JADX INFO: loaded from: classes.dex */
public final class f implements dv0 {
    public final /* synthetic */ dv0 c;
    public final /* synthetic */ hz0 e;

    public f(dv0 dv0Var, hz0 hz0Var) {
        this.c = dv0Var;
        this.e = hz0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // p.dv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(p.fv0 r9, p.c00 r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L52
            if (r2 == r5) goto L44
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r9 = (kotlinx.coroutines.flow.internal.SafeCollector) r9
            kotlin.a.d(r10)     // Catch: java.lang.Throwable -> L32
            goto L7e
        L32:
            r10 = move-exception
            goto L88
        L34:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3c:
            java.lang.Object r9 = r0.L$0
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            kotlin.a.d(r10)
            goto La2
        L44:
            java.lang.Object r9 = r0.L$1
            p.fv0 r9 = (p.fv0) r9
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.f r2 = (kotlinx.coroutines.flow.f) r2
            kotlin.a.d(r10)     // Catch: java.lang.Throwable -> L50
            goto L65
        L50:
            r9 = move-exception
            goto L8e
        L52:
            kotlin.a.d(r10)
            p.dv0 r10 = r8.c     // Catch: java.lang.Throwable -> L8c
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L8c
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L8c
            r0.label = r5     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r10 = r10.c(r9, r0)     // Catch: java.lang.Throwable -> L8c
            if (r10 != r1) goto L64
            return r1
        L64:
            r2 = r8
        L65:
            kotlinx.coroutines.flow.internal.SafeCollector r10 = new kotlinx.coroutines.flow.internal.SafeCollector
            p.y10 r4 = r0.getContext()
            r10.<init>(r9, r4)
            p.hz0 r9 = r2.e     // Catch: java.lang.Throwable -> L84
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L84
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L84
            r0.label = r3     // Catch: java.lang.Throwable -> L84
            java.lang.Object r9 = r9.e(r10, r6, r0)     // Catch: java.lang.Throwable -> L84
            if (r9 != r1) goto L7d
            return r1
        L7d:
            r9 = r10
        L7e:
            r9.j()
            p.lq2 r9 = p.lq2.a
            return r9
        L84:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L88:
            r9.j()
            throw r10
        L8c:
            r9 = move-exception
            r2 = r8
        L8e:
            p.pk2 r10 = new p.pk2
            r10.<init>(r9)
            p.hz0 r2 = r2.e
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r10 = kotlinx.coroutines.flow.d.a(r10, r2, r9, r0)
            if (r10 != r1) goto La2
            return r1
        La2:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.f.c(p.fv0, p.c00):java.lang.Object");
    }
}
