package com.byd.overseaappstore.module.ability.util.notify.alone.language;

import spi.ll;

/* JADX INFO: loaded from: classes.dex */
public final class LanguageChangeStateMgr_Factory implements ll {

    public static final class InstanceHolder {
        private static final LanguageChangeStateMgr_Factory INSTANCE = new LanguageChangeStateMgr_Factory();

        private InstanceHolder() {
        }
    }

    public static LanguageChangeStateMgr_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LanguageChangeStateMgr newInstance() {
        return new LanguageChangeStateMgr();
    }

    @Override // spi.ll
    public LanguageChangeStateMgr get() {
        return newInstance();
    }
}
