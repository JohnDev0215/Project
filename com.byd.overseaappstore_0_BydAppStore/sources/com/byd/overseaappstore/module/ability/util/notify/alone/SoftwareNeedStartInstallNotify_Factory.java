package com.byd.overseaappstore.module.ability.util.notify.alone;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class SoftwareNeedStartInstallNotify_Factory implements ll {

    public static final class InstanceHolder {
        private static final SoftwareNeedStartInstallNotify_Factory INSTANCE = new SoftwareNeedStartInstallNotify_Factory();

        private InstanceHolder() {
        }
    }

    public static SoftwareNeedStartInstallNotify_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SoftwareNeedStartInstallNotify newInstance() {
        return new SoftwareNeedStartInstallNotify();
    }

    @Override // spi.ll
    public SoftwareNeedStartInstallNotify get() {
        return newInstance();
    }
}
