package com.byd.overseaappstore.module.ability.util.notify.alone.line;

import spi.ll;
import spi.o9;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciDispatchMgr_Factory implements ll {
    private final ll<o9> executorHciCacheLineProvider;
    private final ll<ExecutorHciLineDispatch> executorHciLineDispatchProvider;

    public ExecutorHciDispatchMgr_Factory(ll<o9> llVar, ll<ExecutorHciLineDispatch> llVar2) {
        this.executorHciCacheLineProvider = llVar;
        this.executorHciLineDispatchProvider = llVar2;
    }

    public static ExecutorHciDispatchMgr_Factory create(ll<o9> llVar, ll<ExecutorHciLineDispatch> llVar2) {
        return new ExecutorHciDispatchMgr_Factory(llVar, llVar2);
    }

    public static ExecutorHciDispatchMgr newInstance(o9 o9Var, ExecutorHciLineDispatch executorHciLineDispatch) {
        return new ExecutorHciDispatchMgr(o9Var, executorHciLineDispatch);
    }

    @Override // spi.ll
    public ExecutorHciDispatchMgr get() {
        return newInstance(this.executorHciCacheLineProvider.get(), this.executorHciLineDispatchProvider.get());
    }
}
