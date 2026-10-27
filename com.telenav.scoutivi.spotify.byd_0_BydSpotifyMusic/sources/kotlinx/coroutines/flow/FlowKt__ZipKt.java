package kotlinx.coroutines.flow;

import g5.r;
import h5.x;
import java.util.Objects;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.flow.internal.CombineKt;
import r5.a;
import r5.p;
import r5.q;
import r5.s;
import r5.t;
import r5.u;

/* JADX INFO: compiled from: Zip.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__ZipKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6, reason: invalid class name */
    /* JADX INFO: compiled from: Zip.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6", f = "Zip.kt", l = {251}, m = "invokeSuspend")
    public static final class AnonymousClass6<R> extends l implements p<FlowCollector<? super R>, d<? super r>, Object> {
        final /* synthetic */ Flow[] $flows;
        final /* synthetic */ q $transform;
        Object L$0;
        int label;
        private FlowCollector p$;

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$1, reason: invalid class name */
        /* JADX INFO: compiled from: Zip.kt */
        public static final class AnonymousClass1<T> extends m implements a<T[]> {
            public AnonymousClass1() {
                super(0);
            }

            @Override // r5.a
            public final T[] invoke() {
                int length = AnonymousClass6.this.$flows.length;
                kotlin.jvm.internal.l.k(0, "T?");
                return (T[]) new Object[length];
            }
        }

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$2, reason: invalid class name */
        /* JADX INFO: compiled from: Zip.kt */
        @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$2", f = "Zip.kt", l = {251}, m = "invokeSuspend")
        public static final class AnonymousClass2<T> extends l implements q<FlowCollector<? super R>, T[], d<? super r>, Object> {
            Object L$0;
            Object L$1;
            int label;
            private FlowCollector p$;
            private Object[] p$0;

            public AnonymousClass2(d dVar) {
                super(3, dVar);
            }

            public final d<r> create(FlowCollector<? super R> flowCollector, T[] tArr, d<? super r> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
                anonymousClass2.p$ = flowCollector;
                anonymousClass2.p$0 = tArr;
                return anonymousClass2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // r5.q
            public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
                return ((AnonymousClass2) create((FlowCollector) obj, (Object[]) obj2, dVar)).invokeSuspend(r.f6563a);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objD = l5.d.d();
                int i7 = this.label;
                if (i7 == 0) {
                    g5.m.b(obj);
                    FlowCollector flowCollector = this.p$;
                    Object[] objArr = this.p$0;
                    q qVar = AnonymousClass6.this.$transform;
                    this.L$0 = flowCollector;
                    this.L$1 = objArr;
                    this.label = 1;
                    if (qVar.invoke(flowCollector, objArr, this) == objD) {
                        return objD;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    g5.m.b(obj);
                }
                return r.f6563a;
            }

            public final Object invokeSuspend$$forInline(Object obj) {
                AnonymousClass6.this.$transform.invoke(this.p$, this.p$0, this);
                return r.f6563a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(Flow[] flowArr, q qVar, d dVar) {
            super(2, dVar);
            this.$flows = flowArr;
            this.$transform = qVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$flows, this.$transform, dVar);
            anonymousClass6.p$ = (FlowCollector) obj;
            return anonymousClass6;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((AnonymousClass6) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                g5.m.b(obj);
                FlowCollector flowCollector = this.p$;
                Flow[] flowArr = this.$flows;
                kotlin.jvm.internal.l.j();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = flowCollector;
                this.label = 1;
                if (CombineKt.combineInternal(flowCollector, flowArr, anonymousClass1, anonymousClass2, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                g5.m.b(obj);
            }
            return r.f6563a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector flowCollector = this.p$;
            Flow[] flowArr = this.$flows;
            kotlin.jvm.internal.l.j();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            k.a(0);
            CombineKt.combineInternal(flowCollector, flowArr, anonymousClass1, anonymousClass2, this);
            k.a(2);
            k.a(1);
            return r.f6563a;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$7, reason: invalid class name */
    /* JADX INFO: compiled from: Zip.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$7", f = "Zip.kt", l = {308}, m = "invokeSuspend")
    public static final class AnonymousClass7<R> extends l implements p<FlowCollector<? super R>, d<? super r>, Object> {
        final /* synthetic */ Flow[] $flowArray;
        final /* synthetic */ q $transform;
        Object L$0;
        int label;
        private FlowCollector p$;

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$7$1, reason: invalid class name */
        /* JADX INFO: compiled from: Zip.kt */
        public static final class AnonymousClass1<T> extends m implements a<T[]> {
            public AnonymousClass1() {
                super(0);
            }

            @Override // r5.a
            public final T[] invoke() {
                int length = AnonymousClass7.this.$flowArray.length;
                kotlin.jvm.internal.l.k(0, "T?");
                return (T[]) new Object[length];
            }
        }

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$7$2, reason: invalid class name */
        /* JADX INFO: compiled from: Zip.kt */
        @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$7$2", f = "Zip.kt", l = {308}, m = "invokeSuspend")
        public static final class AnonymousClass2<T> extends l implements q<FlowCollector<? super R>, T[], d<? super r>, Object> {
            Object L$0;
            Object L$1;
            int label;
            private FlowCollector p$;
            private Object[] p$0;

            public AnonymousClass2(d dVar) {
                super(3, dVar);
            }

            public final d<r> create(FlowCollector<? super R> flowCollector, T[] tArr, d<? super r> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
                anonymousClass2.p$ = flowCollector;
                anonymousClass2.p$0 = tArr;
                return anonymousClass2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // r5.q
            public final Object invoke(Object obj, Object obj2, d<? super r> dVar) {
                return ((AnonymousClass2) create((FlowCollector) obj, (Object[]) obj2, dVar)).invokeSuspend(r.f6563a);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objD = l5.d.d();
                int i7 = this.label;
                if (i7 == 0) {
                    g5.m.b(obj);
                    FlowCollector flowCollector = this.p$;
                    Object[] objArr = this.p$0;
                    q qVar = AnonymousClass7.this.$transform;
                    this.L$0 = flowCollector;
                    this.L$1 = objArr;
                    this.label = 1;
                    if (qVar.invoke(flowCollector, objArr, this) == objD) {
                        return objD;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    g5.m.b(obj);
                }
                return r.f6563a;
            }

            public final Object invokeSuspend$$forInline(Object obj) {
                AnonymousClass7.this.$transform.invoke(this.p$, this.p$0, this);
                return r.f6563a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(Flow[] flowArr, q qVar, d dVar) {
            super(2, dVar);
            this.$flowArray = flowArr;
            this.$transform = qVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$flowArray, this.$transform, dVar);
            anonymousClass7.p$ = (FlowCollector) obj;
            return anonymousClass7;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((AnonymousClass7) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                g5.m.b(obj);
                FlowCollector flowCollector = this.p$;
                Flow[] flowArr = this.$flowArray;
                kotlin.jvm.internal.l.j();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                this.L$0 = flowCollector;
                this.label = 1;
                if (CombineKt.combineInternal(flowCollector, flowArr, anonymousClass1, anonymousClass2, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                g5.m.b(obj);
            }
            return r.f6563a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            FlowCollector flowCollector = this.p$;
            Flow[] flowArr = this.$flowArray;
            kotlin.jvm.internal.l.j();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            k.a(0);
            CombineKt.combineInternal(flowCollector, flowArr, anonymousClass1, anonymousClass2, this);
            k.a(2);
            k.a(1);
            return r.f6563a;
        }
    }

    /* JADX WARN: Failed to check method usage
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.MethodNode.getTopParentClass()" because "m" is null
    	at jadx.core.codegen.ClassGen.lambda$skipMethod$4(ClassGen.java:366)
    	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    	at jadx.core.codegen.ClassGen.skipMethod(ClassGen.java:367)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:329)
    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
     */
    public static final /* synthetic */ a access$nullArrayFactory() {
        return FlowKt__ZipKt$nullArrayFactory$1.INSTANCE;
    }

    public static final <T1, T2, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return FlowKt.flowCombine(flow, flow2, qVar);
    }

    public static final /* synthetic */ <T, R> Flow<R> combineTransform(Flow<? extends T>[] flowArr, q<? super FlowCollector<? super R>, ? super T[], ? super d<? super r>, ? extends Object> qVar) {
        kotlin.jvm.internal.l.j();
        return FlowKt.flow(new AnonymousClass6(flowArr, qVar, null));
    }

    private static final /* synthetic */ <T, R> Flow<R> combineUnsafe$FlowKt__ZipKt(Flow<? extends T>[] flowArr, p<? super T[], ? super d<? super R>, ? extends Object> pVar) {
        return new FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$3(flowArr, pVar);
    }

    public static final <T1, T2, R> Flow<R> flowCombine(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, flow2, qVar);
    }

    public static final <T1, T2, R> Flow<R> flowCombineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, r5.r<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super d<? super r>, ? extends Object> rVar) {
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$1(new Flow[]{flow, flow2}, null, rVar));
    }

    public static final <T1, T2, R> Flow<R> zip(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return CombineKt.zipImpl(flow, flow2, qVar);
    }

    public static final /* synthetic */ <T, R> Flow<R> combine(Iterable<? extends Flow<? extends T>> iterable, p<? super T[], ? super d<? super R>, ? extends Object> pVar) {
        Object[] array = x.J(iterable).toArray(new Flow[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        kotlin.jvm.internal.l.j();
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$4((Flow[]) array, pVar);
    }

    public static final /* synthetic */ <T, R> Flow<R> combineTransform(Iterable<? extends Flow<? extends T>> iterable, q<? super FlowCollector<? super R>, ? super T[], ? super d<? super r>, ? extends Object> qVar) {
        Object[] array = x.J(iterable).toArray(new Flow[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        kotlin.jvm.internal.l.j();
        return FlowKt.flow(new AnonymousClass7((Flow[]) array, qVar, null));
    }

    public static final <T1, T2, T3, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, final r5.r<? super T1, ? super T2, ? super T3, ? super d<? super R>, ? extends Object> rVar) {
        final Flow[] flowArr = {flow, flow2, flow3};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Zip.kt */
            @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2", f = "Zip.kt", l = {336, 336}, m = "invokeSuspend")
            public static final class AnonymousClass2 extends l implements q<FlowCollector<? super R>, Object[], d<? super r>, Object> {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;
                private FlowCollector p$;
                private Object[] p$0;
                final /* synthetic */ FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(d dVar, FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) {
                    super(3, dVar);
                    this.this$0 = flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
                }

                public final d<r> create(FlowCollector<? super R> flowCollector, Object[] objArr, d<? super r> dVar) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar, this.this$0);
                    anonymousClass2.p$ = flowCollector;
                    anonymousClass2.p$0 = objArr;
                    return anonymousClass2;
                }

                @Override // r5.q
                public final Object invoke(Object obj, Object[] objArr, d<? super r> dVar) {
                    return ((AnonymousClass2) create((FlowCollector) obj, objArr, dVar)).invokeSuspend(r.f6563a);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    FlowCollector flowCollector;
                    FlowCollector flowCollector2;
                    Object[] objArr;
                    Object objD = l5.d.d();
                    int i7 = this.label;
                    if (i7 == 0) {
                        g5.m.b(obj);
                        flowCollector = this.p$;
                        Object[] objArr2 = this.p$0;
                        r5.r rVar = rVar;
                        Object obj2 = objArr2[0];
                        Object obj3 = objArr2[1];
                        Object obj4 = objArr2[2];
                        this.L$0 = flowCollector;
                        this.L$1 = objArr2;
                        this.L$2 = flowCollector;
                        this.L$3 = this;
                        this.L$4 = objArr2;
                        this.label = 1;
                        k.a(6);
                        Object objInvoke = rVar.invoke(obj2, obj3, obj4, this);
                        k.a(7);
                        if (objInvoke == objD) {
                            return objD;
                        }
                        flowCollector2 = flowCollector;
                        objArr = objArr2;
                        obj = objInvoke;
                    } else {
                        if (i7 != 1) {
                            if (i7 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            g5.m.b(obj);
                            return r.f6563a;
                        }
                        flowCollector = (FlowCollector) this.L$2;
                        objArr = (Object[]) this.L$1;
                        flowCollector2 = (FlowCollector) this.L$0;
                        g5.m.b(obj);
                    }
                    this.L$0 = flowCollector2;
                    this.L$1 = objArr;
                    this.label = 2;
                    if (flowCollector.emit(obj, this) == objD) {
                        return objD;
                    }
                    return r.f6563a;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, FlowKt__ZipKt$nullArrayFactory$1.INSTANCE, new AnonymousClass2(null, this), dVar);
                return objCombineInternal == l5.d.d() ? objCombineInternal : r.f6563a;
            }
        };
    }

    public static final <T1, T2, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, r5.r<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super d<? super r>, ? extends Object> rVar) {
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$2(new Flow[]{flow, flow2}, null, rVar));
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, final s<? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super R>, ? extends Object> sVar) {
        final Flow[] flowArr = {flow, flow2, flow3, flow4};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combine$1

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combine$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Zip.kt */
            public static final class AnonymousClass2 extends m implements a<Object[]> {
                public AnonymousClass2() {
                    super(0);
                }

                @Override // r5.a
                public final Object[] invoke() {
                    return new Object[flowArr.length];
                }
            }

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combine$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: Zip.kt */
            @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combine$1$3", f = "Zip.kt", l = {337, 337}, m = "invokeSuspend")
            public static final class AnonymousClass3 extends l implements q<FlowCollector<? super R>, Object[], d<? super r>, Object> {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;
                private FlowCollector p$;
                private Object[] p$0;
                final /* synthetic */ FlowKt__ZipKt$combine$$inlined$combine$1 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(d dVar, FlowKt__ZipKt$combine$$inlined$combine$1 flowKt__ZipKt$combine$$inlined$combine$1) {
                    super(3, dVar);
                    this.this$0 = flowKt__ZipKt$combine$$inlined$combine$1;
                }

                public final d<r> create(FlowCollector<? super R> flowCollector, Object[] objArr, d<? super r> dVar) {
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar, this.this$0);
                    anonymousClass3.p$ = flowCollector;
                    anonymousClass3.p$0 = objArr;
                    return anonymousClass3;
                }

                @Override // r5.q
                public final Object invoke(Object obj, Object[] objArr, d<? super r> dVar) {
                    return ((AnonymousClass3) create((FlowCollector) obj, objArr, dVar)).invokeSuspend(r.f6563a);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    FlowCollector flowCollector;
                    FlowCollector flowCollector2;
                    Object[] objArr;
                    Object objD = l5.d.d();
                    int i7 = this.label;
                    if (i7 == 0) {
                        g5.m.b(obj);
                        flowCollector = this.p$;
                        Object[] objArr2 = this.p$0;
                        s sVar = sVar;
                        Object obj2 = objArr2[0];
                        Object obj3 = objArr2[1];
                        Object obj4 = objArr2[2];
                        Object obj5 = objArr2[3];
                        this.L$0 = flowCollector;
                        this.L$1 = objArr2;
                        this.L$2 = flowCollector;
                        this.L$3 = this;
                        this.L$4 = objArr2;
                        this.label = 1;
                        k.a(6);
                        Object objT = sVar.t(obj2, obj3, obj4, obj5, this);
                        k.a(7);
                        if (objT == objD) {
                            return objD;
                        }
                        flowCollector2 = flowCollector;
                        objArr = objArr2;
                        obj = objT;
                    } else {
                        if (i7 != 1) {
                            if (i7 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            g5.m.b(obj);
                            return r.f6563a;
                        }
                        flowCollector = (FlowCollector) this.L$2;
                        objArr = (Object[]) this.L$1;
                        flowCollector2 = (FlowCollector) this.L$0;
                        g5.m.b(obj);
                    }
                    this.L$0 = flowCollector2;
                    this.L$1 = objArr;
                    this.label = 2;
                    if (flowCollector.emit(obj, this) == objD) {
                        return objD;
                    }
                    return r.f6563a;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, new AnonymousClass2(), new AnonymousClass3(null, this), dVar);
                return objCombineInternal == l5.d.d() ? objCombineInternal : r.f6563a;
            }
        };
    }

    public static final <T1, T2, T3, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, s<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super d<? super r>, ? extends Object> sVar) {
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(new Flow[]{flow, flow2, flow3}, null, sVar));
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combine(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, final t<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super R>, ? extends Object> tVar) {
        final Flow[] flowArr = {flow, flow2, flow3, flow4, flow5};
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2

            /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Zip.kt */
            @f(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2", f = "Zip.kt", l = {338, 338}, m = "invokeSuspend")
            public static final class AnonymousClass2 extends l implements q<FlowCollector<? super R>, Object[], d<? super r>, Object> {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;
                private FlowCollector p$;
                private Object[] p$0;
                final /* synthetic */ FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2 this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(d dVar, FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2) {
                    super(3, dVar);
                    this.this$0 = flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2;
                }

                public final d<r> create(FlowCollector<? super R> flowCollector, Object[] objArr, d<? super r> dVar) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar, this.this$0);
                    anonymousClass2.p$ = flowCollector;
                    anonymousClass2.p$0 = objArr;
                    return anonymousClass2;
                }

                @Override // r5.q
                public final Object invoke(Object obj, Object[] objArr, d<? super r> dVar) {
                    return ((AnonymousClass2) create((FlowCollector) obj, objArr, dVar)).invokeSuspend(r.f6563a);
                }

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    FlowCollector flowCollector;
                    FlowCollector flowCollector2;
                    Object[] objArr;
                    Object objD = l5.d.d();
                    int i7 = this.label;
                    if (i7 == 0) {
                        g5.m.b(obj);
                        flowCollector = this.p$;
                        Object[] objArr2 = this.p$0;
                        t tVar = tVar;
                        Object obj2 = objArr2[0];
                        Object obj3 = objArr2[1];
                        Object obj4 = objArr2[2];
                        Object obj5 = objArr2[3];
                        Object obj6 = objArr2[4];
                        this.L$0 = flowCollector;
                        this.L$1 = objArr2;
                        this.L$2 = flowCollector;
                        this.L$3 = this;
                        this.L$4 = objArr2;
                        this.label = 1;
                        k.a(6);
                        Object objM = tVar.m(obj2, obj3, obj4, obj5, obj6, this);
                        k.a(7);
                        if (objM == objD) {
                            return objD;
                        }
                        flowCollector2 = flowCollector;
                        objArr = objArr2;
                        obj = objM;
                    } else {
                        if (i7 != 1) {
                            if (i7 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            g5.m.b(obj);
                            return r.f6563a;
                        }
                        flowCollector = (FlowCollector) this.L$2;
                        objArr = (Object[]) this.L$1;
                        flowCollector2 = (FlowCollector) this.L$0;
                        g5.m.b(obj);
                    }
                    this.L$0 = flowCollector2;
                    this.L$1 = objArr;
                    this.label = 2;
                    if (flowCollector.emit(obj, this) == objD) {
                        return objD;
                    }
                    return r.f6563a;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, d dVar) {
                Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, FlowKt__ZipKt$nullArrayFactory$1.INSTANCE, new AnonymousClass2(null, this), dVar);
                return objCombineInternal == l5.d.d() ? objCombineInternal : r.f6563a;
            }
        };
    }

    public static final <T1, T2, T3, T4, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, t<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super d<? super r>, ? extends Object> tVar) {
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4(new Flow[]{flow, flow2, flow3, flow4}, null, tVar));
    }

    public static final /* synthetic */ <T, R> Flow<R> combine(Flow<? extends T>[] flowArr, p<? super T[], ? super d<? super R>, ? extends Object> pVar) {
        kotlin.jvm.internal.l.j();
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$3(flowArr, pVar);
    }

    public static final <T1, T2, T3, T4, T5, R> Flow<R> combineTransform(Flow<? extends T1> flow, Flow<? extends T2> flow2, Flow<? extends T3> flow3, Flow<? extends T4> flow4, Flow<? extends T5> flow5, u<? super FlowCollector<? super R>, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super d<? super r>, ? extends Object> uVar) {
        return FlowKt.flow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5(new Flow[]{flow, flow2, flow3, flow4, flow5}, null, uVar));
    }
}
