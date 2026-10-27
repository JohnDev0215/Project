package com.android.launcher3;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.model.LoaderTask;
import com.android.launcher3.provider.RestoreDbTask;
import com.android.launcher3.util.ContentWriter;

/* JADX INFO: loaded from: classes4.dex */
public class AppWidgetsRestoredReceiver extends BroadcastReceiver {
    private static final String TAG = "AWRestoredReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (AppWidgetManager.ACTION_APPWIDGET_HOST_RESTORED.equals(intent.getAction())) {
            int intExtra = intent.getIntExtra(AppWidgetManager.EXTRA_HOST_ID, 0);
            Log.d(TAG, "Widget ID map received for host:" + intExtra);
            if (intExtra != 1024) {
                return;
            }
            int[] intArrayExtra = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_OLD_IDS);
            int[] intArrayExtra2 = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
            if (intArrayExtra != null && intArrayExtra2 != null && intArrayExtra.length == intArrayExtra2.length) {
                RestoreDbTask.setRestoredAppWidgetIds(context, intArrayExtra, intArrayExtra2);
            } else {
                Log.e(TAG, "Invalid host restored received");
            }
        }
    }

    public static void restoreAppWidgetIds(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        LauncherAppWidgetHost launcherAppWidgetHost = new LauncherAppWidgetHost(context);
        if (!RestoreDbTask.isPending(context)) {
            Log.e(TAG, "Skipping widget ID remap as DB already in use");
            for (int i : newWidgetIds) {
                Log.d(TAG, "Deleting widgetId: " + i);
                launcherAppWidgetHost.deleteAppWidgetId(i);
            }
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (int i2 = 0; i2 < oldWidgetIds.length; i2++) {
            Log.i(TAG, "Widget state restore id " + oldWidgetIds[i2] + " => " + newWidgetIds[i2]);
            int i3 = LoaderTask.isValidProvider(appWidgetManager.getAppWidgetInfo(newWidgetIds[i2])) ? 4 : 2;
            String[] strArr = {Integer.toString(oldWidgetIds[i2])};
            if (new ContentWriter(context, new ContentWriter.CommitParams("appWidgetId=? and (restored & 1) = 1", strArr)).put("appWidgetId", Integer.valueOf(newWidgetIds[i2])).put("restored", Integer.valueOf(i3)).commit() == 0) {
                Cursor cursorQuery = contentResolver.query(LauncherSettings.Favorites.CONTENT_URI, new String[]{"appWidgetId"}, "appWidgetId=?", strArr, null);
                try {
                    if (!cursorQuery.moveToFirst()) {
                        launcherAppWidgetHost.deleteAppWidgetId(newWidgetIds[i2]);
                    }
                } finally {
                    cursorQuery.close();
                }
            }
        }
        LauncherAppState instanceNoCreate = LauncherAppState.getInstanceNoCreate();
        if (instanceNoCreate != null) {
            instanceNoCreate.getModel().forceReload();
        }
    }
}
