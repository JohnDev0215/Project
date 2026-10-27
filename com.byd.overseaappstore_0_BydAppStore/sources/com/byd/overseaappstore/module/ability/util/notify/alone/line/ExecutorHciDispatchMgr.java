package com.byd.overseaappstore.module.ability.util.notify.alone.line;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.operate.factory.lineb.CacheCarrier;
import java.util.Objects;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.ct;
import spi.e;
import spi.o9;
import spi.xi;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0014\u0010\u0015J+\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciDispatchMgr;", "", "Lspi/a2;", "bizSrc", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "optParam", "", "currentState", "", "handleWorkToWait", "(Lspi/a2;Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleWaitToWork", "(Lspi/a2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciLineDispatch;", "executorHciLineDispatch", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciLineDispatch;", "getExecutorHciLineDispatch", "()Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciLineDispatch;", "Lspi/o9;", "executorHciCacheLine", "<init>", "(Lspi/o9;Lcom/byd/overseaappstore/module/ability/util/notify/alone/line/ExecutorHciLineDispatch;)V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ExecutorHciDispatchMgr {

    @NotNull
    private final o9 executorHciCacheLine;

    @NotNull
    private final ExecutorHciLineDispatch executorHciLineDispatch;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr$1, reason: invalid class name */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr$1", f = "ExecutorHciDispatchMgr.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return ExecutorHciDispatchMgr.this.new AnonymousClass1(continuation);
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
                SharedFlow<Pair<a2, Pair<Byte, CacheCarrier>>> lineDispatchFlow = ExecutorHciDispatchMgr.this.getExecutorHciLineDispatch().getLineDispatchFlow();
                final ExecutorHciDispatchMgr executorHciDispatchMgr = ExecutorHciDispatchMgr.this;
                FlowCollector<? super Pair<a2, Pair<Byte, CacheCarrier>>> flowCollector = new FlowCollector() { // from class: com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Pair<a2, Pair<Byte, CacheCarrier>>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    @Nullable
                    public final Object emit(@NotNull Pair<a2, Pair<Byte, CacheCarrier>> pair, @NotNull Continuation<? super Unit> continuation) {
                        SoftwareOptParam optParam;
                        a2 first = pair.getFirst();
                        Pair<Byte, CacheCarrier> second = pair.getSecond();
                        byte bByteValue = second.getFirst().byteValue();
                        CacheCarrier second2 = second.getSecond();
                        ct.a(xi.f("executorHciDispatchMgr collect dispatchType:", bByteValue, " pkn:", (second2 == null || (optParam = second2.getOptParam()) == null) ? null : optParam.getPackageName()), new Object[0]);
                        if (bByteValue == 1) {
                            SoftwareOptParam optParam2 = second2 != null ? second2.getOptParam() : null;
                            Integer numBoxInt = second2 != null ? Boxing.boxInt(second2.getState()) : null;
                            if (optParam2 == null) {
                                ct.b("executorHciDispatchMgr WORK_TO_WAIT optParam is null", new Object[0]);
                            } else {
                                if (numBoxInt != null) {
                                    Object objHandleWorkToWait = executorHciDispatchMgr.handleWorkToWait(first, optParam2, numBoxInt.intValue(), continuation);
                                    return objHandleWorkToWait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objHandleWorkToWait : Unit.INSTANCE;
                                }
                                ct.b("executorHciDispatchMgr WORK_TO_WAIT currentState is null", new Object[0]);
                            }
                        } else {
                            if (bByteValue == 2) {
                                Object objHandleWaitToWork = executorHciDispatchMgr.handleWaitToWork(first, continuation);
                                return objHandleWaitToWork == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objHandleWaitToWork : Unit.INSTANCE;
                            }
                            ct.b(e.c("executorHciDispatchMgr unknown flowTag:", bByteValue), new Object[0]);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (lineDispatchFlow.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    public ExecutorHciDispatchMgr(@NotNull o9 executorHciCacheLine, @NotNull ExecutorHciLineDispatch executorHciLineDispatch) {
        Intrinsics.checkNotNullParameter(executorHciCacheLine, "executorHciCacheLine");
        Intrinsics.checkNotNullParameter(executorHciLineDispatch, "executorHciLineDispatch");
        this.executorHciCacheLine = executorHciCacheLine;
        this.executorHciLineDispatch = executorHciLineDispatch;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null))), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object handleWaitToWork(a2 a2Var, Continuation<? super Unit> continuation) {
        Object objA = this.executorHciCacheLine.a(a2Var, continuation);
        return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object handleWorkToWait(a2 a2Var, SoftwareOptParam softwareOptParam, int i, Continuation<? super Unit> continuation) {
        o9 o9Var = this.executorHciCacheLine;
        Objects.requireNonNull(o9Var);
        ct.a("executorHciCacheLine handleWorkToWait pkn:" + softwareOptParam.getPackageName() + " bizSrc:" + a2Var, new Object[0]);
        Object objB = o9Var.d.b(a2Var, o9Var.b.parseToCacheCarrier(softwareOptParam, i), continuation);
        if (objB != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            objB = Unit.INSTANCE;
        }
        return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
    }

    @NotNull
    public final ExecutorHciLineDispatch getExecutorHciLineDispatch() {
        return this.executorHciLineDispatch;
    }
}
