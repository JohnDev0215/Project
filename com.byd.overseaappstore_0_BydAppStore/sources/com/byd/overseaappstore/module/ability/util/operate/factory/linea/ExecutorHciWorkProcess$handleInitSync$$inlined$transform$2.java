package com.byd.overseaappstore.module.ability.util.operate.factory.linea;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.mv;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2", f = "ExecutorHciWorkProcess.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2 extends SuspendLambda implements Function2<FlowCollector<? super mv>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ExecutorHciWorkProcess this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<mv> a;
        public final /* synthetic */ ExecutorHciWorkProcess b;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2$1", f = "ExecutorHciWorkProcess.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3}, l = {237, 243, 254, 262, 272}, m = "emit", n = {"carrier", "$this$handleInitSync_u24lambda_u2d37", "optParam", "this", "carrier", "$this$handleInitSync_u24lambda_u2d37", "optParam", "targetState", "carrier", "$this$handleInitSync_u24lambda_u2d37", "optParam", "this", "carrier", "$this$handleInitSync_u24lambda_u2d37", "optParam", "targetState"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "I$0"})
        public static final class C00411 extends ContinuationImpl {
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public int label;
            public /* synthetic */ Object result;

            public C00411(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector, ExecutorHciWorkProcess executorHciWorkProcess) {
            this.b = executorHciWorkProcess;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01d4 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
            /*
                Method dump skipped, instruction units count: 472
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2(Flow flow, Continuation continuation, ExecutorHciWorkProcess executorHciWorkProcess) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = executorHciWorkProcess;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2 executorHciWorkProcess$handleInitSync$$inlined$transform$2 = new ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2(this.$this_transform, continuation, this.this$0);
        executorHciWorkProcess$handleInitSync$$inlined$transform$2.L$0 = obj;
        return executorHciWorkProcess$handleInitSync$$inlined$transform$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super mv> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
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
