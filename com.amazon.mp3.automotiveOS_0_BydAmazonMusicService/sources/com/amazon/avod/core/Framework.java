package com.amazon.avod.core;

/* JADX INFO: loaded from: classes2.dex */
public class Framework {
    private static volatile boolean sIsDebugConfigurationEnabled = false;

    private Framework() {
    }

    public static boolean isDebugConfigurationEnabled() {
        return sIsDebugConfigurationEnabled;
    }
}
