package androidx.work.impl.constraints;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.ky2;
import p.lq2;
import p.rp1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1", f = "WorkConstraintsTracker.kt", l = {54}, m = "invokeSuspend")
final class WorkConstraintsTrackerKt$listen$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ rp1 $listener;
    final /* synthetic */ ky2 $spec;
    final /* synthetic */ a $this_listen;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerKt$listen$1(a aVar, ky2 ky2Var, rp1 rp1Var, c00 c00Var) {
        super(2, c00Var);
        this.$this_listen = aVar;
        this.$spec = ky2Var;
        this.$listener = rp1Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new WorkConstraintsTrackerKt$listen$1(this.$this_listen, this.$spec, this.$listener, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((WorkConstraintsTrackerKt$listen$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0096  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L16
            if (r1 != r2) goto Le
            kotlin.a.d(r7)
            goto Lae
        Le:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L16:
            kotlin.a.d(r7)
            androidx.work.impl.constraints.a r7 = r6.$this_listen
            p.ky2 r1 = r6.$spec
            r7.getClass()
            java.lang.String r3 = "spec"
            p.v03.j(r1, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r7 = r7.a
            java.util.Iterator r7 = r7.iterator()
        L30:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L47
            java.lang.Object r4 = r7.next()
            r5 = r4
            androidx.work.impl.constraints.controllers.a r5 = (androidx.work.impl.constraints.controllers.a) r5
            boolean r5 = r5.b(r1)
            if (r5 == 0) goto L30
            r3.add(r4)
            goto L30
        L47:
            java.util.ArrayList r7 = new java.util.ArrayList
            int r1 = p.lu.W(r3)
            r7.<init>(r1)
            java.util.Iterator r1 = r3.iterator()
        L54:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L68
            java.lang.Object r3 = r1.next()
            androidx.work.impl.constraints.controllers.a r3 = (androidx.work.impl.constraints.controllers.a) r3
            kotlinx.coroutines.flow.b r3 = r3.d()
            r7.add(r3)
            goto L54
        L68:
            java.util.List r7 = p.ou.n0(r7)
            r1 = 0
            p.dv0[] r1 = new p.dv0[r1]
            java.lang.Object[] r7 = r7.toArray(r1)
            p.dv0[] r7 = (p.dv0[]) r7
            p.ny0 r1 = new p.ny0
            r3 = 3
            r1.<init>(r3, r7)
            p.ry0 r7 = kotlinx.coroutines.flow.e.a
            boolean r7 = r1 instanceof p.ug2
            if (r7 == 0) goto L82
            goto L9c
        L82:
            p.ry0 r7 = kotlinx.coroutines.flow.e.a
            p.fz0 r3 = kotlinx.coroutines.flow.e.b
            boolean r4 = r1 instanceof p.ra0
            if (r4 == 0) goto L96
            r4 = r1
            p.ra0 r4 = (p.ra0) r4
            p.ry0 r5 = r4.e
            if (r5 != r7) goto L96
            p.fz0 r7 = r4.k
            if (r7 != r3) goto L96
            goto L9c
        L96:
            p.ra0 r7 = new p.ra0
            r7.<init>(r1)
            r1 = r7
        L9c:
            p.gx2 r7 = new p.gx2
            p.rp1 r3 = r6.$listener
            p.ky2 r4 = r6.$spec
            r7.<init>(r3, r4)
            r6.label = r2
            java.lang.Object r7 = r1.c(r7, r6)
            if (r7 != r0) goto Lae
            return r0
        Lae:
            p.lq2 r7 = p.lq2.a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1.i(java.lang.Object):java.lang.Object");
    }
}
