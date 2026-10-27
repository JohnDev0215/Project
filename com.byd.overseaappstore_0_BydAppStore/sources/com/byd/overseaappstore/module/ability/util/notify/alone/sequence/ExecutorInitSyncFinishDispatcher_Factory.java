package com.byd.overseaappstore.module.ability.util.notify.alone.sequence;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorInitSyncFinishDispatcher_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorInitSyncFinishDispatcher_Factory INSTANCE = new ExecutorInitSyncFinishDispatcher_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorInitSyncFinishDispatcher_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorInitSyncFinishDispatcher newInstance() {
        return new ExecutorInitSyncFinishDispatcher();
    }

    @Override // spi.ll
    public ExecutorInitSyncFinishDispatcher get() {
        return newInstance();
    }
}
