package com.byd.overseaappstore.module.ability.util.notify.alone.line;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0011\u0010\u0012J(\u0010\b\u001a\u00020\u00072 \u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00020\u0002R4\u0010\n\u001a\"\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00020\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bR7\u0010\r\u001a\"\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00020\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciLineDispatch;", "", "Lkotlin/Pair;", "Lspi/a2;", "", "Lcom/byd/overseaappstore/module/ability/util/operate/factory/lineb/CacheCarrier;", "pair", "", "notifyLineDispatch", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "_lineDispatchFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlow;", "lineDispatchFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getLineDispatchFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ExecutorHciLineDispatch {

    @NotNull
    private final MutableSharedFlow<Pair<a2, Pair<Byte, CacheCarrier>>> _lineDispatchFlow;

    @NotNull
    private final SharedFlow<Pair<a2, Pair<Byte, CacheCarrier>>> lineDispatchFlow;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciLineDispatch$notifyLineDispatch$1, reason: invalid class name */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciLineDispatch$notifyLineDispatch$1", f = "ExecutorHciLineDispatch.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Pair<a2, Pair<Byte, CacheCarrier>> $pair;
        public int label;
        public final /* synthetic */ ExecutorHciLineDispatch this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Pair<a2, Pair<Byte, CacheCarrier>> pair, ExecutorHciLineDispatch executorHciLineDispatch, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$pair = pair;
            this.this$0 = executorHciLineDispatch;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.$pair, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo6invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            SoftwareOptParam optParam;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CacheCarrier second = this.$pair.getSecond().getSecond();
                String packageName = (second == null || (optParam = second.getOptParam()) == null) ? null : optParam.getPackageName();
                ct.a("executor notifyLineDispatch pkn:" + packageName + " bizSrc:" + this.$pair.getFirst() + " ", new Object[0]);
                MutableSharedFlow mutableSharedFlow = this.this$0._lineDispatchFlow;
                Pair<a2, Pair<Byte, CacheCarrier>> pair = this.$pair;
                this.label = 1;
                if (mutableSharedFlow.emit(pair, this) == coroutine_suspended) {
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

    public ExecutorHciLineDispatch() {
        MutableSharedFlow<Pair<a2, Pair<Byte, CacheCarrier>>> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._lineDispatchFlow = mutableSharedFlowMutableSharedFlow$default;
        this.lineDispatchFlow = mutableSharedFlowMutableSharedFlow$default;
    }

    @NotNull
    public final SharedFlow<Pair<a2, Pair<Byte, CacheCarrier>>> getLineDispatchFlow() {
        return this.lineDispatchFlow;
    }

    public final void notifyLineDispatch(@NotNull Pair<a2, Pair<Byte, CacheCarrier>> pair) {
        Intrinsics.checkNotNullParameter(pair, "pair");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO())), null, null, new AnonymousClass1(pair, this, null), 3, null);
    }
}
