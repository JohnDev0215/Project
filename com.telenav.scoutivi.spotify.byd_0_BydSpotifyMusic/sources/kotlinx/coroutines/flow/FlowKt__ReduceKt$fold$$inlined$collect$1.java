package kotlinx.coroutines.flow;

import kotlin.coroutines.jvm.internal.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.r;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__ReduceKt$fold$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ r $accumulator$inlined;
    final /* synthetic */ q $operation$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1$1, reason: invalid class name */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1", f = "Reduce.kt", l = {133}, m = "emit")
    public static final class AnonymousClass1 extends d {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(k5.d dVar) {
            super(dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ReduceKt$fold$$inlined$collect$1.this.emit(null, this);
        }
    }

    public FlowKt__ReduceKt$fold$$inlined$collect$1(r rVar, q qVar) {
        this.$accumulator$inlined = rVar;
        this.$operation$inlined = qVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object emit(java.lang.Object r7, k5.d r8) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = l5.b.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3d
            if (r2 != r3) goto L35
            java.lang.Object r7 = r0.L$4
            kotlin.jvm.internal.r r7 = (kotlin.jvm.internal.r) r7
            java.lang.Object r1 = r0.L$2
            k5.d r1 = (k5.d) r1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1) r0
            g5.m.b(r8)
            goto L5c
        L35:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3d:
            g5.m.b(r8)
            kotlin.jvm.internal.r r8 = r6.$accumulator$inlined
            r5.q r2 = r6.$operation$inlined
            T r4 = r8.f7508e
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r0
            r0.L$3 = r7
            r0.L$4 = r8
            r0.label = r3
            java.lang.Object r7 = r2.invoke(r4, r7, r0)
            if (r7 != r1) goto L59
            return r1
        L59:
            r5 = r8
            r8 = r7
            r7 = r5
        L5c:
            r7.f7508e = r8
            g5.r r7 = g5.r.f6563a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1.emit(java.lang.Object, k5.d):java.lang.Object");
    }

    public Object emit$$forInline(Object obj, k5.d dVar) {
        k.a(4);
        new AnonymousClass1(dVar);
        k.a(5);
        r rVar = this.$accumulator$inlined;
        rVar.f7508e = (T) this.$operation$inlined.invoke(rVar.f7508e, obj, dVar);
        return g5.r.f6563a;
    }
}
