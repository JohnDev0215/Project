package com.byd.dilink.app.am;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkProcessObserver {
    void onForegroundActivitiesChanged(int i, int i2, boolean z);

    void onForegroundServicesChanged(int i, int i2, int i3);

    void onProcessDied(int i, int i2);
}
