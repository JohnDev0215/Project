package com.byd.overseaappstore.module.ability.util.notify.alone.trigger;

import kotlin.Metadata;
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
import spi.ct;
import spi.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "", "()V", "_taskTriggerFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "taskTriggerFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getTaskTriggerFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "notifyIUUStateData", "", "pkn", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorSoftwareNewTaskInTrigger {

    @NotNull
    private final MutableSharedFlow<String> _taskTriggerFlow;

    @NotNull
    private final SharedFlow<String> taskTriggerFlow;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger$notifyIUUStateData$1, reason: invalid class name */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger$notifyIUUStateData$1", f = "ExecutorSoftwareNewTaskInTrigger.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $pkn;
        public int label;
        public final /* synthetic */ ExecutorSoftwareNewTaskInTrigger this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$pkn = str;
            this.this$0 = executorSoftwareNewTaskInTrigger;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.$pkn, this.this$0, continuation);
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
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ct.a(d.f("executor notifyIUUStateData newTaskIn pkn:", this.$pkn), new Object[0]);
                MutableSharedFlow mutableSharedFlow = this.this$0._taskTriggerFlow;
                String str = this.$pkn;
                this.label = 1;
                if (mutableSharedFlow.emit(str, this) == coroutine_suspended) {
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

    public ExecutorSoftwareNewTaskInTrigger() {
        MutableSharedFlow<String> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._taskTriggerFlow = mutableSharedFlowMutableSharedFlow$default;
        this.taskTriggerFlow = mutableSharedFlowMutableSharedFlow$default;
    }

    @NotNull
    public final SharedFlow<String> getTaskTriggerFlow() {
        return this.taskTriggerFlow;
    }

    public final void notifyIUUStateData(@NotNull String pkn) {
        Intrinsics.checkNotNullParameter(pkn, "pkn");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(JobKt__JobKt.Job$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO())), null, null, new AnonymousClass1(pkn, this, null), 3, null);
    }
}
