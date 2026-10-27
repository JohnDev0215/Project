package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ContextKt;

/* JADX INFO: compiled from: SimpleChannelFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022-\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"simpleChannelFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Landroidx/paging/SimpleProducerScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "paging-common"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class SimpleChannelFlowKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: SimpleChannelFlow.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1", f = "SimpleChannelFlow.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<SimpleProducerScope<T>, Continuation<? super Unit>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function2<? super SimpleProducerScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SimpleChannelFlow.kt */
        @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1", f = "SimpleChannelFlow.kt", i = {0, 1}, l = {64, 65}, m = "invokeSuspend", n = {"producer", "producer"}, s = {"L$0", "L$0"})
        static final class C00391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<T> $$this$flow;
            final /* synthetic */ Function2<SimpleProducerScope<T>, Continuation<? super Unit>, Object> $block;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00391(FlowCollector<? super T> flowCollector, Function2<? super SimpleProducerScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C00391> continuation) {
                super(2, continuation);
                this.$$this$flow = flowCollector;
                this.$block = function2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00391 c00391 = new C00391(this.$$this$flow, this.$block, continuation);
                c00391.L$0 = obj;
                return c00391;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:14:0x0060 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
            /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0081 -> B:12:0x0054). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    r12 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r12.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    if (r1 == 0) goto L32
                    if (r1 == r3) goto L25
                    if (r1 != r2) goto L1d
                    java.lang.Object r1 = r12.L$1
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    java.lang.Object r5 = r12.L$0
                    kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5
                    kotlin.ResultKt.throwOnFailure(r13)
                    r13 = r1
                    r1 = r5
                    goto L53
                L1d:
                    java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r13.<init>(r0)
                    throw r13
                L25:
                    java.lang.Object r1 = r12.L$1
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    java.lang.Object r5 = r12.L$0
                    kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5
                    kotlin.ResultKt.throwOnFailure(r13)
                    r6 = r12
                    goto L66
                L32:
                    kotlin.ResultKt.throwOnFailure(r13)
                    java.lang.Object r13 = r12.L$0
                    r5 = r13
                    kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
                    r13 = 0
                    r1 = 6
                    kotlinx.coroutines.channels.Channel r13 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r13, r4, r4, r1, r4)
                    r6 = 0
                    r7 = 0
                    androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1 r8 = new androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1
                    kotlin.jvm.functions.Function2<androidx.paging.SimpleProducerScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r12.$block
                    r8.<init>(r13, r1, r4)
                    r9 = 3
                    r10 = 0
                    kotlinx.coroutines.Job r1 = kotlinx.coroutines.BuildersKt.launch$default(r5, r6, r7, r8, r9, r10)
                    kotlinx.coroutines.channels.ChannelIterator r13 = r13.iterator()
                L53:
                    r5 = r12
                L54:
                    r5.L$0 = r1
                    r5.L$1 = r13
                    r5.label = r3
                    java.lang.Object r6 = r13.hasNext(r5)
                    if (r6 != r0) goto L61
                    return r0
                L61:
                    r11 = r1
                    r1 = r13
                    r13 = r6
                    r6 = r5
                    r5 = r11
                L66:
                    java.lang.Boolean r13 = (java.lang.Boolean) r13
                    boolean r13 = r13.booleanValue()
                    if (r13 == 0) goto L85
                    java.lang.Object r13 = r1.next()
                    kotlinx.coroutines.flow.FlowCollector<T> r7 = r6.$$this$flow
                    r6.L$0 = r5
                    r6.L$1 = r1
                    r6.label = r2
                    java.lang.Object r13 = r7.emit(r13, r6)
                    if (r13 != r0) goto L81
                    return r0
                L81:
                    r13 = r1
                    r1 = r5
                    r5 = r6
                    goto L54
                L85:
                    kotlinx.coroutines.Job.DefaultImpls.cancel$default(r5, r4, r3, r4)
                    kotlin.Unit r13 = kotlin.Unit.INSTANCE
                    return r13
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SimpleChannelFlowKt.AnonymousClass1.C00391.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C00391 c00391 = new C00391((FlowCollector) this.L$0, this.$block, null);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(c00391, this) == coroutine_suspended) {
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

    public static final <T> Flow<T> simpleChannelFlow(Function2<? super SimpleProducerScope<T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return FlowKt__ContextKt.buffer$default(FlowKt.flow(new AnonymousClass1(block, null)), -2, null, 2, null);
    }
}
