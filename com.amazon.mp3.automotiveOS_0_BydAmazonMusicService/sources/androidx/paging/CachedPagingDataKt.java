package androidx.paging;

import androidx.annotation.CheckResult;
import androidx.paging.ActiveFlowTracker;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

/* JADX INFO: compiled from: CachedPagingData.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001aB\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, d2 = {"cachedIn", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tracker", "Landroidx/paging/ActiveFlowTracker;", "paging-common"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class CachedPagingDataKt {
    @CheckResult
    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(scope, "scope");
        return cachedIn(flow, scope, null);
    }

    public static /* synthetic */ Flow cachedIn$default(Flow flow, CoroutineScope coroutineScope, ActiveFlowTracker activeFlowTracker, int i, Object obj) {
        if ((i & 2) != 0) {
            activeFlowTracker = null;
        }
        return cachedIn(flow, coroutineScope, activeFlowTracker);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.CachedPagingDataKt$cachedIn$2, reason: invalid class name */
    /* JADX INFO: compiled from: CachedPagingData.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@"}, d2 = {"", "T", "Landroidx/paging/MulticastedPagingData;", "prev", "next", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.CachedPagingDataKt$cachedIn$2", f = "CachedPagingData.kt", i = {0}, l = {99}, m = "invokeSuspend", n = {"next"}, s = {"L$0"})
    static final class AnonymousClass2<T> extends SuspendLambda implements Function3<MulticastedPagingData<T>, MulticastedPagingData<T>, Continuation<? super MulticastedPagingData<T>>, Object> {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(MulticastedPagingData<T> multicastedPagingData, MulticastedPagingData<T> multicastedPagingData2, Continuation<? super MulticastedPagingData<T>> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = multicastedPagingData;
            anonymousClass2.L$1 = multicastedPagingData2;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                MulticastedPagingData multicastedPagingData = (MulticastedPagingData) this.L$0;
                ResultKt.throwOnFailure(obj);
                return multicastedPagingData;
            }
            ResultKt.throwOnFailure(obj);
            MulticastedPagingData multicastedPagingData2 = (MulticastedPagingData) this.L$0;
            MulticastedPagingData multicastedPagingData3 = (MulticastedPagingData) this.L$1;
            this.L$0 = multicastedPagingData3;
            this.label = 1;
            return multicastedPagingData2.close(this) == coroutine_suspended ? coroutine_suspended : multicastedPagingData3;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.CachedPagingDataKt$cachedIn$4, reason: invalid class name */
    /* JADX INFO: compiled from: CachedPagingData.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PagingData;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.CachedPagingDataKt$cachedIn$4", f = "CachedPagingData.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4<T> extends SuspendLambda implements Function2<FlowCollector<? super PagingData<T>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ActiveFlowTracker $tracker;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(ActiveFlowTracker activeFlowTracker, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$tracker = activeFlowTracker;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.$tracker, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super PagingData<T>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ActiveFlowTracker activeFlowTracker = this.$tracker;
                if (activeFlowTracker != null) {
                    ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGED_DATA_FLOW;
                    this.label = 1;
                    if (activeFlowTracker.onStart(flowType, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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

    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope scope, ActiveFlowTracker activeFlowTracker) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(scope, "scope");
        final Flow flowSimpleRunningReduce = FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new CachedPagingDataKt$cachedIn$$inlined$simpleMapLatest$1(null, scope)), new AnonymousClass2(null));
        return FlowKt.shareIn(FlowKt.onCompletion(FlowKt.onStart(new Flow<PagingData<T>>() { // from class: androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1

            /* JADX INFO: renamed from: androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector<MulticastedPagingData<T>> {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2", f = "CachedPagingData.kt", i = {}, l = {voOSType.VOOSMP_PID_PREFERENCE}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1 r0 = (androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1 r0 = new androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L45
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlinx.coroutines.flow.FlowCollector r6 = r4.$this_unsafeFlow$inlined
                        androidx.paging.MulticastedPagingData r5 = (androidx.paging.MulticastedPagingData) r5
                        androidx.paging.PagingData r5 = r5.asPagingData()
                        r0.label = r3
                        java.lang.Object r5 = r6.emit(r5, r0)
                        if (r5 != r1) goto L45
                        return r1
                    L45:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPagingDataKt$cachedIn$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flowSimpleRunningReduce.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new AnonymousClass4(activeFlowTracker, null)), new AnonymousClass5(activeFlowTracker, null)), scope, SharingStarted.INSTANCE.getLazily(), 1);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.CachedPagingDataKt$cachedIn$5, reason: invalid class name */
    /* JADX INFO: compiled from: CachedPagingData.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PagingData;", "", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.CachedPagingDataKt$cachedIn$5", f = "CachedPagingData.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5<T> extends SuspendLambda implements Function3<FlowCollector<? super PagingData<T>>, Throwable, Continuation<? super Unit>, Object> {
        final /* synthetic */ ActiveFlowTracker $tracker;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(ActiveFlowTracker activeFlowTracker, Continuation<? super AnonymousClass5> continuation) {
            super(3, continuation);
            this.$tracker = activeFlowTracker;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super PagingData<T>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return new AnonymousClass5(this.$tracker, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ActiveFlowTracker activeFlowTracker = this.$tracker;
                if (activeFlowTracker != null) {
                    ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGED_DATA_FLOW;
                    this.label = 1;
                    if (activeFlowTracker.onComplete(flowType, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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
}
