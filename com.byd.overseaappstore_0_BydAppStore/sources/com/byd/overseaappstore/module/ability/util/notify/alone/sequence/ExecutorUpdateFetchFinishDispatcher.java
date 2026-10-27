package com.byd.overseaappstore.module.ability.util.notify.alone.sequence;

import kotlin.Metadata;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorUpdateFetchFinishDispatcher;", "", "()V", "_updateFetchFinishFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "updateFetchFinishFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getUpdateFetchFinishFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "notifyUpdateFetchFinishDispatch", "", "finish", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorUpdateFetchFinishDispatcher {

    @NotNull
    private final MutableStateFlow<Boolean> _updateFetchFinishFlow;

    @NotNull
    private final StateFlow<Boolean> updateFetchFinishFlow;

    public ExecutorUpdateFetchFinishDispatcher() {
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._updateFetchFinishFlow = MutableStateFlow;
        this.updateFetchFinishFlow = MutableStateFlow;
    }

    @NotNull
    public final StateFlow<Boolean> getUpdateFetchFinishFlow() {
        return this.updateFetchFinishFlow;
    }

    public final void notifyUpdateFetchFinishDispatch(boolean finish) {
        ct.a("executor notifyUpdateFetchFinishDispatch finish:" + finish + " ", new Object[0]);
        this._updateFetchFinishFlow.setValue(Boolean.valueOf(finish));
    }
}
