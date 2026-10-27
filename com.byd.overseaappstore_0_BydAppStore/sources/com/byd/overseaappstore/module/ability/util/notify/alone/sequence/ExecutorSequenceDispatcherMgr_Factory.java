package com.byd.overseaappstore.module.ability.util.notify.alone.sequence;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorSequenceDispatcherMgr_Factory implements ll {
    private final ll<ExecutorInitSyncFinishDispatcher> initSyncFinishDispatcherProvider;
    private final ll<ExecutorUpdateFetchFinishDispatcher> updateFetchFinishDispatcherProvider;

    public ExecutorSequenceDispatcherMgr_Factory(ll<ExecutorInitSyncFinishDispatcher> llVar, ll<ExecutorUpdateFetchFinishDispatcher> llVar2) {
        this.initSyncFinishDispatcherProvider = llVar;
        this.updateFetchFinishDispatcherProvider = llVar2;
    }

    public static ExecutorSequenceDispatcherMgr_Factory create(ll<ExecutorInitSyncFinishDispatcher> llVar, ll<ExecutorUpdateFetchFinishDispatcher> llVar2) {
        return new ExecutorSequenceDispatcherMgr_Factory(llVar, llVar2);
    }

    public static ExecutorSequenceDispatcherMgr newInstance(ExecutorInitSyncFinishDispatcher executorInitSyncFinishDispatcher, ExecutorUpdateFetchFinishDispatcher executorUpdateFetchFinishDispatcher) {
        return new ExecutorSequenceDispatcherMgr(executorInitSyncFinishDispatcher, executorUpdateFetchFinishDispatcher);
    }

    @Override // spi.ll
    public ExecutorSequenceDispatcherMgr get() {
        return newInstance(this.initSyncFinishDispatcherProvider.get(), this.updateFetchFinishDispatcherProvider.get());
    }
}
