package com.byd.overseaappstore.module.ability.util.notify.router.gear;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorNotPGearState_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorNotPGearState_Factory INSTANCE = new ExecutorNotPGearState_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorNotPGearState_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorNotPGearState newInstance() {
        return new ExecutorNotPGearState();
    }

    @Override // spi.ll
    public ExecutorNotPGearState get() {
        return newInstance();
    }
}
