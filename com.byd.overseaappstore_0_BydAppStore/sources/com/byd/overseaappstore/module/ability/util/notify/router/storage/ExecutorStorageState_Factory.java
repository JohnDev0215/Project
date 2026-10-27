package com.byd.overseaappstore.module.ability.util.notify.router.storage;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorStorageState_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorStorageState_Factory INSTANCE = new ExecutorStorageState_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorStorageState_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorStorageState newInstance() {
        return new ExecutorStorageState();
    }

    @Override // spi.ll
    public ExecutorStorageState get() {
        return newInstance();
    }
}
