package com.android.launcher3;

import android.appwidget.AppWidgetHostView;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Process;
import com.android.launcher3.model.PackageItemInfo;
import com.android.launcher3.util.ContentWriter;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppWidgetInfo extends ItemInfo {
    public static final int CUSTOM_WIDGET_ID = -100;
    public static final int FLAG_DIRECT_CONFIG = 32;
    public static final int FLAG_ID_ALLOCATED = 16;
    public static final int FLAG_ID_NOT_VALID = 1;
    public static final int FLAG_PROVIDER_NOT_READY = 2;
    public static final int FLAG_RESTORE_STARTED = 8;
    public static final int FLAG_UI_NOT_READY = 4;
    public static final int NO_ID = -1;
    public static final int RESTORE_COMPLETED = 0;
    public int appWidgetId;
    public Intent bindOptions;
    public int installProgress;
    private boolean mHasNotifiedInitialWidgetSizeChanged;
    public PackageItemInfo pendingItemInfo;
    public ComponentName providerName;
    public int restoreStatus;

    public LauncherAppWidgetInfo(int appWidgetId, ComponentName providerName) {
        this.appWidgetId = -1;
        this.installProgress = -1;
        this.appWidgetId = appWidgetId;
        this.providerName = providerName;
        if (isCustomWidget()) {
            this.itemType = 5;
        } else {
            this.itemType = 4;
        }
        this.spanX = -1;
        this.spanY = -1;
        this.user = Process.myUserHandle();
        this.restoreStatus = 0;
    }

    public LauncherAppWidgetInfo() {
        this.appWidgetId = -1;
        this.installProgress = -1;
        this.itemType = 4;
    }

    public boolean isCustomWidget() {
        return this.appWidgetId <= -100;
    }

    @Override // com.android.launcher3.ItemInfo
    public void onAddToDatabase(ContentWriter writer) {
        super.onAddToDatabase(writer);
        writer.put("appWidgetId", Integer.valueOf(this.appWidgetId)).put("appWidgetProvider", this.providerName.flattenToString()).put("restored", Integer.valueOf(this.restoreStatus)).put("intent", this.bindOptions);
    }

    void onBindAppWidget(Launcher launcher, AppWidgetHostView hostView) {
        if (this.mHasNotifiedInitialWidgetSizeChanged) {
            return;
        }
        AppWidgetResizeFrame.updateWidgetSizeRanges(hostView, launcher, this.spanX, this.spanY);
        this.mHasNotifiedInitialWidgetSizeChanged = true;
    }

    @Override // com.android.launcher3.ItemInfo
    protected String dumpProperties() {
        return super.dumpProperties() + " appWidgetId=" + this.appWidgetId;
    }

    public final boolean isWidgetIdAllocated() {
        int i = this.restoreStatus;
        return (i & 1) == 0 || (i & 16) == 16;
    }

    public final boolean hasRestoreFlag(int flag) {
        return (this.restoreStatus & flag) == flag;
    }
}
