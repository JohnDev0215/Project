package kotlinx.coroutines.flow;

import g5.r;
import kotlin.coroutines.jvm.internal.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 implements Flow<Object> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: Collect.kt */
    public static final class AnonymousClass2 implements FlowCollector<Object> {
        final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
        final /* synthetic */ FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 this$0;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1, reason: invalid class name */
        @f(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2", f = "Transform.kt", l = {135}, m = "emit")
        public static final class AnonymousClass1 extends d {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            Object L$6;
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

        public AnonymousClass2(FlowCollector flowCollector, FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 flowKt__TransformKt$filterIsInstance$$inlined$filter$1) {
            this.$this_unsafeFlow$inlined = flowCollector;
            this.this$0 = flowKt__TransformKt$filterIsInstance$$inlined$filter$1;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object emit(java.lang.Object r6, k5.d r7) throws java.lang.Throwable {
            /*
                r5 = this;
                boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r7
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = l5.b.d()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L41
                if (r2 != r3) goto L39
                java.lang.Object r6 = r0.L$6
                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                java.lang.Object r6 = r0.L$4
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r6 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r6
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r6 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r6
                java.lang.Object r6 = r0.L$0
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2 r6 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2) r6
                g5.m.b(r7)
                goto L6f
            L39:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L41:
                g5.m.b(r7)
                kotlinx.coroutines.flow.FlowCollector r7 = r5.$this_unsafeFlow$inlined
                r2 = 3
                java.lang.String r4 = "R"
                kotlin.jvm.internal.l.k(r2, r4)
                boolean r2 = r6 instanceof java.lang.Object
                java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.b.a(r2)
                boolean r2 = r2.booleanValue()
                if (r2 == 0) goto L72
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r0
                r0.L$3 = r6
                r0.L$4 = r0
                r0.L$5 = r6
                r0.L$6 = r7
                r0.label = r3
                java.lang.Object r6 = r7.emit(r6, r0)
                if (r6 != r1) goto L6f
                return r1
            L6f:
                g5.r r6 = g5.r.f6563a
                goto L74
            L72:
                g5.r r6 = g5.r.f6563a
            L74:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, k5.d):java.lang.Object");
        }

        public Object emit$$forInline(Object obj, k5.d dVar) {
            k.a(4);
            new AnonymousClass1(dVar);
            k.a(5);
            FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
            l.k(3, "R");
            if (!(obj instanceof Object)) {
                return r.f6563a;
            }
            k.a(0);
            Object objEmit = flowCollector.emit(obj, dVar);
            k.a(2);
            k.a(1);
            return objEmit;
        }
    }

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$1(Flow flow) {
        this.$this_unsafeTransform$inlined = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super Object> flowCollector, k5.d dVar) {
        Flow flow = this.$this_unsafeTransform$inlined;
        l.j();
        Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    public Object collect$$forInline(FlowCollector flowCollector, k5.d dVar) {
        k.a(4);
        new d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.this.collect(null, this);
            }
        };
        k.a(5);
        Flow flow = this.$this_unsafeTransform$inlined;
        l.j();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(flowCollector, this);
        k.a(0);
        flow.collect(anonymousClass2, dVar);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
