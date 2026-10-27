package androidx.paging;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: FlowExt.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0082\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032?\b\u0004\u0010\b\u001a9\b\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001ah\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u000329\b\u0004\u0010\b\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001ab\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u000323\b\u0004\u0010\b\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001an\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0003\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00032F\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a|\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u00032\u0006\u0010\u001c\u001a\u0002H\u00042F\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001ap\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u00032B\u0010\b\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u001f\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018¢\u0006\u0002\b!H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"NULL", "", "combineWithoutBatching", "Lkotlinx/coroutines/flow/Flow;", "R", PlatformWeblabs.T1, PlatformWeblabs.T2, "otherFlow", "transform", "Lkotlin/Function4;", "Landroidx/paging/CombineSource;", "Lkotlin/ParameterName;", "name", "updateFrom", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "simpleFlatMapLatest", "T", "Lkotlin/Function2;", "value", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "simpleMapLatest", "simpleRunningReduce", "operation", "Lkotlin/Function3;", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "simpleScan", "initial", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "simpleTransformLatest", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "paging-common"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class FlowExtKt {
    private static final Object NULL = new Object();

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleScan$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@"}, d2 = {"T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleScan$1", f = "FlowExt.kt", i = {0, 0}, l = {52, 222}, m = "invokeSuspend", n = {"$this$flow", "accumulator"}, s = {"L$0", "L$1"})
    static final class C02231<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ R $initial;
        final /* synthetic */ Function3<R, T, Continuation<? super R>, Object> $operation;
        final /* synthetic */ Flow<T> $this_simpleScan;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02231(R r, Flow<? extends T> flow, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super C02231> continuation) {
            super(2, continuation);
            this.$initial = r;
            this.$this_simpleScan = flow;
            this.$operation = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02231 c02231 = new C02231(this.$initial, this.$this_simpleScan, this.$operation, continuation);
            c02231.L$0 = obj;
            return c02231;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
            return ((C02231) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to androidx.paging.FlowExtKt$simpleScan$1<R> for r6v1 'this'  kotlin.coroutines.Continuation
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L26
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r7)
                goto L5b
            L12:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1a:
                java.lang.Object r1 = r6.L$1
                kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
                java.lang.Object r3 = r6.L$0
                kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                kotlin.ResultKt.throwOnFailure(r7)
                goto L44
            L26:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
                r1.<init>()
                R r4 = r6.$initial
                r1.element = r4
                r6.L$0 = r7
                r6.L$1 = r1
                r6.label = r3
                java.lang.Object r3 = r7.emit(r4, r6)
                if (r3 != r0) goto L43
                return r0
            L43:
                r3 = r7
            L44:
                kotlinx.coroutines.flow.Flow<T> r7 = r6.$this_simpleScan
                kotlin.jvm.functions.Function3<R, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r4 = r6.$operation
                androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1 r5 = new androidx.paging.FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1
                r5.<init>(r1, r4, r3)
                r1 = 0
                r6.L$0 = r1
                r6.L$1 = r1
                r6.label = r2
                java.lang.Object r7 = r7.collect(r5, r6)
                if (r7 != r0) goto L5b
                return r0
            L5b:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.C02231.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T, R> Flow<R> simpleScan(Flow<? extends T> flow, R r, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> operation) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return FlowKt.flow(new C02231(r, flow, operation, null));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleRunningReduce$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleRunningReduce$1", f = "FlowExt.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    static final class C02221<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<T, T, Continuation<? super T>, Object> $operation;
        final /* synthetic */ Flow<T> $this_simpleRunningReduce;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02221(Flow<? extends T> flow, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3, Continuation<? super C02221> continuation) {
            super(2, continuation);
            this.$this_simpleRunningReduce = flow;
            this.$operation = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02221 c02221 = new C02221(this.$this_simpleRunningReduce, this.$operation, continuation);
            c02221.L$0 = obj;
            return c02221;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return ((C02221) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = (T) FlowExtKt.NULL;
                Flow<T> flow = this.$this_simpleRunningReduce;
                FlowExtKt$simpleRunningReduce$1$invokeSuspend$$inlined$collect$1 flowExtKt$simpleRunningReduce$1$invokeSuspend$$inlined$collect$1 = new FlowExtKt$simpleRunningReduce$1$invokeSuspend$$inlined$collect$1(objectRef, this.$operation, flowCollector);
                this.label = 1;
                if (flow.collect(flowExtKt$simpleRunningReduce$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> Flow<T> simpleRunningReduce(Flow<? extends T> flow, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> operation) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(operation, "operation");
        return FlowKt.flow(new C02221(flow, operation, null));
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleTransformLatest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@"}, d2 = {"T", "R", "Landroidx/paging/SimpleProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleTransformLatest$1", f = "FlowExt.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    static final class C02241<R> extends SuspendLambda implements Function2<SimpleProducerScope<R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow<T> $this_simpleTransformLatest;
        final /* synthetic */ Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02241(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C02241> continuation) {
            super(2, continuation);
            this.$this_simpleTransformLatest = flow;
            this.$transform = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02241 c02241 = new C02241(this.$this_simpleTransformLatest, this.$transform, continuation);
            c02241.L$0 = obj;
            return c02241;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(SimpleProducerScope<R> simpleProducerScope, Continuation<? super Unit> continuation) {
            return ((C02241) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
                Flow<T> flow = this.$this_simpleTransformLatest;
                C00311 c00311 = new C00311(this.$transform, new ChannelFlowCollector(simpleProducerScope), null);
                this.label = 1;
                if (FlowKt.collectLatest(flow, c00311, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Add missing generic type declarations: [T] */
        /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleTransformLatest$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: FlowExt.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "R", "value", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleTransformLatest$1$1", f = "FlowExt.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
        static final class C00311<T> extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {
            final /* synthetic */ ChannelFlowCollector<R> $collector;
            final /* synthetic */ Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> $transform;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00311(Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, ChannelFlowCollector<R> channelFlowCollector, Continuation<? super C00311> continuation) {
                super(2, continuation);
                this.$transform = function3;
                this.$collector = channelFlowCollector;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00311 c00311 = new C00311(this.$transform, this.$collector, continuation);
                c00311.L$0 = obj;
                return c00311;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(T t, Continuation<? super Unit> continuation) {
                return ((C00311) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$simpleTransformLatest$1$1<T> for r4v1 'this'  java.lang.Object
                	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
                	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
                	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
                	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
                	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                /*
                    r4 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r4.label
                    r2 = 1
                    if (r1 == 0) goto L17
                    if (r1 != r2) goto Lf
                    kotlin.ResultKt.throwOnFailure(r5)
                    goto L29
                Lf:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r0)
                    throw r5
                L17:
                    kotlin.ResultKt.throwOnFailure(r5)
                    java.lang.Object r5 = r4.L$0
                    kotlin.jvm.functions.Function3<kotlinx.coroutines.flow.FlowCollector<? super R>, T, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r4.$transform
                    androidx.paging.ChannelFlowCollector<R> r3 = r4.$collector
                    r4.label = r2
                    java.lang.Object r5 = r1.invoke(r3, r5, r4)
                    if (r5 != r0) goto L29
                    return r0
                L29:
                    kotlin.Unit r5 = kotlin.Unit.INSTANCE
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.C02241.C00311.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public static final <T, R> Flow<R> simpleTransformLatest(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> transform) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return SimpleChannelFlowKt.simpleChannelFlow(new C02241(flow, transform, null));
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleFlatMapLatest$1, reason: invalid class name */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleFlatMapLatest$1", f = "FlowExt.kt", i = {}, l = {96, 96}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1<R, T> extends SuspendLambda implements Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super Flow<? extends R>>, Object> $transform;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$transform = function2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super R> flowCollector, T t, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.L$1 = t;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$simpleFlatMapLatest$1<R, T> for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r6)
                goto L47
            L12:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1a:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L39
            L22:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                r1 = r6
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                java.lang.Object r6 = r5.L$1
                kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends R>>, java.lang.Object> r4 = r5.$transform
                r5.L$0 = r1
                r5.label = r3
                java.lang.Object r6 = r4.mo4invoke(r6, r5)
                if (r6 != r0) goto L39
                return r0
            L39:
                kotlinx.coroutines.flow.Flow r6 = (kotlinx.coroutines.flow.Flow) r6
                r3 = 0
                r5.L$0 = r3
                r5.label = r2
                java.lang.Object r6 = kotlinx.coroutines.flow.FlowKt.emitAll(r1, r6, r5)
                if (r6 != r0) goto L47
                return r0
            L47:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$simpleFlatMapLatest$1<R, T> for r2v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        public final java.lang.Object invokeSuspend$$forInline(java.lang.Object r3) {
            /*
                r2 = this;
                java.lang.Object r3 = r2.L$0
                kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                java.lang.Object r0 = r2.L$1
                kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends R>>, java.lang.Object> r1 = r2.$transform
                java.lang.Object r0 = r1.mo4invoke(r0, r2)
                kotlinx.coroutines.flow.Flow r0 = (kotlinx.coroutines.flow.Flow) r0
                r1 = 0
                kotlin.jvm.internal.InlineMarker.mark(r1)
                kotlinx.coroutines.flow.FlowKt.emitAll(r3, r0, r2)
                r3 = 1
                kotlin.jvm.internal.InlineMarker.mark(r3)
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.AnonymousClass1.invokeSuspend$$forInline(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T, R> Flow<R> simpleFlatMapLatest(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> transform) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return simpleTransformLatest(flow, new AnonymousClass1(transform, null));
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$simpleMapLatest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$simpleMapLatest$1", f = "FlowExt.kt", i = {}, l = {103, 103}, m = "invokeSuspend", n = {}, s = {})
    public static final class C02211<R, T> extends SuspendLambda implements Function3<FlowCollector<? super R>, T, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<T, Continuation<? super R>, Object> $transform;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02211(Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C02211> continuation) {
            super(3, continuation);
            this.$transform = function2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super R> flowCollector, T t, Continuation<? super Unit> continuation) {
            C02211 c02211 = new C02211(this.$transform, continuation);
            c02211.L$0 = flowCollector;
            c02211.L$1 = t;
            return c02211.invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$simpleMapLatest$1<R, T> for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r6)
                goto L45
            L12:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1a:
                java.lang.Object r1 = r5.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r6)
                goto L39
            L22:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                r1 = r6
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                java.lang.Object r6 = r5.L$1
                kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r4 = r5.$transform
                r5.L$0 = r1
                r5.label = r3
                java.lang.Object r6 = r4.mo4invoke(r6, r5)
                if (r6 != r0) goto L39
                return r0
            L39:
                r3 = 0
                r5.L$0 = r3
                r5.label = r2
                java.lang.Object r6 = r1.emit(r6, r5)
                if (r6 != r0) goto L45
                return r0
            L45:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.C02211.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$simpleMapLatest$1<R, T> for r2v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        public final java.lang.Object invokeSuspend$$forInline(java.lang.Object r3) {
            /*
                r2 = this;
                java.lang.Object r3 = r2.L$0
                kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
                java.lang.Object r0 = r2.L$1
                kotlin.jvm.functions.Function2<T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r1 = r2.$transform
                java.lang.Object r0 = r1.mo4invoke(r0, r2)
                r1 = 0
                kotlin.jvm.internal.InlineMarker.mark(r1)
                r3.emit(r0, r2)
                r3 = 1
                kotlin.jvm.internal.InlineMarker.mark(r3)
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt.C02211.invokeSuspend$$forInline(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T, R> Flow<R> simpleMapLatest(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super R>, ? extends Object> transform) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        return simpleTransformLatest(flow, new C02211(transform, null));
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.paging.FlowExtKt$combineWithoutBatching$2, reason: invalid class name */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00020\u0003H\u008a@"}, d2 = {PlatformWeblabs.T1, PlatformWeblabs.T2, "R", "Landroidx/paging/SimpleProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2", f = "FlowExt.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<SimpleProducerScope<R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Flow<T2> $otherFlow;
        final /* synthetic */ Flow<T1> $this_combineWithoutBatching;
        final /* synthetic */ Function4<T1, T2, CombineSource, Continuation<? super R>, Object> $transform;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_combineWithoutBatching = flow;
            this.$otherFlow = flow2;
            this.$transform = function4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_combineWithoutBatching, this.$otherFlow, this.$transform, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(SimpleProducerScope<R> simpleProducerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(simpleProducerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
                AtomicInteger atomicInteger = new AtomicInteger(2);
                UnbatchedFlowCombiner unbatchedFlowCombiner = new UnbatchedFlowCombiner(new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(simpleProducerScope, this.$transform, null));
                CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
                Flow[] flowArr = {this.$this_combineWithoutBatching, this.$otherFlow};
                int i2 = 0;
                int i3 = 0;
                while (i3 < 2) {
                    BuildersKt__Builders_commonKt.launch$default(simpleProducerScope, completableJobJob$default, null, new FlowExtKt$combineWithoutBatching$2$1$1(flowArr[i3], atomicInteger, simpleProducerScope, unbatchedFlowCombiner, i2, null), 2, null);
                    i3++;
                    i2++;
                }
                C00302 c00302 = new C00302(completableJobJob$default);
                this.label = 1;
                if (simpleProducerScope.awaitClose(c00302, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
            AtomicInteger atomicInteger = new AtomicInteger(2);
            UnbatchedFlowCombiner unbatchedFlowCombiner = new UnbatchedFlowCombiner(new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(simpleProducerScope, this.$transform, null));
            CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            Flow[] flowArr = {this.$this_combineWithoutBatching, this.$otherFlow};
            int i = 0;
            int i2 = 0;
            while (i2 < 2) {
                BuildersKt__Builders_commonKt.launch$default(simpleProducerScope, completableJobJob$default, null, new FlowExtKt$combineWithoutBatching$2$1$1(flowArr[i2], atomicInteger, simpleProducerScope, unbatchedFlowCombiner, i, null), 2, null);
                i2++;
                i++;
            }
            C00302 c00302 = new C00302(completableJobJob$default);
            InlineMarker.mark(0);
            simpleProducerScope.awaitClose(c00302, this);
            InlineMarker.mark(1);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.paging.FlowExtKt$combineWithoutBatching$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: FlowExt.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002H\n"}, d2 = {PlatformWeblabs.T1, PlatformWeblabs.T2, "R", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        public static final class C00302 extends Lambda implements Function0<Unit> {
            final /* synthetic */ CompletableJob $parentJob;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00302(CompletableJob completableJob) {
                super(0);
                this.$parentJob = completableJob;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Job.DefaultImpls.cancel$default((Job) this.$parentJob, (CancellationException) null, 1, (Object) null);
            }
        }
    }

    public static final <T1, T2, R> Object combineWithoutBatching(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super Flow<? extends R>> continuation) {
        return SimpleChannelFlowKt.simpleChannelFlow(new AnonymousClass2(flow, flow2, function4, null));
    }
}
