package com.byd.vrsettings.eventbus;

import android.content.res.Configuration;

/* JADX INFO: loaded from: classes.dex */
public class OnConfigChangeEvent {
    private Configuration newConfig;

    public OnConfigChangeEvent(Configuration configuration) {
        this.newConfig = configuration;
    }

    public Configuration getNewConfig() {
        return this.newConfig;
    }
}
