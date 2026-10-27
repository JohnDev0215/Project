package com.byd.overseaappstore.module.ability.util.notify.alone.task;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorTaskSchedule_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorTaskSchedule_Factory INSTANCE = new ExecutorTaskSchedule_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorTaskSchedule_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorTaskSchedule newInstance() {
        return new ExecutorTaskSchedule();
    }

    @Override // spi.ll
    public ExecutorTaskSchedule get() {
        return newInstance();
    }
}
