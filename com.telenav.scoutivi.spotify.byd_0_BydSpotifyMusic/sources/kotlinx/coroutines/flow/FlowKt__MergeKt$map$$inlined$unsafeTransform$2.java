package kotlinx.coroutines.flow;

import g5.r;
import kotlin.coroutines.jvm.internal.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.k;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__MergeKt$map$$inlined$unsafeTransform$2<R> implements Flow<R> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ p $transform$inlined$1;

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    public static final class AnonymousClass2<T> implements FlowCollector<T> {
        final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
        final /* synthetic */ FlowKt__MergeKt$map$$inlined$unsafeTransform$2 this$0;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1, reason: invalid class name */
        @f(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2", f = "Transform.kt", l = {134, 134}, m = "emit")
        public static final class AnonymousClass1 extends d {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
            Object L$7;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(k5.d dVar) {
                super(dVar);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, FlowKt__MergeKt$map$$inlined$unsafeTransform$2 flowKt__MergeKt$map$$inlined$unsafeTransform$2) {
            this.$this_unsafeFlow$inlined = flowCollector;
            this.this$0 = flowKt__MergeKt$map$$inlined$unsafeTransform$2;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object emit(java.lang.Object r11, k5.d r12) throws java.lang.Throwable {
            /*
                r10 = this;
                boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r12
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1
                r0.<init>(r12)
            L18:
                java.lang.Object r12 = r0.result
                java.lang.Object r1 = l5.b.d()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L63
                if (r2 == r4) goto L45
                if (r2 != r3) goto L3d
                java.lang.Object r11 = r0.L$6
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                java.lang.Object r11 = r0.L$4
                k5.d r11 = (k5.d) r11
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2.AnonymousClass1) r11
                java.lang.Object r11 = r0.L$0
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2) r11
                g5.m.b(r12)
                goto La5
            L3d:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L45:
                java.lang.Object r11 = r0.L$7
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                java.lang.Object r2 = r0.L$6
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                java.lang.Object r4 = r0.L$5
                java.lang.Object r5 = r0.L$4
                k5.d r5 = (k5.d) r5
                java.lang.Object r6 = r0.L$3
                java.lang.Object r7 = r0.L$2
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2$1 r7 = (kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2.AnonymousClass1) r7
                java.lang.Object r8 = r0.L$1
                java.lang.Object r9 = r0.L$0
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2$2 r9 = (kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2) r9
                g5.m.b(r12)
                goto L8e
            L63:
                g5.m.b(r12)
                kotlinx.coroutines.flow.FlowCollector r12 = r10.$this_unsafeFlow$inlined
                kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2 r2 = r10.this$0
                r5.p r2 = r2.$transform$inlined$1
                r0.L$0 = r10
                r0.L$1 = r11
                r0.L$2 = r0
                r0.L$3 = r11
                r0.L$4 = r0
                r0.L$5 = r11
                r0.L$6 = r12
                r0.L$7 = r12
                r0.label = r4
                java.lang.Object r2 = r2.invoke(r11, r0)
                if (r2 != r1) goto L85
                return r1
            L85:
                r9 = r10
                r4 = r11
                r6 = r4
                r8 = r6
                r11 = r12
                r5 = r0
                r7 = r5
                r12 = r2
                r2 = r11
            L8e:
                r0.L$0 = r9
                r0.L$1 = r8
                r0.L$2 = r7
                r0.L$3 = r6
                r0.L$4 = r5
                r0.L$5 = r4
                r0.L$6 = r2
                r0.label = r3
                java.lang.Object r11 = r11.emit(r12, r0)
                if (r11 != r1) goto La5
                return r1
            La5:
                g5.r r11 = g5.r.f6563a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.AnonymousClass2.emit(java.lang.Object, k5.d):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Object emit$$forInline(Object obj, k5.d dVar) {
            k.a(4);
            new AnonymousClass1(dVar);
            k.a(5);
            FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
            Object objInvoke = this.this$0.$transform$inlined$1.invoke(obj, dVar);
            k.a(0);
            Object objEmit = flowCollector.emit(objInvoke, dVar);
            k.a(2);
            k.a(1);
            return objEmit;
        }
    }

    public FlowKt__MergeKt$map$$inlined$unsafeTransform$2(Flow flow, p pVar) {
        this.$this_unsafeTransform$inlined = flow;
        this.$transform$inlined$1 = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, k5.d dVar) {
        Object objCollect = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector, this), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    public Object collect$$forInline(FlowCollector flowCollector, k5.d dVar) {
        k.a(4);
        new d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__MergeKt$map$$inlined$unsafeTransform$2.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__MergeKt$map$$inlined$unsafeTransform$2.this.collect(null, this);
            }
        };
        k.a(5);
        Flow flow = this.$this_unsafeTransform$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(flowCollector, this);
        k.a(0);
        flow.collect(anonymousClass2, dVar);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
