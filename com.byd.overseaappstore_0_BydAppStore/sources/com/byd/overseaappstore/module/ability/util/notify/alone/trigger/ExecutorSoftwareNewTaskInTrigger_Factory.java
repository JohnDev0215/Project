package com.byd.overseaappstore.module.ability.util.notify.alone.trigger;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class ExecutorSoftwareNewTaskInTrigger_Factory implements ll {

    public static final class InstanceHolder {
        private static final ExecutorSoftwareNewTaskInTrigger_Factory INSTANCE = new ExecutorSoftwareNewTaskInTrigger_Factory();

        private InstanceHolder() {
        }
    }

    public static ExecutorSoftwareNewTaskInTrigger_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ExecutorSoftwareNewTaskInTrigger newInstance() {
        return new ExecutorSoftwareNewTaskInTrigger();
    }

    @Override // spi.ll
    public ExecutorSoftwareNewTaskInTrigger get() {
        return newInstance();
    }
}
