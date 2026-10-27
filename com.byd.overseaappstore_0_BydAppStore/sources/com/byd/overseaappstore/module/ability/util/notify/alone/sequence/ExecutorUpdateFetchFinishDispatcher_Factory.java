package com.byd.overseaappstore.module.ability.util.notify.alone.sequence;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorUpdateFetchFinishDispatcher_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorUpdateFetchFinishDispatcher_Factory INSTANCE = new ExecutorUpdateFetchFinishDispatcher_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorUpdateFetchFinishDispatcher_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorUpdateFetchFinishDispatcher newInstance() {
        return new ExecutorUpdateFetchFinishDispatcher();
    }

    @Override // spi.ll
    public ExecutorUpdateFetchFinishDispatcher get() {
        return newInstance();
    }
}
