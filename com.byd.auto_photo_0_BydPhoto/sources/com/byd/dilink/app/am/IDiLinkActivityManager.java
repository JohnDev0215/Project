package com.byd.dilink.app.am;

import android.app.ActivityManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkActivityManager {
    List<ActivityManager.RunningAppProcessInfo> getRunningAppProcesses();

    String getTopPackageName();

    void killApplicationEx(String str, int i, int i2, String str2);

    void killApplicationProcess(String str, int i);

    void registerActivityListener(IDiLinkActivityListener iDiLinkActivityListener);

    void registerActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener);

    void registerActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener);

    void registerProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver);

    void scheduleApplicationInfoChanged(List<String> list, int i);

    void unregisterActivityListener(IDiLinkActivityListener iDiLinkActivityListener);

    void unregisterActivityTopListener(IDiLinkActivityTopListener iDiLinkActivityTopListener);

    void unregisterActivityTopListenerMultiDisplay(IDiLinkMultiDisplayActivityTopListener iDiLinkMultiDisplayActivityTopListener);

    void unregisterProcessObserver(IDiLinkProcessObserver iDiLinkProcessObserver);
}
