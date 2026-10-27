package kotlinx.coroutines.flow;

import g5.r;
import h5.c0;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.l;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Transform.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__TransformKt {
    public static final <T> Flow<T> filter(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return new FlowKt__TransformKt$filter$$inlined$unsafeTransform$1(flow, pVar);
    }

    public static final /* synthetic */ <R> Flow<R> filterIsInstance(Flow<?> flow) {
        l.j();
        return new FlowKt__TransformKt$filterIsInstance$$inlined$filter$1(flow);
    }

    public static final <T> Flow<T> filterNot(Flow<? extends T> flow, p<? super T, ? super d<? super Boolean>, ? extends Object> pVar) {
        return new FlowKt__TransformKt$filterNot$$inlined$unsafeTransform$1(flow, pVar);
    }

    public static final <T> Flow<T> filterNotNull(final Flow<? extends T> flow) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(final FlowCollector flowCollector, d dVar) {
                Object objCollect = flow.collect(new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj, d dVar2) {
                        FlowCollector flowCollector2 = flowCollector;
                        if (obj == null) {
                            return r.f6563a;
                        }
                        Object objEmit = flowCollector2.emit(obj, dVar2);
                        return objEmit == l5.d.d() ? objEmit : r.f6563a;
                    }
                }, dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        };
    }

    public static final <T, R> Flow<R> map(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return new FlowKt__TransformKt$map$$inlined$unsafeTransform$1(flow, pVar);
    }

    public static final <T, R> Flow<R> mapNotNull(Flow<? extends T> flow, p<? super T, ? super d<? super R>, ? extends Object> pVar) {
        return new FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1(flow, pVar);
    }

    public static final <T> Flow<T> onEach(final Flow<? extends T> flow, final p<? super T, ? super d<? super r>, ? extends Object> pVar) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            public static final class AnonymousClass2 implements FlowCollector<T> {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 this$0;

                /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1, reason: invalid class name */
                @f(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {134, 135}, m = "emit")
                public static final class AnonymousClass1 extends kotlin.coroutines.jvm.internal.d {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    Object L$5;
                    Object L$6;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d dVar) {
                        super(dVar);
                    }

                    @Override // kotlin.coroutines.jvm.internal.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.this$0 = flowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r10, k5.d r11) throws java.lang.Throwable {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = l5.b.d()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L5e
                        if (r2 == r4) goto L44
                        if (r2 != r3) goto L3c
                        java.lang.Object r10 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        java.lang.Object r10 = r0.L$4
                        k5.d r10 = (k5.d) r10
                        java.lang.Object r10 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r10 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r10
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2 r10 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2) r10
                        g5.m.b(r11)
                        goto La4
                    L3c:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L44:
                        java.lang.Object r10 = r0.L$6
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        java.lang.Object r2 = r0.L$5
                        java.lang.Object r4 = r0.L$4
                        k5.d r4 = (k5.d) r4
                        java.lang.Object r5 = r0.L$3
                        java.lang.Object r6 = r0.L$2
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r6 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r6
                        java.lang.Object r7 = r0.L$1
                        java.lang.Object r8 = r0.L$0
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2 r8 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2) r8
                        g5.m.b(r11)
                        goto L8d
                    L5e:
                        g5.m.b(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.$this_unsafeFlow$inlined
                        kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 r2 = r9.this$0
                        r5.p r2 = r2
                        r0.L$0 = r9
                        r0.L$1 = r10
                        r0.L$2 = r0
                        r0.L$3 = r10
                        r0.L$4 = r0
                        r0.L$5 = r10
                        r0.L$6 = r11
                        r0.label = r4
                        r4 = 6
                        kotlin.jvm.internal.k.a(r4)
                        java.lang.Object r2 = r2.invoke(r10, r0)
                        r4 = 7
                        kotlin.jvm.internal.k.a(r4)
                        if (r2 != r1) goto L86
                        return r1
                    L86:
                        r8 = r9
                        r2 = r10
                        r5 = r2
                        r7 = r5
                        r10 = r11
                        r4 = r0
                        r6 = r4
                    L8d:
                        r0.L$0 = r8
                        r0.L$1 = r7
                        r0.L$2 = r6
                        r0.L$3 = r5
                        r0.L$4 = r4
                        r0.L$5 = r2
                        r0.L$6 = r10
                        r0.label = r3
                        java.lang.Object r10 = r10.emit(r2, r0)
                        if (r10 != r1) goto La4
                        return r1
                    La4:
                        g5.r r10 = g5.r.f6563a
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.emit(java.lang.Object, k5.d):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, this), dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        };
    }

    public static final <T> Flow<T> runningReduce(Flow<? extends T> flow, q<? super T, ? super T, ? super d<? super T>, ? extends Object> qVar) {
        return new FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(flow, qVar);
    }

    public static final <T, R> Flow<R> scan(Flow<? extends T> flow, R r7, q<? super R, ? super T, ? super d<? super R>, ? extends Object> qVar) {
        return new FlowKt__TransformKt$scan$$inlined$unsafeFlow$1(flow, r7, qVar);
    }

    public static final <T> Flow<c0<T>> withIndex(final Flow<? extends T> flow) {
        return new Flow<c0<? extends T>>() { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(final FlowCollector flowCollector, d dVar) {
                final kotlin.jvm.internal.p pVar = new kotlin.jvm.internal.p();
                pVar.f7506e = 0;
                Object objCollect = flow.collect(new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1$lambda$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj, d dVar2) {
                        FlowCollector flowCollector2 = flowCollector;
                        kotlin.jvm.internal.p pVar2 = pVar;
                        int i7 = pVar2.f7506e;
                        pVar2.f7506e = i7 + 1;
                        if (i7 < 0) {
                            throw new ArithmeticException("Index overflow has happened");
                        }
                        Object objEmit = flowCollector2.emit(new c0(i7, obj), dVar2);
                        return objEmit == l5.d.d() ? objEmit : r.f6563a;
                    }
                }, dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        };
    }
}
