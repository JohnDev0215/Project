package com.spotify.musicappplatform.state.processor;

import androidx.annotation.Keep;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AppLifecycleServiceBinder {

    @Keep
    public static final AppLifecycleServiceBinder BACKGROUND;

    @Keep
    public static final AppLifecycleServiceBinder CONNECT;

    @Keep
    public static final AppLifecycleServiceBinder COSMOS;

    @Keep
    public static final AppLifecycleServiceBinder TEST;
    public static final /* synthetic */ AppLifecycleServiceBinder[] c;

    static {
        AppLifecycleServiceBinder appLifecycleServiceBinder = new AppLifecycleServiceBinder("TEST", 0);
        TEST = appLifecycleServiceBinder;
        AppLifecycleServiceBinder appLifecycleServiceBinder2 = new AppLifecycleServiceBinder("COSMOS", 1);
        COSMOS = appLifecycleServiceBinder2;
        AppLifecycleServiceBinder appLifecycleServiceBinder3 = new AppLifecycleServiceBinder("CONNECT", 2);
        CONNECT = appLifecycleServiceBinder3;
        AppLifecycleServiceBinder appLifecycleServiceBinder4 = new AppLifecycleServiceBinder("BACKGROUND", 3);
        BACKGROUND = appLifecycleServiceBinder4;
        c = new AppLifecycleServiceBinder[]{appLifecycleServiceBinder, appLifecycleServiceBinder2, appLifecycleServiceBinder3, appLifecycleServiceBinder4};
    }

    public static AppLifecycleServiceBinder valueOf(String str) {
        return (AppLifecycleServiceBinder) Enum.valueOf(AppLifecycleServiceBinder.class, str);
    }

    public static AppLifecycleServiceBinder[] values() {
        return (AppLifecycleServiceBinder[]) c.clone();
    }
}
