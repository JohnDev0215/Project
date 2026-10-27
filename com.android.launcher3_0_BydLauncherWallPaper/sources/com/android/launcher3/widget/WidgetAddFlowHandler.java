package com.android.launcher3.widget;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.util.PendingRequestArgs;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetAddFlowHandler implements Parcelable {
    public static final Parcelable.Creator<WidgetAddFlowHandler> CREATOR = new Parcelable.Creator<WidgetAddFlowHandler>() { // from class: com.android.launcher3.widget.WidgetAddFlowHandler.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WidgetAddFlowHandler createFromParcel(Parcel source) {
            return new WidgetAddFlowHandler(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WidgetAddFlowHandler[] newArray(int size) {
            return new WidgetAddFlowHandler[size];
        }
    };
    private final AppWidgetProviderInfo mProviderInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WidgetAddFlowHandler(AppWidgetProviderInfo providerInfo) {
        this.mProviderInfo = providerInfo;
    }

    protected WidgetAddFlowHandler(Parcel parcel) {
        this.mProviderInfo = AppWidgetProviderInfo.CREATOR.createFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mProviderInfo.writeToParcel(parcel, i);
    }

    public void startBindFlow(Launcher launcher, int appWidgetId, ItemInfo info, int requestCode) {
        launcher.setWaitingForResult(PendingRequestArgs.forWidgetInfo(appWidgetId, this, info));
        launcher.getAppWidgetHost().startBindFlow(launcher, appWidgetId, this.mProviderInfo, requestCode);
    }

    public boolean startConfigActivity(Launcher launcher, LauncherAppWidgetInfo info, int requestCode) {
        return startConfigActivity(launcher, info.appWidgetId, info, requestCode);
    }

    public boolean startConfigActivity(Launcher launcher, int appWidgetId, ItemInfo info, int requestCode) {
        if (!needsConfigure()) {
            return false;
        }
        launcher.setWaitingForResult(PendingRequestArgs.forWidgetInfo(appWidgetId, this, info));
        launcher.getAppWidgetHost().startConfigActivity(launcher, appWidgetId, requestCode);
        return true;
    }

    public boolean needsConfigure() {
        return this.mProviderInfo.configure != null;
    }

    public LauncherAppWidgetProviderInfo getProviderInfo(Context context) {
        return LauncherAppWidgetProviderInfo.fromProviderInfo(context, this.mProviderInfo);
    }
}
