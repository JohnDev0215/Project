package com.android.launcher3.compat;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.launcher3.LauncherAppWidgetProviderInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AppWidgetManagerCompat {
    private static AppWidgetManagerCompat sInstance;
    private static final Object sInstanceLock = new Object();
    final AppWidgetManager mAppWidgetManager;
    final Context mContext;

    public abstract boolean bindAppWidgetIdIfAllowed(int appWidgetId, AppWidgetProviderInfo info, Bundle options);

    public abstract LauncherAppWidgetProviderInfo findProvider(ComponentName provider, UserHandle user);

    public abstract List<AppWidgetProviderInfo> getAllProviders(PackageUserKey packageUser);

    public abstract HashMap<ComponentKey, AppWidgetProviderInfo> getAllProvidersMap();

    public static AppWidgetManagerCompat getInstance(Context context) {
        AppWidgetManagerCompat appWidgetManagerCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                if (Utilities.ATLEAST_OREO) {
                    sInstance = new AppWidgetManagerCompatVO(context.getApplicationContext());
                } else {
                    sInstance = new AppWidgetManagerCompatVL(context.getApplicationContext());
                }
            }
            appWidgetManagerCompat = sInstance;
        }
        return appWidgetManagerCompat;
    }

    AppWidgetManagerCompat(Context context) {
        this.mContext = context;
        this.mAppWidgetManager = AppWidgetManager.getInstance(context);
    }

    public LauncherAppWidgetProviderInfo getLauncherAppWidgetInfo(int appWidgetId) {
        AppWidgetProviderInfo appWidgetInfo = this.mAppWidgetManager.getAppWidgetInfo(appWidgetId);
        if (appWidgetInfo == null) {
            return null;
        }
        return LauncherAppWidgetProviderInfo.fromProviderInfo(this.mContext, appWidgetInfo);
    }
}
