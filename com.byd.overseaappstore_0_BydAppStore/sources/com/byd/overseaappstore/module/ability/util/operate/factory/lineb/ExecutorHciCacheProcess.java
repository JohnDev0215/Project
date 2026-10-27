package com.byd.overseaappstore.module.ability.util.operate.factory.lineb;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.a2;
import spi.g9;
import spi.m9;
import spi.p9;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciCacheProcess extends m9<CacheCarrier> {

    @NotNull
    public final g9 b;

    @NotNull
    public final p9 c;

    public ExecutorHciCacheProcess(@NotNull g9 executorConnectWorkMgr, @NotNull p9 executorHciCheckPlugin) {
        Intrinsics.checkNotNullParameter(executorConnectWorkMgr, "executorConnectWorkMgr");
        Intrinsics.checkNotNullParameter(executorHciCheckPlugin, "executorHciCheckPlugin");
        this.b = executorConnectWorkMgr;
        this.c = executorHciCheckPlugin;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.m9
    @Nullable
    public final Object a(@NotNull a2 a2Var, @NotNull Flow<? extends CacheCarrier> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciCacheProcess$handleWaitToWork$$inlined$transform$1(flow, null, this));
        m9<T> m9Var = this.a;
        if (m9Var != 0) {
            Object objA = m9Var.a(a2Var, (Flow<? extends T>) flow2, continuation);
            return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.m9
    public final Object b(a2 a2Var, CacheCarrier cacheCarrier, Continuation continuation) {
        CacheCarrier cacheCarrier2 = cacheCarrier;
        m9<T> m9Var = this.a;
        if (m9Var != 0) {
            Object objB = m9Var.b(a2Var, (T) cacheCarrier2, (Continuation<? super Unit>) continuation);
            if (objB == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objB;
            }
        } else if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }
}
