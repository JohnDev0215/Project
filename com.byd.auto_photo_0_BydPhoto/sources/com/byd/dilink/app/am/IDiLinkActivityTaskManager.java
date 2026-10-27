package com.byd.dilink.app.am;

import android.app.ActivityManager;
import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkActivityTaskManager {
    IDiLinkParceledListSlice<ActivityManager.RecentTaskInfo> getRecentTasksByDisplay(int i, int i2, boolean z);

    IDiLinkTaskSnapShot getTaskCurrentSnapshot(int i);

    List<ActivityManager.RunningTaskInfo> getTasksByDisplay(int i, boolean z, boolean z2, boolean z3);

    void removePackageActivities(@NonNull String str);

    void swapSplitUpdateEndPkg();
}
