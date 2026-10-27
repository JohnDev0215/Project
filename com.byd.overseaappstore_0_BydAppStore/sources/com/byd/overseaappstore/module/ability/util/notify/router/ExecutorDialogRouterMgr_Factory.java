package com.byd.overseaappstore.module.ability.util.notify.router;

import com.byd.overseaappstore.module.ability.util.notify.router.gear.ExecutorNotPGearState;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.ExecutorIUUState;
import com.byd.overseaappstore.module.ability.util.notify.router.net.ExecutorNetContextState;
import com.byd.overseaappstore.module.ability.util.notify.router.storage.ExecutorStorageState;
import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorDialogRouterMgr_Factory implements ll {
    private final ll<ExecutorIUUState> executorIuuStateProvider;
    private final ll<ExecutorNetContextState> executorNetContextStateProvider;
    private final ll<ExecutorNotPGearState> executorNotPGearStateProvider;
    private final ll<ExecutorStorageState> executorStorageStateProvider;

    public ExecutorDialogRouterMgr_Factory(ll<ExecutorNetContextState> llVar, ll<ExecutorNotPGearState> llVar2, ll<ExecutorStorageState> llVar3, ll<ExecutorIUUState> llVar4) {
        this.executorNetContextStateProvider = llVar;
        this.executorNotPGearStateProvider = llVar2;
        this.executorStorageStateProvider = llVar3;
        this.executorIuuStateProvider = llVar4;
    }

    public static ExecutorDialogRouterMgr_Factory create(ll<ExecutorNetContextState> llVar, ll<ExecutorNotPGearState> llVar2, ll<ExecutorStorageState> llVar3, ll<ExecutorIUUState> llVar4) {
        return new ExecutorDialogRouterMgr_Factory(llVar, llVar2, llVar3, llVar4);
    }

    public static ExecutorDialogRouterMgr newInstance(ExecutorNetContextState executorNetContextState, ExecutorNotPGearState executorNotPGearState, ExecutorStorageState executorStorageState, ExecutorIUUState executorIUUState) {
        return new ExecutorDialogRouterMgr(executorNetContextState, executorNotPGearState, executorStorageState, executorIUUState);
    }

    @Override // spi.ll
    public ExecutorDialogRouterMgr get() {
        return newInstance(this.executorNetContextStateProvider.get(), this.executorNotPGearStateProvider.get(), this.executorStorageStateProvider.get(), this.executorIuuStateProvider.get());
    }
}
