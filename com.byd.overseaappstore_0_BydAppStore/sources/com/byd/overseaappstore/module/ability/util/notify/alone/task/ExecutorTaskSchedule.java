package com.byd.overseaappstore.module.ability.util.notify.alone.task;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import spi.a2;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0013\u0010\u0014J.\u0010\n\u001a\u00020\t2&\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00040\u0002R:\u0010\f\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00040\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR=\u0010\u000f\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00020\u0003\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00040\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/task/ExecutorTaskSchedule;", "", "Lkotlin/Pair;", "Lspi/a2;", "Lkotlin/Triple;", "", "", "Lcom/byd/overseaappstore/module/ability/util/download/data/SoftwareOptParam;", "taskProcessPair", "", "notifyTaskProcess", "Lkotlinx/coroutines/flow/MutableStateFlow;", "_taskFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/StateFlow;", "taskFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getTaskFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ExecutorTaskSchedule {

    @NotNull
    private final MutableStateFlow<Pair<a2, Triple<String, Integer, SoftwareOptParam>>> _taskFlow;

    @NotNull
    private final StateFlow<Pair<a2, Triple<String, Integer, SoftwareOptParam>>> taskFlow;

    public ExecutorTaskSchedule() {
        MutableStateFlow<Pair<a2, Triple<String, Integer, SoftwareOptParam>>> MutableStateFlow = StateFlowKt.MutableStateFlow(new Pair(new a2((byte) 0, (byte) 0, (byte) 0, 7, null), new Triple("", 0, null)));
        this._taskFlow = MutableStateFlow;
        this.taskFlow = MutableStateFlow;
    }

    @NotNull
    public final StateFlow<Pair<a2, Triple<String, Integer, SoftwareOptParam>>> getTaskFlow() {
        return this.taskFlow;
    }

    public final void notifyTaskProcess(@NotNull Pair<a2, Triple<String, Integer, SoftwareOptParam>> taskProcessPair) {
        Intrinsics.checkNotNullParameter(taskProcessPair, "taskProcessPair");
        this._taskFlow.setValue(taskProcessPair);
    }
}
