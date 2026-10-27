package com.amazon.music.config;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class ConfigurationFactory {
    private static IndigoConfiguration sConfigurationInstance;

    public static synchronized Configuration getInstance(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        if (sConfigurationInstance == null) {
            sConfigurationInstance = new IndigoConfiguration(context, configurationLoader, attributes);
        }
        return sConfigurationInstance;
    }

    public static synchronized void notifyWeblabControllerInitComplete(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        ((IndigoConfiguration) getInstance(context, configurationLoader, attributes)).setIsWebLabControllerInitComplete(true);
    }

    public static synchronized void notifyUserInfoReady(Context context, ConfigurationLoader configurationLoader, Attributes attributes) {
        ((IndigoConfiguration) getInstance(context, configurationLoader, attributes)).setIsUserInfoReady(true);
    }
}
