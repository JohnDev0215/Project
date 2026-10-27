package kotlinx.coroutines;

import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import p.c00;
import p.e60;
import p.fz0;
import p.kb2;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", l = {956, 958}, m = "invokeSuspend")
final class JobSupport$children$1 extends RestrictedSuspendLambda implements fz0 {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ g this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobSupport$children$1(c00 c00Var, g gVar) {
        super(c00Var);
        this.this$0 = gVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(c00Var, this.this$0);
        jobSupport$children$1.L$0 = obj;
        return jobSupport$children$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((JobSupport$children$1) a((kb2) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006e -> B:26:0x0086). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0083 -> B:26:0x0086). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2b
            if (r1 == r4) goto L27
            if (r1 != r3) goto L1f
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.internal.a r1 = (kotlinx.coroutines.internal.a) r1
            java.lang.Object r4 = r9.L$1
            p.pn1 r4 = (p.pn1) r4
            java.lang.Object r5 = r9.L$0
            p.kb2 r5 = (p.kb2) r5
            kotlin.a.d(r10)
            r10 = r9
            r6 = r0
            goto L86
        L1f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L27:
            kotlin.a.d(r10)
            goto L8b
        L2b:
            kotlin.a.d(r10)
            java.lang.Object r10 = r9.L$0
            p.kb2 r10 = (p.kb2) r10
            kotlinx.coroutines.g r1 = r9.this$0
            java.lang.Object r1 = r1.I()
            boolean r5 = r1 instanceof p.gs
            if (r5 == 0) goto L49
            p.gs r1 = (p.gs) r1
            p.js r1 = r1.m
            r9.label = r4
            r10.e = r1
            r10.c = r2
            r10.k = r9
            return r0
        L49:
            boolean r4 = r1 instanceof p.e41
            if (r4 == 0) goto L8b
            p.e41 r1 = (p.e41) r1
            p.pn1 r1 = r1.c()
            if (r1 == 0) goto L8b
            java.lang.Object r4 = r1.h()
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            p.v03.g(r4, r5)
            kotlinx.coroutines.internal.a r4 = (kotlinx.coroutines.internal.a) r4
            r5 = r10
            r6 = r0
            r10 = r9
            r8 = r4
            r4 = r1
            r1 = r8
        L66:
            boolean r7 = p.v03.b(r1, r4)
            if (r7 != 0) goto L8b
            boolean r7 = r1 instanceof p.gs
            if (r7 == 0) goto L86
            r7 = r1
            p.gs r7 = (p.gs) r7
            r10.L$0 = r5
            r10.L$1 = r4
            r10.L$2 = r1
            r10.label = r3
            p.js r7 = r7.m
            r5.e = r7
            r5.c = r2
            r5.k = r10
            if (r0 != r6) goto L86
            return r6
        L86:
            kotlinx.coroutines.internal.a r1 = r1.i()
            goto L66
        L8b:
            p.lq2 r10 = p.lq2.a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.i(java.lang.Object):java.lang.Object");
    }
}
