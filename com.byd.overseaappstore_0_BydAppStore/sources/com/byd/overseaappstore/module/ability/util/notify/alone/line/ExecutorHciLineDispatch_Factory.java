package com.byd.overseaappstore.module.ability.util.notify.alone.line;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorHciLineDispatch_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorHciLineDispatch_Factory INSTANCE = new ExecutorHciLineDispatch_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorHciLineDispatch_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorHciLineDispatch newInstance() {
        return new ExecutorHciLineDispatch();
    }

    @Override // spi.ll
    public ExecutorHciLineDispatch get() {
        return newInstance();
    }
}
