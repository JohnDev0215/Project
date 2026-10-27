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
import spi.a2;
import spi.mv;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleWork$$inlined$transform$1", f = "ExecutorHciWorkProcess.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorHciWorkProcess$handleWork$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super mv>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ a2 $bizSrc$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ExecutorHciWorkProcess this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleWork$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<mv> a;
        public final /* synthetic */ a2 b;
        public final /* synthetic */ ExecutorHciWorkProcess c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleWork$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleWork$$inlined$transform$1$1", f = "ExecutorHciWorkProcess.kt", i = {1, 1, 1, 1, 3, 3, 3, 3, 3, 4}, l = {308, 316, 323, 326, 330, 348}, m = "emit", n = {"this", "carrier", "$this$handleWork_u24lambda_u2d2", "optParam", "this", "carrier", "$this$handleWork_u24lambda_u2d2", "optParam", "pairStateRet", "this"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0"})
        public static final class C00471 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public int label;
            public /* synthetic */ Object result;

            public C00471(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, a2 a2Var, ExecutorHciWorkProcess executorHciWorkProcess) {
            this.b = a2Var;
            this.c = executorHciWorkProcess;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:46:0x01f3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x01f4  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0200  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x022c  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x025c  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x029f  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 748
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleWork$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorHciWorkProcess$handleWork$$inlined$transform$1(Flow flow, Continuation continuation, a2 a2Var, ExecutorHciWorkProcess executorHciWorkProcess) {
        super(2, continuation);
        this.$this_transform = flow;
        this.$bizSrc$inlined = a2Var;
        this.this$0 = executorHciWorkProcess;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorHciWorkProcess$handleWork$$inlined$transform$1 executorHciWorkProcess$handleWork$$inlined$transform$1 = new ExecutorHciWorkProcess$handleWork$$inlined$transform$1(this.$this_transform, continuation, this.$bizSrc$inlined, this.this$0);
        executorHciWorkProcess$handleWork$$inlined$transform$1.L$0 = obj;
        return executorHciWorkProcess$handleWork$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super mv> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorHciWorkProcess$handleWork$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.$bizSrc$inlined, this.this$0);
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
