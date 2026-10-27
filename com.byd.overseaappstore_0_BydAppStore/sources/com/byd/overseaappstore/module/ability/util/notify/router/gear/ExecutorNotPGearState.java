package com.byd.overseaappstore.module.ability.util.notify.router.gear;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import spi.ct;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/ExecutorNotPGearState;", "", "()V", "notPGearStateDataFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/byd/overseaappstore/module/ability/util/notify/router/gear/NotPGearState;", "getNotPGearStateDataFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "notifyNotPGearStateData", "", "notPGearState", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutorNotPGearState {

    @NotNull
    private final MutableStateFlow<NotPGearState> notPGearStateDataFlow = StateFlowKt.MutableStateFlow(new NotPGearState(true));

    @NotNull
    public final MutableStateFlow<NotPGearState> getNotPGearStateDataFlow() {
        return this.notPGearStateDataFlow;
    }

    public final void notifyNotPGearStateData(@NotNull NotPGearState notPGearState) {
        Intrinsics.checkNotNullParameter(notPGearState, "notPGearState");
        this.notPGearStateDataFlow.setValue(notPGearState);
        ct.a("executor notifyNotPGearStateData notPGearState: " + notPGearState, new Object[0]);
    }
}
