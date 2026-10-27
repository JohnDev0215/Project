package com.amazon.mp3.util.health;

/* JADX INFO: loaded from: classes4.dex */
public interface AppHealthEventMonitor {
    void appCrash();

    void appOpened(final String currentVersion);

    void customerSupportContacted();
}
