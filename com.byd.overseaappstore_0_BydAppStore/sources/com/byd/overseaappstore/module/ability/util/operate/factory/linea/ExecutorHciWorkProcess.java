package com.byd.overseaappstore.module.ability.util.operate.factory.linea;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.notify.alone.line.ExecutorHciDispatchMgr;
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
import spi.i9;
import spi.mv;
import spi.oa;
import spi.p9;
import spi.t9;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciWorkProcess extends t9<mv> {

    @NotNull
    public final oa b;

    @NotNull
    public final i9 c;

    @NotNull
    public final g9 d;

    @NotNull
    public final DataGenerator e;

    @NotNull
    public final Context f;

    @NotNull
    public final ExecutorHciDispatchMgr g;

    @NotNull
    public final p9 h;

    public ExecutorHciWorkProcess(@NotNull oa executorSoftwareStateMgr, @NotNull i9 executorDbMgr, @NotNull g9 executorConnectWorkMgr, @NotNull DataGenerator dataGenerator, @NotNull Context context, @NotNull ExecutorHciDispatchMgr executorHciDispatchMgr, @NotNull p9 executorHciCheckPlugin) {
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(executorDbMgr, "executorDbMgr");
        Intrinsics.checkNotNullParameter(executorConnectWorkMgr, "executorConnectWorkMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executorHciDispatchMgr, "executorHciDispatchMgr");
        Intrinsics.checkNotNullParameter(executorHciCheckPlugin, "executorHciCheckPlugin");
        this.b = executorSoftwareStateMgr;
        this.c = executorDbMgr;
        this.d = executorConnectWorkMgr;
        this.e = dataGenerator;
        this.f = context;
        this.g = executorHciDispatchMgr;
        this.h = executorHciCheckPlugin;
    }

    @Override // spi.t9
    @Nullable
    public final Object a(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objA = t9Var.a(a2Var, str, continuation);
            return objA == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objA : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    @Override // spi.t9
    @Nullable
    public final Object b(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objB = t9Var.b(a2Var, str, continuation);
            return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object c(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleInitSync$$inlined$transform$2(FlowKt.flow(new ExecutorHciWorkProcess$handleInitSync$$inlined$transform$1(flow, null, this)), null, this));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objC = t9Var.c(a2Var, (Flow<? extends T>) flow2, continuation);
            return objC == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objC : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object d(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handlePauseTasks$$inlined$transform$1(flow, null, this));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objD = t9Var.d(a2Var, (Flow<? extends T>) flow2, continuation);
            return objD == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objD : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object e(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleResumeActing$$inlined$transform$1(flow, null, this, a2Var));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objE = t9Var.e(a2Var, (Flow<? extends T>) flow2, continuation);
            return objE == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objE : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object f(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleStartDownloadOnlyTasks$$inlined$transform$1(flow, null, this));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objF = t9Var.f(a2Var, (Flow<? extends T>) flow2, continuation);
            return objF == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objF : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object g(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1(flow, null, this, a2Var));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objG = t9Var.g(a2Var, (Flow<? extends T>) flow2, continuation);
            return objG == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objG : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object h(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleStartUpdateOnlyTasks$$inlined$transform$1(flow, null, this, a2Var));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objH = t9Var.h(a2Var, (Flow<? extends T>) flow2, continuation);
            return objH == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objH : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    public final void i(a2 bizSrc, mv mvVar) {
        mv carrier = mvVar;
        Intrinsics.checkNotNullParameter(bizSrc, "bizSrc");
        Intrinsics.checkNotNullParameter(carrier, "carrier");
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            t9Var.i(bizSrc, (T) carrier);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.t9
    @Nullable
    public final Object j(@NotNull a2 a2Var, @NotNull Flow<? extends mv> flow, @NotNull Continuation<? super Unit> continuation) {
        Flow flow2 = FlowKt.flow(new ExecutorHciWorkProcess$handleWork$$inlined$transform$1(flow, null, a2Var, this));
        t9<T> t9Var = this.a;
        if (t9Var != 0) {
            Object objJ = t9Var.j(a2Var, (Flow<? extends T>) flow2, continuation);
            return objJ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJ : Unit.INSTANCE;
        }
        if (IntrinsicsKt.getCOROUTINE_SUSPENDED() == null) {
            return null;
        }
        return Unit.INSTANCE;
    }
}
