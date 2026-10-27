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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1", f = "ExecutorHciWorkProcess.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super mv>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ a2 $bizSrc$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ExecutorHciWorkProcess this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<mv> a;
        public final /* synthetic */ ExecutorHciWorkProcess b;
        public final /* synthetic */ a2 c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1$1", f = "ExecutorHciWorkProcess.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12}, l = {251, 260, 262, 277, 319, 367, 373, 375, 389, 436, 442, 444, 458}, m = "emit", n = {"this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "updatePauseOptParamList", "toUpdateOptParamList", "networkCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "updatePauseOptParamList", "toUpdateOptParamList", "pairStateListRet", "networkCount", "networkCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "toUpdateOptParamList", "pairStateListRet", "networkCount", "index$iv", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "updatePauseOptParamList", "toUpdateOptParamList", "paramList", "networkCount", "networkDownloadsCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "paramList", "toFreshList", "toAddWaitingList", "networkCount", "networkDownloadsCount", "supplyCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "paramList", "toFreshList", "toAddWaitingList", "pairStateListRet", "networkCount", "networkDownloadsCount", "supplyCount", "paramList", "toFreshList", "toAddWaitingList", "networkCount", "networkDownloadsCount", "supplyCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "pairStateListRet", "index$iv", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "paramList", "toFreshList", "toAddWaitingList", "networkCount", "networkUpdatesCount", "supplyCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "paramList", "toFreshList", "toAddWaitingList", "pairStateListRet", "networkCount", "networkUpdatesCount", "supplyCount", "paramList", "toFreshList", "toAddWaitingList", "networkCount", "networkUpdatesCount", "supplyCount", "this", "carrier", "$this$handleStartUpdateOnlyTasks_u24lambda_u2d32", "pairStateListRet", "index$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "I$0"})
        public static final class C00461 extends ContinuationImpl {
            public int I$0;
            public int I$1;
            public int I$2;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public int label;
            public /* synthetic */ Object result;

            public C00461(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, ExecutorHciWorkProcess executorHciWorkProcess, a2 a2Var) {
            this.b = executorHciWorkProcess;
            this.c = a2Var;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Path cross not found for [B:121:0x04d6, B:125:0x04e1], limit reached: 284 */
        /* JADX WARN: Path cross not found for [B:167:0x0681, B:171:0x068b], limit reached: 284 */
        /* JADX WARN: Path cross not found for [B:180:0x06e4, B:184:0x06ee], limit reached: 284 */
        /* JADX WARN: Path cross not found for [B:241:0x0950, B:245:0x095a], limit reached: 284 */
        /* JADX WARN: Path cross not found for [B:254:0x09bb, B:258:0x09c5], limit reached: 284 */
        /* JADX WARN: Removed duplicated region for block: B:104:0x040d  */
        /* JADX WARN: Removed duplicated region for block: B:105:0x041b  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x04e8  */
        /* JADX WARN: Removed duplicated region for block: B:129:0x04ea  */
        /* JADX WARN: Removed duplicated region for block: B:132:0x04fe  */
        /* JADX WARN: Removed duplicated region for block: B:133:0x0507  */
        /* JADX WARN: Removed duplicated region for block: B:141:0x0555  */
        /* JADX WARN: Removed duplicated region for block: B:149:0x0579  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x0584  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x05ab  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x0629  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x064c  */
        /* JADX WARN: Removed duplicated region for block: B:173:0x068f  */
        /* JADX WARN: Removed duplicated region for block: B:174:0x06b0  */
        /* JADX WARN: Removed duplicated region for block: B:186:0x06f2  */
        /* JADX WARN: Removed duplicated region for block: B:191:0x073e  */
        /* JADX WARN: Removed duplicated region for block: B:194:0x0792  */
        /* JADX WARN: Removed duplicated region for block: B:247:0x095e  */
        /* JADX WARN: Removed duplicated region for block: B:248:0x097f  */
        /* JADX WARN: Removed duplicated region for block: B:260:0x09c9  */
        /* JADX WARN: Removed duplicated region for block: B:265:0x0a13  */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0a64  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x033e  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0341  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x034f  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x037f  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0382  */
        /* JADX WARN: Removed duplicated region for block: B:91:0x03af  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x03ca  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:198:0x07c9 -> B:18:0x011f). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:272:0x0a9b -> B:13:0x005b). Please report as a decompilation issue!!! */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r34, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r35) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 2754
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1(Flow flow, Continuation continuation, ExecutorHciWorkProcess executorHciWorkProcess, a2 a2Var) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = executorHciWorkProcess;
        this.$bizSrc$inlined = a2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1 executorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1 = new ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1(this.$this_transform, continuation, this.this$0, this.$bizSrc$inlined);
        executorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1.L$0 = obj;
        return executorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super mv> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0, this.$bizSrc$inlined);
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
