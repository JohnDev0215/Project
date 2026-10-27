package com.byd.overseaappstore.module.ability.util.notify.router.iuu;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorIUUState_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorIUUState_Factory INSTANCE = new ExecutorIUUState_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorIUUState_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorIUUState newInstance() {
        return new ExecutorIUUState();
    }

    @Override // spi.ll
    public ExecutorIUUState get() {
        return newInstance();
    }
}
